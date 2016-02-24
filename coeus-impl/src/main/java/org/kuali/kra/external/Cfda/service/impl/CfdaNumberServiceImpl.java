/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.kra.external.Cfda.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.CFDA;
import org.kuali.kra.external.Cfda.CfdaDTO;
import org.kuali.kra.external.Cfda.service.CfdaNumberService;
import org.kuali.kra.external.HashMapElement;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.ObjectUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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


    @Override
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
        cfdaDTO.setActive(cfda.getActive());
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
