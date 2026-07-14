package main.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 移行元 - 顧客Daoクラス
 *
 * @author Wataru Yamada
 */
public class SrcCustomerDao extends BaseDao {

    /**
     * 移行元の顧客情報のリストを取得する
     *
     * @param con DBコネクション
     * @param offset 取得オフセット
     * @param limit  取得件数
     * @return 移行元の顧客情報のリスト
     * @throws SQLException DBエラー発生時
     */
    public List<Map<String, Object>> findByRange(Connection con, int offset, int limit) throws SQLException {

        List<Map<String, Object>> result = new ArrayList<>();
        String sql = "SELECT * FROM SRC_CUSTOMER "
                + "WHERE DELETE_FLAG = 0 "
                + "ORDER BY CUSTOMER_ID "
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, offset);
            ps.setInt(2, limit);
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Map<String, Object> row = new HashMap<>();
                    ResultSetMetaData meta = rs.getMetaData();
                    for (int i = 1; i <= meta.getColumnCount(); i++) {
                        row.put(meta.getColumnName(i), rs.getObject(i));
                    }
                    result.add(row);
                }
            }
        }

        return result;
    }
}
