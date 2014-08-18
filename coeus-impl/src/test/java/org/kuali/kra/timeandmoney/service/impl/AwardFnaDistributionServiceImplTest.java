/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.timeandmoney.service.impl;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(JMock.class)
public class AwardFnaDistributionServiceImplTest {

    private static final String DISABLED = "D";
    private static final String MANDATORY = "M";
    private static final String OPTIONAL = "O";
    
    private AwardFnaDistributionServiceImpl awardFnaDistributionServiceImpl;
    private Mockery context = new JUnit4Mockery() {{ setThreadingPolicy(new Synchroniser()); }};
    
    @Before
    public void setUp() throws Exception {
        awardFnaDistributionServiceImpl = new AwardFnaDistributionServiceImpl();
    }

    @After
    public void tearDown() throws Exception {
        awardFnaDistributionServiceImpl = null;
    }
    
    private ParameterService getParameterService(final String parmVal) {
        final ParameterService parameterService = context.mock(ParameterService.class);
        context.checking(new Expectations() {{
            one(parameterService).getParameterValueAsString(AwardFnaDistributionServiceImpl.AWARD_FNA_DISTRIBUTION_NAMESPACE, 
                    AwardFnaDistributionServiceImpl.AWARD_FNA_DISTRIBUTION_COMPONENT, 
                    AwardFnaDistributionServiceImpl.AWARD_FNA_DISTRIBUTION_PARAMETER_NAME);will(returnValue(parmVal));
        }});
        context.checking(new Expectations() {{
            one(parameterService).getParameterValueAsString(AwardFnaDistributionServiceImpl.AWARD_FNA_DISTRIBUTION_NAMESPACE, 
                    AwardFnaDistributionServiceImpl.AWARD_FNA_DISTRIBUTION_COMPONENT, 
                    AwardFnaDistributionServiceImpl.AWARD_FNA_DISTRIBUTION_PARAMETER_NAME);will(returnValue(parmVal));
        }});
        context.checking(new Expectations() {{
            one(parameterService).getParameterValueAsString(AwardFnaDistributionServiceImpl.AWARD_FNA_DISTRIBUTION_NAMESPACE, 
                    AwardFnaDistributionServiceImpl.AWARD_FNA_DISTRIBUTION_COMPONENT, 
                    AwardFnaDistributionServiceImpl.AWARD_FNA_DISTRIBUTION_PARAMETER_NAME);will(returnValue(parmVal));
        }});
        return parameterService;
    }
    
    @Test
    public void testDisabledParm() {
        awardFnaDistributionServiceImpl.setParameterService(getParameterService(DISABLED));
        assertFalse(awardFnaDistributionServiceImpl.displayAwardFAndADistributionEqualityValidationAsWarning());
        assertFalse(awardFnaDistributionServiceImpl.displayAwardFAndADistributionEqualityValidationAsError());
        assertTrue(awardFnaDistributionServiceImpl.disableFAndADistributionEqualityValidation());
        assertTrue(awardFnaDistributionServiceImpl.isDisabled(DISABLED));
        assertFalse(awardFnaDistributionServiceImpl.isMandatory(DISABLED));
        assertFalse(awardFnaDistributionServiceImpl.isOptional(DISABLED));
    }
    
    @Test
    public void testMandatoryParm() {
        awardFnaDistributionServiceImpl.setParameterService(getParameterService(MANDATORY));
        assertFalse(awardFnaDistributionServiceImpl.displayAwardFAndADistributionEqualityValidationAsWarning());
        assertTrue(awardFnaDistributionServiceImpl.displayAwardFAndADistributionEqualityValidationAsError());
        assertFalse(awardFnaDistributionServiceImpl.disableFAndADistributionEqualityValidation());
        assertFalse(awardFnaDistributionServiceImpl.isDisabled(MANDATORY));
        assertTrue(awardFnaDistributionServiceImpl.isMandatory(MANDATORY));
        assertFalse(awardFnaDistributionServiceImpl.isOptional(MANDATORY));
    }
    
    @Test
    public void testOptionalParm() {
        awardFnaDistributionServiceImpl.setParameterService(getParameterService(OPTIONAL));
        assertTrue(awardFnaDistributionServiceImpl.displayAwardFAndADistributionEqualityValidationAsWarning());
        assertFalse(awardFnaDistributionServiceImpl.displayAwardFAndADistributionEqualityValidationAsError());
        assertFalse(awardFnaDistributionServiceImpl.disableFAndADistributionEqualityValidation());
        assertFalse(awardFnaDistributionServiceImpl.isDisabled(OPTIONAL));
        assertFalse(awardFnaDistributionServiceImpl.isMandatory(OPTIONAL));
        assertTrue(awardFnaDistributionServiceImpl.isOptional(OPTIONAL));
    }
}