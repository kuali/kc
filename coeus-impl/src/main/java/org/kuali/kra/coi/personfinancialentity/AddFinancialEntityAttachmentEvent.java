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
