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
package org.kuali.coeus.propdev.impl.budget.subaward;

import java.util.Objects;

import javax.persistence.*;

import org.apache.commons.lang3.ObjectUtils;
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

    @PortableSequenceGenerator(name = "SEQ_BUDGET_SUBAWARD_PER_DET")
    @GeneratedValue(generator = "SEQ_BUDGET_SUBAWARD_PER_DET")
    @Id
    @Column(name = "SUBAWARD_PERIOD_DETAIL_ID")
    private Long id;
    
    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumns({ @JoinColumn(name = "BUDGET_ID", referencedColumnName = "BUDGET_ID"), @JoinColumn(name = "SUBAWARD_NUMBER", referencedColumnName = "SUB_AWARD_NUMBER") })
    private BudgetSubAwards budgetSubAward;     

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
    
    @Column(name = "SUBAWARD_NUMBER", insertable = false, updatable = false)
    private Integer subAwardNumber;
    

    @Transient
    private transient boolean amountsModified = false;

    public BudgetSubAwardPeriodDetail() {
    	super();
    }

    public BudgetSubAwardPeriodDetail(BudgetSubAwards subAward, BudgetPeriod period) {
    	this.budgetSubAward = subAward;
    	this.subAwardNumber = subAward.getSubAwardNumber();
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
        computeTotal();
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
        computeTotal();
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

    @Override
    public ScaleTwoDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(ScaleTwoDecimal totalCost) {
        this.totalCost = totalCost;
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
    
	public BudgetSubAwards getBudgetSubAward() {
		return budgetSubAward;
	}

	public void setBudgetSubAward(BudgetSubAwards budgetSubAward) {
		this.budgetSubAward = budgetSubAward;
	}

	@Override
	public Integer getSubAwardNumber() {
		return subAwardNumber;
	}
	
	public void setSubAwardNumber(Integer subAwardNumber) {
		this.subAwardNumber = subAwardNumber;
	}

	@Override
	public Long getBudgetId() {
		if (budgetSubAward != null) {
			return budgetSubAward.getBudgetId();
		} else {
			return null;
		}		
	}
	
	public BudgetPeriod getBudgetPeriodBO() {
		for (BudgetPeriod period : getBudgetSubAward().getBudget().getBudgetPeriods()) {
			if (Objects.equals(budgetPeriod, period.getBudgetPeriod())) {
				return period;
			}
		}
		return null;
	}
}
