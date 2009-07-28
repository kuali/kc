/*
 * Copyright 2006-2008 The Kuali Foundation
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

import java.util.List;

import org.kuali.kra.SequenceOwner;
import org.kuali.kra.bo.versioning.VersionHistory;

public interface VersionHistoryService {
    
    /**
     * 
     * Create a new VersiionHistory entry
     * @param sequenceOwner
     * @param userId
     * @return
     */
    VersionHistory createVersionHistory(SequenceOwner<? extends SequenceOwner<?>> sequenceOwner, String userId);
    
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
}
