package org.kuali.coeus.coi.framework;

/**
 * Publishes a COI project.
 */
public interface ProjectPublisher {

    /**
     * Publishes a project to the COI system.
     *
     * @param project a project.  Cannot be null.  Must be in a valid state.
     * @throws IllegalArgumentException if the project is null or invalid.
     */
    void publishProject(Project project);
}
