package main.java.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import main.java.dao.MstEnergyTypeDao;
import main.java.dao.MstInvoiceStatusDao;
import main.java.dao.SrcContractDao;
import main.java.dao.TgtContractDao;
import main.java.dao.TgtContractEnergyDao;
import main.java.dao.TgtContractRemarkADao;
import main.java.dao.TgtContractRemarkBDao;
import main.java.dto.SrcContractDto;
import main.java.dto.TgtContractDto;
import main.java.dto.TgtContractEnergyDto;
import main.java.dto.TgtContractRemarkADto;
import main.java.dto.TgtContractRemarkBDto;
import main.java.util.Constants;
import main.java.util.StringUtils;

/**
 * 契約移行サービスクラス
 *
 * @author Wataru Yamada
 */
public class ContractMigrationService extends BaseService implements IMigrationService {

    /**
     * ロジックを実行する
     *
     * @param con DBコネクション
     * @param batchStartTime バッチ開始日時
     * @throws SQLException DBエラー発生時
     */
    @Override
    public void execute(Connection con, LocalDateTime batchStartTime) throws SQLException {

        String targetFrom = "SRC_CONTRACT";
        String targetTo = "TGT_CONTRACT、TGT_CONTRACT_ENERGY、TGT_CONTRACT_REMARK_A、B";

        TgtContractDao tgtContractDao = new TgtContractDao();
        TgtContractEnergyDao tgtContractEnergyDao = new TgtContractEnergyDao();
        TgtContractRemarkADao tgtContractRemarkADao = new TgtContractRemarkADao();
        TgtContractRemarkBDao tgtContractRemarkBDao = new TgtContractRemarkBDao();

        if (isExistRecord(con, tgtContractDao, tgtContractEnergyDao, tgtContractRemarkADao, tgtContractRemarkBDao)) {
            writeServiceSkipLog(targetFrom, targetTo);
            return;
        }

        LocalDateTime serviceStartTime = LocalDateTime.now(Constants.ZONE_JST);
        writeServiceStartLog(targetFrom, targetTo, serviceStartTime);

        MstInvoiceStatusDao invoiceDao = new MstInvoiceStatusDao();
        Map<String, String> invoiceStatusList = invoiceDao.getList(con);

        MstEnergyTypeDao energyTypeDao = new MstEnergyTypeDao();
        Map<String, Long> energyTypeList = energyTypeDao.getList(con);

        int offset = 0;
        SrcContractDao srcDao = new SrcContractDao();

        try {
            while (true) {
                List<SrcContractDto> chunk = srcDao.findByRange(con, offset, Constants.COMMIT_SIZE);
                if (chunk.isEmpty()) {
                    break;
                }

                for (SrcContractDto row : chunk) {

                    String statusCode = invoiceStatusList.get("ISSUED");
                    if (statusCode == null) {
                        logger.error("変換不能 CONTRACT_ID: " + row.contractId);
                        continue;
                    }

                    migrationToTgtContract(con, tgtContractDao, row, statusCode, batchStartTime);

                    if (!migrationToTgtContractEnergy(con, tgtContractEnergyDao, row, energyTypeList, batchStartTime)) {
                        continue;
                    }

                    migrationToTgtContractRemarkA(con, tgtContractRemarkADao, row, batchStartTime);
                    migrationToTgtContractRemarkB(con, tgtContractRemarkBDao, row, batchStartTime);
                }

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
     * 移行先テーブルにデータが存在するか確認する
     *
     * @param con DBコネクション
     * @param dao1
     * @param dao2
     * @param dao3
     * @param dao4
     * @return 確認結果(true:存在する false:存在しない)
     * @throws SQLException DBエラー発生時 
     */
    private boolean isExistRecord(Connection con, TgtContractDao dao1, TgtContractEnergyDao dao2,
            TgtContractRemarkADao dao3, TgtContractRemarkBDao dao4) throws SQLException {

        return dao1.isExistRecord(con) ||
                dao2.isExistRecord(con) ||
                dao3.isExistRecord(con) ||
                dao4.isExistRecord(con);
    }

    /**
     * 移行元の契約から、移行先の契約へデータ移行を行う
     *
     * @param con DB接続
     * @param tgtDao 移行先 - 契約Dao
     * @param row 移行元 - 契約情報
     * @param statusCode ステータスコード
     * @param batchStartTime バッチ実行日時
     * @throws SQLException DBエラー発生時
     */
    private void migrationToTgtContract(Connection con, TgtContractDao tgtDao, SrcContractDto row,
            String statusCode,
            LocalDateTime batchStartTime) throws SQLException {

        TgtContractDto dto = new TgtContractDto.Builder()
                .contractId(row.contractId)
                .customerId(row.customerId)
                .statusCode(statusCode)
                .startDate(row.startDate)
                .endDate(row.endDate)
                .deleteFlag(Constants.DEFAULT_DELETE_FLAG)
                .createdBy(Constants.DEFAULT_CREATED_BY)
                .createdAt(batchStartTime)
                .updatedBy(Constants.DEFAULT_UPDATED_BY)
                .updatedAt(Constants.DEFAULT_UPDATED_AT)
                .build();

        tgtDao.insert(con, dto);
    }

    /**
     * 移行元の契約から、移行先の契約エネルギーへデータ移行を行う
     *
     * @param con DB接続
     * @param tgtDao 移行先 - 契約エネルギーDao
     * @param row 移行元 - 契約情報
     * @param energyTypeList エネルギータイプ一覧
     * @param batchStartTime バッチ実行日時
     * @throws SQLException DBエラー発生時
     */
    private boolean migrationToTgtContractEnergy(Connection con, TgtContractEnergyDao tgtDao, SrcContractDto row,
            Map<String, Long> energyTypeList,
            LocalDateTime batchStartTime) throws SQLException {

        Long energyTypeId = energyTypeList.get(row.energyType);
        if (energyTypeId == null) {
            logger.error("変換不能 CONTRACT_ID: " + row.energyType);
            return false;
        }

        // DTOに変換して登録処理
        TgtContractEnergyDto dto = new TgtContractEnergyDto.Builder()
                .contractId(row.contractId)
                .energyTypeId(energyTypeId)
                .deleteFlag(Constants.DEFAULT_DELETE_FLAG)
                .createdBy(Constants.DEFAULT_CREATED_BY)
                .createdAt(batchStartTime)
                .updatedBy(Constants.DEFAULT_UPDATED_BY)
                .updatedAt(Constants.DEFAULT_UPDATED_AT)
                .build();

        tgtDao.insert(con, dto);
        return true;
    }

    /**
     * 移行元の契約から、移行先の契約備考Aへデータ移行を行う
     *
     * @param con DB接続
     * @param tgtDao 移行先 - 契約備考A Dao
     * @param row 移行元 - 契約情報
     * @param batchStartTime バッチ実行日時
     * @throws SQLException DBエラー発生時
     */
    private void migrationToTgtContractRemarkA(Connection con, TgtContractRemarkADao tgtDao, SrcContractDto row,
            LocalDateTime batchStartTime) throws SQLException {

        TgtContractRemarkADto dto = new TgtContractRemarkADto.Builder()
                .remark1(StringUtils.truncateWithLog("REMARK1", row.remark1, 50, logger))
                .remark2(StringUtils.truncateWithLog("REMARK2", row.remark2, 75, logger))
                .remark3(StringUtils.truncateWithLog("REMARK3", row.remark3, 50, logger))
                .remark4(StringUtils.truncateWithLog("REMARK4", row.remark4, 100, logger))
                .remark5(StringUtils.truncateWithLog("REMARK5", row.remark5, 20, logger))
                .remark6(StringUtils.truncateWithLog("REMARK6", row.remark6, 20, logger))
                .remark7(StringUtils.truncateWithLog("REMARK7", row.remark7, 20, logger))
                .remark8(StringUtils.truncateWithLog("REMARK8", row.remark8, 20, logger))
                .remark9(StringUtils.truncateWithLog("REMARK9", row.remark9, 10, logger))
                .remark10(StringUtils.truncateWithLog("REMARK10", row.remark10, 200, logger))
                .deleteFlag(Constants.DEFAULT_DELETE_FLAG)
                .createdBy(Constants.DEFAULT_CREATED_BY)
                .createdAt(batchStartTime)
                .updatedBy(Constants.DEFAULT_UPDATED_BY)
                .updatedAt(Constants.DEFAULT_UPDATED_AT)
                .build();

        tgtDao.insert(con, dto);
    }

    /**
     * 移行元の契約から、移行先の契約備考Bへデータ移行を行う
     *
     * @param con DB接続
     * @param tgtDao 移行先 - 契約備考B Dao
     * @param row 移行元 - 契約情報
     * @param batchStartTime バッチ実行日時
     * @throws SQLException DBエラー発生時
     */
    private void migrationToTgtContractRemarkB(Connection con, TgtContractRemarkBDao tgtDao, SrcContractDto row,
            LocalDateTime batchStartTime) throws SQLException {

        TgtContractRemarkBDto dto = new TgtContractRemarkBDto.Builder()
                .remark11(StringUtils.truncateWithLog("REMARK11", row.remark11, 20, logger))
                .remark12(StringUtils.truncateWithLog("REMARK12", row.remark12, 200, logger))
                .remark13(StringUtils.truncateWithLog("REMARK13", row.remark13, 10, logger))
                .remark14(StringUtils.truncateWithLog("REMARK14", row.remark14, 50, logger))
                .remark15(StringUtils.truncateWithLog("REMARK15", row.remark15, 30, logger))
                .remark16(StringUtils.truncateWithLog("REMARK16", row.remark16, 50, logger))
                .remark17(StringUtils.truncateWithLog("REMARK17", row.remark17, 20, logger))
                .remark18(StringUtils.truncateWithLog("REMARK18", row.remark18, 50, logger))
                .remark19(StringUtils.truncateWithLog("REMARK19", row.remark19, 200, logger))
                .remark20(StringUtils.truncateWithLog("REMARK20", row.remark20, 100, logger))
                .deleteFlag(Constants.DEFAULT_DELETE_FLAG)
                .createdBy(Constants.DEFAULT_CREATED_BY)
                .createdAt(batchStartTime)
                .updatedBy(Constants.DEFAULT_UPDATED_BY)
                .updatedAt(Constants.DEFAULT_UPDATED_AT)
                .build();

        tgtDao.insert(con, dto);
    }
}
