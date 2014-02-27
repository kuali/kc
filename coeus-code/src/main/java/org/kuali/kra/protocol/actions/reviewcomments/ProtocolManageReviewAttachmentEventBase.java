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
