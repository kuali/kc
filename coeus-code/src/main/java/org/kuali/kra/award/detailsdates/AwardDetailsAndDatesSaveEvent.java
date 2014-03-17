/*
 * Copyright 2005-2014 The Kuali Foundation
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
