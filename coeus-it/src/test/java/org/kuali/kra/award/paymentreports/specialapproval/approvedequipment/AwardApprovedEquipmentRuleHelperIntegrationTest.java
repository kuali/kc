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
package org.kuali.kra.award.paymentreports.specialapproval.approvedequipment;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;

/**
 * This class tests ApprovedEquipmentActionHelper 
 */
public class AwardApprovedEquipmentRuleHelperIntegrationTest extends KcIntegrationTestBase {
    private static final double DELTA = 0.001;
    private static final double INSTITUTION_MINIMUM = 0.00;
    private static final double FEDERAL_MINIMUM = 0.00;
    private static final String FED_PARM = EquipmentCapitalizationMinimumLoader.FEDERAL_CAPITALIZATION_MIN_PARM_NAME;
    private static final String INST_PARM = EquipmentCapitalizationMinimumLoader.INSTITUTE_CAPITALIZATION_MIN_PARM_NAME;
    private EquipmentCapitalizationMinimumLoader helper;
    
    @Before
    public void setUp() throws Exception {
        helper = new EquipmentCapitalizationMinimumLoader();
    }
    @After
    public void tearDown() throws Exception {
        helper = null;
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
