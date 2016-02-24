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
package org.kuali.coeus.propdev.impl.person.creditsplit;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.sys.framework.rule.KcDocumentEventBase;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.BusinessRule;

public class CalculateCreditSplitEvent  extends KcDocumentEventBase {

    private static final Log LOG = LogFactory.getLog(CalculateCreditSplitEvent.class);

    /**
     * Constructs an CalculateCreditSplitEvent with the given errorPathPrefix, document.
     * 
     * @param errorPathPrefix
     * @param document
     * @see org.kuali.coeus.sys.framework.rule.KcDocumentEventBase#KcDocumentEventBase(String, String, Document)
     */
    public CalculateCreditSplitEvent(String errorPathPrefix, ProposalDevelopmentDocument document) {
        super("Saving personnel on document " + getDocumentId(document), errorPathPrefix, document);
        logEvent();
    }
    

    @Override
    protected void logEvent() {
        StringBuffer logMessage = new StringBuffer(StringUtils.substringAfterLast(this.getClass().getName(), "."));
        logMessage.append(" with ");
        
        for (ProposalPerson person : ((ProposalDevelopmentDocument) getDocument()).getDevelopmentProposal().getProposalPersons()) {
            logMessage.append(person.toString());
            logMessage.append(", ");
        }
        
        if (logMessage.substring(logMessage.length() - 2).equals(", ")) {
            logMessage.delete(logMessage.length() - 2, logMessage.length());
        }

        LOG.debug(logMessage);
        
    }

    
    /**
     * Constructs an SaveKeyPersonEvent with the given errorPathPrefix, document, and proposalPerson.
     * 
     * @param errorPathPrefix
     * @param document
     */
    public CalculateCreditSplitEvent(String errorPathPrefix, Document document) {
        this(errorPathPrefix, (ProposalDevelopmentDocument) document);
    }
    
    
    public Class getRuleInterfaceClass() {

        return CalculateCreditSplitRule.class;
    }
    @Override
    public boolean invokeRuleMethod(BusinessRule rule) {
        LOG.info("Calling processCalculateCreditSplitBusinessRules on " + rule.getClass().getSimpleName());
        return ((CalculateCreditSplitRule) rule).processCalculateCreditSplitBusinessRules((ProposalDevelopmentDocument) getDocument());
    }

   
    
    
    

}
