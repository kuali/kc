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
import org.kuali.kra.irb.onlinereview.ProtocolReviewAttachment;
import org.kuali.kra.rule.BusinessRuleInterface;
import org.kuali.kra.rule.event.KraDocumentEventBaseExtension;

/**
 * 
 * This class is validate the new review attachment when 'add' is clicked
 */
public class ProtocolAddReviewAttachmentEvent extends KraDocumentEventBaseExtension {
    
    // TODO : technically, this can be refactored to share with ProtocolAddReviewCommentEvent/rule
    // Since, we are waiting for KRMS, so probably just live with this for now.
    private String propertyName;
    private ProtocolReviewAttachment reviewAttachment;

    /**
     * Constructs a ProtocolAddReviewAttachmentEvent.
     * 
     * @param document The document to validate
     * @param propertyName The error path property prefix
     * @param reviewAttachment The added Reviewer Attachment
     */
    public ProtocolAddReviewAttachmentEvent(ProtocolDocument document, String propertyName, ProtocolReviewAttachment reviewAttachment) {
        super("Enter reviewer attachment", "", document);
        this.propertyName = propertyName;
        this.reviewAttachment = reviewAttachment;
    }
    
    public ProtocolDocument getProtocolDocument() {
        return (ProtocolDocument) getDocument();
    }
    
    public String getPropertyName() {
        return propertyName;
    }
    
    public ProtocolReviewAttachment getReviewAttachment() {
        return reviewAttachment;
    }

    @Override
    @SuppressWarnings("unchecked")
    public BusinessRuleInterface getRule() {
        return new ProtocolAddReviewAttachmentRule();
    }


}
