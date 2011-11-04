/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.service;

import java.util.List;

import org.kuali.kra.bo.Organization;
import org.kuali.kra.bo.OrganizationCorrespondent;
import org.kuali.kra.bo.UnitAdministrator;

public interface OrganizationService {

    /**
     * This method returns the organization name for a given organization id.
     * @param organizationId identifier for the organization
     * @return The name of the organization identified by this id. 
     * null value is returned if organization not found
     */
    public String getOrganizationName(String organizationId);
    
    /**
     * This method returns the organization
     * Organization data is retrieved based on above organization id.
     * @param organizationId identifier for the organization
     * @return organization
     * null value is returned if organization not found
     */
    public Organization getOrganization(String organizationId);
    
    /**
     * This method returns a list of OrganizationCorrespondent
     * objects based on a passed organization id.
     * @param organizationId identifier for the organization
     * @return list of OrganizationCorrespondent objects
     * null value is returned if organization not found
     */
    public List<OrganizationCorrespondent> retrieveOrganizationCorrespondentsByOrganizationId(String organizationId);

}
