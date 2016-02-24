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
package org.kuali.kra.protocol.onlinereview.event;

import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.coeus.sys.framework.rule.KcDocumentEventBaseExtension;
import org.kuali.kra.protocol.ProtocolOnlineReviewDocumentBase;
import org.kuali.kra.protocol.onlinereview.ProtocolReviewAttachmentBase;
import org.kuali.kra.protocol.onlinereview.rules.AddOnlineReviewAttachmentRule;

/**
 * 
 * This class implements the add OLR review attachment event for new attachment validation.
 */
public class AddProtocolOnlineReviewAttachmentEvent extends KcDocumentEventBaseExtension {
    
    private String propertyName;
    private ProtocolReviewAttachmentBase reviewAttachment;

    /**
     * Constructs a AddProtocolOnlineReviewAttachmentEvent.
     * 
     * @param document The document to validate
     * @param propertyName The error path property prefix
     * @param reviewComment The added Reviewer Attachment
     */
    public AddProtocolOnlineReviewAttachmentEvent(ProtocolOnlineReviewDocumentBase document, String propertyName, ProtocolReviewAttachmentBase reviewAttachment) {
        super("Enter reviewer attachment", "", document);
        this.propertyName = propertyName;
        this.reviewAttachment = reviewAttachment;
    }
    
    public ProtocolOnlineReviewDocumentBase getProtocolDocument() {
        return (ProtocolOnlineReviewDocumentBase) getDocument();
    }
    
    public String getPropertyName() {
        return propertyName;
    }
    
    public ProtocolReviewAttachmentBase getReviewAttachment() {
        return reviewAttachment;
    }

    @Override
    @SuppressWarnings("unchecked")
    public KcBusinessRule getRule() {
        return new AddOnlineReviewAttachmentRule();
    }
}
