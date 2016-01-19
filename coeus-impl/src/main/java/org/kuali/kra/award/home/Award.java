/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.kra.award.home;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.core.BudgetParent;
import org.kuali.coeus.common.budget.framework.core.BudgetParentDocument;
import org.kuali.coeus.common.framework.auth.SystemAuthorizationService;
import org.kuali.coeus.common.framework.auth.perm.Permissionable;
import org.kuali.coeus.common.framework.keyword.KeywordsManager;
import org.kuali.coeus.common.framework.keyword.ScienceKeyword;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.common.framework.rolodex.PersonRolodex;
import org.kuali.coeus.common.framework.sponsor.Sponsor;
import org.kuali.coeus.common.framework.sponsor.Sponsorable;
import org.kuali.coeus.common.framework.type.ActivityType;
import org.kuali.coeus.common.framework.type.ProposalType;
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.coeus.common.framework.unit.UnitService;
import org.kuali.coeus.common.framework.unit.admin.UnitAdministrator;
import org.kuali.coeus.common.framework.version.VersionStatus;
import org.kuali.coeus.common.framework.version.history.VersionHistorySearchBo;
import org.kuali.coeus.common.framework.version.sequence.owner.SequenceOwner;
import org.kuali.coeus.common.permissions.impl.PermissionableKeys;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.AwardAmountInfoService;
import org.kuali.kra.award.AwardTemplateSyncScope;
import org.kuali.kra.award.awardhierarchy.AwardHierarchyService;
import org.kuali.kra.award.awardhierarchy.AwardHierarchyTempObject;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncChange;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncStatus;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncableProperty;
import org.kuali.kra.award.budget.AwardBudgetExt;
import org.kuali.kra.award.budget.AwardBudgetLimit;
import org.kuali.kra.award.budget.AwardBudgetService;
import org.kuali.kra.award.cgb.AwardCgb;
import org.kuali.kra.award.commitments.AwardCostShare;
import org.kuali.kra.award.commitments.AwardFandaRate;
import org.kuali.kra.award.contacts.AwardPerson;
import org.kuali.kra.award.contacts.AwardSponsorContact;
import org.kuali.kra.award.contacts.AwardUnitContact;
import org.kuali.kra.award.customdata.AwardCustomData;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.approvedsubawards.AwardApprovedSubaward;
import org.kuali.kra.award.home.fundingproposal.AwardFundingProposal;
import org.kuali.kra.award.home.keywords.AwardScienceKeyword;
import org.kuali.kra.award.lookup.AwardDocumentStatusConstants;
import org.kuali.kra.award.notesandattachments.attachments.AwardAttachment;
import org.kuali.kra.award.notesandattachments.notes.AwardNotepad;
import org.kuali.kra.award.paymentreports.awardreports.AwardReportTerm;
import org.kuali.kra.award.paymentreports.closeout.AwardCloseout;
import org.kuali.kra.award.paymentreports.paymentschedule.AwardPaymentSchedule;
import org.kuali.kra.award.paymentreports.specialapproval.approvedequipment.AwardApprovedEquipment;
import org.kuali.kra.award.paymentreports.specialapproval.foreigntravel.AwardApprovedForeignTravel;
import org.kuali.kra.award.specialreview.AwardSpecialReview;
import org.kuali.kra.award.timeandmoney.AwardDirectFandADistribution;
import org.kuali.kra.bo.AccountType;
import org.kuali.kra.coi.Disclosurable;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.service.InstitutionalProposalService;
import org.kuali.kra.negotiations.bo.Negotiable;
import org.kuali.kra.negotiations.bo.NegotiationPersonDTO;
import org.kuali.kra.subaward.bo.SubAward;
import org.kuali.kra.timeandmoney.TimeAndMoneyDocumentHistory;
import org.kuali.kra.timeandmoney.document.TimeAndMoneyDocument;
import org.kuali.kra.timeandmoney.service.TimeAndMoneyHistoryService;
import org.kuali.kra.timeandmoney.transactions.AwardTransactionType;
import org.kuali.rice.core.api.CoreApiServiceLocator;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kim.api.role.Role;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.springframework.util.AutoPopulatingList;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

public class Award extends KcPersistableBusinessObjectBase implements KeywordsManager<AwardScienceKeyword>, Permissionable,
        SequenceOwner<Award>, BudgetParent, Sponsorable, Negotiable, Disclosurable {
    public static final String DEFAULT_AWARD_NUMBER = "000000-00000";
    public static final String BLANK_COMMENT = "";
    public static final String ICR_RATE_CODE_NONE = "ICRNONE";
    private static final String NONE = "None";

    private static final String NO_FLAG = "N";
    private static final int TOTAL_STATIC_REPORTS = 5;
    public static final String CLOSE_OUT_REPORT_TYPE_FINANCIAL_REPORT = "1";
    public static final String CLOSE_OUT_REPORT_TYPE_TECHNICAL = "4";
    public static final String CLOSE_OUT_REPORT_TYPE_PATENT = "3";
    public static final String CLOSE_OUT_REPORT_TYPE_PROPERTY = "2";
    public static final String CLOSE_OUT_REPORT_TYPE_INVOICE = "6";
    private static final String DEFAULT_GROUP_CODE_FOR_CENTRAL_ADMIN_CONTACTS = "C";

    public static final String NOTIFICATION_IRB_SPECIAL_REVIEW_LINK_ADDED = "552";
    public static final String NOTIFICATION_IRB_SPECIAL_REVIEW_LINK_DELETED = "553";
    public static final String NOTIFICATION_IACUC_SPECIAL_REVIEW_LINK_ADDED = "554";
    public static final String NOTIFICATION_IACUC_SPECIAL_REVIEW_LINK_DELETED = "555";
    public static final String BUDGET_STATUS = "2";

    /*
     * Used by Current Report to determine if award in Active, Pending, or Hold state.
     */
    private static final String REPORT_STATUSES = "1 3 6";


    private static final long serialVersionUID = 3797220122448310165L;
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(Award.class);
    private static final String AWARD_DOCUMENT = "awardDocument";
    private static final String AWARD_STATUS = "awardStatus";
    private static final String SPONSOR = "sponsor";
    private static final String PRIME_SPONSOR = "primeSponsor";
    private static final String AWARD_NUMBER = "awardNumber";
    private static final String DOCUMENT_NUMBER = "documentNumber";
    private static final String SEQUENCE_NUMBER = "sequenceNumber";
    private static final String AWARD = "Award";
    private static final String CG_2 = "CG2";
    private static final String CG_1 = "CG1";
    private static final String CG_3 = "CG3";
    private static final String AWARD_AMOUNT_INFOS = "awardAmountInfos";
    private static final String ACCOUNT_TYPE_CODE = "accountTypeCode";
    private static final String UNIT_NUMBER = "unitNumber";
    private static final String COLON = ":";
    private Long awardId;
    private AwardDocument awardDocument;
    private String awardNumber;
    private Integer sequenceNumber;
    @AwardSyncableProperty
    private String sponsorCode;
    @AwardSyncableProperty
    private Integer statusCode;
    private AwardStatus awardStatus;
    private String accountNumber;
    private String approvedEquipmentIndicator;
    private String approvedForeignTripIndicator;
    private String subContractIndicator;
    private Date awardEffectiveDate;
    private Date awardExecutionDate;
    private Date beginDate;
    private String costSharingIndicator;
    private String indirectCostIndicator;
    private String modificationNumber;
    private String nsfCode;
    private String paymentScheduleIndicator;
    private String scienceCodeIndicator;
    private String specialReviewIndicator;
    private String sponsorAwardNumber;
    private String transferSponsorIndicator;
    private Integer accountTypeCode;
    private String activityTypeCode;
    private Integer awardTypeCode;
    private AwardType awardType;
    private String cfdaNumber;
    private String documentFundingId;
    private ScaleTwoDecimal preAwardAuthorizedAmount;
    private Date preAwardEffectiveDate;
    private ScaleTwoDecimal preAwardInstitutionalAuthorizedAmount;
    private Date preAwardInstitutionalEffectiveDate;
    private String procurementPriorityCode;
    private String proposalNumber;
    private ScaleTwoDecimal specialEbRateOffCampus;
    private ScaleTwoDecimal specialEbRateOnCampus;
    private String subPlanFlag;
    private String title;
    private String archiveLocation;
    private Date closeoutDate;
    private Integer awardTransactionTypeCode;
    private Date noticeDate;
    private String currentActionComments;
    private String financialAccountDocumentNumber;
    private Date financialAccountCreationDate;
    private String financialChartOfAccountsCode;
    private String awardSequenceStatus;
    private String awardSequenceStatusResult;


    private boolean newVersion;


    private Integer templateCode;
    @AwardSyncable(scopes = { AwardTemplateSyncScope.AWARD_PAGE })
    private String primeSponsorCode;
    @AwardSyncable(impactSourceScopeEmpty = false, scopes = { AwardTemplateSyncScope.PAYMENTS_AND_INVOICES_TAB })
    private String basisOfPaymentCode;
    @AwardSyncable(impactSourceScopeEmpty = false, scopes = { AwardTemplateSyncScope.PAYMENTS_AND_INVOICES_TAB })
    private String methodOfPaymentCode;

    private AwardTemplate awardTemplate;
    private AwardBasisOfPayment awardBasisOfPayment;
    private AwardMethodOfPayment awardMethodOfPayment;
    private AwardTransactionType awardTransactionType;

    private ActivityType activityType;

    private Sponsor sponsor;
    private Sponsor primeSponsor;

    @AwardSyncableList(syncClass = AwardComment.class, syncSourceClass = AwardTemplateComment.class, scopes={AwardTemplateSyncScope.COST_SHARE,AwardTemplateSyncScope.PAYMENTS_AND_INVOICES_TAB,AwardTemplateSyncScope.COMMENTS_TAB}, removeMissingListElementsFromTarget=false)
    private List<AwardComment> awardComments;
    @AwardSyncableList(syncClass = AwardReportTerm.class, syncSourceClass = AwardTemplateReportTerm.class, scopes = {AwardTemplateSyncScope.PAYMENTS_AND_INVOICES_TAB,AwardTemplateSyncScope.REPORTS_TAB})
    private List<AwardReportTerm> awardReportTermItems;
    @AwardSyncableList(syncClass = AwardSponsorTerm.class, syncSourceClass = AwardTemplateTerm.class, scopes = { AwardTemplateSyncScope.TERMS_TAB })
    private List<AwardSponsorTerm> awardSponsorTerms;
    @AwardSyncableList(syncClass = AwardSponsorContact.class, syncSourceClass = AwardTemplateContact.class, scopes = { AwardTemplateSyncScope.SPONSOR_CONTACTS_TAB })
    private List<AwardSponsorContact> sponsorContacts;

    private List<AwardCustomData> awardCustomDataList;
    private List<Boolean> awardCommentHistoryFlags;

    private Map<String, AwardComment> commentMap;
    private List<AwardCostShare> awardCostShares;
    private List<AwardFandaRate> awardFandaRate;
    private List<AwardDirectFandADistribution> awardDirectFandADistributions;

    private List<AwardApprovedSubaward> awardApprovedSubawards;

    private List<AwardScienceKeyword> keywords;

    private List<AwardPerson> projectPersons;
    private List<AwardUnitContact> awardUnitContacts;

    private List<AwardSpecialReview> specialReviews;
    private List<AwardApprovedEquipment> approvedEquipmentItems;
    private List<AwardApprovedForeignTravel> approvedForeignTravelTrips;
    private List<AwardPaymentSchedule> paymentScheduleItems;
    private List<AwardTransferringSponsor> awardTransferringSponsors;
    private List<AwardAmountInfo> awardAmountInfos;
    private List<AwardCloseout> awardCloseoutItems;
    private List<AwardCloseout> awardCloseoutNewItems;
    private List<AwardNotepad> awardNotepads;
    private List<AwardAttachment> awardAttachments;
    private List<AwardSyncChange> syncChanges;
    private List<AwardSyncStatus> syncStatuses;
    private boolean syncChild;

    private List<AwardFundingProposal> fundingProposals;
    private List<AwardFundingProposal> allFundingProposals;

    private List<AwardBudgetLimit> awardBudgetLimits;

    // Additional fields for lookup
    private Unit leadUnit;
    private String unitNumber;

    /* 
     * This is just to provide lookup tool for investigator.
     * We need to set this to satisfy DD validation.
     */
    private transient KcPerson investigator;
    private KcPerson ospAdministrator;
    private String principalInvestigatorName;

    // For award-account integration
    private String icrRateCode;

    private transient boolean awardInMultipleNodeHierarchy;
    private transient boolean awardHasAssociatedTandMOrIsVersioned;

    private transient boolean sponsorNihMultiplePi;

    private transient List<AwardHierarchyTempObject> awardHierarchyTempObjects;

    // transient for award header label
    private transient String docIdStatus;

    private transient String lookupOspAdministratorName;
    private transient AwardAmountInfoService awardAmountInfoService;
    private transient TimeAndMoneyHistoryService timeAndMoneyHistoryService;
    private transient AwardHierarchyService awardHierarchyService;
    private transient SystemAuthorizationService systemAuthorizationService;
    private transient AwardBudgetService awardBudgetService;
    private transient InstitutionalProposalService institutionalProposalService;
    private transient BusinessObjectService businessObjectService;
    private transient UnitService unitService;

    private transient List<AwardUnitContact> centralAdminContacts;
    private List<SubAward> subAwardList;
    
    private transient boolean allowUpdateTimestampToBeReset = true;
    
    private VersionHistorySearchBo versionHistory;
    private transient KcPersonService kcPersonService;

    private List<AwardCgb> awardCgbList;
    
    private transient Integer indexOfAwardAmountInfoForDisplay;
    private String fainId;
    private Integer fedAwardYear;
    private Date fedAwardDate;

    public Award() {
        super();
        initializeAwardWithDefaultValues();
        initializeCollections();
    }

    private void initializeAwardWithDefaultValues() {
        setAwardNumber(DEFAULT_AWARD_NUMBER);
        setSequenceNumber(1);
        setApprovedEquipmentIndicator(NO_FLAG);
        setApprovedForeignTripIndicator(NO_FLAG);
        setSubContractIndicator(NO_FLAG);
        setCostSharingIndicator(NO_FLAG);
        setIdcIndicator(NO_FLAG);
        setPaymentScheduleIndicator(NO_FLAG);
        setScienceCodeIndicator(NO_FLAG);
        setSpecialReviewIndicator(NO_FLAG);
        setTransferSponsorIndicator(NO_FLAG);
        awardComments = new AutoPopulatingList<>(AwardComment.class);
        setCurrentActionComments("");
        setNewVersion(false);
        awardSequenceStatus = VersionStatus.PENDING.name();
        awardSequenceStatusResult = VersionStatus.PENDING.name();
    }


    private Map<String, AwardComment> getCommentMap() {
        if (commentMap == null || getNewVersion()) {
            commentMap = new HashMap<>();
            for (AwardComment ac : awardComments) {
                if (getNewVersion() && ac.getCommentType().getCommentTypeCode().equals(Constants.CURRENT_ACTION_COMMENT_TYPE_CODE))
                { 
                    ac.setComments(BLANK_COMMENT);
                }
                commentMap.put(ac.getCommentType().getCommentTypeCode(), ac);
            }
        }
        return commentMap;
    }

    public Integer getTemplateCode() {
        return templateCode;
    }

    public String getFinancialChartOfAccountsCode() {
        return financialChartOfAccountsCode;
    }

    public void setFinancialChartOfAccountsCode(String financialChartOfAccountsCode) {
        this.financialChartOfAccountsCode = financialChartOfAccountsCode;
    }


    public Long getAwardId() {
        return awardId;
    }

    public void setAwardId(Long awardId) {
        this.awardId = awardId;
    }



    public String getAwardNumber() {
        return awardNumber;
    }

    public void setAwardNumber(String awardNumber) {
        this.awardNumber = awardNumber;
    }


    @Override
    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }


    public int getIndexOfLastAwardAmountInfo() {
        return awardAmountInfos.size() - 1;
    }

    public AwardAmountInfo getLastAwardAmountInfo() {
        return awardAmountInfos.get(getIndexOfLastAwardAmountInfo());
    }

    public int getIndexOfAwardAmountInfoForDisplay() throws WorkflowException {
    	if (indexOfAwardAmountInfoForDisplay != null) {
    		return indexOfAwardAmountInfoForDisplay;
    	}
        AwardAmountInfo aai = getAwardAmountInfoService().fetchLastAwardAmountInfoForAwardVersionAndFinalizedTandMDocumentNumber(this);
        int returnVal = 0;
        int index = 0;
        if (aai.getAwardAmountInfoId() != null && this.isAwardInMultipleNodeHierarchy()) {
            this.refreshReferenceObject(AWARD_AMOUNT_INFOS);
        }
        if (isAwardInitialCopy()) {
            // if it's copied, on initialization we want to return index of last AwardAmountInfo in collection.
            returnVal = getAwardAmountInfos().size() - 1;
        }else {
            for (AwardAmountInfo awardAmountInfo : getAwardAmountInfos()) {
                if (awardAmountInfo.getAwardAmountInfoId() == null && aai.getAwardAmountInfoId() == null) {
                    returnVal = index;
                }else if(awardAmountInfo.getAwardAmountInfoId().equals(aai.getAwardAmountInfoId())) {
                    returnVal = index;
                }else {
                    index++;
                }
            }
        }
        indexOfAwardAmountInfoForDisplay = returnVal;
        return indexOfAwardAmountInfoForDisplay;
    }

    public int getIndexOfAwardAmountInfoForDisplayFromTimeAndMoneyDocNumber(String docNum) throws WorkflowException {
        AwardAmountInfo aai = getAwardAmountInfoService().fetchLastAwardAmountInfoForDocNum(this, docNum);
        int returnVal = 0;
        int index = 0;
        if (aai.getAwardAmountInfoId() != null && this.isAwardInMultipleNodeHierarchy()) {
            this.refreshReferenceObject(AWARD_AMOUNT_INFOS);
        }
        if (isAwardInitialCopy()) {
            // if it's copied, on initialization we want to return index of last AwardAmountInfo in collection.
            returnVal = getAwardAmountInfos().size() - 1;
        }else {
            for (AwardAmountInfo awardAmountInfo : getAwardAmountInfos()) {
                if (awardAmountInfo.getAwardAmountInfoId() == null && aai.getAwardAmountInfoId() == null) {
                    returnVal = index;
                }else if(awardAmountInfo.getAwardAmountInfoId().equals(aai.getAwardAmountInfoId())) {
                    returnVal = index;
                }else {
                    index++;
                }
            }
        }
        return returnVal;
    }

    /**
     * If the Award is copied then initially the AwardAmountInfos will
     * have two entries without AwardAmountInfoId's.  We need to recognize this
     * so we can display the correct data on initialization.
     */
    public boolean isAwardInitialCopy() {
        boolean returnValue = true;
        if (this.getAwardAmountInfos().size() > 1) {
            for (AwardAmountInfo aai : getAwardAmountInfos()) {
                if (aai.getAwardAmountInfoId() != null) {
                    returnValue = false;
                    break;
                }
            }
        }
        return returnValue;
    }

    public AwardAmountInfoService getAwardAmountInfoService() {
        awardAmountInfoService = KcServiceLocator.getService(AwardAmountInfoService.class);
        return awardAmountInfoService;
    }

    public AwardHierarchyService getAwardHierarchyService() {
        if (awardHierarchyService == null) {
            awardHierarchyService = KcServiceLocator.getService(AwardHierarchyService.class);
        }
        return awardHierarchyService;
    }

    public TimeAndMoneyHistoryService getTimeAndMoneyHistoryService() {
        if (timeAndMoneyHistoryService == null) {
            timeAndMoneyHistoryService = KcServiceLocator.getService(TimeAndMoneyHistoryService.class);
        }
        return timeAndMoneyHistoryService;
    }

    public void setAwardAmountInfoService(AwardAmountInfoService awardAmountInfoService) {
        this.awardAmountInfoService = awardAmountInfoService;
    }



    public String getSponsorCode() {
        return sponsorCode;
    }

    public void setSponsorCode(String sponsorCode) {
        this.sponsorCode = sponsorCode;
    }

    public String getAccountTypeDescription() {
        AccountType accountType = getBusinessObjectService().findByPrimaryKey
            (AccountType.class, Collections.singletonMap(ACCOUNT_TYPE_CODE, getAccountTypeCode()));
        if (accountType == null) {
            return "None Selected";
        }else {
            return accountType.getDescription();
        }
    }



    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }


    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }


    public List<AwardApprovedEquipment> getApprovedEquipmentItems() {
        return approvedEquipmentItems;
    }


    public List<AwardUnitContact> getAwardUnitContacts() {
        return awardUnitContacts;
    }

    public AwardPerson getProjectPerson(int index) {
        return projectPersons.get(index);
    }

    public AwardPerson getProjectPerson(String personId) {
        if (!StringUtils.isBlank(personId)) {
            for (AwardPerson awardPerson : this.getProjectPersons()) {
                if (personId.equals(awardPerson.getPersonId())) {
                    return awardPerson;
                }
            }
        }
        return null;
    }

    public AwardPerson getProjectPerson(Integer rolodexId) {
        if (rolodexId != null) {
            for (AwardPerson awardPerson : this.getProjectPersons()) {
                if (rolodexId.equals(awardPerson.getRolodexId())) {
                    return awardPerson;
                }
            }
        }
        return null;
    }


    public List<AwardPerson> getProjectPersons() {
        return projectPersons;
    }


    public List<AwardPerson> getProjectPersonsSorted() {
        List<AwardPerson> aList = new ArrayList<>();
        if (this.getPrincipalInvestigator() != null) {
            aList.add(this.getPrincipalInvestigator());
        }
        aList.addAll(this.getMultiplePis());
        aList.addAll(this.getCoInvestigators());
        aList.addAll(this.getKeyPersons());
        return aList;
    }

    public List<AwardPerson> getInvestigators() {
        return projectPersons.stream().filter(person -> person.isPrincipalInvestigator() || person.isCoInvestigator()).sorted().collect(Collectors.toList());
    }

    public List<AwardPerson> getCoInvestigators() {
        return projectPersons.stream().filter(AwardPerson::isCoInvestigator).sorted().collect(Collectors.toList());
    }

    public List<AwardPerson> getMultiplePis() {
        if (isSponsorNihMultiplePi()) {
            return projectPersons.stream().filter(AwardPerson::isMultiplePi).sorted().collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    public List<AwardPerson> getKeyPersons() {
        return projectPersons.stream().filter(AwardPerson::isKeyPerson).sorted().collect(Collectors.toList());
    }

    public int getTotalUnitCount() {
        int count = 0;
        for (AwardPerson person : projectPersons)
            count += person.getUnits().size();
        return count;
    }


    public int getAwardContactsCount() {
        return awardUnitContacts.size();
    }


    public int getApprovedEquipmentItemCount() {
        return approvedEquipmentItems.size();
    }


    public int getApprovedForeignTravelTripCount() {
        return approvedForeignTravelTrips.size();
    }


    public List<AwardApprovedForeignTravel> getApprovedForeignTravelTrips() {
        return approvedForeignTravelTrips;
    }

    public void setAwardUnitContacts(List<AwardUnitContact> awardUnitContacts) {
        this.awardUnitContacts = awardUnitContacts;
    }

    public void setApprovedEquipmentItems(List<AwardApprovedEquipment> awardApprovedEquipmentItems) {
        this.approvedEquipmentItems = awardApprovedEquipmentItems;
    }

    public void setApprovedForeignTravelTrips(List<AwardApprovedForeignTravel> approvedForeignTravelTrips) {
        this.approvedForeignTravelTrips = approvedForeignTravelTrips;
    }


    public String getApprovedEquipmentIndicator() {
        return approvedEquipmentIndicator;
    }

    public void setApprovedEquipmentIndicator(String approvedEquipmentIndicator) {
        this.approvedEquipmentIndicator = approvedEquipmentIndicator;
    }



    public String getApprovedForeignTripIndicator() {
        return approvedForeignTripIndicator;
    }

    public void setApprovedForeignTripIndicator(String approvedForeignTripIndicator) {
        this.approvedForeignTripIndicator = approvedForeignTripIndicator;
    }



    public String getSubContractIndicator() {
        return subContractIndicator;
    }

    public void setSubContractIndicator(String subContractIndicator) {
        this.subContractIndicator = subContractIndicator;
    }



    public Date getAwardEffectiveDate() {
        return awardEffectiveDate;
    }

    public void setAwardEffectiveDate(Date awardEffectiveDate) {
        this.awardEffectiveDate = awardEffectiveDate;
    }



    public Date getAwardExecutionDate() {
        return awardExecutionDate;
    }

    public void setAwardExecutionDate(Date awardExecutionDate) {
        this.awardExecutionDate = awardExecutionDate;
    }



    public Date getBeginDate() {
        return beginDate;
    }

    public Date getProjectEndDate() {
        return awardAmountInfos.get(0).getFinalExpirationDate();
    }

    public void setProjectEndDate(Date date) {
        this.awardAmountInfos.get(0).setFinalExpirationDate(date);
    }

    public Date getObligationExpirationDate() {
        return getLastAwardAmountInfo().getObligationExpirationDate();
    }

    public void setObligationExpirationDate(Date date) {
        this.awardAmountInfos.get(0).setObligationExpirationDate(date);
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }



    public String getCostSharingIndicator() {
        return costSharingIndicator;
    }

    public void setCostSharingIndicator(String costSharingIndicator) {
        this.costSharingIndicator = costSharingIndicator;
    }


    public List<AwardFundingProposal> getFundingProposals() {
        return fundingProposals;
    }

    /**
     * 
     * For ease of use in JSP and tag files; the getter method uses acronym instead of full meaning.
     * idcIndicator is an acronym. Its full meaning is Indirect Cost Indicator 
     */
    public String getIdcIndicator() {
        return indirectCostIndicator;
    }

    /**
     * 
     * For ease of use in JSP and tag files; the setter method uses acronym instead of full meaning.
     * idcIndicator is an acronym. Its full meaning is Indirect Cost Indicator
     */
    public void setIdcIndicator(String indirectCostIndicator) {
        this.indirectCostIndicator = indirectCostIndicator;
    }



    public String getModificationNumber() {
        return modificationNumber;
    }

    public void setModificationNumber(String modificationNumber) {
        this.modificationNumber = modificationNumber;
    }

    public String getNsfCode() {
        return nsfCode;
    }

    public void setNsfCode(String nsfCode) {
        this.nsfCode = nsfCode;
    }



    public String getPaymentScheduleIndicator() {
        return paymentScheduleIndicator;
    }

    public void setPaymentScheduleIndicator(String paymentScheduleIndicator) {
        this.paymentScheduleIndicator = paymentScheduleIndicator;
    }



    public String getScienceCodeIndicator() {
        return scienceCodeIndicator;
    }

    public void setScienceCodeIndicator(String scienceCodeIndicator) {
        this.scienceCodeIndicator = scienceCodeIndicator;
    }



    public String getSpecialReviewIndicator() {
        return specialReviewIndicator;
    }

    public void setSpecialReviewIndicator(String specialReviewIndicator) {
        this.specialReviewIndicator = specialReviewIndicator;
    }

    public String getSponsorAwardNumber() {
        return sponsorAwardNumber;
    }

    public void setSponsorAwardNumber(String sponsorAwardNumber) {
        this.sponsorAwardNumber = sponsorAwardNumber;
    }



    public String getTransferSponsorIndicator() {
        return transferSponsorIndicator;
    }

    public String getUnitName() {
        Unit leadUnit = getLeadUnit();
        return leadUnit != null ? leadUnit.getUnitName() : null;
    }

    public String getUnitNumber() {
        return unitNumber;
    }


    public String getLeadUnitNumber() {
        return getUnitNumber();
    }

    public void setTransferSponsorIndicator(String transferSponsorIndicator) {
        this.transferSponsorIndicator = transferSponsorIndicator;
    }


    public Integer getAccountTypeCode() {
        return accountTypeCode;
    }

    public void setAccountTypeCode(Integer accountTypeCode) {
        this.accountTypeCode = accountTypeCode;
    }



    public String getActivityTypeCode() {
        return activityTypeCode;
    }

    public void setActivityTypeCode(String activityTypeCode) {
        this.activityTypeCode = activityTypeCode;
    }



    public Integer getAwardTypeCode() {
        return awardTypeCode;
    }

    public void setAwardTypeCode(Integer awardTypeCode) {
        this.awardTypeCode = awardTypeCode;
    }

    /**
     * 
     * cfdaNumber is an acronym. Its full meaning is Catalog of Federal Domestic Assistance
     */
    public String getCfdaNumber() {
        return cfdaNumber;
    }

    /**
     * 
     * cfdaNumber is an acronym. Its full meaning is Catalog of Federal Domestic Assistance
     */
    public void setCfdaNumber(String cfdaNumber) {
        this.cfdaNumber = cfdaNumber;
    }


    public String getDocumentFundingId() {
        return documentFundingId;
    }

    public void setDocumentFundingId(String documentFundingId) {
        this.documentFundingId = documentFundingId;
    }


    public KcPerson getOspAdministrator() {
        for (AwardUnitContact contact : getCentralAdminContacts()) {
            if (contact.isOspAdministrator()) {
                ospAdministrator = contact.getPerson();
                break;
            }
        }
        return ospAdministrator;
    }


    public String getOspAdministratorName() {
        KcPerson ospAdministrator = getOspAdministrator();
        return ospAdministrator != null ? ospAdministrator.getFullName() : null;
    }


    public ScaleTwoDecimal getPreAwardAuthorizedAmount() {
        return preAwardAuthorizedAmount;
    }

    /**
     * For negative values, this method makes the number positive by dropping the negative sign.
     */
    public void setPreAwardAuthorizedAmount(ScaleTwoDecimal preAwardAuthorizedAmount) {
        if (preAwardAuthorizedAmount != null && preAwardAuthorizedAmount.isNegative()) {
            this.preAwardAuthorizedAmount = ScaleTwoDecimal.ZERO.subtract(preAwardAuthorizedAmount);
        }
        else {
            this.preAwardAuthorizedAmount = preAwardAuthorizedAmount;
        }
    }

    public Date getPreAwardEffectiveDate() {
        return preAwardEffectiveDate;
    }

    public void setPreAwardEffectiveDate(Date preAwardEffectiveDate) {
        this.preAwardEffectiveDate = preAwardEffectiveDate;
    }

    public String getProcurementPriorityCode() {
        return procurementPriorityCode;
    }

    public void setProcurementPriorityCode(String procurementPriorityCode) {
        this.procurementPriorityCode = procurementPriorityCode;
    }

    public String getProposalNumber() {
        return proposalNumber;
    }

    public void setProposalNumber(String proposalNumber) {
        this.proposalNumber = proposalNumber;
    }

    public ScaleTwoDecimal getSpecialEbRateOffCampus() {
        return specialEbRateOffCampus;
    }

    public void setSpecialEbRateOffCampus(ScaleTwoDecimal specialEbRateOffCampus) {
        this.specialEbRateOffCampus = specialEbRateOffCampus;
    }

    public ScaleTwoDecimal getSpecialEbRateOnCampus() {
        return specialEbRateOnCampus;
    }

    public void setSpecialEbRateOnCampus(ScaleTwoDecimal specialEbRateOnCampus) {
        this.specialEbRateOnCampus = specialEbRateOnCampus;
    }

    public String getSubPlanFlag() {
        return subPlanFlag;
    }

    public void setSubPlanFlag(String subPlanFlag) {
        this.subPlanFlag = subPlanFlag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArchiveLocation() {
        return archiveLocation;
    }

    public void setArchiveLocation(String archiveLocation) {
        this.archiveLocation = archiveLocation;
    }

    public Date getCloseoutDate() {
        return closeoutDate;
    }

    public void setCloseoutDate(Date closeoutDate) {
        this.closeoutDate = closeoutDate;
    }

    public Integer getAwardTransactionTypeCode() {
        return awardTransactionTypeCode;
    }

    public void setAwardTransactionTypeCode(Integer awardTransactionTypeCode) {
        this.awardTransactionTypeCode = awardTransactionTypeCode;
    }

    public Date getNoticeDate() {
        return noticeDate;
    }

    public void setNoticeDate(Date noticeDate) {
        if (getNewVersion()) {
            this.noticeDate = null;
        } else {
            this.noticeDate = noticeDate;
        }
    }

    public String getCurrentActionComments() {
        return currentActionComments;
    }

    public void setCurrentActionComments(String currentActionComments) {
        if (getNewVersion())
        {
            this.currentActionComments = BLANK_COMMENT;
        }
        else
        {
            this.currentActionComments = currentActionComments;
        }
    }

    public void setNewVersion (boolean newVersion)
    {
        this.newVersion = newVersion;
        if (this.newVersion)
        {
            commentMap = getCommentMap();
            setCurrentActionComments(BLANK_COMMENT);
            setNoticeDate(null);
        }
    }

    public boolean getNewVersion ()
    {
        return this.newVersion;
    }

    public AwardTransactionType getAwardTransactionType() {
        return awardTransactionType;
    }

    public void setAwardTransactionType(AwardTransactionType awardTransactionType) {
        this.awardTransactionType = awardTransactionType;
    }

    public String getFinancialAccountDocumentNumber() {
        return financialAccountDocumentNumber;
    }

    public void setFinancialAccountDocumentNumber(String financialAccountDocumentNumber) {
        this.financialAccountDocumentNumber = financialAccountDocumentNumber;
    }

    public Date getFinancialAccountCreationDate() {
        return financialAccountCreationDate;
    }

    public void setFinancialAccountCreationDate(Date financialAccountCreationDate) {
        this.financialAccountCreationDate = financialAccountCreationDate;
    }

    public AwardDocument getAwardDocument() {
        if (awardDocument == null) {
            this.refreshReferenceObject(AWARD_DOCUMENT);
        }
        return awardDocument;
    }
    
    public String getAwardDocumentUrl() {
        return getAwardDocument().buildForwardUrl();
    }

    public void setAwardDocument(AwardDocument awardDocument) {
        this.awardDocument = awardDocument;
    }

    public List<AwardComment> getAwardComments() {
        return awardComments;
    }

    public void setAwardComments(List<AwardComment> awardComments) {
        this.awardComments = awardComments;
    }

    public List<AwardCostShare> getAwardCostShares() {
        return awardCostShares;
    }

    public void setAwardCostShares(List<AwardCostShare> awardCostShares) {
        this.awardCostShares = awardCostShares;
    }

    public List<AwardApprovedSubaward> getAwardApprovedSubawards() {
        return awardApprovedSubawards;
    }

    public void setAwardApprovedSubawards(List<AwardApprovedSubaward> awardApprovedSubawards) {
        this.awardApprovedSubawards = awardApprovedSubawards;
    }

    public AwardComment getAwardCostShareComment() {
        return getAwardCommentByType(Constants.COST_SHARE_COMMENT_TYPE_CODE, Constants.AWARD_COMMENT_INCLUDE_IN_CHECKLIST, true);
    }

    public AwardComment getawardPreAwardSponsorAuthorizationComment() {
        return getAwardCommentByType( Constants.PREAWARD_SPONSOR_AUTHORIZATION_COMMENT_TYPE_CODE,Constants.AWARD_COMMENT_EXCLUDE_FROM_CHECKLIST, true );
    }

    public AwardComment getawardPreAwardInstitutionalAuthorizationComment() {
        return getAwardCommentByType( Constants.PREAWARD_INSTITUTIONAL_AUTHORIZATION_COMMENT_TYPE_CODE,Constants.AWARD_COMMENT_EXCLUDE_FROM_CHECKLIST, true );
    }

    public AwardComment getAwardFandaRateComment() {
        return getAwardCommentByType(Constants.FANDA_RATE_COMMENT_TYPE_CODE, Constants.AWARD_COMMENT_INCLUDE_IN_CHECKLIST, true);
    }

    public AwardComment getAwardPaymentAndInvoiceRequirementsComments() {
        return getAwardCommentByType( Constants.PAYMENT_AND_INVOICES_COMMENT_TYPE_CODE,Constants.AWARD_COMMENT_INCLUDE_IN_CHECKLIST, true );
    }

    public AwardComment getAwardBenefitsRateComment() {
        return getAwardCommentByType(Constants.BENEFITS_RATES_COMMENT_TYPE_CODE, Constants.AWARD_COMMENT_INCLUDE_IN_CHECKLIST, true);
    }

    public AwardComment getAwardGeneralComments() {
        return getAwardCommentByType(Constants.GENERAL_COMMENT_TYPE_CODE, Constants.AWARD_COMMENT_INCLUDE_IN_CHECKLIST, true);
    }

    public AwardComment getAwardFiscalReportComments() {
        return getAwardCommentByType(Constants.FISCAL_REPORT_COMMENT_TYPE_CODE, Constants.AWARD_COMMENT_INCLUDE_IN_CHECKLIST, true);
    }

    public AwardComment getAwardCurrentActionComments() {
        return getAwardCommentByType( Constants.CURRENT_ACTION_COMMENT_TYPE_CODE,Constants.AWARD_COMMENT_EXCLUDE_FROM_CHECKLIST, true );
    }

    public AwardComment getAwardIntellectualPropertyComments() {
        return getAwardCommentByType( Constants.INTELLECTUAL_PROPERTY_COMMENT_TYPE_CODE,Constants.AWARD_COMMENT_EXCLUDE_FROM_CHECKLIST, true );
    }

    public AwardComment getAwardProcurementComments() {
        return getAwardCommentByType(Constants.PROCUREMENT_COMMENT_TYPE_CODE, Constants.AWARD_COMMENT_INCLUDE_IN_CHECKLIST, true);
    }

    public AwardComment getAwardPropertyComments() {
        return getAwardCommentByType(Constants.PROPERTY_COMMENT_TYPE_CODE, Constants.AWARD_COMMENT_INCLUDE_IN_CHECKLIST, true);
    }

    public AwardComment getAwardSpecialRate() {
        return getAwardCommentByType(Constants.SPECIAL_RATE_COMMENT_TYPE_CODE, Constants.AWARD_COMMENT_EXCLUDE_FROM_CHECKLIST, true);
    }

    public AwardComment getAwardSpecialReviewComments() {
        return getAwardCommentByType( Constants.SPECIAL_REVIEW_COMMENT_TYPE_CODE,Constants.AWARD_COMMENT_EXCLUDE_FROM_CHECKLIST, true );
    }

    public AwardComment getawardProposalSummary() {
        return getAwardCommentByType( Constants.PROPOSAL_SUMMARY_COMMENT_TYPE_CODE,Constants.AWARD_COMMENT_EXCLUDE_FROM_CHECKLIST, true );
    }

    public AwardComment getawardProposalComments() {
        return getAwardCommentByType(Constants.PROPOSAL_COMMENT_TYPE_CODE, Constants.AWARD_COMMENT_EXCLUDE_FROM_CHECKLIST, true);
    }

    public AwardComment getAwardProposalIPReviewComment() {
        return getAwardCommentByType( Constants.PROPOSAL_IP_REVIEW_COMMENT_TYPE_CODE,Constants.AWARD_COMMENT_EXCLUDE_FROM_CHECKLIST, true );
    }

    public AwardComment getAwardCommentByType(String awardCommentTypeCode, Boolean checklistPrintFlag, boolean createNew) {
        AwardCommentFactory awardCommentFactory = new AwardCommentFactory();
        AwardComment awardComment = getCommentMap().get(awardCommentTypeCode);
        if ((awardComment == null && createNew)) {
            awardComment = awardCommentFactory.createAwardComment(awardCommentTypeCode, (checklistPrintFlag != null && checklistPrintFlag));
            add(awardComment);
            commentMap.put(awardComment.getCommentType().getCommentTypeCode(), awardComment);
        }
        return awardComment;
    }

    public AwardSponsorTerm getAwardSponsorTermByTemplateTerm(AwardTemplateTerm templateTerm, boolean createNew) {
        AwardSponsorTerm result = null;
        for (AwardSponsorTerm term : this.getAwardSponsorTerms()) {
            if (term.getSponsorTermId().equals(templateTerm.getSponsorTermId())) {
                result = term;
                break;
            }
        }
        if (result == null && createNew) {
            result = new AwardSponsorTerm();
            result.setSponsorTermId(templateTerm.getSponsorTermId());
            result.setSponsorTerm(templateTerm.getSponsorTerm());
        }
        return result;
    }

    public ScaleTwoDecimal getTotalCostShareCommitmentAmount() {
        return getTotalAmount(awardCostShares);
    }

    public ScaleTwoDecimal getTotalCostShareMetAmount() {
        ScaleTwoDecimal returnVal = ScaleTwoDecimal.ZERO;
        for (AwardCostShare awardCostShare : awardCostShares) {
             ScaleTwoDecimal amount = awardCostShare.getCostShareMet() != null ? awardCostShare.getCostShareMet() : ScaleTwoDecimal.ZERO;
            returnVal = returnVal.add(amount);
        }
        return returnVal;
    }

    public ScaleTwoDecimal getTotalDirectFandADistributionDirectCostAmount() {
        ScaleTwoDecimal returnVal = ScaleTwoDecimal.ZERO;
        for (AwardDirectFandADistribution awardDirectFandADistribution : awardDirectFandADistributions) {
            ScaleTwoDecimal amount;
            if (awardDirectFandADistribution.getDirectCost() != null) {
                amount = awardDirectFandADistribution.getDirectCost();
             }else {
                amount = ScaleTwoDecimal.ZERO;
            }
            returnVal = returnVal.add(amount);
        }
        return returnVal;
    }

    public ScaleTwoDecimal getTotalDirectFandADistributionIndirectCostAmount() {
        ScaleTwoDecimal returnVal = ScaleTwoDecimal.ZERO;
        for (AwardDirectFandADistribution awardDirectFandADistribution : awardDirectFandADistributions) {
            ScaleTwoDecimal amount;
            if (awardDirectFandADistribution.getIndirectCost() != null) {
                amount = awardDirectFandADistribution.getIndirectCost();
             }else {
                amount = ScaleTwoDecimal.ZERO;
            }
            returnVal = returnVal.add(amount);
        }
        return returnVal;
    }

    public ScaleTwoDecimal getTotalDirectFandADistributionAnticipatedCostAmount() {
        ScaleTwoDecimal returnVal = ScaleTwoDecimal.ZERO;
        returnVal = returnVal.add(getTotalDirectFandADistributionDirectCostAmount());
        returnVal = returnVal.add(getTotalDirectFandADistributionIndirectCostAmount());
        return returnVal;
    }

    public ScaleTwoDecimal getTotalApprovedSubawardAmount() {
        return getTotalAmount(awardApprovedSubawards);
    }

    public ScaleTwoDecimal getTotalApprovedEquipmentAmount() {
        return getTotalAmount(approvedEquipmentItems);
    }

    public ScaleTwoDecimal getTotalApprovedApprovedForeignTravelAmount() {
        return getTotalAmount(approvedForeignTravelTrips);
    }

    public List<AwardFandaRate> getAwardFandaRate() {
        return awardFandaRate;
    }

    public void setAwardFandaRate(List<AwardFandaRate> awardFandaRate) {
        this.awardFandaRate = awardFandaRate;
    }

    @Override
    public List<AwardScienceKeyword> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<AwardScienceKeyword> keywords) {
        this.keywords = keywords;
    }

    public void setLeadUnit(Unit leadUnit) {
        this.leadUnit = leadUnit;
        this.unitNumber = leadUnit != null ? leadUnit.getUnitNumber() : null;
    }

    public void setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber;
    }

    public void addKeyword(ScienceKeyword scienceKeyword) {
        AwardScienceKeyword awardScienceKeyword = new AwardScienceKeyword(getAwardId(), scienceKeyword);
        getKeywords().add(awardScienceKeyword);
    }

    public AwardScienceKeyword getKeyword(int index) {
        return getKeywords().get(index);
    }

    public void setSpecialReviews(List<AwardSpecialReview> awardSpecialReviews) {
        this.specialReviews = awardSpecialReviews;
    }

    public void addSpecialReview(AwardSpecialReview specialReview) {
        specialReview.setSequenceOwner(this);
        getSpecialReviews().add(specialReview);
    }

    public AwardSpecialReview getSpecialReview(int index) {
        return getSpecialReviews().get(index);
    }

    public List<AwardSpecialReview> getSpecialReviews() {
        return specialReviews;
    }

    public void add(AwardApprovedEquipment approvedEquipmentItem) {
        approvedEquipmentItems.add(0, approvedEquipmentItem);
        approvedEquipmentItem.setAward(this);
    }

    public void add(AwardFandaRate fandaRate) {
        awardFandaRate.add(fandaRate);
        fandaRate.setAward(this);
    }

    public void add(AwardSpecialReview awardSpecialReview) {
        specialReviews.add(awardSpecialReview);
        awardSpecialReview.setSequenceOwner(this);
    }

    public void add(AwardSponsorContact awardSponsorContact) {
        sponsorContacts.add(awardSponsorContact);
        awardSponsorContact.setAward(this);
    }

    public void add(AwardReportTerm awardReportTerm) {
        awardReportTermItems.add(awardReportTerm);
        awardReportTerm.setAward(this);
    }

    public void add(AwardCloseout awardCloseoutItem) {
        awardCloseoutNewItems.clear();
        if(awardCloseoutItems != null && awardCloseoutItems.size() > TOTAL_STATIC_REPORTS){
            for(int i = TOTAL_STATIC_REPORTS ;i < awardCloseoutItems.size() ; i++){
                awardCloseoutNewItems.add(awardCloseoutItems.get(i));
            }
        }
        awardCloseoutItems.removeAll(awardCloseoutNewItems);
        awardCloseoutNewItems.add(awardCloseoutItem);
        Collections.sort(awardCloseoutNewItems, Comparator.comparing(AwardCloseout::getCloseoutReportName));
        awardCloseoutItems.addAll(awardCloseoutNewItems);
        awardCloseoutItem.setAward(this);
    }

    public void addStaticCloseout(AwardCloseout awardCloseoutItem) {
        awardCloseoutItems.add(awardCloseoutItem);
        awardCloseoutItem.setAward(this);
    }

    public void add(AwardUnitContact awardUnitContact) {
        awardUnitContacts.add(awardUnitContact);
        awardUnitContact.setAward(this);
    }

    public void add(InstitutionalProposal institutionalProposal) {
        if (institutionalProposal != null) {
            AwardFundingProposal afp = new AwardFundingProposal(this, institutionalProposal);
            fundingProposals.add(afp);
            allFundingProposals.add(afp);
            institutionalProposal.add(afp);
        }
    }

    public void addSponsorContact(AwardSponsorContact awardSponsorContact) {
        sponsorContacts.add(awardSponsorContact);
        awardSponsorContact.setAward(this);
    }

    public void add(AwardPerson projectPerson) {
        projectPersons.add(projectPerson);
        projectPerson.setAward(this);
    }

    public void add(AwardPaymentSchedule paymentScheduleItem) {
        paymentScheduleItems.add(paymentScheduleItem);
        paymentScheduleItem.setAward(this);
    }

    public void addAwardTransferringSponsor(Sponsor sponsor) {
        AwardTransferringSponsor awardTransferringSponsor = new AwardTransferringSponsor(this, sponsor);
        awardTransferringSponsors.add(0, awardTransferringSponsor);
    }

    protected void initializeCollections() {
        setAwardCostShares(new ArrayList<>());
        setAwardComments(new ArrayList<>());
        awardApprovedSubawards = new ArrayList<>();
        setAwardFandaRate(new ArrayList<>());
        setAwardReportTermItems(new ArrayList<>());
        keywords = new ArrayList<>();
        specialReviews = new ArrayList<>();
        approvedEquipmentItems = new ArrayList<>();
        approvedForeignTravelTrips = new ArrayList<>();
        setAwardSponsorTerms(new ArrayList<>());
        paymentScheduleItems = new ArrayList<>();
        awardTransferringSponsors = new ArrayList<>();
        awardDirectFandADistributions = new ArrayList<>();
        awardCustomDataList = new ArrayList<>();
        awardCloseoutItems = new ArrayList<>();
        awardCloseoutNewItems = new ArrayList<>();
        awardNotepads = new ArrayList<>();
        initializeAwardAmountInfoObjects();
        projectPersons = new ArrayList<>();
        awardUnitContacts = new ArrayList<>();
        sponsorContacts = new ArrayList<>();
        awardBudgetLimits = new ArrayList<>();
        awardCgbList = new ArrayList<>();

        fundingProposals = new ArrayList<>();
        allFundingProposals = new ArrayList<>();
        initializeAwardHierarchyTempObjects();

        syncChanges = new ArrayList<>();
        syncStatuses = new ArrayList<>();
        subAwardList = new ArrayList<>();
        currentVersionBudgets = new ArrayList<>();
    }

    public void initializeAwardAmountInfoObjects() {
        awardAmountInfos = new ArrayList<>();
        AwardAmountInfo awardAmountInfo = new AwardAmountInfo();
        awardAmountInfo.setAward(this);
        awardAmountInfo.setOriginatingAwardVersion(1);
        awardAmountInfos.add(awardAmountInfo);
    }

    public void initializeAwardHierarchyTempObjects() {
        awardHierarchyTempObjects = new AutoPopulatingList<>(AwardHierarchyTempObject.class);
    }

    public ScaleTwoDecimal getPreAwardInstitutionalAuthorizedAmount() {
        return preAwardInstitutionalAuthorizedAmount;
    }

    /**
     * For negative values, this method makes the number positive by dropping the negative sign.
     */
    public void setPreAwardInstitutionalAuthorizedAmount(ScaleTwoDecimal preAwardInstitutionalAuthorizedAmount) {
        // if preAwardInstitutionalAuthorizedAmount is negative, make it positive
        if (preAwardInstitutionalAuthorizedAmount != null && preAwardInstitutionalAuthorizedAmount.isNegative()) {
            this.preAwardInstitutionalAuthorizedAmount = ScaleTwoDecimal.ZERO.subtract(preAwardInstitutionalAuthorizedAmount);
        }
        else {
            this.preAwardInstitutionalAuthorizedAmount = preAwardInstitutionalAuthorizedAmount;
        }
    }

    public Date getPreAwardInstitutionalEffectiveDate() {
        return preAwardInstitutionalEffectiveDate;
    }

    public void setPreAwardInstitutionalEffectiveDate(Date preAwardInstitutionalEffectiveDate) {
        this.preAwardInstitutionalEffectiveDate = preAwardInstitutionalEffectiveDate;
    }

    public void add(AwardCostShare awardCostShare) {
        awardCostShares.add(awardCostShare);
        awardCostShare.setAward(this);
    }

    public void add(AwardApprovedSubaward awardApprovedSubaward) {
        awardApprovedSubawards.add(awardApprovedSubaward);
        awardApprovedSubaward.setAward(this);
    }

    public void add(AwardComment awardComment) {
        awardComments.add(awardComment);
        awardComment.setAward(this);
    }

    public void addTemplateComments(List<AwardTemplateComment> awardTemplateComments) {
        AwardCommentFactory awardCommentFactory = new AwardCommentFactory();
        for (AwardTemplateComment awardTemplateComment : awardTemplateComments) {
            AwardComment testAwardComment = getCommentMap().get(awardTemplateComment.getCommentTypeCode());
            if (testAwardComment == null) {
                AwardComment awardComment = awardCommentFactory.createAwardComment(awardTemplateComment.getCommentTypeCode(),
                        awardTemplateComment.getChecklistPrintFlag());
                awardComment.setComments(awardTemplateComment.getComments());
                add(awardComment);
                commentMap.put(awardComment.getCommentType().getCommentTypeCode(), awardComment);
            }else {
                testAwardComment.setComments(awardTemplateComment.getComments());
            }
        }
    }

    public void add(AwardSponsorTerm awardSponsorTerm) {
        awardSponsorTerms.add(awardSponsorTerm);
        awardSponsorTerm.setAward(this);
    }

    public void addTemplateTerms(List<AwardTemplateTerm> awardTemplateTerms) {
        List<AwardSponsorTerm> tempAwardSponsorTerms = awardTemplateTerms.stream().map(awardTemplateTerm -> new AwardSponsorTerm(awardTemplateTerm.getSponsorTermId(), awardTemplateTerm.getSponsorTerm())).collect(Collectors.toList());
        setAwardSponsorTerms(tempAwardSponsorTerms);
    }

    public void add(AwardDirectFandADistribution awardDirectFandADistribution) {
        awardDirectFandADistributions.add(awardDirectFandADistribution);
        awardDirectFandADistribution.setAward(this);
        awardDirectFandADistribution.setBudgetPeriod(awardDirectFandADistributions.size());
    }

    public void add(int index, AwardDirectFandADistribution awardDirectFandADistribution) {
        awardDirectFandADistributions.add(index, awardDirectFandADistribution);
        awardDirectFandADistribution.setAward(this);
        awardDirectFandADistribution.setBudgetPeriod(index + 1);
        updateDirectFandADistributionBudgetPeriods(index + 1);
    }

    public void add(AwardNotepad awardNotepad) {
        awardNotepad.setEntryNumber(awardNotepads.size() + 1);
        awardNotepad.setAwardNumber(this.getAwardNumber());
        awardNotepads.add(awardNotepad);
        awardNotepad.setAward(this);
    }

    public void updateDirectFandADistributionBudgetPeriods(int index) {
        for (int newIndex = index; newIndex < awardDirectFandADistributions.size(); newIndex++) {
            awardDirectFandADistributions.get(newIndex).setBudgetPeriod(newIndex + 1);
        }
    }

    ScaleTwoDecimal getTotalAmount(List<? extends ValuableItem> valuableItems) {
        ScaleTwoDecimal returnVal = ScaleTwoDecimal.ZERO;
        for (ValuableItem item : valuableItems) {
            ScaleTwoDecimal amount = item.getAmount() != null ? item.getAmount() : ScaleTwoDecimal.ZERO;
            returnVal = returnVal.add(amount);
        }
        return returnVal;
    }

    public List<AwardSponsorTerm> getAwardSponsorTerms() {
        return awardSponsorTerms;
    }


    public AwardStatus getAwardStatus() {
        if (awardStatus == null && statusCode != null) {
            refreshReferenceObject(AWARD_STATUS);
        }
        return awardStatus;
    }

    public void setAwardSponsorTerms(List<AwardSponsorTerm> awardSponsorTerms) {
        this.awardSponsorTerms = awardSponsorTerms;
    }

    /**
     * This method violates our policy of not calling a service in a getter.
     * This will only call the service once to set a sponsor when a sponsor code exists, 
     * but no sponsor was fetched
     * 
     * Seems like a persistence design issue to me. Why wouldn't Sponsor:Award be a 1:M 
     * relationship handled automagically by the persistence framework? 
     *
     */
    public Sponsor getSponsor() {
        if (!StringUtils.isEmpty(sponsorCode)) {
            this.refreshReferenceObject(SPONSOR);
        }
        return sponsor;
    }


    public List<AwardSponsorContact> getSponsorContacts() {
        return sponsorContacts;
    }


    public void setSponsorContacts(List<AwardSponsorContact> awardSponsorContacts) {
        this.sponsorContacts = awardSponsorContacts;
    }

    public void setSponsor(Sponsor sponsor) {
        this.sponsor = sponsor;
        this.sponsorCode = sponsor != null ? sponsor.getSponsorCode() : null;
    }

    public String getSponsorName() {
        Sponsor sponsor = getSponsor();
        return sponsor != null ? sponsor.getSponsorName() : null;
    }
    
    public String getIcrRateCode() {
        return icrRateCode;
    }

    public void setIcrRateCode(String icrRateCode) {
        this.icrRateCode = icrRateCode;
    }

    public void add(AwardApprovedForeignTravel approvedForeignTravelTrip) {
        approvedForeignTravelTrips.add(approvedForeignTravelTrip);
        approvedForeignTravelTrip.setAward(this);
    }

    public List<AwardPaymentSchedule> getPaymentScheduleItems() {
        return paymentScheduleItems;
    }

    public void setPaymentScheduleItems(List<AwardPaymentSchedule> paymentScheduleItems) {
        this.paymentScheduleItems = paymentScheduleItems;
    }

    public ScaleTwoDecimal getTotalPaymentScheduleAmount() {
        ScaleTwoDecimal amount = ScaleTwoDecimal.ZERO;
        for (AwardPaymentSchedule schedule : paymentScheduleItems) {
            if (schedule.getAmount() != null) {
                amount = amount.add(schedule.getAmount());
            }
        }
        return amount;
    }

    public Sponsor getPrimeSponsor() {
        if (!StringUtils.isEmpty(getPrimeSponsorCode())) {
            this.refreshReferenceObject(PRIME_SPONSOR);
        }
        return primeSponsor;
    }

    public void setPrimeSponsor(Sponsor primeSponsor) {
        this.primeSponsor = primeSponsor;
    }

    public List<AwardTransferringSponsor> getAwardTransferringSponsors() {
        return awardTransferringSponsors;
    }

    public void setAwardStatus(AwardStatus awardStatus) {
        this.awardStatus = awardStatus;
    }

    public void setAwardTransferringSponsors(List<AwardTransferringSponsor> awardTransferringSponsors) {
        this.awardTransferringSponsors = awardTransferringSponsors;
    }

    public List<AwardDirectFandADistribution> getAwardDirectFandADistributions() {
        return awardDirectFandADistributions;
    }

    public void setAwardDirectFandADistributions(List<AwardDirectFandADistribution> awardDirectFandADistributions) {
        for (AwardDirectFandADistribution awardDirectFandADistribution : awardDirectFandADistributions) {
            awardDirectFandADistribution.setAward(this);
        }
        this.awardDirectFandADistributions = awardDirectFandADistributions;
    }

    public List<AwardNotepad> getAwardNotepads() {
        return awardNotepads;
    }

    public void setAwardNotepads(List<AwardNotepad> awardNotepads) {
        this.awardNotepads = awardNotepads;
    }

    public String getIndirectCostIndicator() {
        return indirectCostIndicator;
    }

    public void setIndirectCostIndicator(String indirectCostIndicator) {
        this.indirectCostIndicator = indirectCostIndicator;
    }

    public ScaleTwoDecimal getObligatedTotal() {
        ScaleTwoDecimal returnValue = ScaleTwoDecimal.ZERO;
        if (getLastAwardAmountInfo().getAmountObligatedToDate() != null) {
            returnValue = returnValue.add(getLastAwardAmountInfo().getAmountObligatedToDate());
        }
        return returnValue;
    }

    public ScaleTwoDecimal getObligatedDistributableTotal() {
        ScaleTwoDecimal returnValue = ScaleTwoDecimal.ZERO;
        if (getLastAwardAmountInfo().getObliDistributableAmount() != null) {
            returnValue = getLastAwardAmountInfo().getObliDistributableAmount();
        }
        return returnValue;
    }

    /**
     * Returns the obligated distributable total or the total cost limit
     * whichever is less.
     */
    public ScaleTwoDecimal getBudgetTotalCostLimit() {
        ScaleTwoDecimal limit = getTotalCostBudgetLimit();
        ScaleTwoDecimal obliTotal = getObligatedDistributableTotal();
        if (limit != null && limit.isLessEqual(obliTotal)) {
            return limit;
        } else {
            return obliTotal;
        }
    }

    public ScaleTwoDecimal getObligatedTotalDirect() {
        ScaleTwoDecimal returnValue = ScaleTwoDecimal.ZERO;

        if (getLastAwardAmountInfo().getObligatedTotalDirect() != null) {
            returnValue = returnValue.add(getLastAwardAmountInfo().getObligatedTotalDirect());
        }
        return returnValue;
    }

    public ScaleTwoDecimal getObligatedTotalIndirect() {
        ScaleTwoDecimal returnValue = ScaleTwoDecimal.ZERO;

        if (getLastAwardAmountInfo().getObligatedTotalIndirect() != null) {
            returnValue = returnValue.add(getLastAwardAmountInfo().getObligatedTotalIndirect());
        }
        return returnValue;
    }

    public ScaleTwoDecimal getAnticipatedTotal() {
        ScaleTwoDecimal returnValue = ScaleTwoDecimal.ZERO;

        if (getLastAwardAmountInfo().getAnticipatedTotalAmount() != null) {
            returnValue = returnValue.add(getLastAwardAmountInfo().getAnticipatedTotalAmount());
        }
        return returnValue;
    }

    public ScaleTwoDecimal getAnticipatedTotalDirect() {
        ScaleTwoDecimal returnValue = ScaleTwoDecimal.ZERO;
        if (getLastAwardAmountInfo().getAnticipatedTotalDirect() != null) {
            returnValue = returnValue.add(getLastAwardAmountInfo().getAnticipatedTotalDirect());
        }
        return returnValue;
    }

    public ScaleTwoDecimal getAnticipatedTotalIndirect() {
        ScaleTwoDecimal returnValue = ScaleTwoDecimal.ZERO;

        if (getLastAwardAmountInfo().getAnticipatedTotalIndirect() != null) {
            returnValue = returnValue.add(getLastAwardAmountInfo().getAnticipatedTotalIndirect());
        }
        return returnValue;
    }

    @Override
    public String getDocumentNumberForPermission() {
        return awardId != null ? awardId.toString() : "";
    }

    @Override
    public String getDocumentKey() {
        return PermissionableKeys.AWARD_KEY;
    }

    @Override
    public List<String> getRoleNames() {
        return getSystemAuthorizationService().getRoles(Constants.MODULE_NAMESPACE_AWARD).stream().map(Role::getName).collect(Collectors.toList());
    }

    public List<AwardAmountInfo> getAwardAmountInfos() {
        return awardAmountInfos;
    }

    public void setAwardAmountInfos(List<AwardAmountInfo> awardAmountInfos) {
        this.awardAmountInfos = awardAmountInfos;
    }

    public AwardAmountInfo getAwardAmountInfo() {
        return awardAmountInfos.get(0);
    }

    public AwardAmountInfo getLatestAwardAmountInfo() {
        return getAwardAmountInfoService().fetchAwardAmountInfoWithHighestTransactionId(awardAmountInfos);
    }

    public Unit getLeadUnit() {
        if (leadUnit == null && unitNumber != null) {
            loadLeadUnit();
        }
        return leadUnit;
    }

    public boolean isNew() {
        return awardId == null;
    }

    static class ARTComparator implements Comparator<AwardReportTerm>
    {
        
        public int compare(AwardReportTerm art1, AwardReportTerm art2)
        {
            try
            {
                String art1Desc = art1.getReport().getDescription();
                String art2Desc = art2.getReport().getDescription();
                if (art1Desc == null)
                {
                    art1Desc = "";
                }
                if (art2Desc == null)
                {
                    art2Desc = "";
                }
                return art1Desc.compareTo(art2Desc);
            }
            catch (Exception e)
            {
                return 0;
            }
        }
    
    }


    /**
     * Gets the awardReportTermItems attribute.
     * @return Returns the awardReportTermItems.
     */
    public List<AwardReportTerm> getAwardReportTermItems() {
        Collections.sort(awardReportTermItems, new ARTComparator());
        return awardReportTermItems;
    }

    /**
     * Sets the awardReportTermItems attribute value.
     * @param awardReportTermItems The awardReportTermItems to set.
     */
    public void setAwardReportTermItems(List<AwardReportTerm> awardReportTermItems) {
        this.awardReportTermItems = awardReportTermItems;
    }

    /**
     * Find principle investigator, if any
     * @return Principle investigator. May return null
     */
    public AwardPerson getPrincipalInvestigator() {
        AwardPerson principleInvestigator = null;
        for (AwardPerson person : projectPersons) {
            if (person.isPrincipalInvestigator()) {
                principleInvestigator = person;
                break;
            }
        }
        return principleInvestigator;
    }

    /**
     * This method find PI name
     * @return PI name; may return null
     */
    public String getPrincipalInvestigatorName() {
        AwardPerson pi = getPrincipalInvestigator();
        if (pi != null) {
            if (pi.getIsRolodexPerson()) {
                principalInvestigatorName = pi.getRolodex().getOrganization();
            } else {
                principalInvestigatorName = pi.getFullName();
            }
        }
        return principalInvestigatorName;
    }

    public void setPrincipalInvestigatorName(String principalInvestigatorName) {
        this.principalInvestigatorName = principalInvestigatorName;
    }

    public String getStatusDescription() {
        AwardStatus status = getAwardStatus();
        return status != null ? status.getDescription() : null;
    }

    /**
     * Gets the awardCustomDataList attribute.
     * @return Returns the awardCustomDataList.
     */
    public List<AwardCustomData> getAwardCustomDataList() {
        return awardCustomDataList;
    }

    /**
     * Sets the awardCustomDataList attribute value.
     * @param awardCustomDataList The awardCustomDataList to set.
     */
    public void setAwardCustomDataList(List<AwardCustomData> awardCustomDataList) {
        this.awardCustomDataList = awardCustomDataList;
    }

    /**
     * Gets the awardCloseoutItems attribute.
     * @return Returns the awardCloseoutItems.
     */
    public List<AwardCloseout> getAwardCloseoutItems() {
        return awardCloseoutItems;
    }

    /**
     * Sets the awardCloseoutItems attribute value.
     * @param awardCloseoutItems The awardCloseoutItems to set.
     */
    public void setAwardCloseoutItems(List<AwardCloseout> awardCloseoutItems) {
        if(awardCloseoutItems != null && awardCloseoutItems.size() > TOTAL_STATIC_REPORTS){
            awardCloseoutNewItems.clear();
            for(int i = TOTAL_STATIC_REPORTS ;i < awardCloseoutItems.size() ; i++){
                awardCloseoutNewItems.add(awardCloseoutItems.get(i));
            }
            for (int i = awardCloseoutItems.size();i > TOTAL_STATIC_REPORTS; i--) {
                awardCloseoutItems.remove(i - 1);
            }
            Collections.sort(awardCloseoutNewItems, Comparator.comparing(AwardCloseout::getCloseoutReportName));
            awardCloseoutItems.addAll(TOTAL_STATIC_REPORTS, awardCloseoutNewItems);
        }
        this.awardCloseoutItems = awardCloseoutItems;
    }

    public List<AwardCloseout> getAwardCloseoutNewItems() {
        return awardCloseoutNewItems;
    }

    public void setAwardCloseoutNewItems(List<AwardCloseout> awardCloseoutNewItems) {
        this.awardCloseoutNewItems = awardCloseoutNewItems;
    }

    public void setTemplateCode(Integer templateCode) {
        this.templateCode = templateCode;
    }

    public String getPrimeSponsorCode() {
        return primeSponsorCode;
    }

    public void setPrimeSponsorCode(String primeSponsorCode) {
        this.primeSponsorCode = primeSponsorCode;
    }

    public String getBasisOfPaymentCode() {
        return basisOfPaymentCode;
    }

    public void setBasisOfPaymentCode(String basisOfPaymentCode) {
        this.basisOfPaymentCode = basisOfPaymentCode;
    }

    public String getMethodOfPaymentCode() {
        return methodOfPaymentCode;
    }

    public void setMethodOfPaymentCode(String methodOfPaymentCode) {
        this.methodOfPaymentCode = methodOfPaymentCode;
    }

    public AwardTemplate getAwardTemplate() {
        return awardTemplate;
    }

    public void setAwardTemplate(AwardTemplate awardTemplate) {
        this.awardTemplate = awardTemplate;
    }

    public AwardBasisOfPayment getAwardBasisOfPayment() {
        return awardBasisOfPayment;
    }

    public void setAwardBasisOfPayment(AwardBasisOfPayment awardBasisOfPayment) {
        this.awardBasisOfPayment = awardBasisOfPayment;
    }

    public AwardMethodOfPayment getAwardMethodOfPayment() {
        return awardMethodOfPayment;
    }

    public void setAwardMethodOfPayment(AwardMethodOfPayment awardMethodOfPayment) {
        this.awardMethodOfPayment = awardMethodOfPayment;
    }

    @Override
    public Integer getOwnerSequenceNumber() {
        return null;
    }

    @Override
    public void incrementSequenceNumber() {
        this.sequenceNumber++;
    }

    @Override
    public Award getSequenceOwner() {
        return this;
    }

    @Override
    public void setSequenceOwner(Award newOwner) {
        // no-op
    }

    @Override
    public void resetPersistenceState() {
        this.awardId = null;
    }

    @Override
    public String getVersionNameField() {
        return AWARD_NUMBER;
    }

    public ActivityType getActivityType() {
        return activityType;
    }

    public void setActivityType(ActivityType activityType) {
        this.activityType = activityType;
    }

    /**
     * This method removes Funding Proposal for specified index from list
     *
     * It also removes the AwardFundingProposal from the InstitutionalProposal
     *
     */
    public AwardFundingProposal removeFundingProposal(int index) {
        final AwardFundingProposal afp = (index >= 0) ? getAllFundingProposalsSortedBySequence().get(index) : null;
        if (afp != null) {
            fundingProposals.remove(afp);
            allFundingProposals.remove(afp);
            afp.getProposalId();
            final InstitutionalProposal proposal = getInstitutionalProposalService().getInstitutionalProposal(afp.getProposalId().toString());
            if (proposal != null) {
                proposal.remove(afp);
            }
        }

        return afp;
    }

    /**
     * Given an AwardComment as a template, try to find an existing AwardComment of that type
     * @return The found awardComment of a specific type. If an existing comment is not found, return null
     */
    public AwardComment findCommentOfSpecifiedType(AwardComment template) {
        return findCommentOfSpecifiedType(template.getCommentTypeCode());
    }

    /**
     * For a given type code, this method returns the award comment, or null if none exists.
     * @param commentTypeCode One of the ..._COMMENT_TYPE_CODE values in org.kuali.kra.infrastructure.Constants
     */
    public AwardComment findCommentOfSpecifiedType(String commentTypeCode) {
        AwardComment comment = null;
        for (AwardComment ac : getAwardComments()) {
            if (ac.getCommentTypeCode().equals(commentTypeCode)) {
                comment = ac;
                break;
            }
        }

        return comment;
    }

    public String getBudgetStatus() {
        return BUDGET_STATUS;
    }

    @Override
    public List<AwardPerson> getPersonRolodexList() {
        return getProjectPersons();
    }

    public PersonRolodex getProposalEmployee(String personId) {
        return getPerson(personId, true);
    }

    private PersonRolodex getPerson(String personId, boolean personFindFlag) {
        List<AwardPerson> awardPersons = getProjectPersons();
        for (AwardPerson awardPerson : awardPersons) {
            // rearranged order of condition to handle null personId
            if ((personId != null) && personId.equals(awardPerson.getPersonId())) {
                if (personFindFlag && awardPerson.isEmployee()) {
                    return awardPerson;
                }else{
                    return awardPerson;
                }
            }
        }
        return null;
    }

    public ContactRole getProposalEmployeeRole(String personId) {
        if (getProposalEmployee(personId) != null) {
            return (getProposalEmployee(personId)).getContactRole();
        } else {
            return null;
        }
    }

    public PersonRolodex getProposalNonEmployee(Integer rolodexId) {
        List<AwardPerson> awardPersons = getProjectPersons();
        for (AwardPerson awardPerson : awardPersons) {
            if (rolodexId.equals(awardPerson.getRolodexId())) {
                return awardPerson;
            }
        }
        return null;
    }

    public ContactRole getProposalNonEmployeeRole(Integer rolodexId) {
        if (getProposalNonEmployee(rolodexId) != null) {
            return (getProposalNonEmployee(rolodexId)).getContactRole();
        } else {
            return null;
        }
    }

    public Date getRequestedEndDateInitial() {
        return getObligationExpirationDate();
    }

    public Date getRequestedStartDateInitial() {
        AwardAmountInfo awardAmountInfo = getLastAwardAmountInfo();
        return awardAmountInfo == null ? null : awardAmountInfo.getCurrentFundEffectiveDate();
    }

    public Unit getUnit() {
        return getLeadUnit();
    }

    public boolean isSponsorNihMultiplePi() {
        return sponsorNihMultiplePi;
    }

    public void setBudgetStatus(String budgetStatus) {
    }

    public List<AwardAttachment> getAwardAttachments() {
        if (this.awardAttachments == null) {
            this.awardAttachments = new ArrayList<>();
        }

        return this.awardAttachments;
    }

    public void setAttachments(List<AwardAttachment> attachments) {
        this.awardAttachments = attachments;
    }

    public AwardAttachment getAwardAttachment(int index) {
        return this.awardAttachments.get(index);
    }

    public void addAttachment(AwardAttachment attachment) {
        this.getAwardAttachments().add(attachment);
        attachment.setAward(this);
    }

    public boolean isPersisted() {
        return awardId != null;
    }

    public AwardApprovedSubaward getAwardApprovedSubawards(int index) {
        return getAwardApprovedSubawards().get(index);
    }

    public String getNamespace() {
        return Constants.MODULE_NAMESPACE_AWARD;
    }

    public String getDocumentRoleTypeCode() {
        return RoleConstants.AWARD_ROLE_TYPE;
    }

    protected void loadLeadUnit() {
        leadUnit = getBusinessObjectService().findByPrimaryKey(Unit.class, Collections.singletonMap(UNIT_NUMBER, getUnitNumber()));
    }

    public void populateAdditionalQualifiedRoleAttributes(Map<String, String> qualifiedRoleAttributes) {
        /**
         * when we check to see if the logged in user can create an award account, this function is called, but awardDocument is null at that time.
         */
        String documentNumber = getAwardDocument() != null ? getAwardDocument().getDocumentNumber() : "";
        qualifiedRoleAttributes.put(DOCUMENT_NUMBER, documentNumber);
    }

    public String getHierarchyStatus() {
        return Constants.NO_FLAG;
    }

    /**
     * This method finds the latest final expiration date from the collection of AmnoutInfos
     * @return The latest final expiration date from the collection of AmnoutInfos. If there are no AmoutInfos, 1/1/1900 is returned
     */
    public Date findLatestFinalExpirationDate() {
        Date latestExpDate = new Date(new GregorianCalendar(1900, Calendar.JANUARY, 1).getTimeInMillis());
        for (AwardAmountInfo amountInfo : getAwardAmountInfos()) {
            Date expDate = amountInfo.getFinalExpirationDate();
            if (expDate != null && expDate.after(latestExpDate)) {
                latestExpDate = expDate;
            }
        }
        return latestExpDate;
    }

    public boolean isParentInHierarchyComplete() {
        return true;
    }

    public String getDefaultBudgetStatusParameter() {
        return KeyConstants.AWARD_BUDGET_STATUS_IN_PROGRESS;
    }

    public List<AwardHierarchyTempObject> getAwardHierarchyTempObjects() {
        return awardHierarchyTempObjects;
    }

    public AwardHierarchyTempObject getAwardHierarchyTempObject(int index) {
        if (awardHierarchyTempObjects == null) {
            initializeAwardHierarchyTempObjects();
        }

        return awardHierarchyTempObjects.get(index);
    }

    public AwardType getAwardType() {
        return awardType;
    }

    public void setAwardType(AwardType awardType) {
        this.awardType = awardType;
    }

    public AwardComment getAwardComment(int index) {
        while (getAwardComments().size() <= index) {
            getAwardComments().add(new AwardComment());
        }
        return getAwardComments().get(index);
    }


    public String getDocIdStatus() {
        return docIdStatus;
    }

    public String getAwardIdAccount() {
            if (StringUtils.isNotBlank(getAccountNumber())) {
                return getAwardNumber() + COLON + getAccountNumber();
            } else {
                return getAwardNumber() + COLON;
            }
    }

    public void setLookupOspAdministratorName(String lookupOspAdministratorName) {
        this.lookupOspAdministratorName = lookupOspAdministratorName;
    }

    public String getLookupOspAdministratorName() {
        return lookupOspAdministratorName;
    }

    public List<AwardUnitContact> getCentralAdminContacts() {
        if (centralAdminContacts == null) {
            initCentralAdminContacts();
        }
        return centralAdminContacts;
    }

    /**
     * Builds the list of central admin contacts based on the lead unit of this
     * award. Build here instead of on a form bean as ospAdministrator
     * must be accessible during Award lookup.
     * 
     */
    public void initCentralAdminContacts() {
        centralAdminContacts = new ArrayList<>();
        List<UnitAdministrator> unitAdministrators = 
            getUnitService().retrieveUnitAdministratorsByUnitNumber(getUnitNumber());
        for (UnitAdministrator unitAdministrator : unitAdministrators) {
            if(unitAdministrator.getUnitAdministratorType().getDefaultGroupFlag().equals(DEFAULT_GROUP_CODE_FOR_CENTRAL_ADMIN_CONTACTS)) {
                KcPerson person = null;
                try {
                  person = getKcPersonService().getKcPersonByPersonId(unitAdministrator.getPersonId());
                } catch (IllegalArgumentException e) {
                  LOG.info("initCentralAdminContacts(): entity/person missing: " + unitAdministrator.getPersonId());
                }
                if (person != null) {
                AwardUnitContact newAwardUnitContact = new AwardUnitContact();
                newAwardUnitContact.setAward(this);
                newAwardUnitContact.setPerson(person);
                newAwardUnitContact.setUnitAdministratorType(unitAdministrator.getUnitAdministratorType());
                newAwardUnitContact.setFullName(person.getFullName());
                centralAdminContacts.add(newAwardUnitContact);
                }
            }
        }
    }

    public boolean isAwardInMultipleNodeHierarchy() {
        return awardInMultipleNodeHierarchy;
    }

    public void setAwardInMultipleNodeHierarchy(boolean awardInMultipleNodeHierarchy) {
        this.awardInMultipleNodeHierarchy = awardInMultipleNodeHierarchy;
    }

    public boolean isAwardHasAssociatedTandMOrIsVersioned() {
        return awardHasAssociatedTandMOrIsVersioned;
    }

    public void setAwardHasAssociatedTandMOrIsVersioned(boolean awardHasAssociatedTandMOrIsVersioned) {
        this.awardHasAssociatedTandMOrIsVersioned = awardHasAssociatedTandMOrIsVersioned;
    }

    public boolean isSyncChild() {
        return syncChild;
    }

    public void setSyncChild(boolean syncChild) {
        this.syncChild = syncChild;
    }

    public List<AwardSyncChange> getSyncChanges() {
        return syncChanges;
    }

    public void setSyncChanges(List<AwardSyncChange> syncChanges) {
        this.syncChanges = syncChanges;
    }


    public List<AwardSyncStatus> getSyncStatuses() {
        return syncStatuses;
    }

    public void setSyncStatuses(List<AwardSyncStatus> syncStatuses) {
        this.syncStatuses = syncStatuses;
    }

    public void setSponsorNihMultiplePi(boolean sponsorNihMultiplePi) {
        this.sponsorNihMultiplePi = sponsorNihMultiplePi;
    }

    public boolean isActiveVersion() {
        return (REPORT_STATUSES.contains(getAwardStatus().getStatusCode()));
    }

    public List<AwardBudgetLimit> getAwardBudgetLimits() {
        return awardBudgetLimits;
    }

    public void setAwardBudgetLimits(List<AwardBudgetLimit> awardBudgetLimits) {
        this.awardBudgetLimits = awardBudgetLimits;
    }

    public ScaleTwoDecimal getTotalCostBudgetLimit() {
        return getSpecificBudgetLimit(AwardBudgetLimit.LIMIT_TYPE.TOTAL_COST).getLimit();
    }

    public ScaleTwoDecimal getDirectCostBudgetLimit() {
        return getSpecificBudgetLimit(AwardBudgetLimit.LIMIT_TYPE.DIRECT_COST).getLimit();
    }

    public ScaleTwoDecimal getIndirectCostBudgetLimit() {
        return getSpecificBudgetLimit(AwardBudgetLimit.LIMIT_TYPE.INDIRECT_COST).getLimit();
    }

    protected AwardBudgetLimit getSpecificBudgetLimit(AwardBudgetLimit.LIMIT_TYPE type) {
        for (AwardBudgetLimit limit : getAwardBudgetLimits()) {
            if (limit.getLimitType() == type) {
                return limit;
            }
        }
        return new AwardBudgetLimit(type);
    }

    public List<Boolean> getAwardCommentHistoryFlags() {
        return awardCommentHistoryFlags;
    }

    public void setAwardCommentHistoryFlags(List<Boolean> awardCommentHistoryFlags) {
        this.awardCommentHistoryFlags = awardCommentHistoryFlags;
    }
    
    public void orderStaticCloseOutReportItems(List<AwardCloseout> awardCloseoutItems) {
        if(awardCloseoutItems != null && awardCloseoutItems.size() == TOTAL_STATIC_REPORTS){
            awardCloseoutNewItems.clear();
            List<AwardCloseout> staticCloseoutItems = new ArrayList<>();
            for(int i = 0; i < TOTAL_STATIC_REPORTS ; i++){
                staticCloseoutItems.add(awardCloseoutItems.get(i));
                awardCloseoutNewItems.add(awardCloseoutItems.get(i));
            }
            awardCloseoutItems.removeAll(staticCloseoutItems);
            
            for(AwardCloseout awardCloseout : staticCloseoutItems){
                if(awardCloseout.getCloseoutReportCode() != null && awardCloseout.getCloseoutReportCode().equalsIgnoreCase(CLOSE_OUT_REPORT_TYPE_FINANCIAL_REPORT)){
                    awardCloseoutNewItems.remove(awardCloseout);
                    awardCloseoutNewItems.add(0,awardCloseout);
                }else if(awardCloseout.getCloseoutReportCode() != null && awardCloseout.getCloseoutReportCode().equalsIgnoreCase(CLOSE_OUT_REPORT_TYPE_TECHNICAL)){
                    awardCloseoutNewItems.remove(awardCloseout);
                    awardCloseoutNewItems.add(1,awardCloseout);
                }else if(awardCloseout.getCloseoutReportCode() != null && awardCloseout.getCloseoutReportCode().equalsIgnoreCase(CLOSE_OUT_REPORT_TYPE_PATENT)){
                    awardCloseoutNewItems.remove(awardCloseout);
                    awardCloseoutNewItems.add(2,awardCloseout);
                }else if(awardCloseout.getCloseoutReportCode() != null && awardCloseout.getCloseoutReportCode().equalsIgnoreCase(CLOSE_OUT_REPORT_TYPE_PROPERTY)){
                    awardCloseoutNewItems.remove(awardCloseout);
                    awardCloseoutNewItems.add(3,awardCloseout);
                }else if(awardCloseout.getCloseoutReportCode() != null && awardCloseout.getCloseoutReportCode().equalsIgnoreCase(CLOSE_OUT_REPORT_TYPE_INVOICE)){
                    awardCloseoutNewItems.remove(awardCloseout);
                    awardCloseoutNewItems.add(4,awardCloseout);
                }
            }
            awardCloseoutItems.addAll(0,awardCloseoutNewItems);
        }
    }

    @Override
    public String getLeadUnitName() {
        return getLeadUnit() == null ? EMPTY_STRING : getLeadUnit().getUnitName();
    }
    
    @Override
    public String getPiName() {
        return getPiEmployeeName();
    }

    @Override
    public String getPiEmployeeName() {
        return getPrincipalInvestigatorName();
    }

    @Override
    public String getPiNonEmployeeName() {
        return EMPTY_STRING;
    }

    @Override
    public String getAdminPersonName() {
        return EMPTY_STRING;
    }

    @Override
    public String getPrimeSponsorName() {
        return getPrimeSponsor() == null ? EMPTY_STRING : getPrimeSponsor().getSponsorName();
    }

    @Override
    public String getSubAwardOrganizationName() {
        return EMPTY_STRING;
    }
    
    @Override
    public List<NegotiationPersonDTO> getProjectPeople() {
        return getProjectPersons().stream().map(person -> new NegotiationPersonDTO(person.getPerson(), person.getContactRoleCode())).collect(Collectors.toList());
    }
    
    public String getNegotiableProposalTypeCode() {
        return EMPTY_STRING;
    }
    

    public String getParentNumber() {
        return this.getAwardNumber();
    }

    public String getParentPIName() {
        String investigatorName = null;
        for (AwardPerson aPerson : this.getProjectPersons()) {
            if (aPerson != null && aPerson.isPrincipalInvestigator() )
            {
                investigatorName = aPerson.getFullName();
                break;
            }
        }
        return investigatorName;
    }

    public String getParentTitle() {
        return this.getTitle();
    }

    public String getOwnedByUnitNumber() {
        return this.getLeadUnitName();
    }

    public Integer getParentInvestigatorFlag(String personId, Integer flag) {
        for (AwardPerson aPerson : this.getProjectPersons()) {
            if (aPerson.getPersonId() != null
                    && aPerson.getPersonId().equals(personId)
                    || aPerson.getRolodexId() != null
                    && aPerson.getRolodexId().toString().equals(personId)) {
                flag = 2;
                if (aPerson.isPrincipalInvestigator()) {
                    flag = 1;
                    break;
                }
            }
        }
        return flag;
    }

    public AwardFandaRate getCurrentFandaRate() {
        List<AwardFandaRate> rates = this.getAwardFandaRate();
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        
        // when both On and Off campus rates are in, send the higher one.
        // Ideally only one should be there
        // the single rate validation parameter needs to be set on award
        AwardFandaRate currentFandaRate = rates.stream()
                .filter(rate -> Integer.parseInt(rate.getFiscalYear()) == currentYear)
                .max(Comparator.comparing(AwardFandaRate::getApplicableFandaRate))
                .orElse(null);

        return currentFandaRate;
    }
    
    public String getParentTypeName(){
        return AWARD;
    }

    @Override
    public String getAssociatedDocumentId() {
        return getAwardNumber();
    }

    public String getAwardSequenceStatus() {
        return awardSequenceStatus;
    }

    public void setAwardSequenceStatus(String awardSequenceStatus) {
        this.awardSequenceStatus = awardSequenceStatus;
    }

    public String getAwardSequenceStatusResult() {
        awardSequenceStatusResult = AwardDocumentStatusConstants.Pending.description();
        for (AwardDocumentStatusConstants status : AwardDocumentStatusConstants.values()) {
            if (status.code().equals(getAwardSequenceStatus())) {
                awardSequenceStatusResult = status.description();
            }
        }
        return awardSequenceStatusResult;
    }

    public void setAwardSequenceStatusResult(String awardSequenceStatusResult) {
        this.awardSequenceStatusResult = awardSequenceStatusResult;
    }

    @Override
    public ProposalType getNegotiableProposalType() {
        return null;
    }

    @Override
    public String getSubAwardRequisitionerName() {
        return EMPTY_STRING;
    }

    @Override
    public String getSubAwardRequisitionerUnitNumber() {
        return EMPTY_STRING;
    }

    @Override
    public String getSubAwardRequisitionerUnitName() {
        return EMPTY_STRING;
    }

    @Override
    public String getSubAwardRequisitionerId() {
        return EMPTY_STRING;
    }
    public List<SubAward> getSubAwardList() {
        return subAwardList;
    }
    
    public void setSubAwardList(List<SubAward> subAwardList) {
        this.subAwardList = subAwardList;
    }

    @Override
    public String getProjectName() {

        return getTitle();
    }

    @Override
    public String getProjectId() {

        return getAwardNumber();
    }
    
    public boolean isAllowUpdateTimestampToBeReset() {
        return allowUpdateTimestampToBeReset;
    }
    
    /**
     * 
     * Setting this value to false will prevent the update timestamp field from being upddate just once.  After that, the update timestamp field will update as regular.
     */
    public void setAllowUpdateTimestampToBeReset(boolean allowUpdateTimestampToBeReset) {
        this.allowUpdateTimestampToBeReset = allowUpdateTimestampToBeReset;
    }

    @Override
    public void setUpdateTimestamp(Timestamp updateTimestamp) {

        if (isAllowUpdateTimestampToBeReset()) {
            super.setUpdateTimestamp(updateTimestamp);
        } else {
            setAllowUpdateTimestampToBeReset(true);
        }
    }
    
    public List<Award> getAwardVersions() {
        Map<String, String> fieldValues = new HashMap<>();
        fieldValues.put(AWARD_NUMBER, getAwardNumber());
        return (List<Award>)getBusinessObjectService().findMatchingOrderBy(Award.class, fieldValues, SEQUENCE_NUMBER, true);
    }

    public String getAwardDescriptionLine() {
		String noticeDate;
		String transactionTypeDescription;
		String versionNumber;

		versionNumber = getSequenceNumber().toString();

		if (!(getNoticeDate() == null)) {
			noticeDate = getNoticeDate().toString();
		} else {
			noticeDate = NONE;
		}
		if (!(getAwardTransactionType() == null)) {
			transactionTypeDescription = getAwardTransactionType().getDescription();
		} else {
			transactionTypeDescription = NONE;
		}
		return "Award Version " + versionNumber + ", " + transactionTypeDescription + ", notice date: " + noticeDate + ", updated " + getUpdateTimeAndUser() + ". Comments:"
				+ (getAwardCurrentActionComments().getComments() == null ? NONE + "." : getAwardCurrentActionComments().getComments());
    }

    public String getUpdateTimeAndUser() {
        String createDateStr = null;
        String updateUser = null;
        if (getUpdateTimestamp() != null) {
            createDateStr = CoreApiServiceLocator.getDateTimeService().toString(awardDocument.getUpdateTimestamp(), "hh:mm a MM/dd/yyyy");
            updateUser = awardDocument.getUpdateUser().length() > 30 ? getUpdateUser().substring(0, 30) : getUpdateUser(); 
        }
        return createDateStr + ", by " + updateUser;
    }
 
    public List<TimeAndMoneyDocumentHistory>getTimeAndMoneyDocumentHistoryList() throws WorkflowException {  
        List<TimeAndMoneyDocument> tnmDocs = getTimeAndMoneyHistoryService().buildTimeAndMoneyListForAwardDisplay(this, true);
        return
            getTimeAndMoneyHistoryService().getDocHistoryAndValidInfosAssociatedWithAwardVersion(tnmDocs,getAwardAmountInfos(), this);
    }

    public VersionHistorySearchBo getVersionHistory() {
        return versionHistory;
    }

    public void setVersionHistory(VersionHistorySearchBo versionHistory) {
        this.versionHistory = versionHistory;
    }
    public void setProjectPersons(List<AwardPerson> projectPersons) {
        this.projectPersons = projectPersons;
    }

    protected KcPersonService getKcPersonService() {
        if (this.kcPersonService == null) {
            this.kcPersonService = KcServiceLocator.getService(KcPersonService.class);
        }
        return this.kcPersonService;
    }

    /*
     * This method is used by the tag file to display the F&amp;A rate totals.
     * Needed to convert to KualiDecimal to avoid rounding issues.
     */
    public ScaleTwoDecimal getFandATotals() {
        ScaleTwoDecimal total = ScaleTwoDecimal.ZERO;
        for (AwardFandaRate currentRate : getAwardFandaRate()) {
            if (currentRate.getUnderrecoveryOfIndirectCost() != null) {
                total = total.add(new ScaleTwoDecimal(currentRate.getUnderrecoveryOfIndirectCost().bigDecimalValue()));
            }
        }
        return total;
    }

	@Override
	public boolean isProposalBudget() {
		return false;
	}

	@Override
	public BudgetParentDocument<Award> getDocument() {
		return getAwardDocument();
	}

	@Override
	public Budget getNewBudget() {
		return new AwardBudgetExt();
	}
    private List<AwardBudgetExt> currentVersionBudgets;
    private List<AwardBudgetExt> budgets;

	@Override
	public Integer getNextBudgetVersionNumber() {
		return getAwardDocument().getNextBudgetVersionNumber();
	}

	public List<AwardBudgetExt> getBudgets() {
		if (budgets == null || budgets.isEmpty()) {
			budgets = getAwardBudgetService().getAllBudgetsForAward(this);
		}
		return budgets;
	}

	public void setBudgets(List<AwardBudgetExt> budgets) {
		this.budgets = budgets;
	}

	public List<AwardBudgetExt> getCurrentVersionBudgets() {
		return currentVersionBudgets;
	}

	public void setCurrentVersionBudgets(List<AwardBudgetExt> budgets) {
		this.currentVersionBudgets = budgets;
	}

    public AwardComment getAdditionalFormsDescriptionComment() {
        return getAwardCommentByType(CG_2, false, true);
    }

    public AwardComment getStopWorkReasonComment() {
        return getAwardCommentByType(CG_1, false, true);
    }

    public AwardComment getSuspendInvoicingComment() {
        return getAwardCommentByType(CG_3, false, true);
    }

    public AwardCgb getAwardCgb() {
        if (awardCgbList.isEmpty()) {
            awardCgbList.add(new AwardCgb(this));
        }
        return awardCgbList.get(0);
    }

    public void setAwardCgb(AwardCgb awardCgb) {
        awardCgbList.set(0, awardCgb);
    }

    public List<AwardCgb> getAwardCgbList() {
        return awardCgbList;
    }

    public void setAwardCgbList(List<AwardCgb> awardCgbList) {
        this.awardCgbList = awardCgbList;
    }

	public List<AwardFundingProposal> getAllFundingProposals() {
		return allFundingProposals;
	}

	public void setAllFundingProposals(List<AwardFundingProposal> allFundingProposals) {
		this.allFundingProposals = allFundingProposals;
	}

	public List<AwardFundingProposal> getAllFundingProposalsSortedBySequence() {
		return getAllFundingProposals().stream()
				.sorted(Comparator.comparing(AwardFundingProposal::getAwardSequenceNumber))
				.collect(Collectors.toList());
	}

    public String getFainId() {
        return fainId;
    }

    public void setFainId(String fainId) {
        this.fainId = fainId;
    }

    public Integer getFedAwardYear() {
        return fedAwardYear;
    }

    public void setFedAwardYear(Integer fedAwardYear) {
        this.fedAwardYear = fedAwardYear;
    }

    public Date getFedAwardDate() {
        return fedAwardDate;
    }

    public void setFedAwardDate(Date fedAwardDate) {
        this.fedAwardDate = fedAwardDate;
    }

    public SystemAuthorizationService getSystemAuthorizationService() {
        if (systemAuthorizationService == null) {
            systemAuthorizationService = KcServiceLocator.getService(SystemAuthorizationService.class);
        }
        return systemAuthorizationService;
    }

    public void setSystemAuthorizationService(SystemAuthorizationService systemAuthorizationService) {
        this.systemAuthorizationService = systemAuthorizationService;
    }

    public AwardBudgetService getAwardBudgetService() {
        if (awardBudgetService == null) {
            awardBudgetService = KcServiceLocator.getService(AwardBudgetService.class);
        }

        return awardBudgetService;
    }

    public void setAwardBudgetService(AwardBudgetService awardBudgetService) {
        this.awardBudgetService = awardBudgetService;
    }

    public InstitutionalProposalService getInstitutionalProposalService() {
        if (this.institutionalProposalService == null) {
            this.institutionalProposalService = KcServiceLocator.getService(InstitutionalProposalService.class);
        }

        return this.institutionalProposalService;
    }

    public void setInstitutionalProposalService(InstitutionalProposalService institutionalProposalService) {
        this.institutionalProposalService = institutionalProposalService;
    }

    public BusinessObjectService getBusinessObjectService() {
        if (businessObjectService == null) {
            businessObjectService = KcServiceLocator.getService(BusinessObjectService.class);
        }

        return businessObjectService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    public UnitService getUnitService() {
        if (unitService == null) {
            unitService = KcServiceLocator.getService(UnitService.class);
        }

        return unitService;
    }

    public void setUnitService(UnitService unitService) {
        this.unitService = unitService;
    }

	public KcPerson getInvestigator() {
		return investigator;
	}
}
