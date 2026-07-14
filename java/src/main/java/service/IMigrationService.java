package main.java.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;

public interface IMigrationService {
    void execute(Connection con, LocalDateTime batchStartTime) throws SQLException;
}
