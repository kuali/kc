package org.kuali.coeus.common.budget.impl.core.category;

import java.util.Collections;

import org.kuali.coeus.common.budget.framework.core.category.BudgetCategory;
import org.kuali.coeus.common.budget.framework.core.category.BudgetCategoryType;
import org.kuali.coeus.sys.framework.controller.rest.SimpleCrudRestController;
import org.kuali.coeus.sys.framework.rest.DataDictionaryValidationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller(value="budgetCategoryController")
@RequestMapping(value="/api/v1/budget-categories/")
public class BudgetCategoryController extends SimpleCrudRestController<BudgetCategory, BudgetCategoryDto> {

	@Override
	protected Class<BudgetCategoryDto> getDtoClass() {
		return BudgetCategoryDto.class;
	}

	@Override
	protected Class<BudgetCategory> getDoClass() {
		return BudgetCategory.class;
	}

	@Override
	protected BudgetCategory getNewDataObject() {
		return new BudgetCategory();
	}

	@Override
	protected Object getPrimaryKeyFromDto(BudgetCategoryDto dataObject) {
		return dataObject.getCode();
	}
	
	@Override
	protected void validateBusinessObject(BudgetCategory budgetCategory) {
		super.validateBusinessObject(budgetCategory);
		BudgetCategoryType budgetCategoryType = getBusinessObjectService().findBySinglePrimaryKey(BudgetCategoryType.class, budgetCategory.getBudgetCategoryTypeCode());
		if (budgetCategoryType == null) {
			throw new DataDictionaryValidationException(Collections.singletonMap("budgetCategoryTypeCode", Collections.singletonList("referenced budget category type does not exist")));
		}
	}

}
