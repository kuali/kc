/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.kra.irb.actions.modifysubmission;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.actions.submit.*;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.Collections;
import static org.junit.Assert.*;
public class ProtocolModifySubmissionRuleTest extends KcIntegrationTestBase {
    
    private ProtocolModifySubmissionRule rule;
    ProtocolDocument document;
    
    private Mockery context = new JUnit4Mockery() {{
        setImposteriser(ClassImposteriser.INSTANCE);
        setThreadingPolicy(new Synchroniser());
    }};
    
    @Before
    public void setUp() throws Exception {

        rule = new ProtocolModifySubmissionRule();
    }

    @After
    public void tearDown() throws Exception {
        rule = null;
        document = null;
        
    }

    @Test
    public void testOK() {
        ProtocolModifySubmissionBean protocolModifySubmissionBean = getMockProtocolModifySubmissionBean(ProtocolSubmissionType.INITIAL_SUBMISSION, 
                ProtocolSubmissionQualifierType.ANNUAL_SCHEDULED_BY_IRB, ProtocolReviewType.FULL_TYPE_CODE, false, false);
        assertTrue(rule.processModifySubmissionRule(document, protocolModifySubmissionBean));
    }
    
    @Test
    public void testEmpty() {
        ProtocolModifySubmissionBean protocolModifySubmissionBean = getMockProtocolModifySubmissionBean(Constants.EMPTY_STRING, 
                Constants.EMPTY_STRING, Constants.EMPTY_STRING, false, false);
        assertFalse(rule.processModifySubmissionRule(document, protocolModifySubmissionBean));
        assertEquals(2, GlobalVariables.getMessageMap().getErrorCount());
    }
    
    @Test
    public void testExpeditedOK() {
        ProtocolModifySubmissionBean protocolModifySubmissionBean = getMockProtocolModifySubmissionBean(ProtocolSubmissionType.INITIAL_SUBMISSION, 
                ProtocolSubmissionQualifierType.ANNUAL_SCHEDULED_BY_IRB, ProtocolReviewType.EXPEDITED_REVIEW_TYPE_CODE, true, false);
        boolean result = rule.processModifySubmissionRule(document, protocolModifySubmissionBean);
        assertTrue(result);
    }
    
    @Test
    public void testExpeditedNothingChecked() {
        ProtocolModifySubmissionBean protocolModifySubmissionBean = getMockProtocolModifySubmissionBean(ProtocolSubmissionType.INITIAL_SUBMISSION, 
                ProtocolSubmissionQualifierType.ANNUAL_SCHEDULED_BY_IRB, ProtocolReviewType.EXPEDITED_REVIEW_TYPE_CODE, false, false);
        assertFalse(rule.processModifySubmissionRule(document, protocolModifySubmissionBean));
        assertEquals(1, GlobalVariables.getMessageMap().getErrorCount());
    }
    
    @Test
    public void testExemptExpeditedChecked() {
        ProtocolModifySubmissionBean protocolModifySubmissionBean = getMockProtocolModifySubmissionBean(ProtocolSubmissionType.INITIAL_SUBMISSION, 
                ProtocolSubmissionQualifierType.ANNUAL_SCHEDULED_BY_IRB, ProtocolReviewType.EXEMPT_STUDIES_REVIEW_TYPE_CODE, true, false);
        assertFalse(rule.processModifySubmissionRule(document, protocolModifySubmissionBean));
        assertEquals(1, GlobalVariables.getMessageMap().getErrorCount());
    }
    
    @Test
    public void testExemptOK() {
        ProtocolModifySubmissionBean protocolModifySubmissionBean = getMockProtocolModifySubmissionBean(ProtocolSubmissionType.INITIAL_SUBMISSION, 
                ProtocolSubmissionQualifierType.ANNUAL_SCHEDULED_BY_IRB, ProtocolReviewType.EXEMPT_STUDIES_REVIEW_TYPE_CODE, false, true);
        assertTrue(rule.processModifySubmissionRule(document, protocolModifySubmissionBean));
    }
    
    @Test
    public void testExemptNothingChecked() {
        ProtocolModifySubmissionBean protocolModifySubmissionBean = getMockProtocolModifySubmissionBean(ProtocolSubmissionType.INITIAL_SUBMISSION, 
                ProtocolSubmissionQualifierType.ANNUAL_SCHEDULED_BY_IRB, ProtocolReviewType.EXEMPT_STUDIES_REVIEW_TYPE_CODE, false, false);
        assertFalse(rule.processModifySubmissionRule(document, protocolModifySubmissionBean));
        assertEquals(1, GlobalVariables.getMessageMap().getErrorCount());
    }
    
    @Test
    public void testExpeditedExemptChecked() {
        ProtocolModifySubmissionBean protocolModifySubmissionBean = getMockProtocolModifySubmissionBean(ProtocolSubmissionType.INITIAL_SUBMISSION, 
                ProtocolSubmissionQualifierType.ANNUAL_SCHEDULED_BY_IRB, ProtocolReviewType.EXPEDITED_REVIEW_TYPE_CODE, false, true);
        assertFalse(rule.processModifySubmissionRule(document, protocolModifySubmissionBean));
        assertEquals(1, GlobalVariables.getMessageMap().getErrorCount());
    }
    
    private ProtocolModifySubmissionBean getMockProtocolModifySubmissionBean(final String protocolSubmissionTypeCode, 
            final String protocolSubmissionQualifierTypeCode, final String protocolReviewTypeCode, final boolean expeditedChecklistChecked, 
            final boolean exemptChecklistChecked) {
        
        final ProtocolModifySubmissionBean bean = context.mock(ProtocolModifySubmissionBean.class);
        final ExpeditedReviewCheckListItem expeditedItem = context.mock(ExpeditedReviewCheckListItem.class);
        final ExemptStudiesCheckListItem exemptItem = context.mock(ExemptStudiesCheckListItem.class);
        
        context.checking(new Expectations() {{
            allowing(bean).getSubmissionTypeCode();
            will(returnValue(protocolSubmissionTypeCode));
            
            allowing(bean).getSubmissionQualifierTypeCode();
            will(returnValue(protocolSubmissionQualifierTypeCode));
            
            allowing(bean).getProtocolReviewTypeCode();
            will(returnValue(protocolReviewTypeCode));
            
            allowing(bean).isBillable();
            will(returnValue(true));
            
            allowing(bean).getExpeditedReviewCheckList();
            will(returnValue(Collections.singletonList(expeditedItem)));
            
            allowing(expeditedItem).getChecked();
            will(returnValue(expeditedChecklistChecked));
            
            allowing(bean).getExemptStudiesCheckList();
            will(returnValue(Collections.singletonList(exemptItem)));
            
            allowing(exemptItem).getChecked();
            will(returnValue(exemptChecklistChecked));
        }});
        
        return bean;
    }

}
