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
        //set award on and off campus Benefits Rates to known record in Valid Rates Table.
        award.setSpecialEbRateOffCampus(new KualiDecimal(0.00));
        award.setSpecialEbRateOnCampus(new KualiDecimal(0.00));
        
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
     * Verify that businessObjectService is returning values.
     */
    @Test
    public void testGetValidRatesNotNull() {
        awardBenefitsRatesRule = new AwardBenefitsRatesRuleImpl();
        
        final Map<String, Object> FIELDVALUES = new HashMap<String, Object>();
        FIELDVALUES.put(ON_CAMPUS_RATE, award.getSpecialEbRateOnCampus());
        FIELDVALUES.put(OFF_CAMPUS_RATE, award.getSpecialEbRateOffCampus());
        
        final Collection<ValidRates> VALIDRATES = new ArrayList<ValidRates>();
        ValidRates validRate = new ValidRates();
        VALIDRATES.add(validRate);
        
        final BusinessObjectService MOCKEDBUSINESSOBJECTSERVICE = context.mock(BusinessObjectService.class);
        context.checking(new Expectations() {{
            one(MOCKEDBUSINESSOBJECTSERVICE).findMatching(ValidRates.class, FIELDVALUES); 
            will(returnValue(VALIDRATES));
        }});
        awardBenefitsRatesRule.setBusinessObjectService(MOCKEDBUSINESSOBJECTSERVICE);
        
        Assert.assertFalse(awardBenefitsRatesRule.getValidRates(this.award) == null);
    }
    
    /**
     * This tests for valid rates in Valid Rates Table
     */
    @Test
    public void testGetValidRates(){
        awardBenefitsRatesRule = new AwardBenefitsRatesRuleImpl();
        
        final Map<String, Object> FIELDVALUES = new HashMap<String, Object>();
        FIELDVALUES.put(ON_CAMPUS_RATE, award.getSpecialEbRateOnCampus());
        FIELDVALUES.put(OFF_CAMPUS_RATE, award.getSpecialEbRateOffCampus());
        
        final Collection<ValidRates> VALIDRATES = new ArrayList<ValidRates>();
        ValidRates validRate = new ValidRates();
        VALIDRATES.add(validRate);
        
        final BusinessObjectService MOCKEDBUSINESSOBJECTSERVICE = context.mock(BusinessObjectService.class);
        context.checking(new Expectations() {{
            one(MOCKEDBUSINESSOBJECTSERVICE).findMatching(ValidRates.class, FIELDVALUES); 
            will(returnValue(VALIDRATES));
        }});
        awardBenefitsRatesRule.setBusinessObjectService(MOCKEDBUSINESSOBJECTSERVICE);
        
        Assert.assertEquals(VALIDRATES, awardBenefitsRatesRule.getValidRates(award));
        
    }
    
    /**
     * This tests for failure of method getValidRates()
     */
    @Test
    public void testGetValidRatesFails(){
        awardBenefitsRatesRule = new AwardBenefitsRatesRuleImpl();
        
        final Map<String, Object> FIELDVALUES = new HashMap<String, Object>();
        FIELDVALUES.put(ON_CAMPUS_RATE, award.getSpecialEbRateOnCampus());
        FIELDVALUES.put(OFF_CAMPUS_RATE, award.getSpecialEbRateOffCampus());  
        
        final Collection<ValidRates> VALIDRATES = new ArrayList<ValidRates>();
        
        final BusinessObjectService MOCKEDBUSINESSOBJECTSERVICE = context.mock(BusinessObjectService.class);
        context.checking(new Expectations() {{
            one(MOCKEDBUSINESSOBJECTSERVICE).findMatching(ValidRates.class, FIELDVALUES); 
            will(returnValue(VALIDRATES));
        }});
        awardBenefitsRatesRule.setBusinessObjectService(MOCKEDBUSINESSOBJECTSERVICE);
        
        Assert.assertTrue(awardBenefitsRatesRule.getValidRates(award).size() == 0);
        
    }
    
    /**
     * This tests for valid rates in Valid Rates Table
     */
    @Test
    public void testCheckValidRateInValidRatesTable(){
        awardBenefitsRatesRule = new AwardBenefitsRatesRuleImpl();
        
        final Map<String, Object> FIELDVALUES = new HashMap<String, Object>();
        FIELDVALUES.put(ON_CAMPUS_RATE, award.getSpecialEbRateOnCampus());
        FIELDVALUES.put(OFF_CAMPUS_RATE, award.getSpecialEbRateOffCampus());
        
        final Collection<ValidRates> VALIDRATES = new ArrayList<ValidRates>();
        ValidRates validRate = new ValidRates();
        VALIDRATES.add(validRate);
        
        final BusinessObjectService MOCKEDBUSINESSOBJECTSERVICE = context.mock(BusinessObjectService.class);
        context.checking(new Expectations() {{
            one(MOCKEDBUSINESSOBJECTSERVICE).findMatching(ValidRates.class, FIELDVALUES); 
            will(returnValue(VALIDRATES));
        }});
        awardBenefitsRatesRule.setBusinessObjectService(MOCKEDBUSINESSOBJECTSERVICE);
        
        Assert.assertTrue(awardBenefitsRatesRule.checkValidRateInValidRatesTable(this.award));
    }
    
   
    
}
