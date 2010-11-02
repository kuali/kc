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
package org.kuali.kra.irb.actions.assignagenda;

import java.sql.Date;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.test.ProtocolFactory;
import org.kuali.kra.irb.test.ProtocolRuleTestBase;
import org.kuali.rice.kns.util.GlobalVariables;

/**
 * Test the business rules for Assigning a protocol to a committee.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class ProtocolAssignToAgendaRuleTest extends ProtocolRuleTestBase {
    
    private static final String COMMITTEE_ID = "10014";
    private static final Date ACTION_DATE = new Date(System.currentTimeMillis());

    private ProtocolAssignToAgendaRule rule;
    
    private Mockery context = new JUnit4Mockery() {{
        setImposteriser(ClassImposteriser.INSTANCE);
    }};
    
    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        
        rule = new ProtocolAssignToAgendaRule();
    }

    @Override
    @After
    public void tearDown() throws Exception {
        rule = null;
        
        super.tearDown();
    }

    /**
     * Test a valid assignment.
     * @throws Exception
     */
    @Test
    public void testHasCommittee() throws Exception {
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
        
        assertTrue(rule.processAssignToAgendaRule(protocolDocument, getMockAssignToAgendaBean(COMMITTEE_ID)));
        assertTrue(GlobalVariables.getMessageMap().hasNoErrors());
    }

    /**
     * Test an invalid assignment, no committee selected.
     * @throws Exception
     */
    @Test
    public void testNoCommittee() throws Exception {
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
        
        assertFalse(rule.processAssignToAgendaRule(protocolDocument, getMockAssignToAgendaBean(Constants.EMPTY_STRING)));
        assertError(Constants.PROTOCOL_ASSIGN_TO_AGENDA_PROPERTY_KEY + ".committeeId", 
                    KeyConstants.ERROR_PROTOCOL_COMMITTEE_NOT_SELECTED);
    }
    
    private ProtocolAssignToAgendaBean getMockAssignToAgendaBean(final String committeeId) {
        final ProtocolAssignToAgendaBean bean = context.mock(ProtocolAssignToAgendaBean.class);
        
        context.checking(new Expectations() {{
           allowing(bean).getCommitteeId();
           will(returnValue(committeeId));
           
           allowing(bean).getActionDate();
           will(returnValue(ACTION_DATE));
        }});
        
        return bean;
    }
    
}