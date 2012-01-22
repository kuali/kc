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
package org.kuali.kra.proposaldevelopment.rule.event;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.rules.SaveProposalSitesRule;
import org.kuali.kra.rule.event.KraDocumentEventBase;
import org.kuali.rice.krad.rules.rule.BusinessRule;

public class SaveProposalSitesEvent extends KraDocumentEventBase {
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(SaveProposalSitesEvent.class);

    public SaveProposalSitesEvent(String errorPathPrefix, ProposalDevelopmentDocument document) {
        super("Saving Proposal Sites of document " + getDocumentId(document), errorPathPrefix, document);
    }
    
    public Class<? extends BusinessRule> getRuleInterfaceClass() {
        return SaveProposalSitesRule.class;
    }

    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((SaveProposalSitesRule) rule).processSaveProposalSiteBusinessRules(this);
    }

    @Override
    protected void logEvent() {
        StringBuffer logMessage = new StringBuffer(StringUtils.substringAfterLast(this.getClass().getName(), "."));

        LOG.debug(logMessage);
    }
}
