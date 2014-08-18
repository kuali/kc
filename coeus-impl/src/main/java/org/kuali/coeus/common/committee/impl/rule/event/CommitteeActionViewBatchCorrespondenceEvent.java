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
package org.kuali.coeus.common.committee.impl.rule.event;

import org.kuali.coeus.common.committee.impl.bo.CommitteeBatchCorrespondenceBase;
import org.kuali.coeus.common.committee.impl.rules.CommitteeActionPrintCommitteeDocumentRule;
import org.kuali.coeus.common.committee.impl.rules.CommitteeActionViewBatchCorrespondenceRule;
import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.rice.krad.document.Document;

import java.util.List;

public class CommitteeActionViewBatchCorrespondenceEvent extends CommitteeActionsEventBase<CommitteeActionPrintCommitteeDocumentRule> {
    private static final String MSG = "view batch correspondence";
    
    private List<CommitteeBatchCorrespondenceBase> committeeBatchCorrespondences;
    private boolean viewGenerated;

    public CommitteeActionViewBatchCorrespondenceEvent(String errorPathPrefix, Document document, List<CommitteeBatchCorrespondenceBase> committeeBatchCorrespondences, boolean viewGenerated) {
        super(MSG + getDocumentId(document), errorPathPrefix, document);
        setCommitteeBatchCorrespondences(committeeBatchCorrespondences);
        setViewGenerated(viewGenerated);
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public KcBusinessRule getRule() {
        return new CommitteeActionViewBatchCorrespondenceRule();
    }

    public List<CommitteeBatchCorrespondenceBase> getCommitteeBatchCorrespondences() {
        return committeeBatchCorrespondences;
    }

    public void setCommitteeBatchCorrespondences(List<CommitteeBatchCorrespondenceBase> committeeBatchCorrespondences) {
        this.committeeBatchCorrespondences = committeeBatchCorrespondences;
    }

    public void setViewGenerated(boolean viewGenerated) {
        this.viewGenerated = viewGenerated;
    }

    public boolean isViewGenerated() {
        return viewGenerated;
    }

}
