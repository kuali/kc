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
package org.kuali.kra.bo.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.bo.SponsorFormTemplate;
import org.kuali.kra.bo.SponsorForms;
import org.kuali.kra.test.infrastructure.KcSeleniumTestBase;
import org.kuali.rice.kns.document.MaintenanceDocumentBase;

/**
 * Tests the Sponsor Form and Sponsor Form Template maintenance documents.
 */
public class SponsorFormMaintenanceDocumentSeleniumTest extends KcSeleniumTestBase {
    
    private static final String SPONSOR_FORM_DOCUMENT_TITLE = "Sponsor Forms";
    private static final String SPONSOR_FORM_MAINTENANCE_DOCUMENT_TITLE = "Kuali :: Sponsor Form";
    private static final String SPONSOR_FORM_TEMPLATE_DOCUMENT_TITLE = "Sponsor Form Templates";
    private static final String SPONSOR_FORM_TEMPLATE_MAINTENANCE_DOCUMENT_TITLE = "Kuali :: Sponsor Form Template";
    
    private static final String PACKAGE_NAME_ID = "packageName";
    private static final String PACKAGE_NUMBER_ID = "packageNumber";
    private static final String SPONSOR_CODE_ID = "sponsorCode";
    private static final String SPONSOR_FORM_ID_ID = "sponsorFormId";
    private static final String PAGE_DESCRIPTION_ID = "pageDescription";
    private static final String PAGE_NUMBER_ID = "pageNumber";
    
    private static final String CREATE_SPONSOR_FORM_DOCUMENT_DESCRIPTION = "Sponsor Form - Test Create";
    private static final String CREATE_SPONSOR_FORM_SPONSOR_CODE = "000100";
    
    private static final String EDIT_SPONSOR_FORM_DOCUMENT_DESCRIPTION = "Sponsor Form - Test Edit";
    private static final String EDIT_SPONSOR_FORM_SPONSOR_CODE_1 = "000100";
    private static final String EDIT_SPONSOR_FORM_SPONSOR_CODE_2 = "000101";
    
    private static final String COPY_SPONSOR_FORM_DOCUMENT_DESCRIPTION = "Sponsor Form - Test Copy";
    private static final String COPY_SPONSOR_FORM_SPONSOR_CODE_1 = "000100";
    private static final String COPY_SPONSOR_FORM_SPONSOR_CODE_2 = "000101";
    
    private static final String CREATE_SPONSOR_FORM_TEMPLATE_DOCUMENT_DESCRIPTION = "Sponsor Form Template - Test Create";
    private static final String CREATE_SPONSOR_FORM_TEMPLATE_PAGE_DESCRIPTION = "Test Create Sponsor Form Template";
    
    private static final String EDIT_SPONSOR_FORM_TEMPLATE_DOCUMENT_DESCRIPTION = "Sponsor Form Template - Test Edit";
    private static final String EDIT_SPONSOR_FORM_TEMPLATE_PAGE_DESCRIPTION_1 = "Test Edit Sponsor Form Template (Before)";
    private static final String EDIT_SPONSOR_FORM_TEMPLATE_PAGE_DESCRIPTION_2 = "Test Edit Sponsor Form Template (After)";
    
    private static final String COPY_SPONSOR_FORM_TEMPLATE_DOCUMENT_DESCRIPTION = "Sponsor Form - Test Copy";
    private static final String COPY_SPONSOR_FORM_TEMPLATE_PAGE_DESCRIPTION = "Test Copy Sponsor Form Template";
    
    private static final String DEFAULT_PACKAGE_NAME = "Test Sponsor Form";
    private static final String DEFAULT_SPONSOR_CODE = "000100";

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
     * Test creating a sponsor form.
     *
     * @throws Exception
     */
    @Test
    public void testCreateSponsorForm() throws Exception {
        int packageNumber = getNewPackageNumber();
        String documentNumber = createNewSponsorFormMaintenanceDocument(CREATE_SPONSOR_FORM_DOCUMENT_DESCRIPTION, packageNumber, CREATE_SPONSOR_FORM_SPONSOR_CODE);
        
        verifyExistingSponsorFormMaintenanceDocument(documentNumber, packageNumber, CREATE_SPONSOR_FORM_SPONSOR_CODE);
    }
    
    /**
     * Test editing a sponsor form.
     *
     * @throws Exception
     */
    @Test
    public void testEditSponsorForm() throws Exception {
        int packageNumber = getNewPackageNumber();
        createNewSponsorFormMaintenanceDocument(EDIT_SPONSOR_FORM_DOCUMENT_DESCRIPTION, packageNumber, EDIT_SPONSOR_FORM_SPONSOR_CODE_1);
        
        Map<String, String> searchValues = new LinkedHashMap<String, String>();
        searchValues.put(PACKAGE_NAME_ID, DEFAULT_PACKAGE_NAME);
        searchValues.put(PACKAGE_NUMBER_ID, String.valueOf(packageNumber));
        String documentNumber = helper.editMaintenanceDocument(SPONSOR_FORM_DOCUMENT_TITLE, SponsorForms.class.getName(), searchValues, SPONSOR_FORM_MAINTENANCE_DOCUMENT_TITLE);
        
        Map<String, String> fieldValues = new LinkedHashMap<String, String>();
        fieldValues.put(SPONSOR_CODE_ID, EDIT_SPONSOR_FORM_SPONSOR_CODE_2);
        helper.populateMaintenanceDocument(EDIT_SPONSOR_FORM_DOCUMENT_DESCRIPTION, fieldValues);
        
        helper.routeDocument();
        helper.assertRoute();
        
        verifyExistingSponsorFormMaintenanceDocument(documentNumber, packageNumber, EDIT_SPONSOR_FORM_SPONSOR_CODE_2);
    }

    /**
     * Test copying a sponsor form.
     *
     * @throws Exception
     */
    @Test
    public void testCopySponsorForm() throws Exception {
        int packageNumber1 = getNewPackageNumber();
        createNewSponsorFormMaintenanceDocument(COPY_SPONSOR_FORM_DOCUMENT_DESCRIPTION, packageNumber1, COPY_SPONSOR_FORM_SPONSOR_CODE_1);
        
        Map<String, String> searchValues = new LinkedHashMap<String, String>();
        searchValues.put(PACKAGE_NAME_ID, DEFAULT_PACKAGE_NAME);
        searchValues.put(PACKAGE_NUMBER_ID, String.valueOf(packageNumber1));
        String documentNumber = helper.copyMaintenanceDocument(SPONSOR_FORM_DOCUMENT_TITLE, SponsorForms.class.getName(), searchValues, SPONSOR_FORM_MAINTENANCE_DOCUMENT_TITLE);
        
        int packageNumber2 = getNewPackageNumber();
        Map<String, String> fieldValues = new LinkedHashMap<String, String>();
        fieldValues.put(PACKAGE_NAME_ID, DEFAULT_PACKAGE_NAME);
        fieldValues.put(PACKAGE_NUMBER_ID, String.valueOf(packageNumber2));
        fieldValues.put(SPONSOR_CODE_ID, COPY_SPONSOR_FORM_SPONSOR_CODE_2);
        helper.populateMaintenanceDocument(COPY_SPONSOR_FORM_DOCUMENT_DESCRIPTION, fieldValues);
        
        helper.routeDocument();
        helper.assertRoute();
        
        verifyExistingSponsorFormMaintenanceDocument(documentNumber, packageNumber2, COPY_SPONSOR_FORM_SPONSOR_CODE_2);
    }
    
    /**
     * Test creating a sponsor form template.
     *
     * @throws Exception
     */
    @Test
    public void testCreateSponsorFormTemplate() throws Exception {
        long sponsorFormId = createNewSponsorFormForSponsorFormTemplate();
        
        int pageNumber = getNewPageNumber(sponsorFormId);
        String documentNumber = createNewSponsorFormTemplateMaintenanceDocument(CREATE_SPONSOR_FORM_TEMPLATE_DOCUMENT_DESCRIPTION, sponsorFormId, CREATE_SPONSOR_FORM_TEMPLATE_PAGE_DESCRIPTION, pageNumber);
        
        verifyExistingSponsorFormTemplateMaintenanceDocument(documentNumber, sponsorFormId, CREATE_SPONSOR_FORM_TEMPLATE_PAGE_DESCRIPTION, pageNumber);
    }
    
    /**
     * Test editing a sponsor form template.
     *
     * @throws Exception
     */
    @Test
    public void testEditSponsorFormTemplate() throws Exception {
        long sponsorFormId = createNewSponsorFormForSponsorFormTemplate();
        
        int pageNumber = getNewPageNumber(sponsorFormId);
        createNewSponsorFormTemplateMaintenanceDocument(EDIT_SPONSOR_FORM_TEMPLATE_DOCUMENT_DESCRIPTION, sponsorFormId, EDIT_SPONSOR_FORM_TEMPLATE_PAGE_DESCRIPTION_1, pageNumber);
        
        Map<String, String> searchValues = new LinkedHashMap<String, String>();
        searchValues.put(SPONSOR_FORM_ID_ID, String.valueOf(sponsorFormId));
        String documentNumber = helper.editMaintenanceDocument(SPONSOR_FORM_TEMPLATE_DOCUMENT_TITLE, SponsorFormTemplate.class.getName(), searchValues, SPONSOR_FORM_TEMPLATE_MAINTENANCE_DOCUMENT_TITLE);
        
        Map<String, String> fieldValues = new LinkedHashMap<String, String>();
        fieldValues.put(PAGE_DESCRIPTION_ID, EDIT_SPONSOR_FORM_TEMPLATE_PAGE_DESCRIPTION_2);
        helper.populateMaintenanceDocument(EDIT_SPONSOR_FORM_TEMPLATE_DOCUMENT_DESCRIPTION, fieldValues);
        
        helper.routeDocument();
        helper.assertRoute();
        
        verifyExistingSponsorFormTemplateMaintenanceDocument(documentNumber, sponsorFormId, EDIT_SPONSOR_FORM_TEMPLATE_PAGE_DESCRIPTION_2, pageNumber);
    }
    
    /**
     * Test copying a sponsor form template.
     *
     * @throws Exception
     */
    @Test
    public void testCopySponsorFormTemplate() throws Exception {
        long sponsorFormId = createNewSponsorFormForSponsorFormTemplate();
        
        int pageNumber1 = getNewPageNumber(sponsorFormId);
        createNewSponsorFormTemplateMaintenanceDocument(COPY_SPONSOR_FORM_TEMPLATE_DOCUMENT_DESCRIPTION, sponsorFormId, COPY_SPONSOR_FORM_TEMPLATE_PAGE_DESCRIPTION, pageNumber1);
        
        Map<String, String> searchValues = new LinkedHashMap<String, String>();
        searchValues.put(SPONSOR_FORM_ID_ID, String.valueOf(sponsorFormId));
        String documentNumber = helper.copyMaintenanceDocument(SPONSOR_FORM_TEMPLATE_DOCUMENT_TITLE, SponsorFormTemplate.class.getName(), searchValues, SPONSOR_FORM_TEMPLATE_MAINTENANCE_DOCUMENT_TITLE);
        
        int pageNumber2 = getNewPageNumber(sponsorFormId);
        Map<String, String> fieldValues = new LinkedHashMap<String, String>();
        fieldValues.put(PAGE_DESCRIPTION_ID, COPY_SPONSOR_FORM_TEMPLATE_PAGE_DESCRIPTION);
        fieldValues.put(PAGE_NUMBER_ID, String.valueOf(pageNumber2));
        helper.populateMaintenanceDocument(COPY_SPONSOR_FORM_TEMPLATE_DOCUMENT_DESCRIPTION, fieldValues);
        
        helper.routeDocument();
        helper.assertRoute();
        
        verifyExistingSponsorFormTemplateMaintenanceDocument(documentNumber, sponsorFormId, COPY_SPONSOR_FORM_TEMPLATE_PAGE_DESCRIPTION, pageNumber2);
    }
    
    /**
     * Create a new maintenance document.
     * 
     * @param documentDescription the document description
     * @param packageNumber the package number
     * @param sponsorCode the sponsor code
     * @return the document number of the new maintenance document
     */
    // TODO: Add template files
    private String createNewSponsorFormMaintenanceDocument(String documentDescription, int packageNumber, String sponsorCode) {
        String documentNumber = helper.createMaintenanceDocument(SPONSOR_FORM_DOCUMENT_TITLE, SponsorForms.class.getName(), SPONSOR_FORM_MAINTENANCE_DOCUMENT_TITLE);
    
        Map<String, String> fieldValues = new LinkedHashMap<String, String>();
        fieldValues.put(PACKAGE_NAME_ID, DEFAULT_PACKAGE_NAME);
        fieldValues.put(PACKAGE_NUMBER_ID, String.valueOf(packageNumber));
        fieldValues.put(SPONSOR_CODE_ID, sponsorCode);
//        fieldValues.put(TEMPLATE_FILE_ID, DEFAULT_TEMPLATE_FILE);
        helper.populateMaintenanceDocument(documentDescription, fieldValues);
        
        helper.routeDocument();
        helper.assertRoute();
        
        return documentNumber;
    }
    
    /**
     * Create a new maintenance document.
     * 
     * @param documentDescription the document description
     * @param sponsorFormId the sponsor form id
     * @param packageDescription the package description
     * @param pageNumber the page number
     * @return the document number of the new maintenance document
     */
    // TODO: Add template files
    private String createNewSponsorFormTemplateMaintenanceDocument(String documentDescription, long sponsorFormId, String pageDescription, int pageNumber) {
        String documentNumber = helper.createMaintenanceDocument(SPONSOR_FORM_TEMPLATE_DOCUMENT_TITLE, SponsorFormTemplate.class.getName(), SPONSOR_FORM_TEMPLATE_MAINTENANCE_DOCUMENT_TITLE);
    
        Map<String, String> fieldValues = new LinkedHashMap<String, String>();
        fieldValues.put(SPONSOR_FORM_ID_ID, String.valueOf(sponsorFormId));
        fieldValues.put(PAGE_DESCRIPTION_ID, pageDescription);
        fieldValues.put(PAGE_NUMBER_ID, String.valueOf(pageNumber));
//        fieldValues.put(TEMPLATE_FILE_ID, DEFAULT_TEMPLATE_FILE);
        helper.populateMaintenanceDocument(documentDescription, fieldValues);
        
        helper.routeDocument();
        helper.assertRoute();
        
        return documentNumber;
    }
    
    /**
     * Creates a new sponsor form for a new sponsor form template.
     * 
     * @return the id of the new sponsor form
     */
    @SuppressWarnings("unchecked")
    private long createNewSponsorFormForSponsorFormTemplate() {
        int packageNumber = getNewPackageNumber();
        createNewSponsorFormMaintenanceDocument(CREATE_SPONSOR_FORM_DOCUMENT_DESCRIPTION, packageNumber, DEFAULT_SPONSOR_CODE);
        
        Map<String, String> fieldValues = new LinkedHashMap<String, String>();
        fieldValues.put(PACKAGE_NAME_ID, DEFAULT_PACKAGE_NAME);
        fieldValues.put(PACKAGE_NUMBER_ID, String.valueOf(packageNumber));
        List<SponsorForms> sponsorForms = new ArrayList<SponsorForms>(getBusinessObjectService().findMatching(SponsorForms.class, fieldValues));

        return sponsorForms.get(0).getSponsorFormId();
    }
    
    /**
     * Verify the details of an existing maintenance document.
     *
     * @param documentNumber the document of the maintenance document to verify
     * @param packageNumber the package number
     * @param sponsorCode the sponsor code
     * @throws Exception
     */
    private void verifyExistingSponsorFormMaintenanceDocument(String documentNumber, int packageNumber, String sponsorCode) throws Exception {
        MaintenanceDocumentBase document = (MaintenanceDocumentBase) getDocumentService().getByDocumentHeaderId(documentNumber);
        assertNotNull(document.getDocumentNumber());
        assertNotNull(document.getDocumentHeader());
        assertEquals(documentNumber, document.getDocumentHeader().getDocumentNumber());
        SponsorForms sponsorForm = (SponsorForms) document.getNewMaintainableObject().getDataObject();
        assertEquals(DEFAULT_PACKAGE_NAME, sponsorForm.getPackageName());
        assertEquals(Integer.valueOf(packageNumber), sponsorForm.getPackageNumber());
        assertEquals(sponsorCode, sponsorForm.getSponsorCode());
    }
    
    /**
     * Verify the details of an existing maintenance document.
     *
     * @param documentNumber the document of the maintenance document to verify
     * @param sponsorFormId the sponsor form id
     * @param packageDescription the package description
     * @param pageNumber the page number
     * @throws Exception
     */
    private void verifyExistingSponsorFormTemplateMaintenanceDocument(String documentNumber, long sponsorFormId, String pageDescription, int pageNumber) throws Exception {
        MaintenanceDocumentBase document = (MaintenanceDocumentBase) getDocumentService().getByDocumentHeaderId(documentNumber);
        assertNotNull(document.getDocumentNumber());
        assertNotNull(document.getDocumentHeader());
        assertEquals(documentNumber, document.getDocumentHeader().getDocumentNumber());
        SponsorFormTemplate sponsorFormTemplate = (SponsorFormTemplate) document.getNewMaintainableObject().getDataObject();
        assertEquals(Long.valueOf(sponsorFormId), sponsorFormTemplate.getSponsorFormId());
        assertEquals(pageDescription, sponsorFormTemplate.getPageDescription());
        assertEquals(Integer.valueOf(pageNumber), sponsorFormTemplate.getPageNumber());
    }
    
    /**
     * Create a new unique package number.
     * 
     * @return a new unique package number
     */
    @SuppressWarnings("unchecked")
    private int getNewPackageNumber() {
        int maxPackageNumber = 1;
        Collection<SponsorForms> sponsorForms = getBusinessObjectService().findAll(SponsorForms.class);
        for (SponsorForms sponsorForm : sponsorForms) {
            int packageNumber = sponsorForm.getPackageNumber();
            if (packageNumber > maxPackageNumber) {
                maxPackageNumber = packageNumber;
            }
        }
        
        return maxPackageNumber + 1;
    }
    
    /**
     * Create a new unique page number.
     * 
     * @param sponsorFormId the sponsor form id
     * @return a new unique page number
     */
    @SuppressWarnings("unchecked")
    private int getNewPageNumber(long sponsorFormId) {
        int maxPageNumber = 1;
        Map<String, String> fieldValues = new LinkedHashMap<String, String>();
        fieldValues.put(SPONSOR_FORM_ID_ID, String.valueOf(sponsorFormId));
        Collection<SponsorFormTemplate> sponsorFormTemplates = getBusinessObjectService().findMatching(SponsorFormTemplate.class, fieldValues);
        for (SponsorFormTemplate sponsorFormTemplate : sponsorFormTemplates) {
            int activityTypeCode = sponsorFormTemplate.getPageNumber();
            if (activityTypeCode > maxPageNumber) {
                maxPageNumber = activityTypeCode;
            }
        }
        
        return maxPageNumber + 1;
    }

}