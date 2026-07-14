package main.java.dto;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * 移行元 契約DTOクラス
 *
 * @author Wataru Yamada
 */
public class SrcContractDto extends BaseSrcDto {

    /** 契約ID */
    public Long contractId;

    /** 顧客ID */
    public Long customerId;

    /** エネルギータイプ */
    public String energyType;

    /** ステータス */
    public String status;

    /** 開始日 */
    public LocalDateTime startDate;

    /** 終了日 */
    public LocalDateTime endDate;

    /** 備考1(契約申込経路（Web / 電話 / 店舗）) */
    public String remark1;

    /** 備考2(営業担当コード（A1023など）) */
    public String remark2;

    /** 備考3(キャンペーン適用コード（WINTER2025など）) */
    public String remark3;

    /** 備考4(初回接続工事予定日メモ) */
    public String remark4;

    /** 備考5(顧客優先度（通常 / 重要 / 法人）) */
    public String remark5;

    /** 備考6(メーター種別（スマートメーター / 旧式）) */
    public String remark6;

    /** 備考7(供給開始時トラブル有無メモ) */
    public String remark7;

    /** 備考8(電圧区分（100V / 200V）) */
    public String remark8;

    /** 備考9(ガス配管工事の要否) */
    public String remark9;

    /** 備考10(過去停電・供給停止履歴メモ) */
    public String remark10;

    /** 備考11(支払方法（口座振替 / クレカ / 振込）) */
    public String remark11;

    /** 備考12(請求書送付先変更履歴) */
    public String remark12;

    /** 備考13(過去未払いフラグ（Y/N）) */
    public String remark13;

    /** 備考14(割引適用履歴（長期契約割など）) */
    public String remark14;

    /** 備考15(請求締日カスタム設定) */
    public String remark15;

    /** 備考16(CS対応メモ（問い合わせ履歴）) */
    public String remark16;

    /** 備考17(クレーム対応履歴（軽微）) */
    public String remark17;

    /** 備考18(システム移行時注意事項) */
    public String remark18;

    /** 備考19(社内引き継ぎ用コメント) */
    public String remark19;

    /** 備考20(その他自由記述（旧システム未整理データ）) */
    public String remark20;

    /**
     * コンストラクタ
     *
     * @param row 移行元の顧客情報
     */
    public SrcContractDto(Map<String, Object> row) {

        super(row);

        contractId = ((Number) row.get("CONTRACT_ID")).longValue();
        customerId = ((Number) row.get("CUSTOMER_ID")).longValue();        
        energyType = (String) row.get("ENERGY_TYPE");
        status = (String) row.get("STATUS");
        startDate = toLocalDateTime(row.get("START_DATE"));
        endDate = toLocalDateTime(row.get("END_DATE"));
        remark1 = (String) row.get("REMARK1");
        remark2 = (String) row.get("REMARK2");
        remark3 = (String) row.get("REMARK3");
        remark4 = (String) row.get("REMARK4");
        remark5 = (String) row.get("REMARK5");
        remark6 = (String) row.get("REMARK6");
        remark7 = (String) row.get("REMARK7");
        remark8 = (String) row.get("REMARK8");
        remark9 = (String) row.get("REMARK9");
        remark10 = (String) row.get("REMARK10");
        remark11 = (String) row.get("REMARK11");
        remark12 = (String) row.get("REMARK12");
        remark13 = (String) row.get("REMARK13");
        remark14 = (String) row.get("REMARK14");
        remark15 = (String) row.get("REMARK15");
        remark16 = (String) row.get("REMARK16");
        remark17 = (String) row.get("REMARK17");
        remark18 = (String) row.get("REMARK18");
        remark19 = (String) row.get("REMARK19");
        remark20 = (String) row.get("REMARK20");
    }

    @Override
    public String toString() {
        return "SrcContractDto [contractId=" + contractId + ", customerId=" + customerId + ", energyType=" + energyType
                + ", status=" + status + ", startDate=" + startDate + ", endDate=" + endDate + ", remark1=" + remark1
                + ", remark2=" + remark2 + ", remark3=" + remark3 + ", remark4=" + remark4 + ", remark5=" + remark5
                + ", remark6=" + remark6 + ", remark7=" + remark7 + ", remark8=" + remark8 + ", remark9=" + remark9
                + ", remark10=" + remark10 + ", remark11=" + remark11 + ", remark12=" + remark12 + ", remark13="
                + remark13 + ", remark14=" + remark14 + ", remark15=" + remark15 + ", remark16=" + remark16
                + ", remark17=" + remark17 + ", remark18=" + remark18 + ", remark19=" + remark19 + ", remark20="
                + remark20 + ", deleteFlag=" + deleteFlag + ", createdBy=" + createdBy + ", createdAt=" + createdAt
                + ", updatedBy=" + updatedBy + ", updatedAt=" + updatedAt + "]";
    }
}
