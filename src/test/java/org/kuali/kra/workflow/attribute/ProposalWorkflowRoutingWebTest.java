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
package org.kuali.kra.workflow.attribute;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.KraKEWXmlDataLoaderLifecycle;
import org.kuali.kra.infrastructure.TestUtilities;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.web.ProposalDevelopmentWebTestBase;
import org.kuali.rice.core.lifecycle.Lifecycle;
import org.kuali.rice.kns.UserSession;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.kns.util.ErrorMap;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.workflow.service.KualiWorkflowDocument;
import org.kuali.rice.test.lifecycles.SQLDataLoaderLifecycle;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlCheckBoxInput;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlHiddenInput;
import com.gargoylesoftware.htmlunit.html.HtmlImageInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;


public class ProposalWorkflowRoutingWebTest extends ProposalDevelopmentWebTestBase {
    protected static final String KEY_PERSONNEL_LINK_NAME = "keyPersonnel.x";
    protected static final String PROPOSAL_PAGE_LINK_NAME = "actions.x";
    private static final String RADIO_FIELD_VALUE = "Y";
    private static final String CREDIT_SPLIT_VALUE = "100.00";
    private static final int IMAGE_INPUT = 4;
    private static final int SUBMIT_INPUT_BY_NAME = 5;
    private static final int SUBMIT_INPUT_BY_VALUE = 6;
    private static final int CHECK_BOX_INPUT = 7;
    private DocumentService documentService = null;
    private Lifecycle customKEWLifecycle = null;
    private static final String CUSTOM_DATA_LINK_NAME = "methodToCall.headerTab.headerDispatch.save.navigateTo.customData.x";
    private static final String QUESTIONS_LINK_NAME = "methodToCall.headerTab.headerDispatch.save.navigateTo.questions.x";
    private static final String GRADUATE_STUDENT_COUNT_ID = "customAttributeValues(id4)";
    private static final String BILLING_ELEMENT_ID = "customAttributeValues(id1)";
    private static final String BUTTON_SAVE = "save";
    
     @Before
    public void setUp() throws Exception {
        super.setUp();
        transactionalLifecycle.stop();
        new SQLDataLoaderLifecycle("classpath:sql/dml/load_users.sql", ";").start();
        customKEWLifecycle = new KraKEWXmlDataLoaderLifecycle("classpath:kew/xml/Routing");
        customKEWLifecycle.start();
        transactionalLifecycle.start();
        setProposalDevelopmentPage(buildProposalDevelopmentPage());
        GlobalVariables.setUserSession(new UserSession("quickstart"));
        documentService = KNSServiceLocator.getDocumentService();
    }  

    @After
    public void tearDown() throws Exception {


        super.tearDown();
        customKEWLifecycle = new KraKEWXmlDataLoaderLifecycle("classpath:kew/xml");
        customKEWLifecycle.start();
        GlobalVariables.setErrorMap(new ErrorMap());
        stopLifecycles(this.perTestLifeCycles);
        logAfterRun();
    }

    @Test
    public void testproposalworkflowRouting() throws Exception {

        Calendar c = Calendar.getInstance(); 
        c.add(Calendar.DAY_OF_MONTH, 1);
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy"); 
        String sponsorDeadlineDate = sdf.format(c.getTime()); 
        HtmlPage proposaldevelopmentPage = getProposalDevelopmentPage();
        setDefaultRequiredFields(proposaldevelopmentPage);
        setFieldValue(proposaldevelopmentPage,"document.deadlineDate",sponsorDeadlineDate);
        HtmlForm proposaldevform = (HtmlForm) proposaldevelopmentPage.getForms().get(0);
        final HtmlHiddenInput documentNumber = (HtmlHiddenInput) proposaldevform.getInputByName("document.documentHeader.documentNumber");
        HtmlPage KeyPersonnelpage = clickOnTab(proposaldevelopmentPage, KEY_PERSONNEL_LINK_NAME);
        assertTrue(KeyPersonnelpage.asText().contains("Document was successfully saved"));
        KeyPersonnelpage=lookup(KeyPersonnelpage, "org.kuali.kra.bo.Person", "personId", "000000001");
        assertEquals("Terry Durkin", getFieldValue(KeyPersonnelpage, "newProposalPerson.fullName"));
        setFieldValue(KeyPersonnelpage,"newProposalPerson.proposalPersonRoleId", "PI");
        KeyPersonnelpage = clickOn(KeyPersonnelpage, "methodToCall.insertProposalPerson");
        setFieldValue(KeyPersonnelpage,"document.proposalPersons[0].proposalPersonYnq[0].answer",RADIO_FIELD_VALUE);
        setFieldValue(KeyPersonnelpage,"document.proposalPersons[0].proposalPersonYnq[1].answer",RADIO_FIELD_VALUE);
        setFieldValue(KeyPersonnelpage,"document.proposalPersons[0].proposalPersonYnq[2].answer",RADIO_FIELD_VALUE);
        KeyPersonnelpage=lookup(KeyPersonnelpage, "org.kuali.kra.bo.Person", "personId", "000000006");
        assertEquals("Andrew Slusar", getFieldValue(KeyPersonnelpage, "newProposalPerson.fullName"));
        setFieldValue(KeyPersonnelpage,"newProposalPerson.proposalPersonRoleId", "KP");
        KeyPersonnelpage = clickOn(KeyPersonnelpage, "methodToCall.insertProposalPerson");
        KeyPersonnelpage = clickOn(KeyPersonnelpage, "methodToCall.addCertificationQuestion.document.proposalPersons[1].line1");
        setFieldValue(KeyPersonnelpage,"document.proposalPersons[1].proposalPersonYnq[0].answer",RADIO_FIELD_VALUE);
        setFieldValue(KeyPersonnelpage,"document.proposalPersons[1].proposalPersonYnq[1].answer",RADIO_FIELD_VALUE);
        setFieldValue(KeyPersonnelpage,"document.proposalPersons[1].proposalPersonYnq[2].answer",RADIO_FIELD_VALUE);
        setFieldValue(KeyPersonnelpage,"document.proposalPersons[1].projectRole","test");
        setFieldValue(KeyPersonnelpage,"document.investigator[0].creditSplit[0].credit",CREDIT_SPLIT_VALUE);
        setFieldValue(KeyPersonnelpage,"document.investigator[0].creditSplit[1].credit",CREDIT_SPLIT_VALUE);
        setFieldValue(KeyPersonnelpage,"document.investigator[0].creditSplit[2].credit",CREDIT_SPLIT_VALUE);
        setFieldValue(KeyPersonnelpage,"document.investigator[0].creditSplit[3].credit",CREDIT_SPLIT_VALUE);
        setFieldValue(KeyPersonnelpage,"document.investigator[0].unit[0].creditSplit[0].credit",CREDIT_SPLIT_VALUE);
        setFieldValue(KeyPersonnelpage,"document.investigator[0].unit[0].creditSplit[1].credit",CREDIT_SPLIT_VALUE);
        setFieldValue(KeyPersonnelpage,"document.investigator[0].unit[0].creditSplit[2].credit",CREDIT_SPLIT_VALUE);
        setFieldValue(KeyPersonnelpage,"document.investigator[0].unit[0].creditSplit[3].credit",CREDIT_SPLIT_VALUE);
              
        // set up required custom attributes
        HtmlPage customDataPage = clickOn(KeyPersonnelpage, CUSTOM_DATA_LINK_NAME);
        assertContains(customDataPage,TestUtilities.GROUP_NAME_1);
        assertContains(customDataPage,TestUtilities.GROUP_NAME_2);  
        assertContains(customDataPage,TestUtilities.GROUP_NAME_3);
  
        setFieldValue(customDataPage, GRADUATE_STUDENT_COUNT_ID, TestUtilities.GRADUATE_STUDENT_COUNT_VALUE);
        setFieldValue(customDataPage, BILLING_ELEMENT_ID, TestUtilities.BILLING_ELEMENT_VALUE);

        HtmlPage proposalPage = saveAndSearchDoc(customDataPage);
        proposalPage = clickOn(proposalPage, QUESTIONS_LINK_NAME);
        for(int i=0; i<4; i++) {
            String fieldName = "document.developmentProposalList[0].proposalYnq[" + i + "].answer";
            String explanation = "document.developmentProposalList[0].proposalYnq[" + i + "].explanation";
            String reviewDate = "document.developmentProposalList[0].proposalYnq[" + i + "].reviewDate";
            setFieldValue(proposalPage, fieldName, RADIO_FIELD_VALUE);
            setFieldValue(proposalPage, explanation, "test comments");
            setFieldValue(proposalPage, reviewDate, sponsorDeadlineDate);
        }
        proposalPage = clickOn(proposalPage, BUTTON_SAVE);

        HtmlPage submitPage = clickOnTab(proposalPage, ACTIONS_LINK_NAME);
        HtmlForm form1 = (HtmlForm) submitPage.getForms().get(0);
        final HtmlPage confirmationPage = clickButton(submitPage, form1, "methodToCall.route", IMAGE_INPUT);

        assertNotNull(confirmationPage);
        assertTrue(confirmationPage.asText().contains("Document was successfully submitted"));

        GlobalVariables.setUserSession(null);
        GlobalVariables.setUserSession(new UserSession("jtester"));
        final WebClient newWebClient = new WebClient(BrowserVersion.INTERNET_EXPLORER_7_0);
        final URL url = new URL("http://localhost:" + getPort() + "/kra-dev/");
        final HtmlPage pageAfterLogin = login(newWebClient, url, "kew/ActionList.do", "jtester");
        assertNotNull(pageAfterLogin);

        HtmlAnchor docLink = getAnchorFromPage(pageAfterLogin, "docId=" + documentNumber.getDefaultValue());
        assertNotNull(docLink);
        HtmlPage docDisplay = (HtmlPage) docLink.click();
        assertNotNull(docDisplay);

        HtmlPage approvePage = clickOnTab(docDisplay, ACTIONS_LINK_NAME);
        HtmlForm form2 = (HtmlForm) approvePage.getForms().get(0);
        final HtmlPage approvalConfirmationPage = clickButton(approvePage, form2, "methodToCall.approve", IMAGE_INPUT);
        assertNotNull(approvalConfirmationPage);

        GlobalVariables.setUserSession(new UserSession("tdurkin"));
        final WebClient newWebClient1 = new WebClient(BrowserVersion.INTERNET_EXPLORER_7_0);
        final URL url1 = new URL("http://localhost:" + getPort() + "/kra-dev/");
        final HtmlPage pageAfterLogin1 = login(newWebClient1, url1, "kew/ActionList.do", "tdurkin");
        assertNotNull(pageAfterLogin1);
            
        
        HtmlAnchor docLink1 = getAnchorFromPage(pageAfterLogin1, "docId=" + documentNumber.getDefaultValue());
        assertNotNull(docLink1);
        HtmlPage docDisplay1 = (HtmlPage) docLink1.click();
        assertNotNull(docDisplay1);
        HtmlPage PIApprovePage = clickOnTab(docDisplay1, ACTIONS_LINK_NAME); 
        final HtmlPage rejectConfirmationPage = clickOn(PIApprovePage, "methodToCall.reject");
        assertNotNull(rejectConfirmationPage);
        ProposalDevelopmentDocument savedDocument = (ProposalDevelopmentDocument) documentService
        .getByDocumentHeaderId(documentNumber.getDefaultValue());
        assertNotNull(savedDocument);
        KualiWorkflowDocument workflowDoc = savedDocument.getDocumentHeader().getWorkflowDocument();
        assertNotNull(workflowDoc);
        String currentnodename=workflowDoc.getCurrentRouteNodeNames();
        assertEquals(currentnodename,"ProposalPersons");
        
        
    }



    private HtmlPage clickButton(HtmlPage page, HtmlForm htmlForm, String buttonName, int type) throws Exception {
        String completeButtonName = getImageTagName(page, buttonName);
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
    private String getImageTagName(HtmlPage page, String uniqueNamePrefix) {
        int idx1 = page.asXml().indexOf(uniqueNamePrefix);
        int idx2 = page.asXml().indexOf("\"", idx1);
        return page.asXml().substring(idx1, idx2).replace("&amp;", "&").replace("((&lt;&gt;))", "((<>))");
    }

    private HtmlPage login(WebClient webClient, URL url, String loginLocation, String userid) throws Exception {
        final HtmlPage page1 = (HtmlPage) webClient.getPage(url);
        assertEquals("Kuali Portal Index", page1.getTitleText());

        // LOGIN
        final HtmlPage page2 = (HtmlPage) webClient.getPage(url + loginLocation);
        setFieldValue(page2, "username", userid);

        // Get the form that we are dealing with and within that form,
        // find the submit button and the field that we want to change.
        final HtmlForm form = (HtmlForm) page2.getForms().get(0);

        // Now submit the form by clicking the button and get back the
        // second page.
        return clickButton(page2, form, "Login", SUBMIT_INPUT_BY_VALUE);

    }

    private HtmlAnchor getAnchorFromPage(HtmlPage page, String stringToMatch) {
        List<HtmlAnchor> anchors = page.getAnchors();
        HtmlAnchor target = null;
        for (HtmlAnchor anchor : anchors) {
            if (anchor.getHrefAttribute().contains(stringToMatch)) {
                target = anchor;
            }
        }

        return target;
    }


}
