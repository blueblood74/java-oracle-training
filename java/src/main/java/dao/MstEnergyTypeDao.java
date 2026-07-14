package main.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * エネルギータイプマスタDaoクラス
 *
 * @author Wataru Yamada
 */
public class MstEnergyTypeDao extends BaseDao {

    /**
     * エネルギータイプのデータを取得する
     *
     * @param con DB接続コネクション
     * @return エネルギータイプ一覧
     * @throws SQLException DBエラー発生時
     */
    public Map<String, Long> getList(Connection con) throws SQLException {

        String sql = "SELECT ENERGY_TYPE_ID, ENERGY_NAME_SHORT "
                + "FROM MST_ENERGY_TYPE "
                + "WHERE DELETE_FLAG = 0 ";

        Map<String, Long> row = new HashMap<>();
        try (PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                row.put(rs.getString(2), rs.getLong(1));
            }
        }

        return row;
    }
}
