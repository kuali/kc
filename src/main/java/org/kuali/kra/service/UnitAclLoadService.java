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

import org.kuali.kra.common.permissions.Permissionable;

/**
 * This service loads the KraAuthorizationService with the proper access control list based on 
 * the documents unit.  The document must implement the UnitAclLoadable interface in order to utilize this
 * service.
 * 
 * Administrators can maintain the access control list by assigning users to document roles within a
 * specific unit.
 */
public interface UnitAclLoadService {
    
    /**
     * Loads the access control list of a specific unit into the document's authorization service.
     * 
     * @param unitAclLoadable
     */
    // TODO: Refactor Proposal to use UnitAclLoadService instead of ProposalRoleTemplate
    //       FYI: in the ProposalRoleTemplate the following method is called "addUsers()".
    void loadUnitAcl(Permissionable permissionable);

}
