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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.award.budget.AwardBudgetService;
import org.kuali.kra.award.contacts.AwardPerson;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.institutionalproposal.contacts.InstitutionalProposalPerson;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.proposallog.ProposalLog;
import org.kuali.kra.institutionalproposal.service.InstitutionalProposalService;
import org.kuali.kra.negotiations.bo.Negotiation;
import org.kuali.kra.negotiations.bo.NegotiationAssociatedDetailBean;
import org.kuali.kra.negotiations.bo.NegotiationAssociationType;
import org.kuali.kra.negotiations.bo.NegotiationStatus;
import org.kuali.kra.negotiations.bo.NegotiationUnassociatedDetail;
import org.kuali.kra.negotiations.document.NegotiationDocument;
import org.kuali.rice.kns.bo.BusinessObject;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.ParameterService;

/**
 * Service impl for NegotiationService.
 */
public class NegotiationServiceImpl implements NegotiationService {
    
    private static final String PARAMETER_DELIMITER = ",";
    
    private ParameterService parameterService;
    private AwardBudgetService awardBudgetService;
    private InstitutionalProposalService institutionalProposalService;
    
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
    
    public BusinessObject getAssociatedObject(Negotiation negotiation) {
        if (negotiation != null && negotiation.getNegotiationAssociationType() != null) {
            BusinessObject bo = null;
            if (StringUtils.equals(negotiation.getNegotiationAssociationType().getCode(), NegotiationAssociationType.AWARD_ASSOCIATION)) {
                bo = getAward(negotiation.getAssociatedDocumentId());
            } else if (StringUtils.equals(negotiation.getNegotiationAssociationType().getCode(), 
                    NegotiationAssociationType.INSTITUATIONAL_PROPOSAL_ASSOCIATION)) {
                bo = getInstitutionalProposal(negotiation.getAssociatedDocumentId());
            } else if (StringUtils.equals(negotiation.getNegotiationAssociationType().getCode(), 
                    NegotiationAssociationType.NONE_ASSOCIATION)) {
                bo = negotiation.getUnAssociatedDetail();
            } else if (StringUtils.equals(negotiation.getNegotiationAssociationType().getCode(), 
                    NegotiationAssociationType.PROPOSAL_LOG_ASSOCIATION)) {
                bo = getProposalLog(negotiation.getAssociatedDocumentId());
            } else if (StringUtils.equals(negotiation.getNegotiationAssociationType().getCode(), 
                    NegotiationAssociationType.SUB_AWARD_ASSOCIATION)) {
                bo = null;
            }
            return bo;
        } else {
            return null;
        }
    }
    
    /**
     * 
     * @see org.kuali.kra.negotiations.service.NegotiationService#buildNegotiationAssociatedDetailBean(org.kuali.kra.negotiations.bo.Negotiation)
     */
    public NegotiationAssociatedDetailBean buildNegotiationAssociatedDetailBean(Negotiation negotiation) {   
        if (negotiation.getNegotiationAssociationType() != null) {
            NegotiationAssociatedDetailBean bean;
            if (StringUtils.equals(negotiation.getNegotiationAssociationType().getCode(), NegotiationAssociationType.AWARD_ASSOCIATION)) {
                Award award = getAward(negotiation.getAssociatedDocumentId());
                bean = new NegotiationAssociatedDetailBean(award);
            } else if (StringUtils.equals(negotiation.getNegotiationAssociationType().getCode(), 
                    NegotiationAssociationType.INSTITUATIONAL_PROPOSAL_ASSOCIATION)) {
                InstitutionalProposal ip = getInstitutionalProposal(negotiation.getAssociatedDocumentId());
                bean = new NegotiationAssociatedDetailBean(ip);
            } else if (StringUtils.equals(negotiation.getNegotiationAssociationType().getCode(), 
                    NegotiationAssociationType.NONE_ASSOCIATION)) {
                NegotiationUnassociatedDetail detail = negotiation.getUnAssociatedDetail();
                bean = new NegotiationAssociatedDetailBean(detail);
            } else if (StringUtils.equals(negotiation.getNegotiationAssociationType().getCode(), 
                    NegotiationAssociationType.PROPOSAL_LOG_ASSOCIATION)) {
                ProposalLog pl = getProposalLog(negotiation.getAssociatedDocumentId());
                bean = new NegotiationAssociatedDetailBean(pl);
            } else if (StringUtils.equals(negotiation.getNegotiationAssociationType().getCode(), 
                    NegotiationAssociationType.SUB_AWARD_ASSOCIATION)) {
                bean = new NegotiationAssociatedDetailBean();
            } else {
                throw new IllegalArgumentException(negotiation.getNegotiationAssociationType().getCode() + " is an invalid code, should never gete here!");
            }
            return bean;
        } else {
            return new NegotiationAssociatedDetailBean();
        }
    }
    
    private Award getAward(String awardNumber) {
        Award award = this.getAwardBudgetService().getActiveOrNewestAward(awardNumber);
        return award;
    }
    
    private ProposalLog getProposalLog(String proposalNumber) {
        Map<String, String> primaryKeys = new HashMap<String, String>();
        primaryKeys.put("PROPOSAL_NUMBER", proposalNumber);
        ProposalLog pl = (ProposalLog) this.getBusinessObjectService().findByPrimaryKey(ProposalLog.class, primaryKeys);
        return pl;
    }
    
    private InstitutionalProposal getInstitutionalProposal(String proposalNumber) {
        InstitutionalProposal ip = this.getInstitutionalProposalService().getActiveInstitutionalProposalVersion(proposalNumber);
        return ip;
    }
    
    @SuppressWarnings("unchecked")
    public List<Negotiation> getAssociatedNegotiations(BusinessObject bo) {
        List<Negotiation> result = new ArrayList<Negotiation>();
        if (bo instanceof ProposalLog) {
            ProposalLog propLog = (ProposalLog) bo;
            return new ArrayList(getAssociatedNegotiations(propLog.getProposalNumber(), NegotiationAssociationType.PROPOSAL_LOG_ASSOCIATION));
        } else if (bo instanceof InstitutionalProposal) {
            InstitutionalProposal ip = (InstitutionalProposal) bo;
            return new ArrayList(getAssociatedNegotiations(ip.getProposalNumber(), NegotiationAssociationType.INSTITUATIONAL_PROPOSAL_ASSOCIATION));
        } else if (bo instanceof Award) {
            Award award = (Award) bo;
            return new ArrayList(getAssociatedNegotiations(award.getAwardNumber(), NegotiationAssociationType.AWARD_ASSOCIATION));            
        }
        //TODO: subaward links to be implemented here when subaward is implemented.
        return result;
    }  
    
    @SuppressWarnings("unchecked")
    protected Collection<Negotiation> getAssociatedNegotiations(String associatedId, String associationTypeCode) {
        Map<String, Object> values = new HashMap<String, Object>();
        values.put("associatedDocumentId", associatedId);
        values.put("negotiationAssociationTypeId", getNegotiationAssociationType(associationTypeCode).getId());
        return (Collection<Negotiation>) getBusinessObjectService().findMatching(Negotiation.class, values);
    }
    
    @SuppressWarnings("unchecked")
    public NegotiationAssociationType getNegotiationAssociationType(String associationTypeCode) {
        Map params = new HashMap();
        params.put("code", associationTypeCode);
        return (NegotiationAssociationType) this.getBusinessObjectService().findMatching(NegotiationAssociationType.class, params).iterator().next();
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

    public AwardBudgetService getAwardBudgetService() {
        return awardBudgetService;
    }

    public void setAwardBudgetService(AwardBudgetService awardBudgetService) {
        this.awardBudgetService = awardBudgetService;
    }

    public InstitutionalProposalService getInstitutionalProposalService() {
        return institutionalProposalService;
    }

    public void setInstitutionalProposalService(InstitutionalProposalService institutionalProposalService) {
        this.institutionalProposalService = institutionalProposalService;
    }

    @Override
    public boolean isAwardLinkingEnabled() {
        String value = getParameterService().getParameterValue(NegotiationDocument.class, "ENABLE_NEGOTIATION_TO_AWARD_LINK");
        return StringUtils.equals(value, "1");
    }

    @Override
    public boolean isInstitutionalProposalLinkingEnabled() {
        String value = getParameterService().getParameterValue(NegotiationDocument.class, "ENABLE_NEGOTIATION_TO_INSTPROP_LINK");
        return StringUtils.equals(value, "1");
    }

    @Override
    public boolean isNoModuleLinkingEnabled() {
        String value = getParameterService().getParameterValue(NegotiationDocument.class, "ENABLE_NEGOTIATION_TO_NO_MODULE");
        return StringUtils.equals(value, "1");
    }

    @Override
    public boolean isProposalLogLinkingEnabled() {
        String value = getParameterService().getParameterValue(NegotiationDocument.class, "ENABLE_NEGOTIATION_TO_PROPOSALLOG_LINK");
        return StringUtils.equals(value, "1");
    }

    @Override
    public boolean isSubawardLinkingEnabled() {
        String value = getParameterService().getParameterValue(NegotiationDocument.class, "ENABLE_NEGOTIATION_TO_SUBAWARD_LINK");
        return StringUtils.equals(value, "1");
    }

    /**
     * @see org.kuali.kra.negotiations.service.NegotiationService#checkForPropLogPromotion(org.kuali.kra.negotiations.bo.Negotiation)
     */
    @Override
    public void checkForPropLogPromotion(Negotiation negotiation) {
        if (negotiation.getNegotiationAssociationType() != null 
                && StringUtils.equals(negotiation.getNegotiationAssociationType().getCode(), NegotiationAssociationType.PROPOSAL_LOG_ASSOCIATION)
                && isInstitutionalProposalLinkingEnabled()) {
            ProposalLog propLog = getBusinessObjectService().findBySinglePrimaryKey(ProposalLog.class, negotiation.getAssociatedDocumentId());
            //if the proplog has been promoted to a inst prop then relink negotiation to the new inst prop.
            if (StringUtils.isNotBlank(propLog.getInstProposalNumber())) {
                negotiation.setNegotiationAssociationType(
                        getNegotiationAssociationType(NegotiationAssociationType.INSTITUATIONAL_PROPOSAL_ASSOCIATION));
                InstitutionalProposal proposal = getBusinessObjectService().findBySinglePrimaryKey(InstitutionalProposal.class, propLog.getInstProposalNumber());
                if (proposal != null) {
                    negotiation.setNegotiationAssociationTypeId(negotiation.getNegotiationAssociationType().getId());
                    negotiation.setAssociatedDocumentId(proposal.getProposalNumber());
                }
            }
        }
        
    }
    
    public boolean isPersonIsAssociatedPerson(Negotiation negotiation, String personToCheckPersonId) {
        if (negotiation != null && negotiation.getNegotiationAssociationType() != null) {
            if (StringUtils.equals(negotiation.getNegotiationAssociationType().getCode(), NegotiationAssociationType.AWARD_ASSOCIATION)) {
                Award award = getAward(negotiation.getAssociatedDocumentId());
                List<AwardPerson> persons = award.getProjectPersons();
                for (AwardPerson person : persons) {
                    if (StringUtils.equals(person.getPerson().getPersonId(), personToCheckPersonId)) {
                        return true;
                    }
                }
            } else if (StringUtils.equals(negotiation.getNegotiationAssociationType().getCode(), 
                    NegotiationAssociationType.INSTITUATIONAL_PROPOSAL_ASSOCIATION)) {
                InstitutionalProposal ip = getInstitutionalProposal(negotiation.getAssociatedDocumentId());
                List<InstitutionalProposalPerson> persons = ip.getProjectPersons();
                for (InstitutionalProposalPerson person : persons) {
                    if (StringUtils.equals(person.getPerson().getPersonId(), personToCheckPersonId)) {
                        return true;
                    }
                }
            } else if (StringUtils.equals(negotiation.getNegotiationAssociationType().getCode(), 
                    NegotiationAssociationType.NONE_ASSOCIATION)) {
                if (negotiation.getUnAssociatedDetail() == null && negotiation.getAssociatedDocumentId() != null){
                    findAndLoadNegotiationUnassociatedDetail(negotiation, false);
                }
                if (negotiation.getUnAssociatedDetail().getPIEmployee() != null) {
                    if (StringUtils.equals(negotiation.getUnAssociatedDetail().getPIEmployee().getPersonId(), personToCheckPersonId)) {
                        return true;
                    }
                }
                //bo = negotiation.getUnAssociatedDetail();
            } else if (StringUtils.equals(negotiation.getNegotiationAssociationType().getCode(), 
                    NegotiationAssociationType.PROPOSAL_LOG_ASSOCIATION)) {
                ProposalLog pl = getProposalLog(negotiation.getAssociatedDocumentId());
            } else if (StringUtils.equals(negotiation.getNegotiationAssociationType().getCode(), 
                    NegotiationAssociationType.SUB_AWARD_ASSOCIATION)) {
                //bo = null;
                /**
                 * @todo after subwards has been created, implement people stuff here.
                 */
            }
        }
        return false;
    }
    
    public void findAndLoadNegotiationUnassociatedDetail(Negotiation negotiation, boolean reload) {
        if (negotiation.getNegotiationAssociationType() != null 
                && StringUtils.equalsIgnoreCase(negotiation.getNegotiationAssociationType().getCode(), NegotiationAssociationType.NONE_ASSOCIATION) 
                && StringUtils.isNotEmpty(negotiation.getAssociatedDocumentId())) {
            if (reload || negotiation.getUnAssociatedDetail() == null) {
                Map params = new HashMap();
                params.put("NEGOTIATION_UNASSOC_DETAIL_ID", negotiation.getAssociatedDocumentId());
                NegotiationUnassociatedDetail unAssociatedDetail = (NegotiationUnassociatedDetail) 
                        this.getBusinessObjectService().findByPrimaryKey(NegotiationUnassociatedDetail.class, params);
                negotiation.setUnAssociatedDetail(unAssociatedDetail);
            }
        }
    }

}