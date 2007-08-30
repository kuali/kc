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
import java.util.ArrayList;
import java.util.List;

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

import com.gargoylesoftware.htmlunit.CollectingAlertHandler;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlHiddenInput;
import com.gargoylesoftware.htmlunit.html.HtmlImageInput;
import com.gargoylesoftware.htmlunit.html.HtmlOption;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextArea;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

/**
 * This class tests the KraServiceLocator
 */
public class ProposalDevelopmentDocumentWebTest extends KraTestBase {

    private static final Logger LOG = Logger.getLogger(ProposalDevelopmentDocumentWebTest.class);
    private boolean isDataLoaded = false;
    private DocumentService documentService = null;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        documentService = KNSServiceLocator.getDocumentService();
        GlobalVariables.setUserSession(new UserSession("quickstart"));
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
        documentService = null;
        GlobalVariables.setUserSession(null);
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
        assertEquals("Kuali Portal Index", page1.getTitleText() );

        LOG.info("getting page2");
        // LOGIN
        final HtmlPage page2 = (HtmlPage)webClient.getPage(url + "proposalDevelopmentProposal.do?methodToCall=docHandler&command=initiate&docTypeName=ProposalDevelopmentDocument");

        // Get the form that we are dealing with and within that form,
        // find the submit button and the field that we want to change.
        LOG.info("getting page2 form");
        final HtmlForm form = (HtmlForm) page2.getForms().get(0);
        LOG.info("getting page2 Login button");
        final HtmlSubmitInput button
            = (HtmlSubmitInput) form.getInputByValue("Login");

        // Now submit the form by clicking the button and get back the
        // second page.
        LOG.info("getting page3");
        final HtmlPage page3 = (HtmlPage) button.click();
        assertEquals("Kuali :: Proposal Development Document", page3.getTitleText() );

        // test document overview help link
        LOG.info("getting page4");
        final HtmlPage page4 = (HtmlPage)webClient.getPage(url + "kr/help.do?methodToCall=getAttributeHelpText&businessObjectClassName=org.kuali.core.bo.DocumentHeader&attributeName=financialDocumentDescription");
        assertEquals("Kuali :: Kuali Help", page4.getTitleText() );

        // test proposal development document attribute help link
        LOG.info("getting page5");
        final HtmlPage page5 = (HtmlPage)webClient.getPage(url + "kr/help.do?methodToCall=getAttributeHelpText&businessObjectClassName=org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument&attributeName=sponsorCode");
        assertEquals("Kuali :: Kuali Help", page5.getTitleText() );
    }

    @Test public void testSaveProposalDevelopmentDocumentWeb() throws Exception {
        final WebClient webClient = new WebClient();
        final URL url = new URL("http://localhost:" + getPort() + "/kra-dev/");
        final HtmlPage page1 = (HtmlPage)webClient.getPage(url);

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

        ProposalDevelopmentDocument doc = (ProposalDevelopmentDocument)getDocument(documentNumber.getDefaultValue());
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
 
 
   
    @Test
    public void testOrganizationLocationPanel() throws Exception {
        final WebClient webClient = new WebClient();
        final URL url = new URL("http://localhost:" + getPort() + "/kra-dev/");

        final HtmlPage page3 = login(webClient, url, "proposalDevelopmentProposal.do?methodToCall=docHandler&command=initiate&docTypeName=ProposalDevelopmentDocument");
        assertEquals("Kuali :: Proposal Development Document", page3.getTitleText());

        final HtmlForm kualiForm = (HtmlForm) page3.getForms().get(0);
        setupProposalDevelopmentDocumentRequiredFields(kualiForm);
        
        // start to set up organization/location panel
        
        // organization
        StringBuffer orgLookupTagName=new StringBuffer();
        orgLookupTagName.append("methodToCall.performLookup.(!!org.kuali.kra.bo.Organization!!).").append(
                "(((organizationId:document.organizationId,congressionalDistrict:document.organization.congressionalDistrict").append(
                ",organizationName:document.organization.organizationName,rolodex.firstName:document.organization.rolodex.firstName").append(
                ",rolodex.lastName:document.organization.rolodex.lastName,rolodex.addressLine1:document.organization.rolodex.addressLine1").append(
                ",rolodex.addressLine2:document.organization.rolodex.addressLine2,rolodex.addressLine3:document.organization.rolodex.addressLine3").append(
                ",rolodex.city:document.organization.rolodex.city,rolodex.state:document.organization.rolodex.state))).((##)).((<>)).(([])).((**)).((^^)).((&&)).((//)).((~~)).anchor");

        
        final HtmlPage page43 = lookup(webClient, (HtmlImageInput) kualiForm.getInputByName(orgLookupTagName.toString()), "000001", "proposalDevelopmentProposal.do?document.organization","organizationId");
        final HtmlForm form1 = (HtmlForm) page43.getForms().get(0);
        final HtmlHiddenInput organizationId1 = (HtmlHiddenInput) form1.getInputByName("document.organizationId");
        assertEquals("000001", organizationId1.getValueAttribute());
        assertTrue(page43.asText().contains("Congressional District: Eighth"));
        assertTrue(page43.asText().contains("Performing Organization Id: University"));
        assertTrue(page43.asText().contains("Applicant Organization: University"));
        assertTrue(page43.asText().contains("Authorized Representative Name & Address: First Name"));
        // default prop location created
        final HtmlTextInput defaultLocation = (HtmlTextInput) form1.getInputByName("document.propLocations[0].location");
        assertEquals("University", defaultLocation.getValueAttribute());
        // delete default line
        final HtmlImageInput deleteLocationButton1 = (HtmlImageInput) form1.getInputByName("methodToCall.deleteLocation.line0.");
        final HtmlPage page44 = (HtmlPage) deleteLocationButton1.click();
        final HtmlForm form41=(HtmlForm)page44.getForms().get(0);
        // save without location line
        final HtmlImageInput saveButton1 = (HtmlImageInput) form41.getInputByName("methodToCall.save");
        // the default location line will be recreated
        final HtmlPage page45 = (HtmlPage) saveButton1.click();
        assertEquals("Kuali :: Proposal Development Document", page45.getTitleText());
        final HtmlForm form42 = (HtmlForm) page45.getForms().get(0);
        // one of the following to check save is OK
        assertTrue(page45.asText().contains("error(s) found"));
        assertFalse(page45.asText().contains("Document was successfully saved"));

         // performingorg lookup
        
        String lookupTagName="methodToCall.performLookup.(!!org.kuali.kra.bo.Organization!!).(((organizationId:document.performingOrganizationId,organizationName:document.performingOrganization.organizationName))).((##)).((<>)).(([])).((**)).((^^)).((&&)).((//)).((~~)).anchor";
        final HtmlPage page52 = lookup(webClient, (HtmlImageInput) form42.getInputByName(lookupTagName), "000002", "proposalDevelopmentProposal.do?document.performingOrganization","organizationId");
        final HtmlForm form2 = (HtmlForm) page52.getForms().get(0);
        final HtmlHiddenInput performingOrganizationId = (HtmlHiddenInput) form2.getInputByName("document.performingOrganizationId");
        assertEquals("000002", performingOrganizationId.getValueAttribute());
        assertTrue(page52.asText().contains("Performing Organization Id: California Institute of Technology"));
        //California Institute of Technology
        
        // proplocations
        // set up and add first line
        final HtmlTextInput newLocation = (HtmlTextInput) kualiForm.getInputByName("newPropLocation.location");
        newLocation.setValueAttribute("location 1");
        
        // test rolodex lookup lookup
        StringBuffer rolodexIdName=new StringBuffer();
        rolodexIdName.append("methodToCall.performLookup.(!!org.kuali.kra.bo.Rolodex!!).").append(
                "(((rolodexId:newPropLocation.rolodexId,postalCode:newPropLocation.rolodex.postalCode").append(
                ",addressLine1:newPropLocation.rolodex.addressLine1").append(
                ",addressLine2:newPropLocation.rolodex.addressLine2,addressLine3:newPropLocation.rolodex.addressLine3").append(
                ",city:newPropLocation.rolodex.city,state:newPropLocation.rolodex.state))).((##)).((<>)).(([])).((**)).((^^)).((&&)).((//)).((~~)).anchor");
        final HtmlPage page53 = lookup(webClient, (HtmlImageInput) form2.getInputByName(rolodexIdName.toString()), "1728", "proposalDevelopmentProposal.do?newPropLocation.rolodex.","rolodexId");
        final HtmlForm form3 = (HtmlForm) page53.getForms().get(0);
        final HtmlHiddenInput rolodexId1 = (HtmlHiddenInput) form3.getInputByName("newPropLocation.rolodexId");
        assertEquals("1728", rolodexId1.getValueAttribute());
        assertTrue(page53.asText().contains("National Center for Environmental Research and Quality Assurance"));

        final HtmlImageInput addLocationButton = (HtmlImageInput)form3.getInputByName("methodToCall.addLocation");
        final HtmlPage page54 = (HtmlPage) addLocationButton.click();
        final HtmlForm form4 = (HtmlForm)page54.getForms().get(0);

        final HtmlHiddenInput rolodexId21 = (HtmlHiddenInput) form4.getInputByName("newPropLocation.rolodexId");
        assertEquals("0", rolodexId21.getValueAttribute());
        // how to check newlocation address is empty
        final HtmlHiddenInput rolodexId22 = (HtmlHiddenInput) form4.getInputByName("document.propLocations[1].rolodexId");
        assertEquals("1728", rolodexId22.getValueAttribute());
        assertTrue(page54.asText().contains("National Center for Environmental Research and Quality Assurance"));

        // 2nd line
        // set up and add 2nd line
        final HtmlTextInput newLocation1 = (HtmlTextInput) form4.getInputByName("newPropLocation.location");
        newLocation1.setValueAttribute("location 2");
        
        // test rolodex lookup 
        final HtmlPage page6 = lookup(webClient, (HtmlImageInput) form4.getInputByName(rolodexIdName.toString()), "1727", "proposalDevelopmentProposal.do?newPropLocation.rolodex.","rolodexId");
        final HtmlForm form5 = (HtmlForm) page6.getForms().get(0);
        final HtmlHiddenInput rolodexId24 = (HtmlHiddenInput) form5.getInputByName("newPropLocation.rolodexId");
        assertEquals("1727", rolodexId24.getValueAttribute());
        assertTrue(page6.asText().contains("Organization 1126"));

        final HtmlImageInput addLocationButton1 = (HtmlImageInput)form5.getInputByName("methodToCall.addLocation");
        final HtmlPage page61 = (HtmlPage) addLocationButton1.click();
        final HtmlForm form6 = (HtmlForm)page61.getForms().get(0);

        final HtmlHiddenInput rolodexId25 = (HtmlHiddenInput) form6.getInputByName("newPropLocation.rolodexId");
        assertEquals("0", rolodexId25.getValueAttribute());
        // how to check newlocation address is empty
        final HtmlHiddenInput rolodexId26 = (HtmlHiddenInput) form6.getInputByName("document.propLocations[2].rolodexId");
        assertEquals("1727", rolodexId26.getValueAttribute());
        assertTrue(page61.asText().contains("Organization 1126"));

        // clearaddress
        final HtmlImageInput clearAddressButton = (HtmlImageInput) form6.getInputByName("methodToCall.clearAddress.line1.");
        final HtmlPage page62 = (HtmlPage) clearAddressButton.click();
        final HtmlForm form7 = (HtmlForm)page62.getForms().get(0);
        final HtmlHiddenInput rolodexId61 = (HtmlHiddenInput) form7.getInputByName("document.propLocations[1].rolodexId");
        assertEquals("0", rolodexId61.getValueAttribute());
        assertFalse(page62.asText().contains("National Center for Environmental Research and Quality Assurance"));
        // verify other fields too? location, proplocations[1] ?
        
        // delete lines
        final HtmlImageInput deleteLocationButton = (HtmlImageInput) form7.getInputByName("methodToCall.deleteLocation.line1.");
        final HtmlPage page63 = (HtmlPage) deleteLocationButton.click();
        final HtmlForm form8 = (HtmlForm)page63.getForms().get(0);
        final HtmlHiddenInput rolodexId62 = (HtmlHiddenInput) form8.getInputByName("document.propLocations[1].rolodexId");
        assertEquals("1727", rolodexId62.getValueAttribute());
        assertTrue(page63.asText().contains("Organization 1126"));
        // how to check only one left
        final HtmlImageInput saveButton = (HtmlImageInput) form8.getInputByName("methodToCall.save");        
        final HtmlPage page7 = (HtmlPage) saveButton.click();
        assertEquals("Kuali :: Proposal Development Document", page6.getTitleText());
        final HtmlForm form9 = (HtmlForm) page7.getForms().get(0);
        // one of the following to check save is OK
        assertFalse(page7.asText().contains("error(s) found"));
        assertTrue(page7.asText().contains("Document was successfully saved"));
        // verify for is still ok
        final HtmlHiddenInput organizationId2 = (HtmlHiddenInput) form9.getInputByName("document.organizationId");
        assertEquals("000001", organizationId2.getValueAttribute());
        assertTrue(page7.asText().contains("Congressional District: Eighth"));
        assertTrue(page7.asText().contains("Applicant Organization: University"));
        assertTrue(page7.asText().contains("Authorized Representative Name & Address: First Name"));
        final HtmlHiddenInput performingOrganizationId1 = (HtmlHiddenInput) form9.getInputByName("document.performingOrganizationId");
        assertEquals("000002", performingOrganizationId1.getValueAttribute());
        assertTrue(page7.asText().contains("Performing Organization Id: California Institute of Technology"));

        final HtmlHiddenInput rolodexId63 = (HtmlHiddenInput) form9.getInputByName("document.propLocations[1].rolodexId");
        assertEquals("1727", rolodexId63.getValueAttribute());
        assertTrue(page7.asText().contains("Organization 1126"));
        final HtmlHiddenInput rolodexId64 = (HtmlHiddenInput) form9.getInputByName("document.propLocations[0].rolodexId");
        assertEquals("0", rolodexId64.getValueAttribute());
        final HtmlTextInput location0 = (HtmlTextInput) form9.getInputByName("document.propLocations[0].location");
        assertEquals("University", location0.getValueAttribute());

        // verify DB
        final HtmlHiddenInput documentNumber = (HtmlHiddenInput) form4.getInputByName("document.documentHeader.documentNumber");

        ProposalDevelopmentDocument doc = (ProposalDevelopmentDocument)getDocument(documentNumber.getDefaultValue());
          assertNotNull(doc);
    
          assertEquals("1", doc.getActivityTypeCode());
          assertEquals("000002", doc.getOwnedByUnit());
          assertEquals("ProposalDevelopmentDocumentWebTest test", doc.getDocumentHeader().getFinancialDocumentDescription());
          assertEquals("123456", doc.getSponsorCode());
          assertEquals("project title", doc.getTitle());
          assertEquals("2007-08-14", doc.getRequestedStartDateInitial().toString());
          assertEquals("2007-08-21", doc.getRequestedEndDateInitial().toString());
          assertEquals("2", doc.getProposalTypeCode());
          assertEquals("000001", doc.getOrganizationId());
          assertEquals("000002", doc.getPerformingOrganizationId());
          assertEquals("University", doc.getPropLocations().get(0).getLocation());
          assertEquals(0, doc.getPropLocations().get(0).getRolodexId());
          assertEquals("location 2", doc.getPropLocations().get(1).getLocation());
          assertEquals(1727, doc.getPropLocations().get(1).getRolodexId());

    }
    


    
   
    @Test
    public void testSpecialReviewPage() throws Exception {
        final WebClient webClient = new WebClient();
        final URL url = new URL("http://localhost:" + getPort() + "/kra-dev/");

        final HtmlPage page3 = login(webClient, url, "proposalDevelopmentProposal.do?methodToCall=docHandler&command=initiate&docTypeName=ProposalDevelopmentDocument");
        assertEquals("Kuali :: Proposal Development Document", page3.getTitleText());

        final HtmlForm kualiForm = (HtmlForm) page3.getForms().get(0);
        setupProposalDevelopmentDocumentRequiredFields(kualiForm);
        HtmlSubmitInput specialReviewButton= (HtmlSubmitInput)kualiForm.getInputByName("methodToCall.headerTab.headerDispatch.save.navigateTo.specialReview.x");
        final HtmlPage page4 = (HtmlPage)specialReviewButton.click();
        assertTrue(page4.asText().contains("Document was successfully saved"));
        assertTrue(page4.asText().contains("Approval Status Protocol # Application Date Approval Date Comments"));
        HtmlForm form1 = (HtmlForm)page4.getForms().get(0);
        
        webClient.setJavaScriptEnabled(false);
        final HtmlPage page5 = setSpecialReviewLine(form1,"08/01/2007;;123;1;2;comment1");
        
        final HtmlForm form2 = (HtmlForm) page5.getForms().get(0);
        final HtmlTextArea textArea1 = (HtmlTextArea) form2.getTextAreasByName("newPropSpecialReview.comments").get(0);
        assertEquals("comment1 \n line2", textArea1.getText());
        final HtmlImageInput addSpecialReviewButton = (HtmlImageInput)form2.getInputByName("methodToCall.addSpecialReview");
        final HtmlPage page51 = (HtmlPage) addSpecialReviewButton.click();
        final HtmlForm form3 = (HtmlForm)page51.getForms().get(0);
        validateSpecialReviewLine(form3, "document.propSpecialReviews[0]","08/01/2007;;123;1;2;comment1");
        // 2nd line
        final HtmlPage page52 = setSpecialReviewLine(form3,"08/02/2007;;456;2;3;comment2");
        final HtmlForm form4 = (HtmlForm)page52.getForms().get(0);
        final HtmlTextArea textArea2 = (HtmlTextArea) form4.getTextAreasByName("newPropSpecialReview.comments").get(0);
        assertEquals("comment2 \n line2", textArea2.getText());
        final HtmlImageInput addSpecialReviewButton1 = (HtmlImageInput)form4.getInputByName("methodToCall.addSpecialReview");
        final HtmlPage page53 = (HtmlPage) addSpecialReviewButton1.click();
        final HtmlForm form5 = (HtmlForm)page53.getForms().get(0);
        validateSpecialReviewLine(form5, "document.propSpecialReviews[0]","08/01/2007;;123;1;2;comment1");
        validateSpecialReviewLine(form5, "document.propSpecialReviews[1]","08/02/2007;;456;2;3;comment2");

        // delete special review line 0
        final HtmlImageInput deleteSpecialReviewButton = (HtmlImageInput)form5.getInputByName("methodToCall.deleteSpecialReview.line0.");
        final HtmlPage page6 = (HtmlPage) deleteSpecialReviewButton.click();
        final HtmlForm form6 = (HtmlForm)page6.getForms().get(0);
        validateSpecialReviewLine(form6, "document.propSpecialReviews[0]","08/02/2007;;456;2;3;comment2");
       // save
        final HtmlImageInput saveButton = (HtmlImageInput) form6.getInputByName("methodToCall.save");        
        final HtmlPage page7 = (HtmlPage) saveButton.click();
        assertEquals("Kuali :: Proposal Development Document", page6.getTitleText());
        final HtmlForm form7 = (HtmlForm) page7.getForms().get(0);
        // one of the following to check save is OK
        assertFalse(page7.asText().contains("error(s) found"));
        assertTrue(page7.asText().contains("Document was successfully saved"));
        validateSpecialReviewLine(form6, "document.propSpecialReviews[0]","08/02/2007;;456;2;3;comment2");

    }
    
    @Test
    public void testDeliveryInfoPanel() throws Exception {
        final WebClient webClient = new WebClient();
        final URL url = new URL("http://localhost:" + getPort() + "/kra-dev/");
        final HtmlPage page3 = login(webClient, url, "proposalDevelopmentProposal.do?methodToCall=docHandler&command=initiate&docTypeName=ProposalDevelopmentDocument");
        assertEquals("Kuali :: Proposal Development Document", page3.getTitleText());

        final HtmlForm kualiForm = (HtmlForm) page3.getForms().get(0);
        setupProposalDevelopmentDocumentRequiredFields(kualiForm);


        // check dropdowns
        final HtmlSelect mailBy = (HtmlSelect) kualiForm.getSelectByName("document.mailBy");
        assertEquals(3, mailBy.getOptionSize());
        mailBy.setSelectedAttribute("1", true);

        final HtmlSelect mailType = (HtmlSelect) kualiForm.getSelectByName("document.mailType");
        assertEquals(4, mailType.getOptionSize());
        mailType.setSelectedAttribute("2", true);

        // input fields
        final HtmlTextInput mailAccountNumber = (HtmlTextInput) kualiForm.getInputByName("document.mailAccountNumber");
        mailAccountNumber.setValueAttribute("10-0001");
        final HtmlTextInput numberOfCopies = (HtmlTextInput) kualiForm.getInputByName("document.numberOfCopies");
        numberOfCopies.setValueAttribute("2");

        // test mailing address lookup
        StringBuffer mailingAddressIdName=new StringBuffer();
        mailingAddressIdName.append("methodToCall.performLookup.(!!org.kuali.kra.bo.Rolodex!!).").append(
                "(((rolodexId:document.mailingAddressId,firstName:document.rolodex.firstName").append(
                ",lastName:document.rolodex.lastName,addressLine1:document.rolodex.addressLine1").append(
                ",addressLine2:document.rolodex.addressLine2,addressLine3:document.rolodex.addressLine3").append(
                ",city:document.rolodex.city,state:document.rolodex.state))).((##)).((<>)).(([])).((**)).((^^)).((&&)).((//)).((~~)).anchor");
        final HtmlPage page43 = lookup(webClient, (HtmlImageInput) kualiForm.getInputByName(mailingAddressIdName.toString()), "1728", "proposalDevelopmentProposal.do?document.rolodex.","rolodexId");
        final HtmlImageInput mailingAddressLookupButton = (HtmlImageInput) kualiForm.getInputByName(mailingAddressIdName.toString());

        final HtmlForm form1 = (HtmlForm) page43.getForms().get(0);
        final HtmlHiddenInput mailingAddressId = (HtmlHiddenInput) form1.getInputByName("document.mailingAddressId");
        assertEquals("1728", mailingAddressId.getValueAttribute());
        assertTrue(page43.asText().contains("National Center for Environmental Research and Quality Assurance"));

    // mail description textarea
        final HtmlTextArea mailDescription = (HtmlTextArea) form1.getTextAreasByName("document.mailDescription").get(0);
        mailDescription.setText("mail description");
        final HtmlImageInput textAreaButton = (HtmlImageInput) form1.getInputByName("methodToCall.updateTextArea.((#document.mailDescription:proposalDevelopmentProposal:Mail Description#))");

        webClient.setJavaScriptEnabled(false);
        final HtmlPage page5 = (HtmlPage) textAreaButton.click();
        final HtmlForm form2 = (HtmlForm) page5.getForms().get(0);
        final HtmlTextArea textArea = (HtmlTextArea) form2.getTextAreasByName("document.mailDescription").get(0);
        assertEquals("mail description", textArea.getText());
        textArea.setText("mail description \n line2");

        final HtmlImageInput saveTextAreaButton = (HtmlImageInput) form2.getInputByName("methodToCall.postTextAreaToParent");
        final HtmlPage page51 = (HtmlPage) saveTextAreaButton.click();
        final HtmlForm form3 = (HtmlForm) page51.getForms().get(0);
        final HtmlTextArea textArea1 = (HtmlTextArea) form3.getTextAreasByName("document.mailDescription").get(0);
        assertEquals("mail description \n line2", textArea1.getText());


        // save and check
        final HtmlImageInput saveButton = (HtmlImageInput) form3.getInputByName("methodToCall.save");        
        final HtmlPage page6 = (HtmlPage) saveButton.click();
        assertEquals("Kuali :: Proposal Development Document", page6.getTitleText());
        final HtmlForm form4 = (HtmlForm) page6.getForms().get(0);
        // one of the following to check save is OK
        assertFalse(page6.asText().contains("error(s) found"));
        assertTrue(page6.asText().contains("Document was successfully saved"));
        
        final HtmlSelect mailType1 = (HtmlSelect) form4.getSelectByName("document.mailType");
        assertEquals("2", ((HtmlOption)(mailType1.getSelectedOptions().get(0))).getValueAttribute());
        final HtmlSelect mailBy1 = (HtmlSelect) form4.getSelectByName("document.mailBy");
        assertEquals("1", ((HtmlOption)(mailBy1.getSelectedOptions().get(0))).getValueAttribute());

        final HtmlTextInput mailAccountNumber1 = (HtmlTextInput) form4.getInputByName("document.mailAccountNumber");
        assertEquals("10-0001",mailAccountNumber1.getValueAttribute());
        final HtmlTextInput numberOfCopies1 = (HtmlTextInput) form4.getInputByName("document.numberOfCopies");
        assertEquals("2",numberOfCopies1.getValueAttribute());

        
        final HtmlHiddenInput mailingAddressId1 = (HtmlHiddenInput) form4.getInputByName("document.mailingAddressId");
        assertEquals("1728", mailingAddressId1.getValueAttribute());
        assertTrue(page6.asText().contains("National Center for Environmental Research and Quality Assurance"));

        final HtmlTextArea textArea2 = (HtmlTextArea) form4.getTextAreasByName("document.mailDescription").get(0);
        assertEquals("mail description \n line2", textArea2.getText());

        final HtmlHiddenInput documentNumber = (HtmlHiddenInput) form4.getInputByName("document.documentHeader.documentNumber");

        ProposalDevelopmentDocument doc = (ProposalDevelopmentDocument)getDocument(documentNumber.getDefaultValue());
          assertNotNull(doc);
    
          assertEquals("1", doc.getActivityTypeCode());
          assertEquals("000002", doc.getOwnedByUnit());
          assertEquals("ProposalDevelopmentDocumentWebTest test", doc.getDocumentHeader().getFinancialDocumentDescription());
          assertEquals("123456", doc.getSponsorCode());
          assertEquals("project title", doc.getTitle());
          assertEquals("2007-08-14", doc.getRequestedStartDateInitial().toString());
          assertEquals("2007-08-21", doc.getRequestedEndDateInitial().toString());
          assertEquals("2", doc.getProposalTypeCode());
          assertEquals("1", doc.getMailBy());
          assertEquals("2", doc.getMailType());
          assertEquals(1728, doc.getMailingAddressId());
          assertEquals("10-0001", doc.getMailAccountNumber());
          assertEquals("2", doc.getNumberOfCopies());
          assertEquals("mail description \n line2", doc.getMailDescription());


    }
    
    
    
    @Test
    public void testExpandedTextArea() throws Exception {
        String fieldName="document.title";
        String fieldText="project title";
        String methodToCall="methodToCall.updateTextArea.((#"+fieldName+":proposalDevelopmentProposal:Project Title#))";
        //textAreaPop(fieldName, fieldText, methodToCall,true);
        final HtmlPage page5=textAreaPop(fieldName, fieldText, methodToCall,false);
        final HtmlForm form2 = (HtmlForm) page5.getForms().get(0);
        final HtmlTextArea textArea1 = (HtmlTextArea) form2.getTextAreasByName(fieldName).get(0);
        assertEquals(fieldText+" \n line2", textArea1.getText());

    }
    
    private HtmlPage textAreaPop(String fieldName,String fieldText,String methodToCall,boolean scriptEnabled) throws Exception {
        final WebClient webClient = new WebClient();
        final URL url = new URL("http://localhost:" + getPort() + "/kra-dev/");
        final HtmlPage page3 = login(webClient, url, "proposalDevelopmentProposal.do?methodToCall=docHandler&command=initiate&docTypeName=ProposalDevelopmentDocument");
        assertEquals("Kuali :: Proposal Development Document", page3.getTitleText());
        
        // collection of alerts from js
        final List collectedAlerts = new ArrayList();
        webClient.setAlertHandler( new CollectingAlertHandler(collectedAlerts) );

        final HtmlForm kualiForm = (HtmlForm) page3.getForms().get(0);
        final HtmlTextArea title = (HtmlTextArea) kualiForm.getTextAreasByName(fieldName).get(0);
        title.setText(fieldText);
        final HtmlImageInput textAreaButton = (HtmlImageInput) kualiForm.getInputByName(methodToCall);
        //js or non-js
        if (!scriptEnabled) {
           webClient.setJavaScriptEnabled(false);
        }
        final HtmlPage page4 = (HtmlPage) textAreaButton.click();
        final HtmlForm form1 = (HtmlForm) page4.getForms().get(0);
        final HtmlTextArea textArea = (HtmlTextArea) form1.getTextAreasByName(fieldName).get(0);
        assertEquals(fieldText, textArea.getText());
        textArea.setText(fieldText+" \n line2");

        final HtmlImageInput saveTextAreaButton = (HtmlImageInput) form1.getInputByName("methodToCall.postTextAreaToParent");
        return (HtmlPage) saveTextAreaButton.click();
        //final HtmlForm form2 = (HtmlForm) page5.getForms().get(0);
        //final HtmlTextArea textArea1 = (HtmlTextArea) form2.getTextAreasByName(fieldName).get(0);
        //assertEquals(fieldText+" \n line2", textArea1.getText());



    }



    private HtmlPage login(WebClient webClient, URL url, String loginLocation) throws Exception {
        final HtmlPage page1 = (HtmlPage) webClient.getPage(url);
        assertEquals("Kuali Portal Index", page1.getTitleText());

        // LOGIN
        final HtmlPage page2 = (HtmlPage) webClient.getPage(url+loginLocation);

        // Get the form that we are dealing with and within that form,
        // find the submit button and the field that we want to change.
        final HtmlForm form = (HtmlForm) page2.getForms().get(0);
        final HtmlSubmitInput button = (HtmlSubmitInput) form.getInputByValue("Login");

        // Now submit the form by clicking the button and get back the
        // second page.
        return (HtmlPage) button.click();

    }


    // should be able to make one lookup method for all single value lookup
    private HtmlPage lookup(WebClient webClient, HtmlImageInput lookupButton,  String selectedRolodexId, String returnProperty, String searchField) throws Exception {
        final URL url = new URL("http://localhost:" + getPort() + "/kra-dev/");
 
        final HtmlPage page41 = (HtmlPage)lookupButton.click();
        final HtmlForm lookForm = (HtmlForm) page41.getForms().get(0);
        final HtmlTextInput organizationId = (HtmlTextInput) lookForm.getInputByName(searchField);
        organizationId.setValueAttribute(selectedRolodexId);
        final HtmlImageInput searchButton = (HtmlImageInput) lookForm.getInputByName("methodToCall.search");
        final HtmlPage page42 = (HtmlPage) searchButton.click();
        assertTrue(page42.asText().contains("Return value "+selectedRolodexId));
        int idx1=page42.asXml().indexOf(returnProperty);
        int idx2=page42.asXml().indexOf("anchor=topOfForm", idx1);
        String returnPath=page42.asXml().substring(idx1, idx2+16).replace("&amp;", "&");
        return (HtmlPage) webClient.getPage(url+returnPath);

    }
    
    private void setupProposalDevelopmentDocumentRequiredFields(HtmlForm kualiForm) throws Exception {

        final HtmlTextInput description = (HtmlTextInput) kualiForm
                .getInputByName("document.documentHeader.financialDocumentDescription");
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

    }

    private void validateSpecialReviewLine(HtmlForm kualiForm, String prefix, String paramList) throws Exception {
        String[] params=paramList.split(";");
        final HtmlTextInput applicationDate = (HtmlTextInput) kualiForm.getInputByName(prefix+".applicationDate");
        assertEquals(params[0], applicationDate.getValueAttribute());
        final HtmlTextInput approvalDate = (HtmlTextInput) kualiForm.getInputByName(prefix+".approvalDate");
        assertEquals(params[1], approvalDate.getValueAttribute());
        final HtmlTextInput protocolNumber = (HtmlTextInput) kualiForm.getInputByName(prefix+".protocolNumber");
        assertEquals(params[2], protocolNumber.getValueAttribute());

        final HtmlSelect type = (HtmlSelect) kualiForm.getSelectByName(prefix+".specialReviewCode");
        assertEquals(params[3],((HtmlOption) type.getSelectedOptions().get(0)).getValueAttribute());
        final HtmlSelect approvalStatus = (HtmlSelect) kualiForm.getSelectByName(prefix+".approvalTypeCode");
        assertEquals(params[4],((HtmlOption) approvalStatus.getSelectedOptions().get(0)).getValueAttribute());
        
        // comments textarea
        final HtmlTextArea comments = (HtmlTextArea) kualiForm.getTextAreasByName(prefix+".comments").get(0);
        assertEquals(params[5]+" \n line2", comments.getText());
     }
 
    private HtmlPage setSpecialReviewLine(HtmlForm kualiForm, String paramList) throws Exception {
        String[] params=paramList.split(";");
        // in "application date; approval date; protocol#; special review code; approval type; comments" order
        final HtmlTextInput applicationDate = (HtmlTextInput) kualiForm.getInputByName("newPropSpecialReview.applicationDate");
        applicationDate.setValueAttribute(params[0]);
        final HtmlTextInput approvalDate = (HtmlTextInput) kualiForm.getInputByName("newPropSpecialReview.approvalDate");
        approvalDate.setValueAttribute(params[1]);
        final HtmlTextInput protocolNumber = (HtmlTextInput) kualiForm.getInputByName("newPropSpecialReview.protocolNumber");
        protocolNumber.setValueAttribute(params[2]);

        final HtmlSelect type = (HtmlSelect) kualiForm.getSelectByName("newPropSpecialReview.specialReviewCode");
        assertEquals(13, type.getOptionSize());
        type.setSelectedAttribute(params[3], true);
        final HtmlSelect approvalStatus = (HtmlSelect) kualiForm.getSelectByName("newPropSpecialReview.approvalTypeCode");
        assertEquals(6, approvalStatus.getOptionSize());
        approvalStatus.setSelectedAttribute(params[4], true);
        
        // comments textarea
        final HtmlTextArea comments = (HtmlTextArea) kualiForm.getTextAreasByName("newPropSpecialReview.comments").get(0);
        comments.setText(params[5]);
        final HtmlImageInput textAreaButton = (HtmlImageInput) kualiForm.getInputByName("methodToCall.updateTextArea.((#newPropSpecialReview.comments:proposalDevelopmentSpecialReview:Comments#))");

        final HtmlPage page5 = (HtmlPage) textAreaButton.click();
        final HtmlForm form2 = (HtmlForm) page5.getForms().get(0);
        final HtmlTextArea textArea = (HtmlTextArea) form2.getTextAreasByName("newPropSpecialReview.comments").get(0);
        assertEquals(params[5], textArea.getText());
        textArea.setText(params[5]+" \n line2");
        final HtmlImageInput saveTextAreaButton = (HtmlImageInput) form2.getInputByName("methodToCall.postTextAreaToParent");
        return (HtmlPage) saveTextAreaButton.click();

    }
    

}
