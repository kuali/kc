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
package org.kuali.kra.irb.actions.approve;

import org.apache.commons.lang3.time.DateUtils;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Test;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.irb.test.ProtocolRuleTestBase;
import org.kuali.kra.rules.TemplateRuleTest;

import java.sql.Date;

/**
 * Test the business rules for approving a Protocol.  Since all three types of approvals (full, expedited, and response) are using the same rule, 
 * we only need to test the rule for a Full submission.
 */
public class ProtocolApproveRuleTest extends ProtocolRuleTestBase {
    
    private static final Date ACTION_DATE = new Date(System.currentTimeMillis());
    private static final Date APPROVAL_DATE = org.kuali.coeus.sys.framework.util.DateUtils.convertToSqlDate(DateUtils.addWeeks(ACTION_DATE, -1));
    private static final Date EXPIRATION_DATE = org.kuali.coeus.sys.framework.util.DateUtils.convertToSqlDate(DateUtils.addYears(ACTION_DATE, 1));
    
    private static final String APPROVAL_DATE_FIELD = "approvalDate";
    private static final String EXPIRATION_DATE_FIELD = "expirationDate";
    private static final String ACTION_DATE_FIELD = "actionDate";
    
    private Mockery context = new JUnit4Mockery() {{
        setImposteriser(ClassImposteriser.INSTANCE);
        setThreadingPolicy(new Synchroniser());
    }};

    /**
     * Tests a valid Approval.
     * @throws Exception
     */
    @Test
    public void testOk() {
        new TemplateRuleTest<ProtocolApproveEvent, ProtocolApproveRule>() {

            @Override
            protected void prerequisite() {
                ProtocolApproveBean bean = getMockProtocolApproveBean(APPROVAL_DATE, EXPIRATION_DATE, ACTION_DATE);
                event = new ProtocolApproveEvent(null, bean);
                rule = new ProtocolApproveRule();
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
        new TemplateRuleTest<ProtocolApproveEvent, ProtocolApproveRule>() {

            @Override
            protected void prerequisite() {
                ProtocolApproveBean bean = getMockProtocolApproveBean(null, EXPIRATION_DATE, ACTION_DATE);
                event = new ProtocolApproveEvent(null, bean);
                rule = new ProtocolApproveRule();
                expectedReturnValue = false;
            }
            
            @Override
            public void checkRuleAssertions() {
                assertError(APPROVAL_DATE_FIELD, KeyConstants.ERROR_PROTOCOL_APPROVAL_DATE_REQUIRED);
            }
            
        };
    }
    
    /**
     * Tests an invalid Response Approval with no expiration date.
     * @throws Exception
     */
    @Test
    public void testNoExpirationDate() throws Exception {
        new TemplateRuleTest<ProtocolApproveEvent, ProtocolApproveRule>() {

            @Override
            protected void prerequisite() {
                ProtocolApproveBean bean = getMockProtocolApproveBean(APPROVAL_DATE, null, ACTION_DATE);
                event = new ProtocolApproveEvent(null, bean);
                rule = new ProtocolApproveRule();
                expectedReturnValue = false;
            }
            
            @Override
            public void checkRuleAssertions() {
                assertError(EXPIRATION_DATE_FIELD, KeyConstants.ERROR_PROTOCOL_APPROVAL_EXPIRATION_DATE_REQUIRED);
            }
            
        };
    }
    
    /**
     * Tests an invalid Response Approval with no action date.
     * @throws Exception
     */
    @Test
    public void testNoActionDate() {
        new TemplateRuleTest<ProtocolApproveEvent, ProtocolApproveRule>() {

            @Override
            protected void prerequisite() {
                ProtocolApproveBean bean = getMockProtocolApproveBean(APPROVAL_DATE, EXPIRATION_DATE, null);
                event = new ProtocolApproveEvent(null, bean);
                rule = new ProtocolApproveRule();
                expectedReturnValue = false;
            }
            
            @Override
            public void checkRuleAssertions() {
                assertError(ACTION_DATE_FIELD, KeyConstants.ERROR_PROTOCOL_GENERIC_ACTION_DATE_REQUIRED);
            }
            
        };
    }
    
    private ProtocolApproveBean getMockProtocolApproveBean(final Date approvalDate, final Date expirationDate, final Date actionDate) {
        final ProtocolApproveBean bean = context.mock(ProtocolApproveBean.class);
        
        context.checking(new Expectations() {{
            allowing(bean).getApprovalDate();
            will(returnValue(approvalDate));
            
            allowing(bean).getExpirationDate();
            will(returnValue(expirationDate));
            
            allowing(bean).getActionDate();
            will(returnValue(actionDate));
            
            allowing(bean).getErrorPropertyKey();
            will(returnValue(Constants.PROTOCOL_FULL_APPROVAL_ACTION_PROPERTY_KEY));
        }});
        
        return bean;
    }
    
}
