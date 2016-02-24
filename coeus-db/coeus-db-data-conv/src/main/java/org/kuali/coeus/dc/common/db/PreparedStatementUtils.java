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
package org.kuali.coeus.dc.common.db;


import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public final class PreparedStatementUtils {

    private PreparedStatementUtils() {
        throw new UnsupportedOperationException("do not call");
    }

    public static PreparedStatement setString(int index, String string, PreparedStatement stmt) throws SQLException {
        stmt.setString(index, string);
        return stmt;
    }

    public static PreparedStatement setLong(int index, Long l, PreparedStatement stmt) throws SQLException {
        stmt.setLong(index, l);
        return stmt;
    }

    public static PreparedStatement setTimestamp(int index, Timestamp t, PreparedStatement stmt) throws SQLException {
        stmt.setTimestamp(index, t);
        return stmt;
    }
}
