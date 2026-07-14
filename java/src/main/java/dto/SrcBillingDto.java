package main.java.dto;

import java.math.BigDecimal;
import java.util.Map;

/**
 * 移行元 請求ヘッダDTOクラス
 *
 * @author Wataru Yamada
 */
public class SrcBillingDto extends BaseSrcDto {

    /** 請求ID */
    public Long billingId;

    /** 契約ID */
    public Long contractId;

    /** 請求月 */
    public String billMonth;

    /** 合計金額  */
    public BigDecimal totalAmount;

    /**
     * コンストラクタ
     *
     * @param row 移行元の顧客情報
     */
    public SrcBillingDto(Map<String, Object> row) {

        super(row);
        this.billingId = ((Number) row.get("BILLING_ID")).longValue();
        this.contractId = ((Number) row.get("CONTRACT_ID")).longValue();
        this.billMonth = (String) row.get("BILL_MONTH");
        this.totalAmount = (BigDecimal) row.get("TOTAL_AMOUNT");
    }

    @Override
    public String toString() {
        return "SrcBillingDto [billingId=" + billingId + ", contractId=" + contractId + ", billMonth=" + billMonth
                + ", totalAmount=" + totalAmount + ", deleteFlag=" + deleteFlag + ", createdBy=" + createdBy
                + ", createdAt=" + createdAt + ", updatedBy=" + updatedBy + ", updatedAt=" + updatedAt + "]";
    }
}
