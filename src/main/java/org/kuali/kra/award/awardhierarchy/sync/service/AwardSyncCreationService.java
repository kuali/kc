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

import org.kuali.kra.award.awardhierarchy.sync.AwardSyncChange;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncPendingChangeBean;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncXmlExport;
import org.kuali.kra.award.home.Award;

public interface AwardSyncCreationService {
    
    /**
     * Generate a AwardSyncChange BO from the sync type, the attr name and the syncable bo.
     * @param syncType
     * @param syncableObject
     * @param attrName
     * @return
     * @throws Exception 
     */
    AwardSyncChange createAwardSyncChange(AwardSyncPendingChangeBean pendingChange) throws Exception;

    /**
     * 
     * Create and add a new award hierarchy sync object to the award, checking for and replacing any duplicates found
     * @param award
     * @param syncType
     * @param syncableObject
     * @param attrName
     * @throws Exception 
     */
    void addAwardSyncChange(Award award, AwardSyncPendingChangeBean pendingChange) throws Exception;
    
    /**
     * 
     * Add a new award hierarchy sync object to the award, 
     * checking for and replacing any duplicates found.
     * @param award
     * @param syncChange
     */
    void addAwardSyncChange(Award award, AwardSyncChange syncChange); 
    
    /**
     * 
     * Returns the {@link AwardSyncXmlExport} from reading the xml included in change.
     * @param change
     * @return
     */
    AwardSyncXmlExport getXmlExport(AwardSyncChange change);   

}
