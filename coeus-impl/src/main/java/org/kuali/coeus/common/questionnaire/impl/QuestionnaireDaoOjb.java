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

package org.kuali.coeus.common.questionnaire.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.QueryByCriteria;
import org.apache.ojb.broker.query.QueryFactory;
import org.apache.ojb.broker.query.ReportQueryByCriteria;
import org.kuali.coeus.common.questionnaire.framework.answer.AnswerHeader;
import org.kuali.coeus.common.questionnaire.framework.core.Questionnaire;
import org.kuali.coeus.common.questionnaire.framework.core.QuestionnaireConstants;
import org.kuali.rice.core.framework.persistence.ojb.dao.PlatformAwareDaoBaseOjb;

public class QuestionnaireDaoOjb extends PlatformAwareDaoBaseOjb implements QuestionnaireDao {

    @Override
    public Integer getCurrentQuestionnaireSequenceNumber(String questionnaireSeqId) {
        final Criteria criteria = new Criteria();
        criteria.addEqualTo(QuestionnaireConstants.QUESTIONNAIRE_SEQUENCE_ID_PARAMETER_NAME, questionnaireSeqId);
        final String[] columns = {"max(" + QuestionnaireConstants.QUESTIONNAIRE_SEQUENCE_NUMBER_PARAMETER_NAME + ")"};
        final ReportQueryByCriteria query = QueryFactory.newReportQuery(Questionnaire.class, columns, criteria, false);
        final Iterator iter = getPersistenceBrokerTemplate().getReportQueryIteratorByQuery(query);
        final Integer sequenceNumber = iter.hasNext() ? ((BigDecimal) ((Object[]) iter.next())[0]).intValue() : null;
        while (iter.hasNext()) {
            iter.next(); // exhaust the iterator so the db resources will be closed.  See org.apache.ojb.broker.accesslayer.OJBIterator
        }

        return sequenceNumber;
    }

    public List<AnswerHeader> getQuestionnaireAnswers(String moduleCode, String moduleItemKey, String moduleSubItemKey) {
        String certificationModuleItemKey = moduleItemKey + "|%";
        Criteria certificationCriteria = new Criteria();
        certificationCriteria.addEqualTo(QuestionnaireConstants.MODULE_ITEM_CODE, moduleCode);
        certificationCriteria.addEqualTo(QuestionnaireConstants.MODULE_SUB_ITEM_KEY, moduleSubItemKey);
        certificationCriteria.addLike(QuestionnaireConstants.MODULE_ITEM_KEY, certificationModuleItemKey);

        Criteria otherQuestionnaireCriteria = new Criteria();
        otherQuestionnaireCriteria.addEqualTo(QuestionnaireConstants.MODULE_ITEM_CODE, moduleCode);
        otherQuestionnaireCriteria.addEqualTo(QuestionnaireConstants.MODULE_SUB_ITEM_KEY, moduleSubItemKey);
        otherQuestionnaireCriteria.addEqualTo(QuestionnaireConstants.MODULE_ITEM_KEY, moduleItemKey);

        certificationCriteria.addOrCriteria(otherQuestionnaireCriteria);

        QueryByCriteria query = QueryFactory.newQuery(AnswerHeader.class, certificationCriteria);

        return (List<AnswerHeader>) getPersistenceBrokerTemplate().getCollectionByQuery(query);
    }

}
