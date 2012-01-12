/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.common.notification;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;

/**
 * This class contains tests for NotificationRendererBase
 */
public class NotificationRendererBaseTest extends KcUnitTestBase {
    
    private static final String USER_FULLNAME_VAL = "Geoff  McGregor";
    
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
        
        assertEquals(2, nameValueMap.size());
        assertEquals(USER_FULLNAME_VAL, nameValueMap.get(NotificationRendererBase.USER_FULLNAME));
        // not testing actual value of the application URL since that would make this test quite fragile
        assertTrue(nameValueMap.containsKey(NotificationRendererBase.DOCHANDLER_PREFIX));
    }
    
}
