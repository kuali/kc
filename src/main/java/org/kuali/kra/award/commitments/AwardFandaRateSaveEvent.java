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
package org.kuali.kra.award.commitments;

import org.kuali.kra.award.AwardDocumentRule;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.rice.krad.rules.rule.BusinessRule;

public class AwardFandaRateSaveEvent extends AwardFandaRateEvent {
    private int fandaRateIndex;

    /**
     * Constructs an AwardFandaRateSaveEvent with the given errorPathPrefix, 
     * awardDocument, and awardFandaRate.
     * 
     * @param errorPathPrefix
     * @param awardDocument
     * @param fandaRateIndex The index of the F&A rate in the list of F&A rates
     */
    public AwardFandaRateSaveEvent(String errorPathPrefix, AwardDocument awardDocument, int fandaRateIndex) {
        super("Saving F&A Rates in Award document " + getDocumentId(awardDocument)
                , errorPathPrefix, awardDocument, awardDocument.getAward().getAwardFandaRate().get(fandaRateIndex));
        this.fandaRateIndex = fandaRateIndex;
    }

    public Class<? extends BusinessRule> getRuleInterfaceClass() {
        return AwardFandaRateSaveRule.class;
    }

    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((AwardDocumentRule) rule).processSaveFandaRateBusinessRules(this);
    }

    public int getFandaRateIndex() {
        return fandaRateIndex;
    }

}
