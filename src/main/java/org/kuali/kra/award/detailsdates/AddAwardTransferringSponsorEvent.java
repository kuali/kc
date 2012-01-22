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
package org.kuali.kra.award.detailsdates;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.bo.Sponsor;
import org.kuali.kra.rule.event.KraDocumentEventBase;
import org.kuali.rice.krad.rules.rule.BusinessRule;

/**
 * This class represents the AddAwardTransferringSponsorEvent
 * 
 * @author Kuali Coeus development team
 */
public class AddAwardTransferringSponsorEvent extends KraDocumentEventBase {
    
    private static final org.apache.commons.logging.Log LOG = 
        org.apache.commons.logging.LogFactory.getLog(AddAwardTransferringSponsorEvent.class);
    
    private Sponsor sponsorToBecomeAwardTransferringSponsor;
    private Award award;
    
    /**
     * Constructs a AddAwardTransferringSponsorEvent.
     * @param description
     * @param errorPathPrefix
     * @param document
     * @param awardTransferringSponsor
     */
    public AddAwardTransferringSponsorEvent(String errorPathPrefix, AwardDocument document, Award award, Sponsor sponsor) {
        super("adding an award transferring sponsor object" + getDocumentId(document), errorPathPrefix, document);
        this.sponsorToBecomeAwardTransferringSponsor = sponsor;
        this.award = award;
        logEvent();
    }
    
    /**
     * @see org.kuali.core.rule.event.KualiDocumentEvent#invokeRuleMethod(org.kuali.core.rule.BusinessRule)
     */
    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((AwardDetailsAndDatesRule) rule).processAddAwardTransferringSponsorEvent(this);
    }
    
    /**
     * @see org.kuali.core.rule.event.KualiDocumentEvent#getRuleInterfaceClass()
     */
    public Class<AwardDetailsAndDatesRule> getRuleInterfaceClass() {
        return AwardDetailsAndDatesRule.class;
    }
    
    /**
     * Logs the event type and some information about the associated sponsor
     */
    protected void logEvent() {
        if(LOG.isDebugEnabled()) {
            StringBuilder logMessage = new StringBuilder(StringUtils.substringAfterLast(
                    this.getClass().getName(), "."));
            logMessage.append(" with ");

            if (getSponsorToBecomeAwardTransferringSponsor() == null) {
                logMessage.append("null Award Transferring Sponsor");
            } else {
                logMessage.append(getSponsorToBecomeAwardTransferringSponsor().toString());
            }

            LOG.debug(logMessage);            
        }
    }

    public Sponsor getSponsorToBecomeAwardTransferringSponsor() {
        return sponsorToBecomeAwardTransferringSponsor;
    }

    public Award getAward() {
        return award;
    }
    
}
