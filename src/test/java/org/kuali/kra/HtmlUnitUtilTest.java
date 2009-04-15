/*
 * Copyright 2006-2009 The Kuali Foundation.
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
import org.kuali.core.UserSession;
import org.kuali.core.bo.user.UniversalUser;
import org.kuali.core.util.ErrorMap;
import org.kuali.core.util.GlobalVariables;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.service.ProposalAuthorizationService;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;

public class HtmlUnitUtilTest extends KraTestBase {
    private static String kraHomePageUrl;
    private ProposalDevelopmentDocument document;
    private static final String PROPOSAL_DOCUMENT_DESC = "ProposalDevelopmentDocumentTest";
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        transactionalLifecycle.stop();
        document = createDocument();
        transactionalLifecycle.start(); 
        kraHomePageUrl = "http://localhost:" + getPort() + "/kra-dev/";
    }
    
    @Test
    public void testDocumentSearch() throws Exception {
      //Verify that the document was created successfully
        assertNotNull(document);
        assertEquals(1L, document.getVersionNumber().longValue());
        
        String docId = getDocument().getDocumentNumber();
        
        //quickstart logs in, performs Doc Search and opens the PD
        String quickstartUser = "quickstart";
        WebClient webClient = new WebClient();
        HtmlPage kraHomePage = KraWebTestUtil.loadPage(webClient, kraHomePageUrl);
        
        Map<String, String> proposalSearchParameters = new HashMap<String, String>();
        proposalSearchParameters.put(KraWebTestUtil.KRA_DOCSEARCH_INPUT_DOCUMENT_ELEMENT_ID, docId);
        HtmlPage docSearchResultsPage = KraWebTestUtil.performDocSearch(kraHomePage, proposalSearchParameters, quickstartUser);
       
        final HtmlTable table = (HtmlTable) docSearchResultsPage.getHtmlElementById("result");
        assertNotNull(table);
        assertEquals(1, table.getRowCount());
        System.out.println(docSearchResultsPage.asText());
        assertTrue(docSearchResultsPage.asText().contains("Document Id Type Title Route Status Initiator Date Created Route Log"));
        assertTrue(docSearchResultsPage.asText().contains(docId+" KRA Proposal Development Proposal Development Document - " + PROPOSAL_DOCUMENT_DESC + " SAVED " + quickstartUser));
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
              setBaseDocumentFields(document, PROPOSAL_DOCUMENT_DESC, "005770", "project title",
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
        document.getDocumentHeader().setFinancialDocumentDescription(description);
        document.setSponsorCode(sponsorCode);
        document.setTitle(title);
        document.setRequestedStartDateInitial(requestedStartDateInitial);
        document.setRequestedEndDateInitial(requestedEndDateInitial);
        document.setActivityTypeCode(activityTypeCode);
        document.setProposalTypeCode(proposalTypeCode);
        document.setOwnedByUnitNumber(ownedByUnit);
    }

    private void initializeAuthorization(ProposalDevelopmentDocument doc) {
        UniversalUser user = GlobalVariables.getUserSession().getUniversalUser();
        String username = user.getPersonUserIdentifier();
        ProposalAuthorizationService proposalAuthService = KraServiceLocator.getService(ProposalAuthorizationService.class);
        proposalAuthService.addRole(username, RoleConstants.AGGREGATOR, doc);
        proposalAuthService.addRole("jtester", RoleConstants.AGGREGATOR, doc);
    }

    public ProposalDevelopmentDocument getDocument() {
        return document;
    }

    public void setDocument(ProposalDevelopmentDocument document) {
        this.document = document;
    }
}
