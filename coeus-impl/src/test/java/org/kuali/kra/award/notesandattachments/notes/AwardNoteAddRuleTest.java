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
package org.kuali.kra.award.notesandattachments.notes;

import org.junit.Test;
import org.kuali.coeus.sys.impl.validation.ErrorReporterImpl;
import org.kuali.kra.award.notesandattachments.notes.AwardNoteEventBase.ErrorType;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.rules.TemplateRuleTest;

public class AwardNoteAddRuleTest {

    @Test
    public void testOK() {
        new TemplateRuleTest<AwardNoteAddEvent, AwardNoteAddRule>() {
            @Override
            protected void prerequisite() {

                AwardNotepad awardNotepad = new AwardNotepad();
                awardNotepad.setNoteTopic("test");
                event = new AwardNoteAddEvent(Constants.EMPTY_STRING, null, awardNotepad, ErrorType.HARDERROR);
                rule = new AwardNoteAddRule();
                rule.setErrorReporter(new ErrorReporterImpl());
                expectedReturnValue = true;
            }
        };


    }

    @Test
    public void testNotOK() {
        new TemplateRuleTest<AwardNoteAddEvent, AwardNoteAddRule>() {
            @Override
            protected void prerequisite() {

                AwardNotepad awardNotepad = new AwardNotepad();
                awardNotepad.setNoteTopic("");
                event = new AwardNoteAddEvent(Constants.EMPTY_STRING, null, awardNotepad, ErrorType.HARDERROR);
                rule = new AwardNoteAddRule();
                rule.setErrorReporter(new ErrorReporterImpl());
                expectedReturnValue = false;
            }
        };


    }

}
