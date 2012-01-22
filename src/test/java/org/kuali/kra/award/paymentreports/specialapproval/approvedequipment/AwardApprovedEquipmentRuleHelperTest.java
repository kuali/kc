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
