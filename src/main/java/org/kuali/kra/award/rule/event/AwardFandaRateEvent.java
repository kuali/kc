/*
 * Copyright 2006-2008 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.award.rule.event;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.award.bo.AwardFandaRate;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.rule.event.KraDocumentEventBase;
import org.kuali.rice.kns.util.ObjectUtils;

/**
 * 
 * This is the base event class for <code>AwardFandaRate</code> business object.
 */
public abstract class AwardFandaRateEvent extends KraDocumentEventBase {
    private static final org.apache.commons.logging.Log LOG = 
        org.apache.commons.logging.LogFactory.getLog(AwardFandaRateEvent.class);

    private AwardFandaRate awardFandaRate;

    /**
     * 
     * Constructs a AwardFandaRateEventBase.java.
     * @param description
     * @param errorPathPrefix
     * @param document
     * @param awardFandaRate
     */
    protected AwardFandaRateEvent(String description, String errorPathPrefix, 
            AwardDocument document, AwardFandaRate awardFandaRate) {
        super(description, errorPathPrefix, document);
    
        // by doing a deep copy, we are ensuring that the business rule class can't update
        // the original object by reference
        this.awardFandaRate = (AwardFandaRate) ObjectUtils.deepCopy(awardFandaRate);
        logEvent();
    }

    /**
    * @return <code>{@link AwardFandaRate}</code> that triggered this event.
    */
    public AwardFandaRate getAwardFandaRate() {
        return awardFandaRate;
    }

    /**
    * @see org.kuali.core.rule.event.KualiDocumentEvent#validate()
    */
    public void validate() {
        super.validate();
        if (getAwardFandaRate() == null) {
            throw new IllegalArgumentException("invalid (null) award fandaRate");
        }
    }

    /**
    * Logs the event type and some information about the associated special review
    */
    protected void logEvent() {
        if(LOG.isDebugEnabled()){
            StringBuilder logMessage = new StringBuilder(StringUtils.substringAfterLast(
                    this.getClass().getName(), "."));
            logMessage.append(" with ");
            
            // vary logging detail as needed
            if (getAwardFandaRate() == null) {
                logMessage.append("null award F and a Rate Event");
            }else {
                logMessage.append(getAwardFandaRate().toString());
            }
            
            LOG.debug(logMessage);    
        }
    }
    
}
