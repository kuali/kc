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
package org.kuali.coeus.award.impl.coi;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.coi.framework.*;
import org.kuali.kra.infrastructure.Constants;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;

@Component("awardProjectRetrievalService")
public class AwardProjectRetrievalServiceImpl extends AbstractProjectRetrievalService {
    private static final String AWARD_ALL_PROJECT_QUERY = "SELECT t.TITLE, t.AWARD_ID, t.STATUS_CODE, t.AWARD_EFFECTIVE_DATE, t.SPONSOR_CODE, u.SPONSOR_NAME FROM AWARD t LEFT OUTER JOIN SPONSOR u ON t.SPONSOR_CODE = u.SPONSOR_CODE";
    private static final String AWARD_ALL_PROJECT_PERSON_QUERY = "SELECT t.AWARD_ID, t.PERSON_ID, t.ROLODEX_ID, t.CONTACT_ROLE_CODE FROM AWARD_PERSONS t";

    private static final String AWARD_PROJECT_QUERY = AWARD_ALL_PROJECT_QUERY + " WHERE t.AWARD_ID = ?";
    private static final String AWARD_PROJECT_PERSON_QUERY = AWARD_ALL_PROJECT_PERSON_QUERY + " WHERE t.AWARD_ID = ?";

    @Override
    protected Project toProject(ResultSet rs) throws SQLException {
        final Project project = new Project();
        project.setTitle(rs.getString(1));
        project.setTypeCode(ProjectTypeCode.AWARD.getId());
        project.setSourceSystem(Constants.MODULE_NAMESPACE_AWARD);
        final String sourceIdentifier = rs.getString(2);
        project.setSourceIdentifier(sourceIdentifier);
        project.setSourceStatus(rs.getString(3));
        project.setStartDate(rs.getDate(4));
        setSponsorFields(Collections.singletonList(new ProjectSponsor(Constants.MODULE_NAMESPACE_AWARD, sourceIdentifier, rs.getString(5), rs.getString(6))), project);

        return project;
    }

    @Override
    protected ProjectPerson toProjectPerson(ResultSet rs) throws SQLException {
        final ProjectPerson person = new ProjectPerson();
        person.setSourceSystem(Constants.MODULE_NAMESPACE_AWARD);
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
        return AWARD_ALL_PROJECT_QUERY;
    }

    @Override
    protected String allProjectPersonQuery() {
        return AWARD_ALL_PROJECT_PERSON_QUERY;
    }

    @Override
    protected String projectQuery() {
        return AWARD_PROJECT_QUERY;
    }

    @Override
    protected String projectPersonQuery() {
        return AWARD_PROJECT_PERSON_QUERY;
    }
}
