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
package org.kuali.kra.external.award.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.external.award.AwardAccountDTO;
import org.kuali.kra.external.award.AwardAccountService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.ParameterService;
import org.kuali.rice.kns.util.ObjectUtils;

/**
 * This class implements the award account service.
 */
public class AwardAccountServiceImpl implements AwardAccountService {


    private BusinessObjectService businessObjectService;
    private static final Log LOG = LogFactory.getLog(AwardAccountServiceImpl.class);
    private ParameterService parameterService;

    /**
     * @see org.kuali.kra.external.award.AwardAccountService#getAwardAccount(java.lang.String)
     */
    public AwardAccountDTO getAwardAccount(String financialAccountNumber) {
        
        Award award = getAward(financialAccountNumber); 
        AwardAccountDTO awardAccountDTO = new AwardAccountDTO();
        if (ObjectUtils.isNotNull(award)) {
            awardAccountDTO.setProposalFederalPassThroughAgencyNumber(award.getSponsorCode());
            //sponsor award id same as sponsor award number
            awardAccountDTO.setGrantNumber(award.getSponsorAwardNumber());
            // how to get IP id from award
            awardAccountDTO.setInstitutionalproposalId(getProposalId(award));
            awardAccountDTO.setAwardId(award.getAwardId());
            awardAccountDTO.setProjectDirector(award.getPrincipalInvestigator().getPersonId());
            // send the award number which is the proposal number on the KFS side
            awardAccountDTO.setProposalNumber(award.getAwardNumber());
            awardAccountDTO.setSponsorCode(award.getSponsorCode());
            awardAccountDTO.setSponsorName(award.getSponsorName());
            awardAccountDTO.setFederalSponsor(isFederalSponsor(award));
            awardAccountDTO.setAwardTitle(award.getTitle());
            awardAccountDTO.setPrimeSponsorCode(award.getPrimeSponsorCode());
            // where is the prime sponsor agency number?
            if(ObjectUtils.isNotNull(award.getPrimeSponsor())) {
                awardAccountDTO.setPrimeSponsorName(award.getPrimeSponsor().getSponsorName());
            }
            else {
                awardAccountDTO.setPrimeSponsorName("");
            }
            
        } else {
            awardAccountDTO.setErrorMessage("There is no award with the financial account number " + financialAccountNumber);
        }
        return awardAccountDTO;
    }

    /**
     * This method returns the proposal ID related to an award
     * Can award have multiple P IDs?
     * @param award
     * @return
     */
    public Long getProposalId(Award award) {
        String proposalNumber = award.getProposalNumber();
        List<InstitutionalProposal> proposals;
        HashMap<String, String> searchCriteria =  new HashMap<String, String>();
        searchCriteria.put("proposalNumber", proposalNumber);  
        proposals = new ArrayList<InstitutionalProposal>(businessObjectService.findMatching(InstitutionalProposal.class, searchCriteria));
        return proposals.isEmpty() ? null : proposals.get(0).getProposalId();
    }
    
    /**
     * * Method checks if the award has a federal sponsor.
     * If the award sponsor type code or the prime sponsor type is federal, then
     * the document should be routed.
     * @see org.kuali.kra.external.award.AwardAccountService#isFederalSponsor(java.lang.String)
     */
    protected boolean isFederalSponsor(Award award) {
       
        String federalSponsorTypeCode = parameterService.getParameterValue(AwardDocument.class, Constants.FEDERAL_SPONSOR_TYPE_CODE);
        String awardSponsorType = award.getSponsor().getSponsorTypeCode();
            
        //If the sponsor type or prime sponsor type is federal, then document should be routed, return true.
        if ((ObjectUtils.isNotNull(awardSponsorType) && awardSponsorType.equals(federalSponsorTypeCode)) 
            || (ObjectUtils.isNotNull(award.getPrimeSponsor()) 
            && award.getPrimeSponsor().getSponsorType().getSponsorTypeCode().equals(federalSponsorTypeCode))) {
            return true;
        }         
        return false;
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

    /**
     * Sets the parameterService attribute value. Injected by Spring.
     * 
     * @param parameterService The parameterService to set.
     */
    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }
 
}
