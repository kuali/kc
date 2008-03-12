/*
 * Copyright 2005-2007 The Kuali Foundation.
 * 
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

import java.sql.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.core.UserSession;
import org.kuali.core.service.DocumentService;
import org.kuali.core.util.GlobalVariables;
import org.kuali.core.workflow.service.KualiWorkflowDocument;
import org.kuali.kra.KraTestBase;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.rice.KNSServiceLocator;

import edu.iu.uis.eden.clientapp.WorkflowInfo;
import edu.iu.uis.eden.clientapp.vo.ActionRequestVO;
import edu.iu.uis.eden.clientapp.vo.DocumentDetailVO;
import edu.iu.uis.eden.clientapp.vo.NetworkIdVO;
import edu.iu.uis.eden.clientapp.vo.ReportCriteriaVO;
import edu.iu.uis.eden.clientapp.vo.UserIdVO;

public class ProposalDevelopmentDocumentRoutingTest extends KraTestBase {
    private DocumentService documentService = null;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        GlobalVariables.setUserSession(new UserSession("quickstart"));
        documentService = KNSServiceLocator.getDocumentService();
    }  

    @After
    public void tearDown() throws Exception {
        GlobalVariables.setUserSession(null);
        documentService = null;
        super.tearDown();
    }
    
    @Test
    public void testAlternateRoutingPath() throws Exception {
        ProposalDevelopmentDocument document = (ProposalDevelopmentDocument) documentService
                .getNewDocument("ProposalDevelopmentDocument");

        Date requestedStartDateInitial = new Date(System.currentTimeMillis());
        Date requestedEndDateInitial = new Date(System.currentTimeMillis());

        document.getDocumentHeader().setFinancialDocumentDescription("TestRoutingPath-1");
        document.setSponsorCode("005770");
        document.setTitle("AltRoutingPath");
        document.setRequestedStartDateInitial(requestedStartDateInitial);
        document.setRequestedEndDateInitial(requestedEndDateInitial);
        document.setActivityTypeCode("1");
        document.setProposalTypeCode("1");
        document.setOwnedByUnitNumber("000001");

        documentService.saveDocument(document);

        ProposalDevelopmentDocument savedDocument = (ProposalDevelopmentDocument) documentService.getByDocumentHeaderId(document
                .getDocumentNumber());
        assertNotNull(savedDocument);

        KualiWorkflowDocument workflowDoc = savedDocument.getDocumentHeader().getWorkflowDocument();
        workflowDoc.complete("test");

        WorkflowInfo info = new WorkflowInfo();
        NetworkIdVO networkId = new NetworkIdVO("quickstart");
        ReportCriteriaVO reportCriteria = new ReportCriteriaVO(new Long(workflowDoc.getRouteHeaderId()));
        reportCriteria.setTargetUsers(new UserIdVO[] { networkId });
        reportCriteria.setActivateRequests(true);

        DocumentDetailVO results1 = info.routingReport(reportCriteria);
        ActionRequestVO[] actionRequests = results1.getActionRequests();
        assertNotNull(actionRequests);
        assertEquals(3, actionRequests.length);
        
        for(ActionRequestVO actionRequest: actionRequests) {
            if(actionRequest.getNodeName().equalsIgnoreCase("Initiated")) { 
                assertEquals("U", actionRequest.getRecipientTypeCd());
                assertNotNull(actionRequest.getUserVO().getNetworkId());
                assertEquals("quickstart", actionRequest.getUserVO().getNetworkId());
            } else if(actionRequest.getNodeName().equalsIgnoreCase("FirstApproval")) {
                assertEquals("U", actionRequest.getRecipientTypeCd());
                assertNotNull(actionRequest.getUserVO().getNetworkId());
                assertEquals("quickstart", actionRequest.getUserVO().getNetworkId()); 
            } else if(actionRequest.getNodeName().equalsIgnoreCase("SecondApproval")) {
                assertEquals("U", actionRequest.getRecipientTypeCd());
                assertNotNull(actionRequest.getUserVO().getNetworkId());
                assertEquals("quickstart", actionRequest.getUserVO().getNetworkId());
            } else {
                fail("Unexpected ActionRequest generated for ProposalDevelopmentDocument");
            }
        }
    }
}
