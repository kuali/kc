/*
 * Copyright 2007 The Kuali Foundation.
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

import java.net.URL;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.core.UserSession;
import org.kuali.core.service.DocumentService;
import org.kuali.core.util.GlobalVariables;
import org.kuali.kra.KraTestBase;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.rice.KNSServiceLocator;

import com.gargoylesoftware.htmlunit.WaitingRefreshHandler;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlHiddenInput;
import com.gargoylesoftware.htmlunit.html.HtmlImageInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextArea;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

/**
 * This class tests the KraServiceLocator
 */
public class ProposalDevelopmentDocumentWebTest extends KraTestBase {

    private DocumentService documentService = null;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        GlobalVariables.setUserSession(new UserSession("quickstart"));
        documentService = KNSServiceLocator.getDocumentService();
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
        GlobalVariables.setUserSession(null);
        documentService = null;
    }

    @Test public void testProposalTypeLink() throws Exception {
        final WebClient webClient = new WebClient();
        final URL url = new URL("http://localhost:" + getPort() + "/kra-dev/");
        final HtmlPage page1 = (HtmlPage)webClient.getPage(url);
        assertEquals("Kuali Portal Index", page1.getTitleText() );

        // Administration Tab - LOGIN
        final HtmlPage page2 = (HtmlPage)webClient.getPage(url + "portal.do?selectedTab=portalAdministrationBody");

        // Get the form that we are dealing with and within that form,
        // find the submit button and the field that we want to change.
        final HtmlForm form = (HtmlForm) page2.getForms().get(0);
        final HtmlSubmitInput button
            = (HtmlSubmitInput) form.getInputByValue("Login");

        // Now submit the form by clicking the button and get back the
        // second page.
        final HtmlPage page3 = (HtmlPage) button.click();
        assertEquals("Kuali Portal Index", page3.getTitleText() );

        // test proposalType link
        final HtmlPage page4 = (HtmlPage)webClient.getPage(url + "kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.proposaldevelopment.bo.ProposalType&returnLocation=kra-dev/portal.do&hideReturnLink=true&docFormKey=88888888");
        assertEquals("Kuali :: Lookup", page4.getTitleText() );

        // test proposalType link - based on anchor
        final HtmlAnchor proposalTypeLink = (HtmlAnchor) page3.getAnchorByName("lookupProposalType");
        final HtmlPage page5 = (HtmlPage)webClient.getPage(url + proposalTypeLink.getHrefAttribute());
        assertEquals("Kuali :: Lookup", page5.getTitleText() );

    }

    @Test public void testHelpLink() throws Exception {
        final WebClient webClient = new WebClient();
        final URL url = new URL("http://localhost:" + getPort() + "/kra-dev/");
        final HtmlPage page1 = (HtmlPage)webClient.getPage(url);
        webClient.setRefreshHandler(new WaitingRefreshHandler());
        assertEquals("Kuali Portal Index", page1.getTitleText() );

        // LOGIN
        final HtmlPage page2 = (HtmlPage)webClient.getPage(url + "proposalDevelopmentProposal.do?methodToCall=docHandler&command=initiate&docTypeName=ProposalDevelopmentDocument");

        // Get the form that we are dealing with and within that form,
        // find the submit button and the field that we want to change.
        final HtmlForm form = (HtmlForm) page2.getForms().get(0);
        final HtmlSubmitInput button
            = (HtmlSubmitInput) form.getInputByValue("Login");

        // Now submit the form by clicking the button and get back the
        // second page.
        final HtmlPage page3 = (HtmlPage) button.click();
        assertEquals("Kuali :: Proposal Development Document", page3.getTitleText() );

        // test document overview help link
        final HtmlPage page4 = (HtmlPage)webClient.getPage(url + "kr/help.do?methodToCall=getAttributeHelpText&businessObjectClassName=org.kuali.core.bo.DocumentHeader&attributeName=financialDocumentDescription");
        assertEquals("Kuali :: Kuali Help", page4.getTitleText() );

        // test proposal development document attribute help link
        final HtmlPage page5 = (HtmlPage)webClient.getPage(url + "kr/help.do?methodToCall=getAttributeHelpText&businessObjectClassName=org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument&attributeName=sponsorCode");
        assertEquals("Kuali :: Kuali Help", page5.getTitleText() );
    }

    @Test public void testSaveProposalDevelopmentDocumentWeb() throws Exception {
        final WebClient webClient = new WebClient();
        final URL url = new URL("http://localhost:" + getPort() + "/kra-dev/");
        final HtmlPage page1 = (HtmlPage)webClient.getPage(url);
        webClient.setRefreshHandler(new WaitingRefreshHandler());

        assertEquals("Kuali Portal Index", page1.getTitleText() );

        // LOGIN
        final HtmlPage page2 = (HtmlPage)webClient.getPage(url + "proposalDevelopmentProposal.do?methodToCall=docHandler&command=initiate&docTypeName=ProposalDevelopmentDocument");

        // Get the form that we are dealing with and within that form,
        // find the submit button and the field that we want to change.
        final HtmlForm form = (HtmlForm) page2.getForms().get(0);
        final HtmlSubmitInput button
            = (HtmlSubmitInput) form.getInputByValue("Login");

        // Now submit the form by clicking the button and get back the
        // second page.
        final HtmlPage page3 = (HtmlPage) button.click();
        assertEquals("Kuali :: Proposal Development Document", page3.getTitleText() );

        final HtmlForm kualiForm = (HtmlForm) page3.getForms().get(0);
        final HtmlImageInput saveButton = (HtmlImageInput) kualiForm.getInputByName("methodToCall.save");

        final HtmlTextInput description = (HtmlTextInput) kualiForm.getInputByName("document.documentHeader.financialDocumentDescription");
        description.setValueAttribute("ProposalDevelopmentDocumentWebTest test");

        final HtmlTextInput sponsorCode = (HtmlTextInput) kualiForm.getInputByName("document.sponsorCode");
        sponsorCode.setValueAttribute("123456");

        final HtmlTextArea title = (HtmlTextArea) kualiForm.getTextAreasByName("document.title").get(0);
        title.setText("project title");

        final HtmlTextInput startDate = (HtmlTextInput) kualiForm.getInputByName("document.requestedStartDateInitial");
        startDate.setValueAttribute("08/14/2007");

        final HtmlTextInput endDate = (HtmlTextInput) kualiForm.getInputByName("document.requestedEndDateInitial");
        endDate.setValueAttribute("08/21/2007");

        final HtmlSelect activityType = (HtmlSelect) kualiForm.getSelectByName("document.activityTypeCode");
        assertEquals(10, activityType.getOptionSize());
        activityType.setSelectedAttribute("1", true);

        final HtmlSelect proposalType = (HtmlSelect) kualiForm.getSelectByName("document.proposalTypeCode");
        assertEquals(10, proposalType.getOptionSize());
        proposalType.setSelectedAttribute("2", true);

        final HtmlSelect ownedByUnit = (HtmlSelect) kualiForm.getSelectByName("document.ownedByUnit");
        assertEquals(3, ownedByUnit.getOptionSize());
        ownedByUnit.setSelectedAttribute("000002", true);

        final HtmlHiddenInput documentNumber = (HtmlHiddenInput) kualiForm.getInputByName("document.documentHeader.documentNumber");

        final HtmlPage page4 = (HtmlPage) saveButton.click();
        assertEquals("Kuali :: Proposal Development Document", page4.getTitleText() );

        String page4AsText = page4.asText();
        String errorMessage = "Errors were found: ";
        int index1 = page4AsText.indexOf("error");
        if (index1 > -1) {
            int index2 = page4AsText.indexOf("Document Overview");
            if (index2 > -1) {
                errorMessage += page4AsText.substring(index1, index2);
            } else {
                errorMessage += page4AsText.substring(index1);
            }
        }

        assertFalse(errorMessage, page4.asText().contains("error(s) found on page"));

        ProposalDevelopmentDocument doc = (ProposalDevelopmentDocument) documentService.getByDocumentHeaderId(documentNumber.getDefaultValue());
        assertNotNull(doc);

        assertEquals("1", doc.getActivityTypeCode());
        assertEquals("000002", doc.getOwnedByUnit());
        assertEquals("ProposalDevelopmentDocumentWebTest test", doc.getDocumentHeader().getFinancialDocumentDescription());
        assertEquals("123456", doc.getSponsorCode());
        assertEquals("project title", doc.getTitle());
        assertEquals("2007-08-14", doc.getRequestedStartDateInitial().toString());
        assertEquals("2007-08-21", doc.getRequestedEndDateInitial().toString());
        assertEquals("2", doc.getProposalTypeCode());

    }

}
