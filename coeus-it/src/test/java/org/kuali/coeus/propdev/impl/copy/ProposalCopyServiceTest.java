package org.kuali.coeus.propdev.impl.copy;

import org.apache.commons.lang3.StringUtils;
import org.apache.cxf.common.util.CollectionUtils;
import org.jmock.Mockery;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.proposaldevelopment.rules.ProposalDevelopmentRuleTestBase;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.bo.Note;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.service.KRADServiceLocatorWeb;
import org.kuali.rice.krad.service.KualiRuleService;
import org.springframework.mock.web.MockMultipartFile;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class ProposalCopyServiceTest extends ProposalDevelopmentRuleTestBase {
	ProposalCopyService proposalCopyService;
	Mockery context;
	ProposalCopyCriteria criteria;
	KualiRuleService kualiRuleService;
	ProposalDevelopmentDocument proposalDocument;

    String SPONSOR_CODE = "000162";
    String ACTIVITY_TYPE_CODE = "1";
    String PROPOSAL_TYPE_CODE = "1";
    String PRIME_SPONSOR_CODE = "000120";
    String ORIGINAL_LEAD_UNIT = "000001";
    String NEW_LEAD_UNIT = "BL-IIDC";
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

        // test cong district
        assertEquals(copiedSites.get(0).getDefaultCongressionalDistrict().getCongressionalDistrict(), "MA-008");

        assertTrue(copiedDocument.getDevelopmentProposal().getNarratives().size() == 0);

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
        copiedDocument.getDevelopmentProposal().setNarratives(null);
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


}
