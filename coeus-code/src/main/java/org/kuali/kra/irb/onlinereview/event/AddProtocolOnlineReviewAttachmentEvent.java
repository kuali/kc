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
package org.kuali.kra.irb.onlinereview.event;

import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.coeus.sys.framework.rule.KcDocumentEventBaseExtension;
import org.kuali.kra.irb.ProtocolOnlineReviewDocument;
import org.kuali.kra.irb.onlinereview.ProtocolReviewAttachment;
import org.kuali.kra.irb.onlinereview.rules.AddOnlineReviewAttachmentRule;

/**
 * 
 * This class implements the add OLR review attachment event for new attachment validation.
 */
public class AddProtocolOnlineReviewAttachmentEvent extends KcDocumentEventBaseExtension {
    
    private String propertyName;
    private ProtocolReviewAttachment reviewAttachment;

    /**
     * Constructs a AddProtocolOnlineReviewAttachmentEvent.
     * 
     * @param document The document to validate
     * @param propertyName The error path property prefix
     * @param reviewComment The added Reviewer Attachment
     */
    public AddProtocolOnlineReviewAttachmentEvent(ProtocolOnlineReviewDocument document, String propertyName, ProtocolReviewAttachment reviewAttachment) {
        super("Enter reviewer attachment", "", document);
        this.propertyName = propertyName;
        this.reviewAttachment = reviewAttachment;
    }
    
    public ProtocolOnlineReviewDocument getProtocolDocument() {
        return (ProtocolOnlineReviewDocument) getDocument();
    }
    
    public String getPropertyName() {
        return propertyName;
    }
    
    public ProtocolReviewAttachment getReviewAttachment() {
        return reviewAttachment;
    }

    @Override
    @SuppressWarnings("unchecked")
    public KcBusinessRule getRule() {
        return new AddOnlineReviewAttachmentRule();
    }
}
