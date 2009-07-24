/*
 * Copyright 2006-2008 The Kuali Foundation
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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.collections.CollectionUtils;
import org.kuali.kra.authorization.KraAuthorizationConstants;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.bo.Organization;
import org.kuali.kra.bo.Rolodex;
import org.kuali.kra.bo.Sponsor;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.budget.bo.BudgetVersionOverview;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.document.BudgetVersionCollection;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.service.NarrativeService;
import org.kuali.kra.proposaldevelopment.service.ProposalDevelopmentService;
import org.kuali.kra.proposaldevelopment.service.ProposalPersonBiographyService;
import org.kuali.kra.rice.shim.UniversalUser;
import org.kuali.kra.s2s.bo.S2sAppSubmission;
import org.kuali.kra.s2s.bo.S2sOppForms;
import org.kuali.kra.s2s.bo.S2sOpportunity;
import org.kuali.kra.s2s.bo.S2sSubmissionHistory;
import org.kuali.kra.service.YnqService;
import org.kuali.rice.kns.datadictionary.DataDictionary;
import org.kuali.rice.kns.datadictionary.DocumentEntry;
import org.kuali.rice.kns.document.authorization.PessimisticLock;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.kns.service.KualiConfigurationService;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.TypedArrayList;

/**
 * This class...
 */
public class DevelopmentProposal extends KraPersistableBusinessObjectBase implements BudgetVersionCollection {


    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -9211313487776934111L;

    private String proposalNumber;
    private String proposalTypeCode;
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
    private String deadlineType;
    private String cfdaNumber;
    private String programAnnouncementNumber;
    private String primeSponsorCode;
    private String sponsorProposalNumber;
    private String nsfCode;
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
    private String organizationId;
    private String performingOrganizationId;
    private List<ProposalLocation> proposalLocations;
    private Organization organization;
    // TODO: just for organization panel. not a real reference
    private Organization performingOrganization;
    // TODO: just for delivery panel. not a real reference
    private Rolodex rolodex;
    private List<ProposalSpecialReview> propSpecialReviews;
    private List<PropScienceKeyword> propScienceKeywords;
    private List<ProposalPerson> proposalPersons;
    private List<S2sOppForms> s2sOppForms;
    private ProposalPerson principalInvestigator;
    private S2sOpportunity s2sOpportunity;
    private List<S2sAppSubmission> s2sAppSubmission;
    private String newScienceKeywordCode;
    private String newDescription;
    private Sponsor sponsor;
    private Integer nextProposalPersonNumber;
    private String budgetStatus;
    private Integer postSubmissionStatusCode;
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

    private transient Boolean allowsNoteAttachments;

    private List<ProposalYnq> proposalYnqs;
    private List<YnqGroupName> ynqGroupNames;
    private List<BudgetVersionOverview> budgetVersionOverviews;
    private String creationStatusCode;
    private List<S2sSubmissionHistory> s2sSubmissionHistory;
    private boolean nih;
    private Map<String, String> nihDescription;

    private List<ProposalChangedData> proposalChangedDataList;
    private Map<String, List<ProposalChangedData>> proposalChangeHistory;

    private Boolean submitFlag = Boolean.FALSE;


    private ProposalDevelopmentDocument proposalDocument;


    public DevelopmentProposal() {
        super();
        setProposalStateTypeCode(ProposalState.IN_PROGRESS);
        propScienceKeywords = new TypedArrayList(PropScienceKeyword.class);
        newDescription = getDefaultNewDescription();
        proposalLocations = new ArrayList<ProposalLocation>();
        propSpecialReviews = new TypedArrayList(ProposalSpecialReview.class);
        proposalPersons = new ArrayList<ProposalPerson>();
        nextProposalPersonNumber = Integer.valueOf(1);
        narratives = new ArrayList<Narrative>();
        proposalAbstracts = new ArrayList<ProposalAbstract>();
        instituteAttachments = new ArrayList<Narrative>();
        propPersonBios = new ArrayList<ProposalPersonBiography>();
        proposalYnqs = new ArrayList<ProposalYnq>();
        ynqGroupNames = new ArrayList<YnqGroupName>();
        budgetVersionOverviews = new TypedArrayList(BudgetVersionOverview.class);
        investigators = new ArrayList<ProposalPerson>();
        s2sOppForms = new ArrayList<S2sOppForms>();
        s2sAppSubmission = new ArrayList<S2sAppSubmission>();
        s2sSubmissionHistory = new ArrayList<S2sSubmissionHistory>();
        proposalChangedDataList = new TypedArrayList(ProposalChangedData.class);
        proposalChangeHistory = new TreeMap<String, List<ProposalChangedData>>();
    }

    public void initializeOwnedByUnitNumber() {
        ProposalDevelopmentService proposalDevelopmentService = KraServiceLocator.getService(ProposalDevelopmentService.class);
        List<Unit> userUnits = proposalDevelopmentService.getDefaultModifyProposalUnitsForUser(GlobalVariables.getUserSession()
                .getPrincipalName());
        if (userUnits.size() == 1) {
            this.setOwnedByUnitNumber(userUnits.get(0).getUnitNumber());
            proposalDevelopmentService.initializeUnitOrganzationLocation(this.getProposalDocument());
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

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getPerformingOrganizationId() {
        return performingOrganizationId;
    }

    public void setPerformingOrganizationId(String performingOrganizationId) {
        // TODO : not sure yet
        if ((performingOrganizationId == null || performingOrganizationId.trim().equals("")) && organizationId != null) {
            this.performingOrganizationId = organizationId;
        }
        else {
            this.performingOrganizationId = performingOrganizationId;
        }
    }

    public List<ProposalLocation> getProposalLocations() {
        return proposalLocations;
    }

    public void setProposalLocations(List<ProposalLocation> proposalLocations) {
        this.proposalLocations = proposalLocations;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Organization getPerformingOrganization() {
        return performingOrganization;
    }

    public void setPerformingOrganization(Organization performingOrganization) {
        this.performingOrganization = performingOrganization;
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

    private ProposalDevelopmentDocument getPersistedCopy() {
        ProposalDevelopmentDocument copy = null;

        try {
            copy = (ProposalDevelopmentDocument) KNSServiceLocator.getDocumentService().getByDocumentHeaderId(
                    this.getProposalDocument().getDocumentNumber());
        }
        catch (Exception e) {
        }

        return copy;
    }

    private boolean isNotLatest(ProposalDevelopmentDocument copy) {
        if (copy.getVersionNumber().longValue() > this.getVersionNumber().longValue())
            return false;

        return true;
    }

    @SuppressWarnings("unchecked")
    private void refreshNarrativesFromUpdatedCopy(List managedLists) {
        ProposalDevelopmentDocument retrievedDocument = getPersistedCopy();
        if (retrievedDocument == null)
            return;

        List<Narrative> narratives = retrievedDocument.getDevelopmentProposal().getNarratives();
        for (Narrative narrative : narratives) {
            managedLists.add(narrative.getNarrativeUserRights());
        }
        managedLists.add(narratives);

        if (isNotLatest(retrievedDocument)) {
            // The same document has been updated by someone else
            // Refresh Narratives related collections
            if (narratives.size() >= this.getNarratives().size()) {
                this.setNarratives(narratives);
            }
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List buildListOfDeletionAwareLists() {
        List managedLists = super.buildListOfDeletionAwareLists();
        UniversalUser currentUser = new UniversalUser(GlobalVariables.getUserSession().getPerson());

        for (PessimisticLock lock : getProposalDocument().getPessimisticLocks()) {
            if (lock.isOwnedByUser(currentUser)
                    && lock.getLockDescriptor().contains(KraAuthorizationConstants.LOCK_DESCRIPTOR_PROPOSAL)) {
                refreshNarrativesFromUpdatedCopy(managedLists);
                break;
            }
        }
        List<ProposalPersonUnit> units = new ArrayList<ProposalPersonUnit>();
        List<ProposalPersonDegree> degrees = new ArrayList<ProposalPersonDegree>();
        for (ProposalPerson person : getProposalPersons()) {
            units.addAll(person.getUnits());
            degrees.addAll(person.getProposalPersonDegrees());
        }

        managedLists.add(units);
        managedLists.add(degrees);
        managedLists.add(getProposalLocations());
        managedLists.add(getPropSpecialReviews());

        List<ProposalExemptNumber> proposalExemptNumbers = new ArrayList<ProposalExemptNumber>();

        for (ProposalSpecialReview review : getPropSpecialReviews()) {
            proposalExemptNumbers.addAll(review.getProposalExemptNumbers());
        }
        managedLists.add(proposalExemptNumbers);

        managedLists.add(getProposalPersons());
        managedLists.add(getPropScienceKeywords());
        managedLists.add(getProposalAbstracts());
        managedLists.add(getPropPersonBios());
        managedLists.add(getS2sAppSubmission());
        managedLists.add(getS2sSubmissionHistory());
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


    /**
     * Accessor for the principal investigator of this proposal
     * 
     * @return ProposalPerson
     */
    public ProposalPerson getPrincipalInvestigator() {
        return principalInvestigator;
    }

    /**
     * Accessor for the principal investigator of this proposal
     * 
     * @param person
     */
    public void setPrincipalInvestigator(ProposalPerson person) {
        principalInvestigator = person;
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
    public void addProposalPersonBiography(ProposalPersonBiography proposalPersonBiography) throws Exception {
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
     * @param narrativeService The narrativeService to set.
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
     * Gets index i from the proposalLocations list.
     * 
     * @param index
     * @return Question at index i
     */
    public ProposalLocation getProposalLocation(int index) {
        while (getProposalLocations().size() <= index) {
            getProposalLocations().add(new ProposalLocation());
        }
        return getProposalLocations().get(index);
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

    public List<BudgetVersionOverview> getBudgetVersionOverviews() {
        return budgetVersionOverviews;
    }

    public void setBudgetVersionOverviews(List<BudgetVersionOverview> budgetVersionOverviews) {
        this.budgetVersionOverviews = budgetVersionOverviews;
    }

    /**
     * This method gets the next budget version number for this proposal. We can't use documentNextVersionNumber because that
     * requires a save and we don't want to save the proposal development document before forwarding to the budget document.
     * 
     * @return Integer
     */
    public Integer getNextBudgetVersionNumber() {
        List<BudgetVersionOverview> versions = getBudgetVersionOverviews();
        if (versions.isEmpty()) {
            return 1;
        }
        Collections.sort(versions);
        BudgetVersionOverview lastVersion = versions.get(versions.size() - 1);
        return lastVersion.getBudgetVersionNumber() + 1;
    }

    /**
     * Gets index i from the budget versions list.
     * 
     * @param index
     * @return BudgetVersionOverview at index i
     */
    public BudgetVersionOverview getBudgetVersionOverview(int index) {
        while (getBudgetVersionOverviews().size() <= index) {
            getBudgetVersionOverviews().add(new BudgetVersionOverview());
        }
        return getBudgetVersionOverviews().get(index);
    }

    public List<S2sOppForms> getS2sOppForms() {
        return s2sOppForms;
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

    public ProposalPersonRole getProposalEmployeeRole(String personId) {
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

    public ProposalPersonRole getProposalNonEmployeeRole(Integer rolodexId) {
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

    public BudgetVersionOverview getFinalBudgetVersion() {
        for (BudgetVersionOverview version : this.getBudgetVersionOverviews()) {
            if (version.isFinalVersionFlag()) {
                return version;
            }
        }
        return null;
    }

    public Boolean getAllowsNoteAttachments() {
        if (allowsNoteAttachments == null) {
            DataDictionary dataDictionary = KNSServiceLocator.getDataDictionaryService().getDataDictionary();
            DocumentEntry entry = dataDictionary.getDocumentEntry(getClass().getName());
            allowsNoteAttachments = entry.getAllowsNoteAttachments();
        }

        return allowsNoteAttachments;
    }

    public void setAllowsNoteAttachments(boolean allowsNoteAttachments) {
        this.allowsNoteAttachments = allowsNoteAttachments;
    }

    public void addNewBudgetVersion(BudgetDocument budgetDocument, String name, boolean isDescriptionUpdatable) {
        BudgetVersionOverview budgetVersion = new BudgetVersionOverview();
        budgetVersion.setDocumentNumber(budgetDocument.getDocumentNumber());
        budgetVersion.setProposalNumber(this.getProposalNumber());
        budgetVersion.setDocumentDescription(name);
        budgetVersion.setBudgetVersionNumber(budgetDocument.getBudgetVersionNumber());
        budgetVersion.setStartDate(budgetDocument.getStartDate());
        budgetVersion.setEndDate(budgetDocument.getEndDate());
        budgetVersion.setOhRateTypeCode(budgetDocument.getOhRateTypeCode());
        budgetVersion.setOhRateClassCode(budgetDocument.getOhRateClassCode());
        budgetVersion.setVersionNumber(budgetDocument.getVersionNumber());
        budgetVersion.setDescriptionUpdatable(isDescriptionUpdatable);

        String budgetStatusIncompleteCode = KraServiceLocator.getService(KualiConfigurationService.class).getParameterValue(
                Constants.PARAMETER_MODULE_BUDGET, Constants.PARAMETER_COMPONENT_DOCUMENT, Constants.BUDGET_STATUS_INCOMPLETE_CODE);
        budgetVersion.setBudgetStatus(budgetStatusIncompleteCode);

        this.getBudgetVersionOverviews().add(budgetVersion);
    }

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
        String budgetStatusCompleteCode = KraServiceLocator.getService(KualiConfigurationService.class).getParameterValue(
                Constants.PARAMETER_MODULE_BUDGET, Constants.PARAMETER_COMPONENT_DOCUMENT, Constants.BUDGET_STATUS_COMPLETE_CODE);

        if (this.getBudgetStatus() != null && budgetStatusCompleteCode != null
                && this.getBudgetStatus().equals(budgetStatusCompleteCode)) {
            return true;
        }
        return false;
    }

    public int getNumberOfVersions() {
        return this.getBudgetVersionOverviews().size();
    }


    public boolean isNih() {
        return nih;
    }

    public void setNih(boolean nih) {
        this.nih = nih;
    }

    public Map<String, String> getNihDescription() {
        return nihDescription;
    }

    public void setNihDescription(Map<String, String> nihDescription) {
        this.nihDescription = nihDescription;

    }

    public List<S2sAppSubmission> getS2sAppSubmission() {
        return s2sAppSubmission;
    }

    public void setS2sAppSubmission(List<S2sAppSubmission> appSubmission) {
        s2sAppSubmission = appSubmission;
    }

    public List<S2sSubmissionHistory> getS2sSubmissionHistory() {
        return s2sSubmissionHistory;
    }

    public void setS2sSubmissionHistory(List<S2sSubmissionHistory> submissionHistory) {
        s2sSubmissionHistory = submissionHistory;
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

    public Integer getPostSubmissionStatusCode() {
        return postSubmissionStatusCode;
    }

    public void setPostSubmissionStatusCode(Integer postSubmissionStatusCode) {
        this.postSubmissionStatusCode = postSubmissionStatusCode;
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
     * @see org.kuali.rice.kns.bo.BusinessObjectBase#toStringMapper()
     */
    @Override
    protected LinkedHashMap toStringMapper() {
        // TODO Auto-generated method stub
        return null;
    }


}
