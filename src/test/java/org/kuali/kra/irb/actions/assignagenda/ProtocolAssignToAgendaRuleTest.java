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
import org.junit.Test;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.irb.test.ProtocolRuleTestBase;
import org.kuali.kra.rules.TemplateRuleTest;

/**
 * Test the business rules for Assigning a protocol to a committee.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class ProtocolAssignToAgendaRuleTest extends ProtocolRuleTestBase {
    
    private static final String COMMITTEE_ID = "10014";
    private static final Date ACTION_DATE = new Date(System.currentTimeMillis());
    
    private static final String COMMITTEE_ID_FIELD = "committeeId";
    
    private Mockery context = new JUnit4Mockery() {{
        setImposteriser(ClassImposteriser.INSTANCE);
    }};

    /**
     * Test a valid assignment.
     * @throws Exception
     */
    @Test
    public void testHasCommittee() throws Exception {
        new TemplateRuleTest<ProtocolAssignToAgendaEvent, ProtocolAssignToAgendaRule>() {

            @Override
            protected void prerequisite() {
                event = new ProtocolAssignToAgendaEvent(null, getMockAssignToAgendaBean(COMMITTEE_ID));
                rule = new ProtocolAssignToAgendaRule();
                expectedReturnValue = true;
            }
            
        };
    }

    /**
     * Test an invalid assignment, no committee selected.
     * @throws Exception
     */
    @Test
    public void testNoCommittee() throws Exception {
        new TemplateRuleTest<ProtocolAssignToAgendaEvent, ProtocolAssignToAgendaRule>() {

            @Override
            protected void prerequisite() {
                event = new ProtocolAssignToAgendaEvent(null, getMockAssignToAgendaBean(Constants.EMPTY_STRING));
                rule = new ProtocolAssignToAgendaRule();
                expectedReturnValue = false;
            }
            
            @Override
            public void checkRuleAssertions() {
                assertError(COMMITTEE_ID_FIELD, KeyConstants.ERROR_PROTOCOL_COMMITTEE_NOT_SELECTED);
            }
            
        };
    }
    
    private ProtocolAssignToAgendaBean getMockAssignToAgendaBean(final String committeeId) {
        final ProtocolAssignToAgendaBean bean = context.mock(ProtocolAssignToAgendaBean.class);
        
        context.checking(new Expectations() {{
           allowing(bean).getCommitteeId();
           will(returnValue(committeeId));
           
           allowing(bean).getActionDate();
           will(returnValue(ACTION_DATE));
           
           allowing(bean).isProtocolAssigned();
           will(returnValue(true));
        }});
        
        return bean;
    }
    
}