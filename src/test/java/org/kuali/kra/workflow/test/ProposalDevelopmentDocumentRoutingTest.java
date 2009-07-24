/*
 * Copyright 2006-2009 The Kuali Foundation
 * 
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

import java.sql.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.KraTestBase;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.rice.kew.dto.ActionRequestDTO;
import org.kuali.rice.kew.dto.DocumentDetailDTO;
import org.kuali.rice.kew.dto.ReportCriteriaDTO;
import org.kuali.rice.kew.service.WorkflowInfo;
import org.kuali.rice.kns.UserSession;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.workflow.service.KualiWorkflowDocument;

public class ProposalDevelopmentDocumentRoutingTest extends KraTestBase {
    private DocumentService documentService = null;
    
    private static final String USER_PRINCIPLE_ID = "quickstart";

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

        document.getDocumentHeader().setDocumentDescription("TestRoutingPath-1");
        document.getDevelopmentProposal().setSponsorCode("005770");
        document.getDevelopmentProposal().setTitle("AltRoutingPath");
        document.getDevelopmentProposal().setRequestedStartDateInitial(requestedStartDateInitial);
        document.getDevelopmentProposal().setRequestedEndDateInitial(requestedEndDateInitial);
        document.getDevelopmentProposal().setActivityTypeCode("1");
        document.getDevelopmentProposal().setProposalTypeCode("1");
        document.getDevelopmentProposal().setOwnedByUnitNumber("000001");

        documentService.saveDocument(document);

        ProposalDevelopmentDocument savedDocument = (ProposalDevelopmentDocument) documentService.getByDocumentHeaderId(document
                .getDocumentNumber());
        assertNotNull(savedDocument);

        KualiWorkflowDocument workflowDoc = savedDocument.getDocumentHeader().getWorkflowDocument();
        workflowDoc.complete("test");

        WorkflowInfo info = new WorkflowInfo();
        ReportCriteriaDTO reportCriteria = new ReportCriteriaDTO(new Long(workflowDoc.getRouteHeaderId()));
        reportCriteria.setTargetPrincipalIds(new String[] { USER_PRINCIPLE_ID });
        reportCriteria.setActivateRequests(true);

        DocumentDetailDTO results1 = info.routingReport(reportCriteria);
        ActionRequestDTO[] actionRequests = results1.getActionRequests();
        assertNotNull(actionRequests);
        assertEquals(3, actionRequests.length);
        
        for(ActionRequestDTO actionRequest: actionRequests) {
            if(actionRequest.getNodeName().equalsIgnoreCase("Initiated")) { 
                assertEquals("U", actionRequest.getRecipientTypeCd());
                assertNotNull(actionRequest.getPrincipalId());
                assertEquals("quickstart", actionRequest.getPrincipalId());
            } else if(actionRequest.getNodeName().equalsIgnoreCase("FirstApproval")) {
                assertEquals("U", actionRequest.getRecipientTypeCd());
                assertNotNull(actionRequest.getPrincipalId());
                assertEquals("quickstart", actionRequest.getPrincipalId()); 
            } else if(actionRequest.getNodeName().equalsIgnoreCase("SecondApproval")) {
                assertEquals("U", actionRequest.getRecipientTypeCd());
                assertNotNull(actionRequest.getPrincipalId());
                assertEquals("quickstart", actionRequest.getPrincipalId());
            } else {
                fail("Unexpected ActionRequest generated for ProposalDevelopmentDocument");
            }
        }
    }
}
