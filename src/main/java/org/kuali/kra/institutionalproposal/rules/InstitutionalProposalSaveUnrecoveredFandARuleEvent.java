/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.institutionalproposal.rules;

import org.apache.log4j.Logger;
import org.kuali.kra.institutionalproposal.document.InstitutionalProposalDocument;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposalUnrecoveredFandA;
import org.kuali.kra.rule.event.KraDocumentEventBase;
import org.kuali.rice.kns.rule.BusinessRule;

/**
 * This class...
 */
public class InstitutionalProposalSaveUnrecoveredFandARuleEvent extends KraDocumentEventBase {

private static final Logger LOG = Logger.getLogger(InstitutionalProposalAddCostShareRuleEvent.class);
    
    private InstitutionalProposalUnrecoveredFandA institutionalProposalUnrecoveredFandA;

    public InstitutionalProposalSaveUnrecoveredFandARuleEvent(String errorPathPrefix, 
            InstitutionalProposalDocument institutionalProposalDocument,
            InstitutionalProposalUnrecoveredFandA institutionalProposalUnrecoveredFandA) {
        super("Cost Share", errorPathPrefix, institutionalProposalDocument);
        this.institutionalProposalUnrecoveredFandA = institutionalProposalUnrecoveredFandA;
    }
    
    /**
     * Convenience method to return an InstitutionalProposalDocument
     * @return
     */
    public InstitutionalProposalDocument getInstitutionalProposalDocument() {
        return (InstitutionalProposalDocument) getDocument();
    }
    
    /**
     * This method returns the equipment item for validation
     * @return
     */
    public InstitutionalProposalUnrecoveredFandA getUnrecoveredFandAForValidation() {
        return institutionalProposalUnrecoveredFandA;
    }
    
    
    /**
     * @see org.kuali.kra.rule.event.KraDocumentEventBase#logEvent()
     */
    @Override
    protected void logEvent() {
        LOG.info("Logging InstitutionalProposalUnrecoveredFandARuleEvent");
    }

    /**
     * @see org.kuali.core.rule.event.KualiDocumentEvent#getRuleInterfaceClass()
     */
    @SuppressWarnings("unchecked")
    public Class getRuleInterfaceClass() {
        return InstitutionalProposalUnrecoveredFandARule.class;
    }

    /**
     * @see org.kuali.core.rule.event.KualiDocumentEvent#invokeRuleMethod(org.kuali.core.rule.BusinessRule)
     */
    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((InstitutionalProposalUnrecoveredFandARule)rule).processSaveInstitutionalProposalUnrecoveredFandABusinessRules(this);
    }

}
