package main.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import main.java.dto.TgtCustomerDto;

/**
 * 移行先 - 顧客Daoクラス
 *
 * @author Wataru Yamada
 */
public class TgtCustomerDao extends BaseDao {

    /**
     * レコードが1件以上存在するか確認する
     *
     * @param con DBコネクション
     * @return 判定結果(true:存在する false:存在しない)
     * @throws SQLException DBエラー発生時
     */
    public boolean isExistRecord(Connection con) throws SQLException {

        return super.isExistRecord(con, "TGT_CUSTOMER");
    }

    /**
     * 顧客情報を登録する
     *
     * @param con DBコネクション
     * @param dto 移行先 顧客DTOクラス
     * @return 登録件数
     * @throws SQLException DBエラー発生時
     */
    public int insert(Connection con, TgtCustomerDto dto) throws SQLException {

        String sql = "INSERT INTO TGT_CUSTOMER ("
                + "CUSTOMER_ID, CUSTOMER_NAME, ADDRESS, CUSTOMER_TYPE_ID, DELETE_FLAG,"
                + "CREATED_BY, CREATED_AT, UPDATED_BY, UPDATED_AT"
                + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, dto.customerId);
            ps.setString(2, dto.customerName);
            ps.setString(3, dto.address);
            ps.setLong(4, dto.customerTypeId);
            ps.setInt(5, dto.deleteFlag);
            ps.setString(6, dto.createdBy);
            ps.setTimestamp(7, toTimestamp(dto.createdAt));
            ps.setString(8, dto.updatedBy);
            ps.setTimestamp(9, toTimestamp(dto.updatedAt));

            return ps.executeUpdate();
        }
    }
}
