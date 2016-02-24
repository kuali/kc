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
