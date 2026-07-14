package main.java.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;

import main.java.dao.MstInvoiceStatusDao;
import main.java.dao.SrcBillingDao;
import main.java.dao.TgtInvoiceDao;
import main.java.dto.SrcBillingDto;
import main.java.dto.TgtInvoiceDto;
import main.java.util.Constants;

/**
 * 請求移行サービスクラス
 *
 * @author Wataru Yamada
 */
public class BillingMigrationService extends BaseService implements IMigrationService {

    /**
     * ロジックを実行する
     *
     * @param con DBコネクション
     * @param batchStartTime バッチ開始日時
     * @throws SQLException DBエラー発生時
     */
    @Override
    public void execute(Connection con, LocalDateTime batchStartTime) throws SQLException {

        String targetFrom = "SRC_BILLING";
        String targetTo = "TGT_INVOICE";

        TgtInvoiceDao tgtDao = new TgtInvoiceDao();
        if (tgtDao.isExistRecord(con)) {
            writeServiceSkipLog(targetFrom, targetTo);
            return;
        }

        LocalDateTime serviceStartTime = LocalDateTime.now(Constants.ZONE_JST);
        writeServiceStartLog(targetFrom, targetTo, serviceStartTime);

        int offset = 0;
        SrcBillingDao srcDao = new SrcBillingDao();

        try {
            MstInvoiceStatusDao mstDao = new MstInvoiceStatusDao();
            Map<String, String> statusList = mstDao.getList(con);

            while (true) {
                List<SrcBillingDto> chunk = srcDao.findByRange(con, offset, Constants.COMMIT_SIZE);
                if (chunk.isEmpty()) {
                    break;
                }

                insertRecords(con, tgtDao, chunk, statusList, batchStartTime);
                con.commit();
                offset += chunk.size();
            }
        } catch (Exception e) {
            con.rollback();
            throw e;
        } finally {
            writeServiceEndLog(targetFrom, targetTo, serviceStartTime, offset);
        }
    }

    /**
     * 移行先テーブルにレコードを登録する
     *
     * @param con DBコネクション
     * @param tgtDao 移行先 - 顧客Daoクラス
     * @param chunk  移行元の顧客情報のリスト
     * @param convList 顧客種別変換マスタのデータ
     * @param batchStartTime バッチ開始日時
     * @throws SQLException DBエラー発生時
     */
    private void insertRecords(Connection con, TgtInvoiceDao tgtDao, List<SrcBillingDto> chunk,
            Map<String, String> statusList, LocalDateTime batchStartTime) throws SQLException {

        String statusCode = statusList.get("ISSUED");
        for (SrcBillingDto row : chunk) {
            if (statusCode == null) {
                logger.error("変換不能 BILLING_ID: " + row.billingId);
                continue;
            }

            // DTOに変換して登録処理
            LocalDate invoiceDate = YearMonth.of(
                    Integer.parseInt(row.billMonth.substring(0, 4)),
                    Integer.parseInt(row.billMonth.substring(4, 6))).atEndOfMonth();
            TgtInvoiceDto dto = new TgtInvoiceDto.Builder()
                    .invoiceId(row.billingId)
                    .contractId(row.contractId)
                    .invoiceDate(invoiceDate)
                    .totalAmount(row.totalAmount)
                    .statusId(Long.valueOf(statusCode))
                    .deleteFlag(Constants.DEFAULT_DELETE_FLAG)
                    .createdBy(Constants.DEFAULT_CREATED_BY)
                    .createdAt(batchStartTime)
                    .updatedBy(Constants.DEFAULT_UPDATED_BY)
                    .updatedAt(Constants.DEFAULT_UPDATED_AT)
                    .build();
            tgtDao.insert(con, dto);
        }
    }
}
