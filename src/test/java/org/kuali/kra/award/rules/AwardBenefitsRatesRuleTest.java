/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.award.rules;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kuali.core.service.BusinessObjectService;
import org.kuali.core.util.KualiDecimal;
import org.kuali.kra.award.bo.Award;
import org.kuali.kra.award.bo.ValidRates;

/**
 * This class tests Award Benefits Rates business rules.
 */
@RunWith(JMock.class)
public class AwardBenefitsRatesRuleTest {
    
    private static final String ON_CAMPUS_RATE = "onCampusRate";
    private static final String OFF_CAMPUS_RATE = "offCampusRate";
    
    final Map<String, Object> FIELD_VALUES = new HashMap<String, Object>();
    final Collection<ValidRates> NULL_VALID_RATES = new ArrayList<ValidRates>();
    final Collection<ValidRates> INITIALIZED_VALID_RATES = new ArrayList<ValidRates>();
    
    Award award;
    AwardBenefitsRatesRuleImpl awardBenefitsRatesRule;
    
    private Mockery context = new JUnit4Mockery();
    
    
    /**
     * This method...
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        award = new Award();
        setKnownBenefitsRates();
        FIELD_VALUES.put(ON_CAMPUS_RATE, award.getSpecialEbRateOnCampus());
        FIELD_VALUES.put(OFF_CAMPUS_RATE, award.getSpecialEbRateOffCampus());
        ValidRates validRate = new ValidRates();
        INITIALIZED_VALID_RATES.add(validRate);
    }
    
    /**
     * This method...
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        award = null;
        awardBenefitsRatesRule = null;
    }
    
    /**
     * This method sets award on and off campus Benefits Rates to known record in Valid Rates Table.
     */
    public void setKnownBenefitsRates() {
        award.setSpecialEbRateOffCampus(new KualiDecimal(0.00));
        award.setSpecialEbRateOnCampus(new KualiDecimal(0.00));
    }
    
    public BusinessObjectService getMockedBusinessObjectService(Collection<ValidRates> validRates) {
        final BusinessObjectService MOCKED_BUSINESS_OBJECT_SERVICE;
        final Collection<ValidRates> VALID_RATES = validRates;
        MOCKED_BUSINESS_OBJECT_SERVICE = context.mock(BusinessObjectService.class);
        context.checking(new Expectations() {{
            one(MOCKED_BUSINESS_OBJECT_SERVICE).findMatching(ValidRates.class, FIELD_VALUES); 
            will(returnValue(VALID_RATES));
        }});
        return MOCKED_BUSINESS_OBJECT_SERVICE;
    }
    
    /**
     * Verify that businessObjectService is returning values.
     */
    @Test
    public void testGetValidRatesNotNull() {
        awardBenefitsRatesRule = new AwardBenefitsRatesRuleImpl();
      
        final BusinessObjectService MOCKED_BUSINESS_OBJECT_SERVICE = 
                                            getMockedBusinessObjectService(INITIALIZED_VALID_RATES);
        awardBenefitsRatesRule.setBusinessObjectService(MOCKED_BUSINESS_OBJECT_SERVICE);
        
        Assert.assertFalse(awardBenefitsRatesRule.getValidRates(this.award) == null);
    }
    
    /**
     * This tests for valid rates in Valid Rates Table
     */
    @Test
    public void testGetValidRates(){
        awardBenefitsRatesRule = new AwardBenefitsRatesRuleImpl();
        
        final BusinessObjectService MOCKED_BUSINESS_OBJECT_SERVICE = 
                                        getMockedBusinessObjectService(INITIALIZED_VALID_RATES);
        awardBenefitsRatesRule.setBusinessObjectService(MOCKED_BUSINESS_OBJECT_SERVICE);
        
        Assert.assertEquals(INITIALIZED_VALID_RATES, awardBenefitsRatesRule.getValidRates(award));
        
    }
    
    /**
     * This tests for failure of method getValidRates()
     */
    @Test
    public void testGetValidRatesFails(){
        awardBenefitsRatesRule = new AwardBenefitsRatesRuleImpl();
        
        final BusinessObjectService MOCKED_BUSINESS_OBJECT_SERVICE = 
                                        getMockedBusinessObjectService(NULL_VALID_RATES);
        awardBenefitsRatesRule.setBusinessObjectService(MOCKED_BUSINESS_OBJECT_SERVICE);
        
        Assert.assertTrue(awardBenefitsRatesRule.getValidRates(award).size() == 0);
        
    }
    
    /**
     * This tests for valid rates in Valid Rates Table
     */
    @Test
    public void testCheckValidRateInValidRatesTable(){
        awardBenefitsRatesRule = new AwardBenefitsRatesRuleImpl();
        
        final BusinessObjectService MOCKED_BUSINESS_OBJECT_SERVICE = 
                                            getMockedBusinessObjectService(INITIALIZED_VALID_RATES);
        awardBenefitsRatesRule.setBusinessObjectService(MOCKED_BUSINESS_OBJECT_SERVICE);
        
        Assert.assertTrue(awardBenefitsRatesRule.checkValidRateInValidRatesTable(this.award));
    }
    
   
    
}
