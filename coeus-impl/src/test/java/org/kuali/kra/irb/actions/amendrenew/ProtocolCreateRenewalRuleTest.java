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

import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.sys.impl.validation.ErrorReporterImpl;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.krad.util.ErrorMessage;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.MessageMap;

import java.util.List;

import static org.junit.Assert.*;

public class ProtocolCreateRenewalRuleTest {
    private static final String PROPERTY_KEY = "key";
    private static final String SUMMARY = "summary";

    @Before
    public void setUp() throws Exception {
        GlobalVariables.setMessageMap(new MessageMap());
    }

    @Test
    public void testOK() {

        //CreateRenewalEvent<?> event = new CreateRenewalEvent<CreateRenewalRule>(null, PROPERTY_KEY, SUMMARY);
        CreateRenewalEvent event = new CreateRenewalEvent(null, PROPERTY_KEY, SUMMARY);

        CreateRenewalRule rule = new CreateRenewalRule();
        rule.setErrorReporter(new ErrorReporterImpl());
        assertTrue(rule.processRules(event));
    }

    @Test
    public void testSummary() {

        //CreateRenewalEvent<?> event = new CreateRenewalEvent<CreateRenewalRule>(null, PROPERTY_KEY, "");
        CreateRenewalEvent event = new CreateRenewalEvent(null, PROPERTY_KEY, "");

        CreateRenewalRule rule = new CreateRenewalRule();
        rule.setErrorReporter(new ErrorReporterImpl());
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
        List errors = GlobalVariables.getMessageMap().getMessages(propertyKey);
        assertNotNull(errors);
        assertTrue(errors.size() == 1);

        ErrorMessage message = (ErrorMessage) errors.get(0);
        assertNotNull(message);
        assertEquals(message.getErrorKey(), errorKey);
    }


}
