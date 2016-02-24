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
package org.kuali.kra.common.notification;

import org.junit.Test;
import org.kuali.coeus.common.notification.impl.NotificationRendererBase;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;

import java.util.HashMap;
import java.util.Map;
import static org.junit.Assert.*;
/**
 * This class contains tests for NotificationRendererBase
 */
public class NotificationRendererBaseTest extends KcIntegrationTestBase {
    
    private static final String USER_FULLNAME_VAL = "Geoff McGregor";
    
    @SuppressWarnings("serial")
    @Test
    public void testRender() {
        String inputText = "The {Q} brown {F} {J} over the lazy {D}.";
    
        NotificationRendererBase renderer = new NotificationRendererBase() {
            
            // mocking this method; we are testing it below
            public Map<String, String> getDefaultReplacementParameters() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("{J}", "jumps");
                params.put("{Q}", "quick");
                params.put("{D}", "dog");
                params.put("{F}", "fox");
                return params;
            }
            
        };
        String outputText = renderer.render(inputText);
        assertEquals("The quick brown fox jumps over the lazy dog.", outputText);
    
        // test with null parameter values
        renderer = new NotificationRendererBase() {
            
            // return a couple of null values in the map
            public Map<String, String> getDefaultReplacementParameters() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("{J}", null);
                params.put("{Q}", null);
                params.put("{D}", "dog");
                params.put("{F}", "fox");
                return params;
            }
            
        };
        outputText = renderer.render(inputText);
        assertEquals("The {Q} brown fox {J} over the lazy dog.", outputText);
    }
    
    @Test
    public void testGetDefaultReplacementParameters() {
        // we will test an anonymous instance of the abstract renderer
        @SuppressWarnings("serial")
        NotificationRendererBase renderer = new NotificationRendererBase() {};
        Map<String, String> nameValueMap = renderer.getDefaultReplacementParameters();
        
        assertEquals(3, nameValueMap.size());
        assertEquals(USER_FULLNAME_VAL, nameValueMap.get(NotificationRendererBase.USER_FULLNAME));
        // not testing actual value of the application URL since that would make this test quite fragile
        assertTrue(nameValueMap.containsKey(NotificationRendererBase.DOCHANDLER_PREFIX));
        assertTrue(nameValueMap.containsKey(NotificationRendererBase.APP_LINK_PREFIX));
    }
    
}
