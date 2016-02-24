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
package org.kuali.kra.irb.protocol.questionnaire;

import org.junit.Assert;
import org.junit.Test;
import org.kuali.coeus.sys.impl.validation.ErrorReporterImpl;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.irb.questionnaire.SaveProtocolQuestionnaireEvent;
import org.kuali.kra.irb.questionnaire.SaveProtocolQuestionnaireRule;
import org.kuali.coeus.common.questionnaire.framework.answer.AnswerHeader;
import org.kuali.kra.rules.TemplateRuleTest;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
public class SaveProtocolQuestionnaireRuleTest {
    
    
    /**
     * 
     * This method is to test that the rule passes correctly
     */
    @Test
    public void testRuleDoesNotFindErrors() {
        
        //case 1: empty header list
        new TemplateRuleTest<SaveProtocolQuestionnaireEvent, SaveProtocolQuestionnaireRule>() {            
            @Override
            protected void prerequisite() {
                List<AnswerHeader> answerHeaders = new ArrayList<AnswerHeader>();
                event = new SaveProtocolQuestionnaireEvent(null, answerHeaders);
                rule = new SaveProtocolQuestionnaireRule();
                rule.setErrorReporter(new ErrorReporterImpl());
                expectedReturnValue = true;
            }
            
            @Override
            public void checkRuleAssertions() {
                assertEquals(0, getErrorMap().getErrorMessages().size());
            }            
        };
        
        // case 2: 4 headers, none having a true value for the not updated flag 
        new TemplateRuleTest<SaveProtocolQuestionnaireEvent, SaveProtocolQuestionnaireRule>() {            
            @Override
            protected void prerequisite() {
                List<AnswerHeader> answerHeaders = new ArrayList<AnswerHeader>();
                // add to the list, by default the "not updated" flags for new headers are false
                answerHeaders.add(new AnswerHeader());
                answerHeaders.add(new AnswerHeader());
                answerHeaders.add(new AnswerHeader());
                answerHeaders.add(new AnswerHeader());
                
                event = new SaveProtocolQuestionnaireEvent(null, answerHeaders);
                rule = new SaveProtocolQuestionnaireRule();
                rule.setErrorReporter(new ErrorReporterImpl());
                expectedReturnValue = true;
            }
            
            @Override
            public void checkRuleAssertions() {
                assertEquals(0, getErrorMap().getErrorMessages().size());
            }            
        };
    }
    
    /**
     * 
     * This method is to test that the rule finds errors correctly
     */
    @Test
    public void testRuleFindsErrors() {
        // 4 headers, with 2 of them having "not updated" flag as true
        new TemplateRuleTest<SaveProtocolQuestionnaireEvent, SaveProtocolQuestionnaireRule>() {
            @Override
            protected void prerequisite() {
                List<AnswerHeader> answerHeaders = new ArrayList<AnswerHeader>();
                // add to the list, by default the "not updated" flags for new headers are false
                answerHeaders.add(new AnswerHeader());
                answerHeaders.add(new AnswerHeader());
                answerHeaders.add(new AnswerHeader());
                answerHeaders.add(new AnswerHeader());
                
                answerHeaders.get(0).setNotUpdated(true);
                answerHeaders.get(2).setNotUpdated(true);
                
                event = new SaveProtocolQuestionnaireEvent(null, answerHeaders);
                rule = new SaveProtocolQuestionnaireRule();
                rule.setErrorReporter(new ErrorReporterImpl());
                expectedReturnValue = false;
            }
            
            @Override
            public void checkRuleAssertions() {          
                assertEquals(2, getErrorMap().getErrorMessages().size());
                assertTrue(getErrorMap().getErrorMessages().containsKey("questionnaireHelper.answerHeaders[0]"));
                assertTrue(getErrorMap().getErrorMessages().containsKey("questionnaireHelper.answerHeaders[2]"));
                
                assertFalse(getErrorMap().getErrorMessages().containsKey("questionnaireHelper.answerHeaders[1]"));
                assertFalse(getErrorMap().getErrorMessages().containsKey("questionnaireHelper.answerHeaders[3]"));
                
                Assert.assertEquals(KeyConstants.ERROR_QUESTIONNAIRE_NOT_UPDATED +"()", getErrorMap().getErrorMessages().get("questionnaireHelper.answerHeaders[0]").get(0).toString());
                Assert.assertEquals(KeyConstants.ERROR_QUESTIONNAIRE_NOT_UPDATED + "()", getErrorMap().getErrorMessages().get("questionnaireHelper.answerHeaders[2]").get(0).toString());
            }
            
        };
    }
    
}
