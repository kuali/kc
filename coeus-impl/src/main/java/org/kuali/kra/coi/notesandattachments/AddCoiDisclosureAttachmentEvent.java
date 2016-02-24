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
package org.kuali.kra.coi.notesandattachments;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.sys.framework.rule.KcDocumentEventBase;
import org.kuali.kra.coi.notesandattachments.attachments.CoiDisclosureAttachment;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.BusinessRule;

public class AddCoiDisclosureAttachmentEvent extends KcDocumentEventBase {
    private static final Log LOG = LogFactory.getLog(AddCoiDisclosureAttachmentEvent.class);
    private final CoiDisclosureAttachment newCoiDisclosureAttachment;

    public AddCoiDisclosureAttachmentEvent(Document document, CoiDisclosureAttachment newCoiDisclosureAttachment) {
        super("adding new coi disclosure attachment", "coiNoteAndAttachmentHelper", document);
        
        if (document == null) {
            throw new IllegalArgumentException("the document is null");
        }
        
        if (newCoiDisclosureAttachment == null) {
            throw new IllegalArgumentException("the newCoiDisclosureAttachment is null");
        }
        
        this.newCoiDisclosureAttachment = newCoiDisclosureAttachment;    }

    @Override
    protected void logEvent() {
        LOG.debug("adding new: " + this.newCoiDisclosureAttachment + " on doc # " + this.getDocument().getDocumentNumber());
        
    }

    @Override
    public Class<AddCoiDisclosureAttachmentRule> getRuleInterfaceClass() {
        return AddCoiDisclosureAttachmentRule.class;
    }

    @Override
    public boolean invokeRuleMethod(BusinessRule rule) {
        return this.getRuleInterfaceClass().cast(rule).processAddCoiDisclosureAttachmentRules(this);

    }
    
    public CoiDisclosureAttachment getNewCoiDisclosureAttachment() {
        return this.newCoiDisclosureAttachment;
    }

}
