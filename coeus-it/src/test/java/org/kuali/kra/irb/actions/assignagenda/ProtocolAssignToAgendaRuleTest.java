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
package org.kuali.kra.irb.actions.assignagenda;

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
        setThreadingPolicy(new Synchroniser());
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
