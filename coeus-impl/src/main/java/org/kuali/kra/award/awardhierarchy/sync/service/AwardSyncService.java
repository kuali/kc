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

import org.kuali.coeus.common.framework.version.VersionException;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
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
