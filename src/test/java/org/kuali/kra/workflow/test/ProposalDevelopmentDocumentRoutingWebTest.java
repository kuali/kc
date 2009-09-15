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
package org.kuali.kra.workflow.test;

import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.KraKEWXmlDataLoaderLifecycle;
import org.kuali.kra.infrastructure.TestUtilities;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.web.ProposalDevelopmentWebTestBase;
import org.kuali.rice.core.lifecycle.Lifecycle;
import org.kuali.rice.kew.dto.ActionRequestDTO;
import org.kuali.rice.kew.dto.DocumentDetailDTO;
import org.kuali.rice.kew.dto.NetworkIdDTO;
import org.kuali.rice.kew.dto.ReportCriteriaDTO;
import org.kuali.rice.kew.engine.node.KeyValuePair;
import org.kuali.rice.kew.service.WorkflowInfo;
import org.kuali.rice.kew.util.KEWConstants;
import org.kuali.rice.kns.UserSession;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.kns.util.ErrorMap;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.workflow.service.KualiWorkflowDocument;
import org.kuali.rice.test.lifecycles.SQLDataLoaderLifecycle;
import org.springframework.core.io.ClassPathResource;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlCheckBoxInput;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlHiddenInput;
import com.gargoylesoftware.htmlunit.html.HtmlImageInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;

/**
 * This class tests the KraServiceLocator
 */
public class ProposalDevelopmentDocumentRoutingWebTest extends ProposalDevelopmentWebTestBase {
    private static final int IMAGE_INPUT = 4;
    private static final int SUBMIT_INPUT_BY_NAME = 5;
    private static final int SUBMIT_INPUT_BY_VALUE = 6;
    private static final int CHECK_BOX_INPUT = 7;

    private static final String USERNAME_FIELD_ID = "newProposalUser.username";
    private static final String ROLENAME_FIELD_ID = "newProposalUser.roleName";
    private static final String ADD_BTN_ID = "methodToCall.addProposalUser";

    private static final String APPROVER = "jtester";
    private static final String PROPOSAL_CREATOR = "quickstart";
    private static final String AGGREGATOR_ROLENAME = "Aggregator";
    private static final String VIEWER_ROLENAME = "Viewer";
    protected static final String PERMISSIONS_LINK_NAME = "permissions.x";
    protected static final String KEY_PERSONNEL_LINK_NAME = "keyPersonnel.x";
    private static final String RADIO_FIELD_VALUE = "Y";
    private static final String CREDIT_SPLIT_VALUE = "100.00";
    private static final String WORKFLOW_ADMIN_GROUP_ID = "1";
    private static final String USER_PRINCIPLE_ID = "jtester";
    
    private Lifecycle customKEWLifecycle = null;
    private static final String CUSTOM_DATA_LINK_NAME = "methodToCall.headerTab.headerDispatch.save.navigateTo.customData.x";
    private static final String QUESTIONS_LINK_NAME = "methodToCall.headerTab.headerDispatch.save.navigateTo.questions.x";
    private static final String GRADUATE_STUDENT_COUNT_ID = "customAttributeValues(id4)";
    private static final String BILLING_ELEMENT_ID = "customAttributeValues(id1)";
    private static final String BUTTON_SAVE = "save";

    private File xmlBackupDir = null;
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        transactionalLifecycle.stop();
        
        /* Whatever this is trying to do isn't working - all of the tests run after this one
         * that rely on ProposalDevelopmentDocument/Rules fail.
         * 
         * For now I'm just going to re-run the whole KEWXmlDataLoaderLifecycle in tearDown() instead of the 'revert' directory.
         * There's a Jira to clean this test up - KRACOEUS-2122
         */
        ClassPathResource routingResource1 = new ClassPathResource("kew/xml/ProposalDevelopmentDocument.xml");
        ClassPathResource routingResource2 = new ClassPathResource("kew/xml/ProposalDevelopmentDocumentRules.xml");
        xmlBackupDir = new File(new ClassPathResource("kew/xml/test").getFile(), "revert");
        xmlBackupDir.mkdir();
        
        FileUtils.copyFileToDirectory(routingResource1.getFile(), xmlBackupDir);
        FileUtils.copyFileToDirectory(routingResource2.getFile(), xmlBackupDir);

        new SQLDataLoaderLifecycle("classpath:sql/dml/clear_kew_rules.sql", ";").start();
        customKEWLifecycle = new KraKEWXmlDataLoaderLifecycle("classpath:kew/xml/test");
        customKEWLifecycle.start();
        transactionalLifecycle.start();
        setProposalDevelopmentPage(buildProposalDevelopmentPage());
        GlobalVariables.setUserSession(new UserSession("quickstart"));
        documentService = KNSServiceLocator.getDocumentService();
    }  

    @After
    public void tearDown() throws Exception {
        
        GlobalVariables.setUserSession(null);
        documentService = null;
        customKEWLifecycle.stop();
        customKEWLifecycle = null;
        
        transactionalLifecycle.stop();
        new SQLDataLoaderLifecycle("classpath:sql/dml/clear_kew_rules.sql", ";").start();
        customKEWLifecycle = new KraKEWXmlDataLoaderLifecycle();
        customKEWLifecycle.start();

        FileUtils.deleteDirectory(xmlBackupDir);
        
        GlobalVariables.setErrorMap(new ErrorMap());
        stopLifecycles(this.perTestLifeCycles);
        logAfterRun();
    }


    @Test
    public void testAlternateRoutingWithMultipleApproval() throws Exception {
        HtmlPage proposaldevelopmentPage = getProposalDevelopmentPage();
        setDefaultRequiredFields(proposaldevelopmentPage);
        
        Calendar date = Calendar.getInstance();
        date.add(Calendar.DAY_OF_MONTH, 1); 
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy"); 
        String sponsorDeadlineDate = sdf.format(date.getTime()); 

        //Required Field for PD Submission
        setFieldValue(proposaldevelopmentPage,"document.deadlineDate",sponsorDeadlineDate);
        
        HtmlForm proposalForm = (HtmlForm) proposaldevelopmentPage.getForms().get(0);
        final HtmlHiddenInput documentNumber = (HtmlHiddenInput) proposalForm.getInputByName("document.documentHeader.documentNumber");
        
        //Required for PD Submission
        HtmlPage keyPersonnelpage = clickOnTab(proposaldevelopmentPage, KEY_PERSONNEL_LINK_NAME);
        assertTrue(keyPersonnelpage.asText().contains("Document was successfully saved"));
        keyPersonnelpage = lookup(keyPersonnelpage, "org.kuali.kra.bo.Person", "personId", "000000001");
        assertEquals("Terry Durkin", getFieldValue(keyPersonnelpage, "newProposalPerson.fullName"));
        setFieldValue(keyPersonnelpage,"newProposalPerson.proposalPersonRoleId", "PI");
        keyPersonnelpage = clickOn(keyPersonnelpage, "methodToCall.insertProposalPerson");
        setFieldValue(keyPersonnelpage,"document.proposalPersons[0].proposalPersonYnq[0].answer",RADIO_FIELD_VALUE);
        setFieldValue(keyPersonnelpage,"document.proposalPersons[0].proposalPersonYnq[1].answer",RADIO_FIELD_VALUE);
        setFieldValue(keyPersonnelpage,"document.proposalPersons[0].proposalPersonYnq[2].answer",RADIO_FIELD_VALUE);
        setFieldValue(keyPersonnelpage,"document.investigator[0].creditSplit[0].credit",CREDIT_SPLIT_VALUE);
        setFieldValue(keyPersonnelpage,"document.investigator[0].creditSplit[1].credit",CREDIT_SPLIT_VALUE);
        setFieldValue(keyPersonnelpage,"document.investigator[0].creditSplit[2].credit",CREDIT_SPLIT_VALUE);
        setFieldValue(keyPersonnelpage,"document.investigator[0].creditSplit[3].credit",CREDIT_SPLIT_VALUE);
        setFieldValue(keyPersonnelpage,"document.investigator[0].unit[0].creditSplit[0].credit",CREDIT_SPLIT_VALUE);
        setFieldValue(keyPersonnelpage,"document.investigator[0].unit[0].creditSplit[1].credit",CREDIT_SPLIT_VALUE);
        setFieldValue(keyPersonnelpage,"document.investigator[0].unit[0].creditSplit[2].credit",CREDIT_SPLIT_VALUE);
        setFieldValue(keyPersonnelpage,"document.investigator[0].unit[0].creditSplit[3].credit",CREDIT_SPLIT_VALUE);
        
        //Setting up required Custom Data for PD Submission
        HtmlPage customDataPage = clickOn(keyPersonnelpage, CUSTOM_DATA_LINK_NAME);
        assertContains(customDataPage,TestUtilities.GROUP_NAME_1);
        assertContains(customDataPage,TestUtilities.GROUP_NAME_2);
        assertContains(customDataPage,TestUtilities.GROUP_NAME_3);
        setFieldValue(customDataPage, GRADUATE_STUDENT_COUNT_ID, TestUtilities.GRADUATE_STUDENT_COUNT_VALUE);
        setFieldValue(customDataPage, BILLING_ELEMENT_ID, TestUtilities.BILLING_ELEMENT_VALUE);

        HtmlPage permissionsPage = clickOnTab(customDataPage, PERMISSIONS_LINK_NAME);
        permissionsPage = addUser(permissionsPage, APPROVER, VIEWER_ROLENAME);
        permissionsPage = addUser(permissionsPage, PROPOSAL_CREATOR, AGGREGATOR_ROLENAME);

        // Save the proposal and re-check to be sure the data is still correctly displayed.
        HtmlPage proposalPage = saveAndSearchDoc(permissionsPage);
        
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
        HtmlForm submitForm = (HtmlForm) submitPage.getForms().get(0);

        //Submit PD 
        final HtmlPage confirmationPage = clickButton(submitPage, submitForm, "methodToCall.route", IMAGE_INPUT);
        assertNotNull(confirmationPage);
        assertTrue(confirmationPage.asText().contains("Document was successfully submitted"));

        GlobalVariables.setUserSession(null);
        //Login as jtester User
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
        
        //jtester Should be shown the Question Page (since he is configured to get Multiple Approval Requests)
        assertTrue(approvalConfirmationPage.asText().contains("Kuali :: Question Dialog Page"));

        //jtester answers Yes to the question
        HtmlForm form3 = (HtmlForm) approvalConfirmationPage.getForms().get(0);
        final HtmlPage backToActionList = clickButton(approvalConfirmationPage, form3, "methodToCall.processAnswer.button0",
                IMAGE_INPUT);
        assertNotNull(backToActionList);
        assertTrue(backToActionList.asText().contains("Action List"));

        ProposalDevelopmentDocument savedDocument = (ProposalDevelopmentDocument) documentService
                .getByDocumentHeaderId(documentNumber.getDefaultValue());
        assertNotNull(savedDocument);
        KualiWorkflowDocument workflowDoc = savedDocument.getDocumentHeader().getWorkflowDocument();
        assertNotNull(workflowDoc);

        NetworkIdDTO networkId = new NetworkIdDTO("jtester");
        boolean receiveFutureRequests = false;
        boolean doNotReceiveFutureRequests = false;

        List variables = workflowDoc.getRouteHeader().getVariables();
        if (CollectionUtils.isNotEmpty(variables)) {
            for (Object variable : variables) {
                KeyValuePair kvp = (KeyValuePair) variable;
                if (kvp.getKey().startsWith(KEWConstants.RECEIVE_FUTURE_REQUESTS_BRANCH_STATE_KEY)
                        && kvp.getValue().toUpperCase().equals(KEWConstants.RECEIVE_FUTURE_REQUESTS_BRANCH_STATE_VALUE)
                        && kvp.getKey().contains(networkId.getNetworkId())) {
                    receiveFutureRequests = true;
                }
                else if (kvp.getKey().startsWith(KEWConstants.RECEIVE_FUTURE_REQUESTS_BRANCH_STATE_KEY)
                        && kvp.getValue().toUpperCase().equals(KEWConstants.DONT_RECEIVE_FUTURE_REQUESTS_BRANCH_STATE_VALUE)
                        && kvp.getKey().contains(networkId.getNetworkId())) {
                    doNotReceiveFutureRequests = true;  
                }
            }
        }

        //Asserting on the Workflow Document variables based on jtester's response
        assertTrue(receiveFutureRequests);
        assertFalse(doNotReceiveFutureRequests);

        WorkflowInfo info = new WorkflowInfo();
        ReportCriteriaDTO reportCriteria = new ReportCriteriaDTO(new Long(workflowDoc.getRouteHeaderId()));
        reportCriteria.setTargetPrincipalIds(new String[] { USER_PRINCIPLE_ID });

        DocumentDetailDTO results1 = info.routingReport(reportCriteria);
        assertNotNull(results1.getActionRequests());
        assertEquals(4, results1.getActionRequests().length);
        
        for(ActionRequestDTO actionRequest: results1.getActionRequests()) {
            if(actionRequest.getNodeName().equalsIgnoreCase("Initiated")) { 
                assertEquals("U", actionRequest.getRecipientTypeCd());
                assertNotNull(actionRequest.getPrincipalId());
                assertEquals("quickstart", actionRequest.getPrincipalId());
            } else if(actionRequest.getNodeName().equalsIgnoreCase("FirstApproval")) {
                assertEquals("U", actionRequest.getRecipientTypeCd());
                assertNotNull(actionRequest.getPrincipalId());
                assertEquals("jtester", actionRequest.getPrincipalId());
                assertFalse(actionRequest.isPending());  
                assertTrue(actionRequest.isDone());
            } else if(actionRequest.getNodeName().equalsIgnoreCase("SecondApproval")) {
                assertEquals("U", actionRequest.getRecipientTypeCd());
                assertNotNull(actionRequest.getPrincipalId());
                assertEquals("quickstart", actionRequest.getPrincipalId());
            } else if(actionRequest.getNodeName().equalsIgnoreCase("FinalApproval")) {
                assertEquals("W", actionRequest.getRecipientTypeCd());
                assertNotNull(actionRequest.getGroupId());
                assertEquals(WORKFLOW_ADMIN_GROUP_ID, actionRequest.getGroupId());
            } else {
                fail("Unexpected ActionRequest generated for ProposalDevelopmentDocument");
            }
        } 
    }

    private HtmlPage addUser(HtmlPage page, String username, String roleName) throws Exception {
        setFieldValue(page, USERNAME_FIELD_ID, username);
        setFieldValue(page, ROLENAME_FIELD_ID, roleName);
        HtmlElement addBtn = getElementByName(page, ADD_BTN_ID, true);
        return clickOn(addBtn);
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


}
