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
package org.kuali.kra.irb.actions.grantexemption;

import java.sql.Date;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Test;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.irb.test.ProtocolRuleTestBase;
import org.kuali.kra.rules.TemplateRuleTest;
import org.kuali.kra.util.DateUtils;

/**
 * Test the business rules for granting an exemption on a Protocol.
 */
public class ProtocolGrantExemptionRuleTest extends ProtocolRuleTestBase {
    
    private static final Date ACTION_DATE = new Date(System.currentTimeMillis());
    private static final Date APPROVAL_DATE = DateUtils.convertToSqlDate(DateUtils.addWeeks(ACTION_DATE, -1));
    
    private static final String APPROVAL_DATE_FIELD = "approvalDate";
    private static final String ACTION_DATE_FIELD = "actionDate";
    
    private Mockery context = new JUnit4Mockery() {{
        setImposteriser(ClassImposteriser.INSTANCE);
    }};

    /**
     * Tests a valid Approval.
     * @throws Exception
     */
    @Test
    public void testOk() {
        new TemplateRuleTest<ProtocolGrantExemptionEvent, ProtocolGrantExemptionRule>() {

            @Override
            protected void prerequisite() {
                ProtocolGrantExemptionBean bean = getMockProtocolGrantExemptionBean(APPROVAL_DATE, ACTION_DATE);
                event = new ProtocolGrantExemptionEvent(null, bean);
                rule = new ProtocolGrantExemptionRule();
                expectedReturnValue = true;
            }
            
        };
    }

    /**
     * Tests an invalid Response Approval with no approval date.
     * @throws Exception
     */
    @Test
    public void testNoApprovalDate() {
        new TemplateRuleTest<ProtocolGrantExemptionEvent, ProtocolGrantExemptionRule>() {

            @Override
            protected void prerequisite() {
                ProtocolGrantExemptionBean bean = getMockProtocolGrantExemptionBean(null, ACTION_DATE);
                event = new ProtocolGrantExemptionEvent(null, bean);
                rule = new ProtocolGrantExemptionRule();
                expectedReturnValue = false;
            }
            
            @Override
            public void checkRuleAssertions() {
                assertError(APPROVAL_DATE_FIELD, KeyConstants.ERROR_PROTOCOL_APPROVAL_DATE_REQUIRED);
            }
            
        };
    }
    
    /**
     * Tests an invalid Response Approval with no action date.
     * @throws Exception
     */
    @Test
    public void testNoActionDate() {
        new TemplateRuleTest<ProtocolGrantExemptionEvent, ProtocolGrantExemptionRule>() {

            @Override
            protected void prerequisite() {
                ProtocolGrantExemptionBean bean = getMockProtocolGrantExemptionBean(APPROVAL_DATE, null);
                event = new ProtocolGrantExemptionEvent(null, bean);
                rule = new ProtocolGrantExemptionRule();
                expectedReturnValue = false;
            }
            
            @Override
            public void checkRuleAssertions() {
                assertError(ACTION_DATE_FIELD, KeyConstants.ERROR_PROTOCOL_GENERIC_ACTION_DATE_REQUIRED);
            }
            
        };
    }
    
    private ProtocolGrantExemptionBean getMockProtocolGrantExemptionBean(final Date approvalDate, final Date actionDate) {
        final ProtocolGrantExemptionBean bean = context.mock(ProtocolGrantExemptionBean.class);
        
        context.checking(new Expectations() {{
            allowing(bean).getApprovalDate();
            will(returnValue(approvalDate));
            
            allowing(bean).getActionDate();
            will(returnValue(actionDate));
            
            allowing(bean).getErrorPropertyKey();
            will(returnValue(Constants.PROTOCOL_GRANT_EXEMPTION_ACTION_PROPERTY_KEY));
        }});
        
        return bean;
    }
    
}