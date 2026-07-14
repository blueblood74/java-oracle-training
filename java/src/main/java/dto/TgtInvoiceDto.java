package main.java.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 移行先 顧客DTOクラス
 *
 * @author Wataru Yamada
 */
public class TgtInvoiceDto extends BaseTgtDto {

    /** 請求ID */
    public Long invoiceId;

    /** 契約ID */
    public Long contractId;

    /** 請求日 */
    public LocalDate invoiceDate;

    /** 合計金額 */
    public BigDecimal totalAmount;

    /** ステータスID */
    public Long statusId;

    public static class Builder {
        private TgtInvoiceDto dto = new TgtInvoiceDto();

        public Builder invoiceId(Long invoiceId) {
            dto.invoiceId = invoiceId;
            return this;
        }

        public Builder contractId(Long contractId) {
            dto.contractId = contractId;
            return this;
        }

        public Builder invoiceDate(LocalDate invoiceDate) {
            dto.invoiceDate = invoiceDate;
            return this;
        }

        public Builder totalAmount(BigDecimal totalAmount) {
            dto.totalAmount = totalAmount;
            return this;
        }

        public Builder statusId(Long statusId) {
            dto.statusId = statusId;
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

        public TgtInvoiceDto build() {
            return dto;
        }
    }

    @Override
    public String toString() {
        return "TgtInvoiceDto [invoiceId=" + invoiceId + ", contractId=" + contractId + ", invoiceDate=" + invoiceDate
                + ", totalAmount=" + totalAmount + ", statusId=" + statusId + ", deleteFlag=" + deleteFlag
                + ", createdBy=" + createdBy + ", createdAt=" + createdAt + ", updatedBy=" + updatedBy + ", updatedAt="
                + updatedAt + "]";
    }
}
