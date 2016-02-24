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
