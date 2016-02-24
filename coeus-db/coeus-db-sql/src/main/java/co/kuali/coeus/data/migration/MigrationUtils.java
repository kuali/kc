/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
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
