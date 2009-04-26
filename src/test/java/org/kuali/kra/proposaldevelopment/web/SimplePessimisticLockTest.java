/*
 * Copyright 2008 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.proposaldevelopment.web;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.HtmlUnitUtil;
import org.kuali.kra.KraTestBase;
import org.kuali.kra.KraWebTestUtil;
import org.kuali.kra.authorization.KraAuthorizationConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.rice.shim.UniversalUser;
import org.kuali.kra.service.KraAuthorizationService;
import org.kuali.rice.kns.UserSession;
import org.kuali.rice.kns.document.authorization.PessimisticLock;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.kns.util.ErrorMap;
import org.kuali.rice.kns.util.GlobalVariables;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlImageInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class SimplePessimisticLockTest extends KraTestBase {
    private BusinessObjectService boService = null;
    
    private ProposalDevelopmentDocument document;
    private static String kraHomePageUrl;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        transactionalLifecycle.stop();
        boService = KNSServiceLocator.getBusinessObjectService();
        document = createDocument();
        transactionalLifecycle.start(); 
        kraHomePageUrl = "http://localhost:" + getPort() + "/kra-dev/";
    }
    
    private PessimisticLock findDocumentProposalLock() {
        String lockDescriptor = document.getDocumentNumber() + "-" + KraAuthorizationConstants.LOCK_DESCRIPTOR_PROPOSAL;
        List<PessimisticLock> documentProposalLocks = findMatchingLocksWithGivenDescriptor(lockDescriptor);
        assertEquals(1, documentProposalLocks.size());
        PessimisticLock proposalLock = documentProposalLocks.get(0);
        assertNotNull(proposalLock);
        return proposalLock;
    }
    
    private List<PessimisticLock> findMatchingLocksWithGivenDescriptor(String lockDescriptor) {
        Map fieldValues = new HashMap();
        fieldValues.put("lockDescriptor", lockDescriptor);
        List<PessimisticLock> matchingLocks = (List<PessimisticLock>) boService.findMatching(PessimisticLock.class, fieldValues);
        return matchingLocks;
    }
    
    private ProposalDevelopmentDocument retrieveUpdatedProposal(ProposalDevelopmentDocument document) throws Exception {
        GlobalVariables.setUserSession(new UserSession("quickstart"));
        ProposalDevelopmentDocument tmpReturn = (ProposalDevelopmentDocument) getDocument(document.getDocumentNumber());
        GlobalVariables.setUserSession(null);
        return tmpReturn;
    }
    
    private HtmlPage loadKraHomePage(WebClient webClient) throws Exception {  
        return KraWebTestUtil.loadPage(webClient, kraHomePageUrl);
    }
    
    @Test
    public void testLockScenario1() {
        //Verify that the document was created successfully
        assertNotNull(document);
        assertEquals(1L, document.getVersionNumber().longValue());
        
        try {
            //quickstart logs in, performs Doc Search and opens the PD
            String quickstartUser = "quickstart";
            WebClient webClient = new WebClient();
            HtmlPage kraHomePage = loadKraHomePage(webClient);
            
            Map<String, String> proposalSearchParameters = new HashMap<String, String>();
            proposalSearchParameters.put(KraWebTestUtil.KRA_DOCSEARCH_INPUT_DOCUMENT_ELEMENT_ID, getDocument().getDocumentNumber());
            HtmlPage docSearchResultsPage = KraWebTestUtil.performDocSearch(kraHomePage, proposalSearchParameters, quickstartUser);
            HtmlPage proposalDocumentPage = null;
            PessimisticLock proposalLock = null;
            
            if(docSearchResultsPage != null) {
                proposalDocumentPage = KraWebTestUtil.loadProposalFromSearchResults(docSearchResultsPage, getDocument().getDocumentNumber());
                proposalLock = findDocumentProposalLock(); 
                //This user should be displayed with a save button
                HtmlImageInput saveButton = KraWebTestUtil.getSaveButton(proposalDocumentPage);

                //Assert on the Pessimistic Locking Messages
                //This user owns the Lock. Hence there should be  no lock message displayed
                assertFalse(proposalDocumentPage.asXml().contains("This Proposal is locked for editing by " + proposalLock.getOwnedByPrincipalIdentifier()));
                HtmlPage savedProposalPage = (HtmlPage) saveButton.click();
                assertTrue(savedProposalPage.asXml().contains("Document was successfully saved."));
            }
            
            //Retrieve the initial versionNumber of the PD just saved
            ProposalDevelopmentDocument retrievedDBDocument = retrieveUpdatedProposal(document);  
            assertEquals(2L, retrievedDBDocument.getVersionNumber().longValue());
            
            //jtester logs in next, performs Doc Search and opens the same PD
            String jtesterUser = "jtester";
            WebClient secondWebClient = new WebClient();
            HtmlPage secondKraHomePage = loadKraHomePage(secondWebClient);
            HtmlPage secondDocSearchResultsPage = KraWebTestUtil.performDocSearch(secondKraHomePage, proposalSearchParameters, jtesterUser);
            HtmlPage secondProposalDocumentPage = null;
            PessimisticLock budgetLock = null;
            HtmlPage budgetSummaryPage = null;
            
            if(secondDocSearchResultsPage != null) { 
                secondProposalDocumentPage = KraWebTestUtil.loadProposalFromSearchResults(secondDocSearchResultsPage, getDocument().getDocumentNumber());
                //This user does NOT own the Lock. Hence there should be a Proposal lock message displayed
                assertTrue(secondProposalDocumentPage.asXml().contains("This Proposal is locked for editing by " + proposalLock.getOwnedByPrincipalIdentifier()));
                
                //Save button should NOT be displayed for this user
                HtmlImageInput saveButton = null;
                try {
                    saveButton = KraWebTestUtil.getSaveButton(secondProposalDocumentPage);
                    //Previous statement should have thrown ElementNotFoundException
                    fail("Test should have thrown ElementNotFoundException");
                } catch (ElementNotFoundException e) {}
                assertNull(saveButton); 
                
                HtmlPage budgetVersionsPage = KraWebTestUtil.navigateToTab(secondProposalDocumentPage, KraWebTestUtil.KRA_TAB_RELOAD_BUDGETVERSIONS_BUTTON_ID);
                saveButton = KraWebTestUtil.getSaveButton(budgetVersionsPage);
                assertNotNull(saveButton);
                
                getDocument().refreshPessimisticLocks();
                List<PessimisticLock> currentDocumentLocks = getDocument().getPessimisticLocks();
                assertEquals(2, currentDocumentLocks.size());
                
                for(PessimisticLock lock: currentDocumentLocks) {
                    if(lock.getLockDescriptor().contains(KraAuthorizationConstants.LOCK_DESCRIPTOR_BUDGET)) {
                        budgetLock = lock;
                        //This user should own the Budget Lock
                        assertEquals(lock.getOwnedByPrincipalIdentifier(), jtesterUser);
                        break;
                    }
                }
                
                retrievedDBDocument = retrieveUpdatedProposal(document);  
                assertEquals(2L, retrievedDBDocument.getVersionNumber().longValue());
                
                budgetVersionsPage = KraWebTestUtil.addBudgetVersion(budgetVersionsPage, "v1");
                budgetSummaryPage = KraWebTestUtil.openBudgetVersion(budgetVersionsPage, 0);
                HtmlPage savedBudgetVersionsPage = KraWebTestUtil.savePage(budgetSummaryPage);
                assertTrue(savedBudgetVersionsPage.asXml().contains("Document was successfully saved."));
                
                getDocument().refreshPessimisticLocks();
                currentDocumentLocks = getDocument().getPessimisticLocks();
                assertEquals(2, currentDocumentLocks.size());
                
                List<PessimisticLock> budgetDocumentLocks = findMatchingLocksWithGivenDescriptor(budgetLock.getLockDescriptor());
                assertEquals(2, budgetDocumentLocks.size());
            }
            
            retrievedDBDocument = retrieveUpdatedProposal(document);
            assertEquals(3L, retrievedDBDocument.getVersionNumber().longValue());
            
            //quickstart user reloads Proposal Page to see the Budget Lock message
            proposalDocumentPage = KraWebTestUtil.reloadPage(proposalDocumentPage);
            assertTrue(proposalDocumentPage.asXml().contains("This Budget is locked for editing by " + budgetLock.getOwnedByPrincipalIdentifier()));
            proposalDocumentPage = KraWebTestUtil.savePage(proposalDocumentPage);
            assertTrue(proposalDocumentPage.asXml().contains("Document was successfully saved."));
            
            retrievedDBDocument = retrieveUpdatedProposal(document);
            assertEquals(4L, retrievedDBDocument.getVersionNumber().longValue());
            
            //quickstart user adds a Special Review line and saves
            HtmlPage firstSpecialReviewPage = KraWebTestUtil.navigateToTab(proposalDocumentPage, KraWebTestUtil.KRA_TAB_SAVE_SPECIALREVIEW_BUTTON_ID);
            assertTrue(firstSpecialReviewPage.asText().contains("Document was successfully saved"));
            retrievedDBDocument = retrieveUpdatedProposal(document);
            assertEquals(5L, retrievedDBDocument.getVersionNumber().longValue());
            
            assertTrue(firstSpecialReviewPage.asText().contains("Approval Status Protocol ID Application Date Approval Date Expiration Date Exempt # Comments"));
            webClient.setJavaScriptEnabled(false);
            firstSpecialReviewPage = addSpecialReviewLine(firstSpecialReviewPage, "08/01/2007;;123;1;2;Something to comment on");
            firstSpecialReviewPage = KraWebTestUtil.savePage(firstSpecialReviewPage);
            
            retrievedDBDocument = retrieveUpdatedProposal(document);
            assertEquals(6L, retrievedDBDocument.getVersionNumber().longValue());
            
            //quickstart user closes the document - Proposal Lock should be released
            HtmlPage closeQuestionDialog = KraWebTestUtil.closePage(firstSpecialReviewPage);
            //Would you like to save this document before you close it?
            //quickstart answers Yes to the question
            HtmlImageInput yesButton = HtmlUnitUtil.getImageInput(closeQuestionDialog, KraWebTestUtil.KRA_QUESTION_ANSWER_YES_BUTTON);
            HtmlPage kraHomePageAgain = (HtmlPage) yesButton.click();
             
            getDocument().refreshPessimisticLocks();
            List<PessimisticLock> currentDocumentLocks = getDocument().getPessimisticLocks();
            //Assert that Proposal Lock is no longer is present
            assertEquals(1, currentDocumentLocks.size());
            
            //jtester saves again
            budgetSummaryPage = KraWebTestUtil.savePage(budgetSummaryPage);
            assertTrue(proposalDocumentPage.asXml().contains("Document was successfully saved."));
            retrievedDBDocument = retrieveUpdatedProposal(document);
            assertEquals(7L, retrievedDBDocument.getVersionNumber().longValue());
        }
        catch (Exception e) {
            e.printStackTrace();
            fail("SimplePessimisticLockTest1 did not complete successfully!!!");
        }
    }
    
    private HtmlPage addSpecialReviewLine(HtmlPage htmlPage, String paramList) throws Exception {
        String[] params = paramList.split(";");
        
        HtmlUnitUtil.setTextFieldValue(htmlPage, "newPropSpecialReview.applicationDate", params[0]);
        HtmlUnitUtil.setTextFieldValue(htmlPage, "newPropSpecialReview.approvalDate", params[1]);
        HtmlUnitUtil.setTextFieldValue(htmlPage, "newPropSpecialReview.protocolNumber", params[2]);
        HtmlUnitUtil.setSelectBoxValue(htmlPage, "newPropSpecialReview.specialReviewCode", params[3]);
        HtmlUnitUtil.setSelectBoxValue(htmlPage, "newPropSpecialReview.approvalTypeCode", params[4]);
        HtmlUnitUtil.setTextAreaValue(htmlPage, "newPropSpecialReview.comments", params[5]);
        return (HtmlPage) KraWebTestUtil.getButton(htmlPage, "methodToCall.addSpecialReview.anchorSpecialReview").click();
    }
    
    private ProposalDevelopmentDocument createDocument() {
      ProposalDevelopmentDocument document = null;
      
      try {
            GlobalVariables.setUserSession(new UserSession("quickstart"));
            GlobalVariables.setErrorMap(new ErrorMap());
            document = (ProposalDevelopmentDocument) getDocumentService()
                    .getNewDocument("ProposalDevelopmentDocument");
            Date requestedStartDateInitial = new Date(System.currentTimeMillis());
            Date requestedEndDateInitial = new Date(System.currentTimeMillis());
            setBaseDocumentFields(document, "ProposalDevelopmentDocumentTest test doc", "005770", "project title",
                    requestedStartDateInitial, requestedEndDateInitial, "1", "1", "000001");
            getDocumentService().saveDocument(document);
            initializeAuthorization(document);
            GlobalVariables.setUserSession(null);
        } catch (Exception e) {
            fail("Exception occurred while creating test PD");
        }
        
        
        return document;
    }
    
    private void setBaseDocumentFields(ProposalDevelopmentDocument document, String description, String sponsorCode, String title, Date requestedStartDateInitial, Date requestedEndDateInitial, String activityTypeCode, String proposalTypeCode, String ownedByUnit) {
        document.getDocumentHeader().setDocumentDescription(description);
        document.setSponsorCode(sponsorCode);
        document.setTitle(title);
        document.setRequestedStartDateInitial(requestedStartDateInitial);
        document.setRequestedEndDateInitial(requestedEndDateInitial);
        document.setActivityTypeCode(activityTypeCode);
        document.setProposalTypeCode(proposalTypeCode);
        document.setOwnedByUnitNumber(ownedByUnit);
    }

    private void initializeAuthorization(ProposalDevelopmentDocument doc) {
        UniversalUser user = (UniversalUser) GlobalVariables.getUserSession().getPerson();
        String username = user.getPersonUserIdentifier();
        KraAuthorizationService kraAuthorizationService = KraServiceLocator.getService(KraAuthorizationService.class);
        kraAuthorizationService.addRole(username, RoleConstants.AGGREGATOR, doc);
        kraAuthorizationService.addRole("jtester", RoleConstants.AGGREGATOR, doc);
    }

    public ProposalDevelopmentDocument getDocument() {
        return document;
    }

    public void setDocument(ProposalDevelopmentDocument document) {
        this.document = document;
    }
}
