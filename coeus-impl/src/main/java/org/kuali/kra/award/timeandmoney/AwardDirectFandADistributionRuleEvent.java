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
package org.kuali.kra.award.timeandmoney;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.sys.framework.rule.KcDocumentEventBase;
import org.kuali.kra.timeandmoney.document.TimeAndMoneyDocument;
import org.kuali.rice.krad.rules.rule.BusinessRule;

import java.util.List;

/**
 * This class represents the rule event for Award Direct F and A Distribution tab.
 */
public class AwardDirectFandADistributionRuleEvent extends KcDocumentEventBase {

    private static final Log LOG = LogFactory.getLog(AwardDirectFandADistributionRuleEvent.class);
    private static final String DIRECT_FNA_DISTRIBUTION = "Direct F and A Distribution";
    
    AwardDirectFandADistribution awardDirectFandADistribution;
    List<AwardDirectFandADistribution> awardDirectFandADistributions;
    int currentIndex;
    
    /**
     * Constructor for rule event for add rules.
     * @param errorPathPrefix
     * @param awardDocument
     * @param awardDirectFandADistribution
     */
    public AwardDirectFandADistributionRuleEvent(String errorPathPrefix, 
                                                        TimeAndMoneyDocument timeAndMoneyDocument, AwardDirectFandADistribution awardDirectFandADistribution) {
            super(DIRECT_FNA_DISTRIBUTION, errorPathPrefix, timeAndMoneyDocument);
            this.awardDirectFandADistribution = awardDirectFandADistribution;
    }
    
    /**
     * Constructor for rule event for save rules.
     * @param errorPathPrefix
     * @param awardDocument
     * @param awardDirectFandADistributions
     */
    public AwardDirectFandADistributionRuleEvent(String errorPathPrefix, 
                                                        TimeAndMoneyDocument timeAndMoneyDocument, 
                                                            List<AwardDirectFandADistribution> awardDirectFandADistributions) {
            super(DIRECT_FNA_DISTRIBUTION, errorPathPrefix, timeAndMoneyDocument);
            this.awardDirectFandADistributions = awardDirectFandADistributions;
    }
    
    /**
     * Convenience method to return an AwardDocument
     * @return
     */
    public TimeAndMoneyDocument getTimeAndMoneyDocument() {
        return (TimeAndMoneyDocument) getDocument();
    }
    
    /**
     * This method returns the awardDirectFandADistribution for validation
     * @return
     */
    public AwardDirectFandADistribution getAwardDirectFandADistributionForValidation() {
        return awardDirectFandADistribution;
    }
    
    /**
     * This method returns the list of awardDirectFandADistributions for validation
     * @return
     */
    public List<AwardDirectFandADistribution> getAwardDirectFandADistributionsForValidation() {
        return awardDirectFandADistributions;
    }
    
    
    @Override
    protected void logEvent() {
        LOG.info("Logging AwardDirectFandADistributionRuleEvent");
    }

    @Override
    @SuppressWarnings("unchecked")
    public Class getRuleInterfaceClass() {
        return AwardDirectFandADistributionRule.class;
    }

    @Override
    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((AwardDirectFandADistributionRule) rule).processAddAwardDirectFandADistributionBusinessRules(this);
    }

    /**
     * Gets the currentIndex attribute. 
     * @return Returns the currentIndex.
     */
    public int getCurrentIndex() {
        return currentIndex;
    }

    /**
     * Sets the currentIndex attribute value.
     * @param currentIndex The currentIndex to set.
     */
    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }

}
