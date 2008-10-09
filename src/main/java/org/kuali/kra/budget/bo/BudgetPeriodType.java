package org.kuali.kra.budget.bo;

import javax.persistence.Version;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.Table;
import javax.persistence.Entity;

import java.util.LinkedHashMap;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

@Entity
@Table(name="BUDGET_PERIOD_TYPE")
public class BudgetPeriodType extends KraPersistableBusinessObjectBase {
	@Id
	@Column(name="BUDGET_PERIOD_TYPE_CODE")
	private String budgetPeriodTypeCode;
	@Column(name="DESCRIPTION")
	private String description;

	public String getBudgetPeriodTypeCode() {
		return budgetPeriodTypeCode;
	}

	public void setBudgetPeriodTypeCode(String budgetPeriodTypeCode) {
		this.budgetPeriodTypeCode = budgetPeriodTypeCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	@SuppressWarnings("unchecked")
	@Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("budgetPeriodTypeCode", getBudgetPeriodTypeCode());
		hashMap.put("description", getDescription());
		return hashMap;
	}
}

