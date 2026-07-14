package main.java.util;

import org.slf4j.Logger;


/**
 * 文字列ユーティリティクラス
 *
 * @author Wataru Yamada
 */
public class StringUtils {

    /**
     * コンストラクタ
     *
     * インスタンス化禁止
     */
    private StringUtils() {
        throw new UnsupportedOperationException();
    }

    /**
     * 文字列を指定サイズに切り詰める。(切り詰めた場合はログを出力する)
     *
     * @param columnName カラム名
     * @param value 対象文字列
     * @param maxLength 切り詰める文字数
     * @param logger ロガー
     * @return 切り詰めた文字列
     */
    public static final String truncateWithLog(String columnName, String value, int maxLength, Logger logger) {

        if (value == null) {
            return null;
        }

        String truncated = truncate(value, maxLength);
        if (!value.equals(truncated)) {
            logger.warn("REMARK truncated: column={}, before={}chars, after={}chars",
                    columnName, value.length(), truncated.length());
        }

        return truncated;
    }

    /**
     * 文字列を指定サイズに切り詰める
     * 
     * @param target 対象文字列
     * @param size 切り詰める文字数
     * @return 切り詰めた文字列
     */
    public static final String truncate(String target, int size) {

        if (target == null || target.length() <= size) {
            return target;
        }

        return target.substring(0, size);
    }
}