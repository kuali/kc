/*
 * Copyright 2006-2009 The Kuali Foundation
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
package org.kuali.kra.budget.rules;

import static org.junit.matchers.JUnitMatchers.hasItem;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.bo.AbstractInstituteRate;
import org.kuali.kra.bo.InstituteLaRate;
import org.kuali.kra.bo.InstituteRate;
import org.kuali.kra.budget.rates.InstituteRateRateTypeRateClassRule;
import org.kuali.kra.budget.rates.InstituteRateRateTypeRateClassRuleImpl;
import org.kuali.kra.budget.rates.RateClass;
import org.kuali.kra.budget.rates.RateType;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.KualiConfigurationService;
import org.kuali.rice.kns.util.ErrorMap;
import org.kuali.rice.kns.util.GlobalVariables;

/**
 * Tests the {@link InstituteRateRateTypeRateClassRuleImpl InstituteRateRateTypeRateClassRuleImpl} class.
 */
public class InstituteRateRateTypeRateClassRuleTest {

    private Mockery context;
    
    /** sets up the Mockery. */
    @Before
    public void setupMockery() {
        this.context = new JUnit4Mockery();
    }
    
    /** sets a new error map. */
    @Before
    public void setupErrorMap() {
        GlobalVariables.setErrorMap(new ErrorMap());
    }
    
    /**
     * Tests that a NullPointerException occurs with a null BusinessObjectService.
     */
    @Test(expected = NullPointerException.class)
    public void testNullBusinessObjectService() {
        new InstituteRateRateTypeRateClassRuleImpl(null, this.context.mock(KualiConfigurationService.class));
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
            this.context.mock(KualiConfigurationService.class));
        rule.validateRateTypeAndRateClass(null);
    }
    
    /**
     * Tests that a IllegalArgumentException occurs with a unrecognized rate type.
     * @throws Throwable if bad happens
     */
    @Test(expected = IllegalArgumentException.class)
    public void testUnsupportedDocumentGetValidRateTypes() throws Throwable {
        InstituteRateRateTypeRateClassRule rule = new InstituteRateRateTypeRateClassRuleImpl(this.context.mock(BusinessObjectService.class),
            this.context.mock(KualiConfigurationService.class));
        
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
            this.context.mock(KualiConfigurationService.class));
        
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
            this.context.mock(KualiConfigurationService.class));
        
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
        
        final KualiConfigurationService cService = this.context.mock(KualiConfigurationService.class);
        
        InstituteRateRateTypeRateClassRule rule = new InstituteRateRateTypeRateClassRuleImpl(this.context.mock(BusinessObjectService.class),
            cService);
        
        Method m = rule.getClass().getDeclaredMethod("getValidRateClassTypes", AbstractInstituteRate.class);
        m.setAccessible(true);
        InstituteLaRate instituteLaRate = new InstituteLaRate();
        
        final Collection<String> validTypes = new ArrayList<String>();
        validTypes.add("foo");
        
        this.context.checking(new Expectations() {
            {
                oneOf(cService).getParameterValues("KRA-B", "A", "instituteLaRateClassTypes");
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
        
        final KualiConfigurationService cService = this.context.mock(KualiConfigurationService.class);
        
        InstituteRateRateTypeRateClassRule rule = new InstituteRateRateTypeRateClassRuleImpl(this.context.mock(BusinessObjectService.class),
            cService);
        
        Method m = rule.getClass().getDeclaredMethod("getValidRateClassTypes", AbstractInstituteRate.class);
        m.setAccessible(true);
        InstituteRate instituteRate = new InstituteRate();
        
        final Collection<String> validTypes = new ArrayList<String>();
        validTypes.add("foo");
        
        this.context.checking(new Expectations() {
            {
                oneOf(cService).getParameterValues("KRA-B", "A", "instituteRateClassTypes");
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
        final KualiConfigurationService cService = this.context.mock(KualiConfigurationService.class);
        final BusinessObjectService boService = this.context.mock(BusinessObjectService.class);
        
        InstituteRateRateTypeRateClassRule rule = new InstituteRateRateTypeRateClassRuleImpl(boService, cService);
        
        Method m = rule.getClass().getDeclaredMethod("checkCorrectRateType", AbstractInstituteRate.class);
        m.setAccessible(true);
        
        InstituteRate instituteRate = new InstituteRate();
        instituteRate.setRateTypeCode("foo");
        
        final Collection<String> validTypes = new ArrayList<String>();
        validTypes.add("abc");
        
        this.context.checking(new Expectations() {
            {
                oneOf(cService).getParameterValues("KRA-B", "A", "instituteRateClassTypes");
                will(returnValue(validTypes));
            }
            
            {
                Collection<RateType> rateTypes = new ArrayList<RateType>();
                {
                    RateType type = new RateType();
                    RateClass rClass = new RateClass();
                    rClass.setRateClassType("cdf");
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
        Assert.assertEquals("should be errors " + GlobalVariables.getErrorMap(),  1, GlobalVariables.getErrorMap().size());
        
        this.context.assertIsSatisfied();
    }
    
    /**
     * Tests a rate type code that is invalid for a rate type is properly flagged.
     * @throws Throwable if bad happens
     */
    @Test
    public void testInvalidRateClassForRateType() throws Throwable {
        final KualiConfigurationService cService = this.context.mock(KualiConfigurationService.class);
        final BusinessObjectService boService = this.context.mock(BusinessObjectService.class);
        
        InstituteRateRateTypeRateClassRule rule = new InstituteRateRateTypeRateClassRuleImpl(boService, cService);
        
        Method m = rule.getClass().getDeclaredMethod("checkCorrectRateClass", AbstractInstituteRate.class);
        m.setAccessible(true);
        
        InstituteRate instituteRate = new InstituteRate();
        instituteRate.setRateClassCode("foo");
        
        final Collection<String> validTypes = new ArrayList<String>();
        validTypes.add("abc");
        
        this.context.checking(new Expectations() {
            {
                oneOf(cService).getParameterValues("KRA-B", "A", "instituteRateClassTypes");
                will(returnValue(validTypes));
            }
            
            {
                Collection<RateType> rateTypes = new ArrayList<RateType>();
                {
                    RateType type = new RateType();
                    RateClass rClass = new RateClass();
                    rClass.setRateClassType("cdf");
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
        Assert.assertEquals("should be errors " + GlobalVariables.getErrorMap(),  1, GlobalVariables.getErrorMap().size());
        
        this.context.assertIsSatisfied();
    }
    
    /**
     * Tests a rate class that is valid for a rate type is not flagged.
     * @throws Throwable if bad happens
     */
    @Test
    public void testValidRateClassForRateType() throws Throwable {
        final KualiConfigurationService cService = this.context.mock(KualiConfigurationService.class);
        final BusinessObjectService boService = this.context.mock(BusinessObjectService.class);
        
        InstituteRateRateTypeRateClassRule rule = new InstituteRateRateTypeRateClassRuleImpl(boService, cService);
        
        Method m = rule.getClass().getDeclaredMethod("checkCorrectRateClass", AbstractInstituteRate.class);
        m.setAccessible(true);
        
        InstituteRate instituteRate = new InstituteRate();
        instituteRate.setRateClassCode("foo");
        
        final Collection<String> validTypes = new ArrayList<String>();
        validTypes.add("abc");
        
        this.context.checking(new Expectations() {
            {
                oneOf(cService).getParameterValues("KRA-B", "A", "instituteRateClassTypes");
                will(returnValue(validTypes));
            }
            
            {
                Collection<RateType> rateTypes = new ArrayList<RateType>();
                {
                    RateType type = new RateType();
                    RateClass rClass = new RateClass();
                    rClass.setRateClassType("abc");
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
        Assert.assertEquals("should be no errors " + GlobalVariables.getErrorMap(),  0, GlobalVariables.getErrorMap().size());
        
        this.context.assertIsSatisfied();
    }
    
    /**
     * Tests a rate type code that is valid for a rate type is not flagged.
     * @throws Throwable if bad happens
     */
    @Test
    public void testValidRateTypeForRateType() throws Throwable {
        final KualiConfigurationService cService = this.context.mock(KualiConfigurationService.class);
        final BusinessObjectService boService = this.context.mock(BusinessObjectService.class);
        
        InstituteRateRateTypeRateClassRule rule = new InstituteRateRateTypeRateClassRuleImpl(boService, cService);
        
        Method m = rule.getClass().getDeclaredMethod("checkCorrectRateType", AbstractInstituteRate.class);
        m.setAccessible(true);
        
        InstituteRate instituteRate = new InstituteRate();
        instituteRate.setRateTypeCode("foo");
        
        final Collection<String> validTypes = new ArrayList<String>();
        validTypes.add("abc");
        
        this.context.checking(new Expectations() {
            {
                oneOf(cService).getParameterValues("KRA-B", "A", "instituteRateClassTypes");
                will(returnValue(validTypes));
            }
            
            {
                Collection<RateType> rateTypes = new ArrayList<RateType>();
                {
                    RateType type = new RateType();
                    RateClass rClass = new RateClass();
                    rClass.setRateClassType("abc");
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
        Assert.assertEquals("should be no errors " + GlobalVariables.getErrorMap(),  0, GlobalVariables.getErrorMap().size());
        
        this.context.assertIsSatisfied();
    }
}
