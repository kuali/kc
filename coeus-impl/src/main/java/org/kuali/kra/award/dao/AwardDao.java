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
package org.kuali.kra.award.dao;

import org.kuali.kra.award.home.Award;
import org.kuali.rice.krad.bo.BusinessObject;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface AwardDao {
    public String getAwardNumber(Long awardId);

    /**
     * Does a non-wildcarded yet still limited search of awards, retrieved by the given criteria
     * @param fieldValues the field values to set
     * @return a Collection of found awards
     */
    public Collection<Award> retrieveAwardsByCriteria(Map<String, Object> fieldValues);

}
