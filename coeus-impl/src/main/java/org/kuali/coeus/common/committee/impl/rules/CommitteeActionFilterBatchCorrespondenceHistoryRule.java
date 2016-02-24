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
package org.kuali.coeus.common.committee.impl.rules;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.committee.impl.rule.event.CommitteeActionFilterBatchCorrespondenceHistoryEvent;
import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.kra.infrastructure.KeyConstants;

/**
 * 
 * This class contains the rules to validate an <code>{@link CommitteeActionFilterBatchCorrespondenceHistoryEvent}</code>.
 */
public class CommitteeActionFilterBatchCorrespondenceHistoryRule extends KcTransactionalDocumentRuleBase
                                                                 implements KcBusinessRule<CommitteeActionFilterBatchCorrespondenceHistoryEvent> {

    private static final String BATCH_CORRESPONDENCE_TYPE_FIELD = "committeeHelper.historyBatchCorrespondenceTypeCode";
    private static final String END_DATE_FIELD = "committeeHelper.historyEndDate";

    /**
     * ProcessDefinitionDefinitionDefinition the validation rules for an <code>{@link CommitteeActionFilterBatchCorrespondenceHistoryEvent}</code>.
     * 
     * @param event the CommitteeActionFilterBatchCorrespondenceHistoryEvent
     * @return <code>true</code> if all validation rules are passed, <code>false</code> otherwise
     */
    public boolean processRules(CommitteeActionFilterBatchCorrespondenceHistoryEvent event) {
        boolean rulePassed = true;
        
        if (StringUtils.isEmpty(event.getBatchCorrespondenceTypeCode())) {
            reportError(BATCH_CORRESPONDENCE_TYPE_FIELD, KeyConstants.ERROR_COMMITTEE_ACTION_HISTORY_BATCH_CORRESPONDENCE_TYPE_CODE_NOT_SPECIFIED);
            rulePassed = false;
        }
        
        if (event.getStartDate() != null && event.getEndDate() != null
                && event.getEndDate().before(event.getStartDate())) {
            reportError(END_DATE_FIELD, KeyConstants.ERROR_COMMITTEE_ACTION_HISTORY_END_DATE_BEFORE_START_DATE);
            rulePassed = false;
        }
        
        return rulePassed;
    }

}
