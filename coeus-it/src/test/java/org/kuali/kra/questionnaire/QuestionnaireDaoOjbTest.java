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
