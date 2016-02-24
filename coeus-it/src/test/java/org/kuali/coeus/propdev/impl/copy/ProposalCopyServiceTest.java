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
package org.kuali.coeus.propdev.impl.copy;

import java.sql.Date;
import java.util.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.framework.custom.attr.CustomAttributeDocValue;
import org.kuali.coeus.common.framework.org.Organization;
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.coeus.common.framework.unit.UnitService;
import org.kuali.coeus.common.questionnaire.framework.answer.QuestionnaireAnswerService;
import org.kuali.coeus.propdev.impl.attachment.Narrative;
import org.kuali.coeus.propdev.impl.attachment.NarrativeStatus;
import org.kuali.coeus.propdev.impl.attachment.NarrativeType;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentService;
import org.kuali.coeus.propdev.impl.location.ProposalSite;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;
import org.kuali.coeus.propdev.impl.s2s.S2sOppForms;
import org.kuali.coeus.propdev.impl.s2s.S2sOpportunity;
import org.kuali.coeus.propdev.impl.s2s.S2sProvider;
import org.kuali.coeus.propdev.impl.s2s.S2sSubmissionService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.proposaldevelopment.rules.ProposalDevelopmentRuleTestBase;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.bo.Note;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.service.KRADServiceLocatorWeb;
import org.springframework.mock.web.MockMultipartFile;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class ProposalCopyServiceTest extends ProposalDevelopmentRuleTestBase {
	ProposalCopyService proposalCopyService;
	ProposalDevelopmentDocument proposalDocument;

    String SPONSOR_CODE = "000162";
    String ACTIVITY_TYPE_CODE = "1";
    String PROPOSAL_TYPE_CODE = "1";
    String PRIME_SPONSOR_CODE = "000120";
    String ORIGINAL_LEAD_UNIT = "000001";
    String NEW_LEAD_UNIT = "BL-IIDC";
    String OPP_ID = "PA-C-R03";
    String COMP_ID = "FORMS-C";
    String INS_URL = "insUrl";
    String SCHEMA_URL = "http://at07apply.grants.gov/apply/opportunities/schemas/applicant/oppPA-C-R03-cfda93.838-cidFORMS-C.xsd";
    String CFDA = "93.838";
    String PROVIDER_CODE = "1";
    String SUB_TYPE_CODE = "2";
    String AGENCY_CONTACT_INFO= "contactInfo";

    private ProposalDevelopmentDocument oldDocument;

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
        List<Narrative> narratives = new ArrayList<Narrative>();
        narratives.add(getNewNarrative(document.getDevelopmentProposal()));
        document.getDevelopmentProposal().setNarratives(narratives);
        addNote(document);

		return document;
	}

    protected S2sOpportunity getS2s(DevelopmentProposal proposal) throws DatatypeConfigurationException {
        S2sOpportunity opportunity = new S2sOpportunity();
        opportunity.setAgencyContactInfo(AGENCY_CONTACT_INFO);
        opportunity.setSchemaUrl(SCHEMA_URL);
        opportunity.setCompetetionId(COMP_ID);
        opportunity.setInstructionUrl(INS_URL);
        opportunity.setOpportunityId(OPP_ID);
        opportunity.setOpportunity("bogus opportunity character data");
        opportunity.setCfdaNumber(CFDA);
        opportunity.setS2sSubmissionTypeCode(SUB_TYPE_CODE);
        opportunity.setProviderCode(PROVIDER_CODE);
        opportunity.setMultiProject(false);
        opportunity.setDevelopmentProposal(proposal);

        GregorianCalendar c = new GregorianCalendar();
        c.setTime(new Date(System.currentTimeMillis()));
        XMLGregorianCalendar date1 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);


        opportunity.setClosingDate(date1.toGregorianCalendar());
        opportunity.setOpeningDate(date1.toGregorianCalendar());
        Boolean mandatoryFormNotAvailable = false;
        opportunity.setS2sProvider(getDataObjectService().find(S2sProvider.class, opportunity.getProviderCode()));
        List<S2sOppForms> s2sOppForms = getS2sSubmissionService().parseOpportunityForms(opportunity);
        if(s2sOppForms!=null){
            for(S2sOppForms s2sOppForm:s2sOppForms){
                if(s2sOppForm.getMandatory() && !s2sOppForm.getAvailable()){
                    mandatoryFormNotAvailable = true;
                    break;
                }
            }
        }
        if(!mandatoryFormNotAvailable) {
            Collections.sort(s2sOppForms, (arg0, arg1) -> {
                int result = arg0.getMandatory().compareTo(arg1.getMandatory()) * -1;
                if (result == 0) {
                    result = arg0.getFormName().compareTo(arg1.getFormName());
                }
                return result;
            });
            opportunity.setS2sOppForms(s2sOppForms);
        }
        return opportunity;
    }

    protected void addNote(ProposalDevelopmentDocument proposalDocument) {
        Note note = new Note();
        note.setNoteText("testNote");
        note.setNoteTypeCode("BO");
        note.setNoteTopicText("topic");
        List<Note> notes = new ArrayList<Note>();
        notes.add(note);

        proposalDocument.setNotes(notes);
    }

    protected ProposalPerson getPersonnel() {
        ProposalPerson newProposalPerson = new ProposalPerson();
        newProposalPerson.setPersonId("10000000008");
        newProposalPerson.setFullName("Allyson Cate");
        newProposalPerson.setUserName("cate");
        newProposalPerson.setProposalPersonRoleId("PI");
        return newProposalPerson;
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
        oldDocument = (ProposalDevelopmentDocument) getDocumentService().saveDocument(createProposal());
        oldDocument.getDevelopmentProposal().setS2sOpportunity(getS2s(oldDocument.getDevelopmentProposal()));
        getDocumentService().saveDocument(oldDocument);
    }

	@Test
	public void testCopySameUnitNoAttachment() throws Exception {

        ProposalCopyCriteria criteria = new ProposalCopyCriteria();
        criteria.setLeadUnitNumber(ORIGINAL_LEAD_UNIT);
	    ProposalDevelopmentDocument copiedDocument = getProposalCopyService().copyProposal(oldDocument, criteria);

        assertEquals(copiedDocument.getDevelopmentProposal().getOwnedByUnitNumber(), ORIGINAL_LEAD_UNIT);
        assertEquals(copiedDocument.getDevelopmentProposal().getActivityTypeCode(), ACTIVITY_TYPE_CODE);
        assertEquals(copiedDocument.getDevelopmentProposal().getSponsorCode(), SPONSOR_CODE);
        assertEquals(copiedDocument.getDevelopmentProposal().getProposalTypeCode(), PROPOSAL_TYPE_CODE);
        assertEquals(copiedDocument.getDevelopmentProposal().getPrimeSponsorCode(), PRIME_SPONSOR_CODE);

        // test org
        List<ProposalSite> copiedSites = copiedDocument.getDevelopmentProposal().getProposalSites();
        assertEquals(copiedSites.get(0).getOrganization().getOrganizationId(), "000001");
        assertEquals(copiedSites.get(1).getOrganization().getOrganizationId(), "000001");

        Organization organization = (Organization)getDataObjectService().find(Organization.class, copiedSites.get(0).getOrganization().getOrganizationId());

        // test cong district
        assertEquals(copiedSites.get(0).getDefaultCongressionalDistrict().getCongressionalDistrict(), organization.getCongressionalDistrict());
        assertTrue(copiedDocument.getDevelopmentProposal().getNarratives().size() == 0);

        assertTrue(copiedDocument.getDevelopmentProposal().getS2sOpportunity() != null);
        assertTrue(copiedDocument.getDevelopmentProposal().getS2sOpportunity().getOpportunityId() == OPP_ID);
        assertTrue(copiedDocument.getDevelopmentProposal().getS2sOpportunity().getS2sOppForms().size() == 11);

    }

    @Test
    public void testCopyDifferentUnit() throws Exception {
        ProposalCopyCriteria criteria = new ProposalCopyCriteria();
        criteria.setLeadUnitNumber(NEW_LEAD_UNIT);
        ProposalDevelopmentDocument copiedDocument = getProposalCopyService().copyProposal(oldDocument, criteria);

        assertEquals(copiedDocument.getDevelopmentProposal().getOwnedByUnitNumber(), NEW_LEAD_UNIT);

        List<ProposalSite> copiedSites = copiedDocument.getDevelopmentProposal().getProposalSites();
        assertEquals(copiedSites.get(0).getOrganization().getOrganizationId(), getOrgForUnit(NEW_LEAD_UNIT).getOrganizationId());
        assertEquals(copiedSites.get(1).getOrganization().getOrganizationId(), getOrgForUnit(NEW_LEAD_UNIT).getOrganizationId());
        assertNotNull(copiedSites.get(0).getDefaultCongressionalDistrict().getCongressionalDistrict(), getOrgForUnit(NEW_LEAD_UNIT).getCongressionalDistrict());
    }

    @Test
    public void testCopyNarrative() throws Exception {
        ProposalCopyCriteria criteria = new ProposalCopyCriteria();
        criteria.setLeadUnitNumber(ORIGINAL_LEAD_UNIT);
        criteria.setIncludeAttachments(true);
        ProposalDevelopmentDocument copiedDocument = getProposalCopyService().copyProposal(oldDocument, criteria);
        assertTrue(copiedDocument.getDevelopmentProposal().getNarratives().size() == 1);

    }

    @Test
    public void testDeleteNarrative() throws Exception{
        ProposalCopyCriteria criteria = new ProposalCopyCriteria();
        criteria.setLeadUnitNumber(ORIGINAL_LEAD_UNIT);
        criteria.setIncludeAttachments(true);
        ProposalDevelopmentDocument copiedDocument = getProposalCopyService().copyProposal(oldDocument, criteria);
        copiedDocument.getDevelopmentProposal().setNarratives(new ArrayList<Narrative>());
        getDocumentService().saveDocument(copiedDocument);
        assertTrue(copiedDocument.getDevelopmentProposal().getNarratives().size() == 0);
        assertTrue(oldDocument.getDevelopmentProposal().getNarratives().size() == 1);
    }

    protected Narrative getNewNarrative(DevelopmentProposal proposal) throws Exception {
        MockMultipartFile mockMultipartFile = new MockMultipartFile(
                "test.txt",                //filename
                "Hello World".getBytes()); //content
        Narrative narrative = new Narrative();
        narrative.setName("test");
        narrative.setModuleStatusCode(Constants.NARRATIVE_MODULE_STATUS_INCOMPLETE);
        Map map = new HashMap();
        map.put("code", "1");
        List<NarrativeType> types = (List<NarrativeType>) getBusinessObjectService().findMatching(NarrativeType.class, map);
        narrative.setNarrativeType(types.get(0));
        narrative.setNarrativeTypeCode(types.get(0).getCode());
        map = new HashMap();
        map.put("code", "C");
        List<NarrativeStatus> status = (List<NarrativeStatus>) getBusinessObjectService().findMatching(NarrativeStatus.class, map);
        narrative.setNarrativeStatus(status.get(0));
        narrative.setModuleNumber(1);
        narrative.setModuleSequenceNumber(1);
        narrative.init(mockMultipartFile);
        narrative.getNarrativeAttachment().setName("test");
        narrative.setInstitutionalAttachmentTypeCode(types.get(0).getCode());
        narrative.setDevelopmentProposal(proposal);

        return narrative;
    }

    protected Organization getOrgForUnit(String unitNumber) {
       UnitService unitService = KcServiceLocator.getService(UnitService.class);
       Unit unit = unitService.getUnit(unitNumber);
       return unit.getOrganization();
    }


    protected DocumentService getDocumentService() {
        return KcServiceLocator.getService(DocumentService.class);
    }

    protected ProposalCopyService getProposalCopyService() {
        return KcServiceLocator.getService(ProposalCopyService.class);
    }


    protected ProposalDevelopmentDocument getNewProposalDevelopmentDocument() throws WorkflowException {
        return (ProposalDevelopmentDocument) documentService.getNewDocument("ProposalDevelopmentDocument");
    }

    protected ProposalDevelopmentService getProposalDevelopmentService() {
        return KcServiceLocator.getService(ProposalDevelopmentService.class);
    }

    protected QuestionnaireAnswerService getQuestionnaireAnswerService() {
        return KcServiceLocator.getService(QuestionnaireAnswerService.class);
    }

    protected DataObjectService getDataObjectService() {
        return KcServiceLocator.getService(DataObjectService.class);
    }


    public S2sSubmissionService getS2sSubmissionService() {
        return KcServiceLocator.getService(S2sSubmissionService.class);
    }
}
