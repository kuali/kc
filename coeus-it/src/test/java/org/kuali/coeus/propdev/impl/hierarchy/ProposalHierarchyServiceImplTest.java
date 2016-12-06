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
package org.kuali.coeus.propdev.impl.hierarchy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.kuali.coeus.sys.framework.service.KcServiceLocator.getService;

import java.util.*;
import java.util.stream.Collectors;

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
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.coeus.common.framework.unit.UnitService;
import org.kuali.coeus.propdev.impl.attachment.Narrative;
import org.kuali.coeus.propdev.impl.attachment.NarrativeAttachment;
import org.kuali.coeus.propdev.impl.attachment.NarrativeType;
import org.kuali.coeus.propdev.impl.budget.BudgetStatus;
import org.kuali.coeus.propdev.impl.budget.ProposalDevelopmentBudgetExt;
import org.kuali.coeus.propdev.impl.core.*;
import org.kuali.coeus.propdev.impl.keyword.PropScienceKeyword;
import org.kuali.coeus.propdev.impl.location.ProposalSite;
import org.kuali.coeus.propdev.impl.person.KeyPersonnelService;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;
import org.kuali.coeus.propdev.impl.person.ProposalPersonUnit;
import org.kuali.coeus.propdev.impl.person.attachment.PropPerDocType;
import org.kuali.coeus.propdev.impl.person.attachment.ProposalPersonBiography;
import org.kuali.coeus.propdev.impl.person.attachment.ProposalPersonBiographyAttachment;
import org.kuali.coeus.propdev.impl.specialreview.ProposalSpecialReview;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.sys.framework.util.DateUtils;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.kew.framework.postprocessor.DocumentRouteStatusChange;
import org.kuali.rice.krad.bo.DocumentHeader;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.service.KRADServiceLocatorWeb;
import org.springframework.mock.web.MockMultipartFile;

public class ProposalHierarchyServiceImplTest extends KcIntegrationTestBase {

    public static final String FIRST_NAME = "firstname";
    public static final String LAST_NAME = "lastName";
    public static final String PERSON_ID = "10000000001";
    private static final String DOC_TYPE_DESCRIPTION = "description";
    public static final String BL_IIDC = "BL-IIDC";
    public static final String IN_PERS = "IN-PERS";
    public static final String BL_RUGS = "BL-RUGS";
    public static final String UNIVERSITY = "000001";
    public static final String IN_CARD = "IN-CARD";
    public static final String IN_CARR = "IN-CARR";
    private ProposalHierarchyService hierarchyService;
	private DataObjectService dataObjectService;
    private KcAuthorizationService kcAuthorizationService;
    public static final String HIERARCHY_UNIT_SYNC = "HIERARCHY_UNIT_SYNC";

	@Before
	public void setup() throws Exception {
		dataObjectService = KcServiceLocator.getService(DataObjectService.class);
        kcAuthorizationService = KcServiceLocator.getService(KcAuthorizationService.class);
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
		String parentProposalNumber = getProposalHierarchyService().createHierarchy(childProposal, userId);
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
        String parentProposalNumber = getProposalHierarchyService().createHierarchy(childProposal, userId);
        childProposal.setProposalPersons(new ArrayList<>());
        createProposalPerson2(childProposal, Constants.PRINCIPAL_INVESTIGATOR_ROLE);
        DevelopmentProposal changedChildProposal = dataObjectService.save(childProposal);
        getProposalHierarchyService().synchronizeChild(changedChildProposal);
        DevelopmentProposal parentProposal = changedChildProposal.getParent();
        assertTrue(parentProposal.getProposalPerson(0).getPersonId().equalsIgnoreCase("999"));
        assertTrue(parentProposal.getProposalPerson(0).getProposalPersonRoleId().equalsIgnoreCase(Constants.CO_INVESTIGATOR_ROLE));
        getProposalHierarchyService().removeFromHierarchy(changedChildProposal);
        assertTrue(changedChildProposal.getHierarchyParentProposalNumber() == null);
        parentProposal = getDevelopmentProposal(parentProposalNumber);
        assertTrue(parentProposal.getProposalPersons().isEmpty());
        assertTrue(getProposalHierarchyService().getHierarchyChildren(parentProposalNumber).isEmpty());
    }

    @Test
    public void test_Org_sync() throws Exception {
        ProposalDevelopmentDocument pdDocument = initializeProposalDevelopmentDocument();
        DevelopmentProposal childProposal = getChildProposal(pdDocument.getDevelopmentProposal());

        String userId = PERSON_ID;
        String parentProposalNumber = getProposalHierarchyService().createHierarchy(childProposal, userId);
        DevelopmentProposal parentProposal = getDevelopmentProposal(parentProposalNumber);
        Assert.assertTrue(parentProposal.getProposalSites().size() == 2);
        Assert.assertTrue(parentProposal.getProposalSites().get(0).getLocationTypeCode().equals(ProposalSite.PROPOSAL_SITE_APPLICANT_ORGANIZATION));
        Assert.assertTrue(parentProposal.getProposalSites().get(1).getLocationTypeCode().equals(ProposalSite.PROPOSAL_SITE_PERFORMING_ORGANIZATION));

        ProposalSite site3 = new ProposalSite();
        site3.setLocationName("PROPOSAL_SITE_OTHER_ORGANIZATION");
        site3.setRolodexId(10031);
        site3.setLocationTypeCode(ProposalSite.PROPOSAL_SITE_OTHER_ORGANIZATION);
        parentProposal.addOtherOrganization(site3);
        ProposalSite site4 = new ProposalSite();
        site4.setLocationName("PROPOSAL_SITE_OTHER_ORGANIZATION");
        site4.setRolodexId(10030);
        site4.setLocationTypeCode(ProposalSite.PROPOSAL_SITE_OTHER_ORGANIZATION);
        parentProposal.addOtherOrganization(site4);
        getProposalDevService().initializeUnitOrganizationLocation(parentProposal.getProposalDocument());
        getProposalDevService().initializeProposalSiteNumbers(parentProposal.getProposalDocument());
        parentProposal = dataObjectService.save(parentProposal);

        Assert.assertTrue(parentProposal.getProposalSites().size() == 4);
        Assert.assertEquals(parentProposal.getProposalSites().stream().filter(
                proposalSite -> proposalSite.getLocationTypeCode().intValue() == ProposalSite.PROPOSAL_SITE_APPLICANT_ORGANIZATION).count(), 1);
        Assert.assertEquals(parentProposal.getProposalSites().stream().filter(
                proposalSite -> proposalSite.getLocationTypeCode().intValue() == ProposalSite.PROPOSAL_SITE_PERFORMING_ORGANIZATION).count(), 1);
        Assert.assertEquals(parentProposal.getProposalSites().stream().filter(
                proposalSite -> proposalSite.getLocationTypeCode().intValue() == ProposalSite.PROPOSAL_SITE_OTHER_ORGANIZATION).count(), 2);

        Assert.assertEquals(parentProposal.getProposalSites().stream().filter(
                proposalSite -> proposalSite.getSiteNumber() == 1).count(), 1);
        Assert.assertEquals(parentProposal.getProposalSites().stream().filter(
                proposalSite -> proposalSite.getSiteNumber() == 2).count(), 1);
        Assert.assertEquals(parentProposal.getProposalSites().stream().filter(
                proposalSite -> proposalSite.getSiteNumber() == 3).count(), 1);
        Assert.assertEquals(parentProposal.getProposalSites().stream().filter(
                proposalSite -> proposalSite.getSiteNumber() == 4).count(), 1);
    }

    public ProposalDevelopmentService getProposalDevService() {
        return KcServiceLocator.getService(ProposalDevelopmentService.class);
    }

    @Test
    public void test_adding_key_personnel_to_child_with_propBios() throws Exception {
        ProposalDevelopmentDocument pdDocument = initializeProposalDevelopmentDocument();
        DevelopmentProposal childProposal = getChildProposal(pdDocument.getDevelopmentProposal());
        String userId = PERSON_ID;
        String parentProposalNumber = getProposalHierarchyService().createHierarchy(childProposal, userId);
        childProposal.setProposalPersons(new ArrayList<>());
        createProposalPerson2(childProposal, Constants.PRINCIPAL_INVESTIGATOR_ROLE);
        addEmpPersonBios(childProposal, "Test", "Name1", childProposal.getProposalPerson(0).getPersonId(), 1, childProposal.getProposalPerson(0).getProposalPersonNumber(), "1");
        childProposal = dataObjectService.save(childProposal);
        getProposalHierarchyService().synchronizeChild(childProposal);
        DevelopmentProposal parentProposal = childProposal.getParent();
        assertTrue(parentProposal.getProposalPerson(0).getPersonId().equalsIgnoreCase("999"));
        assertTrue(parentProposal.getProposalPerson(0).getProposalPersonRoleId().equalsIgnoreCase(Constants.CO_INVESTIGATOR_ROLE));
        assertTrue(!parentProposal.getPropPersonBios().isEmpty());
        assertTrue(parentProposal.getPropPersonBios().get(0).getPersonnelAttachment().getData() != null);
        createKeyPerson(childProposal);
        getProposalHierarchyService().synchronizeChild(childProposal);
        parentProposal = childProposal.getParent();
        assertTrue(parentProposal.getProposalPersons().size() == 2);
        assertTrue(parentProposal.getProposalPersons().get(1).getProposalPersonRoleId().equalsIgnoreCase(Constants.KEY_PERSON_ROLE));
        assertTrue(!parentProposal.getPropPersonBios().isEmpty());
        assertTrue(parentProposal.getPropPersonBios().get(0).getPersonnelAttachment().getData() != null);
    }

	@Test
	public void test_deleting_propBios_one_child() throws Exception {
		ProposalDevelopmentDocument pdDocument = initializeProposalDevelopmentDocument();
		DevelopmentProposal childProposal = getChildProposal(pdDocument.getDevelopmentProposal());
		String userId = PERSON_ID;
		String parentProposalNumber = getProposalHierarchyService().createHierarchy(childProposal, userId);
		childProposal.setProposalPersons(new ArrayList<>());
		createProposalPerson2(childProposal, Constants.PRINCIPAL_INVESTIGATOR_ROLE);
		addEmpPersonBios(childProposal, "Test", "Name1", childProposal.getProposalPerson(0).getPersonId(), 1, childProposal.getProposalPerson(0).getProposalPersonNumber(), "1");
		childProposal = dataObjectService.save(childProposal);
		getProposalHierarchyService().synchronizeChild(childProposal);
		DevelopmentProposal parentProposal = childProposal.getParent();
		assertTrue(parentProposal.getProposalPerson(0).getPersonId().equalsIgnoreCase("999"));
		assertTrue(parentProposal.getProposalPerson(0).getProposalPersonRoleId().equalsIgnoreCase(Constants.CO_INVESTIGATOR_ROLE));
		assertTrue(!parentProposal.getPropPersonBios().isEmpty());
		assertTrue(parentProposal.getPropPersonBios().get(0).getPersonnelAttachment().getData() != null);
		createKeyPerson(childProposal);
		getProposalHierarchyService().synchronizeChild(childProposal);
		parentProposal = childProposal.getParent();
		assertTrue(parentProposal.getProposalPersons().size() == 2);
		assertTrue(parentProposal.getProposalPersons().get(1).getProposalPersonRoleId().equalsIgnoreCase(Constants.KEY_PERSON_ROLE));
		assertTrue(!parentProposal.getPropPersonBios().isEmpty());
		assertTrue(parentProposal.getPropPersonBios().get(0).getPersonnelAttachment().getData() != null);

		childProposal.getPropPersonBios().remove(0);
		childProposal = dataObjectService.save(childProposal);

		getProposalHierarchyService().synchronizeChild(childProposal);
		assertTrue(childProposal.getPropPersonBios().isEmpty());
		parentProposal = childProposal.getParent();
		assertTrue(parentProposal.getPropPersonBios().isEmpty());
	}

	@Test
	public void test_deleting_propBios_multi_child() throws Exception {

		DevelopmentProposal childProposal1 = getChildProposal(initializeProposalDevelopmentDocument().getDevelopmentProposal());
		getProposalHierarchyService().createHierarchy(childProposal1, PERSON_ID);
		childProposal1.setProposalPersons(new ArrayList<>());
		createProposalPerson2(childProposal1, Constants.PRINCIPAL_INVESTIGATOR_ROLE);
		addEmpPersonBios(childProposal1, "Test", "Name1", childProposal1.getProposalPerson(0).getPersonId(), 1, childProposal1.getProposalPerson(0).getProposalPersonNumber(), "1");
		childProposal1 = dataObjectService.save(childProposal1);
		getProposalHierarchyService().synchronizeChild(childProposal1);
		DevelopmentProposal parentProposal = childProposal1.getParent();
		assertTrue(parentProposal.getProposalPerson(0).getPersonId().equalsIgnoreCase("999"));
		assertTrue(parentProposal.getProposalPerson(0).getProposalPersonRoleId().equalsIgnoreCase(Constants.CO_INVESTIGATOR_ROLE));
		assertTrue(!parentProposal.getPropPersonBios().isEmpty());
		assertTrue(parentProposal.getPropPersonBios().get(0).getPersonnelAttachment().getData() != null);
		createKeyPerson(childProposal1);
		getProposalHierarchyService().synchronizeChild(childProposal1);
		parentProposal = childProposal1.getParent();
		assertTrue(parentProposal.getProposalPersons().size() == 2);
		assertTrue(parentProposal.getProposalPersons().get(1).getProposalPersonRoleId().equalsIgnoreCase(Constants.KEY_PERSON_ROLE));
		assertTrue(!parentProposal.getPropPersonBios().isEmpty());
		assertTrue(parentProposal.getPropPersonBios().get(0).getPersonnelAttachment().getData() != null);

		DevelopmentProposal childProposal2 = getChildProposal(initializeProposalDevelopmentDocument().getDevelopmentProposal());
		getProposalHierarchyService().createHierarchy(childProposal2, PERSON_ID);
		getProposalHierarchyService().linkToHierarchy(childProposal1.getParent(), childProposal2, HierarchyBudgetTypeConstants.SubBudget.code());
		createProposalPerson2(childProposal2, Constants.PRINCIPAL_INVESTIGATOR_ROLE);
		addEmpPersonBios(childProposal2, "Test", "Name1", childProposal2.getProposalPerson(0).getPersonId(), 1, childProposal2.getProposalPerson(0).getProposalPersonNumber(), "1");
		getProposalHierarchyService().synchronizeChild(childProposal2);

		childProposal2.getPropPersonBios().remove(0);
		childProposal2 = dataObjectService.save(childProposal2);

		getProposalHierarchyService().synchronizeChild(childProposal2);
		assertTrue(childProposal2.getPropPersonBios().isEmpty());
		parentProposal = childProposal2.getParent();
		assertTrue(!parentProposal.getPropPersonBios().isEmpty());
	}

    // child 1 has one person and bios for that person.
    //create another child, same person, same bio, link to parent, see what happens.
    @Test
    public void samePersonWithBiosInMultipleChildren() throws Exception {
        DevelopmentProposal childProposal = getNewProposal();
        childProposal.setProposalPersons(new ArrayList<>());
        createEmpProposalPerson(childProposal, Constants.PRINCIPAL_INVESTIGATOR_ROLE, FIRST_NAME, LAST_NAME, 1, PERSON_ID);
        childProposal = dataObjectService.save(childProposal);
        addEmpPersonBios(childProposal, "Test", "Name1", childProposal.getProposalPerson(0).getPersonId(), 1, childProposal.getProposalPerson(0).getProposalPersonNumber(), "1");
        childProposal = dataObjectService.save(childProposal);
        String parentProposalNumber = getProposalHierarchyService().createHierarchy(childProposal, PERSON_ID);

        DevelopmentProposal childProposal2 = getNewProposal();
        childProposal2.setProposalPersons(new ArrayList<>());
        createEmpProposalPerson(childProposal2, Constants.PRINCIPAL_INVESTIGATOR_ROLE, FIRST_NAME, LAST_NAME, 1, PERSON_ID);
        createProposalPerson2(childProposal2, Constants.PRINCIPAL_INVESTIGATOR_ROLE);
        childProposal2 = dataObjectService.save(childProposal2);
        addEmpPersonBios(childProposal2, "Test2", "Name2", childProposal2.getProposalPerson(0).getPersonId(), 1, childProposal2.getProposalPerson(0).getProposalPersonNumber(), "1");
        addEmpPersonBios(childProposal2, "Test3", "Name3", childProposal2.getProposalPerson(1).getPersonId(), 2, childProposal2.getProposalPerson(1).getProposalPersonNumber(), "1");
        childProposal2 = dataObjectService.save(childProposal2);
        DevelopmentProposal parentProposal = getDevelopmentProposal(parentProposalNumber);
        getProposalHierarchyService().linkChild(parentProposal, childProposal2, "", true);
        parentProposal = getDevelopmentProposal(parentProposalNumber);
        Assert.assertTrue(parentProposal.getPropPersonBios().size() == 2);
        Assert.assertTrue(parentProposal.getPropPersonBio(0).getDescription().equalsIgnoreCase("Test"));
        Assert.assertTrue(parentProposal.getPropPersonBio(1).getDescription().equalsIgnoreCase("Test3"));

    }

    // child 1 has one person and bios for that person.
    //create another child, same person, same bio, link to parent, see what happens.
    @Test
    public void changeAttachmentInChildAfterLink() throws Exception {
        DevelopmentProposal childProposal = getNewProposal();
        childProposal.setProposalPersons(new ArrayList<>());
        createEmpProposalPerson(childProposal, Constants.PRINCIPAL_INVESTIGATOR_ROLE, FIRST_NAME, LAST_NAME, 1, PERSON_ID);
        childProposal = dataObjectService.save(childProposal);
        addEmpPersonBios(childProposal, "Name1", "Name1", childProposal.getProposalPerson(0).getPersonId(), 1, childProposal.getProposalPerson(0).getProposalPersonNumber(), "1");
        childProposal = dataObjectService.save(childProposal);
        String parentProposalNumber = getProposalHierarchyService().createHierarchy(childProposal, PERSON_ID);
        childProposal.setPropPersonBios(new ArrayList<>());
        addEmpPersonBios(childProposal, "Name2", "Name2", childProposal.getProposalPerson(0).getPersonId(), 1, childProposal.getProposalPerson(0).getProposalPersonNumber(), "1");
        DevelopmentProposal parentProposal = getDevelopmentProposal(parentProposalNumber);
        getProposalHierarchyService().synchronizeAll(parentProposal);
        Assert.assertTrue(parentProposal.getPropPersonBios().size() == 1);
        Assert.assertTrue(parentProposal.getPropPersonBio(0).getDescription().equalsIgnoreCase("Name2"));
        Assert.assertTrue(parentProposal.getPropPersonBio(0).getName().equalsIgnoreCase("Name2"));
        Assert.assertTrue(parentProposal.getPropPersonBio(0).getPersonnelAttachment().getName().equalsIgnoreCase("Name2"));
    }

    // create child with bio, link to parent
    // create another child with diff bio for diff person
    // create third child with same bio as previous
    //sync all
    @Test
    public void samePersonWithBiosInMultipleChildrenSyncSimultaneously() throws Exception {
        DevelopmentProposal childProposal = getNewProposal();
        childProposal.setProposalPersons(new ArrayList<>());
        createEmpProposalPerson(childProposal, Constants.PRINCIPAL_INVESTIGATOR_ROLE, FIRST_NAME, LAST_NAME, 1, PERSON_ID);
        childProposal = dataObjectService.save(childProposal);
        addEmpPersonBios(childProposal, "Name1", "Name1", childProposal.getProposalPerson(0).getPersonId(), 1, childProposal.getProposalPerson(0).getProposalPersonNumber(), "1");
        childProposal = dataObjectService.save(childProposal);
        String parentProposalNumber = getProposalHierarchyService().createHierarchy(childProposal, PERSON_ID);

        DevelopmentProposal childProposal2 = getNewProposal();
        DevelopmentProposal parentProposal = getDevelopmentProposal(parentProposalNumber);
        getProposalHierarchyService().linkChild(parentProposal, childProposal2, "", true);

        DevelopmentProposal childProposal3 = getNewProposal();
        parentProposal = getDevelopmentProposal(parentProposalNumber);
        getProposalHierarchyService().linkChild(parentProposal, childProposal3, "", true);

        childProposal2.setProposalPersons(new ArrayList<>());
        createProposalPerson2(childProposal2, Constants.PRINCIPAL_INVESTIGATOR_ROLE);
        addEmpPersonBios(childProposal2, "Name2", "Name2", childProposal2.getProposalPerson(0).getPersonId(), 1, childProposal2.getProposalPerson(0).getProposalPersonNumber(), "1");
        childProposal2 = dataObjectService.save(childProposal2);
        childProposal3.setProposalPersons(new ArrayList<>());
        createProposalPerson2(childProposal3, Constants.PRINCIPAL_INVESTIGATOR_ROLE);
        addEmpPersonBios(childProposal3, "Name3", "Name3", childProposal2.getProposalPerson(0).getPersonId(), 1, childProposal2.getProposalPerson(0).getProposalPersonNumber(), "1");
        childProposal3 = dataObjectService.save(childProposal3);
        parentProposal = getDevelopmentProposal(parentProposalNumber);
        getProposalHierarchyService().synchronizeAll(parentProposal);

        parentProposal = getDevelopmentProposal(parentProposalNumber);
        Assert.assertTrue(parentProposal.getPropPersonBios().size() == 2);
        Assert.assertTrue(parentProposal.getPropPersonBio(0).getDescription().equalsIgnoreCase("Name1"));
    }

    @Test
    public void twoChildrenOneWithRolodexKPTest() throws Exception {
        DevelopmentProposal childProposal = getNewProposal();
        childProposal.setProposalPersons(new ArrayList<>());
        createEmpProposalPerson(childProposal, Constants.PRINCIPAL_INVESTIGATOR_ROLE, FIRST_NAME, LAST_NAME, 1, PERSON_ID);
        createNonEmpProposalPerson(childProposal, new Integer(1), Constants.PRINCIPAL_INVESTIGATOR_ROLE, "foo", "bar", 2);
        childProposal = dataObjectService.save(childProposal);

        addEmpPersonBios(childProposal, "Name1", "Name1", childProposal.getProposalPerson(0).getPersonId(), 1, childProposal.getProposalPerson(0).getProposalPersonNumber(), "1");
        addNonEmpPersonBios(childProposal, "nonEmp1", "nonEmp1", childProposal.getProposalPerson(1).getRolodexId(), 2, childProposal.getProposalPerson(1).getProposalPersonNumber(), "1");
        childProposal = dataObjectService.save(childProposal);

        String parentProposalNumber = getProposalHierarchyService().createHierarchy(childProposal, PERSON_ID);

        DevelopmentProposal childProposal2 = getNewProposal();
        createEmpProposalPerson(childProposal2, Constants.PRINCIPAL_INVESTIGATOR_ROLE, FIRST_NAME, LAST_NAME, 1, PERSON_ID);
        createNonEmpProposalPerson(childProposal2, new Integer(1), Constants.PRINCIPAL_INVESTIGATOR_ROLE, "foo", "bar", 2);
        createNonEmpProposalPerson(childProposal2, new Integer(2), Constants.KEY_PERSON_ROLE, "Leonard", "Hofstadter", 3);
        childProposal2 = dataObjectService.save(childProposal2);

        int bioPositionNumber = 1;
        addEmpPersonBios(childProposal2, "Name1", "Name1", childProposal2.getProposalPerson(0).getPersonId(), bioPositionNumber, childProposal.getProposalPerson(0).getProposalPersonNumber(), "1");
        bioPositionNumber = 2;
        addNonEmpPersonBios(childProposal2, "nonEmp1", "nonEmp1", childProposal2.getProposalPerson(1).getRolodexId(), bioPositionNumber, childProposal.getProposalPerson(1).getProposalPersonNumber(), "1");
        childProposal2 = dataObjectService.save(childProposal2);

        DevelopmentProposal parentProposal = getDevelopmentProposal(parentProposalNumber);
        getProposalHierarchyService().linkChild(parentProposal, childProposal2, "", true);
        parentProposal = getDevelopmentProposal(parentProposalNumber);
        Assert.assertTrue(parentProposal.getPropPersonBios().size() == 2);
        bioPositionNumber = 3;
        final String documentTypeCode3 = "3";
        addEmpPersonBios(parentProposal, "parent1", "parent1", parentProposal.getProposalPerson(0).getPersonId(), bioPositionNumber,
                parentProposal.getProposalPerson(0).getProposalPersonNumber(), documentTypeCode3);
        parentProposal = dataObjectService.save(parentProposal);
        Assert.assertTrue(parentProposal.getPropPersonBios().size() == 3);
        getProposalHierarchyService().synchronizeAll(parentProposal);
        Assert.assertTrue(parentProposal.getPropPersonBios().size() == 3);
    }

    public ProposalPersonUnit getPersonUnit(String unitName) {
        UnitService unitService = KcServiceLocator.getService(UnitService.class);
        Unit unit = unitService.getUnit(unitName);
        ProposalPersonUnit personUnit = new ProposalPersonUnit();
        personUnit.setUnit(unit);
        return personUnit;
    }

    protected boolean isUnitSyncEnabled() {
        return getParameterService().getParameterValueAsBoolean(Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT, Constants.KC_ALL_PARAMETER_DETAIL_TYPE_CODE,
                HIERARCHY_UNIT_SYNC);
    }

    @Test
    public void test_sync_person_units() throws Exception {
        if(isUnitSyncEnabled()) {
            ProposalDevelopmentDocument pdDocument = initializeProposalDevelopmentDocument();
            DevelopmentProposal childProposal = getChildProposal(pdDocument.getDevelopmentProposal());
            childProposal.setProposalPersons(new ArrayList<>());
            createEmpProposalPerson(childProposal, Constants.PRINCIPAL_INVESTIGATOR_ROLE, "first", "last", 1, "10000000001");
            createProposalPerson2(childProposal, Constants.CO_INVESTIGATOR_ROLE);
            KeyPersonnelService keyPersonnelService = getKeyPersonnelService();

            ProposalPerson pi = childProposal.getProposalPersons().get(0);
            keyPersonnelService.addUnitToPerson(pi, keyPersonnelService.createProposalPersonUnit(BL_IIDC, pi));
            ProposalPerson coI = childProposal.getProposalPersons().get(1);
            keyPersonnelService.addUnitToPerson(coI, keyPersonnelService.createProposalPersonUnit(IN_PERS, coI));
            keyPersonnelService.addUnitToPerson(coI, keyPersonnelService.createProposalPersonUnit(UNIVERSITY, coI));

            DevelopmentProposal changedChildProposal = dataObjectService.save(childProposal);
            String child1 = changedChildProposal.getProposalNumber();
            String userId = PERSON_ID;
            String parentProposalNumber = getProposalHierarchyService().createHierarchy(changedChildProposal, userId);
            DevelopmentProposal parentProposal = getDevelopmentProposal(parentProposalNumber);
            Assert.assertTrue(changedChildProposal.getPrincipalInvestigator().getUnits().size() == 1);

            ProposalDevelopmentDocument pdDocument2 = initializeProposalDevelopmentDocument();
            DevelopmentProposal childProposal2 = getChildProposal(pdDocument2.getDevelopmentProposal());
            childProposal2.setProposalPersons(new ArrayList<>());
            createEmpProposalPerson(childProposal2, Constants.PRINCIPAL_INVESTIGATOR_ROLE, "first", "last", 1, "10000000001");
            createEmpProposalPerson(childProposal2, Constants.CO_INVESTIGATOR_ROLE, "Dominic", "Nugent", 2, "10000000011");
            ProposalPerson pi2 = childProposal2.getProposalPersons().get(0);
            keyPersonnelService.addUnitToPerson(pi2, keyPersonnelService.createProposalPersonUnit(UNIVERSITY, pi2));
            keyPersonnelService.addUnitToPerson(pi2, keyPersonnelService.createProposalPersonUnit(BL_IIDC, pi2));
            keyPersonnelService.addUnitToPerson(pi2, keyPersonnelService.createProposalPersonUnit(BL_RUGS, pi2));

            ProposalPerson coI2 = childProposal2.getProposalPersons().get(1);
            keyPersonnelService.addUnitToPerson(coI2, keyPersonnelService.createProposalPersonUnit(UNIVERSITY, coI2));
            keyPersonnelService.addUnitToPerson(coI2, keyPersonnelService.createProposalPersonUnit(IN_CARD, coI2));

            DevelopmentProposal changedChildProposal2 = dataObjectService.save(childProposal2);
            Assert.assertTrue(changedChildProposal.getPrincipalInvestigator().getUnits().size() == 1);
            getProposalHierarchyService().linkToHierarchy(parentProposal, changedChildProposal2, "");
            parentProposal = getDevelopmentProposal(parentProposalNumber);

            Assert.assertTrue(parentProposal.getProposalPersons().size() == 3);
            Assert.assertTrue(parentProposal.getPrincipalInvestigator().getUnits().size() == 3);
            Assert.assertTrue(parentProposal.getInvestigators().get(1).getUnits().size() == 2);
            Assert.assertTrue(parentProposal.getInvestigators().get(2).getUnits().size() == 2);
            changedChildProposal = getDevelopmentProposal(child1);

            coI2 = childProposal2.getProposalPersons().get(1);
            keyPersonnelService.addUnitToPerson(coI2, keyPersonnelService.createProposalPersonUnit(IN_CARR, coI2));

            changedChildProposal2 = dataObjectService.save(childProposal2);
            parentProposal = getDevelopmentProposal(parentProposalNumber);
            getProposalHierarchyService().synchronizeAllChildren(parentProposal);
            changedChildProposal = getDevelopmentProposal(child1);

            Assert.assertTrue(parentProposal.getInvestigators().get(2).getUnits().size() == 3);

            changedChildProposal2.getPrincipalInvestigator().getUnits().remove(2);
            changedChildProposal2 = dataObjectService.save(changedChildProposal2);
            changedChildProposal = getDevelopmentProposal(child1);
            Assert.assertTrue(changedChildProposal2.getPrincipalInvestigator().getUnits().size() == 2);
        }
    }


    @Test
    public void test_pi_lead_unit_sync() throws Exception {
        ProposalDevelopmentDocument pdDocument = initializeProposalDevelopmentDocument();
        DevelopmentProposal childProposal = getChildProposal(pdDocument.getDevelopmentProposal());
        childProposal.setProposalPersons(new ArrayList<>());
        createEmpProposalPerson(childProposal, Constants.PRINCIPAL_INVESTIGATOR_ROLE, "first", "last", 1, "10000000001");
        KeyPersonnelService keyPersonnelService = getKeyPersonnelService();

        ProposalPerson pi = childProposal.getProposalPersons().get(0);
        keyPersonnelService.addUnitToPerson(pi, keyPersonnelService.createProposalPersonUnit(BL_IIDC, pi));
        pi.getUnit(BL_IIDC).setLeadUnit(true);

        DevelopmentProposal changedChildProposal = dataObjectService.save(childProposal);
        String userId = PERSON_ID;
        String parentProposalNumber = getProposalHierarchyService().createHierarchy(changedChildProposal, userId);
        DevelopmentProposal parentProposal = getDevelopmentProposal(parentProposalNumber);
        Assert.assertTrue(changedChildProposal.getPrincipalInvestigator().getUnits().size() == 1);
        Assert.assertTrue(changedChildProposal.getPrincipalInvestigator().getUnit(BL_IIDC).isLeadUnit());

        ProposalDevelopmentDocument pdDocument2 = initializeProposalDevelopmentDocument();
        DevelopmentProposal childProposal2 = getChildProposal(pdDocument2.getDevelopmentProposal());
        childProposal2.setProposalPersons(new ArrayList<>());
        createEmpProposalPerson(childProposal2, Constants.PRINCIPAL_INVESTIGATOR_ROLE, "ba", "barackus", 1, "10000000011");

        ProposalPerson pi2 = childProposal2.getProposalPersons().get(0);
        keyPersonnelService.addUnitToPerson(pi2, keyPersonnelService.createProposalPersonUnit(UNIVERSITY, pi2));
        pi2.getUnit(UNIVERSITY).setLeadUnit(true);

        DevelopmentProposal changedChildProposal2 = dataObjectService.save(childProposal2);
        Assert.assertTrue(changedChildProposal.getPrincipalInvestigator().getUnits().size() == 1);
        getProposalHierarchyService().linkToHierarchy(parentProposal, changedChildProposal2, "");
        parentProposal = getDevelopmentProposal(parentProposalNumber);
        List<ProposalPerson> ppuList = parentProposal.getProposalPersons().stream().filter(person -> person.getPersonId().equalsIgnoreCase("10000000011")).collect(Collectors.toList());
        assertTrue(!ppuList.get(0).getUnit(UNIVERSITY).isLeadUnit());
    }


    private void addEmpPersonBios(DevelopmentProposal proposal, String description, String name, String personId, int bioPositionNumber, Integer proposalPersonNumber, String documentTypeCode) throws Exception {
        ProposalPersonBiography proposalPersonBiography = createProposalPersonBiography(proposal, description, name, bioPositionNumber, proposalPersonNumber, documentTypeCode);
        proposalPersonBiography.setPersonId(personId);
        proposal.getPropPersonBios().add(proposalPersonBiography);
    }

    private void addNonEmpPersonBios(DevelopmentProposal proposal, String description, String name, Integer rolodexId, int bioPositionNumber, Integer proposalPersonNumber, String documentTypeCode) throws Exception {
        ProposalPersonBiography proposalPersonBiography = createProposalPersonBiography(proposal, description, name, bioPositionNumber, proposalPersonNumber, documentTypeCode);
        proposalPersonBiography.setRolodexId(rolodexId);
        proposal.getPropPersonBios().add(proposalPersonBiography);
    }

    private ProposalPersonBiography createProposalPersonBiography(DevelopmentProposal proposal, String description, String name, int bioPositionNumber, Integer proposalPersonNumber, String documentTypeCode) throws Exception {
        ProposalPersonBiography proposalPersonBiography = new ProposalPersonBiography();
        proposalPersonBiography.setDescription(description);
        proposalPersonBiography.setContentType("MIME");
        proposalPersonBiography.setDocumentTypeCode(documentTypeCode);
        proposalPersonBiography.setName(name);
        proposalPersonBiography.setPositionNumber(bioPositionNumber);
        proposalPersonBiography.setPropPerDocType(getAPropPerDocType());
        proposalPersonBiography.setDevelopmentProposal(proposal);
        proposalPersonBiography.setBiographyNumber(proposal.getProposalDocument().getDocumentNextValue(Constants.PROP_PERSON_BIO_NUMBER));
        proposalPersonBiography.setProposalPersonNumber(proposalPersonNumber);

        ProposalPersonBiographyAttachment attachment = new ProposalPersonBiographyAttachment();
        attachment.setName(name);
        attachment.setType("MIME");
        attachment.setFileDataId("00111");
        MockMultipartFile mockMultipartFile = new MockMultipartFile(
                "test.txt",                //filename
                name, "MIME",
                "Hello World".getBytes()); //content
        proposalPersonBiography.init(mockMultipartFile);
        return proposalPersonBiography;
    }

    protected PropPerDocType getAPropPerDocType() {
        Map<String,String> criteria = new HashMap<String,String>();
        criteria.put(DOC_TYPE_DESCRIPTION, "Biosketch");
        return dataObjectService.findMatching(PropPerDocType.class, QueryByCriteria.Builder.andAttributes(criteria).build()).getResults().get(0);
    }

    @Test
    public void test_linkToHierarchy() throws Exception {
        ProposalDevelopmentDocument pdDocument = initializeProposalDevelopmentDocument();
        DevelopmentProposal childProposal = getChildProposal(pdDocument.getDevelopmentProposal());
        DevelopmentProposal hierarchyProposal = setDevelopmentProposalAdditionalData(pdDocument.getDevelopmentProposal(), childProposal);
        getBudget(hierarchyProposal);
        String hierarchyBudgetTypeCode = "B";
        getProposalHierarchyService().linkToHierarchy(hierarchyProposal, childProposal, hierarchyBudgetTypeCode);
    }

    @Test
    public void test_removeFromHierarchy() throws Exception {
        ProposalDevelopmentDocument pdDocument = initializeProposalDevelopmentDocument();
        DevelopmentProposal childProposal = getChildProposal(pdDocument.getDevelopmentProposal());
        DevelopmentProposal hierarchyProposal = setDevelopmentProposalAdditionalData(pdDocument.getDevelopmentProposal(), childProposal);
        getBudget(hierarchyProposal);
        String hierarchyBudgetTypeCode = "B";
        getProposalHierarchyService().linkToHierarchy(hierarchyProposal, childProposal, hierarchyBudgetTypeCode);
        getProposalHierarchyService().removeFromHierarchy(childProposal);
    }

    @Test
    public void test_synchronizeChild() throws Exception {
        ProposalDevelopmentDocument pdDocument = initializeProposalDevelopmentDocument();
        DevelopmentProposal childProposal = getChildProposal(pdDocument.getDevelopmentProposal());
        DevelopmentProposal hierarchyProposal = setDevelopmentProposalAdditionalData(pdDocument.getDevelopmentProposal(), childProposal);
        getBudget(hierarchyProposal);
        String hierarchyBudgetTypeCode = "B";
        getProposalHierarchyService().linkToHierarchy(hierarchyProposal, childProposal, hierarchyBudgetTypeCode);
        getProposalHierarchyService().synchronizeChild(childProposal);
	}

	@Test
	public void test_synchronizeChildProposalBudget() throws Exception {
        ProposalDevelopmentDocument pdDocument = initializeProposalDevelopmentDocument();
        DevelopmentProposal childProposal = getChildProposal(pdDocument.getDevelopmentProposal());
        DevelopmentProposal hierarchyProposal = setDevelopmentProposalAdditionalData(pdDocument.getDevelopmentProposal(), childProposal);
        getBudget(hierarchyProposal);
        String hierarchyBudgetTypeCode = "B";
        getProposalHierarchyService().linkToHierarchy(hierarchyProposal, childProposal, hierarchyBudgetTypeCode);
        ProposalDevelopmentBudgetExt budget = childProposal.getLatestBudget();
		getProposalHierarchyService().synchronizeChildBudget(hierarchyProposal, budget);
	}

	@Test
	public void test_synchronizeAllChildren() throws Exception {
        ProposalDevelopmentDocument pdDocument = initializeProposalDevelopmentDocument();
        DevelopmentProposal childProposal = getChildProposal(pdDocument.getDevelopmentProposal());
        DevelopmentProposal hierarchyProposal = setDevelopmentProposalAdditionalData(pdDocument.getDevelopmentProposal(), childProposal);
        getBudget(hierarchyProposal);
        String hierarchyBudgetTypeCode = "B";
        getProposalHierarchyService().linkToHierarchy(hierarchyProposal, childProposal, hierarchyBudgetTypeCode);
        getProposalHierarchyService().synchronizeAllChildren(pdDocument.getDevelopmentProposal());
	}

	@Test
	public void test_getDevelopmentProposal() throws Exception {
        ProposalDevelopmentDocument pdDocument = initializeProposalDevelopmentDocument();
        DevelopmentProposal childProposal = getChildProposal(pdDocument.getDevelopmentProposal());
        DevelopmentProposal hierarchyProposal = setDevelopmentProposalAdditionalData(pdDocument.getDevelopmentProposal(), childProposal);

        DevelopmentProposal developmentProposal = null;
		String proposalNumber = hierarchyProposal.getProposalNumber();
		developmentProposal = getProposalHierarchyService().getDevelopmentProposal(proposalNumber);
        assertNotNull(developmentProposal);
        assertEquals(hierarchyProposal, developmentProposal);
        assertEquals(proposalNumber, developmentProposal.getProposalNumber());
	}

	public void test_lookupParent(DevelopmentProposal parentProposal, DevelopmentProposal childProposal) {
		DevelopmentProposal developmentProposal;
		developmentProposal = getProposalHierarchyService().lookupParent(childProposal);
		assertNotNull(developmentProposal);
		assertEquals(parentProposal, developmentProposal);
		assertEquals(parentProposal.getProposalNumber(),
				developmentProposal.getProposalNumber());
	}

	public void test_getHierarchyPersonnelSummaries(DevelopmentProposal hierarchyProposal) {
		List<HierarchyPersonnelSummary> hierarchyPersonnelSummaries = null;
		hierarchyPersonnelSummaries = getProposalHierarchyService()
				.getHierarchyPersonnelSummaries(hierarchyProposal.getProposalNumber());
        assertNotNull(hierarchyPersonnelSummaries);
	}

	public void test_getHierarchyProposalSummaries(DevelopmentProposal hierarchyProposal) {
		List<HierarchyProposalSummary> hierarchyProposalSummaries = null;
		hierarchyProposalSummaries = getProposalHierarchyService()
				.getHierarchyProposalSummaries(hierarchyProposal.getProposalNumber());
		assertNotNull(hierarchyProposalSummaries);
        assertTrue(hierarchyProposalSummaries.size() > 0);
	}

	public void test_validateChildBudgetPeriods(DevelopmentProposal hierarchyProposal, DevelopmentProposal childProposal) {
		List<ProposalHierarchyErrorWarningDto> hierarchyErrorWarningDto = null;
		hierarchyErrorWarningDto = getProposalHierarchyService().validateChildBudgetPeriods(
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
		hierarchyErrorWarningDto = getProposalHierarchyService().validateChildBudgetPeriods(
                hierarchyProposal, childProposal, true);
		String errorKey = "error.hierarchy.budget.startDateInconsistent";
		assertNotNull(hierarchyErrorWarningDto);
		assertTrue(!hierarchyErrorWarningDto.isEmpty());
        assertEquals(errorKey, hierarchyErrorWarningDto.get(0).getErrorKey());
	}

	public void test_getHierarchyProposals(DevelopmentProposal childProposal) {
		List<DevelopmentProposal> developmentProposals = null;
		developmentProposals = getProposalHierarchyService().getHierarchyProposals(childProposal);
		assertNotNull(developmentProposals);
		assertTrue(developmentProposals.size() == 2);
		assertEquals(childProposal.getHierarchyParentProposalNumber(),
				developmentProposals.get(0).getProposalNumber());
	}

	public void test_getParentWorkflowDocument(DevelopmentProposal childProposal) throws Exception {
		WorkflowDocument workflowDocument = null;
		workflowDocument = getProposalHierarchyService()
				.getParentWorkflowDocument(childProposal.getProposalDocument());
        assertNotNull(workflowDocument);
	}

	public void test_getParentDocument(DevelopmentProposal childProposal, ProposalDevelopmentDocument parentDocument) throws Exception {
        ProposalDevelopmentDocument document = getProposalHierarchyService().getParentDocument(childProposal.getProposalDocument());
		assertNotNull(document);
        assertEquals(parentDocument.getDocumentNumber(), document.getDocumentNumber());
	}

	public void test_getHierarchyChildren(DevelopmentProposal childProposal) {
		List<DevelopmentProposal> developmentProposals = null;
		developmentProposals = getProposalHierarchyService()
				.getHierarchyChildren(childProposal.getHierarchyParentProposalNumber());
        assertNotNull(developmentProposals);
	}

	public void test_validateRemovePermissions(DevelopmentProposal childProposal) {
        getProposalHierarchyService().removeFromHierarchy(childProposal);
		boolean valid = true;
		valid = getProposalHierarchyService().validateRemovePermissions(childProposal, PERSON_ID);
        assertFalse(valid);
	}

	public void test_calculateAndSetProposalAppDocStatus(DevelopmentProposal childProposal) {
		DocumentRouteStatusChange dto = new DocumentRouteStatusChange(
				childProposal.getProposalDocument().getDocumentNumber(), null,
				KewApiConstants.ROUTE_HEADER_INITIATED_CD,
				KewApiConstants.ROUTE_HEADER_ENROUTE_CD);
		getProposalHierarchyService().calculateAndSetProposalAppDocStatus(
                childProposal.getProposalDocument(), dto);
	}

	public void test_getSyncableBudget(DevelopmentProposal childProposal) {
		ProposalDevelopmentBudgetExt budgetExt = null;
		budgetExt = getProposalHierarchyService().getSyncableBudget(childProposal);
		assertNotNull(budgetExt);
        assertEquals(childProposal.getLatestBudget(), budgetExt);
	}

	public void test_getProposalSummary(DevelopmentProposal hierarchyProposal) {
		HierarchyProposalSummary proposalSummary = null;
		proposalSummary = getProposalHierarchyService().getProposalSummary(hierarchyProposal.getProposalNumber());
		assertNotNull(proposalSummary);
		assertEquals(hierarchyProposal.getProposalNumber(),
				proposalSummary.getProposalNumber());
	}

	public void test_validateChildCandidate(DevelopmentProposal hierarchyProposal) {
		hierarchyProposal.setHierarchyStatus(HierarchyStatusConstants.None.code());
		ProposalDevelopmentBudgetExt finalBudget = hierarchyProposal.getLatestBudget();
		hierarchyProposal.setFinalBudget(finalBudget);
		List<ProposalHierarchyErrorWarningDto> errors;
		errors = getProposalHierarchyService().validateChildCandidate(hierarchyProposal);
        assertTrue(errors.isEmpty());
	}

	public void test_validateChildCandidate_inHierarchy(DevelopmentProposal childProposal) {
		ProposalDevelopmentBudgetExt finalBudget = childProposal.getLatestBudget();
		childProposal.setFinalBudget(finalBudget);
		List<ProposalHierarchyErrorWarningDto> errors;
		String errorKey = "error.hierarchy.link.alreadyHierarchyMember";
		errors = getProposalHierarchyService().validateChildCandidate(childProposal);
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
        errors = getProposalHierarchyService().validateChildCandidate(hierarchyProposal);
		assertNotNull(errors);
        assertTrue(errors.size() == 2);
        assertTrue(errors.get(0).getErrorKey().equalsIgnoreCase(errorKey1));
        assertTrue(errors.get(1).getErrorKey().equalsIgnoreCase(errorKey2));
    }

	public void test_validateChildForSync(DevelopmentProposal hierarchyProposal, DevelopmentProposal childProposal) {
		List<ProposalHierarchyErrorWarningDto> errors;
		errors = getProposalHierarchyService().validateChildForSync(childProposal, hierarchyProposal, true);
		assertTrue(errors.isEmpty());
	}

	public void test_validateChildForSync_nullPI(DevelopmentProposal hierarchyProposal, DevelopmentProposal childProposal) {
		List<ProposalHierarchyErrorWarningDto> errors;
		String errorKey = "error.hierarchy.sync.noPrincipleInvestigator";
		childProposal.getProposalPersons().clear();
		errors = getProposalHierarchyService().validateChildForSync(childProposal, hierarchyProposal, true);
		assertNotNull(errors);
		assertTrue(errors.size() == 3);
        assertEquals(errorKey, errors.get(0).getErrorKey());
	}

	public void test_validateChildForRemoval(DevelopmentProposal childProposal) {
		List<ProposalHierarchyErrorWarningDto> errors;
		errors = getProposalHierarchyService().validateChildForRemoval(childProposal);
        assertTrue(errors.isEmpty());
	}

	public void test_validateParent(DevelopmentProposal hierarchyProposal) {
		List<ProposalHierarchyErrorWarningDto> errors;
		errors = getProposalHierarchyService().validateParent(hierarchyProposal);
		assertTrue(errors.isEmpty());
	}

	public void test_validateParent_noParent(DevelopmentProposal hierarchyProposal) {
		List<ProposalHierarchyErrorWarningDto> errors;
		String errorKey = "error.hierarchy.link.notParent";
		hierarchyProposal.setHierarchyStatus(HierarchyStatusConstants.None.code());
		errors = getProposalHierarchyService().validateParent(hierarchyProposal);
		assertNotNull(errors);
		assertTrue(errors.size() == 1);
        assertEquals(errorKey, errors.get(0).getErrorKey());
	}

	public void test_validateChildCandidateForHierarchy(DevelopmentProposal hierarchyProposal, DevelopmentProposal childProposal) {
		List<ProposalHierarchyErrorWarningDto> errors;
		errors = getProposalHierarchyService().validateChildCandidateForHierarchy(
                hierarchyProposal, childProposal, true);
        assertTrue(errors.isEmpty());
	}

	public void test_validateChildCandidateForHierarchy_differentSponsorCode(DevelopmentProposal hierarchyProposal, DevelopmentProposal childProposal) {
		List<ProposalHierarchyErrorWarningDto> errors;
		childProposal.setSponsorCode("000010");
		String errorKey = "warning.hierarchy.link.differentSponsor";
		errors = getProposalHierarchyService().validateChildCandidateForHierarchy(
                hierarchyProposal, childProposal, true);
		assertNotNull(errors);
		assertTrue(errors.size() == 1);
        assertEquals(errorKey, errors.get(0).getErrorKey());
	}

	public void test_validateLinkToHierarchy_inHierarchy(DevelopmentProposal hierarchyProposal, DevelopmentProposal childProposal) {
		List<ProposalHierarchyErrorWarningDto> errors;
		String errorKey = "error.hierarchy.proposal.not.hierarchy.child";
		errors = getProposalHierarchyService().validateLinkToHierarchy(hierarchyProposal, childProposal);
		assertNotNull(errors);
		assertTrue(errors.toString(), errors.size() == 1);
		assertEquals(errorKey, errors.get(0).getErrorKey());
	}

	public void test_isSynchronized(DevelopmentProposal childProposal) {
		boolean sync = false;
		sync = getProposalHierarchyService().isSynchronized(childProposal);
        assertFalse(sync);
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
        Unit result = getDataObjectService().find(Unit.class, UNIVERSITY);
        pd.getDevelopmentProposal().setOwnedByUnit(result);
        pd.getDevelopmentProposal().setOwnedByUnitNumber(UNIVERSITY);
        pdService.initializeUnitOrganizationLocation(pd);
        pdService.initializeProposalSiteNumbers(pd);
        kcAuthorizationService.addDocumentLevelRole(PERSON_ID, RoleConstants.AGGREGATOR_DOCUMENT_LEVEL, pd);
        return pd;
    }

	private DevelopmentProposal initializeDevelopmentProposal(
			ProposalDevelopmentDocument pd) {
		DevelopmentProposal developmentProposal = pd.getDevelopmentProposal();
		developmentProposal.setPrimeSponsorCode("000120");
		developmentProposal.setActivityTypeCode("1");
		developmentProposal.refreshReferenceObject("activityType");
		developmentProposal.setSponsorCode("000100");
		developmentProposal.setOwnedByUnitNumber(UNIVERSITY);
		developmentProposal.refreshReferenceObject("ownedByUnit");
		developmentProposal.setProposalTypeCode("1");
		developmentProposal.setCreationStatusCode("1");
		developmentProposal.setApplicantOrganizationId(UNIVERSITY);
		developmentProposal.setPerformingOrganizationId(UNIVERSITY);
		developmentProposal.setNoticeOfOpportunityCode("1");
		developmentProposal.setRequestedStartDateInitial(DateUtils.newDate(2014, Calendar.JULY, 10));
		developmentProposal.setRequestedEndDateInitial(DateUtils.newDate(2015, Calendar.JULY, 10));
		developmentProposal.setTitle("Test Proposal Hierarchy Service");
		developmentProposal.setDeadlineType("P");
		developmentProposal.setDeadlineDate(DateUtils.newDate(2015, Calendar.JULY, 10));
		developmentProposal.setNsfSequenceNumber(1);
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
		createEmpProposalPerson(developmentProposal, Constants.PRINCIPAL_INVESTIGATOR_ROLE, FIRST_NAME, LAST_NAME, 1, PERSON_ID);
        createSponsorActivity(developmentProposal);
		createPropScienceKeyword(developmentProposal);
		createNarrative(developmentProposal);
		createProposalSpecialReview(developmentProposal);
		developmentProposal = dataObjectService.save(developmentProposal);
		return developmentProposal;
	}

    private void createSponsorActivity(DevelopmentProposal developmentProposal) {
        createSponsor(developmentProposal);
        createActivityType(developmentProposal);
    }

    private void createEmpProposalPerson(DevelopmentProposal developmentProposal, String role, String firstName, String lastName, int proposalPersonNumber, String personId) {
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
        person.setPersonId(personId);
    }

    private void createNonEmpProposalPerson(DevelopmentProposal developmentProposal, int rolodexId, String role, String firstName, String lastName, int proposalPersonNumber) {
        ProposalPerson person = new ProposalPerson();
        person.setProposalPersonNumber(proposalPersonNumber);
        person.setProposalPersonRoleId(role);
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setMiddleName("middleName");
        setPersonData(person);
        person.setRolodexId(rolodexId);
        person.setPersonId(null);
        person.setEraCommonsUserName("firstUser");
        person.setDevelopmentProposal(developmentProposal);
        developmentProposal.getProposalPersons().add(person);
    }

    private void createProposalPerson2(DevelopmentProposal developmentProposal, String role) {
        ProposalPerson person = new ProposalPerson();
        person.setProposalPersonNumber(2);
        person.setProposalPersonRoleId(role);
        person.setFirstName("Bruce");
        person.setLastName("Wayne");
        person.setMiddleName("middleName");
        setPersonData(person);
        person.setRolodexId(2);
        person.setEraCommonsUserName("bwayne");
        person.setDevelopmentProposal(developmentProposal);
        developmentProposal.getProposalPersons().add(person);
        person.setPersonId("999");
    }

    private void createKeyPerson(DevelopmentProposal developmentProposal) {
        ProposalPerson person = new ProposalPerson();
        person.setProposalPersonNumber(3);
        person.setProposalPersonRoleId(Constants.KEY_PERSON_ROLE);
        person.setFirstName("The");
        person.setLastName("Dude");
        person.setMiddleName("middleName");
        setPersonData(person);
        person.setDevelopmentProposal(developmentProposal);
        developmentProposal.getProposalPersons().add(person);
        person.setPersonId("555");
        person.setEraCommonsUserName("thedude");
        person.setProjectRole("Big Lebowski");
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

    public DevelopmentProposal getNewProposal() throws Exception {
        ProposalDevelopmentDocument document = initializeProposalDevelopmentDocument();
        DevelopmentProposal proposal = document.getDevelopmentProposal();
        createSponsorActivity(proposal);
        proposal = dataObjectService.save(proposal);
        getBudget(proposal);
        return proposal;
    }

    public DevelopmentProposal getChildProposal(DevelopmentProposal hierarchyProposal) throws Exception {
		ProposalDevelopmentDocument document = initializeProposalDevelopmentDocument();
		DevelopmentProposal childProposal = document.getDevelopmentProposal();
		childProposal.setHierarchyStatus(HierarchyStatusConstants.Child.code());
		childProposal.setHierarchyParentProposalNumber(hierarchyProposal.getProposalNumber());
		childProposal = setDevelopmentProposalAdditionalData(childProposal, null);
		childProposal = dataObjectService.save(childProposal);
		getBudget(childProposal);
		return childProposal;
	}

    public ProposalHierarchyService getProposalHierarchyService() {
        if(hierarchyService == null) {
            hierarchyService = KcServiceLocator.getService(ProposalHierarchyService.class);
        }
        return hierarchyService;
    }

    public DataObjectService getDataObjectService() {
        if(dataObjectService == null) {
            dataObjectService = KcServiceLocator.getService(DataObjectService.class);
        }
        return dataObjectService;
    }
    
    public KeyPersonnelService getKeyPersonnelService() {
        return KcServiceLocator.getService(KeyPersonnelService.class);
    }

    private ParameterService getParameterService() {
        return KcServiceLocator.getService(ParameterService.class);
    }

}
