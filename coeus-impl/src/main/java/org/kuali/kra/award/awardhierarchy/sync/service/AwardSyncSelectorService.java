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
