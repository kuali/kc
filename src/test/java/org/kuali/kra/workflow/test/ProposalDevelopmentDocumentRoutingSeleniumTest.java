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

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.web.ProposalDevelopmentSeleniumHelper;
import org.kuali.kra.test.infrastructure.KcSeleniumTestBase;
import org.kuali.rice.kew.dto.ActionRequestDTO;
import org.kuali.rice.kew.dto.DocumentDetailDTO;
import org.kuali.rice.kew.dto.KeyValueDTO;
import org.kuali.rice.kew.dto.NetworkIdDTO;
import org.kuali.rice.kew.dto.ReportCriteriaDTO;
import org.kuali.rice.kew.engine.node.KeyValuePair;
import org.kuali.rice.kew.service.WorkflowInfo;
import org.kuali.rice.kew.util.KEWConstants;
import org.kuali.rice.kns.workflow.service.KualiWorkflowDocument;

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
      
//      GlobalVariables.setErrorMap(new ErrorMap());
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
        KualiWorkflowDocument workflowDoc = savedDocument.getDocumentHeader().getWorkflowDocument();
        assertNotNull(workflowDoc);

        NetworkIdDTO networkId = new NetworkIdDTO(APPROVER);
        boolean receiveFutureRequests = false;
        boolean doNotReceiveFutureRequests = false;

        List<KeyValueDTO> variables = workflowDoc.getRouteHeader().getVariables();
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
        reportCriteria.setTargetPrincipalIds(new String[] { APPROVER });

        DocumentDetailDTO results1 = info.routingReport(reportCriteria);
        assertNotNull(results1.getActionRequests());
        assertEquals(4, results1.getActionRequests().length);
        
        for (ActionRequestDTO actionRequest: results1.getActionRequests()) {
            if (actionRequest.getNodeName().equalsIgnoreCase("Initiated")) { 
                assertEquals("U", actionRequest.getRecipientTypeCd());
                assertNotNull(actionRequest.getPrincipalId());
                assertEquals("quickstart", actionRequest.getPrincipalId());
            } else if (actionRequest.getNodeName().equalsIgnoreCase("FirstApproval")) {
                assertEquals("U", actionRequest.getRecipientTypeCd());
                assertNotNull(actionRequest.getPrincipalId());
                assertEquals("jtester", actionRequest.getPrincipalId());
                assertFalse(actionRequest.isPending());  
                assertTrue(actionRequest.isDone());
            } else if (actionRequest.getNodeName().equalsIgnoreCase("SecondApproval")) {
                assertEquals("U", actionRequest.getRecipientTypeCd());
                assertNotNull(actionRequest.getPrincipalId());
                assertEquals("quickstart", actionRequest.getPrincipalId());
            } else if (actionRequest.getNodeName().equalsIgnoreCase("FinalApproval")) {
                assertEquals("W", actionRequest.getRecipientTypeCd());
                assertNotNull(actionRequest.getGroupId());
                assertEquals(WORKFLOW_ADMIN_GROUP_ID, actionRequest.getGroupId());
            } else {
                fail("Unexpected ActionRequest generated for ProposalDevelopmentDocument");
            }
        } 
    }

}