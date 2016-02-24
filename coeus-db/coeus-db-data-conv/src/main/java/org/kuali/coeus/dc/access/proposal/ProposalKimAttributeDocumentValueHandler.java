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
import org.kuali.coeus.dc.access.kim.KimAttributeDocumentValueHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import static org.kuali.coeus.dc.common.db.PreparedStatementUtils.*;

public class ProposalKimAttributeDocumentValueHandler implements KimAttributeDocumentValueHandler {

    private static final Logger LOG = Logger.getLogger(ProposalKimAttributeDocumentValueHandler.class.getName());

    private ProposalKimAttributeDefnDao proposalKimAttributeDefnDao;
    private ConnectionDaoService connectionDaoService;
    private boolean delete;

    @Override
    public String transform(String val) {
        Connection connection = connectionDaoService.getCoeusConnection();
        try (PreparedStatement stmt = setString(1, val, connection.prepareStatement("SELECT DOCUMENT_NUMBER FROM EPS_PROPOSAL WHERE PROPOSAL_NUMBER = ?"));
            ResultSet result = stmt.executeQuery()) {
            if (result.next()) {
                return result.getString(1);
            } else {
                LOG.warning("cannot find document number for proposal: " + val);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public boolean isDocumentValueType(String attrDefnId) {
        return proposalKimAttributeDefnDao.getDocumentQualifierAttrDefnId().equals(attrDefnId);
    }

    @Override
    public void cleanup() {
        if (!proposalKimAttributeDefnDao.isDocumentQualifierAttrDefnUsed()) {
            if (delete) {
                    proposalKimAttributeDefnDao.deleteDocumentQualifierAttrDefn();
            } else {
                proposalKimAttributeDefnDao.inactivateDocumentQualifierAttrDefn();
            }
        } else {
            LOG.warning("Proposal Attribute Definition is still used.  It cannot be "
                    + (delete ? "deleted" : "inactivated") + ".");
        }
    }

    public ProposalKimAttributeDefnDao getProposalKimAttributeDefnDao() {
        return proposalKimAttributeDefnDao;
    }

    public void setProposalKimAttributeDefnDao(ProposalKimAttributeDefnDao proposalKimAttributeDefnDao) {
        this.proposalKimAttributeDefnDao = proposalKimAttributeDefnDao;
    }

    public ConnectionDaoService getConnectionDaoService() {
        return connectionDaoService;
    }

    public void setConnectionDaoService(ConnectionDaoService connectionDaoService) {
        this.connectionDaoService = connectionDaoService;
    }

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }
}
