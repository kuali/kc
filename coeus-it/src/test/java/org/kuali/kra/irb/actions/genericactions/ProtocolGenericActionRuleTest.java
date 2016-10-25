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
package org.kuali.kra.irb.actions.genericactions;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.Test;
import org.kuali.coeus.org.jmock.lib.legacy.ClassImposteriser;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.irb.test.ProtocolRuleTestBase;
import org.kuali.kra.rules.TemplateRuleTest;

import java.sql.Date;

/**
 * Test the business rules for running a generic action on a Protocol.  Since all actions are using the same rule, 
 * we only need to test the rule for a Close action.
 */
public class ProtocolGenericActionRuleTest extends ProtocolRuleTestBase {
    
    private static final Date ACTION_DATE = new Date(System.currentTimeMillis());
    
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
        new TemplateRuleTest<ProtocolGenericActionEvent, ProtocolGenericActionRule>() {

            @Override
            protected void prerequisite() {
                ProtocolGenericActionBean bean = getMockProtocolGenericActionBean(ACTION_DATE);
                event = new ProtocolGenericActionEvent(null, bean);
                rule = new ProtocolGenericActionRule();
                expectedReturnValue = true;
            }
            
        };
    }
    
    /**
     * Tests an invalid Response Approval with no action date.
     * @throws Exception
     */
    @Test
    public void testNoActionDate() {
        new TemplateRuleTest<ProtocolGenericActionEvent, ProtocolGenericActionRule>() {

            @Override
            protected void prerequisite() {
                ProtocolGenericActionBean bean = getMockProtocolGenericActionBean(null);
                event = new ProtocolGenericActionEvent(null, bean);
                rule = new ProtocolGenericActionRule();
                expectedReturnValue = false;
            }
            
            @Override
            public void checkRuleAssertions() {
                assertError(ACTION_DATE_FIELD, KeyConstants.ERROR_PROTOCOL_GENERIC_ACTION_DATE_REQUIRED);
            }
            
        };
    }
    
    private ProtocolGenericActionBean getMockProtocolGenericActionBean(final Date actionDate) {
        final ProtocolGenericActionBean bean = context.mock(ProtocolGenericActionBean.class);
        
        context.checking(new Expectations() {{
            allowing(bean).getActionDate();
            will(returnValue(actionDate));
            
            allowing(bean).getErrorPropertyKey();
            will(returnValue(Constants.PROTOCOL_CLOSE_ACTION_PROPERTY_KEY));
        }});
        
        return bean;
    }

}
