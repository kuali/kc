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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.award.contacts.AwardPersonUnitRuleAddEvent;
import org.kuali.kra.rule.event.KraDocumentEventBase;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.BusinessRule;

/**
 * This class...
 */
public class InstitutionalProposalPersonUnitRuleAddEvent extends KraDocumentEventBase {

private static final Log LOG = LogFactory.getLog(AwardPersonUnitRuleAddEvent.class);
    
    private InstitutionalProposalPersonUnit newPersonUnit;
    private InstitutionalProposalPerson projectPerson;
    
    protected InstitutionalProposalPersonUnitRuleAddEvent(String description, String errorPathPrefix, Document document, 
            InstitutionalProposalPerson projectPerson, InstitutionalProposalPersonUnit newPersonUnit) {
        super(description, errorPathPrefix, document);
        this.newPersonUnit = newPersonUnit;
        this.projectPerson = projectPerson;
    }

    /**
     * @return
     */
    public InstitutionalProposalPersonUnit getNewPersonUnit() {
        return newPersonUnit;
    }

    public InstitutionalProposalPerson getProjectPerson() {
        return projectPerson;
    }
    
    public Class<InstitutionalProposalPersonUnitAddRule> getRuleInterfaceClass() {
        return InstitutionalProposalPersonUnitAddRule.class;
    }

    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((InstitutionalProposalPersonUnitAddRule) rule).processAddInstitutionalProposalPersonUnitBusinessRules(this);
    }

    @Override
    protected void logEvent() {
        LOG.info("Logging AddInstitutionalProposalProjectPersonRuleEvent");
    }

}
