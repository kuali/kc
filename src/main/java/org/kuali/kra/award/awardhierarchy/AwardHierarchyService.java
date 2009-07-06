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
package org.kuali.kra.award.awardhierarchy;

import java.util.Map;

public interface AwardHierarchyService {

    void persistAwardHierarchy(AwardHierarchy awardHierarchy);
    
    Map<String, AwardHierarchy> getAwardHierarchy(String awardNumber);
    
    void createBasicHierarchy(String awardNumber);
    
    void createNewChildAward(String newAwardNumber, String parentAwardNumber, String rootAwardNumber);
    
    void createNewAwardBasedOnParent(String awardNumber);
    
    void createNewAwardBasedOnAnotherAwardInHierarchy(String awardNumber);
    
    void copyAwardAsNewHierarchy();
    
    void copyAwardAndAllDescendantsAsNewHierarchy();
    
    void copyAwardAsChildOfAnAwardInCurrentHierarchy();
    
    void copyAwardAndDescendantsAsChildOfAnAwardInCurrentHierarchy();
    
    void copyAwardAsChildOfAnAwardInAnotherHierarchy();
    
    void copyAwardAndDescendantsAsChildOfAnAwardInAnotherHierarchy();
    
}
