package main.java.dto;

import java.time.LocalDateTime;

/**
 * 移行先 契約備考B DTOクラス
 *
 * @author Wataru Yamada
 */
public class TgtContractRemarkBDto extends BaseTgtDto {

    /** 備考11(支払方法（口座振替 / クレカ / 振込）) */
    public String remark11;

    /** 備考12(請求書送付先変更履歴) */
    public String remark12;

    /** 備考13(過去未払いフラグ) */
    public String remark13;

    /** 備考14(割引適用履歴（長期契約割など）) */
    public String remark14;

    /** 備考15(請求締日カスタム設定) */
    public String remark15;

    /** 備考16(メーター種別（CS対応メモ（問い合わせ履歴）) */
    public String remark16;

    /** 備考17(クレーム対応履歴（軽微）) */
    public String remark17;

    /** 備考18(システム移行時注意事項) */
    public String remark18;

    /** 備考19(社内引き継ぎ用コメント) */
    public String remark19;

    /** 備考20(その他自由記述（旧システム未整理データ）) */
    public String remark20;


    public static class Builder {
        private TgtContractRemarkBDto dto = new TgtContractRemarkBDto();

        public Builder remark11(String remark11) {
            dto.remark11 = remark11;
            return this;
        }

        public Builder remark12(String remark12) {
            dto.remark12 = remark12;
            return this;
        }

        public Builder remark13(String remark13) {
            dto.remark13 = remark13;
            return this;
        }

        public Builder remark14(String remark14) {
            dto.remark14 = remark14;
            return this;
        }

        public Builder remark15(String remark15) {
            dto.remark15 = remark15;
            return this;
        }

        public Builder remark16(String remark16) {
            dto.remark16 = remark16;
            return this;
        }

        public Builder remark17(String remark17) {
            dto.remark17 = remark17;
            return this;
        }

        public Builder remark18(String remark18) {
            dto.remark18 = remark18;
            return this;
        }

        public Builder remark19(String remark19) {
            dto.remark19 = remark19;
            return this;
        }

        public Builder remark20(String remark20) {
            dto.remark20 = remark20;
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

        public TgtContractRemarkBDto build() {
            return dto;
        }
    }

    @Override
    public String toString() {
        return "TgtContractRemarkBDto [remark11=" + remark11 + ", remark12=" + remark12 + ", remark13=" + remark13
                + ", remark14=" + remark14 + ", remark15=" + remark15 + ", remark16=" + remark16 + ", remark17="
                + remark17 + ", remark18=" + remark18 + ", remark19=" + remark19 + ", remark20=" + remark20
                + ", deleteFlag=" + deleteFlag + ", createdBy=" + createdBy + ", createdAt=" + createdAt
                + ", updatedBy=" + updatedBy + ", updatedAt=" + updatedAt + "]";
    }
}
