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

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.core.rule.BusinessRule;
import org.kuali.core.util.ObjectUtils;
import org.kuali.kra.proposaldevelopment.bo.NarrativeUserRights;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.rule.NewNarrativeUserRightsRule;
import org.kuali.kra.rule.event.KraDocumentEventBase;

/**
 * The NewNarrativeUserRightsEvent is generated when the User Rights for
 * a narrative are to be changed.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class NewNarrativeUserRightsEvent extends KraDocumentEventBase {
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(NewNarrativeUserRightsEvent.class);
    
    private List<NarrativeUserRights> newNarrativeUserRights;
    private int narrativeIndex;
    
    /**
     * Constructs a NewNarrativeUserRightsEvent.
     * @param document proposal development document
     * @param newNarrativeUserRights the list of user rights for a narrative
     * @param narrativeIndex the narrative to change in the proposal
     */
    public NewNarrativeUserRightsEvent(ProposalDevelopmentDocument document, List<NarrativeUserRights> newNarrativeUserRights, int narrativeIndex) {
        super("editing narrative user rights to document " + getDocumentId(document), "", document);
    
        // by doing a deep copy, we are ensuring that the business rule class can't update
        // the original object by reference
        this.newNarrativeUserRights = (List<NarrativeUserRights>) ObjectUtils.deepCopy((Serializable) newNarrativeUserRights);
        this.narrativeIndex = narrativeIndex;
        
        logEvent();
    }
    
    /**
     * @see org.kuali.core.rule.event.KualiDocumentEventBase#validate()
     */
    public void validate() {
        super.validate();
        if (this.newNarrativeUserRights == null) {
            throw new IllegalArgumentException("invalid (null) newNarrativeUserRights");
        }
    }
    
    /**
     * @see org.kuali.kra.rule.event.KraDocumentEventBase#logEvent()
     */
    @Override
    protected void logEvent() {
        StringBuffer logMessage = new StringBuffer(StringUtils.substringAfterLast(this.getClass().getName(), "."));
        logMessage.append(" with ");

        // vary logging detail as needed
        if (this.newNarrativeUserRights == null) {
            logMessage.append("null newNarrativeUserRights");
        }
        else {
            logMessage.append(this.newNarrativeUserRights.toString());
            logMessage.append(Integer.toString(this.narrativeIndex));
        }

        LOG.debug(logMessage);
    }

    /**
     * @see org.kuali.core.rule.event.KualiDocumentEvent#getRuleInterfaceClass()
     */
    public Class getRuleInterfaceClass() {
        return NewNarrativeUserRightsRule.class;
    }

    /**
     * @see org.kuali.core.rule.event.KualiDocumentEvent#invokeRuleMethod(org.kuali.core.rule.BusinessRule)
     */
    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((NewNarrativeUserRightsRule) rule).processNewNarrativeUserRightsBusinessRules((ProposalDevelopmentDocument) this.getDocument(), 
                                                                        this.newNarrativeUserRights, this.narrativeIndex);
    }
}
