/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.common.framework.auth.perm;

import java.util.List;
import java.util.Map;

public interface Permissionable {
    
    /**
     * 
     * This method returns the appropriate document number for implementing documents
     * For award it would be awardNumber and for PDD it would be proposal Number.
     */
    String getDocumentNumberForPermission();
    
    /**
     * 
     * This method returns unique key for implementing document.
     */
    String getDocumentKey();
    
    /**
     * 
     * This method gets all the role names for particular document.
     */
    List<String> getRoleNames();
    
    String getNamespace();
    
    String getLeadUnitNumber();
    
    String getDocumentRoleTypeCode();
    
    /**
     * Allows a permissionable to set additional qualified role attributes that may be needed by 
     * kim services to resolve the role members.
     */
    void populateAdditionalQualifiedRoleAttributes( Map<String, String> qualifiedRoleAttributes );
    
    
    
}
