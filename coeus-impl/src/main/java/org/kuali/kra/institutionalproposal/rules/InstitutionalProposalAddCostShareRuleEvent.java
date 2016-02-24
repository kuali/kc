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
package org.kuali.kra.institutionalproposal.rules;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.sys.framework.rule.KcDocumentEventBase;
import org.kuali.kra.institutionalproposal.document.InstitutionalProposalDocument;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposalCostShare;
import org.kuali.rice.krad.rules.rule.BusinessRule;


public class InstitutionalProposalAddCostShareRuleEvent extends KcDocumentEventBase {

    private static final Log LOG = LogFactory.getLog(InstitutionalProposalAddCostShareRuleEvent.class);
    
    private InstitutionalProposalCostShare institutionalProposalCostShare;

    public InstitutionalProposalAddCostShareRuleEvent(String errorPathPrefix, 
            InstitutionalProposalDocument institutionalProposalDocument,
            InstitutionalProposalCostShare institutionalProposalCostShare) {
        super("Cost Share", errorPathPrefix, institutionalProposalDocument);
        this.institutionalProposalCostShare = institutionalProposalCostShare;
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
    public InstitutionalProposalCostShare getCostShareForValidation() {
        return institutionalProposalCostShare;
    }
    
    
    @Override
    protected void logEvent() {
        LOG.info("Logging InstitutionalProposalCostShareRuleEvent");
    }

    @Override
    @SuppressWarnings("unchecked")
    public Class getRuleInterfaceClass() {
        return InstitutionalProposalAddCostShareRule.class;
    }

    @Override
    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((InstitutionalProposalAddCostShareRule)rule).processAddInstitutionalProposalCostShareBusinessRules(this);
    }

}
