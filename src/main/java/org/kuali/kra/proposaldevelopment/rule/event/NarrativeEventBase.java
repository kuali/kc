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
import org.kuali.kra.rule.event.KraDocumentEventBase;
import org.kuali.core.util.ObjectUtils;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;


/**
 * Base implementation for events triggered when a Key Person state is modified on a 
 * <code>{@link ProposalDevelopmentDocument}</code>
 *
 * @author $Author: gthomas $
 * @version $Revision: 1.1 $
 */
public abstract class NarrativeEventBase extends KraDocumentEventBase implements NarrativeEvent {
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(NarrativeEventBase.class);
    
    private Narrative narrative;
    
    protected NarrativeEventBase(String description, String errorPathPrefix, ProposalDevelopmentDocument document, Narrative narrative) {
        super(description, errorPathPrefix, document);

        // by doing a deep copy, we are ensuring that the business rule class can't update
        // the original object by reference
        this.narrative = (Narrative) ObjectUtils.deepCopy(narrative);
        
        logEvent();
    }

    
    /**
     * @return <code>{@link Narrative}</code> that triggered this event.
     */
    public Narrative getNarrative() {
        return narrative;
    }

    /**
     * @see org.kuali.core.rule.event.KualiDocumentEvent#validate()
     */
    public void validate() {
        super.validate();
        if (getNarrative() == null) {
            throw new IllegalArgumentException("invalid (null) narrative");
        }
    }

    /**
     * Logs the event type and some information about the associated accountingLine
     */
    protected void logEvent() {
        StringBuffer logMessage = new StringBuffer(StringUtils.substringAfterLast(this.getClass().getName(), "."));
        logMessage.append(" with ");

        // vary logging detail as needed
        if (getNarrative() == null) {
            logMessage.append("null Narrative");
        }
        else {
            logMessage.append(getNarrative().toString());
        }

        LOG.debug(logMessage);
    }
}
