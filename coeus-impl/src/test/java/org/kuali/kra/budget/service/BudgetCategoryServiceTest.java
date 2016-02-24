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
package org.kuali.kra.budget.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.budget.framework.core.category.BudgetCategory;
import org.kuali.coeus.common.budget.framework.core.category.BudgetCategoryType;
import org.kuali.kra.external.budget.service.BudgetCategoryDTO;
import org.kuali.kra.external.budget.service.BudgetCategoryService;
import org.kuali.kra.external.budget.service.impl.BudgetCategoryServiceImpl;
import org.kuali.rice.krad.service.BusinessObjectService;

public class BudgetCategoryServiceTest {
	
	private Mockery context;
	private BudgetCategoryService budgetCategoryService;
	private BusinessObjectService boService;
	
	@Before
    public void setupMockery() {
        this.context.checking(new Expectations() {
        	{  
	        	Collection<BudgetCategory> budgetCategories = new ArrayList<BudgetCategory>();
	            BudgetCategory budgetCategory = new BudgetCategory();
	            budgetCategory.setCode("100");
	            budgetCategory.setDescription("Test Budget Category Service");
	            budgetCategory.setUpdateUser("quickstart");
	            budgetCategory.setUpdateTimestamp(new Timestamp(System.currentTimeMillis()));
	            budgetCategory.setBudgetCategoryTypeCode("BGTYP 1");
	            BudgetCategoryType budgetCategoryType = new BudgetCategoryType();
	            budgetCategoryType.setDescription("Budget Category Type 1");
	            budgetCategory.setBudgetCategoryType(budgetCategoryType);
	            budgetCategories.add(budgetCategory) ;
	            oneOf(boService).findAll(BudgetCategory.class);
	            will(returnValue(budgetCategories));
        	}
            
        });
    }
	
    @Before
    public void setUp() {
    	this.context = new JUnit4Mockery() ;
    	boService = this.context.mock(BusinessObjectService.class);
    	budgetCategoryService = new BudgetCategoryServiceImpl();
    	((BudgetCategoryServiceImpl)budgetCategoryService).setBusinessObjectService(boService);
    }
	
    @Test
    public void testBudgetCategoryService_lookupBudgetCategories() throws Exception {
    	List<BudgetCategoryDTO> budgetCategoryDTOs = budgetCategoryService.lookupBudgetCategories(null);
    	Assert.assertTrue(budgetCategoryDTOs.size()==1);
    }

}
