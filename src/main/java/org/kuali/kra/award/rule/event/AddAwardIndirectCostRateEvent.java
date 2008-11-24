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

import org.kuali.core.document.Document;
import org.kuali.core.rule.BusinessRule;
import org.kuali.kra.award.bo.AwardIndirectCostRate;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.rule.AddIndirectCostRateRule;

/**
 * 
 * This class represents the AddAwardIndirectCostRateEvent
 */
public class AddAwardIndirectCostRateEvent extends AwardIndirectCostRateEventBase{
    
    /**
     * Constructs an AddAwardIndirectCostRateEvent with the given errorPathPrefix, awardDocument, and awardIndirectCostRate.
     * 
     * @param errorPathPrefix
     * @param awardDocument
     * @param awardIndirectCostRate
     */
    public AddAwardIndirectCostRateEvent(String errorPathPrefix, AwardDocument awardDocument, AwardIndirectCostRate awardIndirectCostRate) {
        super("adding Indirect Cost Rate to Award document " + getDocumentId(awardDocument), errorPathPrefix, awardDocument, awardIndirectCostRate);
    }

    /**
     * Constructs an AddAwardIndirectCostRateEvent with the given errorPathPrefix, document, and awardIndirectCostRate.
     * 
     * @param errorPathPrefix
     * @param document
     * @param awardIndirectCostRate
     */
    public AddAwardIndirectCostRateEvent(String errorPathPrefix, Document document, AwardIndirectCostRate awardIndirectCostRate) {
        this(errorPathPrefix, (AwardDocument) document, awardIndirectCostRate);
    }

    /**
     * @see org.kuali.core.rule.event.KualiDocumentEvent#getRuleInterfaceClass()
     */
    public Class getRuleInterfaceClass() {
        return AddIndirectCostRateRule.class;
    }

    /**
     * @see org.kuali.core.rule.event.KualiDocumentEvent#invokeRuleMethod(org.kuali.core.rule.BusinessRule)
     */
    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((AddIndirectCostRateRule) rule).processAddIndirectCostRatesBusinessRules(this);
    }

}