/*
 * Copyright 2005-2010 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.s2s.generator.impl;

import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.irb.personnel.ProtocolPerson;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.bo.NarrativeType;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.s2s.generator.S2STestBase;
import org.kuali.rice.krad.service.BusinessObjectService;

/**
 * This class tests the NSFCoverPageV1_2 Generator
 */
public class PHS398FellowshipSupplementalV1_1GeneratorTest extends
		S2STestBase<PHS398FellowshipSupplementalV1_1Generator> {
	protected static final String PRINCIPAL_INVESTIGATOR_ID = "10000000001";
	protected static final String PRINCIPAL_INVESTIGATOR_NAME = "Joe Tester";
	protected static final String PRINCIPAL_INVESTIGATOR_ROLE = "PI";
	protected static final String REFERENCE_PERSON_ROLE = "protocolPersonRole";

	@Override
	protected Class<PHS398FellowshipSupplementalV1_1Generator> getFormGeneratorClass() {
		return PHS398FellowshipSupplementalV1_1Generator.class;
	}

	@Override
	protected void prepareData(ProposalDevelopmentDocument document)
			throws Exception {
		document.getDevelopmentProposal()
				.setNarratives(getNarratives(document));
		//TODO How to set Principal Investigator,getPrincipalInvestigator();
	}

	private List<Narrative> getNarratives(ProposalDevelopmentDocument pdDoc) {
		Narrative narrative = new Narrative();
		List<Narrative> naList = new ArrayList<Narrative>();
		narrative.setProposalNumber(pdDoc.getDevelopmentProposal()
				.getProposalNumber());
		narrative.setModuleNumber(1);
		narrative.setModuleSequenceNumber(1);
		narrative.setModuleStatusCode("C");
		narrative.setNarrativeTypeCode("52");
		narrative.setObjectId("12345678890abcd");
		narrative.setFileName("exercise1");
		NarrativeType narrativeType = new NarrativeType();
		narrativeType.setDescription("Testing for Attachments Attachment");
		narrative.setNarrativeType(narrativeType);
		naList.add(narrative);
		getService(BusinessObjectService.class).save(narrative);
		narrative.getNarrativeAttachmentList().clear();
		return naList;
	}

	protected ProtocolPerson getPrincipalInvestigator() {
		return getProtocolPerson(PRINCIPAL_INVESTIGATOR_ID,
				PRINCIPAL_INVESTIGATOR_NAME, PRINCIPAL_INVESTIGATOR_ROLE);
	}

	protected ProtocolPerson getProtocolPerson(String personId,
			String personName, String personRole) {
		ProtocolPerson protocolPerson = new ProtocolPerson();
		protocolPerson.setPersonId(personId);
		protocolPerson.setPersonName(personName);
		protocolPerson.setProtocolPersonRoleId(personRole);
		protocolPerson.setPreviousPersonRoleId(personRole);
		protocolPerson.setProtocolNumber("0");
		protocolPerson.setSequenceNumber(0);
		protocolPerson.refreshReferenceObject(REFERENCE_PERSON_ROLE);
		return protocolPerson;
	}
}
