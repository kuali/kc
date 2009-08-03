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

public class QuestionnaireDaoTest extends KraTestBase {
    // TODO : need load_coeus_module & load_question as suite test data
    private String[] qScripts = {"","insert into Q10,1,0,'N','','',1,user,sysdate)"};
    private String[] uScripts = {"","insert U;1;Award Label"};
    @Before
    public void setUp() throws Exception {
        super.setUp();
        GlobalVariables.setUserSession(new UserSession("quickstart"));
    }
    
    @After 
    public void tearDown() throws Exception {
        GlobalVariables.setUserSession(null);
        super.tearDown();
    }
    
    @Test
    public void testInsertQuestion() {
        getQuestionnaireDao().runScripts(qScripts, 1);
        HashMap<String, String> keyMap = new HashMap<String, String> ();
        keyMap.put("questionRefIdFk", "10");
        keyMap.put("questionNumber", "1");
        List<QuestionnaireQuestion> questions = (List<QuestionnaireQuestion>)getService(BusinessObjectService.class).findMatching(QuestionnaireQuestion.class, keyMap);
        assertTrue(questions.size() == 1);
    }
    
    @Test
    public void testInsertUsage() {
        getQuestionnaireDao().runScripts(uScripts, 1);
        HashMap<String, String> keyMap = new HashMap<String, String> ();
        keyMap.put("moduleItemCode", "1");
        keyMap.put("questionnaireLabel", "Award Label");
        List<QuestionnaireUsage> questions = (List<QuestionnaireUsage>)getService(BusinessObjectService.class).findMatching(QuestionnaireUsage.class, keyMap);
        assertTrue(questions.size() == 1);
    }
    
    private QuestionnaireDao getQuestionnaireDao() {
        return getService(QuestionnaireDao.class);
    }

}
