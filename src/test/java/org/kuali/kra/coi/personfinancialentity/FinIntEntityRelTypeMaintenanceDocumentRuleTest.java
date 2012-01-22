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
package org.kuali.kra.coi.personfinancialentity;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.maintenance.MaintenanceRuleTestBase;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.krad.service.BusinessObjectService;

public class FinIntEntityRelTypeMaintenanceDocumentRuleTest extends MaintenanceRuleTestBase {
    
    private static final String REL_TYPE_SORT_ID_FIELD_NAME = "sortId";
    private static final String DESCRIPTION_FIELD_NAME = "description";
    
    private static final String CODE_1 = "1";
    private static final String CODE_2 = "2";
    private static final Integer SORT_ID_1 = 1;
    private static final String DESCRIPTION_1 = "Test1";
    private static final String DESCRIPTION_2 = "Test2";
       
    private FinIntEntityRelTypeMaintenanceDocumentRule rule;
    
    private Mockery context = new JUnit4Mockery();
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        
        rule = new FinIntEntityRelTypeMaintenanceDocumentRule();
        rule.setBusinessObjectService(getMockBusinessObjectService());
    }

    @After
    public void tearDown() throws Exception {
        rule = null;
        
        super.tearDown();
    }
    
    @Test
    public void testUniqueOK() throws Exception {
        MaintenanceDocument finIntEntityRelTypeMaintenanceDocument = getFinIntEntityRelTypeMaintenanceDocument(CODE_1, DESCRIPTION_1, SORT_ID_1);
        
        assertTrue(rule.processCustomRouteDocumentBusinessRules(finIntEntityRelTypeMaintenanceDocument));
        assertTrue(rule.processCustomApproveDocumentBusinessRules(finIntEntityRelTypeMaintenanceDocument));
    }
    
    @Test
    public void testUniqueNotOK() throws Exception {
        MaintenanceDocument finIntEntityRelTypeMaintenanceDocument = getFinIntEntityRelTypeMaintenanceDocument(CODE_2, DESCRIPTION_1, SORT_ID_1);
        
        assertFalse(rule.processCustomRouteDocumentBusinessRules(finIntEntityRelTypeMaintenanceDocument));
        assertFalse(rule.processCustomApproveDocumentBusinessRules(finIntEntityRelTypeMaintenanceDocument));
    }
    
    
    private MaintenanceDocument getFinIntEntityRelTypeMaintenanceDocument(String typeCode, String description, Integer sortId) throws Exception {
        FinIntEntityRelType finIntEntityRelType = new FinIntEntityRelType();
        finIntEntityRelType.setRelationshipTypeCode(typeCode);
        finIntEntityRelType.setDescription(description);
        finIntEntityRelType.setSortId(sortId);
        
        return newMaintDoc(finIntEntityRelType);
    }
    
    
    private BusinessObjectService getMockBusinessObjectService() {
        final BusinessObjectService service = context.mock(BusinessObjectService.class);
        
        context.checking(new Expectations() {{
            Map<String, Object> fieldValues1 = new HashMap<String, Object>();
            fieldValues1.put(REL_TYPE_SORT_ID_FIELD_NAME, SORT_ID_1);
 //           fieldValues1.put(GROUP_NAME_FIELD_NAME, GROUP_NAME);
            
            FinIntEntityRelType finIntEntityRelType = new FinIntEntityRelType();
            finIntEntityRelType.setRelationshipTypeCode(CODE_1);
            
            allowing(service).findMatching(FinIntEntityRelType.class, fieldValues1);
            will(returnValue(Collections.singleton(finIntEntityRelType)));
        }});
        
        context.checking(new Expectations() {{
            Map<String, String> fieldValues = new HashMap<String, String>();
            fieldValues.put(DESCRIPTION_FIELD_NAME, DESCRIPTION_1);
            FinIntEntityRelType finIntEntityRelType = new FinIntEntityRelType();
            finIntEntityRelType.setRelationshipTypeCode(CODE_1);
            
            allowing(service).findMatching(FinIntEntityRelType.class, fieldValues);
            will(returnValue(Collections.singleton(finIntEntityRelType)));
        }});
        
        return service;
    }

}
