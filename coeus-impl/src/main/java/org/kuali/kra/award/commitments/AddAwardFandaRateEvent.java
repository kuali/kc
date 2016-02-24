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
