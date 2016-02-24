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

import org.kuali.coeus.common.committee.impl.bo.CommitteeBatchCorrespondenceBase;
import org.kuali.coeus.common.committee.impl.bo.CommitteeBatchCorrespondenceDetailBase;
import org.kuali.coeus.common.committee.impl.rule.event.CommitteeActionViewBatchCorrespondenceEvent;
import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.kra.infrastructure.KeyConstants;

/**
 * 
 * This class contains the document rules of the ViewBatchCorrespondence event.
 */
public class CommitteeActionViewBatchCorrespondenceRule extends KcTransactionalDocumentRuleBase
                                                        implements KcBusinessRule<CommitteeActionViewBatchCorrespondenceEvent> {
    
    private static final String BATCH_VIEW_ERROR_FIELD = "committeeHelper.generateBatchCorrespondence";
    private static final String HISTORY_VIEW_ERROR_FIELD = "committeeHelper.batchCorrespondenceHistory";

    /**
     * 
     * This method processes the rules of the CommitteeActionViewBatchCorrespondenceEvent.
     * 
     * @param event to be validated against the rules.
     * @return true if validation passed the rules, false otherwise.
     */
    public boolean processRules(CommitteeActionViewBatchCorrespondenceEvent event) {
        for (CommitteeBatchCorrespondenceBase committeeBatchCorrespondence : event.getCommitteeBatchCorrespondences()) {
            for (CommitteeBatchCorrespondenceDetailBase committeeBatchCorrespondenceDetail : 
                    committeeBatchCorrespondence.getCommitteeBatchCorrespondenceDetails()) {
                if (committeeBatchCorrespondenceDetail.getSelected()) {
                    return true;
                }
            }
        }
        
        if (event.isViewGenerated()) {
            reportError(BATCH_VIEW_ERROR_FIELD, KeyConstants.ERROR_COMMITTEE_ACTION_GENERATE_VIEW_NOT_SPECIFIED);
        } else {
            reportError(HISTORY_VIEW_ERROR_FIELD, KeyConstants.ERROR_COMMITTEE_ACTION_HISTORY_VIEW_NOT_SPECIFIED);
        }
        return false;
    }

}
