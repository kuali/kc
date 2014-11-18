package org.kuali.kra.award.budget.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPerson;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPersonnelDetails;
import org.kuali.coeus.common.budget.impl.personnel.BudgetPersonServiceImpl;
import org.kuali.kra.award.budget.service.AwardBudgetPersonService;

public class AwardBudgetPersonServiceImpl extends BudgetPersonServiceImpl implements AwardBudgetPersonService {
	
	@Override
	protected void refreshPersonAppointmentType(BudgetPerson budgetPerson) {
		if(StringUtils.isNotEmpty(budgetPerson.getAppointmentTypeCode())) {
			budgetPerson.refreshReferenceObject(APOINTMENT_TYPE_FIELD_NAME);
		}
	}
	
	@Override
    public BudgetPerson findBudgetPerson(BudgetPersonnelDetails budgetPersonnelDetails) {
		Map queryMap = new HashMap();
		queryMap.put(BUDGET_ID, budgetPersonnelDetails.getBudgetId());
		queryMap.put(PERSON_SEQUENCE_NUMBER, budgetPersonnelDetails.getPersonSequenceNumber());
		BudgetPerson budgetPerson = getBusinessObjectService().findByPrimaryKey(BudgetPerson.class, queryMap);
		if (budgetPerson == null) {
			//if the budgetPerson hasn't been persisted yet, just get it from the details object
			budgetPerson = budgetPersonnelDetails.getBudgetPerson();
		}
		return budgetPerson;
    }
}
