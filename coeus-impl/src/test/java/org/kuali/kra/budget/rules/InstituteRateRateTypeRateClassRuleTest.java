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
package org.kuali.kra.budget.rules;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.budget.framework.rate.AbstractInstituteRate;
import org.kuali.coeus.common.budget.framework.rate.InstituteLaRate;
import org.kuali.coeus.common.budget.framework.rate.InstituteRate;
import org.kuali.coeus.common.budget.impl.rate.InstituteRateRateTypeRateClassRule;
import org.kuali.coeus.common.budget.impl.rate.InstituteRateRateTypeRateClassRuleImpl;
import org.kuali.coeus.common.budget.framework.rate.RateClass;
import org.kuali.coeus.common.budget.framework.rate.RateType;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.MessageMap;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.junit.matchers.JUnitMatchers.hasItem;

/**
 * Tests the {@link InstituteRateRateTypeRateClassRuleImpl InstituteRateRateTypeRateClassRuleImpl} class.
 */
public class InstituteRateRateTypeRateClassRuleTest {

    private Mockery context;
    
    /** sets up the Mockery. */
    @Before
    public void setupMockery() {
        this.context = new JUnit4Mockery() {{ setThreadingPolicy(new Synchroniser()); }};
    }
    
    /** sets a new error map. */
    @Before
    public void setupErrorMap() {
        GlobalVariables.setMessageMap(new MessageMap());
    }
    
    /**
     * Tests that a NullPointerException occurs with a null BusinessObjectService.
     */
    @Test(expected = NullPointerException.class)
    public void testNullBusinessObjectService() {
        new InstituteRateRateTypeRateClassRuleImpl(null, this.context.mock(ParameterService.class));
    }
    
    /**
     * Tests that a NullPointerException occurs with a null ConfigService.
     */
    @Test(expected = NullPointerException.class)
    public void testNullConfigService() {
        new InstituteRateRateTypeRateClassRuleImpl(this.context.mock(BusinessObjectService.class), null);
    }
    
    /**
     * Tests that a NullPointerException occurs with a null Document.
     */
    @Test(expected = NullPointerException.class)
    public void testNullDocument() {
        InstituteRateRateTypeRateClassRule rule = new InstituteRateRateTypeRateClassRuleImpl(this.context.mock(BusinessObjectService.class),
            this.context.mock(ParameterService.class));
        rule.validateRateTypeAndRateClass(null);
    }
    
    /**
     * Tests that a IllegalArgumentException occurs with a unrecognized rate type.
     * @throws Throwable if bad happens
     */
    @Test(expected = IllegalArgumentException.class)
    public void testUnsupportedDocumentGetValidRateTypes() throws Throwable {
        InstituteRateRateTypeRateClassRule rule = new InstituteRateRateTypeRateClassRuleImpl(this.context.mock(BusinessObjectService.class),
            this.context.mock(ParameterService.class));
        
        Method m = rule.getClass().getDeclaredMethod("getValidRateClassTypes", AbstractInstituteRate.class);
        m.setAccessible(true);
        try {
            m.invoke(rule, new AbstractInstituteRate() { });
        } catch (InvocationTargetException e) {
            throw e.getCause();
        }
    }
    
    /**
     * Tests that the method returns valid if null rate class.
     * 
     * @throws Throwable if bad happens
     */
    @Test
    public void testNoRateClassCheckCorrectRateClass() throws Throwable{
        InstituteRateRateTypeRateClassRule rule = new InstituteRateRateTypeRateClassRuleImpl(this.context.mock(BusinessObjectService.class),
            this.context.mock(ParameterService.class));
        
        Method m = rule.getClass().getDeclaredMethod("checkCorrectRateClass", AbstractInstituteRate.class);
        m.setAccessible(true);
        
        InstituteRate instituteRate = new InstituteRate();
        instituteRate.setRateClassCode(null);
        
        Assert.assertTrue("Should have been valid", ((Boolean)m.invoke(rule, instituteRate)).booleanValue());

        this.context.assertIsSatisfied();
    }
    
    /**
     * Tests that the method returns valid if null rate type.
     * 
     * @throws Throwable if bad happens
     */
    @Test
    public void testNoRateTypeCheckCorrectRateType() throws Throwable {
        InstituteRateRateTypeRateClassRule rule = new InstituteRateRateTypeRateClassRuleImpl(this.context.mock(BusinessObjectService.class),
            this.context.mock(ParameterService.class));
        
        Method m = rule.getClass().getDeclaredMethod("checkCorrectRateType", AbstractInstituteRate.class);
        m.setAccessible(true);
        
        InstituteRate instituteRate = new InstituteRate();
        instituteRate.setRateTypeCode(null);
        
        Assert.assertTrue("Should have been valid", ((Boolean)m.invoke(rule, instituteRate)).booleanValue());
        
        this.context.assertIsSatisfied();
    }
    
    /**
     * Tests that an LA rate type document receives a Collection of valid rates for LA types 
     * @throws Throwable if bad happens
     */
    @Test
    public void testLaRatesValidTypes() throws Throwable {
        
        final ParameterService pService = this.context.mock(ParameterService.class);
        
        InstituteRateRateTypeRateClassRule rule = new InstituteRateRateTypeRateClassRuleImpl(this.context.mock(BusinessObjectService.class),
                pService);
        
        Method m = rule.getClass().getDeclaredMethod("getValidRateClassTypes", AbstractInstituteRate.class);
        m.setAccessible(true);
        InstituteLaRate instituteLaRate = new InstituteLaRate();
        
        final Collection<String> validTypes = new ArrayList<String>();
        validTypes.add("foo");
        
        this.context.checking(new Expectations() {
            {
                oneOf(pService).getParameterValuesAsString(Constants.MODULE_NAMESPACE_BUDGET, ParameterConstants.ALL_COMPONENT, "instituteLaRateClassTypes");
                will(returnValue(validTypes));
            }
        });
        
        @SuppressWarnings("unchecked")
        Collection<String> validRates = (Collection<String>) m.invoke(rule, instituteLaRate);

        Assert.assertThat(validRates, hasItem("foo"));
        
        this.context.assertIsSatisfied();
    }
    
    /**
     * Tests that an rate type document receives a Collection of valid rates for regular types 
     * @throws Throwable if bad happens
     */
    @Test
    public void testRatesValidTypes() throws Throwable {
        
        final ParameterService pService = this.context.mock(ParameterService.class);
        
        InstituteRateRateTypeRateClassRule rule = new InstituteRateRateTypeRateClassRuleImpl(this.context.mock(BusinessObjectService.class),
                pService);
        
        Method m = rule.getClass().getDeclaredMethod("getValidRateClassTypes", AbstractInstituteRate.class);
        m.setAccessible(true);
        InstituteRate instituteRate = new InstituteRate();
        
        final Collection<String> validTypes = new ArrayList<String>();
        validTypes.add("foo");
        
        this.context.checking(new Expectations() {
            {
                oneOf(pService).getParameterValuesAsString(Constants.MODULE_NAMESPACE_BUDGET, ParameterConstants.ALL_COMPONENT, "instituteRateClassTypes");
                will(returnValue(validTypes));
            }
        });
        
        @SuppressWarnings("unchecked")
        Collection<String> validRates = (Collection<String>) m.invoke(rule, instituteRate);

        Assert.assertThat(validRates, hasItem("foo"));
        
        this.context.assertIsSatisfied();
    }
    
    /**
     * Tests a rate class that is invalid for a rate type is properly flagged.
     * @throws Throwable if bad happens
     */
    @Test
    public void testInvalidRateTypeForRateType() throws Throwable {    
        final ParameterService pService = this.context.mock(ParameterService.class);
        final BusinessObjectService boService = this.context.mock(BusinessObjectService.class);
        
        InstituteRateRateTypeRateClassRule rule = new InstituteRateRateTypeRateClassRuleImpl(boService, pService);
        
        Method m = rule.getClass().getDeclaredMethod("checkCorrectRateType", AbstractInstituteRate.class);
        m.setAccessible(true);
        
        InstituteRate instituteRate = new InstituteRate();
        instituteRate.setRateTypeCode("foo");
        
        final Collection<String> validTypes = new ArrayList<String>();
        validTypes.add("abc");
        
        this.context.checking(new Expectations() {
            {
                oneOf(pService).getParameterValuesAsString(Constants.MODULE_NAMESPACE_BUDGET, ParameterConstants.ALL_COMPONENT, "instituteRateClassTypes");
                will(returnValue(validTypes));
            }
            
            {
                Collection<RateType> rateTypes = new ArrayList<RateType>();
                {
                    RateType type = new RateType();
                    RateClass rClass = new RateClass();
                    rClass.setRateClassTypeCode("cdf");
                    type.setRateClass(rClass);
                    rateTypes.add(type);
                }
                
                final Map<String, Object> fieldValues = new HashMap<String, Object>();
                fieldValues.put("rateTypeCode", "foo");
                
                oneOf(boService).findMatching(RateType.class, fieldValues);
                will(returnValue(rateTypes));
            }
            
        });
        
        Assert.assertFalse("Should not be valid", ((Boolean)m.invoke(rule, instituteRate)).booleanValue());
        Assert.assertEquals("should be errors " + GlobalVariables.getMessageMap(),  1, GlobalVariables.getMessageMap().getErrorMessages().size());
        
        this.context.assertIsSatisfied();
    }
    
    /**
     * Tests a rate type code that is invalid for a rate type is properly flagged.
     * @throws Throwable if bad happens
     */
    @Test
    public void testInvalidRateClassForRateType() throws Throwable {
        final ParameterService pService = this.context.mock(ParameterService.class);
        final BusinessObjectService boService = this.context.mock(BusinessObjectService.class);
        
        InstituteRateRateTypeRateClassRule rule = new InstituteRateRateTypeRateClassRuleImpl(boService, pService);
        
        Method m = rule.getClass().getDeclaredMethod("checkCorrectRateClass", AbstractInstituteRate.class);
        m.setAccessible(true);
        
        InstituteRate instituteRate = new InstituteRate();
        instituteRate.setRateClassCode("foo");
        
        final Collection<String> validTypes = new ArrayList<String>();
        validTypes.add("abc");
        
        this.context.checking(new Expectations() {
            {
                oneOf(pService).getParameterValuesAsString(Constants.MODULE_NAMESPACE_BUDGET, ParameterConstants.ALL_COMPONENT, "instituteRateClassTypes");
                will(returnValue(validTypes));
            }
            
            {
                Collection<RateType> rateTypes = new ArrayList<RateType>();
                {
                    RateType type = new RateType();
                    RateClass rClass = new RateClass();
                    rClass.setRateClassTypeCode("cdf");
                    type.setRateClass(rClass);
                    rateTypes.add(type);
                }
                
                final Map<String, Object> fieldValues = new HashMap<String, Object>();
                fieldValues.put("rateClassCode", "foo");
                
                oneOf(boService).findMatching(RateType.class, fieldValues);
                will(returnValue(rateTypes));
            }
            
        });
        
        Assert.assertFalse("Should not be valid", ((Boolean)m.invoke(rule, instituteRate)).booleanValue());
        Assert.assertEquals("should be errors " + GlobalVariables.getMessageMap(),  1, GlobalVariables.getMessageMap().getErrorMessages().size());
        
        this.context.assertIsSatisfied();
    }
    
    /**
     * Tests a rate class that is valid for a rate type is not flagged.
     * @throws Throwable if bad happens
     */
    @Test
    public void testValidRateClassForRateType() throws Throwable {
        final ParameterService pService = this.context.mock(ParameterService.class);
        final BusinessObjectService boService = this.context.mock(BusinessObjectService.class);
        
        InstituteRateRateTypeRateClassRule rule = new InstituteRateRateTypeRateClassRuleImpl(boService, pService);
        
        Method m = rule.getClass().getDeclaredMethod("checkCorrectRateClass", AbstractInstituteRate.class);
        m.setAccessible(true);
        
        InstituteRate instituteRate = new InstituteRate();
        instituteRate.setRateClassCode("foo");
        
        final Collection<String> validTypes = new ArrayList<String>();
        validTypes.add("abc");
        
        this.context.checking(new Expectations() {
            {
                oneOf(pService).getParameterValuesAsString(Constants.MODULE_NAMESPACE_BUDGET, ParameterConstants.ALL_COMPONENT, "instituteRateClassTypes");
                will(returnValue(validTypes));
            }
            
            {
                Collection<RateType> rateTypes = new ArrayList<RateType>();
                {
                    RateType type = new RateType();
                    RateClass rClass = new RateClass();
                    rClass.setRateClassTypeCode("abc");
                    type.setRateClass(rClass);
                    rateTypes.add(type);
                }
                
                final Map<String, Object> fieldValues = new HashMap<String, Object>();
                fieldValues.put("rateClassCode", "foo");
                
                oneOf(boService).findMatching(RateType.class, fieldValues);
                will(returnValue(rateTypes));
            }
            
        });
        
        Assert.assertTrue("Should not be valid", ((Boolean)m.invoke(rule, instituteRate)).booleanValue());
        Assert.assertEquals("should be no errors " + GlobalVariables.getMessageMap(),  0, GlobalVariables.getMessageMap().getErrorMessages().size());
        
        this.context.assertIsSatisfied();
    }
    
    /**
     * Tests a rate type code that is valid for a rate type is not flagged.
     * @throws Throwable if bad happens
     */
    @Test
    public void testValidRateTypeForRateType() throws Throwable {
        final ParameterService pService = this.context.mock(ParameterService.class);
        final BusinessObjectService boService = this.context.mock(BusinessObjectService.class);
        
        InstituteRateRateTypeRateClassRule rule = new InstituteRateRateTypeRateClassRuleImpl(boService, pService);
        
        Method m = rule.getClass().getDeclaredMethod("checkCorrectRateType", AbstractInstituteRate.class);
        m.setAccessible(true);
        
        InstituteRate instituteRate = new InstituteRate();
        instituteRate.setRateTypeCode("foo"); 
        
        final Collection<String> validTypes = new ArrayList<String>();
        validTypes.add("abc");
        
        this.context.checking(new Expectations() {
            {
                oneOf(pService).getParameterValuesAsString(Constants.MODULE_NAMESPACE_BUDGET, ParameterConstants.ALL_COMPONENT, "instituteRateClassTypes");
                will(returnValue(validTypes));
            }
            
            {
                Collection<RateType> rateTypes = new ArrayList<RateType>();
                {
                    RateType type = new RateType();
                    RateClass rClass = new RateClass();
                    rClass.setRateClassTypeCode("abc");
                    type.setRateClass(rClass);
                    rateTypes.add(type);
                }
                
                final Map<String, Object> fieldValues = new HashMap<String, Object>();
                fieldValues.put("rateTypeCode", "foo");
                
                oneOf(boService).findMatching(RateType.class, fieldValues);
                will(returnValue(rateTypes));
            }
            
        });
        
        Assert.assertTrue("Should not be valid", ((Boolean)m.invoke(rule, instituteRate)).booleanValue());
        Assert.assertEquals("should be no errors " + GlobalVariables.getMessageMap(),  0, GlobalVariables.getMessageMap().getErrorMessages().size());
        
        this.context.assertIsSatisfied();
    }
}
