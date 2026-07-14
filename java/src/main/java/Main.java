package main.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import main.java.service.BillingDetailMigrationService;
import main.java.service.BillingMigrationService;
import main.java.service.ContractMigrationService;
import main.java.service.CustomerMigrationService;
import main.java.service.IMigrationService;
import main.java.util.Constants;
import main.java.vo.MigrationTargets;

public class Main {

    /** ロガー */
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    /**
     * メイン処理
     *
     * @param args コマンドライン引数
     */
    public static void main(String[] args) {

        LocalDateTime batchStartTime = LocalDateTime.now(Constants.ZONE_JST);

        try (Connection con = DriverManager.getConnection(Constants.URL, Constants.USER, Constants.PASSWORD)) {
            MigrationTargets targets = new MigrationTargets(args);
            
            con.setAutoCommit(false); // 手動コミットに設定

            if (targets.isNeedMigrationSrcCustomer()) {
                IMigrationService customService = new CustomerMigrationService();
                customService.execute(con, batchStartTime);
            }

            if (targets.isNeedMigrationSrcContract()) {
                IMigrationService contractService = new ContractMigrationService();
                contractService.execute(con, batchStartTime);
            }

            if (targets.isNeedMigrationSrcBilling()) {
                IMigrationService billingService = new BillingMigrationService();
                billingService.execute(con, batchStartTime);
            }

            if (targets.isNeedMigrationSrcBillingDetail()) {
                IMigrationService billingDetailService = new BillingDetailMigrationService();
                billingDetailService.execute(con, batchStartTime);
            }
        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage(), e);
            System.exit(1);
        } catch (Exception e) {
            logger.error("予期せぬエラーが発生しました", e);
            System.exit(1);
        }

        System.exit(0);
    }
}
