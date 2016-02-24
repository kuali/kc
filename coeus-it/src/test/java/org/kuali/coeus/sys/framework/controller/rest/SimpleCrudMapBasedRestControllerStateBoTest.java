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

import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.sys.framework.rest.DataDictionaryValidationException;
import org.kuali.coeus.sys.framework.rest.ResourceNotFoundException;
import org.kuali.coeus.sys.framework.rest.UnprocessableEntityException;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.location.impl.state.StateBo;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;

/**
 * This test primarily ensures that resources with compound keys work correctly.
 */
public class SimpleCrudMapBasedRestControllerStateBoTest extends KcIntegrationTestBase {

    private static final String AUTHORIZED_USER = "admin";

    private SimpleCrudMapBasedRestController<StateBo> stateController;

    @SuppressWarnings("unchecked")
    @Before
    public void findActivityTypeRestController() {
        stateController = KcServiceLocator.getServicesOfType(SimpleCrudMapBasedRestController.class)
                .stream()
                .filter(c -> c.getDataObjectClazz().equals(StateBo.class))
                .findAny()
                .get();
    }

    @Test
    public void test_authorized_getAll() {
        GlobalVariables.setUserSession(new UserSession(AUTHORIZED_USER));
        Collection<Map<String,Object>> all = stateController.getAll(Collections.emptyMap());
        assertTrue(all != null && !all.isEmpty());
    }

    @Test
    public void test_authorized_get() {
        GlobalVariables.setUserSession(new UserSession(AUTHORIZED_USER));
        Map<String, Object> object = stateController.get("MI:US");
        assertTrue(object != null && !object.isEmpty());
        assertEquals(object.get("code"), "MI");
        assertEquals(object.get("countryCode"), "US");
        assertEquals(object.get("name"), "MICHIGAN");
        assertEquals(object.get("active"), Boolean.TRUE);
        assertEquals(object.get("_primaryKey"), "MI:US");
    }

    @Test
    public void test_authorized_create() {
        GlobalVariables.setUserSession(new UserSession(AUTHORIZED_USER));
        Map<String, Object> object = new HashMap<String, Object>() {{
            put("code", "MR");
            put("countryCode", "US");
            put("name", "MARS");
            put("active", Boolean.TRUE);
        }};

        Map<String, Object> added = stateController.add(object);
        assertEquals(added.get("code"), "MR");
        assertEquals(added.get("countryCode"), "US");
        assertEquals(added.get("name"), "MARS");
        assertEquals(added.get("active"), Boolean.TRUE);
        assertEquals(added.get("_primaryKey"), "MR:US");
    }

    @Test
    public void test_authorized_update() {
        GlobalVariables.setUserSession(new UserSession(AUTHORIZED_USER));
        Map<String, Object> object = new HashMap<String, Object>() {{
            put("code", "MI");
            put("countryCode", "US");
            put("name", "MICHIGAN");
            put("active", Boolean.FALSE);
        }};
        stateController.update("MI:US", object);
        Map<String, Object> updated = stateController.get("MI:US");
        assertEquals(updated.get("active"), Boolean.FALSE);
    }

    @Test
    public void test_authorized_delete() {
        GlobalVariables.setUserSession(new UserSession(AUTHORIZED_USER));
        stateController.delete("HI:US");
        try {
            stateController.get("HI:US");
        } catch (ResourceNotFoundException e) {
            return;
        }
        fail();
    }

    @Test
    public void test_authorized_schema() {
        GlobalVariables.setUserSession(new UserSession(AUTHORIZED_USER));

        final Map<String, Object> schema = stateController.getSchema();
        assertTrue(schema != null && !schema.isEmpty());
        assertEquals(schema.get("primaryKey"), "code:countryCode");
        assertEquals(schema.get("columns"), Stream.of("code", "countryCode", "name", "active").collect(Collectors.toList()));
    }

    @Test
    public void test_getAll_filtered() {
        GlobalVariables.setUserSession(new UserSession(AUTHORIZED_USER));
        Collection<Map<String, Object>> all = stateController.getAll(Collections.singletonMap("name", "MICHIGAN"));
        assertTrue(all != null && !all.isEmpty() && all.size() == 1);
        assertEquals(all.iterator().next().get("code"), "MI");
        assertEquals(all.iterator().next().get("countryCode"), "US");
        assertEquals(all.iterator().next().get("name"), "MICHIGAN");
        assertEquals(all.iterator().next().get("active"), Boolean.TRUE);
        assertEquals(all.iterator().next().get("_primaryKey"), "MI:US");
    }

    @Test(expected = ResourceNotFoundException.class)
    public void test_not_found_getAll() {
        GlobalVariables.setUserSession(new UserSession(AUTHORIZED_USER));
        stateController.getAll(Collections.singletonMap("name", "Not Found"));
    }

    @Test(expected = ResourceNotFoundException.class)
    public void test_not_found_get() {
        GlobalVariables.setUserSession(new UserSession(AUTHORIZED_USER));
        stateController.get("FOO:BAR");
    }

    @Test(expected = ResourceNotFoundException.class)
    public void test_not_found_get_partial_key() {
        GlobalVariables.setUserSession(new UserSession(AUTHORIZED_USER));
        stateController.get("FOO");
    }

    @Test(expected = ResourceNotFoundException.class)
    public void test_not_found_update() {
        GlobalVariables.setUserSession(new UserSession(AUTHORIZED_USER));
        Map<String, Object> object = new HashMap<String, Object>() {{
            put("code", "FOO");
            put("countryCode", "BAR");
            put("name", "Bleh");
            put("active", Boolean.TRUE);
        }};
        stateController.update("FOO:BAR", object);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void test_not_found_update_partial_key() {
        GlobalVariables.setUserSession(new UserSession(AUTHORIZED_USER));
        Map<String, Object> object = new HashMap<String, Object>() {{
            put("code", "FOO");
            put("countryCode", "BAR");
            put("name", "Bleh");
            put("active", Boolean.TRUE);
        }};
        stateController.update("FOO:", object);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void test_not_found_delete() {
        GlobalVariables.setUserSession(new UserSession(AUTHORIZED_USER));
        stateController.delete("FOO:BAR");
    }

    @Test(expected = ResourceNotFoundException.class)
    public void test_not_found_delete_partial_key() {
        GlobalVariables.setUserSession(new UserSession(AUTHORIZED_USER));
        stateController.delete("FOO");
    }

    @Test(expected = UnprocessableEntityException.class)
    public void test_duplicate_create() {
        GlobalVariables.setUserSession(new UserSession(AUTHORIZED_USER));

        Map<String, Object> object = new HashMap<String, Object>() {{
            put("code", "MI");
            put("countryCode", "US");
            put("name", "Michigan");
            put("active", Boolean.TRUE);
        }};
        stateController.add(object);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void test_missing_user_supplied_pk() {
        GlobalVariables.setUserSession(new UserSession(AUTHORIZED_USER));

        Map<String, Object> object = new HashMap<String, Object>() {{
            put("name", "Michigan");
            put("active", Boolean.TRUE);
        }};
        stateController.add(object);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void test_missing_user_supplied_pk_partial() {
        GlobalVariables.setUserSession(new UserSession(AUTHORIZED_USER));

        Map<String, Object> object = new HashMap<String, Object>() {{
            put("code", "MI");
            put("name", "Michigan");
            put("active", Boolean.TRUE);
        }};
        stateController.add(object);
    }

    @Test(expected = DataDictionaryValidationException.class)
    public void test_missing_required_field() {
        GlobalVariables.setUserSession(new UserSession(AUTHORIZED_USER));

        Map<String, Object> object = new HashMap<String, Object>() {{
            put("code", "MR");
            put("countryCode", "US");
            put("active", Boolean.TRUE);
        }};
        stateController.add(object);
    }

    @Test(expected = UnprocessableEntityException.class)
    public void test_invalid_field() {
        GlobalVariables.setUserSession(new UserSession(AUTHORIZED_USER));

        Map<String, Object> object = new HashMap<String, Object>() {{
            put("code", "MR");
            put("countryCode", "US");
            put("name", "MARS");
            put("active", "Z");
        }};
        stateController.add(object);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void test_getAll_invalid_field() {
        GlobalVariables.setUserSession(new UserSession(AUTHORIZED_USER));
        stateController.getAll(Collections.singletonMap("active", "Z"));
    }
}
