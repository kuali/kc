/*
 * Copyright 2005-2010 The Kuali Foundation.
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
package org.kuali.kra.s2s.generator.impl;

import org.kuali.kra.bo.Rolodex;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.budget.modular.BudgetModular;
import org.kuali.kra.proposaldevelopment.budget.modular.BudgetModularIdc;
import org.kuali.kra.s2s.generator.S2SBaseFormGenerator;
import org.kuali.kra.s2s.service.S2SBudgetCalculatorService;
import org.kuali.kra.s2s.service.S2SUtilService;

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
	protected S2SUtilService s2sUtilService;
	protected S2SBudgetCalculatorService s2sBudgetCalculatorService;
	protected static final int PERSONNEL_JUSTIFICATION_CODE = 35;
	protected static final int CONSORTIUM_JUSTIFICATION_CODE = 36;
	protected static final int NARRATIVE_JUSTIFICATION_CODE = 37;

	/**
	 * 
	 * Constructs a PHS398ModularBudgetBaseGenerator.java.
	 */
	public PHS398ModularBudgetBaseGenerator() {
		s2sUtilService = KraServiceLocator.getService(S2SUtilService.class);
		s2sBudgetCalculatorService = KraServiceLocator
				.getService(S2SBudgetCalculatorService.class);
	}

	/**
	 * This method is used to get total cost as sum of totalDirectCost and total
	 * sum of fundRequested.
	 * 
	 * @param budgetModular
	 * @return totalCost
	 */
	protected BudgetDecimal getTotalCost(BudgetModular budgetModular) {
		BudgetDecimal totalCost = BudgetDecimal.ZERO;
		if (budgetModular.getTotalDirectCost() != null) {
			totalCost = budgetModular.getTotalDirectCost();
		}
		for (BudgetModularIdc budgetModularIdc : budgetModular
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
	protected String getCognizantFederalAgency(Rolodex rolodex) {
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
}
