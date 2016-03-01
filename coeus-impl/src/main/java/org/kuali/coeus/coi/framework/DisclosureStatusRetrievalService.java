package org.kuali.coeus.coi.framework;

public interface DisclosureStatusRetrievalService {

    public DisclosureProjectStatus getDisclosureStatusForPerson(String sourceId, String projectId, String personId);

}
