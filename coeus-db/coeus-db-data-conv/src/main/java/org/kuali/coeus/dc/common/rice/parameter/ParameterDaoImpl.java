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
package org.kuali.coeus.dc.common.rice.parameter;


import org.kuali.coeus.dc.common.db.ConnectionDaoService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.kuali.coeus.dc.common.db.PreparedStatementUtils.setString;

public class ParameterDaoImpl implements ParameterDao {

    private ConnectionDaoService connectionDaoService;

    @Override
    public Parameter getParameter(ParameterKey key) {
        Connection connection = connectionDaoService.getRiceConnection();
        try (PreparedStatement stmt =
                     setString(4, key.getApplicationId(),
                     setString(3, key.getName(),
                     setString(2, key.getComponentCode(),
                     setString(1, key.getNamespaceCode(),
                             connection.prepareStatement("SELECT VAL, PARM_DESC_TXT, PARM_TYP_CD, EVAL_OPRTR_CD " +
                                     " FROM KRCR_PARM_T WHERE NMSPC_CD = ? AND CMPNT_CD = ? AND PARM_NM = ? AND APPL_ID = ?")))));
             ResultSet result = stmt.executeQuery()) {
            if (result.next()) {
                Parameter p = new Parameter();
                p.setParameterKey(key);
                p.setValue(result.getString(1));
                p.setDescription(result.getString(2));
                p.setParameterTypeCode(result.getString(3));
                p.setEvaluationOperatorCode(result.getString(4));

                return p;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteParameter(ParameterKey key) {
        Connection connection = connectionDaoService.getRiceConnection();
        try (PreparedStatement stmt =
                     setString(4, key.getApplicationId(),
                     setString(3, key.getName(),
                     setString(2, key.getComponentCode(),
                     setString(1, key.getNamespaceCode(),
                       connection.prepareStatement("DELETE FROM KRCR_PARM_T WHERE NMSPC_CD = ? AND CMPNT_CD = ? AND PARM_NM = ? AND APPL_ID = ?")))));) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ConnectionDaoService getConnectionDaoService() {
        return connectionDaoService;
    }

    public void setConnectionDaoService(ConnectionDaoService connectionDaoService) {
        this.connectionDaoService = connectionDaoService;
    }
}
