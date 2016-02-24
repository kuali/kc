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
package org.kuali.kra.costshare;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.framework.costshare.CostShareService;
import org.kuali.coeus.common.impl.costshare.CostShareServiceImpl;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.coreservice.framework.CoreFrameworkServiceLocator;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants.COMPONENT;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants.NAMESPACE;

import static org.junit.Assert.*;
@NAMESPACE(namespace=Constants.KC_GENERIC_PARAMETER_NAMESPACE)
@COMPONENT(component=Constants.KC_ALL_PARAMETER_DETAIL_TYPE_CODE)
public class CostShareServiceTest extends KcIntegrationTestBase {
    
    private CostShareService costShareService;
    private CostShareServiceImpl costShareServiceImpl;

    @Before
    public void setUp() throws Exception {
        costShareService = KcServiceLocator.getService(CostShareService.class);
        costShareServiceImpl = (CostShareServiceImpl) KcServiceLocator.getService(CostShareService.class);
    }

    @After
    public void tearDown() throws Exception {
        costShareService = null;
        costShareServiceImpl = null;
    }

    @Test
    public void testGetCostShareLabel() {
        String parameterForBackup = CoreFrameworkServiceLocator.getParameterService().getParameterValueAsString(this.getClass(), "CostShareProjectPeriodNameLabel");
        updateParameterForTesting(this.getClass(), "CostShareProjectPeriodNameLabel", "Project Period");
        String result = costShareService.getCostShareLabel();
        assertEquals("Project Period", result);
        
        //Switch it back
        updateParameterForTesting(this.getClass(), "CostShareProjectPeriodNameLabel", parameterForBackup);
    }
    
    @Test
    public void testValidateProjectPeriodAsFiscalYear() {
        String parameterForBackup = CoreFrameworkServiceLocator.getParameterService().getParameterValueAsString(this.getClass(), "CostShareProjectPeriodNameLabel");
        updateParameterForTesting(this.getClass(), "CostShareProjectPeriodNameLabel", "Project Period");
        
        boolean result = costShareService.validateProjectPeriodAsFiscalYear();
        assertFalse(result);
        
      //Switch it back
        updateParameterForTesting(this.getClass(), "CostShareProjectPeriodNameLabel", parameterForBackup);
    }
    
    @Test
    public void tesValidateProjectPeriodAsProjectPeriod() {
        String parameterForBackup = CoreFrameworkServiceLocator.getParameterService().getParameterValueAsString(this.getClass(), "CostShareProjectPeriodNameLabel");
        updateParameterForTesting(this.getClass(), "CostShareProjectPeriodNameLabel", "Project Period");
        
        boolean result = costShareService.validateProjectPeriodAsProjectPeriod();
        assertTrue(result);
        
      //Switch it back
      updateParameterForTesting(this.getClass(), "CostShareProjectPeriodNameLabel", parameterForBackup);
    }

    @Test
    public void testSwitchParmAndReTestValidations() {        
        String fiscalYearParm = "FisCal Year";
        String parameterForBackup = costShareService.getCostShareLabel();
        
        updateParameterForTesting(this.getClass(), "CostShareProjectPeriodNameLabel", fiscalYearParm);
        String newParm = costShareService.getCostShareLabel();
        
        assertEquals(fiscalYearParm, newParm);
        assertTrue(costShareService.validateProjectPeriodAsFiscalYear());
        assertFalse(costShareService.validateProjectPeriodAsProjectPeriod());
        
        String fooBar = "foo";
        updateParameterForTesting(this.getClass(), "CostShareProjectPeriodNameLabel", fooBar);
        newParm = costShareService.getCostShareLabel();
        assertEquals(fooBar, newParm);
        assertFalse(costShareService.validateProjectPeriodAsFiscalYear());
        assertFalse(costShareService.validateProjectPeriodAsProjectPeriod());
        
        //Switch it back
        updateParameterForTesting(this.getClass(), "CostShareProjectPeriodNameLabel", parameterForBackup);
    }

    @Test
    public void testGetParameterService() {
        assertNotNull(costShareServiceImpl.getParameterService());
    }

}
