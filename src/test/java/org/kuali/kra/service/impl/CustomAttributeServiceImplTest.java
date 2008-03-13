/*
 * Copyright 2007 The Kuali Foundation.
 *
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.RicePropertyConstants;
import org.kuali.core.UserSession;
import org.kuali.core.service.BusinessObjectService;
import org.kuali.core.service.DocumentService;
import org.kuali.core.util.GlobalVariables;
import org.kuali.kra.KraTestBase;
import org.kuali.kra.bo.CustomAttribute;
import org.kuali.kra.bo.CustomAttributeDocValue;
import org.kuali.kra.bo.CustomAttributeDocument;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.TestUtilities;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.service.CustomAttributeService;
import org.kuali.rice.KNSServiceLocator;

import edu.iu.uis.eden.exception.WorkflowException;

/**
 * This class tests CustomAttributeServiceImpl.
 */
public class CustomAttributeServiceImplTest extends KraTestBase {


    private Map<String, CustomAttributeDocument> testCustomAttributeDocuments;

    private DocumentService documentService = null;
    private CustomAttributeService customAttributeService = null;
    private BusinessObjectService businessObjectService = null;
    private static final String TEST_DOCUMENT_TYPE_CODE = "PRDV";
    private static final String TEST_DOCUMENT_NUMBER = "2000";

    @Before
    public void setUp() throws Exception {
        super.setUp();
        GlobalVariables.setUserSession(new UserSession("quickstart"));
        testCustomAttributeDocuments = TestUtilities.setupTestCustomAttributeDocuments();
        documentService = KNSServiceLocator.getDocumentService();
        customAttributeService = KraServiceLocator.getService(CustomAttributeService.class);
        businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
    }

    @After
    public void tearDown() throws Exception {
        GlobalVariables.setUserSession(null);
        testCustomAttributeDocuments = null;
        documentService = null;
        customAttributeService = null;
        businessObjectService = null;
        super.tearDown();
    }


    @Test public void testGetDefaultCustomAttributesForDocumentType() throws Exception {
        Map<String, CustomAttributeDocument>customAttributeDocuments = customAttributeService.getDefaultCustomAttributesForDocumentType(TEST_DOCUMENT_TYPE_CODE, TEST_DOCUMENT_NUMBER);
        assertNotNull(customAttributeDocuments);
        assertEquals(testCustomAttributeDocuments.size(), customAttributeDocuments.size());

        for(Map.Entry<String, CustomAttributeDocument> testCustomAttributeDocumentEntry: testCustomAttributeDocuments.entrySet()) {
            CustomAttributeDocument testCustomAttributeDocument = testCustomAttributeDocumentEntry.getValue();
            CustomAttributeDocument customAttributeDocument = customAttributeDocuments.get(testCustomAttributeDocument.getCustomAttributeId().toString());
            assertNotNull(customAttributeDocument);
            assertEquals(testCustomAttributeDocument.getDocumentTypeCode(), customAttributeDocument.getDocumentTypeCode());
            assertEquals(testCustomAttributeDocument.isRequired(), customAttributeDocument.isRequired());
            CustomAttribute testCustomAttribute = testCustomAttributeDocument.getCustomAttribute();
            CustomAttribute customAttribute = customAttributeDocument.getCustomAttribute();

            assertEquals(testCustomAttribute.getId(), customAttribute.getId());
            assertEquals(testCustomAttribute.getName(), customAttribute.getName());
            assertEquals(testCustomAttribute.getLabel(), customAttribute.getLabel());
            assertEquals(testCustomAttribute.getDataTypeCode(), customAttribute.getDataTypeCode());
            assertEquals(testCustomAttribute.getDataLength(), customAttribute.getDataLength());
            assertEquals(testCustomAttribute.getGroupName(), customAttribute.getGroupName());
            assertEquals(testCustomAttribute.getLookupClass(), customAttribute.getLookupClass());
            assertEquals(testCustomAttribute.getLookupReturn(), customAttribute.getLookupReturn());
        }
    }

    @Test public void testGetDefaultCustomAttributesForDocumentTypeNullDocument() throws Exception {
        Map<String, CustomAttributeDocument>customAttributeDocuments = customAttributeService.getDefaultCustomAttributesForDocumentType(TEST_DOCUMENT_TYPE_CODE, TEST_DOCUMENT_NUMBER);
        assertNotNull(customAttributeDocuments);
        assertNotNull(testCustomAttributeDocuments);
        assertEquals(testCustomAttributeDocuments.size(), customAttributeDocuments.size());

        for(Map.Entry<String, CustomAttributeDocument> testCustomAttributeDocumentEntry: testCustomAttributeDocuments.entrySet()) {
            CustomAttributeDocument testCustomAttributeDocument = testCustomAttributeDocumentEntry.getValue();
            CustomAttributeDocument customAttributeDocument = customAttributeDocuments.get(testCustomAttributeDocument.getCustomAttributeId().toString());
            assertNotNull(customAttributeDocument);
            assertEquals(testCustomAttributeDocument.getDocumentTypeCode(), customAttributeDocument.getDocumentTypeCode());
            assertEquals(testCustomAttributeDocument.isRequired(), customAttributeDocument.isRequired());
            CustomAttribute testCustomAttribute = testCustomAttributeDocument.getCustomAttribute();
            CustomAttribute customAttribute = customAttributeDocument.getCustomAttribute();

            assertEquals(testCustomAttribute.getId(), customAttribute.getId());
            assertEquals(testCustomAttribute.getName(), customAttribute.getName());
            assertEquals(testCustomAttribute.getLabel(), customAttribute.getLabel());
            assertEquals(testCustomAttribute.getDataTypeCode(), customAttribute.getDataTypeCode());
            assertEquals(testCustomAttribute.getDataLength(), customAttribute.getDataLength());
            assertEquals(testCustomAttribute.getGroupName(), customAttribute.getGroupName());
            assertEquals(testCustomAttribute.getLookupClass(), customAttribute.getLookupClass());
            assertEquals(testCustomAttribute.getLookupReturn(), customAttribute.getLookupReturn());
        }
    }

    @Test public void testGetDefaultCustomAttributesFromNewDocument() throws Exception {
        ProposalDevelopmentDocument document = (ProposalDevelopmentDocument) documentService.getNewDocument(ProposalDevelopmentDocument.class);
        document.initialize();

        Map<String, CustomAttributeDocument>customAttributeDocuments = document.getCustomAttributeDocuments();
        assertNotNull(customAttributeDocuments);
        assertNotNull(testCustomAttributeDocuments);
        assertEquals(testCustomAttributeDocuments.size(), customAttributeDocuments.size());

        for(Map.Entry<String, CustomAttributeDocument> testCustomAttributeDocumentEntry: testCustomAttributeDocuments.entrySet()) {
            CustomAttributeDocument testCustomAttributeDocument = testCustomAttributeDocumentEntry.getValue();
            CustomAttributeDocument customAttributeDocument = customAttributeDocuments.get(testCustomAttributeDocument.getCustomAttributeId().toString());
            assertNotNull(customAttributeDocument);
            assertEquals(testCustomAttributeDocument.getDocumentTypeCode(), customAttributeDocument.getDocumentTypeCode());
            assertEquals(testCustomAttributeDocument.isRequired(), customAttributeDocument.isRequired());
            CustomAttribute testCustomAttribute = testCustomAttributeDocument.getCustomAttribute();
            CustomAttribute customAttribute = customAttributeDocument.getCustomAttribute();

            assertEquals(testCustomAttribute.getId(), customAttribute.getId());
            assertEquals(testCustomAttribute.getName(), customAttribute.getName());
            assertEquals(testCustomAttribute.getLabel(), customAttribute.getLabel());
            assertEquals(testCustomAttribute.getDataTypeCode(), customAttribute.getDataTypeCode());
            assertEquals(testCustomAttribute.getDataLength(), customAttribute.getDataLength());
            assertEquals(testCustomAttribute.getGroupName(), customAttribute.getGroupName());
            assertEquals(testCustomAttribute.getLookupClass(), customAttribute.getLookupClass());
            assertEquals(testCustomAttribute.getLookupReturn(), customAttribute.getLookupReturn());
        }
    }

    @Test public void testSaveCustomAttributes() throws Exception {
        ProposalDevelopmentDocument document = (ProposalDevelopmentDocument) documentService.getNewDocument(ProposalDevelopmentDocument.class);
        String documentNumber = document.getDocumentNumber();
        document.initialize();
        Map<String, CustomAttributeDocument>customAttributeDocuments = document.getCustomAttributeDocuments();
        CustomAttributeDocument customAttributeDocument = customAttributeDocuments.get("1");
        Integer customAttributeId = customAttributeDocument.getCustomAttributeId();
        CustomAttribute customAttribute = customAttributeDocument.getCustomAttribute();
        String name = customAttribute.getName();
        customAttribute.setValue(name + "Value");

        customAttributeService.saveCustomAttributeValues(document);

        Map<String, Object> primaryKeys = new HashMap<String, Object>();
        primaryKeys.put("customAttributeId", customAttributeId);
        primaryKeys.put(RicePropertyConstants.DOCUMENT_NUMBER, documentNumber);
        CustomAttributeDocValue customAttributeDocValue = (CustomAttributeDocValue) businessObjectService.findByPrimaryKey(CustomAttributeDocValue.class, primaryKeys);
        assertNotNull("customAttributeDocValue should be found and not null", customAttributeDocValue);
        assertEquals(name + "Value", customAttributeDocValue.getValue());
    }

    @Test public void testGetDefaultCustomAttributesFromSavedDocument() throws Exception {
        ProposalDevelopmentDocument document = getDocument();

        Map<String, CustomAttributeDocument>customAttributeDocuments = document.getCustomAttributeDocuments();
        assertNotNull(customAttributeDocuments);
        assertNotNull(testCustomAttributeDocuments);
        assertEquals(testCustomAttributeDocuments.size(), customAttributeDocuments.size());

        for(Map.Entry<String, CustomAttributeDocument> testCustomAttributeDocumentEntry: testCustomAttributeDocuments.entrySet()) {
            CustomAttributeDocument testCustomAttributeDocument = testCustomAttributeDocumentEntry.getValue();
            CustomAttributeDocument customAttributeDocument = customAttributeDocuments.get(testCustomAttributeDocument.getCustomAttributeId().toString());
            assertNotNull(customAttributeDocument);
            assertEquals(testCustomAttributeDocument.getDocumentTypeCode(), customAttributeDocument.getDocumentTypeCode());
            assertEquals(testCustomAttributeDocument.isRequired(), customAttributeDocument.isRequired());
            CustomAttribute testCustomAttribute = testCustomAttributeDocument.getCustomAttribute();
            CustomAttribute customAttribute = customAttributeDocument.getCustomAttribute();

            assertEquals(testCustomAttribute.getId(), customAttribute.getId());
            assertEquals(testCustomAttribute.getName(), customAttribute.getName());
            assertEquals(testCustomAttribute.getLabel(), customAttribute.getLabel());
            assertEquals(testCustomAttribute.getDataTypeCode(), customAttribute.getDataTypeCode());
            assertEquals(testCustomAttribute.getDataLength(), customAttribute.getDataLength());
            assertEquals(testCustomAttribute.getGroupName(), customAttribute.getGroupName());
            assertEquals(testCustomAttribute.getLookupClass(), customAttribute.getLookupClass());
            assertEquals(testCustomAttribute.getLookupReturn(), customAttribute.getLookupReturn());
        }
    }
    
    @Test public void testGetLookupReturns() throws Exception {
        List userRoleProperties = new ArrayList();
        userRoleProperties.add("roleId");
        userRoleProperties.add("unitNumber");
        userRoleProperties.add("userId");
        userRoleProperties.add("descendFlag");
        List lookupReturnFields = customAttributeService.getLookupReturns("org.kuali.kra.bo.UserRole");
        assertEquals(userRoleProperties.size(), lookupReturnFields.size());

        for(Object returnField : lookupReturnFields) {
            assertTrue(userRoleProperties.contains(returnField));
        }
    }
    
    @Test public void testGetLookupReturnsForAjaxCall() throws Exception {
        String userRoleProperties = ",roleId,unitNumber,userId,descendFlag";
        String lookupReturnFields = customAttributeService.getLookupReturnsForAjaxCall("org.kuali.kra.bo.UserRole");
        assertEquals(userRoleProperties,lookupReturnFields);
    }



    private ProposalDevelopmentDocument getDocument() throws WorkflowException {
        ProposalDevelopmentDocument document = (ProposalDevelopmentDocument) documentService.getNewDocument("ProposalDevelopmentDocument");
        document.initialize();

        Date requestedStartDateInitial = new Date(System.currentTimeMillis());
        Date requestedEndDateInitial = new Date(System.currentTimeMillis());

        setBaseDocumentFields(document, "ProposalDevelopmentDocumentTest test doc", "005770", "project title", requestedStartDateInitial, requestedEndDateInitial, "1", "1", "000001");

        documentService.saveDocument(document);

        ProposalDevelopmentDocument savedDocument = (ProposalDevelopmentDocument) documentService.getByDocumentHeaderId(document.getDocumentNumber());

        return savedDocument;
    }

    /**
     * This method sets the base/required document fields
     * @param document ProposalDevelopmentDocument to set fields for
     * @param title String title to set
     * @param requestedStartDateInitial Date start date to set
     * @param requestedEndDateInitila Date end date to set
     * @param activityTypeCode String activity type code to set
     * @param proposalTypeCode String proposal type code to set
     * @param ownedByUnit String owned-by unit to set
     */
    private void setBaseDocumentFields(ProposalDevelopmentDocument document, String description, String sponsorCode, String title, Date requestedStartDateInitial, Date requestedEndDateInitial, String activityTypeCode, String proposalTypeCode, String ownedByUnit) {
        document.getDocumentHeader().setFinancialDocumentDescription(description);
        document.setSponsorCode(sponsorCode);
        document.setTitle(title);
        document.setRequestedStartDateInitial(requestedStartDateInitial);
        document.setRequestedEndDateInitial(requestedEndDateInitial);
        document.setActivityTypeCode(activityTypeCode);
        document.setProposalTypeCode(proposalTypeCode);
        document.setOwnedByUnitNumber(ownedByUnit);
    }

}
