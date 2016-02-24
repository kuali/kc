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
package org.kuali.coeus.coi.framework;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class AbstractMultiSponsorProjectRetrievalService extends AbstractProjectRetrievalService {

    @Override
    public Collection<Project> retrieveProjects() {
        final Map<String, Project> projects = getProjectsMap();

        if (!projects.isEmpty()) {
            final Map<String, List<ProjectPerson>> persons = getPersonsMap();
            persons.forEach((k,v) -> projects.get(k).setPersons(v));

            final Map<String, List<ProjectSponsor>> sponsors = getJdbcOperations().query(c -> c.prepareStatement(allProjectSponsorQuery()), (rs, rowNum) -> {
                return toProjectSponsor(rs);
            }).stream().collect(Collectors.groupingBy(ProjectSponsor::getSourceIdentifier));
            sponsors.forEach((k, v) -> setSponsorFields(v, projects.get(k)));
        }

        return projects.values();
    }

    @Override
    public Project retrieveProject(String sourceIdentifier) {
        final Project project = super.retrieveProject(sourceIdentifier);

        if (project != null) {
            final List<ProjectSponsor> sponsors = getJdbcOperations().query(c -> {
                PreparedStatement statement = c.prepareStatement(projectSponsorQuery());
                statement.setString(1, sourceIdentifier);
                return statement;
            }, (rs, rowNum) -> {
                return toProjectSponsor(rs);
            });
            setSponsorFields(sponsors, project);
        }

        return project;
    }

    protected abstract ProjectSponsor toProjectSponsor(ResultSet rs) throws SQLException;
    protected abstract String allProjectSponsorQuery();
    protected abstract String projectSponsorQuery();
}
