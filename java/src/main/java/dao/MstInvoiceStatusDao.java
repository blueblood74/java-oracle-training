package main.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * インボイスステータスマスタDaoクラス
 *
 * @author Wataru Yamada
 */
public class MstInvoiceStatusDao extends BaseDao {

    /**
     * インボイスステータスマスタのデータを取得する
     *
     * @param con DB接続コネクション
     * @return インボイスステータスマスタの一覧
     * @throws SQLException DBエラー発生時
     */
    public Map<String, String> getList(Connection con) throws SQLException {

        String sql = "SELECT STATUS_CODE, STATUS_NAME "
                + "FROM MST_INVOICE_STATUS "
                + "WHERE DELETE_FLAG = 0 ";

        Map<String, String> row = new HashMap<>();
        try (PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                row.put(rs.getString(2), rs.getString(1));
            }
        }

        return row;
    }
}
