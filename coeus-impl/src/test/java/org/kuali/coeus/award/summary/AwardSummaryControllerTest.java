package org.kuali.coeus.award.summary;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.sponsor.Sponsor;
import org.kuali.coeus.common.framework.type.ActivityType;
import org.kuali.coeus.sys.framework.summary.SearchResults;
import org.kuali.kra.award.contacts.AwardPerson;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardStatus;
import org.kuali.kra.award.home.fundingproposal.AwardFundingProposal;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposalBoLite;

public class AwardSummaryControllerTest {
	
	private static final String PERSON_ID123 = "personId123";
	private AwardSummaryController awardSummaryController;
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
		proposal1.setProposalNumber("123");
		proposal1.setSequenceNumber(1);
		
		proposal2 = new InstitutionalProposalBoLite();
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
		
		award1 = new Award() {
			public void refreshReferenceObject(String prop) {
				
			}
		};
		award1.setAwardId(1L);
		award1.setAwardNumber("000001-00001");
		award1.setSequenceNumber(1);
		award1.setAccountNumber("123");
		award1.setModificationNumber("TestMod");
		award1.setSponsorAwardNumber("00001234");
		award1.setCfdaNumber("1234");
		award1.setTitle("Testing");
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
		
		award2 = new Award() {
			public void refreshReferenceObject(String prop) {
				
			}
		};
		award2.setAwardId(1L);
		award2.setAwardNumber("000001-00001");
		award2.setSequenceNumber(1);
		award2.setAccountNumber("123");
		award2.setModificationNumber("TestMod");
		award2.setSponsorAwardNumber("00001234");
		award2.setCfdaNumber("1234");
		award2.setTitle("Testing");
		award2.setAwardStatus(awardStatus);
		award2.setActivityType(activityType);
		award2.setSponsor(sponsor);
		award2.getInvestigators().add(pi);
		fp1 = new AwardFundingProposal();
		fp1.setProposal(proposal1);
		fp1.setAward(award2);
		award2.getFundingProposals().add(fp2);
		
		awards = new ArrayList<>();
		awards.add(award1);
		awards.add(award2);
		
	}
	
	@Test
	public void testOrganizationSummary() {
		awardSummaryController = new AwardSummaryController() {
			@Override
			SearchResults<Award> getAwards(Date updatedSince, Integer page, Integer numberPerPage) {
				SearchResults<Award> result = new SearchResults<>();
				result.setResults(awards);
				result.setTotalResults(2);
				return result;
			}
		};
		
		AwardResults result = awardSummaryController.getAwardSummary(null, null, null);
		assertEquals(2, result.getTotalFound().intValue());
		assertEquals(2, result.getCount().intValue());
		assertEquals(2, result.getAwards().size());
		assertEquals(award1.getAwardId(), ((List<AwardSummaryDto>)result.getAwards()).get(0).getAwardId());
	}	
}
