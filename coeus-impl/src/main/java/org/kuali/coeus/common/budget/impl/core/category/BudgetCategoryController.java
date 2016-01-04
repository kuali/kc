/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller(value="budgetCategoryController")
@RequestMapping(value="/api/v1/budget-categories")
public class BudgetCategoryController extends SimpleCrudDtoRestController<BudgetCategory, BudgetCategoryDto> {

	public BudgetCategoryController() {
		setDataObjectClazz(BudgetCategory.class);
		setDtoObjectClazz(BudgetCategoryDto.class);
		setPrimaryKeyColumn("code");

		setRegisterMapping(false);
	}
}
