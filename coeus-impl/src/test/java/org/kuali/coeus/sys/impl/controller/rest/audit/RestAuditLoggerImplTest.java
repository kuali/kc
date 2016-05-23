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
package org.kuali.coeus.sys.impl.controller.rest.audit;

import static org.junit.Assert.*;

import java.sql.Date;
import java.time.Instant;
import java.util.Arrays;
import java.util.Map;

import org.junit.Test;
import org.kuali.coeus.common.budget.framework.core.category.BudgetCategory;
import org.kuali.coeus.common.budget.framework.rate.InstituteRate;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.springframework.beans.BeanWrapperImpl;

public class RestAuditLoggerImplTest {

	@Test
	public void testAddNewItem() {
		RestAuditLoggerImpl logger = new RestAuditLoggerImpl("quickstart", BudgetCategory.class, 
				Arrays.asList("code", "description", "budgetCategoryTypeCode"), null, BeanWrapperImpl::new);
		BudgetCategory category = createDefaultBudgetCategory();
		logger.addNewItem(category);
		assertTrue("Has changes", logger.getRestAuditLog().hasChanges());
		assertEquals("Number of additions", 1, logger.getRestAuditLog().getAdded().size());
		final Map<String, Object> auditItem = logger.getRestAuditLog().getAdded().get(0);
		assertEquals(category.getCode(), auditItem.get("code"));
		assertEquals(category.getDescription(), auditItem.get("description"));
		assertEquals(category.getBudgetCategoryTypeCode(), auditItem.get("budgetCategoryTypeCode"));
	}
	
	@Test
	public void testDeleteItem() {
		RestAuditLoggerImpl logger = new RestAuditLoggerImpl("quickstart", BudgetCategory.class, 
				Arrays.asList("code", "description", "budgetCategoryTypeCode"), null, BeanWrapperImpl::new);
		BudgetCategory category = createDefaultBudgetCategory();
		logger.addDeletedItem(category);
		assertTrue("Has changes", logger.getRestAuditLog().hasChanges());
		assertEquals("Number of additions", 1, logger.getRestAuditLog().getDeleted().size());
		final Map<String, Object> auditItem = logger.getRestAuditLog().getDeleted().get(0);
		assertEquals(category.getCode(), auditItem.get("code"));
		assertEquals(category.getDescription(), auditItem.get("description"));
		assertEquals(category.getBudgetCategoryTypeCode(), auditItem.get("budgetCategoryTypeCode"));
	}
	
	@Test
	public void testModifyItem() {
		RestAuditLoggerImpl logger = new RestAuditLoggerImpl("quickstart", BudgetCategory.class, 
				Arrays.asList("code", "description", "budgetCategoryTypeCode"), null, BeanWrapperImpl::new);
		BudgetCategory categoryCurrent = createDefaultBudgetCategory();
		final String modifiedDescription = "Modified Description";
		BudgetCategory categoryUpdated = createDefaultBudgetCategory();
		categoryUpdated.setDescription(modifiedDescription);
		logger.addModifiedItem(categoryCurrent, categoryUpdated);
		assertTrue("Has changes", logger.getRestAuditLog().hasChanges());
		assertEquals("Number of additions", 1, logger.getRestAuditLog().getModified().size());
		final Map<String, Object> auditItem = logger.getRestAuditLog().getModified().get(0);
		assertEquals(categoryCurrent.getCode(), auditItem.get("code"));
		assertEquals(categoryCurrent.getBudgetCategoryTypeCode(), auditItem.get("budgetCategoryTypeCode"));
		assertTrue("Description is a map", auditItem.get("description") instanceof Map);
		Map<String, Object> modifiedMap = (Map<String, Object>) auditItem.get("description");
		assertEquals(categoryCurrent.getDescription(), modifiedMap.get("old"));
		assertEquals(categoryUpdated.getDescription(), modifiedMap.get("new"));
	}
	
	@Test
	public void testModifyComplexItem() {
		RestAuditLoggerImpl logger = new RestAuditLoggerImpl("quickstart", InstituteRate.class, 
				Arrays.asList("id", "fiscalYear", "instituteRate", "startDate"), null, BeanWrapperImpl::new);
		InstituteRate currentRate = createDefaultInstituteRate();
		InstituteRate updatedRate = createDefaultInstituteRate();
		updatedRate.setInstituteRate(new ScaleTwoDecimal(12.00));
		updatedRate.setStartDate(new Date(Instant.now().minusSeconds(80000).toEpochMilli()));
		logger.addModifiedItem(currentRate, updatedRate);
		assertTrue("Has changes", logger.getRestAuditLog().hasChanges());
		assertEquals("Number of additions", 1, logger.getRestAuditLog().getModified().size());
		final Map<String, Object> auditItem = logger.getRestAuditLog().getModified().get(0);
		assertEquals(currentRate.getId(), auditItem.get("id"));
		assertEquals(currentRate.getFiscalYear(), auditItem.get("fiscalYear"));
		assertTrue("Description is a map", auditItem.get("instituteRate") instanceof Map);
		Map<String, Object> modifiedMap = (Map<String, Object>) auditItem.get("instituteRate");
		assertEquals(currentRate.getInstituteRate(), modifiedMap.get("old"));
		assertEquals(updatedRate.getInstituteRate(), modifiedMap.get("new"));
		modifiedMap = (Map<String, Object>) auditItem.get("startDate");
		assertEquals(currentRate.getStartDate(), modifiedMap.get("old"));
		assertEquals(updatedRate.getStartDate(), modifiedMap.get("new"));

	}
	

	
	protected BudgetCategory createDefaultBudgetCategory() {
		BudgetCategory category = new BudgetCategory();
		category.setCode("123");
		category.setDescription("Testing");
		category.setBudgetCategoryTypeCode("1");
		return category;
	}
	
	protected InstituteRate createDefaultInstituteRate() {
		InstituteRate result = new InstituteRate();
		result.setId(1L);
		result.setFiscalYear("2014");
		result.setInstituteRate(ScaleTwoDecimal.ONE_HUNDRED);
		result.setStartDate(new Date(System.currentTimeMillis()));
		return result;
	}
}
