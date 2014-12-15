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
package org.kuali.kra.award.external.award.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.common.framework.version.VersionStatus;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.external.award.AwardAccountDTO;
import org.kuali.kra.award.external.award.AwardAccountService;
import org.kuali.kra.external.service.KcDtoService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
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
     * @see org.kuali.kra.award.external.award.AwardAccountService#getAwardAccount(java.lang.String)
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
