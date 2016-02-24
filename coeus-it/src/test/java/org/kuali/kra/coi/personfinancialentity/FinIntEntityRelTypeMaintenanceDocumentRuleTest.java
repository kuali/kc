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
package org.kuali.kra.coi.personfinancialentity;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.maintenance.MaintenanceRuleTestBase;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import static org.junit.Assert.*;
public class FinIntEntityRelTypeMaintenanceDocumentRuleTest extends MaintenanceRuleTestBase {
    
    private static final String REL_TYPE_SORT_ID_FIELD_NAME = "sortId";
    private static final String DESCRIPTION_FIELD_NAME = "description";
    
    private static final String CODE_1 = "1";
    private static final String CODE_2 = "2";
    private static final Integer SORT_ID_1 = 1;
    private static final String DESCRIPTION_1 = "Test1";

    private FinIntEntityRelTypeMaintenanceDocumentRule rule;
    
    private Mockery context = new JUnit4Mockery() {{ setThreadingPolicy(new Synchroniser()); }};
    
    @Before
    public void setUp() throws Exception {

        rule = new FinIntEntityRelTypeMaintenanceDocumentRule();
        rule.setBusinessObjectService(getMockBusinessObjectService());
    }

    @After
    public void tearDown() throws Exception {
        rule = null;
        
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
