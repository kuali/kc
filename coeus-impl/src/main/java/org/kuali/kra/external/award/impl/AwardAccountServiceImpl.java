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
package org.kuali.kra.external.award.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.common.framework.version.VersionStatus;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.external.award.AwardAccountDTO;
import org.kuali.kra.external.award.AwardAccountService;
import org.kuali.kra.external.service.KcDtoService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.ObjectUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This class implements the award account service.
 */
public class AwardAccountServiceImpl implements AwardAccountService {


    private BusinessObjectService businessObjectService;
    private static final Log LOG = LogFactory.getLog(AwardAccountServiceImpl.class);
    private KcDtoService<AwardAccountDTO, Award> awardAccountDtoService;

    /**
     * This method returns all the awards linked to a financial account number and the chart
     * @see org.kuali.kra.external.award.AwardAccountService#getAwardAccount(java.lang.String)
     */
    public List<AwardAccountDTO> getAwardAccounts(String financialAccountNumber, String chartOfAccounts) {
        if (ObjectUtils.isNull(financialAccountNumber) || ObjectUtils.isNull(chartOfAccounts)) {
            LOG.warn("One or both of the criteria sent was null.");
            return null;
        }
        List<Award> awards = getAwards(financialAccountNumber, chartOfAccounts);
        return getAwardAccountDTOs(awards); 
    }
    
    protected List<AwardAccountDTO> getAwardAccountDTOs(List<Award> awards) {
        List<AwardAccountDTO> awardDTOs = new ArrayList<AwardAccountDTO>();

        if (ObjectUtils.isNotNull(awards)) {
            for (Award award : awards) {
                awardDTOs.add(awardAccountDtoService.buildDto(award));
            }
            
        } 
        return awardDTOs;
    }


    /**
     * This method returns awards based on the account number and chart of account
     * @param financialAccountNumber
     * @param chartOfAccounts
     * @return
     */
    protected List<Award> getAwards(String financialAccountNumber, String chartOfAccounts) {
        List<Award> awards = new ArrayList<Award>();
        HashMap<String, String> searchCriteria =  new HashMap<String, String>();
        // It is possible to have a null chart of accounts code
        if (ObjectUtils.isNotNull(financialAccountNumber)) {
            searchCriteria.put("accountNumber", financialAccountNumber);  
            searchCriteria.put("financialChartOfAccountsCode", chartOfAccounts);
            // use the awardSequenceStatus to return the latest active award
            searchCriteria.put("awardSequenceStatus", VersionStatus.ACTIVE.name());
            awards = new ArrayList<Award>(businessObjectService.findMatching(Award.class, searchCriteria));
        }
        if (ObjectUtils.isNull(awards) || awards.isEmpty()) {           
            LOG.warn("No award found for the account number " + financialAccountNumber + " and chart " + "chartOfAccounts");            
        }  
        return awards;
    }

    
    /**
     * Sets the businessObjectService attribute value. Injected by Spring.
     * 
     * @param businessObjectService The businessObjectService to set.
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    protected KcDtoService<AwardAccountDTO, Award> getAwardAccountDtoService() {
        return awardAccountDtoService;
    }

    public void setAwardAccountDtoService(KcDtoService<AwardAccountDTO, Award> awardAccountDtoService) {
        this.awardAccountDtoService = awardAccountDtoService;
    }

    protected BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

}
