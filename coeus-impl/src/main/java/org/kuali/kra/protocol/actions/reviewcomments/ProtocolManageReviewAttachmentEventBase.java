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

import java.util.List;

/**
 * 
 * This class is for the rule event of 'manage review attachment'
 */
public abstract class ProtocolManageReviewAttachmentEventBase<PRA extends ProtocolReviewAttachmentBase> extends KcDocumentEventBaseExtension {
    
    private String propertyName;
    private List<PRA> reviewAttachments;

    /**
     * Constructs a ProtocolManageReviewAttachmentEventBase.
     * 
     * @param document The document to validate
     * @param propertyName The error path property prefix
     * @param reviewAttachments The manage Reviewer Attachment
     */
    public ProtocolManageReviewAttachmentEventBase(ProtocolDocumentBase document, String propertyName, List<PRA> reviewAttachments) {
        super("Enter reviewer attachment", "", document);
        this.propertyName = propertyName;
        this.reviewAttachments = reviewAttachments;
    }
    
    public ProtocolDocumentBase getProtocolDocument() {
        return (ProtocolDocumentBase) getDocument();
    }
    
    public String getPropertyName() {
        return propertyName;
    }
    
 
    @Override
    @SuppressWarnings("unchecked")
    public KcBusinessRule getRule() {
        return getNewProtocolManageReviewAttachmentRuleInstaceHook();
    }

    protected abstract ProtocolManageReviewAttachmentRule<?> getNewProtocolManageReviewAttachmentRuleInstaceHook();

    
    public List<PRA> getReviewAttachments() {
        return reviewAttachments;
    }


}
