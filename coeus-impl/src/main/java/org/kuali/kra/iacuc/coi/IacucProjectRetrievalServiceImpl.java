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
package org.kuali.kra.iacuc.coi;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.coi.framework.*;
import org.kuali.kra.infrastructure.Constants;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component("iacucProjectRetrievalService")
public class IacucProjectRetrievalServiceImpl extends AbstractMultiSponsorProjectRetrievalService {

    private static final String IACUC_ALL_PROJECT_QUERY = "SELECT t.TITLE, t.PROTOCOL_ID, t.PROTOCOL_STATUS_CODE, t.APPLICATION_DATE, t.EXPIRATION_DATE FROM IACUC_PROTOCOL t";
    private static final String IACUC_ALL_PROJECT_PERSON_QUERY = "SELECT t.PROTOCOL_ID, t.PERSON_ID, t.ROLODEX_ID, t.PROTOCOL_PERSON_ROLE_ID FROM IACUC_PROTOCOL_PERSONS t";
    private static final String IACUC_ALL_PROJECT_SPONSOR_QUERY = "SELECT t.PROTOCOL_ID, t.FUNDING_SOURCE, t.FUNDING_SOURCE_NAME FROM IACUC_PROTOCOL_FUNDING_SOURCE t WHERE t.FUNDING_SOURCE_TYPE_CODE = '1'";

    private static final String IACUC_PROJECT_QUERY = IACUC_ALL_PROJECT_QUERY + " WHERE t.PROTOCOL_ID = ?";
    private static final String IACUC_PROJECT_PERSON_QUERY = IACUC_ALL_PROJECT_PERSON_QUERY + " WHERE t.PROTOCOL_ID = ?";
    private static final String IACUC_PROJECT_SPONSOR_QUERY = IACUC_ALL_PROJECT_SPONSOR_QUERY + " AND t.PROTOCOL_ID = ?";

    @Override
    protected Project toProject(ResultSet rs) throws SQLException {
        final Project project = new Project();
        project.setTitle(rs.getString(1));
        project.setTypeCode(ProjectTypeCode.IACUC_PROTOCOL.getId());
        project.setSourceSystem(Constants.MODULE_NAMESPACE_IACUC);
        project.setSourceIdentifier(rs.getString(2));
        project.setSourceStatus(rs.getString(3));
        project.setStartDate(rs.getDate(4));
        project.setEndDate(rs.getDate(5));

        return project;
    }

    @Override
    protected ProjectPerson toProjectPerson(ResultSet rs) throws SQLException {
        final ProjectPerson person = new ProjectPerson();
        person.setSourceSystem(Constants.MODULE_NAMESPACE_IACUC);
        person.setSourceIdentifier(rs.getString(1));
        final String personId = rs.getString(2);
        final String rolodexId = rs.getString(3);
        person.setPersonId(StringUtils.isNotBlank(personId) ? personId : rolodexId);
        person.setSourcePersonType(StringUtils.isNotBlank(personId) ? PersonType.EMPLOYEE.toString() : PersonType.NONEMPLOYEE.toString());
        person.setRoleCode(rs.getString(4));

        return person;
    }

    @Override
    protected ProjectSponsor toProjectSponsor(ResultSet rs) throws SQLException {
        final ProjectSponsor sponsor = new ProjectSponsor();
        sponsor.setSourceSystem(Constants.MODULE_NAMESPACE_IACUC);
        sponsor.setSourceIdentifier(rs.getString(1));
        sponsor.setSponsorCode(rs.getString(2));
        sponsor.setSponsorName(rs.getString(3));

        return sponsor;
    }

    @Override
    protected String allProjectQuery() {
        return IACUC_ALL_PROJECT_QUERY;
    }

    @Override
    protected String allProjectPersonQuery() {
        return IACUC_ALL_PROJECT_PERSON_QUERY;
    }

    @Override
    protected String allProjectSponsorQuery() {
        return IACUC_ALL_PROJECT_SPONSOR_QUERY;
    }

    @Override
    protected String projectQuery() {
        return IACUC_PROJECT_QUERY;
    }

    @Override
    protected String projectPersonQuery() {
        return IACUC_PROJECT_PERSON_QUERY;
    }

    @Override
    protected String projectSponsorQuery() {
        return IACUC_PROJECT_SPONSOR_QUERY;
    }
}
