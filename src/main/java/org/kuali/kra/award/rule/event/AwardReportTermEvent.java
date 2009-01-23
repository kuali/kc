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
import org.kuali.core.util.ObjectUtils;
import org.kuali.kra.award.bo.AwardReportTerm;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.rule.event.AddSpecialReviewEvent;
import org.kuali.kra.rule.event.KraDocumentEventBase;

/**
 * 
 * This class represents the AddAwardReportTermEvent
 */
public abstract class AwardReportTermEvent extends KraDocumentEventBase {
    
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(AddSpecialReviewEvent.class);

    private AwardReportTerm awardReportTerm;
    /**
     * Constructs an AddProposalSpecialReviewEvent with the given errorPathPrefix, document, and proposalSpecialReview.
     * 
     * @param errorPathPrefix
     * @param proposalDevelopmentDocument
     * @param proposalSpecialReview
     */
    protected AwardReportTermEvent(String description, String errorPathPrefix, 
            AwardDocument document, AwardReportTerm awardReportTerm){
        super("adding special review to document " + getDocumentId(document), errorPathPrefix, document);
        this.awardReportTerm = (AwardReportTerm) ObjectUtils.deepCopy(awardReportTerm);
        logEvent();
    }    
    
    /**
     * @see org.kuali.core.rule.event.KualiDocumentEvent#validate()
     */
    public void validate() {
        super.validate();
        if (getAwardReportTerm() == null) {
            throw new IllegalArgumentException("invalid (null) specialreview");
        }
    }

    /**
     * Logs the event type and some information about the associated special review
     */
    protected void logEvent() {
        StringBuffer logMessage = new StringBuffer(StringUtils.substringAfterLast(this.getClass().getName(), "."));
        logMessage.append(" with ");

        // vary logging detail as needed
        if (getAwardReportTerm() == null) {
            logMessage.append("null Award Report Terms");
        }
        else {
            logMessage.append(getAwardReportTerm().toString());
        }

        LOG.debug(logMessage);
    }

    public AwardReportTerm getAwardReportTerm() {
        return awardReportTerm;
    }
}