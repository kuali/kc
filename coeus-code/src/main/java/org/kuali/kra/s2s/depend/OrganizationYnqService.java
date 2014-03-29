package org.kuali.kra.s2s.depend;


import org.kuali.coeus.common.framework.org.OrganizationYnq;

import java.util.List;

public interface OrganizationYnqService {

    /**
     * Finds the {@link OrganizationYnq}s by organizationId.  Will return an empty
     * List if no items exist.
     * @param organizationId the organization Id.  Cannot be blank.
     * @return a list of {@link OrganizationYnq}s or an empty list.
     * @throws IllegalArgumentException if the organizationId is blank
     */
    List<OrganizationYnq> findOrganizationYnqByOrgId(String organizationId);
}
