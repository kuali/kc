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
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.ObjectUtils;

/**
 * This class implements the award account service.
 */
public class AwardAccountServiceImpl implements AwardAccountService {


    private BusinessObjectService businessObjectService;
    private static final Log LOG = LogFactory.getLog(AwardAccountServiceImpl.class);
    private ParameterService parameterService;
   
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
                AwardAccountDTO awardAccountDTO = new AwardAccountDTO();
                awardAccountDTO.setProposalFederalPassThroughAgencyNumber(award.getSponsorCode());
                //sponsor award id same as sponsor award number
                awardAccountDTO.setGrantNumber(award.getSponsorAwardNumber());
                // how to get IP id from award
                awardAccountDTO.setInstitutionalproposalId(getProposalId(award));
                awardAccountDTO.setAwardId(award.getAwardId());
                if (ObjectUtils.isNotNull(award.getPrincipalInvestigator())) {
                    awardAccountDTO.setProjectDirector(award.getPrincipalInvestigator().getPersonId());
                } else {
                    awardAccountDTO.setProjectDirector(null);
                }
                
                // send the award number which is the proposal number on the KFS side
                awardAccountDTO.setProposalNumber(award.getAwardNumber());
                awardAccountDTO.setSponsorCode(award.getSponsorCode());
                awardAccountDTO.setSponsorName(award.getSponsorName());
                awardAccountDTO.setFederalSponsor(isFederalSponsor(award));
                awardAccountDTO.setAwardTitle(award.getTitle());
                awardAccountDTO.setPrimeSponsorCode(award.getPrimeSponsorCode());
                
                if (ObjectUtils.isNotNull(award.getPrimeSponsor())) {
                    awardAccountDTO.setPrimeSponsorName(award.getPrimeSponsor().getSponsorName());
                    awardAccountDTO.setPrimeSponsorTypeCode(award.getPrimeSponsor().getSponsorTypeCode());
                } else {
                    awardAccountDTO.setPrimeSponsorTypeCode(null);
                    awardAccountDTO.setPrimeSponsorName(null);
                }
                
                if(ObjectUtils.isNotNull(award.getSponsor())) {
                    awardAccountDTO.setSponsorTypeCode(award.getSponsor().getSponsorTypeCode());
                } else {
                    awardAccountDTO.setSponsorTypeCode(null);
                }
                awardDTOs.add(awardAccountDTO);
            }
            
        } 
        return awardDTOs;
    }

 
    /**
     * This method returns the proposal ID related to an award
     * Can award have multiple P IDs?
     * @param award
     * @return
     */
    protected Long getProposalId(Award award) {
        String proposalNumber = award.getProposalNumber();
        List<InstitutionalProposal> proposals;
        HashMap<String, String> searchCriteria =  new HashMap<String, String>();
        searchCriteria.put("proposalNumber", proposalNumber);  
        proposals = new ArrayList<InstitutionalProposal>(businessObjectService.findMatching(InstitutionalProposal.class, searchCriteria));
        if (ObjectUtils.isNotNull(proposals)) {
            return proposals.isEmpty() ? null : proposals.get(0).getProposalId();
        }
        return null;
    }
    
    /**
     * * Method checks if the award has a federal sponsor.
     * If the award sponsor type code or the prime sponsor type is federal, then
     * the document should be routed.
     * @see org.kuali.kra.external.award.AwardAccountService#isFederalSponsor(java.lang.String)
     */
    protected boolean isFederalSponsor(Award award) {
       
        String federalSponsorTypeCode = parameterService.getParameterValueAsString(AwardDocument.class, Constants.FEDERAL_SPONSOR_TYPE_CODE);
        //If the sponsor type or prime sponsor type is federal, then document should be routed, return true.
        return isSponsorTypeFederal(award, federalSponsorTypeCode) || isPrimeSponsorFederal(award, federalSponsorTypeCode);
    }

    /**
     * This method checks if prime sponsor is federal
     * @param award
     * @param federalSponsorTypeCode
     * @return
     */
    protected boolean isPrimeSponsorFederal(Award award, String federalSponsorTypeCode) {
        if (ObjectUtils.isNotNull(award.getPrimeSponsor()) && ObjectUtils.isNotNull(award.getPrimeSponsor().getSponsorType()))  {
            if (award.getPrimeSponsor().getSponsorType().getSponsorTypeCode().equals(federalSponsorTypeCode)) {
                return true;
            }
        }
        return false;
    }
    
    
    /**
     * This method checks if sponsor is federal.
     * @param award
     * @param federalSponsorTypeCode
     * @return
     */
    protected boolean isSponsorTypeFederal(Award award, String federalSponsorTypeCode) {
        if (ObjectUtils.isNotNull(award.getSponsor()) && ObjectUtils.isNotNull(award.getSponsor().getSponsorTypeCode())) {
            if (award.getSponsor().getSponsorTypeCode().equals(federalSponsorTypeCode)) {
                return true;
            }
        }
        return false;
    }
   
    /**
     * This method returns awards based on the account number and chart of account
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

    /**
     * Sets the parameterService attribute value. Injected by Spring.
     * 
     * @param parameterService The parameterService to set.
     */
    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

}
