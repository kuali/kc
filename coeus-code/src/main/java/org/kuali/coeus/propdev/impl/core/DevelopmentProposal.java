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
package org.kuali.coeus.propdev.impl.core;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.persistence.annotations.Customizer;
import org.eclipse.persistence.config.DescriptorCustomizer;
import org.kuali.coeus.common.api.sponsor.hierarchy.SponsorHierarchyService;
import org.kuali.coeus.common.framework.noo.NoticeOfOpportunity;
import org.kuali.coeus.common.framework.org.Organization;
import org.kuali.coeus.common.framework.rolodex.Rolodex;
import org.kuali.coeus.common.framework.rolodex.NonOrganizationalRolodex;
import org.kuali.coeus.common.framework.sponsor.Sponsor;
import org.kuali.coeus.common.framework.sponsor.Sponsorable;
import org.kuali.coeus.common.framework.type.ActivityType;
import org.kuali.coeus.common.framework.type.DeadlineType;
import org.kuali.coeus.common.framework.type.InvestigatorCreditType;
import org.kuali.coeus.common.framework.type.ProposalType;
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.coeus.common.framework.ynq.YnqGroupName;
import org.kuali.coeus.common.framework.ynq.YnqService;
import org.kuali.coeus.propdev.api.core.DevelopmentProposalContract;
import org.kuali.coeus.propdev.impl.abstrct.ProposalAbstract;
import org.kuali.coeus.propdev.impl.attachment.LegacyNarrativeService;
import org.kuali.coeus.propdev.impl.attachment.Narrative;
import org.kuali.coeus.propdev.impl.attachment.NarrativeStatus;
import org.kuali.coeus.propdev.impl.budget.ProposalBudgetStatusService;
import org.kuali.coeus.propdev.impl.budget.ProposalDevelopmentBudgetExt;
import org.kuali.coeus.propdev.impl.editable.ProposalChangedData;
import org.kuali.coeus.propdev.impl.keyword.PropScienceKeyword;
import org.kuali.coeus.propdev.impl.location.CongressionalDistrict;
import org.kuali.coeus.propdev.impl.location.ProposalSite;
import org.kuali.coeus.propdev.impl.person.KeyPersonnelService;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;
import org.kuali.coeus.propdev.impl.person.ProposalPersonDegree;
import org.kuali.coeus.propdev.impl.person.ProposalPersonUnit;
import org.kuali.coeus.propdev.impl.person.attachment.ProposalPersonBiography;
import org.kuali.coeus.propdev.impl.person.attachment.ProposalPersonBiographyService;
import org.kuali.coeus.propdev.impl.state.ProposalState;
import org.kuali.coeus.propdev.impl.ynq.ProposalYnq;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.framework.persistence.CompositeDescriptorCustomizer;
import org.kuali.coeus.sys.framework.persistence.FilterByMapDescriptorCustomizer;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.home.AwardType;
import org.kuali.kra.award.home.ContactRole;
import org.kuali.kra.bo.*;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.core.BudgetParent;
import org.kuali.coeus.common.budget.framework.core.BudgetDocument;
import org.kuali.coeus.common.budget.framework.core.BudgetParentDocument;
import org.kuali.coeus.common.framework.rolodex.PersonRolodex;
import org.kuali.kra.coi.Disclosurable;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.krms.KcKrmsContextBo;
import org.kuali.kra.krms.KrmsRulesContext;
import org.kuali.coeus.propdev.impl.budget.editable.BudgetChangedData;
import org.kuali.coeus.propdev.impl.hierarchy.HierarchyStatusConstants;
import org.kuali.coeus.propdev.impl.hierarchy.ProposalHierarchyException;
import org.kuali.coeus.propdev.impl.hierarchy.ProposalHierarchyService;
import org.kuali.coeus.propdev.impl.specialreview.ProposalSpecialReview;
import org.kuali.coeus.propdev.impl.specialreview.ProposalSpecialReviewExemption;
import org.kuali.coeus.propdev.impl.s2s.S2sAppSubmission;
import org.kuali.coeus.propdev.impl.s2s.S2sOppForms;
import org.kuali.coeus.propdev.impl.s2s.S2sOpportunity;
import org.kuali.coeus.propdev.impl.s2s.S2sUserAttachedForm;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.data.jpa.PortableSequenceGenerator;
import org.kuali.rice.krad.data.jpa.converters.BooleanYNConverter;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;

import javax.persistence.*;

import java.sql.Date;
import java.util.*;

@Entity
@Table(name = "EPS_PROPOSAL")
@Customizer(DevelopmentProposal.DevelopmentProposalCustomizer.class)
public class DevelopmentProposal extends KcPersistableBusinessObjectBase implements BudgetParent, Sponsorable, Disclosurable, KcKrmsContextBo, DevelopmentProposalContract {

    private static final long serialVersionUID = -9211313487776934111L;

    private static final String ATTACHMENTS_COMPLETE = "Complete";

    private static final String ATTACHMENTS_INCOMPLETE = "Inomplete";

    private static final String ATTACHMENTS_NONE = "None";

    private static final String ROLODEX_ID_FIELD_NAME = "rolodexId";

    @PortableSequenceGenerator(name = "SEQ_PROPOSAL_NUMBER_KRA")
    @GeneratedValue(generator = "SEQ_PROPOSAL_NUMBER_KRA")
    @Id
    @Column(name = "PROPOSAL_NUMBER")
    private String proposalNumber;

    @Column(name = "PROPOSAL_TYPE_CODE")
    private String proposalTypeCode;

    @Column(name = "CONTINUED_FROM")
    private String continuedFrom;

    @Column(name = "SPONSOR_CODE")
    private String sponsorCode;

    @Column(name = "ACTIVITY_TYPE_CODE")
    private String activityTypeCode;

    @Column(name = "OWNED_BY_UNIT")
    private String ownedByUnitNumber;

    @Column(name = "REQUESTED_START_DATE_INITIAL")
    private Date requestedStartDateInitial;

    @Column(name = "REQUESTED_END_DATE_INITIAL")
    private Date requestedEndDateInitial;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "CURRENT_AWARD_NUMBER")
    private String currentAwardNumber;

    @Column(name = "DEADLINE_DATE")
    private Date deadlineDate;

    @Column(name = "DEADLINE_TIME")
    private String deadlineTime;

    @Column(name = "NOTICE_OF_OPPORTUNITY_CODE")
    private String noticeOfOpportunityCode;

    @Column(name = "DEADLINE_TYPE")
    private String deadlineType;

    @Column(name = "ANTICIPATED_AWARD_TYPE_CODE")
    private Integer anticipatedAwardTypeCode;

    @Column(name = "CFDA_NUMBER")
    private String cfdaNumber;

    @Column(name = "PROGRAM_ANNOUNCEMENT_NUMBER")
    private String programAnnouncementNumber;

    @Column(name = "PRIME_SPONSOR_CODE")
    private String primeSponsorCode;

    @Column(name = "SPONSOR_PROPOSAL_NUMBER")
    private String sponsorProposalNumber;

    @Column(name = "NSF_CODE")
    private String nsfCode;

    @Column(name = "SUBCONTRACT_FLAG")
    @Convert(converter = BooleanYNConverter.class)
    private Boolean subcontracts;

    @Column(name = "AGENCY_DIVISION_CODE")
    private String agencyDivisionCode;

    @Column(name = "AGENCY_PROGRAM_CODE")
    private String agencyProgramCode;

    @Column(name = "PROGRAM_ANNOUNCEMENT_TITLE")
    private String programAnnouncementTitle;

    @Column(name = "MAIL_BY")
    private String mailBy;

    @Column(name = "MAIL_TYPE")
    private String mailType;

    @Column(name = "MAIL_ACCOUNT_NUMBER")
    private String mailAccountNumber;

    @Column(name = "MAIL_DESCRIPTION")
    private String mailDescription;

    @Column(name = "MAILING_ADDRESS_ID")
    private Integer mailingAddressId;

    @Column(name = "NUMBER_OF_COPIES")
    private String numberOfCopies;

    @Column(name = "STATUS_CODE")
    private String proposalStateTypeCode;

    @Column(name = "CREATION_STATUS_CODE")
    private String creationStatusCode;

    @Column(name = "SUBMIT_FLAG")
    @Convert(converter = BooleanYNConverter.class)
    private Boolean submitFlag = Boolean.FALSE;

    @Column(name = "IS_HIERARCHY")
    private String hierarchyStatus;

    @Column(name = "HIERARCHY_ORIG_CHILD_PROP_NBR")
    private String hierarchyOriginatingChildProposalNumber;

    @Column(name = "HIERARCHY_PROPOSAL_NUMBER")
    private String hierarchyParentProposalNumber;

    @Column(name = "HIERARCHY_HASH_CODE")
    private Integer hierarchyLastSyncHashCode;

    @Column(name = "HIERARCHY_BUDGET_TYPE")
    private String hierarchyBudgetType;

    @Column(name = "HIERARCHY_LAST_BUDGET_DOC_NBR")
    private String lastSyncedBudgetDocumentNumber;

    @Column(name = "PROPOSALNUMBER_GG")
    private String proposalNumberForGG;

    @Column(name = "OPPORTUNITYID_GG")
    private String opportunityIdForGG;

    @Column(name = "AGENCY_ROUTING_IDENTIFIER")
    private String agencyRoutingIdentifier;
    
    @Column(name = "PREV_GG_TRACKID")
    private String prevGrantsGovTrackingID;

    @ManyToOne(targetEntity = DeadlineType.class, cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "DEADLINE_TYPE", referencedColumnName = "DEADLINE_TYPE_CODE", insertable = false, updatable = false)
    private DeadlineType deadlineTypeRef;

    @ManyToOne(targetEntity = ProposalType.class, cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "PROPOSAL_TYPE_CODE", referencedColumnName = "PROPOSAL_TYPE_CODE", insertable = false, updatable = false)
    private ProposalType proposalType;

    @ManyToOne(targetEntity = NoticeOfOpportunity.class, cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "NOTICE_OF_OPPORTUNITY_CODE", referencedColumnName = "NOTICE_OF_OPPORTUNITY_CODE", insertable = false, updatable = false)
    private NoticeOfOpportunity noticeOfOpportunity;

    @ManyToOne(targetEntity = AwardType.class, cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "ANTICIPATED_AWARD_TYPE_CODE", referencedColumnName = "AWARD_TYPE_CODE", insertable = false, updatable = false)
    private AwardType anticipatedAwardType;

    @ManyToOne(targetEntity = ProposalState.class, cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "STATUS_CODE", referencedColumnName = "STATE_TYPE_CODE", insertable = false, updatable = false)
    private ProposalState proposalState;

    @ManyToOne(targetEntity = Rolodex.class, cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "MAILING_ADDRESS_ID", referencedColumnName = "ROLODEX_ID", insertable = false, updatable = false)
    private Rolodex rolodex;

    @ManyToOne(targetEntity = Sponsor.class, cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "SPONSOR_CODE", referencedColumnName = "SPONSOR_CODE", insertable = false, updatable = false)
    private Sponsor sponsor;

    @ManyToOne(targetEntity = Unit.class, cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "OWNED_BY_UNIT", referencedColumnName = "UNIT_NUMBER", insertable = false, updatable = false)
    private Unit ownedByUnit;

    @ManyToOne(targetEntity = Sponsor.class, cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "PRIME_SPONSOR_CODE", referencedColumnName = "SPONSOR_CODE", insertable = false, updatable = false)
    private Sponsor primeSponsor;

    @ManyToOne(targetEntity = ActivityType.class, cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "ACTIVITY_TYPE_CODE", referencedColumnName = "ACTIVITY_TYPE_CODE", insertable = false, updatable = false)
    private ActivityType activityType;

    //@OneToMany(targetEntity = ProposalSite.class, orphanRemoval = true, cascade = { CascadeType.REFRESH, CascadeType.REMOVE, CascadeType.PERSIST })
    //@JoinColumn(name = "PROPOSAL_NUMBER", referencedColumnName = "PROPOSAL_NUMBER", insertable = false, updatable = false)
    //@OrderBy("siteNumber")
    @Transient //Transient for now because this mapping is messed up
    private List<ProposalSite> proposalSites;

    @OneToMany(mappedBy="developmentProposal", orphanRemoval = true, cascade = { CascadeType.ALL })
    private List<ProposalSpecialReview> propSpecialReviews;

    @OneToMany(mappedBy="developmentProposal", orphanRemoval = true, cascade = { CascadeType.ALL })
    private List<PropScienceKeyword> propScienceKeywords;

    @OneToMany(mappedBy="developmentProposal", orphanRemoval = true, cascade = { CascadeType.ALL })
    @OrderBy("ordinalPosition")
    private List<ProposalPerson> proposalPersons;

    @OneToMany(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "PROPOSAL_NUMBER", referencedColumnName = "PROPOSAL_NUMBER")
    private List<S2sOppForms> s2sOppForms;

    @OneToMany(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "PROPOSAL_NUMBER", referencedColumnName = "PROPOSAL_NUMBER")
    private List<S2sAppSubmission> s2sAppSubmission;

    @OneToMany(mappedBy="developmentProposal", cascade = { CascadeType.ALL })
    private List<S2sUserAttachedForm> s2sUserAttachedForms;

    @OneToMany(orphanRemoval = true, cascade = { CascadeType.ALL })
    @JoinColumn(name = "PROPOSAL_NUMBER", referencedColumnName = "PROPOSAL_NUMBER")
    @OrderBy("sortId")
    private List<ProposalYnq> proposalYnqs;

    @OneToMany(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "PROPOSAL_NUMBER", referencedColumnName = "PROPOSAL_NUMBER")
    @OrderBy("columnName DESC, changeNumber DESC")
    private List<ProposalChangedData> proposalChangedDataList;

    @OneToMany(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "PROPOSAL_NUMBER", referencedColumnName = "PROPOSAL_NUMBER")
    @OrderBy("columnName DESC, changeNumber DESC")
    private List<BudgetChangedData> budgetChangedDataList;

    @OneToMany(mappedBy="developmentProposal",orphanRemoval = true, cascade = { CascadeType.ALL })
    private List<Narrative> narratives;

    @OneToMany(orphanRemoval = true, cascade = { CascadeType.ALL })
    @JoinColumn(name = "PROPOSAL_NUMBER", referencedColumnName = "PROPOSAL_NUMBER")
    private List<ProposalAbstract> proposalAbstracts;

    @OneToMany(mappedBy="developmentProposal",orphanRemoval = true, cascade = { CascadeType.ALL })
    private List<Narrative> instituteAttachments;

    @OneToMany(mappedBy="developmentProposal",orphanRemoval = true, cascade = { CascadeType.ALL })
    private List<ProposalPersonBiography> propPersonBios;

    @OneToOne(mappedBy = "developmentProposal", cascade = CascadeType.ALL)
    private S2sOpportunity s2sOpportunity;

    @OneToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "DOCUMENT_NUMBER", referencedColumnName = "DOCUMENT_NUMBER", insertable = true, updatable = true)
    private ProposalDevelopmentDocument proposalDocument;

    @OneToMany(mappedBy="developmentProposal", orphanRemoval = true, cascade = { CascadeType.ALL })
    private List<ProposalDevelopmentBudgetExt> budgets;


	@Transient
    private NsfCode nsfCodeBo;

    @Transient
    private String newScienceKeywordCode;

    @Transient
    private String newDescription;

    @Transient
    private Integer nextProposalPersonNumber;

    @Transient
    private String budgetStatus;

    @Transient
    private String budgetStatusDescription;

    @Transient
    private List<ProposalPerson> investigators;

    @Transient
    private Collection<InvestigatorCreditType> investigatorCreditTypes;

    @Transient
    private List<YnqGroupName> ynqGroupNames;

    @Transient
    private Map<String, List<ProposalChangedData>> proposalChangeHistory;

    @Transient
    private Map<String, List<BudgetChangedData>> budgetChangeHistory;

    @Transient
    private Boolean grantsGovSelectFlag = Boolean.FALSE;

    @Transient
    private String hierarchyStatusName;

    @Transient
    private transient BusinessObjectService businessObjectService;

    @Transient
    private transient DataObjectService dataObjectService;

    @Transient
    private transient ParameterService parameterService;

    @Transient
    private transient ProposalHierarchyService proposalHierarchyService;

    @Transient
    private transient KeyPersonnelService keyPersonnelService;

    @Transient
    private transient LegacyNarrativeService narrativeService;

    @Transient
    private transient ProposalPersonBiographyService proposalPersonBiographyService;

    @Transient
    private transient SponsorHierarchyService sponsorHierarchyService;

    @Override
    public String getProposalNumberForGG() {
        if (s2sOpportunity != null) {
            proposalNumberForGG = s2sOpportunity.getProposalNumber();
        }
        return proposalNumberForGG;
    }

    @Override
    public String getOpportunityIdForGG() {
        if (s2sOpportunity != null) {
            opportunityIdForGG = s2sOpportunity.getOpportunityId();
        }
        return opportunityIdForGG;
    }

    public void setProposalNumberForGG(String proposalNumberForGG) {
        this.proposalNumberForGG = proposalNumberForGG;
    }

    public void setOpportunityIdForGG(String opportunityIdForGG) {
        this.opportunityIdForGG = opportunityIdForGG;
    }

    protected ParameterService getParameterService() {
        if (this.parameterService == null) {
            this.parameterService = KcServiceLocator.getService(ParameterService.class);
        }
        return this.parameterService;
    }

    protected ProposalHierarchyService getProposalHierarchyService() {
        if (this.proposalHierarchyService == null) {
            this.proposalHierarchyService = KcServiceLocator.getService(ProposalHierarchyService.class);
        }
        return this.proposalHierarchyService;
    }

    protected KeyPersonnelService getKeyPersonnelService() {
        if (keyPersonnelService == null) {
            keyPersonnelService = KcServiceLocator.getService(KeyPersonnelService.class);
        }
        return keyPersonnelService;
    }

    @Override
    public String getHierarchyStatus() {
        return hierarchyStatus;
    }

    public void setHierarchyStatus(String hierarchyStatus) {
        this.hierarchyStatus = hierarchyStatus;
    }

    @Override
    public String getHierarchyParentProposalNumber() {
        return hierarchyParentProposalNumber;
    }

    public void setHierarchyParentProposalNumber(String hierarchyParentProposalNumber) {
        this.hierarchyParentProposalNumber = hierarchyParentProposalNumber;
    }

    @Override
    public String getHierarchyOriginatingChildProposalNumber() {
        return hierarchyOriginatingChildProposalNumber;
    }

    public void setHierarchyOriginatingChildProposalNumber(String hierarchyOriginatingChildProposalNumber) {
        this.hierarchyOriginatingChildProposalNumber = hierarchyOriginatingChildProposalNumber;
    }

    public void setHierarchyLastSyncHashCode(Integer hierarchyLastSyncHashCode) {
        this.hierarchyLastSyncHashCode = hierarchyLastSyncHashCode;
    }

    @Override
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
            if (status.code().equals(getHierarchyStatus()))
                hierarchyStatusName = status.description();
        }
        return hierarchyStatusName;
    }

    public void setHierarchyStatusName(String hierarchyStatusName) {
        this.hierarchyStatusName = hierarchyStatusName;
    }

    public void setHierarchyBudgetType(String hierarchyBudgetType) {
        this.hierarchyBudgetType = hierarchyBudgetType;
    }

    @Override
    public String getHierarchyBudgetType() {
        return hierarchyBudgetType;
    }

    public DevelopmentProposal() {
        super();
        setProposalStateTypeCode(ProposalState.IN_PROGRESS);
        propScienceKeywords = new ArrayList<PropScienceKeyword>();
        newDescription = getDefaultNewDescription();
        propSpecialReviews = new ArrayList<ProposalSpecialReview>();
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
        proposalChangedDataList = new ArrayList<ProposalChangedData>();
        s2sUserAttachedForms = new ArrayList<S2sUserAttachedForm>();
        proposalChangeHistory = new TreeMap<String, List<ProposalChangedData>>();
        budgetChangedDataList = new ArrayList<BudgetChangedData>();
        budgetChangeHistory = new TreeMap<String, List<BudgetChangedData>>();
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
        ProposalDevelopmentService proposalDevelopmentService = KcServiceLocator.getService(ProposalDevelopmentService.class);
        List<Unit> userUnits = proposalDevelopmentService.getDefaultModifyProposalUnitsForUser(GlobalVariables.getUserSession().getPrincipalId());
        if (userUnits.size() == 1) {
            this.setOwnedByUnitNumber(userUnits.get(0).getUnitNumber());
            proposalDevelopmentService.initializeUnitOrganizationLocation(this.getProposalDocument());
        }
    }

    @Override
    public List<ProposalPerson> getProposalPersons() {
        evaluateMoveOptions();
        return this.proposalPersons;
    }

    private void evaluateMoveOptions() {
        for (int i = 0; i < proposalPersons.size(); i++) {
            ProposalPerson person = proposalPersons.get(i);
            person.setMoveUpAllowed(i > 0 && !person.isPrincipalInvestigator() && !proposalPersons.get(i - 1).isPrincipalInvestigator() && !(proposalPersons.get(i - 1).isMultiplePi()));
            person.setMoveDownAllowed(i < (proposalPersons.size() - 1) && !person.isPrincipalInvestigator());
            if (person.isMultiplePi()) {
                person.setMoveUpAllowed(i > 0 && person.isMultiplePi() == proposalPersons.get(i - 1).isMultiplePi());
                person.setMoveDownAllowed(person.isMoveDownAllowed() && person.isMultiplePi() == proposalPersons.get(i + 1).isMultiplePi());
            }
        }
    }

    public void setInvestigators(List<ProposalPerson> investigators) {
        this.investigators = investigators;
    }

    @Override
    public List<ProposalPerson> getInvestigators() {
        return investigators;
    }

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

    /**
     * Dummy getter to support 2 different views of ownedByUnit through the DD.
     * @return
     */
    public String getOwnedByUnitNumberRestricted() {
        return ownedByUnitNumber;
    }

    public void setOwnedByUnitNumberRestricted(String ownedByUnit) {
        this.ownedByUnitNumber = ownedByUnit;
    }

    public String getProposalTypeCode() {
        return proposalTypeCode;
    }

    public void setProposalTypeCode(String proposalTypeCode) {
        this.proposalTypeCode = proposalTypeCode;
    }

    @Override
    public String getContinuedFrom() {
        return continuedFrom;
    }

    public void setContinuedFrom(String continuedFrom) {
        this.continuedFrom = continuedFrom;
    }

    @Override
    public Date getRequestedEndDateInitial() {
        return requestedEndDateInitial;
    }

    public void setRequestedEndDateInitial(Date requestedEndDateInitial) {
        this.requestedEndDateInitial = requestedEndDateInitial;
    }

    @Override
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

    @Override
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getProposalNumber() {
        return proposalNumber;
    }

    public void setProposalNumber(String proposalNumber) {
        this.proposalNumber = proposalNumber;
    }

    @Override
    public String getCurrentAwardNumber() {
        return currentAwardNumber;
    }

    public void setCurrentAwardNumber(String currentAwardNumber) {
        this.currentAwardNumber = currentAwardNumber;
    }

    @Override
    public String getAgencyDivisionCode() {
        return agencyDivisionCode;
    }

    public void setAgencyDivisionCode(String agencyDivisionCode) {
        this.agencyDivisionCode = agencyDivisionCode;
    }

    @Override
    public String getAgencyProgramCode() {
        return agencyProgramCode;
    }

    public void setAgencyProgramCode(String agencyProgramCode) {
        this.agencyProgramCode = agencyProgramCode;
    }

    @Override
    public String getCfdaNumber() {
        return cfdaNumber;
    }

    public void setCfdaNumber(String cfdaNumber) {
        this.cfdaNumber = cfdaNumber;
    }

    @Override
    public Date getDeadlineDate() {
        return deadlineDate;
    }

    public void setDeadlineDate(Date deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    @Override
    public String getDeadlineTime() {
        return deadlineTime;
    }

    public void setDeadlineTime(String deadlineTime) {
        this.deadlineTime = deadlineTime;
    }

    @Override
    public String getDeadlineType() {
        return deadlineType;
    }

    public void setAnticipatedAwardTypeCode(Integer anticipatedAwardTypeCode) {
        this.anticipatedAwardTypeCode = anticipatedAwardTypeCode;
    }

    public void setAnticipatedAwardType(AwardType anticipatedAwardType) {
        this.anticipatedAwardType = anticipatedAwardType;
    }

    public Integer getAnticipatedAwardTypeCode() {
        return anticipatedAwardTypeCode;
    }

    @Override
    public AwardType getAnticipatedAwardType() {
        return anticipatedAwardType;
    }

    public void setDeadlineType(String deadlineType) {
        this.deadlineType = deadlineType;
    }

    public String getNoticeOfOpportunityCode() {
        return noticeOfOpportunityCode;
    }

    public void setNoticeOfOpportunityCode(String noticeOfOpportunityCode) {
        this.noticeOfOpportunityCode = noticeOfOpportunityCode;
    }

    @Override
    public String getNsfCode() {
        return nsfCode;
    }

    public void setNsfCode(String nsfCode) {
        this.nsfCode = nsfCode;
    }

    public String getPrimeSponsorCode() {
        return primeSponsorCode;
    }

    public void setPrimeSponsorCode(String primeSponsorCode) {
        this.primeSponsorCode = primeSponsorCode;
    }

    @Override
    public String getProgramAnnouncementNumber() {
        return programAnnouncementNumber;
    }

    public void setProgramAnnouncementNumber(String programAnnouncementNumber) {
        this.programAnnouncementNumber = programAnnouncementNumber;
    }

    @Override
    public String getProgramAnnouncementTitle() {
        return programAnnouncementTitle;
    }

    public void setProgramAnnouncementTitle(String programAnnouncementTitle) {
        this.programAnnouncementTitle = programAnnouncementTitle;
    }

    @Override
    public String getSponsorProposalNumber() {
        return sponsorProposalNumber;
    }

    public void setSponsorProposalNumber(String sponsorProposalNumber) {
        this.sponsorProposalNumber = sponsorProposalNumber;
    }

    @Override
    public Boolean getSubcontracts() {
        return subcontracts;
    }

    public void setSubcontracts(Boolean subcontracts) {
        this.subcontracts = subcontracts;
    }

    @Override
    public String getMailBy() {
        return mailBy;
    }

    public void setMailBy(String mailBy) {
        this.mailBy = mailBy;
    }

    @Override
    public String getMailType() {
        return mailType;
    }

    public void setMailType(String mailType) {
        this.mailType = mailType;
    }

    @Override
    public String getMailAccountNumber() {
        return mailAccountNumber;
    }

    public void setMailAccountNumber(String mailAccountNumber) {
        this.mailAccountNumber = mailAccountNumber;
    }

    @Override
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
        refreshRolodex();
    }

    @Override
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
     */
    public void setApplicantOrgFromOrganization(Organization organization) {
        if (organization == null) {
            setApplicantOrganization((ProposalSite) null);
        } else {
            ProposalSite applicantSite = new ProposalSite();
            applicantSite.setOrganization(organization);
            setApplicantOrganization(applicantSite);
        }
    }

    @Override
    public ProposalSite getApplicantOrganization() {
        ProposalSite applicant = getProposalSiteForType(ProposalSite.PROPOSAL_SITE_APPLICANT_ORGANIZATION);
        if (applicant != null)
            applicant.setRolodex(applicant.getOrganization() == null ? null : applicant.getOrganization().getRolodex());
        return applicant;
    }

    public void setPerformingOrganization(ProposalSite performingOrganization) {
        setProposalSiteForType(performingOrganization, ProposalSite.PROPOSAL_SITE_PERFORMING_ORGANIZATION);
    }

    /**
     * This method sets the Performing Organization based on a Organization object.
     * @param organization
     */
    public void setPerformingOrgFromOrganization(Organization organization) {
        if (organization == null) {
            setPerformingOrganization((ProposalSite) null);
        } else {
            ProposalSite performingSite = new ProposalSite();
            performingSite.setOrganization(organization);
            setPerformingOrganization(performingSite);
        }
    }

    @Override
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
        proposalSite.setLocationTypeCode(locationType);
        // make sure the location type is set 
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
            proposalSite.setLocationTypeCode(locationType);
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
        } else {
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

    @Override
    public List<ProposalSite> getPerformanceSites() {
        return getProposalSitesForType(ProposalSite.PROPOSAL_SITE_PERFORMANCE_SITE);
    }

    public void addPerformanceSite(ProposalSite performanceSite) {
        performanceSite.setLocationTypeCode(ProposalSite.PROPOSAL_SITE_PERFORMANCE_SITE);
        // make sure the location type is set 
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

    @Override
    public List<ProposalSite> getOtherOrganizations() {
        return getProposalSitesForType(ProposalSite.PROPOSAL_SITE_OTHER_ORGANIZATION);
    }

    public void addOtherOrganization(ProposalSite otherOrganization) {
        otherOrganization.setLocationTypeCode(ProposalSite.PROPOSAL_SITE_OTHER_ORGANIZATION);
        // make sure the location type is set 
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

    @Override
    public List<ProposalSite> getProposalSites() {
        return proposalSites;
    }

    @Override
    public Rolodex getRolodex() {
        return rolodex;
    }

    public void setRolodex(Rolodex rolodex) {
        this.rolodex = rolodex;
    }

    public void setPropScienceKeywords(List<PropScienceKeyword> propScienceKeywords) {
    	if (propScienceKeywords != null) {
    		this.propScienceKeywords = propScienceKeywords;
    	} else {
    		this.propScienceKeywords.clear();
    	}
    	for (PropScienceKeyword keyword : this.propScienceKeywords) {
    		keyword.setDevelopmentProposal(this);
    	}
    }

    @Override
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

    @Override
    public List<ProposalSpecialReview> getPropSpecialReviews() {
        return propSpecialReviews;
    }

    public void setPropSpecialReviews(List<ProposalSpecialReview> propSpecialReviews) {
        this.propSpecialReviews = propSpecialReviews;
    }

    /*
     * Refreshes the rolodex from the mailingAddressId
     */
    protected void refreshRolodex() {
        NonOrganizationalRolodex rolodex;
        if (mailingAddressId != null) {
            rolodex = (NonOrganizationalRolodex) getBusinessObjectService().findByPrimaryKey(NonOrganizationalRolodex.class, getIdentifierMap(ROLODEX_ID_FIELD_NAME, mailingAddressId));
        } else {
            rolodex = null;
        }
        setRolodex(rolodex);
    }

    /**
     * Build an identifier map for the BOS lookup
     * @param identifierField
     * @param identifierValue
     * @return
     */
    protected Map<String, Object> getIdentifierMap(String identifierField, Object identifierValue) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(identifierField, identifierValue);
        return map;
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
        } else {
            managedLists.add(new ArrayList<S2sOppForms>());
        }
        managedLists.add(opportunities);
        managedLists.add(getS2sUserAttachedForms());
        return managedLists;
    }

    /**
     * Gets the sponsor attribute.
     * 
     * @return Returns the sponsor.
     */
    public Sponsor getSponsor() {
        if (!StringUtils.isEmpty(sponsorCode)) {
            this.refreshReferenceObject("sponsor");
        }
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
     * @param lineToDelete
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
     * @param lineToDelete
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
     * @param lineToDelete
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
    public LegacyNarrativeService getNarrativeService() {
        if (narrativeService == null) {
            narrativeService = KcServiceLocator.getService(LegacyNarrativeService.class);
        }
        return narrativeService;
    }

    /**
     * Sets the narrativeService attribute value.
     * 
     * @param narrativeService The narrativeService to set.`
     */
    public void setNarrativeService(LegacyNarrativeService narrativeService) {
        this.narrativeService = narrativeService;
    }

    public ProposalPersonBiographyService getProposalPersonBiographyService() {
        if (proposalPersonBiographyService == null) {
            proposalPersonBiographyService = KcServiceLocator.getService(ProposalPersonBiographyService.class);
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
        return KcServiceLocator.getService(YnqService.class);
    }

    public String getBudgetStatus() {
        return budgetStatus;
    }

    public void setBudgetStatus(String budgetStatus) {
        this.budgetStatus = budgetStatus;
    }

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
            KcServiceLocator.getService(ProposalBudgetStatusService.class).loadBudgetStatus(this);
        }
        return budgetStatusDescription;
    }

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
        String budgetStatusCompleteCode = this.getParameterService().getParameterValueAsString(BudgetDocument.class, Constants.BUDGET_STATUS_COMPLETE_CODE);
        if (this.getBudgetStatus() != null && budgetStatusCompleteCode != null && this.getBudgetStatus().equals(budgetStatusCompleteCode)) {
            return true;
        }
        return false;
    }

    public boolean isParentProposalComplete() {
        boolean retval = false;
        if (isChild()) {
            try {
                DevelopmentProposal parent = getProposalHierarchyService().lookupParent(this);
                KcServiceLocator.getService(ProposalBudgetStatusService.class).loadBudgetStatus(parent);
                retval = parent.isProposalComplete();
            } catch (ProposalHierarchyException x) {
                // this should never happen   
                throw new IllegalStateException(x);
            }
        }
        return retval;
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

    public Boolean getGrantsGovSelectFlag() {
        return grantsGovSelectFlag;
    }

    public void setGrantsGovSelectFlag(Boolean grantsGovSelectFlag) {
        this.grantsGovSelectFlag = grantsGovSelectFlag;
    }

    public String getProposalStateTypeCode() {
        return proposalStateTypeCode;
    }

    public void setProposalStateTypeCode(String proposalStateTypeCode) {
        this.proposalStateTypeCode = proposalStateTypeCode;
    }

    public ProposalState getProposalState() {
        return proposalState;
    }

    /**
     * 
     * This method returns the linked ProposalDevelopmentDocument.  If there isn't a linked ProposalDevelopmentDocument, then a 
     * new one is created per KRACOEUS-5304.
     * @return
     */
    public ProposalDevelopmentDocument getProposalDocument() {
        if (proposalDocument == null) {
            proposalDocument = new ProposalDevelopmentDocument();
        }
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
                    this.getProposalChangeHistory().put(proposalChangedData.getEditableColumn().getColumnLabel(), new ArrayList<ProposalChangedData>());
                }
                this.getProposalChangeHistory().get(proposalChangedData.getEditableColumn().getColumnLabel()).add(proposalChangedData);
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

    /**
     * In the case where a person is in the proposal twice (Investigator and Key Person),
     * this method's return list contains only the Investigator.
     * 
     * @see org.kuali.coeus.common.budget.framework.core.BudgetParent#getPersonRolodexList()
     */
    public List<PersonRolodex> getPersonRolodexList() {
        ArrayList<PersonRolodex> filtered = new ArrayList<PersonRolodex>();
        for (ProposalPerson person : getProposalPersons()) {
            if (!filtered.contains(person))
                filtered.add(person);
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
        if (s2sOpportunity != null) {
            this.proposalNumberForGG = proposalNumber;
        }
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
        return spon == null && !StringUtils.isEmpty(code) || (spon != null && !StringUtils.equals(spon.getSponsorCode(), code)) && !StringUtils.isEmpty(code);
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
            if (pPerson.getPersonId() != null && pPerson.getProposalPersonRoleId().equals("PI")) {
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
        BusinessObjectService businessObjectService = KcServiceLocator.getService(BusinessObjectService.class);
        LookupableDevelopmentProposal lookupDevProposal = (LookupableDevelopmentProposal) businessObjectService.findByPrimaryKey(LookupableDevelopmentProposal.class, proposalNumberMap);
        if (lookupDevProposal != null) {
            return lookupDevProposal.getSponsor().getOwnedByUnit();
        }
        return "";
    }

    public Integer getParentInvestigatorFlag(String personId, Integer flag) {
        for (ProposalPerson pPerson : this.getProposalPersons()) {
            if (pPerson.getPersonId() != null && pPerson.getPersonId().equals(personId) || pPerson.getRolodexId() != null && pPerson.getRolodexId().equals(Integer.valueOf(personId))) {
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

        return getTitle();
    }

    @Override
    public String getProjectId() {

        return getProposalNumber();
    }

    public void setBudgetChangeHistory(Map<String, List<BudgetChangedData>> budgetChangeHistory) {
        this.budgetChangeHistory = budgetChangeHistory;
    }

    public Map<String, List<BudgetChangedData>> getBudgetChangeHistory() {
        return budgetChangeHistory;
    }

    /*
    This method will update the budget change data history
    */
    public void updateBudgetChangeHistory() {
        budgetChangeHistory = new TreeMap<String, List<BudgetChangedData>>();
        // Arranging Budget Change History   
        if (CollectionUtils.isNotEmpty(this.getBudgetChangedDataList())) {
            for (BudgetChangedData budgetChangedData : this.getBudgetChangedDataList()) {
                if (this.getBudgetChangeHistory().get(budgetChangedData.getEditableColumn().getColumnLabel()) == null) {
                    this.getBudgetChangeHistory().put(budgetChangedData.getEditableColumn().getColumnLabel(), new ArrayList<BudgetChangedData>());
                }
                this.getBudgetChangeHistory().get(budgetChangedData.getEditableColumn().getColumnLabel()).add(budgetChangedData);
            }
        }
    }

    public void setBudgetChangedDataList(List<BudgetChangedData> budgetChangedDataList) {
        this.budgetChangedDataList = budgetChangedDataList;
    }

    public List<BudgetChangedData> getBudgetChangedDataList() {
        return budgetChangedDataList;
    }

    public KrmsRulesContext getKrmsRulesContext() {
        return (KrmsRulesContext) getProposalDocument();
    }

    public String getLastSyncedBudgetDocumentNumber() {
        return lastSyncedBudgetDocumentNumber;
    }

    public void setLastSyncedBudgetDocumentNumber(String lastSyncedBudgetDocumentNumber) {
        this.lastSyncedBudgetDocumentNumber = lastSyncedBudgetDocumentNumber;
    }

/**
 * Gets the agencyRoutingIdentifier attribute.
 * 
 * @return Returns the agencyRoutingIdentifier.
 */
public String getAgencyRoutingIdentifier() {
    return agencyRoutingIdentifier;
}

/**
 * Sets the agencyRoutingIdentifier attribute value.
 * 
 * @param agencyRoutingIdentifier The agencyRoutingIdentifier to set.
 */
public void setAgencyRoutingIdentifier(String agencyRoutingIdentifier) {
    this.agencyRoutingIdentifier = agencyRoutingIdentifier;
}

/**
 * Gets the prevGrantsGovTrackingID attribute.
 * 
 * @return Returns the prevGrantsGovTrackingID.
 */
public String getPrevGrantsGovTrackingID() {
    return prevGrantsGovTrackingID;
}

/**
 * Sets the prevGrantsGovTrackingID attribute value.
 * 
 * @param prevGrantsGovTrackingID The prevGrantsGovTrackingID to set.
 */
public void setPrevGrantsGovTrackingID(String prevGrantsGovTrackingID) {
    this.prevGrantsGovTrackingID = prevGrantsGovTrackingID;
}
   
    public void markNarratives(NarrativeStatus narrativeStatus) {
        if (narrativeStatus != null) {
            for (Narrative narrative : narratives) {
                if (narrative.getReplaceAttachment(GlobalVariables.getUserSession().getPrincipalId())) {
                    narrative.setNarrativeStatus(narrativeStatus);
                    narrative.setModuleStatusCode(narrativeStatus.getCode());
                    narrative.refreshReferenceObject("narrativeStatus");
                    getDataObjectService().save(narrative);
                }
            }
        }
    }

    public void modifyNarrativeStatus(int selectedLine) {
        Narrative narrative = getNarratives().get(selectedLine);
        if (narrative.getReplaceAttachment(GlobalVariables.getUserSession().getPrincipalId())) {
            narrative.refreshReferenceObject("narrativeStatus");
            getDataObjectService().save(narrative);
        }
    }
    public String getAllUnitNumbers() {
        List<String> unitNumbers = new ArrayList<String>();
        List<ProposalPerson> proposalPersons = getProposalPersons();
        for (ProposalPerson proposalPerson : proposalPersons) {
            List<ProposalPersonUnit> proposalPersonUnits = proposalPerson.getUnits();
            for (ProposalPersonUnit proposalPersonUnit : proposalPersonUnits) {
                unitNumbers.add(proposalPersonUnit.getUnitNumber());
            }
        }
        return StringUtils.join(unitNumbers,',');
    }
    public abstract static class AbstractHiddenInHierarchyCustomizer extends FilterByMapDescriptorCustomizer {

        @Override
        public Map<String, ?> getFilterMap() {
            return Collections.singletonMap("hiddenInHierarchy", "N");
        }
    }

    public static class NarrativeCustomizer extends AbstractHiddenInHierarchyCustomizer {

        @Override
        public String getAttributeName() {
            return "narratives";
        }
    }

    public static class ProposalSpecialReviewCustomizer extends AbstractHiddenInHierarchyCustomizer {

        @Override
        public String getAttributeName() {
            return "propSpecialReviews";
        }
    }

    public static class PropScienceKeywordCustomizer extends AbstractHiddenInHierarchyCustomizer {

        @Override
        public String getAttributeName() {
            return "propScienceKeywords";
        }
    }

    public static class ProposalPersonCustomizer extends AbstractHiddenInHierarchyCustomizer {

        @Override
        public String getAttributeName() {
            return "proposalPersons";
        }
    }

    public static class InstituteAttachmentsCustomizer extends FilterByMapDescriptorCustomizer {

        private static final String ATTR_NAME = "narrativeType.narrativeTypeGroup";

        @Override
        public String getAttributeName() {
            return "instituteAttachments";
        }

        @Override
        public Map<String, ?> getFilterMap() {
            final String value = getParameterService().getParameterValueAsString(ProposalDevelopmentDocument.class, ATTR_NAME, "0");
            return Collections.singletonMap("narrativeType.narrativeTypeGroup", value);
        }

        protected ParameterService getParameterService() {
            return KcServiceLocator.getService(ParameterService.class);
        }
    }

    public static class DevelopmentProposalCustomizer extends CompositeDescriptorCustomizer {

        private static final Collection<DescriptorCustomizer> CUSTOMIZERS;

        static {
            final Collection<DescriptorCustomizer> customizers = new ArrayList<DescriptorCustomizer>();
            customizers.add(new NarrativeCustomizer());
            customizers.add(new InstituteAttachmentsCustomizer());
            customizers.add(new ProposalSpecialReviewCustomizer());
            customizers.add(new PropScienceKeywordCustomizer());
            customizers.add(new ProposalPersonCustomizer());
            CUSTOMIZERS = Collections.unmodifiableCollection(customizers);
        }

        @Override
        protected Collection<DescriptorCustomizer> getCustomizers() {
            return CUSTOMIZERS;
        }
    }
    
    /**
     * Gets the s2sUserAttachedForms attribute. 
     * @return Returns the s2sUserAttachedForms.
     */
    public List<S2sUserAttachedForm> getS2sUserAttachedForms() {
        return s2sUserAttachedForms;
    }

    /**
     * Sets the s2sUserAttachedForms attribute value.
     * @param s2sUserAttachedForms The s2sUserAttachedForms to set.
     */
    public void setS2sUserAttachedForms(List<S2sUserAttachedForm> s2sUserAttachedForms) {
        this.s2sUserAttachedForms = s2sUserAttachedForms;
    }

	@Override
	public boolean isProposalBudget() {
		return true;
	}

	@Override
	public BudgetParentDocument<DevelopmentProposal> getDocument() {
		return getProposalDocument();
	}

	@Override
	public Budget getNewBudget() {
		return new ProposalDevelopmentBudgetExt();
	}

	@Override
	public Integer getNextBudgetVersionNumber() {
		return getDocument().getNextBudgetVersionNumber();
	}

	public List<ProposalDevelopmentBudgetExt> getBudgetVersionOverviews() {
		return budgets;
	}

	public void setBudgetVersionOverviews(
			List<ProposalDevelopmentBudgetExt> budgetVersionOverviews) {
		this.budgets = budgetVersionOverviews;
	}

	public List<ProposalDevelopmentBudgetExt> getBudgets() {
		return budgets;
	}

	public void setBudgets(List<ProposalDevelopmentBudgetExt> budgets) {
		this.budgets = budgets;
	}

    @Override
    public ProposalDevelopmentBudgetExt getFinalBudget() {
        if (getBudgets() != null) {
            for (ProposalDevelopmentBudgetExt budget : getBudgets()) {
                if (budget.isFinalVersionFlag()) {
                    return budget;
                }
            }
        }
        return null;
    }

    public DeadlineType getDeadlineTypeRef() {
        return deadlineTypeRef;
    }

    public void setDeadlineTypeRef(DeadlineType deadlineTypeRef) {
        this.deadlineTypeRef = deadlineTypeRef;
    }
    
    public BusinessObjectService getBusinessObjectService() {
        if (businessObjectService == null) {
            businessObjectService = KcServiceLocator.getService(BusinessObjectService.class);
        }

        return businessObjectService;
    }

    public DataObjectService getDataObjectService() {
        if (dataObjectService == null) {
            dataObjectService = KcServiceLocator.getService(DataObjectService.class);
        }

        return dataObjectService;
    }

    /**
     * Finds whether the chosen sponsorCode is in the Sponsor Hierarchy parameter
     * @return Returns true if sponsor code is found in the Sponsor Hierarchy parameter
     */
    public boolean isSponsorProgramAndDivCodeRequired(){
        boolean success = false;
        Collection<String> sponsorHierarchies = getParameterService().getParameterValuesAsString(ProposalDevelopmentDocument.class,Constants.SPONSOR_HIERACHY_REQ_DIV_PROG_CODES);

        for (String sponsorHierarchy : sponsorHierarchies){
            String[] hierarchyStr = sponsorHierarchy.split(",");
            int level = hierarchyStr.length - 1;
            if (getSponsorHierarchyService().isSponsorInHierarchy(getSponsorCode(), hierarchyStr[0], level, hierarchyStr[level])){
                success = true;
                break;
            }
        }
        return success;
    }

    public SponsorHierarchyService getSponsorHierarchyService(){
        if (this.sponsorHierarchyService == null) {
            this.sponsorHierarchyService = KcServiceLocator.getService(SponsorHierarchyService.class);
        }
        return this.sponsorHierarchyService;
    }
}
