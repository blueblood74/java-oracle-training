package main.java.dto;

import java.time.LocalDateTime;

/**
 * 移行先 契約エネルギーDTOクラス
 *
 * @author Wataru Yamada
 */
public class TgtContractEnergyDto extends BaseTgtDto {

    /** 契約ID */
    public Long contractId;

    /** エネルギータイプID */
    public Long energyTypeId;

    public static class Builder {
        private TgtContractEnergyDto dto = new TgtContractEnergyDto();

        public Builder contractId(Long contractId) {
            dto.contractId = contractId;
            return this;
        }

        public Builder energyTypeId(Long energyTypeId) {
            dto.energyTypeId = energyTypeId;
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

        public TgtContractEnergyDto build() {
            return dto;
        }
    }

    @Override
    public String toString() {
        return "TgtContractEnergyDto [contractId=" + contractId + ", energyTypeId=" + energyTypeId + ", deleteFlag="
                + deleteFlag + ", createdBy=" + createdBy + ", createdAt=" + createdAt + ", updatedBy=" + updatedBy
                + ", updatedAt=" + updatedAt + "]";
    }
}
