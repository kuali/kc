/*
 * Copyright 2005-2013 The Kuali Foundation
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
package org.kuali.kra.irb.actions.noreview;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
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