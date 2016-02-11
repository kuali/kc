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

import com.codiform.moo.annotation.Ignore;
import org.kuali.coeus.sys.framework.controller.rest.PrimaryKeyDto;

public class BudgetCategoryDto implements PrimaryKeyDto {

	private String code;
	private String budgetCategoryTypeCode;
	private String description;
	@Ignore
	private String $primaryKey;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getBudgetCategoryTypeCode() {
		return budgetCategoryTypeCode;
	}
	public void setBudgetCategoryTypeCode(String budgetCategoryTypeCode) {
		this.budgetCategoryTypeCode = budgetCategoryTypeCode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String get$primaryKey() {
		return $primaryKey;
	}

	@Override
	public void set$primaryKey(String $primaryKey) {
		this.$primaryKey = $primaryKey;
	}
}
