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
package org.kuali.kra.award.contacts;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.api.sponsor.hierarchy.SponsorHierarchyService;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.PropAwardPersonRole;
import org.kuali.coeus.common.framework.rolodex.NonOrganizationalRolodex;
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.coeus.common.impl.person.PropAwardPersonRoleServiceImpl;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.ContactRole;
import org.kuali.kra.bo.KcPersonFixtureFactory;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.core.api.criteria.GenericQueryResults;
import org.kuali.rice.core.api.criteria.PredicateFactory;
import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.util.ErrorMessage;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.MessageMap;

import java.util.ArrayList;
import java.util.List;

/**
 * This class tests AddAwardProjectPersonRuleImpl
 */
public class AwardProjectPersonsSaveRuleImplTest {

    private static final String MISSING_UNIT_DETAILS_NOT_IDENTIFIED = "Missing unit Details not identified";
    private static final int ROLODEX_ID = 1002;
    private Award award;
    private AwardProjectPersonsSaveRuleImpl rule;
    private Unit unitA;
    private Unit unitB;
    private static final String PERSON_ID = "1001";
    private static final String KP_PERSON_ID = "1002";

    private AwardPerson piPerson;
    private AwardPerson coiPerson;
    private AwardPerson kpPerson;

    private Mockery context;
    private ArrayList<PropAwardPersonRole> roles;
    private GenericQueryResults.Builder roleListBuilder;
	protected static final String SPONSOR_CODE = "000500";
	
	private PropAwardPersonRoleServiceImpl roleService;

    @Before
    public void setUp() {
        context = new JUnit4Mockery() {{ setThreadingPolicy(new Synchroniser()); }};
        roleListBuilder = GenericQueryResults.Builder.create();
        roleListBuilder.setResults(new ArrayList<PropAwardPersonRole>(){{
        	add(createTestRole(3L, "PI", PropAwardPersonRoleServiceImpl.NIH_MULTIPLE_PI_HIERARCHY));
        	add(createTestRole(4L, "KP", PropAwardPersonRoleServiceImpl.NIH_MULTIPLE_PI_HIERARCHY));
        	add(createTestRole(5L, "MPI", PropAwardPersonRoleServiceImpl.NIH_MULTIPLE_PI_HIERARCHY));
        	add(createTestRole(6L, "COI", PropAwardPersonRoleServiceImpl.NIH_MULTIPLE_PI_HIERARCHY));
        }});

        
        rule = new AwardProjectPersonsSaveRuleImpl();
        award = new Award();
        award.setSponsorCode(SPONSOR_CODE);

        unitA = new Unit();
        unitA.setUnitName("a");
        unitA.setUnitNumber("1");

        unitB = new Unit();
        unitB.setUnitName("b");
        unitB.setUnitNumber("2");

        KcPerson employee = KcPersonFixtureFactory.createKcPerson(PERSON_ID);
        piPerson = new AwardPerson(employee, ContactRoleFixtureFactory.MOCK_PI);
        piPerson.add(new AwardPersonUnit(piPerson, unitA, true));

        NonOrganizationalRolodex nonEmployee;
        nonEmployee = new NonOrganizationalRolodex();
        nonEmployee.setRolodexId(ROLODEX_ID);
        coiPerson = new AwardPerson(nonEmployee, ContactRoleFixtureFactory.MOCK_COI);
        coiPerson.add(new AwardPersonUnit(coiPerson, unitA, false));

        KcPerson employee2 = KcPersonFixtureFactory.createKcPerson(KP_PERSON_ID);
        kpPerson = new AwardPerson(employee2, ContactRoleFixtureFactory.MOCK_KEY_PERSON);
        kpPerson.setKeyPersonRole("Tester");                
        kpPerson.add(new AwardPersonUnit(kpPerson, unitA, false));

        piPerson.setAward(award);
        coiPerson.setAward(award);
        kpPerson.setAward(award);

        roleService = getPropAwardPersonRoleService();
        piPerson.setPropAwardPersonRoleService(roleService);
        coiPerson.setPropAwardPersonRoleService(roleService);
        kpPerson.setPropAwardPersonRoleService(roleService);
        
        award.add(piPerson);
        award.add(coiPerson);
        award.add(kpPerson);
        
        GlobalVariables.setMessageMap(new MessageMap());
    }
    
    @After
    public void tearDown() {
        rule = null;
        award = null;
    }
    
    @Test
    public void testCheckForExistingPI() {
        Assert.assertTrue("PI not found or more than one found", rule.checkForOnePrincipalInvestigator(award.getProjectPersons()));
        award.getProjectPersons().remove(0);
        Assert.assertFalse("PI existence check failed", rule.checkForOnePrincipalInvestigator(award.getProjectPersons()));
        checkErrorState(AwardProjectPersonsSaveRule.AWARD_PROJECT_PERSON_LIST_ERROR_KEY, AwardProjectPersonsSaveRule.ERROR_AWARD_PROJECT_PERSON_NO_PI);
    }

    @Test
    public void testCheckForMultiplePIs() {
        AwardPerson newPerson = new AwardPerson(KcPersonFixtureFactory.createKcPerson(KP_PERSON_ID), ContactRoleFixtureFactory.MOCK_PI);
        newPerson.setPropAwardPersonRoleService(roleService);
        award.add(newPerson);
        Assert.assertFalse("Multiple PIs not detected", rule.checkForOnePrincipalInvestigator(award.getProjectPersons()));

        checkErrorState(AwardProjectPersonsSaveRule.AWARD_PROJECT_PERSON_LIST_ERROR_KEY,
                                AwardProjectPersonsSaveRule.ERROR_AWARD_PROJECT_PERSON_MULTIPLE_PI_EXISTS);
    }

    @Test
    public void testCheckForRequiredUnitDetails_PI() {
        Assert.assertTrue(MISSING_UNIT_DETAILS_NOT_IDENTIFIED, rule.checkForRequiredUnitDetails(award.getProjectPersons()));

        piPerson.getUnits().clear();
        Assert.assertFalse(MISSING_UNIT_DETAILS_NOT_IDENTIFIED, rule.checkForRequiredUnitDetails(award.getProjectPersons()));

        checkErrorState(AwardProjectPersonsSaveRule.AWARD_PROJECT_PERSON_LIST_ERROR_KEY,
                                AwardProjectPersonsSaveRule.ERROR_AWARD_PROJECT_PERSON_UNIT_DETAILS_REQUIRED);
    }

    @Test
    public void testCheckForRequiredUnitDetails_COI() {
        Assert.assertTrue(MISSING_UNIT_DETAILS_NOT_IDENTIFIED, rule.checkForRequiredUnitDetails(award.getProjectPersons()));

        coiPerson.getUnits().clear();
        Assert.assertFalse(MISSING_UNIT_DETAILS_NOT_IDENTIFIED, rule.checkForRequiredUnitDetails(award.getProjectPersons()));

        checkErrorState(AwardProjectPersonsSaveRule.AWARD_PROJECT_PERSON_LIST_ERROR_KEY,
                                AwardProjectPersonsSaveRule.ERROR_AWARD_PROJECT_PERSON_UNIT_DETAILS_REQUIRED);
    }

    @Test
    public void testCheckForUnitDetailsNotRequired_KP() {
        Assert.assertTrue(MISSING_UNIT_DETAILS_NOT_IDENTIFIED, rule.checkForRequiredUnitDetails(award.getProjectPersons()));

        kpPerson.getUnits().clear();
        Assert.assertTrue(MISSING_UNIT_DETAILS_NOT_IDENTIFIED, rule.checkForRequiredUnitDetails(award.getProjectPersons()));
        Assert.assertEquals(0, GlobalVariables.getMessageMap().getErrorCount());
    }
    
    @Test
    public void testCheckForDuplicateUnits_NoneFound() {
        piPerson.add(new AwardPersonUnit(piPerson, unitB, false));
        Assert.assertTrue(rule.checkForDuplicateUnits(award.getProjectPersons()));
    }

    @Test
    public void testCheckForDuplicateUnits_DupeFound() {
        piPerson.add(new AwardPersonUnit(piPerson, unitA, false));
        Assert.assertFalse("Duplicate should have been found", rule.checkForDuplicateUnits(award.getProjectPersons()));

        checkErrorState(AwardProjectPersonsSaveRule.AWARD_PROJECT_PERSON_LIST_ERROR_KEY,
                                AwardProjectPersonsSaveRule.ERROR_AWARD_PROJECT_PERSON_DUPLICATE_UNITS);
    }

    @Test
    public void testCheckForLeadUnit_NoneFound() {
        Assert.assertTrue("No lead unit was found", rule.checkForLeadUnitForPI(award.getProjectPersons()));

        piPerson.getUnit(0).setLeadUnit(false);
        Assert.assertFalse("No lead unit should have been found", rule.checkForLeadUnitForPI(award.getProjectPersons()));

        checkErrorState(AwardProjectPersonsSaveRule.AWARD_PROJECT_PERSON_LIST_ERROR_KEY,
                                AwardProjectPersonsSaveRule.ERROR_AWARD_PROJECT_PERSON_LEAD_UNIT_REQUIRED);
    }

    @Test
    public void testCheckForKeyPersonRole_NotFound() {
        kpPerson.setKeyPersonRole(null);
        Assert.assertFalse("Key Person Role not checked for", rule.checkForKeyPersonProjectRoles(award.getProjectPersons()));

        checkErrorState(AwardProjectPersonsSaveRule.AWARD_PROJECT_PERSON_LIST_ERROR_KEY,
                                AwardProjectPersonsSaveRule.ERROR_AWARD_PROJECT_KEY_PERSON_ROLE_REQUIRED);
    }

    @Test
    public void testCheckForKeyPersonRole_Found() {
        Assert.assertTrue("Key Person Role not checked for", rule.checkForKeyPersonProjectRoles(award.getProjectPersons()));
    }
    
    @Test
    public void testProjectRolesChanges() {
        // when a coi is changed to key person
        coiPerson.setContactRole(ContactRoleFixtureFactory.MOCK_KEY_PERSON);
        Assert.assertFalse("Key Person Role not checked for", rule.checkForKeyPersonProjectRoles(award.getProjectPersons()));
        coiPerson.setKeyPersonRole("fromCOI");
        Assert.assertTrue("Key Person Role not checked for", rule.checkForKeyPersonProjectRoles(award.getProjectPersons()));
        
        // when a key person is changed to coi
        kpPerson.setContactRole(ContactRoleFixtureFactory.MOCK_COI);
        Assert.assertTrue("rule should return true", rule.processSaveAwardProjectPersonsBusinessRules(award.getProjectPersons()));
    }
    

    private void checkErrorState(String errorProperty, String errorMessageKey) {
        MessageMap messageMap = GlobalVariables.getMessageMap();
        Assert.assertTrue( messageMap.getErrorCount() >= 1);
        @SuppressWarnings("unchecked") List<ErrorMessage> errors = messageMap.getErrorMessagesForProperty(errorProperty);
        if(errors != null) {
            Assert.assertEquals(1, errors.size());
            Assert.assertEquals(errorMessageKey, errors.get(0).getErrorKey());
        } else {
            Assert.fail("No errors posted");
        }

        Assert.assertFalse("rule should return false", rule.processSaveAwardProjectPersonsBusinessRules(award.getProjectPersons()));
    }

    protected PropAwardPersonRole createTestRole(Long id, String code, String hierarchyName) {
        PropAwardPersonRole role = new PropAwardPersonRole();
        role.setId(id);
        role.setCode(code);
        role.setSponsorHierarchyName(hierarchyName);
        return role;
    }

    protected PropAwardPersonRoleServiceImpl getPropAwardPersonRoleService() {
    	final PropAwardPersonRoleServiceImpl roleService = new PropAwardPersonRoleServiceImpl();
    	final DataObjectService dataObjectService = context.mock(DataObjectService.class);
    	final ParameterService parameterService = context.mock(ParameterService.class);
    	final SponsorHierarchyService hierarchyService = context.mock(SponsorHierarchyService.class);

        context.checking(new Expectations() {{
        	one(parameterService).getParameterValueAsBoolean(Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT, Constants.PARAMETER_COMPONENT_DOCUMENT, PropAwardPersonRoleServiceImpl.ALL_SPONSOR_HIERARCHY_NIH_MULTI_PI);
        	will(returnValue(true));
        	one(dataObjectService).findMatching(PropAwardPersonRole.class, QueryByCriteria.Builder.forAttribute("sponsorHierarchyName", PropAwardPersonRoleServiceImpl.NIH_MULTIPLE_PI_HIERARCHY).build());
        	will(returnValue(roleListBuilder.build()));
        }});
        
        
		final QueryByCriteria.Builder criteriaCoInvestigator1 = 
				QueryByCriteria.Builder.create().setPredicates(PredicateFactory.equal("code", ContactRole.COI_CODE), PredicateFactory.equal("sponsorHierarchyName", PropAwardPersonRoleServiceImpl.NIH_MULTIPLE_PI_HIERARCHY));
		final PropAwardPersonRole roleCoInvestigator1 = createTestRole(3L, ContactRole.COI_CODE, PropAwardPersonRoleServiceImpl.NIH_MULTIPLE_PI_HIERARCHY);
        context.checking(new Expectations() {{
        	one(parameterService).getParameterValueAsBoolean(Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT, Constants.PARAMETER_COMPONENT_DOCUMENT, PropAwardPersonRoleServiceImpl.ALL_SPONSOR_HIERARCHY_NIH_MULTI_PI);
        	will(returnValue(true));
        	one(dataObjectService).findUnique(PropAwardPersonRole.class, criteriaCoInvestigator1.build());
        	will(returnValue(roleCoInvestigator1));
        }});
        
		final QueryByCriteria.Builder criteriaCoInvestigator2 = 
				QueryByCriteria.Builder.create().setPredicates(PredicateFactory.equal("code", ContactRole.COI_CODE), PredicateFactory.equal("sponsorHierarchyName", PropAwardPersonRoleServiceImpl.NIH_MULTIPLE_PI_HIERARCHY));
		final PropAwardPersonRole roleCoInvestigator2 = createTestRole(3L, ContactRole.COI_CODE, PropAwardPersonRoleServiceImpl.NIH_MULTIPLE_PI_HIERARCHY);
        context.checking(new Expectations() {{
        	one(parameterService).getParameterValueAsBoolean(Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT, Constants.PARAMETER_COMPONENT_DOCUMENT, PropAwardPersonRoleServiceImpl.ALL_SPONSOR_HIERARCHY_NIH_MULTI_PI);
        	will(returnValue(true));
        	one(dataObjectService).findUnique(PropAwardPersonRole.class, criteriaCoInvestigator2.build());
        	will(returnValue(roleCoInvestigator2));
        }});
        
		final QueryByCriteria.Builder criteriaKeyPerson1 = 
				QueryByCriteria.Builder.create().setPredicates(PredicateFactory.equal("code", ContactRole.KEY_PERSON_CODE), PredicateFactory.equal("sponsorHierarchyName", PropAwardPersonRoleServiceImpl.NIH_MULTIPLE_PI_HIERARCHY));
		final PropAwardPersonRole roleKeyPerson1 = createTestRole(3L, ContactRole.KEY_PERSON_CODE, PropAwardPersonRoleServiceImpl.NIH_MULTIPLE_PI_HIERARCHY);
        context.checking(new Expectations() {{
        	one(parameterService).getParameterValueAsBoolean(Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT, Constants.PARAMETER_COMPONENT_DOCUMENT, PropAwardPersonRoleServiceImpl.ALL_SPONSOR_HIERARCHY_NIH_MULTI_PI);
        	will(returnValue(true));
        	one(dataObjectService).findUnique(PropAwardPersonRole.class, criteriaKeyPerson1.build());
        	will(returnValue(roleKeyPerson1));
        }});
        
		final QueryByCriteria.Builder criteriaKeyPerson2 = 
				QueryByCriteria.Builder.create().setPredicates(PredicateFactory.equal("code", ContactRole.KEY_PERSON_CODE), PredicateFactory.equal("sponsorHierarchyName", PropAwardPersonRoleServiceImpl.NIH_MULTIPLE_PI_HIERARCHY));
		final PropAwardPersonRole roleKeyPerson2 = createTestRole(3L, ContactRole.KEY_PERSON_CODE, PropAwardPersonRoleServiceImpl.NIH_MULTIPLE_PI_HIERARCHY);
        context.checking(new Expectations() {{
        	one(parameterService).getParameterValueAsBoolean(Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT, Constants.PARAMETER_COMPONENT_DOCUMENT, PropAwardPersonRoleServiceImpl.ALL_SPONSOR_HIERARCHY_NIH_MULTI_PI);
        	will(returnValue(true));
        	one(dataObjectService).findUnique(PropAwardPersonRole.class, criteriaKeyPerson2.build());
        	will(returnValue(roleKeyPerson2));
        }});
        
		final QueryByCriteria.Builder criteriaKeyPerson3 = 
				QueryByCriteria.Builder.create().setPredicates(PredicateFactory.equal("code", ContactRole.KEY_PERSON_CODE), PredicateFactory.equal("sponsorHierarchyName", PropAwardPersonRoleServiceImpl.NIH_MULTIPLE_PI_HIERARCHY));
		final PropAwardPersonRole roleKeyPerson3 = createTestRole(3L, ContactRole.KEY_PERSON_CODE, PropAwardPersonRoleServiceImpl.NIH_MULTIPLE_PI_HIERARCHY);
        context.checking(new Expectations() {{
        	one(parameterService).getParameterValueAsBoolean(Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT, Constants.PARAMETER_COMPONENT_DOCUMENT, PropAwardPersonRoleServiceImpl.ALL_SPONSOR_HIERARCHY_NIH_MULTI_PI);
        	will(returnValue(true));
        	one(dataObjectService).findUnique(PropAwardPersonRole.class, criteriaKeyPerson3.build());
        	will(returnValue(roleKeyPerson3));
        }});
        
		final QueryByCriteria.Builder criteriaKeyPerson4 = 
				QueryByCriteria.Builder.create().setPredicates(PredicateFactory.equal("code", ContactRole.KEY_PERSON_CODE), PredicateFactory.equal("sponsorHierarchyName", PropAwardPersonRoleServiceImpl.NIH_MULTIPLE_PI_HIERARCHY));
		final PropAwardPersonRole roleKeyPerson4 = createTestRole(3L, ContactRole.KEY_PERSON_CODE, PropAwardPersonRoleServiceImpl.NIH_MULTIPLE_PI_HIERARCHY);
        context.checking(new Expectations() {{
        	one(parameterService).getParameterValueAsBoolean(Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT, Constants.PARAMETER_COMPONENT_DOCUMENT, PropAwardPersonRoleServiceImpl.ALL_SPONSOR_HIERARCHY_NIH_MULTI_PI);
        	will(returnValue(true));
        	one(dataObjectService).findUnique(PropAwardPersonRole.class, criteriaKeyPerson4.build());
        	will(returnValue(roleKeyPerson4));
        }});
        
		final QueryByCriteria.Builder criteriaKeyPerson5 = 
				QueryByCriteria.Builder.create().setPredicates(PredicateFactory.equal("code", ContactRole.KEY_PERSON_CODE), PredicateFactory.equal("sponsorHierarchyName", PropAwardPersonRoleServiceImpl.NIH_MULTIPLE_PI_HIERARCHY));
		final PropAwardPersonRole roleKeyPerson5 = createTestRole(3L, ContactRole.KEY_PERSON_CODE, PropAwardPersonRoleServiceImpl.NIH_MULTIPLE_PI_HIERARCHY);
        context.checking(new Expectations() {{
        	one(parameterService).getParameterValueAsBoolean(Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT, Constants.PARAMETER_COMPONENT_DOCUMENT, PropAwardPersonRoleServiceImpl.ALL_SPONSOR_HIERARCHY_NIH_MULTI_PI);
        	will(returnValue(true));
        	one(dataObjectService).findUnique(PropAwardPersonRole.class, criteriaKeyPerson5.build());
        	will(returnValue(roleKeyPerson5));
        }});
        
 		final QueryByCriteria.Builder criteriaPrincipalInvestigator1 = 
				QueryByCriteria.Builder.create().setPredicates(PredicateFactory.equal("code", ContactRole.PI_CODE), PredicateFactory.equal("sponsorHierarchyName", PropAwardPersonRoleServiceImpl.NIH_MULTIPLE_PI_HIERARCHY));
		final PropAwardPersonRole rolePrincipalInvestigator1 = createTestRole(3L, ContactRole.PI_CODE, PropAwardPersonRoleServiceImpl.NIH_MULTIPLE_PI_HIERARCHY);
        context.checking(new Expectations() {{
        	one(parameterService).getParameterValueAsBoolean(Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT, Constants.PARAMETER_COMPONENT_DOCUMENT, PropAwardPersonRoleServiceImpl.ALL_SPONSOR_HIERARCHY_NIH_MULTI_PI);
        	will(returnValue(true));
        	one(dataObjectService).findUnique(PropAwardPersonRole.class, criteriaPrincipalInvestigator1.build());
        	will(returnValue(rolePrincipalInvestigator1));
        }});
        
		final QueryByCriteria.Builder criteriaPrincipalInvestigator2 = 
				QueryByCriteria.Builder.create().setPredicates(PredicateFactory.equal("code", ContactRole.PI_CODE), PredicateFactory.equal("sponsorHierarchyName", PropAwardPersonRoleServiceImpl.NIH_MULTIPLE_PI_HIERARCHY));
		final PropAwardPersonRole rolePrincipalInvestigator2 = createTestRole(3L, ContactRole.PI_CODE, PropAwardPersonRoleServiceImpl.NIH_MULTIPLE_PI_HIERARCHY);
        context.checking(new Expectations() {{
        	one(parameterService).getParameterValueAsBoolean(Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT, Constants.PARAMETER_COMPONENT_DOCUMENT, PropAwardPersonRoleServiceImpl.ALL_SPONSOR_HIERARCHY_NIH_MULTI_PI);
        	will(returnValue(true));
        	one(dataObjectService).findUnique(PropAwardPersonRole.class, criteriaPrincipalInvestigator2.build());
        	will(returnValue(rolePrincipalInvestigator2));
        }});
        
		final QueryByCriteria.Builder criteriaPrincipalInvestigator3 = 
				QueryByCriteria.Builder.create().setPredicates(PredicateFactory.equal("code", ContactRole.PI_CODE), PredicateFactory.equal("sponsorHierarchyName", PropAwardPersonRoleServiceImpl.NIH_MULTIPLE_PI_HIERARCHY));
		final PropAwardPersonRole rolePrincipalInvestigator3 = createTestRole(3L, ContactRole.PI_CODE, PropAwardPersonRoleServiceImpl.NIH_MULTIPLE_PI_HIERARCHY);
        context.checking(new Expectations() {{
        	one(parameterService).getParameterValueAsBoolean(Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT, Constants.PARAMETER_COMPONENT_DOCUMENT, PropAwardPersonRoleServiceImpl.ALL_SPONSOR_HIERARCHY_NIH_MULTI_PI);
        	will(returnValue(true));
        	one(dataObjectService).findUnique(PropAwardPersonRole.class, criteriaPrincipalInvestigator3.build());
        	will(returnValue(rolePrincipalInvestigator3));
        }});
        
        roleService.setDataObjectService(dataObjectService);
        roleService.setSponsorHierarchyService(hierarchyService);
        roleService.setParameterService(parameterService);
        return roleService;
    }    

}
