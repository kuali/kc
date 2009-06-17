/*
 * Copyright 2006-2009 The Kuali Foundation
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
package org.kuali.kra.common.permissions;

import java.util.List;

/**
 * This class...
 */
public interface Permissionable {
    
    String PROPOSAL_KEY = "kra.proposal";
    String AWARD_KEY = "kra.award";
    
    /**
     * 
     * This method returns the appropriate document number for implementing documents
     * For award it would be awardNumber and for PDD it would be proposal Number.
     * @return
     */
    String getDocumentNumberForPermission();
    
    /**
     * 
     * This method returns unique key for implementing document.
     * 
     * @return
     */
    String getDocumentKey();
    
    /**
     * 
     * This method gets all the role names for particular document. 
     * @return
     */
    List<String> getRoleNames();
    
}
