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
package org.kuali.kra.workflow.test;

import java.net.URL;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.core.UserSession;
import org.kuali.core.util.ErrorMap;
import org.kuali.core.util.GlobalVariables;
import org.kuali.core.workflow.service.KualiWorkflowDocument;
import org.kuali.kra.KraKEWXmlDataLoaderLifecycle;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.web.ProposalDevelopmentWebTestBase;
import org.kuali.rice.KNSServiceLocator;
import org.kuali.rice.lifecycle.Lifecycle;
import org.kuali.rice.test.lifecycles.SQLDataLoaderLifecycle;

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

import edu.iu.uis.eden.EdenConstants;
import edu.iu.uis.eden.clientapp.WorkflowInfo;
import edu.iu.uis.eden.clientapp.vo.ActionRequestVO;
import edu.iu.uis.eden.clientapp.vo.DocumentDetailVO;
import edu.iu.uis.eden.clientapp.vo.NetworkIdVO;
import edu.iu.uis.eden.clientapp.vo.ReportCriteriaVO;
import edu.iu.uis.eden.clientapp.vo.UserIdVO;
import edu.iu.uis.eden.engine.node.KeyValuePair;

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

    private Lifecycle customKEWLifecycle = null;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        transactionalLifecycle.stop();
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
        customKEWLifecycle = new KraKEWXmlDataLoaderLifecycle("classpath:kew/xml/test/revert");
        customKEWLifecycle.start();

        GlobalVariables.setErrorMap(new ErrorMap());
        stopLifecycles(this.perTestLifeCycles);
        afterRun();
    }


    @Test
    public void testAddUsers() throws Exception {
        HtmlPage permissionsPage = getPermissionsPage();
        // Add the users.
        permissionsPage = addUser(permissionsPage, APPROVER, VIEWER_ROLENAME);
        permissionsPage = addUser(permissionsPage, PROPOSAL_CREATOR, AGGREGATOR_ROLENAME);

        // Save the proposal and re-check to be sure the data is still correctly displayed.
        HtmlPage proposalPage = saveAndSearchDoc(permissionsPage);
        HtmlForm form = (HtmlForm) proposalPage.getForms().get(0);
        final HtmlHiddenInput documentNumber = (HtmlHiddenInput) form.getInputByName("document.documentHeader.documentNumber");

        
        HtmlPage submitPage = clickOnTab(proposalPage, ACTIONS_LINK_NAME);
        HtmlForm form1 = (HtmlForm) submitPage.getForms().get(0);

        final HtmlPage confirmationPage = clickButton(submitPage, form1, "methodToCall.route", IMAGE_INPUT);

        assertNotNull(confirmationPage);
        assertTrue(confirmationPage.asText().contains("Document was successfully submitted"));

        GlobalVariables.setUserSession(null);
        GlobalVariables.setUserSession(new UserSession("jtester"));
        final WebClient newWebClient = new WebClient(BrowserVersion.INTERNET_EXPLORER_7_0);
        final URL url = new URL("http://localhost:" + getPort() + "/kra-dev/");
        final HtmlPage pageAfterLogin = login(newWebClient, url, "en/ActionList.do", "jtester");
        assertNotNull(pageAfterLogin);

        HtmlAnchor docLink = getAnchorFromPage(pageAfterLogin, "docId=" + documentNumber.getDefaultValue());
        assertNotNull(docLink);
        HtmlPage docDisplay = (HtmlPage) docLink.click();
        assertNotNull(docDisplay);

        HtmlPage approvePage = clickOnTab(docDisplay, ACTIONS_LINK_NAME);
        HtmlForm form2 = (HtmlForm) approvePage.getForms().get(0);
        final HtmlPage approvalConfirmationPage = clickButton(approvePage, form2, "methodToCall.approve", IMAGE_INPUT);
        assertNotNull(approvalConfirmationPage);
        assertTrue(approvalConfirmationPage.asText().contains("Kuali :: Question Dialog Page"));

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

        NetworkIdVO networkId = new NetworkIdVO("jtester");
        boolean receiveFutureRequests = false;
        boolean doNotReceiveFutureRequests = false;

        List variables = workflowDoc.getRouteHeader().getVariables();
        if (CollectionUtils.isNotEmpty(variables)) {
            for (Object variable : variables) {
                KeyValuePair kvp = (KeyValuePair) variable;
                if (kvp.getKey().startsWith(EdenConstants.RECEIVE_FUTURE_REQUESTS_BRANCH_STATE_KEY)
                        && kvp.getValue().toUpperCase().equals(EdenConstants.RECEIVE_FUTURE_REQUESTS_BRANCH_STATE_VALUE)
                        && kvp.getKey().contains(networkId.getNetworkId())) {
                    receiveFutureRequests = true;
                }
                else if (kvp.getKey().startsWith(EdenConstants.RECEIVE_FUTURE_REQUESTS_BRANCH_STATE_KEY)
                        && kvp.getValue().toUpperCase().equals(EdenConstants.DONT_RECEIVE_FUTURE_REQUESTS_BRANCH_STATE_VALUE)
                        && kvp.getKey().contains(networkId.getNetworkId())) {
                    doNotReceiveFutureRequests = true;
                }
            }
        }

        assertTrue(receiveFutureRequests);
        assertFalse(doNotReceiveFutureRequests);

        WorkflowInfo info = new WorkflowInfo();
        ReportCriteriaVO reportCriteria = new ReportCriteriaVO(new Long(workflowDoc.getRouteHeaderId()));
        reportCriteria.setTargetUsers(new UserIdVO[] { networkId });

        DocumentDetailVO results1 = info.routingReport(reportCriteria);
        assertNotNull(results1.getActionRequests());
        assertEquals(4, results1.getActionRequests().length);
        
        for(ActionRequestVO actionRequest: results1.getActionRequests()) {
            if(actionRequest.getNodeName().equalsIgnoreCase("Initiated")) { 
                assertEquals("U", actionRequest.getRecipientTypeCd());
                assertNotNull(actionRequest.getUserVO().getNetworkId());
                assertEquals("quickstart", actionRequest.getUserVO().getNetworkId());
            } else if(actionRequest.getNodeName().equalsIgnoreCase("FirstApproval")) {
                assertEquals("U", actionRequest.getRecipientTypeCd());
                assertNotNull(actionRequest.getUserVO().getNetworkId());
                assertEquals("jtester", actionRequest.getUserVO().getNetworkId());
            } else if(actionRequest.getNodeName().equalsIgnoreCase("SecondApproval")) {
                assertEquals("U", actionRequest.getRecipientTypeCd());
                assertNotNull(actionRequest.getUserVO().getNetworkId());
                assertEquals("quickstart", actionRequest.getUserVO().getNetworkId());
            } else if(actionRequest.getNodeName().equalsIgnoreCase("FinalApproval")) {
                assertEquals("W", actionRequest.getRecipientTypeCd());
                assertNotNull(actionRequest.getWorkgroupVO());
                assertEquals("WorkflowAdmin", actionRequest.getWorkgroupVO().getWorkgroupName());
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
