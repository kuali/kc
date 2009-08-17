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

import java.util.HashMap;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.KraTestBase;
import org.kuali.rice.kns.UserSession;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.test.data.PerSuiteUnitTestData;
import org.kuali.rice.test.data.UnitTestData;
@PerSuiteUnitTestData({   
        @UnitTestData(filename = "classpath:sql/dml/load_COEUS_MODULE.sql", delimiter = ";"),
        @UnitTestData(filename = "classpath:sql/dml/load_QUESTION.sql", delimiter = ";"),
        @UnitTestData("insert into questionnaire (QUESTIONNAIRE_ID,NAME,DESCRIPTION,UPDATE_TIMESTAMP,UPDATE_USER,IS_FINAL,VER_NBR,OBJ_ID )" + 
        " values (1,'test','test',sysdate,user,'N',1,'test')")})

public class QuestionnaireDaoTest extends KraTestBase {
    private String[] qScripts = { "", "insert into Q10,1,0,'N','','',1,user,sysdate)","" ,"","",""};
    private String[] uScripts = { "", "insert U;1;Award Label" };
    private String[] uScripts1 = { "", "delete U;1" };
    private QuestionnaireDao qDao;
    private BusinessObjectService bos;
    private static final Long TEST_QN_ID = new Long(1); 

    @Before
    public void setUp() throws Exception {
        super.setUp();
        qDao = getService(QuestionnaireDao.class);
        bos = getService(BusinessObjectService.class);
        GlobalVariables.setUserSession(new UserSession("quickstart"));
    }

    @After
    public void tearDown() throws Exception {
        GlobalVariables.setUserSession(null);
        qDao = null;
        bos = null;
        super.tearDown();
    }

    @Test
    public void testUpdateMove() {
        qScripts[2] = "insert into Q11,2,1,'N','','',1,user,sysdate)";
        qScripts[3] = "insert into Q11,3,1,'N','','',2,user,sysdate)";
        qScripts[4] = "update QMove;1;11;3";
        qScripts[5] = "update QMove;2;11;2";
        qDao.runScripts(qScripts, TEST_QN_ID);
        HashMap<String, String> keyMap = new HashMap<String, String>();
        keyMap.put("questionRefIdFk", "10");
        keyMap.put("questionNumber", "1");
        List<QuestionnaireQuestion> questions = (List<QuestionnaireQuestion>) bos.findMatching(
                QuestionnaireQuestion.class, keyMap);
        assertTrue(questions.size() == 1);
        keyMap.put("questionRefIdFk", "11");
        keyMap.put("questionNumber", "2");
        questions = (List<QuestionnaireQuestion>) bos.findMatching(QuestionnaireQuestion.class,
                keyMap);
        assertTrue(questions.size() == 1);
        assertTrue(questions.get(0).getQuestionSeqNumber() == 2);
        keyMap.put("questionRefIdFk", "11");
        keyMap.put("questionNumber", "3");
        questions = (List<QuestionnaireQuestion>) bos.findMatching(QuestionnaireQuestion.class,
                keyMap);
        assertTrue(questions.size() == 1);
        assertTrue(questions.get(0).getQuestionSeqNumber() == 1);

    }

    @Test
    public void testUpdateCondition() {
        qScripts[2] = "insert into Q11,2,1,'N','','',1,user,sysdate)";
        qScripts[3] = "update QCond;'Y';1;'A';11;2";
        qDao.runScripts(qScripts, TEST_QN_ID);
        HashMap<String, String> keyMap = new HashMap<String, String>();
        keyMap.put("questionRefIdFk", "10");
        keyMap.put("questionNumber", "1");
        List<QuestionnaireQuestion> questions = (List<QuestionnaireQuestion>) bos.findMatching(
                QuestionnaireQuestion.class, keyMap);
        assertTrue(questions.size() == 1);
        keyMap.put("questionRefIdFk", "11");
        keyMap.put("questionNumber", "2");
        questions = (List<QuestionnaireQuestion>) bos.findMatching(QuestionnaireQuestion.class,
                keyMap);
        assertTrue(questions.size() == 1);

        QuestionnaireQuestion question = questions.get(0);
        assertTrue(question.getConditionFlag());
        assertEquals(question.getCondition(), "1");
        assertEquals(question.getConditionValue(), "A");
    }

    @Test
    public void testInsertDeleteQuestion() {
        qDao.runScripts(qScripts, TEST_QN_ID);
        HashMap<String, String> keyMap = new HashMap<String, String>();
        keyMap.put("questionRefIdFk", "10");
        keyMap.put("questionNumber", "1");
        List<QuestionnaireQuestion> questions = (List<QuestionnaireQuestion>) bos.findMatching(
                QuestionnaireQuestion.class, keyMap);
        assertTrue(questions.size() == 1);
        Long qid = questions.get(0).getQuestionnaireRefIdFk();
        qScripts[1] = "delete Q;10;" + qid + ";1;0;1";
        qDao.runScripts(qScripts, TEST_QN_ID);
        questions = (List<QuestionnaireQuestion>) bos.findMatching(QuestionnaireQuestion.class,
                keyMap);
        assertTrue(questions.size() == 0);
    }

    @Test
    public void testInsertDeleteUsage() {
        qDao.runScripts(uScripts, TEST_QN_ID);
        HashMap<String, String> keyMap = new HashMap<String, String>();
        keyMap.put("moduleItemCode", "1");
        keyMap.put("questionnaireLabel", "Award Label");
        List<QuestionnaireUsage> questions = (List<QuestionnaireUsage>) bos.findMatching(
                QuestionnaireUsage.class, keyMap);
        assertTrue(questions.size() == 1);
        qDao.runScripts(uScripts1, TEST_QN_ID);
        questions = (List<QuestionnaireUsage>) bos.findMatching(QuestionnaireUsage.class,
                keyMap);
        assertTrue(questions.size() == 0);

    }

}
