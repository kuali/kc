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
package org.kuali.coeus.sys.framework.controller.rest;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.framework.type.DeadlineType;
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
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class SimpleCrudMapBasedRestControllerDeadlineTypeTest extends KcIntegrationTestBase {

    private static final String AUTHORIZED_USER = "admin";
    private static final String UNAUTHORIZED_USER = "mwmartin";

    private SimpleCrudMapBasedRestController<DeadlineType> deadlineTypeController;

    @SuppressWarnings("unchecked")
    @Before
    public void findActivityTypeRestController() {
        deadlineTypeController = KcServiceLocator.getServicesOfType(SimpleCrudMapBasedRestController.class)
                .stream()
                .filter(c -> c.getDataObjectClazz().equals(DeadlineType.class))
                .findAny()
                .get();
    }

    @Test(expected = UnauthorizedAccessException.class)
    public void test_unauthorized_getAll() {
        GlobalVariables.setUserSession(new UserSession(UNAUTHORIZED_USER));
        deadlineTypeController.getAll(Collections.emptyMap());
    }

    @Test(expected = UnauthorizedAccessException.class)
    public void test_unauthorized_get() {
        GlobalVariables.setUserSession(new UserSession(UNAUTHORIZED_USER));
        deadlineTypeController.get("P");
    }

    @Test(expected = UnauthorizedAccessException.class)
    public void test_unauthorized_create() {
        GlobalVariables.setUserSession(new UserSession(UNAUTHORIZED_USER));
        deadlineTypeController.add(Collections.emptyMap());
    }

    @Test(expected = UnauthorizedAccessException.class)
    public void test_unauthorized_update() {
        GlobalVariables.setUserSession(new UserSession(UNAUTHORIZED_USER));
        deadlineTypeController.update("P", Collections.emptyMap());
    }

    @Test(expected = UnauthorizedAccessException.class)
    public void test_unauthorized_delete() {
        GlobalVariables.setUserSession(new UserSession(UNAUTHORIZED_USER));
        deadlineTypeController.delete("P");
    }

    @Test(expected = UnauthorizedAccessException.class)
    public void test_unauthorized_schema() {
        GlobalVariables.setUserSession(new UserSession(UNAUTHORIZED_USER));
        deadlineTypeController.getSchema();
    }

    @Test
    public void test_authorized_getAll() {
        GlobalVariables.setUserSession(new UserSession(AUTHORIZED_USER));
        Collection<Map<String,Object>> all = deadlineTypeController.getAll(Collections.emptyMap());
        assertTrue(all != null && !all.isEmpty());
    }

    @Test
    public void test_authorized_get() {
        GlobalVariables.setUserSession(new UserSession(AUTHORIZED_USER));
        Map<String, Object> object = deadlineTypeController.get("P");
        assertTrue(object != null && !object.isEmpty());
        assertEquals(object.get("deadlineTypeCode"), "P");
        assertEquals(object.get("description"), "Postmark");
        assertEquals(object.get("_primaryKey"), "P");
    }

    @Test
    public void test_authorized_create() {
        GlobalVariables.setUserSession(new UserSession(AUTHORIZED_USER));
        Map<String, Object> object = new HashMap<String, Object>() {{
            put("deadlineTypeCode", "S");
            put("description", "Squirrel");
        }};

        Map<String, Object> added = deadlineTypeController.add(object);
        assertEquals(added.get("deadlineTypeCode"), "S");
        assertEquals(added.get("description"), "Squirrel");
        assertEquals(added.get("_primaryKey"), "S");
    }

    @Test
    public void test_authorized_update() {
        GlobalVariables.setUserSession(new UserSession(AUTHORIZED_USER));
        Map<String, Object> object = new HashMap<String, Object>() {{
            put("deadlineTypeCode", "P");
            put("description", "Teacup Pig");
        }};
        deadlineTypeController.update("P", object);
        Map<String, Object> updated = deadlineTypeController.get("P");
        assertEquals(updated.get("description"), "Teacup Pig");
    }

    @Test
    public void test_authorized_delete() {
        GlobalVariables.setUserSession(new UserSession(AUTHORIZED_USER));
        deadlineTypeController.delete("R");
        try {
            deadlineTypeController.get("R");
        } catch (ResourceNotFoundException e) {
            return;
        }
        fail();
    }

    @Test
    public void test_authorized_schema() {
        GlobalVariables.setUserSession(new UserSession(AUTHORIZED_USER));

        final Map<String, Object> schema = deadlineTypeController.getSchema();
        assertTrue(schema != null && !schema.isEmpty());
        assertEquals(schema.get("primaryKey"), "deadlineTypeCode");
        assertEquals(schema.get("columns"), Stream.of("deadlineTypeCode", "description").collect(Collectors.toList()));
    }

    @Test
    public void test_getAll_filtered() {
        GlobalVariables.setUserSession(new UserSession(AUTHORIZED_USER));
        Collection<Map<String, Object>> all = deadlineTypeController.getAll(Collections.singletonMap("description", "Target"));
        assertTrue(all != null && !all.isEmpty() && all.size() == 1);
        assertEquals(all.iterator().next().get("deadlineTypeCode"), "T");
        assertEquals(all.iterator().next().get("description"), "Target");
        assertEquals(all.iterator().next().get("_primaryKey"), "T");
    }

    @Test(expected = ResourceNotFoundException.class)
    public void test_not_found_getAll() {
        GlobalVariables.setUserSession(new UserSession(AUTHORIZED_USER));
        deadlineTypeController.getAll(Collections.singletonMap("description", "Not Found"));
    }

    @Test(expected = ResourceNotFoundException.class)
    public void test_not_found_get() {
        GlobalVariables.setUserSession(new UserSession(AUTHORIZED_USER));
        deadlineTypeController.get("FOO");
    }

    @Test(expected = ResourceNotFoundException.class)
    public void test_not_found_update() {
        GlobalVariables.setUserSession(new UserSession(AUTHORIZED_USER));
        Map<String, Object> object = new HashMap<String, Object>() {{
            put("deadlineTypeCode", "Pig");
            put("description", "Teacup Pig");
        }};
        deadlineTypeController.update("Pig", object);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void test_not_found_delete() {
        GlobalVariables.setUserSession(new UserSession(AUTHORIZED_USER));
        deadlineTypeController.delete("FOO");
    }

    @Test(expected = UnprocessableEntityException.class)
    public void test_duplicate_create() {
        GlobalVariables.setUserSession(new UserSession(AUTHORIZED_USER));

        Map<String, Object> object = new HashMap<String, Object>() {{
            put("deadlineTypeCode", "P");
            put("description", "Pork");
        }};
        deadlineTypeController.add(object);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void test_missing_user_supplied_pk() {
        GlobalVariables.setUserSession(new UserSession(AUTHORIZED_USER));

        Map<String, Object> object = new HashMap<String, Object>() {{
            put("description", "Pork");
        }};
        deadlineTypeController.add(object);
    }

    @Test(expected = DataDictionaryValidationException.class)
    public void test_missing_required_field() {
        GlobalVariables.setUserSession(new UserSession(AUTHORIZED_USER));

        Map<String, Object> object = new HashMap<String, Object>() {{
            put("deadlineTypeCode", "A");
        }};
        deadlineTypeController.add(object);
    }

    @Test(expected = DataDictionaryValidationException.class)
    public void test_invalid_field() {
        GlobalVariables.setUserSession(new UserSession(AUTHORIZED_USER));

        Map<String, Object> object = new HashMap<String, Object>() {{
            put("deadlineTypeCode", "A");
            put("description", StringUtils.repeat("LongString","",21));
        }};
        deadlineTypeController.add(object);
    }
}
