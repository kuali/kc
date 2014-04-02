/*
 * Copyright 2005-2014 The Kuali Foundation
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

import org.apache.xmlbeans.XmlObject;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentService;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.s2s.generator.bo.AttachmentData;
import org.kuali.kra.s2s.service.S2SValidatorService;
import org.kuali.kra.s2s.util.AuditError;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.bo.DocumentHeader;
import org.kuali.rice.krad.bo.PersistableBusinessObject;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.service.KRADServiceLocatorWeb;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.ArrayList;
import java.util.Calendar;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.kuali.coeus.sys.framework.service.KcServiceLocator.getService;
/**
 * This is the base class for all generator Junit test classes.
 */
public abstract class S2STestBase<T> extends KcIntegrationTestBase {
    private S2SBaseFormGenerator generatorObject;

    @Before
    public void setUp() throws Exception {
        GlobalVariables.setUserSession(new UserSession("quickstart"));
    }

    @After
    public void tearDown() throws Exception {
        GlobalVariables.setUserSession(null);
    }

    protected abstract void prepareData(ProposalDevelopmentDocument document) throws Exception;
    protected abstract Class<T> getFormGeneratorClass();

    @Test
    public void testValidateForm() throws Exception {
        GlobalVariables.setUserSession(new UserSession("quickstart"));
        ProposalDevelopmentDocument document = initializeApp();

        prepareData(document);
        document = saveBO(document);
        ArrayList<AuditError> errors = new ArrayList<AuditError>();
        generatorObject.setAuditErrors(errors);
        generatorObject.setAttachments(new ArrayList<AttachmentData>());
        XmlObject object=generatorObject.getFormObject(document);
        getService(S2SValidatorService.class).validate(object, errors);
        for (AuditError auditError : errors) {
            assertNull(auditError.getMessageKey()+":"+auditError.getErrorKey(),auditError.getErrorKey());
        }
    }

    private void saveProposalDocument(ProposalDevelopmentDocument pd) throws Exception {
        pd.setUpdateUser("quickst");
        pd.setUpdateTimestamp(new java.sql.Timestamp(Calendar.getInstance().getTimeInMillis()));
        DocumentHeader docHeader = pd.getDocumentHeader();
        docHeader.setDocumentDescription("Test s2s service description");
        String docNumber = docHeader.getDocumentNumber();
        assertNotNull(docNumber);
        assertNotNull(pd.getDevelopmentProposal());
        KRADServiceLocatorWeb.getDocumentService().saveDocument(pd);
    }

    private ProposalDevelopmentDocument initializeDocument() throws Exception {
        ProposalDevelopmentDocument pd = (ProposalDevelopmentDocument) KRADServiceLocatorWeb.getDocumentService().getNewDocument("ProposalDevelopmentDocument");
        Assert.assertNotNull(pd.getDocumentHeader().getWorkflowDocument());
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

    protected ProposalDevelopmentDocument saveBO(Document bo) throws Exception {
        return (ProposalDevelopmentDocument) getService(DocumentService.class).saveDocument(bo);
    }

    protected void saveBO(PersistableBusinessObject bo) throws Exception {
        getService(DataObjectService.class).save(bo);
    }

    private ProposalDevelopmentDocument initializeApp() throws Exception {
        try {
            generatorObject = (S2SBaseFormGenerator) getFormGeneratorClass().newInstance();
            ProposalDevelopmentDocument document = initializeDocument();
            initializeDevelopmentProposal(document);
            Assert.assertNotNull(document.getDocumentHeader().getWorkflowDocument());
            saveProposalDocument(document);
            document = (ProposalDevelopmentDocument) KRADServiceLocatorWeb.getDocumentService().getByDocumentHeaderId(document.getDocumentHeader().getDocumentNumber());
            assertNotNull(document.getDevelopmentProposal());
            return document;
        } catch (Throwable e) {
            LOG.error(e.getMessage(), e);
            tearDown();
            throw new RuntimeException(e);
        }
    }
}
