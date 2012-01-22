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
import org.kuali.kra.award.home.CFDA;
import org.kuali.kra.external.HashMapElement;
import org.kuali.kra.external.Cfda.CfdaDTO;
import org.kuali.kra.external.Cfda.service.CfdaNumberService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.ObjectUtils;

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
    public List<CfdaDTO> getCfdaNumber(String financialAccountNumber, String financialChartOfAccounts) {
        
        List<Award> awards = getAwards(financialAccountNumber, financialChartOfAccounts);
        List<CfdaDTO> cfdaNumbers = new ArrayList<CfdaDTO>();
        if (ObjectUtils.isNotNull(awards)) {           
            for (Award award : awards) {
                HashMap<String, String> searchCriteria =  new HashMap<String, String>();
                searchCriteria.put("cfdaNumber", award.getCfdaNumber());
                if (ObjectUtils.isNotNull(award.getCfdaNumber())) {
                    CFDA cfda = (CFDA) businessObjectService.findByPrimaryKey(CFDA.class, searchCriteria);
                    if (ObjectUtils.isNotNull(cfda)) {
                        CfdaDTO cfdaDTO = boToDTO(cfda);
                        cfdaDTO.setAwardId(award.getAwardId() + "");
                        cfdaNumbers.add(cfdaDTO);
                    }
                }
            }
            return cfdaNumbers;
        } else {
            return null;
        }
    }


    /**
     * @see org.kuali.kra.external.Cfda.service.CfdaNumberService#lookupCfda(java.util.List)
     */
    public List<CfdaDTO> lookupCfda(List<HashMapElement> criteria) {
        HashMap<String, String> searchCriteria =  new HashMap<String, String>();
        List<CFDA> cfdaNumbers = new ArrayList<CFDA>();
        // if the criteria passed is null, then return all units.
        if (ObjectUtils.isNull(criteria)) {
            cfdaNumbers =  new ArrayList<CFDA>(businessObjectService.findAll(CFDA.class));
        } else {
            // Reconstruct Hashmap from object list
            for (HashMapElement element : criteria) {
                searchCriteria.put(element.getKey(), element.getValue());  
            }
            cfdaNumbers =  new ArrayList<CFDA>(businessObjectService.findMatching(CFDA.class, searchCriteria));
        }
        
        List<CfdaDTO> cfdaDTOs = new ArrayList<CfdaDTO>();
        for (CFDA cfda : cfdaNumbers) {
            cfdaDTOs.add(boToDTO(cfda));
        }
        
        return cfdaDTOs;
    }
    
    /**
     * This method converts the BO to a DTO.
     * @param cfda
     * @param award
     * @return
     */
    protected CfdaDTO boToDTO(CFDA cfda) {
        CfdaDTO cfdaDTO = new CfdaDTO();
        cfdaDTO.setCfdaMaintenanceTypeId(cfda.getCfdaMaintenanceTypeId());
        cfdaDTO.setCfdaNumber(cfda.getCfdaNumber());
        cfdaDTO.setCfdaProgramTitleName(cfda.getCfdaProgramTitleName());
        return cfdaDTO;
    }
    
    /**
     * This method returns awards based on the account number and chart of account.
     * @param financialAccountNumber
     * @param chartOfAccounts
     * @return
     */
    protected List<Award> getAwards(String financialAccountNumber, String chartOfAccounts) {
        List<Award> awards;
        HashMap<String, String> searchCriteria =  new HashMap<String, String>();
        searchCriteria.put("accountNumber", financialAccountNumber);  
        searchCriteria.put("financialChartOfAccountsCode", chartOfAccounts);
        awards = new ArrayList<Award>(businessObjectService.findMatching(Award.class, searchCriteria));
        if (ObjectUtils.isNotNull(awards) && !awards.isEmpty()) {
            return awards;
        } else {
            LOG.warn("No award found for the account number " + financialAccountNumber + " and chart " + "chartOfAccounts");            
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
