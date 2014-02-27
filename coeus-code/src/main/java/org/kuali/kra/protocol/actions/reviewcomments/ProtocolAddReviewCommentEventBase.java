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
