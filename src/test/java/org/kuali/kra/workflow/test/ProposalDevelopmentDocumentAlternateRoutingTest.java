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

import java.io.File;
import java.sql.Date;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.KraKEWXmlDataLoaderLifecycle;
import org.kuali.kra.KraTestBase;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.rice.kew.dto.ActionRequestDTO;
import org.kuali.rice.kew.dto.DocumentDetailDTO;
import org.kuali.rice.kew.dto.ReportCriteriaDTO;
import org.kuali.rice.kew.service.WorkflowInfo;
import org.kuali.rice.kns.UserSession;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.kns.util.ErrorMap;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.workflow.service.KualiWorkflowDocument;
import org.kuali.rice.test.lifecycles.SQLDataLoaderLifecycle;
import org.springframework.core.io.ClassPathResource;

public class ProposalDevelopmentDocumentAlternateRoutingTest extends KraTestBase {
    private DocumentService documentService = null;
    private KraKEWXmlDataLoaderLifecycle customKEWLifecycle = null;
    private File xmlBackupDir = null;
    private static final String WORKFLOW_ADMIN_GROUP_ID = "1";
    private static final String USER_PRINCIPLE_ID = "jtester";
        
    @Before
    public void setUp() throws Exception {
        super.setUp();
        transactionalLifecycle.stop();
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
    public void testAlternateRoutingPath() throws Exception {
        ProposalDevelopmentDocument document = (ProposalDevelopmentDocument) documentService
                .getNewDocument("ProposalDevelopmentDocument");

        Date requestedStartDateInitial = new Date(System.currentTimeMillis());
        Date requestedEndDateInitial = new Date(System.currentTimeMillis());

        document.getDocumentHeader().setDocumentDescription("TestAltRoutingPath-1");
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
        assertEquals(4, actionRequests.length);
        
        for(ActionRequestDTO actionRequest: actionRequests) {
            if(actionRequest.getNodeName().equalsIgnoreCase("Initiated")) { 
                assertEquals("U", actionRequest.getRecipientTypeCd());
                assertNotNull(actionRequest.getPrincipalId());
                assertEquals("quickstart", actionRequest.getPrincipalId());
            } else if(actionRequest.getNodeName().equalsIgnoreCase("FirstApproval")) {
                assertEquals("U", actionRequest.getRecipientTypeCd());
                assertNotNull(actionRequest.getPrincipalId());
                assertEquals("jtester", actionRequest.getPrincipalId());
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
}
