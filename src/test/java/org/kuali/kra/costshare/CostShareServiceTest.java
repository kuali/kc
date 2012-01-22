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
package org.kuali.kra.costshare;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants.COMPONENT;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants.NAMESPACE;

@NAMESPACE(namespace=Constants.KC_GENERIC_PARAMETER_NAMESPACE)
@COMPONENT(component=Constants.KC_ALL_PARAMETER_DETAIL_TYPE_CODE)
public class CostShareServiceTest extends KcUnitTestBase {
    
    private CostShareService costShareService;
    private CostShareServiceImpl costShareServiceImpl;
    private ParameterService ps;

    @Before
    public void setUp() throws Exception {
        ps = KraServiceLocator.getService(ParameterService.class);
        costShareService = KraServiceLocator.getService(CostShareService.class);
        costShareServiceImpl = (CostShareServiceImpl) KraServiceLocator.getService(CostShareService.class);
    }

    @After
    public void tearDown() throws Exception {
        costShareService = null;
        costShareServiceImpl = null;
        ps = null;
    }

    @Test
    public void testGetCostShareLabel() {
        updateParameterForTesting(this.getClass(), "CostShareProjectPeriodNameLabel", "Project Period");
        String result = costShareService.getCostShareLabel(false);
        assertEquals("Project Period", result);
    }
    
    @Test
    public void testValidateProjectPeriodAsFiscalYear() {
        boolean result = costShareService.validateProjectPeriodAsFiscalYear(false);
        assertFalse(result);
    }
    
    @Test
    public void tesValidateProjectPeriodAsProjectPeriod() {
        boolean result = costShareService.validateProjectPeriodAsProjectPeriod(false);
        assertTrue(result);
    }
    
    @Test
    public void testSwitchParmAndReTestValidations() {        
        String fiscalYearParm = "FisCal Year";
        updateParameterForTesting(this.getClass(), "CostShareProjectPeriodNameLabel", fiscalYearParm);
        String newParm = costShareService.getCostShareLabel(true);
        
        assertEquals(fiscalYearParm, newParm);
        assertTrue(costShareService.validateProjectPeriodAsFiscalYear(false));
        assertFalse(costShareService.validateProjectPeriodAsProjectPeriod(false));
        
        String fooBar = "foo";
        updateParameterForTesting(this.getClass(), "CostShareProjectPeriodNameLabel", fooBar);
        newParm = costShareService.getCostShareLabel(true);
        assertEquals(fooBar, newParm);
        assertFalse(costShareService.validateProjectPeriodAsFiscalYear(false));
        assertFalse(costShareService.validateProjectPeriodAsProjectPeriod(false));
    }

    @Test
    public void testGetParameterService() {
        assertNotNull(costShareServiceImpl.getParameterService());
    }

}
