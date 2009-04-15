/*
 * Copyright 2006-2009 The Kuali Foundation
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
package org.kuali.kra.proposaldevelopment.rule.event;

import org.apache.commons.lang.StringUtils;
import org.kuali.core.rule.BusinessRule;
import org.kuali.kra.proposaldevelopment.bo.ProposalChangedData;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.rule.ProposalDataOverrideRule;
import org.kuali.kra.rule.event.KraDocumentEventBase;

public class ProposalDataOverrideEvent extends KraDocumentEventBase {
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(ProposalDataOverrideEvent.class);
    
    private ProposalChangedData proposalChangedData;
    
    /**
     * Constructs an CopyProposalEvent.
     * 
     * @param document proposal development document
     * @param criteria the proposal copy criteria
     */
    public ProposalDataOverrideEvent(ProposalDevelopmentDocument document, ProposalChangedData proposalChangedData) {
        super("Override Proposal Data " + getDocumentId(document), "", document);
        this.proposalChangedData = proposalChangedData;
        logEvent();
    }
    
    /**
     * @see org.kuali.core.rule.event.KualiDocumentEventBase#validate()
     */
    public void validate() {
        super.validate();
    }
    
    /**
     * @see org.kuali.kra.rule.event.KraDocumentEventBase#logEvent()
     */
    @Override
    protected void logEvent() {
        StringBuffer logMessage = new StringBuffer(StringUtils.substringAfterLast(this.getClass().getName(), "."));
        LOG.debug(logMessage);
    }

    /**
     * @see org.kuali.core.rule.event.KualiDocumentEvent#getRuleInterfaceClass()
     */
    public Class getRuleInterfaceClass() {
        return ProposalDataOverrideRule.class;
    }

    /**
     * @see org.kuali.core.rule.event.KualiDocumentEvent#invokeRuleMethod(org.kuali.core.rule.BusinessRule)
     */
    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((ProposalDataOverrideRule) rule).processProposalDataOverrideRules(this);
    }

    public ProposalChangedData getProposalChangedData() {
        return proposalChangedData;
    }

    public void setProposalChangedData(ProposalChangedData proposalChangedData) {
        this.proposalChangedData = proposalChangedData;
    }
}
