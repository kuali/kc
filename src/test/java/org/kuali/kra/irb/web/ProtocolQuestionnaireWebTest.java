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
package org.kuali.kra.irb.web;

import org.junit.Test;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.rice.test.data.PerSuiteUnitTestData;
import org.kuali.rice.test.data.UnitTestData;
import org.kuali.rice.test.data.UnitTestFile;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
@PerSuiteUnitTestData(@UnitTestData(sqlFiles = {
        @UnitTestFile(filename = "classpath:sql/dml/load_questionnaire.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_COEUS_MODULE.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_questionnaire_question.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_questionnaire_usage.sql", delimiter = ";") }))

public class ProtocolQuestionnaireWebTest extends ProtocolWebTestBase {
    
    @Test
    public void testQuestionnairePage() throws Exception {
        HtmlPage protocolPage = getProtocolSavedRequiredFieldsPage();
        String documentNumber = getDocNbr(protocolPage);
        HtmlPage questionnairePage = clickOnTab(protocolPage, QUESTIONNAIRE_LINK_NAME);

        assertContains(questionnairePage, "IRB Questionnaire 1 V2 (Incomplete)");
        assertContains(questionnairePage, "IRB Question 2 (Incomplete)");

        setFieldValue(questionnairePage, "questionnaireHelper.answerHeaders[0].answers[0].answer", "Y");
        setFieldValue(questionnairePage, "questionnaireHelper.answerHeaders[0].answers[1].answer", "Y");
        setFieldValue(questionnairePage, "questionnaireHelper.answerHeaders[0].answers[8].answer", "10");
        setFieldValue(questionnairePage, "questionnaireHelper.answerHeaders[0].answers[11].answer", "Arizona");
        setFieldValue(questionnairePage, "questionnaireHelper.answerHeaders[0].answers[13].answer", "01/01/2010");
        setFieldValue(questionnairePage, "questionnaireHelper.answerHeaders[1].answers[0].answer", "Y");
        setFieldValue(questionnairePage, "questionnaireHelper.answerHeaders[1].answers[1].answer", "test");
        setFieldValue(questionnairePage, "questionnaireHelper.answerHeaders[1].answers[4].answer", "goo");
        questionnairePage = saveDoc(questionnairePage);
        
        assertContains(questionnairePage, "Document was successfully saved.");
        assertContains(questionnairePage, "IRB Questionnaire 1 V2 (Incomplete)");
        assertContains(questionnairePage, "IRB Question 2 (Complete)");
        assertContains(questionnairePage, "Arizona");
        assertContains(questionnairePage, "01/01/2010");
        assertContains(questionnairePage, "goo");
        
        ProtocolDocument doc = (ProtocolDocument) getDocument(documentNumber);
        assertNotNull(doc);
    }

}
