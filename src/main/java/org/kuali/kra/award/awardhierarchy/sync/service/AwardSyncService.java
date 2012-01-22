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
package org.kuali.kra.award.awardhierarchy.sync.service;

import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.service.VersionException;
import org.kuali.rice.kew.api.exception.WorkflowException;


public interface AwardSyncService {    
    
    /**
     * Run all validation checks against the award in the hierarchy and the sync changes attached to the specified award
     * @param award
     * @return
     * @throws WorkflowException 
     */
    void validateHierarchyChanges(Award award);
    
    /**
     * Apply the sync changes queued on the specified award against the award hierarchy  
     * @param award
     * @param logs 
     * @return
     * @throws VersionException 
     * @throws WorkflowException 
     */
    void applyAwardSyncChangesToHierarchy(Award award); 
    
    /**
     * Checks to see if the award hierarchy is locked for the given principal.
     * Hierarchy is locked if there is an award higher in the hierarchy enroute with
     * award hierarchy descendant sync and the principal is not who routed the parent document.
     * @param awardDocument
     * @param principalId
     * @return the award document responsible for the lock
     */
    AwardDocument getAwardLockingHierarchyForSync(AwardDocument awardDocument, String principalId);
}
