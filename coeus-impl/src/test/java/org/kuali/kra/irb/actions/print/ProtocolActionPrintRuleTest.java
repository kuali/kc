/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.kra.irb.actions.print;

import org.junit.Test;
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
