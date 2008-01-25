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

import java.io.IOException;

import org.junit.Test;

import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import edu.iu.uis.eden.exception.WorkflowException;

public class UserRoleBoundDocSearchWebTest extends ProposalDevelopmentWebTestBase {

    @Test
    public void testDocSearch() throws Exception {
        // save a new document and get the doc #
        String documentNumber = saveDocument();

        //1. Got the Portal Page.
        final HtmlPage portalPage = getPortalPage();

        //2. Click on doc search and get doc search page.
        final HtmlPage docSearchPage = clickOn(portalPage, "Document Search");
        //HtmlPage searchPage = lookup(docSearchPage, "edu.iu.uis.eden.doctype.DocumentType");

        //3. lookup document type.
        setFieldValue(docSearchPage, "lookupableImplServiceName", "DocumentTypeLookupableImplService");
        final HtmlPage documentTypeLookupPage = clickOn(docSearchPage, "methodToCall.performLookup");
        assertTrue(documentTypeLookupPage.asText().contains("Lookup a Document type")); 

        final HtmlPage documentTypeSearchPage = clickOn(documentTypeLookupPage, "methodToCall.search");

        // return proposaldevelopmentdocument
        HtmlAnchor hyperlink = getAnchor(documentTypeSearchPage, "docTypeFullName=ProposalDevelopmentDocument");
        assertNotNull(hyperlink);
        final HtmlPage docPage = clickOn(hyperlink);
        assertTrue(docPage.asText().contains("Document Type: KRA Proposal Development"));
        // should see the user roles specified in document type xml : aggregator/budget creator/narrative writer/viewer/approver
        assertTrue(docPage.asText().contains("Aggregator: Budget Creator: Narrative Writer: Viewer: Approver:")); 
         
        // 'quickstart' is temporary set to reference the login user.  When the search xpath is finalized, this may not be the same
        setFieldValue(docPage, "propertyField[0].value", "quickstart");
        final HtmlPage docSearchResultsPage = clickOn(docPage, "methodToCall.doDocSearch");
        assertTrue(docSearchResultsPage.asText().contains(documentNumber+" KRA Proposal Development Proposal Development Document"));

    }

    /**
     * This method uses HtmlUnit to navigate the web UI, save a new document and return the
     * document number.
     * @return document number of the newly saved document.
     * @throws WorkflowException
     * @throws IOException
     */
    private String saveDocument() throws WorkflowException, IOException {
        HtmlPage proposalPage = getProposalDevelopmentPage();

        setRequiredFields(proposalPage, DEFAULT_DOCUMENT_DESCRIPTION, "005891", DEFAULT_PROPOSAL_TITLE, "08/14/2007", "08/21/2007", DEFAULT_PROPOSAL_ACTIVITY_TYPE, DEFAULT_PROPOSAL_TYPE_CODE, DEFAULT_PROPOSAL_OWNED_BY_UNIT);

        String documentNumber = getFieldValue(proposalPage, "document.documentHeader.documentNumber");

        HtmlPage savedProposalPage = clickOn(proposalPage, "methodToCall.save", "Kuali :: Proposal Development Document");

        assertContains(savedProposalPage, "Document was successfully saved.");

        return documentNumber;
    }

}
