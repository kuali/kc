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
package org.kuali.kra.proposaldevelopment.web;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang.math.NumberUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.bo.web.MaintenanceDocumentSeleniumHelper;
import org.kuali.kra.proposaldevelopment.bo.ActivityType;
import org.kuali.kra.test.infrastructure.KcSeleniumTestBase;
import org.kuali.rice.kns.document.MaintenanceDocumentBase;

/**
 * Tests the Activity Type maintenance document.
 */
public class ActivityTypeMaintenanceDocumentSeleniumTest extends KcSeleniumTestBase {
    
    private static final String DOCUMENT_TITLE = "Activity Type";
    private static final String MAINTENANCE_DOCUMENT_TITLE = "Kuali :: Activity Type Maintenance Document";
    
    private static final String ACTIVITY_TYPE_CODE_ID = "activityTypeCode";
    private static final String DESCRIPTION_ID = "description";
    private static final String HIGHER_EDUCATION_FUNCTION_CODE_ID = "higherEducationFunctionCode";
    
    private static final String CREATE_DOCUMENT_DESCRIPTION = "Activity Type - Test Create";
    private static final String CREATE_DESCRIPTION = "Test Create Activity Type";
    
    private static final String EDIT_DOCUMENT_DESCRIPTION = "Activity Type - Test Edit";
    private static final String EDIT_DESCRIPTION_1 = "Test Edit Activity Type (Before)";
    private static final String EDIT_DESCRIPTION_2 = "Test Edit Activity Type (After)";
    
    private static final String COPY_DOCUMENT_DESCRIPTION = "Activity Type - Test Copy";
    private static final String COPY_DESCRIPTION_1 = "Test Copy Activity Type (Before)";
    private static final String COPY_DESCRIPTION_2 = "Test Copy Activity Type (After)";
    
    private static final String DEFAULT_HIGHER_EDUCATION_FUNCTION_CODE = "IPR";
    
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
     * Test creating an activity type.
     *
     * @throws Exception
     */
    @Test
    public void testCreateActivityType() throws Exception {
        String activityTypeCode = getNewActivityTypeCode();
        String documentNumber = createNewMaintenanceDocument(CREATE_DOCUMENT_DESCRIPTION, activityTypeCode, CREATE_DESCRIPTION);
        
        verifyExistingMaintenanceDocument(documentNumber, activityTypeCode, CREATE_DESCRIPTION);
    }
    
    /**
     * Test editing an activity type.
     *
     * @throws Exception
     */
    @Test
    public void testEditActivityType() throws Exception {
        String activityTypeCode = getNewActivityTypeCode();
        createNewMaintenanceDocument(EDIT_DOCUMENT_DESCRIPTION, activityTypeCode, EDIT_DESCRIPTION_1);
        
        Map<String, String> searchValues = new LinkedHashMap<String, String>();
        searchValues.put(ACTIVITY_TYPE_CODE_ID, activityTypeCode);
        String documentNumber = helper.editMaintenanceDocument(DOCUMENT_TITLE, ActivityType.class.getName(), searchValues, MAINTENANCE_DOCUMENT_TITLE);
        
        Map<String, String> fieldValues = new LinkedHashMap<String, String>();
        fieldValues.put(DESCRIPTION_ID, EDIT_DESCRIPTION_2);
        helper.populateMaintenanceDocument(EDIT_DOCUMENT_DESCRIPTION, fieldValues);
        
        helper.routeDocument();
        helper.assertRoute();
        
        verifyExistingMaintenanceDocument(documentNumber, activityTypeCode, EDIT_DESCRIPTION_2);
    }

    /**
     * Test copying an activity type.
     *
     * @throws Exception
     */
    @Test
    public void testCopyActivityType() throws Exception {
        String activityTypeCode1 = getNewActivityTypeCode();
        createNewMaintenanceDocument(COPY_DOCUMENT_DESCRIPTION, activityTypeCode1, COPY_DESCRIPTION_1);
        
        Map<String, String> searchValues = new LinkedHashMap<String, String>();
        searchValues.put(ACTIVITY_TYPE_CODE_ID, activityTypeCode1);
        String documentNumber = helper.copyMaintenanceDocument(DOCUMENT_TITLE, ActivityType.class.getName(), searchValues, MAINTENANCE_DOCUMENT_TITLE);
        
        String activityTypeCode2 = getNewActivityTypeCode();
        Map<String, String> fieldValues = new LinkedHashMap<String, String>();
        fieldValues.put(ACTIVITY_TYPE_CODE_ID, activityTypeCode2);
        fieldValues.put(DESCRIPTION_ID, COPY_DESCRIPTION_2);
        helper.populateMaintenanceDocument(COPY_DOCUMENT_DESCRIPTION, fieldValues);
        
        helper.routeDocument();
        helper.assertRoute();
        
        verifyExistingMaintenanceDocument(documentNumber, activityTypeCode2, COPY_DESCRIPTION_2);
    }
    
    /**
     * Create a new maintenance document.
     * 
     * @param documentDescription the document description
     * @param activityTypeCode the activity type code
     * @param description the description
     * @return the document number of the new maintenance document
     */
    private String createNewMaintenanceDocument(String documentDescription, String activityTypeCode, String description) {
        String documentNumber = helper.createMaintenanceDocument(DOCUMENT_TITLE, ActivityType.class.getName(), MAINTENANCE_DOCUMENT_TITLE);
    
        Map<String, String> fieldValues = new LinkedHashMap<String, String>();
        fieldValues.put(ACTIVITY_TYPE_CODE_ID, activityTypeCode);
        fieldValues.put(DESCRIPTION_ID, description);
        fieldValues.put(HIGHER_EDUCATION_FUNCTION_CODE_ID, DEFAULT_HIGHER_EDUCATION_FUNCTION_CODE);
        helper.populateMaintenanceDocument(documentDescription, fieldValues);
        
        helper.routeDocument();
        helper.assertRoute();
        
        return documentNumber;
    }
    
    /**
     * Verify the details of an existing maintenance document.
     *
     * @param documentNumber the document of the maintenance document to verify
     * @param activityTypeCode the activity type code
     * @param description the description
     * @throws Exception
     */
    private void verifyExistingMaintenanceDocument(String documentNumber, String activityTypeCode, String description) throws Exception {
        MaintenanceDocumentBase document = (MaintenanceDocumentBase) getDocumentService().getByDocumentHeaderId(documentNumber);
        assertNotNull(document.getDocumentNumber());
        assertNotNull(document.getDocumentHeader());
        assertEquals(documentNumber, document.getDocumentHeader().getDocumentNumber());
        ActivityType activityType = (ActivityType) document.getNewMaintainableObject().getBusinessObject();
        assertEquals(activityTypeCode, activityType.getActivityTypeCode());
        assertEquals(description, activityType.getDescription());
    }
    
    /**
     * Create a new unique activity type code.
     * 
     * @return a new unique activity type code
     */
    @SuppressWarnings("unchecked")
    private String getNewActivityTypeCode() {
        int maxActivityTypeCode = 1;
        Collection<ActivityType> activityTypes = getBusinessObjectService().findAll(ActivityType.class);
        for (ActivityType activityType : activityTypes) {
            int activityTypeCode = NumberUtils.toInt(activityType.getActivityTypeCode(), 1);
            if (activityTypeCode > maxActivityTypeCode) {
                maxActivityTypeCode = activityTypeCode;
            }
        }
        
        return String.valueOf(maxActivityTypeCode + 1);
    }
    
}