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
import org.kuali.kra.award.bo.AwardReportTermRecipient;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.rule.event.KraDocumentEventBase;
import org.kuali.rice.kns.util.ObjectUtils;

/**
 * 
 * This class represents the AwardReportTermRecipientEvent
 */
public abstract class AwardReportTermRecipientEvent extends KraDocumentEventBase {
    
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(
            AwardReportTermRecipientEvent.class);

    private AwardReportTermRecipient awardReportTermRecipient;
    
    /**
     * 
     * Constructs a AwardReportTermRecipientEvent.java.
     * @param description
     * @param errorPathPrefix
     * @param document
     * @param awardReportTermRecipient
     */
    protected AwardReportTermRecipientEvent(String description, String errorPathPrefix, 
            AwardDocument document, AwardReportTermRecipient awardReportTermRecipient){
        super("Constructing an AwardReportTermRecipientEvent" + getDocumentId(document), errorPathPrefix, 
                document);
        this.awardReportTermRecipient = (AwardReportTermRecipient) ObjectUtils.deepCopy(
                                                                       awardReportTermRecipient);
        logEvent();
    }    
    
    /**
     * @see org.kuali.core.rule.event.KualiDocumentEvent#validate()
     */
    public void validate() {
        super.validate();
        if (getAwardReportTermRecipient() == null) {
            throw new IllegalArgumentException("invalid (null) awardReportTermRecipient");
        }
    }

    /**
     * Logs the event type and some information about the associated special review
     */
    protected void logEvent() {
        if(LOG.isDebugEnabled()){
            StringBuilder logMessage = new StringBuilder(
                    StringUtils.substringAfterLast(this.getClass().getName(), "."));
            logMessage.append(" with ");

            if (getAwardReportTermRecipient() == null) {
                logMessage.append("null Award Report Term Recipient");
            }else {
                logMessage.append(getAwardReportTermRecipient().toString());
            }

            LOG.debug(logMessage);
        }
    }

    /**
     * 
     * This method returns the AwardReportTermRecipient BO
     * @return
     */
    public AwardReportTermRecipient getAwardReportTermRecipient() {
        return awardReportTermRecipient;
    }
}