package main.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

/**
 * 基底Daoクラス
 *
 * @author Wataru Yamada
 */
public class BaseDao {

    /**
     * レコードが1件以上存在するか確認する
     *
     * @param con DBコネクション
     * @param tableName テーブル名
     * @return 判定結果(true:存在する false:存在しない)
     * @throws SQLException DBエラー発生時
     */
    protected boolean isExistRecord(Connection con, String tableName) throws SQLException {

        String sql = "SELECT 1 FROM " + tableName + " WHERE ROWNUM <= 1";
        try (PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                return true;
            }
        }

        return false;
    }
    
    /**
     * 値をTimestamp型に変換する
     *
     * @param dt 変換対象
     * @return 変換結果
     */
    protected Timestamp toTimestamp(ZonedDateTime dt) {

        return dt == null ? null : Timestamp.valueOf(dt.toLocalDateTime());
    }

    /**
     * 値をTimestamp型に変換する
     *
     * @param dt 変換対象
     * @return 変換結果
     */
    protected Timestamp toTimestamp(LocalDateTime dt) {

        return dt == null ? null : Timestamp.valueOf(dt);
    }
}
