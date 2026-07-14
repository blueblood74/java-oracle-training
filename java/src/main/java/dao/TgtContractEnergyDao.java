package main.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import main.java.dto.TgtContractEnergyDto;

/**
 * 移行先 - 契約エネルギーDaoクラス
 *
 * @author Wataru Yamada
 */
public class TgtContractEnergyDao extends BaseDao {

    /**
     * レコードが1件以上存在するか確認する
     *
     * @param con DBコネクション
     * @return 判定結果(true:存在する false:存在しない)
     * @throws SQLException DBエラー発生時
     */
    public boolean isExistRecord(Connection con) throws SQLException {

        return super.isExistRecord(con, "TGT_CONTRACT_ENERGY");
    }

    /**
     * 契約エネルギー情報を登録する
     *
     * @param con DBコネクション
     * @param dto 移行先 契約エネルギータイプDTOクラス
     * @return 登録件数
     * @throws SQLException DBエラー発生時
     */
    public int insert(Connection con, TgtContractEnergyDto dto) throws SQLException {

        String sql = "INSERT INTO TGT_CONTRACT_ENERGY ("
                + "CONTRACT_ENERGY_ID, CONTRACT_ID, ENERGY_TYPE_ID, "
                + "DELETE_FLAG, CREATED_BY, CREATED_AT, UPDATED_BY, UPDATED_AT"
                + ") VALUES (SEQ_CONTRACT_ENERGY_ID.nextval, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, dto.contractId);
            ps.setLong(2, dto.energyTypeId);
            ps.setInt(3, dto.deleteFlag);
            ps.setString(4, dto.createdBy);
            ps.setTimestamp(5, toTimestamp(dto.createdAt));
            ps.setString(6, dto.updatedBy);
            ps.setTimestamp(7, toTimestamp(dto.updatedAt));

            return ps.executeUpdate();
        }
    }
}
