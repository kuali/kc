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
import org.kuali.kra.iacuc.actions.submit.IacucExecuteProtocolSubmitActionRule;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmitAction;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmitActionRule;
import org.kuali.kra.iacuc.protocol.reference.IacucProtocolReferenceRule;
import org.kuali.kra.iacuc.protocol.research.IacucProtocolResearchAreaAuditRule;
import org.kuali.kra.iacuc.species.exception.rule.AddProtocolExceptionEvent;
import org.kuali.kra.iacuc.species.exception.rule.AddProtocolExceptionRule;
import org.kuali.kra.iacuc.species.exception.rule.ProtocolExceptionRule;
import org.kuali.kra.iacuc.species.rule.AddProtocolSpeciesEvent;
import org.kuali.kra.iacuc.species.rule.AddProtocolSpeciesRule;
import org.kuali.kra.iacuc.species.rule.ProtocolSpeciesRule;
import org.kuali.kra.iacuc.IacucProtocolDocument;
import org.kuali.kra.iacuc.actions.submit.IacucExecuteProtocolSubmitActionRule;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmitAction;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmitActionRule;
import org.kuali.kra.protocol.ProtocolDocumentRule;
import org.kuali.kra.protocol.protocol.research.ProtocolResearchAreaAuditRule;

/**
 * Main Business Rule class for <code>{@link IacucProtocolDocument}</code>. Responsible for delegating rules to independent rule classes.
 *
 * @author Kuali Nervous System Team (kualidev@oncourse.iu.edu)
 */
public class IacucProtocolDocumentRule extends ProtocolDocumentRule implements AddProtocolSpeciesRule, AddProtocolExceptionRule, IacucExecuteProtocolSubmitActionRule {

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

    /**
     * @see org.kuali.kra.iacuc.actions.submit.IacucExecuteProtocolSubmitActionRule#processSubmitAction(org.kuali.kra.irb.ProtocolDocument, org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmitActionBean)
     */
    public boolean processSubmitAction(IacucProtocolDocument document, IacucProtocolSubmitAction submitAction) {
        return new IacucProtocolSubmitActionRule().processSubmitAction(document, submitAction);
    }

    @Override
    public boolean processAddProtocolExceptionBusinessRules(AddProtocolExceptionEvent addProtocolExceptionEvent) {
        return new ProtocolExceptionRule().processAddProtocolExceptionBusinessRules(addProtocolExceptionEvent);
    }
    

    @Override
    protected IacucProtocolReferenceRule getNewProtocolReferenceRuleInstanceHook() {
        return new IacucProtocolReferenceRule();
    }
    
}
