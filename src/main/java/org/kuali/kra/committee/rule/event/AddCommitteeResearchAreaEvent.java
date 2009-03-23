/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.committee.rule.event;

import org.apache.commons.lang.StringUtils;
import org.kuali.core.rule.BusinessRule;
import org.kuali.kra.committee.document.CommitteeDocument;
import org.kuali.kra.committee.rule.AddCommitteeResearchAreaRule;
import org.kuali.kra.rule.event.KraDocumentEventBase;

/**
 * This class represents the event when a <code>{@link CommitteeResearchArea}</code> 
 * is added to a <code>{@link Committee}</code>.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class AddCommitteeResearchAreaEvent extends KraDocumentEventBase  {
    
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(AddCommitteeResearchAreaEvent.class);
    
    private String researchAreaCode;
    
    /**
     * Constructs a <code>{@link AddCommitteeResearchAreaEvent}</code>.
     * @param committeeDocument
     * @param researchAreaCode type code for the research area to add
     */
   public AddCommitteeResearchAreaEvent(CommitteeDocument committeeDocument, String researchAreaCode) {
        super("adding CommitteeMembership to document " + getDocumentId(committeeDocument), "", committeeDocument);
        this.researchAreaCode = researchAreaCode;
    }

    /**
     * @see org.kuali.kra.rule.event.KraDocumentEventBase#logEvent()
     */
    @Override
    protected void logEvent() {
        StringBuffer logMessage = new StringBuffer(StringUtils.substringAfterLast(this.getClass().getName(), "."));
        logMessage.append(" with " + researchAreaCode);
        LOG.debug(logMessage);
    }
    
    /**
     * @see org.kuali.core.rule.event.KualiDocumentEvent#getRuleInterfaceClass()
     */
    @SuppressWarnings("unchecked")
    public Class getRuleInterfaceClass() {
        return AddCommitteeResearchAreaRule.class;
    }

    /**
     * @see org.kuali.core.rule.event.KualiDocumentEvent#invokeRuleMethod(org.kuali.core.rule.BusinessRule)
     */
    public boolean invokeRuleMethod(BusinessRule rule) {
        CommitteeDocument committeeDocument = (CommitteeDocument) getDocument();
        return ((AddCommitteeResearchAreaRule) rule).processAddCommitteeResearchAreaRules(committeeDocument.getCommittee(), researchAreaCode);
    }
}
