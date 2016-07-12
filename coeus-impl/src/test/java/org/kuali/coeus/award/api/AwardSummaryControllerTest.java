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
package org.kuali.coeus.award.api;

import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.sponsor.Sponsor;
import org.kuali.coeus.common.framework.type.ActivityType;
import org.kuali.coeus.sys.framework.rest.SearchResults;
import org.kuali.kra.award.contacts.AwardPerson;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardStatus;
import org.kuali.kra.award.home.fundingproposal.AwardFundingProposal;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposalBoLite;

public class AwardSummaryControllerTest {
	
	private static final String PERSON_ID123 = "personId123";
	private AwardControllerVersion1 awardController;
	private Award award1;
	private Award award2;
	private AwardStatus awardStatus;
	private ActivityType activityType;
	private InstitutionalProposalBoLite proposal1;
	private InstitutionalProposalBoLite proposal2;
	private AwardPerson pi;
	private KcPerson piPerson;
	private Sponsor sponsor;
	private List<Award> awards;
	
	@Before
	public void setup() {
		final String accountNumber = "123";
		
		sponsor = new Sponsor();
		sponsor.setSponsorCode("000001");
		sponsor.setAcronym("TSC");
		sponsor.setSponsorName("TestSponsorCode");
		
		awardStatus = new AwardStatus();
		awardStatus.setStatusCode("1");
		awardStatus.setDescription("Test Status");
		
		activityType = new ActivityType();
		activityType.setCode("1");
		activityType.setDescription("Test");
		
		proposal1 = new InstitutionalProposalBoLite();
		proposal1.setProposalId(1L);
		proposal1.setProposalNumber(accountNumber);
		proposal1.setSequenceNumber(1);
		
		proposal2 = new InstitutionalProposalBoLite();
		proposal2.setProposalId(2L);
		proposal2.setProposalNumber("345");
		proposal2.setSequenceNumber(2);
		
		piPerson = new KcPerson() {
			String personId;
			public String getPersonId() {
				return personId;
			}
			public void setPersonId(String personId) {
				this.personId = personId;
			}
		};
		piPerson.setPersonId(PERSON_ID123);
		pi = new AwardPerson() {
			public String getProjectRole() {
				return "Principal Investigator";
			}
		};
		pi.setPerson(piPerson);
		pi.setFullName("Foobar");
		pi.setEmailAddress("test@nowhere.com");
		pi.setRoleCode("PI");
		
		final String modificationNumber = "TestMod";
		final String sponsorAwardNumber = "00001234";
		final String cfdaNumber = "1234";
		final String title = "Testing";
		award1 = buildTestAward(1L, "000001-00001", accountNumber, modificationNumber, 
				sponsorAwardNumber, cfdaNumber, title);
		award2 = buildTestAward(2L, "000001-00002", accountNumber, modificationNumber, 
				sponsorAwardNumber, cfdaNumber, title);
		
		awards = new ArrayList<>();
		awards.add(award1);
		awards.add(award2);
		
	}
	
	@Test
	public void testOrganizationSummary() {
		awardController = new AwardControllerVersion1() {
			@Override
			SearchResults<Award> getAwards(Date updatedSince, Integer page, Integer numberPerPage) {
				SearchResults<Award> result = new SearchResults<>();
				result.setResults(awards);
				result.setTotalResults(2);
				return result;
			}

			@Override
			protected void assertUserHasReadAccess() {
				//no op
			}
		};
		
		AwardResults result = awardController.getAwardSummary(null, null, null);
		assertEquals(2, result.getTotalFound().intValue());
		assertEquals(2, result.getCount().intValue());
		assertEquals(2, result.getAwards().size());
		assertEquals(award1.getAwardId(), ((List<AwardSummaryDto>)result.getAwards()).get(0).getAwardId());
	}
	
	Award buildTestAward(final long awardId, final String awardNumber, final String accountNumber, final String modificationNumber, final String sponsorAwardNumber, final String cfdaNumber,
			final String title) {
		Award award1;
		award1 = new Award() {
			public void refreshReferenceObject(String prop) {
				
			}
		};
		award1.setAwardId(awardId);
		award1.setAwardNumber(awardNumber);
		award1.setSequenceNumber(1);
		award1.setAccountNumber(accountNumber);
		award1.setModificationNumber(modificationNumber);
		award1.setSponsorAwardNumber(sponsorAwardNumber);
		award1.setCfdaNumber(cfdaNumber);
		award1.setTitle(title);
		award1.setAwardStatus(awardStatus);
		award1.setActivityType(activityType);
		award1.setSponsor(sponsor);
		award1.getInvestigators().add(pi);
		AwardFundingProposal fp1 = new AwardFundingProposal();
		fp1.setProposal(proposal1);
		fp1.setAward(award1);
		award1.getFundingProposals().add(fp1);
		AwardFundingProposal fp2 = new AwardFundingProposal();
		fp2.setProposal(proposal2);
		fp2.setAward(award1);
		award1.getFundingProposals().add(fp2);
		award1.setUpdateTimestamp(new Timestamp(new Date().getDate()));
		return award1;
	}
}
