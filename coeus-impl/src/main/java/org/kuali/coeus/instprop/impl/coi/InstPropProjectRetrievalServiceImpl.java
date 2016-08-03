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
package org.kuali.coeus.instprop.impl.coi;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.coi.framework.*;
import org.kuali.kra.infrastructure.Constants;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Component("instPropProjectRetrievalService")
public class InstPropProjectRetrievalServiceImpl extends AbstractProjectRetrievalService {

    private static final String IP_ALL_HIGHEST_SEQUENCE_QUERY = "SELECT PROPOSAL_NUMBER, MAX(SEQUENCE_NUMBER) maxseq FROM PROPOSAL WHERE PROPOSAL_SEQUENCE_STATUS = 'ACTIVE' or PROPOSAL_SEQUENCE_STATUS='PENDING' GROUP BY PROPOSAL_NUMBER";

    private static final String IP_ALL_PROJECT_QUERY = "SELECT t.TITLE, t.PROPOSAL_ID, t.STATUS_CODE, t.REQUESTED_START_DATE_INITIAL, t.REQUESTED_END_DATE_INITIAL, t.SPONSOR_CODE, u.SPONSOR_NAME, t.PROPOSAL_NUMBER, t.SEQUENCE_NUMBER FROM (" + IP_ALL_HIGHEST_SEQUENCE_QUERY + ") x " +
            "INNER JOIN PROPOSAL t ON t.PROPOSAL_NUMBER = x.PROPOSAL_NUMBER and t.SEQUENCE_NUMBER = x.maxseq " +
            "LEFT OUTER JOIN SPONSOR u ON t.SPONSOR_CODE = u.SPONSOR_CODE";

    private static final String IP_ALL_PROJECT_PERSON_QUERY = "SELECT t.PROPOSAL_ID, t.PERSON_ID, t.ROLODEX_ID, t.CONTACT_ROLE_CODE, t.PROPOSAL_NUMBER, t.SEQUENCE_NUMBER FROM (" + IP_ALL_HIGHEST_SEQUENCE_QUERY + ") x " +
            "INNER JOIN PROPOSAL_PERSONS t ON t.PROPOSAL_NUMBER = x.PROPOSAL_NUMBER and t.SEQUENCE_NUMBER = x.maxseq";

    private static final String IP_PROJECT_QUERY = IP_ALL_PROJECT_QUERY + " WHERE t.PROPOSAL_NUMBER = ?";
    private static final String IP_PROJECT_PERSON_QUERY = IP_ALL_PROJECT_PERSON_QUERY + " WHERE t.PROPOSAL_NUMBER = ?";

    @Override
    protected Project toProject(ResultSet rs) throws SQLException {
        final Project project = new Project();
        project.setTitle(rs.getString(1));
        project.setTypeCode(ProjectTypeCode.INSTITUTIONAL_PROPOSAL.getId());
        project.setSourceSystem(Constants.MODULE_NAMESPACE_INSITUTIONAL_PROPOSAL);
        final String sourceIdentifier = rs.getString(8);
        project.setSourceIdentifier(sourceIdentifier);
        project.setSourceStatus(rs.getString(3));
        project.setStartDate(rs.getDate(4));
        project.setEndDate(rs.getDate(5));

        final Map<String, String> metadata = new HashMap<>();
        metadata.put(SOURCE_UNIQUE_IDENTIFIER_METADATA, rs.getString(2));
        project.setMetadata(metadata);

        setSponsorFields(Collections.singletonList(new ProjectSponsor(Constants.MODULE_NAMESPACE_INSITUTIONAL_PROPOSAL, sourceIdentifier, rs.getString(6), rs.getString(7), new HashMap<>(metadata))), project);

        return project;
    }

    @Override
    protected ProjectPerson toProjectPerson(ResultSet rs) throws SQLException {
        final ProjectPerson person = new ProjectPerson();
        person.setSourceSystem(Constants.MODULE_NAMESPACE_INSITUTIONAL_PROPOSAL);
        person.setSourceIdentifier(rs.getString(5));
        final String personId = rs.getString(2);
        final String rolodexId = rs.getString(3);
        person.setPersonId(StringUtils.isNotBlank(personId) ? personId : rolodexId);
        person.setSourcePersonType(StringUtils.isNotBlank(personId) ? PersonType.EMPLOYEE.toString() : PersonType.NONEMPLOYEE.toString());
        person.setRoleCode(rs.getString(4));

        final Map<String, String> metadata = new HashMap<>();
        metadata.put(SOURCE_UNIQUE_IDENTIFIER_METADATA, rs.getString(1));

        person.setMetadata(metadata);

        return person;
    }

    @Override
    protected String allProjectQuery() {
        return IP_ALL_PROJECT_QUERY;
    }

    @Override
    protected String allProjectPersonQuery() {
        return IP_ALL_PROJECT_PERSON_QUERY;
    }

    @Override
    protected String projectQuery() {
        return IP_PROJECT_QUERY;
    }

    @Override
    protected String projectPersonQuery() {
        return IP_PROJECT_PERSON_QUERY;
    }

}
