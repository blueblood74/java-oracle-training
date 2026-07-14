package main.java.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

import main.java.dao.MstCustomerTypeConversionDao;
import main.java.dao.SrcCustomerDao;
import main.java.dao.TgtCustomerDao;
import main.java.dto.TgtCustomerDto;
import main.java.util.Constants;

/**
 * 顧客移行サービスクラス
 *
 * @author Wataru Yamada
 */
public class CustomerMigrationService extends BaseService implements IMigrationService {

    /**
     * ロジックを実行する
     *
     * @param con DBコネクション
     * @param batchStartTime バッチ開始日時
     * @throws SQLException DBエラー発生時
     */
    @Override
    public void execute(Connection con, LocalDateTime batchStartTime) throws SQLException {

        String targetFrom = "SRC_CUSTOMER";
        String targetTo = "TGT_CUSTOMER";

        TgtCustomerDao tgtDao = new TgtCustomerDao();
        if (tgtDao.isExistRecord(con)) {
            writeServiceSkipLog(targetFrom, targetTo);
            return;
        }

        LocalDateTime serviceStartTime = LocalDateTime.now(Constants.ZONE_JST);
        writeServiceStartLog(targetFrom, targetTo, serviceStartTime);

        int offset = 0;
        SrcCustomerDao srcDao = new SrcCustomerDao();
        try {
            MstCustomerTypeConversionDao mstDao = new MstCustomerTypeConversionDao();
            Map<String, Long> convList = mstDao.getConverionList(con);

            while (true) {
                List<Map<String, Object>> chunk = srcDao.findByRange(con, offset, Constants.COMMIT_SIZE);
                if (chunk.isEmpty()) {
                    break;
                }

                insertRecords(con, tgtDao, chunk, convList, batchStartTime);
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
    private void insertRecords(Connection con, TgtCustomerDao tgtDao, List<Map<String, Object>> chunk,
            Map<String, Long> convList, LocalDateTime batchStartTime) throws SQLException {

        for (Map<String, Object> row : chunk) {
            Long newCustomerTypeId = convList.get(row.get("CUSTOMER_TYPE_ID"));
            if (newCustomerTypeId == null) {
                logger.error("変換不能 CUSTOMER_ID: " + ((Number) row.get("CUSTOMER_ID")).longValue());
                continue;
            }

            // DTOに変換して登録処理
            TgtCustomerDto dto = new TgtCustomerDto(
                    row,
                    newCustomerTypeId,
                    Constants.DEFAULT_DELETE_FLAG,
                    Constants.DEFAULT_CREATED_BY,
                    batchStartTime,
                    Constants.DEFAULT_UPDATED_BY,
                    Constants.DEFAULT_UPDATED_AT);

            tgtDao.insert(con, dto);
        }
    }
}
