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
package org.kuali.kra.award.budget;

import java.io.Serializable;

import org.kuali.kra.award.AwardForm;
import org.kuali.rice.core.api.util.type.KualiDecimal;

public class AwardBudgetLimitsBean implements Serializable {
    
    private AwardForm awardForm;
    
    public AwardBudgetLimitsBean(AwardForm awardForm) {
        this.awardForm = awardForm;
    }

    public KualiDecimal getTotalCostBudgetLimit() {
        return getSpecificBudgetLimit(AwardBudgetLimit.LIMIT_TYPE.TOTAL_COST).getLimit();
    }
    public void setTotalCostBudgetLimit(KualiDecimal newLimit) {
        setSpecificBudgetLimit(newLimit, AwardBudgetLimit.LIMIT_TYPE.TOTAL_COST);
    }
    
    public KualiDecimal getDirectCostBudgetLimit() {
        return getSpecificBudgetLimit(AwardBudgetLimit.LIMIT_TYPE.DIRECT_COST).getLimit();
    }
    public void setDirectCostBudgetLimit(KualiDecimal newLimit) {
        setSpecificBudgetLimit(newLimit, AwardBudgetLimit.LIMIT_TYPE.DIRECT_COST);
    }
    
    public KualiDecimal getIndirectCostBudgetLimit() {
        return getSpecificBudgetLimit(AwardBudgetLimit.LIMIT_TYPE.INDIRECT_COST).getLimit();
    }
    public void setIndirectCostBudgetLimit(KualiDecimal newLimit) {
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
    protected void setSpecificBudgetLimit(KualiDecimal newLimit, AwardBudgetLimit.LIMIT_TYPE type) {
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
