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
package org.kuali.kra.award.contacts;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.sys.framework.rule.KcDocumentEventBase;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.BusinessRule;

import java.util.Map;

/**
 * This class is used by AwardPersonCreditSplitRule implementers
 */
public class AwardPersonCreditSplitRuleEvent extends KcDocumentEventBase {
    private static final Log LOG = LogFactory.getLog(AwardPersonCreditSplitRuleEvent.class);
    
    private Map<String, ScaleTwoDecimal> totalsByCreditSplitType;
    
    /**
     * Constructs a AwardPersonCreditSplitRuleEvent
     * @param description
     * @param errorPathPrefix
     * @param document
     */
    public AwardPersonCreditSplitRuleEvent(Document document, Map<String, ScaleTwoDecimal> totalsByCreditSplitType) {
        super("Credit splits invalid", "document.awardList[0].creditSplits.*", document);
        this.totalsByCreditSplitType = totalsByCreditSplitType;
    }

    @Override
    protected void logEvent() {
        LOG.info("Logging event");
    }

    @Override
    public Class<AwardPersonCreditSplitRule> getRuleInterfaceClass() {
        return AwardPersonCreditSplitRule.class;
    }

    @Override
    public boolean invokeRuleMethod(BusinessRule rule) {
        return false;
    }

    /**
     * Gets the totalsByCreditSplitType attribute. 
     * @return Returns the totalsByCreditSplitType.
     */
    public Map<String, ScaleTwoDecimal> getTotalsByCreditSplitType() {
        return totalsByCreditSplitType;
    }
}
