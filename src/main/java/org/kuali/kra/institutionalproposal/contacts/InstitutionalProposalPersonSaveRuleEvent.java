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
package org.kuali.kra.institutionalproposal.contacts;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.institutionalproposal.document.InstitutionalProposalDocument;
import org.kuali.kra.rule.event.KraDocumentEventBase;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.BusinessRule;

/**
 * This class...
 */
public class InstitutionalProposalPersonSaveRuleEvent extends KraDocumentEventBase {

private static final Log LOG = LogFactory.getLog(InstitutionalProposalPersonSaveRuleEvent.class);
    
    private List<InstitutionalProposalPerson> projectPersons;
    
    public InstitutionalProposalPersonSaveRuleEvent(String description, String errorPathPrefix, Document document) {
        super(description, errorPathPrefix, document);
        this.projectPersons = ((InstitutionalProposalDocument) document).getInstitutionalProposal().getProjectPersons();
    }

    /**
     * Gets the InstitutionalProposal project persons. 
     * @return Returns the InstitutionalProposal project persons
     */
    public List<InstitutionalProposalPerson> getProjectPersons() {
        return projectPersons;
    }

    public Class<InstitutionalProposalProjectPersonAddRule> getRuleInterfaceClass() {
        return InstitutionalProposalProjectPersonAddRule.class;
    }

    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((InstitutionalProposalPersonSaveRule) rule).processInstitutionalProposalPersonSaveBusinessRules(this);
    }

    @Override
    protected void logEvent() {
        LOG.info("Logging InstitutionalProposalPersonSaveRuleEvent");
    }
}
