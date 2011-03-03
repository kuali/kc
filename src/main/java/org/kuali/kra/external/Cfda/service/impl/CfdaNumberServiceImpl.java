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
package org.kuali.kra.external.Cfda.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.external.Cfda.service.CfdaNumberService;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.ObjectUtils;

/**
 * This class is used for querying CFDA data from KC.
 */
public class CfdaNumberServiceImpl implements CfdaNumberService {

    private BusinessObjectService businessObjectService;
    private static final Log LOG = LogFactory.getLog(CfdaNumberServiceImpl.class);
    
    /**
     * This method is used to return the cfda number of an award.
     * @see org.kuali.kra.external.Cfda.service.CfdaNumberService#getCfdaNumber(java.lang.String)
     */
    public String getCfdaNumber(String financialAccountNumber) {
        
        Award award = getAward(financialAccountNumber);
        if (ObjectUtils.isNotNull(award) && ObjectUtils.isNotNull(award.getCfdaNumber())) {
            return award.getCfdaNumber();
        } else {
            return null;
        }
    }

    /**
     * This helper method returns the award based on the financial account number.
     * @param financialAccountNumber
     * @return
     */
    protected Award getAward(String financialAccountNumber) {
        List<Award> awards;
        HashMap<String, String> searchCriteria =  new HashMap<String, String>();
        searchCriteria.put("accountNumber", financialAccountNumber);  
        awards = new ArrayList<Award>(businessObjectService.findMatching(Award.class, searchCriteria));
        if (ObjectUtils.isNotNull(awards) && !awards.isEmpty()) {
            return awards.get(0);
        } else {
            LOG.warn("No award found for the corresponding account number.");
            return null;
        }   
    }
    

    /**
     * Sets the businessObjectService attribute value. Injected by Spring.
     * 
     * @param businessObjectService The businessObjectService to set.
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
}
