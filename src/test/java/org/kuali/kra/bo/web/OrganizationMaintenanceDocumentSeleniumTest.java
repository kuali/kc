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

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.math.NumberUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.bo.Organization;
import org.kuali.kra.proposaldevelopment.bo.ActivityType;
import org.kuali.kra.test.infrastructure.KcSeleniumTestBase;
import org.kuali.rice.kns.document.MaintenanceDocumentBase;

/**
 * Tests the Organization maintenance document.
 */
public class OrganizationMaintenanceDocumentSeleniumTest extends KcSeleniumTestBase {
    
    private static final String DOCUMENT_TITLE = "Organization";
    private static final String MAINTENANCE_DOCUMENT_TITLE = "Kuali :: Organization Maintenance Document";

    private static final String ORGANIZATION_ID_ID = "organizationId";
    private static final String CONTACT_ADDRESS_ID_ID = "contactAddressId";
    private static final String ORGANIZATION_NAME_ID = "organizationName";
    private static final String ORGANIZATION_TYPES_ORGANIZATION_TYPE_CODE_ID = "organizationTypes.organizationTypeCode";
    private static final String ORGANIZATION_YNQS_ANSWER_ID = "organizationYnqs[%s].answer";
    private static final String ORGANIZATION_YNQS_EXPLANATION_ID = "organizationYnqs[%s].explanation";
    private static final String ORGANIZATION_YNQS_REVIEW_DATE_ID = "organizationYnqs[%s].reviewDate";
    private static final String ORGANIZATION_AUDITS_FISCAL_YEAR_ID = "organizationAudits.fiscalYear";
    private static final String ORGANIZATION_IDCS_IDC_NUMBER_ID = "organizationIdcs.idcNumber";
    private static final String ORGANIZATION_IDCS_APPLICABLE_INDIRECT_COST_RATE_ID = "organizationIdcs.applicableIndirectcostRate";
    private static final String ORGANIZATION_IDCS_END_DATE = "organizationIdcs.endDate";
    private static final String ORGANIZATION_IDCS_IDC_RATE_TYPE_CODE = "organizationIdcs.idcRateTypeCode";
    private static final String ORGANIZATION_IDCS_REQUESTED_DATE = "organizationIdcs.requestedDate";
    private static final String ORGANIZATION_IDCS_START_DATE = "organizationIdcs.startDate";
    
    private static final String CREATE_DOCUMENT_DESCRIPTION = "Organization - Test Create";
    private static final int CREATE_CONTACT_ADDRESS_ID = 1741;
    private static final String CREATE_ORGANIZATION_NAME = "Test Create Organization";
    
    private static final String EDIT_DOCUMENT_DESCRIPTION = "Organization - Test Edit";
    private static final int EDIT_CONTACT_ADDRESS_ID_1 = 1741;
    private static final int EDIT_CONTACT_ADDRESS_ID_2 = 13469;
    private static final String EDIT_ORGANIZATION_NAME = "Test Edit Organization";
    
    private static final String COPY_DOCUMENT_DESCRIPTION = "Organization - Test Copy";
    private static final int COPY_CONTACT_ADDRESS_ID_1 = 1741;
    private static final int COPY_CONTACT_ADDRESS_ID_2 = 13469;
    private static final String COPY_ORGANIZATION_NAME_1 = "Test Copy Organization (Before)";
    private static final String COPY_ORGANIZATION_NAME_2 = "Test Copy Organization (After)";
    
    private static final int DEFAULT_ORGANIZATION_TYPES_ORGANIZATION_TYPE_CODE = 1;
    private static final String DEFAULT_ORGANIZATION_YNQS_ANSWER_YES = "Y";
    private static final String DEFAULT_ORGANIZATION_YNQS_ANSWER_NO = "N";
    private static final String DEFAULT_ORGANIZATION_YNQS_EXPLANATION = "Test";
    private static final String DEFAULT_ORGANIZATION_YNQS_REVIEW_DATE = "01/01/2008";
    private static final String DEFAULT_ORGANIZATION_AUDITS_FISCAL_YEAR = "2008";
    private static final int DEFAULT_ORGANIZATION_IDCS_IDC_NUMBER = 1;
    private static final String DEFAULT_ORGANIZATION_IDCS_APPLICABLE_INDIRECT_COST_RATE = "1.0";
    private static final int DEFAULT_ORGANIZATION_IDCS_IDC_RATE_TYPE_CODE = 1;
    private static final String DEFAULT_ORGANIZATION_IDCS_END_DATE = "01/01/2008";
    private static final String DEFAULT_ORGANIZATION_IDCS_REQUESTED_DATE = "01/01/2008";
    private static final String DEFAULT_ORGANIZATION_IDCS_START_DATE = "01/01/2008";
    
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
     * Test creating an organization.
     *
     * @throws Exception
     */
    @Test
    public void testCreateOrganization() throws Exception {
        String organizationId = getNewOrganizationId();
        String documentNumber = createNewMaintenanceDocument(CREATE_DOCUMENT_DESCRIPTION, organizationId, CREATE_CONTACT_ADDRESS_ID, CREATE_ORGANIZATION_NAME, 11);
        
        verifyExistingMaintenanceDocument(documentNumber, organizationId, CREATE_CONTACT_ADDRESS_ID, CREATE_ORGANIZATION_NAME);
    }

    /**
     * Test editing an organization.
     *
     * @throws Exception
     */
    @Test
    public void testEditOrganization() throws Exception {
        String organizationId = getNewOrganizationId();
        createNewMaintenanceDocument(EDIT_DOCUMENT_DESCRIPTION, organizationId, EDIT_CONTACT_ADDRESS_ID_1, EDIT_ORGANIZATION_NAME, 11);
        
        Map<String, String> searchValues = new LinkedHashMap<String, String>();
        searchValues.put(ORGANIZATION_ID_ID, organizationId);
        String documentNumber = helper.editMaintenanceDocument(DOCUMENT_TITLE, Organization.class.getName(), searchValues, MAINTENANCE_DOCUMENT_TITLE);
        
        Map<String, String> fieldValues = new LinkedHashMap<String, String>();
        fieldValues.put(CONTACT_ADDRESS_ID_ID, String.valueOf(EDIT_CONTACT_ADDRESS_ID_2));
        helper.populateMaintenanceDocument(EDIT_DOCUMENT_DESCRIPTION, fieldValues);
        
        helper.routeDocument();
        helper.assertRoute();
        
        verifyExistingMaintenanceDocument(documentNumber, organizationId, EDIT_CONTACT_ADDRESS_ID_2, EDIT_ORGANIZATION_NAME);
    }
    
    /**
     * Test copying an organization.
     *
     * @throws Exception
     */
    @Test
    public void testCopyOrganization() throws Exception {
        String organizationId1 = getNewOrganizationId();
        createNewMaintenanceDocument(COPY_DOCUMENT_DESCRIPTION, organizationId1, COPY_CONTACT_ADDRESS_ID_1, COPY_ORGANIZATION_NAME_1, 11);
        
        Map<String, String> searchValues = new LinkedHashMap<String, String>();
        searchValues.put(ORGANIZATION_ID_ID, organizationId1);
        String documentNumber = helper.copyMaintenanceDocument(DOCUMENT_TITLE, ActivityType.class.getName(), searchValues, MAINTENANCE_DOCUMENT_TITLE);
        
        String organizationId2 = getNewOrganizationId();
        Map<String, String> fieldValues = new LinkedHashMap<String, String>();
        fieldValues.put(ORGANIZATION_ID_ID, organizationId2);
        fieldValues.put(CONTACT_ADDRESS_ID_ID, String.valueOf(COPY_CONTACT_ADDRESS_ID_2));
        fieldValues.put(ORGANIZATION_NAME_ID, COPY_ORGANIZATION_NAME_2);
        helper.populateMaintenanceDocument(COPY_DOCUMENT_DESCRIPTION, fieldValues);
        
        helper.routeDocument();
        helper.assertRoute();
        
        verifyExistingMaintenanceDocument(documentNumber, organizationId2, COPY_CONTACT_ADDRESS_ID_2, COPY_ORGANIZATION_NAME_2);
    }
    
    /**
     * Create a new maintenance document.
     * 
     * @param documentDescription the document description
     * @param organizationId the organization id
     * @param contactAddressId the contact address id
     * @param organizationName the organization name
     * @param numberYnqs the number of YNQs
     * @return the document number of the new maintenance document
     */
    private String createNewMaintenanceDocument(String documentDescription, String organizationId, int contactAddressId, String organizationName, int numberYnqs) {
        String documentNumber = helper.createMaintenanceDocument(DOCUMENT_TITLE, Organization.class.getName(), MAINTENANCE_DOCUMENT_TITLE);
    
        Map<String, String> fieldValues = new LinkedHashMap<String, String>();
        fieldValues.put(ORGANIZATION_ID_ID, organizationId);
        fieldValues.put(CONTACT_ADDRESS_ID_ID, String.valueOf(contactAddressId));
        fieldValues.put(ORGANIZATION_NAME_ID, organizationName);
        for (int i = 0; i <= numberYnqs; i++) {
            fieldValues.put(String.format(ORGANIZATION_YNQS_ANSWER_ID, i), (i % 2 == 0) ? DEFAULT_ORGANIZATION_YNQS_ANSWER_YES : DEFAULT_ORGANIZATION_YNQS_ANSWER_NO);
            fieldValues.put(String.format(ORGANIZATION_YNQS_EXPLANATION_ID, i), DEFAULT_ORGANIZATION_YNQS_EXPLANATION);
            fieldValues.put(String.format(ORGANIZATION_YNQS_REVIEW_DATE_ID, i), DEFAULT_ORGANIZATION_YNQS_REVIEW_DATE);
        }
        
        List<Map<String, String>> collectionFieldValues = new ArrayList<Map<String, String>>();
        Map<String, String> organizationTypeFieldValues = new LinkedHashMap<String, String>();
        organizationTypeFieldValues.put(ORGANIZATION_TYPES_ORGANIZATION_TYPE_CODE_ID, String.valueOf(DEFAULT_ORGANIZATION_TYPES_ORGANIZATION_TYPE_CODE));
        collectionFieldValues.add(organizationTypeFieldValues);
        Map<String, String> organizationAuditsFieldValues = new LinkedHashMap<String, String>();
        organizationAuditsFieldValues.put(ORGANIZATION_AUDITS_FISCAL_YEAR_ID, DEFAULT_ORGANIZATION_AUDITS_FISCAL_YEAR);
        collectionFieldValues.add(organizationAuditsFieldValues);
        Map<String, String> organizationIdcsFieldValues = new LinkedHashMap<String, String>();
        organizationIdcsFieldValues.put(ORGANIZATION_IDCS_IDC_NUMBER_ID, String.valueOf(DEFAULT_ORGANIZATION_IDCS_IDC_NUMBER));
        organizationIdcsFieldValues.put(ORGANIZATION_IDCS_APPLICABLE_INDIRECT_COST_RATE_ID, DEFAULT_ORGANIZATION_IDCS_APPLICABLE_INDIRECT_COST_RATE);
        organizationIdcsFieldValues.put(ORGANIZATION_IDCS_END_DATE, DEFAULT_ORGANIZATION_IDCS_END_DATE);
        organizationIdcsFieldValues.put(ORGANIZATION_IDCS_IDC_RATE_TYPE_CODE, String.valueOf(DEFAULT_ORGANIZATION_IDCS_IDC_RATE_TYPE_CODE));
        organizationIdcsFieldValues.put(ORGANIZATION_IDCS_REQUESTED_DATE, DEFAULT_ORGANIZATION_IDCS_REQUESTED_DATE);
        organizationIdcsFieldValues.put(ORGANIZATION_IDCS_START_DATE, DEFAULT_ORGANIZATION_IDCS_START_DATE);
        collectionFieldValues.add(organizationIdcsFieldValues);

        helper.populateMaintenanceDocument(documentDescription, fieldValues, collectionFieldValues);
        
        helper.click("methodToCall.addLine.organizationTypes");
        helper.click("methodToCall.addLine.organizationAudits");
        helper.click("methodToCall.addLine.organizationIdcs");
        
        helper.routeDocument();
        helper.assertRoute();
        
        return documentNumber;
    }
    
    /**
     * Verify the details of an existing maintenance document.
     *
     * @param documentNumber the document of the maintenance document to verify
     * @param organizationId the organization id
     * @param contactAddressId the contact address id
     * @param organizationName the organization name
     * @param numberYnqs the number of YNQs
     * @throws Exception
     */
    private void verifyExistingMaintenanceDocument(String documentNumber, String organizationId, int contactAddressId, String organizationName) throws Exception {
        MaintenanceDocumentBase document = (MaintenanceDocumentBase) getDocumentService().getByDocumentHeaderId(documentNumber);
        assertNotNull(document.getDocumentNumber());
        assertNotNull(document.getDocumentHeader());
        assertEquals(documentNumber, document.getDocumentHeader().getDocumentNumber());
        Organization organization = (Organization) document.getNewMaintainableObject().getBusinessObject();
        assertEquals(organizationId, organization.getOrganizationId());
        assertEquals(Integer.valueOf(contactAddressId), organization.getContactAddressId());
        assertEquals(organizationName, organization.getOrganizationName());
        assertEquals(Integer.valueOf(DEFAULT_ORGANIZATION_TYPES_ORGANIZATION_TYPE_CODE), organization.getOrganizationTypes().get(0).getOrganizationTypeCode());
        assertEquals(DEFAULT_ORGANIZATION_AUDITS_FISCAL_YEAR, organization.getOrganizationAudits().get(0).getFiscalYear());
        assertEquals(Integer.valueOf(DEFAULT_ORGANIZATION_IDCS_IDC_NUMBER), organization.getOrganizationIdcs().get(0).getIdcNumber());
    }
    
    /**
     * Create a new unique organization id.
     * 
     * @return a new unique organization id
     */
    @SuppressWarnings("unchecked")
    private String getNewOrganizationId() {
        int maxOrganizationId = 1;
        Collection<Organization> organizations = getBusinessObjectService().findAll(Organization.class);
        for (Organization organization : organizations) {
            int activityTypeCode = NumberUtils.toInt(organization.getOrganizationId(), 1);
            if (activityTypeCode > maxOrganizationId) {
                maxOrganizationId = activityTypeCode;
            }
        }
        
        return String.format("%06d", maxOrganizationId + 1);
    }

}