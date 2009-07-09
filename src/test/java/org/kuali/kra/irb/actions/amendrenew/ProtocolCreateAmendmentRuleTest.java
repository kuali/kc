/*
 * Copyright 2006-2008 The Kuali Foundation
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

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.kns.util.ErrorMap;
import org.kuali.rice.kns.util.ErrorMessage;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.TypedArrayList;

public class ProtocolCreateAmendmentRuleTest {

    private static final String PROPERTY_KEY = "key";
    private static final String SUMMARY = "summary";

    @Before
    public void setUp() throws Exception {
        GlobalVariables.setErrorMap(new ErrorMap());
    }
    
    @Test
    public void testOK() {
        ProtocolAmendmentBean amendmentBean = new ProtocolAmendmentBean();
        amendmentBean.setAddModifyAttachments(true);
        amendmentBean.setSummary(SUMMARY);
        
        CreateAmendmentEvent<?> event = new CreateAmendmentEvent<CreateAmendmentRule>(null, PROPERTY_KEY, amendmentBean);
        
        CreateAmendmentRule rule = new CreateAmendmentRule();
        assertTrue(rule.processRules(event));
    }
    
    @Test
    public void testSummary() {
        ProtocolAmendmentBean amendmentBean = new ProtocolAmendmentBean();
        amendmentBean.setAddModifyAttachments(true);
        
        CreateAmendmentEvent<?> event = new CreateAmendmentEvent<CreateAmendmentRule>(null, PROPERTY_KEY, amendmentBean);
        
        CreateAmendmentRule rule = new CreateAmendmentRule();
        assertFalse(rule.processRules(event));
        assertError(PROPERTY_KEY, KeyConstants.ERROR_PROTOCOL_SUMMARY_IS_REQUIRED);
    }
    
    @Test
    public void testSelection() {
        ProtocolAmendmentBean amendmentBean = new ProtocolAmendmentBean();
        amendmentBean.setSummary(SUMMARY);
        
        CreateAmendmentEvent<?> event = new CreateAmendmentEvent<CreateAmendmentRule>(null, PROPERTY_KEY, amendmentBean);
        
        CreateAmendmentRule rule = new CreateAmendmentRule();
        assertFalse(rule.processRules(event));
        assertError(PROPERTY_KEY, KeyConstants.ERROR_PROTOCOL_SELECT_MODULE);
    }
    
    /**
     * Assert an error.  The Error Map should have one error with the given
     * property key and error key.
     * @param propertyKey
     * @param errorKey
     */
    protected void assertError(String propertyKey, String errorKey) {
        TypedArrayList errors = GlobalVariables.getErrorMap().getMessages(propertyKey);
        assertNotNull(errors);
        assertTrue(errors.size() == 1);
        
        ErrorMessage message = (ErrorMessage) errors.get(0);
        assertNotNull(message);
        assertEquals(message.getErrorKey(), errorKey);
    }
}
