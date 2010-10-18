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
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

import org.apache.commons.lang.ObjectUtils;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardAmountInfo;
import org.kuali.rice.core.util.KeyLabelPair;
import org.kuali.rice.kns.service.BusinessObjectService;

import java.util.Collections;

public class AwardTransactionLookupServiceImpl implements AwardTransactionLookupService {

    private BusinessObjectService businessObjectService;
    
    /**
     * 
     * @see org.kuali.kra.award.lookup.AwardTransactionLookupService#getApplicableTransactionIds(java.lang.String, java.lang.Integer)
     */
    @SuppressWarnings("unchecked")
    public Map<Integer, String> getApplicableTransactionIds(String awardNumber, Integer sequenceNumber) {
        List<Long> transactionIds = new ArrayList<Long>();
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
        Award currentAward = getAwardVersion(awardNumber, sequenceNumber);
        transactionIds.removeAll(excludedTransactionIds);
        Map<Integer, String> retval = new TreeMap<Integer, String>(new Comparator<Integer>(){
            public int compare(Integer o1, Integer o2) {
                //sort in descending order instead of ascending
                return o1.compareTo(o2) * -1;
            }
        });
        for (Long id : transactionIds) {
            if (id != null) {
                retval.put(getAwardAmountInfoIndex(currentAward, id), id.toString());
            }
        }
        if (sequenceNumber == 1) {
            retval.put(0, "Initial");
        }
        return retval;
    }
    
    protected int getAwardAmountInfoIndex(Award award, Long transactionId) {
        for (int i = 0; i < award.getAwardAmountInfos().size(); i++) {
            if (ObjectUtils.equals(award.getAwardAmountInfos().get(i).getTransactionId(), transactionId)) {
                return i;
            }
        }
        return 0;
    }
    
    protected Award getAwardVersion(String awardNumber, int sequenceNumber) {
        Map<String, Object> values = new HashMap<String, Object>();
        values.put("awardNumber", awardNumber);
        values.put("sequenceNumber", sequenceNumber);
        Collection<Award> awards = businessObjectService.findMatching(Award.class, values);
        return awards.iterator().next();
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    protected BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

}
