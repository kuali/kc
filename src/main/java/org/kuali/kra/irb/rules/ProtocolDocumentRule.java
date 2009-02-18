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

import java.util.List;

import org.kuali.core.document.Document;
import org.kuali.kra.common.permissions.bo.PermissionsUser;
import org.kuali.kra.common.permissions.bo.PermissionsUserEditRoles;
import org.kuali.kra.common.permissions.rule.PermissionsRule;
import org.kuali.kra.common.permissions.web.bean.User;
import org.kuali.kra.irb.document.ProtocolDocument;
import org.kuali.kra.irb.rule.AddProtocolFundingSourceRule;
import org.kuali.kra.irb.rule.AddProtocolLocationRule;
import org.kuali.kra.irb.rule.AddProtocolParticipantRule;
import org.kuali.kra.irb.rule.AddProtocolPersonnelRule;
import org.kuali.kra.irb.rule.AddProtocolReferenceRule;
import org.kuali.kra.irb.rule.AddProtocolUnitRule;
import org.kuali.kra.irb.rule.SaveProtocolLocationRule;
import org.kuali.kra.irb.rule.SaveProtocolPersonnelRule;
import org.kuali.kra.irb.rule.SaveProtocolRequiredFieldsRule;
import org.kuali.kra.irb.rule.event.AddProtocolFundingSourceEvent;
import org.kuali.kra.irb.rule.event.AddProtocolLocationEvent;
import org.kuali.kra.irb.rule.event.AddProtocolParticipantEvent;
import org.kuali.kra.irb.rule.event.AddProtocolPersonnelEvent;
import org.kuali.kra.irb.rule.event.AddProtocolReferenceEvent;
import org.kuali.kra.irb.rule.event.AddProtocolUnitEvent;
import org.kuali.kra.irb.rule.event.SaveProtocolLocationEvent;
import org.kuali.kra.irb.rule.event.SaveProtocolPersonnelEvent;
import org.kuali.kra.irb.rule.event.SaveProtocolRequiredFieldsEvent;
import org.kuali.kra.rules.ResearchDocumentRuleBase;

/**
 * Main Business Rule class for <code>{@link ProtocolDocument}</code>. Responsible for delegating rules to independent rule classes.
 *
 * @author Kuali Nervous System Team (kualidev@oncourse.iu.edu)
 */
public class ProtocolDocumentRule extends ResearchDocumentRuleBase  implements AddProtocolReferenceRule, AddProtocolParticipantRule, AddProtocolLocationRule, SaveProtocolLocationRule, SaveProtocolRequiredFieldsRule, AddProtocolPersonnelRule, SaveProtocolPersonnelRule, AddProtocolFundingSourceRule, PermissionsRule, AddProtocolUnitRule {
    
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

    /**
     * @see org.kuali.kra.irb.rule.SaveProtocolPersonnelRule#processSaveProtocolPersonnelBusinessRules(org.kuali.kra.irb.rule.event.SaveProtocolPersonnelEvent)
     */
    public boolean processSaveProtocolPersonnelBusinessRules(SaveProtocolPersonnelEvent saveProtocolPersonnelEvent) {

        return new ProtocolPersonnelRule().processSaveProtocolPersonnelBusinessRules(saveProtocolPersonnelEvent);
        
    }
    
    /**
     * @see org.kuali.kra.irb.rule.AddProtocolFundingSourceRule#processAddProtocolFundingSourceBusinessRules(org.kuali.kra.irb.rule.event.AddProtocolFundingSourceEvent)
     */
    public boolean processAddProtocolFundingSourceBusinessRules(AddProtocolFundingSourceEvent addProtocolFundingSourceEvent) {

        return new ProtocolFundingSourceRule().processAddProtocolFundingSourceBusinessRules(addProtocolFundingSourceEvent);
        
    }
    
    /**
     * @see org.kuali.kra.common.permissions.rule.PermissionsRule#processAddPermissionsUserBusinessRules(org.kuali.core.document.Document, java.util.List, org.kuali.kra.common.permissions.bo.PermissionsUser)
     */
    public boolean processAddPermissionsUserBusinessRules(Document document, List<User> users, PermissionsUser newUser) {
        return new ProtocolPermissionsRule().processAddPermissionsUserBusinessRules(document, users, newUser);
    }

    /**
     * @see org.kuali.kra.common.permissions.rule.PermissionsRule#processDeletePermissionsUserBusinessRules(org.kuali.core.document.Document, java.util.List, int)
     */
    public boolean processDeletePermissionsUserBusinessRules(Document document, List<User> users, int index) {
        return new ProtocolPermissionsRule().processDeletePermissionsUserBusinessRules(document, users, index);     
    }

    /**
     * @see org.kuali.kra.common.permissions.rule.PermissionsRule#processEditPermissionsUserRolesBusinessRules(org.kuali.core.document.Document, java.util.List, org.kuali.kra.common.permissions.bo.PermissionsUserEditRoles)
     */
    public boolean processEditPermissionsUserRolesBusinessRules(Document document, List<User> users,
            PermissionsUserEditRoles editRoles) {
        return new ProtocolPermissionsRule().processEditPermissionsUserRolesBusinessRules(document, users, editRoles);
    }

    /**
     * @see org.kuali.kra.irb.rule.AddProtocolUnitRule#processAddProtocolUnitBusinessRules(org.kuali.kra.irb.rule.event.AddProtocolUnitEvent)
     */
    public boolean processAddProtocolUnitBusinessRules(AddProtocolUnitEvent addProtocolUnitEvent) {

        return new ProtocolUnitRule().processAddProtocolUnitBusinessRules(addProtocolUnitEvent);
        
    }


}
