/*
 * Copyright 2007 The Kuali Foundation.
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

import org.apache.commons.lang.StringUtils;
import org.kuali.core.document.Document;
import org.kuali.core.rule.BusinessRule;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.rule.SaveKeyPersonRule;
import org.kuali.kra.rule.event.KraDocumentEventBase;

/**
 * Event triggered when a Key Person is added to a
 * <code>{@link ProposalDevelopmentDocument}</code>
 *
 * @author $Author: lprzybyl $
 * @version $Revision: 1.1 $
 */
public class SaveKeyPersonEvent extends KraDocumentEventBase {
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(SaveKeyPersonEvent.class);

    /**
     * Constructs an SaveKeyPersonEvent with the given errorPathPrefix, document, and proposalPerson.
     * 
     * @param errorPathPrefix
     * @param document
     * @see org.kuali.kra.rule.event.KraDocumentEventBase#KraDocumentEventBase(String, String, Document)
     */
    public SaveKeyPersonEvent(String errorPathPrefix, ProposalDevelopmentDocument document) {
        super("Saving personnel on document " + getDocumentId(document), errorPathPrefix, document);
        logEvent();
    }

    /**
     * Logs the event type and some information about the associated accountingLine
     */
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
    public SaveKeyPersonEvent(String errorPathPrefix, Document document) {
        this(errorPathPrefix, (ProposalDevelopmentDocument) document);
    }

    /**
     * @see org.kuali.core.rule.event.KualiDocumentEvent#getRuleInterfaceClass()
     */
    public Class getRuleInterfaceClass() {
        return SaveKeyPersonRule.class;
    }

    /**
     * @see org.kuali.core.rule.event.KualiDocumentEvent#invokeRuleMethod(org.kuali.core.rule.BusinessRule)
     */
    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((SaveKeyPersonRule) rule).processSaveKeyPersonBusinessRules((ProposalDevelopmentDocument) getDocument());
    }
}
