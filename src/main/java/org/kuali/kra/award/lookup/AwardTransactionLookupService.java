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
package org.kuali.kra.award.lookup;

import java.util.Map;

public interface AwardTransactionLookupService {

    /**
     * 
     * Gets all transaction ids that were added to the award version, excluding
     * transactions that were added to previous or later versions.
     * @param awardNumber
     * @param sequenceNumber
     * @return
     */
    public Map<Integer, String> getApplicableTransactionIds(String awardNumber, Integer sequenceNumber);
    
}
