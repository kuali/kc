/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
