package main.java.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 移行先 顧客DTOクラス
 *
 * @author Wataru Yamada
 */
public class TgtInvoiceDetailDto extends BaseTgtDto {

    /** 請求明細ID */
    public Long invoiceDetailId;

    /** 契約ID */
    public Long invoiceId;

    /** 種別ID */
    public Long energyTypeId;

    /** 使用量 */
    public BigDecimal usageQty;
    
    /** 単価 */
    public BigDecimal unitPrice;

    /** 金額 */
    public BigDecimal amount;

    public static class Builder {
        private TgtInvoiceDetailDto dto = new TgtInvoiceDetailDto();

        public Builder invoiceDetailId(Long invoiceDetailId) {
            dto.invoiceDetailId = invoiceDetailId;
            return this;
        }

        public Builder invoiceId(Long invoiceId) {
            dto.invoiceId = invoiceId;
            return this;
        }

        public Builder energyTypeId(Long energyTypeId) {
            dto.energyTypeId = energyTypeId;
            return this;
        }

        public Builder usageQty(BigDecimal usageQty) {
            dto.usageQty = usageQty;
            return this;
        }

        public Builder unitPrice(BigDecimal unitPrice) {
            dto.unitPrice = unitPrice;
            return this;
        }

        public Builder amount(BigDecimal amount) {
            dto.amount = amount;
            return this;
        }

        public Builder deleteFlag(int deleteFlag) {
            dto.deleteFlag = deleteFlag;
            return this;
        }

        public Builder createdBy(String createdBy) {
            dto.createdBy = createdBy;
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt) {
            dto.createdAt = createdAt;
            return this;
        }

        public Builder updatedBy(String updatedBy) {
            dto.updatedBy = updatedBy;
            return this;
        }

        public Builder updatedAt(LocalDateTime updatedAt) {
            dto.updatedAt = updatedAt;
            return this;
        }

        public TgtInvoiceDetailDto build() {
            return dto;
        }
    }

    @Override
    public String toString() {
        return "TgtInvoiceDetail [invoiceDetailId=" + invoiceDetailId + ", invoiceId=" + invoiceId + ", energyTypeId="
                + energyTypeId + ", usageQty=" + usageQty + ", unitPrice=" + unitPrice + ", amount=" + amount + "]";
    }
}
