/*
 * Copyright 2008 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.proposaldevelopment.rule.event;

import static org.kuali.kra.logging.FormattedLogger.info;

import org.apache.commons.lang.StringUtils;
import org.kuali.core.document.Document;
import org.kuali.core.rule.BusinessRule;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.rule.CalculateCreditSplitRule;
import org.kuali.kra.proposaldevelopment.rule.SaveKeyPersonRule;
import org.kuali.kra.rule.event.KraDocumentEventBase;

public class CalculateCreditSplitEvent  extends KraDocumentEventBase  {

    
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(SaveKeyPersonEvent.class);
 
     
    /**
     * Constructs an CalculateCreditSplitEvent with the given errorPathPrefix, document.
     * 
     * @param errorPathPrefix
     * @param document
     * @see org.kuali.kra.rule.event.KraDocumentEventBase#KraDocumentEventBase(String, String, Document)
     */
    public CalculateCreditSplitEvent(String errorPathPrefix, ProposalDevelopmentDocument document) {
        super("Saving personnel on document " + getDocumentId(document), errorPathPrefix, document);
        logEvent();
    }
    

    @Override
    protected void logEvent() {
        StringBuffer logMessage = new StringBuffer(StringUtils.substringAfterLast(this.getClass().getName(), "."));
        logMessage.append(" with ");
        
        for (ProposalPerson person : ((ProposalDevelopmentDocument) getDocument()).getProposalPersons()) {
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
     * @param proposalPerson
     */
    public CalculateCreditSplitEvent(String errorPathPrefix, Document document) {
        this(errorPathPrefix, (ProposalDevelopmentDocument) document);
    }
    
    
    public Class getRuleInterfaceClass() {
        // TODO Auto-generated method stub
        return CalculateCreditSplitRule.class;
    }
    /**
     * @see org.kuali.core.rule.event.KualiDocumentEvent#invokeRuleMethod(org.kuali.core.rule.BusinessRule)
     */
    public boolean invokeRuleMethod(BusinessRule rule) {
        info("Calling processCalculateCreditSplitBusinessRules on %s", rule.getClass().getSimpleName());
        return ((CalculateCreditSplitRule) rule).processCalculateCreditSplitBusinessRules((ProposalDevelopmentDocument) getDocument());
    }

   
    
    
    

}
