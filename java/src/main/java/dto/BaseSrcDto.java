package main.java.dto;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * 移行元 基底DTOクラス
 *
 * @author Wataru Yamada
 */
public class BaseSrcDto {

    /** 削除フラグ */
    public int deleteFlag;

    /** 作成者 */
    public String createdBy;

    /** 作成日時 */
    public LocalDateTime createdAt;

    /** 更新者 */
    public String updatedBy;

    /** 更新日時 */
    public LocalDateTime updatedAt;

    /**
     * コンストラクタ
     *
     * @param row 移行元情報
     */
    public BaseSrcDto(Map<String, Object> row) {

        this.deleteFlag = ((Number) row.get("DELETE_FLAG")).intValue();
        this.createdBy = (String) row.get("CREATED_BY");
        this.createdAt = toLocalDateTime(row.get("CREATED_AT"));
        this.updatedBy = (String) row.get("UPDATED_BY");
        this.updatedAt = toLocalDateTime(row.get("UPDATED_AT"));
    }

    /**
     * 値をLoclDateTime型に変換して返す
     *
     * @param value 対象値
     * @return 変換した値
     */
    protected LocalDateTime toLocalDateTime(Object value) {
        
        if (value == null) {
            return null;
        }

        if (value instanceof Timestamp) {
            return ((Timestamp) value).toLocalDateTime();
        }
        
        if (value instanceof LocalDateTime) {
            return (LocalDateTime) value;
        }

        throw new IllegalArgumentException("Unexpected type: " + value.getClass());

    }

    @Override
    public String toString() {
        return "BaseSrcDto [deleteFlag=" + deleteFlag + ", createdBy=" + createdBy + ", createdAt=" + createdAt
                + ", updatedBy=" + updatedBy + ", updatedAt=" + updatedAt + "]";
    }
}
