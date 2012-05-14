/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.iacuc;

import org.kuali.kra.iacuc.actions.IacucProtocolStatus;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmitActionRule;
import org.kuali.kra.iacuc.personnel.IacucProtocolPersonnelAuditRule;
import org.kuali.kra.iacuc.personnel.IacucProtocolUnitRule;
import org.kuali.kra.iacuc.personnel.SaveIacucProtocolPersonnelEvent;
import org.kuali.kra.iacuc.protocol.funding.IacucProtocolFundingSourceAuditRule;
import org.kuali.kra.iacuc.protocol.funding.IacucProtocolFundingSourceRule;
import org.kuali.kra.iacuc.protocol.location.IacucProtocolLocationRule;
import org.kuali.kra.iacuc.protocol.reference.IacucProtocolReferenceRule;
import org.kuali.kra.iacuc.protocol.research.IacucProtocolResearchAreaAuditRule;
import org.kuali.kra.iacuc.species.exception.rule.AddProtocolExceptionEvent;
import org.kuali.kra.iacuc.species.exception.rule.AddProtocolExceptionRule;
import org.kuali.kra.iacuc.species.exception.rule.ProtocolExceptionRule;
import org.kuali.kra.iacuc.species.rule.AddProtocolSpeciesEvent;
import org.kuali.kra.iacuc.species.rule.AddProtocolSpeciesRule;
import org.kuali.kra.iacuc.species.rule.ProtocolSpeciesRule;
import org.kuali.kra.iacuc.IacucProtocolDocument;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmitAction;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmitActionRule;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmitActionRule;
import org.kuali.kra.protocol.protocol.funding.ProtocolFundingSourceAuditRule;
import org.kuali.kra.protocol.protocol.funding.ProtocolFundingSourceRule;
import org.kuali.kra.protocol.protocol.location.ProtocolLocationRule;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.protocol.ProtocolDocument;
import org.kuali.kra.protocol.ProtocolDocumentRule;
import org.kuali.kra.protocol.personnel.AddProtocolUnitEvent;
import org.kuali.kra.protocol.personnel.ProtocolPersonnelAuditRule;
import org.kuali.kra.protocol.personnel.ProtocolUnitRule;
import org.kuali.kra.protocol.personnel.SaveProtocolPersonnelEvent;
import org.kuali.kra.protocol.protocol.research.ProtocolResearchAreaAuditRule;
import org.kuali.kra.rule.event.KraDocumentEventBaseExtension;

/**
 * Main Business Rule class for <code>{@link IacucProtocolDocument}</code>. Responsible for delegating rules to independent rule classes.
 *
 * @author Kuali Nervous System Team (kualidev@oncourse.iu.edu)
 */
public class IacucProtocolDocumentRule extends ProtocolDocumentRule implements AddProtocolSpeciesRule, AddProtocolExceptionRule {

    @Override
    protected String getInProgressProtocolStatusCodeHook() {
        return IacucProtocolStatus.IN_PROGRESS;
    }

    @Override
    protected ProtocolResearchAreaAuditRule getNewProtocolResearchAreaAuditRuleInstanceHook() {
        return new IacucProtocolResearchAreaAuditRule();
    }

    @Override
    public boolean processAddProtocolSpeciesBusinessRules(AddProtocolSpeciesEvent addProtocolSpeciesEvent) {
        return new ProtocolSpeciesRule().processAddProtocolSpeciesBusinessRules(addProtocolSpeciesEvent);
    }

    @Override
    public boolean processAddProtocolExceptionBusinessRules(AddProtocolExceptionEvent addProtocolExceptionEvent) {
        return new ProtocolExceptionRule().processAddProtocolExceptionBusinessRules(addProtocolExceptionEvent);
    }
    

    @Override
    protected IacucProtocolReferenceRule getNewProtocolReferenceRuleInstanceHook() {
        return new IacucProtocolReferenceRule();
    }

    @Override
    protected KraDocumentEventBaseExtension getSaveProtocolPersonnelEventHook(ProtocolDocument document) {
        return new SaveIacucProtocolPersonnelEvent(Constants.EMPTY_STRING, document);
    }

    @Override
    protected ProtocolPersonnelAuditRule getNewProtocolPersonnelAuditRuleInstanceHook() {
        return new IacucProtocolPersonnelAuditRule();
    }

    @Override
    protected ProtocolUnitRule getNewProtocolUnitRuleInstanceHook() {
        return new IacucProtocolUnitRule();
    }

    @Override
    protected ProtocolSubmitActionRule newProtocolSubmitActionRuleInstanceHook() {
        return new IacucProtocolSubmitActionRule();
    }

    @Override
    protected ProtocolLocationRule getNewProtocolLocationRuleInstanceHook() {
        return new IacucProtocolLocationRule();
    }

    @Override
    protected ProtocolFundingSourceAuditRule getNewProtocolFundingSourceAuditRuleInstanceHook() {
        return new IacucProtocolFundingSourceAuditRule();
    }

    @Override
    protected ProtocolFundingSourceRule getNewProtocolFundingSourceRuleInstanceHook() {
        return new IacucProtocolFundingSourceRule();
    }    
}
