/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.irb.actions.print;

import org.junit.Test;
import org.kuali.coeus.sys.framework.validation.ErrorReporter;
import org.kuali.coeus.sys.impl.validation.ErrorReporterImpl;
import org.kuali.kra.rules.TemplateRuleTest;

public class ProtocolActionPrintRuleTest {
    @Test
    public void testOK() {
        new TemplateRuleTest<ProtocolActionPrintEvent, ProtocolActionPrintRule>() {
            @Override
            protected void prerequisite() {

                event = new ProtocolActionPrintEvent(null, true, false, false, false);
                rule = new ProtocolActionPrintRule();
                rule.setErrorReporter(new ErrorReporterImpl());
                expectedReturnValue = true;
            }
        };


    }

    @Test
    public void testNotOK() {
        new TemplateRuleTest<ProtocolActionPrintEvent, ProtocolActionPrintRule>() {
            @Override
            protected void prerequisite() {

                event = new ProtocolActionPrintEvent(null, false, false, false, false);
                rule = new ProtocolActionPrintRule();
                rule.setErrorReporter(new ErrorReporterImpl());
                expectedReturnValue = false;
            }
        };


    }

}
