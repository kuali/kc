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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.bo.web.MaintenanceDocumentSeleniumHelper;
import org.kuali.kra.budget.calculator.ValidCalcType;
import org.kuali.kra.test.infrastructure.KcSeleniumTestBase;
import org.kuali.rice.kns.document.MaintenanceDocumentBase;

/**
 * Tests the Valid Calc Type maintenance document.
 */
public class ValidCalcTypeMaintenanceDocumentSeleniumTest extends KcSeleniumTestBase {
    
    private static final String DOCUMENT_TITLE = "Valid Calculation Type";
    private static final String MAINTENANCE_DOCUMENT_TITLE = "Kuali :: Valid Calc Types Maintenance Document"; 

    private static final String CALC_TYPE_ID_ID = "calcTypeId";
    private static final String DEPENDENT_SEQ_NUMBER_ID = "dependentSeqNumber";
    private static final String RATE_CLASS_TYPE_ID = "rateClassType";
    private static final String DEPENDENT_RATE_CLASS_TYPE_ID = "dependentRateClassType";
    private static final String RATE_CLASS_CODE_ID = "rateClassCode";
    private static final String RATE_TYPE_CODE_ID = "rateTypeCode";
    
    private static final String CREATE_DOCUMENT_DESCRIPTION = "Valid Calculation Type - Test Create";
    private static final String CREATE_DEPENDENT_RATE_CLASS_TYPE = "X";
    private static final String CREATE_RATE_CLASS_CODE = "12";
    private static final String CREATE_RATE_TYPE_CODE = "1";
    
    private static final String EDIT_DOCUMENT_DESCRIPTION = "Valid Calculation Type - Test Edit";
    private static final String EDIT_DEPENDENT_RATE_CLASS_TYPE_1 = "X";
    private static final String EDIT_DEPENDENT_RATE_CLASS_TYPE_2 = "Y";
    private static final String EDIT_RATE_CLASS_CODE_1 = "12";
    private static final String EDIT_RATE_CLASS_CODE_2 = "5";
    private static final String EDIT_RATE_TYPE_CODE_1 = "1";
    private static final String EDIT_RATE_TYPE_CODE_2 = "3";
    
    private static final String COPY_DOCUMENT_DESCRIPTION = "Valid Calculation Type - Test Copy";
    private static final String COPY_DEPENDENT_RATE_CLASS_TYPE_1 = "X";
    private static final String COPY_DEPENDENT_RATE_CLASS_TYPE_2 = "Y";
    private static final String COPY_RATE_CLASS_CODE_1 = "12";
    private static final String COPY_RATE_CLASS_CODE_2 = "5";
    private static final String COPY_RATE_TYPE_CODE_1 = "1";
    private static final String COPY_RATE_TYPE_CODE_2 = "3";
    
    private static final int DEFAULT_DEPENDENT_SEQ_NUMBER = 1;
    private static final String DEFAULT_RATE_CLASS_TYPE = "E";
    
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
     * Test creating a valid calc type.
     *
     * @throws Exception
     */
    @Test
    public void testCreateValidCalcType() throws Exception {
        String calcTypeId = getNewCalcTypeId();
        String documentNumber = createNewMaintenanceDocument(CREATE_DOCUMENT_DESCRIPTION, calcTypeId, CREATE_DEPENDENT_RATE_CLASS_TYPE, CREATE_RATE_CLASS_CODE, CREATE_RATE_TYPE_CODE);
        
        verifyExistingMaintenanceDocument(documentNumber, calcTypeId, CREATE_DEPENDENT_RATE_CLASS_TYPE, CREATE_RATE_CLASS_CODE, CREATE_RATE_TYPE_CODE);
    }

    /**
     * Test editing a valid calc type.
     *
     * @throws Exception
     */
    @Test
    public void testEditValidCalcType() throws Exception {
        String calcTypeId = getNewCalcTypeId();
        createNewMaintenanceDocument(EDIT_DOCUMENT_DESCRIPTION, calcTypeId, EDIT_DEPENDENT_RATE_CLASS_TYPE_1, EDIT_RATE_CLASS_CODE_1, EDIT_RATE_TYPE_CODE_1);
        
        Map<String, String> searchValues = new LinkedHashMap<String, String>();
        searchValues.put(CALC_TYPE_ID_ID, calcTypeId);
        String documentNumber = helper.editMaintenanceDocument(DOCUMENT_TITLE, ValidCalcType.class.getName(), searchValues, MAINTENANCE_DOCUMENT_TITLE);
        
        Map<String, String> fieldValues = new LinkedHashMap<String, String>();
        fieldValues.put(DEPENDENT_RATE_CLASS_TYPE_ID, EDIT_DEPENDENT_RATE_CLASS_TYPE_2);
        fieldValues.put(RATE_CLASS_CODE_ID, EDIT_RATE_CLASS_CODE_2);
        fieldValues.put(RATE_TYPE_CODE_ID, EDIT_RATE_TYPE_CODE_2);
        helper.populateMaintenanceDocument(EDIT_DOCUMENT_DESCRIPTION, fieldValues);
        
        helper.routeDocument();
        helper.assertRoute();
        
        verifyExistingMaintenanceDocument(documentNumber, calcTypeId, EDIT_DEPENDENT_RATE_CLASS_TYPE_2, EDIT_RATE_CLASS_CODE_2, EDIT_RATE_TYPE_CODE_2);
    }

    /**
     * Test copying a valid calc type.
     *
     * @throws Exception
     */
    @Test
    public void testCopyValidCalcType() throws Exception {
        String calcTypeId1 = getNewCalcTypeId();
        createNewMaintenanceDocument(COPY_DOCUMENT_DESCRIPTION, calcTypeId1, COPY_DEPENDENT_RATE_CLASS_TYPE_1, COPY_RATE_CLASS_CODE_1, COPY_RATE_TYPE_CODE_1);
        
        Map<String, String> searchValues = new LinkedHashMap<String, String>();
        searchValues.put(CALC_TYPE_ID_ID, calcTypeId1);
        String documentNumber = helper.copyMaintenanceDocument(DOCUMENT_TITLE, ValidCalcType.class.getName(), searchValues, MAINTENANCE_DOCUMENT_TITLE);
        
        String calcTypeId2 = getNewCalcTypeId();
        Map<String, String> fieldValues = new LinkedHashMap<String, String>();
        fieldValues.put(CALC_TYPE_ID_ID, calcTypeId2);
        fieldValues.put(DEPENDENT_SEQ_NUMBER_ID, String.valueOf(DEFAULT_DEPENDENT_SEQ_NUMBER));
        fieldValues.put(RATE_CLASS_TYPE_ID, DEFAULT_RATE_CLASS_TYPE);
        fieldValues.put(DEPENDENT_RATE_CLASS_TYPE_ID, COPY_DEPENDENT_RATE_CLASS_TYPE_2);
        fieldValues.put(RATE_CLASS_CODE_ID, COPY_RATE_CLASS_CODE_2);
        fieldValues.put(RATE_TYPE_CODE_ID, COPY_RATE_TYPE_CODE_2);
        helper.populateMaintenanceDocument(COPY_DOCUMENT_DESCRIPTION, fieldValues);
        
        helper.routeDocument();
        helper.assertRoute();
        
        verifyExistingMaintenanceDocument(documentNumber, calcTypeId2, COPY_DEPENDENT_RATE_CLASS_TYPE_2, COPY_RATE_CLASS_CODE_2, COPY_RATE_TYPE_CODE_2);
    }
    
    /**
     * Create a new maintenance document.
     * 
     * @param documentDescription the document description
     * @param calcTypeId the calc type id
     * @param dependentRateClassType the dependent rate class type
     * @param rateClassCode the rate class code
     * @param rateTypeCode the rate type code
     * @return the document number of the new maintenance document
     */
    private String createNewMaintenanceDocument(String documentDescription, String calcTypeId, String dependentRateClassType, String rateClassCode, String rateTypeCode) {
        String documentNumber = helper.createMaintenanceDocument(DOCUMENT_TITLE, ValidCalcType.class.getName(), MAINTENANCE_DOCUMENT_TITLE);
    
        Map<String, String> fieldValues = new LinkedHashMap<String, String>();
        fieldValues.put(CALC_TYPE_ID_ID, calcTypeId);
        fieldValues.put(DEPENDENT_SEQ_NUMBER_ID, String.valueOf(DEFAULT_DEPENDENT_SEQ_NUMBER));
        fieldValues.put(RATE_CLASS_TYPE_ID, DEFAULT_RATE_CLASS_TYPE);
        fieldValues.put(DEPENDENT_RATE_CLASS_TYPE_ID, dependentRateClassType);
        fieldValues.put(RATE_CLASS_CODE_ID, rateClassCode);
        fieldValues.put(RATE_TYPE_CODE_ID, rateTypeCode);
        helper.populateMaintenanceDocument(documentDescription, fieldValues);
        
        helper.routeDocument();
        helper.assertRoute();
        
        return documentNumber;
    }
    
    /**
     * Verify the details of an existing maintenance document.
     *
     * @param documentNumber the document of the maintenance document to verify
     * @param calcTypeId the calc type id
     * @param dependentRateClassType the dependent rate class type
     * @param rateClassCode the rate class code
     * @param rateTypeCode the rate type code
     * @throws Exception
     */
    private void verifyExistingMaintenanceDocument(String documentNumber, String calcTypeId, String dependentRateClassType, String rateClassCode, String rateTypeCode) throws Exception {
        MaintenanceDocumentBase document = (MaintenanceDocumentBase) getDocumentService().getByDocumentHeaderId(documentNumber);
        assertNotNull(document.getDocumentNumber());
        assertNotNull(document.getDocumentHeader());
        assertEquals(documentNumber, document.getDocumentHeader().getDocumentNumber());
        ValidCalcType validCalcType = (ValidCalcType) document.getNewMaintainableObject().getDataObject();
        assertEquals(calcTypeId, validCalcType.getCalcTypeId());
        assertEquals(Integer.valueOf(DEFAULT_DEPENDENT_SEQ_NUMBER), validCalcType.getDependentSeqNumber());
        assertEquals(DEFAULT_RATE_CLASS_TYPE, validCalcType.getRateClassType());
        assertEquals(dependentRateClassType, validCalcType.getDependentRateClassType());
        assertEquals(rateClassCode, validCalcType.getRateClassCode());
        assertEquals(rateTypeCode, validCalcType.getRateTypeCode());
    }
    
    /**
     * Create a new unique calc type id.
     * 
     * @return a new unique calc type id
     */
    @SuppressWarnings("unchecked")
    private String getNewCalcTypeId() {
        int maxCalcTypeId = 1;
        Collection<ValidCalcType> validCalcTypes = getBusinessObjectService().findAll(ValidCalcType.class);
        for (ValidCalcType validCalcType : validCalcTypes) {
            int activityTypeCode = Integer.valueOf(validCalcType.getCalcTypeId());
            if (activityTypeCode > maxCalcTypeId) {
                maxCalcTypeId = activityTypeCode;
            }
        }
        
        return String.valueOf(maxCalcTypeId + 1);
    }

}