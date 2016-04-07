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
package org.kuali.coeus.sys.framework.controller.rest;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.budget.impl.core.category.BudgetCategoryController;
import org.kuali.coeus.common.budget.impl.core.category.BudgetCategoryDto;
import org.kuali.coeus.sys.framework.rest.DataDictionaryValidationException;
import org.kuali.coeus.sys.framework.rest.ResourceNotFoundException;
import org.kuali.coeus.sys.framework.rest.UnauthorizedAccessException;
import org.kuali.coeus.sys.framework.rest.UnprocessableEntityException;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class SimpleCrudDtoRestControllerBudgetCategoryTest extends KcIntegrationTestBase {

    private static final String AUTHORIZED_USER = "admin";
    private static final String UNAUTHORIZED_USER = "mwmartin";

    private BudgetCategoryController budgetCategoryController;

    @Before
    public void findActivityTypeRestController() {
        budgetCategoryController = KcServiceLocator.getService("budgetCategoryController");
    }

    @Test(expected = UnauthorizedAccessException.class)
    public void test_unauthorized_getAll() {
        GlobalVariables.setUserSession(new UserSession(UNAUTHORIZED_USER));
        budgetCategoryController.getAll(Collections.emptyMap());
    }

    @Test(expected = UnauthorizedAccessException.class)
    public void test_unauthorized_get() {
        GlobalVariables.setUserSession(new UserSession(UNAUTHORIZED_USER));
        budgetCategoryController.get("1");
    }

    @Test(expected = UnauthorizedAccessException.class)
    public void test_unauthorized_create() {
        GlobalVariables.setUserSession(new UserSession(UNAUTHORIZED_USER));
        budgetCategoryController.add(Collections.emptyMap());
    }

    @Test(expected = UnauthorizedAccessException.class)
    public void test_unauthorized_update() {
        GlobalVariables.setUserSession(new UserSession(UNAUTHORIZED_USER));
        budgetCategoryController.update("1", new BudgetCategoryDto());
    }

    @Test(expected = UnauthorizedAccessException.class)
    public void test_unauthorized_delete() {
        GlobalVariables.setUserSession(new UserSession(UNAUTHORIZED_USER));
        budgetCategoryController.delete("1");
    }

    @Test
    public void test_unauthorized_schema() {
        //there is no authorization around _schema
        GlobalVariables.setUserSession(new UserSession(UNAUTHORIZED_USER));
        budgetCategoryController.getSchema();
    }

    @Test
    public void test_authorized_getAll() {
        GlobalVariables.setUserSession(new UserSession(AUTHORIZED_USER));
        Collection<BudgetCategoryDto> all = budgetCategoryController.getAll(Collections.emptyMap());
        assertTrue(all != null && !all.isEmpty());
    }

    @Test
    public void test_authorized_get() {
        GlobalVariables.setUserSession(new UserSession(AUTHORIZED_USER));
        BudgetCategoryDto object = budgetCategoryController.get("1");
        assertTrue(object != null);
        assertEquals("1", object.getCode());
        assertEquals("Senior Personnel", object.getDescription());
        assertEquals("P", object.getBudgetCategoryTypeCode());
        assertEquals("1", object.get_primaryKey());
    }

    @Test
    public void test_authorized_create() {
        GlobalVariables.setUserSession(new UserSession(AUTHORIZED_USER));
        BudgetCategoryDto object = new BudgetCategoryDto() {{
            setCode("80");
            setDescription("GnR hires a new Teacup Pig mascot");
            setBudgetCategoryTypeCode("P");
        }};

        BudgetCategoryDto added = (BudgetCategoryDto) budgetCategoryController.add(object);
        assertEquals(added.getCode(), "80");
        assertEquals(added.getDescription(), "GnR hires a new Teacup Pig mascot");
        assertEquals(added.getBudgetCategoryTypeCode(), "P");
        assertEquals(added.get_primaryKey(), "80");
    }

    @Test
    public void test_authorized_update() {
        GlobalVariables.setUserSession(new UserSession(AUTHORIZED_USER));
        BudgetCategoryDto object = new BudgetCategoryDto() {{
            setCode("1");
            setDescription("Give the Pig a buttermilk bath like in Charlotte's Web");
            setBudgetCategoryTypeCode("P");
        }};
        budgetCategoryController.update("1", object);
        BudgetCategoryDto updated = budgetCategoryController.get("1");
        assertEquals(updated.getDescription(), "Give the Pig a buttermilk bath like in Charlotte's Web");
    }

    @Test(expected = DataDictionaryValidationException.class)
    public void test_authorized_delete_references_exist() {
        GlobalVariables.setUserSession(new UserSession(AUTHORIZED_USER));
        budgetCategoryController.delete("1");
    }

    @Test
    public void test_authorized_delete() {
        GlobalVariables.setUserSession(new UserSession(AUTHORIZED_USER));
        budgetCategoryController.delete("34");
        try {
            budgetCategoryController.get("34");
        } catch (ResourceNotFoundException e) {
            return;
        }
        fail();
    }

    @Test
    public void test_authorized_schema() {
        GlobalVariables.setUserSession(new UserSession(AUTHORIZED_USER));

        final Map<String, Object> schema = budgetCategoryController.getSchema();
        assertTrue(schema != null && !schema.isEmpty());
        assertEquals(schema.get("primaryKey"), "code");
        assertEquals(Stream.of("budgetCategoryTypeCode", "code", "description").collect(Collectors.toList()), schema.get("columns"));
    }

    @Test
    public void test_getAll_filtered() {
        GlobalVariables.setUserSession(new UserSession(AUTHORIZED_USER));
        Collection<BudgetCategoryDto> all = budgetCategoryController.getAll(Collections.singletonMap("description", "Duplicating"));
        assertTrue(all != null && !all.isEmpty() && all.size() == 1);
        assertEquals(all.iterator().next().getCode(), "10");
        assertEquals(all.iterator().next().getDescription(), "Duplicating");
        assertEquals(all.iterator().next().getBudgetCategoryTypeCode(), "O");
        assertEquals(all.iterator().next().get_primaryKey(), "10");
    }

    @Test(expected = ResourceNotFoundException.class)
    public void test_not_found_getAll() {
        GlobalVariables.setUserSession(new UserSession(AUTHORIZED_USER));
        budgetCategoryController.getAll(Collections.singletonMap("description", "Not Found"));
    }

    @Test(expected = ResourceNotFoundException.class)
    public void test_not_found_get() {
        GlobalVariables.setUserSession(new UserSession(AUTHORIZED_USER));
        budgetCategoryController.get("FOO");
    }

    @Test(expected = ResourceNotFoundException.class)
    public void test_not_found_update() {
        GlobalVariables.setUserSession(new UserSession(AUTHORIZED_USER));
        BudgetCategoryDto object = new BudgetCategoryDto() {{
            setBudgetCategoryTypeCode("Pig");
            setDescription("Teacup Pig");
        }};
        budgetCategoryController.update("Pig", object);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void test_not_found_delete() {
        GlobalVariables.setUserSession(new UserSession(AUTHORIZED_USER));
        budgetCategoryController.delete("FOO");
    }

    @Test(expected = UnprocessableEntityException.class)
    public void test_duplicate_create() {
        GlobalVariables.setUserSession(new UserSession(AUTHORIZED_USER));

        BudgetCategoryDto object = new BudgetCategoryDto() {{
            setCode("1");
            setDescription("Pork");
        }};
        budgetCategoryController.add(object);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void test_missing_user_supplied_pk() {
        GlobalVariables.setUserSession(new UserSession(AUTHORIZED_USER));

        BudgetCategoryDto object = new BudgetCategoryDto() {{
            setDescription("Pork");
        }};
        budgetCategoryController.add(object);
    }

    @Test(expected = DataDictionaryValidationException.class)
    public void test_missing_required_field() {
        GlobalVariables.setUserSession(new UserSession(AUTHORIZED_USER));

        BudgetCategoryDto object = new BudgetCategoryDto() {{
            setCode("80");
        }};
        budgetCategoryController.add(object);
    }

    @Test(expected = DataDictionaryValidationException.class)
    public void test_invalid_field() {
        GlobalVariables.setUserSession(new UserSession(AUTHORIZED_USER));

        BudgetCategoryDto object = new BudgetCategoryDto() {{
            setCode("80");
            setDescription(StringUtils.repeat("LongString","",21));
        }};
        budgetCategoryController.add(object);
    }
}
