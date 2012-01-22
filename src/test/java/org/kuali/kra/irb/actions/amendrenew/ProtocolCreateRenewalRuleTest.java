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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.krad.util.ErrorMessage;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.MessageMap;
import org.springframework.util.AutoPopulatingList;

public class ProtocolCreateRenewalRuleTest {
    private static final String PROPERTY_KEY = "key";
    private static final String SUMMARY = "summary";

    @Before
    public void setUp() throws Exception {
        GlobalVariables.setMessageMap(new MessageMap());
    }

    @Test
    public void testOK() {

        CreateRenewalEvent<?> event = new CreateRenewalEvent<CreateRenewalRule>(null, PROPERTY_KEY, SUMMARY);

        CreateRenewalRule rule = new CreateRenewalRule();
        assertTrue(rule.processRules(event));
    }

    @Test
    public void testSummary() {

        CreateRenewalEvent<?> event = new CreateRenewalEvent<CreateRenewalRule>(null, PROPERTY_KEY, "");

        CreateRenewalRule rule = new CreateRenewalRule();
        assertFalse(rule.processRules(event));
        assertError(PROPERTY_KEY, KeyConstants.ERROR_PROTOCOL_SUMMARY_IS_REQUIRED);
    }


    /**
     * Assert an error. The Error Map should have one error with the given property key and error key.
     * 
     * @param propertyKey
     * @param errorKey
     */
    protected void assertError(String propertyKey, String errorKey) {
        AutoPopulatingList errors = GlobalVariables.getMessageMap().getMessages(propertyKey);
        assertNotNull(errors);
        assertTrue(errors.size() == 1);

        ErrorMessage message = (ErrorMessage) errors.get(0);
        assertNotNull(message);
        assertEquals(message.getErrorKey(), errorKey);
    }


}
