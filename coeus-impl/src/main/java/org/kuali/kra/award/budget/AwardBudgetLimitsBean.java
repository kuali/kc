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
package org.kuali.kra.award.budget;

import org.kuali.kra.award.AwardForm;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;

import java.io.Serializable;

public class AwardBudgetLimitsBean implements Serializable {
    
    private AwardForm awardForm;
    
    public AwardBudgetLimitsBean(AwardForm awardForm) {
        this.awardForm = awardForm;
    }

    public ScaleTwoDecimal getTotalCostBudgetLimit() {
        return getSpecificBudgetLimit(AwardBudgetLimit.LIMIT_TYPE.TOTAL_COST).getLimit();
    }
    public void setTotalCostBudgetLimit(ScaleTwoDecimal newLimit) {
        setSpecificBudgetLimit(newLimit, AwardBudgetLimit.LIMIT_TYPE.TOTAL_COST);
    }
    
    public ScaleTwoDecimal getDirectCostBudgetLimit() {
        return getSpecificBudgetLimit(AwardBudgetLimit.LIMIT_TYPE.DIRECT_COST).getLimit();
    }
    public void setDirectCostBudgetLimit(ScaleTwoDecimal newLimit) {
        setSpecificBudgetLimit(newLimit, AwardBudgetLimit.LIMIT_TYPE.DIRECT_COST);
    }
    
    public ScaleTwoDecimal getIndirectCostBudgetLimit() {
        return getSpecificBudgetLimit(AwardBudgetLimit.LIMIT_TYPE.INDIRECT_COST).getLimit();
    }
    public void setIndirectCostBudgetLimit(ScaleTwoDecimal newLimit) {
        setSpecificBudgetLimit(newLimit, AwardBudgetLimit.LIMIT_TYPE.INDIRECT_COST);
    }
    
    protected AwardBudgetLimit getSpecificBudgetLimit(AwardBudgetLimit.LIMIT_TYPE type) {
        for (AwardBudgetLimit limit : awardForm.getAwardDocument().getAward().getAwardBudgetLimits()) {
            if (limit.getLimitType() == type) {
                return limit;
            }
        }
        return new AwardBudgetLimit(type);
    }
    protected void setSpecificBudgetLimit(ScaleTwoDecimal newLimit, AwardBudgetLimit.LIMIT_TYPE type) {
        for (AwardBudgetLimit limit : awardForm.getAwardDocument().getAward().getAwardBudgetLimits()) {
            if (limit.getLimitType() == type) {
                limit.setLimit(newLimit);
                return;
            }
        }
        AwardBudgetLimit limit = new AwardBudgetLimit(type);
        limit.setLimit(newLimit);
        awardForm.getAwardDocument().getAward().getAwardBudgetLimits().add(limit);       
    }
}
