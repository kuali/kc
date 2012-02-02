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
package org.kuali.kra.coi.notesandattachments;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.coi.notesandattachments.attachments.FinancialEntityAttachment;
import org.kuali.kra.rule.event.KraDocumentEventBase;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.BusinessRule;

public class AddFinancialEntityAttachmentEvent extends KraDocumentEventBase{
    private static final Log LOG = LogFactory.getLog(AddFinancialEntityAttachmentEvent.class);
    private final FinancialEntityAttachment newFinancialEntityAttachment;

    public AddFinancialEntityAttachmentEvent(Document document, FinancialEntityAttachment newFinancialEntityAttachment) {
        super("adding new coi disclosure attachment", "coiNoteAndAttachmentHelper", document);
        
        if (newFinancialEntityAttachment == null) {
            throw new IllegalArgumentException("the newFinancialEntityAttachment is null");
        }
        
        this.newFinancialEntityAttachment = newFinancialEntityAttachment;    }

    @Override
    protected void logEvent() {
        LOG.debug("adding new: " + this.newFinancialEntityAttachment + " on doc # " + this.getDocument().getDocumentNumber());
    }

    @Override
    public Class<AddFinancialEntityAttachmentRule> getRuleInterfaceClass() {
        return AddFinancialEntityAttachmentRule.class;
    }

    @Override
    public boolean invokeRuleMethod(BusinessRule rule) {
        return this.getRuleInterfaceClass().cast(rule).processAddFinancialEntityAttachmentRules(this);

    }
    
    public FinancialEntityAttachment getNewFinancialEntityAttachment() {
        return this.newFinancialEntityAttachment;
    }

}
