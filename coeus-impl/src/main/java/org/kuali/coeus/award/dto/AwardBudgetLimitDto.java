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
package org.kuali.coeus.award.dto;

import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;

public class AwardBudgetLimitDto {

    private Long budgetLimitId;

    private Long awardId;

    private Long budgetId;

    private String limitTypeCode;

    private ScaleTwoDecimal limit;


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

    public ScaleTwoDecimal getLimit() {
        return limit;
    }

    public void setLimit(ScaleTwoDecimal limit) {
        this.limit = limit;
    }
}
