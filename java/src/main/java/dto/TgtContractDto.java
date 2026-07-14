package main.java.dto;

import java.time.LocalDateTime;

/**
 * 移行先 契約DTOクラス
 *
 * @author Wataru Yamada
 */
public class TgtContractDto extends BaseTgtDto {

    /** 契約ID */
    public Long contractId;

    /** 顧客ID */
    public Long customerId;

    /** ステータスコード */
    public String statusCode;

    /** 開始日 */
    public LocalDateTime startDate;

    /** 終了日 */
    public LocalDateTime endDate;

    /** 統一後 顧客タイプコード */
    public Long customerTypeId;

    public static class Builder {
        private TgtContractDto dto = new TgtContractDto();

        public Builder contractId(Long contractId) {
            dto.contractId = contractId;
            return this;
        }

        public Builder customerId(Long customerId) {
            dto.customerId = customerId;
            return this;
        }

        public Builder statusCode(String statusCode) {
            dto.statusCode = statusCode;
            return this;
        }

        public Builder startDate(LocalDateTime startDate) {
            dto.startDate = startDate;
            return this;
        }

        public Builder endDate(LocalDateTime endDate) {
            dto.endDate = endDate;
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

        public TgtContractDto build() {
            return dto;
        }
    }

    @Override
    public String toString() {
        return "TgtContractDto [contractId=" + contractId + ", customerId=" + customerId + ", statusCode=" + statusCode
                + ", startDate=" + startDate + ", endDate=" + endDate + ", customerTypeId=" + customerTypeId
                + ", deleteFlag=" + deleteFlag + ", createdBy=" + createdBy + ", createdAt=" + createdAt
                + ", updatedBy=" + updatedBy + ", updatedAt=" + updatedAt + "]";
    }
}
