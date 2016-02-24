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

import org.kuali.coeus.sys.framework.rule.KcDocumentEventBase;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.rice.krad.rules.rule.BusinessRule;


public class AwardDetailsAndDatesSaveEvent extends KcDocumentEventBase {

    private static final org.apache.commons.logging.Log LOG = 
        org.apache.commons.logging.LogFactory.getLog(AwardDetailsAndDatesSaveEvent.class);
    
    private Award award;
    
    /**
     * Constructs a AddAwardTransferringSponsorEvent.
     * @param description
     * @param errorPathPrefix
     * @param document
     * @param awardTransferringSponsor
     */
    public AwardDetailsAndDatesSaveEvent(AwardDocument document, Award award) {
        super("adding an award transferring sponsor object" + getDocumentId(document), "awardAmountInfos", document);
        this.award = award;
        logEvent();
    }
    
    @Override
    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((AwardDetailsAndDatesRule) rule).processSaveAwardDetailsAndDates(this);
    }
    
    @Override
    public Class<AwardDetailsAndDatesRule> getRuleInterfaceClass() {
        return AwardDetailsAndDatesRule.class;
    }
    
    @Override
    protected void logEvent() {
        LOG.info("Logging AwardDetailsAndDatesSaveEvent");
    }

    public Award getAward() {
        return award;
    }

}
