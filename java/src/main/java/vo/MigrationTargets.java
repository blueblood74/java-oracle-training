package main.java.vo;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public final class MigrationTargets {

    /** 全てを表す指定 */
    private static final String ALL = "ALL";

    /** コマンドライン引数*/
    private final Set<String> targets;

    /**
     * コンストラクタ
     *
     * @param args コマンドライン引数
     * @throws IllegalArgumentException ALLと個別テーブル名が同時に指定された場合
     */
    public MigrationTargets(String[] args) {

        Set<String> parsed = new LinkedHashSet<>(Arrays.asList(args));
        if (parsed.size() > 1 && parsed.contains(ALL)) {
            throw new IllegalArgumentException("ALLと個別テーブル名は同時に指定できません: " + parsed);
        }

        this.targets = parsed;
    }

    /**
     * SRC_CUSTOMERテーブルの移行処理が必要か判定する
     *
     * @return 判定結果(true:移行が必要、false:移行が不要)
     */
    public boolean isNeedMigrationSrcCustomer() {
        return isNeedMigration("SRC_CUSTOMER");
    }

    /**
     * SRC_CONTRACTテーブルの移行処理が必要か判定する
     *
     * @return 判定結果(true:移行が必要、false:移行が不要)
     */
    public boolean isNeedMigrationSrcContract() {
        return isNeedMigration("SRC_CONTRACT");
    }

    /**
     * SRC_BILLINGテーブルの移行処理が必要か判定する
     *
     * @return 判定結果(true:移行が必要、false:移行が不要)
     */
    public boolean isNeedMigrationSrcBilling() {
        return isNeedMigration("SRC_BILLING");
    }

    /**
     * SRC_BILLING_DETAILテーブルの移行処理が必要か判定する
     *
     * @return 判定結果(true:移行が必要、false:移行が不要)
     */
    public boolean isNeedMigrationSrcBillingDetail() {
        return isNeedMigration("SRC_BILLING_DETAIL");
    }

    /**
     * 指定対象のテーブルの移行処理が必要か判定する
     *
     * @param tableName テーブル名
     * @return 判定結果(true:移行が必要、false:移行が不要)
     */
    public boolean isNeedMigration(String tableName) {
        return targets.isEmpty() || targets.contains(ALL) || targets.contains(tableName);
    }
}