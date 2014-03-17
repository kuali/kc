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
