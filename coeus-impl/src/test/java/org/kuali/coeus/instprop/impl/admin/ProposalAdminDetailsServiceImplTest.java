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
package org.kuali.coeus.instprop.impl.admin;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.institutionalproposal.proposaladmindetails.ProposalAdminDetails;
import org.kuali.rice.krad.service.BusinessObjectService;

public class ProposalAdminDetailsServiceImplTest {

	private ProposalAdminDetailsServiceImpl detailsServiceImpl;
	private Mockery context;
	private BusinessObjectService businessObjectService;

	@Before
	public void setUp() throws Exception {
		detailsServiceImpl = new ProposalAdminDetailsServiceImpl();
		context = new JUnit4Mockery() {{setThreadingPolicy(new Synchroniser());}};
		businessObjectService = context.mock(BusinessObjectService.class);
		detailsServiceImpl.setBusinessObjectService(businessObjectService);
	}

	@After
	public void tearDown() throws Exception {
		detailsServiceImpl = null;
		context = null;
		businessObjectService = null;
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFindProposalAdminDetailsByPropDevNumberEmptyProposalNumber() {
		List<ProposalAdminDetails> adminDetailsList = null;
		adminDetailsList = (List<ProposalAdminDetails>) detailsServiceImpl.findProposalAdminDetailsByPropDevNumber("");
		Assert.assertNotNull((Object)adminDetailsList);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testFindProposalAdminDetailsByPropDevNumberNullProposalNumber() {
		List<ProposalAdminDetails> adminDetailsList = null;
		adminDetailsList = (List<ProposalAdminDetails>) detailsServiceImpl.findProposalAdminDetailsByPropDevNumber(null);
		Assert.assertNotNull((Object)adminDetailsList);
	}

	@Test
	public void test_findProposalAdminDetailsByPropDevNumber() {
		final Map<String, Object> fieldValues = new HashMap<String, Object>();
		fieldValues.put("devProposalNumber", "1");
		final List<ProposalAdminDetails> details = new ArrayList<ProposalAdminDetails>();
		context.checking(new Expectations() {
			{
				one(businessObjectService).findMatching(ProposalAdminDetails.class, fieldValues);
				will(returnValue(details));
			}
		});
		detailsServiceImpl.setBusinessObjectService(businessObjectService);
		assertEquals(details, detailsServiceImpl.findProposalAdminDetailsByPropDevNumber("1"));
	}
	
	@Test
	public void testFindProposalAdminDetailsByPropDevNumberReturnNotNull() {
		final Map<String, Object> fieldValues = new HashMap<String, Object>();
		fieldValues.put("devProposalNumber", "1");
		final List<ProposalAdminDetails> details = null;
		context.checking(new Expectations() {
			{
				one(businessObjectService).findMatching(ProposalAdminDetails.class, fieldValues);
				will(returnValue(details));
			}
		});
		Assert.assertNotNull(detailsServiceImpl.findProposalAdminDetailsByPropDevNumber("1"));
	}

}
