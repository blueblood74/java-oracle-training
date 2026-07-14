package main.java.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import main.java.util.Constants;

/**
 * 基底Serviceクラス
 *
 * @author Wataru Yamada
 */
public class BaseService {

    protected static final Logger logger = LoggerFactory.getLogger(BaseService.class);
    protected static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * 移行処理スキップ時のメッセージをログイン具する
     *
     * @param from 移行元テーブル名
     * @param to   移行先テーブル名
     */
    public void writeServiceSkipLog(String from, String to) {

        logger.info(String.format(
                "==================================================%n" +
                        "Migration Info%n" +
                        "Table      : %s => %s%n" +
                        "データが1件以上が入っているため移行処理をスキップします。%n" +
                        "移行処理をする場合は、対象テーブルを空にしてください。%n" +
                        "==================================================",
                from, to));
    }

    /**
     * 移行処理開始時のロギングをする
     *
     * @param from 移行元テーブル名
     * @param to   移行先テーブル名
     * @param datetime 処理開始時間
     */
    public void writeServiceStartLog(String from, String to, LocalDateTime datetime) {

        String formatted = datetime.format(formatter);

        logger.info(String.format(
                "==================================================%n" +
                        "Migration Start%n" +
                        "Table      : %s => %s%n" +
                        "Start Time : %s%n" +
                        "==================================================",
                from, to, formatted));
    }

    /**
     * 移行処理開始時のロギングをする
     *
     * @param from 移行元テーブル名
     * @param to   移行先テーブル名
     * @param startDateTime 処理開始時間
     * @param count 移行レコード数
     */
    public void writeServiceEndLog(String from, String to, LocalDateTime startDateTime, int count) {

        LocalDateTime endDateTime = LocalDateTime.now(Constants.ZONE_JST);
        String formattedEnd = endDateTime.format(formatter);
        double elapsed = Duration.between(startDateTime, endDateTime).toMillis() / 1000.0;

        logger.info(String.format(
                "==================================================%n" +
                        "Migration End%n" +
                        "Table      : %s => %s%n" +
                        "End Time : %s%n" +
                        "Elapsed    : %.3f sec%n" +
                        "Inserted Count : %,d%n" +
                        "==================================================",
                from, to, formattedEnd, elapsed, count));
    }
}