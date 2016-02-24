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

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.award.AwardAssociate;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;

/**
 * 
 * Award Budget Limit is intended to linked to either an award or a budget, not both, and defines limits
 * on the budgets. If linked to the award, will persist across versions of the award and budget and
 * copied into each new budget or via user interaction. Limit type is intended to be
 * a constant value in the local enum LIMIT_TYPES, but designed to be expanded to any object code
 * if future features as discussed are ever implemented.
 */
public class AwardBudgetLimit extends AwardAssociate {

    private static final long serialVersionUID = 1215583539908050082L;

    private Long budgetLimitId;

    private Long awardId;

    private Long budgetId;

    private String limitTypeCode;

    private ScaleTwoDecimal limit;

    public AwardBudgetLimit() {
    }

    public AwardBudgetLimit(LIMIT_TYPE type) {
        setLimitType(type);
    }

    /**
     * 
     * Constructs a AwardBudgetLimit.java by copying relevant info from the AwardBudgetLimit (limitTypeCode and limit)
     * @param budget
     * @param budgetLimit
     */
    public AwardBudgetLimit(AwardBudgetLimit budgetLimit) {
        limitTypeCode = budgetLimit.getLimitTypeCode();
        limit = budgetLimit.getLimit();
    }

    public Long getBudgetLimitId() {
        return budgetLimitId;
    }

    public void setBudgetLimitId(Long budgetLimitId) {
        this.budgetLimitId = budgetLimitId;
    }

    public Long getAwardId() {
        return awardId;
    }

    public void setAwardId(Long awardId) {
        this.awardId = awardId;
    }

    public Long getBudgetId() {
        return budgetId;
    }

    public void setBudgetId(Long budgetId) {
        this.budgetId = budgetId;
    }

    public String getLimitTypeCode() {
        return limitTypeCode;
    }

    public void setLimitTypeCode(String limitTypeCode) {
        this.limitTypeCode = limitTypeCode;
    }

    public LIMIT_TYPE getLimitType() {
        if (limitTypeCode == null) {
            return null;
        }
        for (LIMIT_TYPE type : LIMIT_TYPE.values()) {
            if (StringUtils.equals(limitTypeCode, type.getType())) {
                return type;
            }
        }
        return null;
    }

    public void setLimitType(LIMIT_TYPE type) {
        limitTypeCode = type.getType();
    }

    public ScaleTwoDecimal getLimit() {
        return limit;
    }

    public void setLimit(ScaleTwoDecimal limit) {
        this.limit = limit;
    }

    public static enum LIMIT_TYPE {

        TOTAL_COST("totalCost", "Total", "totalCost"), DIRECT_COST("directCost", "Total Direct", "totalDirectCost"), INDIRECT_COST("indirectCost", "Total F&A", "totalIndirectCost");

        private String type;

        private String desc;

        private String budgetProperty;

        private LIMIT_TYPE(String type, String desc, String budgetProperty) {
            this.type = type;
            this.desc = desc;
            this.budgetProperty = budgetProperty;
        }

        public String getType() {
            return type;
        }

        public String getDesc() {
            return desc;
        }

        public String getBudgetProperty() {
            return budgetProperty;
        }
    }

    public void resetPersistenceState() {
        this.setBudgetLimitId(null);
    }

    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((budgetLimitId == null) ? 0 : budgetLimitId.hashCode());
        result = PRIME * result + ((awardId == null) ? 0 : awardId.hashCode());
        result = PRIME * result + ((budgetId == null) ? 0 : budgetId.hashCode());
        result = PRIME * result + ((limitTypeCode == null) ? 0 : limitTypeCode.hashCode());
        result = PRIME * result + ((limit == null) ? 0 : limit.hashCode());
        return result;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !(o instanceof AwardBudgetLimit)) {
            return false;
        }
        AwardBudgetLimit l = (AwardBudgetLimit) o;
        if (!ObjectUtils.equals(this.getBudgetLimitId(), l.getBudgetLimitId())) {
            return false;
        }
        return true;
    }
}
