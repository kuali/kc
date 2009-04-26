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
import org.kuali.kra.award.bo.AwardReportTerm;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.rule.event.KraDocumentEventBase;
import org.kuali.rice.kns.util.ObjectUtils;

/**
 * 
 * This class represents the AwardReportTermEvent
 */
public abstract class AwardReportTermEvent extends KraDocumentEventBase {
    
    private static final org.apache.commons.logging.Log LOG = 
        org.apache.commons.logging.LogFactory.getLog(AwardReportTermEvent.class);

    private AwardReportTerm awardReportTerm;
    
    /**
     * 
     * Constructs a AwardReportTermEvent.java.
     * @param description
     * @param errorPathPrefix
     * @param document
     * @param awardReportTerm
     */
    protected AwardReportTermEvent(String description, String errorPathPrefix, 
            AwardDocument document, AwardReportTerm awardReportTerm){
        super("adding an award report term object" + getDocumentId(document), errorPathPrefix, document);
        this.awardReportTerm = (AwardReportTerm) ObjectUtils.deepCopy(awardReportTerm);
        logEvent();
    }    
    
    /**
     * @see org.kuali.core.rule.event.KualiDocumentEvent#validate()
     */
    public void validate() {
        super.validate();
        if (getAwardReportTerm() == null) {
            throw new IllegalArgumentException("invalid (null) awardReportTerm");
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

            if (getAwardReportTerm() == null) {
                logMessage.append("null Award Report Terms");
            }else {
                logMessage.append(getAwardReportTerm().toString());
            }

            LOG.debug(logMessage);            
        }
    }

    /**
     * 
     * This method returns the AwardReportTerm BO.
     * @return
     */
    public AwardReportTerm getAwardReportTerm() {
        return awardReportTerm;
    }
}