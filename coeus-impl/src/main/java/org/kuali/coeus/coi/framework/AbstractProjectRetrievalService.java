package org.kuali.coeus.coi.framework;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcOperations;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.kuali.coeus.sys.framework.util.CollectionUtils.entriesToMap;
import static org.kuali.coeus.sys.framework.util.CollectionUtils.entry;

public abstract class AbstractProjectRetrievalService implements ProjectRetrievalService {

    @Autowired
    @Qualifier("jdbcOperations")
    private JdbcOperations jdbcOperations;

    @Override
    public Collection<Project> retrieveProjects() {
        final Map<String, Project> projects = getProjectsMap();

        if (!projects.isEmpty()) {
            final Map<String, List<ProjectPerson>> persons = getPersonsMap();
            persons.forEach((k,v) -> projects.get(k).setPersons(v));
        }

        return projects.values();
    }

    protected Map<String, Project> getProjectsMap() {
        return jdbcOperations.query(c -> c.prepareStatement(allProjectQuery()), (rs, rowNum) -> {
                final Project project = toProject(rs);
                return entry(project.getSourceIdentifier(), project);
            }).stream().collect(entriesToMap());
    }

    protected Map<String, List<ProjectPerson>> getPersonsMap() {
        return jdbcOperations.query(c -> c.prepareStatement(allProjectPersonQuery()), (rs, rowNum) -> {
            return toProjectPerson(rs);
        }).stream().collect(Collectors.groupingBy(ProjectPerson::getSourceIdentifier));
    }

    @Override
    public Project retrieveProject(String sourceIdentifier) {
        if (StringUtils.isBlank(sourceIdentifier)) {
            throw new IllegalArgumentException("sourceIdentifier is blank");
        }

        final Project project = jdbcOperations.query(c -> {
            PreparedStatement statement = c.prepareStatement(projectQuery());
            statement.setString(1, sourceIdentifier);
            return statement;
        }, (rs) -> (rs.next() ? toProject(rs) : null));

        if (project != null) {
            final List<ProjectPerson> persons = jdbcOperations.query(c -> {
                PreparedStatement statement = c.prepareStatement(projectPersonQuery());
                statement.setString(1, sourceIdentifier);
                return statement;
            }, (rs, rowNum) -> {
                return toProjectPerson(rs);
            });
            project.setPersons(persons);
        }

        return project;
    }

    protected void setSponsorFields(List<ProjectSponsor> sponsors, Project project) {
        sponsors.stream().findFirst().ifPresent(sponsor -> {
            project.setSponsorCode(sponsor.getSponsorCode());
            project.setSponsorName(sponsor.getSponsorName());
        });

        project.setSponsors(sponsors);
    }

    protected abstract Project toProject(ResultSet rs) throws SQLException;
    protected abstract ProjectPerson toProjectPerson(ResultSet rs) throws SQLException;
    protected abstract String allProjectQuery();
    protected abstract String allProjectPersonQuery();
    protected abstract String projectQuery();
    protected abstract String projectPersonQuery();

    public JdbcOperations getJdbcOperations() {
        return jdbcOperations;
    }

    public void setJdbcOperations(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }
}
