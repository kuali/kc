package org.kuali.coeus.common.api.org.audit;

public interface OrganizationAuditContract {

    String getFiscalYear();

    String getOrganizationId();

    boolean getAuditAccepted();

    String getAuditComment();
}
