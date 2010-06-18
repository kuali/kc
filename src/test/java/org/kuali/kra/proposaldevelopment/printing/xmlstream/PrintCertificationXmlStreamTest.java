/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.proposaldevelopment.printing.xmlstream;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.kuali.kra.bo.Organization;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.bo.Rolodex;
import org.kuali.kra.bo.Sponsor;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.document.ResearchDocumentBase;
import org.kuali.kra.printing.util.PrintingTestUtils;
import org.kuali.kra.printing.util.XmlStreamTestBase;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonUnit;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonYnq;
import org.kuali.kra.proposaldevelopment.bo.ProposalSite;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;

/**
 * This class tests generation and validation of XML for Print Certification
 * Report
 * 
 */

public class PrintCertificationXmlStreamTest extends
		XmlStreamTestBase<PrintCertificationXmlStream> {

	@Override
	protected Map<String, Object> getReportParameters() {
		return PrintingTestUtils.getPrintCertificationParameters();
	}

	@Override
	protected Class<PrintCertificationXmlStream> getXmlStream() {
		return PrintCertificationXmlStream.class;
	}

	@Override
	protected ResearchDocumentBase prepareData() {
		return getPrintCertification();
	}
	
	public static ResearchDocumentBase getPrintCertification(){
		ProposalDevelopmentDocument pdDoc = (ProposalDevelopmentDocument) PrintingTestUtils.getProposalDevelopmentDocument();
		
		//DevelopmentProposal
		DevelopmentProposal devProposal = new DevelopmentProposal();
		devProposal.setProposalNumber("1001");
		devProposal.setTitle("Test service title");
		//Person
		//FIXME: Kim migration
		KcPerson person = new KcPerson();
		person.setPersonId("993764481");
		//person.setFullName("Irvine, Darrell J");
		//person.setHomeUnit("066000");
		//Sponsor
		Sponsor sponsor = new Sponsor();
		sponsor.setSponsorCode("005410");
		sponsor.setSponsorName("Amersham Pharmacia Biotech AB");
		sponsor.setSponsorTypeCode("13");
		devProposal.setSponsor(sponsor);
		//Rolodex
		Rolodex rolodex = new Rolodex();
		rolodex.setRolodexId(1);
		rolodex.setOrganization("Harvard University");
		rolodex.setAddressLine1("Harvard School of Public Health");
		rolodex.setAddressLine2("Kresge Building, Room 1001");
		rolodex.setAddressLine3("677 Huntington Avenue");
		rolodex.setCity("Boston");
		rolodex.setState("MA");
		rolodex.setPostalCode("02115");
		rolodex.setCounty("USA");
		//Organization
		Organization organization = new Organization();
		organization.setOrganizationId("000040");
		organization.setOrganizationName("Harvard University");
		devProposal.setPerformingOrgFromOrganization(organization);
		//ProposalSite
		List<ProposalSite> proposalSites = new ArrayList<ProposalSite>();
		ProposalSite proposalSite = new ProposalSite();
		proposalSite.setProposalNumber("1001");
		proposalSite.setLocationName("Performance site");
		proposalSite.setOrganization(organization);
		proposalSite.setLocationTypeCode(2);
		proposalSite.setUpdateTimestamp(new java.sql.Timestamp(Calendar.getInstance()
				.getTimeInMillis()));
		proposalSite.setUpdateUser("quickst");
		proposalSite.setRolodex(rolodex);
		proposalSites.add(proposalSite);
		devProposal.setProposalSites(proposalSites);
		//ProposalPerson
		List<ProposalPerson> proposalPersonList = new ArrayList<ProposalPerson>();
		ProposalPerson proposalPerson = new ProposalPerson();
		proposalPerson.setPersonId("900015025");
		proposalPerson.setProposalPersonRoleId("PI");
		//ProposalPersonYnq
		List<ProposalPersonYnq> proposalPersonYnqList = new ArrayList<ProposalPersonYnq>();
		ProposalPersonYnq proposalPersonYnq = new ProposalPersonYnq();
		proposalPersonYnq.setProposalNumber("00001971");
		proposalPersonYnq.setProposalPersonNumber(900053951);
		proposalPersonYnq.setQuestionId("Z1");
		proposalPersonYnq.setAnswer("N");
		proposalPersonYnqList.add(proposalPersonYnq);
		ProposalPersonYnq proposalPersonYnq1 = new ProposalPersonYnq();
		proposalPersonYnq.setProposalNumber("00001971");
		proposalPersonYnq.setProposalPersonNumber(900053951);
		proposalPersonYnq1.setQuestionId("P1");
		proposalPersonYnq1.setAnswer("Y");
		proposalPersonYnqList.add(proposalPersonYnq);
		proposalPerson.setProposalPersonYnqs(proposalPersonYnqList);
		//Unit
		Unit unit = new Unit();
		unit.setUnitNumber("1");
		unit.setUnitName("School of Engineering");		
		//ProposalPersonUnit
		List<ProposalPersonUnit> units = new ArrayList<ProposalPersonUnit>();
		ProposalPersonUnit pUnit = new ProposalPersonUnit();
		pUnit.setProposalNumber("00001971");
		pUnit.setProposalPersonNumber(900053951);
		pUnit.setLeadUnit(true);
		pUnit.setUnitNumber("1");
		pUnit.setUnit(unit);
		units.add(pUnit);
		proposalPerson.setUnits(units);
		
		proposalPersonList.add(proposalPerson);		
		devProposal.setProposalPersons(proposalPersonList);
		devProposal.setSponsorCode("005410");
		devProposal.setPrimeSponsorCode("000400");
		pdDoc.setDevelopmentProposal(devProposal);
		return pdDoc;
	}
}
