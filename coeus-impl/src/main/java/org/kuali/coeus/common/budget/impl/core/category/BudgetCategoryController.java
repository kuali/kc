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
package org.kuali.coeus.common.budget.impl.core.category;


import org.kuali.coeus.common.budget.framework.core.category.BudgetCategory;
import org.kuali.coeus.sys.framework.controller.rest.SimpleCrudDtoRestController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller(value="budgetCategoryController")
@RequestMapping(value="/api/v1/budget-categories/")
public class BudgetCategoryController extends SimpleCrudDtoRestController<BudgetCategory, BudgetCategoryDto> {

	private static final String BUDGET_CATEGORY_DO_CLASS = "org.kuali.coeus.common.budget.framework.core.category.BudgetCategory";
	private static final String BUDGET_CATEGORY_DTO_CLASS = "org.kuali.coeus.common.budget.impl.core.category.BudgetCategoryDto";
	private static final String PRIMARY_KEY_COLUMN = "code";
	private static final String REGISTER_MAPPING = "false";

	@Value(BUDGET_CATEGORY_DO_CLASS)
	@Override
	public void setDataObjectClazz(Class<BudgetCategory> dataObjectClazz) {
		super.setDataObjectClazz(dataObjectClazz);
	}

	@Value(BUDGET_CATEGORY_DTO_CLASS)
	@Override
	public void setDtoObjectClazz(Class<BudgetCategoryDto> dtoObjectClazz) {
		super.setDtoObjectClazz(dtoObjectClazz);
	}

	@Value(PRIMARY_KEY_COLUMN)
	@Override
	public void setPrimaryKeyColumn(String primaryKeyColumn) {
		super.setPrimaryKeyColumn(primaryKeyColumn);
	}

	@Value(REGISTER_MAPPING)
	@Override
	public void setRegisterMapping(boolean registerMapping) {
		super.setRegisterMapping(registerMapping);
	}
}
