/*
 * Copyright 2006-2008 The Kuali Foundation
 * 
 * Licensed under the Educational Commawardy License, Version 2.0 (the "License");
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

import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.service.ServiceHelper;
import org.kuali.rice.kns.service.BusinessObjectService;

/** {@inheritDoc} */
public class AwardServiceImpl implements AwardService {
    
    private static final String AWARD_NUMBER = "awardNumber";
    private static final String AWARD_ID = "awardId";
    private static final String SEQUENCE_NUMBER = "sequenceNumber";
    
    private BusinessObjectService businessObjectService;
    
    /**
     * Note Awards are ordered by sequenceNumber
     * @see org.kuali.kra.award.home.AwardService#findAwardsForAwardNumber(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public List<Award> findAwardsForAwardNumber(String awardNumber) {
        List<Award> results = new ArrayList<Award>(businessObjectService.findMatchingOrderBy(Award.class, 
                                                                ServiceHelper.getInstance().buildCriteriaMap(AWARD_NUMBER, awardNumber),
                                                                SEQUENCE_NUMBER,
                                                                true));
        return results;
    }    
    
    /** {@inheritDoc} */
    public Award getAward(Long awardId) {
        return awardId != null ? (Award) businessObjectService.findByPrimaryKey(Award.class, 
                                        ServiceHelper.getInstance().buildCriteriaMap(AWARD_ID, awardId)) : null;
    }
    
    /** {@inheritDoc} */
    public Award getAward(String awardId) {
        return awardId != null ? (Award) businessObjectService.findByPrimaryKey(Award.class, 
                                        ServiceHelper.getInstance().buildCriteriaMap(AWARD_ID, awardId)) : null;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
}
