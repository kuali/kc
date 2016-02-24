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
