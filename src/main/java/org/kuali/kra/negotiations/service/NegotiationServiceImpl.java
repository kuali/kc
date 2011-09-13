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
package org.kuali.kra.negotiations.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.proposallog.ProposalLog;
import org.kuali.kra.negotiations.bo.Negotiation;
import org.kuali.kra.negotiations.bo.NegotiationAssociatedDetailBean;
import org.kuali.kra.negotiations.bo.NegotiationAssociationType;
import org.kuali.kra.negotiations.document.NegotiationDocument;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.ParameterService;

/**
 * Service impl for NegotiationService.
 */
public class NegotiationServiceImpl implements NegotiationService {
    
    private static final String PARAMETER_DELIMITER = ",";
    
    private ParameterService parameterService;
    
    private BusinessObjectService businessObjectService;
    
    /**
     * Return the negotiationInProgressStatusCodes as a list of strings.
     * @see org.kuali.kra.negotiations.service.NegotiationService#getInProgressStatusCodes()
     */
    public List<String> getInProgressStatusCodes() {
        String value = getParameterService().getParameterValue(NegotiationDocument.class, "negotiationInProgressStatusCodes");
        return Arrays.asList(value.split(PARAMETER_DELIMITER));
    }
    
    /**
     * Return the negotiationCompletedStatusCodes as a list of strings.
     * @see org.kuali.kra.negotiations.service.NegotiationService#getCompletedStatusCodes()
     */
    public List<String> getCompletedStatusCodes() {
        String value = getParameterService().getParameterValue(NegotiationDocument.class, "negotiationCompletedStatusCodes");
        return Arrays.asList(value.split(PARAMETER_DELIMITER));        
    }
    
    /**
     * 
     * @see org.kuali.kra.negotiations.service.NegotiationService#buildNegotiationAssociatedDetailBean(org.kuali.kra.negotiations.bo.Negotiation)
     */
    public NegotiationAssociatedDetailBean buildNegotiationAssociatedDetailBean(Negotiation negotiation) {        
        if (negotiation.getNegotiationAssociationType() != null) {
            Map<String, String> primaryKeys = new HashMap<String, String>();
            NegotiationAssociatedDetailBean bean;
            if (StringUtils.equals(negotiation.getNegotiationAssociationType().getCode(), NegotiationAssociationType.AWARD_ASSOCIATION)) {
                primaryKeys.put("AWARD_ID", negotiation.getAssociatedDocumentId());
                bean = new NegotiationAssociatedDetailBean((Award) this.getBusinessObjectService().findByPrimaryKey(Award.class, primaryKeys));
            } else if (StringUtils.equals(negotiation.getNegotiationAssociationType().getCode(), 
                    NegotiationAssociationType.INSTITUATIONAL_PROPOSAL_ASSOCIATION)) {
                primaryKeys = new HashMap<String, String>();
                primaryKeys.put("PROPOSAL_ID", negotiation.getAssociatedDocumentId());
                bean = new NegotiationAssociatedDetailBean((InstitutionalProposal) this.getBusinessObjectService().findByPrimaryKey(
                        InstitutionalProposal.class, primaryKeys));
               
            } else if (StringUtils.equals(negotiation.getNegotiationAssociationType().getCode(), 
                    NegotiationAssociationType.NONE_ASSOCIATION)) {
                bean = new NegotiationAssociatedDetailBean(negotiation.getUnAssociatedDetail());
            } else if (StringUtils.equals(negotiation.getNegotiationAssociationType().getCode(), 
                    NegotiationAssociationType.PROPOSAL_LOG_ASSOCIATION)) {
                primaryKeys = new HashMap<String, String>();
                primaryKeys.put("PROPOSAL_NUMBER", negotiation.getAssociatedDocumentId());
                bean = new NegotiationAssociatedDetailBean((ProposalLog) this.getBusinessObjectService().findByPrimaryKey(ProposalLog.class, primaryKeys));
            } else if (StringUtils.equals(negotiation.getNegotiationAssociationType().getCode(), 
                    NegotiationAssociationType.SUB_AWARD_ASSOCIATION)) {
                bean = new NegotiationAssociatedDetailBean();
            } else {
                throw new IllegalArgumentException(negotiation.getNegotiationAssociationType().getCode() + " is an invalid code, should never gete here!");
            }
            return bean;
        } else {
            return null;
        }
    }

    protected ParameterService getParameterService() {
        return parameterService;
    }

    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

}
