/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
     * @param fandaRateIndex The index of the F&amp;A rate in the list of F&amp;A rates
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
