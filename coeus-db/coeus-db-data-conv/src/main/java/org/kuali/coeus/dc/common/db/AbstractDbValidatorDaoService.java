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

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class AbstractDbValidatorDaoService implements DbValidatorDaoService {

    private static final Logger LOG = Logger.getLogger(AbstractDbValidatorDaoService.class.getName());

    private ConnectionDaoService connectionService;

    @Override
    public boolean isValidRiceConnection() {
        Connection conn = connectionService.getRiceConnection();
        try (PreparedStatement stmt = conn.prepareStatement(getValidationQuery());
             ResultSet rs = stmt.executeQuery()) {
            return true;
        } catch (SQLException e) {
            LOG.log(Level.INFO, "validation failed", e);
        }
        return false;
    }

    @Override
    public boolean isValidCoeusConnection() {
        final Connection conn = connectionService.getCoeusConnection();
        try (PreparedStatement stmt = conn.prepareStatement(getValidationQuery());
             ResultSet rs = stmt.executeQuery()) {
            return true;
        } catch (SQLException e) {
            LOG.log(Level.INFO, "validation failed", e);
        }
        return false;
    }

    public ConnectionDaoService getConnectionService() {
        return connectionService;
    }

    public void setConnectionService(ConnectionDaoService connectionService) {
        this.connectionService = connectionService;
    }

    public abstract String getValidationQuery();
}
