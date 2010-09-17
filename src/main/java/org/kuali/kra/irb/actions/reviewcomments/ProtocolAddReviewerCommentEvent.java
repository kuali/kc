/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.irb.actions.reviewcomments;

import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.meeting.CommitteeScheduleMinute;
import org.kuali.kra.rule.BusinessRuleInterface;
import org.kuali.kra.rule.event.KraDocumentEventBaseExtension;

/**
 * Encapsulates a validation event for a Reviewer Comment add action.
 */
public class ProtocolAddReviewerCommentEvent extends KraDocumentEventBaseExtension {
    
    private String propertyName;
    private CommitteeScheduleMinute reviewerComment;

    /**
     * Constructs a ProtocolAddReviewerCommentEvent.
     * 
     * @param document The document to validate
     * @param propertyName The error path property prefix
     * @param reviewerComment The added Reviewer Comment
     */
    public ProtocolAddReviewerCommentEvent(ProtocolDocument document, String propertyName, CommitteeScheduleMinute reviewerComment) {
        super("Enter reviewer comment", "", document);
        this.propertyName = propertyName;
        this.reviewerComment = reviewerComment;
    }
    
    public ProtocolDocument getProtocolDocument() {
        return (ProtocolDocument) getDocument();
    }
    
    public String getPropertyName() {
        return propertyName;
    }
    
    public CommitteeScheduleMinute getReviewerComment() {
        return reviewerComment;
    }

    @Override
    @SuppressWarnings("unchecked")
    public BusinessRuleInterface getRule() {
        return new ProtocolAddReviewerCommentRule();
    }

}