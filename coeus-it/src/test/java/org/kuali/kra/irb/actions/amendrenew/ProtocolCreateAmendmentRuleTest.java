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
package org.kuali.kra.irb.actions.amendrenew;

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

public class ProtocolCreateAmendmentRuleTest extends ProtocolRuleTestBase {

    private static final String PROPERTY_KEY = "key";
    private static final String SUMMARY = "summary";
    
    private Mockery context = new JUnit4Mockery() {{
        setImposteriser(ClassImposteriser.INSTANCE);
        setThreadingPolicy(new Synchroniser());
    }};
    
    @Test
    public void testOK() {
        //new TemplateRuleTest<CreateAmendmentEvent<CreateAmendmentRule>, CreateAmendmentRule> () {
        new TemplateRuleTest<CreateAmendmentEvent, CreateAmendmentRule> () {

            @Override
            protected void prerequisite() {
                ProtocolAmendmentBean bean = getMockProtocolAmendmentBean(SUMMARY, true);
                
                //event = new CreateAmendmentEvent<CreateAmendmentRule>(null, PROPERTY_KEY, bean);
                event = new CreateAmendmentEvent(null, PROPERTY_KEY, bean);
                rule = new CreateAmendmentRule();
                expectedReturnValue = true;
            }
            
        };
    }
    
    @Test
    public void testSummary() {
        //new TemplateRuleTest<CreateAmendmentEvent<CreateAmendmentRule>, CreateAmendmentRule> () {
        new TemplateRuleTest<CreateAmendmentEvent, CreateAmendmentRule> () {

            @Override
            protected void prerequisite() {
                ProtocolAmendmentBean bean = getMockProtocolAmendmentBean(Constants.EMPTY_STRING, true);
                
                //event = new CreateAmendmentEvent<CreateAmendmentRule>(null, PROPERTY_KEY, bean);
                event = new CreateAmendmentEvent(null, PROPERTY_KEY, bean);
                rule = new CreateAmendmentRule();
                expectedReturnValue = false;
            }
            
            @Override
            public void checkRuleAssertions() {
                assertError(PROPERTY_KEY, KeyConstants.ERROR_PROTOCOL_SUMMARY_IS_REQUIRED);
            }
            
        };
    }
    
    @Test
    public void testSelection() {
        //new TemplateRuleTest<CreateAmendmentEvent<CreateAmendmentRule>, CreateAmendmentRule> () {
        new TemplateRuleTest<CreateAmendmentEvent, CreateAmendmentRule> () {

            @Override
            protected void prerequisite() {
                ProtocolAmendmentBean bean = getMockProtocolAmendmentBean(SUMMARY, false);
                
                //event = new CreateAmendmentEvent<CreateAmendmentRule>(null, PROPERTY_KEY, bean);
                event = new CreateAmendmentEvent(null, PROPERTY_KEY, bean);
                rule = new CreateAmendmentRule();
                expectedReturnValue = false;
            }
            
            @Override
            public void checkRuleAssertions() {
                assertError(PROPERTY_KEY, KeyConstants.ERROR_PROTOCOL_SELECT_MODULE);
            }
            
        };
    }
    
    private ProtocolAmendmentBean getMockProtocolAmendmentBean(final String summary, final boolean someSelected) {
        final ProtocolAmendmentBean bean = context.mock(ProtocolAmendmentBean.class);
        
        context.checking(new Expectations() {{
            allowing(bean).getSummary();
            will(returnValue(summary));
            
            allowing(bean).isSomeSelected();
            will(returnValue(someSelected));
        }});
        
        return bean;
    }
    
}
