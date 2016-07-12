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

import com.codiform.moo.annotation.CollectionProperty;
import org.codehaus.jackson.annotate.JsonProperty;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;

import java.sql.Date;
import java.util.*;

public class BudgetPeriodDto {

    private String comments;
    private ScaleTwoDecimal costSharingAmount;
    private Date endDate;
    private Date startDate;
    private ScaleTwoDecimal totalCost;
    private ScaleTwoDecimal totalCostLimit;
    private ScaleTwoDecimal totalDirectCost;
    private ScaleTwoDecimal totalIndirectCost;
    private ScaleTwoDecimal underrecoveryAmount;
    @JsonProperty(value="budgetLineItems")
    @CollectionProperty(source="budgetLineItems", itemClass= BudgetLineItemDto.class)
    private List<BudgetLineItemDto> budgetLineItems;
    private Integer numberOfParticipants;
    private ScaleTwoDecimal directCostLimit;
    private ScaleTwoDecimal expenseTotal;
    private Long budgetId;

    public Long getBudgetId() {
        return budgetId;
    }
    public Long setBudgetId(Long budgetId) {
        return this.budgetId = budgetId;
    }

    public String getComments() {
        return comments;
    }
    public void setComments(String comments) {
        this.comments = comments;
    }

    public ScaleTwoDecimal getCostSharingAmount() {
        return costSharingAmount;
    }
    public void setCostSharingAmount(ScaleTwoDecimal costSharingAmount) {
        this.costSharingAmount = costSharingAmount;
    }

    public Date getEndDate() {
        return endDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public ScaleTwoDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(ScaleTwoDecimal totalCost) {
        this.totalCost = totalCost;
    }
    public ScaleTwoDecimal getTotalCostLimit() {
        return totalCostLimit;
    }

    public void setTotalCostLimit(ScaleTwoDecimal totalCostLimit) {
        this.totalCostLimit = totalCostLimit;
    }

    public ScaleTwoDecimal getTotalDirectCost() {
        return totalDirectCost;
    }
    public void setTotalDirectCost(ScaleTwoDecimal totalDirectCost) {
        this.totalDirectCost = totalDirectCost;
    }

    public ScaleTwoDecimal getTotalIndirectCost() {
        return totalIndirectCost;
    }
    public void setTotalIndirectCost(ScaleTwoDecimal totalIndirectCost) {
        this.totalIndirectCost = totalIndirectCost;
    }

    public ScaleTwoDecimal getUnderrecoveryAmount() {
        return underrecoveryAmount;
    }
    public void setUnderrecoveryAmount(ScaleTwoDecimal underrecoveryAmount) {
        this.underrecoveryAmount = underrecoveryAmount;
    }

    public List<BudgetLineItemDto> getBudgetLineItems() {
        return budgetLineItems;
    }
    public void setBudgetLineItems(List<BudgetLineItemDto> budgetLineItems) {
        this.budgetLineItems = budgetLineItems;
    }

    public ScaleTwoDecimal getExpenseTotal() {
        return expenseTotal;
    }

    public void setExpenseTotal(ScaleTwoDecimal expenseTotal) {
        this.expenseTotal = expenseTotal;
    }

    public ScaleTwoDecimal getDirectCostLimit() {
        return directCostLimit;
    }

    public void setDirectCostLimit(ScaleTwoDecimal directCostLimit) {
        this.directCostLimit = directCostLimit;
    }

    public Integer getNumberOfParticipants() {
        return numberOfParticipants;
    }

    public void setNumberOfParticipants(Integer numberOfParticipants) {
        this.numberOfParticipants = numberOfParticipants;
    }
}