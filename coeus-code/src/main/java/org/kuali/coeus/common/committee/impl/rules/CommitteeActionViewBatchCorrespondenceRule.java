/*
 * Copyright 2005-2014 The Kuali Foundation
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
