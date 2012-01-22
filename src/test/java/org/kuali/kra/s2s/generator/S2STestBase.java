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
package org.kuali.kra.s2s.generator;

import java.util.ArrayList;
import java.util.Calendar;

import org.apache.xmlbeans.XmlObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.service.ProposalDevelopmentService;
import org.kuali.kra.s2s.generator.bo.AttachmentData;
import org.kuali.kra.s2s.service.S2SValidatorService;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.rice.kns.util.AuditError;
import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.bo.DocumentHeader;
import org.kuali.rice.krad.bo.PersistableBusinessObjectBase;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;

/**
 * This is the base class for all generator Junit test classes.
 */
public abstract class S2STestBase<T> extends KcUnitTestBase {
    private S2SBaseFormGenerator generatorObject;
    private static ProposalDevelopmentDocument document;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        initializeApp();
        GlobalVariables.setUserSession(new UserSession("quickstart"));
    }

    @After
    public void tearDown() throws Exception {
        GlobalVariables.setUserSession(null);
        super.tearDown();
    }

    protected abstract void prepareData(ProposalDevelopmentDocument document) throws Exception;
    protected abstract Class<T> getFormGeneratorClass();

    @Test
    public void testValidateForm() throws Exception {
        prepareData(document);
        saveBO(document);
        ArrayList<AuditError> errors = new ArrayList<AuditError>();
        generatorObject.setAuditErrors(errors);
        generatorObject.setAttachments(new ArrayList<AttachmentData>());
        XmlObject object=generatorObject.getFormObject(document);
        getService(S2SValidatorService.class).validate(generatorObject.getFormObject(document), errors);
        assertTrue(errors.isEmpty());
    }

    private void saveProposalDocument(ProposalDevelopmentDocument pd) throws Exception {
        pd.setUpdateUser("quickst");
        pd.setUpdateTimestamp(new java.sql.Timestamp(Calendar.getInstance().getTimeInMillis()));
        DocumentHeader docHeader = pd.getDocumentHeader();
        docHeader.setDocumentDescription("Test s2s service description");
        String docNumber = docHeader.getDocumentNumber();
        assertNotNull(docNumber);
        assertNotNull(pd.getDevelopmentProposal());
        assertEquals(1, pd.getDevelopmentProposalList().size());    
        getDocumentService().saveDocument(pd);
    }

    private ProposalDevelopmentDocument initializeDocument() throws Exception {
        ProposalDevelopmentDocument pd = (ProposalDevelopmentDocument) getDocumentService().getNewDocument("ProposalDevelopmentDocument");
        ProposalDevelopmentService pdService = getService(ProposalDevelopmentService.class);
        pdService.initializeUnitOrganizationLocation(pd);
        pdService.initializeProposalSiteNumbers(pd);
        return pd;
    }

    private DevelopmentProposal initializeDevelopmentProposal(ProposalDevelopmentDocument pd) {
        DevelopmentProposal developmentProposal = pd.getDevelopmentProposal();
        developmentProposal.setPrimeSponsorCode("000120");
        developmentProposal.setActivityTypeCode("1");
        developmentProposal.refreshReferenceObject("activityType");
        developmentProposal.setSponsorCode("000162");
        developmentProposal.setOwnedByUnitNumber("000001");
        developmentProposal.refreshReferenceObject("ownedByUnit");
        developmentProposal.setProposalTypeCode("1");
        developmentProposal.setCreationStatusCode("1");
        developmentProposal.setApplicantOrganizationId("000001");
        developmentProposal.setPerformingOrganizationId("000001");
        developmentProposal.setNoticeOfOpportunityCode("1");
        developmentProposal.setRequestedStartDateInitial(new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
        developmentProposal.setRequestedEndDateInitial(new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
        developmentProposal.setTitle("Test s2s service title");
        developmentProposal.setDeadlineType("P");
        developmentProposal.setDeadlineDate(new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
        developmentProposal.setNsfCode("J.05");
        return developmentProposal;
    }

    /**
     * This method...
     * @param pd
     */
    protected void saveBO(PersistableBusinessObjectBase bo) {
        getService(BusinessObjectService.class).save(bo);
    }

    private void initializeApp() throws Exception {
        try {
            generatorObject = (S2SBaseFormGenerator) getFormGeneratorClass().newInstance();
            document = initializeDocument();
            initializeDevelopmentProposal(document);
            saveProposalDocument(document);
            document = (ProposalDevelopmentDocument) getDocumentService().getByDocumentHeaderId(document.getDocumentHeader().getDocumentNumber());
            assertNotNull(document.getDevelopmentProposal());
        } catch (Throwable e) {
            e.printStackTrace();
            tearDown();
            throw new RuntimeException(e);
        }
    }
}
