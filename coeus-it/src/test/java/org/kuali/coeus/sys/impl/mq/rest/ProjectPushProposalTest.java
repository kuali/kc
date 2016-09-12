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
package org.kuali.coeus.sys.impl.mq.rest;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.load.Dereferencing;
import com.github.fge.jsonschema.core.load.configuration.LoadingConfiguration;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.coi.framework.Project;
import org.kuali.coeus.coi.framework.ProjectRetrievalService;
import org.kuali.coeus.common.framework.custom.attr.CustomAttributeDocValue;
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.coeus.propdev.impl.copy.ProposalCopyService;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentService;
import org.kuali.coeus.propdev.impl.location.ProposalSite;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;
import org.kuali.coeus.propdev.impl.s2s.S2sSubmissionService;
import org.kuali.coeus.sys.framework.mq.rest.RestRequest;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.service.KRADServiceLocatorWeb;

import javax.jms.ObjectMessage;
import java.sql.Date;

public class ProjectPushProposalTest extends ProjectPushTestBase {
    private static final String PROP_DEV_PROJECT_RETRIEVAL_SERVICE = "propDevProjectRetrievalService";
    ProposalCopyService proposalCopyService;
    DocumentService documentService;

    String SPONSOR_CODE = "000162";
    String ACTIVITY_TYPE_CODE = "1";
    String PROPOSAL_TYPE_CODE = "1";
    String PRIME_SPONSOR_CODE = "000120";
    String ORIGINAL_LEAD_UNIT = "000001";
    public static final String PERSON_ID = "10000000001";
    public static final String FIRST_NAME = "firstname";
    public static final String LAST_NAME = "lastName";

    private ProposalDevelopmentDocument proposalDocument;
    private ProjectRetrievalService propDevProjectRetrievalService;

    @Before
	public void setUp() throws Exception {
        documentService = KRADServiceLocatorWeb.getDocumentService();
        saveDoc();
    }

    private ProposalDevelopmentDocument createProposal() throws Exception {
		ProposalDevelopmentDocument document = getNewProposalDevelopmentDocument();
		Date requestedStartDateInitial = new Date(System.currentTimeMillis());
		Date requestedEndDateInitial = new Date(System.currentTimeMillis());

		document.getDocumentHeader()
				.setDocumentDescription("Original document");
		document.getDevelopmentProposal().setSponsorCode(SPONSOR_CODE);
		document.getDevelopmentProposal().setTitle("project title");
		document.getDevelopmentProposal().setRequestedStartDateInitial(
                requestedStartDateInitial);
		document.getDevelopmentProposal().setRequestedEndDateInitial(
                requestedEndDateInitial);
		document.getDevelopmentProposal().setActivityTypeCode(ACTIVITY_TYPE_CODE);
		document.getDevelopmentProposal().setProposalTypeCode(PROPOSAL_TYPE_CODE);
		document.getDevelopmentProposal().setOwnedByUnitNumber(ORIGINAL_LEAD_UNIT);
		document.getDevelopmentProposal().setPrimeSponsorCode(PRIME_SPONSOR_CODE);

        getProposalDevelopmentService().initializeUnitOrganizationLocation(document);
        getProposalDevelopmentService().initializeProposalSiteNumbers(document);

        document.getDevelopmentProposal().getProposalYnqs();
        createCustomDocument(document);

		return document;
	}

    private void createEmpProposalPerson(DevelopmentProposal developmentProposal, String role, String firstName, String lastName, int proposalPersonNumber) {
        ProposalPerson person = new ProposalPerson();
        person.setProposalPersonNumber(proposalPersonNumber);
        person.setProposalPersonRoleId(role);
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setMiddleName("middleName");
        setPersonData(person);
        person.setRolodexId(null);
        person.setDevelopmentProposal(developmentProposal);
        developmentProposal.getProposalPersons().add(person);
        person.setPersonId(PERSON_ID);
    }

    public void setPersonData(ProposalPerson person) {
        person.setOfficePhone("321-321-1228");
        person.setEmailAddress("kcnotification@gmail.com");
        person.setFaxNumber("321-321-1289");
        person.setAddressLine1("addressLine1");
        person.setAddressLine2("addressLine2");
        person.setCity("Coeus");
        person.setPostalCode("53421");
        person.setCounty("UNITED STATES");
        person.setCountryCode("USA");
        person.setState("MA");
        person.setDirectoryTitle("directoryTitle");
        person.setDivision("division");
    }

    protected void createCustomDocument(ProposalDevelopmentDocument document) {
        CustomAttributeDocValue newDocValue = new CustomAttributeDocValue();
        newDocValue.setDocumentNumber(document.getDocumentNumber());
        newDocValue.setId(3L);
        newDocValue.setValue("34");
        document.getCustomDataList().add(newDocValue);
    }

    protected BusinessObjectService getBusinessObjectService() {
        return KcServiceLocator.getService(BusinessObjectService.class);
    }

    protected void setOrg(ProposalDevelopmentDocument document) {

        Unit ownedByUnit = document.getDevelopmentProposal().getOwnedByUnit();
        if (ownedByUnit != null) {
            String unitOrganizationId = ownedByUnit.getOrganizationId();
            for (ProposalSite proposalSite: document.getDevelopmentProposal().getProposalSites()) {
                // set location name to default from Organization
                proposalSite.setOrganizationId(unitOrganizationId);
                proposalSite.refreshReferenceObject("organization");
                proposalSite.setLocationName(proposalSite.getOrganization().getOrganizationName());
                proposalSite.setRolodexId(proposalSite.getOrganization().getContactAddressId());
                proposalSite.refreshReferenceObject("rolodex");
                proposalSite.initializeDefaultCongressionalDistrict();
            }
        }
    }

	@After
	public void tearDown() throws Exception {
		proposalDocument = null;
		proposalCopyService = null;
	}

    protected void saveDoc() throws Exception {
        proposalDocument = (ProposalDevelopmentDocument) getDocumentService().saveDocument(createProposal());
        createEmpProposalPerson(proposalDocument.getDevelopmentProposal(), Constants.PRINCIPAL_INVESTIGATOR_ROLE, FIRST_NAME, LAST_NAME, 1);
        getDocumentService().saveDocument(proposalDocument);
    }

    @Test
	public void test() throws Exception {
        final Project developmentProposalProject = getProjectRetrievalService().retrieveProject(getDocumentIdentifier());
        if(isCoiEnabled()) {
            ObjectMessage message = getMessageFromProject(developmentProposalProject);
            getRestMessageConsumer().onMessage(message);
        }
    }

    @Test
    public void testSchema() throws Exception {
        final Project developmentProposalProject = getProjectRetrievalService().retrieveProject(proposalDocument.getDevelopmentProposal().getProposalNumber());
        RestRequest request = (RestRequest) getMessageFromProject(developmentProposalProject).getObject();
        JsonNode projectData = JsonLoader.fromString(request.getBody());
        JsonNode projectSchema = JsonLoader.fromFile(getFile());
        final LoadingConfiguration cfg = LoadingConfiguration.newBuilder()
                .dereferencing(Dereferencing.INLINE).freeze();
        final JsonSchemaFactory factory = JsonSchemaFactory.newBuilder()
                .setLoadingConfiguration(cfg).freeze();
        final JsonSchema schema = factory.getJsonSchema(projectSchema);
        ProcessingReport report;
        report = schema.validate(projectData);
        Assert.assertTrue(report.isSuccess());
    }

    protected DocumentService getDocumentService() {
        return KcServiceLocator.getService(DocumentService.class);
    }

    protected ProposalDevelopmentDocument getNewProposalDevelopmentDocument() throws WorkflowException {
        return (ProposalDevelopmentDocument) documentService.getNewDocument("ProposalDevelopmentDocument");
    }

    protected ProposalDevelopmentService getProposalDevelopmentService() {
        return KcServiceLocator.getService(ProposalDevelopmentService.class);
    }

    public S2sSubmissionService getS2sSubmissionService() {
        return KcServiceLocator.getService(S2sSubmissionService.class);
    }

    @Override
    public String getDocumentIdentifier() {
        return proposalDocument.getDevelopmentProposal().getProposalNumber();
    }

    public ProjectRetrievalService getProjectRetrievalService() {
        if (propDevProjectRetrievalService == null) {
            propDevProjectRetrievalService = KcServiceLocator.getService(PROP_DEV_PROJECT_RETRIEVAL_SERVICE);
        }
        return propDevProjectRetrievalService;
    }

}
