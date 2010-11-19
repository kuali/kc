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
package org.kuali.kra.irb.actions.amendrenew;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
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
    }};
    
    @Test
    public void testOK() {
        new TemplateRuleTest<CreateAmendmentEvent<CreateAmendmentRule>, CreateAmendmentRule> () {

            @Override
            protected void prerequisite() {
                ProtocolAmendmentBean bean = getMockProtocolAmendmentBean(SUMMARY, true);
                
                event = new CreateAmendmentEvent<CreateAmendmentRule>(null, PROPERTY_KEY, bean);
                rule = new CreateAmendmentRule();
                expectedReturnValue = true;
            }
            
        };
    }
    
    @Test
    public void testSummary() {
        new TemplateRuleTest<CreateAmendmentEvent<CreateAmendmentRule>, CreateAmendmentRule> () {

            @Override
            protected void prerequisite() {
                ProtocolAmendmentBean bean = getMockProtocolAmendmentBean(Constants.EMPTY_STRING, true);
                
                event = new CreateAmendmentEvent<CreateAmendmentRule>(null, PROPERTY_KEY, bean);
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
        new TemplateRuleTest<CreateAmendmentEvent<CreateAmendmentRule>, CreateAmendmentRule> () {

            @Override
            protected void prerequisite() {
                ProtocolAmendmentBean bean = getMockProtocolAmendmentBean(SUMMARY, false);
                
                event = new CreateAmendmentEvent<CreateAmendmentRule>(null, PROPERTY_KEY, bean);
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