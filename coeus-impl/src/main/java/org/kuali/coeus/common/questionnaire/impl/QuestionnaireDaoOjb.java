/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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

package org.kuali.coeus.common.questionnaire.impl;

import java.math.BigDecimal;
import java.util.Iterator;
import org.apache.commons.lang3.ArrayUtils;
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
