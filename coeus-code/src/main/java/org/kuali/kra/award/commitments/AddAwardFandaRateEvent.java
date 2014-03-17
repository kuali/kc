/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.award.commitments;

import org.kuali.kra.award.document.AwardDocument;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.BusinessRule;

/**
 * 
 * This class represents the AddAwardFandaRateEvent
 */
public class AddAwardFandaRateEvent extends AwardFandaRateEvent{
    
    /**
     * Constructs an AddAwardFandaRateEvent with the given errorPathPrefix, 
     * awardDocument, and awardFandaRate.
     * 
     * @param errorPathPrefix
     * @param awardDocument
     * @param awardFandaRate
     */
    public AddAwardFandaRateEvent(String errorPathPrefix, AwardDocument awardDocument
            , AwardFandaRate awardFandaRate) {
        super("adding Indirect Cost Rate to Award document " + getDocumentId(awardDocument)
                , errorPathPrefix, awardDocument, awardFandaRate);
    }

    /**
     * Constructs an AddAwardFandaRateEvent with the given errorPathPrefix
     * , document, and awardFandaRate.
     * 
     * @param errorPathPrefix
     * @param document
     * @param awardFandaRate
     */
    public AddAwardFandaRateEvent(String errorPathPrefix, Document document
            , AwardFandaRate awardFandaRate) {
        this(errorPathPrefix, (AwardDocument) document, awardFandaRate);
    }

    @Override
    public Class<? extends BusinessRule> getRuleInterfaceClass() {
        return AwardFandaRateRule.class;
    }

    @Override
    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((AddFandaRateRule) rule).processAddFandaRateBusinessRules(this);
    }

}