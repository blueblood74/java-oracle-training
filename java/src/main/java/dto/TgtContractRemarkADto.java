package main.java.dto;

import java.time.LocalDateTime;

/**
 * 移行先 契約備考A DTOクラス
 *
 * @author Wataru Yamada
 */
public class TgtContractRemarkADto extends BaseTgtDto {

    /** 備考1(契約申込経路（Web / 電話 / 店舗） ) */
    public String remark1;

    /** 備考2(営業担当コード（A1023など）) */
    public String remark2;

    /** 備考3(キャンペーン適用コード（WINTER2025など）) */
    public String remark3;

    /** 備考4(回接続工事予定日メモ) */
    public String remark4;

    /** 備考5(顧客優先度（通常 / 重要 / 法人）) */
    public String remark5;

    /** 備考6(メーター種別（スマートメーター/旧式）) */
    public String remark6;

    /** 備考7(供給開始時のトラブル有無) */
    public String remark7;

    /** 備考8(電圧区分（100V / 200V）) */
    public String remark8;

    /** 備考9(ガス配管工事の要否) */
    public String remark9;

    /** 備考10(過去停電・供給停止履歴メモ) */
    public String remark10;

    public static class Builder {

        private TgtContractRemarkADto dto = new TgtContractRemarkADto();

        public Builder remark1(String remark1) {
            dto.remark1 = remark1;
            return this;
        }

        public Builder remark2(String remark2) {
            dto.remark2 = remark2;
            return this;
        }

        public Builder remark3(String remark3) {
            dto.remark3 = remark3;
            return this;
        }

        public Builder remark4(String remark4) {
            dto.remark4 = remark4;
            return this;
        }

        public Builder remark5(String remark5) {
            dto.remark5 = remark5;
            return this;
        }

        public Builder remark6(String remark6) {
            dto.remark6 = remark6;
            return this;
        }

        public Builder remark7(String remark7) {
            dto.remark7 = remark7;
            return this;
        }

        public Builder remark8(String remark8) {
            dto.remark8 = remark8;
            return this;
        }

        public Builder remark9(String remark9) {
            dto.remark9 = remark9;
            return this;
        }

        public Builder remark10(String remark10) {
            dto.remark10 = remark10;
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

        public TgtContractRemarkADto build() {
            return dto;
        }
    }

    @Override
    public String toString() {
        return "TgtContractRemarkADto [remark1=" + remark1 + ", remark2=" + remark2 + ", remark3=" + remark3
                + ", remark4=" + remark4 + ", remark5=" + remark5 + ", remark6=" + remark6 + ", remark7=" + remark7
                + ", remark8=" + remark8 + ", remark9=" + remark9 + ", remark10=" + remark10 + ", deleteFlag="
                + deleteFlag + ", createdBy=" + createdBy + ", createdAt=" + createdAt + ", updatedBy=" + updatedBy
                + ", updatedAt=" + updatedAt + "]";
    }
}
