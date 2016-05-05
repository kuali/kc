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
package org.kuali.coeus.common.impl.person;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.api.sponsor.hierarchy.SponsorHierarchyService;
import org.kuali.coeus.common.framework.person.PropAwardPersonRole;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.core.api.criteria.GenericQueryResults;
import org.kuali.rice.core.api.criteria.PredicateFactory;
import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.data.DataObjectService;

public class PropAwardPersonRoleServiceTest {

    private Mockery context;
    private ArrayList<PropAwardPersonRole> roles;
    private GenericQueryResults.Builder roleListBuilder;

    @Before()
    public void setUpMockery() {
        context = new JUnit4Mockery() {{ setThreadingPolicy(new Synchroniser()); }};
                
        roleListBuilder = GenericQueryResults.Builder.create();
        roleListBuilder.setResults(new ArrayList<PropAwardPersonRole>(){{
        	add(createTestRole(3L, "PI", PropAwardPersonRoleServiceImpl.NIH_MULTIPLE_PI_HIERARCHY));
        	add(createTestRole(4L, "MPI", PropAwardPersonRoleServiceImpl.NIH_MULTIPLE_PI_HIERARCHY));
        	add(createTestRole(5L, "COI", PropAwardPersonRoleServiceImpl.NIH_MULTIPLE_PI_HIERARCHY));
        }});
    }
        
    protected PropAwardPersonRole createTestRole(Long id, String code, String hierarchyName) {
        PropAwardPersonRole role = new PropAwardPersonRole();
        role.setId(id);
        role.setCode(code);
        role.setSponsorHierarchyName(hierarchyName);
        return role;
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void test_getRole_nullId() {
        final PropAwardPersonRoleServiceImpl roleService = new PropAwardPersonRoleServiceImpl();
        roleService.getRole(null);
    }
    
    @Test
    public void test_getRole_notExist() {
    	final PropAwardPersonRoleServiceImpl roleService = new PropAwardPersonRoleServiceImpl();
    	final DataObjectService dataObjectService = context.mock(DataObjectService.class);
        context.checking(new Expectations() {{
            one(dataObjectService).find(PropAwardPersonRole.class, 1L);
            will(returnValue(null));
        }});
        
        roleService.setDataObjectService(dataObjectService);
    	assertNull(roleService.getRole(1L));
    }
    
    @Test
    public void test_getRole_success() {
    	final PropAwardPersonRoleServiceImpl roleService = new PropAwardPersonRoleServiceImpl();
    	final DataObjectService dataObjectService = context.mock(DataObjectService.class);
    	final PropAwardPersonRole role = new PropAwardPersonRole();
    	role.setId(1L);
        context.checking(new Expectations() {{
            one(dataObjectService).find(PropAwardPersonRole.class, 1L);
            will(returnValue(role));
        }});
        
        roleService.setDataObjectService(dataObjectService);
    	assertEquals(roleService.getRole(1L), role);	
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void test_getRole_nullRoleCode() {
        final PropAwardPersonRoleServiceImpl roleService = new PropAwardPersonRoleServiceImpl();
        roleService.getRole(null, "000340");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void test_getRole_nullSponsorCode() {
        final PropAwardPersonRoleServiceImpl roleService = new PropAwardPersonRoleServiceImpl();
        roleService.getRole("PI", null);
    }
    
    @Test
    public void test_getRole_allNih() {
    	final PropAwardPersonRoleServiceImpl roleService = new PropAwardPersonRoleServiceImpl();
    	final DataObjectService dataObjectService = context.mock(DataObjectService.class);
    	final ParameterService parameterService = context.mock(ParameterService.class);
    	final SponsorHierarchyService hierarchyService = context.mock(SponsorHierarchyService.class);

		final QueryByCriteria.Builder criteria = 
				QueryByCriteria.Builder.create().setPredicates(PredicateFactory.equal("code", "PI"), PredicateFactory.equal("sponsorHierarchyName", PropAwardPersonRoleServiceImpl.NIH_MULTIPLE_PI_HIERARCHY));
		final PropAwardPersonRole role = createTestRole(3L, "PI", PropAwardPersonRoleServiceImpl.NIH_MULTIPLE_PI_HIERARCHY);
        context.checking(new Expectations() {{
        	one(parameterService).getParameterValueAsBoolean(Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT, Constants.PARAMETER_COMPONENT_DOCUMENT, Constants.ALL_SPONSOR_HIERARCHY_NIH_MULTI_PI);
        	will(returnValue(true));
        	one(dataObjectService).findUnique(PropAwardPersonRole.class, criteria.build());
        	will(returnValue(role));
        }});
        
        roleService.setDataObjectService(dataObjectService);
        roleService.setSponsorHierarchyService(hierarchyService);
        roleService.setParameterService(parameterService);
    	assertEquals(roleService.getRole("PI", "000500"), role);
    }
    
    @Test
    public void test_getRole_inNihSponsor() {
    	final PropAwardPersonRoleServiceImpl roleService = new PropAwardPersonRoleServiceImpl();
    	final DataObjectService dataObjectService = context.mock(DataObjectService.class);
    	final ParameterService parameterService = context.mock(ParameterService.class);
    	final SponsorHierarchyService hierarchyService = context.mock(SponsorHierarchyService.class);

		final QueryByCriteria.Builder criteria = 
				QueryByCriteria.Builder.create().setPredicates(PredicateFactory.equal("code", "PI"), PredicateFactory.equal("sponsorHierarchyName", PropAwardPersonRoleServiceImpl.NIH_MULTIPLE_PI_HIERARCHY));
		final PropAwardPersonRole role = createTestRole(3L, "PI", PropAwardPersonRoleServiceImpl.NIH_MULTIPLE_PI_HIERARCHY);
        context.checking(new Expectations() {{
        	one(parameterService).getParameterValueAsBoolean(Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT, Constants.PARAMETER_COMPONENT_DOCUMENT, Constants.ALL_SPONSOR_HIERARCHY_NIH_MULTI_PI);
        	will(returnValue(false));
        	one(parameterService).getParameterValuesAsString(Constants.KC_GENERIC_PARAMETER_NAMESPACE, Constants.KC_ALL_PARAMETER_DETAIL_TYPE_CODE, PropAwardPersonRoleServiceImpl.SPONSOR_HIERARCHIES_PARM);
        	will(returnValue(new ArrayList<String>(){{
        		add(PropAwardPersonRoleServiceImpl.NIH_MULTIPLE_PI_HIERARCHY);
        	}}));
        	one(hierarchyService).isSponsorInHierarchy("000340", PropAwardPersonRoleServiceImpl.NIH_MULTIPLE_PI_HIERARCHY);
        	will(returnValue(true));
        	one(dataObjectService).findUnique(PropAwardPersonRole.class, criteria.build());
        	will(returnValue(role));
        }});
        
        roleService.setDataObjectService(dataObjectService);
        roleService.setSponsorHierarchyService(hierarchyService);
        roleService.setParameterService(parameterService);
    	assertEquals(roleService.getRole("PI", "000340"), role);
    }
    
    @Test
    public void test_getRole_inNotInSponsorHierarchy() {
    	final PropAwardPersonRoleServiceImpl roleService = new PropAwardPersonRoleServiceImpl();
    	final DataObjectService dataObjectService = context.mock(DataObjectService.class);
    	final ParameterService parameterService = context.mock(ParameterService.class);
    	final SponsorHierarchyService hierarchyService = context.mock(SponsorHierarchyService.class);

		final QueryByCriteria.Builder criteria = 
				QueryByCriteria.Builder.create().setPredicates(PredicateFactory.equal("code", "PI"), PredicateFactory.equal("sponsorHierarchyName", PropAwardPersonRoleServiceImpl.DEFAULT_SPONSOR_HIERARCHY_NAME));
		final PropAwardPersonRole role = createTestRole(1L, "PI", PropAwardPersonRoleServiceImpl.DEFAULT_SPONSOR_HIERARCHY_NAME);
        context.checking(new Expectations() {{
        	one(parameterService).getParameterValueAsBoolean(Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT, Constants.PARAMETER_COMPONENT_DOCUMENT, Constants.ALL_SPONSOR_HIERARCHY_NIH_MULTI_PI);
        	will(returnValue(false));
        	one(parameterService).getParameterValuesAsString(Constants.KC_GENERIC_PARAMETER_NAMESPACE, Constants.KC_ALL_PARAMETER_DETAIL_TYPE_CODE, PropAwardPersonRoleServiceImpl.SPONSOR_HIERARCHIES_PARM);
        	will(returnValue(new ArrayList<String>(){{
        		add(PropAwardPersonRoleServiceImpl.NIH_MULTIPLE_PI_HIERARCHY);
        	}}));
        	one(hierarchyService).isSponsorInHierarchy("000500", PropAwardPersonRoleServiceImpl.NIH_MULTIPLE_PI_HIERARCHY);
        	will(returnValue(false));
        	one(dataObjectService).findUnique(PropAwardPersonRole.class, criteria.build());
        	will(returnValue(role));
        }});
        
        roleService.setDataObjectService(dataObjectService);
        roleService.setSponsorHierarchyService(hierarchyService);
        roleService.setParameterService(parameterService);
    	assertEquals(roleService.getRole("PI", "000500"), role);
    }

    @Test
    public void test_getRole_roleNotInSponsorHierarchy() {
    	final PropAwardPersonRoleServiceImpl roleService = new PropAwardPersonRoleServiceImpl();
    	final DataObjectService dataObjectService = context.mock(DataObjectService.class);
    	final ParameterService parameterService = context.mock(ParameterService.class);
    	final SponsorHierarchyService hierarchyService = context.mock(SponsorHierarchyService.class);

		final QueryByCriteria.Builder criteria = 
				QueryByCriteria.Builder.create().setPredicates(PredicateFactory.equal("code", "MPI"), PredicateFactory.equal("sponsorHierarchyName", PropAwardPersonRoleServiceImpl.DEFAULT_SPONSOR_HIERARCHY_NAME));
        context.checking(new Expectations() {{
        	one(parameterService).getParameterValueAsBoolean(Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT, Constants.PARAMETER_COMPONENT_DOCUMENT, Constants.ALL_SPONSOR_HIERARCHY_NIH_MULTI_PI);
        	will(returnValue(false));
        	one(parameterService).getParameterValuesAsString(Constants.KC_GENERIC_PARAMETER_NAMESPACE, Constants.KC_ALL_PARAMETER_DETAIL_TYPE_CODE, PropAwardPersonRoleServiceImpl.SPONSOR_HIERARCHIES_PARM);
        	will(returnValue(new ArrayList<String>(){{
        		add(PropAwardPersonRoleServiceImpl.NIH_MULTIPLE_PI_HIERARCHY);
        	}}));
        	one(hierarchyService).isSponsorInHierarchy("000500", PropAwardPersonRoleServiceImpl.NIH_MULTIPLE_PI_HIERARCHY);
        	will(returnValue(false));
        	one(dataObjectService).findUnique(PropAwardPersonRole.class, criteria.build());
        	will(returnValue(null));
        }});
        
        roleService.setDataObjectService(dataObjectService);
        roleService.setSponsorHierarchyService(hierarchyService);
        roleService.setParameterService(parameterService);
    	assertNull(roleService.getRole("MPI", "000500"));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void test_getRolesByHIerarchy_nullCode() {
        final PropAwardPersonRoleServiceImpl roleService = new PropAwardPersonRoleServiceImpl();
        roleService.getRolesByHierarchy(null);
    }
    
    @Test
    public void test_getRolesByHierarchy_allNih() {
    	final PropAwardPersonRoleServiceImpl roleService = new PropAwardPersonRoleServiceImpl();
    	final DataObjectService dataObjectService = context.mock(DataObjectService.class);
    	final ParameterService parameterService = context.mock(ParameterService.class);
    	final SponsorHierarchyService hierarchyService = context.mock(SponsorHierarchyService.class);

        context.checking(new Expectations() {{
        	one(parameterService).getParameterValueAsBoolean(Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT, Constants.PARAMETER_COMPONENT_DOCUMENT, Constants.ALL_SPONSOR_HIERARCHY_NIH_MULTI_PI);
        	will(returnValue(true));
        	one(dataObjectService).findMatching(PropAwardPersonRole.class, QueryByCriteria.Builder.forAttribute("sponsorHierarchyName", PropAwardPersonRoleServiceImpl.NIH_MULTIPLE_PI_HIERARCHY).build());
        	will(returnValue(roleListBuilder.build()));
        }});
        
        roleService.setDataObjectService(dataObjectService);
        roleService.setSponsorHierarchyService(hierarchyService);
        roleService.setParameterService(parameterService);
    	assertEquals(roleService.getRolesByHierarchy("000340").size(), 3);
    }
    
    @Test
    public void test_getRolesByHierarchy_isNih() {
    	final PropAwardPersonRoleServiceImpl roleService = new PropAwardPersonRoleServiceImpl();
    	final DataObjectService dataObjectService = context.mock(DataObjectService.class);
    	final ParameterService parameterService = context.mock(ParameterService.class);
    	final SponsorHierarchyService hierarchyService = context.mock(SponsorHierarchyService.class);

        context.checking(new Expectations() {{
        	one(parameterService).getParameterValueAsBoolean(Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT, Constants.PARAMETER_COMPONENT_DOCUMENT, Constants.ALL_SPONSOR_HIERARCHY_NIH_MULTI_PI);
        	will(returnValue(false));
        	one(parameterService).getParameterValuesAsString(Constants.KC_GENERIC_PARAMETER_NAMESPACE, Constants.KC_ALL_PARAMETER_DETAIL_TYPE_CODE, PropAwardPersonRoleServiceImpl.SPONSOR_HIERARCHIES_PARM);
        	will(returnValue(new ArrayList<String>(){{
        		add(PropAwardPersonRoleServiceImpl.NIH_MULTIPLE_PI_HIERARCHY);
        	}}));
        	one(hierarchyService).isSponsorInHierarchy("000340", PropAwardPersonRoleServiceImpl.NIH_MULTIPLE_PI_HIERARCHY);
        	will(returnValue(true));
        	one(dataObjectService).findMatching(PropAwardPersonRole.class, QueryByCriteria.Builder.forAttribute("sponsorHierarchyName", PropAwardPersonRoleServiceImpl.NIH_MULTIPLE_PI_HIERARCHY).build()).getResults();
        	will(returnValue(roleListBuilder.build()));
        }});
        
        roleService.setDataObjectService(dataObjectService);
        roleService.setSponsorHierarchyService(hierarchyService);
        roleService.setParameterService(parameterService);
    	assertEquals(roleService.getRolesByHierarchy("000340").size(), 3);
    }
    
    @Test
    public void test_getRolesByHierarchy_notNih() {
    	final PropAwardPersonRoleServiceImpl roleService = new PropAwardPersonRoleServiceImpl();
    	final DataObjectService dataObjectService = context.mock(DataObjectService.class);
    	final ParameterService parameterService = context.mock(ParameterService.class);
    	final SponsorHierarchyService hierarchyService = context.mock(SponsorHierarchyService.class);

        context.checking(new Expectations() {{
        	one(parameterService).getParameterValueAsBoolean(Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT, Constants.PARAMETER_COMPONENT_DOCUMENT, Constants.ALL_SPONSOR_HIERARCHY_NIH_MULTI_PI);
        	will(returnValue(false));
        	one(parameterService).getParameterValuesAsString(Constants.KC_GENERIC_PARAMETER_NAMESPACE, Constants.KC_ALL_PARAMETER_DETAIL_TYPE_CODE, PropAwardPersonRoleServiceImpl.SPONSOR_HIERARCHIES_PARM);
        	will(returnValue(new ArrayList<String>(){{
        		add(PropAwardPersonRoleServiceImpl.NIH_MULTIPLE_PI_HIERARCHY);
        	}}));
        	one(hierarchyService).isSponsorInHierarchy("000500", PropAwardPersonRoleServiceImpl.NIH_MULTIPLE_PI_HIERARCHY);
        	will(returnValue(false));
        	one(dataObjectService).findMatching(PropAwardPersonRole.class, QueryByCriteria.Builder.forAttribute("sponsorHierarchyName", PropAwardPersonRoleServiceImpl.DEFAULT_SPONSOR_HIERARCHY_NAME).build()).getResults();
        	will(returnValue(roleListBuilder.build()));
        }});
        
        roleService.setDataObjectService(dataObjectService);
        roleService.setSponsorHierarchyService(hierarchyService);
        roleService.setParameterService(parameterService);
    	assertEquals(roleService.getRolesByHierarchy("000500").size(), 3);
    }
    
    @Test
    public void test_getRolesByHierarchy_multipleRoles() {
    	final PropAwardPersonRoleServiceImpl roleService = new PropAwardPersonRoleServiceImpl();
    	final DataObjectService dataObjectService = context.mock(DataObjectService.class);
    	final ParameterService parameterService = context.mock(ParameterService.class);
    	final SponsorHierarchyService hierarchyService = context.mock(SponsorHierarchyService.class);

        context.checking(new Expectations() {{
        	one(parameterService).getParameterValueAsBoolean(Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT, Constants.PARAMETER_COMPONENT_DOCUMENT, Constants.ALL_SPONSOR_HIERARCHY_NIH_MULTI_PI);
        	will(returnValue(false));
        	one(parameterService).getParameterValuesAsString(Constants.KC_GENERIC_PARAMETER_NAMESPACE, Constants.KC_ALL_PARAMETER_DETAIL_TYPE_CODE, PropAwardPersonRoleServiceImpl.SPONSOR_HIERARCHIES_PARM);
        	will(returnValue(new ArrayList<String>(){{
        		add(PropAwardPersonRoleServiceImpl.NIH_MULTIPLE_PI_HIERARCHY);
        		add("New Custom Role");
        	}}));
        	one(hierarchyService).isSponsorInHierarchy("000500", PropAwardPersonRoleServiceImpl.NIH_MULTIPLE_PI_HIERARCHY);
        	will(returnValue(false));
        	one(hierarchyService).isSponsorInHierarchy("000500", "New Custom Role");
        	will(returnValue(true));
        	one(dataObjectService).findMatching(PropAwardPersonRole.class, QueryByCriteria.Builder.forAttribute("sponsorHierarchyName", "New Custom Role").build()).getResults();
        	will(returnValue(roleListBuilder.build()));
        }});
        
        roleService.setDataObjectService(dataObjectService);
        roleService.setSponsorHierarchyService(hierarchyService);
        roleService.setParameterService(parameterService);
    	assertEquals(roleService.getRolesByHierarchy("000500").size(), 3);
    }
    
    @Test
    public void test_isNihOtherSignificantContributerEnabledForSponsor_inHierarchy() {
    	final PropAwardPersonRoleServiceImpl roleService = new PropAwardPersonRoleServiceImpl();
    	final DataObjectService dataObjectService = context.mock(DataObjectService.class);
    	final ParameterService parameterService = context.mock(ParameterService.class);
    	final SponsorHierarchyService hierarchyService = context.mock(SponsorHierarchyService.class);

        context.checking(new Expectations() {{
        	one(parameterService).getParameterValueAsBoolean(Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT, Constants.PARAMETER_COMPONENT_DOCUMENT, Constants.ALL_SPONSOR_HIERARCHY_NIH_MULTI_PI);
        	will(returnValue(false));
        	one(hierarchyService).isSponsorNihOsc("000500");
        	will(returnValue(true));
        }});
        
        roleService.setDataObjectService(dataObjectService);
        roleService.setSponsorHierarchyService(hierarchyService);
        roleService.setParameterService(parameterService);
    	assertEquals(roleService.isNihOtherSignificantContributorEnabledForSponsor("000500"), true);
    }
    
    @Test
    public void test_isNihOtherSignificantContributerEnabledForSponsor_allSponsor() {
    	final PropAwardPersonRoleServiceImpl roleService = new PropAwardPersonRoleServiceImpl();
    	final DataObjectService dataObjectService = context.mock(DataObjectService.class);
    	final ParameterService parameterService = context.mock(ParameterService.class);
    	final SponsorHierarchyService hierarchyService = context.mock(SponsorHierarchyService.class);

        context.checking(new Expectations() {{
        	one(parameterService).getParameterValueAsBoolean(Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT, Constants.PARAMETER_COMPONENT_DOCUMENT, Constants.ALL_SPONSOR_HIERARCHY_NIH_MULTI_PI);
        	will(returnValue(true));
        }});
        
        roleService.setDataObjectService(dataObjectService);
        roleService.setSponsorHierarchyService(hierarchyService);
        roleService.setParameterService(parameterService);
    	assertEquals(roleService.isNihOtherSignificantContributorEnabledForSponsor("000500"), true);
    }
}
