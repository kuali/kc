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
package org.kuali.kra.award.detailsdates;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.sponsor.Sponsor;
import org.kuali.coeus.sys.framework.rule.KcDocumentEventBase;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.rice.krad.rules.rule.BusinessRule;

/**
 * This class represents the AddAwardTransferringSponsorEvent
 * 
 * @author Kuali Coeus development team
 */
public class AddAwardTransferringSponsorEvent extends KcDocumentEventBase {
    
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
    
    @Override
    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((AwardDetailsAndDatesRule) rule).processAddAwardTransferringSponsorEvent(this);
    }
    
    @Override
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
