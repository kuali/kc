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
package org.kuali.kra.award.rules;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.award.bo.AwardIndirectCostRate;
import org.kuali.kra.award.rule.AddIndirectCostRateRule;
import org.kuali.kra.award.rule.event.AddAwardIndirectCostRateEvent;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.rules.ResearchDocumentRuleBase;

/**
 * 
 * This class represents the AwardIndirectCostRateRule
 */
public class AwardIndirectCostRateRule  extends ResearchDocumentRuleBase implements AddIndirectCostRateRule {
    private static final String NEW_INDIRECT_COST_RATE = "newAwardIndirectCostRate";

    /**
     * 
     * @see org.kuali.kra.award.rule.AddIndirectCostRateRule#processAddIndirectCostRatesBusinessRules(org.kuali.kra.award.rule.event.AddAwardIndirectCostRateEvent)
     */
    public boolean processAddIndirectCostRatesBusinessRules(AddAwardIndirectCostRateEvent addAwardIndirectCostRateEvent) {
        AwardIndirectCostRate awardIndirectCostRate = addAwardIndirectCostRateEvent.getAwardIndirectCostRate();        
        boolean rulePassed = true;
        String errorPath = NEW_INDIRECT_COST_RATE;
        String[] dateParams = {"End Date","Start Date"};

        if(awardIndirectCostRate.getApplicableIndirectCostRate()==null || StringUtils.isBlank(awardIndirectCostRate.getApplicableIndirectCostRate().toString())){
            rulePassed = false;
            reportError(errorPath+".applicableIndirectCostRate", KeyConstants.ERROR_REQUIRED_APPLICABLE_INDIRECT_COST_RATE);
        }
        if(awardIndirectCostRate.getIdcRateTypeCode()==null || StringUtils.isBlank(awardIndirectCostRate.getIdcRateTypeCode().toString())){
            rulePassed = false;
            reportError(errorPath+".idcRateTypeCode", KeyConstants.ERROR_REQUIRED_INDIRECT_RATE_TYPE_CODE);
        }
        if(awardIndirectCostRate.getFiscalYear()==null || StringUtils.isBlank(awardIndirectCostRate.getFiscalYear())){
            rulePassed = false;
            reportError(errorPath+".fiscalYear", KeyConstants.ERROR_REQUIRED_FISCAL_YEAR);
        }else if(awardIndirectCostRate.getFiscalYear().length()!=4){
            rulePassed = false;
            reportError(errorPath+".fiscalYear", KeyConstants.ERROR_FISCAL_YEAR_INCORRECT_FORMAT);
        }else{
            try{
                Integer.parseInt(awardIndirectCostRate.getFiscalYear());
            }catch(NumberFormatException e){
                rulePassed = false;
                reportError(errorPath+".fiscalYear", KeyConstants.ERROR_FISCAL_YEAR_INCORRECT_FORMAT);
            }
        }
        if(awardIndirectCostRate.getStartDate()==null || StringUtils.isBlank(awardIndirectCostRate.getStartDate().toString())){
            rulePassed = false;
            reportError(errorPath+".startDate", KeyConstants.ERROR_REQUIRED_START_DATE);
        }        
        if (awardIndirectCostRate.getEndDate() !=null && awardIndirectCostRate.getStartDate() != null && awardIndirectCostRate.getEndDate().before(awardIndirectCostRate.getStartDate())) {
            rulePassed = false;
            reportError(errorPath+".endDate", KeyConstants.ERROR_END_DATE_BEFORE_START_DATE_INDIRECT_COST_RATE,dateParams);
        }

        return rulePassed;
    }

}
