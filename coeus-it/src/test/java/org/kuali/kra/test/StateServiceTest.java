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
