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
package org.kuali.kra.irb.actions.noreview;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.org.jmock.lib.legacy.ClassImposteriser;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.krad.util.GlobalVariables;

import java.sql.Date;
import static org.junit.Assert.*;
public class ProtocolReviewNotRequiredRuleTest extends KcIntegrationTestBase {
    
    private static final String COMMENTS = "really cool comments";
    private static final Date ACTION_DATE = new Date(System.currentTimeMillis());
    private static final Date DECISION_DATE = new Date(System.currentTimeMillis());
    
    private ProtocolReviewNotRequiredRule rule;
    
    @Before
    public void setUp() throws Exception {

        rule = new ProtocolReviewNotRequiredRule();
    }

    @After
    public void tearDown() throws Exception {
        rule = null;
        
    }
    
    private Mockery context = new JUnit4Mockery() {{
        setImposteriser(ClassImposteriser.INSTANCE);
        setThreadingPolicy(new Synchroniser());
    }};

    @Test
    public void testOK() {
        ProtocolReviewNotRequiredBean protocolReviewNotRequredBean = getMockProtocolReviewNotRequiredBean(COMMENTS, ACTION_DATE, DECISION_DATE);
        assertTrue(rule.processReviewNotRequiredRule(null, protocolReviewNotRequredBean));
        assertEquals(0, GlobalVariables.getMessageMap().getErrorCount());
    }
    
    @Test
    public void testNoComments() {
        ProtocolReviewNotRequiredBean protocolReviewNotRequredBean = getMockProtocolReviewNotRequiredBean(null, ACTION_DATE, DECISION_DATE);
        assertTrue(rule.processReviewNotRequiredRule(null, protocolReviewNotRequredBean));
        assertEquals(0, GlobalVariables.getMessageMap().getErrorCount());
    }
    
    @Test
    public void testNoActionDate() {
        ProtocolReviewNotRequiredBean protocolReviewNotRequredBean = getMockProtocolReviewNotRequiredBean(COMMENTS, null, DECISION_DATE);
        assertFalse(rule.processReviewNotRequiredRule(null, protocolReviewNotRequredBean));
        assertEquals(1, GlobalVariables.getMessageMap().getErrorCount());
    }
    
    @Test
    public void testNoDecisionDate() {
        ProtocolReviewNotRequiredBean protocolReviewNotRequredBean = getMockProtocolReviewNotRequiredBean(COMMENTS, ACTION_DATE, null);
        assertFalse(rule.processReviewNotRequiredRule(null, protocolReviewNotRequredBean));
        assertEquals(1, GlobalVariables.getMessageMap().getErrorCount());
    }
    
    private ProtocolReviewNotRequiredBean getMockProtocolReviewNotRequiredBean(final String comments, final Date actionDate, final Date decisionDate) {
        final ProtocolReviewNotRequiredBean bean = context.mock(ProtocolReviewNotRequiredBean.class);
        
        context.checking(new Expectations() {{
            allowing(bean).getComments();
            will(returnValue(comments));
            
            allowing(bean).getActionDate();
            will(returnValue(actionDate));
            
            allowing(bean).getDecisionDate();
            will(returnValue(decisionDate));
        }});
        
        return bean;
    }

}
