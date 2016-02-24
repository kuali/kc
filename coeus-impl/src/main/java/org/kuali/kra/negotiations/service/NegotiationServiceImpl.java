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
package org.kuali.kra.negotiations.service;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.common.framework.version.history.VersionHistory;
import org.kuali.coeus.common.framework.version.history.VersionHistoryService;
import org.kuali.kra.award.budget.AwardBudgetService;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.proposallog.ProposalLog;
import org.kuali.kra.institutionalproposal.service.InstitutionalProposalService;
import org.kuali.coeus.common.impl.unit.admin.UnitAdministratorDerivedRoleTypeServiceImpl;
import org.kuali.kra.negotiations.bo.*;
import org.kuali.kra.negotiations.document.NegotiationDocument;
import org.kuali.kra.negotiations.notifications.NegotiationNotification;
import org.kuali.kra.subaward.bo.SubAward;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.sql.Date;
import java.util.*;

/**
 * Service impl for NegotiationService.
 */
public class NegotiationServiceImpl implements NegotiationService {
    
    static final String ZERO_DAYS_LONG_STRING = "0 Days";

	private static final String PARAMETER_DELIMITER = ",";
    
    private ParameterService parameterService;
    private AwardBudgetService awardBudgetService;
    private InstitutionalProposalService institutionalProposalService;
    private UnitAdministratorDerivedRoleTypeServiceImpl unitAdministratorDerivedRoleTypeServiceImpl;
    private KcPersonService kcPersonService;
    private VersionHistoryService versionHistoryService;
    
    private BusinessObjectService businessObjectService;
    
    /**
     * Return the negotiationInProgressStatusCodes as a list of strings.
     * @see org.kuali.kra.negotiations.service.NegotiationService#getInProgressStatusCodes()
     */
    public List<String> getInProgressStatusCodes() {
        String value = getParameterService().getParameterValueAsString(NegotiationDocument.class, "negotiationInProgressStatusCodes");
        return Arrays.asList(value.split(PARAMETER_DELIMITER));
    }
    
    /**
     * Return the negotiationCompletedStatusCodes as a list of strings.
     * @see org.kuali.kra.negotiations.service.NegotiationService#getCompletedStatusCodes()
     */
    public List<String> getCompletedStatusCodes() {
        String value = getParameterService().getParameterValueAsString(NegotiationDocument.class, "negotiationCompletedStatusCodes");
        return Arrays.asList(value.split(PARAMETER_DELIMITER));        
    }

    /**
     * Return the CLOSED_NEGOTIATION_STATUS param.
     * @see org.kuali.kra.negotiations.service.NegotiationService#getCompleteStatusCode()
     */
    public String getCompleteStatusCode() {
        String value = getParameterService().getParameterValueAsString(NegotiationDocument.class, "CLOSED_NEGOTIATION_STATUS");
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
                bo = getSubAward(negotiation.getAssociatedDocumentId());
            }
            return bo;
        } else {
            return null;
        }
    }
    
    @Override
    public NegotiationAssociatedDetailBean buildNegotiationAssociatedDetailBean(Negotiation negotiation) {
        negotiation.refreshReferenceObject("negotiationAssociationType");
        if (negotiation.getNegotiationAssociationType() != null) {
            Negotiable negotiable = negotiation.getAssociatedDocument();
            NegotiationAssociatedDetailBean bean = new NegotiationAssociatedDetailBean(negotiable);
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
    
    private SubAward getSubAward(String subAwardId) {
        VersionHistory versionHistory = getVersionHistoryService().getActiveOrNewestVersion(SubAward.class, subAwardId);
        if (versionHistory != null) {
            return (SubAward) versionHistory.getSequenceOwner();
        } else {
            return null;
        }
    }
    
    private InstitutionalProposal getInstitutionalProposal(String proposalNumber) {
        InstitutionalProposal ip = this.getInstitutionalProposalService().getActiveInstitutionalProposalVersion(proposalNumber);
        if (ip == null) {
            //the proposal_number doesn't have an active one associated with it. so grab an inactive one, this will happen when a
            //a proposal log has been promoted to an institutional proposal but not completed yet.
            Map params = new HashMap();
            params.put("PROPOSAL_NUMBER", proposalNumber);
            Collection<InstitutionalProposal> proposals = this.businessObjectService.findMatching(InstitutionalProposal.class, params);
            if (proposals != null && proposals.size() > 0) {
                ip = proposals.iterator().next();
            }
        }
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
        } else if (bo instanceof SubAward) {
            SubAward subAward = (SubAward) bo;
            return new ArrayList(getAssociatedNegotiations(subAward.getSubAwardCode(), NegotiationAssociationType.SUB_AWARD_ASSOCIATION));
        }
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
        return this.isNegotaitionAssociationTypeActive(NegotiationAssociationType.AWARD_ASSOCIATION);
    }

    @Override
    public boolean isInstitutionalProposalLinkingEnabled() {
        return this.isNegotaitionAssociationTypeActive(NegotiationAssociationType.INSTITUATIONAL_PROPOSAL_ASSOCIATION);
    }

    @Override
    public boolean isNoModuleLinkingEnabled() {
        return this.isNegotaitionAssociationTypeActive(NegotiationAssociationType.NONE_ASSOCIATION);
    }

    @Override
    public boolean isProposalLogLinkingEnabled() {
        return this.isNegotaitionAssociationTypeActive(NegotiationAssociationType.PROPOSAL_LOG_ASSOCIATION);
    }

    @Override
    public boolean isSubawardLinkingEnabled() {
        return this.isNegotaitionAssociationTypeActive(NegotiationAssociationType.SUB_AWARD_ASSOCIATION);
    }
    
    /**
     * 
     * This method is a helper method for the isXXXLinkingEnabled functions.  It checks the association type object's active value.
     * @param associationTypeCode
     * @return
     */
    protected boolean isNegotaitionAssociationTypeActive(String associationTypeCode) {
        NegotiationAssociationType nat = this.getNegotiationAssociationType(associationTypeCode);
        return nat.isActive();
    }

    @Override
    public void checkForPropLogPromotion(Negotiation negotiation) {
        if (negotiation.getNegotiationAssociationType() != null 
                && StringUtils.equals(negotiation.getNegotiationAssociationType().getCode(), NegotiationAssociationType.PROPOSAL_LOG_ASSOCIATION)
                && isInstitutionalProposalLinkingEnabled()) {
            ProposalLog propLog = getBusinessObjectService().findBySinglePrimaryKey(ProposalLog.class, negotiation.getAssociatedDocumentId());
            //if the proplog has been promoted to a inst prop then relink negotiation to the new inst prop.
            if (propLog != null && StringUtils.isNotBlank(propLog.getInstProposalNumber())) {
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
    
    @Override
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
    
    @Override
    public List<NegotiationActivityHistoryLineBean> getNegotiationActivityHistoryLineBeans(List<NegotiationActivity> activities) {
        List<NegotiationActivityHistoryLineBean> beans = new ArrayList<NegotiationActivityHistoryLineBean>();
        for (NegotiationActivity activity : activities) {
            if (activity.getLocation() != null && activity.getActivityType() != null) {
                NegotiationActivityHistoryLineBean bean = new NegotiationActivityHistoryLineBean(activity);
                beans.add(bean);
            }
        }
        Collections.sort(beans);
        
        // now set the effective dates and calculate the location days.
        Date previousStartDate = null;
        Date previousEndDate = null;
        String previousLocation = "";
        int counter = 1;
        List<NegotiationActivityHistoryLineBean> beansToReturn = new ArrayList<NegotiationActivityHistoryLineBean>();
        for (NegotiationActivityHistoryLineBean bean : beans) {
            if (StringUtils.equals(previousLocation, bean.getLocation())) {
                if (isDateBetween(bean.getStartDate(), previousStartDate, previousEndDate)
                        && isDateBetween(bean.getEndDate(), previousStartDate, previousEndDate)) {
                    //current date range lies within the previous date range
                    setBeanStuff(bean, null, null, ZERO_DAYS_LONG_STRING);
                    //leave previous alone
                } else if (isDateBetween(bean.getStartDate(), previousStartDate, previousEndDate) 
                        && (bean.getEndDate() == null || bean.getEndDate().after(previousEndDate))) {
                    //current date range starts within the previous range, but finishes past it.
                    Date previousEndDatePlusOneDay = new Date(previousEndDate.getTime() + NegotiationActivity.MILLISECS_PER_DAY);                    
                    previousEndDate = bean.getEndDate() != null ? bean.getEndDate() : new Date(Calendar.getInstance().getTimeInMillis());
                    setBeanStuff(bean, previousEndDatePlusOneDay, bean.getEndDate(), NegotiationActivity.getNumberOfDays(previousEndDatePlusOneDay, bean.getEndDate()));
                } else {
                    //completely separate range.
                    previousStartDate = bean.getStartDate();
                    previousEndDate = bean.getEndDate();
                    setBeanStuff(bean, bean.getStartDate(), bean.getEndDate(), NegotiationActivity.getNumberOfDays(bean.getStartDate(), bean.getEndDate()));
                }
            } else {
                // new location so set the effective date
                previousStartDate = bean.getStartDate();
                previousEndDate = bean.getEndDate();
                previousLocation = bean.getLocation();
                setBeanStuff(bean, bean.getStartDate(), bean.getEndDate(), NegotiationActivity.getNumberOfDays(bean.getStartDate(), bean.getEndDate()));
                if (!beansToReturn.isEmpty()) { 
                    beansToReturn.add(new NegotiationActivityHistoryLineBean());
                }
            }
            bean.setLineNumber(String.valueOf(counter));
            beansToReturn.add(bean);
            counter++;
        }
        return beansToReturn;
    }
    
    public List<NegotiationNotification> getNegotiationNotifications(Negotiation negotiation) {
        List<NegotiationNotification> notifications = new ArrayList<NegotiationNotification>();
        if (negotiation.getNegotiationDocument() != null) {
            Map<String, Object> fieldValues = new HashMap<String, Object>(); 
            fieldValues.put("documentNumber", negotiation.getNegotiationDocument().getDocumentNumber());
            notifications = (List<NegotiationNotification>)getBusinessObjectService().findMatching(NegotiationNotification.class, fieldValues);
        }
        return notifications;
    }
    

    private void setBeanStuff(NegotiationActivityHistoryLineBean bean, Date efectiveLocationStartDate, Date efectiveLocationEndDate, String locationDays) {
        bean.setEfectiveLocationEndDate(efectiveLocationEndDate);
        bean.setEfectiveLocationStartDate(efectiveLocationStartDate);
        bean.setLocationDays(locationDays); 
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
    
    @Override
    public void promoteProposalLogNegotiation(String proposalLogProposalNumber, String institutionalProposalProposalNumber) {
        Collection<Negotiation> negotiations = getAssociatedNegotiations(proposalLogProposalNumber, NegotiationAssociationType.PROPOSAL_LOG_ASSOCIATION);
        ArrayList<Negotiation> negotiationsToSave = new ArrayList<Negotiation>();
        if (negotiations != null && !negotiations.isEmpty()) {
            NegotiationAssociationType ipAssocationType = getNegotiationAssociationType(NegotiationAssociationType.INSTITUATIONAL_PROPOSAL_ASSOCIATION);
            for (Negotiation negotiation : negotiations) {
                negotiation.setNegotiationAssociationType(ipAssocationType);
                negotiation.setNegotiationAssociationTypeId(ipAssocationType.getId());
                negotiation.setAssociatedDocumentId(institutionalProposalProposalNumber);
                negotiationsToSave.add(negotiation);
            }
        }
        this.getBusinessObjectService().save(negotiationsToSave);
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

    public VersionHistoryService getVersionHistoryService() {
        return versionHistoryService;
    }

    public void setVersionHistoryService(VersionHistoryService versionHistoryService) {
        this.versionHistoryService = versionHistoryService;
    }
    
    
}
