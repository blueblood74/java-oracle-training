package main.java.dto;

import java.time.LocalDateTime;

/**
 * 移行先 基底DTOクラス
 *
 * @author Wataru Yamada
 */
public class BaseTgtDto {

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

    @Override
    public String toString() {
        return "BaseDto [deleteFlag=" + deleteFlag + ", createdBy=" + createdBy + ", createdAt=" + createdAt
                + ", updatedBy=" + updatedBy + ", updatedAt=" + updatedAt + "]";
    }
}
