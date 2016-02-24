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

import org.kuali.coeus.common.framework.type.InvestigatorCreditType;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * This class processes AwardPersonCreditSplitRules
 */
public class AwardPersonCreditSplitRuleImpl extends KcTransactionalDocumentRuleBase implements AwardPersonCreditSplitRule {
    private static final ScaleTwoDecimal MAX_TOTAL_VALUE = new ScaleTwoDecimal(100.00);

    @Override
    public boolean checkAwardPersonCreditSplitTotals(AwardPersonCreditSplitRuleEvent event) {
        int errorCount = 0; 
        for(InvestigatorCreditType creditType: loadInvestigatorCreditTypes()) {
            if(creditType.addsToHundred()) {
                ScaleTwoDecimal value = event.getTotalsByCreditSplitType().get(creditType.getCode());
                if(value == null) {
                    break;   // value may not have been initialized yet, so we don't want to block save
                }
                if(!MAX_TOTAL_VALUE.subtract(value).isZero()) {
                    reportError(AWARD_CREDIT_SPLIT_LIST_ERROR_KEY, AWARD_PERSON_CREDIT_SPLIT_ERROR_MSG_KEY, creditType.getDescription());
                    errorCount++;
                }
            }
        }
        
        return errorCount == 0;
        
    }
    
    @SuppressWarnings("unchecked")
    Collection<InvestigatorCreditType> loadInvestigatorCreditTypes() {
        Map<String,String> valueMap = new HashMap<String, String>();
        valueMap.put("active", "true");
        return getBusinessObjectService().findMatching(InvestigatorCreditType.class, valueMap);
    }
}
