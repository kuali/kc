/*
 * Copyright 2006-2008 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
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
package org.kuali.kra.irb.web;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.ProtocolSubmissionDoc;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionQualifierType;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionType;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.test.data.PerSuiteUnitTestData;
import org.kuali.rice.test.data.UnitTestData;
import org.kuali.rice.test.data.UnitTestFile;

import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * Test the Notify IRB action.
 */
@PerSuiteUnitTestData(
        @UnitTestData(
            sqlFiles = {
                @UnitTestFile(filename = "classpath:sql/dml/load_SUBMISSION_TYPE.sql", delimiter = ";")
               ,@UnitTestFile(filename = "classpath:sql/dml/load_protocol_review_type.sql", delimiter = ";")
               ,@UnitTestFile(filename = "classpath:sql/dml/load_PROTOCOL_ACTION_TYPE.sql", delimiter = ";")
               ,@UnitTestFile(filename = "classpath:sql/dml/load_SUBMISSION_TYPE_QUALIFIER.sql", delimiter = ";")
            }
        )
    )
public class ProtocolNotifyIrbWebTest extends ProtocolWebTestBase {

    private static final String REASON = "this is a test";
    private static final String ATTACHMENT_FILENAME = "/org/kuali/kra/irb/web/ProtocolRequestWebTest.class";
    
    private BusinessObjectService businessObjectService;
    
    @Before
    public void setUp() throws Exception {
        super.setUp();    
        businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
    }
   
    @Test
    public void testNotifyIrb() throws Exception {
        HtmlPage protocolPage = getProtocolSavedRequiredFieldsPage();
        HtmlPage protocolActionsPage = clickOnTab(protocolPage, PROTOCOL_ACTIONS_LINK_NAME);
        
        setFieldValue(protocolActionsPage, "actionHelper.protocolNotifyIrbBean.submissionQualifierTypeCode", ProtocolSubmissionQualifierType.AE_UADE);
        setFieldValue(protocolActionsPage, "actionHelper.protocolNotifyIrbBean.committeeId", "");
        setFieldValue(protocolActionsPage, "actionHelper.protocolNotifyIrbBean.comment", REASON);
        setFieldValue(protocolActionsPage, "actionHelper.protocolNotifyIrbBean.file", getFilePath());
        
        HtmlPage resultPage = clickOn(protocolActionsPage, "methodToCall.notifyIrbProtocol.anchor:NotifyIRB");
        
        assertNotNull(resultPage);
        assertDoesNotContain(resultPage, ERRORS_FOUND_ON_PAGE);
        
        String docNbr = this.getDocNbr(resultPage);
        ProtocolDocument protocolDocument = (ProtocolDocument) getDocument(docNbr);
        
        // Verify that we created the correct submission BO
        ProtocolSubmission submission = findSubmission(protocolDocument.getProtocol().getProtocolId());
        assertEquals(ProtocolSubmissionType.NOTIFY_IRB, submission.getSubmissionTypeCode());
        assertEquals(ProtocolSubmissionQualifierType.AE_UADE, submission.getSubmissionTypeQualifierCode());
    
        // Verify that we created the correct protocol action BO
        ProtocolAction protocolAction = findProtocolAction(protocolDocument.getProtocol().getProtocolId());
        assertEquals(ProtocolActionType.NOTIFY_IRB, protocolAction.getProtocolActionTypeCode());
        assertEquals(REASON, protocolAction.getComments());
        
        // Verify that we uploaded an attachment
        ProtocolSubmissionDoc submissionDoc = findSubmissionDoc(submission);
        assertNotNull(submissionDoc);
    }
    
    /**
     * Get the path to a file.  That file will be uploaded for the submission.
     * @return
     */
    private String getFilePath() {
        URL fileUrl = getClass().getResource(ATTACHMENT_FILENAME);
        assertNotNull(fileUrl);
        return fileUrl.getPath();
    }
    
    @SuppressWarnings("unchecked")
    private ProtocolAction findProtocolAction(Long protocolId) {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("protocolId", protocolId);
        List<ProtocolAction> actions = (List<ProtocolAction>) businessObjectService.findMatching(ProtocolAction.class, fieldValues);
        
        assertEquals(1, actions.size());
        ProtocolAction action = actions.get(0);
        return action;
    }
    
    @SuppressWarnings("unchecked")
    private ProtocolSubmission findSubmission(Long protocolId) {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("protocolId", protocolId);
        List<ProtocolSubmission> submissions = (List<ProtocolSubmission>) businessObjectService.findMatching(ProtocolSubmission.class, fieldValues);
        
        assertEquals(1, submissions.size());
        ProtocolSubmission submission = submissions.get(0);
        return submission;
    }
    
    @SuppressWarnings("unchecked")
    private ProtocolSubmissionDoc findSubmissionDoc(ProtocolSubmission protocolSubmission) {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("submissionIdFk", protocolSubmission.getSubmissionId());
        List<ProtocolSubmissionDoc> docs = (List<ProtocolSubmissionDoc>) businessObjectService.findMatching(ProtocolSubmissionDoc.class, fieldValues);
        
        if (docs.size() == 0) {
            return null;
        }
        return docs.get(0);
    }
}
