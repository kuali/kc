/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.award.commitments;

import org.kuali.rice.kns.rule.BusinessRule;

/**
 * This interface defines methods that must be supplied in AwardBenefitsRatesRuleImpl
 */
public interface AwardBenefitsRatesRule extends BusinessRule {

    /**
     * This method is called to process business rules in any implementing class.
     * @param awardBenefitsRatesRuleEvent
     * @return
     */
    public boolean processBenefitsRatesBusinessRules(AwardBenefitsRatesRuleEvent 
            awardBenefitsRatesRuleEvent);
}
