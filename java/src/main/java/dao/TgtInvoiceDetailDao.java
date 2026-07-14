package main.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import main.java.dto.TgtInvoiceDetailDto;


/**
 * 移行先 - 請求明細Daoクラス
 *
 * @author Wataru Yamada
 */
public class TgtInvoiceDetailDao extends BaseDao {

    /**
     * レコードが1件以上存在するか確認する
     *
     * @param con DBコネクション
     * @return 判定結果(true:存在する false:存在しない)
     * @throws SQLException DBエラー発生時
     */
    public boolean isExistRecord(Connection con) throws SQLException {

        return super.isExistRecord(con, "TGT_INVOICE_DETAIL");
    }

    /**
     * 請求明細情報を登録する
     *
     * @param con DBコネクション
     * @param dto 移行先 - 請求明細DTOクラス
     * @return 登録件数
     * @throws SQLException DBエラー発生時
     */
    public int insert(Connection con, TgtInvoiceDetailDto dto) throws SQLException {

        String sql = "INSERT INTO TGT_INVOICE_DETAIL ("
                + "INVOICE_DETAIL_ID, INVOICE_ID, ENERGY_TYPE_ID, USAGE_QTY, UNIT_PRICE, AMOUNT, "
                + "DELETE_FLAG, CREATED_BY, CREATED_AT, UPDATED_BY, UPDATED_AT"
                + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, dto.invoiceDetailId);
            ps.setLong(2, dto.invoiceId);
            ps.setLong(3, dto.energyTypeId);
            ps.setBigDecimal(4, dto.usageQty);
            ps.setBigDecimal(5, dto.unitPrice);
            ps.setBigDecimal(6, dto.amount);
            ps.setInt(7, dto.deleteFlag);
            ps.setString(8, dto.createdBy);
            ps.setTimestamp(9, toTimestamp(dto.createdAt));
            ps.setString(10, dto.updatedBy);
            ps.setTimestamp(11, toTimestamp(dto.updatedAt));

            return ps.executeUpdate();
        }
    }
}
