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
package org.kuali.kra.common.specialreview.lookup.keyvalue;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.framework.compliance.core.SpecialReviewTypeValuesFinder;
import org.kuali.kra.authorization.KraAuthorizationConstants;
import org.kuali.coeus.common.framework.compliance.core.SpecialReviewType;
import org.kuali.coeus.common.framework.compliance.core.SpecialReviewUsage;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.kim.api.permission.PermissionService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.KeyValuesService;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.*;
public class SpecialReviewTypeValuesFinderTest extends KcIntegrationTestBase {
    
    private static final String MODULE_CODE_NAME = "moduleCode";
    private static final String PERMISSION_NAME = "View Active Special Review Types";
    
    private static final String HUMAN_SUBJECTS_KEY = "1";
    private static final String ANIMAL_USAGE_KEY = "2";
    private static final String RECOMBINANT_DNA_KEY = "3";
    
    private static final String HUMAN_SUBJECTS_VALUE = "Human Subjects";
    private static final String ANIMAL_USAGE_VALUE = "Animal Usage";
    private static final String RECOMBINANT_DNA_VALUE = "Recombinant DNA";
    
    private Mockery context  = new JUnit4Mockery() {{ setThreadingPolicy(new Synchroniser()); }};
    
    private SpecialReviewTypeValuesFinder valuesFinder;
    
    @Before
    public void setUp() throws Exception {

        valuesFinder = new SpecialReviewTypeValuesFinder() {
            public String getModuleCode() {
                return Constants.EMPTY_STRING;
            }
        };
        valuesFinder.setKeyValuesService(getMockKeyValuesService());
        valuesFinder.setBusinessObjectService(getMockBusinessObjectService());
    }

    @After
    public void tearDown() throws Exception {
        valuesFinder = null;
        
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
