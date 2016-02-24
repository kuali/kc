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
public class FinEntitiesDataMatrixMaintenanceDocumentRuleTest extends MaintenanceRuleTestBase {
    
    private static final String COLUMN_SORT_ID_FIELD_NAME = "columnSortId";
    private static final String GROUP_ID_FIELD_NAME = "dataGroupId";
    
    private static final Integer GROUP_ID_1 = 1;
    private static final Integer SORT_ID_1 = 1;
    private static final String COLUMN_NAME = "Test";
    private static final String COLUMN_NAME_1 = "Test1";
    
    private FinEntitiesDataMatrixMaintenanceDocumentRule rule;
    
    private Mockery context = new JUnit4Mockery() {{ setThreadingPolicy(new Synchroniser()); }};
    
    @Before
    public void setUp() throws Exception {

        rule = new FinEntitiesDataMatrixMaintenanceDocumentRule();
    }

    @After
    public void tearDown() throws Exception {
        rule = null;
        
    }
    
    @Test
    public void testUniqueOK() throws Exception {
        MaintenanceDocument finEntitiesDataMatrixMaintenanceDocument = getFinEntitiesDataMatrixMaintenanceDocument(COLUMN_NAME, SORT_ID_1, GROUP_ID_1);
        rule.setBusinessObjectService(getMockBusinessObjectService());
       
        assertTrue(rule.processCustomRouteDocumentBusinessRules(finEntitiesDataMatrixMaintenanceDocument));
        assertTrue(rule.processCustomApproveDocumentBusinessRules(finEntitiesDataMatrixMaintenanceDocument));
    }
    
    @Test
    public void testUniqueNotOK() throws Exception {
        rule.setBusinessObjectService(getMockBusinessObjectService1());
        MaintenanceDocument finEntitiesDataMatrixMaintenanceDocument = getFinEntitiesDataMatrixMaintenanceDocument(COLUMN_NAME_1, SORT_ID_1, GROUP_ID_1);
        
        assertFalse(rule.processCustomRouteDocumentBusinessRules(finEntitiesDataMatrixMaintenanceDocument));
        assertFalse(rule.processCustomApproveDocumentBusinessRules(finEntitiesDataMatrixMaintenanceDocument));
    }
    
    
    private MaintenanceDocument getFinEntitiesDataMatrixMaintenanceDocument(String columnName, Integer sortId, Integer groupId) throws Exception {
        FinEntitiesDataMatrix finEntitiesDataMatrix = new FinEntitiesDataMatrix();
        finEntitiesDataMatrix.setColumnName(columnName);
        finEntitiesDataMatrix.setColumnSortId(sortId);
        finEntitiesDataMatrix.setDataGroupId(groupId);
        
        return newMaintDoc(finEntitiesDataMatrix);
    }
    
    
    private BusinessObjectService getMockBusinessObjectService() {
        final BusinessObjectService service = context.mock(BusinessObjectService.class);
        
        context.checking(new Expectations() {{
            Map<String, Object> fieldValues1 = new HashMap<String, Object>();
            fieldValues1.put(COLUMN_SORT_ID_FIELD_NAME,  SORT_ID_1);
 //           fieldValues1.put(GROUP_NAME_FIELD_NAME, GROUP_NAME);
            
            FinEntitiesDataMatrix finEntitiesDataMatrix = new FinEntitiesDataMatrix();
            finEntitiesDataMatrix.setColumnName(COLUMN_NAME);
            finEntitiesDataMatrix.setDataGroupId(GROUP_ID_1);
            allowing(service).findMatching(FinEntitiesDataMatrix.class, fieldValues1);
            will(returnValue(Collections.singleton(finEntitiesDataMatrix)));
        }});
        
        context.checking(new Expectations() {{
            Map<String, Object> fieldValues = new HashMap<String, Object>();
            fieldValues.put(GROUP_ID_FIELD_NAME, GROUP_ID_1);
            
            allowing(service).countMatching(FinEntitiesDataGroup.class, fieldValues);
            will(returnValue(1));
        }});
        
        return service;
    }
    private BusinessObjectService getMockBusinessObjectService1() {
        final BusinessObjectService service = context.mock(BusinessObjectService.class);
        
        context.checking(new Expectations() {{
            Map<String, Object> fieldValues1 = new HashMap<String, Object>();
            fieldValues1.put(COLUMN_SORT_ID_FIELD_NAME,  SORT_ID_1);
 //           fieldValues1.put(GROUP_NAME_FIELD_NAME, GROUP_NAME);
            
            FinEntitiesDataMatrix finEntitiesDataMatrix = new FinEntitiesDataMatrix();
            finEntitiesDataMatrix.setColumnName(COLUMN_NAME);
            finEntitiesDataMatrix.setDataGroupId(GROUP_ID_1);
            allowing(service).findMatching(FinEntitiesDataMatrix.class, fieldValues1);
            will(returnValue(Collections.singleton(finEntitiesDataMatrix)));
        }});
        
        context.checking(new Expectations() {{
            Map<String, Object> fieldValues = new HashMap<String, Object>();
            fieldValues.put(GROUP_ID_FIELD_NAME, GROUP_ID_1);
            
            allowing(service).countMatching(FinEntitiesDataGroup.class, fieldValues);
            will(returnValue(0));
        }});
        
        return service;
    }

}
