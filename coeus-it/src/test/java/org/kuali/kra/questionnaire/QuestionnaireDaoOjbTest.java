/**
 * Copyright 2005-2014 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.questionnaire;

import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.questionnaire.impl.QuestionnaireDao;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.kew.api.exception.WorkflowException;

import static org.junit.Assert.assertEquals;

/**
 * @author Kuali Rice Team (rice.collab@kuali.org)
 */
public class QuestionnaireDaoOjbTest extends KcIntegrationTestBase {
    private QuestionnaireDao questionnaireDao;

    private static final String QUESTIONNAIRE_SEQUENCE_ID = "1000";
    private static final Integer CURRENT_QUESTIONNAIRE_SEQUENCE_NUMBER = 2;

    @Before
    public void setUp() throws Exception {
        questionnaireDao = KcServiceLocator.getService("questionnaireDao");
    }


    @Test
    public void testFindProtocol() throws WorkflowException {
      assertEquals(CURRENT_QUESTIONNAIRE_SEQUENCE_NUMBER, questionnaireDao.getCurrentQuestionnaireSequenceNumber(QUESTIONNAIRE_SEQUENCE_ID));

    }
}