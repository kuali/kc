/*
 * Copyright 2005-2010 The Kuali Foundation
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

import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.web.ProposalDevelopmentSeleniumHelper;
import org.kuali.kra.test.infrastructure.KcSeleniumTestBase;
import org.kuali.rice.core.api.resourceloader.GlobalResourceLoader;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.kew.api.action.ActionRequest;
import org.kuali.rice.kew.api.action.RoutingReportCriteria;
import org.kuali.rice.kew.api.action.WorkflowDocumentActionsService;
import org.kuali.rice.kew.api.document.DocumentDetail;

/**
 * This class tests the KraServiceLocator
 */
@Ignore
public class ProposalDevelopmentDocumentRoutingSeleniumTest extends KcSeleniumTestBase {
    
    private static final String APPROVER = "jtester";
    private static final String WORKFLOW_ADMIN_GROUP_ID = "1";

    private ProposalDevelopmentSeleniumHelper helper;
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        
        helper = ProposalDevelopmentSeleniumHelper.instance(driver);
        
//      transactionalLifecycle.stop();
//      
//      /* Whatever this is trying to do isn't working - all of the tests run after this one
//       * that rely on ProposalDevelopmentDocument/Rules fail.
//       * 
//       * For now I'm just going to re-run the whole KEWXmlDataLoaderLifecycle in tearDown() instead of the 'revert' directory.
//       * There's a Jira to clean this test up - KRACOEUS-2122
//       */
//      ClassPathResource routingResource1 = new ClassPathResource("kew/xml/ProposalDevelopmentDocument.xml");
//      ClassPathResource routingResource2 = new ClassPathResource("kew/xml/ProposalDevelopmentDocumentRules.xml");
//      xmlBackupDir = new File(new ClassPathResource("kew/xml/test").getFile(), "revert");
//      xmlBackupDir.mkdir();
//      
//      FileUtils.copyFileToDirectory(routingResource1.getFile(), xmlBackupDir);
//      FileUtils.copyFileToDirectory(routingResource2.getFile(), xmlBackupDir);
//
//      new SQLDataLoaderLifecycle("classpath:sql/dml/clear_kew_rules.sql", ";").start();
//      customKEWLifecycle = new KraKEWXmlDataLoaderLifecycle("classpath:kew/xml/test");
//      customKEWLifecycle.start();
//      transactionalLifecycle.start();
    }
    
    @After
    public void tearDown() throws Exception {
        helper = null;
        
//      customKEWLifecycle.stop();
//      customKEWLifecycle = null;
//      
//      transactionalLifecycle.stop();
//      new SQLDataLoaderLifecycle("classpath:sql/dml/clear_kew_rules.sql", ";").start();
//      //FIXME: kew file reorg
//      //customKEWLifecycle = new KraKEWXmlDataLoaderLifecycle();
//      customKEWLifecycle.start();
//
//      FileUtils.deleteDirectory(xmlBackupDir);
      
//      GlobalVariables.setMessageMap(new MessageMap());
//      stopLifecycles(this.perTestLifeCycles);
        
        super.tearDown();
    }

    @Test
    public void testAlternateRoutingWithMultipleApproval() throws Exception {
        helper.createProposalDevelopment();
        
        helper.addKeyPersonnel();
        
        helper.addCustomData();

        helper.addPermissions();
        
        helper.addQuestions();

        helper.clickProposalDevelopmentActionsPage();

        helper.routeDocument();
        helper.clickYesAnswer();
        helper.assertRoute();
        
        String documentNumber = helper.getDocumentNumber();
        
        helper.closeDocument();
        
        helper.loginBackdoor(APPROVER);
        
        helper.click("Action List");
        
        helper.click(documentNumber);

        helper.clickProposalDevelopmentActionsPage();
        
        helper.approveDocument();
        helper.clickYesAnswer();

        ProposalDevelopmentDocument savedDocument = (ProposalDevelopmentDocument) getDocumentService().getByDocumentHeaderId(documentNumber);
        assertNotNull(savedDocument);
        WorkflowDocument workflowDoc = savedDocument.getDocumentHeader().getWorkflowDocument();
        assertNotNull(workflowDoc);

        boolean receiveFutureRequests = false;
        boolean doNotReceiveFutureRequests = false;

        Map<String, String> variables = workflowDoc.getVariables();
        if (variables != null && CollectionUtils.isNotEmpty(variables.keySet())) {
            Iterator<String> variableIterator = variables.keySet().iterator();
            while(variableIterator.hasNext()) {
                    String variableKey = variableIterator.next();
                    String variableValue = variables.get(variableKey);
                    if (variableKey.startsWith(KewApiConstants.RECEIVE_FUTURE_REQUESTS_BRANCH_STATE_KEY)
                            && variableValue.toUpperCase().equals(KewApiConstants.RECEIVE_FUTURE_REQUESTS_BRANCH_STATE_VALUE)
                            && variableKey.contains(APPROVER)) {
                        receiveFutureRequests = true; 
                        break;
                    }
                    else if (variableKey.startsWith(KewApiConstants.RECEIVE_FUTURE_REQUESTS_BRANCH_STATE_KEY)
                          && variableValue.toUpperCase().equals(KewApiConstants.DONT_RECEIVE_FUTURE_REQUESTS_BRANCH_STATE_VALUE)
                          && variableKey.contains(APPROVER)) {
                        doNotReceiveFutureRequests = true; 
                        break;
                    }
            }
        } 
        //Asserting on the Workflow Document variables based on jtester's response
        assertTrue(receiveFutureRequests);
        assertFalse(doNotReceiveFutureRequests);

        RoutingReportCriteria.Builder reportCriteriaBuilder = RoutingReportCriteria.Builder.createByDocumentId(workflowDoc.getDocumentId());
        reportCriteriaBuilder.setTargetPrincipalIds(Collections.singletonList(APPROVER));
        reportCriteriaBuilder.setActivateRequests(true);
        WorkflowDocumentActionsService info = GlobalResourceLoader.getService("rice.kew.workflowDocumentActionsService");
        DocumentDetail results1 = info.executeSimulation(reportCriteriaBuilder.build());
        assertNotNull(results1.getActionRequests());
        assertEquals(4, results1.getActionRequests().size());
        
        for(ActionRequest actionRequest: results1.getActionRequests()) {
            if(actionRequest.getNodeName().equalsIgnoreCase("Initiated")) { 
                assertEquals("U", actionRequest.getRecipientType().getCode());
                assertNotNull(actionRequest.getPrincipalId());
                assertEquals("quickstart", actionRequest.getPrincipalId());
            } else if(actionRequest.getNodeName().equalsIgnoreCase("FirstApproval")) {
                assertEquals("U", actionRequest.getRecipientType().getCode());
                assertNotNull(actionRequest.getPrincipalId());
                assertEquals("jtester", actionRequest.getPrincipalId());
                assertFalse(actionRequest.isPending());  
                assertTrue(actionRequest.isDone());
            } else if(actionRequest.getNodeName().equalsIgnoreCase("SecondApproval")) {
                assertEquals("U", actionRequest.getRecipientType().getCode());
                assertNotNull(actionRequest.getPrincipalId());
                assertEquals("quickstart", actionRequest.getPrincipalId());
            } else if(actionRequest.getNodeName().equalsIgnoreCase("FinalApproval")) {
                assertEquals("W", actionRequest.getRecipientType().getCode());
                assertNotNull(actionRequest.getGroupId());
                assertEquals(WORKFLOW_ADMIN_GROUP_ID, actionRequest.getGroupId());
            } else {
                fail("Unexpected ActionRequest generated for ProposalDevelopmentDocument");
            }
        } 
    }

}