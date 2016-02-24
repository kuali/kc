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
