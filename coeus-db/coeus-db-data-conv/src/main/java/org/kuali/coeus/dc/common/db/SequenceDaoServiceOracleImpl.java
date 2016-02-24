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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SequenceDaoServiceOracleImpl implements SequenceDaoService {

    private ConnectionDaoService connectionDaoService;

    @Override
    public String getNextRiceSequence(String sequenceName, String prefix) {
        Connection connection = connectionDaoService.getRiceConnection();
        try {
            return getNextSequence(sequenceName, prefix, connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getNextCoeusSequence(String sequenceName, String prefix) {
        Connection connection = connectionDaoService.getCoeusConnection();
        try {
            return getNextSequence(sequenceName, prefix, connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected String getNextSequence(String sequenceName, String prefix, Connection connection) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement("SELECT " + sequenceName + ".NEXTVAL FROM DUAL");
             ResultSet result = stmt.executeQuery()) {
            result.next();
            return prefix + result.getString(1);
        }
    }

    public ConnectionDaoService getConnectionDaoService() {
        return connectionDaoService;
    }

    public void setConnectionDaoService(ConnectionDaoService connectionDaoService) {
        this.connectionDaoService = connectionDaoService;
    }
}
