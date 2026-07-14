package main.java.dto;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * 移行先 顧客DTOクラス
 *
 * @author Wataru Yamada
 */
public class TgtCustomerDto extends BaseTgtDto {

    /** 顧客ID */
    public Long customerId;

    /** 顧客名 */
    public String customerName;

    /** 住所 */
    public String address;

    /** 統一後 顧客タイプコード */
    public Long customerTypeId;

    /**
     * コンストラクタ
     *
     * @param row 移行元の顧客情報
     * @param customerTypeId 顧客タイプコード
     * @param deleteFlag 削除フラグ
     * @param createdBy 作成者
     * @param createdAt 作成日時
     * @param updatedBy 更新者
     * @param updatedAt 更新日時
     */
    public TgtCustomerDto(Map<String, Object> row, Long customerTypeId, int deleteFlag, String createdBy,
            LocalDateTime createdAt, String updatedBy, LocalDateTime updatedAt) {

        this.customerId = ((Number) row.get("CUSTOMER_ID")).longValue();
        this.customerName = (String) row.get("CUSTOMER_NAME");
        this.address = (String) row.get("ADDRESS");
        this.customerTypeId = customerTypeId;
        this.deleteFlag = deleteFlag;
    }

    @Override
    public String toString() {
        return "TgtCustomerDto [customerId=" + customerId + ", customerName=" + customerName + ", address=" + address
                + ", customerTypeId=" + customerTypeId + ", deleteFlag=" + deleteFlag + ", createdBy=" + createdBy
                + ", createdAt=" + createdAt + ", updatedBy=" + updatedBy + ", updatedAt=" + updatedAt + "]";
    }
}
