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
package org.kuali.kra.award.commitments;

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
import org.kuali.kra.award.bo.Award;
import org.kuali.kra.award.bo.ValidRates;
import org.kuali.kra.award.commitments.AwardBenefitsRatesRuleEvent;
import org.kuali.kra.award.commitments.AwardBenefitsRatesRuleImpl;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.ErrorMap;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.KualiDecimal;

/**
 * This class tests Award Benefits Rates business rules.
 */
@RunWith(JMock.class)
public class AwardBenefitsRatesRuleTest {
    
    private static final String ON_CAMPUS_RATE = "onCampusRate";
    private static final String OFF_CAMPUS_RATE = "offCampusRate";
    private static final String ERROR_PATH_KEY = "test error";
    
    final Map<String, Object> FIELD_VALUES = new HashMap<String, Object>();
    final Collection<ValidRates> NULL_VALID_RATES = new ArrayList<ValidRates>();
    final Collection<ValidRates> INITIALIZED_VALID_RATES = new ArrayList<ValidRates>();
    
    Award award;
    AwardBenefitsRatesRuleImpl awardBenefitsRatesRule;
    AwardBenefitsRatesRuleEvent awardBenefitsRatesRuleEvent;
    
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
        awardBenefitsRatesRuleEvent = new AwardBenefitsRatesRuleEvent(ERROR_PATH_KEY, award, null);
        GlobalVariables.setErrorMap(new ErrorMap());
        awardBenefitsRatesRule = new AwardBenefitsRatesRuleImpl();
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
    private void setKnownBenefitsRates() {
        award.setSpecialEbRateOffCampus(new KualiDecimal(0.00));
        award.setSpecialEbRateOnCampus(new KualiDecimal(0.00));
    }
    
    /**
     * This method returns the mocked business object service using validRates to find the matching Valid Rates objects.
     * @param validRates
     * @return
     */
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
     * Test rule returns true when given valid rates.
     */
    @Test
    public void testProcessBenefitsRatesBusinessRulesWithValidRate() {
      
        final BusinessObjectService MOCKED_BUSINESS_OBJECT_SERVICE = 
                                            getMockedBusinessObjectService(INITIALIZED_VALID_RATES);
        awardBenefitsRatesRule.setBusinessObjectService(MOCKED_BUSINESS_OBJECT_SERVICE);
        
        Assert.assertTrue
            (awardBenefitsRatesRule.processBenefitsRatesBusinessRules(awardBenefitsRatesRuleEvent));
    }
    
    /**
     * Test rule returns false when the rates are invalid.
     */
    @Test
    public void testProcessBenefitsRatesBusinessRulesWithInvalidRates(){
        
        final BusinessObjectService MOCKED_BUSINESS_OBJECT_SERVICE = 
                                        getMockedBusinessObjectService(NULL_VALID_RATES);
        awardBenefitsRatesRule.setBusinessObjectService(MOCKED_BUSINESS_OBJECT_SERVICE);
        Assert.assertFalse
            (awardBenefitsRatesRule.processBenefitsRatesBusinessRules(awardBenefitsRatesRuleEvent));   
    }
    
    
    /**
     * This tests that errors are being put in Global Error map when rates are invalid.
     */
    @Test
    public void testProcessBenefitsRatesBusinessRulesReportsError(){
        GlobalVariables.setErrorMap(new ErrorMap());
        
        final BusinessObjectService MOCKED_BUSINESS_OBJECT_SERVICE = 
                                            getMockedBusinessObjectService(NULL_VALID_RATES);
        awardBenefitsRatesRule.setBusinessObjectService(MOCKED_BUSINESS_OBJECT_SERVICE);
        
        awardBenefitsRatesRule.processBenefitsRatesBusinessRules(awardBenefitsRatesRuleEvent);
        Assert.assertFalse(GlobalVariables.getErrorMap().isEmpty());
    }
    
   
    
}
