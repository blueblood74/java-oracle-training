package main.java.dto;

import java.math.BigDecimal;
import java.util.Map;

/**
 * 移行元 請求明細DTOクラス
 *
 * @author Wataru Yamada
 */
public class SrcBillingDetailDto extends BaseSrcDto {

    /** 請求明細ID */
    public Long billingDetailId;

    /** 請求ID */
    public Long billingId;

    /** 項目種別 */
    public String itemType;

    /** 使用量 */
    public BigDecimal usageQty;

    /** 単価 */
    public BigDecimal unitPrice;

    /** 金額 */
    public BigDecimal amount;

    /**
     * コンストラクタ
     *
     * @param row 移行元の請求明細情報
     */
    public SrcBillingDetailDto(Map<String, Object> row) {

        super(row);
        this.billingDetailId = ((Number) row.get("BILLING_DETAIL_ID")).longValue();
        this.billingId = ((Number) row.get("BILLING_ID")).longValue();
        this.itemType = (String) row.get("ITEM_TYPE");
        this.usageQty = (BigDecimal) row.get("USAGE_QTY");
        this.unitPrice = (BigDecimal) row.get("UNIT_PRICE");
        this.amount = (BigDecimal) row.get("AMOUNT");

    }

    @Override
    public String toString() {
        return "SrcBillingDetailDto [billingDetailId=" + billingDetailId + ", billingId=" + billingId + ", itemType="
                + itemType + ", usageQty=" + usageQty + ", unitPrice=" + unitPrice + ", amount=" + amount
                + ", deleteFlag=" + deleteFlag + ", createdBy=" + createdBy + ", createdAt=" + createdAt
                + ", updatedBy=" + updatedBy + ", updatedAt=" + updatedAt + "]";
    }
}
