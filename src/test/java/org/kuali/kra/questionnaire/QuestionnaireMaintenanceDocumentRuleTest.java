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
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.maintenance.MaintenanceRuleTestBase;
import org.kuali.rice.kns.UserSession;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.GlobalVariables;

public class QuestionnaireMaintenanceDocumentRuleTest extends MaintenanceRuleTestBase {
    private QuestionnaireMaintenanceDocumentRule rule = null;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        rule = new QuestionnaireMaintenanceDocumentRule();
        GlobalVariables.setUserSession(new UserSession("quickstart"));
    }

    @After
    public void tearDown() throws Exception {
        rule = null;
        super.tearDown();
    }
    
    @Test
    public void testQuestionnaireRule() throws Exception {

        QuestionnaireRule questionnaireRule = new QuestionnaireRule();
        Questionnaire questionnaire = new Questionnaire();
        MaintenanceDocument questionnaireMaintenanceDocument = newMaintDoc(questionnaire);
        assertFalse(rule.processCustomRouteDocumentBusinessRules(questionnaireMaintenanceDocument));
        questionnaire.setName("test name");
        assertFalse(rule.processCustomRouteDocumentBusinessRules(questionnaireMaintenanceDocument));
        questionnaire.setDescription("test name");
        assertTrue(rule.processCustomRouteDocumentBusinessRules(questionnaireMaintenanceDocument));
        Questionnaire questionnaire1 = new Questionnaire();
        questionnaire1.setName("test name");
        questionnaire1.setDescription("test name");
        questionnaire1.setSequenceNumber(1);
        questionnaireMaintenanceDocument = newMaintDoc(questionnaire1);
        assertTrue(rule.processCustomRouteDocumentBusinessRules(questionnaireMaintenanceDocument));
        KraServiceLocator.getService(BusinessObjectService.class).save(questionnaire1);
        questionnaire1.setQuestionnaireRefId(null);
        questionnaire1.setQuestionnaireId(null);
        assertFalse(rule.processCustomRouteDocumentBusinessRules(questionnaireMaintenanceDocument));
        

    }
    
}
