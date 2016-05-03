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
import org.kuali.kra.bo.FundingSourceType;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.protocol.ProtocolSpecialVersion;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component("iacucProjectRetrievalService")
public class IacucProjectRetrievalServiceImpl extends AbstractMultiSponsorProjectRetrievalService {

    private static final String IACUC_SPECIAL_VALUES_CRITERIA = Arrays.stream(ProtocolSpecialVersion.values())
            .map(v -> "PROTOCOL_NUMBER NOT LIKE '%" + v.getCode() + "%'")
            .collect(Collectors.joining(" AND "));

    private static final String IACUC_ALL_HIGHEST_SEQUENCE_QUERY = "SELECT PROTOCOL_NUMBER, MAX(SEQUENCE_NUMBER) maxseq FROM IACUC_PROTOCOL " +
            "WHERE " + IACUC_SPECIAL_VALUES_CRITERIA + " " +
            "GROUP BY PROTOCOL_NUMBER";

    private static final String IACUC_ALL_PROJECT_QUERY = "SELECT t.TITLE, t.PROTOCOL_ID, t.PROTOCOL_STATUS_CODE, t.APPLICATION_DATE, t.EXPIRATION_DATE, t.PROTOCOL_NUMBER, t.SEQUENCE_NUMBER FROM (" + IACUC_ALL_HIGHEST_SEQUENCE_QUERY + ") x " +
            "INNER JOIN IACUC_PROTOCOL t ON t.PROTOCOL_NUMBER = x.PROTOCOL_NUMBER and t.SEQUENCE_NUMBER = x.maxseq";

    private static final String IACUC_ALL_PROJECT_PERSON_QUERY = "SELECT t.PROTOCOL_ID, t.PERSON_ID, t.ROLODEX_ID, t.PROTOCOL_PERSON_ROLE_ID, t.PROTOCOL_NUMBER, t.SEQUENCE_NUMBER FROM (" + IACUC_ALL_HIGHEST_SEQUENCE_QUERY + ") x " +
            "INNER JOIN IACUC_PROTOCOL_PERSONS t ON t.PROTOCOL_NUMBER = x.PROTOCOL_NUMBER and t.SEQUENCE_NUMBER = x.maxseq";

    private static final String IACUC_ALL_PROJECT_SPONSOR_QUERY = "SELECT t.PROTOCOL_ID, t.FUNDING_SOURCE, t.FUNDING_SOURCE_NAME, t.PROTOCOL_NUMBER, t.SEQUENCE_NUMBER FROM (" + IACUC_ALL_HIGHEST_SEQUENCE_QUERY + ") x " +
            "INNER JOIN IACUC_PROTOCOL_FUNDING_SOURCE t ON t.PROTOCOL_NUMBER = x.PROTOCOL_NUMBER and t.SEQUENCE_NUMBER = x.maxseq " +
            "WHERE t.FUNDING_SOURCE_TYPE_CODE = '" + FundingSourceType.SPONSOR + "'";

    private static final String IACUC_PROJECT_QUERY = IACUC_ALL_PROJECT_QUERY + " WHERE t.PROTOCOL_NUMBER = ?";
    private static final String IACUC_PROJECT_PERSON_QUERY = IACUC_ALL_PROJECT_PERSON_QUERY + " WHERE t.PROTOCOL_NUMBER = ?";
    private static final String IACUC_PROJECT_SPONSOR_QUERY = IACUC_ALL_PROJECT_SPONSOR_QUERY + " AND t.PROTOCOL_NUMBER = ?";

    @Override
    protected Project toProject(ResultSet rs) throws SQLException {
        final Project project = new Project();
        project.setTitle(rs.getString(1));
        project.setTypeCode(ProjectTypeCode.IACUC_PROTOCOL.getId());
        project.setSourceSystem(Constants.MODULE_NAMESPACE_IACUC);
        project.setSourceIdentifier(rs.getString(6));
        project.setSourceStatus(rs.getString(3));
        project.setStartDate(rs.getDate(4));
        project.setEndDate(rs.getDate(5));

        final Map<String, String> metadata = new HashMap<>();
        metadata.put(SOURCE_UNIQUE_IDENTIFIER_METADATA, rs.getString(2));
        project.setMetadata(metadata);

        return project;
    }

    @Override
    protected ProjectPerson toProjectPerson(ResultSet rs) throws SQLException {
        final ProjectPerson person = new ProjectPerson();
        person.setSourceSystem(Constants.MODULE_NAMESPACE_IACUC);
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
    protected ProjectSponsor toProjectSponsor(ResultSet rs) throws SQLException {
        final ProjectSponsor sponsor = new ProjectSponsor();
        sponsor.setSourceSystem(Constants.MODULE_NAMESPACE_IACUC);
        sponsor.setSourceIdentifier(rs.getString(4));
        sponsor.setSponsorCode(rs.getString(2));
        sponsor.setSponsorName(rs.getString(3));

        final Map<String, String> metadata = new HashMap<>();
        metadata.put(SOURCE_UNIQUE_IDENTIFIER_METADATA, rs.getString(1));
        sponsor.setMetadata(metadata);

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
