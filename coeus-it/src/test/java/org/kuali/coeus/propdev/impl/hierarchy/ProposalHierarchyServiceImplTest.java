/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.coeus.propdev.impl.hierarchy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.kuali.coeus.sys.framework.service.KcServiceLocator.getService;

import java.util.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.common.budget.framework.personnel.HierarchyPersonnelSummary;
import org.kuali.coeus.common.framework.auth.perm.KcAuthorizationService;
import org.kuali.coeus.common.framework.keyword.ScienceKeyword;
import org.kuali.coeus.common.framework.sponsor.Sponsor;
import org.kuali.coeus.common.framework.type.ActivityType;
import org.kuali.coeus.propdev.impl.attachment.LegacyNarrativeService;
import org.kuali.coeus.propdev.impl.attachment.Narrative;
import org.kuali.coeus.propdev.impl.attachment.NarrativeAttachment;
import org.kuali.coeus.propdev.impl.attachment.NarrativeType;
import org.kuali.coeus.propdev.impl.budget.BudgetStatus;
import org.kuali.coeus.propdev.impl.budget.ProposalDevelopmentBudgetExt;
import org.kuali.coeus.propdev.impl.budget.hierarchy.ProposalBudgetHierarchyService;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentService;
import org.kuali.coeus.propdev.impl.keyword.PropScienceKeyword;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;
import org.kuali.coeus.propdev.impl.person.attachment.ProposalPersonBiographyService;
import org.kuali.coeus.propdev.impl.specialreview.ProposalSpecialReview;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.sys.framework.util.DateUtils;
import org.kuali.coeus.sys.framework.workflow.KcDocumentRejectionService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.RoleConstants;
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
import org.kuali.rice.krad.service.PessimisticLockService;
import org.kuali.rice.krad.workflow.service.WorkflowDocumentService;

public class ProposalHierarchyServiceImplTest extends KcIntegrationTestBase {

    public static final String FIRST_NAME = "firstname";
    public static final String LAST_NAME = "lastName";
    public static final String PERSON_ID = "10000000001";
	private ProposalHierarchyServiceImpl hierarchyService;
	private DataObjectService dataObjectService;
    private KcAuthorizationService kcAuthorizationService;

	@Before
	public void setup() throws Exception {
		dataObjectService = KcServiceLocator.getService(DataObjectService.class);
        kcAuthorizationService = KcServiceLocator.getService(KcAuthorizationService.class);
		initializeProposalHierarchyService();
	}

	@After
	public void teardown() {
		dataObjectService = null;
	}

	@Test
	public void test_createHierarchy() throws Exception {
        ProposalDevelopmentDocument pdDocument = initializeProposalDevelopmentDocument();
        DevelopmentProposal childProposal = getChildProposal(pdDocument.getDevelopmentProposal());
        DevelopmentProposal hierarchyProposal = setDevelopmentProposalAdditionalData(pdDocument.getDevelopmentProposal(), childProposal);
        getBudget(hierarchyProposal);
		String userId = PERSON_ID;
		String parentProposalNumber = hierarchyService.createHierarchy(childProposal, userId);
		assertNotNull(parentProposalNumber);
		assertTrue(parentProposalNumber.length() > 0);
        DevelopmentProposal parentDevelopmentProposal = getDevelopmentProposal(parentProposalNumber);
        ProposalPerson person = parentDevelopmentProposal.getProposalPerson(0);
        assertEquals(person.getFirstName(), FIRST_NAME);
        assertEquals(person.getPersonId(), PERSON_ID);
        assertEquals(person.getProposalPersonRoleId(), Constants.PRINCIPAL_INVESTIGATOR_ROLE);
        test_validateChildCandidate_inHierarchy(childProposal);
        test_validateChildForSync(parentDevelopmentProposal, childProposal);
        test_validateChildForRemoval(childProposal);
        test_validateChildCandidateForHierarchy(parentDevelopmentProposal, childProposal);
        test_validateLinkToHierarchy_inHierarchy(parentDevelopmentProposal, childProposal);
        test_validateParent(parentDevelopmentProposal);
        test_getSyncableBudget(childProposal);
        test_lookupParent(parentDevelopmentProposal, childProposal);
        test_getHierarchyProposalSummaries(parentDevelopmentProposal);
        test_validateChildBudgetPeriods(parentDevelopmentProposal, childProposal);
        test_validateChildBudgetPeriods_startDateInconsistent(parentDevelopmentProposal, childProposal);
        test_getHierarchyProposals(childProposal);
        test_getParentWorkflowDocument(childProposal);
        test_getParentDocument(childProposal, parentDevelopmentProposal.getProposalDocument());
        test_getHierarchyChildren(childProposal);
        test_calculateAndSetProposalAppDocStatus(childProposal);
        test_getProposalSummary(parentDevelopmentProposal);
        test_validateRemovePermissions(childProposal);
        test_validateParent_noParent(parentDevelopmentProposal);
        test_validateChildCandidateForHierarchy_differentSponsorCode(parentDevelopmentProposal, childProposal);
        test_validateChildCandidate(childProposal);
        test_validateChildCandidate_finalBudgetNull(parentDevelopmentProposal);
        test_validateChildForSync_nullPI(parentDevelopmentProposal, childProposal);
        test_isSynchronized(childProposal);
        childProposal.setHierarchyStatus(HierarchyStatusConstants.Child.code());
        childProposal.setHierarchyParentProposalNumber(parentDevelopmentProposal.getProposalNumber());
    }

    public DevelopmentProposal getDevelopmentProposal(String proposalNumber) {
        String PROPOSAL_NUMBER = "proposalNumber";
        Map<String, String> fieldMap = new HashMap<>();
        fieldMap.put(PROPOSAL_NUMBER, proposalNumber);
        final List<DevelopmentProposal> developmentProposals = dataObjectService.findMatching(DevelopmentProposal.class,
                QueryByCriteria.Builder.andAttributes(fieldMap).build()).getResults();
        return developmentProposals.get(0);
    }

    @Test
    public void test_synchronize_Child_After_Pi_Change() throws Exception {
        ProposalDevelopmentDocument pdDocument = initializeProposalDevelopmentDocument();
        DevelopmentProposal childProposal = getChildProposal(pdDocument.getDevelopmentProposal());
        String userId = PERSON_ID;
        String parentProposalNumber = hierarchyService.createHierarchy(childProposal, userId);
        childProposal.setProposalPersons(new ArrayList<>());
        createProposalPerson2(childProposal);
        DevelopmentProposal changedChildProposal = dataObjectService.save(childProposal);
        hierarchyService.synchronizeChild(changedChildProposal);
        DevelopmentProposal parentProposal = changedChildProposal.getParent();
        assertTrue(parentProposal.getProposalPerson(0).getPersonId().equalsIgnoreCase("999"));
        assertTrue(parentProposal.getProposalPerson(0).getProposalPersonRoleId().equalsIgnoreCase(Constants.CO_INVESTIGATOR_ROLE));
        hierarchyService.removeFromHierarchy(changedChildProposal);
        assertTrue(changedChildProposal.getHierarchyParentProposalNumber() == null);
        parentProposal = getDevelopmentProposal(parentProposalNumber);
        assertTrue(parentProposal.getProposalPersons().isEmpty());
        assertTrue(hierarchyService.getHierarchyChildren(parentProposalNumber).isEmpty());
    }

	@Test
	public void test_linkToHierarchy() throws Exception {
        ProposalDevelopmentDocument pdDocument = initializeProposalDevelopmentDocument();
        DevelopmentProposal childProposal = getChildProposal(pdDocument.getDevelopmentProposal());
        DevelopmentProposal hierarchyProposal = setDevelopmentProposalAdditionalData(pdDocument.getDevelopmentProposal(), childProposal);
        getBudget(hierarchyProposal);
        String hierarchyBudgetTypeCode = "B";
		hierarchyService.linkToHierarchy(hierarchyProposal, childProposal, hierarchyBudgetTypeCode);
	}

	@Test
	public void test_removeFromHierarchy() throws Exception {
        ProposalDevelopmentDocument pdDocument = initializeProposalDevelopmentDocument();
        DevelopmentProposal childProposal = getChildProposal(pdDocument.getDevelopmentProposal());
        DevelopmentProposal hierarchyProposal = setDevelopmentProposalAdditionalData(pdDocument.getDevelopmentProposal(), childProposal);
        getBudget(hierarchyProposal);
        String hierarchyBudgetTypeCode = "B";
        hierarchyService.linkToHierarchy(hierarchyProposal, childProposal, hierarchyBudgetTypeCode);
        hierarchyService.removeFromHierarchy(childProposal);
	}

	@Test
	public void test_synchronizeChild() throws Exception {
        ProposalDevelopmentDocument pdDocument = initializeProposalDevelopmentDocument();
        DevelopmentProposal childProposal = getChildProposal(pdDocument.getDevelopmentProposal());
        DevelopmentProposal hierarchyProposal = setDevelopmentProposalAdditionalData(pdDocument.getDevelopmentProposal(), childProposal);
        getBudget(hierarchyProposal);
        String hierarchyBudgetTypeCode = "B";
        hierarchyService.linkToHierarchy(hierarchyProposal, childProposal, hierarchyBudgetTypeCode);
        hierarchyService.synchronizeChild(childProposal);
	}

	@Test
	public void test_synchronizeChildProposalBudget() throws Exception {
        ProposalDevelopmentDocument pdDocument = initializeProposalDevelopmentDocument();
        DevelopmentProposal childProposal = getChildProposal(pdDocument.getDevelopmentProposal());
        DevelopmentProposal hierarchyProposal = setDevelopmentProposalAdditionalData(pdDocument.getDevelopmentProposal(), childProposal);
        getBudget(hierarchyProposal);
        String hierarchyBudgetTypeCode = "B";
        hierarchyService.linkToHierarchy(hierarchyProposal, childProposal, hierarchyBudgetTypeCode);
        ProposalDevelopmentBudgetExt budget = childProposal.getLatestBudget();
		hierarchyService.synchronizeChildBudget(hierarchyProposal, budget);
	}

	@Test
	public void test_synchronizeAllChildren() throws Exception {
        ProposalDevelopmentDocument pdDocument = initializeProposalDevelopmentDocument();
        DevelopmentProposal childProposal = getChildProposal(pdDocument.getDevelopmentProposal());
        DevelopmentProposal hierarchyProposal = setDevelopmentProposalAdditionalData(pdDocument.getDevelopmentProposal(), childProposal);
        getBudget(hierarchyProposal);
        String hierarchyBudgetTypeCode = "B";
        hierarchyService.linkToHierarchy(hierarchyProposal, childProposal, hierarchyBudgetTypeCode);
        hierarchyService.synchronizeAllChildren(pdDocument.getDevelopmentProposal());
	}

	@Test
	public void test_getDevelopmentProposal() throws Exception {
        ProposalDevelopmentDocument pdDocument = initializeProposalDevelopmentDocument();
        DevelopmentProposal childProposal = getChildProposal(pdDocument.getDevelopmentProposal());
        DevelopmentProposal hierarchyProposal = setDevelopmentProposalAdditionalData(pdDocument.getDevelopmentProposal(), childProposal);

        DevelopmentProposal developmentProposal = null;
		String proposalNumber = hierarchyProposal.getProposalNumber();
		developmentProposal = hierarchyService.getDevelopmentProposal(proposalNumber);
		assertNotNull(developmentProposal);
		assertEquals(hierarchyProposal, developmentProposal);
		assertEquals(proposalNumber, developmentProposal.getProposalNumber());
	}

	public void test_lookupParent(DevelopmentProposal parentProposal, DevelopmentProposal childProposal) {
		DevelopmentProposal developmentProposal;
		developmentProposal = hierarchyService.lookupParent(childProposal);
		assertNotNull(developmentProposal);
		assertEquals(parentProposal, developmentProposal);
		assertEquals(parentProposal.getProposalNumber(),
				developmentProposal.getProposalNumber());
	}

	public void test_getHierarchyPersonnelSummaries(DevelopmentProposal hierarchyProposal) {
		List<HierarchyPersonnelSummary> hierarchyPersonnelSummaries = null;
		hierarchyPersonnelSummaries = hierarchyService
				.getHierarchyPersonnelSummaries(hierarchyProposal.getProposalNumber());
		assertNotNull(hierarchyPersonnelSummaries);
	}

	public void test_getHierarchyProposalSummaries(DevelopmentProposal hierarchyProposal) {
		List<HierarchyProposalSummary> hierarchyProposalSummaries = null;
		hierarchyProposalSummaries = hierarchyService
				.getHierarchyProposalSummaries(hierarchyProposal.getProposalNumber());
		assertNotNull(hierarchyProposalSummaries);
		assertTrue(hierarchyProposalSummaries.size() > 0);
	}

	public void test_validateChildBudgetPeriods(DevelopmentProposal hierarchyProposal, DevelopmentProposal childProposal) {
		List<ProposalHierarchyErrorWarningDto> hierarchyErrorWarningDto = null;
		hierarchyErrorWarningDto = hierarchyService.validateChildBudgetPeriods(
				hierarchyProposal, childProposal, true);
		assertTrue(hierarchyErrorWarningDto.isEmpty());
	}

	public void test_validateChildBudgetPeriods_startDateInconsistent(DevelopmentProposal hierarchyProposal, DevelopmentProposal childProposal) {
		List<ProposalHierarchyErrorWarningDto> hierarchyErrorWarningDto = null;
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
		assertTrue(!hierarchyErrorWarningDto.isEmpty());
		assertEquals(errorKey, hierarchyErrorWarningDto.get(0).getErrorKey());
	}

	public void test_getHierarchyProposals(DevelopmentProposal childProposal) {
		List<DevelopmentProposal> developmentProposals = null;
		developmentProposals = hierarchyService.getHierarchyProposals(childProposal);
		assertNotNull(developmentProposals);
		assertTrue(developmentProposals.size() == 2);
		assertEquals(childProposal.getHierarchyParentProposalNumber(),
				developmentProposals.get(0).getProposalNumber());
	}

	public void test_getParentWorkflowDocument(DevelopmentProposal childProposal) throws Exception {
		WorkflowDocument workflowDocument = null;
		workflowDocument = hierarchyService
				.getParentWorkflowDocument(childProposal.getProposalDocument());
		assertNotNull(workflowDocument);
	}

	public void test_getParentDocument(DevelopmentProposal childProposal, ProposalDevelopmentDocument parentDocument) throws Exception {
        ProposalDevelopmentDocument document = hierarchyService.getParentDocument(childProposal.getProposalDocument());
		assertNotNull(document);
		assertEquals(parentDocument.getDocumentNumber(), document.getDocumentNumber());
	}

	public void test_getHierarchyChildren(DevelopmentProposal childProposal) {
		List<DevelopmentProposal> developmentProposals = null;
		developmentProposals = hierarchyService
				.getHierarchyChildren(childProposal.getHierarchyParentProposalNumber());
		assertNotNull(developmentProposals);
	}

	public void test_validateRemovePermissions(DevelopmentProposal childProposal) {
        hierarchyService.removeFromHierarchy(childProposal);
		boolean valid = true;
		valid = hierarchyService.validateRemovePermissions(childProposal, PERSON_ID);
		assertFalse(valid);
	}

	public void test_calculateAndSetProposalAppDocStatus(DevelopmentProposal childProposal) {
		DocumentRouteStatusChange dto = new DocumentRouteStatusChange(
				childProposal.getProposalDocument().getDocumentNumber(), null,
				KewApiConstants.ROUTE_HEADER_INITIATED_CD,
				KewApiConstants.ROUTE_HEADER_ENROUTE_CD);
		hierarchyService.calculateAndSetProposalAppDocStatus(
				childProposal.getProposalDocument(), dto);
	}

	public void test_getSyncableBudget(DevelopmentProposal childProposal) {
		ProposalDevelopmentBudgetExt budgetExt = null;
		budgetExt = hierarchyService.getSyncableBudget(childProposal);
		assertNotNull(budgetExt);
		assertEquals(childProposal.getLatestBudget(), budgetExt);
	}

	public void test_getProposalSummary(DevelopmentProposal hierarchyProposal) {
		HierarchyProposalSummary proposalSummary = null;
		proposalSummary = hierarchyService.getProposalSummary(hierarchyProposal.getProposalNumber());
		assertNotNull(proposalSummary);
		assertEquals(hierarchyProposal.getProposalNumber(),
				proposalSummary.getProposalNumber());
	}

	public void test_validateChildCandidate(DevelopmentProposal hierarchyProposal) {
		hierarchyProposal.setHierarchyStatus(HierarchyStatusConstants.None.code());
		ProposalDevelopmentBudgetExt finalBudget = hierarchyProposal.getLatestBudget();
		hierarchyProposal.setFinalBudget(finalBudget);
		List<ProposalHierarchyErrorWarningDto> errors;
		errors = hierarchyService.validateChildCandidate(hierarchyProposal);
		assertTrue(errors.isEmpty());
	}

	public void test_validateChildCandidate_inHierarchy(DevelopmentProposal childProposal) {
		ProposalDevelopmentBudgetExt finalBudget = childProposal.getLatestBudget();
		childProposal.setFinalBudget(finalBudget);
		List<ProposalHierarchyErrorWarningDto> errors;
		String errorKey = "error.hierarchy.link.alreadyHierarchyMember";
		errors = hierarchyService.validateChildCandidate(childProposal);
		assertNotNull(errors);
		assertTrue(errors.size() == 1);
		assertEquals(errorKey, errors.get(0).getErrorKey());
	}

	public void test_validateChildCandidate_finalBudgetNull(DevelopmentProposal hierarchyProposal) {
		hierarchyProposal.setHierarchyStatus(HierarchyStatusConstants.None
				.code());
        hierarchyProposal.setFinalBudget(null);
        List<ProposalHierarchyErrorWarningDto> errors;
		String errorKey1 = "warning.hierarchy.link.noFinalBudget";
        String errorKey2 = "error.hierarchy.link.noPrincipleInvestigator";
        errors = hierarchyService.validateChildCandidate(hierarchyProposal);
		assertNotNull(errors);
		assertTrue(errors.size() == 2);
        assertTrue(errors.get(0).getErrorKey().equalsIgnoreCase(errorKey1));
        assertTrue(errors.get(1).getErrorKey().equalsIgnoreCase(errorKey2));
    }

	public void test_validateChildForSync(DevelopmentProposal hierarchyProposal, DevelopmentProposal childProposal) {
		List<ProposalHierarchyErrorWarningDto> errors;
		errors = hierarchyService.validateChildForSync(childProposal, hierarchyProposal, true);
		assertTrue(errors.isEmpty());
	}

	public void test_validateChildForSync_nullPI(DevelopmentProposal hierarchyProposal, DevelopmentProposal childProposal) {
		List<ProposalHierarchyErrorWarningDto> errors;
		String errorKey = "error.hierarchy.sync.noPrincipleInvestigator";
		childProposal.getProposalPersons().clear();
		errors = hierarchyService.validateChildForSync(childProposal, hierarchyProposal, true);
		assertNotNull(errors);
		assertTrue(errors.size() == 3);
		assertEquals(errorKey, errors.get(0).getErrorKey());
	}

	public void test_validateChildForRemoval(DevelopmentProposal childProposal) {
		List<ProposalHierarchyErrorWarningDto> errors;
		errors = hierarchyService.validateChildForRemoval(childProposal);
		assertTrue(errors.isEmpty());
	}

	public void test_validateParent(DevelopmentProposal hierarchyProposal) {
		List<ProposalHierarchyErrorWarningDto> errors;
		errors = hierarchyService.validateParent(hierarchyProposal);
		assertTrue(errors.isEmpty());
	}

	public void test_validateParent_noParent(DevelopmentProposal hierarchyProposal) {
		List<ProposalHierarchyErrorWarningDto> errors;
		String errorKey = "error.hierarchy.link.notParent";
		hierarchyProposal.setHierarchyStatus(HierarchyStatusConstants.None.code());
		errors = hierarchyService.validateParent(hierarchyProposal);
		assertNotNull(errors);
		assertTrue(errors.size() == 1);
		assertEquals(errorKey, errors.get(0).getErrorKey());
	}

	public void test_validateChildCandidateForHierarchy(DevelopmentProposal hierarchyProposal, DevelopmentProposal childProposal) {
		List<ProposalHierarchyErrorWarningDto> errors;
		errors = hierarchyService.validateChildCandidateForHierarchy(
				hierarchyProposal, childProposal, true);
		assertTrue(errors.isEmpty());
	}

	public void test_validateChildCandidateForHierarchy_differentSponsorCode(DevelopmentProposal hierarchyProposal, DevelopmentProposal childProposal) {
		List<ProposalHierarchyErrorWarningDto> errors;
		childProposal.setSponsorCode("000010");
		String errorKey = "warning.hierarchy.link.differentSponsor";
		errors = hierarchyService.validateChildCandidateForHierarchy(
				hierarchyProposal, childProposal, true);
		assertNotNull(errors);
		assertTrue(errors.size() == 1);
		assertEquals(errorKey, errors.get(0).getErrorKey());
	}

	public void test_validateLinkToHierarchy_inHierarchy(DevelopmentProposal hierarchyProposal, DevelopmentProposal childProposal) {
		List<ProposalHierarchyErrorWarningDto> errors;
		String errorKey = "error.hierarchy.proposal.not.hierarchy.child";
		errors = hierarchyService.validateLinkToHierarchy(hierarchyProposal, childProposal);
		assertNotNull(errors);
		assertTrue(errors.toString(), errors.size() == 1);
		assertEquals(errorKey, errors.get(0).getErrorKey());
	}

	public void test_isSynchronized(DevelopmentProposal childProposal) {
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
		KcAuthorizationService kcAuthorizationService = KcServiceLocator
				.getService(KcAuthorizationService.class);
		ParameterService parameterService = KcServiceLocator
				.getService(ParameterService.class);
		LegacyNarrativeService legacyNarrativeService = KcServiceLocator
				.getService(LegacyNarrativeService.class);
		ProposalHierarchyDao proposalHierarchyDao = KcServiceLocator
				.getService(ProposalHierarchyDao.class);
		ConfigurationService configurationService = KcServiceLocator
				.getService(ConfigurationService.class);
		KcDocumentRejectionService kcDocumentRejectionService = KcServiceLocator
				.getService(KcDocumentRejectionService.class);
		PessimisticLockService pessimisticLockService = KcServiceLocator
				.getService(PessimisticLockService.class);
        ProposalPersonBiographyService proposalPersonBiographyService = KcServiceLocator.
                getService(ProposalPersonBiographyService.class);
		ProposalBudgetHierarchyService proposalBudgetHierarchyService = KcServiceLocator
				.getService(ProposalBudgetHierarchyService.class);

		hierarchyService.setKradWorkflowDocumentService(kradWorkflowDocumentService);
		hierarchyService.setGlobalVariableService(globalVariableService);
		hierarchyService.setDocumentService(documentService);
		hierarchyService.setKcAuthorizationService(kcAuthorizationService);
		hierarchyService.setDataObjectService(dataObjectService);
		hierarchyService.setParameterService(parameterService);
		hierarchyService.setLegacyNarrativeService(legacyNarrativeService);
		hierarchyService.setProposalHierarchyDao(proposalHierarchyDao);
		hierarchyService.setKualiConfigurationService(configurationService);
		hierarchyService.setKcDocumentRejectionService(kcDocumentRejectionService);
		hierarchyService.setPessimisticLockService(pessimisticLockService);
        hierarchyService.setProposalPersonBiographyService(proposalPersonBiographyService);
		hierarchyService.setProposalBudgetHierarchyService(proposalBudgetHierarchyService);
	}

	private ProposalDevelopmentDocument initializeProposalDevelopmentDocument()
			throws Exception {
		ProposalDevelopmentDocument document = initializeDocument();
		initializeDevelopmentProposal(document);
		Assert.assertNotNull(document.getDocumentHeader().getWorkflowDocument());
		saveProposalDocument(document);
		document = (ProposalDevelopmentDocument) getDocumentService().getByDocumentHeaderId(document.getDocumentHeader().getDocumentNumber());
		assertNotNull(document.getDevelopmentProposal());
		return document;
	}

	private ProposalDevelopmentDocument initializeDocument() throws Exception {
		ProposalDevelopmentDocument pd = (ProposalDevelopmentDocument) getDocumentService().getNewDocument(
                "ProposalDevelopmentDocument");
		Assert.assertNotNull(pd.getDocumentHeader().getWorkflowDocument());
		ProposalDevelopmentService pdService = getService(ProposalDevelopmentService.class);
		pdService.initializeUnitOrganizationLocation(pd);
		pdService.initializeProposalSiteNumbers(pd);
        kcAuthorizationService.addDocumentLevelRole(PERSON_ID, RoleConstants.AGGREGATOR_DOCUMENT_LEVEL,pd);
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

	private DevelopmentProposal setDevelopmentProposalAdditionalData(DevelopmentProposal developmentProposal, DevelopmentProposal childProposal) {
		if (childProposal != null) {
			developmentProposal.setHierarchyOriginatingChildProposalNumber(childProposal.getProposalNumber());
			developmentProposal.setHierarchyParentProposalNumber(childProposal.getProposalNumber());
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
		person.setProposalPersonRoleId(Constants.PRINCIPAL_INVESTIGATOR_ROLE);
		person.setFirstName(FIRST_NAME);
		person.setLastName(LAST_NAME);
		person.setMiddleName("middleName");
        setPersonData(person);
		person.setRolodexId(1);
		person.setDevelopmentProposal(developmentProposal);
		developmentProposal.getProposalPersons().add(person);
        person.setPersonId(PERSON_ID);
    }

    private void createProposalPerson2(DevelopmentProposal developmentProposal) {
        ProposalPerson person = new ProposalPerson();
        person.setProposalPersonNumber(2);
        person.setProposalPersonRoleId(Constants.PRINCIPAL_INVESTIGATOR_ROLE);
        person.setFirstName("Bruce");
        person.setLastName("Wayne");
        person.setMiddleName("middleName");
        setPersonData(person);
        person.setRolodexId(1);
        person.setDevelopmentProposal(developmentProposal);
        developmentProposal.getProposalPersons().add(person);
        person.setPersonId("999");
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
		byte[] b = new byte[100];
		new Random().nextBytes(b);
		narrativeAttachment.setData(b);
		narrative.setNarrativeAttachment(narrativeAttachment);
	}

	private void createProposalSpecialReview(
			DevelopmentProposal developmentProposal) {
		List<ProposalSpecialReview> proposalSpecialReviews = new ArrayList<ProposalSpecialReview>();
		ProposalSpecialReview proposalSpecialReview = new ProposalSpecialReview();
		proposalSpecialReview.setDevelopmentProposal(developmentProposal);
		proposalSpecialReview.setSpecialReviewTypeCode("5");
		proposalSpecialReview.setApprovalTypeCode("2");
		proposalSpecialReview.setHierarchyProposalNumber(developmentProposal
				.getHierarchyParentProposalNumber());
        proposalSpecialReview.setSpecialReviewNumber(1);
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
		getDocumentService().saveDocument(pd);
	}

    public DocumentService getDocumentService() {
        return KRADServiceLocatorWeb.getDocumentService();
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
