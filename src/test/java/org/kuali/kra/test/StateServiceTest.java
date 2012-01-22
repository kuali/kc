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
package org.kuali.kra.test;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.rice.location.api.state.State;
import org.kuali.rice.location.api.state.StateService;


public class StateServiceTest extends KcUnitTestBase{
    private StateService stateService;
    private static final String POSTAL_CNTRY_CD_UNITED_STATES = "US";
    private static final String ALT_POSTAL_CNTRY_CD_UNITED_STATES = "USA";
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        stateService = KraServiceLocator.getService(StateService.class);
    }
    @After
    public void tearDown() throws Exception {
        stateService = null;
        super.tearDown();
    }
    
    @Test 
    public void testFindAllStatesByAltCountryCode() throws Exception {
        //TODO: Rice Upgrade 2.0 The commented method call has been re-introduced in b1 version.
        //List<State> states = stateService.findAllStatesInCountryByAltCode(ALT_POSTAL_CNTRY_CD_UNITED_STATES); 
        List<State> states = stateService.findAllStatesInCountry(POSTAL_CNTRY_CD_UNITED_STATES);
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
