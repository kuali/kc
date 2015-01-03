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
package org.kuali.coeus.common.questionnaire.impl;

import java.math.BigDecimal;
import java.util.Iterator;
import org.apache.commons.lang.ArrayUtils;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.QueryFactory;
import org.apache.ojb.broker.query.ReportQueryByCriteria;
import org.kuali.coeus.common.questionnaire.framework.core.Questionnaire;
import org.kuali.coeus.common.questionnaire.framework.core.QuestionnaireConstants;
import org.kuali.rice.core.framework.persistence.ojb.dao.PlatformAwareDaoBaseOjb;

/**
 * @author Kuali Rice Team (rice.collab@kuali.org)
 */
public class QuestionnaireDaoOjb extends PlatformAwareDaoBaseOjb implements QuestionnaireDao {

    @Override
    public Integer getCurrentQuestionnaireSequenceNumber(String questionnaireSeqId) {
        Criteria criteria = new Criteria();
        criteria.addEqualTo(QuestionnaireConstants.QUESTIONNAIRE_SEQUENCE_ID_PARAMETER_NAME, questionnaireSeqId);
        String[] columns = {"max(" + QuestionnaireConstants.QUESTIONNAIRE_SEQUENCE_NUMBER_PARAMETER_NAME + ")"};
        ReportQueryByCriteria query = QueryFactory.newReportQuery(Questionnaire.class, columns, criteria, false);
        Iterator iter = getPersistenceBrokerTemplate().getReportQueryIteratorByQuery(query);
        if (iter.hasNext()) {
            Object[] objects = (Object[]) iter.next();
            return ((BigDecimal) objects[0]).intValue();
        } else {
            return null;
        }
    }

}