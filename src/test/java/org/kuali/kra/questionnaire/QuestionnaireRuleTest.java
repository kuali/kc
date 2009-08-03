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
package org.kuali.kra.questionnaire;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.KraTestBase;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.kns.UserSession;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.ErrorMap;
import org.kuali.rice.kns.util.GlobalVariables;

public class QuestionnaireRuleTest extends KraTestBase {


    @Before
    public void setUp() throws Exception {
        //GlobalVariables.setUserSession(new UserSession("quickstart"));
        super.setUp();
        GlobalVariables.setUserSession(new UserSession("quickstart"));
        GlobalVariables.setErrorMap(new ErrorMap());
    }

    @After
    public void tearDown() throws Exception {
        //GlobalVariables.setUserSession(null);
        GlobalVariables.setUserSession(null);
        GlobalVariables.setErrorMap(null);
        super.tearDown();
    }

    @Test
    public void testQuestionnaireRule() throws Exception {

        QuestionnaireRule questionnaireRule = new QuestionnaireRule();
        Questionnaire questionnaire = new Questionnaire();
        QuestionnaireForm questionnaireForm = new QuestionnaireForm();
        questionnaireForm.setNewQuestionnaire(questionnaire);
        assertFalse(questionnaireRule.questionnaireRequiredFields(questionnaireForm));
        questionnaire.setName("test name");
        assertFalse(questionnaireRule.questionnaireRequiredFields(questionnaireForm));
        questionnaire.setDescription("test name");
        assertTrue(questionnaireRule.questionnaireRequiredFields(questionnaireForm));
        Questionnaire questionnaire1 = new Questionnaire();
        questionnaire1.setName("test name");
        questionnaire1.setDescription("test name");
        // questionnaire will not be persisted, but seq_questionnaire_id will increase by 1
        KraServiceLocator.getService(BusinessObjectService.class).save(questionnaire1);
        assertFalse(questionnaireRule.questionnaireRequiredFields(questionnaireForm));
        questionnaire.setQuestionnaireId(questionnaire1.getQuestionnaireId()+1);
        assertFalse(questionnaireRule.questionnaireRequiredFields(questionnaireForm));
        questionnaire.setQuestionnaireId(questionnaire1.getQuestionnaireId());
        assertTrue(questionnaireRule.questionnaireRequiredFields(questionnaireForm));
        

    }

}
