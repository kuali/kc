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

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.bo.NarrativeAttachment;
import org.kuali.kra.proposaldevelopment.bo.NarrativeType;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.s2s.bo.S2sOpportunity;
import org.kuali.kra.s2s.generator.S2STestBase;
import org.kuali.kra.s2s.generator.util.S2STestUtils;
import org.kuali.rice.krad.service.BusinessObjectService;

/**
 * This class tests the PHS398CareerDevelopmentAwardSupV1_0Generator
 */
public class PHS398CareerDevelopmentAwardSupV1_1GeneratorTest extends
		S2STestBase<PHS398CareerDevelopmentAwardSupV1_1Generator> {

	@Override
	protected Class<PHS398CareerDevelopmentAwardSupV1_1Generator> getFormGeneratorClass() {
		return PHS398CareerDevelopmentAwardSupV1_1Generator.class;
	}

	@Override
	protected void prepareData(ProposalDevelopmentDocument document)
			throws Exception {
		Narrative narrative = new Narrative();
		List<Narrative> naList = new ArrayList<Narrative>();
		NarrativeAttachment narrativeAttachment = new NarrativeAttachment();
		File file = new File(S2STestUtils.ATT_DIR_PATH + "exercise7.pdf");
		InputStream inStream = new FileInputStream(file);
		BufferedInputStream bis = new BufferedInputStream(inStream);
		byte[] narrativePdf = new byte[bis.available()];
		narrativeAttachment.setNarrativeData(narrativePdf);
		List<NarrativeAttachment> narrativeList = new ArrayList<NarrativeAttachment>();
		narrativeList.add(narrativeAttachment);
		narrative.setProposalNumber(document.getDevelopmentProposal()
				.getProposalNumber());
		narrative.setModuleNumber(1);
		narrative.setModuleSequenceNumber(1);
		narrative.setModuleStatusCode("C");
		narrative.setNarrativeTypeCode("70");
		narrative.setNarrativeAttachmentList(narrativeList);
		narrative.setObjectId("12345678890abcd");
		narrative.setFileName("exercise7");
		NarrativeType narrativeType = new NarrativeType();
		narrativeType.setDescription("Testing for NSFCoverPage Attachment");
		narrative.setNarrativeType(narrativeType);
		naList.add(narrative);
		getService(BusinessObjectService.class).save(narrative);
		narrative.getNarrativeAttachmentList().clear();
		document.getDevelopmentProposal().setNarratives(naList);

		S2sOpportunity s2sOpportunity = new S2sOpportunity();
		s2sOpportunity
				.setOpportunityTitle("OpportunityTitle for faith based survey");
		s2sOpportunity.setCfdaNumber("00.000");
		s2sOpportunity.setProposalNumber(document.getDevelopmentProposal()
				.getProposalNumber());
		s2sOpportunity.setClosingDate(new Timestamp(new Date().getTime()));
		document.getDevelopmentProposal().setS2sOpportunity(s2sOpportunity);
	}
}
