/*
 * Copyright 2005-2013 The Kuali Foundation
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
package org.kuali.kra.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.location.api.state.State;
import org.kuali.rice.location.api.state.StateService;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class StateServiceTest extends KcIntegrationTestBase {
    private StateService stateService;
    private static final String POSTAL_CNTRY_CD_UNITED_STATES = "US";
    private static final String ALT_POSTAL_CNTRY_CD_UNITED_STATES = "USA";
    
    @Before
    public void setUp() throws Exception {
        stateService = KcServiceLocator.getService(StateService.class);
    }
    @After
    public void tearDown() throws Exception {
        stateService = null;
    }
    
    @Test 
    public void testFindAllStatesByAltCountryCode() throws Exception {
        List<State> states = stateService.findAllStatesInCountryByAltCode(ALT_POSTAL_CNTRY_CD_UNITED_STATES); 
        List<State> statesForComparison = stateService.findAllStatesInCountry(POSTAL_CNTRY_CD_UNITED_STATES);
        
        assertNotNull(states);
        assertNotNull(statesForComparison);
        assertEquals(states.size(), statesForComparison.size());
        int i = 0;
        for(State state : states) {
            assertEquals(state.getName(), statesForComparison.get(i).getName());
            i++;
        }
    }
}
