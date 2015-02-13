package co.kuali.coeus.data.migration;

import java.sql.Connection;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class MigrationUtils {
	
	private static final Logger LOG = LoggerFactory.getLogger(MigrationUtils.class);

	public static final String MYSQL_PRODUCT = "MySQL";
	public static final String ORACLE_PRODUCT = "ORACLE";
	public static enum DatabaseType {
		Mysql, Oracle, Unsupported
	};
	
	public static DatabaseType getDatabaseTypeFromConnection(Connection conn) throws SQLException {
		final String dbProduct = conn.getMetaData().getDatabaseProductName();
		LOG.info("database product: " + dbProduct);
		if (isMysqlConnection(dbProduct)) {
			return DatabaseType.Mysql;
		} else if (isOracleConnection(dbProduct)) {
			return DatabaseType.Oracle;
		} else {
			return DatabaseType.Unsupported;
		}
		
	}
	
	public static boolean isMysqlConnection(String productName) throws SQLException {
		return MYSQL_PRODUCT.equalsIgnoreCase(productName);
	}
	
	public static boolean isOracleConnection(String productName) throws SQLException {
		return productName.toUpperCase().contains(ORACLE_PRODUCT);
	}
	
	private MigrationUtils() {
		throw new UnsupportedOperationException("static class cannot be instantiated");
	}
}
