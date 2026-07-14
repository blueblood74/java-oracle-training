package main.java.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import main.java.dao.MstEnergyTypeDao;
import main.java.dao.SrcBillingDetailDao;
import main.java.dao.TgtInvoiceDetailDao;
import main.java.dto.SrcBillingDetailDto;
import main.java.dto.TgtInvoiceDetailDto;
import main.java.util.Constants;

/**
 * 請求明細移行サービスクラス
 *
 * @author Wataru Yamada
 */
public class BillingDetailMigrationService extends BaseService implements IMigrationService {

    /**
     * ロジックを実行する
     *
     * @param con DBコネクション
     * @param batchStartTime バッチ開始日時
     * @throws SQLException DBエラー発生時
     */
    @Override
    public void execute(Connection con, LocalDateTime batchStartTime) throws SQLException {

        String targetFrom = "SRC_BILLING_DETAIL";
        String targetTo = "TGT_INVOICE_DETAIL";

        TgtInvoiceDetailDao tgtDao = new TgtInvoiceDetailDao();
        if (tgtDao.isExistRecord(con)) {
            writeServiceSkipLog(targetFrom, targetTo);
            return;
        }

        LocalDateTime serviceStartTime = LocalDateTime.now(Constants.ZONE_JST);
        writeServiceStartLog(targetFrom, targetTo, serviceStartTime);

        int offset = 0;
        SrcBillingDetailDao srcDao = new SrcBillingDetailDao();

        try {
            MstEnergyTypeDao mstDao = new MstEnergyTypeDao();
            Map<String, Long> statusList = mstDao.getList(con);

            while (true) {
                List<SrcBillingDetailDto> chunk = srcDao.findByRange(con, offset, Constants.COMMIT_SIZE);
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
    private void insertRecords(Connection con, TgtInvoiceDetailDao tgtDao, List<SrcBillingDetailDto> chunk,
            Map<String, Long> statusList, LocalDateTime batchStartTime) throws SQLException {

        for (SrcBillingDetailDto row : chunk) {
            Long energyTypeId = statusList.get(row.itemType);
            if (energyTypeId == null) {
                logger.error("変換不能 BILLING_DETAIL_ID: " + row.billingDetailId);
                continue;
            }

            TgtInvoiceDetailDto dto = new TgtInvoiceDetailDto.Builder()
                    .invoiceDetailId(row.billingDetailId)
                    .invoiceId(row.billingId)
                    .energyTypeId(energyTypeId)
                    .usageQty(row.usageQty)
                    .unitPrice(row.unitPrice)
                    .amount(row.amount)
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
