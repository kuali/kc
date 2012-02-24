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
package org.kuali.kra.lookup;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kns.web.ui.Field;
import org.kuali.rice.kns.web.ui.Row;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.util.GlobalVariables;

public class UnitLookupableHelperServiceImplTest extends KcUnitTestBase {
    
    private static final int LOOKUP_CRITERIA_FIELD_COUNT = 6;
    private static final int SEARCH_RESULTS_NO_CAMPUS_CODE_COUNT = 13;
    private static final int SEARCH_RESULTS_CAMPUS_CODE_COUNT = 4;
    private static final String CAMPUS_CODE_FIELD = "code";
    private static final String CAMPUS_LOOKUPABLE_CLASS_NAME = "org.kuali.rice.location.impl.campus.CampusBo";
    
    private static final String CAMPUS_CODE = "BL";
    
    private UnitLookupableHelperServiceImpl service;
    
    private Mockery context = new JUnit4Mockery();
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        
        service = new UnitLookupableHelperServiceImpl();
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
        
        service = null;
    }
    
    @Test
    public void testNonMultiCampusRows() {
        service.setBusinessObjectClass(Unit.class);
        service.setParameterService(getMockParameterService(false));
        GlobalVariables.getUserSession().addObject(Constants.USER_CAMPUS_CODE_KEY, (Object) CAMPUS_CODE);
        
        List<Row> rows = service.getRows();
        assertEquals(LOOKUP_CRITERIA_FIELD_COUNT, rows.size());
        for (Row row : rows) {
            for (Field field : row.getFields()) {
                if (field.getPropertyName().equals(CAMPUS_CODE_FIELD)) {
                    assertFieldProperties(field, CAMPUS_CODE_FIELD, CAMPUS_LOOKUPABLE_CLASS_NAME);
                    assertEquals(Constants.EMPTY_STRING, field.getPropertyValue());
                }
            }
        }
    }
    
    @Test
    public void testMultiCampusRows() {
        service.setBusinessObjectClass(Unit.class);
        service.setParameterService(getMockParameterService(true));
        GlobalVariables.getUserSession().addObject(Constants.USER_CAMPUS_CODE_KEY, (Object) CAMPUS_CODE);
        
        List<Row> rows = service.getRows();
        assertEquals(LOOKUP_CRITERIA_FIELD_COUNT, rows.size());
        for (Row row : rows) {
            for (Field field : row.getFields()) {
                if (field.getPropertyName().equals(CAMPUS_CODE_FIELD)) {
                    assertFieldProperties(field, CAMPUS_CODE_FIELD, CAMPUS_LOOKUPABLE_CLASS_NAME);
                    assertEquals(CAMPUS_CODE, field.getPropertyValue());
                }
            }
        }
    }
    
    @Test
    public void testNoCampusCodeSearchResults() {
        service.setBusinessObjectClass(Unit.class);
        
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put(CAMPUS_CODE_FIELD, Constants.EMPTY_STRING);
        List<? extends BusinessObject> searchResults = service.getSearchResults(fieldValues);
        assertEquals(SEARCH_RESULTS_NO_CAMPUS_CODE_COUNT, searchResults.size());
    }
    
    @Test
    public void testCampusCodeSearchResults() {
        service.setBusinessObjectClass(Unit.class);
        
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put(CAMPUS_CODE_FIELD, CAMPUS_CODE);
        List<? extends BusinessObject> searchResults = service.getSearchResults(fieldValues);
        assertEquals(SEARCH_RESULTS_CAMPUS_CODE_COUNT, searchResults.size());
        
        for (BusinessObject searchResult : searchResults) {
            Unit unit = (Unit) searchResult;
            assertTrue(StringUtils.startsWith(unit.getUnitNumber(), CAMPUS_CODE));
        }
    }
    
    private void assertFieldProperties(Field field, String keyName, String className) {
        assertEquals(field.getFieldConversions(), keyName + Constants.COLON + field.getPropertyName());
        assertTrue(field.isFieldDirectInquiryEnabled());
        assertEquals(field.getLookupParameters(), field.getPropertyName() + Constants.COLON + keyName);
        assertEquals(field.getInquiryParameters(), field.getPropertyName() + Constants.COLON + keyName);
        assertEquals(field.getQuickFinderClassNameImpl(), className);
    }
    
    private ParameterService getMockParameterService(final boolean multiCampusEnabled) {
        final ParameterService service = context.mock(ParameterService.class);
        
        context.checking(new Expectations() {{
            allowing(service).getParameterValueAsBoolean(
                Constants.KC_GENERIC_PARAMETER_NAMESPACE, Constants.KC_ALL_PARAMETER_DETAIL_TYPE_CODE, Constants.PARAMETER_MULTI_CAMPUS_ENABLED);
            will(returnValue(multiCampusEnabled));
        }});
        
        return service;
    }

}