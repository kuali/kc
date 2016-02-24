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
package org.kuali.coeus.common.framework.version.history;

import org.kuali.coeus.common.framework.version.sequence.owner.SequenceOwner;
import org.kuali.coeus.common.framework.version.VersionStatus;

import java.util.List;

public interface VersionHistoryService {

    /**
     * Update or create the version history to the appropriate status. If versionStatus is Active, then all other
     * active versions are set to archived.
     * @param sequenceOwner
     * @param versionStatus
     * @param userId
     * @return
     */
    VersionHistory updateVersionHistory(SequenceOwner<? extends SequenceOwner<?>> sequenceOwner, VersionStatus versionStatus, String userId);
    
    /**
     * Find the active VersionHistory for a given SequenceOwner type and version name
     * @param klass
     * @param versionName
     * @return
     */
    VersionHistory findActiveVersion(Class<? extends SequenceOwner> klass, String versionName);
    
    /**
     * Find the complete version history for a given SequenceOwner implementation and the version name,
     * where version name is the common "name" the versions are known by. In Award, this would be the awardNumber. 
     * In Protocol, it's the protocolNumber.
     * 
     * Note: The associated SequenceOwners are NOT eagerly fetched. Use the version name and sequence numbers from the 
     * VersionHistory to fetch the SequenceOwner associated to that VersionHistory 
     * 
     * @param klass
     * @param versionName
     * @return
     */
    List<VersionHistory> loadVersionHistory(Class<? extends SequenceOwner> klass, String versionName);
    
    /**
     * Find the pending VersionHistory for a given SequenceOwner type and version name with a specific sequence number
     * @param klass
     * @param versionName
     * @return
     */
    VersionHistory findPendingVersion(Class<? extends SequenceOwner> klass, String versionName, String sequenceNumber);
    
    /**
     * Find the pending version for a given SequenceOwner
     * @param klass
     * @param versionName
     * @return
     */
    VersionHistory findPendingVersion(Class<? extends SequenceOwner> klass, String versionName);
    
    /**
     * Find version histories without fetching the sequence owner. If you need sequence owner included in history list, use loadVersionHistory() method.
     * @param klass
     * @param versionName
     * @return
     */
    List<VersionHistory> findVersionHistory(Class<? extends SequenceOwner> klass, String versionName);

    /**
     * 
     * This method will load the appropriate sequenceOwner to the VersionHistory
     * @param versionHistory
     */
    void loadSequenceOwner(Class<? extends SequenceOwner> klass,VersionHistory versionHistory);
    
    /**
     * Finds the active(current FINAL version) or the newest version of the sequence owner if an active version does not exist.
     * @param klass
     * @param versionName
     * @return
     */
    VersionHistory getActiveOrNewestVersion(Class<? extends SequenceOwner> klass, String versionName);
}
