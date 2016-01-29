package org.kuali.coeus.common.budget.impl.core;

import static org.junit.Assert.*;

import java.time.Instant;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.budget.framework.core.category.BudgetCategory;
import org.kuali.coeus.common.budget.impl.core.category.BudgetCategoryController;
import org.kuali.coeus.common.budget.impl.core.category.BudgetCategoryDto;
import org.kuali.coeus.sys.framework.controller.rest.audit.RestAuditLog;
import org.kuali.coeus.sys.framework.controller.rest.audit.RestAuditLogger;
import org.kuali.coeus.sys.framework.rest.ResourceNotFoundException;
import org.kuali.coeus.sys.framework.rest.UnprocessableEntityException;
import org.kuali.coeus.sys.impl.controller.rest.audit.RestAuditLoggerImpl;

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
		BudgetCategoryController controller = new BudgetCategoryController() {
			@Override
			protected Collection<BudgetCategory> getAllFromDataStore() {
				return Stream.of(budgetCat1, budgetCat2).collect(Collectors.toList());
			}

			@Override
			protected void assertUserHasReadAccess() {

			}
		};
		
		Collection<BudgetCategoryDto> categories = controller.getAll();
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
		BudgetCategoryController controller = new BudgetCategoryController() {
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
		BudgetCategoryController controller = new BudgetCategoryController() {
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
			protected boolean validateUpdateDataObject(BudgetCategory budgetCategory) { return true; }
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
		return new RestAuditLoggerImpl("quickstart", BudgetCategory.class, Arrays.asList("code", "description", "budgetCategoryTypeCode"), null) {
			@Override
			public void saveAuditLog() {
				savedAuditLog = this.getRestAuditLog(); 
			}
		}; 
	}
	
	@Test(expected=ResourceNotFoundException.class)
	public void testBudgetCategory_updateNonExistent() {
		BudgetCategoryController controller = new BudgetCategoryController() {
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
		BudgetCategoryController controller = new BudgetCategoryController() {
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
			protected boolean validateInsertDataObject(BudgetCategory budgetCategory) { return true; }
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
		BudgetCategoryController controller = new BudgetCategoryController() {
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
		BudgetCategoryController controller = new BudgetCategoryController() {
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
		BudgetCategoryController controller = new BudgetCategoryController() {
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
}
