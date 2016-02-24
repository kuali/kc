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
package org.kuali.coeus.common.framework.org;

import org.kuali.coeus.common.framework.org.crrspndnt.OrganizationCorrespondent;
import org.kuali.kra.iacuc.bo.IacucOrganizationCorrespondent;

import java.util.List;

public interface OrganizationService {

    /**
     * This method returns the organization name for a given organization id.
     * @param organizationId identifier for the organization
     * @return The name of the organization identified by this id.
     * null value is returned if organization not found
     */
    String getOrganizationName(String organizationId);

    /**
     * This method returns the organization duns for a given organization id.
     * @param organizationId identifier for the organization
     * @return The duns number identified by this id.
     * null value is returned if organization not found
     */
    String getOrganizationDuns(String organizationId);
    
    /**
     * This method returns the organization
     * Organization data is retrieved based on above organization id.
     * @param organizationId identifier for the organization
     * @return organization
     * null value is returned if organization not found
     */
    Organization getOrganization(String organizationId);
    
    /**
     * This method returns a list of OrganizationCorrespondent
     * objects based on a passed organization id.
     * @param organizationId identifier for the organization
     * @return list of OrganizationCorrespondent objects
     * null value is returned if organization not found
     */
    List<OrganizationCorrespondent> retrieveOrganizationCorrespondentsByOrganizationId(String organizationId);
    
    /**
     * This method returns a list of IacucOrganizationCorrespondent
     * objects based on a passed organization id.
     * @param organizationId identifier for the organization
     * @return list of IacucOrganizationCorrespondent objects
     * null value is returned if organization not found
     */
    List<IacucOrganizationCorrespondent> retrieveIacucOrganizationCorrespondentsByOrganizationId(String organizationId);

}
