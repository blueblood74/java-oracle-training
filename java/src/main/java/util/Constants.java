package main.java.util;

import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * 移行バッチ 定数クラス
 *
 * @author Wataru Yamada
 */
public final  class Constants {

    /**
     * コンストラクタ
     *
     * インスタンス化禁止
     */
    private Constants() {
        throw new UnsupportedOperationException();
    }

    /** DB接続URL */
    public static final String URL = "jdbc:oracle:thin:@oracle:1521/FREEPDB1";

    /** DBユーザ */
    public static final String USER = "system";

    /** DBパスワード */
    public static final String PASSWORD = "Oracle123";

    /** 1コミットで移行するレコード数 */
    public static final int COMMIT_SIZE = 1000;

    /** 登録時の削除フラグ値 */
    public static final int DEFAULT_DELETE_FLAG = 0;

    /** 登録時の作成者名 */
    public static final String DEFAULT_CREATED_BY = "migration_batch";

    /** 登録時の更新者名 */
    public static final String DEFAULT_UPDATED_BY = null;

    /** 登録時の更新日時 */
    public static final LocalDateTime DEFAULT_UPDATED_AT = null;

    /** */
    public static final ZoneId ZONE_JST = ZoneId.of("Asia/Tokyo");
}
