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
package org.kuali.kra.award;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ojb.broker.query.Criteria;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.dao.KraLookupDao;
import org.kuali.kra.dao.ojb.KraLookupDaoOjb;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.SequenceAccessorService;

public class AwardNumberServiceImpl implements AwardNumberService {

    private SequenceAccessorService sequenceAccessorService;
    private KraLookupDao kraLookupDao;

    private String DASH = "-";
    private String PERCENT = "%";
    private String AWARD_NUMBER = "awardNumber";
    
    public AwardNumberServiceImpl() {
    }
    /**
     * Set the Sequence Accessor Service.
     * @param sequenceAccessorService the Sequence Accessor Service
     */
    public void setSequenceAccessorService(SequenceAccessorService sequenceAccessorService) {
        this.sequenceAccessorService = sequenceAccessorService;
    }
    
    /**
     * Sets the kraLookupDao attribute value.
     * @param kraLookupDao The kraLookupDao to set.
     */
    public void setKraLookupDao(KraLookupDao kraLookupDao) {
        this.kraLookupDao = kraLookupDao;
    }

    
    /** {@inheritDoc} */
    public String getNextAwardNumber() {
        Long nextAwardNumber = sequenceAccessorService.getNextAvailableSequenceNumber(Constants.AWARD_SEQUENCE_AWARD_NUMBER);
        
        // Use Coeus' xxxxxx-yyy format for compatibility
        DecimalFormat formatter = new DecimalFormat("000000");        
        return formatter.format(nextAwardNumber) + "-00001";
    }
    
    /**
     * This method searches generates the next Award Node Number in Sequence.
     * @param awardNumber
     * @return
     */
    public String getNextAwardNumberInHierarchy(String awardNumber) {
        String[] splitAwardNumber = awardNumber.split(DASH);
        String awardNumberLike = splitAwardNumber[0] + PERCENT;
        StringBuilder returnString = new StringBuilder(12);
        returnString.append(splitAwardNumber[0]);
        returnString.append(DASH);
        returnString.append(lookupLikeAwardNumbers(awardNumberLike));  
        return returnString.toString();
    }
    
    /**
     * This method gets the Award BO's that match the given awardNumber.
     * @param lookupLike
     * @return
     */
    @SuppressWarnings("unchecked")
    private String lookupLikeAwardNumbers(String lookupLike) {
        DecimalFormat formatter = new DecimalFormat("00000");
        List<Award> awardList = 
            (List<Award>) kraLookupDao.findCollectionUsingWildCard(Award.class, "awardNumber", lookupLike, Boolean.TRUE);
        return formatter.format(getHighestSequenceNode(awardList)); 
    }
    
    /**
     * This method returns the highest node value from the awardList of BO's.
     * @param awardList
     * @return
     */
    private Long getHighestSequenceNode(List<Award> awardList) {
        Long returnValue = null;
        for (Award loopAward : awardList) {
            String[] splitAwardNumber = loopAward.getAwardNumber().split("-");
            Long testValue = Long.parseLong(splitAwardNumber[1]);
            if (returnValue == null || testValue > returnValue) {
                returnValue = testValue;
            }
        }
        return returnValue + 1;
    }

}