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
package org.kuali.kra.committee.rules;

import org.junit.Test;
import org.kuali.coeus.sys.impl.validation.ErrorReporterImpl;
import org.kuali.kra.committee.rule.event.CommitteeActionPrintCommitteeDocumentEvent;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.rules.TemplateRuleTest;

public class CommitteeActionPrintCommitteeDocumentRuleTest {

    @Test
    public void testTrue() {
        
        new TemplateRuleTest<CommitteeActionPrintCommitteeDocumentEvent, CommitteeActionPrintCommitteeDocumentRule>() {

            @Override
            protected void prerequisite() {
                event = new CommitteeActionPrintCommitteeDocumentEvent(Constants.EMPTY_STRING, null, true, true);
                rule = new CommitteeActionPrintCommitteeDocumentRule();
                rule.setErrorReporter(new ErrorReporterImpl());
                expectedReturnValue = true;
            }
        };
    }

    @Test
    public void testFalse() {
        
        new TemplateRuleTest<CommitteeActionPrintCommitteeDocumentEvent, CommitteeActionPrintCommitteeDocumentRule>() {

            @Override
            protected void prerequisite() {
                event = new CommitteeActionPrintCommitteeDocumentEvent(Constants.EMPTY_STRING, null, false, false);
                rule = new CommitteeActionPrintCommitteeDocumentRule();
                rule.setErrorReporter(new ErrorReporterImpl());
                expectedReturnValue = false;
            }
        };
    }

}
