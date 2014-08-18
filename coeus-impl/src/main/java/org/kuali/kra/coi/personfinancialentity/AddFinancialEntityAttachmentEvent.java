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
package org.kuali.kra.coi.personfinancialentity;

import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.coeus.sys.framework.rule.KcDocumentEventBaseExtension;
import org.kuali.kra.coi.notesandattachments.FinancialEntityAttachmentRule;
import org.kuali.kra.coi.notesandattachments.attachments.FinancialEntityAttachment;

/**
 * 
 * This class is and event class when save FE
 */
public class AddFinancialEntityAttachmentEvent  extends KcDocumentEventBaseExtension {
    private FinancialEntityAttachment attachment;

    /**
     * 
     * Constructs a SaveFinancialEntityEvent.java.
     * @param propertyName
     * @param personFinIntDisclosure
     */
    public AddFinancialEntityAttachmentEvent(String prefixPath, FinancialEntityAttachment attachment) {
        super("Add Financial Entity Attachment", prefixPath, null);
        this.attachment = attachment;
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public KcBusinessRule getRule() {
        return new FinancialEntityAttachmentRule();
    }

    public FinancialEntityAttachment getAttachment() {
        return attachment;
    }

    public void setAttachment(FinancialEntityAttachment attachment) {
        this.attachment = attachment;
    }

}
