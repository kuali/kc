/*
 * Copyright 2005-2014 The Kuali Foundation
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
import org.kuali.coeus.sys.framework.rule.KcDocumentEventBase;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.BusinessRule;

import java.util.Map;


public class InstitutionalProposalPersonUnitCreditSplitRuleEvent extends KcDocumentEventBase {

private static final Log LOG = LogFactory.getLog(InstitutionalProposalPersonUnitCreditSplitRuleEvent.class);
    
    private InstitutionalProposalPerson projectPerson;
    private Map<String, ScaleTwoDecimal> totalsByCreditSplitType;
    
    /**
     * Constructs a InstitutionalProposalPersonCreditSplitRuleEvent
     * @param description
     * @param errorPathPrefix
     * @param document
     */
    public InstitutionalProposalPersonUnitCreditSplitRuleEvent(Document document, InstitutionalProposalPerson projectPerson, 
                                                Map<String, ScaleTwoDecimal> totalsByCreditSplitType) {
        super("Credit splits invalid", "document.awardList[0].creditSplits.*", document);
        this.projectPerson = projectPerson;
        this.totalsByCreditSplitType =  totalsByCreditSplitType;
    }

    /**
     * Gets the projectPerson attribute. 
     * @return Returns the projectPerson.
     */
    public InstitutionalProposalPerson getProjectPerson() {
        return projectPerson;
    }

    @Override
    public Class<InstitutionalProposalPersonUnitCreditSplitRule> getRuleInterfaceClass() {
        return InstitutionalProposalPersonUnitCreditSplitRule.class;
    }

    /**
     * Gets the totalsByCreditSplitType attribute. 
     * @return Returns the totalsByCreditSplitType.
     */
    public Map<String, ScaleTwoDecimal> getTotalsByCreditSplitType() {
        return totalsByCreditSplitType;
    }

    @Override
    public boolean invokeRuleMethod(BusinessRule rule) {
        return false;
    }

    @Override
    protected void logEvent() {
        LOG.info("Logging event");
    }
}
