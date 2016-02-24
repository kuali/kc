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
package org.kuali.coeus.dc.access.kim;

import org.kuali.coeus.dc.common.db.ConnectionDaoService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class KimTypeDaoImpl implements KimTypeDao {

    private ConnectionDaoService connectionService;

    @Override
    public String getDocAccessKimTypeId() {
        Connection connection = connectionService.getRiceConnection();
        try (PreparedStatement stmt = connection.prepareStatement("select KIM_TYP_ID from KRIM_TYP_T WHERE NM = 'Derived Role: Document Access' AND NMSPC_CD = 'KC-SYS'");
             ResultSet result = stmt.executeQuery()) {
            if (result.next()) {
                return result.getString(1);
            } else {
                throw new IllegalStateException("can't find document access kim type");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ConnectionDaoService getConnectionService() {
        return connectionService;
    }

    public void setConnectionService(ConnectionDaoService connectionService) {
        this.connectionService = connectionService;
    }
}
