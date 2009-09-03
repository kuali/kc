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
package org.kuali.kra.award.home;

import java.util.List;

/**
 * 
 * This class intends to provide basic business service behavior 
 * and accessors for Awards. 

 */
public interface AwardService {
    /**
     * Get the Award based upon its unique id number.
     * 
     * @param awardId the Award's unique id number
     * @return the Award or null if not found.
     * 
     * @deprecated The identifier for Award is a Long, but this method expects a String
     */
    public Award getAward(String awardId);

    /**
     * Get the Award based upon its unique id number.
     * 
     * @param awardId
     * @return
     */
    public Award getAward(Long awardId);
    
    /**
     * This method finds all Awards for the specified awardNumber
     * @param awardId
     * @return The list of Awards
     */
    public List<Award> findAwardsForAwardNumber(String awardNumber);
    
}
