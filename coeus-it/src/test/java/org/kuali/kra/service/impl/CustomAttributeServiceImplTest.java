/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.service.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.framework.custom.attr.CustomAttribute;
import org.kuali.coeus.common.framework.custom.attr.CustomAttributeService;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.bo.CustomAttributeDocValue;
import org.kuali.kra.bo.CustomAttributeDocument;
import org.kuali.kra.bo.CustomAttributeDocumentTestUtilities;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.service.KRADServiceLocatorWeb;
import org.kuali.rice.krad.util.GlobalVariables;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
/**
 * This class tests CustomAttributeServiceImpl.
 */
public class CustomAttributeServiceImplTest extends KcIntegrationTestBase {


    private Map<String, CustomAttributeDocument> testCustomAttributeDocuments;

    private DocumentService documentService = null;
    private CustomAttributeService customAttributeService = null;
    private ProposalDevelopmentService proposalDevelopmentService;
    
    private static final String TEST_DOCUMENT_TYPE_CODE = "PRDV";

    @Before
    public void setUp() throws Exception {
        GlobalVariables.setUserSession(new UserSession("quickstart"));
        testCustomAttributeDocuments = CustomAttributeDocumentTestUtilities.setupTestCustomAttributeDocuments();
        documentService = KRADServiceLocatorWeb.getDocumentService();
        customAttributeService = KcServiceLocator.getService(CustomAttributeService.class);
        proposalDevelopmentService = KcServiceLocator.getService(ProposalDevelopmentService.class);
    }

    @After
    public void tearDown() throws Exception {
        GlobalVariables.setUserSession(null);
        testCustomAttributeDocuments = null;
        documentService = null;
        customAttributeService = null;
    }


    @Test public void testGetDefaultCustomAttributesForDocumentType() throws Exception {
        Map<String, CustomAttributeDocument>customAttributeDocuments = customAttributeService.getDefaultCustomAttributeDocuments(TEST_DOCUMENT_TYPE_CODE, new ArrayList<CustomAttributeDocValue>());
        assertNotNull(customAttributeDocuments);
        assertEquals(testCustomAttributeDocuments.size(), customAttributeDocuments.size());

        for(Map.Entry<String, CustomAttributeDocument> testCustomAttributeDocumentEntry: testCustomAttributeDocuments.entrySet()) {
            CustomAttributeDocument testCustomAttributeDocument = testCustomAttributeDocumentEntry.getValue();
            CustomAttributeDocument customAttributeDocument = customAttributeDocuments.get(testCustomAttributeDocument.getCustomAttributeId().toString());
            assertNotNull(customAttributeDocument);
            assertEquals(testCustomAttributeDocument.getDocumentTypeName(), customAttributeDocument.getDocumentTypeName());
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
        Map<String, CustomAttributeDocument>customAttributeDocuments = customAttributeService.getDefaultCustomAttributeDocuments(TEST_DOCUMENT_TYPE_CODE, new ArrayList<CustomAttributeDocValue>());
        assertNotNull(customAttributeDocuments);
        assertNotNull(testCustomAttributeDocuments);
        assertEquals(testCustomAttributeDocuments.size(), customAttributeDocuments.size());

        for(Map.Entry<String, CustomAttributeDocument> testCustomAttributeDocumentEntry: testCustomAttributeDocuments.entrySet()) {
            CustomAttributeDocument testCustomAttributeDocument = testCustomAttributeDocumentEntry.getValue();
            CustomAttributeDocument customAttributeDocument = customAttributeDocuments.get(testCustomAttributeDocument.getCustomAttributeId().toString());
            assertNotNull(customAttributeDocument);
            assertEquals(testCustomAttributeDocument.getDocumentTypeName(), customAttributeDocument.getDocumentTypeName());
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
            assertEquals(testCustomAttributeDocument.getDocumentTypeName(), customAttributeDocument.getDocumentTypeName());
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
            assertEquals(testCustomAttributeDocument.getDocumentTypeName(), customAttributeDocument.getDocumentTypeName());
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
        List<String> properties = new ArrayList<String>();
        properties.add("degreeCode");
        properties.add("degreeLevel");
        properties.add("description");
        List lookupReturnFields = customAttributeService.getLookupReturns("org.kuali.kra.bo.DegreeType");
        assertEquals(properties.size(), lookupReturnFields.size());

        for(Object returnField : lookupReturnFields) {
            assertTrue(properties.contains(returnField));
        }
    }
    
    @Test
    public void testGetLookupReturnsForAjaxCall() throws Exception {
        String properties = ",degreeCode;Degree Code,degreeLevel;Degree Level,description;Description";
        String lookupReturnFields = customAttributeService.getLookupReturnsForAjaxCall("org.kuali.kra.bo.DegreeType");
        assertEquals(properties,lookupReturnFields);
    }

    private ProposalDevelopmentDocument getDocument() throws WorkflowException {
        ProposalDevelopmentDocument document = (ProposalDevelopmentDocument) documentService.getNewDocument("ProposalDevelopmentDocument");
        document.initialize();

        Date requestedStartDateInitial = new Date(System.currentTimeMillis());
        Date requestedEndDateInitial = new Date(System.currentTimeMillis());

        setBaseDocumentFields(document, "ProposalDevelopmentDocumentTest test doc", "005770", "project title", requestedStartDateInitial, requestedEndDateInitial, "1", "1", "000001", "000120");

        documentService.saveDocument(document);

        ProposalDevelopmentDocument savedDocument = (ProposalDevelopmentDocument) documentService.getByDocumentHeaderId(document.getDocumentNumber());

        return savedDocument;
    }

    /**
     * This method sets the base/required document fields
     * @param document ProposalDevelopmentDocument to set fields for
     * @param title String title to set
     * @param requestedStartDateInitial Date start date to set
     * @param requestedEndDateInitial Date end date to set
     * @param activityTypeCode String activity type code to set
     * @param proposalTypeCode String proposal type code to set
     * @param ownedByUnit String owned-by unit to set
     */
    private void setBaseDocumentFields(ProposalDevelopmentDocument document, String description, String sponsorCode, String title, Date requestedStartDateInitial, Date requestedEndDateInitial, String activityTypeCode, String proposalTypeCode, String ownedByUnit, String primeSponsorCode) {
        document.getDocumentHeader().setDocumentDescription(description);
        document.getDevelopmentProposal().setSponsorCode(sponsorCode);
        document.getDevelopmentProposal().setTitle(title);
        document.getDevelopmentProposal().setRequestedStartDateInitial(requestedStartDateInitial);
        document.getDevelopmentProposal().setRequestedEndDateInitial(requestedEndDateInitial);
        document.getDevelopmentProposal().setActivityTypeCode(activityTypeCode);
        document.getDevelopmentProposal().setProposalTypeCode(proposalTypeCode);
        document.getDevelopmentProposal().setOwnedByUnitNumber(ownedByUnit);
        document.getDevelopmentProposal().setPrimeSponsorCode(primeSponsorCode);

        proposalDevelopmentService.initializeUnitOrganizationLocation(document);
        proposalDevelopmentService.initializeProposalSiteNumbers(document);

    }

}
