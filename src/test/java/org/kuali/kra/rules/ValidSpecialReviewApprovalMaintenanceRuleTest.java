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
package org.kuali.kra.rules;

import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.bo.ValidSpecialReviewApproval;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.maintenance.MaintenanceRuleTestBase;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.kns.util.GlobalVariables;

public class ValidSpecialReviewApprovalMaintenanceRuleTest extends MaintenanceRuleTestBase {
    private ValidSpecialReviewApprovalMaintenanceRule rule;
    private MaintenanceDocument maintDoc;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        rule = new ValidSpecialReviewApprovalMaintenanceRule();
        ValidSpecialReviewApproval specialReviewApproval = new ValidSpecialReviewApproval();
        maintDoc = newMaintDoc(specialReviewApproval);
    }
    
    /** Tests with an invalid Approval Type code and an invalid Special Review Code */
    @Test
    public void testBothInvalid() throws Exception {
        ValidSpecialReviewApproval specialReviewApproval = (ValidSpecialReviewApproval)maintDoc.getDocumentBusinessObject();
        specialReviewApproval.setApprovalTypeCode("-9999");
        specialReviewApproval.setSpecialReviewCode("-9999");
        boolean valid = rule.processCustomRouteDocumentBusinessRules(maintDoc);
        assertFalse(valid);
        assertEquals(2, GlobalVariables.getErrorMap().getErrorCount());
        assertEquals(1, GlobalVariables.getErrorMap().getErrorMessagesForProperty(Constants.VALID_SPECIAL_REVIEW_APPROVAL_TYPE_CODE_KEY).size());
        assertEquals(1, GlobalVariables.getErrorMap().getErrorMessagesForProperty(Constants.VALID_SPECIAL_REVIEW_APPROVAL_REVIEW_CODE_KEY).size());
    }
    
    /** Tests with an valid Approval Type code and an invalid Special Review Code */
    @Test
    public void testInvalidReviewCode() throws Exception {
        ValidSpecialReviewApproval specialReviewApproval = (ValidSpecialReviewApproval)maintDoc.getDocumentBusinessObject();
        specialReviewApproval.setApprovalTypeCode("2");
        specialReviewApproval.setSpecialReviewCode("-9999");
        boolean valid = rule.processCustomRouteDocumentBusinessRules(maintDoc);
        assertFalse(valid);
        assertEquals(1, GlobalVariables.getErrorMap().getErrorCount());
        assertEquals(1, GlobalVariables.getErrorMap().getErrorMessagesForProperty(Constants.VALID_SPECIAL_REVIEW_APPROVAL_REVIEW_CODE_KEY).size());
    }
    
    /** Tests with an invalid Approval Type code and a valid Special Review Code */
    @Test
    public void testInvalidApprovalCode() throws Exception {
        ValidSpecialReviewApproval specialReviewApproval = (ValidSpecialReviewApproval)maintDoc.getDocumentBusinessObject();
        specialReviewApproval.setApprovalTypeCode("-9999");
        specialReviewApproval.setSpecialReviewCode("1");
        boolean valid = rule.processCustomRouteDocumentBusinessRules(maintDoc);
        assertFalse(valid);
        assertEquals(1, GlobalVariables.getErrorMap().getErrorCount());
        assertEquals(1, GlobalVariables.getErrorMap().getErrorMessagesForProperty(Constants.VALID_SPECIAL_REVIEW_APPROVAL_TYPE_CODE_KEY).size());
    }
    
    /** Tests with a valid Approval Type code and a valid Special Review Code */
    @Test
    public void testBothValid() throws Exception {
        ValidSpecialReviewApproval specialReviewApproval = (ValidSpecialReviewApproval)maintDoc.getDocumentBusinessObject();
        specialReviewApproval.setApprovalTypeCode("4");
        specialReviewApproval.setSpecialReviewCode("1");
        boolean valid = rule.processCustomRouteDocumentBusinessRules(maintDoc);
        assertTrue(valid);
        assertEquals(0, GlobalVariables.getErrorMap().getErrorCount());
    }
}
