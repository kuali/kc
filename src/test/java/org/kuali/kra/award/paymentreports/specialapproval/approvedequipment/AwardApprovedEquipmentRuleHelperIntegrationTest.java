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
package org.kuali.kra.award.paymentreports.specialapproval.approvedequipment;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;

/**
 * This class tests ApprovedEquipmentActionHelper 
 */
public class AwardApprovedEquipmentRuleHelperIntegrationTest extends KcUnitTestBase {
    private static final double DELTA = 0.001;
    private static final double INSTITUTION_MINIMUM = 0.00;
    private static final double FEDERAL_MINIMUM = 0.00;
    private static final String FED_PARM = EquipmentCapitalizationMinimumLoader.FEDERAL_CAPITALIZATION_MIN_PARM_NAME;
    private static final String INST_PARM = EquipmentCapitalizationMinimumLoader.INSTITUTE_CAPITALIZATION_MIN_PARM_NAME;
    private EquipmentCapitalizationMinimumLoader helper;
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        helper = new EquipmentCapitalizationMinimumLoader();
    }
    @After
    public void tearDown() throws Exception {
        helper = null;
        super.tearDown();
    }
    
    @Test
    public void testGettingValueFromParameter() {
        checkValuefromParm(FED_PARM, FEDERAL_MINIMUM);
        checkValuefromParm(INST_PARM, INSTITUTION_MINIMUM);        
    }
    
    @Test
    public void testGettingFederalMinimum() {
        Assert.assertEquals(FEDERAL_MINIMUM, helper.getFederalCapitalizationMinimum(), DELTA);
    }
    
    @Test
    public void testGettingInstitutionMinimum() {
        Assert.assertEquals(INSTITUTION_MINIMUM, helper.getInstitutionCapitalizationMinimum(), DELTA);
    }
    
    private void checkValuefromParm(String parmName, double parmValue) {
        double value = helper.getValueFromParameter(parmName);
        Assert.assertEquals(parmValue, value, DELTA);
    }
}
