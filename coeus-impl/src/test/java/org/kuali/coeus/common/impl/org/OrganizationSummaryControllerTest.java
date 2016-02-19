package org.kuali.coeus.common.impl.org;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.framework.org.Organization;
import org.kuali.coeus.common.framework.org.OrganizationSummaryDto;
import org.kuali.coeus.common.framework.rolodex.Rolodex;
import org.kuali.coeus.common.framework.sponsor.Sponsor;

public class OrganizationSummaryControllerTest {

	private OrganizationController organizationSummaryController;
	private Organization organization1;
	private Organization organization2;
	private Rolodex rolodex1;
	private Sponsor sponsor1;
	private List<Organization> organizationList;
	
	@Before
	public void setup() {
		sponsor1 = new Sponsor();
		sponsor1.setSponsorCode("000001");
		sponsor1.setAcronym("TSC");
		sponsor1.setSponsorName("TestSponsorCode");
		
		rolodex1 = new Rolodex();
		rolodex1.setRolodexId(1);
		rolodex1.setAddressLine1("Test Address");
		rolodex1.setAddressLine2("Test Address2");
		rolodex1.setAddressLine3("Test Address 3");
		rolodex1.setCity("Nowhere");
		rolodex1.setCountryCode("USA");
		rolodex1.setCounty("Timbuktoo");
		rolodex1.setEmailAddress("test@nowhere.com");
		rolodex1.setFaxNumber("555-555-5555");
		rolodex1.setFirstName("Noone");
		rolodex1.setLastName("Noone For Sure");
		rolodex1.setMiddleName("Noone Still");
		rolodex1.setPhoneNumber("555-555-5555");
		rolodex1.setPostalCode("55555");
		rolodex1.setPrefix("Sr");
		rolodex1.setSponsor(sponsor1);
		rolodex1.setState("NA");
		rolodex1.setSuffix("Sr");
		rolodex1.setTitle("Sir");
		rolodex1.setActive(true);

		organization1 = new Organization();
		organization1.setOrganizationId("1");
		organization1.setOrganizationName("Test Org 1");
		organization1.setAddress("123 Nowhere St Nowhere NA 55555");
		organization1.setRolodex(rolodex1);
		
		organization2 = new Organization();
		organization2.setOrganizationId("2");
		organization2.setOrganizationName("Test Org 2");
		organization2.setAddress("456 Nowhere St Nowhere NA 55555");
		organization2.setRolodex(rolodex1);
		
		organizationList = new ArrayList<>();
		organizationList.add(organization1);
		organizationList.add(organization2);
	}
	
	@Test
	public void testOrganizationSummary() {
		organizationSummaryController = new OrganizationController() {
			@Override
			protected Collection<Organization> getAllFromDataStore() {
				return organizationList;
			}

			@Override
			protected void assertUserHasReadAccess() {
				//no op
			}
		};
		
		OrganizationResults result = organizationSummaryController.getOrganizationSummary();
		assertEquals(2, result.getTotalFound().intValue());
		assertEquals(2, result.getCount().intValue());
		assertEquals(2, result.getOrganizations().size());
		assertEquals(organization1.getOrganizationId(), ((List<OrganizationSummaryDto>)result.getOrganizations()).get(0).getOrganizationId());
	}	
}
