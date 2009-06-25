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
package org.kuali.kra.award;

import java.sql.Date;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.kuali.kra.KraTestBase;
import org.kuali.kra.SequenceAssociate;
import org.kuali.kra.award.commitments.AwardCostShare;
import org.kuali.kra.award.commitments.AwardFandaRate;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardApprovedSubaward;
import org.kuali.kra.award.home.AwardComment;
import org.kuali.kra.award.home.AwardSponsorTerm;
import org.kuali.kra.award.paymentreports.awardreports.AwardReportTerm;
import org.kuali.kra.award.paymentreports.specialapproval.approvedequipment.AwardApprovedEquipment;
import org.kuali.kra.award.specialreview.AwardSpecialReview;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.service.VersionException;
import org.kuali.kra.service.VersioningService;
import org.kuali.rice.kew.exception.WorkflowException;
import org.kuali.rice.kns.UserSession;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.kns.util.ErrorMap;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.KualiDecimal;

import com.ibm.icu.util.Calendar;

/**
 * This class 
 */
public class AwardVersioningTest extends KraTestBase {
    private static final double COST_SHARE_COMMIT_AMT = 1000.00;
    private static final String COST_SHARE_DEST1 = "576434";
    private static final String COST_SHARE_DEST2 = "777777";
    private static final String COST_SHARE_SOURCE = "7568657";
    private static final String FISCAL_YEAR = "2009";
    private static final String VENDOR_A = "VendorA";
    private static final String MODEL_A = "ModelA";
    private static final String ITEM_A = "ItemA";
    private static final String ITEM_B = "ItemB";
    private static final double AMOUNT = 1000.00;
    private static final String AWARD_TITLE = "Award Title";
    private static final String GOOGLE_SPONSOR_CODE = "005979";
    private static final String SPONSOR_AWARD_NUMBER = "1R01CA123456";
    private static final String DOCUMENT_DESCRIPTION = "Award Versioning Test Document";    
    
    private BusinessObjectService bos;
    private DocumentService documentService;
    private VersioningService versioningService;
    private List<AwardDocument> savedDocuments;
    private List<Award> awards;
    

    /**
     * @see org.kuali.kra.KraTestBase#setUp()
     */
    @Override
    public void setUp() throws Exception {
       super.setUp();
       GlobalVariables.setUserSession(new UserSession("quickstart"));
       savedDocuments = new ArrayList<AwardDocument>();
       awards = new ArrayList<Award>();
       locateServices();
       initializeAward();
       AwardDocument originalDocument = initializeNewDocument(awards.get(0));       
       saveDocument(originalDocument);       
    }

    /**
     * @see org.kuali.kra.KraTestBase#tearDown()
     */
    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void testVersioningAward_Level1() throws VersionException, WorkflowException {
        Award awardVersion2 = versionAward(savedDocuments.get(0));
        
        addSomeApprovedEquipmentAndVerifyBaseline(awardVersion2);
        addSomeAwardCommentsAndVerifyBaseline(awardVersion2);
        addSomeAwardCostSharesAndVerifyBaseline(awardVersion2);
        addSomeAwardFandaRatesAndVerifyBaseline(awardVersion2);
        addSomeAwardReportTermsAndVerifyBaseline(awardVersion2);
        addSomeAwardSponsorTermsAndVerifyBaseline(awardVersion2);
        addSomeAwardSubawardsAndVerifyBaseline(awardVersion2);
        addSomeAwardSpecialReviewsAndVerifyBaseline(awardVersion2);
        
        Award awardVersion3 = (Award) versioningService.createNewVersion(awardVersion2);
        assertEquals(2, awardVersion2.getApprovedEquipmentItemCount());
        assertEquals(2, awardVersion3.getApprovedEquipmentItemCount());
        
        AwardDocument document = (AwardDocument) documentService.saveDocument(initializeNewDocument(awardVersion3));
        awardVersion3 = document.getAward();
        
        verifySequenceAssociatesAfterVersioning(awardVersion2.getApprovedEquipmentItems(), awardVersion3.getApprovedEquipmentItems());
        verifySequenceAssociatesAfterVersioning(awardVersion2.getAwardComments(), awardVersion3.getAwardComments());
        verifySequenceAssociatesAfterVersioning(awardVersion2.getAwardCostShares(), awardVersion3.getAwardCostShares());
        verifySequenceAssociatesAfterVersioning(awardVersion2.getAwardFandaRate(), awardVersion3.getAwardFandaRate());
        verifySequenceAssociatesAfterVersioning(awardVersion2.getAwardReportTermItems(), awardVersion3.getAwardReportTermItems());
        verifySequenceAssociatesAfterVersioning(awardVersion2.getAwardSponsorTerms(), awardVersion3.getAwardSponsorTerms());
        verifySequenceAssociatesAfterVersioning(awardVersion2.getAwardApprovedSubawards(), awardVersion3.getAwardApprovedSubawards());
        verifySequenceAssociatesAfterVersioning(awardVersion2.getSpecialReviews(), awardVersion3.getSpecialReviews());
    }

    /**
     * This method...
     * @param awardVersion2
     */
    private void addSomeApprovedEquipmentAndVerifyBaseline(Award awardVersion2) {
        awardVersion2.add(new AwardApprovedEquipment(VENDOR_A, MODEL_A, ITEM_A, AMOUNT));
        awardVersion2.add(new AwardApprovedEquipment(VENDOR_A, MODEL_A, ITEM_B, AMOUNT));
        saveAndVerifySequenceAssociateValues(awardVersion2, awardVersion2.getApprovedEquipmentItems());        
    }

    private void addSomeAwardCommentsAndVerifyBaseline(Award awardVersion2) {
        awardVersion2.add(createComment("Comment 1"));
        awardVersion2.add(createComment("Comment 2"));
        awardVersion2.add(createComment("Comment 3"));
        saveAndVerifySequenceAssociateValues(awardVersion2, awardVersion2.getAwardComments());
    }
    
    private void addSomeAwardCostSharesAndVerifyBaseline(Award awardVersion2) {
        awardVersion2.add(createCostShare(0.75, COST_SHARE_DEST1));
        awardVersion2.add(createCostShare(0.25, COST_SHARE_DEST2));
        saveAndVerifySequenceAssociateValues(awardVersion2, awardVersion2.getAwardCostShares());
    }
    
    private void addSomeAwardFandaRatesAndVerifyBaseline(Award awardVersion2) {
        awardVersion2.add(createAwardFandaRate("N"));
        awardVersion2.add(createAwardFandaRate("F"));
        saveAndVerifySequenceAssociateValues(awardVersion2, awardVersion2.getAwardFandaRate());
    } 

    private void addSomeAwardReportTermsAndVerifyBaseline(Award awardVersion2) {
        /*  Sample Code From Varun:
            report class - 2
            report code - 39
            frequency code - 14
            frequency base code - 2 
            
            report class - 1
            report code - 5
            frequency code - 14
            frequency base code - 2
         */
        awardVersion2.add(createAwardReportTerm("2", "39", "14", "2"));
        awardVersion2.add(createAwardReportTerm("1", "5", "14", "2"));
    }
    
    private void addSomeAwardSponsorTermsAndVerifyBaseline(Award awardVersion2) {
        awardVersion2.add(createAwardSponsorTerm());
    }
    
    private void addSomeAwardSubawardsAndVerifyBaseline(Award awardVersion2) {
        awardVersion2.add(createApprovedSubaward("Org A"));
        awardVersion2.add(createApprovedSubaward("Org B"));
    }
    
    private void addSomeAwardSpecialReviewsAndVerifyBaseline(Award awardVersion2) {
        awardVersion2.add(createSpecialReview(1, "1", "1"));
        awardVersion2.add(createSpecialReview(2, "2", "2"));
    }
    
    private AwardApprovedSubaward createApprovedSubaward(String organizationName) {
        AwardApprovedSubaward subaward = new AwardApprovedSubaward();
        subaward.setAmount(new KualiDecimal(100.00));
        subaward.setOrganizationName(organizationName);
        return subaward;
    }
    
    private AwardReportTerm createAwardReportTerm(String reportClassCode, String reportCode, String frequencyCode, String frequencyBaseCode) {
        AwardReportTerm term = new AwardReportTerm();
        term.setReportClassCode(reportClassCode);
        term.setReportCode(reportCode);
        term.setFrequencyBaseCode(frequencyBaseCode);
        term.setFrequencyCode(frequencyCode);
        term.setDueDate(new Date(10000332));
        term.setOspDistributionCode("1");
        
        return term;
    }
    
    private AwardSponsorTerm createAwardSponsorTerm() {
        AwardSponsorTerm term = new AwardSponsorTerm();
        term.setSponsorTermId(null);
        return term;
    }
    
    /**
     * @return
     */
    private AwardComment createComment(String comment) {
        AwardComment comment1 = new AwardComment();
        comment1.setComments(comment);
        comment1.setChecklistPrintFlag(Boolean.TRUE);
        comment1.setCommentTypeCode("1");
        return comment1;
    }
    
    private AwardCostShare createCostShare(double costSharePct, String destination) {
        AwardCostShare costShare = new AwardCostShare();
        costShare.setCostSharePercentage(new KualiDecimal(costSharePct));
        costShare.setCostShareMet(costShare.getCostSharePercentage());
        costShare.setCostShareTypeCode(1);
        costShare.setFiscalYear(FISCAL_YEAR);
        costShare.setSource(COST_SHARE_SOURCE);
        costShare.setDestination(destination);
        costShare.setCommitmentAmount(new KualiDecimal(COST_SHARE_COMMIT_AMT));
        return costShare;
    }

    private AwardFandaRate createAwardFandaRate(String onOffCampusFlag) {
        AwardFandaRate rate = new AwardFandaRate();
        rate.setApplicableFandaRate(new KualiDecimal(5.0));
        rate.setFandaRateTypeCode(1);
        rate.setFiscalYear("2008");
        rate.setStartDate(new Date(new GregorianCalendar(2007, Calendar.JULY, 1).getTimeInMillis()));
        rate.setStartDate(new Date(new GregorianCalendar(2008, Calendar.JUNE, 30).getTimeInMillis()));
        rate.setOnCampusFlag(onOffCampusFlag);
        return rate;
    }
    
    private AwardSpecialReview createSpecialReview(Integer specialReviewNo, String specialReviewCode, String approvalTypeCode) {
        AwardSpecialReview review = new AwardSpecialReview();
        review.setSpecialReviewNumber(specialReviewNo);
        review.setSpecialReviewCode(specialReviewCode);
        review.setApprovalTypeCode(approvalTypeCode);
        return review;
    }
    
    private void initializeAward() {
        Award awardVersion1 = new Award();
        awardVersion1.setAwardTypeCode(1);
        awardVersion1.setTitle(AWARD_TITLE);
        awardVersion1.setActivityTypeCode(1);
        awardVersion1.setPrimeSponsorCode(GOOGLE_SPONSOR_CODE);
        awardVersion1.setSponsorCode(GOOGLE_SPONSOR_CODE);
        awardVersion1.setStatusCode(1);
        awardVersion1.setModificationNumber("1");
        awardVersion1.setSponsorAwardNumber(SPONSOR_AWARD_NUMBER);
           
        Date date = new Date(System.currentTimeMillis());
           
        awardVersion1.setAwardEffectiveDate(date);
        awardVersion1.setAwardExecutionDate(date);
        awardVersion1.setBeginDate(date);
        awardVersion1.setProjectEndDate(date);
        awards.add(awardVersion1);
    }
    
    /**
     * @throws WorkflowException
     */
    private AwardDocument initializeNewDocument(Award award) throws WorkflowException {
        AwardDocument document = (AwardDocument) documentService.getNewDocument(AwardDocument.class);
        document.getDocumentHeader().setDocumentDescription(DOCUMENT_DESCRIPTION);
        document.setAward(award);
        return document;
    }

    private void locateServices() {
        documentService = KraServiceLocator.getService(DocumentService.class);
        bos = KraServiceLocator.getService(BusinessObjectService.class);
        versioningService = KraServiceLocator.getService(VersioningService.class);
    }

    /**
     * @param award
     * @param items
     */
    private void saveAndVerifySequenceAssociateValues(Award award, List<? extends SequenceAssociate> items) {
        bos.save(award);
        Map<String,Object> keys = new HashMap<String, Object>();
        keys.put("awardId", award.getAwardId());
        award = (Award) bos.findByPrimaryKey(Award.class, keys);
        for(SequenceAssociate sequenceAssociate: items) {
            assertEquals(award.getSequenceNumber(), sequenceAssociate.getSequenceNumber());
            assertEquals(award.getAwardId(), ((Award) sequenceAssociate.getSequenceOwner()).getAwardId());
        }
    }

    /**
     * This method saves a document
     * @param document
     * @return
     * @throws WorkflowException 
     * @throws Exception
     */
    private AwardDocument saveDocument(AwardDocument document) throws WorkflowException {
        try {
            document = (AwardDocument) documentService.saveDocument(document);
            savedDocuments.add(document);
            return document;
        } catch(WorkflowException e) {
            ErrorMap errorMap = GlobalVariables.getErrorMap();
            if(errorMap.getErrorCount() > 0) {
                   for(String errorProperty : errorMap.getPropertiesWithErrors()) {
                       System.err.println("-------\nProperty in error " + errorProperty);
                       for(Object error: errorMap.getErrorMessagesForProperty(errorProperty)) {
                           System.err.println(error);
                       }
                   }    
            }
            throw e;
       }
    }
    
    /**
     * This method compares the sequence numbers of SequenceAssociates in one Award version with those in the subsequent Award version 
     * @param awardVersion2
     * @param awardVersion3
     */
    private void verifySequenceAssociatesAfterVersioning(List<? extends SequenceAssociate> sequenceAssociatesBeforeVersioning, List<? extends SequenceAssociate> sequenceAssociatesAfterVersioning) {
        assertEquals(sequenceAssociatesBeforeVersioning.size(), sequenceAssociatesAfterVersioning.size());
        for(int index = 0; index < sequenceAssociatesBeforeVersioning.size(); index++) {
            SequenceAssociate associateBeforeVersioning = sequenceAssociatesBeforeVersioning.get(index);
            SequenceAssociate associateAfterVersioning = sequenceAssociatesAfterVersioning.get(index);
            assertEquals(associateBeforeVersioning.getSequenceNumber().intValue() + 1, associateAfterVersioning.getSequenceNumber().intValue());
        }
    }
    
    /**
     * This method...
     * @return
     * @throws VersionException
     * @throws WorkflowException
     */
    private Award versionAward(AwardDocument oldVersionDocument) throws VersionException, WorkflowException {
        Award oldVersion = oldVersionDocument.getAward();
        Award newVersion = (Award) versioningService.createNewVersion(oldVersion);
        assertEquals(oldVersion.getSequenceNumber() + 1, newVersion.getSequenceNumber().intValue());
        assertNull(newVersion.getAwardId());
        AwardDocument newDocument = initializeNewDocument(newVersion);
        newDocument = saveDocument(newDocument);
        
        AwardDocument fetchedOldVersionDocument = (AwardDocument) documentService.getByDocumentHeaderId(oldVersionDocument.getDocumentNumber());
        Award fetchedOldVersionAward = fetchedOldVersionDocument.getAward();
        assertEquals(oldVersion.getAwardId(), fetchedOldVersionAward.getAwardId());
        assertEquals(oldVersion.getSequenceNumber(), fetchedOldVersionAward.getSequenceNumber());
        
        AwardDocument fetchedNewVersionDocument = (AwardDocument) documentService.getByDocumentHeaderId(newDocument.getDocumentNumber());
        Award fetchedNewVersion = fetchedNewVersionDocument.getAward();
        assertNotSame(oldVersion.getAwardId(), fetchedNewVersion.getAwardId());
        assertEquals(oldVersion.getSequenceNumber() + 1, fetchedNewVersion.getSequenceNumber().intValue());
        return fetchedNewVersion;
    }    
}
