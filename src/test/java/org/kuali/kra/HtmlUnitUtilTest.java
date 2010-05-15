/*
 * Copyright 2005-2010 The Kuali Foundation.
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
package org.kuali.kra;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.service.ProposalDevelopmentService;
import org.kuali.kra.service.KraAuthorizationService;
import org.kuali.rice.kim.bo.Person;
import org.kuali.rice.kim.service.PersonService;
import org.kuali.rice.kns.UserSession;
import org.kuali.rice.kns.util.MessageMap;
import org.kuali.rice.kns.util.GlobalVariables;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;

/**
 * WARNING: This test is affected by the side effects other tests cause and will fail if not executed first.
 */
public class HtmlUnitUtilTest extends KraTestBase {
    private static String kraHomePageUrl;
    private ProposalDevelopmentDocument document;
    private static final String PROPOSAL_DOCUMENT_DESC = "ProposalDevelopmentDocumentTest";
    private ProposalDevelopmentService proposalDevelopmentService;
    
    @Before
    @Override
    public void setUp() throws Exception {
        super.setUp();
        transactionalLifecycle.stop();
        transactionalLifecycle = null;
        proposalDevelopmentService = KraServiceLocator.getService(ProposalDevelopmentService.class);
        //transactionalLifecycle.start(); 
        kraHomePageUrl = "http://localhost:" + getPort() + "/kc-dev/";
    }
    
    @Test
    public void testDocumentSearch() throws Exception {
      //Verify that the document was created successfully
        document = createDocument();
        assertNotNull(document);
        //assertEquals(1L, document.getVersionNumber().longValue());
        
        String docId = getDocument().getDocumentNumber();
        
        //quickstart logs in, performs Doc Search and opens the PD
        String quickstartUser = "quickstart";
        WebClient webClient = new WebClient();
        HtmlPage kraHomePage = KraWebTestUtil.loadPage(webClient, kraHomePageUrl);
        
        Map<String, String> proposalSearchParameters = new HashMap<String, String>();
        proposalSearchParameters.put(KraWebTestUtil.KRA_DOCSEARCH_INPUT_DOCUMENT_ELEMENT_ID, docId);
        HtmlPage docSearchResultsPage = KraWebTestUtil.performDocSearch(kraHomePage, proposalSearchParameters, quickstartUser);
       
        final HtmlTable table = (HtmlTable) docSearchResultsPage.getHtmlElementById("row");
        assertNotNull(table);
        assertEquals(2, table.getRowCount());
        assertTrue(docSearchResultsPage.asText(), docSearchResultsPage.asText().contains("Document/Notification Id Type Title Route Status Document Status Initiator Date Created Route Log Copy Document"));                                                          
        assertTrue(docSearchResultsPage.asText(), docSearchResultsPage.asText().contains(docId+" Proposal Development Document Proposal Development Document - " + PROPOSAL_DOCUMENT_DESC + " SAVED "));
    }
    
    private ProposalDevelopmentDocument createDocument() {
        ProposalDevelopmentDocument document = null;
        
        try {
              GlobalVariables.setUserSession(new UserSession("quickstart"));
              GlobalVariables.setMessageMap(new MessageMap());
              document = (ProposalDevelopmentDocument) getDocumentService()
                      .getNewDocument("ProposalDevelopmentDocument");
              Date requestedStartDateInitial = new Date(System.currentTimeMillis());
              Date requestedEndDateInitial = new Date(System.currentTimeMillis());
              setBaseDocumentFields(document, PROPOSAL_DOCUMENT_DESC, "005770", "project title",
                      requestedStartDateInitial, requestedEndDateInitial, "1", "1", "000001");
              proposalDevelopmentService.initializeUnitOrganizationLocation(document);
              proposalDevelopmentService.initializeProposalSiteNumbers(document);
              
              getDocumentService().saveDocument(document);
              initializeAuthorization(document);
              GlobalVariables.setUserSession(null);
          } catch (Exception e) {
              throw new RuntimeException(e);
          }
          
          
          return document;
      }
    
    private void setBaseDocumentFields(ProposalDevelopmentDocument document, String description, String sponsorCode, String title, Date requestedStartDateInitial, Date requestedEndDateInitial, String activityTypeCode, String proposalTypeCode, String ownedByUnit) {
        document.getDocumentHeader().setDocumentDescription(description);
        document.getDevelopmentProposal().setSponsorCode(sponsorCode);
        document.getDevelopmentProposal().setTitle(title);
        document.getDevelopmentProposal().setRequestedStartDateInitial(requestedStartDateInitial);
        document.getDevelopmentProposal().setRequestedEndDateInitial(requestedEndDateInitial);
        document.getDevelopmentProposal().setActivityTypeCode(activityTypeCode);
        document.getDevelopmentProposal().setProposalTypeCode(proposalTypeCode);
        document.getDevelopmentProposal().setOwnedByUnitNumber(ownedByUnit);
    }

    private void initializeAuthorization(ProposalDevelopmentDocument doc) {
        String userId = GlobalVariables.getUserSession().getPrincipalId();
        KraAuthorizationService kraAuthorizationService = KraServiceLocator.getService(KraAuthorizationService.class);
        kraAuthorizationService.addRole(userId, RoleConstants.AGGREGATOR, doc);
        PersonService<Person> personService = getService(PersonService.class);
        Person jtester = personService.getPersonByPrincipalName("jtester");
        kraAuthorizationService.addRole(jtester.getPrincipalId(), RoleConstants.AGGREGATOR, doc);
    }

    public ProposalDevelopmentDocument getDocument() {
        return document;
    }

    public void setDocument(ProposalDevelopmentDocument document) {
        this.document = document;
    }
}
