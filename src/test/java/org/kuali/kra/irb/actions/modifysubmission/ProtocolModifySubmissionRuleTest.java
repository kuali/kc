/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.irb.actions.modifysubmission;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.actions.submit.ProtocolExemptStudiesCheckListItem;
import org.kuali.kra.irb.actions.submit.ProtocolExpeditedReviewCheckListItem;
import org.kuali.kra.irb.actions.submit.ProtocolReviewType;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionType;
import org.kuali.kra.irb.test.ProtocolFactory;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.rice.kns.util.GlobalVariables;

public class ProtocolModifySubmissionRuleTest extends KcUnitTestBase {
    ProtocolModifySubmissionAction actionBean;
    ProtocolSubmissionType type;
    ProtocolModifySubmissionRule rule;
    ProtocolSubmission submission;
    ProtocolDocument document;
    @Before
    public void setUp() throws Exception {
        type = new ProtocolSubmissionType();
        type.setDescription("test");
        type.setSubmissionTypeCode(ProtocolSubmissionType.INITIAL_SUBMISSION);
        submission = new ProtocolSubmission();
        submission.setProtocolSubmissionType(type);
        submission.setSubmissionTypeQualifierCode("xyz");
        submission.setProtocolReviewTypeCode(ProtocolReviewType.FULL_TYPE_CODE);
        submission.setBillable(true);
        submission.setExemptStudiesCheckList(new ArrayList<ProtocolExemptStudiesCheckListItem>());
        submission.setExpeditedReviewCheckList(new ArrayList<ProtocolExpeditedReviewCheckListItem>());
        actionBean = new ProtocolModifySubmissionAction(submission);
        rule = new ProtocolModifySubmissionRule();
        document = ProtocolFactory.createProtocolDocument("123");
    }

    @After
    public void tearDown() throws Exception {
        rule = null;
        actionBean = null;
        submission = null;
        document = null;
    }

    @Test
    public void testProcessModifySubmissionRule1() {
        //default test
        boolean result = rule.processModifySubmissionRule(document, actionBean);
        assertTrue(result);
    }
    
    @Test
    public void testProcessModifySubmissionRule2() {
        //no input test
        actionBean.setProtocolReviewTypeCode("");
        actionBean.setSubmissionQualifierTypeCode("");
        actionBean.setSubmissionTypeCode("");
        boolean result = rule.processModifySubmissionRule(document, actionBean);
        assertFalse(result);
        assertEquals(3, GlobalVariables.getErrorMap().size());
    }
    
    @Test
    public void testProcessModifySubmissionRule3() {
        //exempt studies, but nothing checked
        actionBean.setProtocolReviewTypeCode(ProtocolReviewType.EXEMPT_STUDIES_REVIEW_TYPE_CODE);
        boolean result = rule.processModifySubmissionRule(document, actionBean);
        assertFalse(result);
        assertEquals(1, GlobalVariables.getErrorMap().size());
    }
    
    @Test
    public void testProcessModifySubmissionRule4() {
        //exempt studies, one item checked
        actionBean.setProtocolReviewTypeCode(ProtocolReviewType.EXEMPT_STUDIES_REVIEW_TYPE_CODE);
        actionBean.getExemptStudiesCheckList().get(0).setChecked(true);
        boolean result = rule.processModifySubmissionRule(document, actionBean);
        assertTrue(result);
    }
    
    @Test
    public void testProcessModifySubmissionRule5() {
        //expedited, but nothing checked
        actionBean.setProtocolReviewTypeCode(ProtocolReviewType.EXPEDITED_REVIEW_TYPE_CODE);
        boolean result = rule.processModifySubmissionRule(document, actionBean);
        assertFalse(result);
        assertEquals(1, GlobalVariables.getErrorMap().size());
    }
    
    @Test
    public void testProcessModifySubmissionRule6() {
        //expedited, one item checked
        actionBean.setProtocolReviewTypeCode(ProtocolReviewType.EXPEDITED_REVIEW_TYPE_CODE);
        actionBean.getExpeditedReviewCheckList().get(0).setChecked(true);
        boolean result = rule.processModifySubmissionRule(document, actionBean);
        assertTrue(result);
    }
    
    @Test
    public void testProcessModifySubmissionRule7() {
        //expedited, but exempt item checked
        actionBean.setProtocolReviewTypeCode(ProtocolReviewType.EXPEDITED_REVIEW_TYPE_CODE);
        actionBean.getExemptStudiesCheckList().get(0).setChecked(true);
        boolean result = rule.processModifySubmissionRule(document, actionBean);
        assertFalse(result);
        assertEquals(1, GlobalVariables.getErrorMap().size());
    }

}
