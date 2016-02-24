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
package org.kuali.kra.award.commitments;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.rule.KcDocumentEventBase;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.rice.krad.util.ObjectUtils;

/**
 * 
 * This is the base event class for <code>AwardFandaRate</code> business object.
 */
public abstract class AwardFandaRateEvent extends KcDocumentEventBase {
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
    * @see org.kuali.core.rule.event.DocumentEvent#validate()
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
