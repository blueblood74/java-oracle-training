package main.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import main.java.dto.TgtContractRemarkADto;

/**
 * 移行先 - 契約備考A Daoクラス
 *
 * @author Wataru Yamada
 */
public class TgtContractRemarkADao extends BaseDao {

    /**
     * レコードが1件以上存在するか確認する
     *
     * @param con DBコネクション
     * @return 判定結果(true:存在する false:存在しない)
     * @throws SQLException DBエラー発生時
     */
    public boolean isExistRecord(Connection con) throws SQLException {

        return super.isExistRecord(con, "TGT_CONTRACT_REMARK_A");
    }

    /**
     * 契約備考A情報を登録する
     *
     * @param con DBコネクション
     * @param dto 移行先 契約備考A DTOクラス
     * @return 登録件数
     * @throws SQLException DBエラー発生時
     */
    public int insert(Connection con, TgtContractRemarkADto dto) throws SQLException {

        String sql = "INSERT INTO TGT_CONTRACT_REMARK_A ("
                + "CONTRACT_ENERGY_ID, "
                + "REMARK1, REMARK2, REMARK3, REMARK4, REMARK5, "
                + "REMARK6, REMARK7, REMARK8, REMARK9, REMARK10, "
                + "DELETE_FLAG, CREATED_BY, CREATED_AT, UPDATED_BY, UPDATED_AT"
                + ") VALUES (" 
                + "SEQ_CONTRACT_ENERGY_ID.currval, "
                + "?, ?, ?, ?, ?, "
                + "?, ?, ?, ?, ?, "
                + "?, ?, ?, ?, ?) ";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, dto.remark1);
            ps.setString(2, dto.remark2);
            ps.setString(3, dto.remark3);
            ps.setString(4, dto.remark4);
            ps.setString(5, dto.remark5);
            ps.setString(6, dto.remark6);
            ps.setString(7, dto.remark7);
            ps.setString(8, dto.remark8);
            ps.setString(9, dto.remark9);
            ps.setString(10, dto.remark10);
            ps.setInt(11, dto.deleteFlag);
            ps.setString(12, dto.createdBy);
            ps.setTimestamp(13, toTimestamp(dto.createdAt));
            ps.setString(14, dto.updatedBy);
            ps.setTimestamp(15, toTimestamp(dto.updatedAt));

            return ps.executeUpdate();
        }
    }
}

