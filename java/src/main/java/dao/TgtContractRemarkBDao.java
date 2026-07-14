package main.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import main.java.dto.TgtContractRemarkBDto;

/**
 * 移行先 - 契約備考B Daoクラス
 *
 * @author Wataru Yamada
 */
public class TgtContractRemarkBDao extends BaseDao {

    /**
     * レコードが1件以上存在するか確認する
     *
     * @param con DBコネクション
     * @return 判定結果(true:存在する false:存在しない)
     * @throws SQLException DBエラー発生時
     */
    public boolean isExistRecord(Connection con) throws SQLException {

        return super.isExistRecord(con, "TGT_CONTRACT_REMARK_B");
    }

    /**
     * 契約備考B情報を登録する
     *
     * @param con DBコネクション
     * @param dto 移行先 顧客DTOクラス
     * @return 登録件数
     * @throws SQLException DBエラー発生時
     */
    public int insert(Connection con, TgtContractRemarkBDto dto) throws SQLException {

        String sql = "INSERT INTO TGT_CONTRACT_REMARK_B ("
                + "CONTRACT_ENERGY_ID, "
                + "REMARK11, REMARK12, REMARK13, REMARK14, REMARK15, "
                + "REMARK16, REMARK17, REMARK18, REMARK19, REMARK20, "
                + "DELETE_FLAG, CREATED_BY, CREATED_AT, UPDATED_BY, UPDATED_AT"
                + ") VALUES ("
                + "SEQ_CONTRACT_ENERGY_ID.currval, "
                + "?, ?, ?, ?, ?, "
                + "?, ?, ?, ?, ?, "
                + "?, ?, ?, ?, ?) ";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, dto.remark11);
            ps.setString(2, dto.remark12);
            ps.setString(3, dto.remark13);
            ps.setString(4, dto.remark14);
            ps.setString(5, dto.remark15);
            ps.setString(6, dto.remark16);
            ps.setString(7, dto.remark17);
            ps.setString(8, dto.remark18);
            ps.setString(9, dto.remark19);
            ps.setString(10, dto.remark20);
            ps.setInt(11, dto.deleteFlag);
            ps.setString(12, dto.createdBy);
            ps.setTimestamp(13, toTimestamp(dto.createdAt));
            ps.setString(14, dto.updatedBy);
            ps.setTimestamp(15, toTimestamp(dto.updatedAt));

            return ps.executeUpdate();
        }
    }
}
