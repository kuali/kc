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
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.bo.NarrativeAttachment;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonBiography;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonBiographyAttachment;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.rice.KNSServiceLocator;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlCheckBoxInput;
import com.gargoylesoftware.htmlunit.html.HtmlFileInput;
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
public class ProposalDevelopmentDocumentWebTest extends ProposalDevelopmentWebTestBase {

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
    private static final int FILE_INPUT = 8;
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

    /**
     * Verify that all the Help links on the web page go to the Kuali Help Web Page.
     * This will test the help links on all the panels on the main Proposal Development page.
     * @throws Exception
     */
    @Test
    public void testHelpLinks() throws Exception {
        HtmlPage proposalDevelopmentPage = getProposalDevelopmentPage();
        this.checkHelpLinks(proposalDevelopmentPage);
    }

    @Test
    public void testSaveProposalDevelopmentDocumentWeb() throws Exception {
        final WebClient webClient = new WebClient();
        final URL url = new URL("http://localhost:" + getPort() + "/kra-dev/");
        final HtmlPage page3 = login(webClient, url, "proposalDevelopmentProposal.do?methodToCall=docHandler&command=initiate&docTypeName=ProposalDevelopmentDocument");
        assertEquals("Kuali :: Proposal Development Document", page3.getTitleText());

        final HtmlForm kualiForm = (HtmlForm) page3.getForms().get(0);
        setupProposalDevelopmentDocumentRequiredFields(kualiForm, "ProposalDevelopmentDocumentWebTest test", "005770", "project title", "08/14/2007", "08/21/2007", "1", "1", DEFAULT_PROPOSAL_OWNED_BY_UNIT);

        final HtmlHiddenInput documentNumber = (HtmlHiddenInput) kualiForm.getInputByName("document.documentHeader.documentNumber");

        final HtmlPage page4 = clickButton(page3, kualiForm,"methodToCall.save",IMAGE_INPUT);
        assertEquals("Kuali :: Proposal Development Document", page4.getTitleText());

        String page4AsText = page4.asText();
        String errorMessage = extractErrorMessage(page4AsText);

        assertFalse(errorMessage, page4AsText.contains(ERRORS_FOUND_ON_PAGE));

        ProposalDevelopmentDocument doc = (ProposalDevelopmentDocument) documentService.getByDocumentHeaderId(documentNumber.getDefaultValue());

        assertNotNull(doc);

        verifySavedRequiredFields(doc, "1", DEFAULT_PROPOSAL_OWNED_BY_UNIT, "ProposalDevelopmentDocumentWebTest test", "005770", "project title", "2007-08-14", "2007-08-21", "1");
    }

    @Test
    public void testSaveProposalDevelopmentDocumentNotNewWeb() throws Exception {
        final WebClient webClient = new WebClient();
        final URL url = new URL("http://localhost:" + getPort() + "/kra-dev/");
        final HtmlPage page3 = login(webClient, url, "proposalDevelopmentProposal.do?methodToCall=docHandler&command=initiate&docTypeName=ProposalDevelopmentDocument");
        assertEquals("Kuali :: Proposal Development Document", page3.getTitleText() );

        final HtmlForm kualiForm = (HtmlForm) page3.getForms().get(0);
        final HtmlImageInput saveButton = (HtmlImageInput) kualiForm.getInputByName("methodToCall.save");

        setupProposalDevelopmentDocumentRequiredFields(kualiForm, "ProposalDevelopmentDocumentWebTest test", "005770", "project title", "08/14/2007", "08/21/2007", "1", "2", DEFAULT_PROPOSAL_OWNED_BY_UNIT);

        final HtmlTextInput continuedFrom = (HtmlTextInput) kualiForm.getInputByName("document.continuedFrom");
        continuedFrom.setValueAttribute("123456");

        final HtmlHiddenInput documentNumber = (HtmlHiddenInput) kualiForm.getInputByName("document.documentHeader.documentNumber");

        final HtmlPage page4 = (HtmlPage) saveButton.click();
        assertEquals("Kuali :: Proposal Development Document", page4.getTitleText() );

        String page4AsText = page4.asText();
        String errorMessage = extractErrorMessage(page4AsText);

        assertFalse(errorMessage, page4AsText.contains(ERRORS_FOUND_ON_PAGE));

        ProposalDevelopmentDocument doc = (ProposalDevelopmentDocument) documentService.getByDocumentHeaderId(documentNumber.getDefaultValue());
        assertNotNull(doc);

        verifySavedRequiredFields(doc, "1", DEFAULT_PROPOSAL_OWNED_BY_UNIT, "ProposalDevelopmentDocumentWebTest test", "005770", "project title", "2007-08-14", "2007-08-21", "2");
        assertEquals("123456", doc.getContinuedFrom());
    }

    @Test
    public void testSaveProposalDevelopmentDocumentWithErrorsWeb() throws Exception {
        final WebClient webClient = new WebClient();
        final URL url = new URL("http://localhost:" + getPort() + "/kra-dev/");

        final HtmlPage page3 = login(webClient, url, "proposalDevelopmentProposal.do?methodToCall=docHandler&command=initiate&docTypeName=ProposalDevelopmentDocument");
        assertEquals("Kuali :: Proposal Development Document", page3.getTitleText());

        final HtmlForm kualiForm = (HtmlForm) page3.getForms().get(0);
        final HtmlImageInput saveButton = (HtmlImageInput) kualiForm.getInputByName("methodToCall.save");

        setupProposalDevelopmentDocumentRequiredFields(kualiForm, "ProposalDevelopmentDocumentWebTest test", "005770", "project title", "08/14/2007", "08/21/2007", "1", "2", DEFAULT_PROPOSAL_OWNED_BY_UNIT);

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

        setupProposalDevelopmentDocumentRequiredFields(kualiForm, "ProposalDevelopmentDocumentWebTest test", "005770", "project title", "08/14/2007", "08/21/2007", "1", "", DEFAULT_PROPOSAL_OWNED_BY_UNIT);

        final HtmlPage page4 = (HtmlPage) saveButton.click();
        assertEquals("Kuali :: Proposal Development Document", page4.getTitleText() );

        String page4AsText = page4.asText();
        String errorMessage = extractErrorMessage(page4AsText);

        assertTrue(errorMessage, page4AsText.contains(ERRORS_FOUND_ON_PAGE));

    }

    /**
     * 
     * Test organization/location panel on proposal page
     * @throws Exception
     */
    @Test
    public void testOrganizationLocationPanel() throws Exception {
        final WebClient webClient = new WebClient();
        final URL url = new URL("http://localhost:" + getPort() + "/kra-dev/");

        final HtmlPage page3 = login(webClient, url,
                "proposalDevelopmentProposal.do?methodToCall=docHandler&command=initiate&docTypeName=ProposalDevelopmentDocument");
        assertEquals("Kuali :: Proposal Development Document", page3.getTitleText());

        final HtmlForm kualiForm = (HtmlForm) page3.getForms().get(0);
        setupProposalDevelopmentDocumentRequiredFields(kualiForm, "ProposalDevelopmentDocumentWebTest test", "005770", "project title", "08/14/2007", "08/21/2007", "1", "1", DEFAULT_PROPOSAL_OWNED_BY_UNIT);

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
        assertEquals("University", getFieldValue(form1, TEXT_INPUT, "document.proposalLocations[0].location"));
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
        assertEquals("1728", getFieldValue(form6, HIDDEN_INPUT, "document.proposalLocations[0].rolodexId"));
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
        assertEquals("1727", getFieldValue(form8, HIDDEN_INPUT, "document.proposalLocations[1].rolodexId"));
        assertTrue(page11.asText().contains("Organization 1126"));

        // clearaddress
        final HtmlPage page12 = clickButton(page11, form8, "methodToCall.clearAddress.line0.", IMAGE_INPUT);
        final HtmlForm form9 = (HtmlForm) page12.getForms().get(0);
        assertEquals("0", getFieldValue(form9, HIDDEN_INPUT, "document.proposalLocations[0].rolodexId"));
        assertFalse(page12.asText().contains("National Center for Environmental Research and Quality Assurance"));
        // verify other fields too? location, proplocations[1] ?

        // delete lines
        final HtmlPage page13 = clickButton(page12, form9, "methodToCall.deleteLocation.line0.", IMAGE_INPUT);
        final HtmlForm form10 = (HtmlForm) page13.getForms().get(0);
        assertEquals("1727", getFieldValue(form10, HIDDEN_INPUT, "document.proposalLocations[0].rolodexId"));
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

        assertEquals("1727", getFieldValue(form11, HIDDEN_INPUT, "document.proposalLocations[0].rolodexId"));
        assertTrue(page14.asText().contains("Organization 1126"));

        // verify DB
        final HtmlHiddenInput documentNumber = (HtmlHiddenInput) form6.getInputByName("document.documentHeader.documentNumber");

        ProposalDevelopmentDocument doc = (ProposalDevelopmentDocument) getDocument(documentNumber.getDefaultValue());
        assertNotNull(doc);

        verifySavedRequiredFields(doc, "1", DEFAULT_PROPOSAL_OWNED_BY_UNIT, "ProposalDevelopmentDocumentWebTest test", "005770", "project title", "2007-08-14", "2007-08-21", "1");
        assertEquals("000001", doc.getOrganizationId());
        assertEquals("000002", doc.getPerformingOrganizationId());
        assertEquals("location 2", doc.getProposalLocations().get(0).getLocation());
        assertEquals(new Integer(1727), doc.getProposalLocations().get(0).getRolodexId());

    }

    /**
     * 
     * Test delivery info panel on proposal page
     * @throws Exception
     */
    @Test
    public void testDeliveryInfoPanel() throws Exception {
        final WebClient webClient = new WebClient();
        final URL url = new URL("http://localhost:" + getPort() + "/kra-dev/");
        final HtmlPage page3 = login(webClient, url,
                "proposalDevelopmentProposal.do?methodToCall=docHandler&command=initiate&docTypeName=ProposalDevelopmentDocument");
        assertEquals("Kuali :: Proposal Development Document", page3.getTitleText());

        final HtmlForm kualiForm = (HtmlForm) page3.getForms().get(0);
        setupProposalDevelopmentDocumentRequiredFields(kualiForm, "ProposalDevelopmentDocumentWebTest test", "005770", "project title", "08/14/2007", "08/21/2007", "1", "1", DEFAULT_PROPOSAL_OWNED_BY_UNIT);


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
        verifySavedRequiredFields(doc, "1", DEFAULT_PROPOSAL_OWNED_BY_UNIT, "ProposalDevelopmentDocumentWebTest test", "005770", "project title", "2007-08-14", "2007-08-21", "1");

        assertEquals("1", doc.getMailBy());
        assertEquals("2", doc.getMailType());
        assertEquals(new Integer(1728), doc.getMailingAddressId());
        assertEquals("10-0001", doc.getMailAccountNumber());
        assertEquals("2", doc.getNumberOfCopies());
        assertEquals("mail description \n line2", doc.getMailDescription());
    }

    /**
     * 
     * Test special review page.
     * @throws Exception
     */
    @Test
    public void testSpecialReviewPage() throws Exception {
        final WebClient webClient = new WebClient();
        final URL url = new URL("http://localhost:" + getPort() + "/kra-dev/");

        final HtmlPage page3 = login(webClient, url,
                "proposalDevelopmentProposal.do?methodToCall=docHandler&command=initiate&docTypeName=ProposalDevelopmentDocument");
        assertEquals("Kuali :: Proposal Development Document", page3.getTitleText());

        final HtmlForm kualiForm = (HtmlForm) page3.getForms().get(0);
        setupProposalDevelopmentDocumentRequiredFields(kualiForm, "ProposalDevelopmentDocumentWebTest test", "005770", "project title", "08/14/2007", "08/21/2007", "1", "1", DEFAULT_PROPOSAL_OWNED_BY_UNIT);
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

    /**
     * 
     * Test institutional attachments.  
     * @throws Exception
     */
    @Test
    public void testInstituteAttachment() throws Exception {
        final WebClient webClient = new WebClient();
        final URL url = new URL("http://localhost:" + getPort() + "/kra-dev/");

        final HtmlPage pageAfterLogin = login(webClient, url,
                "proposalDevelopmentProposal.do?methodToCall=docHandler&command=initiate&docTypeName=ProposalDevelopmentDocument");
        assertEquals("Kuali :: Proposal Development Document", pageAfterLogin.getTitleText());

        final HtmlForm kualiForm = (HtmlForm) pageAfterLogin.getForms().get(0);
        setupProposalDevelopmentDocumentRequiredFields(kualiForm, "ProposalDevelopmentDocumentWebTest test", "005770", "project title", "08/14/2007", "08/21/2007", "1", "1", DEFAULT_PROPOSAL_OWNED_BY_UNIT);
        final HtmlPage abstractAttachmentPage = clickButton(pageAfterLogin, kualiForm, "methodToCall.headerTab.headerDispatch.save.navigateTo.abstractsAttachments.x",
                SUBMIT_INPUT_BY_NAME);
        assertTrue(abstractAttachmentPage.asText().contains("Document was successfully saved"));
        // really is in abstracts & attachments page
        assertTrue(abstractAttachmentPage.asText().contains("Add Institutional Attachments &nbsp Timestamp Author"));
        HtmlForm form1 = (HtmlForm) abstractAttachmentPage.getForms().get(0);
        //webClient.setJavaScriptEnabled(false);
        String fileName=getFileName();
        final HtmlPage pageAfterAddAttachment =setInstituteAttachmentLine(abstractAttachmentPage,form1,fileName+";59");
        final HtmlForm form2 = (HtmlForm) pageAfterAddAttachment.getForms().get(0);
        assertTrue(pageAfterAddAttachment.asText().contains("Institutional Attachment 1 workflow-workspace.html"));

        // multiple attachment is not allowed for this type

        final HtmlPage pageWithSameAttachmentType =setInstituteAttachmentLine(pageAfterAddAttachment,form2,fileName+";59");
        final HtmlForm form3 = (HtmlForm) pageWithSameAttachmentType.getForms().get(0);
        assertTrue(pageWithSameAttachmentType.asText().contains("Errors found in this Section: Institute attachment with Attachment Type 'Institutional Attachment 1' already exists"));

        // delete attachment
        final HtmlPage pageAfterDeleteAttachment = clickButton(pageWithSameAttachmentType, form3, "methodToCall.deleteInstitutionalAttachment.line0.anchor", IMAGE_INPUT);
        final HtmlForm form4 = (HtmlForm) pageAfterDeleteAttachment.getForms().get(0);
        assertFalse(pageAfterDeleteAttachment.asText().contains("Institutional Attachment 1 workflow-workspace.html"));

        // add line back
        final HtmlPage pageWithAttachment =setInstituteAttachmentLine(pageAfterDeleteAttachment,form4,fileName+";59");
        final HtmlForm form5 = (HtmlForm) pageWithAttachment.getForms().get(0);
        assertTrue(pageWithAttachment.asText().contains("Institutional Attachment 1 workflow-workspace.html"));

        // save
        final HtmlPage pageSave = clickButton(pageWithAttachment, form5, "methodToCall.save", IMAGE_INPUT);
        final HtmlForm formAfterSave = (HtmlForm) pageSave.getForms().get(0);

        assertFalse(pageSave.asText().contains(ERRORS_FOUND_ON_PAGE));
        assertTrue(pageSave.asText().contains("Document was successfully saved"));
        assertTrue(pageSave.asText().contains("Institutional Attachment 1 workflow-workspace.html"));

        // try to view file - only work for 'text/html' file 
        final HtmlPage attachmentFilePage = clickButton(pageSave, formAfterSave, "methodToCall.viewInstitutionalAttachment.line0.anchor", IMAGE_INPUT);
        assertTrue(attachmentFilePage.asText().contains("Workflow Workspace This area is provided as a workspace for workflow activities"));

        final HtmlHiddenInput documentNumber = (HtmlHiddenInput) kualiForm.getInputByName("document.documentHeader.documentNumber");
        ProposalDevelopmentDocument doc = (ProposalDevelopmentDocument) documentService.getByDocumentHeaderId(documentNumber.getDefaultValue());
        assertNotNull(doc);
        verifySavedRequiredFields(doc, "1", DEFAULT_PROPOSAL_OWNED_BY_UNIT, "ProposalDevelopmentDocumentWebTest test", "005770", "project title", "2007-08-14", "2007-08-21", "1");
        Narrative narrative=(Narrative)doc.getInstitutes().get(0);
        narrative.refreshReferenceObject("narrativeAttachmentList");
        NarrativeAttachment narrativeAttachment=(NarrativeAttachment)narrative.getNarrativeAttachmentList().get(0);
        assertNotNull(narrativeAttachment);
        assertEquals("workflow-workspace.html", narrativeAttachment.getFileName());
        assertEquals("text/html", narrativeAttachment.getContentType());

        
        
    }


    /**
     * 
     * Test personnel biography attachments.
     * @throws Exception
     */
//    @Test
//    public void testPersonnelAttachment() throws Exception {
//        final WebClient webClient = new WebClient();
//        final URL url = new URL("http://localhost:" + getPort() + "/kra-dev/");
//
//        final HtmlPage pageAfterLogin = login(webClient, url,
//                "proposalDevelopmentProposal.do?methodToCall=docHandler&command=initiate&docTypeName=ProposalDevelopmentDocument");
//        assertEquals("Kuali :: Proposal Development Document", pageAfterLogin.getTitleText());
//
//        final HtmlForm kualiForm = (HtmlForm) pageAfterLogin.getForms().get(0);
//        setupProposalDevelopmentDocumentRequiredFields(kualiForm, "ProposalDevelopmentDocumentWebTest test", "123456", "project title", "08/14/2007", "08/21/2007", "1", "1", DEFAULT_PROPOSAL_OWNED_BY_UNIT);
//        // TODO :proposaldevelopmentaction.abstractsAttachments has a temporary set up for proposal person if it is not set up 
//
//        
//        final HtmlPage abstractAttachmentPage = clickButton(pageAfterLogin, kualiForm, "methodToCall.headerTab.headerDispatch.save.navigateTo.abstractsAttachments.x",
//                SUBMIT_INPUT_BY_NAME);
//        final HtmlForm form1 = (HtmlForm) abstractAttachmentPage.getForms().get(0);
//        assertTrue(abstractAttachmentPage.asText().contains("Document was successfully saved"));
//        assertTrue(abstractAttachmentPage.asText().contains("Personnel Attachments &nbsp Timestamp Author * Person"));
//
//        // add new personnel attachment line
//        //String fileName="C:/java/projects/kra_project/src/main/webapp/en/htdocs/workflow-workspace.html";
//        String fileName=getFileName();
//        final HtmlPage pageAfterAddAttachment =setPersonnelAttachmentLine(abstractAttachmentPage,form1,"3;1;desc;"+fileName);
//        final HtmlForm form2 = (HtmlForm) pageAfterAddAttachment.getForms().get(0);
//        assertTrue(pageAfterAddAttachment.asText().contains("Durkin,Terry Budget Details"));
//
//
//        // delete attachment
//        final HtmlPage pageAfterDeleteAttachment = clickButton(pageAfterAddAttachment, form2, "methodToCall.deletePersonnelAttachment.line0.anchor", IMAGE_INPUT);
//        final HtmlForm form4 = (HtmlForm) pageAfterDeleteAttachment.getForms().get(0);
//        assertFalse(pageAfterDeleteAttachment.asText().contains("Durkin,Terry Budget Details"));
//
//        // add line back
//        final HtmlPage pageWithAttachment =setPersonnelAttachmentLine(pageAfterDeleteAttachment,form4,"3;1;desc;"+fileName);
//        final HtmlForm form5 = (HtmlForm) pageWithAttachment.getForms().get(0);
//        assertTrue(pageWithAttachment.asText().contains("Durkin,Terry Budget Details"));
//
//        // save
//        final HtmlPage pageSave = clickButton(pageWithAttachment, form5, "methodToCall.save", IMAGE_INPUT);
//        final HtmlForm formAfterSave = (HtmlForm) pageSave.getForms().get(0);
//
//        assertFalse(pageSave.asText().contains(ERRORS_FOUND_ON_PAGE));
//        assertTrue(pageSave.asText().contains("Document was successfully saved"));
//        assertTrue(pageSave.asText().contains("Durkin,Terry Budget Details"));
//        
//        // try to view file - only work for html file now.  The otehr content type will cause castexception - unexpectedpage
//        final HtmlPage attachmentFilePage = clickButton(pageSave, formAfterSave, "methodToCall.viewPersonnelAttachment.line0.anchor", IMAGE_INPUT);
//        assertTrue(attachmentFilePage.asText().contains("Workflow Workspace This area is provided as a workspace for workflow activities"));
//
//        
//        final HtmlHiddenInput documentNumber = (HtmlHiddenInput) kualiForm.getInputByName("document.documentHeader.documentNumber");
//
//        ProposalDevelopmentDocument doc = (ProposalDevelopmentDocument) documentService.getByDocumentHeaderId(documentNumber.getDefaultValue());
//        assertNotNull(doc);
//        verifySavedRequiredFields(doc, "1", DEFAULT_PROPOSAL_OWNED_BY_UNIT, "ProposalDevelopmentDocumentWebTest test", "123456", "project title", "2007-08-14", "2007-08-21", "1");
//        ProposalPersonBiography personBio=(ProposalPersonBiography)doc.getPropPersonBios().get(0);
//        personBio.refreshReferenceObject("personnelAttachmentList");
//        ProposalPersonBiographyAttachment personnelAttachment=(ProposalPersonBiographyAttachment)personBio.getPersonnelAttachmentList().get(0);
//        assertNotNull(personnelAttachment);
//        assertEquals("workflow-workspace.html", personnelAttachment.getFileName());
//        assertEquals("text/html", personnelAttachment.getContentType());
//
//    }
//

    

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
        
        // set username field for authentication
        setFieldValue(page2, "username", "quickstart");

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
            case FILE_INPUT:
                final HtmlFileInput file = (HtmlFileInput) htmlForm.getInputByName(fieldName);
                file.setValueAttribute(value);
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
        setFieldValue(kualiForm, SELECTED_INPUT, "document.ownedByUnitNumber", ownedByUnit, 4);
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
    private void verifySavedRequiredFields(ProposalDevelopmentDocument doc, String activityType, String ownedByUnitNumber, String description, String sponsorCode, String title, String requestedStartDateInitial, String requestedEndDateInitial, String proposalTypeCode) throws WorkflowException {
        assertEquals(activityType, doc.getActivityTypeCode());
        assertEquals(ownedByUnitNumber, doc.getOwnedByUnitNumber());
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

    private HtmlPage setInstituteAttachmentLine(HtmlPage htmlPage, HtmlForm kualiForm, String paramList) throws Exception {
        String[] params = paramList.split(";");
        setFieldValue(kualiForm, FILE_INPUT, "newInstitute.narrativeFile", params[0]);
        //setFieldValue(kualiForm, SELECTED_INPUT, "newInstitute.moduleStatusCode", params[1],3);
        setFieldValue(kualiForm, SELECTED_INPUT, "newInstitute.institutionalAttachmentTypeCode", params[1],3);


        return clickButton(htmlPage, kualiForm, "methodToCall.addInstitutionalAttachment.anchor", IMAGE_INPUT);

    }
    private HtmlPage setPersonnelAttachmentLine(HtmlPage htmlPage, HtmlForm kualiForm, String paramList) throws Exception {
        String[] params = paramList.split(";");
        setFieldValue(kualiForm, SELECTED_INPUT, "newPropPersonBio.documentTypeCode", params[0],6);
        setFieldValue(kualiForm, SELECTED_INPUT, "newPropPersonBio.proposalPersonNumber", params[1],3);
        setFieldValue(kualiForm, TEXT_AREA, "newPropPersonBio.description", params[2]);
        setFieldValue(kualiForm, FILE_INPUT, "newPropPersonBio.personnelAttachmentFile", params[3]);


        return clickButton(htmlPage, kualiForm, "methodToCall.addPersonnelAttachment.anchor", IMAGE_INPUT);

    }

    
    /**
     * 
     * Get file name for institute and personnel attachment.
     */
    private static String getFileName() {
        String userDir = System.getProperty("user.dir");
        String path = userDir + "/src/main/webapp/en/htdocs/";
        return path+"workflow-workspace.html";
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
