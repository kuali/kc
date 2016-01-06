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
package org.kuali.coeus.award.finance.impl;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.award.finance.AccountInformationDto;
import org.kuali.coeus.common.framework.custom.attr.CustomAttribute;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.sponsor.Sponsor;
import org.kuali.coeus.common.framework.type.ActivityType;
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.kra.award.contacts.AwardPerson;
import org.kuali.kra.award.contacts.AwardUnitContact;
import org.kuali.kra.award.customdata.AwardCustomData;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardAmountInfo;
import org.kuali.kra.award.home.AwardBasisOfPayment;
import org.kuali.kra.award.home.AwardMethodOfPayment;
import org.kuali.kra.award.home.AwardStatus;
import org.kuali.kra.award.home.fundingproposal.AwardFundingProposal;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposalBoLite;
import org.kuali.rice.kew.api.exception.WorkflowException;

public class AccountServiceTest {

	private static final String PERSON_ID123 = "personId123";
	private Award award1;
	private AwardStatus awardStatus;
	private ActivityType activityType;
	private InstitutionalProposalBoLite proposal1;
	private InstitutionalProposalBoLite proposal2;
	private AwardPerson pi;
	private KcPerson piPerson;
	private Sponsor sponsor;
	private AwardBasisOfPayment awardBasisOfPayment;
	private AwardMethodOfPayment awardMethodOfPayment;
	private CustomAttribute customAttribute;
	private AwardCustomData awardCustomData;
	
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
		
		awardBasisOfPayment = new AwardBasisOfPayment();
		awardBasisOfPayment.setDescription("Payment");
		
		awardMethodOfPayment = new AwardMethodOfPayment();
		awardMethodOfPayment.setDescription("Method");
		
		customAttribute = new CustomAttribute();
		customAttribute.setId(1L);
		customAttribute.setName("Test Attr");
		
		awardCustomData = new AwardCustomData();
		awardCustomData.setCustomAttribute(customAttribute);
		awardCustomData.setValue("Test Val");
		
		activityType = new ActivityType();
		activityType.setCode("1");
		activityType.setDescription("Test");
		
		proposal1 = new InstitutionalProposalBoLite();
		proposal1.setProposalNumber(accountNumber);
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
			public Unit getUnit() {
				return null;
			}
			public String getAddressLine1() {
				return "123 FooBar";
			}
			public String getAddressLine2() {
				return null;
			}
			public String getAddressLine3() {
				return null;
			}
			public String getCity() {
				return "Foobar";
			}
			public String getState() {
				return "FB";
			}
			public String getPostalCode() {
				return "11111";
			}
		};
		piPerson.setPersonId(PERSON_ID123);
		pi = new AwardPerson() {
			public String getProjectRole() {
				return "Principal Investigator";
			}
			public String getEmailAddress() {
				return "notanemail";
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
		
	}
	
	Award buildTestAward(final long awardId, final String awardNumber, final String accountNumber, final String modificationNumber, final String sponsorAwardNumber, final String cfdaNumber,
			final String title) {
		Award award1;
		award1 = new Award() {
			public void refreshReferenceObject(String prop) {
				
			}
			public int getIndexOfAwardAmountInfoForDisplay() throws WorkflowException {
				return 0;
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
		award1.getProjectPersons().add(pi);
		AwardFundingProposal fp1 = new AwardFundingProposal();
		fp1.setProposal(proposal1);
		fp1.setAward(award1);
		award1.getFundingProposals().add(fp1);
		AwardFundingProposal fp2 = new AwardFundingProposal();
		fp2.setProposal(proposal2);
		fp2.setAward(award1);
		award1.getFundingProposals().add(fp2);
		award1.setUpdateTimestamp(new Timestamp(new Date().getDate()));
		final AwardUnitContact awardUnitContact = new AwardUnitContact();
		awardUnitContact.setPerson(piPerson);
		award1.setCentralAdminContacts(Arrays.asList(awardUnitContact));
		award1.setAwardBasisOfPayment(awardBasisOfPayment);
		award1.setAwardMethodOfPayment(awardMethodOfPayment);
		award1.setAccountTypeCode(1);
		award1.getAwardCustomDataList().add(awardCustomData);
		award1.getAwardAmountInfos().add(new AwardAmountInfo());
		return award1;
	}
	
	@Test
	public void testCreateAccountInformation() {
		AccountServiceImpl service = new AccountServiceImpl();
		
		AccountInformationDto dto = service.createAccountInformation(award1);
		assertNotNull(dto);
		assertEquals(award1.getAwardAmountInfos().size(), dto.getTransactions().size());
		assertEquals(award1.getAwardNumber(), dto.getAwardNumber());
		assertEquals(award1.getAwardCustomDataList().size(), dto.getCustomData().size());
		
	}
}
