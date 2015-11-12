package org.kuali.coeus.common.budget.impl.core.category;

import java.util.Collections;
import java.util.Map;

import org.kuali.coeus.common.budget.framework.core.category.BudgetCategory;
import org.kuali.coeus.common.budget.framework.core.category.BudgetCategoryType;
import org.kuali.coeus.sys.framework.controller.rest.SimpleCrudRestController;
import org.kuali.coeus.sys.framework.rest.DataDictionaryValidationException;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.kuali.coeus.sys.framework.util.CollectionUtils.entry;

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
	protected Map.Entry<String, String> getPermission() {
		return entry(Constants.MODULE_NAMESPACE_MAINTENANCE, PermissionConstants.MAINTAIN_INSTITUTE_RATES);
	}

	@Override
	protected Object getPrimaryKeyFromDto(BudgetCategoryDto dataObject) {
		return dataObject.getCode();
	}
}
