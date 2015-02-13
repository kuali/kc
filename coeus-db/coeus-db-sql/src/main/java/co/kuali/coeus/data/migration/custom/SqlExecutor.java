package co.kuali.coeus.data.migration.custom;

import java.sql.Connection;
import java.sql.SQLException;

public interface SqlExecutor {
    boolean executeInTransaction();
    void execute(Connection connection) throws SQLException;
}
