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

package org.kuali.coeus.dc.proposalpersons;

import org.kuali.coeus.dc.common.db.ConnectionDaoService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.logging.Logger;

public class ProposalPersonsDaoImpl implements ProposalPersonsDao {
    public static final String SELECT_PROPOSAL_PERSONS = "select proposal_person_id, person_id, full_name from proposal_persons";
    public static final String SELECT_NAMES = "select n.first_nm, n.middle_nm, n.last_nm from krim_entity_nm_t as n, krim_prncpl_t as p where p.prncpl_id = ? and p.entity_id = n.entity_id";
    public static final String UPDATE_PROPOSAL_PERSON = "update proposal_persons set full_name = ? where proposal_person_id = ?";

    private static final Logger LOG = Logger.getLogger(ProposalPersonsDaoImpl.class.getName());

    private ConnectionDaoService connectionDaoService;

    @Override
    public void fixFullNames() {
        Connection connection = connectionDaoService.getCoeusConnection();
        try (
                PreparedStatement selectProposalPersons = connection.prepareStatement(SELECT_PROPOSAL_PERSONS);
                PreparedStatement selectNames = connection.prepareStatement(SELECT_NAMES);
                PreparedStatement updateProposalPerson = connection.prepareStatement(UPDATE_PROPOSAL_PERSON);
        ) {
            int updated = 0;
            ResultSet results = selectProposalPersons.executeQuery();
            while(results.next()) {
                String oldName = results.getString(3);
                String newName = getNames(selectNames, results);
                if (!newName.equals(oldName)) {
                    String proposalPersonId = results.getString(1);
                    updateProposalPerson.setString(1, newName);
                    updateProposalPerson.setString(2, proposalPersonId);
                    updateProposalPerson.executeUpdate();
                    LOG.info("Updating Proposal Person ID # " + proposalPersonId + " full name from '" + oldName + "' to '" + newName + "'");
                    updated ++;
                }

            }

            LOG.info("Proposal persons full name update complete: " + updated + " records updated");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String getNames(PreparedStatement selectNames, ResultSet results) throws SQLException {
        selectNames.setString(1, results.getString(2));
        ResultSet names = selectNames.executeQuery();
        names.next();

        return getFullName(names.getString(1), names.getString(2), names.getString(3));
    }

    private String getFullName(String firstName, String middleName, String lastName) {
        middleName = middleName != null &&!middleName.isEmpty() ? middleName + " " : "";
        return firstName + " " + middleName + lastName.trim();
    }

    public ConnectionDaoService getConnectionDaoService() {
        return connectionDaoService;
    }

    public void setConnectionDaoService(ConnectionDaoService connectionDaoService) {
        this.connectionDaoService = connectionDaoService;
    }
}
