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
import org.kuali.rice.core.api.config.ConfigurationException;

/**
 * This class tests ApprovedEquipmentActionHelper
 */
public class AwardApprovedEquipmentRuleHelperTest {
    private static final String INVALID_NUMBER_FORMAT = "$1,234.56";
    private static final double DELTA = 0.001;
    private static final String INSTITUTION_MINIMUM = "50.00";
    private static final String FEDERAL_MINIMUM = "100.00";
    private static final String FED_PARM = EquipmentCapitalizationMinimumLoader.FEDERAL_CAPITALIZATION_MIN_PARM_NAME;
    private static final String INST_PARM = EquipmentCapitalizationMinimumLoader.INSTITUTE_CAPITALIZATION_MIN_PARM_NAME;
    private EquipmentCapitalizationMinimumLoader helper;
    
    @Before
    public void setUp() {
        helper = new EquipmentCapitalizationMinimumLoader() {
            @Override
            protected String fetchParameterValue(String parmName) {
                return parmName.equals(FED_PARM) ? FEDERAL_MINIMUM : INSTITUTION_MINIMUM;
            }
        };
    }
    @After
    public void tearDown() {
        helper = null;
    }

    @Test(expected=ConfigurationException.class)
    public void testGettingValueFromParameter_FederalMinimumNull() {
        helper = new EquipmentCapitalizationMinimumLoader() {
            @Override
            protected String fetchParameterValue(String parmName) {
                return parmName.equals(FED_PARM) ? null : INSTITUTION_MINIMUM;
            }
        };
        helper.getValueFromParameter(FED_PARM);
    }
    
    @Test(expected=ConfigurationException.class)
    public void testGettingValueFromParameter_InstitutionMinimumNull() {
        helper = new EquipmentCapitalizationMinimumLoader() {
            @Override
            protected String fetchParameterValue(String parmName) {
                return parmName.equals(FED_PARM) ? FEDERAL_MINIMUM : null;
            }
        };
        helper.getValueFromParameter(INST_PARM);
    }
    
    @Test(expected=ConfigurationException.class)
    public void testGettingValueFromParameter_FederalMinimumNotNumber() {
        helper = new EquipmentCapitalizationMinimumLoader() {
            @Override
            protected String fetchParameterValue(String parmName) {
                return parmName.equals(FED_PARM) ? INVALID_NUMBER_FORMAT : INSTITUTION_MINIMUM;
            }
        };
        helper.getValueFromParameter(FED_PARM);
    }
    
    @Test(expected=ConfigurationException.class)
    public void testGettingValueFromParameter_InstitutionMinimumNotNumber() {
        helper = new EquipmentCapitalizationMinimumLoader() {
            @Override
            protected String fetchParameterValue(String parmName) {
                return parmName.equals(FED_PARM) ? FEDERAL_MINIMUM : INVALID_NUMBER_FORMAT;
            }
        };
        helper.getValueFromParameter(INST_PARM);
    }
    
    @Test
    public void testGettingValueFromParameter() {
        checkValuefromParm(FED_PARM, FEDERAL_MINIMUM);
        checkValuefromParm(INST_PARM, INSTITUTION_MINIMUM);        
    }
    
    private void checkValuefromParm(String parmName, String parmValue) {
        double value = helper.getValueFromParameter(parmName);
        Assert.assertEquals(Double.parseDouble(parmValue), value, DELTA);
    }
}
