package main.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import main.java.dto.TgtContractDto;

/**
 * 移行先 - 契約Daoクラス
 *
 * @author Wataru Yamada
 */
public class TgtContractDao extends BaseDao {

    /**
     * レコードが1件以上存在するか確認する
     *
     * @param con DBコネクション
     * @return 判定結果(true:存在する false:存在しない)
     * @throws SQLException DBエラー発生時
     */
    public boolean isExistRecord(Connection con) throws SQLException {

        return super.isExistRecord(con, "TGT_CONTRACT");
    }

    /**
     * 顧客情報を登録する
     *
     * @param con DBコネクション
     * @param dto 移行先 顧客DTOクラス
     * @return 登録件数
     * @throws SQLException DBエラー発生時
     */
    public int insert(Connection con, TgtContractDto dto) throws SQLException {

        String sql = "INSERT INTO TGT_CONTRACT ("
                + "CONTRACT_ID, CUSTOMER_ID, STATUS_CODE, START_DATE, END_DATE, "
                + "DELETE_FLAG, CREATED_BY, CREATED_AT, UPDATED_BY, UPDATED_AT"
                + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, dto.contractId);
            ps.setLong(2, dto.customerId);
            ps.setString(3, dto.statusCode);
            ps.setTimestamp(4, toTimestamp(dto.startDate));
            ps.setTimestamp(5, toTimestamp(dto.endDate));
            ps.setInt(6, dto.deleteFlag);
            ps.setString(7, dto.createdBy);
            ps.setTimestamp(8, toTimestamp(dto.createdAt));
            ps.setString(9, dto.updatedBy);
            ps.setTimestamp(10, toTimestamp(dto.updatedAt));

            return ps.executeUpdate();
        }
    }
}
