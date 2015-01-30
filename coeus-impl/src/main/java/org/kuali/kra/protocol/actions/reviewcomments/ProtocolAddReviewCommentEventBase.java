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
package org.kuali.kra.protocol.actions.reviewcomments;


import org.kuali.coeus.common.committee.impl.meeting.CommitteeScheduleMinuteBase;
import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.coeus.sys.framework.rule.KcDocumentEventBaseExtension;
import org.kuali.kra.protocol.ProtocolDocumentBase;

/**
 * Encapsulates a validation event for a Reviewer Comment add action.
 */
public abstract class ProtocolAddReviewCommentEventBase extends KcDocumentEventBaseExtension {
    
    private String propertyName;
    private CommitteeScheduleMinuteBase reviewComment;

    /**
     * Constructs a ProtocolAddReviewerCommentEvent.
     * 
     * @param document The document to validate
     * @param propertyName The error path property prefix
     * @param reviewComment The added Reviewer Comment
     */
    public ProtocolAddReviewCommentEventBase(ProtocolDocumentBase document, String propertyName, CommitteeScheduleMinuteBase reviewComment) {
        super("Enter reviewer comment", "", document);
        this.propertyName = propertyName;
        this.reviewComment = reviewComment;
    }
    
    public ProtocolDocumentBase getProtocolDocument() {
        return (ProtocolDocumentBase) getDocument();
    }
    
    public String getPropertyName() {
        return propertyName;
    }
    
    public CommitteeScheduleMinuteBase getReviewComment() {
        return reviewComment;
    }

    @Override
    @SuppressWarnings("unchecked")
    public KcBusinessRule getRule() {
        return getNewProtocolAddReviewCommentRuleInstanceHook();
    }

    protected abstract ProtocolAddReviewCommentRuleBase<?> getNewProtocolAddReviewCommentRuleInstanceHook();

}
