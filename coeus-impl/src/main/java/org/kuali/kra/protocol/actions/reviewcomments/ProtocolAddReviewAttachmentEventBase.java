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
package org.kuali.kra.protocol.actions.reviewcomments;

import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.coeus.sys.framework.rule.KcDocumentEventBaseExtension;
import org.kuali.kra.protocol.ProtocolDocumentBase;
import org.kuali.kra.protocol.onlinereview.ProtocolReviewAttachmentBase;

/**
 * 
 * This class is validate the new review attachment when 'add' is clicked
 */
public abstract class ProtocolAddReviewAttachmentEventBase<PRA extends ProtocolReviewAttachmentBase> extends KcDocumentEventBaseExtension {
    
    // TODO : technically, this can be refactored to share with ProtocolAddReviewCommentEventBase/rule
    // Since, we are waiting for KRMS, so probably just live with this for now.
    private String propertyName;
    private PRA reviewAttachment;

    /**
     * Constructs a ProtocolAddReviewAttachmentEventBase.
     * 
     * @param document The document to validate
     * @param propertyName The error path property prefix
     * @param reviewAttachment The added Reviewer Attachment
     */
    public ProtocolAddReviewAttachmentEventBase(ProtocolDocumentBase document, String propertyName, PRA reviewAttachment) {
        super("Enter reviewer attachment", "", document);
        this.propertyName = propertyName;
        this.reviewAttachment = reviewAttachment;
    }
    
    public ProtocolDocumentBase getProtocolDocument() {
        return (ProtocolDocumentBase) getDocument();
    }
    
    public String getPropertyName() {
        return propertyName;
    }
    
    public PRA getReviewAttachment() {
        return reviewAttachment;
    }

    @Override
    @SuppressWarnings("unchecked")
    public KcBusinessRule getRule() {
        return getNewProtocolAddReviewAttachmentRuleInstancehook();
    }

    protected abstract ProtocolAddReviewAttachmentRule<?> getNewProtocolAddReviewAttachmentRuleInstancehook();


}
