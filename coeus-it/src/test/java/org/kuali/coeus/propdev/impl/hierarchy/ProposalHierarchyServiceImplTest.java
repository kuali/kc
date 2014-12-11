package org.kuali.coeus.propdev.impl.hierarchy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.kuali.coeus.sys.framework.service.KcServiceLocator.getService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.common.budget.framework.personnel.HierarchyPersonnelSummary;
import org.kuali.coeus.common.budget.framework.summary.BudgetSummaryService;
import org.kuali.coeus.common.framework.auth.perm.KcAuthorizationService;
import org.kuali.coeus.common.framework.keyword.ScienceKeyword;
import org.kuali.coeus.common.framework.sponsor.Sponsor;
import org.kuali.coeus.common.framework.type.ActivityType;
import org.kuali.coeus.propdev.impl.attachment.LegacyNarrativeService;
import org.kuali.coeus.propdev.impl.attachment.Narrative;
import org.kuali.coeus.propdev.impl.attachment.NarrativeAttachment;
import org.kuali.coeus.propdev.impl.attachment.NarrativeType;
import org.kuali.coeus.propdev.impl.budget.BudgetStatus;
import org.kuali.coeus.propdev.impl.budget.ProposalBudgetService;
import org.kuali.coeus.propdev.impl.budget.ProposalDevelopmentBudgetExt;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentService;
import org.kuali.coeus.propdev.impl.keyword.PropScienceKeyword;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;
import org.kuali.coeus.propdev.impl.specialreview.ProposalSpecialReview;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.sys.framework.util.DateUtils;
import org.kuali.coeus.sys.framework.workflow.KcDocumentRejectionService;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.kew.framework.postprocessor.DocumentRouteStatusChange;
import org.kuali.rice.krad.bo.DocumentHeader;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.service.KRADServiceLocatorWeb;
import org.kuali.rice.krad.workflow.service.WorkflowDocumentService;

public class ProposalHierarchyServiceImplTest extends KcIntegrationTestBase {

	private ProposalDevelopmentDocument pdDocument;
	private DevelopmentProposal hierarchyProposal;
	private DevelopmentProposal childProposal;
	private ProposalHierarchyServiceImpl hierarchyService;
	private DataObjectService dataObjectService;

	@Before
	public void setup() throws Exception {
		dataObjectService = KcServiceLocator.getService(DataObjectService.class);
		pdDocument = initializeProposalDevelopmentDocument();
		childProposal = getChildProposal(this.pdDocument.getDevelopmentProposal());
		hierarchyProposal = setDevelopmentProposalAdditionalData(
				this.pdDocument.getDevelopmentProposal(), childProposal);
		getBudget(hierarchyProposal);
		initializeProposalHierarchyService();
	}

	@After
	public void teardown() {
		dataObjectService = null;
		pdDocument = null;
		childProposal = null;
		hierarchyProposal = null;
	}

	@Test
	public void test_createHierarchy() {
		String userId = "10000000001";
		String proposalNumber = "";
		proposalNumber = hierarchyService.createHierarchy(hierarchyProposal, userId);
		assertNotNull(proposalNumber);
		assertTrue(proposalNumber.length() > 0);
	}

	@Test
	public void test_linkToHierarchy() {
		String hierarchyBudgetTypeCode = "B";
		hierarchyService.linkToHierarchy(hierarchyProposal, childProposal, hierarchyBudgetTypeCode);
	}

	@Test
	public void test_removeFromHierarchy() {
		hierarchyService.removeFromHierarchy(childProposal);
	}

	@Test
	public void test_synchronizeChild() {
		hierarchyService.synchronizeChild(childProposal);
	}

	@Test
	public void test_synchronizeChildProposalBudget() {
		ProposalDevelopmentBudgetExt budget = childProposal.getLatestBudget();
		hierarchyService.synchronizeChildProposalBudget(budget, childProposal);
	}

	@Test
	public void test_synchronizeAllChildren() {
		hierarchyService.synchronizeAllChildren(pdDocument.getDevelopmentProposal());
	}

	@Test
	public void test_getDevelopmentProposal() {
		DevelopmentProposal developmentProposal = null;
		String proposalNumber = hierarchyProposal.getProposalNumber();
		developmentProposal = hierarchyService.getDevelopmentProposal(proposalNumber);
		assertNotNull(developmentProposal);
		assertEquals(hierarchyProposal, developmentProposal);
		assertEquals(proposalNumber, developmentProposal.getProposalNumber());
	}

	@Test
	public void test_lookupParent() {
		DevelopmentProposal developmentProposal = null;
		developmentProposal = hierarchyService.lookupParent(childProposal);
		assertNotNull(developmentProposal);
		assertEquals(hierarchyProposal, developmentProposal);
		assertEquals(hierarchyProposal.getProposalNumber(),
				developmentProposal.getProposalNumber());
	}

	@Test
	public void test_getHierarchyPersonnelSummaries() {
		List<HierarchyPersonnelSummary> hierarchyPersonnelSummaries = null;
		hierarchyPersonnelSummaries = hierarchyService
				.getHierarchyPersonnelSummaries(hierarchyProposal.getProposalNumber());
		assertNotNull(hierarchyPersonnelSummaries);
	}

	@Test
	public void test_getHierarchyProposalSummaries() {
		List<HierarchyProposalSummary> hierarchyProposalSummaries = null;
		hierarchyProposalSummaries = hierarchyService
				.getHierarchyProposalSummaries(hierarchyProposal.getProposalNumber());
		assertNotNull(hierarchyProposalSummaries);
		assertTrue(hierarchyProposalSummaries.size() > 0);
	}

	@Test
	public void test_validateChildBudgetPeriods() {
		ProposalHierarchyErrorWarningDto hierarchyErrorWarningDto = null;
		hierarchyErrorWarningDto = hierarchyService.validateChildBudgetPeriods(
				hierarchyProposal, childProposal, true);
		assertTrue(hierarchyErrorWarningDto == null);
	}

	@Test
	public void test_validateChildBudgetPeriods_startDateInconsistent() {
		ProposalHierarchyErrorWarningDto hierarchyErrorWarningDto = null;
		List<ProposalDevelopmentBudgetExt> budgets = hierarchyProposal.getBudgets();
		List<BudgetPeriod> budgetPeriods = budgets.get(0).getBudgetPeriods();
		budgetPeriods.get(0).setStartDate(
				DateUtils.newDate(2014, Calendar.JULY, 10));
		budgetPeriods.get(0).setEndDate(
				DateUtils.newDate(2014, Calendar.JULY, 10));
		budgets.get(0).setBudgetPeriods(budgetPeriods);
		hierarchyProposal.setBudgets(budgets);
		childProposal.setBudgets(budgets);
		hierarchyErrorWarningDto = hierarchyService.validateChildBudgetPeriods(
				hierarchyProposal, childProposal, true);
		String errorKey = "error.hierarchy.budget.startDateInconsistent";
		assertNotNull(hierarchyErrorWarningDto);
		assertEquals(errorKey, hierarchyErrorWarningDto.getErrorKey());
	}

	@Test
	public void test_getHierarchyProposals() {
		List<DevelopmentProposal> developmentProposals = null;
		developmentProposals = hierarchyService.getHierarchyProposals(childProposal);
		assertNotNull(developmentProposals);
		assertTrue(developmentProposals.size() == 2);
		assertEquals(childProposal.getHierarchyParentProposalNumber(),
				developmentProposals.get(0).getProposalNumber());
	}

	@Test
	public void test_getParentWorkflowDocument() throws Exception {
		WorkflowDocument workflowDocument = null;
		workflowDocument = hierarchyService
				.getParentWorkflowDocument(childProposal.getProposalDocument());
		assertNotNull(workflowDocument);
	}

	@Test
	public void test_getParentDocument() throws Exception {
		ProposalDevelopmentDocument document = null;
		document = hierarchyService.getParentDocument(childProposal
				.getProposalDocument());
		assertNotNull(document);
		assertEquals(this.pdDocument, document);
	}

	@Test
	public void test_getHierarchyChildren() {
		List<DevelopmentProposal> developmentProposals = null;
		developmentProposals = hierarchyService
				.getHierarchyChildren(childProposal.getHierarchyParentProposalNumber());
		assertNotNull(developmentProposals);
	}

	@Test
	public void test_routeHierarchyChildren() {
		DocumentRouteStatusChange change = new DocumentRouteStatusChange(
				this.pdDocument.getDocumentNumber(), null,
				KewApiConstants.ROUTE_HEADER_INITIATED_CD,
				KewApiConstants.ROUTE_HEADER_ENROUTE_CD);
		hierarchyService.routeHierarchyChildren(this.pdDocument, change);
	}

	@Test
	public void test_validateRemovePermissions() {
		boolean valid = true;
		valid = hierarchyService.validateRemovePermissions(childProposal, "10000000001");
		assertFalse(valid);
	}

	@Test
	public void test_calculateAndSetProposalAppDocStatus() {
		DocumentRouteStatusChange dto = new DocumentRouteStatusChange(
				childProposal.getProposalDocument().getDocumentNumber(), null,
				KewApiConstants.ROUTE_HEADER_INITIATED_CD,
				KewApiConstants.ROUTE_HEADER_ENROUTE_CD);
		hierarchyService.calculateAndSetProposalAppDocStatus(
				childProposal.getProposalDocument(), dto);
	}

	@Test
	public void test_getSyncableBudget() {
		ProposalDevelopmentBudgetExt budgetExt = null;
		budgetExt = hierarchyService.getSyncableBudget(childProposal);
		assertNotNull(budgetExt);
		assertEquals(childProposal.getLatestBudget(), budgetExt);
	}

	@Test
	public void test_getProposalSummary() {
		HierarchyProposalSummary proposalSummary = null;
		proposalSummary = hierarchyService.getProposalSummary(hierarchyProposal
				.getProposalNumber());
		assertNotNull(proposalSummary);
		assertEquals(hierarchyProposal.getProposalNumber(),
				proposalSummary.getProposalNumber());
	}

	@Test
	public void test_validateChildCandidate() {
		hierarchyProposal.setHierarchyStatus(HierarchyStatusConstants.None
				.code());
		ProposalDevelopmentBudgetExt finalBudget = hierarchyProposal
				.getLatestBudget();
		hierarchyProposal.setFinalBudget(finalBudget);
		List<ProposalHierarchyErrorWarningDto> errors = new ArrayList<ProposalHierarchyErrorWarningDto>();
		errors = hierarchyService.validateChildCandidate(hierarchyProposal);
		assertTrue(errors.isEmpty());
	}

	@Test
	public void test_validateChildCandidate_inHierarchy() {
		ProposalDevelopmentBudgetExt finalBudget = hierarchyProposal
				.getLatestBudget();
		hierarchyProposal.setFinalBudget(finalBudget);
		List<ProposalHierarchyErrorWarningDto> errors = new ArrayList<ProposalHierarchyErrorWarningDto>();
		String errorKey = "error.hierarchy.link.alreadyHierarchyMember";
		errors = hierarchyService.validateChildCandidate(hierarchyProposal);
		assertNotNull(errors);
		assertTrue(errors.size() == 1);
		assertEquals(errorKey, errors.get(0).getErrorKey());
	}

	@Test
	public void test_validateChildCandidate_finalBudgetNull() {
		hierarchyProposal.setHierarchyStatus(HierarchyStatusConstants.None
				.code());
		List<ProposalHierarchyErrorWarningDto> errors = new ArrayList<ProposalHierarchyErrorWarningDto>();
		String errorKey = "warning.hierarchy.link.noFinalBudget";
		errors = hierarchyService.validateChildCandidate(hierarchyProposal);
		assertNotNull(errors);
		assertTrue(errors.size() == 1);
		assertEquals(errorKey, errors.get(0).getErrorKey());
	}

	@Test
	public void test_validateChildForSync() {
		List<ProposalHierarchyErrorWarningDto> errors = new ArrayList<ProposalHierarchyErrorWarningDto>();
		errors = hierarchyService.validateChildForSync(childProposal, hierarchyProposal, true);
		assertTrue(errors.isEmpty());
	}

	@Test
	public void test_validateChildForSync_nullPI() {
		List<ProposalHierarchyErrorWarningDto> errors = new ArrayList<ProposalHierarchyErrorWarningDto>();
		String errorKey = "error.hierarchy.sync.noPrincipleInvestigator";
		childProposal.getProposalPersons().clear();
		errors = hierarchyService.validateChildForSync(childProposal, hierarchyProposal, true);
		assertNotNull(errors);
		assertTrue(errors.size() == 1);
		assertEquals(errorKey, errors.get(0).getErrorKey());
	}

	@Test
	public void test_validateChildForRemoval() {
		List<ProposalHierarchyErrorWarningDto> errors = new ArrayList<ProposalHierarchyErrorWarningDto>();
		errors = hierarchyService.validateChildForRemoval(childProposal);
		assertTrue(errors.isEmpty());
	}

	@Test
	public void test_validateParent() {
		List<ProposalHierarchyErrorWarningDto> errors = new ArrayList<ProposalHierarchyErrorWarningDto>();
		errors = hierarchyService.validateParent(hierarchyProposal);
		assertTrue(errors.isEmpty());
	}

	@Test
	public void test_validateParent_noParent() {
		List<ProposalHierarchyErrorWarningDto> errors = new ArrayList<ProposalHierarchyErrorWarningDto>();
		String errorKey = "error.hierarchy.link.notParent";
		hierarchyProposal.setHierarchyStatus(HierarchyStatusConstants.None.code());
		errors = hierarchyService.validateParent(hierarchyProposal);
		assertNotNull(errors);
		assertTrue(errors.size() == 1);
		assertEquals(errorKey, errors.get(0).getErrorKey());
	}

	@Test
	public void test_validateChildCandidateForHierarchy() {
		List<ProposalHierarchyErrorWarningDto> errors = new ArrayList<ProposalHierarchyErrorWarningDto>();
		errors = hierarchyService.validateChildCandidateForHierarchy(
				hierarchyProposal, childProposal, true);
		assertTrue(errors.isEmpty());
	}

	@Test
	public void test_validateChildCandidateForHierarchy_differentSponsorCode() {
		List<ProposalHierarchyErrorWarningDto> errors = new ArrayList<ProposalHierarchyErrorWarningDto>();
		childProposal.setSponsorCode("000010");
		String errorKey = "warning.hierarchy.link.differentSponsor";
		errors = hierarchyService.validateChildCandidateForHierarchy(
				hierarchyProposal, childProposal, true);
		assertNotNull(errors);
		assertTrue(errors.size() == 1);
		assertEquals(errorKey, errors.get(0).getErrorKey());
	}

	@Test
	public void test_validateLinkToHierarchy() {
		List<ProposalHierarchyErrorWarningDto> errors = new ArrayList<ProposalHierarchyErrorWarningDto>();
		childProposal.setHierarchyStatus(HierarchyStatusConstants.None.code());
		errors = hierarchyService.validateLinkToHierarchy(hierarchyProposal, childProposal);
		assertTrue(errors.isEmpty());
	}

	@Test
	public void test_validateLinkToHierarchy_inHierarchy() {
		List<ProposalHierarchyErrorWarningDto> errors = new ArrayList<ProposalHierarchyErrorWarningDto>();
		String errorKey = "error.hierarchy.proposal.not.hierarchy.child";
		errors = hierarchyService.validateLinkToHierarchy(hierarchyProposal, childProposal);
		assertNotNull(errors);
		assertTrue(errors.size() == 1);
		assertEquals(errorKey, errors.get(0).getErrorKey());
	}

	@Test
	public void test_isSynchronized() {
		boolean sync = false;
		sync = hierarchyService.isSynchronized(childProposal);
		assertFalse(sync);
	}

	private void initializeProposalHierarchyService() {
		hierarchyService = new ProposalHierarchyServiceImpl() {
			@Override
			protected void prepareHierarchySync(
					ProposalDevelopmentDocument pdDoc) {
			}
		};
		WorkflowDocumentService kradWorkflowDocumentService = KcServiceLocator
				.getService(WorkflowDocumentService.class);
		GlobalVariableService globalVariableService = KcServiceLocator
				.getService(GlobalVariableService.class);
		DocumentService documentService = KcServiceLocator
				.getService(DocumentService.class);
		ProposalBudgetService budgetService = KcServiceLocator
				.getService(ProposalBudgetService.class);
		KcAuthorizationService kcAuthorizationService = KcServiceLocator
				.getService(KcAuthorizationService.class);
		ParameterService parameterService = KcServiceLocator
				.getService(ParameterService.class);
		LegacyNarrativeService legacyNarrativeService = KcServiceLocator
				.getService(LegacyNarrativeService.class);
		ProposalHierarchyDao proposalHierarchyDao = KcServiceLocator
				.getService(ProposalHierarchyDao.class);
		BudgetSummaryService budgetSummaryService = KcServiceLocator
				.getService(BudgetSummaryService.class);
		ConfigurationService configurationService = KcServiceLocator
				.getService(ConfigurationService.class);
		KcDocumentRejectionService kcDocumentRejectionService = KcServiceLocator
				.getService(KcDocumentRejectionService.class);

		hierarchyService.setKradWorkflowDocumentService(kradWorkflowDocumentService);
		hierarchyService.setGlobalVariableService(globalVariableService);
		hierarchyService.setDocumentService(documentService);
		hierarchyService.setBudgetService(budgetService);
		hierarchyService.setKcAuthorizationService(kcAuthorizationService);
		hierarchyService.setDataObjectService(dataObjectService);
		hierarchyService.setParameterService(parameterService);
		hierarchyService.setLegacyNarrativeService(legacyNarrativeService);
		hierarchyService.setProposalHierarchyDao(proposalHierarchyDao);
		hierarchyService.setBudgetSummaryService(budgetSummaryService);
		hierarchyService.setKualiConfigurationService(configurationService);
		hierarchyService.setKcDocumentRejectionService(kcDocumentRejectionService);
	}

	private ProposalDevelopmentDocument initializeProposalDevelopmentDocument()
			throws Exception {
		ProposalDevelopmentDocument document = initializeDocument();
		initializeDevelopmentProposal(document);
		Assert.assertNotNull(document.getDocumentHeader().getWorkflowDocument());
		saveProposalDocument(document);
		document = (ProposalDevelopmentDocument) KRADServiceLocatorWeb
				.getDocumentService().getByDocumentHeaderId(
						document.getDocumentHeader().getDocumentNumber());
		assertNotNull(document.getDevelopmentProposal());
		return document;
	}

	private ProposalDevelopmentDocument initializeDocument() throws Exception {
		ProposalDevelopmentDocument pd = (ProposalDevelopmentDocument) KRADServiceLocatorWeb
				.getDocumentService().getNewDocument(
						"ProposalDevelopmentDocument");
		Assert.assertNotNull(pd.getDocumentHeader().getWorkflowDocument());
		ProposalDevelopmentService pdService = getService(ProposalDevelopmentService.class);
		pdService.initializeUnitOrganizationLocation(pd);
		pdService.initializeProposalSiteNumbers(pd);
		return pd;
	}

	private DevelopmentProposal initializeDevelopmentProposal(
			ProposalDevelopmentDocument pd) {
		DevelopmentProposal developmentProposal = pd.getDevelopmentProposal();
		developmentProposal.setPrimeSponsorCode("000120");
		developmentProposal.setActivityTypeCode("1");
		developmentProposal.refreshReferenceObject("activityType");
		developmentProposal.setSponsorCode("000100");
		developmentProposal.setOwnedByUnitNumber("000001");
		developmentProposal.refreshReferenceObject("ownedByUnit");
		developmentProposal.setProposalTypeCode("1");
		developmentProposal.setCreationStatusCode("1");
		developmentProposal.setApplicantOrganizationId("000001");
		developmentProposal.setPerformingOrganizationId("000001");
		developmentProposal.setNoticeOfOpportunityCode("1");
		developmentProposal.setRequestedStartDateInitial(DateUtils.newDate(2014, Calendar.JULY, 10));
		developmentProposal.setRequestedEndDateInitial(DateUtils.newDate(2015, Calendar.JULY, 10));
		developmentProposal.setTitle("Test Proposal Hierarchy Service");
		developmentProposal.setDeadlineType("P");
		developmentProposal.setDeadlineDate(DateUtils.newDate(2015, Calendar.JULY, 10));
		developmentProposal.setNsfCode("J.05");
		developmentProposal.setHierarchyStatus(HierarchyStatusConstants.Parent.code());
		developmentProposal.setMailBy("A");
		developmentProposal.setMailType("A");
		developmentProposal.setMailAccountNumber("100");
		developmentProposal.setNumberOfCopies("5");
		developmentProposal.setMailingAddressId(100);
		developmentProposal.setMailDescription("test");
		developmentProposal.setDeadlineTime("1:00 AM");
		developmentProposal.setAnticipatedAwardTypeCode(1);
		developmentProposal.setCfdaNumber("00.000");
		developmentProposal.setSponsorProposalNumber("100");
		developmentProposal.setAgencyDivisionCode("ADC");
		developmentProposal.setAgencyProgramCode("APC");
		developmentProposal.setSubcontracts(false);
		developmentProposal.setProgramAnnouncementNumber("100");
		developmentProposal.setProgramAnnouncementTitle("test");
		return developmentProposal;
	}

	private DevelopmentProposal setDevelopmentProposalAdditionalData(
			DevelopmentProposal developmentProposal,
			DevelopmentProposal childProposal) {
		if (childProposal != null) {
			developmentProposal
					.setHierarchyOriginatingChildProposalNumber(childProposal
							.getProposalNumber());
			developmentProposal.setHierarchyParentProposalNumber(childProposal
					.getProposalNumber());
		}
		createProposalPerson(developmentProposal);
		createSponsor(developmentProposal);
		createActivityType(developmentProposal);
		createPropScienceKeyword(developmentProposal);
		createNarrative(developmentProposal);
		createProposalSpecialReview(developmentProposal);
		developmentProposal = dataObjectService.save(developmentProposal);
		return developmentProposal;
	}

	private void createProposalPerson(DevelopmentProposal developmentProposal) {
		ProposalPerson person = new ProposalPerson();
		person.setProposalPersonNumber(1);
		person.setProposalPersonRoleId("PI");
		person.setFirstName("firstname");
		person.setLastName("argLastName");
		person.setMiddleName("argMiddleName");
		person.setOfficePhone("321-321-1228");
		person.setEmailAddress("kcnotification@gmail.com");
		person.setFaxNumber("321-321-1289");
		person.setAddressLine1("argAddressLine1");
		person.setAddressLine2("argAddressLine2");
		person.setCity("Coeus");
		person.setPostalCode("53421");
		person.setCounty("UNITED STATES");
		person.setCountryCode("USA");
		person.setState("MA");
		person.setDirectoryTitle("argDirectoryTitle");
		person.setDivision("division");
		person.setPersonId("10000000001");
		person.setRolodexId(1);
		person.setDevelopmentProposal(developmentProposal);
		developmentProposal.getProposalPersons().add(person);
	}

	private void createSponsor(DevelopmentProposal developmentProposal) {
		Sponsor sponsor = null;
		sponsor = dataObjectService.findUnique(Sponsor.class,
				QueryByCriteria.Builder.forAttribute("sponsorCode", "000100")
						.build());
		developmentProposal.setSponsor(sponsor);
	}

	private void createActivityType(DevelopmentProposal developmentProposal) {
		ActivityType activityType = null;
		activityType = dataObjectService.findUnique(ActivityType.class,
				QueryByCriteria.Builder.forAttribute("code", "1").build());
		developmentProposal.setActivityType(activityType);
	}

	private void createPropScienceKeyword(
			DevelopmentProposal developmentProposal) {
		List<PropScienceKeyword> propScienceKeywords = new ArrayList<PropScienceKeyword>();
		ScienceKeyword scienceKeyword = null;
		scienceKeyword = dataObjectService.findUnique(ScienceKeyword.class,
				QueryByCriteria.Builder.forAttribute("code", "1").build());
		PropScienceKeyword propScienceKeyword = new PropScienceKeyword(
				developmentProposal, scienceKeyword);
		propScienceKeyword.setHierarchyProposalNumber(developmentProposal
				.getHierarchyParentProposalNumber());
		propScienceKeywords.add(propScienceKeyword);
		developmentProposal.setPropScienceKeywords(propScienceKeywords);
	}

	private void createNarrative(DevelopmentProposal developmentProposal) {
		Narrative narrative = new Narrative();
		narrative.setDevelopmentProposal(developmentProposal);
		narrative.setModuleNumber(1);
		narrative.setModuleSequenceNumber(1);
		narrative.setModuleStatusCode("C");
		narrative.setNarrativeTypeCode("1");
		narrative.setName("test");
		narrative.setHierarchyProposalNumber(developmentProposal
				.getHierarchyParentProposalNumber());
		createNarrativeType(narrative);
		createNarrativeAttachment(narrative);
		developmentProposal.getNarratives().add(narrative);
		developmentProposal.getInstituteAttachments().add(narrative);
	}

	private void createNarrativeType(Narrative narrative) {
		NarrativeType narrativeType = null;
		narrativeType = dataObjectService.findUnique(NarrativeType.class,
				QueryByCriteria.Builder.forAttribute("code", "299").build());
		narrative.setNarrativeType(narrativeType);
	}

	private void createNarrativeAttachment(Narrative narrative) {
		NarrativeAttachment narrativeAttachment = new NarrativeAttachment();
		narrativeAttachment.setNarrative(narrative);
		narrativeAttachment.setModuleNumber(1);
		narrativeAttachment.setName("test");
		narrative.setNarrativeAttachment(narrativeAttachment);
	}

	private void createProposalSpecialReview(
			DevelopmentProposal developmentProposal) {
		List<ProposalSpecialReview> proposalSpecialReviews = new ArrayList<ProposalSpecialReview>();
		ProposalSpecialReview proposalSpecialReview = new ProposalSpecialReview();
		proposalSpecialReview.setDevelopmentProposal(developmentProposal);
		proposalSpecialReview.setSpecialReviewTypeCode("1");
		proposalSpecialReview.setApprovalTypeCode("2");
		proposalSpecialReview.setHierarchyProposalNumber(developmentProposal
				.getHierarchyParentProposalNumber());
		proposalSpecialReviews.add(proposalSpecialReview);
		developmentProposal.setPropSpecialReviews(proposalSpecialReviews);
	}

	private ProposalDevelopmentBudgetExt getBudget(
			DevelopmentProposal developmentProposal) {
		ProposalDevelopmentBudgetExt budget = dataObjectService
				.save(createBudget(developmentProposal));
		List<ProposalDevelopmentBudgetExt> budgets = developmentProposal
				.getBudgets();
		budgets.add(budget);
		return budget;
	}

	private ProposalDevelopmentBudgetExt createBudget(
			DevelopmentProposal developmentProposal) {
		ProposalDevelopmentBudgetExt budget = new ProposalDevelopmentBudgetExt();
		budget.setDevelopmentProposal(developmentProposal);
		budget.setBudgetVersionNumber(1);
		budget.setName("Hierarchy Budget");
		budget.setStartDate(developmentProposal.getRequestedStartDateInitial());
		budget.setEndDate(developmentProposal.getRequestedEndDateInitial());
		budget.setOhRateClassCode("1");
		budget.setOhRateTypeCode("1");
		budget.setUrRateClassCode("1");
		BudgetStatus budgetStatus = dataObjectService.findUnique(
				BudgetStatus.class,
				QueryByCriteria.Builder.forAttribute("budgetStatusCode", "2")
						.build());
		budget.setBudgetStatus(budgetStatus.getBudgetStatusCode());
		budget.setBudgetStatusDo(budgetStatus);
		budget.setModularBudgetFlag(false);
		budget.setRateClassTypesReloaded(true);
		budget.getRateClassTypes();
		createBudgetPeriod(budget);
		return budget;
	}

	private void createBudgetPeriod(ProposalDevelopmentBudgetExt budget) {
		List<BudgetPeriod> budgetPeriods = new ArrayList<BudgetPeriod>();
		BudgetPeriod budgetPeriod = budget.getNewBudgetPeriod();
		budgetPeriod.setBudgetPeriod(1);
		budgetPeriod.setStartDate(budget.getStartDate());
		budgetPeriod.setEndDate(budget.getEndDate());
		budgetPeriod.setBudget(budget);
		budgetPeriods.add(budgetPeriod);
		budget.setBudgetPeriods(budgetPeriods);
	}

	private void saveProposalDocument(ProposalDevelopmentDocument pd)
			throws Exception {
		pd.setUpdateUser("quickst");
		pd.setUpdateTimestamp(new java.sql.Timestamp(Calendar.getInstance()
				.getTimeInMillis()));
		DocumentHeader docHeader = pd.getDocumentHeader();
		docHeader.setDocumentDescription("Test Proposal Hierarchy Service");
		String docNumber = docHeader.getDocumentNumber();
		assertNotNull(docNumber);
		assertNotNull(pd.getDevelopmentProposal());
		KRADServiceLocatorWeb.getDocumentService().saveDocument(pd);
	}

	public DevelopmentProposal getChildProposal(
			DevelopmentProposal hierarchyProposal) throws Exception {
		ProposalDevelopmentDocument document = initializeProposalDevelopmentDocument();
		DevelopmentProposal childProposal = document.getDevelopmentProposal();
		childProposal.setHierarchyStatus(HierarchyStatusConstants.Child.code());
		childProposal.setHierarchyParentProposalNumber(hierarchyProposal
				.getProposalNumber());
		childProposal = setDevelopmentProposalAdditionalData(childProposal,
				null);
		childProposal = dataObjectService.save(childProposal);
		getBudget(childProposal);
		return childProposal;
	}
}
