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
package org.kuali.kra.bo.web;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.bo.CustomAttribute;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.test.infrastructure.KcSeleniumTestBase;
import org.kuali.rice.kns.document.MaintenanceDocumentBase;

/**
 * Tests the Custom Attribute maintenance document.
 */
public class CustomAttributeMaintenanceDocumentSeleniumTest extends KcSeleniumTestBase {
    
    private static final String DOCUMENT_TITLE = "Custom Attribute";
    private static final String MAINTENANCE_DOCUMENT_TITLE = "Kuali :: CustomAttribute Maintenance Document";
    
    private static final String CUSTOM_ATTRIBUTE_ID_ID = "id";
    private static final String DATA_LENGTH_ID = "dataLength";
    private static final String DATA_TYPE_CODE_ID = "dataTypeCode";
    private static final String GROUP_NAME_ID = "groupName";
    private static final String LABEL_ID = "label";
    private static final String LOOKUP_CLASS_ID = "lookupClass";
    private static final String LOOKUP_RETURN_ID = "lookupReturn";
    private static final String NAME_ID = "name";
    
    private static final String CREATE_DOCUMENT_DESCRIPTION = "Custom Attribute - Test Create";
    private static final int CREATE_DATA_LENGTH = 30;
    
    private static final String EDIT_DOCUMENT_DESCRIPTION = "Custom Attribute - Test Edit";
    private static final int EDIT_DATA_LENGTH_1 = 30;
    private static final int EDIT_DATA_LENGTH_2 = 35;
    
    private static final String COPY_DOCUMENT_DESCRIPTION = "Custom Attribute - Test Copy";
    private static final int COPY_DATA_LENGTH_1 = 30;
    private static final int COPY_DATA_LENGTH_2 = 35;
    
    private static final String DEFAULT_DATA_TYPE_CODE = "1";
    private static final String DEFAULT_DATA_TYPE_CODE_NAME = "String";
    private static final String DEFAULT_GROUP_NAME = "Test Group";
    private static final String DEFAULT_LABEL = "Test 99";
    private static final Class<KcPerson> DEFAULT_LOOKUP_CLASS = KcPerson.class;
    private static final String DEFAULT_LOOKUP_RETURN = "userName";
    private static final String DEFAULT_LOOKUP_RETURN_NAME = "User Name";
    private static final String DEFAULT_NAME = "test99";

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
     * Test creating a custom attribute.
     *
     * @throws Exception
     */
    @Test
    public void testCreateCustomAttribute() throws Exception {
        int customAttributeId = getNewCustomAttributeId();
        String documentNumber = createNewMaintenanceDocument(CREATE_DOCUMENT_DESCRIPTION, customAttributeId, CREATE_DATA_LENGTH);
        
        verifyExistingMaintenanceDocument(documentNumber, customAttributeId, CREATE_DATA_LENGTH);
    }
    
    /**
     * Test editing a custom attribute.
     *
     * @throws Exception
     */
    @Test
    public void testEditCustomAttribute() throws Exception {
        int customAttributeId = getNewCustomAttributeId();
        createNewMaintenanceDocument(EDIT_DOCUMENT_DESCRIPTION, customAttributeId, EDIT_DATA_LENGTH_1);
        
        Map<String, String> searchValues = new LinkedHashMap<String, String>();
        searchValues.put(CUSTOM_ATTRIBUTE_ID_ID, String.valueOf(customAttributeId));
        String documentNumber = helper.editMaintenanceDocument(DOCUMENT_TITLE, CustomAttribute.class.getName(), searchValues, MAINTENANCE_DOCUMENT_TITLE);
        
        Map<String, String> fieldValues = new LinkedHashMap<String, String>();
        fieldValues.put(DATA_LENGTH_ID, String.valueOf(EDIT_DATA_LENGTH_2));
        helper.populateMaintenanceDocument(EDIT_DOCUMENT_DESCRIPTION, fieldValues);
        
        helper.routeDocument();
        helper.assertRoute();
        
        verifyExistingMaintenanceDocument(documentNumber, customAttributeId, EDIT_DATA_LENGTH_2);
    }

    /**
     * Test copying a custom attribute.
     *
     * @throws Exception
     */
    @Test
    public void testCopyCustomAttribute() throws Exception {
        int customAttributeId1 = getNewCustomAttributeId();
        createNewMaintenanceDocument(COPY_DOCUMENT_DESCRIPTION, customAttributeId1, COPY_DATA_LENGTH_1);
        
        Map<String, String> searchValues = new LinkedHashMap<String, String>();
        searchValues.put(CUSTOM_ATTRIBUTE_ID_ID, String.valueOf(customAttributeId1));
        String documentNumber = helper.copyMaintenanceDocument(DOCUMENT_TITLE, CustomAttribute.class.getName(), searchValues, MAINTENANCE_DOCUMENT_TITLE);
        
        int customAttributeId2 = getNewCustomAttributeId();
        Map<String, String> fieldValues = new LinkedHashMap<String, String>();
        fieldValues.put(CUSTOM_ATTRIBUTE_ID_ID, String.valueOf(customAttributeId2));
        fieldValues.put(DATA_LENGTH_ID, String.valueOf(COPY_DATA_LENGTH_2));
        fieldValues.put(DATA_TYPE_CODE_ID, DEFAULT_DATA_TYPE_CODE_NAME);
        fieldValues.put(GROUP_NAME_ID, DEFAULT_GROUP_NAME);
        fieldValues.put(LABEL_ID, DEFAULT_LABEL);
        fieldValues.put(LOOKUP_CLASS_ID, DEFAULT_LOOKUP_CLASS.getSimpleName());
        fieldValues.put(LOOKUP_RETURN_ID, DEFAULT_LOOKUP_RETURN_NAME);
        fieldValues.put(NAME_ID, DEFAULT_NAME);
        helper.populateMaintenanceDocument(COPY_DOCUMENT_DESCRIPTION, fieldValues);
        
        helper.routeDocument();
        helper.assertRoute();
        
        verifyExistingMaintenanceDocument(documentNumber, customAttributeId2, COPY_DATA_LENGTH_2);
    }
    
    /**
     * Create a new maintenance document.
     * 
     * @param documentDescription the document description
     * @param customAttributeId the custom attribute id
     * @param dataLength the data length
     * @return the document number of the new maintenance document
     */
    private String createNewMaintenanceDocument(String documentDescription, int customAttributeId, Integer dataLength) {
        String documentNumber = helper.createMaintenanceDocument(DOCUMENT_TITLE, CustomAttribute.class.getName(), MAINTENANCE_DOCUMENT_TITLE);
    
        Map<String, String> fieldValues = new LinkedHashMap<String, String>();
        fieldValues.put(CUSTOM_ATTRIBUTE_ID_ID, String.valueOf(customAttributeId));
        fieldValues.put(DATA_LENGTH_ID, String.valueOf(dataLength));
        fieldValues.put(DATA_TYPE_CODE_ID, DEFAULT_DATA_TYPE_CODE_NAME);
        fieldValues.put(GROUP_NAME_ID, DEFAULT_GROUP_NAME);
        fieldValues.put(LABEL_ID, DEFAULT_LABEL);
        fieldValues.put(LOOKUP_CLASS_ID, DEFAULT_LOOKUP_CLASS.getSimpleName());
        fieldValues.put(LOOKUP_RETURN_ID, DEFAULT_LOOKUP_RETURN_NAME);
        fieldValues.put(NAME_ID, DEFAULT_NAME);
        helper.populateMaintenanceDocument(documentDescription, fieldValues);
        
        helper.routeDocument();
        helper.assertRoute();
        
        return documentNumber;
    }
    
    /**
     * Verify the details of an existing maintenance document.
     *
     * @param documentNumber the document of the maintenance document to verify
     * @param customAttributeId the custom attribute id
     * @param dataLength the data length
     * @throws Exception
     */
    private void verifyExistingMaintenanceDocument(String documentNumber, int customAttributeId, Integer dataLength) throws Exception {
        MaintenanceDocumentBase document = (MaintenanceDocumentBase) getDocumentService().getByDocumentHeaderId(documentNumber);
        assertNotNull(document.getDocumentNumber());
        assertNotNull(document.getDocumentHeader());
        assertEquals(documentNumber, document.getDocumentHeader().getDocumentNumber());
        CustomAttribute customAttribute = (CustomAttribute) document.getNewMaintainableObject().getDataObject();
        assertEquals(Integer.valueOf(customAttributeId), customAttribute.getId());
        assertEquals(dataLength, customAttribute.getDataLength());
        assertEquals(DEFAULT_DATA_TYPE_CODE, customAttribute.getDataTypeCode());
        assertEquals(DEFAULT_GROUP_NAME, customAttribute.getGroupName());
        assertEquals(DEFAULT_LABEL, customAttribute.getLabel());
        assertEquals(DEFAULT_LOOKUP_CLASS.getName(), customAttribute.getLookupClass());
        assertEquals(DEFAULT_LOOKUP_RETURN, customAttribute.getLookupReturn());
        assertEquals(DEFAULT_NAME, customAttribute.getName());
    }
    
    /**
     * Create a new unique custom attribute id.
     * 
     * @return a new unique custom attribute id
     */
    @SuppressWarnings("unchecked")
    private int getNewCustomAttributeId() {
        int maxCustomAttributeId = 1;
        Collection<CustomAttribute> customAttributes = getBusinessObjectService().findAll(CustomAttribute.class);
        for (CustomAttribute customAttribute : customAttributes) {
            if (customAttribute.getId() > maxCustomAttributeId) {
                maxCustomAttributeId = customAttribute.getId();
            }
        }
        
        return maxCustomAttributeId + 1;
    }

}