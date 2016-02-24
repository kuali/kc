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

import java.util.ArrayList;

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
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.MessageMap;

/**
 * This class tests AddAwardProjectPersonRuleImpl
 */
public class AwardProjectPersonAddRuleImplTest {

    private static final int ROLODEX_ID = 1002;
    private Award award;
    private AwardProjectPersonAddRuleImpl rule;
    private KcPerson person1;
    private static final String PERSON_ID = "1001";
    private Mockery context;
    private ArrayList<PropAwardPersonRole> roles;
    private GenericQueryResults.Builder roleListBuilder;
	protected static final String SPONSOR_CODE = "000500";

    @Before
    public void setUp() {
        context = new JUnit4Mockery() {{ setThreadingPolicy(new Synchroniser()); }};
        roleListBuilder = GenericQueryResults.Builder.create();
        
        rule = new AwardProjectPersonAddRuleImpl();
        award = new Award();

        person1 = KcPersonFixtureFactory.createKcPerson(PERSON_ID);

        NonOrganizationalRolodex person2 = new NonOrganizationalRolodex();
        person2.setRolodexId(ROLODEX_ID);
        
        award.add(new AwardPerson(person1, ContactRoleFixtureFactory.MOCK_PI));
        award.add(new AwardPerson(person2, ContactRoleFixtureFactory.MOCK_COI));
        
        
        GlobalVariables.setMessageMap(new MessageMap());
    }

    @After
    public void tearDown() {
        rule = null;
        award = null;
    }

    @Test
    public void testCheckForExistingPI_DuplicateFound() {
        AwardPerson newPerson = new AwardPerson(new KcPerson(), ContactRoleFixtureFactory.MOCK_PI);
        Assert.assertFalse("Duplicate PI not identified", rule.checkForExistingPrincipalInvestigators(award, newPerson));
    }

    @Test
    public void testCheckForExistingPI_NoDuplicateFound() {
        AwardPerson newPerson = new AwardPerson(new KcPerson(), ContactRoleFixtureFactory.MOCK_KEY_PERSON);
        Assert.assertTrue("Duplicate PI misidentified", rule.checkForExistingPrincipalInvestigators(award, newPerson));
    }

    @Test
    public void testCheckForDuplicateContact_DuplicatePersonFound() {
        KcPerson duplicatePerson = KcPersonFixtureFactory.createKcPerson(PERSON_ID);
        duplicatePerson.setPersonId(person1.getPersonId());
        AwardPerson newPerson = new AwardPerson(duplicatePerson, ContactRoleFixtureFactory.MOCK_KEY_PERSON);
        Assert.assertFalse("Duplicate Person not identified", rule.checkForDuplicatePerson(award, newPerson));
    }


    @Test
    public void testCheckForDuplicateContact_DuplicateRolodexFound() {
        NonOrganizationalRolodex duplicatePerson = new NonOrganizationalRolodex ();
        duplicatePerson.setRolodexId(ROLODEX_ID);
        AwardPerson newPerson = new AwardPerson(duplicatePerson, ContactRoleFixtureFactory.MOCK_KEY_PERSON);
        Assert.assertFalse("Duplicate Rolodex not identified", rule.checkForDuplicatePerson(award, newPerson));
    }
    
    @Test
    public void testCheckForKeyPersonRole_NotFound() {
    	final PropAwardPersonRoleServiceImpl roleService = new PropAwardPersonRoleServiceImpl();
    	final DataObjectService dataObjectService = context.mock(DataObjectService.class);
    	final ParameterService parameterService = context.mock(ParameterService.class);
    	final SponsorHierarchyService hierarchyService = context.mock(SponsorHierarchyService.class);

		final QueryByCriteria.Builder criteria = 
				QueryByCriteria.Builder.create().setPredicates(PredicateFactory.equal("code", ContactRole.KEY_PERSON_CODE), PredicateFactory.equal("sponsorHierarchyName", PropAwardPersonRoleServiceImpl.NIH_MULTIPLE_PI_HIERARCHY));
		final PropAwardPersonRole role = createTestRole(3L, ContactRole.KEY_PERSON_CODE, PropAwardPersonRoleServiceImpl.NIH_MULTIPLE_PI_HIERARCHY);
        context.checking(new Expectations() {{
        	one(parameterService).getParameterValueAsBoolean(Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT, Constants.PARAMETER_COMPONENT_DOCUMENT, PropAwardPersonRoleServiceImpl.ALL_SPONSOR_HIERARCHY_NIH_MULTI_PI);
        	will(returnValue(false));
        	one(parameterService).getParameterValuesAsString(Constants.KC_GENERIC_PARAMETER_NAMESPACE, Constants.KC_ALL_PARAMETER_DETAIL_TYPE_CODE, PropAwardPersonRoleServiceImpl.SPONSOR_HIERARCHIES_PARM);
        	will(returnValue(new ArrayList<String>(){{
        		add(PropAwardPersonRoleServiceImpl.NIH_MULTIPLE_PI_HIERARCHY);
        	}}));
        	one(hierarchyService).isSponsorInHierarchy(SPONSOR_CODE, PropAwardPersonRoleServiceImpl.NIH_MULTIPLE_PI_HIERARCHY);
        	will(returnValue(true));
        	one(dataObjectService).findUnique(PropAwardPersonRole.class, criteria.build());
        	will(returnValue(role));
        }});
        
        roleService.setDataObjectService(dataObjectService);
        roleService.setSponsorHierarchyService(hierarchyService);
        roleService.setParameterService(parameterService);
 
    	
    	award.setSponsorCode(SPONSOR_CODE);
        AwardPerson newPerson = new AwardPerson(new KcPerson(), ContactRoleFixtureFactory.MOCK_KEY_PERSON);
        newPerson.setKeyPersonRole(null);
        newPerson.setAward(award);
        newPerson.setPropAwardPersonRoleService(roleService);

        Assert.assertFalse("Key Person Role not checked for", rule.checkForKeyPersonProjectRoles(newPerson));
    }

    protected PropAwardPersonRole createTestRole(Long id, String code, String hierarchyName) {
        PropAwardPersonRole role = new PropAwardPersonRole();
        role.setId(id);
        role.setCode(code);
        role.setSponsorHierarchyName(hierarchyName);
        return role;
    }

}
