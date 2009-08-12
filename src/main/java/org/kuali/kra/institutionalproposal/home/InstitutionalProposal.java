/*
 * Copyright 2006-2009 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.institutionalproposal.home;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.SequenceOwner;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardType;
import org.kuali.kra.award.home.ValuableItem;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.bo.NoticeOfOpportunity;
import org.kuali.kra.bo.Person;
import org.kuali.kra.bo.Rolodex;
import org.kuali.kra.bo.ScienceKeyword;
import org.kuali.kra.bo.Sponsor;
import org.kuali.kra.document.KeywordsManager;
import org.kuali.kra.document.SpecialReviewHandler;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.institutionalproposal.customdata.InstitutionalProposalCustomData;
import org.kuali.kra.institutionalproposal.document.InstitutionalProposalDocument;
import org.kuali.kra.institutionalproposal.ipreview.IntellectualPropertyReview;
import org.kuali.kra.institutionalproposal.personnel.InstitutionalProposalPersonCreditSplit;
import org.kuali.kra.proposaldevelopment.bo.ActivityType;
import org.kuali.kra.proposaldevelopment.bo.ProposalType;
import org.kuali.kra.proposaldevelopment.bo.ProposalUnitCreditSplit;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.KualiDecimal;

public class InstitutionalProposal extends KraPersistableBusinessObjectBase implements SpecialReviewHandler<InstitutionalProposalSpecialReview>,
                                                                                            KeywordsManager<InstitutionalProposalScienceKeyword>,
                                                                                            SequenceOwner<InstitutionalProposal> { 
    
    
    private static final long serialVersionUID = 1L;

    private InstitutionalProposalDocument institutionalProposalDocument;
    private Long proposalId; 
    private String proposalNumber; 
    private String sponsorProposalNumber; 
    private Integer sequenceNumber; 
    private Integer proposalTypeCode; 
    private String currentAccountNumber; 
    private String title; 
    private String sponsorCode; 
    private Integer rolodexId; 
    private String noticeOfOpportunityCode; 
    private Integer gradStudHeadcount; 
    private KualiDecimal gradStudPersonMonths; 
    private boolean typeOfAccount; 
    private String activityTypeCode; 
    private Date requestedStartDateInitial; 
    private Date requestedStartDateTotal; 
    private Date requestedEndDateInitial; 
    private Date requestedEndDateTotal; 
    private KualiDecimal totalDirectCostInitial; 
    private KualiDecimal totalDirectCostTotal; 
    private KualiDecimal totalIndirectCostInitial; 
    private KualiDecimal totalIndirectCostTotal; 
    private String numberOfCopies; 
    private Date deadlineDate; 
    private Date createTimeStamp;
    private boolean deadlineType; 
    private boolean mailBy; 
    private boolean mailType; 
    private String mailAccountNumber; 
    private String mailDescription;
    private Boolean subcontractFlag; 
    private String costSharingIndicator; 
    private String idcRateIndicator; 
    private String specialReviewIndicator; 
    private Integer statusCode; 
    private String scienceCodeIndicator; 
    private String nsfCode; 
    private String primeSponsorCode; 
    private String initialContractAdmin; 
    private String ipReviewActivityIndicator; 
    private String currentAwardNumber; 
    private String cfdaNumber; 
    private String opportunity; 
    private Integer awardTypeCode; 
    private String newDescription;
    private NoticeOfOpportunity noticeOfOpportunity; 
    private ProposalType proposalType; 
    private Rolodex rolodex; 
    private Sponsor sponsor; 
    private Sponsor primeSponsor;
    private String sponsorName;
    private ActivityType activityType; 
    private AwardType awardType; 
    private InstitutionalProposalScienceKeyword proposalScienceKeyword; 
    private InstitutionalProposalCostShare proposalCostSharing; 
    //private AwardFundingProposals awardFundingProposals; 
    private InstitutionalProposalPersonCreditSplit proposalPerCreditSplit; 
    private ProposalUnitCreditSplit proposalUnitCreditSplit; 
    private InstitutionalProposalUnitAdministrator institutionalProposalUnitAdministrator; 
    private InstitutionalProposalComments proposalComments; 
    private IntellectualPropertyReview intellectualPropertyReview;
    
    private List<InstitutionalProposalCustomData> institutionalProposalCustomDataList;
    private List<InstitutionalProposalNotepad> institutionalProposalNotepads;
    private List<InstitutionalProposalSpecialReview> specialReviews;
    private List<InstitutionalProposalUnitAdministrator> institutionalProposalUnitAdministrators;
    private List<InstitutionalProposalScienceKeyword> institutionalProposalScienceKeywords;
    private List<InstitutionalProposalCostShare> institutionalProposalCostShares;
    private List<InstitutionalProposalUnrecoveredFandA> institutionalProposalUnrecoveredFandAs;

    public InstitutionalProposal() { 
        super();
        initializeInstitutionalProposalWithDefaultValues();
        initializeCollections();
        initializeTemporaryUnitAdministrators();// temporary 
    } 
    
    /**
     * 
     * This method sets the default values for initial persistence as part of skeleton.
     * As various panels are developed; corresponding field initializations should be removed from
     * this method.  
     */
    private void initializeInstitutionalProposalWithDefaultValues(){
        setSequenceNumber(1);
        //setSponsorCode("005852");
        setCostSharingIndicator("1");
        setIdcRateIndicator("1");
        setSpecialReviewIndicator("1");
        setScienceCodeIndicator("1");
        ipReviewActivityIndicator = "A";
        Calendar cl = Calendar.getInstance();
        setCreateTimeStamp(new Date(cl.getTime().getTime()));
        setInitialContractAdmin("Bruno");
        setProposalNumber("1");
        setTotalDirectCostInitial(new KualiDecimal(0));
        setTotalDirectCostTotal(new KualiDecimal(0));
        setTotalIndirectCostInitial(new KualiDecimal(0));
        setTotalIndirectCostTotal(new KualiDecimal(0));
        newDescription = getDefaultNewDescription();
    }
    
    /**
     * This method is temporary until we get the rest of the contacts page funtionality set up.
     */
    private void initializeTemporaryUnitAdministrators() {
        InstitutionalProposalUnitAdministrator ipua1 = new InstitutionalProposalUnitAdministrator();
        InstitutionalProposalUnitAdministrator ipua2 = new InstitutionalProposalUnitAdministrator();
        InstitutionalProposalUnitAdministrator ipua3 = new InstitutionalProposalUnitAdministrator();
        ipua1.setAdministrator("000000081");
        ipua2.setAdministrator("000000011");
        ipua3.setAdministrator("000000052");
        ipua1.setPerson(new Person());
        ipua1.getPerson().setFullName("CLINKSCALES, LOGAN V");
        ipua1.getPerson().setOfficePhone("123-4567");
        ipua1.getPerson().setEmailAddress("clinkscales@indiana.edu");
        ipua2.setPerson(new Person());
        ipua2.getPerson().setFullName("NUGENT,DOMINIC O");
        ipua2.getPerson().setOfficePhone("123-4567");
        ipua2.getPerson().setEmailAddress("nugent@indiana.edu");
        ipua3.setPerson(new Person());
        ipua3.getPerson().setFullName("Inez Chew");
        ipua3.getPerson().setOfficePhone("123-4567");
        ipua3.getPerson().setEmailAddress("chew@indiana.edu");
        ipua1.setInstitutionalProposal(this);
        ipua2.setInstitutionalProposal(this);
        ipua3.setInstitutionalProposal(this);
        ipua1.setUnitAdministratorTypeCode("1");
        ipua2.setUnitAdministratorTypeCode("1");
        ipua3.setUnitAdministratorTypeCode("1");
        institutionalProposalUnitAdministrators.add(ipua1);
        institutionalProposalUnitAdministrators.add(ipua2);
        institutionalProposalUnitAdministrators.add(ipua3);

    }
    
    /**
     * This method returns a business object service
     * @return
     */
    protected BusinessObjectService getKraBusinessObjectService() {
        return (BusinessObjectService) KraServiceLocator.getService("businessObjectService");
    }
    
    /**
     * Gets the institutionalProposalDocument attribute. 
     * @return Returns the institutionalProposalDocument.
     */
    public InstitutionalProposalDocument getInstitutionalProposalDocument() {
        if (institutionalProposalDocument == null) {
            this.refreshReferenceObject("institutionalProposalDocument");
        }
        return institutionalProposalDocument;
    }

    /**
     * Sets the institutionalProposalDocument attribute value.
     * @param institutionalProposalDocument The institutionalProposalDocument to set.
     */
    public void setInstitutionalProposalDocument(InstitutionalProposalDocument institutionalProposalDocument) {
        this.institutionalProposalDocument = institutionalProposalDocument;
    }
    
    /**
     * This method...
     * @param institutionaProposalNotepad
     */
    public void add(InstitutionalProposalNotepad institutionalProposalNotepad) {
        institutionalProposalNotepad.setEntryNumber(institutionalProposalNotepads.size() + 1);
        institutionalProposalNotepad.setProposalNumber(this.getProposalNumber());
        institutionalProposalNotepads.add(institutionalProposalNotepad);
        institutionalProposalNotepad.setInstitutionalProposal(this);
    }
    
    /**
     * This method...
     * @param institutionalProposalCostShare
     */
    public void add(InstitutionalProposalCostShare institutionalProposalCostShare) {
        institutionalProposalCostShare.setInstitutionalProposal(this);
        institutionalProposalCostShares.add(institutionalProposalCostShare);
    }
    
    /**
     * This method...
     * @param institutionalProposalUnrecoveredFandA
     */
    public void add(InstitutionalProposalUnrecoveredFandA institutionalProposalUnrecoveredFandA) {
        institutionalProposalUnrecoveredFandA.setInstitutionalProposal(this);
        institutionalProposalUnrecoveredFandAs.add(institutionalProposalUnrecoveredFandA);
    }
    
    /**
     * This method 
     * @return
     */
     public KualiDecimal getTotalInitialCost() {
        KualiDecimal returnValue = new KualiDecimal(0);
        returnValue = returnValue.add(totalDirectCostInitial);
        returnValue = returnValue.add(totalIndirectCostInitial);
        return returnValue;
    }
     
     /**
      * This method 
      * @return
      */
      public KualiDecimal getTotalCost() {
         KualiDecimal returnValue = new KualiDecimal(0);
         returnValue = returnValue.add(totalDirectCostTotal);
         returnValue = returnValue.add(totalIndirectCostTotal);
         return returnValue;
     }
      
      /**
       * This method calculates the total value of a list of ValuableItems
       * @param valuableItems
       * @return The total value
       */
      KualiDecimal getTotalAmount(List<? extends ValuableItem> valuableItems) {
          KualiDecimal returnVal = new KualiDecimal(0.00);
          for(ValuableItem item : valuableItems) {
              KualiDecimal amount = item.getAmount() != null ? item.getAmount() : new KualiDecimal(0.00);
              returnVal = returnVal.add(amount);
          }
          return returnVal;
      }
      
      /**
       * This method calls getTotalAmount to calculate the total of all Commitment Amounts.
       * @return
       */
       public KualiDecimal getTotalCostShareAmount() {
          return getTotalAmount(institutionalProposalCostShares);
      }
       
       /**
        * This method calls getTotalAmount to calculate the total of all Unrecovered FandAs.
        * @return
        */
        public KualiDecimal getTotalUnrecoveredFandAAmount() {
           return getTotalAmount(institutionalProposalUnrecoveredFandAs);
       }
      
     /**
      * Gets the specialReviews attribute. 
      * @return Returns the specialReviews.
      */
     public List<InstitutionalProposalSpecialReview> getSpecialReviews() {
         return specialReviews;
     }

     /**
      * Sets the specialReviews attribute value.
      * @param specialReviews The specialReviews to set.
      */
     public void setSpecialReviews(List<InstitutionalProposalSpecialReview> specialReviews) {
         this.specialReviews = specialReviews;
     }
    
    /**
     * Gets the institutionalProposalCustomDataList attribute. 
     * @return Returns the institutionalProposalCustomDataList.
     */
    public List<InstitutionalProposalCustomData> getInstitutionalProposalCustomDataList() {
        return institutionalProposalCustomDataList;
    }
    
    

    /**
     * Gets the institutionalProposalNotepads attribute. 
     * @return Returns the institutionalProposalNotepads.
     */
    public List<InstitutionalProposalNotepad> getInstitutionalProposalNotepads() {
        return institutionalProposalNotepads;
    }

    /**
     * Sets the institutionalProposalNotepads attribute value.
     * @param institutionalProposalNotepads The institutionalProposalNotepads to set.
     */
    public void setInstitutionalProposalNotepads(List<InstitutionalProposalNotepad> institutionalProposalNotepads) {
        this.institutionalProposalNotepads = institutionalProposalNotepads;
    }
    
    

    /**
     * Gets the institutionalProposalUnitAdministrators attribute. 
     * @return Returns the institutionalProposalUnitAdministrators.
     */
    public List<InstitutionalProposalUnitAdministrator> getInstitutionalProposalUnitAdministrators() {
        return institutionalProposalUnitAdministrators;
    }

    /**
     * Sets the institutionalProposalUnitAdministrators attribute value.
     * @param institutionalProposalUnitAdministrators The institutionalProposalUnitAdministrators to set.
     */
    public void setInstitutionalProposalUnitAdministrators(
            List<InstitutionalProposalUnitAdministrator> institutionalProposalUnitAdministrators) {
        this.institutionalProposalUnitAdministrators = institutionalProposalUnitAdministrators;
    }

    /**
     * Sets the institutionalProposalCustomDataList attribute value.
     * @param institutionalProposalCustomDataList The institutionalProposalCustomDataList to set.
     */
    public void setInstitutionalProposalCustomDataList(List<InstitutionalProposalCustomData> institutionalProposalCustomDataList) {
        this.institutionalProposalCustomDataList = institutionalProposalCustomDataList;
    }
    
    
    
    /**
     * Gets the institutionalProposalScienceKeywords attribute. 
     * @return Returns the institutionalProposalScienceKeywords.
     */
    public List<InstitutionalProposalScienceKeyword> getInstitutionalProposalScienceKeywords() {
        return institutionalProposalScienceKeywords;
    }

    /**
     * Sets the institutionalProposalScienceKeywords attribute value.
     * @param institutionalProposalScienceKeywords The institutionalProposalScienceKeywords to set.
     */
    public void setInstitutionalProposalScienceKeywords(List<InstitutionalProposalScienceKeyword> institutionalProposalScienceKeywords) {
        this.institutionalProposalScienceKeywords = institutionalProposalScienceKeywords;
    }

    protected void initializeCollections() {
        institutionalProposalCustomDataList = new ArrayList<InstitutionalProposalCustomData>();
        institutionalProposalNotepads = new ArrayList<InstitutionalProposalNotepad>();
        specialReviews = new ArrayList<InstitutionalProposalSpecialReview>();
        institutionalProposalUnitAdministrators = new ArrayList<InstitutionalProposalUnitAdministrator>();
        institutionalProposalScienceKeywords = new ArrayList<InstitutionalProposalScienceKeyword>();
        institutionalProposalCostShares = new ArrayList<InstitutionalProposalCostShare>();
        institutionalProposalUnrecoveredFandAs = new ArrayList<InstitutionalProposalUnrecoveredFandA>();
    }
    
    public Long getProposalId() {
        return proposalId;
    }

    public void setProposalId(Long proposalId) {
        this.proposalId = proposalId;
    }

    public String getProposalNumber() {
        return proposalNumber;
    }

    public void setProposalNumber(String proposalNumber) {
        this.proposalNumber = proposalNumber;
    }

    public String getSponsorProposalNumber() {
        return sponsorProposalNumber;
    }

    public void setSponsorProposalNumber(String sponsorProposalNumber) {
        this.sponsorProposalNumber = sponsorProposalNumber;
    }

    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public Integer getProposalTypeCode() {
        return proposalTypeCode;
    }

    public void setProposalTypeCode(Integer proposalTypeCode) {
        this.proposalTypeCode = proposalTypeCode;
    }

    public String getCurrentAccountNumber() {
        return currentAccountNumber;
    }

    public void setCurrentAccountNumber(String currentAccountNumber) {
        this.currentAccountNumber = currentAccountNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSponsorCode() {
        return sponsorCode;
    }

    public void setSponsorCode(String sponsorCode) {
        this.sponsorCode = sponsorCode;
    }

    public Integer getRolodexId() {
        return rolodexId;
    }

    public void setRolodexId(Integer rolodexId) {
        this.rolodexId = rolodexId;
    }

    public String getNoticeOfOpportunityCode() {
        return noticeOfOpportunityCode;
    }

    public void setNoticeOfOpportunityCode(String noticeOfOpportunityCode) {
        this.noticeOfOpportunityCode = noticeOfOpportunityCode;
    }

    public Integer getGradStudHeadcount() {
        return gradStudHeadcount;
    }

    public void setGradStudHeadcount(Integer gradStudHeadcount) {
        this.gradStudHeadcount = gradStudHeadcount;
    }

    public KualiDecimal getGradStudPersonMonths() {
        return gradStudPersonMonths;
    }

    public void setGradStudPersonMonths(KualiDecimal gradStudPersonMonths) {
        this.gradStudPersonMonths = gradStudPersonMonths;
    }

    public boolean getTypeOfAccount() {
        return typeOfAccount;
    }

    public void setTypeOfAccount(boolean typeOfAccount) {
        this.typeOfAccount = typeOfAccount;
    }

    public String getActivityTypeCode() {
        return activityTypeCode;
    }

    public void setActivityTypeCode(String activityTypeCode) {
        this.activityTypeCode = activityTypeCode;
    }

    public Date getRequestedStartDateInitial() {
        return requestedStartDateInitial;
    }

    public void setRequestedStartDateInitial(Date requestedStartDateInitial) {
        this.requestedStartDateInitial = requestedStartDateInitial;
    }

    public Date getRequestedStartDateTotal() {
        return requestedStartDateTotal;
    }

    public void setRequestedStartDateTotal(Date requestedStartDateTotal) {
        this.requestedStartDateTotal = requestedStartDateTotal;
    }

    public Date getRequestedEndDateInitial() {
        return requestedEndDateInitial;
    }

    public void setRequestedEndDateInitial(Date requestedEndDateInitial) {
        this.requestedEndDateInitial = requestedEndDateInitial;
    }

    public Date getRequestedEndDateTotal() {
        return requestedEndDateTotal;
    }

    public void setRequestedEndDateTotal(Date requestedEndDateTotal) {
        this.requestedEndDateTotal = requestedEndDateTotal;
    }

    public KualiDecimal getTotalDirectCostInitial() {
        return totalDirectCostInitial;
    }

    public void setTotalDirectCostInitial(KualiDecimal totalDirectCostInitial) {
        this.totalDirectCostInitial = totalDirectCostInitial;
    }

    public KualiDecimal getTotalDirectCostTotal() {
        return totalDirectCostTotal;
    }

    public void setTotalDirectCostTotal(KualiDecimal totalDirectCostTotal) {
        this.totalDirectCostTotal = totalDirectCostTotal;
    }

    public KualiDecimal getTotalIndirectCostInitial() {
        return totalIndirectCostInitial;
    }

    public void setTotalIndirectCostInitial(KualiDecimal totalIndirectCostInitial) {
        this.totalIndirectCostInitial = totalIndirectCostInitial;
    }

    public KualiDecimal getTotalIndirectCostTotal() {
        return totalIndirectCostTotal;
    }

    public void setTotalIndirectCostTotal(KualiDecimal totalIndirectCostTotal) {
        this.totalIndirectCostTotal = totalIndirectCostTotal;
    }

    public String getNumberOfCopies() {
        return numberOfCopies;
    }

    public void setNumberOfCopies(String numberOfCopies) {
        this.numberOfCopies = numberOfCopies;
    }

    public Date getDeadlineDate() {
        return deadlineDate;
    }

    public void setDeadlineDate(Date deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    public boolean getDeadlineType() {
        return deadlineType;
    }

    public void setDeadlineType(boolean deadlineType) {
        this.deadlineType = deadlineType;
    }

    public boolean getMailBy() {
        return mailBy;
    }

    public void setMailBy(boolean mailBy) {
        this.mailBy = mailBy;
    }

    public boolean getMailType() {
        return mailType;
    }

    public void setMailType(boolean mailType) {
        this.mailType = mailType;
    }

    public String getMailAccountNumber() {
        return mailAccountNumber;
    }

    public void setMailAccountNumber(String mailAccountNumber) {
        this.mailAccountNumber = mailAccountNumber;
    }
    
    

    /**
     * Gets the mailDescription attribute. 
     * @return Returns the mailDescription.
     */
    public String getMailDescription() {
        return mailDescription;
    }

    /**
     * Sets the mailDescription attribute value.
     * @param mailDescription The mailDescription to set.
     */
    public void setMailDescription(String mailDescription) {
        this.mailDescription = mailDescription;
    }

    public boolean getSubcontractFlag() {
        return subcontractFlag;
    }

    public void setSubcontractFlag(boolean subcontractFlag) {
        this.subcontractFlag = subcontractFlag;
    }

    public String getCostSharingIndicator() {
        return costSharingIndicator;
    }

    public void setCostSharingIndicator(String costSharingIndicator) {
        this.costSharingIndicator = costSharingIndicator;
    }

    public String getIdcRateIndicator() {
        return idcRateIndicator;
    }

    public void setIdcRateIndicator(String idcRateIndicator) {
        this.idcRateIndicator = idcRateIndicator;
    }

    public String getSpecialReviewIndicator() {
        return specialReviewIndicator;
    }

    public void setSpecialReviewIndicator(String specialReviewIndicator) {
        this.specialReviewIndicator = specialReviewIndicator;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getScienceCodeIndicator() {
        return scienceCodeIndicator;
    }

    public void setScienceCodeIndicator(String scienceCodeIndicator) {
        this.scienceCodeIndicator = scienceCodeIndicator;
    }

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

    public String getInitialContractAdmin() {
        return initialContractAdmin;
    }

    public void setInitialContractAdmin(String initialContractAdmin) {
        this.initialContractAdmin = initialContractAdmin;
    }

    public String getIpReviewActivityIndicator() {
        return ipReviewActivityIndicator;
    }

    public void setIpReviewActivityIndicator(String ipReviewActivityIndicator) {
        this.ipReviewActivityIndicator = ipReviewActivityIndicator;
    }

    public String getCurrentAwardNumber() {
        return currentAwardNumber;
    }

    public void setCurrentAwardNumber(String currentAwardNumber) {
        this.currentAwardNumber = currentAwardNumber;
    }

    public String getCfdaNumber() {
        return cfdaNumber;
    }

    public void setCfdaNumber(String cfdaNumber) {
        this.cfdaNumber = cfdaNumber;
    }

    public String getOpportunity() {
        return opportunity;
    }

    public void setOpportunity(String opportunity) {
        this.opportunity = opportunity;
    }

    public Integer getAwardTypeCode() {
        return awardTypeCode;
    }

    public void setAwardTypeCode(Integer awardTypeCode) {
        this.awardTypeCode = awardTypeCode;
    }

    public NoticeOfOpportunity getNoticeOfOpportunity() {
        return noticeOfOpportunity;
    }

    public void setNoticeOfOpportunity(NoticeOfOpportunity noticeOfOpportunity) {
        this.noticeOfOpportunity = noticeOfOpportunity;
    }

    public ProposalType getProposalType() {
        return proposalType;
    }

    public void setProposalType(ProposalType proposalType) {
        this.proposalType = proposalType;
    }

    public Rolodex getRolodex() {
        return rolodex;
    }

    public void setRolodex(Rolodex rolodex) {
        this.rolodex = rolodex;
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
        if(sponsor == null && !StringUtils.isEmpty(sponsorCode)) {
            this.refreshReferenceObject("sponsor");
        }
        return sponsor;
    }

    public void setSponsor(Sponsor sponsor) {
        this.sponsor = sponsor;
        this.sponsorCode = sponsor != null ? sponsor.getSponsorCode() : null;
    }
    
    // Note: following the pattern of Sponsor, this getter indirectly calls a service.
    // Is there a better way?
    public Sponsor getPrimeSponsor() {
      if(primeSponsor == null && !StringUtils.isEmpty(getPrimeSponsorCode())) {
            this.refreshReferenceObject("primeSponsor");
        }
        return primeSponsor;
    }

    public void setPrimeSponsor(Sponsor primeSponsor) {
        this.primeSponsor = primeSponsor;
    }
    
    public String getSponsorName() {
        Sponsor tempSponsor = getSponsor();
        sponsorName = tempSponsor != null ? tempSponsor.getSponsorName() : null;
        return sponsorName;
    }
    
    

    public ActivityType getActivityType() {
        return activityType;
    }

    public void setActivityType(ActivityType activityType) {
        this.activityType = activityType;
    }

    public AwardType getAwardType() {
        return awardType;
    }

    public void setAwardType(AwardType awardType) {
        this.awardType = awardType;
    }

    public InstitutionalProposalScienceKeyword getProposalScienceKeyword() {
        return proposalScienceKeyword;
    }

    public void setProposalScienceKeyword(InstitutionalProposalScienceKeyword proposalScienceKeyword) {
        this.proposalScienceKeyword = proposalScienceKeyword;
    }

    public InstitutionalProposalCostShare getProposalCostSharing() {
        return proposalCostSharing;
    }

    public void setProposalCostSharing(InstitutionalProposalCostShare proposalCostSharing) {
        this.proposalCostSharing = proposalCostSharing;
    }

    /*
    public AwardFundingProposals getAwardFundingProposals() {
        return awardFundingProposals;
    }

    public void setAwardFundingProposals(AwardFundingProposals awardFundingProposals) {
        this.awardFundingProposals = awardFundingProposals;
    }
    */

    public InstitutionalProposalPersonCreditSplit getProposalPerCreditSplit() {
        return proposalPerCreditSplit;
    }

    public void setProposalPerCreditSplit(InstitutionalProposalPersonCreditSplit proposalPerCreditSplit) {
        this.proposalPerCreditSplit = proposalPerCreditSplit;
    }

    public ProposalUnitCreditSplit getProposalUnitCreditSplit() {
        return proposalUnitCreditSplit;
    }

    public void setProposalUnitCreditSplit(ProposalUnitCreditSplit proposalUnitCreditSplit) {
        this.proposalUnitCreditSplit = proposalUnitCreditSplit;
    }

    public InstitutionalProposalUnitAdministrator getInstitutionalProposalUnitAdministrator() {
        return institutionalProposalUnitAdministrator;
    }

    public void setInstitutionalProposalUnitAdministrator(InstitutionalProposalUnitAdministrator institutionalProposalUnitAdministrator) {
        this.institutionalProposalUnitAdministrator = institutionalProposalUnitAdministrator;
    }

    public InstitutionalProposalComments getProposalComments() {
        return proposalComments;
    }

    public void setProposalComments(InstitutionalProposalComments proposalComments) {
        this.proposalComments = proposalComments;
    }
    
    public IntellectualPropertyReview getIntellectualPropertyReview() {
        return intellectualPropertyReview;
    }

    public void setIntellectualPropertyReview(IntellectualPropertyReview intellectualPropertyReview) {
        this.intellectualPropertyReview = intellectualPropertyReview;
    }
    
    
    
    /**
     * Gets the institutionalProposalCostShares attribute. 
     * @return Returns the institutionalProposalCostShares.
     */
    public List<InstitutionalProposalCostShare> getInstitutionalProposalCostShares() {
        return institutionalProposalCostShares;
    }

    /**
     * Sets the institutionalProposalCostShares attribute value.
     * @param institutionalProposalCostShares The institutionalProposalCostShares to set.
     */
    public void setInstitutionalProposalCostShares(List<InstitutionalProposalCostShare> institutionalProposalCostShares) {
        this.institutionalProposalCostShares = institutionalProposalCostShares;
    }

    /**
     * Gets the institutionalProposalUnrecoveredFandAs attribute. 
     * @return Returns the institutionalProposalUnrecoveredFandAs.
     */
    public List<InstitutionalProposalUnrecoveredFandA> getInstitutionalProposalUnrecoveredFandAs() {
        return institutionalProposalUnrecoveredFandAs;
    }

    /**
     * Sets the institutionalProposalUnrecoveredFandAs attribute value.
     * @param institutionalProposalUnrecoveredFandAs The institutionalProposalUnrecoveredFandAs to set.
     */
    public void setInstitutionalProposalUnrecoveredFandAs(
            List<InstitutionalProposalUnrecoveredFandA> institutionalProposalUnrecoveredFandAs) {
        this.institutionalProposalUnrecoveredFandAs = institutionalProposalUnrecoveredFandAs;
    }

    /**
     * Gets the createTimeStamp attribute. 
     * @return Returns the createTimeStamp.
     */
    public Date getCreateTimeStamp() {
        return createTimeStamp;
    }

    /**
     * Sets the createTimeStamp attribute value.
     * @param createTimeStamp The createTimeStamp to set.
     */
    public void setCreateTimeStamp(Date createTimeStamp) {
        this.createTimeStamp = createTimeStamp;
    }
    
    public String getDefaultNewDescription() {
        return "(select)";
    }
    
    public String getNewDescription() {
        return newDescription;
    }

    public void setNewDescription(String newDescription) {
        this.newDescription = newDescription;
    }
    
    //public Timestamp getUpdateTimestamp() {
      //  return super.getUpdateTimestamp();
    //}
    //public void setUpdateTimestamp(Timestamp updateTimestamp) {
        //super.setUpdateTimestamp(updateTimestamp);
    //}//
    //public String getUpdateUser() {
        //return super.getUpdateUser();
    //}
    //public void setUpdateUser(String updateUser) {
        //super.setUpdateUser(updateUser);
    //}

    /** {@inheritDoc} */
    @Override 
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> hashMap = new LinkedHashMap<String, Object>();
        hashMap.put("proposalId", this.getProposalId());
        hashMap.put("proposalNumber", this.getProposalNumber());
        hashMap.put("sponsorProposalNumber", this.getSponsorProposalNumber());
        hashMap.put("sequenceNumber", this.getSequenceNumber());
        hashMap.put("proposalTypeCode", this.getProposalTypeCode());
        hashMap.put("currentAccountNumber", this.getCurrentAccountNumber());
        hashMap.put("title", this.getTitle());
        hashMap.put("sponsorCode", this.getSponsorCode());
        hashMap.put("rolodexId", this.getRolodexId());
        hashMap.put("noticeOfOpportunityCode", this.getNoticeOfOpportunityCode());
        hashMap.put("gradStudHeadcount", this.getGradStudHeadcount());
        hashMap.put("gradStudPersonMonths", this.getGradStudPersonMonths());
        hashMap.put("typeOfAccount", this.getTypeOfAccount());
        hashMap.put("activityTypeCode", this.getActivityTypeCode());
        hashMap.put("requestedStartDateInitial", this.getRequestedStartDateInitial());
        hashMap.put("requestedStartDateTotal", this.getRequestedStartDateTotal());
        hashMap.put("requestedEndDateInitial", this.getRequestedEndDateInitial());
        hashMap.put("requestedEndDateTotal", this.getRequestedEndDateTotal());
        hashMap.put("totalDirectCostInitial", this.getTotalDirectCostInitial());
        hashMap.put("totalDirectCostTotal", this.getTotalDirectCostTotal());
        hashMap.put("totalIndirectCostInitial", this.getTotalIndirectCostInitial());
        hashMap.put("totalIndirectCostTotal", this.getTotalIndirectCostTotal());
        hashMap.put("numberOfCopies", this.getNumberOfCopies());
        hashMap.put("deadlineDate", this.getDeadlineDate());
        hashMap.put("deadlineType", this.getDeadlineType());
        hashMap.put("mailBy", this.getMailBy());
        hashMap.put("mailType", this.getMailType());
        hashMap.put("mailAccountNumber", this.getMailAccountNumber());
        hashMap.put("subcontractFlag", this.getSubcontractFlag());
        hashMap.put("costSharingIndicator", this.getCostSharingIndicator());
        hashMap.put("idcRateIndicator", this.getIdcRateIndicator());
        hashMap.put("specialReviewIndicator", this.getSpecialReviewIndicator());
        hashMap.put("statusCode", this.getStatusCode());
        hashMap.put("scienceCodeIndicator", this.getScienceCodeIndicator());
        hashMap.put("nsfCode", this.getNsfCode());
        hashMap.put("primeSponsorCode", this.getPrimeSponsorCode());
        hashMap.put("initialContractAdmin", this.getInitialContractAdmin());
        hashMap.put("ipReviewActivityIndicator", this.getIpReviewActivityIndicator());
        hashMap.put("currentAwardNumber", this.getCurrentAwardNumber());
        hashMap.put("cfdaNumber", this.getCfdaNumber());
        hashMap.put("opportunity", this.getOpportunity());
        hashMap.put("awardTypeCode", this.getAwardTypeCode());
        return hashMap;
    }

    public void addSpecialReview(InstitutionalProposalSpecialReview specialReview) {
        specialReview.setInstitutionalProposal(this);
        getSpecialReviews().add(specialReview);
        
    }

    public InstitutionalProposalSpecialReview getSpecialReview(int index) {
        return getSpecialReviews().get(index);
    }
    
    /**
     * Gets the keywords attribute. 
     * @return Returns the keywords.
     */
    public List<InstitutionalProposalScienceKeyword> getKeywords() {
        return institutionalProposalScienceKeywords;
    }

    /**
     * Sets the keywords attribute value.
     * @param keywords The keywords to set.
     */
    public void setKeywords(List<InstitutionalProposalScienceKeyword> institutionalProposalScienceKeywords) {
        this.institutionalProposalScienceKeywords = institutionalProposalScienceKeywords;
    }
    /**
     * Add selected science keyword to award science keywords list.
     * @see org.kuali.kra.document.KeywordsManager#addKeyword(org.kuali.kra.bo.ScienceKeyword)
     */
    public void addKeyword(ScienceKeyword scienceKeyword) {
        InstitutionalProposalScienceKeyword institutionalProposalScienceKeyword = new InstitutionalProposalScienceKeyword(this, scienceKeyword);
        getKeywords().add(institutionalProposalScienceKeyword);
    }

    /**
     * It returns the ScienceKeyword object from keywords list
     * @see org.kuali.kra.document.KeywordsManager#getKeyword(int)
     */
    public InstitutionalProposalScienceKeyword getKeyword(int index) {
        return getKeywords().get(index);
    }
    
    /**
     * @see org.kuali.kra.SequenceOwner#getOwnerSequenceNumber()
     */
    public Integer getOwnerSequenceNumber() {
        return null;
    }

    /**
     * @see org.kuali.kra.SequenceOwner#incrementSequenceNumber()
     */
    public void incrementSequenceNumber() {
       this.sequenceNumber++; 
    }

    /**
     * @see org.kuali.kra.SequenceAssociate#getSequenceOwner()
     */
    public InstitutionalProposal getSequenceOwner() {
        return this;
    }

    /**
     * @see org.kuali.kra.SequenceAssociate#setSequenceOwner(org.kuali.kra.SequenceOwner)
     */
    public void setSequenceOwner(InstitutionalProposal newOwner) {
       // no-op
    }

    /**
     * @see org.kuali.kra.Sequenceable#resetPersistenceState()
     */
    public void resetPersistenceState() {
        this.proposalId = null;
    }
    
    /**
     * @see org.kuali.kra.SequenceOwner#getName()
     */
    public String getVersionNameField() {
        return "proposalNumber";
    }
    
}