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
package org.kuali.kra.proposaldevelopment.bo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.kra.award.home.ContactRole;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.bo.NoticeOfOpportunity;
import org.kuali.kra.bo.NsfCode;
import org.kuali.kra.bo.Organization;
import org.kuali.kra.bo.Rolodex;
import org.kuali.kra.bo.Sponsor;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.budget.core.BudgetParent;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.personnel.PersonRolodex;
import org.kuali.kra.coi.Disclosurable;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.hierarchy.HierarchyStatusConstants;
import org.kuali.kra.proposaldevelopment.hierarchy.ProposalHierarchyException;
import org.kuali.kra.proposaldevelopment.hierarchy.service.ProposalHierarchyService;
import org.kuali.kra.proposaldevelopment.service.KeyPersonnelService;
import org.kuali.kra.proposaldevelopment.service.NarrativeService;
import org.kuali.kra.proposaldevelopment.service.ProposalDevelopmentService;
import org.kuali.kra.proposaldevelopment.service.ProposalPersonBiographyService;
import org.kuali.kra.proposaldevelopment.service.ProposalStatusService;
import org.kuali.kra.proposaldevelopment.specialreview.ProposalSpecialReview;
import org.kuali.kra.proposaldevelopment.specialreview.ProposalSpecialReviewExemption;
import org.kuali.kra.s2s.bo.S2sAppSubmission;
import org.kuali.kra.s2s.bo.S2sOppForms;
import org.kuali.kra.s2s.bo.S2sOpportunity;
import org.kuali.kra.service.Sponsorable;
import org.kuali.kra.service.YnqService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.springframework.util.AutoPopulatingList;

/**
 * This class...
 */
public class DevelopmentProposal extends KraPersistableBusinessObjectBase implements BudgetParent, Sponsorable, Disclosurable {

    private static final long serialVersionUID = -9211313487776934111L;

    private static final String ATTACHMENTS_COMPLETE = "Complete";

    private static final String ATTACHMENTS_INCOMPLETE = "Inomplete";

    private static final String ATTACHMENTS_NONE = "None";

    private String proposalNumber;

    private String proposalTypeCode;

    private ProposalType proposalType;

    private String continuedFrom;

    private String sponsorCode;

    private String activityTypeCode;

    private String ownedByUnitNumber;

    private Date requestedStartDateInitial;

    private Date requestedEndDateInitial;

    private String title;

    private String currentAwardNumber;

    private Date deadlineDate;

    private String noticeOfOpportunityCode;

    private NoticeOfOpportunity noticeOfOpportunity;

    private String deadlineType;

    private String cfdaNumber;

    private String programAnnouncementNumber;

    private String primeSponsorCode;

    private String sponsorProposalNumber;

    private String nsfCode;

    private NsfCode nsfCodeBo;

    private Boolean subcontracts;

    private String agencyDivisionCode;

    private String agencyProgramCode;

    private String programAnnouncementTitle;

    private String mailBy;

    private String mailType;

    private String mailAccountNumber;

    private String mailDescription;

    private Integer mailingAddressId;

    private String numberOfCopies;

    private String proposalStateTypeCode;

    private ProposalState proposalState;

    private List<ProposalSite> proposalSites;

    // TODO: just for delivery panel. not a real reference  
    private Rolodex rolodex;

    private List<ProposalSpecialReview> propSpecialReviews;

    private List<PropScienceKeyword> propScienceKeywords;

    private List<ProposalPerson> proposalPersons;

    private List<S2sOppForms> s2sOppForms;

    private S2sOpportunity s2sOpportunity;

    private List<S2sAppSubmission> s2sAppSubmission;

    private String newScienceKeywordCode;

    private String newDescription;

    private Sponsor sponsor;

    private Integer nextProposalPersonNumber;

    private String budgetStatus;

    private String budgetStatusDescription;

    private List<Narrative> narratives;

    private List<ProposalAbstract> proposalAbstracts;

    private List<Narrative> instituteAttachments;

    private List<ProposalPersonBiography> propPersonBios;

    private List<ProposalPerson> investigators;

    private Collection<InvestigatorCreditType> investigatorCreditTypes;

    private Unit ownedByUnit;
    transient private NarrativeService narrativeService;
    transient private ProposalPersonBiographyService proposalPersonBiographyService;
    private ActivityType activityType;

    private List<ProposalYnq> proposalYnqs;

    private List<YnqGroupName> ynqGroupNames;

    //    private List<BudgetDocumentVersion> budgetDocumentVersions;  
    private String creationStatusCode;

    private Map<String, String> nihDescription;

    private boolean sponsorNihMultiplePi;

    private boolean sponsorNihOsc;

    private List<ProposalChangedData> proposalChangedDataList;

    private Map<String, List<ProposalChangedData>> proposalChangeHistory;

    private Boolean submitFlag = Boolean.FALSE;

    private ProposalDevelopmentDocument proposalDocument;

    private String hierarchyStatus;

    private String hierarchyStatusName;
    private String hierarchyOriginatingChildProposalNumber;


    private String hierarchyParentProposalNumber;

    private Integer hierarchyLastSyncHashCode;

    private String hierarchyBudgetType;

    private transient ParameterService parameterService;

    private transient ProposalHierarchyService proposalHierarchyService;
    private transient KeyPersonnelService keyPersonnelService;
    private Sponsor primeSponsor;



    /**
     * Looks up and returns the ParameterService.
     * @return the parameter service. 
     */
    protected ParameterService getParameterService() {
        if (this.parameterService == null) {
            this.parameterService = KraServiceLocator.getService(ParameterService.class);
        }
        return this.parameterService;
    }

    /**
     * Looks up and returns the ProposalHierarchyService.
     * @return the proposal hierarchy service. 
     */
    protected ProposalHierarchyService getProposalHierarchyService() {
        if (this.proposalHierarchyService == null) {
            this.proposalHierarchyService = KraServiceLocator.getService(ProposalHierarchyService.class);
        }
        return this.proposalHierarchyService;
    }

    protected KeyPersonnelService getKeyPersonnelService() {
        if (keyPersonnelService == null) {
            keyPersonnelService = KraServiceLocator.getService(KeyPersonnelService.class);
        }
        return keyPersonnelService;
    }

    /**
     * Gets the hierarchy attribute. 
     * @return Returns the hierarchy.
     */
    public String getHierarchyStatus() {
        return hierarchyStatus;
    }

    /**
     * Sets the hierarchy attribute value.
     * @param hierarchy The hierarchy to set.
     */
    public void setHierarchyStatus(String hierarchyStatus) {
        this.hierarchyStatus = hierarchyStatus;
    }

    /**
     * Gets the hierarchyParentProposalNumber attribute. 
     * @return Returns the hierarchyParentProposalNumber.
     */
    public String getHierarchyParentProposalNumber() {
        return hierarchyParentProposalNumber;
    }

    /**
     * Sets the hierarchyParentProposalNumber attribute value.
     * @param hierarchyParentProposalNumber The hierarchyParentProposalNumber to set.
     */
    public void setHierarchyParentProposalNumber(String hierarchyParentProposalNumber) {
        this.hierarchyParentProposalNumber = hierarchyParentProposalNumber;
    }

    public String getHierarchyOriginatingChildProposalNumber() {
        return hierarchyOriginatingChildProposalNumber;
    }

    public void setHierarchyOriginatingChildProposalNumber(String hierarchyOriginatingChildProposalNumber) {
        this.hierarchyOriginatingChildProposalNumber = hierarchyOriginatingChildProposalNumber;
    }

    /**
     * Sets the hierarchyLastSyncHashCode attribute value.
     * @param hierarchyLastSyncHashCode The hierarchyLastSyncHashCode to set.
     */
    public void setHierarchyLastSyncHashCode(Integer hierarchyLastSyncHashCode) {
        this.hierarchyLastSyncHashCode = hierarchyLastSyncHashCode;
    }

    /**
     * Gets the hierarchyLastSyncHashCode attribute. 
     * @return Returns the hierarchyLastSyncHashCode.
     */
    public Integer getHierarchyLastSyncHashCode() {
        return hierarchyLastSyncHashCode;
    }

    public boolean isParent() {
        return HierarchyStatusConstants.Parent.code().equals(hierarchyStatus);
    }

    public boolean isChild() {
        return HierarchyStatusConstants.Child.code().equals(hierarchyStatus);
    }

    public boolean isInHierarchy() {
        return !HierarchyStatusConstants.None.code().equals(hierarchyStatus);
    }

    public String getHierarchyStatusName() {
        hierarchyStatusName = HierarchyStatusConstants.None.description();
        for (HierarchyStatusConstants status : HierarchyStatusConstants.values()) {
            if (status.code().equals(getHierarchyStatus())) hierarchyStatusName = status.description();
        }
        return hierarchyStatusName;
    }

    public void setHierarchyStatusName(String hierarchyStatusName) {
        this.hierarchyStatusName = hierarchyStatusName;
    }

    /**
     * Sets the hierarchyBudgetType attribute value.
     * @param hierarchyBudgetType The hierarchyBudgetType to set.
     */
    public void setHierarchyBudgetType(String hierarchyBudgetType) {
        this.hierarchyBudgetType = hierarchyBudgetType;
    }

    /**
     * Gets the hierarchyBudgetType attribute. 
     * @return Returns the hierarchyBudgetType.
     */
    public String getHierarchyBudgetType() {
        return hierarchyBudgetType;
    }

    @SuppressWarnings("unchecked")
    public DevelopmentProposal() {
        super();
        setProposalStateTypeCode(ProposalState.IN_PROGRESS);
        propScienceKeywords = new AutoPopulatingList<PropScienceKeyword>(PropScienceKeyword.class);
        newDescription = getDefaultNewDescription();
        propSpecialReviews = new AutoPopulatingList<ProposalSpecialReview>(ProposalSpecialReview.class);
        proposalPersons = new ArrayList<ProposalPerson>();
        nextProposalPersonNumber = Integer.valueOf(1);
        narratives = new ArrayList<Narrative>();
        proposalAbstracts = new ArrayList<ProposalAbstract>();
        instituteAttachments = new ArrayList<Narrative>();
        propPersonBios = new ArrayList<ProposalPersonBiography>();
        proposalYnqs = new ArrayList<ProposalYnq>();
        ynqGroupNames = new ArrayList<YnqGroupName>();
        investigators = new ArrayList<ProposalPerson>();
        s2sOppForms = new ArrayList<S2sOppForms>();
        s2sAppSubmission = new ArrayList<S2sAppSubmission>();
        proposalChangedDataList = new AutoPopulatingList<ProposalChangedData>(ProposalChangedData.class);
        proposalChangeHistory = new TreeMap<String, List<ProposalChangedData>>();
        hierarchyStatus = HierarchyStatusConstants.None.code();
        hierarchyStatusName = HierarchyStatusConstants.None.description();
        initProposalSites();
    }

    private void initProposalSites() {
        proposalSites = new ArrayList<ProposalSite>();
        setApplicantOrganization(new ProposalSite());
        setPerformingOrganization(new ProposalSite());
    }

    public void initializeOwnedByUnitNumber() {
        ProposalDevelopmentService proposalDevelopmentService = KraServiceLocator.getService(ProposalDevelopmentService.class);
        List<Unit> userUnits = proposalDevelopmentService.getDefaultModifyProposalUnitsForUser(GlobalVariables.getUserSession().getPrincipalId());
        if (userUnits.size() == 1) {
            this.setOwnedByUnitNumber(userUnits.get(0).getUnitNumber());
            proposalDevelopmentService.initializeUnitOrganizationLocation(this.getProposalDocument());
        }
    }

    /**
     * Gets the value of proposalPersons
     * 
     * @return the value of proposalPersons
     */
    public List<ProposalPerson> getProposalPersons() {
        evaluateMoveOptions();
        return this.proposalPersons;
    }

    private void evaluateMoveOptions() {
        for (int i = 0; i < proposalPersons.size(); i++) {
            ProposalPerson person = proposalPersons.get(i);
            person.setMoveUpAllowed(i > 0
                    && person.getProposalPersonRoleId().equals(proposalPersons.get(i - 1).getProposalPersonRoleId()));
            person.setMoveDownAllowed(i < (proposalPersons.size() - 1)
                    && person.getProposalPersonRoleId().equals(proposalPersons.get(i + 1).getProposalPersonRoleId()));
            if (isSponsorNihMultiplePi() && getKeyPersonnelService().isCoInvestigator(person)) {
                person.setMoveUpAllowed(person.isMoveUpAllowed() 
                        && person.isMultiplePi() == proposalPersons.get(i - 1).isMultiplePi());
                person.setMoveDownAllowed(person.isMoveDownAllowed() 
                        && person.isMultiplePi() == proposalPersons.get(i + 1).isMultiplePi());
            }
        }
    }

    public void setInvestigators(List<ProposalPerson> investigators) {
        this.investigators = investigators;
    }

    public List<ProposalPerson> getInvestigators() {
        return investigators;
    }

    /**
     * Sets the value of proposalPersons
     * 
     * @param argProposalPersons Value to assign to this.proposalPersons
     */
    public void setProposalPersons(List<ProposalPerson> argProposalPersons) {
        this.proposalPersons = argProposalPersons;
    }

    public String getActivityTypeCode() {
        return activityTypeCode;
    }

    public void setActivityTypeCode(String activityTypeCode) {
        this.activityTypeCode = activityTypeCode;
    }

    public String getOwnedByUnitNumber() {
        return ownedByUnitNumber;
    }

    public void setOwnedByUnitNumber(String ownedByUnit) {
        this.ownedByUnitNumber = ownedByUnit;
    }

    public String getProposalTypeCode() {
        return proposalTypeCode;
    }

    public void setProposalTypeCode(String proposalTypeCode) {
        this.proposalTypeCode = proposalTypeCode;
    }

    /**
     * Gets the continuedFrom attribute.
     * 
     * @return Returns the continuedFrom.
     */
    public String getContinuedFrom() {
        return continuedFrom;
    }

    /**
     * Sets the continuedFrom attribute value.
     * 
     * @param continuedFrom The continuedFrom to set.
     */
    public void setContinuedFrom(String continuedFrom) {
        this.continuedFrom = continuedFrom;
    }

    public Date getRequestedEndDateInitial() {
        return requestedEndDateInitial;
    }

    public void setRequestedEndDateInitial(Date requestedEndDateInitial) {
        this.requestedEndDateInitial = requestedEndDateInitial;
    }

    public Date getRequestedStartDateInitial() {
        return requestedStartDateInitial;
    }

    public void setRequestedStartDateInitial(Date requestedStartDateInitial) {
        this.requestedStartDateInitial = requestedStartDateInitial;
    }

    public String getSponsorCode() {
        return sponsorCode;
    }

    public void setSponsorCode(String sponsorCode) {
        this.sponsorCode = sponsorCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProposalNumber() {
        return proposalNumber;
    }

    public void setProposalNumber(String proposalNumber) {
        this.proposalNumber = proposalNumber;
    }

    public String getCurrentAwardNumber() {
        return currentAwardNumber;
    }

    public void setCurrentAwardNumber(String currentAwardNumber) {
        this.currentAwardNumber = currentAwardNumber;
    }

    /**
     * Gets the agencyDivisionCode attribute.
     * 
     * @return Returns the agencyDivisionCode.
     */
    public String getAgencyDivisionCode() {
        return agencyDivisionCode;
    }

    /**
     * Sets the agencyDivisionCode attribute value.
     * 
     * @param agencyDivisionCode The agencyDivisionCode to set.
     */
    public void setAgencyDivisionCode(String agencyDivisionCode) {
        this.agencyDivisionCode = agencyDivisionCode;
    }

    /**
     * Gets the agencyProgramCode attribute.
     * 
     * @return Returns the agencyProgramCode.
     */
    public String getAgencyProgramCode() {
        return agencyProgramCode;
    }

    /**
     * Sets the agencyProgramCode attribute value.
     * 
     * @param agencyProgramCode The agencyProgramCode to set.
     */
    public void setAgencyProgramCode(String agencyProgramCode) {
        this.agencyProgramCode = agencyProgramCode;
    }

    /**
     * Gets the cfdaNumber attribute.
     * 
     * @return Returns the cfdaNumber.
     */
    public String getCfdaNumber() {
        return cfdaNumber;
    }

    /**
     * Sets the cfdaNumber attribute value.
     * 
     * @param cfdaNumber The cfdaNumber to set.
     */
    public void setCfdaNumber(String cfdaNumber) {
        this.cfdaNumber = cfdaNumber;
    }

    /**
     * Gets the deadlineDate attribute.
     * 
     * @return Returns the deadlineDate.
     */
    public Date getDeadlineDate() {
        return deadlineDate;
    }

    /**
     * Sets the deadlineDate attribute value.
     * 
     * @param deadlineDate The deadlineDate to set.
     */
    public void setDeadlineDate(Date deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    /**
     * Gets the deadlineType attribute.
     * 
     * @return Returns the deadlineType.
     */
    public String getDeadlineType() {
        return deadlineType;
    }

    /**
     * Sets the deadlineType attribute value.
     * 
     * @param deadlineType The deadlineType to set.
     */
    public void setDeadlineType(String deadlineType) {
        this.deadlineType = deadlineType;
    }

    /**
     * Gets the noticeOfOpportunityCode attribute.
     * 
     * @return Returns the noticeOfOpportunityCode.
     */
    public String getNoticeOfOpportunityCode() {
        return noticeOfOpportunityCode;
    }

    /**
     * Sets the noticeOfOpportunityCode attribute value.
     * 
     * @param noticeOfOpportunityCode The noticeOfOpportunityCode to set.
     */
    public void setNoticeOfOpportunityCode(String noticeOfOpportunityCode) {
        this.noticeOfOpportunityCode = noticeOfOpportunityCode;
    }

    /**
     * Gets the nsfCode attribute.
     * 
     * @return Returns the nsfCode.
     */
    public String getNsfCode() {
        return nsfCode;
    }

    /**
     * Sets the nsfCode attribute value.
     * 
     * @param nsfCode The nsfCode to set.
     */
    public void setNsfCode(String nsfCode) {
        this.nsfCode = nsfCode;
    }

    /**
     * Gets the primeSponsorCode attribute.
     * 
     * @return Returns the primeSponsorCode.
     */
    public String getPrimeSponsorCode() {
        return primeSponsorCode;
    }

    /**
     * Sets the primeSponsorCode attribute value.
     * 
     * @param primeSponsorCode The primeSponsorCode to set.
     */
    public void setPrimeSponsorCode(String primeSponsorCode) {
        this.primeSponsorCode = primeSponsorCode;
    }

    /**
     * Gets the programAnnouncementNumber attribute.
     * 
     * @return Returns the programAnnouncementNumber.
     */
    public String getProgramAnnouncementNumber() {
        return programAnnouncementNumber;
    }

    /**
     * Sets the programAnnouncementNumber attribute value.
     * 
     * @param programAnnouncementNumber The programAnnouncementNumber to set.
     */
    public void setProgramAnnouncementNumber(String programAnnouncementNumber) {
        this.programAnnouncementNumber = programAnnouncementNumber;
    }

    /**
     * Gets the programAnnouncementTitle attribute.
     * 
     * @return Returns the programAnnouncementTitle.
     */
    public String getProgramAnnouncementTitle() {
        return programAnnouncementTitle;
    }

    /**
     * Sets the programAnnouncementTitle attribute value.
     * 
     * @param programAnnouncementTitle The programAnnouncementTitle to set.
     */
    public void setProgramAnnouncementTitle(String programAnnouncementTitle) {
        this.programAnnouncementTitle = programAnnouncementTitle;
    }

    /**
     * Gets the sponsorProposalNumber attribute.
     * 
     * @return Returns the sponsorProposalNumber.
     */
    public String getSponsorProposalNumber() {
        return sponsorProposalNumber;
    }

    /**
     * Sets the sponsorProposalNumber attribute value.
     * 
     * @param sponsorProposalNumber The sponsorProposalNumber to set.
     */
    public void setSponsorProposalNumber(String sponsorProposalNumber) {
        this.sponsorProposalNumber = sponsorProposalNumber;
    }

    /**
     * Gets the subcontracts attribute.
     * 
     * @return Returns the subcontracts.
     */
    public Boolean getSubcontracts() {
        return subcontracts;
    }

    /**
     * Sets the subcontracts attribute value.
     * 
     * @param subcontracts The subcontracts to set.
     */
    public void setSubcontracts(Boolean subcontracts) {
        this.subcontracts = subcontracts;
    }

    public String getMailBy() {
        return mailBy;
    }

    public void setMailBy(String mailBy) {
        this.mailBy = mailBy;
    }

    public String getMailType() {
        return mailType;
    }

    public void setMailType(String mailType) {
        this.mailType = mailType;
    }

    public String getMailAccountNumber() {
        return mailAccountNumber;
    }

    public void setMailAccountNumber(String mailAccountNumber) {
        this.mailAccountNumber = mailAccountNumber;
    }

    public String getMailDescription() {
        return mailDescription;
    }

    public void setMailDescription(String mailDescription) {
        this.mailDescription = mailDescription;
    }

    public Integer getMailingAddressId() {
        return mailingAddressId;
    }

    public void setMailingAddressId(Integer mailingAddressId) {
        this.mailingAddressId = mailingAddressId;
    }

    public String getNumberOfCopies() {
        return numberOfCopies;
    }

    public void setNumberOfCopies(String numberOfCopies) {
        this.numberOfCopies = numberOfCopies;
    }

    public void setApplicantOrganizationId(String applicantOrganizationId) {
        ProposalSite applicantOrganization = getApplicantOrganization();
        applicantOrganization.setOrganizationId(applicantOrganizationId);
        setApplicantOrganization(applicantOrganization);
    }

    public void setPerformingOrganizationId(String performingOrganizationId) {
        ProposalSite performingOrganization = getPerformingOrganization();
        performingOrganization.setOrganizationId(performingOrganizationId);
        setPerformingOrganization(performingOrganization);
    }

    public void setApplicantOrganization(ProposalSite applicantOrganization) {
        setProposalSiteForType(applicantOrganization, ProposalSite.PROPOSAL_SITE_APPLICANT_ORGANIZATION);
    }

    /**
     * This method sets the Applicant Organization based on a Organization object.
     * @param organization
     * @see setApplicantOrganization(ProposalSite)
     */
    public void setApplicantOrgFromOrganization(Organization organization) {
        if (organization == null) {
            setApplicantOrganization((ProposalSite) null);
        }
        else {
            ProposalSite applicantSite = new ProposalSite();
            applicantSite.setOrganization(organization);
            setApplicantOrganization(applicantSite);
        }
    }

    public ProposalSite getApplicantOrganization() {
        ProposalSite applicant = getProposalSiteForType(ProposalSite.PROPOSAL_SITE_APPLICANT_ORGANIZATION);
        if (applicant != null) applicant.setRolodex(applicant.getOrganization() == null ? null : applicant.getOrganization().getRolodex());
        return applicant;
    }

    public void setPerformingOrganization(ProposalSite performingOrganization) {
        setProposalSiteForType(performingOrganization, ProposalSite.PROPOSAL_SITE_PERFORMING_ORGANIZATION);
    }

    /**
     * This method sets the Performing Organization based on a Organization object.
     * @param organization
     * @see setPerformingOrgFromOrganization(ProposalSite)
     */
    public void setPerformingOrgFromOrganization(Organization organization) {
        if (organization == null) {
            setPerformingOrganization((ProposalSite) null);
        }
        else {
            ProposalSite performingSite = new ProposalSite();
            performingSite.setOrganization(organization);
            setPerformingOrganization(performingSite);
        }
    }

    public ProposalSite getPerformingOrganization() {
        ProposalSite performingOrganization = getProposalSiteForType(ProposalSite.PROPOSAL_SITE_PERFORMING_ORGANIZATION);
        performingOrganization.refreshReferenceObject("rolodex");
        if (performingOrganization.getRolodex() == null && performingOrganization.getOrganization() != null) {
            performingOrganization.setRolodex(performingOrganization.getOrganization().getRolodex());
        }
        return performingOrganization;
    }

    public void addProposalSite(ProposalSite proposalSite) {
        proposalSites.add(proposalSite);
    }

    /**
     * This method replaces one or more Proposal Sites of a given type with another Proposal Site.
     * The new Proposal Site's type is set to the locationType parameter.
     * @param proposalSite
     * @param locationType
     */
    private void setProposalSiteForType(ProposalSite proposalSite, int locationType) {
        deleteAllProposalSitesOfType(locationType);
        proposalSite.setLocationTypeCode(locationType);   // make sure the location type is set
        addProposalSite(proposalSite);
    }

    /**
     * This method replaces all Proposal Sites of a given type with a new list of Proposal Sites.
     * Each new Proposal Site's types are set to the locationType parameter.
     * @param proposalSites
     * @param locationType
     */
    private void setProposalSitesForType(List<ProposalSite> proposalSites, int locationType) {
        deleteAllProposalSitesOfType(locationType);
        for (ProposalSite proposalSite : proposalSites) {
            proposalSite.setLocationTypeCode(locationType);   // make sure the location type is set
        }
        proposalSites.addAll(proposalSites);
    }

    private void deleteAllProposalSitesOfType(int locationType) {
        for (int i = proposalSites.size() - 1; i >= 0; i--) {
            ProposalSite proposalSite = proposalSites.get(i);
            if (proposalSite.getLocationTypeCode() == locationType) {
                proposalSites.remove(i);
            }
        }
    }

    private ProposalSite getProposalSiteForType(int locationType) {
        List<ProposalSite> matchingSites = getProposalSitesForType(locationType);
        if (matchingSites.isEmpty()) {
            return null;
        }
        else {
            return matchingSites.get(0);
        }
    }

    private List<ProposalSite> getProposalSitesForType(int locationType) {
        ArrayList<ProposalSite> matchingSites = new ArrayList<ProposalSite>();
        for (ProposalSite proposalSite : proposalSites) {
            if (proposalSite.getLocationTypeCode() == locationType) {
                matchingSites.add(proposalSite);
            }
        }
        return matchingSites;
    }

    public void setPerformanceSites(List<ProposalSite> performanceSites) {
        setProposalSitesForType(performanceSites, ProposalSite.PROPOSAL_SITE_PERFORMANCE_SITE);
    }

    public List<ProposalSite> getPerformanceSites() {
        return getProposalSitesForType(ProposalSite.PROPOSAL_SITE_PERFORMANCE_SITE);
    }

    public void addPerformanceSite(ProposalSite performanceSite) {
        performanceSite.setLocationTypeCode(ProposalSite.PROPOSAL_SITE_PERFORMANCE_SITE);   // make sure the location type is set
        addProposalSite(performanceSite);
    }

    public void removePerformanceSite(int index) {
        removeProposalSiteOfType(ProposalSite.PROPOSAL_SITE_PERFORMANCE_SITE, index);
    }

    public void clearPerformanceSiteAddress(int index) {
        ProposalSite performanceSite = getPerformanceSites().get(index);
        performanceSite.setOrganization(new Organization());
        performanceSite.setOrganizationId("");
        performanceSite.setRolodex(new Rolodex());
        performanceSite.setRolodexId(null);
    }

    public void setOtherOrganizations(List<ProposalSite> otherOrganizations) {
        setProposalSitesForType(otherOrganizations, ProposalSite.PROPOSAL_SITE_OTHER_ORGANIZATION);
    }

    public List<ProposalSite> getOtherOrganizations() {
        return getProposalSitesForType(ProposalSite.PROPOSAL_SITE_OTHER_ORGANIZATION);
    }

    public void addOtherOrganization(ProposalSite otherOrganization) {
        otherOrganization.setLocationTypeCode(ProposalSite.PROPOSAL_SITE_OTHER_ORGANIZATION);   // make sure the location type is set
        addProposalSite(otherOrganization);
    }

    public void removeOtherOrganization(int index) {
        removeProposalSiteOfType(ProposalSite.PROPOSAL_SITE_OTHER_ORGANIZATION, index);
    }

    /**
     * Among all ProposalSites of a given location type, this method deletes the index-th
     * one of them.
     * If, for example, there is a total of eight Proposal Sites, of which five are Performance Sites,
     * then calling this method with the location type ProposalSite.PROPOSAL_SITE_PERFORMANCE_SITE
     * and index 2 will delete the second Performance Site (not the second Proposal Site overall).
     * @param locationType
     * @param index
     */
    private void removeProposalSiteOfType(int locationType, int index) {
        for (ProposalSite proposalSite : getProposalSitesForType(locationType)) {
            if (proposalSite.getLocationTypeCode() == locationType) {
                index--;
                if (index < 0) {
                    proposalSites.remove(proposalSite);
                    break;
                }
            }
        }
    }

    public void setProposalSites(List<ProposalSite> proposalSites) {
        this.proposalSites = proposalSites;
    }

    /**
     * This method returns all proposal sites associated with the document
     * @return
     */
    public List<ProposalSite> getProposalSites() {
        return proposalSites;
    }

    public Rolodex getRolodex() {
        return rolodex;
    }

    public void setRolodex(Rolodex rolodex) {
        this.rolodex = rolodex;
    }

    public void setPropScienceKeywords(List<PropScienceKeyword> propScienceKeywords) {
        this.propScienceKeywords = propScienceKeywords;
    }

    public List<PropScienceKeyword> getPropScienceKeywords() {
        return propScienceKeywords;
    }

    public void addPropScienceKeyword(PropScienceKeyword propScienceKeyword) {
        getPropScienceKeywords().add(propScienceKeyword);
    }

    public String getNewScienceKeywordCode() {
        return newScienceKeywordCode;
    }

    public void setNewScienceKeywordCode(String newScienceKeywordCode) {
        this.newScienceKeywordCode = newScienceKeywordCode;
    }

    public String getNewDescription() {
        return newDescription;
    }

    public void setNewDescription(String newDescription) {
        this.newDescription = newDescription;
    }

    public String getDefaultNewDescription() {
        return "(select)";
    }

    public List<ProposalSpecialReview> getPropSpecialReviews() {
        return propSpecialReviews;
    }

    public void setPropSpecialReviews(List<ProposalSpecialReview> propSpecialReviews) {
        this.propSpecialReviews = propSpecialReviews;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List buildListOfDeletionAwareLists() {
        List managedLists = super.buildListOfDeletionAwareLists();
        List<ProposalPersonUnit> units = new ArrayList<ProposalPersonUnit>();
        List<ProposalPersonDegree> degrees = new ArrayList<ProposalPersonDegree>();
        for (ProposalPerson person : getProposalPersons()) {
            units.addAll(person.getUnits());
            degrees.addAll(person.getProposalPersonDegrees());
        }
        managedLists.add(units);
        managedLists.add(degrees);
        managedLists.add(getProposalSites());
        List<CongressionalDistrict> congressionalDistricts = new ArrayList<CongressionalDistrict>();
        for (ProposalSite proposalSite : getProposalSites()) {
            congressionalDistricts.addAll(proposalSite.getCongressionalDistricts());
        }
        managedLists.add(congressionalDistricts);
        List<ProposalSpecialReviewExemption> specialReviewExemptions = new ArrayList<ProposalSpecialReviewExemption>();
        for (ProposalSpecialReview specialReview : getPropSpecialReviews()) {
            specialReviewExemptions.addAll(specialReview.getSpecialReviewExemptions());
        }
        managedLists.add(specialReviewExemptions);
        managedLists.add(getPropSpecialReviews());
        managedLists.add(getProposalPersons());
        managedLists.add(getPropScienceKeywords());
        managedLists.add(getProposalAbstracts());
        managedLists.add(getPropPersonBios());
        managedLists.add(getS2sAppSubmission());
        /*
         * This is really bogus, but OJB doesn't delete a BO component from the database after it is set to null, i.e. the S2S
         * Opportunity. It is the same issue as deleting items from a list. To get around OJB's stupidity, we will construct a list
         * which will contain the Opportunity if it is present. The Kuali Core logic will then force the deletion.
         */
        List<S2sOpportunity> opportunities = new ArrayList<S2sOpportunity>();
        S2sOpportunity opportunity = this.getS2sOpportunity();
        if (opportunity != null) {
            opportunities.add(opportunity);
            s2sOppForms = opportunity.getS2sOppForms();
        }
        if (s2sOppForms != null && s2sOppForms.size() > 0) {
            managedLists.add(s2sOppForms);
        }
        else {
            managedLists.add(new ArrayList<S2sOppForms>());
        }
        managedLists.add(opportunities);
        return managedLists;
    }

    /**
     * Gets the sponsor attribute.
     * 
     * @return Returns the sponsor.
     */
    public Sponsor getSponsor() {
        return sponsor;
    }

    /**
     * Sets the sponsor attribute value.
     * 
     * @param sponsor The sponsor to set.
     */
    public void setSponsor(Sponsor sponsor) {
        this.sponsor = sponsor;
    }

    public String getSponsorName() {
        if (getSponsor() != null) {
            return getSponsor().getSponsorName();
        }
        return null;
    }

    public String getOwnedByUnitName() {
        Unit unit = getOwnedByUnit();
        return unit != null ? unit.getUnitName() : null;
    }

    /**
     * Utility method to get person in ProposalPersons with the PI role
     * 
     * @return ProposalPerson
     */
    public ProposalPerson getPrincipalInvestigator() {
        ProposalPerson principalInvestigator = null;
        for (ProposalPerson person : proposalPersons) {
            if (StringUtils.equals(person.getProposalPersonRoleId(), Constants.PRINCIPAL_INVESTIGATOR_ROLE)) {
                principalInvestigator = person;
                break;
            }
        }
        return principalInvestigator;
    }

    public String getPrincipalInvestigatorName() {
        ProposalPerson pi = getPrincipalInvestigator();
        return pi != null ? pi.getFullName() : null;
    }

    public void setNextProposalPersonNumber(Integer n) {
        nextProposalPersonNumber = n;
    }

    public Integer getNextProposalPersonNumber() {
        return nextProposalPersonNumber;
    }

    /**
     * Adds a new proposal person to the collection in the document
     * 
     * @param p person to add
     */
    public void addProposalPerson(ProposalPerson p) {
        p.setProposalPersonNumber(this.getProposalDocument().getDocumentNextValue(Constants.PROPOSAL_PERSON_NUMBER));
        if (p.getProposalPersonExtendedAttributes() != null && p.getProposalPersonExtendedAttributes().getProposalPersonNumber() == null) {
            p.getProposalPersonExtendedAttributes().setProposalPersonNumber(p.getProposalPersonNumber());
        }
        p.setDevelopmentProposal(this);
        getProposalPersons().add(p);
    }

    public ProposalPerson getProposalPerson(int index) {
        while (getProposalPersons().size() <= index) {
            getProposalPersons().add(new ProposalPerson());
        }
        return getProposalPersons().get(index);
    }

    /**
     * Get the list of Proposal Attachments (Narratives) for this Proposal.
     * 
     * @return the proposal's list of narratives.
     */
    public List<Narrative> getNarratives() {
        return narratives;
    }

    /**
     * Set the list of Proposal Attachments (Narratives) for this Proposal.
     * 
     * @param narratives the proposal's new list of narratives.
     */
    public void setNarratives(List<Narrative> narratives) {
        this.narratives = narratives;
    }

    /**
     * Get the list of Abstracts for this Proposal.
     * 
     * @return the proposal's list of abstracts.
     */
    public List<ProposalAbstract> getProposalAbstracts() {
        return proposalAbstracts;
    }

    /**
     * Set the list of Abstracts for this Proposal.
     * 
     * @param proposalAbstracts the proposal's new list of abstracts.
     */
    public void setProposalAbstracts(List<ProposalAbstract> proposalAbstracts) {
        this.proposalAbstracts = proposalAbstracts;
    }

    public List<Narrative> getInstituteAttachments() {
        return instituteAttachments;
    }

    public void setInstituteAttachments(List<Narrative> instituteAttachments) {
        this.instituteAttachments = instituteAttachments;
    }

    public List<ProposalPersonBiography> getPropPersonBios() {
        return propPersonBios;
    }

    public void setPropPersonBios(List<ProposalPersonBiography> propPersonBios) {
        this.propPersonBios = propPersonBios;
    }

    /**
     * Gets the ownedByUnit attribute.
     * 
     * @return Returns the ownedByUnit.
     */
    public Unit getOwnedByUnit() {
        return ownedByUnit;
    }

    /**
     * Sets the ownedByUnit attribute value.
     * 
     * @param ownedByUnit The ownedByUnit to set.
     */
    public void setOwnedByUnit(Unit ownedByUnit) {
        this.ownedByUnit = ownedByUnit;
    }

    /**
     * 
     * This method adds new personnel attachment to biography and biography attachment bo with proper set up.
     * 
     * @param proposalPersonBiography
     * @throws Exception
     */
    public void addProposalPersonBiography(ProposalPersonBiography proposalPersonBiography) {
        getProposalPersonBiographyService().addProposalPersonBiography(this.getProposalDocument(), proposalPersonBiography);
    }

    /**
     * 
     * This method delete the attachment for the deleted personnel.
     * 
     * @param proposalPerson
     * @throws Exception
     */
    public void removePersonnelAttachmentForDeletedPerson(ProposalPerson proposalPerson) throws Exception {
        getProposalPersonBiographyService().removePersonnelAttachmentForDeletedPerson(this.getProposalDocument(), proposalPerson);
    }

    /**
     * 
     * Method to delete a personnel attachment from personnel attachment list
     * 
     * @param narrative
     */
    public void deleteProposalPersonBiography(int lineToDelete) {
        getProposalPersonBiographyService().deleteProposalPersonBiography(this.getProposalDocument(), lineToDelete);
    }

    /**
     * 
     * Method to add a new narrative to narratives list
     * 
     * @param narrative
     */
    public void addNarrative(Narrative narrative) {
        getNarrativeService().addNarrative(this.getProposalDocument(), narrative);
    }

    /**
     * 
     * Method to delete a narrative from narratives list
     * 
     * @param narrative
     */
    public void deleteProposalAttachment(int lineToDelete) {
        getNarrativeService().deleteProposalAttachment(this.getProposalDocument(), lineToDelete);
    }

    /**
     * 
     * Method to add a new institute attachment to institute attachment list
     * 
     * @param narrative
     */
    public void addInstituteAttachment(Narrative narrative) {
        getNarrativeService().addInstituteAttachment(this.getProposalDocument(), narrative);
    }

    /**
     * 
     * Method to delete a narrative from narratives list
     * 
     * @param narrative
     */
    public void deleteInstitutionalAttachment(int lineToDelete) {
        getNarrativeService().deleteInstitutionalAttachment(this.getProposalDocument(), lineToDelete);
    }

    public void populatePersonNameForNarrativeUserRights(int lineNumber) {
        if (!getNarratives().isEmpty()) {
            Narrative narrative = getNarratives().get(lineNumber);
            getNarrativeService().populatePersonNameForNarrativeUserRights(this.getProposalDocument(), narrative);
        }
    }

    public void populatePersonNameForInstituteAttachmentUserRights(int lineNumber) {
        if (!getInstituteAttachments().isEmpty()) {
            Narrative narrative = getInstituteAttachments().get(lineNumber);
            getNarrativeService().populatePersonNameForNarrativeUserRights(this.getProposalDocument(), narrative);
        }
    }

    public void replaceAttachment(int selectedLine) {
        Narrative narrative = getNarratives().get(selectedLine);
        getNarrativeService().replaceAttachment(narrative);
    }

    public void replaceInstituteAttachment(int selectedLine) {
        Narrative narrative = getInstituteAttachments().get(selectedLine);
        getNarrativeService().replaceAttachment(narrative);
    }

    public void populateNarrativeRightsForLoggedinUser() {
        getNarrativeService().populateNarrativeRightsForLoggedinUser(this.getProposalDocument());
    }

    /**
     * Gets the narrativeService attribute.
     * 
     * @return Returns the narrativeService.
     */
    public NarrativeService getNarrativeService() {
        if (narrativeService == null) {
            narrativeService = KraServiceLocator.getService(NarrativeService.class);
        }
        return narrativeService;
    }

    /**
     * Sets the narrativeService attribute value.
     * 
     * @param narrativeService The narrativeService to set.`
     */
    public void setNarrativeService(NarrativeService narrativeService) {
        this.narrativeService = narrativeService;
    }

    public ProposalPersonBiographyService getProposalPersonBiographyService() {
        if (proposalPersonBiographyService == null) {
            proposalPersonBiographyService = KraServiceLocator.getService(ProposalPersonBiographyService.class);
        }
        return proposalPersonBiographyService;
    }

    public void setProposalPersonBiographyService(ProposalPersonBiographyService proposalPersonBiographyService) {
        this.proposalPersonBiographyService = proposalPersonBiographyService;
    }

    /**
     * Accessor method to locally store credit types
     * 
     * @param creditTypes a <code>{@link Collection}</code> of credit types
     */
    public void setInvestigatorCreditTypes(Collection<InvestigatorCreditType> creditTypes) {
        investigatorCreditTypes = creditTypes;
    }

    /**
     * Accessor method to locally store credit types
     * 
     * @return <code>{@link Collection}</code> of credit types
     */
    public Collection<InvestigatorCreditType> getInvestigatorCreditTypes() {
        return investigatorCreditTypes;
    }

    public List<ProposalYnq> getProposalYnqs() {
        Collections.sort(proposalYnqs);
        return proposalYnqs;
    }

    public void setProposalYnqs(List<ProposalYnq> proposalYnqs) {
        this.proposalYnqs = proposalYnqs;
    }

    public List<YnqGroupName> getYnqGroupNames() {
        if (ynqGroupNames.isEmpty()) {
            getYnqService().populateProposalQuestions(this.proposalYnqs, this.ynqGroupNames, this.getProposalDocument());
        }
        Collections.sort(ynqGroupNames);
        return ynqGroupNames;
    }

    public void setYnqGroupNames(List<YnqGroupName> ynqGroupNames) {
        this.ynqGroupNames = ynqGroupNames;
    }

    // getters to auto-grow list to prevent arrayindexoutofbound exception  
    // also used in JSP  
    /**
     * Gets index i from the propSpecialReviews list.
     * 
     * @param index
     * @return Question at index i
     */
    public ProposalSpecialReview getPropSpecialReview(int index) {
        while (getPropSpecialReviews().size() <= index) {
            getPropSpecialReviews().add(new ProposalSpecialReview());
        }
        return getPropSpecialReviews().get(index);
    }

    /**
     * Gets index i from the propScienceKeywords list.
     * 
     * @param index
     * @return Question at index i
     */
    public PropScienceKeyword getPropScienceKeyword(int index) {
        while (getPropScienceKeywords().size() <= index) {
            getPropScienceKeywords().add(new PropScienceKeyword());
        }
        return getPropScienceKeywords().get(index);
    }

    /**
     * Gets index i from the narratives list.
     * 
     * @param index
     * @return Question at index i
     */
    public Narrative getNarrative(int index) {
        while (getNarratives().size() <= index) {
            getNarratives().add(new Narrative());
        }
        return getNarratives().get(index);
    }

    /**
     * Gets index i from the institute attachment list.
     * 
     * @param index
     * @return Question at index i
     */
    public Narrative getInstituteAttachment(int index) {
        while (getInstituteAttachments().size() <= index) {
            getInstituteAttachments().add(new Narrative());
        }
        return getInstituteAttachments().get(index);
    }

    /**
     * Gets index i from the proposalAbstracts list.
     * 
     * @param index
     * @return Question at index i
     */
    public ProposalAbstract getProposalAbstract(int index) {
        while (getProposalAbstracts().size() <= index) {
            getProposalAbstracts().add(new ProposalAbstract());
        }
        return getProposalAbstracts().get(index);
    }

    /**
     * Gets index i from the proposalAbstracts list.
     * 
     * @param index
     * @return Question at index i
     */
    public ProposalPersonBiography getPropPersonBio(int index) {
        while (getPropPersonBios().size() <= index) {
            getPropPersonBios().add(new ProposalPersonBiography());
        }
        return getPropPersonBios().get(index);
    }

    /**
     * Gets index i from the investigators list.
     * 
     * @param index
     * @return Question at index i
     */
    public ProposalPerson getInvestigator(int index) {
        while (getInvestigators().size() <= index) {
            getInvestigators().add(new ProposalPerson());
        }
        return getInvestigators().get(index);
    }

    /**
     * Gets index i from the proposalYnqs list.
     * 
     * @param index
     * @return Question at index i
     */
    public ProposalYnq getProposalYnq(int index) {
        while (getProposalYnqs().size() <= index) {
            getProposalYnqs().add(new ProposalYnq());
        }
        return getProposalYnqs().get(index);
    }

    /**
     * Gets index i from the ynqGroupNames list.
     * 
     * @param index
     * @return Question at index i
     */
    public YnqGroupName getYnqGroupName(int index) {
        while (getYnqGroupNames().size() <= index) {
            getYnqGroupNames().add(new YnqGroupName());
        }
        return getYnqGroupNames().get(index);
    }

    /**
     * Gets the ynqService attribute.
     * 
     * @return Returns the ynqService.
     */
    public YnqService getYnqService() {
        return KraServiceLocator.getService(YnqService.class);
    }

    public String getBudgetStatus() {
        return budgetStatus;
    }

    public void setBudgetStatus(String budgetStatus) {
        this.budgetStatus = budgetStatus;
    }

    //    public List<BudgetDocumentVersion> getBudgetDocumentVersions() {  
    //        return getProposalDocument().getBudgetDocumentVersions();  
    //    }  
    //  
    //    public void setBudgetDocumentVersions(List<BudgetDocumentVersion> budgetDocumentVersions) {  
    //        this.getProposalDocument().setBudgetDocumentVersions(budgetDocumentVersions);  
    //    }  
    /**
     * This method gets the next budget version number for this proposal. We can't use documentNextVersionNumber because that
     * requires a save and we don't want to save the proposal development document before forwarding to the budget document.
     * 
     * @return Integer
     */
    //    public Integer getNextBudgetVersionNumber() {  
    //        List<BudgetDocumentVersion> versions = getBudgetDocumentVersions();  
    //        if (versions.isEmpty()) {  
    //            return 1;  
    //        }  
    //        Collections.sort(versions);  
    //        BudgetDocumentVersion lastVersion = versions.get(versions.size() - 1);  
    //        return lastVersion.getBudgetVersionOverview().getBudgetVersionNumber() + 1;  
    //    }  
    /**
     * Sets the budgetStatusDescription attribute value.
     * @param budgetStatusDescription The budgetStatusDescription to set.
     */
    public void setBudgetStatusDescription(String budgetStatusDescription) {
        this.budgetStatusDescription = budgetStatusDescription;
    }

    /**
     * Gets the budgetStatusDescription attribute. 
     * @return Returns the budgetStatusDescription.
     */
    public String getBudgetStatusDescription() {
        if (StringUtils.isEmpty(budgetStatusDescription)) {
            KraServiceLocator.getService(ProposalStatusService.class).loadBudgetStatus(this);
        }
        return budgetStatusDescription;
    }

    /**
     * Gets index i from the budget versions list.
     * 
     * @param index
     * @return BudgetVersionOverview at index i
     */
    //    public BudgetDocumentVersion getBudgetDocumentVersion(int index) {  
    //        while (getBudgetDocumentVersions().size() <= index) {  
    //            getBudgetDocumentVersions().add(new BudgetDocumentVersion());  
    //        }  
    //        return getBudgetDocumentVersions().get(index);  
    //    }  
    public List<S2sOppForms> getS2sOppForms() {
        return s2sOppForms;
    }

    public List<S2sOppForms> getSelectedS2sOppForms() {
        List<S2sOppForms> aList = new ArrayList<S2sOppForms>();
        for (S2sOppForms oppForm : s2sOppForms) {
            if (Boolean.TRUE.equals(oppForm.getSelectToPrint())) {
                aList.add(oppForm);
            }
        }
        return aList;
    }

    public void setS2sOppForms(List<S2sOppForms> oppForms) {
        s2sOppForms = oppForms;
    }

    public void setS2sOpportunity(S2sOpportunity s2sOpportunity) {
        this.s2sOpportunity = s2sOpportunity;
    }

    public S2sOpportunity getS2sOpportunity() {
        return s2sOpportunity;
    }

    public ProposalPerson getProposalEmployee(String personId) {
        for (ProposalPerson proposalPerson : getProposalPersons()) {
            if (personId.equals(proposalPerson.getPersonId())) {
                return proposalPerson;
            }
        }
        return null;
    }

    public ProposalPerson getProposalNonEmployee(Integer rolodexId) {
        for (ProposalPerson proposalPerson : getProposalPersons()) {
            if (rolodexId.equals(proposalPerson.getRolodexId())) {
                return proposalPerson;
            }
        }
        return null;
    }

    public ContactRole getProposalEmployeeRole(String personId) {
        ProposalPerson principalInvestigator = getPrincipalInvestigator();
        if (principalInvestigator != null && personId.equals(principalInvestigator.getPersonId())) {
            return principalInvestigator.getRole();
        }
        for (ProposalPerson proposalPerson : getInvestigators()) {
            if (personId.equals(proposalPerson.getPersonId())) {
                return proposalPerson.getRole();
            }
        }
        for (ProposalPerson proposalPerson : getProposalPersons()) {
            if (personId.equals(proposalPerson.getPersonId())) {
                return proposalPerson.getRole();
            }
        }
        return null;
    }

    public ContactRole getProposalNonEmployeeRole(Integer rolodexId) {
        ProposalPerson principalInvestigator = getPrincipalInvestigator();
        if (principalInvestigator != null && rolodexId.equals(principalInvestigator.getRolodexId())) {
            return principalInvestigator.getRole();
        }
        for (ProposalPerson proposalPerson : getInvestigators()) {
            if (rolodexId.equals(proposalPerson.getRolodexId())) {
                return proposalPerson.getRole();
            }
        }
        for (ProposalPerson proposalPerson : getProposalPersons()) {
            if (rolodexId.equals(proposalPerson.getRolodexId())) {
                return proposalPerson.getRole();
            }
        }
        return null;
    }

    //    public BudgetDocumentVersion getFinalBudgetVersion() {  
    //        for (BudgetDocumentVersion version : this.getBudgetDocumentVersions()) {  
    //            if (version.getBudgetVersionOverview().isFinalVersionFlag()) {  
    //                return version;  
    //            }  
    //        }  
    //        return null;  
    //    }  
    //    public void addNewBudgetVersion(BudgetDocument budgetDocument, String name, boolean isDescriptionUpdatable) {  
    //        BudgetDocumentVersion budgetDocumentVersion = new BudgetDocumentVersion();  
    //        budgetDocumentVersion.setParentDocumentKey(getProposalDocument().getDocumentNumber());  
    //        BudgetVersionOverview budgetVersionOverview = budgetDocumentVersion.getBudgetVersionOverview();  
    //        budgetVersionOverview.setDocumentNumber(budgetDocument.getDocumentNumber());  
    ////        budget.setProposalNumber(this.getProposalNumber());  
    //        budgetVersionOverview.setDocumentDescription(name);  
    //        budgetVersionOverview.setBudgetVersionNumber(budgetVersionOverview.getBudgetVersionNumber());  
    //        budgetVersionOverview.setStartDate(budgetVersionOverview.getStartDate());  
    //        budgetVersionOverview.setEndDate(budgetVersionOverview.getEndDate());  
    //        budgetVersionOverview.setOhRateTypeCode(budgetVersionOverview.getOhRateTypeCode());  
    //        budgetVersionOverview.setOhRateClassCode(budgetVersionOverview.getOhRateClassCode());  
    ////        budgetVersionOverview.setVersionNumber(budgetDocument.getVersionNumber());  
    //        budgetVersionOverview.setDescriptionUpdatable(isDescriptionUpdatable);  
    //  
    //        String budgetStatusIncompleteCode = KraServiceLocator.getService(ParameterService.class).getParameterValueAsString(  
    //                Constants.MODULE_NAMESPACE_BUDGET, ParameterConstants.DOCUMENT_COMPONENT, Constants.BUDGET_STATUS_INCOMPLETE_CODE);  
    //        budgetVersionOverview.setBudgetStatus(budgetStatusIncompleteCode);  
    //  
    //        this.getBudgetDocumentVersions().add(budgetDocumentVersion);  
    //    }  
    /**
     * Gets the creationStatusCode attribute.
     * 
     * @return Returns the creationStatusCode.
     */
    public String getCreationStatusCode() {
        return creationStatusCode;
    }

    /**
     * Sets the creationStatusCode attribute value.
     * 
     * @param creationStatusCode The creationStatusCode to set.
     */
    public void setCreationStatusCode(String creationStatusCode) {
        this.creationStatusCode = creationStatusCode;
    }

    public final ActivityType getActivityType() {
        return activityType;
    }

    public final void setActivityType(ActivityType activityType) {
        this.activityType = activityType;
    }

    public boolean isProposalComplete() {
        String budgetStatusCompleteCode = this.getParameterService().getParameterValueAsString(
               BudgetDocument.class, Constants.BUDGET_STATUS_COMPLETE_CODE);

        if (this.getBudgetStatus() != null && budgetStatusCompleteCode != null
                && this.getBudgetStatus().equals(budgetStatusCompleteCode)) {
            return true;
        }
        return false;
    }

    public boolean isParentProposalComplete() {
        boolean retval = false;
        if (isChild()) {
            try {
                DevelopmentProposal parent = getProposalHierarchyService().lookupParent(this);
                KraServiceLocator.getService(ProposalStatusService.class).loadBudgetStatus(parent);
                retval = parent.isProposalComplete();
            }
            catch (ProposalHierarchyException x) {
                // this should never happen  
                throw new IllegalStateException(x);
            }
        }
        return retval;
    }

    //    public int getNumberOfVersions() {  
    //        return this.getBudgetDocumentVersions().size();  
    //    }  
    public Map<String, String> getNihDescription() {
        return nihDescription;
    }

    public void setNihDescription(Map<String, String> nihDescription) {
        this.nihDescription = nihDescription;
    }

    /**
     * This method returns true if the sponsor is in the NIH Multiple PI hierarchy.
     * @return
     */
    public boolean isSponsorNihMultiplePi() {
        return sponsorNihMultiplePi;
    }

    public void setSponsorNihMultiplePi(boolean sponsorNihMultiplePi) {
        this.sponsorNihMultiplePi = sponsorNihMultiplePi;
    }

    /**
     * This method returns true if the sponsor is in the NIH Other Significant Contributor hierarchy.
     * @return
     */
    public boolean isSponsorNihOsc() {
        return sponsorNihOsc;
    }

    public void setSponsorNihOsc(boolean sponsorNihOsc) {
        this.sponsorNihOsc = sponsorNihOsc;
    }

    public List<S2sAppSubmission> getS2sAppSubmission() {
        return s2sAppSubmission;
    }

    public void setS2sAppSubmission(List<S2sAppSubmission> appSubmission) {
        s2sAppSubmission = appSubmission;
    }

    public List<ProposalChangedData> getProposalChangedDataList() {
        return proposalChangedDataList;
    }

    public void setProposalChangedDataList(List<ProposalChangedData> proposalChangedDataList) {
        this.proposalChangedDataList = proposalChangedDataList;
    }

    public Map<String, List<ProposalChangedData>> getProposalChangeHistory() {
        return proposalChangeHistory;
    }

    public void setProposalChangeHistory(Map<String, List<ProposalChangedData>> proposalChangeHistory) {
        this.proposalChangeHistory = proposalChangeHistory;
    }

    public void setSubmitFlag(Boolean submitFlag) {
        this.submitFlag = submitFlag;
    }

    public Boolean getSubmitFlag() {
        return this.submitFlag;
    }

    public String getProposalStateTypeCode() {
        return proposalStateTypeCode;
    }

    public void setProposalStateTypeCode(String proposalStateTypeCode) {
        this.proposalStateTypeCode = proposalStateTypeCode;
        if (proposalStateTypeCode == null) {
            this.proposalState = null;
        }
        else {
            try {
                refreshReferenceObject("proposalState");
            }
            catch (NullPointerException ex) {
                // do nothing: this will happen when
                // creating a proposal from a unit test
                // without access to a database.
            }
        }
    }

    public ProposalState getProposalState() {
        return proposalState;
    }

    public ProposalDevelopmentDocument getProposalDocument() {
        return proposalDocument;
    }

    public void setProposalDocument(ProposalDevelopmentDocument proposalDocument) {
        this.proposalDocument = proposalDocument;
    }

    public void updateProposalChangeHistory() {
        proposalChangeHistory = new TreeMap<String, List<ProposalChangedData>>();
        // Arranging Proposal Change History  
        if (CollectionUtils.isNotEmpty(this.getProposalChangedDataList())) {
            for (ProposalChangedData proposalChangedData : this.getProposalChangedDataList()) {
                if (this.getProposalChangeHistory().get(proposalChangedData.getEditableColumn().getColumnLabel()) == null) {
                    this.getProposalChangeHistory().put(proposalChangedData.getEditableColumn().getColumnLabel(),
                            new ArrayList<ProposalChangedData>());
                }

                this.getProposalChangeHistory().get(proposalChangedData.getEditableColumn().getColumnLabel()).add(
                        proposalChangedData);
            }
        }
    }

    public void updateS2sOpportunity() {
        if (s2sOpportunity != null && s2sOpportunity.getOpportunityId() == null) {
            s2sOpportunity = null;
        }
    }

    /**
     * Sets the proposalType attribute value.
     * @param proposalType The proposalType to set.
     */
    public void setProposalType(ProposalType proposalType) {
        this.proposalType = proposalType;
    }

    /**
     * Gets the proposalType attribute. 
     * @return Returns the proposalType.
     */
    public ProposalType getProposalType() {
        return proposalType;
    }

    //    /**  
    //     * Gets the children attribute.   
    //     * @return Returns the children.  
    //     */  
    //    public List<ProposalHierarchyChild> getChildren() {  
    //        return children;  
    //    }  
    //    /**  
    //     * Sets the children attribute value.  
    //     * @param children The children to set.  
    //     */  
    //    public void setChildren(List<ProposalHierarchyChild> children) {  
    //        this.children = children;  
    //    }  
    /**
     * In the case where a person is in the proposal twice (Investigator and Key Person),
     * this method's return list contains only the Investigator.
     * 
     * @see org.kuali.kra.budget.core.BudgetParent#getPersonRolodexList()
     */
    public List<PersonRolodex> getPersonRolodexList() {
        ArrayList<PersonRolodex> filtered = new ArrayList<PersonRolodex>();
        for (ProposalPerson person : getProposalPersons()) {
            if (!filtered.contains(person)) filtered.add(person);
            else {
                if (person.isInvestigator()) {
                    filtered.remove(person);
                    filtered.add(person);
                }
            }
        }
        return filtered;
    }

    public Unit getUnit() {
        return getOwnedByUnit();
    }

    public String getUnitNumber() {
        return getOwnedByUnitNumber();
    }

    /**
     * This method sets the proposal number on all sub-BOs that have a proposal number.
     */
    public void updateProposalNumbers() {
        String proposalNumber = getProposalNumber();
        for (ProposalSite site : getProposalSites()) {
            site.setProposalNumber(proposalNumber);
        }
        if(s2sOpportunity!=null)
            s2sOpportunity.setProposalNumber(proposalNumber);
    }

    public String getDefaultBudgetStatusParameter() {
        return Constants.BUDGET_STATUS_INCOMPLETE_CODE;
    }

    public boolean isParentInHierarchyComplete() {
        return isParentProposalComplete();
    }

    public NoticeOfOpportunity getNoticeOfOpportunity() {
        return noticeOfOpportunity;
    }

    public void setNoticeOfOpportunity(NoticeOfOpportunity noticeOfOpportunity) {
        this.noticeOfOpportunity = noticeOfOpportunity;
    }

    public NsfCode getNsfCodeBo() {
        return nsfCodeBo;
    }

    public void setNsfCodeBo(NsfCode nsfCodeBo) {
        this.nsfCodeBo = nsfCodeBo;
    }

    public String getAttachmentsStatus() {
        String statusString = ATTACHMENTS_COMPLETE;
        if (!getNarratives().isEmpty()) {
            for (Narrative aNarrative : getNarratives()) {
                if (aNarrative.getNarrativeStatus().getDescription().equals(ATTACHMENTS_INCOMPLETE)) {
                    statusString = ATTACHMENTS_INCOMPLETE;
                }
            }
        } else {
            statusString = ATTACHMENTS_NONE;
        }
        return statusString;
    }

    public void cleanupSpecialReviews(DevelopmentProposal srcProposal) {
        List<ProposalSpecialReview> srcSpecialReviews = srcProposal.getPropSpecialReviews();
        List<ProposalSpecialReview> dstSpecialReviews = getPropSpecialReviews();
        for (int i = 0; i < srcSpecialReviews.size(); i++) {
            ProposalSpecialReview srcSpecialReview = srcSpecialReviews.get(i);
            ProposalSpecialReview dstSpecialReview = dstSpecialReviews.get(i);
            // copy exemption codes, since they are transient and ignored by deepCopy()  
            if (srcSpecialReview.getExemptionTypeCodes() != null) {
                List<String> exemptionCodeCopy = new ArrayList<String>();
                for (String s : srcSpecialReview.getExemptionTypeCodes()) {
                    exemptionCodeCopy.add(new String(s));
                }
                dstSpecialReview.setExemptionTypeCodes(exemptionCodeCopy);
            }
            // force new SQL table inserts  
            dstSpecialReview.resetPersistenceState();
        }
    }

    // Note: following the pattern of Sponsor, this getter indirectly calls a service.  
    // Is there a better way?  
    public Sponsor getPrimeSponsor() {
        if (outOfSync(primeSponsorCode, primeSponsor)) {
            this.refreshReferenceObject("primeSponsor");
        }
        return primeSponsor;
    }

    /**
     * checks if a sponsor code needs refreshing.
     * @param code the code
     * @param spon the sponsor to refresh
     * @return true if needs refreshing
     */
    private static boolean outOfSync(String code, Sponsor spon) {
        return spon == null && !StringUtils.isEmpty(code) || (spon != null && !StringUtils.equals(spon.getSponsorCode(), code))
                && !StringUtils.isEmpty(code);
    }

    public void setPrimeSponsor(Sponsor primeSponsor) {
        this.primeSponsor = primeSponsor;
        this.primeSponsorCode = primeSponsor != null ? primeSponsor.getSponsorCode() : null;
    }

    public String getParentNumber() {
        return this.getProposalNumber();
    }

    public String getParentPIName() {
        String proposalInvestigatorName = null;
        for (ProposalPerson pPerson : this.getProposalPersons()) {
            if (pPerson.getPersonId() != null && pPerson.getProposalPersonRoleId().equals("PI"))
            {
                proposalInvestigatorName = pPerson.getFullName();
                break;
            }
        }
        return proposalInvestigatorName;
    }

    public String getParentTitle() {
        return this.getTitle();
    }

    public String getIsOwnedByUnit() {
        Map<String, String> proposalNumberMap = new HashMap<String, String>();
        String proposalNumber = this.getProposalNumber();
        proposalNumberMap.put("proposalNumber", proposalNumber);
        BusinessObjectService businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
      LookupableDevelopmentProposal lookupDevProposal = (LookupableDevelopmentProposal) businessObjectService
              .findByPrimaryKey(LookupableDevelopmentProposal.class,
                      proposalNumberMap);
        if (lookupDevProposal != null) {
            return lookupDevProposal.getSponsor().getOwnedByUnit();
        }
        return "";
    }

    public Integer getParentInvestigatorFlag(String personId, Integer flag) {
        for (ProposalPerson pPerson : this.getProposalPersons()) {
            if (pPerson.getPersonId() != null
                    && pPerson.getPersonId().equals(personId)
                    || pPerson.getRolodexId() != null
                    && pPerson.getRolodexId().equals(personId)) {
                flag = 2;
                if (pPerson.getProposalPersonRoleId().equals("PI")) {
                    flag = 1;
                    break;
                }
            }
        }
        return flag;
    }

    public String getParentTypeName() {
        return "Proposal";
    }

@Override
public String getProjectName() {
    // TODO Auto-generated method stub
    return getTitle();
}

@Override
public String getProjectId() {
    // TODO Auto-generated method stub
    return getProposalNumber();
}
}
