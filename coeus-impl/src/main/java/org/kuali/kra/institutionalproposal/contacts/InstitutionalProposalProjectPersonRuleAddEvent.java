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
import org.kuali.kra.award.contacts.AwardProjectPersonRuleAddEvent;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.BusinessRule;


public class InstitutionalProposalProjectPersonRuleAddEvent extends KcDocumentEventBase {

private static final Log LOG = LogFactory.getLog(AwardProjectPersonRuleAddEvent.class);
    
    private InstitutionalProposalPerson newProjectPerson;
    
    protected InstitutionalProposalProjectPersonRuleAddEvent(String description, String errorPathPrefix, Document document, InstitutionalProposalPerson newProjectPerson) {
        super(description, errorPathPrefix, document);
        this.newProjectPerson = newProjectPerson;
    }

    /**
     * Gets the newProjectPerson attribute. 
     * @return Returns the newProjectPerson.
     */
    public InstitutionalProposalPerson getNewProjectPerson() {
        return newProjectPerson;
    }

    public Class<InstitutionalProposalProjectPersonAddRule> getRuleInterfaceClass() {
        return InstitutionalProposalProjectPersonAddRule.class;
    }

    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((InstitutionalProposalProjectPersonAddRule) rule).processAddInstitutionalProposalProjectPersonBusinessRules(this);
    }

    @Override
    protected void logEvent() {
        LOG.info("Logging AddInstitutionalProposalProjectPersonRuleEvent");
    }

}
