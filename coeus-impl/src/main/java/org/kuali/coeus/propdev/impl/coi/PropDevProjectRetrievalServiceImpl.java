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
package org.kuali.coeus.propdev.impl.coi;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.coi.framework.*;
import org.kuali.kra.infrastructure.Constants;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;

@Component("propDevProjectRetrievalService")
public class PropDevProjectRetrievalServiceImpl extends AbstractProjectRetrievalService {

    private static final String PD_ALL_PROJECT_QUERY = "SELECT t.TITLE, t.PROPOSAL_NUMBER, t.STATUS_CODE, t.REQUESTED_START_DATE_INITIAL, t.REQUESTED_END_DATE_INITIAL, t.SPONSOR_CODE, u.SPONSOR_NAME FROM EPS_PROPOSAL t LEFT OUTER JOIN SPONSOR u ON t.SPONSOR_CODE = u.SPONSOR_CODE";
    private static final String PD_ALL_PROJECT_PERSON_QUERY = "SELECT t.PROPOSAL_NUMBER, t.PERSON_ID, t.ROLODEX_ID, t.PROP_PERSON_ROLE_ID FROM EPS_PROP_PERSON t";

    private static final String PD_PROJECT_QUERY = PD_ALL_PROJECT_QUERY + " WHERE t.PROPOSAL_NUMBER = ?";
    private static final String PD_PROJECT_PERSON_QUERY = PD_ALL_PROJECT_PERSON_QUERY + " WHERE t.PROPOSAL_NUMBER = ?";

    @Override
    protected Project toProject(ResultSet rs) throws SQLException {
        final Project project = new Project();
        project.setTitle(rs.getString(1));
        project.setTypeCode(ProjectTypeCode.PROPOSAL.getId());
        project.setSourceSystem(Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT);
        final String sourceIdentifier = rs.getString(2);
        project.setSourceIdentifier(sourceIdentifier);
        project.setSourceStatus(rs.getString(3));
        project.setStartDate(rs.getDate(4));
        project.setEndDate(rs.getDate(5));
        setSponsorFields(Collections.singletonList(new ProjectSponsor(Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT, sourceIdentifier, rs.getString(6), rs.getString(7))), project);

        return project;
    }

    @Override
    protected ProjectPerson toProjectPerson(ResultSet rs) throws SQLException {
        final ProjectPerson person = new ProjectPerson();
        person.setSourceSystem(Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT);
        person.setSourceIdentifier(rs.getString(1));
        final String personId = rs.getString(2);
        final String rolodexId = rs.getString(3);
        person.setPersonId(StringUtils.isNotBlank(personId) ? personId : rolodexId);
        person.setSourcePersonType(StringUtils.isNotBlank(personId) ? PersonType.EMPLOYEE.toString() : PersonType.NONEMPLOYEE.toString());
        person.setRoleCode(rs.getString(4));

        return person;
    }

    @Override
    protected String allProjectQuery() {
        return PD_ALL_PROJECT_QUERY;
    }

    @Override
    protected String allProjectPersonQuery() {
        return PD_ALL_PROJECT_PERSON_QUERY;
    }

    @Override
    protected String projectQuery() {
        return PD_PROJECT_QUERY;
    }

    @Override
    protected String projectPersonQuery() {
        return PD_PROJECT_PERSON_QUERY;
    }
}
