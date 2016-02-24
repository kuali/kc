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
public class FinEntitiesDataGroupMaintenanceDocumentRuleTest extends MaintenanceRuleTestBase {
    
    private static final String GROUP_SORT_ID_FIELD_NAME = "dataGroupSortId";
    private static final String GROUP_NAME_FIELD_NAME = "dataGroupName";
    
    private static final Integer GROUP_ID_1 = 1;
    private static final Integer GROUP_ID_2 = 2;
    private static final Integer SORT_ID_1 = 1;
   private static final String GROUP_NAME = "Test";
    
    private FinEntitiesDataGroupMaintenanceDocumentRule rule;
    
    private Mockery context = new JUnit4Mockery() {{ setThreadingPolicy(new Synchroniser()); }};
    
    @Before
    public void setUp() throws Exception {

        rule = new FinEntitiesDataGroupMaintenanceDocumentRule();
        rule.setBusinessObjectService(getMockBusinessObjectService());
    }

    @After
    public void tearDown() throws Exception {
        rule = null;
        
    }
    
    @Test
    public void testUniqueOK() throws Exception {
        MaintenanceDocument finEntitiesDataGroupMaintenanceDocument = getFinEntitiesDataGroupMaintenanceDocument(GROUP_ID_1, GROUP_NAME, SORT_ID_1);
        
        assertTrue(rule.processCustomRouteDocumentBusinessRules(finEntitiesDataGroupMaintenanceDocument));
        assertTrue(rule.processCustomApproveDocumentBusinessRules(finEntitiesDataGroupMaintenanceDocument));
    }
    
    @Test
    public void testUniqueNotOK() throws Exception {
        MaintenanceDocument finEntitiesDataGroupMaintenanceDocument = getFinEntitiesDataGroupMaintenanceDocument(GROUP_ID_2, GROUP_NAME, SORT_ID_1);
        
        assertFalse(rule.processCustomRouteDocumentBusinessRules(finEntitiesDataGroupMaintenanceDocument));
        assertFalse(rule.processCustomApproveDocumentBusinessRules(finEntitiesDataGroupMaintenanceDocument));
    }
    
    
    private MaintenanceDocument getFinEntitiesDataGroupMaintenanceDocument(Integer finEntitiesDataGroupId, String groupName, Integer sortId) throws Exception {
        FinEntitiesDataGroup finEntitiesDataGroup = new FinEntitiesDataGroup();
        finEntitiesDataGroup.setDataGroupId(finEntitiesDataGroupId);
        finEntitiesDataGroup.setDataGroupName(groupName);
        finEntitiesDataGroup.setDataGroupSortId(sortId);
        
        return newMaintDoc(finEntitiesDataGroup);
    }
    
    
    private BusinessObjectService getMockBusinessObjectService() {
        final BusinessObjectService service = context.mock(BusinessObjectService.class);
        
        context.checking(new Expectations() {{
            Map<String, Object> fieldValues1 = new HashMap<String, Object>();
            fieldValues1.put(GROUP_SORT_ID_FIELD_NAME, SORT_ID_1);
 //           fieldValues1.put(GROUP_NAME_FIELD_NAME, GROUP_NAME);
            
            FinEntitiesDataGroup finEntitiesDataGroup = new FinEntitiesDataGroup();
            finEntitiesDataGroup.setDataGroupId(GROUP_ID_1);
            
            allowing(service).findMatching(FinEntitiesDataGroup.class, fieldValues1);
            will(returnValue(Collections.singleton(finEntitiesDataGroup)));
        }});
        
        context.checking(new Expectations() {{
            Map<String, String> fieldValues = new HashMap<String, String>();
            fieldValues.put(GROUP_NAME_FIELD_NAME, GROUP_NAME);
            FinEntitiesDataGroup finEntitiesDataGroup = new FinEntitiesDataGroup();
            finEntitiesDataGroup.setDataGroupId(GROUP_ID_1);
            
            allowing(service).findMatching(FinEntitiesDataGroup.class, fieldValues);
            will(returnValue(Collections.singleton(finEntitiesDataGroup)));
        }});
        
        return service;
    }

}
