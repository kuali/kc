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
package org.kuali.coeus.sys.impl.mq.rest;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.Before;
import org.junit.Test;
import org.kuali.rice.core.api.config.property.ConfigurationService;

import static org.junit.Assert.assertEquals;

public class RestDestinationRegistryImplTest {

    private Mockery context;
    private ConfigurationService configurationService;
    private RestDestinationRegistryImpl registry;

    @Before
    public void setUp() throws Exception {
        registry = new RestDestinationRegistryImpl();
        context = new JUnit4Mockery() {{setThreadingPolicy(new Synchroniser());}};
        configurationService = context.mock(ConfigurationService.class);
        registry.setConfigurationService(configurationService);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_null_destination_enabled() {
        registry.isEnabled(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_blank_destination_enabled() {
        registry.isEnabled(" ");
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_null_destination_url() {
        registry.isEnabled(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_blank_destination_url() {
        registry.isEnabled(" ");
    }

    @Test
    public void test_found_destination_enabled() {
        context.checking(new Expectations() {
            {
                oneOf(configurationService).getPropertyValueAsBoolean("destination" + ".enabled");
                will(returnValue(true));
            }
        });
        assertEquals(true, registry.isEnabled("destination"));
    }

    @Test
    public void test_not_found_destination_enabled() {
        context.checking(new Expectations() {
            {
                oneOf(configurationService).getPropertyValueAsBoolean("destination" + ".enabled");
                will(returnValue(false));
            }
        });
        assertEquals(false, registry.isEnabled("destination"));
    }

    @Test
    public void test_found_destination_url() {
        context.checking(new Expectations() {
            {
                oneOf(configurationService).getPropertyValueAsString("destination" + ".url");
                will(returnValue("https://destination"));
            }
        });
        assertEquals("https://destination", registry.findUrl("destination"));
    }

    @Test
    public void test_not_found_destination_url() {
        context.checking(new Expectations() {
            {
                oneOf(configurationService).getPropertyValueAsString("destination" + ".url");
                will(returnValue(null));
            }
        });
        assertEquals(null, registry.findUrl("destination"));
    }
}
