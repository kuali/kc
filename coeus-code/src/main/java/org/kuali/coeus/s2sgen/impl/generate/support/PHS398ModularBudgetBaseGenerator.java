/*
 * Copyright 2005-2014 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.coeus.s2sgen.impl.generate.support;

import org.kuali.coeus.common.api.rolodex.RolodexContract;
import org.kuali.coeus.common.api.rolodex.RolodexService;
import org.kuali.coeus.propdev.api.budget.modular.BudgetModularContract;
import org.kuali.coeus.propdev.api.budget.modular.BudgetModularIdcContract;
import org.kuali.coeus.s2sgen.impl.budget.S2SCommonBudgetService;
import org.kuali.coeus.s2sgen.impl.datetime.S2SDateTimeService;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.s2sgen.impl.generate.S2SBaseFormGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * This abstract class has methods that are common to all the versions of
 * PHS398ModularBudget form.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public abstract class PHS398ModularBudgetBaseGenerator extends
		S2SBaseFormGenerator {
	private static final String EMPTY_STRING = " ";
	private static final String COMMA_SEPERATOR = ",";

    protected static final int PERSONNEL_JUSTIFICATION_CODE = 35;
    protected static final int CONSORTIUM_JUSTIFICATION_CODE = 36;
    protected static final int NARRATIVE_JUSTIFICATION_CODE = 37;

    @Autowired
    @Qualifier("s2SDateTimeService")
    protected S2SDateTimeService s2SDateTimeService;

    @Autowired
    @Qualifier("rolodexService")
	protected RolodexService rolodexService;

    @Autowired
    @Qualifier("s2SCommonBudgetService")
    protected S2SCommonBudgetService s2SCommonBudgetService;

	/**
	 * This method is used to get total cost as sum of totalDirectCost and total
	 * sum of fundRequested.
	 * 
	 * @param budgetModular
	 * @return totalCost
	 */
	protected ScaleTwoDecimal getTotalCost(BudgetModularContract budgetModular) {
		ScaleTwoDecimal totalCost = ScaleTwoDecimal.ZERO;
		if (budgetModular.getTotalDirectCost() != null) {
			totalCost = budgetModular.getTotalDirectCost();
		}
		for (BudgetModularIdcContract budgetModularIdc : budgetModular
				.getBudgetModularIdcs()) {
			if (budgetModularIdc.getFundsRequested() != null) {
				totalCost = totalCost.add(budgetModularIdc.getFundsRequested());
			}
		}
		return totalCost;
	}

	/**
	 * This method is used to get rolodex Organization FirstName, LastName and
	 * PhoneNumber as a single string
	 * 
	 * @param rolodex
	 * @return String
	 */
	protected String getCognizantFederalAgency(RolodexContract rolodex) {
		StringBuilder agency = new StringBuilder();
		if(rolodex.getOrganization()!=null){
		agency.append(rolodex.getOrganization());
		}agency.append(COMMA_SEPERATOR);
		if(rolodex.getFirstName()!=null){
		agency.append(rolodex.getFirstName());
		}agency.append(EMPTY_STRING);
		if(rolodex.getLastName()!=null){
		agency.append(rolodex.getLastName());
		}agency.append(EMPTY_STRING);
		if(rolodex.getPhoneNumber()!=null){
		agency.append(rolodex.getPhoneNumber());
		}return agency.toString();
	}

    public S2SDateTimeService getS2SDateTimeService() {
        return s2SDateTimeService;
    }

    public void setS2SDateTimeService(S2SDateTimeService s2SDateTimeService) {
        this.s2SDateTimeService = s2SDateTimeService;
    }

    public RolodexService getRolodexService() {
        return rolodexService;
    }

    public void setRolodexService(RolodexService rolodexService) {
        this.rolodexService = rolodexService;
    }

    public S2SCommonBudgetService getS2SCommonBudgetService() {
        return s2SCommonBudgetService;
    }

    public void setS2SCommonBudgetService(S2SCommonBudgetService s2SCommonBudgetService) {
        this.s2SCommonBudgetService = s2SCommonBudgetService;
    }
}
