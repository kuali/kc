/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.budget.printing.xmlstream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import noNamespace.BudgetPeriodData;
import noNamespace.SalaryType;
import noNamespace.BudgetSalaryDocument.BudgetSalary;

import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.calculator.RateClassType;
import org.kuali.kra.budget.core.BudgetCategory;
import org.kuali.kra.budget.core.BudgetParent;
import org.kuali.kra.budget.document.BudgetParentDocument;
import org.kuali.kra.budget.nonpersonnel.BudgetLineItem;
import org.kuali.kra.budget.nonpersonnel.BudgetLineItemCalculatedAmount;
import org.kuali.kra.budget.parameters.BudgetPeriod;
import org.kuali.kra.budget.printing.util.BudgetDataPeriodVO;
import org.kuali.kra.budget.printing.util.SalaryTypeVO;
import org.kuali.kra.budget.rates.RateClass;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;

/**
 * This class will contain all common methods that can be used across budget
 * salary ,budget total ,budget summary total , and industrial cumulative budget
 * XML generator streams . All those report XML stream implementations need to
 * extend and use the functions defined in this class.
 * 
 * @author
 */
public abstract class BudgetBaseSalaryStream extends BudgetBaseStream {
	protected static final String SEPARATER_STRING = " - ";
	protected static final String OVERHEAD_RATE_PREFIX = "OH - ";
	protected static final String EMPTY_STRING = "";
	protected static final String PERIOD_COST_TOTAL = "Total";
	protected static final String CALCULATED_AMOUNT_COST_ELEMENT_DESC = "Calculated Amount";
	private static final String RATE_CLASS_CODE_PARAMETER = "rateClassCode";

	/**
	 * <p>
	 * This method will set the values to BudgetSalary Attributes and finally
	 * return the Budget Salary xml object.
	 * </p>
	 * 
	 * @return Budget Salary xml object
	 */
	protected BudgetSalary getBudgetSalaryTypeXmlObject() {
		BudgetSalary budgetSalary = BudgetSalary.Factory.newInstance();
		BudgetParent bdP = budget.getBudgetParent();		
		String parentNumber = bdP.getParentNumber();
		
		if (parentNumber != null) {
			budgetSalary.setProposalNumber(parentNumber);
			budgetSalary.setParentTypeName(bdP.getParentTypeName());		
		}
		setBudgetSalaryTypeBasicInformation(budgetSalary);
		
		String principleInvestigatorName =  bdP.getParentPIName(); 
		if (principleInvestigatorName != null) {
			budgetSalary.setPIName(principleInvestigatorName);
		}
		if (bdP != null && bdP.getParentTitle() != null) {
			budgetSalary.setTitle(bdP.getParentTitle());
		}
	   return budgetSalary;
	}

	/*
	 * This method will set the basic information of budget salary type like
	 * version number,start date , end date and budget periods size
	 */
	private void setBudgetSalaryTypeBasicInformation(BudgetSalary budgetSalary) {
		if (budget.getBudgetVersionNumber() != null) {
			budgetSalary.setBudgetVersion(budget.getBudgetVersionNumber());
		}
		budgetSalary.setCurrentDate(dateTimeService.getCurrentCalendar());
		if (budget.getStartDate() != null) {
			budgetSalary.setStartDate(dateTimeService.getCalendar(budget.getStartDate()));
		}
		if (budget.getEndDate() != null) {
			budgetSalary.setEndDate(dateTimeService.getCalendar(budget.getEndDate()));
		}
		if (budget.getBudgetPeriods() != null) {
			budgetSalary.setTotalPeriod(budget.getBudgetPeriods().size());
		}
		if (budget.getComments() != null) {
		    if(budget.getPrintBudgetCommentFlag()!=null && budget.getPrintBudgetCommentFlag().equals("true"))
		    budgetSalary.setComments(budget.getComments());
		    
		}
		budget.setPrintBudgetCommentFlag(null);
	}

	/**
	 * <p>
	 * This method will set the values to Salary attributes and finally returns
	 * the salary Type Object
	 * </p>
	 * 
	 * @param costElementDesc
	 *            is a cost element description
	 * @param costElementCode
	 *            is a cost element code
	 * @param name
	 *            is a cost element description
	 * @param budgetPeriodArray
	 *            is array of budget periods xml objects
	 * @param total
	 *            is a sum of period costs for budget period
	 * @return Salary Type xml object
	 */
	protected SalaryType getSalaryTypeXmlObject(String costElementDesc,
			String costElementCode, String name,
			BudgetPeriodData[] budgetPeriodArray, BudgetDecimal total) {
		SalaryType salaryType = SalaryType.Factory.newInstance();
		if (costElementDesc != null) {
			salaryType.setCostElementDesc(costElementDesc);
		}
		if (costElementCode != null) {
			salaryType.setCostElementCode(costElementCode);
		}
		if (name != null) {
			salaryType.setName(name);
		}
		if (budgetPeriodArray != null) {
			salaryType.setPeriodArray(budgetPeriodArray);
		}
		if (total != null) {
			salaryType.setTotal(total.bigDecimalValue());
		}
		return salaryType;
	}

	/**
	 * <p>
	 * This method will set the values to salary type attributes and finally
	 * return the array of Salary type
	 * </p>
	 * 
	 * @return Salary type array
	 */
	protected SalaryType[] getBudgetTotalAndSummarySalaryTypes(boolean includeNonPersonnel) {
		List<SalaryTypeVO> salaryTypeVoList = new ArrayList<SalaryTypeVO>();
		List<List<BudgetLineItemCalculatedAmount>> budgetLineItemCalcAmountsList = new ArrayList<List<BudgetLineItemCalculatedAmount>>();
		Map<String, String> lineItems = getListOfCostElementDescription(budgetLineItemCalcAmountsList);
		for (String costElemetDesc : lineItems.keySet()) {
			salaryTypeVoList.add(getSalaryTypeVOForCostElement(costElemetDesc,
					lineItems.get(costElemetDesc)));
		}
		setSalaryTypesForLineItemCalcuAmount(salaryTypeVoList,includeNonPersonnel);
		List<SalaryType> salaryTypeList = getListOfSalaryTypeXmlObjects(salaryTypeVoList);
		return salaryTypeList.toArray(new SalaryType[0]);
	}

	/*
	 * This method will get the unique list of cost element description.Iterate
	 * over budget periods and budget line items check's with map whether that
	 * cost element is available in map , if not found add cost element
	 * description to map.
	 */
	private Map<String, String> getListOfCostElementDescription(
			List<List<BudgetLineItemCalculatedAmount>> budgetLineItemCalcAmountsList) {
		Map<String, String> lineItems = new HashMap<String, String>();
		for (BudgetPeriod budgetPeriod : budget.getBudgetPeriods()) {
			List<BudgetLineItemCalculatedAmount> budgetLineItemCalcAmounts = new ArrayList<BudgetLineItemCalculatedAmount>();
			for (BudgetLineItem budgetLineItem : budgetPeriod
					.getBudgetLineItems()) {
				budgetLineItemCalcAmounts = budgetLineItem
						.getBudgetLineItemCalculatedAmounts();
				budgetLineItemCalcAmountsList.add(budgetLineItemCalcAmounts);
				if (budgetLineItem.getCostElementBO() != null) {
					String costElementDesc = budgetLineItem.getCostElementBO()
							.getDescription();
					if (costElementDesc != null
							&& !lineItems.containsKey(costElementDesc)
							&& budgetLineItem.getCostElementBO()
									.getCostElement() != null) {
						lineItems.put(costElementDesc, budgetLineItem
								.getCostElementBO().getCostElement());
					}
				}
			}
		}
		return lineItems;
	}

	/**
	 * This method gets SalaryTypeVO for costElement. For given cost element
	 * description get's list of budget period data's and finally set to
	 * SalaryTypeVO;
	 * 
	 * @param costElemetDesc
	 * @param costElementCode
	 * @return SalaryTypeVO
	 */
	protected SalaryTypeVO getSalaryTypeVOForCostElement(String costElemetDesc,
			String costElementCode) {
		SalaryTypeVO salaryTypeVO = new SalaryTypeVO();
		salaryTypeVO.setCostElement(costElemetDesc);
		salaryTypeVO.setCostElementCode(costElementCode);
		salaryTypeVO.setName(costElemetDesc);
		salaryTypeVO
				.setBudgetPeriodVOs(getBudgetDataPeriodVOsForCostElement(costElemetDesc));
		return salaryTypeVO;
	}

	/*
	 * This method will get the budget data periods . For a given cost element
	 * description from budget get list of budget periods , iterate over budget
	 * periods and get list of budget line items ,iterate over budget line items
	 * compare with the cost element description which ever matches add line
	 * item cost to period cost.finally set period cost to BudgetDataPeriodVO
	 * 
	 */
	private List<BudgetDataPeriodVO> getBudgetDataPeriodVOsForCostElement(
			String costElementDesc) {
		List<BudgetDataPeriodVO> budgetPeriodDataList = new ArrayList<BudgetDataPeriodVO>();
		int budgetPeriodDataId = 0;
		for (BudgetPeriod budgetPeriod : budget.getBudgetPeriods()) {
			BudgetDataPeriodVO budgetDataPeriodVO = new BudgetDataPeriodVO();
			budgetDataPeriodVO.setBudgetPeriodId(++budgetPeriodDataId);
			BudgetDecimal periodCost = BudgetDecimal.ZERO;
			for (BudgetLineItem lineItem : budgetPeriod.getBudgetLineItems()) {
				if (lineItem.getCostElementBO() != null
						&& lineItem.getCostElementBO().getDescription() != null
						&& lineItem.getCostElementBO().getDescription().equals(
								costElementDesc)) {
					periodCost = periodCost.add(lineItem.getLineItemCost());
				}
			}
			budgetDataPeriodVO.setPeriodCost(periodCost);
			budgetPeriodDataList.add(budgetDataPeriodVO);
		}
		return budgetPeriodDataList;
	}

	/**
	 * <p>
	 * This method will set the values to salaryType VO for budget line
	 * calculated amount
	 * </p>
	 * 
	 * @param salaryTypeVoList
	 *            is a list contains set of salary types based on budget line
	 *            calculated amount
	 * 
	 */
	protected void setSalaryTypesForLineItemCalcuAmount(
			List<SalaryTypeVO> salaryTypeVoList, boolean includeNonPersonnel) {
		List<String> calculatedAmountDescList = new ArrayList<String>();
		SalaryTypeVO salaryTypeVO = new SalaryTypeVO();
		salaryTypeVO.setCostElement(CALCULATED_AMOUNT_COST_ELEMENT_DESC);
		salaryTypeVoList.add(salaryTypeVO);
		for (BudgetPeriod budgetPeriod : budget.getBudgetPeriods()) {
			for (BudgetLineItem budgetLineItem : budgetPeriod
					.getBudgetLineItems()) {
				List<BudgetLineItemCalculatedAmount> budgetLineItemCalculatedAmounts = budgetLineItem
						.getBudgetLineItemCalculatedAmounts();
				for (BudgetLineItemCalculatedAmount budgetLineItemCalcAmount : budgetLineItemCalculatedAmounts) {
					String rateClassCode = budgetLineItemCalcAmount.getRateClassCode();
					String rateTypeCode = budgetLineItemCalcAmount.getRateTypeCode();
					RateClass rateClass = getRateClassBo(rateClassCode);
					String costElementDesc = getCostElementDescriptionForLineItem(budgetLineItemCalcAmount, rateClass);
					if (costElementDesc != null&& !calculatedAmountDescList.contains(costElementDesc)) {
						calculatedAmountDescList.add(costElementDesc);
						SalaryTypeVO salaryTypeVOForCalculatedAmount = new SalaryTypeVO();
						salaryTypeVOForCalculatedAmount.setName(costElementDesc);
						List<BudgetDataPeriodVO> budgetPeriodDataList = getBudgetDataPeriodsForCalculatedAmounts(
								rateClassCode, rateTypeCode,includeNonPersonnel);
						salaryTypeVOForCalculatedAmount.setBudgetPeriodVOs(budgetPeriodDataList);
						salaryTypeVoList.add(salaryTypeVOForCalculatedAmount);
					}
				}
			}
		}
	}

	/*
	 * This method will fetch the budget category from the BudgetLineItem
	 */
	private String getBudgetCategoryTypeCode(BudgetLineItem budgetLineItem) {
		String budgetCategoryType = null;
		BudgetCategory budgetCategory = budgetLineItem.getBudgetCategory();
		if (budgetCategory != null) {
			budgetCategoryType = budgetCategory.getBudgetCategoryTypeCode();
		}
		return budgetCategoryType;
	}

	/*
	 * This method will determine whether given budgetcategory type is personnel
	 * or not
	 */
	protected boolean isPersonnel(String budgetCategoryType) {
		boolean personnelFound = false;
		if (budgetCategoryType.equalsIgnoreCase(BUDGET_CATEGORY_PERSONNEL)) {
			personnelFound = true;
		}
		return personnelFound;
	}

	/**
	 * <p>
	 * This method will return the budget data periods for calculated amounts.It
	 * checks rate type code ,rate class code against list of Budget LineItem
	 * Calculated Amounts for each budget line item of each budget period of
	 * budget ,if it is equals then add calculated cost of budget LineItem
	 * Calculated Amount to period cost.
	 * </p>
	 * 
	 * @param rateClassCode
	 * @param rateTypeCode
	 * @return list of BudgetDataPeriod VO's
	 */
	protected List<BudgetDataPeriodVO> getBudgetDataPeriodsForCalculatedAmounts(
			String rateClassCode, String rateTypeCode, boolean includeNonPersonnel) {
		List<BudgetDataPeriodVO> budgetPeriodDataList = new ArrayList<BudgetDataPeriodVO>();
		int budgetPeriodDataId = 0;
		for (BudgetPeriod budgetPeriod : budget.getBudgetPeriods()) {
			BudgetDataPeriodVO budgetPeriodVO = new BudgetDataPeriodVO();
			budgetPeriodVO.setBudgetPeriodId(++budgetPeriodDataId);
			BudgetDecimal periodCost = BudgetDecimal.ZERO;
			for (BudgetLineItem budgetLineItem : budgetPeriod.getBudgetLineItems()) {
				String budgetCategoryType = getBudgetCategoryTypeCode(budgetLineItem);
				if (includeNonPersonnel || isPersonnel(budgetCategoryType)) {
					for (BudgetLineItemCalculatedAmount budgetLineItemCalcAmount : budgetLineItem.getBudgetLineItemCalculatedAmounts()) {
						if (budgetLineItemCalcAmount.getRateClassCode() != null
								&& budgetLineItemCalcAmount.getRateClassCode().equals(rateClassCode)
								&& budgetLineItemCalcAmount.getRateTypeCode() != null
								&& budgetLineItemCalcAmount.getRateTypeCode().equals(rateTypeCode)) {
							periodCost = periodCost.add(budgetLineItemCalcAmount.getCalculatedCost());
						}
					}
				}
			}
			budgetPeriodVO.setPeriodCost(periodCost);
			budgetPeriodDataList.add(budgetPeriodVO);
		}
		return budgetPeriodDataList;
	}

	/*
	 * This method will get the cost element description for line item.It checks
	 * rate class type if it is OVERHEAD then concatenate OH with the rate Class
	 * description otherwise concatenates rate class description with rate type
	 * description
	 */
	private String getCostElementDescriptionForLineItem(
			BudgetLineItemCalculatedAmount budgetLineItemCalcAmount,
			RateClass rateClass) {
		String costElementDesc = null;
		if (budgetLineItemCalcAmount.getRateTypeDescription() != null) {
			if (rateClass != null&& rateClass.getRateClassType() != null
			                && rateClass.getRateClassType().equals(
							RateClassType.OVERHEAD.getRateClassType())) {
				costElementDesc = new StringBuilder(OVERHEAD_RATE_PREFIX).append(
								budgetLineItemCalcAmount.getRateTypeDescription()).toString();
			} else if (rateClass != null && rateClass.getDescription() != null) {
				costElementDesc = new StringBuilder(rateClass.getDescription())
						.append(SEPARATER_STRING).append(budgetLineItemCalcAmount.getRateTypeDescription()).toString();
			}
		}
		return costElementDesc;
	}

	/*
	 * This method will return the rate class of given rate class code
	 * 
	 */
	private RateClass getRateClassBo(String rateClassCode) {
		RateClass rateClass = null;
		Map<String, String> personMap = new HashMap<String, String>();
		personMap.put(RATE_CLASS_CODE_PARAMETER, rateClassCode);
		List<RateClass> rateClassList = (List<RateClass>) businessObjectService
				.findMatching(RateClass.class, personMap);
		if (rateClassList != null && !rateClassList.isEmpty()) {
			rateClass = rateClassList.get(0);
		}
		return rateClass;
	}

	/**
	 * <p>
	 * This method will iterate over salaryTypeVo List and set to xml bean
	 * SalaryType and sort the List based on costElement
	 * </p>
	 * 
	 * @param salaryTypeVoList
	 * @return list of SalaryType xml bean
	 */
	protected List<SalaryType> getListOfSalaryTypeXmlObjects(
			List<SalaryTypeVO> salaryTypeVoList) {
		List<SalaryType> salaryTypeList = new ArrayList<SalaryType>();
		Map<Integer, BudgetDecimal> budgetPeriodWiseTotalMap = new HashMap<Integer, BudgetDecimal>();
		if (!salaryTypeVoList.isEmpty()) {
			for (SalaryTypeVO salaryTypeVO : salaryTypeVoList) {
				BudgetPeriodData[] budgetPeriodArray = null;
				List<BudgetDataPeriodVO> budgetDataPeriodVOs = salaryTypeVO.getBudgetPeriodVOs();
				if (budgetDataPeriodVOs != null) {
					BudgetDecimal total = BudgetDecimal.ZERO;
					budgetPeriodArray = getBudgetDataPeriodXmlObjects(budgetDataPeriodVOs, budgetPeriodWiseTotalMap);
					total = getTotalForCostElementOfAllPeriodsCost(budgetDataPeriodVOs);
					salaryTypeVO.setTotal(total);
				}

				salaryTypeList.add(
				        getSalaryTypeXmlObject(salaryTypeVO.getCostElement(),salaryTypeVO.getCostElementCode(),
				                                salaryTypeVO.getName(), budgetPeriodArray, salaryTypeVO.getTotal()));
			}
			setTotalForPeriodWise(salaryTypeList, budgetPeriodWiseTotalMap);
		}
		Collections.sort(salaryTypeList, new Comparator<SalaryType>() {
			public int compare(SalaryType salaryType1, SalaryType salaryType2) {
				int i = 0;
				if (salaryType1.getCostElementCode() != null
						&& salaryType2.getCostElementCode() != null) {
					i = salaryType1.getCostElementCode().compareTo(salaryType2.getCostElementCode());
				}
				return i;
			}
		});
		return salaryTypeList;
	}

	/*
	 * This method calculates the total period cost of entire budget period
	 * irrespective of cost element.
	 */
	private void setTotalForPeriodWise(List<SalaryType> salaryTypeList,
			Map<Integer, BudgetDecimal> budgetPeriodWiseTotalMap) {
		BudgetPeriodData[] budgetPeriodArray = null;
		BudgetDecimal budgetSalaryTotal = BudgetDecimal.ZERO;
		if (!budgetPeriodWiseTotalMap.isEmpty()) {
			List<BudgetPeriodData> budgetPeriodList = new ArrayList<BudgetPeriodData>();
			for (Integer periodId : budgetPeriodWiseTotalMap.keySet()) {
				BudgetPeriodData budgetPeriodData = BudgetPeriodData.Factory.newInstance();
				budgetPeriodData.setBudgetPeriodID(periodId);
				BudgetDecimal periodCost = budgetPeriodWiseTotalMap.get(periodId);
				budgetPeriodData.setPeriodCost(periodCost.bigDecimalValue());
				budgetSalaryTotal = budgetSalaryTotal.add(periodCost);
				budgetPeriodList.add(budgetPeriodData);
			}
			budgetPeriodArray = budgetPeriodList.toArray(new BudgetPeriodData[0]);
		}
		salaryTypeList.add(getSalaryTypeXmlObject(null, null,
				PERIOD_COST_TOTAL, budgetPeriodArray, budgetSalaryTotal));
	}

	/*
	 * This method will calculate the total for cost element of all periods
	 * period cost.
	 */
	private BudgetDecimal getTotalForCostElementOfAllPeriodsCost(
			List<BudgetDataPeriodVO> budgetDataPeriodVOs) {
		BudgetDecimal total = BudgetDecimal.ZERO;
		for (BudgetDataPeriodVO budgetDataPeriodVO : budgetDataPeriodVOs) {
			total = total.add(budgetDataPeriodVO.getPeriodCost());
		}
		return total;
	}

	/*
	 * This method will set the values to budget data period xml object and
	 * finally return the set of budget data period xml objects.
	 */
	private BudgetPeriodData[] getBudgetDataPeriodXmlObjects(
			List<BudgetDataPeriodVO> budgetDataPeriods,
			Map<Integer, BudgetDecimal> budgetPeriodWiseTotalMap) {
		List<BudgetPeriodData> budgetPeriodList = null;
		budgetPeriodList = new ArrayList<BudgetPeriodData>();
		for (BudgetDataPeriodVO budgetPeriodVO : budgetDataPeriods) {
			BudgetPeriodData budgetPeriodData = BudgetPeriodData.Factory.newInstance();
			int budgetPeriodId = budgetPeriodVO.getBudgetPeriodId();
			budgetPeriodData.setBudgetPeriodID(budgetPeriodId);
			BudgetDecimal periodCost = budgetPeriodVO.getPeriodCost();
			budgetPeriodData.setPeriodCost(periodCost.bigDecimalValue());
			if (budgetPeriodWiseTotalMap.containsKey(budgetPeriodId)) {
				BudgetDecimal periodTotal = budgetPeriodWiseTotalMap.get(budgetPeriodId);
				periodTotal = periodTotal.add(periodCost);
				budgetPeriodWiseTotalMap.put(budgetPeriodId, periodTotal);
			} else {
				budgetPeriodWiseTotalMap.put(budgetPeriodId, periodCost);
			}
			budgetPeriodList.add(budgetPeriodData);
		}
		BudgetPeriodData[] budgetPeriodArray = budgetPeriodList.toArray(new BudgetPeriodData[0]);
		return budgetPeriodArray;
	}
}
