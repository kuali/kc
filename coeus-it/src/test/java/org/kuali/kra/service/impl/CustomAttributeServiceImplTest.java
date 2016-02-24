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
package org.kuali.kra.service.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.framework.custom.attr.CustomAttribute;
import org.kuali.coeus.common.framework.custom.attr.CustomAttributeDocValue;
import org.kuali.coeus.common.framework.custom.attr.CustomAttributeDocument;
import org.kuali.coeus.common.framework.custom.attr.CustomAttributeService;
import org.kuali.coeus.common.framework.person.attr.DegreeType;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.bo.CustomAttributeDocumentTestUtilities;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.service.KRADServiceLocatorWeb;
import org.kuali.rice.krad.util.GlobalVariables;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
/**
 * This class tests CustomAttributeServiceImpl.
 */
public class CustomAttributeServiceImplTest extends KcIntegrationTestBase {

    private DocumentService documentService = null;
    private CustomAttributeService customAttributeService = null;
    private ProposalDevelopmentService proposalDevelopmentService;
    private BusinessObjectService businessObjectService;
    
    private static final String TEST_DOCUMENT_TYPE_CODE = "PRDV";

    @Before
    public void setUp() throws Exception {
        GlobalVariables.setUserSession(new UserSession("quickstart"));
        documentService = KRADServiceLocatorWeb.getDocumentService();
        customAttributeService = KcServiceLocator.getService(CustomAttributeService.class);
        proposalDevelopmentService = KcServiceLocator.getService(ProposalDevelopmentService.class);
        businessObjectService = KcServiceLocator.getService(BusinessObjectService.class);
    }

    @After
    public void tearDown() throws Exception {
        GlobalVariables.setUserSession(null);
        documentService = null;
        customAttributeService = null;
    }


    @Test public void testGetDefaultCustomAttributesForDocumentType() throws Exception {
    	Collection<CustomAttributeDocument> dbCustomDataDocuments = businessObjectService.findMatching(CustomAttributeDocument.class, Collections.singletonMap("documentTypeName", TEST_DOCUMENT_TYPE_CODE));
        Map<String, CustomAttributeDocument>customAttributeDocuments = customAttributeService.getDefaultCustomAttributeDocuments(TEST_DOCUMENT_TYPE_CODE, new ArrayList<CustomAttributeDocValue>());
        assertNotNull(customAttributeDocuments);
        assertEquals(dbCustomDataDocuments.size(), customAttributeDocuments.size());

        for(CustomAttributeDocument testCustomAttributeDocument : dbCustomDataDocuments) {
            CustomAttributeDocument customAttributeDocument = customAttributeDocuments.get(testCustomAttributeDocument.getId().toString());
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
    	Collection<CustomAttributeDocument> dbCustomDataDocuments = businessObjectService.findMatching(CustomAttributeDocument.class, Collections.singletonMap("documentTypeName", TEST_DOCUMENT_TYPE_CODE));
        Map<String, CustomAttributeDocument>customAttributeDocuments = customAttributeService.getDefaultCustomAttributeDocuments(TEST_DOCUMENT_TYPE_CODE, new ArrayList<CustomAttributeDocValue>());
        assertNotNull(customAttributeDocuments);
        assertEquals(dbCustomDataDocuments.size(), customAttributeDocuments.size());

        for(CustomAttributeDocument testCustomAttributeDocument : dbCustomDataDocuments) {
            CustomAttributeDocument customAttributeDocument = customAttributeDocuments.get(testCustomAttributeDocument.getId().toString());
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
    	Collection<CustomAttributeDocument> dbCustomDataDocuments = businessObjectService.findMatching(CustomAttributeDocument.class, Collections.singletonMap("documentTypeName", TEST_DOCUMENT_TYPE_CODE));
        ProposalDevelopmentDocument document = (ProposalDevelopmentDocument) documentService.getNewDocument(ProposalDevelopmentDocument.class);
        document.initialize();

        Map<String, CustomAttributeDocument>customAttributeDocuments = document.getCustomAttributeDocuments();
        assertNotNull(customAttributeDocuments);
        assertEquals(dbCustomDataDocuments.size(), customAttributeDocuments.size());

        for(CustomAttributeDocument testCustomAttributeDocument : dbCustomDataDocuments) {
            CustomAttributeDocument customAttributeDocument = customAttributeDocuments.get(testCustomAttributeDocument.getId().toString());
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
    	Collection<CustomAttributeDocument> dbCustomDataDocuments = businessObjectService.findMatching(CustomAttributeDocument.class, Collections.singletonMap("documentTypeName", TEST_DOCUMENT_TYPE_CODE));
        ProposalDevelopmentDocument document = getDocument();

        Map<String, CustomAttributeDocument>customAttributeDocuments = document.getCustomAttributeDocuments();
        assertNotNull(customAttributeDocuments);
        assertEquals(dbCustomDataDocuments.size(), customAttributeDocuments.size());

        for(CustomAttributeDocument testCustomAttributeDocument : dbCustomDataDocuments) {
            CustomAttributeDocument customAttributeDocument = customAttributeDocuments.get(testCustomAttributeDocument.getId().toString());
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
        properties.add("code");
        properties.add("degreeLevel");
        properties.add("description");
        List lookupReturnFields = customAttributeService.getLookupReturns(DegreeType.class.getName());
        assertEquals(properties.size(), lookupReturnFields.size());

        for(Object returnField : lookupReturnFields) {
            assertTrue(properties.contains(returnField));
        }
    }
    
    @Test
    public void testGetLookupReturnsForAjaxCall() throws Exception {
        String properties = ",code;Degree Code,degreeLevel;Degree Level,description;Description";
        String lookupReturnFields = customAttributeService.getLookupReturnsForAjaxCall(DegreeType.class.getName());
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
