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
package org.kuali.coeus.propdev.impl.budget.subaward;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.apache.commons.lang3.ObjectUtils;
import org.kuali.coeus.common.budget.framework.copy.DeepCopyIgnore;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.propdev.api.budget.subaward.BudgetSubAwardPeriodDetailContract;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.framework.persistence.ScaleTwoDecimalConverter;
import org.kuali.rice.krad.data.jpa.PortableSequenceGenerator;

@Entity
@Table(name = "BUDGET_SUB_AWARD_PERIOD_DETAIL")
public class BudgetSubAwardPeriodDetail extends KcPersistableBusinessObjectBase implements BudgetSubAwardPeriodDetailContract {

    private static final long serialVersionUID = 2327612798304765405L;

    @DeepCopyIgnore
    @PortableSequenceGenerator(name = "SEQ_BUDGET_SUBAWARD_PER_DET")
    @GeneratedValue(generator = "SEQ_BUDGET_SUBAWARD_PER_DET")
    @Id
    @Column(name = "SUBAWARD_PERIOD_DETAIL_ID")
    private Long id;

    @Column(name = "SUBAWARD_NUMBER")
    private Integer subAwardNumber;

    @Column(name = "BUDGET_ID")
    private Long budgetId;

    @Column(name = "BUDGET_PERIOD")
    private Integer budgetPeriod;

    @Column(name = "DIRECT_COST")
    @Convert(converter = ScaleTwoDecimalConverter.class)
    private ScaleTwoDecimal directCost = ScaleTwoDecimal.ZERO;

    @Column(name = "INDIRECT_COST")
    @Convert(converter = ScaleTwoDecimalConverter.class)
    private ScaleTwoDecimal indirectCost = ScaleTwoDecimal.ZERO;

    @Column(name = "COST_SHARING_AMOUNT")
    @Convert(converter = ScaleTwoDecimalConverter.class)
    private ScaleTwoDecimal costShare = ScaleTwoDecimal.ZERO;

    @Column(name = "TOTAL_COST")
    @Convert(converter = ScaleTwoDecimalConverter.class)
    private ScaleTwoDecimal totalCost = ScaleTwoDecimal.ZERO;

    @Transient
    private transient boolean amountsModified = false;

    public BudgetSubAwardPeriodDetail() {
    }

    public BudgetSubAwardPeriodDetail(BudgetSubAwards subAward, BudgetPeriod period) {
        this.subAwardNumber = subAward.getSubAwardNumber();
        this.budgetId = period.getBudgetId();
        this.budgetPeriod = period.getBudgetPeriod();
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Integer getBudgetPeriod() {
        return budgetPeriod;
    }

    public void setBudgetPeriod(Integer budgetPeriod) {
        this.budgetPeriod = budgetPeriod;
    }

    @Override
    public ScaleTwoDecimal getDirectCost() {
        return directCost;
    }

    public void setDirectCost(ScaleTwoDecimal directCost) {
        if (!ObjectUtils.equals(this.directCost, directCost)) {
            amountsModified = true;
        }
        this.directCost = directCost;
    }

    @Override
    public ScaleTwoDecimal getIndirectCost() {
        return indirectCost;
    }

    public void setIndirectCost(ScaleTwoDecimal indirectCost) {
        if (!ObjectUtils.equals(this.indirectCost, indirectCost)) {
            amountsModified = true;
        }
        this.indirectCost = indirectCost;
    }

    @Override
    public ScaleTwoDecimal getCostShare() {
        return costShare;
    }

    public void setCostShare(ScaleTwoDecimal costShare) {
        if (!ObjectUtils.equals(this.costShare, costShare)) {
            amountsModified = true;
        }
        this.costShare = costShare;
    }

    public Long getBudgetId() {
        return budgetId;
    }

    public void setBudgetId(Long budgetId) {
        this.budgetId = budgetId;
    }

    @Override
    public ScaleTwoDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(ScaleTwoDecimal totalCost) {
        this.totalCost = totalCost;
    }

    @Override
    public Integer getSubAwardNumber() {
        return subAwardNumber;
    }

    public void setSubAwardNumber(Integer subAwardNumber) {
        this.subAwardNumber = subAwardNumber;
    }

    public void computeTotal() {
        ScaleTwoDecimal total = getDirectCost() == null ? ScaleTwoDecimal.ZERO : getDirectCost();
        total = total.add(getIndirectCost() == null ? ScaleTwoDecimal.ZERO : getIndirectCost());
        total = total.add(getCostShare() == null ? ScaleTwoDecimal.ZERO : getCostShare());
        setTotalCost(total);
    }

    public boolean isAmountsModified() {
        return amountsModified;
    }

    public void setAmountsModified(boolean amountsModified) {
        this.amountsModified = amountsModified;
    }
}
