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
package org.kuali.coeus.common.budget.impl.core;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.budget.framework.core.category.BudgetCategory;
import org.kuali.coeus.common.budget.impl.core.category.BudgetCategoryController;
import org.kuali.coeus.common.budget.impl.core.category.BudgetCategoryDto;
import org.kuali.coeus.sys.framework.controller.rest.SimpleCrudDtoRestController;
import org.kuali.coeus.sys.framework.controller.rest.audit.RestAuditLog;
import org.kuali.coeus.sys.framework.controller.rest.audit.RestAuditLogger;
import org.kuali.coeus.sys.framework.rest.ResourceNotFoundException;
import org.kuali.coeus.sys.framework.rest.UnprocessableEntityException;
import org.kuali.coeus.sys.impl.controller.rest.audit.RestAuditLoggerImpl;
import org.springframework.beans.BeanWrapperImpl;

public class BudgetCategoryControllerTest {

	private BudgetCategory budgetCat1;
	private BudgetCategory budgetCat2;
	
	BudgetCategory updatedCategory = null;
	
	private RestAuditLog savedAuditLog = null;
	
	@Before
	public void setup() {
		budgetCat1 = createBudgetCategory("1", "Test", "P");
		budgetCat2 = createBudgetCategory("2", "Test 2", "E");	
	}
	
	private BudgetCategory createBudgetCategory(String code, String description, String budgetCategoryTypeCode) {
		BudgetCategory category = new BudgetCategory();
		category.setCode(code);
		category.setDescription(description);
		category.setBudgetCategoryTypeCode(budgetCategoryTypeCode);
		return category;
	}
	
	@Test
	public void testBudgetCategory_getAll() {
		SimpleCrudDtoRestController<BudgetCategory, BudgetCategoryDto> controller = new BudgetCategoryController() {
			{
				wireControllerProperties(this);
			}

			@Override
			protected Collection<BudgetCategory> getAllFromDataStore() {
				return Stream.of(budgetCat1, budgetCat2).collect(Collectors.toList());
			}

			@Override
			protected void assertUserHasReadAccess() {

			}
		};
		
		Collection<BudgetCategoryDto> categories = controller.getAll(Collections.emptyMap());
		assertEquals(2, categories.size());
		assertEquals(budgetCat1.getDescription(), 
				categories.stream().filter(cat -> budgetCat1.getCode().equals(cat.getCode()))
					.findFirst().orElse(new BudgetCategoryDto()).getDescription());
		assertEquals(budgetCat2.getBudgetCategoryTypeCode(),
				categories.stream().filter(cat -> budgetCat2.getCode().equals(cat.getCode()))
					.findFirst().orElse(new BudgetCategoryDto()).getBudgetCategoryTypeCode());
	
	}
	
	@Test
	public void testBudgetCategory_getOne() {
		SimpleCrudDtoRestController<BudgetCategory, BudgetCategoryDto> controller = new BudgetCategoryController() {
			{
				wireControllerProperties(this);
			}

			@Override
			protected BudgetCategory getFromDataStore(Object code) {
				assertEquals(budgetCat1.getCode(), code);
				return budgetCat1;
			}

			@Override
			protected void assertUserHasReadAccess() {

			}
		};
		
		BudgetCategoryDto dto = controller.get(budgetCat1.getCode());
		assertEquals(budgetCat1.getCode(), dto.getCode());
		assertEquals(budgetCat1.getDescription(), dto.getDescription());
		assertEquals(budgetCat1.getBudgetCategoryTypeCode(), dto.getBudgetCategoryTypeCode());
	}
	
	@Test
	public void testBudgetCategory_updateOne() {
		SimpleCrudDtoRestController<BudgetCategory, BudgetCategoryDto> controller = new BudgetCategoryController() {
			{
				wireControllerProperties(this);
			}

			@Override
			protected BudgetCategory getFromDataStore(Object code) {
				assertEquals(budgetCat1.getCode(), code);
				return budgetCat1;
			}
			@Override
			protected BudgetCategory save(BudgetCategory bo) {
				updatedCategory = bo;
				return updatedCategory;
			}

			@Override
			protected void assertUserHasWriteAccess() {

			}

			@Override
			protected void assertUserHasReadAccess() {

			}

			@Override
			protected void validateBusinessObject(BudgetCategory budgetCategory) { }
			@Override
			protected void validateUpdateDataObject(BudgetCategory budgetCategory) { }
			@Override
			protected RestAuditLogger getAuditLogger() {
				return getTestRestAuditLogger();
			}
		};
		
		BudgetCategoryDto update = new BudgetCategoryDto();
		update.setDescription("New Description");
		update.setCode(budgetCat1.getCode());
		update.setBudgetCategoryTypeCode(budgetCat1.getBudgetCategoryTypeCode());
		controller.update(budgetCat1.getCode(), update);
		assertEquals(budgetCat1.getCode(), updatedCategory.getCode());
		assertEquals("New Description", updatedCategory.getDescription());
		assertEquals(budgetCat1.getBudgetCategoryTypeCode(), updatedCategory.getBudgetCategoryTypeCode());
		
		assertTrue("restAuditLog was saved", savedAuditLog != null);
		assertTrue(savedAuditLog.hasChanges());
		assertEquals(1, savedAuditLog.getModified().size());
		
	}
	
	protected RestAuditLogger getTestRestAuditLogger() {
		return new RestAuditLoggerImpl("quickstart", BudgetCategory.class, Arrays.asList("code", "description", "budgetCategoryTypeCode"), null, BeanWrapperImpl::new) {
			@Override
			public void saveAuditLog() {
				savedAuditLog = this.getRestAuditLog(); 
			}
		}; 
	}
	
	@Test(expected=ResourceNotFoundException.class)
	public void testBudgetCategory_updateNonExistent() {
		SimpleCrudDtoRestController<BudgetCategory, BudgetCategoryDto> controller = new BudgetCategoryController() {
			{
				wireControllerProperties(this);
			}

			@Override
			protected BudgetCategory getFromDataStore(Object code) {
				assertEquals(budgetCat1.getCode(), code);
				return null;
			}
			@Override
			protected BudgetCategory save(BudgetCategory bo) {
				updatedCategory = bo;
				return updatedCategory;
			}

			@Override
			protected void assertUserHasWriteAccess() {

			}

			@Override
			protected void assertUserHasReadAccess() {

			}

			@Override
			protected void validateBusinessObject(BudgetCategory budgetCategory) { }
		};
		
		BudgetCategoryDto update = new BudgetCategoryDto();
		update.setDescription("New Description");
		update.setCode(budgetCat1.getCode());
		update.setBudgetCategoryTypeCode(budgetCat1.getBudgetCategoryTypeCode());
		controller.update(budgetCat1.getCode(), update);
	}
	
	@Test()
	public void testBudgetCategory_add() {
		SimpleCrudDtoRestController<BudgetCategory, BudgetCategoryDto> controller = new BudgetCategoryController() {
			{
				wireControllerProperties(this);
			}

			@Override
			protected BudgetCategory getFromDataStore(Object code) {
				assertEquals(budgetCat1.getCode(), code);
				return null;
			}
			@Override
			protected BudgetCategory save(BudgetCategory bo) {
				updatedCategory = bo;
				return updatedCategory;
			}

			@Override
			protected void assertUserHasWriteAccess() {

			}

			@Override
			protected void assertUserHasReadAccess() {

			}

			@Override
			protected void validateBusinessObject(BudgetCategory budgetCategory) { }
			@Override
			protected void validateInsertDataObject(BudgetCategory budgetCategory) { }
			@Override
			protected RestAuditLogger getAuditLogger() {
				return getTestRestAuditLogger();
			}
		};
		
		BudgetCategoryDto update = new BudgetCategoryDto();
		update.setDescription(budgetCat1.getDescription());
		update.setCode(budgetCat1.getCode());
		update.setBudgetCategoryTypeCode(budgetCat1.getBudgetCategoryTypeCode());
		controller.add(update);
		assertEquals(budgetCat1.getCode(), updatedCategory.getCode());
		assertEquals(budgetCat1.getDescription(), updatedCategory.getDescription());
		assertEquals(budgetCat1.getBudgetCategoryTypeCode(), updatedCategory.getBudgetCategoryTypeCode());
		assertTrue("restAuditLog was saved", savedAuditLog != null);
		assertTrue(savedAuditLog.hasChanges());
		assertEquals(1, savedAuditLog.getAdded().size());
	}
	
	@Test(expected=UnprocessableEntityException.class)
	public void testBudgetCategory_addExisting() {
		SimpleCrudDtoRestController<BudgetCategory, BudgetCategoryDto> controller = new BudgetCategoryController() {
			{
				wireControllerProperties(this);
			}

			@Override
			protected BudgetCategory getFromDataStore(Object code) {
				assertEquals(budgetCat1.getCode(), code);
				return budgetCat1;
			}
			@Override
			protected BudgetCategory save(BudgetCategory bo) {
				updatedCategory = bo;
				return updatedCategory;
			}

			@Override
			protected void assertUserHasWriteAccess() {

			}

			@Override
			protected void assertUserHasReadAccess() {

			}

			@Override
			protected void validateBusinessObject(BudgetCategory budgetCategory) { }
		};
		
		BudgetCategoryDto update = new BudgetCategoryDto();
		update.setDescription(budgetCat1.getDescription());
		update.setCode(budgetCat1.getCode());
		update.setBudgetCategoryTypeCode(budgetCat1.getBudgetCategoryTypeCode());
		controller.add(update);
	}
	
	public void testBudgetCategory_deleteExisting() {
		SimpleCrudDtoRestController<BudgetCategory, BudgetCategoryDto> controller = new BudgetCategoryController() {
			{
				wireControllerProperties(this);
			}

			@Override
			protected BudgetCategory getFromDataStore(Object code) {
				assertEquals(budgetCat1.getCode(), code);
				return budgetCat1;
			}
			@Override
			protected void delete(BudgetCategory bo) {
				updatedCategory = bo;
			}

			@Override
			protected void assertUserHasWriteAccess() {

			}

			@Override
			protected void assertUserHasReadAccess() {

			}

			@Override
			protected void validateBusinessObject(BudgetCategory budgetCategory) { }
		};
		
		controller.delete(budgetCat1.getCode());
	}
	
	@Test(expected=ResourceNotFoundException.class)
	public void testBudgetCategory_deleteNonexistent() {
		final String fakeCode = "foo";
		SimpleCrudDtoRestController<BudgetCategory, BudgetCategoryDto> controller = new BudgetCategoryController() {
			{
				wireControllerProperties(this);
			}

			@Override
			protected BudgetCategory getFromDataStore(Object code) {
				assertEquals(fakeCode, code);
				return null;
			}
			@Override
			protected void delete(BudgetCategory bo) {
				assertTrue(false);
			}

			@Override
			protected void assertUserHasWriteAccess() {

			}

			@Override
			protected void assertUserHasReadAccess() {

			}

			@Override
			protected void validateBusinessObject(BudgetCategory budgetCategory) { }
		};
		
		controller.delete(fakeCode);
	}

	private void wireControllerProperties(SimpleCrudDtoRestController<BudgetCategory, BudgetCategoryDto> controller) {
		controller.setDataObjectClazz(BudgetCategory.class);
		controller.setDtoObjectClazz(BudgetCategoryDto.class);
		controller.setPrimaryKeyColumn("code");
		controller.setRegisterMapping(false);
	}
}
