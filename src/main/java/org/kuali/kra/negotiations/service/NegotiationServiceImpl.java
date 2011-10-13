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

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.award.budget.AwardBudgetService;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.proposallog.ProposalLog;
import org.kuali.kra.institutionalproposal.service.InstitutionalProposalService;
import org.kuali.kra.kim.bo.KcKimAttributes;
import org.kuali.kra.kim.service.impl.UnitAdministratorDerivedRoleTypeServiceImpl;
import org.kuali.kra.negotiations.bo.Negotiable;
import org.kuali.kra.negotiations.bo.Negotiation;
import org.kuali.kra.negotiations.bo.NegotiationActivity;
import org.kuali.kra.negotiations.bo.NegotiationActivityHistoryLineBean;
import org.kuali.kra.negotiations.bo.NegotiationAssociatedDetailBean;
import org.kuali.kra.negotiations.bo.NegotiationAssociationType;
import org.kuali.kra.negotiations.bo.NegotiationPersonDTO;
import org.kuali.kra.negotiations.bo.NegotiationStatus;
import org.kuali.kra.negotiations.bo.NegotiationUnassociatedDetail;
import org.kuali.kra.negotiations.document.NegotiationDocument;
import org.kuali.kra.service.KcPersonService;
import org.kuali.rice.kim.bo.role.dto.RoleMembershipInfo;
import org.kuali.rice.kim.bo.types.dto.AttributeSet;
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
    private UnitAdministratorDerivedRoleTypeServiceImpl unitAdministratorDerivedRoleTypeServiceImpl;
    private KcPersonService kcPersonService;
    
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
     * Return the CLOSED_NEGOTIATION_STATUS param.
     * @see org.kuali.kra.negotiations.service.NegotiationService#getCompleteStatusCode()
     */
    public String getCompleteStatusCode() {
        String value = getParameterService().getParameterValue(NegotiationDocument.class, "CLOSED_NEGOTIATION_STATUS");
        return value;
    }
    
    
    public Negotiable getAssociatedObject(Negotiation negotiation) {
        if (negotiation != null && negotiation.getNegotiationAssociationType() != null) {
            Negotiable bo = null;
            if (StringUtils.equals(negotiation.getNegotiationAssociationType().getCode(), NegotiationAssociationType.AWARD_ASSOCIATION)) {
                bo = getAward(negotiation.getAssociatedDocumentId());
            } else if (StringUtils.equals(negotiation.getNegotiationAssociationType().getCode(), 
                    NegotiationAssociationType.INSTITUATIONAL_PROPOSAL_ASSOCIATION)) {
                bo = getInstitutionalProposal(negotiation.getAssociatedDocumentId());
            } else if (StringUtils.equals(negotiation.getNegotiationAssociationType().getCode(), 
                    NegotiationAssociationType.NONE_ASSOCIATION)) {
                negotiation.refreshReferenceObject("unAssociatedDetail");
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
        negotiation.refreshReferenceObject("negotiationAssociationType");
        if (negotiation.getNegotiationAssociationType() != null) {
            Negotiable negotiable = negotiation.getAssociatedDocument();
            NegotiationAssociatedDetailBean bean = new NegotiationAssociatedDetailBean(negotiable);
            if (bean.getDisplayOSPAdministrators()) {
                bean.setOspAdministrators(this.getOSPAdministrators(bean.getLeadUnitNumber()));
            }
            return bean;
        } else {
            return new NegotiationAssociatedDetailBean("");
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
    
    @SuppressWarnings("unchecked")
    public NegotiationStatus getNegotiationStatus(String statusCode) {
        Map params = new HashMap();
        params.put("code", statusCode);
        return (NegotiationStatus) this.getBusinessObjectService().findMatching(NegotiationStatus.class, params).iterator().next();
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
    
    /**
     * 
     * @see org.kuali.kra.negotiations.service.NegotiationService#isPersonIsAssociatedPerson(org.kuali.kra.negotiations.bo.Negotiation, java.lang.String)
     */
    public boolean isPersonIsAssociatedPerson(Negotiation negotiation, String personToCheckPersonId) {
        Negotiable bo = getAssociatedObject(negotiation);
        if (bo != null) {
            for (NegotiationPersonDTO person : bo.getProjectPeople()) {
                if (StringUtils.equals(person.getPerson().getPersonId(), personToCheckPersonId)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * 
     * @see org.kuali.kra.negotiations.service.NegotiationService#findAndLoadNegotiationUnassociatedDetail(org.kuali.kra.negotiations.bo.Negotiation, boolean)
     */
    public NegotiationUnassociatedDetail findAndLoadNegotiationUnassociatedDetail(Negotiation negotiation) {
        if (negotiation.getNegotiationAssociationType() != null 
                && StringUtils.equalsIgnoreCase(negotiation.getNegotiationAssociationType().getCode(), NegotiationAssociationType.NONE_ASSOCIATION) 
                && StringUtils.isNotEmpty(negotiation.getAssociatedDocumentId()) && negotiation.getAssociatedDocumentId().matches("\\d*")) {
            NegotiationUnassociatedDetail unAssociatedDetail = (NegotiationUnassociatedDetail) 
                    this.getBusinessObjectService().findBySinglePrimaryKey(NegotiationUnassociatedDetail.class, negotiation.getAssociatedDocumentId());
            return unAssociatedDetail;
        } else {
            return null;
        }
    }
    
    /**
     * 
     * @see org.kuali.kra.negotiations.service.NegotiationService#getOSPAdministrators(java.lang.String)
     */
    public List<KcPerson> getOSPAdministrators(String unitNumber) {
        List<KcPerson> kcPeople = new ArrayList<KcPerson>();
        AttributeSet qualification = new AttributeSet();
        qualification.put(KcKimAttributes.UNIT_NUMBER, unitNumber);
        List<RoleMembershipInfo> roleMembershipInfos = this.getUnitAdministratorDerivedRoleTypeServiceImpl().getRoleMembersFromApplicationRole(
                Constants.MODULE_NAMESPACE_NEGOTIATION, RoleConstants.OSP_ADMINISTRATOR, qualification);
        for (RoleMembershipInfo info : roleMembershipInfos) {
            if (StringUtils.equalsIgnoreCase(info.getMemberTypeCode(), "p")) {
                //this is a person
                KcPerson person = this.getKcPersonService().getKcPersonByPersonId(info.getMemberId());
                if (!IsPersonInList(kcPeople, person)){
                    kcPeople.add(person);
                }
                
            }
        }
        return kcPeople;
    }
    
    private boolean IsPersonInList(List<KcPerson> kcPeople, KcPerson person) {
        for (KcPerson thisPerson : kcPeople) {
            if (StringUtils.equals(thisPerson.getPersonId(), person.getPersonId())) {
                return true;
            }
        }
        return false;
    }
    /**
     * 
     * @see org.kuali.kra.negotiations.service.NegotiationService#getNegotiationActivityHistoryLineBeans(java.util.List)
     */
    public List<NegotiationActivityHistoryLineBean> getNegotiationActivityHistoryLineBeans(List<NegotiationActivity> activities) {
        List<NegotiationActivityHistoryLineBean> beans = new ArrayList<NegotiationActivityHistoryLineBean>();
        for (NegotiationActivity activity : activities) {
            NegotiationActivityHistoryLineBean bean = new NegotiationActivityHistoryLineBean(activity);
            beans.add(bean);
        }
        Collections.sort(beans);
        
        // now set the effective dates and calculate the location days.
        Date previousStartDate = null;
        Date previousEndDate = null;
        String previousLocation = "";
        for (NegotiationActivityHistoryLineBean bean : beans) {
            if (StringUtils.equals(previousLocation, bean.getLocation())) {
                if (isDateBetween(bean.getStartDate(), previousStartDate, previousEndDate)
                        && isDateBetween(bean.getEndDate(), previousStartDate, previousEndDate)) {
                    //current date range lies within the previous date range
                    bean.setEfectiveLocationStartDate(null);
                    bean.setEfectiveLocationEndDate(null);
                    bean.setLocationDays("0 Days");
                    //leave previous alone
                } else if (isDateBetween(bean.getStartDate(), previousStartDate, previousEndDate) 
                        && bean.getEndDate().after(previousEndDate)) {
                    //current date range starts within the previous range, but finishes past it.
                    Date previousEndDatePlusOneDay = new Date(previousEndDate.getTime() + NegotiationActivity.MILLISECS_PER_DAY);
                    bean.setEfectiveLocationStartDate(previousEndDatePlusOneDay);
                    bean.setEfectiveLocationEndDate(bean.getEndDate());
                    bean.setLocationDays(NegotiationActivity.getNumberOfDays(previousEndDatePlusOneDay, bean.getEndDate()));
                    
                    previousEndDate = bean.getEndDate();
                } else {
                    //completely separate range.
                    previousStartDate = bean.getStartDate();
                    previousEndDate = bean.getEndDate();
                    bean.setEfectiveLocationEndDate(bean.getEndDate());
                    bean.setEfectiveLocationStartDate(bean.getStartDate());
                    bean.setLocationDays(NegotiationActivity.getNumberOfDays(bean.getStartDate(), bean.getEndDate()));
                }
            } else {
                // new location so set the effective date
                previousStartDate = bean.getStartDate();
                previousEndDate = bean.getEndDate();
                previousLocation = bean.getLocation();
                bean.setEfectiveLocationEndDate(bean.getEndDate());
                bean.setEfectiveLocationStartDate(bean.getStartDate());
                bean.setLocationDays(NegotiationActivity.getNumberOfDays(bean.getStartDate(), bean.getEndDate()));
            }
        }
        return beans;
    }
    
    private boolean isDateBetween(Date checkDate, Date rangeStart, Date rangeEnd) {
        if (rangeStart == null) {
            return false;
        }
        if (checkDate == null) {
            checkDate = new Date(Calendar.getInstance().getTimeInMillis());
        }
        if (rangeEnd == null) {
            rangeEnd = new Date(Calendar.getInstance().getTimeInMillis());
        }
        boolean startOk = rangeStart.equals(checkDate) || rangeStart.before(checkDate);
        boolean endOk = rangeEnd.equals(checkDate) || rangeEnd.after(checkDate);
        return startOk && endOk;
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

    public UnitAdministratorDerivedRoleTypeServiceImpl getUnitAdministratorDerivedRoleTypeServiceImpl() {
        return unitAdministratorDerivedRoleTypeServiceImpl;
    }

    public void setUnitAdministratorDerivedRoleTypeServiceImpl(
            UnitAdministratorDerivedRoleTypeServiceImpl unitAdministratorDerivedRoleTypeServiceImpl) {
        this.unitAdministratorDerivedRoleTypeServiceImpl = unitAdministratorDerivedRoleTypeServiceImpl;
    }

    public KcPersonService getKcPersonService() {
        return kcPersonService;
    }

    public void setKcPersonService(KcPersonService kcPersonService) {
        this.kcPersonService = kcPersonService;
    }
    
    
}