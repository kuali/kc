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
package org.kuali.kra.irb.protocol.questionnaire;

import org.junit.Assert;
import org.junit.Test;
import org.kuali.coeus.sys.framework.validation.ErrorReporter;
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
