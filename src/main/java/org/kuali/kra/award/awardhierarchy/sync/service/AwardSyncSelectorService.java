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
package org.kuali.kra.award.awardhierarchy.sync.service;

import org.kuali.kra.award.awardhierarchy.sync.AwardSyncChange;
import org.kuali.kra.award.home.Award;

import java.util.List;

public interface AwardSyncSelectorService {

    /**
     * 
     * Returns true is at least one change is applicable to this award.
     * @param award
     * @param changes
     * @return
     */
    boolean isAwardInvolvedInSync(Award award, List<AwardSyncChange> changes);
    
    /**
     * 
     * Using the award sync selector service decide if the change is applicable to the award
     * matching active status and fabricated and cost sharing account selectors.
     * @param award
     * @param change
     * @return
     */
    boolean isChangeApplicableToAward(Award award, AwardSyncChange change);
    
    
    /**
     * 
     * Returns true is an award status matches a value found in the
     * Active Award Status Codes parameter
     * @param award
     * @return
     */
    boolean isAwardActive(Award award);
    
    /**
     * 
     * Returns true if the award is a fabricated account award
     * @param award
     * @return
     */
    boolean isFabricatedAccount(Award award);
    
    /**
     * 
     * Returns true if the award is a cost sharing account
     * @param award
     * @return
     */
    boolean isCostShareAccount(Award award);
}
