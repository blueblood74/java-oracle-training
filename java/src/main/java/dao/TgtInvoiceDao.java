package main.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import main.java.dto.TgtInvoiceDto;


/**
 * 移行先 - 請求ヘッダDaoクラス
 *
 * @author Wataru Yamada
 */
public class TgtInvoiceDao extends BaseDao {

    /**
     * レコードが1件以上存在するか確認する
     *
     * @param con DBコネクション
     * @return 判定結果(true:存在する false:存在しない)
     * @throws SQLException DBエラー発生時
     */
    public boolean isExistRecord(Connection con) throws SQLException {

        return super.isExistRecord(con, "TGT_INVOICE");
    }

    /**
     * 請求ヘッダ情報を登録する
     *
     * @param con DBコネクション
     * @param dto 移行先 - 請求ヘッダDTOクラス
     * @return 登録件数
     * @throws SQLException DBエラー発生時
     */
    public int insert(Connection con, TgtInvoiceDto dto) throws SQLException {

        String sql = "INSERT INTO TGT_INVOICE ("
                + "INVOICE_ID, CONTRACT_ID, INVOICE_DATE, TOTAL_AMOUNT, STATUS_ID, "
                + "DELETE_FLAG, CREATED_BY, CREATED_AT, UPDATED_BY, UPDATED_AT"
                + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, dto.invoiceId);
            ps.setLong(2, dto.contractId);
            ps.setDate(3, java.sql.Date.valueOf(dto.invoiceDate));
            ps.setBigDecimal(4, dto.totalAmount);
            ps.setLong(5, dto.statusId);
            ps.setInt(6, dto.deleteFlag);
            ps.setString(7, dto.createdBy);
            ps.setTimestamp(8, toTimestamp(dto.createdAt));
            ps.setString(9, dto.updatedBy);
            ps.setTimestamp(10, toTimestamp(dto.updatedAt));

            return ps.executeUpdate();
        }
    }
}
