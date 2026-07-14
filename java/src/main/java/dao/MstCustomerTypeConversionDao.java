package main.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 *  顧客種別変換マスタDaoクラス
 *
 * @author Wataru Yamada
 */
public class MstCustomerTypeConversionDao extends BaseDao {

    /**
     * 顧客種別変換マスタのデータを取得する
     *
     * @param con DB接続コネクション
     * @return 顧客種別変換マスタの一覧
     * @throws SQLException DBエラー発生時
     */
    public Map<String, Long> getConverionList(Connection con) throws SQLException {

        String sql = "SELECT SOURCE_TYPE, TARGET_TYPE FROM MST_CUSTOMER_TYPE_CONVERSION "
                + "WHERE DELETE_FLAG = 0 ";

        Map<String, Long> row = new HashMap<>();
        try (PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                row.put(rs.getString(1), rs.getLong(2));
            }
        }

        return row;
    }
}
