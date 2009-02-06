/*
 * Copyright 2006-2008 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.irb.rules;

import org.kuali.core.document.Document;
import org.kuali.kra.irb.document.ProtocolDocument;
import org.kuali.kra.irb.rule.AddProtocolLocationRule;
import org.kuali.kra.irb.rule.AddProtocolParticipantRule;
import org.kuali.kra.irb.rule.AddProtocolPersonnelRule;
import org.kuali.kra.irb.rule.AddProtocolReferenceRule;
import org.kuali.kra.irb.rule.SaveProtocolLocationRule;
import org.kuali.kra.irb.rule.SaveProtocolRequiredFieldsRule;
import org.kuali.kra.irb.rule.event.AddProtocolLocationEvent;
import org.kuali.kra.irb.rule.event.AddProtocolParticipantEvent;
import org.kuali.kra.irb.rule.event.AddProtocolPersonnelEvent;
import org.kuali.kra.irb.rule.event.AddProtocolReferenceEvent;
import org.kuali.kra.irb.rule.event.SaveProtocolLocationEvent;
import org.kuali.kra.irb.rule.event.SaveProtocolRequiredFieldsEvent;
import org.kuali.kra.rules.ResearchDocumentRuleBase;

/**
 * Main Business Rule class for <code>{@link ProtocolDocument}</code>. Responsible for delegating rules to independent rule classes.
 *
 * @author Kuali Nervous System Team (kualidev@oncourse.iu.edu)
 */
public class ProtocolDocumentRule extends ResearchDocumentRuleBase  implements AddProtocolReferenceRule, AddProtocolParticipantRule, AddProtocolLocationRule, SaveProtocolLocationRule, SaveProtocolRequiredFieldsRule, AddProtocolPersonnelRule {
    
    @Override
    protected boolean processCustomRouteDocumentBusinessRules(Document document) {
        boolean retval = true;
        retval &= super.processCustomRouteDocumentBusinessRules(document);
        
        return retval;
    }

    @Override
    protected boolean processCustomSaveDocumentBusinessRules(Document document) {
        if (!(document instanceof ProtocolDocument)) {
            return false;
        }

        boolean valid = true;

        return valid;
    }


    /**
     * @see org.kuali.core.rule.DocumentAuditRule#processRunAuditBusinessRules(org.kuali.core.document.Document)
     */
    public boolean processRunAuditBusinessRules(Document document){
        boolean retval = true;
        
        retval &= super.processRunAuditBusinessRules(document);
        
        return retval;
    }

    /**
     * @see org.kuali.kra.irb.rule.AddProtocolParticipantRule#processAddParticipantBusinessRules(org.kuali.kra.irb.document.ProtocolDocument, org.kuali.kra.irb.bo.ProtocolParticipant)
     */
    public boolean processAddProtocolParticipantBusinessRules(AddProtocolParticipantEvent addProtocolParticipantEvent) {
        return new ProtocolParticipantRule().processAddProtocolParticipantBusinessRules(addProtocolParticipantEvent);
    }

    public boolean processAddProtocolReferenceBusinessRules(AddProtocolReferenceEvent addProtocolReferenceEvent) {

        return new ProtocolReferenceRule().processAddProtocolReferenceBusinessRules(addProtocolReferenceEvent);
        
    }

    /**
     * @see org.kuali.kra.irb.rule.AddProtocolLocationRule#processAddProtocolLocationBusinessRules(org.kuali.kra.irb.rule.event.AddProtocolLocationEvent)
     */
    public boolean processAddProtocolLocationBusinessRules(AddProtocolLocationEvent addProtocolLocationEvent) {

        return new ProtocolLocationRule().processAddProtocolLocationBusinessRules(addProtocolLocationEvent);
        
    }

    /**
     * @see org.kuali.kra.irb.rule.SaveProtocolLocationRule#processSaveProtocolLocationBusinessRules(org.kuali.kra.irb.rule.event.SaveProtocolLocationEvent)
     */
    public boolean processSaveProtocolLocationBusinessRules(SaveProtocolLocationEvent saveProtocolLocationEvent) {

        return new ProtocolLocationRule().processSaveProtocolLocationBusinessRules(saveProtocolLocationEvent);
        
    }
    
    public boolean processSaveProtocolRequiredFieldsRules(SaveProtocolRequiredFieldsEvent saveProtocolRequiredFieldsEvent) {

        return new ProtocolRequiredFieldsRule().processSaveProtocolRequiredFieldsRules(saveProtocolRequiredFieldsEvent);
        
    }

    /**
     * @see org.kuali.kra.irb.rule.AddProtocolPersonnelRule#processAddProtocolPersonnelBusinessRules(org.kuali.kra.irb.rule.event.AddProtocolPersonnelEvent)
     */
    public boolean processAddProtocolPersonnelBusinessRules(AddProtocolPersonnelEvent addProtocolPersonnelEvent) {

        return new ProtocolPersonnelRule().processAddProtocolPersonnelBusinessRules(addProtocolPersonnelEvent);
        
    }

}
