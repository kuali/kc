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

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.core.UserSession;
import org.kuali.core.service.DocumentService;
import org.kuali.core.util.GlobalVariables;
import org.kuali.kra.KraTestBase;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.rice.KNSServiceLocator;

import com.gargoylesoftware.htmlunit.ScriptResult;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlCheckBoxInput;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlHiddenInput;
import com.gargoylesoftware.htmlunit.html.HtmlImageInput;
import com.gargoylesoftware.htmlunit.html.HtmlOption;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextArea;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

import edu.iu.uis.eden.exception.WorkflowException;

/**
 * This class tests the KraServiceLocator
 */
public class ProposalDevelopmentDocumentWebTest extends KraTestBase {

    private static final Logger LOG = Logger.getLogger(ProposalDevelopmentDocumentWebTest.class);
    private static final int TEXT_INPUT = 0;
    private static final int TEXT_AREA = 1;
    private static final int SELECTED_INPUT = 2;
    private static final int HIDDEN_INPUT = 3;
    private static final int IMAGE_INPUT = 4;
    private static final int SUBMIT_INPUT_BY_NAME = 5;
    private static final int SUBMIT_INPUT_BY_VALUE = 6;
    private static final int CHECK_BOX_INPUT = 7;
    private static final String ERRORS_FOUND_ON_PAGE = "error(s) found on page";
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

    @Test
    public void testKeywordPanel() throws Exception {
        final WebClient webClient = new WebClient();
        final URL url = new URL("http://localhost:" + getPort() + "/kra-dev/");

        final HtmlPage pageInit = login(webClient, url,
                "proposalDevelopmentProposal.do?methodToCall=docHandler&command=initiate&docTypeName=ProposalDevelopmentDocument");
        assertEquals("Kuali :: Proposal Development Document", pageInit.getTitleText());

        final HtmlForm kualiForm = (HtmlForm) pageInit.getForms().get(0);
        setupProposalDevelopmentDocumentRequiredFields(kualiForm, "ProposalDevelopmentDocumentWebTest test", "123456", "project title - test", "08/14/2007", "08/21/2007", "1", "1", "000002");
        final HtmlForm formInit = (HtmlForm) pageInit.getForms().get(0);

        /* Save with basic/mandatory data */
        final HtmlPage pageAfterInitSave = clickButton(pageInit, formInit, "methodToCall.save", IMAGE_INPUT);
        final HtmlForm formAfterInitSave = (HtmlForm) pageAfterInitSave.getForms().get(0);

        assertFalse(pageAfterInitSave.asText().contains(ERRORS_FOUND_ON_PAGE));
        assertTrue(pageAfterInitSave.asText().contains("Document was successfully saved"));

        /* performing science keyword lookup */
        String lookUpInfo = "methodToCall.performLookup.(!!org.kuali.kra.proposaldevelopment.bo.ScienceKeyword!!).(:;propScienceKeywords;:)";
        final HtmlPage pageAfterKeywordLookup = multipleValuelookup(pageAfterInitSave, formAfterInitSave,lookUpInfo, "*", "scienceKeywordCode");
        final HtmlForm formAfterKeywordLookup = (HtmlForm) pageAfterKeywordLookup.getForms().get(0);

        /* verify data returned by keyword lookup */
        String keywordData = "1 Chlorine unchecked"; // initial data returned - all rows unchecked
        assertTrue(pageAfterKeywordLookup.asText().contains(keywordData));

        /* save document with science keyword data */
        final HtmlPage pageAfterKeywordSave = clickButton(pageAfterKeywordLookup, formAfterKeywordLookup, "methodToCall.save", IMAGE_INPUT);
        assertFalse(pageAfterKeywordSave.asText().contains(ERRORS_FOUND_ON_PAGE));
        assertTrue(pageAfterKeywordSave.asText().contains("Document was successfully saved"));
        final HtmlForm formAfterKeywordSave = (HtmlForm) pageAfterKeywordSave.getForms().get(0);

        /* Test javascript for select all */
        String selectAllJS = "selectAllKeywords(document)";
        final ScriptResult scriptResult = pageAfterKeywordSave.executeJavaScriptIfPossible(selectAllJS, "onSubmit", true, pageAfterKeywordSave.getDocumentElement());
        final HtmlPage pageAfterSelectAll = (HtmlPage)scriptResult.getNewPage();
        final HtmlForm formAfterSelectAll = (HtmlForm) pageAfterSelectAll.getForms().get(0);
        keywordData = "1 Chlorine checked"; // data after select all - row checked
        assertTrue(pageAfterSelectAll.asText().contains(keywordData));

        /* uncheck one row. science keyword Chlorine is unchecked */
        String uncheckRow = "document.propScienceKeywords[0].selectKeyword";
        final HtmlPage pageAfterUncheckingRow = clickButton(pageAfterSelectAll, formAfterSelectAll, uncheckRow, CHECK_BOX_INPUT);
        keywordData = "1 Chlorine unchecked"; // uncheck this particular row to retain this data
        assertTrue(pageAfterUncheckingRow.asText().contains(keywordData));

        /* check delete selected function - delete all rows other than one unchecked above*/
        final HtmlForm formAfterUncheckingRow = (HtmlForm) pageAfterUncheckingRow.getForms().get(0);
        String deleteSelected = "methodToCall.deleteSelectedScienceKeyword";
        final HtmlPage pageAfterDeleteSelected = clickButton(pageAfterUncheckingRow, formAfterUncheckingRow, deleteSelected, IMAGE_INPUT);
        keywordData = "2 Heat"; // all rows removed except 1 Chlorine
        assertFalse(pageAfterDeleteSelected.asText().contains(keywordData));


        /* save after removing all rows except one uncheked above - 1 Chlorine */
        final HtmlForm formAfterDeleteSelected = (HtmlForm) pageAfterDeleteSelected.getForms().get(0);
        final HtmlPage pageSaveAfterDelete = clickButton(pageAfterDeleteSelected, formAfterDeleteSelected, "methodToCall.save", IMAGE_INPUT);
        assertFalse(pageSaveAfterDelete.asText().contains(ERRORS_FOUND_ON_PAGE));
        assertTrue(pageSaveAfterDelete.asText().contains("Document was successfully saved"));

    }


    @Test
    public void testProposalTypeLink() throws Exception {
        final WebClient webClient = new WebClient();
        final URL url = new URL("http://localhost:" + getPort() + "/kra-dev/");

        // Administration Tab - LOGIN
        final HtmlPage page3 = login(webClient, url, "portal.do?selectedTab=portalAdministrationBody");

        assertEquals("Kuali Portal Index", page3.getTitleText());

        // test proposalType link
        final HtmlPage page4 = (HtmlPage) webClient
                .getPage(url
                        + "kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.proposaldevelopment.bo.ProposalType&returnLocation=kra-dev/portal.do&hideReturnLink=true&docFormKey=88888888");
        assertEquals("Kuali :: Lookup", page4.getTitleText());

        // test proposalType link - based on anchor
        final HtmlAnchor proposalTypeLink = (HtmlAnchor) page3.getAnchorByName("lookupProposalType");
        final HtmlPage page5 = (HtmlPage) webClient.getPage(url + proposalTypeLink.getHrefAttribute());
        assertEquals("Kuali :: Lookup", page5.getTitleText());

    }

    @Test
    public void testHelpLink() throws Exception {
        final WebClient webClient = new WebClient();
        final URL url = new URL("http://localhost:" + getPort() + "/kra-dev/");
        final HtmlPage page3 = login(webClient, url, "proposalDevelopmentProposal.do?methodToCall=docHandler&command=initiate&docTypeName=ProposalDevelopmentDocument");
        assertEquals("Kuali :: Proposal Development Document", page3.getTitleText());

        // test document overview help link
        LOG.info("getting page4");
        final HtmlPage page4 = (HtmlPage) webClient
                .getPage(url
                        + "kr/help.do?methodToCall=getAttributeHelpText&businessObjectClassName=org.kuali.core.bo.DocumentHeader&attributeName=financialDocumentDescription");
        assertEquals("Kuali :: Kuali Help", page4.getTitleText());

        // test proposal development document attribute help link
        LOG.info("getting page5");
        final HtmlPage page5 = (HtmlPage) webClient
                .getPage(url
                        + "kr/help.do?methodToCall=getAttributeHelpText&businessObjectClassName=org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument&attributeName=sponsorCode");
        assertEquals("Kuali :: Kuali Help", page5.getTitleText());
    }

    @Test
    public void testSaveProposalDevelopmentDocumentWeb() throws Exception {
        final WebClient webClient = new WebClient();
        final URL url = new URL("http://localhost:" + getPort() + "/kra-dev/");
        final HtmlPage page3 = login(webClient, url, "proposalDevelopmentProposal.do?methodToCall=docHandler&command=initiate&docTypeName=ProposalDevelopmentDocument");
        assertEquals("Kuali :: Proposal Development Document", page3.getTitleText());

        final HtmlForm kualiForm = (HtmlForm) page3.getForms().get(0);
        setupProposalDevelopmentDocumentRequiredFields(kualiForm, "ProposalDevelopmentDocumentWebTest test", "123456", "project title", "08/14/2007", "08/21/2007", "1", "1", "000002");

        final HtmlHiddenInput documentNumber = (HtmlHiddenInput) kualiForm.getInputByName("document.documentHeader.documentNumber");

        final HtmlPage page4 = clickButton(page3, kualiForm,"methodToCall.save",IMAGE_INPUT);
        assertEquals("Kuali :: Proposal Development Document", page4.getTitleText());

        String page4AsText = page4.asText();
        String errorMessage = extractErrorMessage(page4AsText);

        assertFalse(errorMessage, page4AsText.contains(ERRORS_FOUND_ON_PAGE));

        ProposalDevelopmentDocument doc = (ProposalDevelopmentDocument) documentService.getByDocumentHeaderId(documentNumber.getDefaultValue());
        assertNotNull(doc);

        verifySavedRequiredFields(doc, "1", "000002", "ProposalDevelopmentDocumentWebTest test", "123456", "project title", "2007-08-14", "2007-08-21", "1");
    }

    @Test
    public void testSaveProposalDevelopmentDocumentNotNewWeb() throws Exception {
        final WebClient webClient = new WebClient();
        final URL url = new URL("http://localhost:" + getPort() + "/kra-dev/");
        final HtmlPage page3 = login(webClient, url, "proposalDevelopmentProposal.do?methodToCall=docHandler&command=initiate&docTypeName=ProposalDevelopmentDocument");
        assertEquals("Kuali :: Proposal Development Document", page3.getTitleText() );

        final HtmlForm kualiForm = (HtmlForm) page3.getForms().get(0);
        final HtmlImageInput saveButton = (HtmlImageInput) kualiForm.getInputByName("methodToCall.save");

        setupProposalDevelopmentDocumentRequiredFields(kualiForm, "ProposalDevelopmentDocumentWebTest test", "123456", "project title", "08/14/2007", "08/21/2007", "1", "2", "000002");

        final HtmlTextInput sponsorProposalNumber = (HtmlTextInput) kualiForm.getInputByName("document.sponsorProposalNumber");
        sponsorProposalNumber.setValueAttribute("123456");

        final HtmlHiddenInput documentNumber = (HtmlHiddenInput) kualiForm.getInputByName("document.documentHeader.documentNumber");

        final HtmlPage page4 = (HtmlPage) saveButton.click();
        assertEquals("Kuali :: Proposal Development Document", page4.getTitleText() );

        String page4AsText = page4.asText();
        String errorMessage = extractErrorMessage(page4AsText);

        assertFalse(errorMessage, page4AsText.contains(ERRORS_FOUND_ON_PAGE));

        ProposalDevelopmentDocument doc = (ProposalDevelopmentDocument) documentService.getByDocumentHeaderId(documentNumber.getDefaultValue());
        assertNotNull(doc);

        verifySavedRequiredFields(doc, "1", "000002", "ProposalDevelopmentDocumentWebTest test", "123456", "project title", "2007-08-14", "2007-08-21", "2");
        assertEquals("123456", doc.getSponsorProposalNumber());
    }

    @Test
    public void testSaveProposalDevelopmentDocumentWithErrorsWeb() throws Exception {
        final WebClient webClient = new WebClient();
        final URL url = new URL("http://localhost:" + getPort() + "/kra-dev/");

        final HtmlPage page3 = login(webClient, url, "proposalDevelopmentProposal.do?methodToCall=docHandler&command=initiate&docTypeName=ProposalDevelopmentDocument");
        assertEquals("Kuali :: Proposal Development Document", page3.getTitleText());

        final HtmlForm kualiForm = (HtmlForm) page3.getForms().get(0);
        final HtmlImageInput saveButton = (HtmlImageInput) kualiForm.getInputByName("methodToCall.save");

        setupProposalDevelopmentDocumentRequiredFields(kualiForm, "ProposalDevelopmentDocumentWebTest test", "123456", "project title", "08/14/2007", "08/21/2007", "1", "2", "000002");

        final HtmlPage page4 = (HtmlPage) saveButton.click();
        assertEquals("Kuali :: Proposal Development Document", page4.getTitleText() );

        String page4AsText = page4.asText();
        String errorMessage = extractErrorMessage(page4AsText);

        assertTrue(errorMessage, page4AsText.contains(ERRORS_FOUND_ON_PAGE));

    }

    @Test public void testSaveProposalDevelopmentDocumentWithoutProposalType() throws Exception {
        final WebClient webClient = new WebClient();
        final URL url = new URL("http://localhost:" + getPort() + "/kra-dev/");

        final HtmlPage page3 = login(webClient, url, "proposalDevelopmentProposal.do?methodToCall=docHandler&command=initiate&docTypeName=ProposalDevelopmentDocument");

        assertEquals("Kuali :: Proposal Development Document", page3.getTitleText() );

        final HtmlForm kualiForm = (HtmlForm) page3.getForms().get(0);
        final HtmlImageInput saveButton = (HtmlImageInput) kualiForm.getInputByName("methodToCall.save");

        setupProposalDevelopmentDocumentRequiredFields(kualiForm, "ProposalDevelopmentDocumentWebTest test", "123456", "project title", "08/14/2007", "08/21/2007", "1", "", "000002");

        final HtmlPage page4 = (HtmlPage) saveButton.click();
        assertEquals("Kuali :: Proposal Development Document", page4.getTitleText() );

        String page4AsText = page4.asText();
        String errorMessage = extractErrorMessage(page4AsText);

        assertTrue(errorMessage, page4AsText.contains(ERRORS_FOUND_ON_PAGE));

    }

    @Test
    public void testOrganizationLocationPanel() throws Exception {
        final WebClient webClient = new WebClient();
        final URL url = new URL("http://localhost:" + getPort() + "/kra-dev/");

        final HtmlPage page3 = login(webClient, url,
                "proposalDevelopmentProposal.do?methodToCall=docHandler&command=initiate&docTypeName=ProposalDevelopmentDocument");
        assertEquals("Kuali :: Proposal Development Document", page3.getTitleText());

        final HtmlForm kualiForm = (HtmlForm) page3.getForms().get(0);
        setupProposalDevelopmentDocumentRequiredFields(kualiForm, "ProposalDevelopmentDocumentWebTest test", "123456", "project title", "08/14/2007", "08/21/2007", "1", "1", "000002");

        // start to set up organization/location panel

        // organization

        final HtmlPage page4 = lookup(webClient, page3, kualiForm, "methodToCall.performLookup.(!!org.kuali.kra.bo.Organization!!).(((organizationId:document.organizationId,", "000001",
                "proposalDevelopmentProposal.do?document.organization.", "organizationId");
        final HtmlForm form1 = (HtmlForm) page4.getForms().get(0);
        assertEquals("000001", getFieldValue(form1, HIDDEN_INPUT, "document.organizationId"));
        assertTrue(page4.asText().contains("Congressional District: Eighth"));
        assertTrue(page4.asText().contains("Performing Organization: University"));
        assertTrue(page4.asText().contains("Applicant Organization: University"));
        assertTrue(page4.asText().contains("Authorized Representative Name & Address: First Name"));
        // default prop location created
        assertEquals("University", getFieldValue(form1, TEXT_INPUT, "document.propLocations[0].location"));
        // delete default line
        final HtmlPage page5 = clickButton(page4, form1,  "methodToCall.deleteLocation.line0.", IMAGE_INPUT);
        final HtmlForm form2 = (HtmlForm) page5.getForms().get(0);
        // save without location line
        // the default location line will be recreated
        final HtmlPage page6 = clickButton(page5, form2, "methodToCall.save", IMAGE_INPUT);
        assertEquals("Kuali :: Proposal Development Document", page6.getTitleText());
        final HtmlForm form3 = (HtmlForm) page6.getForms().get(0);
        // one of the following to check save is OK
        assertTrue(page6.asText().contains(ERRORS_FOUND_ON_PAGE));
        assertFalse(page6.asText().contains("Document was successfully saved"));

        // performingorg lookup

        final HtmlPage page7 = lookup(webClient, page6, form3, "methodToCall.performLookup.(!!org.kuali.kra.bo.Organization!!).(((organizationId:document.performingOrganizationId,", "000002",
                "proposalDevelopmentProposal.do?document.performingOrganization.", "organizationId");
        final HtmlForm form4 = (HtmlForm) page7.getForms().get(0);
        assertEquals("000002", getFieldValue(form4, HIDDEN_INPUT, "document.performingOrganizationId"));
        assertTrue(page7.asText().contains("Performing Organization: California Institute of Technology"));
        // California Institute of Technology

        // proplocations
        // set up and add first line
        setFieldValue(kualiForm, TEXT_INPUT, "newPropLocation.location", "location 1");

        // test rolodex lookup
        final HtmlPage page8 = lookup(webClient, page7, form4, "methodToCall.performLookup.(!!org.kuali.kra.bo.Rolodex!!).(((rolodexId:newPropLocation.rolodexId,", "1728",
                "proposalDevelopmentProposal.do?newPropLocation.rolodex.", "rolodexId");
        final HtmlForm form5 = (HtmlForm) page8.getForms().get(0);
        assertEquals("1728", getFieldValue(form5, HIDDEN_INPUT, "newPropLocation.rolodexId"));
        assertTrue(page8.asText().contains("National Center for Environmental Research and Quality Assurance"));

        final HtmlPage page9 = clickButton(page8, form5, "methodToCall.addLocation", IMAGE_INPUT);
        final HtmlForm form6 = (HtmlForm) page9.getForms().get(0);

        assertEquals("0", getFieldValue(form6, HIDDEN_INPUT, "newPropLocation.rolodexId"));
        // how to check newlocation address is empty
        assertEquals("1728", getFieldValue(form6, HIDDEN_INPUT, "document.propLocations[1].rolodexId"));
        assertTrue(page9.asText().contains("National Center for Environmental Research and Quality Assurance"));

        // 2nd line
        // set up and add 2nd line
        setFieldValue(form6, TEXT_INPUT, "newPropLocation.location", "location 2");

        // test rolodex lookup
        final HtmlPage page10 = lookup(webClient, page9, form6, "methodToCall.performLookup.(!!org.kuali.kra.bo.Rolodex!!).(((rolodexId:newPropLocation.rolodexId,", "1727",
                "proposalDevelopmentProposal.do?newPropLocation.rolodex.", "rolodexId");
        final HtmlForm form7 = (HtmlForm) page10.getForms().get(0);
        assertEquals("1727", getFieldValue(form7, HIDDEN_INPUT, "newPropLocation.rolodexId"));
        assertTrue(page10.asText().contains("Organization 1126"));

        final HtmlPage page11 = clickButton(page10, form7, "methodToCall.addLocation", IMAGE_INPUT);
        final HtmlForm form8 = (HtmlForm) page11.getForms().get(0);

        assertEquals("0", getFieldValue(form8, HIDDEN_INPUT, "newPropLocation.rolodexId"));
        // how to check newlocation address is empty
        assertEquals("1727", getFieldValue(form8, HIDDEN_INPUT, "document.propLocations[2].rolodexId"));
        assertTrue(page11.asText().contains("Organization 1126"));

        // clearaddress
        final HtmlPage page12 = clickButton(page11, form8, "methodToCall.clearAddress.line1.", IMAGE_INPUT);
        final HtmlForm form9 = (HtmlForm) page12.getForms().get(0);
        assertEquals("0", getFieldValue(form9, HIDDEN_INPUT, "document.propLocations[1].rolodexId"));
        assertFalse(page12.asText().contains("National Center for Environmental Research and Quality Assurance"));
        // verify other fields too? location, proplocations[1] ?

        // delete lines
        final HtmlPage page13 = clickButton(page12, form9, "methodToCall.deleteLocation.line1.", IMAGE_INPUT);
        final HtmlForm form10 = (HtmlForm) page13.getForms().get(0);
        assertEquals("1727", getFieldValue(form10, HIDDEN_INPUT, "document.propLocations[1].rolodexId"));
        assertTrue(page13.asText().contains("Organization 1126"));
        // how to check only one left
        final HtmlPage page14 = clickButton(page13, form10, "methodToCall.save", IMAGE_INPUT);
        assertEquals("Kuali :: Proposal Development Document", page10.getTitleText());
        final HtmlForm form11 = (HtmlForm) page14.getForms().get(0);
        // one of the following to check save is OK
        assertFalse(page14.asText().contains(ERRORS_FOUND_ON_PAGE));
        assertTrue(page14.asText().contains("Document was successfully saved"));
        // verify for is still ok
        assertEquals("000001", getFieldValue(form11, HIDDEN_INPUT, "document.organizationId"));
        assertTrue(page14.asText().contains("Congressional District: Eighth"));
        assertTrue(page14.asText().contains("Applicant Organization: University"));
        assertTrue(page14.asText().contains("Authorized Representative Name & Address: First Name"));
        assertEquals("000002", getFieldValue(form11, HIDDEN_INPUT, "document.performingOrganizationId"));
        assertTrue(page14.asText().contains("Performing Organization: California Institute of Technology"));

        assertEquals("1727", getFieldValue(form11, HIDDEN_INPUT, "document.propLocations[1].rolodexId"));
        assertTrue(page14.asText().contains("Organization 1126"));
        assertEquals("0", getFieldValue(form11, HIDDEN_INPUT, "document.propLocations[0].rolodexId"));
        assertEquals("University", getFieldValue(form11, TEXT_INPUT, "document.propLocations[0].location"));

        // verify DB
        final HtmlHiddenInput documentNumber = (HtmlHiddenInput) form6.getInputByName("document.documentHeader.documentNumber");

        ProposalDevelopmentDocument doc = (ProposalDevelopmentDocument) getDocument(documentNumber.getDefaultValue());
        assertNotNull(doc);

        verifySavedRequiredFields(doc, "1", "000002", "ProposalDevelopmentDocumentWebTest test", "123456", "project title", "2007-08-14", "2007-08-21", "1");
        assertEquals("000001", doc.getOrganizationId());
        assertEquals("000002", doc.getPerformingOrganizationId());
        assertEquals("University", doc.getPropLocations().get(0).getLocation());
        assertEquals(0, doc.getPropLocations().get(0).getRolodexId());
        assertEquals("location 2", doc.getPropLocations().get(1).getLocation());
        assertEquals(1727, doc.getPropLocations().get(1).getRolodexId());

    }

    @Test
    public void testDeliveryInfoPanel() throws Exception {
        final WebClient webClient = new WebClient();
        final URL url = new URL("http://localhost:" + getPort() + "/kra-dev/");
        final HtmlPage page3 = login(webClient, url,
                "proposalDevelopmentProposal.do?methodToCall=docHandler&command=initiate&docTypeName=ProposalDevelopmentDocument");
        assertEquals("Kuali :: Proposal Development Document", page3.getTitleText());

        final HtmlForm kualiForm = (HtmlForm) page3.getForms().get(0);
        setupProposalDevelopmentDocumentRequiredFields(kualiForm, "ProposalDevelopmentDocumentWebTest test", "123456", "project title", "08/14/2007", "08/21/2007", "1", "1", "000002");


        // dropdowns
        setFieldValue(kualiForm, SELECTED_INPUT, "document.mailBy", "1", 3);
        setFieldValue(kualiForm, SELECTED_INPUT, "document.mailType", "2", 4);


        // input fields
        setFieldValue(kualiForm, TEXT_INPUT, "document.mailAccountNumber", "10-0001");
        setFieldValue(kualiForm, TEXT_INPUT, "document.numberOfCopies", "2");

        // test mailing address lookup
        final HtmlPage page4 = lookup(webClient, page3, kualiForm, "methodToCall.performLookup.(!!org.kuali.kra.bo.Rolodex!!).(((rolodexId:document.mailingAddressId,",
                "1728", "proposalDevelopmentProposal.do?document.rolodex.", "rolodexId");
        final HtmlForm form1 = (HtmlForm) page4.getForms().get(0);
        assertEquals("1728", getFieldValue(form1, HIDDEN_INPUT, "document.mailingAddressId"));
        assertTrue(page4.asText().contains("National Center for Environmental Research and Quality Assurance"));

        // mail description textarea
        setFieldValue(form1, TEXT_AREA, "document.mailDescription", "mail description");

        webClient.setJavaScriptEnabled(false);
        final HtmlPage page5 = clickButton(page4, form1,
                "methodToCall.updateTextArea.((#document.mailDescription:proposalDevelopmentProposal:Mail Description#))",
                IMAGE_INPUT);
        final HtmlForm form2 = (HtmlForm) page5.getForms().get(0);
        assertEquals("mail description", getFieldValue(form2, TEXT_AREA, "document.mailDescription"));
        setFieldValue(form2, TEXT_AREA, "document.mailDescription", "mail description \n line2");

        final HtmlPage page6 = clickButton(page5, form2, "methodToCall.postTextAreaToParent", IMAGE_INPUT);
        final HtmlForm form3 = (HtmlForm) page6.getForms().get(0);
        assertEquals("mail description \n line2", getFieldValue(form3, TEXT_AREA, "document.mailDescription"));


        // save and check
        final HtmlPage page7 = clickButton(page6, form3, "methodToCall.save", IMAGE_INPUT);
        assertEquals("Kuali :: Proposal Development Document", page7.getTitleText());
        final HtmlForm form4 = (HtmlForm) page7.getForms().get(0);
        // one of the following to check save is OK
        assertFalse(page7.asText().contains(ERRORS_FOUND_ON_PAGE));
        assertTrue(page7.asText().contains("Document was successfully saved"));

        assertEquals("2", getFieldValue(form4, SELECTED_INPUT, "document.mailType"));
        assertEquals("1", getFieldValue(form4, SELECTED_INPUT, "document.mailBy"));

        assertEquals("10-0001", getFieldValue(form4, TEXT_INPUT, "document.mailAccountNumber"));
        assertEquals("2", getFieldValue(form4, TEXT_INPUT, "document.numberOfCopies"));


        assertEquals("1728", getFieldValue(form4, HIDDEN_INPUT, "document.mailingAddressId"));
        assertTrue(page7.asText().contains("National Center for Environmental Research and Quality Assurance"));

        assertEquals("mail description \n line2", getFieldValue(form4, TEXT_AREA, "document.mailDescription"));

        final HtmlHiddenInput documentNumber = (HtmlHiddenInput) form4.getInputByName("document.documentHeader.documentNumber");
        ProposalDevelopmentDocument doc = (ProposalDevelopmentDocument) getDocument(documentNumber.getDefaultValue());
        assertNotNull(doc);
        verifySavedRequiredFields(doc, "1", "000002", "ProposalDevelopmentDocumentWebTest test", "123456", "project title", "2007-08-14", "2007-08-21", "1");

        assertEquals("1", doc.getMailBy());
        assertEquals("2", doc.getMailType());
        assertEquals(1728, doc.getMailingAddressId());
        assertEquals("10-0001", doc.getMailAccountNumber());
        assertEquals("2", doc.getNumberOfCopies());
        assertEquals("mail description \n line2", doc.getMailDescription());
    }


    @Test
    public void testSpecialReviewPage() throws Exception {
        final WebClient webClient = new WebClient();
        final URL url = new URL("http://localhost:" + getPort() + "/kra-dev/");

        final HtmlPage page3 = login(webClient, url,
                "proposalDevelopmentProposal.do?methodToCall=docHandler&command=initiate&docTypeName=ProposalDevelopmentDocument");
        assertEquals("Kuali :: Proposal Development Document", page3.getTitleText());

        final HtmlForm kualiForm = (HtmlForm) page3.getForms().get(0);
        setupProposalDevelopmentDocumentRequiredFields(kualiForm, "ProposalDevelopmentDocumentWebTest test", "123456", "project title", "08/14/2007", "08/21/2007", "1", "1", "000002");
        final HtmlPage page4 = clickButton(page3, kualiForm, "methodToCall.headerTab.headerDispatch.save.navigateTo.specialReview.x",
                SUBMIT_INPUT_BY_NAME);
        assertTrue(page4.asText().contains("Document was successfully saved"));
        // really is in special review page
        assertTrue(page4.asText().contains("Approval Status Protocol # Application Date Approval Date Comments"));
        HtmlForm form1 = (HtmlForm) page4.getForms().get(0);
        webClient.setJavaScriptEnabled(false);

        final HtmlPage page5 = setSpecialReviewLine(page4, form1, "08/01/2007;;123;1;2;comment1");

        final HtmlForm form2 = (HtmlForm) page5.getForms().get(0);
        assertEquals("comment1 \n line2", getFieldValue(form2, TEXT_AREA, "newPropSpecialReview.comments"));
        final HtmlPage page6 = clickButton(page5, form2, "methodToCall.addSpecialReview", IMAGE_INPUT);
        final HtmlForm form3 = (HtmlForm) page6.getForms().get(0);
        validateSpecialReviewLine(form3, "document.propSpecialReviews[0]", "08/01/2007;;123;1;2;comment1");
        // 2nd line
        final HtmlPage page7 = setSpecialReviewLine(page5, form3, "08/02/2007;;456;2;3;comment2");
        final HtmlForm form4 = (HtmlForm) page7.getForms().get(0);
        assertEquals("comment2 \n line2", getFieldValue(form4, TEXT_AREA, "newPropSpecialReview.comments"));
        final HtmlPage page8 = clickButton(page7, form4, "methodToCall.addSpecialReview", IMAGE_INPUT);
        final HtmlForm form5 = (HtmlForm) page8.getForms().get(0);
        validateSpecialReviewLine(form5, "document.propSpecialReviews[0]", "08/01/2007;;123;1;2;comment1");
        validateSpecialReviewLine(form5, "document.propSpecialReviews[1]", "08/02/2007;;456;2;3;comment2");

        // delete special review line 0
        final HtmlPage page9 = clickButton(page8, form5, "methodToCall.deleteSpecialReview.line0.", IMAGE_INPUT);
        final HtmlForm form6 = (HtmlForm) page9.getForms().get(0);
        validateSpecialReviewLine(form6, "document.propSpecialReviews[0]", "08/02/2007;;456;2;3;comment2");
        // save
        final HtmlPage page10 = clickButton(page9, form6, "methodToCall.save", IMAGE_INPUT);
        assertEquals("Kuali :: Proposal Development Document", page9.getTitleText());
        final HtmlForm form7 = (HtmlForm) page10.getForms().get(0);
        // one of the following to check save is OK
        assertFalse(page10.asText().contains(ERRORS_FOUND_ON_PAGE));
        assertTrue(page10.asText().contains("Document was successfully saved"));
        validateSpecialReviewLine(form7, "document.propSpecialReviews[0]", "08/02/2007;;456;2;3;comment2");

    }

    @Test
    public void testSponsorProgramInformationPanel() throws Exception {
        final WebClient webClient = new WebClient();
        final URL url = new URL("http://localhost:" + getPort() + "/kra-dev/");
        final HtmlPage page3 = login(webClient, url, "proposalDevelopmentProposal.do?methodToCall=docHandler&command=initiate&docTypeName=ProposalDevelopmentDocument");
        assertEquals("Kuali :: Proposal Development Document", page3.getTitleText() );

        final HtmlForm kualiForm = (HtmlForm) page3.getForms().get(0);
        final HtmlImageInput saveButton = (HtmlImageInput) kualiForm.getInputByName("methodToCall.save");

        setupProposalDevelopmentDocumentRequiredFields(kualiForm, "ProposalDevelopmentDocumentWebTest test", "005891", "project title", "08/14/2007", "08/21/2007", "1", "2", "000002");

        // sponsor program info fields
        setFieldValue(kualiForm, TEXT_INPUT, "document.deadlineDate", "2007-08-14");
        setFieldValue(kualiForm, SELECTED_INPUT, "document.deadlineType", "P", 3);
        setFieldValue(kualiForm, TEXT_INPUT, "document.primeSponsorCode", "005984");
        setFieldValue(kualiForm, TEXT_INPUT, "document.currentAwardNumber", "1234567890");
        setFieldValue(kualiForm, SELECTED_INPUT, "document.nsfCode", "J.02", 39);
        setFieldValue(kualiForm, TEXT_INPUT, "document.agencyDivisionCode", "123");
        setFieldValue(kualiForm, TEXT_AREA, "document.programAnnouncementTitle", "we want to give you money");
        setFieldValue(kualiForm, SELECTED_INPUT, "document.noticeOfOpportunityCode", "2", 8);
        setFieldValue(kualiForm, TEXT_INPUT, "document.cfdaNumber", "123456");
        setFieldValue(kualiForm, TEXT_INPUT, "document.programAnnouncementNumber", "123478");
        setFieldValue(kualiForm, TEXT_INPUT, "document.sponsorProposalNumber", "234567");
        setFieldValue(kualiForm, TEXT_INPUT, "document.continuedFrom", "98765432");

        // TODO: possibly refactor this to use setFieldValue
        final HtmlCheckBoxInput subawards = (HtmlCheckBoxInput) kualiForm.getInputByName("document.subcontracts");
        subawards.setChecked(true);

        setFieldValue(kualiForm, TEXT_INPUT, "document.agencyProgramCode", "456");

        final HtmlHiddenInput documentNumber = (HtmlHiddenInput) kualiForm.getInputByName("document.documentHeader.documentNumber");

        final HtmlPage page4 = (HtmlPage) saveButton.click();
        assertEquals("Kuali :: Proposal Development Document", page4.getTitleText() );

        String page4AsText = page4.asText();
        String errorMessage = extractErrorMessage(page4AsText);

        assertFalse(errorMessage, page4AsText.contains(ERRORS_FOUND_ON_PAGE));

        // make sure the document saved correctly
        ProposalDevelopmentDocument doc = (ProposalDevelopmentDocument) documentService.getByDocumentHeaderId(documentNumber.getDefaultValue());
        assertNotNull(doc);

        verifySavedRequiredFields(doc, "1", "000002", "ProposalDevelopmentDocumentWebTest test", "005891", "project title", "2007-08-14", "2007-08-21", "2");

        // check sponsor program info fields
        assertEquals("P", doc.getDeadlineType());
        assertEquals("005984", doc.getPrimeSponsorCode());
        assertEquals("1234567890", doc.getCurrentAwardNumber());
        assertEquals("J.02", doc.getNsfCode());
        assertEquals("123", doc.getAgencyDivisionCode());
        assertEquals("we want to give you money", doc.getProgramAnnouncementTitle());
        assertEquals("2", doc.getNoticeOfOpportunityCode());
        assertEquals("123456", doc.getCfdaNumber());
        assertEquals("123478", doc.getProgramAnnouncementNumber());
        assertEquals("234567", doc.getSponsorProposalNumber());
        assertEquals("98765432", doc.getContinuedFrom());
        assertTrue("Subcontracts should be true", doc.getSubcontracts());
        assertEquals("456", doc.getAgencyProgramCode());

        // make sure the fields we set are displayed on the form after saving
        final HtmlForm savedForm = (HtmlForm) page4.getForms().get(0);

        assertTrue("Should have saved message", page4AsText.indexOf("Document was successfully saved.") > 0);
        // TODO: verify header fields

        // sponsor program info fields
        assertEquals("08/14/2007", getFieldValue(savedForm, TEXT_INPUT, "document.deadlineDate"));
        assertEquals("P", getFieldValue(savedForm, SELECTED_INPUT, "document.deadlineType"));
        assertEquals("005984", getFieldValue(savedForm, TEXT_INPUT, "document.primeSponsorCode"));
        assertEquals("1234567890", getFieldValue(savedForm, TEXT_INPUT, "document.currentAwardNumber"));
        assertEquals("J.02", getFieldValue(savedForm, SELECTED_INPUT, "document.nsfCode"));
        assertEquals("123", getFieldValue(savedForm, TEXT_INPUT, "document.agencyDivisionCode"));
        assertEquals("we want to give you money", getFieldValue(savedForm, TEXT_AREA, "document.programAnnouncementTitle"));
        assertEquals("2", getFieldValue(savedForm, SELECTED_INPUT, "document.noticeOfOpportunityCode"));
        assertEquals("123456", getFieldValue(savedForm, TEXT_INPUT, "document.cfdaNumber"));
        assertEquals("123478", getFieldValue(savedForm, TEXT_INPUT, "document.programAnnouncementNumber"));
        assertEquals("234567", getFieldValue(savedForm, TEXT_INPUT, "document.sponsorProposalNumber"));
        assertEquals("98765432", getFieldValue(savedForm, TEXT_INPUT, "document.continuedFrom"));

        // TODO: possibly refactor this to use setFieldValue
        final HtmlCheckBoxInput savedSubawards = (HtmlCheckBoxInput) savedForm.getInputByName("document.subcontracts");
        assertEquals("on", savedSubawards.getValueAttribute());

        assertEquals("456", getFieldValue(savedForm, TEXT_INPUT, "document.agencyProgramCode"));

        // test label
        final HtmlDivision sponsorNameDiv = (HtmlDivision) page4.getHtmlElementById("sponsorName.div");
        assertEquals("Baystate Medical Center", sponsorNameDiv.asText());

        // test label
        final HtmlDivision primeSponsorNameDiv = (HtmlDivision) page4.getHtmlElementById("primeSponsorName.div");
        assertEquals("Kuwait Petroleum Corporation", primeSponsorNameDiv.asText());
    }

    private HtmlPage textAreaPop(String fieldName, String fieldText, String methodToCall, boolean scriptEnabled) throws Exception {
        final WebClient webClient = new WebClient();
        final URL url = new URL("http://localhost:" + getPort() + "/kra-dev/");
        final HtmlPage page3 = login(webClient, url,
                "proposalDevelopmentProposal.do?methodToCall=docHandler&command=initiate&docTypeName=ProposalDevelopmentDocument");
        assertEquals("Kuali :: Proposal Development Document", page3.getTitleText());

        // collection of alerts from js
        //final List collectedAlerts = new ArrayList();
        //webClient.setAlertHandler(new CollectingAlertHandler(collectedAlerts));

        final HtmlForm kualiForm = (HtmlForm) page3.getForms().get(0);
        setFieldValue(kualiForm, TEXT_AREA, fieldName, fieldText);
        // js or non-js
        if (!scriptEnabled) {
            webClient.setJavaScriptEnabled(false);
        }
        final HtmlPage page4 = clickButton(page3, kualiForm,  methodToCall, IMAGE_INPUT);
        final HtmlForm form1 = (HtmlForm) page4.getForms().get(0);
        assertEquals(fieldText, getFieldValue(form1, TEXT_AREA, fieldName));
        setFieldValue(form1, TEXT_AREA, fieldName, fieldText + " \n line2");

        return clickButton(page4, form1,  "methodToCall.postTextAreaToParent", IMAGE_INPUT);
        // final HtmlForm form2 = (HtmlForm) page5.getForms().get(0);
        // final HtmlTextArea textArea1 = (HtmlTextArea) form2.getTextAreasByName(fieldName).get(0);
        // assertEquals(fieldText+" \n line2", textArea1.getText());


    }

    @Test
    public void testExpandedTextArea() throws Exception {
        // remove it later
        String fieldName = "document.title";
        String fieldText = "project title";
        String methodToCall = "methodToCall.updateTextArea.((#" + fieldName + ":proposalDevelopmentProposal:Project Title#))";
        //final HtmlPage page5 = textAreaPop(fieldName, fieldText, methodToCall, true);
        final HtmlPage page5=textAreaPop(fieldName, fieldText, methodToCall,false);
        final HtmlForm form2 = (HtmlForm) page5.getForms().get(0);
        assertEquals(fieldText + " \n line2", getFieldValue(form2, TEXT_AREA, fieldName));

    }

    private HtmlPage login(WebClient webClient, URL url, String loginLocation) throws Exception {
        final HtmlPage page1 = (HtmlPage) webClient.getPage(url);
        assertEquals("Kuali Portal Index", page1.getTitleText());

        // LOGIN
        final HtmlPage page2 = (HtmlPage) webClient.getPage(url + loginLocation);

        // Get the form that we are dealing with and within that form,
        // find the submit button and the field that we want to change.
        final HtmlForm form = (HtmlForm) page2.getForms().get(0);

        // Now submit the form by clicking the button and get back the
        // second page.
        return clickButton(page2, form, "Login", SUBMIT_INPUT_BY_VALUE);

    }

    /* multiple return value lookup - select all records and return data */
    private HtmlPage multipleValuelookup(HtmlPage htmlPage, HtmlForm htmlForm, String uniqueLookupButtonName, String selectedFieldValue,
            String searchField) throws Exception {
        final URL url = new URL("http://localhost:" + getPort() + "/kra-dev/");
        final HtmlPage pageInit = clickButton(htmlPage, htmlForm,uniqueLookupButtonName,IMAGE_INPUT);
        final HtmlForm formLookup = (HtmlForm) pageInit.getForms().get(0);
        setFieldValue(formLookup, TEXT_INPUT, searchField, selectedFieldValue);
        final HtmlPage pageAfterSearch = clickButton(pageInit, formLookup, "methodToCall.search", IMAGE_INPUT);
        final HtmlForm formAfterSearch = (HtmlForm) pageAfterSearch.getForms().get(0);

        String selectAll="methodToCall.selectAll.(::;false;::).x";
        final HtmlPage pageAfterSelectAll = clickButton(pageAfterSearch, formAfterSearch, selectAll, IMAGE_INPUT);
        final HtmlForm formAfterSelectAll = (HtmlForm) pageAfterSelectAll.getForms().get(0);

        String returnSelected="methodToCall.prepareToReturnSelectedResults.x";
        final HtmlPage page4 = clickButton(pageAfterSelectAll, formAfterSelectAll, returnSelected, IMAGE_INPUT);
        return page4;
    }



    // should be able to make one lookup method for all single value lookup
    private HtmlPage lookup(WebClient webClient, HtmlPage htmlPage, HtmlForm htmlForm, String uniqueLookupButtonName, String selectedFieldValue, String returnProperty,
            String searchField) throws Exception {
        final URL url = new URL("http://localhost:" + getPort() + "/kra-dev/");

        final HtmlPage page1 = clickButton(htmlPage, htmlForm,uniqueLookupButtonName,IMAGE_INPUT);
        final HtmlForm lookupForm = (HtmlForm) page1.getForms().get(0);
        setFieldValue(lookupForm, TEXT_INPUT, searchField, selectedFieldValue);
        final HtmlPage page2 = clickButton(page1, lookupForm, "methodToCall.search", IMAGE_INPUT);
        assertTrue(page2.asText().contains("Return value " + selectedFieldValue));
        int idx1 = page2.asXml().indexOf(returnProperty);
        //int idx2 = page42.asXml().indexOf("anchor=topOfForm", idx1);
        int idx2 = page2.asXml().indexOf("\"", idx1);
        String returnPath = page2.asXml().substring(idx1, idx2).replace("&amp;", "&");
        return (HtmlPage) webClient.getPage(url + returnPath);

    }

    private String getImageTagName(HtmlPage page, String uniqueNamePrefix) {
        int idx1 = page.asXml().indexOf(uniqueNamePrefix);
        //int idx2 = page.asXml().indexOf(".((##)).((&lt;&gt;)).(([])).((**)).((^^)).((&amp;&amp;)).((//)).((~~)).anchor", idx1);
        int idx2 = page.asXml().indexOf("\"", idx1);
        return page.asXml().substring(idx1, idx2).replace("&amp;", "&").replace("((&lt;&gt;))", "((<>))");
    }


    private void setFieldValue(HtmlForm htmlForm, int type, String fieldName, String value) {
        setFieldValue(htmlForm, type, fieldName, value, -1);
    }

    private void setFieldValue(HtmlForm htmlForm, int type, String fieldName, String value, int optionSize) {
        switch (type) {
            case TEXT_INPUT:
                final HtmlTextInput text = (HtmlTextInput) htmlForm.getInputByName(fieldName);
                text.setValueAttribute(value);
                break;
            case TEXT_AREA:
                final HtmlTextArea textArea = (HtmlTextArea) htmlForm.getTextAreasByName(fieldName).get(0);
                textArea.setText(value);
                break;
            case SELECTED_INPUT:
                final HtmlSelect selected = (HtmlSelect) htmlForm.getSelectByName(fieldName);
                selected.setSelectedAttribute(value, true);
                if (optionSize != -1) {
                    assertEquals(optionSize, selected.getOptionSize());
                }
                break;

            default:
                assertTrue(false);
                break;
        }
    }

    private String getFieldValue(HtmlForm htmlForm, int type, String fieldName) {
        switch (type) {
            case TEXT_INPUT:
                final HtmlTextInput text = (HtmlTextInput) htmlForm.getInputByName(fieldName);
                return text.getValueAttribute();
            case HIDDEN_INPUT:
                final HtmlHiddenInput hiddenText = (HtmlHiddenInput) htmlForm.getInputByName(fieldName);
                return hiddenText.getValueAttribute();
            case TEXT_AREA:
                final HtmlTextArea textArea = (HtmlTextArea) htmlForm.getTextAreasByName(fieldName).get(0);
                return textArea.getText();
            case SELECTED_INPUT:
                final HtmlSelect selected = (HtmlSelect) htmlForm.getSelectByName(fieldName);
                return ((HtmlOption) (selected.getSelectedOptions().get(0))).getValueAttribute();

            default:
                assertTrue(false);
                return null;
        }
    }

    private HtmlPage clickButton(HtmlPage page, HtmlForm htmlForm, String buttonName, int type) throws Exception {
        String completeButtonName=getImageTagName(page, buttonName);
        switch (type) {
            case IMAGE_INPUT:
                final HtmlImageInput button = (HtmlImageInput) htmlForm.getInputByName(completeButtonName);
                return (HtmlPage) button.click();
            case SUBMIT_INPUT_BY_NAME:
                final HtmlSubmitInput button1 = (HtmlSubmitInput) htmlForm.getInputByName(completeButtonName);
                return (HtmlPage) button1.click();
            case SUBMIT_INPUT_BY_VALUE:
                final HtmlSubmitInput button2 = (HtmlSubmitInput) htmlForm.getInputByValue(buttonName);
                return (HtmlPage) button2.click();
            case CHECK_BOX_INPUT:
                final HtmlCheckBoxInput checkbox = (HtmlCheckBoxInput) htmlForm.getInputByName(buttonName);
                return (HtmlPage) checkbox.click();
            default:
                assertTrue(false);
                return null;
        }
    }

    private void setupProposalDevelopmentDocumentRequiredFields(HtmlForm kualiForm, String description, String sponsorCode, String title, String startDate, String endDate, String activityType, String proposalType, String ownedByUnit) throws Exception {
        setFieldValue(kualiForm, TEXT_INPUT, "document.documentHeader.financialDocumentDescription", description);
        setFieldValue(kualiForm, TEXT_INPUT, "document.sponsorCode", sponsorCode);
        setFieldValue(kualiForm, TEXT_AREA, "document.title", title);
        setFieldValue(kualiForm, TEXT_INPUT, "document.requestedStartDateInitial", startDate);
        setFieldValue(kualiForm, TEXT_INPUT, "document.requestedEndDateInitial", endDate);
        setFieldValue(kualiForm, SELECTED_INPUT, "document.activityTypeCode", activityType, 10);
        setFieldValue(kualiForm, SELECTED_INPUT, "document.proposalTypeCode", proposalType, 10);
        setFieldValue(kualiForm, SELECTED_INPUT, "document.ownedByUnit", ownedByUnit, 3);
    }


    /**
     * This method checks document fields against the passed in values
     * @param doc the document to check values against
     * @param activityType to check
     * @param ownedByUnit to check
     * @param description to check
     * @param sponsorCode to check
     * @param title toi check
     * @param requestedStartDateInitial to check
     * @param requestedEndDateInitial to check
     * @param proposalTypeCode to check
     * @throws WorkflowException
     */
    private void verifySavedRequiredFields(ProposalDevelopmentDocument doc, String activityType, String ownedByUnit, String description, String sponsorCode, String title, String requestedStartDateInitial, String requestedEndDateInitial, String proposalTypeCode) throws WorkflowException {
        assertEquals(activityType, doc.getActivityTypeCode());
        assertEquals(ownedByUnit, doc.getOwnedByUnit());
        assertEquals(description, doc.getDocumentHeader().getFinancialDocumentDescription());
        assertEquals(sponsorCode, doc.getSponsorCode());
        assertEquals(title, doc.getTitle());
        assertEquals(requestedStartDateInitial, doc.getRequestedStartDateInitial().toString());
        assertEquals(requestedEndDateInitial, doc.getRequestedEndDateInitial().toString());
        assertEquals(proposalTypeCode, doc.getProposalTypeCode());
    }

    private void validateSpecialReviewLine(HtmlForm kualiForm, String prefix, String paramList) throws Exception {
        String[] params = paramList.split(";");
        assertEquals(params[0], getFieldValue(kualiForm, TEXT_INPUT, prefix + ".applicationDate"));
        assertEquals(params[1], getFieldValue(kualiForm, TEXT_INPUT, prefix + ".approvalDate"));
        assertEquals(params[2], getFieldValue(kualiForm, TEXT_INPUT, prefix + ".protocolNumber"));
        assertEquals(params[3], getFieldValue(kualiForm, SELECTED_INPUT, prefix + ".specialReviewCode"));
        assertEquals(params[4], getFieldValue(kualiForm, SELECTED_INPUT, prefix + ".approvalTypeCode"));

        // comments - textarea
        assertEquals(params[5] + " \n line2", getFieldValue(kualiForm, TEXT_AREA, prefix + ".comments"));
    }

    private HtmlPage setSpecialReviewLine(HtmlPage htmlPage, HtmlForm kualiForm, String paramList) throws Exception {
        String[] params = paramList.split(";");
        // in "application date; approval date; protocol#; special review code; approval type; comments" order
        setFieldValue(kualiForm, TEXT_INPUT, "newPropSpecialReview.applicationDate", params[0]);
        setFieldValue(kualiForm, TEXT_INPUT, "newPropSpecialReview.approvalDate", params[1]);
        setFieldValue(kualiForm, TEXT_INPUT, "newPropSpecialReview.protocolNumber", params[2]);
        setFieldValue(kualiForm, SELECTED_INPUT, "newPropSpecialReview.specialReviewCode", params[3], 13);
        setFieldValue(kualiForm, SELECTED_INPUT, "newPropSpecialReview.approvalTypeCode", params[4], 6);

        // comments - textarea
        setFieldValue(kualiForm, TEXT_AREA, "newPropSpecialReview.comments", params[5]);

        final HtmlPage page = clickButton(htmlPage, kualiForm,
                "methodToCall.updateTextArea.((#newPropSpecialReview.comments:proposalDevelopmentSpecialReview:Comments#))",
                IMAGE_INPUT);
        final HtmlForm form = (HtmlForm) page.getForms().get(0);
        assertEquals(params[5], getFieldValue(form, TEXT_AREA, "newPropSpecialReview.comments"));
        setFieldValue(form, TEXT_AREA, "newPropSpecialReview.comments", params[5] + " \n line2");
        return clickButton(page, form, "methodToCall.postTextAreaToParent", IMAGE_INPUT);

    }

    /**
     * This method extracts the error message (if any) from the html page as text.
     * @param pageAsText text of the html page response to extract the error message from
     * @return error message from the page
     */
    private String extractErrorMessage(String pageAsText) {
        String errorMessage = "Errors were found: ";
        int index1 = pageAsText.indexOf("error");
        if (index1 > -1) {
            int index2 = pageAsText.indexOf("Document Overview");
            if (index2 > -1) {
                errorMessage += pageAsText.substring(index1, index2);
            } else {
                errorMessage += pageAsText.substring(index1);
            }
        }
        return errorMessage;
    }

}
