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
package org.kuali.kra.irb.actions.noreview;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.rice.kns.util.GlobalVariables;

public class ProtocolReviewNotRequiredRuleTest {
    ProtocolDocument protocolDocument;
    ProtocolReviewNotRequiredBean actionBean;
    ProtocolReviewNotRequiredRule rule;
    @Before
    public void setUp() throws Exception {
        protocolDocument = null; //a valid protocol document isn't required for this test.
        actionBean = createValidProtocolReviewNotRequiredBean();
        rule = new ProtocolReviewNotRequiredRule();
    }

    @After
    public void tearDown() throws Exception {
        rule = null;
        actionBean = null;
        GlobalVariables.getMessageMap().clearErrorMessages();
    }
    
    private ProtocolReviewNotRequiredBean createValidProtocolReviewNotRequiredBean() {
        ProtocolReviewNotRequiredBean bean = new ProtocolReviewNotRequiredBean();
        bean.setComments("really cool comments");
        bean.setActionDate(new Date(System.currentTimeMillis()));
        bean.setDecisionDate(new Date(System.currentTimeMillis()));
        return bean;
    }

    @Test
    public void testProcessReviewNotRequiredRule1() {
        boolean result = rule.processReviewNotRequiredRule(protocolDocument, actionBean);
        assertTrue(result);
        assertEquals(0, GlobalVariables.getMessageMap().getErrorCount());
    }
    
    @Test
    public void testProcessReviewNotRequiredRule2() {
        actionBean.setActionDate(null);
        boolean result = rule.processReviewNotRequiredRule(protocolDocument, actionBean);
        assertFalse(result);
        assertEquals(1, GlobalVariables.getMessageMap().getErrorCount());
    }
    
    @Test
    public void testProcessReviewNotRequiredRule3() {
        actionBean.setDecisionDate(null);
        boolean result = rule.processReviewNotRequiredRule(protocolDocument, actionBean);
        assertFalse(result);
        assertEquals(1, GlobalVariables.getMessageMap().getErrorCount());
    }
    
    @Test
    public void testProcessReviewNotRequiredRule4() {
        actionBean.setComments(null);
        boolean result = rule.processReviewNotRequiredRule(protocolDocument, actionBean);
        assertTrue(result);
        assertEquals(0, GlobalVariables.getMessageMap().getErrorCount());
    }

}
