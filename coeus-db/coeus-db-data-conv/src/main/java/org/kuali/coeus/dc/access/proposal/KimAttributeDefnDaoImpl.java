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
package org.kuali.coeus.dc.access.proposal;

import org.kuali.coeus.dc.common.db.ConnectionDaoService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.kuali.coeus.dc.common.db.PreparedStatementUtils.*;

public class KimAttributeDefnDaoImpl implements ProposalKimAttributeDefnDao {

    public static final String KC_SYS = "KC-SYS";
    public static final String PROPOSAL = "proposal";

    private ConnectionDaoService connectionDaoService;

    @Override
    public String getDocumentQualifierAttrDefnId() {
        Connection connection = connectionDaoService.getRiceConnection();
        try (PreparedStatement stmt = setString(2, KC_SYS,
                                     setString(1, PROPOSAL, connection.prepareStatement("SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NM = ? AND NMSPC_CD = ?")));
            ResultSet result = stmt.executeQuery()) {
            if (result.next()) {
                return result.getString(1);
            } else {
                throw new IllegalStateException("proposal attribute definition not found");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteDocumentQualifierAttrDefn() {
        Connection connection = connectionDaoService.getRiceConnection();
        try (PreparedStatement stmt = setString(2, KC_SYS,
                setString(1, PROPOSAL, connection.prepareStatement("DELETE FROM KRIM_ATTR_DEFN_T WHERE NM = ? AND NMSPC_CD = ?")))) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void inactivateDocumentQualifierAttrDefn() {
        Connection connection = connectionDaoService.getRiceConnection();
        try (PreparedStatement stmt = setString(2, KC_SYS,
                setString(1, PROPOSAL, connection.prepareStatement("UPDATE KRIM_ATTR_DEFN_T SET ACTV_IND = 'F' WHERE NM = ? AND NMSPC_CD = ?")))) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean isDocumentQualifierAttrDefnUsed() {
        final String defnId = getDocumentQualifierAttrDefnId();

        Connection connection = connectionDaoService.getRiceConnection();
        try (PreparedStatement stmt =
                    setString(6, defnId,
                    setString(5, defnId,
                    setString(4, defnId,
                    setString(3, defnId,
                    setString(2, defnId,
                    setString(1, defnId, connection.prepareStatement(
                "SELECT count(*) FROM krim_dlgn_mbr_attr_data_t WHERE KIM_ATTR_DEFN_ID = ? UNION " +
                "SELECT count(*) FROM krim_grp_attr_data_t WHERE KIM_ATTR_DEFN_ID = ? UNION " +
                "SELECT count(*) FROM krim_perm_attr_data_t WHERE KIM_ATTR_DEFN_ID = ? UNION " +
                "SELECT count(*) FROM krim_role_mbr_attr_data_t WHERE KIM_ATTR_DEFN_ID = ? UNION " +
                "SELECT count(*) FROM krim_rsp_attr_data_t WHERE KIM_ATTR_DEFN_ID = ? UNION " +
                "SELECT count(*) FROM krim_typ_attr_t WHERE KIM_ATTR_DEFN_ID = ?")))))));
             ResultSet result = stmt.executeQuery()) {
            while (result.next()) {
                if (result.getInt(1) > 0) {
                    return true;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public ConnectionDaoService getConnectionDaoService() {
        return connectionDaoService;
    }

    public void setConnectionDaoService(ConnectionDaoService connectionDaoService) {
        this.connectionDaoService = connectionDaoService;
    }
}
