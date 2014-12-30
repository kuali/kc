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
package org.kuali.kra.award.home;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.keyword.KeywordsManager;
import org.kuali.coeus.common.framework.keyword.ScienceKeyword;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.common.framework.version.sequence.owner.SequenceOwner;
import org.kuali.coeus.common.framework.sponsor.Sponsor;
import org.kuali.coeus.common.framework.sponsor.Sponsorable;
import org.kuali.coeus.common.framework.type.ActivityType;
import org.kuali.coeus.common.framework.type.ProposalType;
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.coeus.common.framework.unit.UnitService;
import org.kuali.coeus.common.framework.unit.admin.UnitAdministrator;
import org.kuali.coeus.common.framework.version.VersionStatus;
import org.kuali.coeus.common.framework.version.history.VersionHistorySearchBo;
import org.kuali.coeus.common.permissions.impl.PermissionableKeys;
import org.kuali.coeus.common.framework.auth.SystemAuthorizationService;
import org.kuali.coeus.common.framework.auth.perm.Permissionable;
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
import org.kuali.kra.award.notesandattachments.attachments.AwardAttachment;
import org.kuali.kra.award.notesandattachments.notes.AwardNotepad;
import org.kuali.kra.award.paymentreports.awardreports.AwardReportTerm;
import org.kuali.kra.award.paymentreports.closeout.AwardCloseout;
import org.kuali.kra.award.paymentreports.paymentschedule.AwardPaymentSchedule;
import org.kuali.kra.award.paymentreports.specialapproval.approvedequipment.AwardApprovedEquipment;
import org.kuali.kra.award.paymentreports.specialapproval.foreigntravel.AwardApprovedForeignTravel;
import org.kuali.kra.award.specialreview.AwardSpecialReview;
import org.kuali.kra.award.timeandmoney.AwardDirectFandADistribution;
import org.kuali.kra.bo.*;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.core.BudgetParent;
import org.kuali.coeus.common.budget.framework.core.BudgetParentDocument;
import org.kuali.coeus.common.framework.rolodex.PersonRolodex;
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
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kim.api.role.Role;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.springframework.util.AutoPopulatingList;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.*;

/**
 * 
 * This class is Award Business Object.
 * It implements ProcessKeywords to process all operations related to AwardScenceKeywords.
 */
public class Award extends KcPersistableBusinessObjectBase implements KeywordsManager<AwardScienceKeyword>, Permissionable,
        SequenceOwner<Award>, BudgetParent, Sponsorable, Negotiable, Disclosurable {
    public static final String DEFAULT_AWARD_NUMBER = "000000-00000";
    public static final String BLANK_COMMENT = "";
    public static final String ICR_RATE_CODE_NONE = "ICRNONE";

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
    
    private static final long serialVersionUID = 3797220122448310165L;
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
//    private String sequenceOwnerVersionNameValue;
//    private Integer sequenceOwnerSequenceNumber;


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

    private List<AwardBudgetLimit> awardBudgetLimits;

    // Additional fields for lookup
    private Unit leadUnit;
    private String unitNumber;

    private KcPerson ospAdministrator;
    private String ospAdministratorName;
    private String principalInvestigatorName;
    private String statusDescription;
    private String sponsorName;

    // For award-account integration
    private String icrRateCode;

    private transient boolean awardInMultipleNodeHierarchy;
    private transient boolean awardHasAssociatedTandMOrIsVersioned;

    private transient boolean sponsorNihMultiplePi;

    private transient List<AwardHierarchyTempObject> awardHierarchyTempObjects;

    // transient for award header label
    private transient String docIdStatus;
    private transient String awardIdAccount;

    private transient String lookupOspAdministratorName;
    transient AwardAmountInfoService awardAmountInfoService;
    transient TimeAndMoneyHistoryService timeAndMoneyHistoryService;
    private transient AwardHierarchyService awardHierarchyService;

    private transient List<AwardUnitContact> centralAdminContacts;
    private List<SubAward> subAwardList;
    
    private transient boolean allowUpdateTimestampToBeReset = true;
    
    private VersionHistorySearchBo versionHistory;
    private transient KcPersonService kcPersonService;

    private List<AwardCgb> awardCgbList;

    /**
     * 
     * Constructs an Award BO.
     */
    public Award() {
        super();
        initializeAwardWithDefaultValues();
        initializeCollections();
    }

    /**
     * 
     * This method sets the default values for initial persistence as part of skeleton.
     * As various panels are developed; corresponding field initializations should be removed from
     * this method.  
     */
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
        awardComments = new AutoPopulatingList<AwardComment>(AwardComment.class);
        setCurrentActionComments("");
        setNewVersion(false);
        awardSequenceStatus = VersionStatus.PENDING.name();
    }


    private Map<String, AwardComment> getCommentMap() {
        if (commentMap == null || getNewVersion()) {
            commentMap = new HashMap<String, AwardComment>();
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

    /**
     * Gets the templateCode attribute.
     * @return Returns the templateCode.
     */
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

    /**
     * 
     * @param awardId
     */
    public void setAwardId(Long awardId) {
        this.awardId = awardId;
    }



    public String getAwardNumber() {
        return awardNumber;
    }

    /**
     * 
     * @param awardNumber
     */
    public void setAwardNumber(String awardNumber) {
        this.awardNumber = awardNumber;
    }


    @Override
    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    /**
     * 
     * @param sequenceNumber
     */
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
        AwardAmountInfo aai = getAwardAmountInfoService().fetchLastAwardAmountInfoForAwardVersionAndFinalizedTandMDocumentNumber(this);
        int returnVal = 0;
        int index = 0;
        if (aai.getAwardAmountInfoId() != null && this.isAwardInMultipleNodeHierarchy()) {
            this.refreshReferenceObject("awardAmountInfos");
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

    public int getIndexOfAwardAmountInfoForDisplayFromTimeAndMoneyDocNumber(String docNum) throws WorkflowException {
        AwardAmountInfo aai = getAwardAmountInfoService().fetchLastAwardAmountInfoForDocNum(this, docNum);
        int returnVal = 0;
        int index = 0;
        if (aai.getAwardAmountInfoId() != null && this.isAwardInMultipleNodeHierarchy()) {
            this.refreshReferenceObject("awardAmountInfos");
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
     * If the Award is copied then initially the AwardAmountInfos will have two entries without AwardAmountInfoId's.  We need to recognize this
     * so we can display the correct data on initialization.
     * @return
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

    /**
     * Gets the awardAmountInfoService attribute.
     * @return Returns the awardAmountInfoService.
     */
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

    /**
     * Sets the awardAmountInfoService attribute value.
     * @param awardAmountInfoService The awardAmountInfoService to set.
     */
    public void setAwardAmountInfoService(AwardAmountInfoService awardAmountInfoService) {
        this.awardAmountInfoService = awardAmountInfoService;
    }



    public String getSponsorCode() {
        return sponsorCode;
    }

    /**
     * 
     * @param sponsorCode
     */
    public void setSponsorCode(String sponsorCode) {
        this.sponsorCode = sponsorCode;
    }

    public String getAccountTypeDescription() {
        AccountType accountType = 
            (AccountType) getBusinessObjectService().findByPrimaryKey
            (AccountType.class, Collections.singletonMap("accountTypeCode", getAccountTypeCode()));
        if (accountType == null) {
            return "None Selected";
        }else {
            return accountType.getDescription();
        }
    }



    public Integer getStatusCode() {
        return statusCode;
    }

    /**
     * 
     * @param statusCode
     */
    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }


    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * 
     * @param accountNumber
     */
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }


    public List<AwardApprovedEquipment> getApprovedEquipmentItems() {
        return approvedEquipmentItems;
    }


    public List<AwardUnitContact> getAwardUnitContacts() {
        return awardUnitContacts;
    }

    /**
     * @param index
     * @return
     */
    public AwardPerson getProjectPerson(int index) {
        return projectPersons.get(index);
    }

    /**
     * Retrieve the AwardPerson for the given personId, if it exists.
     * 
     * @param personId String
     * @return AwardPerson
     */
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

    /**
     * Retrieve the AwardPerson for the given rolodexId, if it exists.
     * 
     * @param rolodexId Integer
     * @return AwardPerson
     */
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
        List<AwardPerson> aList = new ArrayList<AwardPerson>();
        if (this.getPrincipalInvestigator() != null) {
            aList.add(this.getPrincipalInvestigator());
        }
        aList.addAll(this.getMultiplePis());
        aList.addAll(this.getCoInvestigators());
        aList.addAll(this.getKeyPersons());
        return aList;
    }

    /**
     * This method returns all PIs and co-PIs.
     * @return
     */
    public List<AwardPerson> getInvestigators() {
        List<AwardPerson> investigators = new ArrayList<AwardPerson>();
        for (AwardPerson person : projectPersons) {
            if (person.isPrincipalInvestigator() || person.isCoInvestigator()) {
                investigators.add(person);
            }
        }
        Collections.sort(investigators);
        return investigators;
    }

    /**
     * This method returns all co-PIs.
     * @return
     */
    public List<AwardPerson> getCoInvestigators() {
        List<AwardPerson> coInvestigators = new ArrayList<AwardPerson>();
        for (AwardPerson person : projectPersons) {
            if (person.isCoInvestigator()) {
                coInvestigators.add(person);
            }
        }
        Collections.sort(coInvestigators);
        return coInvestigators;
    }

    /**
     * When the sponsor is in the NIH multiple PI hierarchy this will return any multiple pis, otherwise, empty list.
     * @return
     */
    public List<AwardPerson> getMultiplePis() {
        List<AwardPerson> multiplePis = new ArrayList<AwardPerson>();
        if (isSponsorNihMultiplePi()) {
            for (AwardPerson person : projectPersons) {
                if (person.isMultiplePi()) {
                    multiplePis.add(person);
                }
            }
            Collections.sort(multiplePis);
        }
        return multiplePis;
    }

    /**
     * This method returns all key persons
     * @return
     */
    public List<AwardPerson> getKeyPersons() {
        List<AwardPerson> keyPersons = new ArrayList<AwardPerson>();
        for (AwardPerson person : projectPersons) {
            if (person.isKeyPerson()) {
                keyPersons.add(person);
            }
        }
        Collections.sort(keyPersons);
        return keyPersons;
    }

    /**
     * This method returns the combined number of units for all project personnel.
     * @return
     */
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

    /**
     * @param awardUnitContacts
     */
    public void setAwardUnitContacts(List<AwardUnitContact> awardUnitContacts) {
        this.awardUnitContacts = awardUnitContacts;
    }

    /**
     * 
     */
    public void setApprovedEquipmentItems(List<AwardApprovedEquipment> awardApprovedEquipmentItems) {
        this.approvedEquipmentItems = awardApprovedEquipmentItems;
    }

    /**
     * 
     */
    public void setApprovedForeignTravelTrips(List<AwardApprovedForeignTravel> approvedForeignTravelTrips) {
        this.approvedForeignTravelTrips = approvedForeignTravelTrips;
    }


    public String getApprovedEquipmentIndicator() {
        return approvedEquipmentIndicator;
    }

    /**
     * 
     * @param approvedEquipmentIndicator
     */
    public void setApprovedEquipmentIndicator(String approvedEquipmentIndicator) {
        this.approvedEquipmentIndicator = approvedEquipmentIndicator;
    }



    public String getApprovedForeignTripIndicator() {
        return approvedForeignTripIndicator;
    }

    /**
     * 
     * @param approvedForeignTripIndicator
     */
    public void setApprovedForeignTripIndicator(String approvedForeignTripIndicator) {
        this.approvedForeignTripIndicator = approvedForeignTripIndicator;
    }



    public String getSubContractIndicator() {
        return subContractIndicator;
    }

    /**
     * 
     * @param subContractIndicator
     */
    public void setSubContractIndicator(String subContractIndicator) {
        this.subContractIndicator = subContractIndicator;
    }



    public Date getAwardEffectiveDate() {
        return awardEffectiveDate;
    }

    /**
     * 
     * @param awardEffectiveDate
     */
    public void setAwardEffectiveDate(Date awardEffectiveDate) {
        this.awardEffectiveDate = awardEffectiveDate;
    }



    public Date getAwardExecutionDate() {
        return awardExecutionDate;
    }

    /**
     * 
     * @param awardExecutionDate
     */
    public void setAwardExecutionDate(Date awardExecutionDate) {
        this.awardExecutionDate = awardExecutionDate;
    }



    public Date getBeginDate() {
        return beginDate;
    }

    /**
     * This method returns the project end date which is housed in the Amount Info list index[0] on the award.
     * @return
     */
    public Date getProjectEndDate() {
        return awardAmountInfos.get(0).getFinalExpirationDate();
    }

    /**
     * This method sets the project end date which is housed in the Amount Info list index[0] on the award.
     * @return
     */
    public void setProjectEndDate(Date date) {
        this.awardAmountInfos.get(0).setFinalExpirationDate(date);
    }

    public Date getObligationExpirationDate() {
        // return awardAmountInfos.get(0).getObligationExpirationDate();
        return getLastAwardAmountInfo().getObligationExpirationDate();
    }

    public void setObligationExpirationDate(Date date) {
        this.awardAmountInfos.get(0).setObligationExpirationDate(date);
    }

    /**
     * 
     * @param beginDate
     */
    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }



    public String getCostSharingIndicator() {
        return costSharingIndicator;
    }

    /**
     * 
     * @param costSharingIndicator
     */
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
     * @return
     */
    public String getIdcIndicator() {
        return indirectCostIndicator;
    }

    /**
     * 
     * For ease of use in JSP and tag files; the setter method uses acronym instead of full meaning.
     * idcIndicator is an acronym. Its full meaning is Indirect Cost Indicator
     * @param indirectCostIndicator
     */
    public void setIdcIndicator(String indirectCostIndicator) {
        this.indirectCostIndicator = indirectCostIndicator;
    }



    public String getModificationNumber() {
        return modificationNumber;
    }

    /**
     * 
     * @param modificationNumber
     */
    public void setModificationNumber(String modificationNumber) {
        this.modificationNumber = modificationNumber;
    }


    /**
     * NSFCode is an acronym. Its full meaning is National Science Foundation.
     * @return
     */
    public String getNsfCode() {
        return nsfCode;
    }

    /**
     * NSFCode is an acronym. Its full meaning is National Science Foundation.
     * @param nsfCode
     */
    public void setNsfCode(String nsfCode) {
        this.nsfCode = nsfCode;
    }



    public String getPaymentScheduleIndicator() {
        return paymentScheduleIndicator;
    }

    /**
     * 
     * @param paymentScheduleIndicator
     */
    public void setPaymentScheduleIndicator(String paymentScheduleIndicator) {
        this.paymentScheduleIndicator = paymentScheduleIndicator;
    }



    public String getScienceCodeIndicator() {
        return scienceCodeIndicator;
    }

    /**
     * 
     * @param scienceCodeIndicator
     */
    public void setScienceCodeIndicator(String scienceCodeIndicator) {
        this.scienceCodeIndicator = scienceCodeIndicator;
    }



    public String getSpecialReviewIndicator() {
        return specialReviewIndicator;
    }

    /**
     * 
     * @param specialReviewIndicator
     */
    public void setSpecialReviewIndicator(String specialReviewIndicator) {
        this.specialReviewIndicator = specialReviewIndicator;
    }


    /**\
     * 
     * @return
     */
    public String getSponsorAwardNumber() {
        return sponsorAwardNumber;
    }

    /**
     * 
     * @param sponsorAwardNumber
     */
    public void setSponsorAwardNumber(String sponsorAwardNumber) {
        this.sponsorAwardNumber = sponsorAwardNumber;
    }



    public String getTransferSponsorIndicator() {
        return transferSponsorIndicator;
    }

    /**
     * This method finds the lead unit name, if any
     * @return
     */
    public String getUnitName() {
        Unit leadUnit = getLeadUnit();
        return leadUnit != null ? leadUnit.getUnitName() : null;
    }

    /**
     * This method finds the lead unit number, if any
     * @return
     */
    public String getUnitNumber() {
        return unitNumber;
    }


    public String getLeadUnitNumber() {
        return getUnitNumber();
    }

    /**
     * 
     * @param transferSponsorIndicator
     */
    public void setTransferSponsorIndicator(String transferSponsorIndicator) {
        this.transferSponsorIndicator = transferSponsorIndicator;
    }


    public Integer getAccountTypeCode() {
        return accountTypeCode;
    }

    /**
     * 
     * @param accountTypeCode
     */
    public void setAccountTypeCode(Integer accountTypeCode) {
        this.accountTypeCode = accountTypeCode;
    }



    public String getActivityTypeCode() {
        return activityTypeCode;
    }

    /**
     * 
     * @param activityTypeCode
     */
    public void setActivityTypeCode(String activityTypeCode) {
        this.activityTypeCode = activityTypeCode;
    }



    public Integer getAwardTypeCode() {
        return awardTypeCode;
    }

    /**
     * 
     * @param awardTypeCode
     */
    public void setAwardTypeCode(Integer awardTypeCode) {
        this.awardTypeCode = awardTypeCode;
    }

    /**
     * 
     * cfdaNumber is an acronym. Its full meaning is Catalog of Federal Domestic Assistance
     * @return
     */
    public String getCfdaNumber() {
        return cfdaNumber;
    }

    /**
     * 
     * cfdaNumber is an acronym. Its full meaning is Catalog of Federal Domestic Assistance
     * @param cfdaNumber
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
        ospAdministratorName = ospAdministrator != null ? ospAdministrator.getFullName() : null;
        return ospAdministratorName;
    }


    public ScaleTwoDecimal getPreAwardAuthorizedAmount() {
        return preAwardAuthorizedAmount;
    }

    /**
     * For negative values, this method makes the number positive by dropping the negative sign.
     * @param preAwardAuthorizedAmount
     */
    public void setPreAwardAuthorizedAmount(ScaleTwoDecimal preAwardAuthorizedAmount) {
        // if preAwardAuthorizedAmount is negative, make it positive
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

    /**
     * 
     * @param preAwardEffectiveDate
     */
    public void setPreAwardEffectiveDate(Date preAwardEffectiveDate) {
        this.preAwardEffectiveDate = preAwardEffectiveDate;
    }



    public String getProcurementPriorityCode() {
        return procurementPriorityCode;
    }

    /**
     * 
     * @param procurementPriorityCode
     */
    public void setProcurementPriorityCode(String procurementPriorityCode) {
        this.procurementPriorityCode = procurementPriorityCode;
    }



    public String getProposalNumber() {
        return proposalNumber;
    }

    /**
     * 
     * @param proposalNumber
     */
    public void setProposalNumber(String proposalNumber) {
        this.proposalNumber = proposalNumber;
    }



    public ScaleTwoDecimal getSpecialEbRateOffCampus() {
        return specialEbRateOffCampus;
    }

    /**
     * 
     * @param specialEbRateOffCampus
     */
    public void setSpecialEbRateOffCampus(ScaleTwoDecimal specialEbRateOffCampus) {
        this.specialEbRateOffCampus = specialEbRateOffCampus;
    }



    public ScaleTwoDecimal getSpecialEbRateOnCampus() {
        return specialEbRateOnCampus;
    }

    /**
     * 
     * @param specialEbRateOnCampus
     */
    public void setSpecialEbRateOnCampus(ScaleTwoDecimal specialEbRateOnCampus) {
        this.specialEbRateOnCampus = specialEbRateOnCampus;
    }



    public String getSubPlanFlag() {
        return subPlanFlag;
    }

    /**
     * 
     * @param subPlanFlag
     */
    public void setSubPlanFlag(String subPlanFlag) {
        this.subPlanFlag = subPlanFlag;
    }



    public String getTitle() {
        return title;
    }

    /**
     * 
     * @param title
     */
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


    /**
     * Gets the awardTransactionTypeCode attribute.
     * @return Returns the awardTransactionTypeCode.
     */
    public Integer getAwardTransactionTypeCode() {
        return awardTransactionTypeCode;
    }

    /**
     * Sets the awardTransactionTypeCode attribute value.
     * @param awardTransactionTypeCode The awardTransactionTypeCode to set.
     */
    public void setAwardTransactionTypeCode(Integer awardTransactionTypeCode) {
        this.awardTransactionTypeCode = awardTransactionTypeCode;
    }

    /**
     * Gets the noticeDate attribute.
     * @return Returns the noticeDate.
     */
    public Date getNoticeDate() {
        return noticeDate;
    }

    /**
     * Sets the noticeDate attribute value.
     * @param noticeDate The noticeDate to set.
     */
    public void setNoticeDate(Date noticeDate) {
        if (getNewVersion())
        {
            this.noticeDate = null;
        }
        else
        {
            this.noticeDate = noticeDate;
        }
    }

    /**
     * Gets the currentActionComments attribute.
     * @return Returns the currentActionComments.
     */
    public String getCurrentActionComments() {
        return currentActionComments;
    }

    /**
     * Sets the currentActionComments attribute value.
     * @param currentActionComments The currentActionComments to set.
     */
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

    /**
     * sets newVersion to specified value
     * @param newVersion the newVersion to be set
     */
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

    /**
     * Gets the newVersion attribute
     * @return Returns the newVersion attribute
     */
    public boolean getNewVersion ()
    {
        return this.newVersion;
    }


    /**
     * Gets the awardTransactionType attribute.
     * @return Returns the awardTransactionType.
     */
    public AwardTransactionType getAwardTransactionType() {
        return awardTransactionType;
    }

    /**
     * Sets the awardTransactionType attribute value.
     * @param awardTransactionType The awardTransactionType to set.
     */
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
            this.refreshReferenceObject("awardDocument");
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

    /**
     * 
     * Get the award Cost Share Comments. If the comment has not been set...... initialize and return new Comment.
     */
    public AwardComment getAwardCostShareComment() {
        return getAwardCommentByType(Constants.COST_SHARE_COMMENT_TYPE_CODE, Constants.AWARD_COMMENT_INCLUDE_IN_CHECKLIST, true);
    }

    /**
     * 
    * Get the award PreAward Sponsor Authorizations comments.  If the comment has not been set...... initialize and return new Comment.
     */
    public AwardComment getawardPreAwardSponsorAuthorizationComment() {
        return getAwardCommentByType( Constants.PREAWARD_SPONSOR_AUTHORIZATION_COMMENT_TYPE_CODE,Constants.AWARD_COMMENT_EXCLUDE_FROM_CHECKLIST, true );
    }

    /**
     * 
    * Get the award PreAward Institutional Authorizations comments.  If the comment has not been set...... initialize and return new Comment.
     */
    public AwardComment getawardPreAwardInstitutionalAuthorizationComment() {
        return getAwardCommentByType( Constants.PREAWARD_INSTITUTIONAL_AUTHORIZATION_COMMENT_TYPE_CODE,Constants.AWARD_COMMENT_EXCLUDE_FROM_CHECKLIST, true );
    }

    /**
     * 
     * Get the award F & A Rates Comments. If the comment has not been set...... initialize and return new Comment.
     */
    public AwardComment getAwardFandaRateComment() {
        return getAwardCommentByType(Constants.FANDA_RATE_COMMENT_TYPE_CODE, Constants.AWARD_COMMENT_INCLUDE_IN_CHECKLIST, true);
    }

    /**
     * 
    * Get the award AwardPaymentAndInvoiceRequirementsComments.  If the comment has not been set...... initialize and return new Comment.
     */
    public AwardComment getAwardPaymentAndInvoiceRequirementsComments() {
        return getAwardCommentByType( Constants.PAYMENT_AND_INVOICES_COMMENT_TYPE_CODE,Constants.AWARD_COMMENT_INCLUDE_IN_CHECKLIST, true );
    }

    /**
     * 
     * Get the award Benefits Rate comments. If the comment has not been set...... initialize and return new Comment.
     */
    public AwardComment getAwardBenefitsRateComment() {
        return getAwardCommentByType(Constants.BENEFITS_RATES_COMMENT_TYPE_CODE, Constants.AWARD_COMMENT_INCLUDE_IN_CHECKLIST, true);
    }

    /**
     * 
     * Get the award General Comments. If the comment has not been set...... initialize and return new Comment.
     */
    public AwardComment getAwardGeneralComments() {
        return getAwardCommentByType(Constants.GENERAL_COMMENT_TYPE_CODE, Constants.AWARD_COMMENT_INCLUDE_IN_CHECKLIST, true);
    }

    /**
     * 
     * Get the award fiscal report comments. If the comment has not been set...... initialize and return new Comment.
     */
    public AwardComment getAwardFiscalReportComments() {
        return getAwardCommentByType(Constants.FISCAL_REPORT_COMMENT_TYPE_CODE, Constants.AWARD_COMMENT_INCLUDE_IN_CHECKLIST, true);
    }

    /**
     * 
     * Get the award current action comments. If the comment has not been set...... initialize and return new Comment.
     */
    public AwardComment getAwardCurrentActionComments() {
        return getAwardCommentByType( Constants.CURRENT_ACTION_COMMENT_TYPE_CODE,Constants.AWARD_COMMENT_EXCLUDE_FROM_CHECKLIST, true );
    }

    /**
     * 
     * Get the award Intellectual Property comments. If the comment has not been set...... initialize and return new Comment.
     */
    public AwardComment getAwardIntellectualPropertyComments() {
        return getAwardCommentByType( Constants.INTELLECTUAL_PROPERTY_COMMENT_TYPE_CODE,Constants.AWARD_COMMENT_EXCLUDE_FROM_CHECKLIST, true );
    }

    /**
     * 
     * Get the award Procurement Comments. If the comment has not been set...... initialize and return new Comment.
     */
    public AwardComment getAwardProcurementComments() {
        return getAwardCommentByType(Constants.PROCUREMENT_COMMENT_TYPE_CODE, Constants.AWARD_COMMENT_INCLUDE_IN_CHECKLIST, true);
    }

    /**
     * 
     * Get the award Award Property Comments. If the comment has not been set...... initialize and return new Comment.
     */
    public AwardComment getAwardPropertyComments() {
        return getAwardCommentByType(Constants.PROPERTY_COMMENT_TYPE_CODE, Constants.AWARD_COMMENT_INCLUDE_IN_CHECKLIST, true);
    }

    /**
     * 
     * Get the award Special Rate comments. If the comment has not been set...... initialize and return new Comment.
     */
    public AwardComment getAwardSpecialRate() {
        return getAwardCommentByType(Constants.SPECIAL_RATE_COMMENT_TYPE_CODE, Constants.AWARD_COMMENT_EXCLUDE_FROM_CHECKLIST, true);
    }

    /**
     * 
     * Get the award Special Review Comments. If the comment has not been set...... initialize and return new Comment.
     */
    public AwardComment getAwardSpecialReviewComments() {
        return getAwardCommentByType( Constants.SPECIAL_REVIEW_COMMENT_TYPE_CODE,Constants.AWARD_COMMENT_EXCLUDE_FROM_CHECKLIST, true );
    }

    /**
     * 
     * Get the award Proposal Summary comments. If the comment has not been set...... initialize and return new Comment.
     */
    public AwardComment getawardProposalSummary() {
        return getAwardCommentByType( Constants.PROPOSAL_SUMMARY_COMMENT_TYPE_CODE,Constants.AWARD_COMMENT_EXCLUDE_FROM_CHECKLIST, true );
    }

    /**
     * 
     * Get the award Proposal comments. If the comment has not been set...... initialize and return new Comment.
     */
    public AwardComment getawardProposalComments() {
        return getAwardCommentByType(Constants.PROPOSAL_COMMENT_TYPE_CODE, Constants.AWARD_COMMENT_EXCLUDE_FROM_CHECKLIST, true);
    }

    /**
     * 
     * Get the award Proposal IP Review Comments. If the comment has not been set...... initialize and return new Comment.
     */
    public AwardComment getAwardProposalIPReviewComment() {
        return getAwardCommentByType( Constants.PROPOSAL_IP_REVIEW_COMMENT_TYPE_CODE,Constants.AWARD_COMMENT_EXCLUDE_FROM_CHECKLIST, true );
    }

    /*
     * Get a comment by type. If it does not exist, then create it.
     */

    public AwardComment getAwardCommentByType(String awardTypeCode, Boolean checklistPrintFlag, boolean createNew) {
        AwardCommentFactory awardCommentFactory = new AwardCommentFactory();
        AwardComment awardComment = getCommentMap().get(awardTypeCode);
        if ((awardComment == null && createNew)) {
            awardComment = awardCommentFactory.createAwardComment(awardTypeCode, (checklistPrintFlag == null ? false : checklistPrintFlag.booleanValue()));
            add(awardComment);
            commentMap.put(awardComment.getCommentType().getCommentTypeCode(), awardComment);
        }
        return awardComment;
    }


    /*
     * Get a sponsor term by sponsor term id.
     */

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

    /**
     * This method calls getTotalAmount to calculate the total of all Commitment Amounts.
     * @return
     */
    public ScaleTwoDecimal getTotalCostShareCommitmentAmount() {
        return getTotalAmount(awardCostShares);
    }

    /**
     * This method calculates the total Cost Share Met amount for all Award Cost Shares.
     * @param valuableItems
     * @return The total value
     */
    public ScaleTwoDecimal getTotalCostShareMetAmount() {
        ScaleTwoDecimal returnVal = new ScaleTwoDecimal(0.00);
        for (AwardCostShare awardCostShare : awardCostShares) {
             ScaleTwoDecimal amount = awardCostShare.getCostShareMet() != null ? awardCostShare.getCostShareMet() : new ScaleTwoDecimal(0.00);
            returnVal = returnVal.add(amount);
        }
        return returnVal;
    }

    /**
     * This method calculates the total Direct Cost Amount for all Direct F and A Distributions.
     * @return The total value
     */
    public ScaleTwoDecimal getTotalDirectFandADistributionDirectCostAmount() {
        ScaleTwoDecimal returnVal = new ScaleTwoDecimal(0.00);
        for (AwardDirectFandADistribution awardDirectFandADistribution : awardDirectFandADistributions) {
            ScaleTwoDecimal amount;
            if (awardDirectFandADistribution.getDirectCost() != null) {
                amount = awardDirectFandADistribution.getDirectCost();
             }else {
                amount = new ScaleTwoDecimal(0.00);
            }
            returnVal = returnVal.add(amount);
        }
        return returnVal;
    }

    /**
     * This method calculates the total Direct Cost Amount for all Direct F and A Distributions.
     * @return The total value
     */
    public ScaleTwoDecimal getTotalDirectFandADistributionIndirectCostAmount() {
        ScaleTwoDecimal returnVal = new ScaleTwoDecimal(0.00);
        for (AwardDirectFandADistribution awardDirectFandADistribution : awardDirectFandADistributions) {
            ScaleTwoDecimal amount;
            if (awardDirectFandADistribution.getIndirectCost() != null) {
                amount = awardDirectFandADistribution.getIndirectCost();
             }else {
                amount = new ScaleTwoDecimal(0.00);
            }
            returnVal = returnVal.add(amount);
        }
        return returnVal;
    }

    /**
     * This method calculates the total Direct Cost Amount for all Direct F and A Distributions.
     * @return The total value
     */
    public ScaleTwoDecimal getTotalDirectFandADistributionAnticipatedCostAmount() {
        ScaleTwoDecimal returnVal = new ScaleTwoDecimal(0.00);
        returnVal = returnVal.add(getTotalDirectFandADistributionDirectCostAmount());
        returnVal = returnVal.add(getTotalDirectFandADistributionIndirectCostAmount());
        return returnVal;
    }

    /**
     * This method totals Approved SubAward amounts
     * @return
     */
    public ScaleTwoDecimal getTotalApprovedSubawardAmount() {
        return getTotalAmount(awardApprovedSubawards);
    }

    /**
     * This method totals Approved Equipment amounts
     * @return
     */
    public ScaleTwoDecimal getTotalApprovedEquipmentAmount() {
        return getTotalAmount(approvedEquipmentItems);
    }

    /**
     * This method Approved Foreign Travel trip amounts
     * @return
     */
    public ScaleTwoDecimal getTotalApprovedApprovedForeignTravelAmount() {
        return getTotalAmount(approvedForeignTravelTrips);
    }

    public List<AwardFandaRate> getAwardFandaRate() {
        return awardFandaRate;
    }

    public void setAwardFandaRate(List<AwardFandaRate> awardFandaRate) {
        this.awardFandaRate = awardFandaRate;
    }

    /**
     * Gets the keywords attribute.
     * @return Returns the keywords.
     */
    @Override
    public List<AwardScienceKeyword> getKeywords() {
        return keywords;
    }

    /**
     * Sets the keywords attribute value.
     * @param keywords The keywords to set.
     */
    public void setKeywords(List<AwardScienceKeyword> keywords) {
        this.keywords = keywords;
    }

    /**
     * @param leadUnit
     */
    public void setLeadUnit(Unit leadUnit) {
        this.leadUnit = leadUnit;
        this.unitNumber = leadUnit != null ? leadUnit.getUnitNumber() : null;
    }

    public void setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber;
    }

    /**
     * Add selected science keyword to award science keywords list.
     * @see org.kuali.coeus.common.framework.keyword.KeywordsManager#addKeyword(org.kuali.coeus.common.framework.keyword.ScienceKeyword)
     */
    public void addKeyword(ScienceKeyword scienceKeyword) {
        AwardScienceKeyword awardScienceKeyword = new AwardScienceKeyword(getAwardId(), scienceKeyword);
        getKeywords().add(awardScienceKeyword);
    }

    /**
     * It returns the ScienceKeyword object from keywords list
     * @see org.kuali.coeus.common.framework.keyword.KeywordsManager#getKeyword(int)
     */
    public AwardScienceKeyword getKeyword(int index) {
        return getKeywords().get(index);
    }

    /**
     * Sets the awardSpecialReviews attribute value.
     * @param awardSpecialReviews The awardSpecialReviews to set.
     */
    public void setSpecialReviews(List<AwardSpecialReview> awardSpecialReviews) {
        this.specialReviews = awardSpecialReviews;
    }

    /**
     * Add AwardSpecialReview to the AwardSpecialReview list
     * @see org.kuali.kra.document.SpecialReviewHandler#addSpecialReview(java.lang.Object)
     */
    public void addSpecialReview(AwardSpecialReview specialReview) {
        specialReview.setSequenceOwner(this);
        getSpecialReviews().add(specialReview);
    }

    /**
     * Get AwardSpecialReview from special review list
     * @see org.kuali.kra.document.SpecialReviewHandler#getSpecialReview(int)
     */
    public AwardSpecialReview getSpecialReview(int index) {
        return getSpecialReviews().get(index);
    }

    /**
     * Get special review list
     * @see org.kuali.kra.document.SpecialReviewHandler#getSpecialReviews()
     */
    public List<AwardSpecialReview> getSpecialReviews() {
        return specialReviews;
    }

    /**
     * Add an ApprovedEquipment item
     * @param newAwardApprovedEquipment
     */
    public void add(AwardApprovedEquipment approvedEquipmentItem) {
        approvedEquipmentItems.add(0, approvedEquipmentItem);
        approvedEquipmentItem.setAward(this);
    }

    /**
     * Add an AwardFandaRate
     * @param fandaRate
     */
    public void add(AwardFandaRate fandaRate) {
        awardFandaRate.add(fandaRate);
        fandaRate.setAward(this);
    }

    /**
     * @param awardSpecialReview
     */
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
        Collections.sort(awardCloseoutNewItems, new Comparator(){
            public int compare(Object o1, Object o2) {
                if(o1 instanceof AwardCloseout && o2 instanceof AwardCloseout) {
                    AwardCloseout awardCloseout1 = (AwardCloseout)o1;
                    AwardCloseout awardCloseout2 = (AwardCloseout)o2;
                   
                    return awardCloseout1.getCloseoutReportName().compareTo(awardCloseout2.getCloseoutReportName());
                }
                return 0;
              }});
        awardCloseoutItems.addAll(TOTAL_STATIC_REPORTS, awardCloseoutNewItems);
        awardCloseoutItem.setAward(this);
    }

    public void addStaticCloseout(AwardCloseout awardCloseoutItem) {
        awardCloseoutItems.add(awardCloseoutItem);
        awardCloseoutItem.setAward(this);
    }

    /**
     * Add an Award Unit or Central Administration contact
     * @param newAwardApprovedEquipment
     */
    public void add(AwardUnitContact awardUnitContact) {
        awardUnitContacts.add(awardUnitContact);
        awardUnitContact.setAward(this);
    }

    /**
     * Creates an AwardFundingProposal and adds it to the collection
     * 
     * It also adds the AwardFundingProposal to the InstitutionalProposal
     * 
     * @param institutionalProposal
     */
    public void add(InstitutionalProposal institutionalProposal) {
        if (institutionalProposal != null) {
            AwardFundingProposal afp = new AwardFundingProposal(this, institutionalProposal);
            fundingProposals.add(afp);
            institutionalProposal.add(afp);
        }
    }

    /**
     * @param awardSponsorContact
     */
    public void addSponsorContact(AwardSponsorContact awardSponsorContact) {
        sponsorContacts.add(awardSponsorContact);
        awardSponsorContact.setAward(this);
    }

    /**
     * This method adds a Project Person to the award
     * @param projectPerson
     */
    public void add(AwardPerson projectPerson) {
        projectPersons.add(projectPerson);
        projectPerson.setAward(this);
    }

    /**
     * Add an
     * @param newAwardPaymentSchedule
     */
    public void add(AwardPaymentSchedule paymentScheduleItem) {
        paymentScheduleItems.add(paymentScheduleItem);
        paymentScheduleItem.setAward(this);
    }

    public void addAwardTransferringSponsor(Sponsor sponsor) {
        AwardTransferringSponsor awardTransferringSponsor = new AwardTransferringSponsor(this, sponsor);
        awardTransferringSponsors.add(0, awardTransferringSponsor);
    }

    protected void initializeCollections() {
        setAwardCostShares(new ArrayList<AwardCostShare>());
        setAwardComments(new ArrayList<AwardComment>());
        awardApprovedSubawards = new ArrayList<AwardApprovedSubaward>();
        setAwardFandaRate(new ArrayList<AwardFandaRate>());
        setAwardReportTermItems(new ArrayList<AwardReportTerm>());
        keywords = new ArrayList<AwardScienceKeyword>();
        specialReviews = new ArrayList<AwardSpecialReview>();
        approvedEquipmentItems = new ArrayList<AwardApprovedEquipment>();
        approvedForeignTravelTrips = new ArrayList<AwardApprovedForeignTravel>();
        setAwardSponsorTerms(new ArrayList<AwardSponsorTerm>());
        paymentScheduleItems = new ArrayList<AwardPaymentSchedule>();
        awardTransferringSponsors = new ArrayList<AwardTransferringSponsor>();
        awardDirectFandADistributions = new ArrayList<AwardDirectFandADistribution>();
        awardCustomDataList = new ArrayList<AwardCustomData>();
        awardCloseoutItems = new ArrayList<AwardCloseout>();
        awardCloseoutNewItems = new ArrayList<AwardCloseout>();
        awardNotepads = new ArrayList<AwardNotepad>();
        initializeAwardAmountInfoObjects();
        projectPersons = new ArrayList<AwardPerson>();
        awardUnitContacts = new ArrayList<AwardUnitContact>();
        sponsorContacts = new ArrayList<AwardSponsorContact>();
        awardBudgetLimits = new ArrayList<AwardBudgetLimit>();

        fundingProposals = new ArrayList<AwardFundingProposal>();
        initializeAwardHierarchyTempObjects();

        syncChanges = new ArrayList<AwardSyncChange>();
        syncStatuses = new ArrayList<AwardSyncStatus>();
        subAwardList = new ArrayList<SubAward>();
        budgets = new ArrayList<AwardBudgetExt>();
    }

    public void initializeAwardAmountInfoObjects() {
        awardAmountInfos = new ArrayList<AwardAmountInfo>();
        AwardAmountInfo awardAmountInfo = new AwardAmountInfo();
        awardAmountInfo.setAward(this);
        awardAmountInfo.setOriginatingAwardVersion(1);
        awardAmountInfos.add(awardAmountInfo);
    }

    public void initializeAwardHierarchyTempObjects() {
        awardHierarchyTempObjects = new AutoPopulatingList<AwardHierarchyTempObject>(AwardHierarchyTempObject.class);
    }

    public ScaleTwoDecimal getPreAwardInstitutionalAuthorizedAmount() {
        return preAwardInstitutionalAuthorizedAmount;
    }

    /**
     * For negative values, this method makes the number positive by dropping the negative sign.
     * @param preAwardInstitutionalAuthorizedAmount
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

    /**
     * This method adds template comments to award when sync to template is being applied.
     * @param awardComment
     */
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

    /**
     * This method adds template sponsor terms to award when sync to template is being applied.
     * @param awardTemplateTerms
     */
    public void addTemplateTerms(List<AwardTemplateTerm> awardTemplateTerms) {
        List<AwardSponsorTerm> tempAwardSponsorTerms = new ArrayList<AwardSponsorTerm>();
        for (AwardTemplateTerm awardTemplateTerm : awardTemplateTerms) {
            tempAwardSponsorTerms.add(new AwardSponsorTerm(awardTemplateTerm.getSponsorTermId(), awardTemplateTerm.getSponsorTerm()));
        }
        setAwardSponsorTerms(tempAwardSponsorTerms);
    }

    /**
     * This method adds AwardDirectFandADistribution to end of list.
     * @param awardDirectFandADistribution
     */
    public void add(AwardDirectFandADistribution awardDirectFandADistribution) {
        awardDirectFandADistributions.add(awardDirectFandADistribution);
        awardDirectFandADistribution.setAward(this);
        awardDirectFandADistribution.setBudgetPeriod(awardDirectFandADistributions.size());
    }

    /**
     * This method adds AwardDirectFandADistribution to the given index in the list.
     * @param awardDirectFandADistribution
     */
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

    /**
     * This method updates the budget periods in the Award after insertion of new Award Direct F and A Distribution into list.
     * @param index
     */
    public void updateDirectFandADistributionBudgetPeriods(int index) {
        for (int newIndex = index; newIndex < awardDirectFandADistributions.size(); newIndex++) {
            awardDirectFandADistributions.get(newIndex).setBudgetPeriod(newIndex + 1);
        }
    }


    /**
     * This method calculates the total value of a list of ValuableItems
     * @param valuableItems
     * @return The total value
     */
    ScaleTwoDecimal getTotalAmount(List<? extends ValuableItem> valuableItems) {
        ScaleTwoDecimal returnVal = new ScaleTwoDecimal(0.00);
        for (ValuableItem item : valuableItems) {
            ScaleTwoDecimal amount = item.getAmount() != null ? item.getAmount() : new ScaleTwoDecimal(0.00);
            returnVal = returnVal.add(amount);
        }
        return returnVal;
    }

    /**
     * Gets the awardSponsorTerms attribute.
     * @return Returns the awardSponsorTerms.
     */
    public List<AwardSponsorTerm> getAwardSponsorTerms() {
        return awardSponsorTerms;
    }


    public AwardStatus getAwardStatus() {
        if (awardStatus == null && statusCode != null) {
            refreshReferenceObject("awardStatus");
        }
        return awardStatus;
    }

    /**
     * Sets the awardSponsorTerms attribute value.
     * @param awardSponsorTerms The awardSponsorTerms to set.
     */
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
     * @return
     */

    public Sponsor getSponsor() {
        if (!StringUtils.isEmpty(sponsorCode)) {
            this.refreshReferenceObject("sponsor");
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
        sponsorName = sponsor != null ? sponsor.getSponsorName() : null;
        return sponsorName;
    }
    
    public String getIcrRateCode() {
        return icrRateCode;
    }

    public void setIcrRateCode(String icrRateCode) {
        this.icrRateCode = icrRateCode;
    }

    /**
     * This method adds an approved foreign travel trip
     * @param approvedForeignTravelTrip
     */
    public void add(AwardApprovedForeignTravel approvedForeignTravelTrip) {
        approvedForeignTravelTrips.add(approvedForeignTravelTrip);
        approvedForeignTravelTrip.setAward(this);
    }

    /**
     * Gets the paymentScheduleItems attribute.
     * @return Returns the paymentScheduleItems.
     */
    public List<AwardPaymentSchedule> getPaymentScheduleItems() {
        return paymentScheduleItems;
    }

    /**
     * Sets the paymentScheduleItems attribute value.
     * @param paymentScheduleItems The paymentScheduleItems to set.
     */
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

    // Note: following the pattern of Sponsor, this getter indirectly calls a service.
    // Is there a better way?
    public Sponsor getPrimeSponsor() {
        if (!StringUtils.isEmpty(getPrimeSponsorCode())) {
            this.refreshReferenceObject("primeSponsor");
        }
        return primeSponsor;
    }

    public void setPrimeSponsor(Sponsor primeSponsor) {
        this.primeSponsor = primeSponsor;
    }

    public List<AwardTransferringSponsor> getAwardTransferringSponsors() {
        return awardTransferringSponsors;
    }

    /**
     * @param awardStatus
     */
    public void setAwardStatus(AwardStatus awardStatus) {
        this.awardStatus = awardStatus;
    }

    public void setAwardTransferringSponsors(List<AwardTransferringSponsor> awardTransferringSponsors) {
        this.awardTransferringSponsors = awardTransferringSponsors;
    }

    /**
     * Gets the awardDirectFandADistribution attribute.
     * @return Returns the awardDirectFandADistribution.
     */
    public List<AwardDirectFandADistribution> getAwardDirectFandADistributions() {
        return awardDirectFandADistributions;
    }

    /**
     * Sets the awardDirectFandADistribution attribute value.
     * @param awardDirectFandADistribution The awardDirectFandADistribution to set.
     */
    public void setAwardDirectFandADistributions(List<AwardDirectFandADistribution> awardDirectFandADistributions) {
        for (AwardDirectFandADistribution awardDirectFandADistribution : awardDirectFandADistributions) {
            awardDirectFandADistribution.setAward(this);
        }
        this.awardDirectFandADistributions = awardDirectFandADistributions;
    }

    /**
     * Gets the awardNotepads attribute.
     * @return Returns the awardNotepads.
     */
    public List<AwardNotepad> getAwardNotepads() {
        return awardNotepads;
    }

    /**
     * Sets the awardNotepads attribute value.
     * @param awardNotepads The awardNotepads to set.
     */
    public void setAwardNotepads(List<AwardNotepad> awardNotepads) {
        this.awardNotepads = awardNotepads;
    }

    /**
     * Gets the indirectCostIndicator attribute.
     * @return Returns the indirectCostIndicator.
     */
    public String getIndirectCostIndicator() {
        return indirectCostIndicator;
    }

    /**
     * Sets the indirectCostIndicator attribute value.
     * @param indirectCostIndicator The indirectCostIndicator to set.
     */
    public void setIndirectCostIndicator(String indirectCostIndicator) {
        this.indirectCostIndicator = indirectCostIndicator;
    }


    /**
     * Gets the obligatedTotal attribute.
     * @return Returns the obligatedTotal.
     */
    public ScaleTwoDecimal getObligatedTotal() {
        ScaleTwoDecimal returnValue = new ScaleTwoDecimal(0.00);
        // if(awardAmountInfos.get(0).getAmountObligatedToDate()!=null){
        // returnValue = returnValue.add(awardAmountInfos.get(0).getAmountObligatedToDate());
        // }
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
     * @return
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

    /**
     * Gets the obligatedTotal attribute.
     * @return Returns the obligatedTotal.
     */
    public ScaleTwoDecimal getObligatedTotalDirect() {
        ScaleTwoDecimal returnValue = new ScaleTwoDecimal(0.00);
        // if(awardAmountInfos.get(0).getAmountObligatedToDate()!=null){
        // returnValue = returnValue.add(awardAmountInfos.get(0).getAmountObligatedToDate());
        // }
        if (getLastAwardAmountInfo().getObligatedTotalDirect() != null) {
            returnValue = returnValue.add(getLastAwardAmountInfo().getObligatedTotalDirect());
        }
        return returnValue;
    }

    /**
     * Gets the obligatedTotal attribute.
     * @return Returns the obligatedTotal.
     */
    public ScaleTwoDecimal getObligatedTotalIndirect() {
        ScaleTwoDecimal returnValue = new ScaleTwoDecimal(0.00);
        // if(awardAmountInfos.get(0).getAmountObligatedToDate()!=null){
        // returnValue = returnValue.add(awardAmountInfos.get(0).getAmountObligatedToDate());
        // }
        if (getLastAwardAmountInfo().getObligatedTotalIndirect() != null) {
            returnValue = returnValue.add(getLastAwardAmountInfo().getObligatedTotalIndirect());
        }
        return returnValue;
    }

    /**
     * Gets the anticipatedTotal attribute.
     * @return Returns the anticipatedTotal.
     */
    public ScaleTwoDecimal getAnticipatedTotal() {
        ScaleTwoDecimal returnValue = new ScaleTwoDecimal(0.00);
        // if(awardAmountInfos.get(0).getAnticipatedTotalAmount()!=null){
        // returnValue = returnValue.add(awardAmountInfos.get(0).getAnticipatedTotalAmount());
        // }
        if (getLastAwardAmountInfo().getAnticipatedTotalAmount() != null) {
            returnValue = returnValue.add(getLastAwardAmountInfo().getAnticipatedTotalAmount());
        }
        return returnValue;
    }

    /**
     * Gets the anticipatedTotal attribute.
     * @return Returns the anticipatedTotal.
     */
    public ScaleTwoDecimal getAnticipatedTotalDirect() {
        ScaleTwoDecimal returnValue = new ScaleTwoDecimal(0.00);
        // if(awardAmountInfos.get(0).getAnticipatedTotalAmount()!=null){
        // returnValue = returnValue.add(awardAmountInfos.get(0).getAnticipatedTotalAmount());
        // }
        if (getLastAwardAmountInfo().getAnticipatedTotalDirect() != null) {
            returnValue = returnValue.add(getLastAwardAmountInfo().getAnticipatedTotalDirect());
        }
        return returnValue;
    }

    /**
     * Gets the anticipatedTotal attribute.
     * @return Returns the anticipatedTotal.
     */
    public ScaleTwoDecimal getAnticipatedTotalIndirect() {
        ScaleTwoDecimal returnValue = new ScaleTwoDecimal(0.00);
        // if(awardAmountInfos.get(0).getAnticipatedTotalAmount()!=null){
        // returnValue = returnValue.add(awardAmountInfos.get(0).getAnticipatedTotalAmount());
        // }
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
        List<String> roles = new ArrayList<String>();

        SystemAuthorizationService systemAuthorizationService = KcServiceLocator.getService("systemAuthorizationService");
        List<Role> roleBOs = systemAuthorizationService.getRoles(Constants.MODULE_NAMESPACE_AWARD);
        for(Role role : roleBOs) {
            roles.add(role.getName());
        }
 
        return roles;
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
    
    /**
     * Find the lead unit for the award
     * @return
     */
    public Unit getLeadUnit() {
        if (leadUnit == null && unitNumber != null) {
            loadLeadUnit();
        }
        return leadUnit;
    }

    public boolean isNew() {
        return awardId == null;
    }

    class ARTComparator implements Comparator 
    {
        
        public int compare(Object art1, Object art2) 
        {
            try
            {
                String art1Desc = ((AwardReportTerm) art1).getReport().getDescription();    
                String art2Desc = ((AwardReportTerm) art2).getReport().getDescription();
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

    /**
     * @param principalInvestigatorName
     */
    public void setPrincipalInvestigatorName(String principalInvestigatorName) {
        this.principalInvestigatorName = principalInvestigatorName;
    }

    /**
     * This method returns the status description
     * @return
     */
    public String getStatusDescription() {
        AwardStatus status = getAwardStatus();
        statusDescription = status != null ? status.getDescription() : null;
        return statusDescription;
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
            Collections.sort(awardCloseoutNewItems, new Comparator(){
              public int compare(Object o1, Object o2) {
                  if(o1 instanceof AwardCloseout && o2 instanceof AwardCloseout) {
                      AwardCloseout awardCloseout1 = (AwardCloseout)o1;
                      AwardCloseout awardCloseout2 = (AwardCloseout)o2;
                     
                      return awardCloseout1.getCloseoutReportName().compareTo(awardCloseout2.getCloseoutReportName());
                  }
                  return 0;
                }});
            awardCloseoutItems.addAll(TOTAL_STATIC_REPORTS, awardCloseoutNewItems);
        }
        this.awardCloseoutItems = awardCloseoutItems;
    }

    /**
     * Gets the awardCloseoutNewItems attribute. 
     * @return Returns the awardCloseoutNewItems.
     */
    public List<AwardCloseout> getAwardCloseoutNewItems() {
        return awardCloseoutNewItems;
    }

    /**
     * Sets the awardCloseoutNewItems attribute value.
     * @param awardCloseoutNewItems The awardCloseoutNewItems to set.
     */
    public void setAwardCloseoutNewItems(List<AwardCloseout> awardCloseoutNewItems) {
        this.awardCloseoutNewItems = awardCloseoutNewItems;
    }

    /**
     * Sets the templateCode attribute value.
     * @param templateCode The templateCode to set.
     */
    public void setTemplateCode(Integer templateCode) {
        this.templateCode = templateCode;
    }

    /**
     * Gets the primeSponsorCode attribute.
     * @return Returns the primeSponsorCode.
     */
    public String getPrimeSponsorCode() {
        return primeSponsorCode;
    }

    /**
     * Sets the primeSponsorCode attribute value.
     * @param primeSponsorCode The primeSponsorCode to set.
     */
    public void setPrimeSponsorCode(String primeSponsorCode) {
        this.primeSponsorCode = primeSponsorCode;
    }

    /**
     * Gets the basisOfPaymentCode attribute.
     * @return Returns the basisOfPaymentCode.
     */
    public String getBasisOfPaymentCode() {
        return basisOfPaymentCode;
    }

    /**
     * Sets the basisOfPaymentCode attribute value.
     * @param basisOfPaymentCode The basisOfPaymentCode to set.
     */
    public void setBasisOfPaymentCode(String basisOfPaymentCode) {
        this.basisOfPaymentCode = basisOfPaymentCode;
    }

    /**
     * Gets the methodOfPaymentCode attribute.
     * @return Returns the methodOfPaymentCode.
     */
    public String getMethodOfPaymentCode() {
        return methodOfPaymentCode;
    }

    /**
     * Sets the methodOfPaymentCode attribute value.
     * @param methodOfPaymentCode The methodOfPaymentCode to set.
     */
    public void setMethodOfPaymentCode(String methodOfPaymentCode) {
        this.methodOfPaymentCode = methodOfPaymentCode;
    }

    /**
     * Gets the awardTemplate attribute.
     * @return Returns the awardTemplate.
     */
    public AwardTemplate getAwardTemplate() {
        return awardTemplate;
    }

    /**
     * Sets the awardTemplate attribute value.
     * @param awardTemplate The awardTemplate to set.
     */
    public void setAwardTemplate(AwardTemplate awardTemplate) {
        this.awardTemplate = awardTemplate;
    }

    /**
     * Gets the awardBasisOfPayment attribute.
     * @return Returns the awardBasisOfPayment.
     */
    public AwardBasisOfPayment getAwardBasisOfPayment() {
        return awardBasisOfPayment;
    }

    /**
     * Sets the awardBasisOfPayment attribute value.
     * @param awardBasisOfPayment The awardBasisOfPayment to set.
     */
    public void setAwardBasisOfPayment(AwardBasisOfPayment awardBasisOfPayment) {
        this.awardBasisOfPayment = awardBasisOfPayment;
    }

    /**
     * Gets the awardMethodOfPayment attribute.
     * @return Returns the awardMethodOfPayment.
     */
    public AwardMethodOfPayment getAwardMethodOfPayment() {
        return awardMethodOfPayment;
    }

    /**
     * Sets the awardMethodOfPayment attribute value.
     * @param awardMethodOfPayment The awardMethodOfPayment to set.
     */
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
        return "awardNumber";
    }

    /**
     * Gets the activityType attribute.
     * @return Returns the activityType.
     */
    public ActivityType getActivityType() {
        return activityType;
    }

    /**
     * Sets the activityType attribute value.
     * @param activityType The activityType to set.
     */
    public void setActivityType(ActivityType activityType) {
        this.activityType = activityType;
    }

    /**
     * This method removes Funding Proposal for specified index from list
     * 
     * It also removes the AwardFundingProposal from the InstitutionalProposal
     * 
     * @param index
     */
    public AwardFundingProposal removeFundingProposal(int index) {
        AwardFundingProposal afp = (index >= 0) ? fundingProposals.remove(index) : null;
        afp.getProposalId();
        InstitutionalProposal proposal = getInstitutionalProposalService().getInstitutionalProposal(afp.getProposalId().toString());
        if (proposal != null) {
            proposal.remove(afp);
        }
        return afp;
    }
    private InstitutionalProposalService getInstitutionalProposalService() {
        return KcServiceLocator.getService(InstitutionalProposalService.class);
    }


    /**
     * Given an AwardComment as a template, try to find an existing AwardComment of that type
     * @param template
     * @return The found awardComment of a specific type. If an existing comment is not found, return null
     */
    public AwardComment findCommentOfSpecifiedType(AwardComment template) {
        return findCommentOfSpecifiedType(template.getCommentTypeCode());
    }

    /**
     * For a given type code, this method returns the award comment, or null if none exists.
     * @param commentTypeCode One of the ..._COMMENT_TYPE_CODE values in org.kuali.kra.infrastructure.Constants
     * @return
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
        // hard coded as completed
        return "2";
    }

    public List getPersonRolodexList() {
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
            return ((AwardPerson) getProposalEmployee(personId)).getContactRole();
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
            return ((AwardPerson) getProposalNonEmployee(rolodexId)).getContactRole();
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

    /**
     * Gets the attachmentsw. Cannot return {@code null}.
     * @return the attachments
     */
    public List<AwardAttachment> getAwardAttachments() {
        if (this.awardAttachments == null) {
            this.awardAttachments = new ArrayList<AwardAttachment>();
        }

        return this.awardAttachments;
    }

    public void setAttachments(List<AwardAttachment> attachments) {
        this.awardAttachments = attachments;
    }

    /**
     * Gets an attachment.
     * @param index the index
     * @return an attachment personnel
     */
    public AwardAttachment getAwardAttachment(int index) {
        return this.awardAttachments.get(index);
    }

    /**
     * add an attachment.
     * @param attachment the attachment
     * @throws IllegalArgumentException if attachment is null
     */
    public void addAttachment(AwardAttachment attachment) {
        this.getAwardAttachments().add(attachment);
        attachment.setAward(this);
    }

    /**
     * This method indicates if the Awrd has been persisted
     * @return True if persisted
     */
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
        leadUnit = (Unit) getBusinessObjectService().findByPrimaryKey(Unit.class, Collections.singletonMap("unitNumber", getUnitNumber()));
    }

    public void populateAdditionalQualifiedRoleAttributes(Map<String, String> qualifiedRoleAttributes) {
        /**
         * when we check to see if the logged in user can create an award account, this function is called, but awardDocument is null at that time.
         */
        String documentNumber = getAwardDocument() != null ? getAwardDocument().getDocumentNumber() : "";
        qualifiedRoleAttributes.put("documentNumber", documentNumber);
    }

    protected BusinessObjectService getBusinessObjectService() {
        return KcServiceLocator.getService(BusinessObjectService.class);
    }

    public String getHierarchyStatus() {
        return "N";
    }

    /**
     * This method gets the obligated, distributable amount for the Award. This may be replacable with the Award TimeAndMoney obligatedAmount value, but
     * at the time of its creation, TimeAndMoney wasn't complete
     * @return
     */
    public ScaleTwoDecimal calculateObligatedDistributedAmountTotal() {
        ScaleTwoDecimal sum = ScaleTwoDecimal.ZERO;
        for (AwardAmountInfo amountInfo : getAwardAmountInfos()) {
            ScaleTwoDecimal obligatedDistributableAmount = amountInfo.getObliDistributableAmount();
            sum = sum.add(obligatedDistributableAmount != null ? obligatedDistributableAmount : ScaleTwoDecimal.ZERO);
        }
        return sum;
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

    /**
     * 
     * @return awardHierarchyTempObjects
     */
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

    /**
     * 
     * This method text area tag need this method.
     * @param index
     * @return
     */
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
        if (awardIdAccount == null) {
            if (StringUtils.isNotBlank(getAccountNumber())) {
                awardIdAccount = getAwardNumber() + ":" + getAccountNumber();
            } else {
                awardIdAccount = getAwardNumber() + ":";
            }
        }
        return awardIdAccount;
    }

    public void setLookupOspAdministratorName(String lookupOspAdministratorName) {
        this.lookupOspAdministratorName = lookupOspAdministratorName;
    }

    public String getLookupOspAdministratorName() {
        return lookupOspAdministratorName;
    }

    /**
     * 
     * Returns a list of central admin contacts based on the lead unit of this award.
     * @return
     */
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
        centralAdminContacts = new ArrayList<AwardUnitContact>();
        List<UnitAdministrator> unitAdministrators = 
            KcServiceLocator.getService(UnitService.class).retrieveUnitAdministratorsByUnitNumber(getUnitNumber());
        for (UnitAdministrator unitAdministrator : unitAdministrators) {
            if(unitAdministrator.getUnitAdministratorType().getDefaultGroupFlag().equals(DEFAULT_GROUP_CODE_FOR_CENTRAL_ADMIN_CONTACTS)) {
                KcPerson person = getKcPersonService().getKcPersonByPersonId(unitAdministrator.getPersonId());
                AwardUnitContact newAwardUnitContact = new AwardUnitContact();
                newAwardUnitContact.setAward(this);
                newAwardUnitContact.setPerson(person);
                newAwardUnitContact.setUnitAdministratorType(unitAdministrator.getUnitAdministratorType());
                newAwardUnitContact.setFullName(person.getFullName());
                centralAdminContacts.add(newAwardUnitContact);
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

    /*
     * Used by Current Report to determine if award in Active, Pending, or Hold state.
     */
    private static String reportedStatus = "1 3 6";

    public boolean isActiveVersion() {
        return (reportedStatus.indexOf(getAwardStatus().getStatusCode()) != -1);
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
            List<AwardCloseout> staticCloseoutItems = new ArrayList<AwardCloseout>();
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
        String name = getLeadUnit() == null ? EMPTY_STRING : getLeadUnit().getUnitName();
        return name;
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
        String name = getPrimeSponsor() == null ? EMPTY_STRING : getPrimeSponsor().getSponsorName();
        return name;
    }

    @Override
    public String getSubAwardOrganizationName() {
        return EMPTY_STRING;
    }
    
    @Override
    public List<NegotiationPersonDTO> getProjectPeople() {
        List<NegotiationPersonDTO> kcPeople = new ArrayList<NegotiationPersonDTO>();
        for (AwardPerson person : getProjectPersons()) {
            kcPeople.add(new NegotiationPersonDTO(person.getPerson(), person.getContactRoleCode()));
        }
        return kcPeople;
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

    public String getIsOwnedByUnit() {
        return this.getLeadUnitName();
    }

    public Integer getParentInvestigatorFlag(String personId, Integer flag) {
        for (AwardPerson aPerson : this.getProjectPersons()) {
            if (aPerson.getPersonId() != null
                    && aPerson.getPersonId().equals(personId)
                    || aPerson.getRolodexId() != null
                    && aPerson.getRolodexId().equals(personId)) {
                flag = 2;
                if (aPerson.isPrincipalInvestigator()) {
                    flag = 1;
                    break;
                }
            }
        }
        return flag;
    }
    
    /**
     * This method gets the current rate.
     * If there are multiple current rates, return the one with the higher rate
     * @param award
     * @return currentFandaRate
     */
    public AwardFandaRate getCurrentFandaRate() {
        List<AwardFandaRate> rates = this.getAwardFandaRate();
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        
        AwardFandaRate currentFandaRate;
        // when both On and Off campus rates are in, send the higher one. Ideally only one should be there
        // the single rate validation parameter needs to be set on award
        ScaleTwoDecimal currentRateValue = new ScaleTwoDecimal(0.0);
        currentFandaRate = rates.get(0);
        for (AwardFandaRate rate : rates) {
            if (Integer.parseInt(rate.getFiscalYear()) == currentYear) {
                if (rate.getApplicableFandaRate().isGreaterThan(currentRateValue)) {
                    currentFandaRate = rate;
                    currentRateValue = rate.getApplicableFandaRate();
                }
            }      
        }
        return currentFandaRate;
    }
    
    public String getParentTypeName(){
        return "Award";
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
     * @param allowUpdateTimestampToBeReset
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
        Map<String, String> fieldValues = new HashMap<String,String>();
        fieldValues.put("awardNumber", getAwardNumber());
        BusinessObjectService businessObjectService =  KcServiceLocator.getService(BusinessObjectService.class);
        List<Award> awards = (List<Award>)businessObjectService.findMatchingOrderBy(Award.class, fieldValues, "sequenceNumber", true);   
        return awards;
    }

    public String getAwardDescriptionLine() {
        AwardAmountInfo aai = getLastAwardAmountInfo();
        String transactionTypeDescription;
        String versionNumber;
        if(aai == null || aai.getOriginatingAwardVersion() == null) {
            versionNumber = getSequenceNumber().toString();
        }else {
            versionNumber = aai.getOriginatingAwardVersion().toString();
        }
        if(!(getAwardTransactionType() == null)) {
            transactionTypeDescription = getAwardTransactionType().getDescription();
        }else {
            transactionTypeDescription = "None";
        }
        return "Award Version " + versionNumber + ", " + transactionTypeDescription + ", updated " + getUpdateTimeAndUser(); 
    }

    public String getUpdateTimeAndUser() {
        String createDateStr = null;
        String updateUser = null;
        if (getUpdateTimestamp() != null) {
            createDateStr = CoreApiServiceLocator.getDateTimeService().toString(getUpdateTimestamp(), "hh:mm a MM/dd/yyyy");
            updateUser = getUpdateUser().length() > 30 ? getUpdateUser().substring(0, 30) : getUpdateUser(); 
        }
        return createDateStr + ", by " + updateUser;
    }
 
    public List<TimeAndMoneyDocumentHistory>getTimeAndMoneyDocumentHistoryList() throws WorkflowException {  
        List<TimeAndMoneyDocument> tnmDocs = getTimeAndMoneyHistoryService().buildTimeAndMoneyListForAwardDisplay(this);
        List<TimeAndMoneyDocumentHistory> timeAndMoneyHistoryList = 
            getTimeAndMoneyHistoryService().getDocHistoryAndValidInfosAssociatedWithAwardVersion(tnmDocs,getAwardAmountInfos(), this);
        return timeAndMoneyHistoryList;
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
     * This method is used by the tag file to display the F&A rate totals.
     * Needed to convert to KualiDecimal to avoid rounding issues.
     */
    public ScaleTwoDecimal getFandATotals() {
        ScaleTwoDecimal total = new ScaleTwoDecimal(0);
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
    private List<AwardBudgetExt> budgets;
    private List<AwardBudgetExt> allAwardBudgets;

	@Override
	public Integer getNextBudgetVersionNumber() {
		return getAwardDocument().getNextBudgetVersionNumber();
	}

	public List<AwardBudgetExt> getAllAwardBudgets() {
		if (allAwardBudgets == null || allAwardBudgets.isEmpty()) {
			allAwardBudgets = KcServiceLocator.getService(AwardBudgetService.class).getAllBudgetsForAward(this);
		}
		return allAwardBudgets;
	}

	public void setAllAwardBudgets(List<AwardBudgetExt> budgets) {
		this.allAwardBudgets = budgets;
	}

	public List<AwardBudgetExt> getBudgets() {
		return budgets;
	}

	public void setBudgets(List<AwardBudgetExt> budgets) {
		this.budgets = budgets;
	}

    public AwardComment getAdditionalFormsDescriptionComment() {
        return getAwardCommentByType("CG2", false, true);
    }

    public AwardComment getStopWorkReasonComment() {
        return getAwardCommentByType("CG1", false, true);
    }

    public AwardComment getSuspendInvoicingComment() {
        return getAwardCommentByType("CG3", false, true);
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

}