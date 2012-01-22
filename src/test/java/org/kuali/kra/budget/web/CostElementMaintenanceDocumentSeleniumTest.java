/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.budget.web;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang.math.NumberUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.bo.web.MaintenanceDocumentSeleniumHelper;
import org.kuali.kra.budget.core.CostElement;
import org.kuali.kra.test.infrastructure.KcSeleniumTestBase;
import org.kuali.rice.kns.document.MaintenanceDocumentBase;

/**
 * Tests the Cost Element maintenance document.
 */
public class CostElementMaintenanceDocumentSeleniumTest extends KcSeleniumTestBase {

    private static final String DOCUMENT_TITLE = "Object Code";
    private static final String MAINTENANCE_DOCUMENT_TITLE = "Kuali :: Cost Element Maintenance Document";

    private static final String COST_ELEMENT_ID = "costElement";
    private static final String BUDGET_CATEGORY_CODE_ID = "budgetCategoryCode";
    private static final String DESCRIPTION_ID = "description";    
    private static final String ON_OFF_CAMPUS_FLAG_ID = "onOffCampusFlag";
    private static final String ACTIVE_FLAG_ID = "activeFlag";

    private static final String CREATE_DOCUMENT_DESCRIPTION = "Cost Element - Test Create";
    private static final String CREATE_DESCRIPTION = "Test Create Cost Element";
    private static final boolean CREATE_ON_OFF_CAMPUS_FLAG = false;
    
    private static final String EDIT_DOCUMENT_DESCRIPTION = "Cost Element - Test Edit";
    private static final String EDIT_DESCRIPTION_1 = "Test Edit Cost Element (Before)";
    private static final String EDIT_DESCRIPTION_2 = "Test Edit Cost Element (After)";
    private static final boolean EDIT_ON_OFF_CAMPUS_FLAG_1 = false;
    private static final boolean EDIT_ON_OFF_CAMPUS_FLAG_2 = true;
    
    private static final String COPY_DOCUMENT_DESCRIPTION = "Cost Element - Test Copy";
    private static final String COPY_DESCRIPTION_1 = "Test Copy Cost Element (Before)";
    private static final String COPY_DESCRIPTION_2 = "Test Copy Cost Element (After)";
    private static final boolean COPY_ON_OFF_CAMPUS_FLAG_1 = false;
    private static final boolean COPY_ON_OFF_CAMPUS_FLAG_2 = true;
    
    private static final String DEFAULT_BUDGET_CATEGORY_CODE = "3";
    private static final boolean DEFAULT_ACTIVE_FLAG = true;
    
    private MaintenanceDocumentSeleniumHelper helper;
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        
        helper = MaintenanceDocumentSeleniumHelper.instance(driver);
    }
    
    @After
    public void tearDown() throws Exception {
        helper = null;
        
        super.tearDown();
    }

    /**
     * Test creating a cost element.
     *
     * @throws Exception
     */
    @Test
    public void testCreateCostElement() throws Exception {
        String costElementCode = getNewCostElementCode();
        String documentNumber = createNewMaintenanceDocument(CREATE_DOCUMENT_DESCRIPTION, costElementCode, CREATE_DESCRIPTION, CREATE_ON_OFF_CAMPUS_FLAG);
        
        verifyExistingMaintenanceDocument(documentNumber, costElementCode, CREATE_DESCRIPTION, CREATE_ON_OFF_CAMPUS_FLAG);
    }

    /**
     * Test editing a cost element.
     *
     * @throws Exception
     */
    @Test
    public void testEditCostElement() throws Exception {
        String costElementCode = getNewCostElementCode();
        createNewMaintenanceDocument(EDIT_DOCUMENT_DESCRIPTION, costElementCode, EDIT_DESCRIPTION_1, EDIT_ON_OFF_CAMPUS_FLAG_1);
        
        Map<String, String> searchValues = new LinkedHashMap<String, String>();
        searchValues.put(COST_ELEMENT_ID, costElementCode);
        String documentNumber = helper.editMaintenanceDocument(DOCUMENT_TITLE, CostElement.class.getName(), searchValues, MAINTENANCE_DOCUMENT_TITLE);
        
        Map<String, String> fieldValues = new LinkedHashMap<String, String>();
        fieldValues.put(DESCRIPTION_ID, EDIT_DESCRIPTION_2);
        fieldValues.put(ON_OFF_CAMPUS_FLAG_ID, String.valueOf(EDIT_ON_OFF_CAMPUS_FLAG_2));
        helper.populateMaintenanceDocument(EDIT_DOCUMENT_DESCRIPTION, fieldValues);
                        
        helper.routeDocument();
        helper.assertRoute();
        
        verifyExistingMaintenanceDocument(documentNumber, costElementCode, EDIT_DESCRIPTION_2, EDIT_ON_OFF_CAMPUS_FLAG_2);

    }

    /**
     * Test copying a cost element.
     *
     * @throws Exception
     */
    @Test
    public void testCopyCostElement() throws Exception {
        String costElementCode1 = getNewCostElementCode();
        createNewMaintenanceDocument(COPY_DOCUMENT_DESCRIPTION, costElementCode1, COPY_DESCRIPTION_1, COPY_ON_OFF_CAMPUS_FLAG_1);
        
        Map<String, String> searchValues = new LinkedHashMap<String, String>();
        searchValues.put(COST_ELEMENT_ID, costElementCode1);
        String documentNumber = helper.copyMaintenanceDocument(DOCUMENT_TITLE, CostElement.class.getName(), searchValues, MAINTENANCE_DOCUMENT_TITLE);
        
        String costElementCode2 = getNewCostElementCode();
        Map<String, String> fieldValues = new LinkedHashMap<String, String>();
        fieldValues.put(COST_ELEMENT_ID, costElementCode2);
        fieldValues.put(DESCRIPTION_ID, COPY_DESCRIPTION_2);
        fieldValues.put(ON_OFF_CAMPUS_FLAG_ID, String.valueOf(COPY_ON_OFF_CAMPUS_FLAG_2));
        helper.populateMaintenanceDocument(COPY_DOCUMENT_DESCRIPTION, fieldValues);
                
        helper.routeDocument();
        helper.assertRoute();
        
        verifyExistingMaintenanceDocument(documentNumber, costElementCode2, COPY_DESCRIPTION_2, COPY_ON_OFF_CAMPUS_FLAG_2);
    }
    
    /**
     * Create a new maintenance document.
     * 
     * @param documentDescription the document description
     * @param costElementCode the cost element code
     * @param description the description
     * @param onOffCampusFlag the on/off campus flag
     * @return the document number of the new maintenance document
     */
    private String createNewMaintenanceDocument(String documentDescription, String costElementCode, String description, boolean onOffCampusFlag) {
        String documentNumber = helper.createMaintenanceDocument(DOCUMENT_TITLE, CostElement.class.getName(), MAINTENANCE_DOCUMENT_TITLE);
    
        Map<String, String> fieldValues = new LinkedHashMap<String, String>();
        fieldValues.put(COST_ELEMENT_ID, costElementCode);
        fieldValues.put(BUDGET_CATEGORY_CODE_ID, DEFAULT_BUDGET_CATEGORY_CODE);
        fieldValues.put(DESCRIPTION_ID, description);
        fieldValues.put(ON_OFF_CAMPUS_FLAG_ID, String.valueOf(onOffCampusFlag));
        fieldValues.put(ACTIVE_FLAG_ID, String.valueOf(DEFAULT_ACTIVE_FLAG));
        helper.populateMaintenanceDocument(documentDescription, fieldValues);
        
        helper.routeDocument();
        helper.assertRoute();
        
        return documentNumber;
    }
    
    /**
     * Verify the details of an existing maintenance document.
     *
     * @param documentNumber the document of the maintenance document to verify
     * @param costElementCode the cost element code
     * @param description the description
     * @param onOffCampusFlag the on/off campus flag
     * @throws Exception
     */
    private void verifyExistingMaintenanceDocument(String documentNumber, String costElementCode, String description, boolean onOffCampusFlag) throws Exception {
        MaintenanceDocumentBase document = (MaintenanceDocumentBase) getDocumentService().getByDocumentHeaderId(documentNumber);
        assertNotNull(document.getDocumentNumber());
        assertNotNull(document.getDocumentHeader());
        assertEquals(documentNumber, document.getDocumentHeader().getDocumentNumber());
        CostElement costElement = (CostElement) document.getNewMaintainableObject().getDataObject();
        assertEquals(costElementCode, costElement.getCostElement());
        assertEquals(DEFAULT_BUDGET_CATEGORY_CODE, costElement.getBudgetCategoryCode());
        assertEquals(description, costElement.getDescription());
        assertEquals(onOffCampusFlag, costElement.getOnOffCampusFlag());
        assertEquals(DEFAULT_ACTIVE_FLAG, costElement.getActiveFlag());
    }
    
    /**
     * Create a new unique cost element code.
     * 
     * @return a new unique cost element code
     */
    @SuppressWarnings("unchecked")
    private String getNewCostElementCode() {
        int maxCostElementCode = 1;
        Collection<CostElement> costElements = getBusinessObjectService().findAll(CostElement.class);
        for (CostElement costElement : costElements) {
            int costElementCode = NumberUtils.toInt(costElement.getCostElement(), 1);
            if (costElementCode > maxCostElementCode) {
                maxCostElementCode = costElementCode;
            }
        }
        
        return String.valueOf(maxCostElementCode + 1);
    }

}