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
package org.kuali.kra.common.specialreview.lookup.keyvalue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.authorization.KraAuthorizationConstants;
import org.kuali.kra.bo.SpecialReviewType;
import org.kuali.kra.bo.SpecialReviewUsage;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.kim.api.permission.PermissionService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.KeyValuesService;
import org.kuali.rice.krad.util.GlobalVariables;

public class SpecialReviewTypeValuesFinderTest extends KcUnitTestBase {
    
    private static final String MODULE_CODE_NAME = "moduleCode";
    private static final String PERMISSION_NAME = "View Active Special Review Types";
    
    private static final String HUMAN_SUBJECTS_KEY = "1";
    private static final String ANIMAL_USAGE_KEY = "2";
    private static final String RECOMBINANT_DNA_KEY = "3";
    
    private static final String HUMAN_SUBJECTS_VALUE = "Human Subjects";
    private static final String ANIMAL_USAGE_VALUE = "Animal Usage";
    private static final String RECOMBINANT_DNA_VALUE = "Recombinant DNA";
    
    private Mockery context  = new JUnit4Mockery();
    
    private SpecialReviewTypeValuesFinder valuesFinder;
    
    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        
        valuesFinder = new SpecialReviewTypeValuesFinder() {
            public String getModuleCode() {
                return Constants.EMPTY_STRING;
            }
        };
        valuesFinder.setKeyValuesService(getMockKeyValuesService());
        valuesFinder.setBusinessObjectService(getMockBusinessObjectService());
    }

    @Override
    @After
    public void tearDown() throws Exception {
        valuesFinder = null;
        
        super.tearDown();
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testActiveSpecialReviewType() {
        valuesFinder.setPermissionService(getMockPermissionService(true));
        
        List<KeyValue> values = (List<KeyValue>) valuesFinder.getKeyValues();
        
        assertTrue(values.contains(new ConcreteKeyValue(HUMAN_SUBJECTS_KEY, HUMAN_SUBJECTS_VALUE)));
        assertTrue(values.contains(new ConcreteKeyValue(ANIMAL_USAGE_KEY, ANIMAL_USAGE_VALUE)));
        assertFalse(values.contains(new ConcreteKeyValue(RECOMBINANT_DNA_KEY, RECOMBINANT_DNA_VALUE)));
    }
    
    @Test
    @SuppressWarnings("unchecked")
    public void testGlobalSpecialReviewType() {
        valuesFinder.setPermissionService(getMockPermissionService(false));
        
        List<KeyValue> values = (List<KeyValue>) valuesFinder.getKeyValues();
        
        assertTrue(values.contains(new ConcreteKeyValue(HUMAN_SUBJECTS_KEY, HUMAN_SUBJECTS_VALUE)));
        assertFalse(values.contains(new ConcreteKeyValue(ANIMAL_USAGE_KEY, ANIMAL_USAGE_VALUE)));
        assertFalse(values.contains(new ConcreteKeyValue(RECOMBINANT_DNA_KEY, RECOMBINANT_DNA_VALUE)));
    }
    
    @Test
    @SuppressWarnings("unchecked")
    public void testHasPermissionToViewSpecialReviewType() {
        valuesFinder.setPermissionService(getMockPermissionService(true));
        
        List<KeyValue> values = (List<KeyValue>) valuesFinder.getKeyValues();
        
        assertTrue(values.contains(new ConcreteKeyValue(HUMAN_SUBJECTS_KEY, HUMAN_SUBJECTS_VALUE)));
        assertTrue(values.contains(new ConcreteKeyValue(ANIMAL_USAGE_KEY, ANIMAL_USAGE_VALUE)));
        assertFalse(values.contains(new ConcreteKeyValue(RECOMBINANT_DNA_KEY, RECOMBINANT_DNA_VALUE)));
    }
    
    @Test
    @SuppressWarnings("unchecked")
    public void testHasNoPermissionToViewSpecialReviewType() {
        valuesFinder.setPermissionService(getMockPermissionService(false));
        
        List<KeyValue> values = (List<KeyValue>) valuesFinder.getKeyValues();
        
        assertTrue(values.contains(new ConcreteKeyValue(HUMAN_SUBJECTS_KEY, HUMAN_SUBJECTS_VALUE)));
        assertFalse(values.contains(new ConcreteKeyValue(ANIMAL_USAGE_KEY, ANIMAL_USAGE_VALUE)));
        assertFalse(values.contains(new ConcreteKeyValue(RECOMBINANT_DNA_KEY, RECOMBINANT_DNA_VALUE)));
    }
    
    private KeyValuesService getMockKeyValuesService() {
        final KeyValuesService service = context.mock(KeyValuesService.class);
        
        context.checking(new Expectations() {{
            List<SpecialReviewType> specialReviewTypes = new ArrayList<SpecialReviewType>();
            
            SpecialReviewType humanSubjectsType = new SpecialReviewType();
            humanSubjectsType.setSpecialReviewTypeCode(HUMAN_SUBJECTS_KEY);
            humanSubjectsType.setDescription(HUMAN_SUBJECTS_VALUE);
            specialReviewTypes.add(humanSubjectsType);
            
            SpecialReviewType animalUsageType = new SpecialReviewType();
            animalUsageType.setSpecialReviewTypeCode(ANIMAL_USAGE_KEY);
            animalUsageType.setDescription(ANIMAL_USAGE_VALUE);
            specialReviewTypes.add(animalUsageType);
            
            SpecialReviewType recombinantDNAType = new SpecialReviewType();
            recombinantDNAType.setSpecialReviewTypeCode(RECOMBINANT_DNA_KEY);
            recombinantDNAType.setDescription(RECOMBINANT_DNA_VALUE);
            specialReviewTypes.add(recombinantDNAType);
            
            allowing(service).findAllOrderBy(SpecialReviewType.class, "sortId", true);
            will(returnValue(specialReviewTypes));
        }});
        
        return service;
    }
    
    private BusinessObjectService getMockBusinessObjectService() {
        final BusinessObjectService service = context.mock(BusinessObjectService.class);
        
        context.checking(new Expectations() {{
            Map<String, String> fieldValues = new HashMap<String, String>();
            fieldValues.put(MODULE_CODE_NAME, Constants.EMPTY_STRING);
            
            List<SpecialReviewUsage> specialReviewUsages = new ArrayList<SpecialReviewUsage>();
            
            SpecialReviewUsage humanSubjectsUsage = new SpecialReviewUsage();
            humanSubjectsUsage.setSpecialReviewTypeCode(HUMAN_SUBJECTS_KEY);
            humanSubjectsUsage.setGlobal(true);
            humanSubjectsUsage.setActive(true);
            specialReviewUsages.add(humanSubjectsUsage);
            
            SpecialReviewUsage animalUsageUsage = new SpecialReviewUsage();
            animalUsageUsage.setSpecialReviewTypeCode(ANIMAL_USAGE_KEY);
            animalUsageUsage.setGlobal(false);
            animalUsageUsage.setActive(true);
            specialReviewUsages.add(animalUsageUsage);
            
            SpecialReviewUsage recombinantDNAUsage = new SpecialReviewUsage();
            recombinantDNAUsage.setSpecialReviewTypeCode(RECOMBINANT_DNA_KEY);
            recombinantDNAUsage.setGlobal(false);
            recombinantDNAUsage.setActive(false);
            specialReviewUsages.add(recombinantDNAUsage);
            
            allowing(service).findMatching(SpecialReviewUsage.class, fieldValues);
            will(returnValue(specialReviewUsages));
        }});
        
        return service;
    }
    
    private PermissionService getMockPermissionService(final boolean canViewNonGlobalSpecialReviewTypes) {
        final PermissionService service = context.mock(PermissionService.class);
        
        context.checking(new Expectations() {{
            allowing(service).hasPermission(
                GlobalVariables.getUserSession().getPrincipalId(), KraAuthorizationConstants.KC_SYSTEM_NAMESPACE_CODE , PERMISSION_NAME);
            will(returnValue(canViewNonGlobalSpecialReviewTypes));
        }});
        
        return service;
    }

}