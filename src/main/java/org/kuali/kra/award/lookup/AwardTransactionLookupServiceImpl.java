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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardAmountInfo;
import org.kuali.rice.kns.service.BusinessObjectService;

import edu.emory.mathcs.backport.java.util.Collections;

public class AwardTransactionLookupServiceImpl implements AwardTransactionLookupService {

    private BusinessObjectService businessObjectService;
    
    /**
     * 
     * @see org.kuali.kra.award.lookup.AwardTransactionLookupService#getApplicableTransactionIds(java.lang.String, java.lang.Integer)
     */
    @SuppressWarnings("unchecked")
    public List<Long> getApplicableTransactionIds(String awardNumber, Integer sequenceNumber) {
        TreeSet<Long> transactionIds = new TreeSet<Long>();
        Map<String, String> awardValues = new HashMap<String, String>();
        awardValues.put("awardNumber", awardNumber);
        Collection<Award> awards = getBusinessObjectService().findMatchingOrderBy(Award.class, awardValues, "sequenceNumber", true);
        List<Long> excludedTransactionIds = new ArrayList<Long>();
        for (Award award : awards) {
            if (award.getSequenceNumber() < sequenceNumber.intValue()) {
                for (AwardAmountInfo amountInfo : award.getAwardAmountInfos()) {
                    if (amountInfo.getTransactionId() != null) {
                        excludedTransactionIds.add(amountInfo.getTransactionId());
                    }
                }
            } else if (award.getSequenceNumber() == sequenceNumber.intValue()){
                for (AwardAmountInfo amountInfo : award.getAwardAmountInfos()) {
                    if (amountInfo.getTransactionId() != null) {
                        transactionIds.add(amountInfo.getTransactionId());
                    }
                }
            }
        }
        transactionIds.removeAll(excludedTransactionIds);
        List<Long> retVal = new ArrayList<Long>(transactionIds);
        Collections.reverse(retVal);
        return retVal;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    private BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

}
