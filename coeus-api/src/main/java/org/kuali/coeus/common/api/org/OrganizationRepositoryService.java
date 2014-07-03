package org.kuali.coeus.common.api.org;

public interface OrganizationRepositoryService {

    /**
     * Gets Cognizant Federal Agency for an organization.  The pass in organization cannot be null.
     * Will return an empty string the Cognizant Federal Agency cannot be determined.
     *
     * @param organization the organization.
     * @return the Cognizant Federal Agency or a blank string
     * @throws java.lang.IllegalArgumentException if the organization is null
     */
    String getCognizantFedAgency(OrganizationContract organization);
}
