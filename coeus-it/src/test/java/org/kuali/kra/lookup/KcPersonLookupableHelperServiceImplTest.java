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
package org.kuali.kra.lookup;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.impl.person.KcPersonLookupableHelperServiceImpl;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.multicampus.MultiCampusConstants;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kns.web.ui.Field;
import org.kuali.rice.kns.web.ui.Row;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.List;
import static org.junit.Assert.*;
public class KcPersonLookupableHelperServiceImplTest extends KcIntegrationTestBase {
    
    private static final int LOOKUP_CRITERIA_FIELD_COUNT = 9;
    private static final String CAMPUS_CODE_FIELD = "code";
    private static final String CAMPUS_LOOKUPABLE_CLASS_NAME = "org.kuali.rice.location.impl.campus.CampusBo";
    
    private static final String CAMPUS_CODE = "BL";
    
    private KcPersonLookupableHelperServiceImpl service;
    
    private Mockery context = new JUnit4Mockery() {{ setThreadingPolicy(new Synchroniser()); }};
    
    @Before
    public void setUp() throws Exception {

        service = new KcPersonLookupableHelperServiceImpl();
    }

    @After
    public void tearDown() throws Exception {

        service = null;
    }
    
    @Test
    public void testNonMultiCampusRows() {
        service.setBusinessObjectClass(KcPerson.class);
        service.setParameterService(getMockParameterService(false));
        GlobalVariables.getUserSession().addObject(MultiCampusConstants.USER_CAMPUS_CODE_KEY, (Object) CAMPUS_CODE);
        
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
        service.setBusinessObjectClass(KcPerson.class);
        service.setParameterService(getMockParameterService(true));
        GlobalVariables.getUserSession().addObject(MultiCampusConstants.USER_CAMPUS_CODE_KEY, (Object) CAMPUS_CODE);
        
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
                Constants.KC_GENERIC_PARAMETER_NAMESPACE, Constants.KC_ALL_PARAMETER_DETAIL_TYPE_CODE, MultiCampusConstants.PARAMETER_MULTI_CAMPUS_ENABLED);
            will(returnValue(multiCampusEnabled));
        }});
        
        return service;
    }

}