/*
 * Copyright 2005-2014 The Kuali Foundation.
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

import org.kuali.coeus.common.framework.type.ProposalType;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.attachment.Narrative;
import org.kuali.coeus.propdev.impl.attachment.NarrativeAttachment;
import org.kuali.coeus.propdev.impl.attachment.NarrativeType;
import org.kuali.coeus.s2sgen.impl.generate.support.PHS398ResearchPlanV1_1Generator;
import org.kuali.kra.s2s.generator.S2STestBase;
import org.kuali.kra.s2s.generator.util.S2STestConstants;
import org.kuali.rice.core.api.util.ClassLoaderUtils;
import org.kuali.rice.krad.data.DataObjectService;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.kuali.coeus.sys.framework.service.KcServiceLocator.getService;
public class PHS398ResearchPlanV1_2GeneratorTest extends
		S2STestBase<PHS398ResearchPlanV1_1Generator> {

	@Override
	protected String getFormGeneratorName() {
		return PHS398ResearchPlanV1_1Generator.class.getSimpleName();
	}

	@Override
	protected void prepareData(ProposalDevelopmentDocument document)
			throws Exception {
		DevelopmentProposal developmentProposal = document
				.getDevelopmentProposal();
        ProposalType type = new ProposalType();
        type.setCode("5");
        developmentProposal.setProposalType(type);
		Narrative narrative = new Narrative();
		List<Narrative> naList = new ArrayList<Narrative>();
		NarrativeAttachment narrativeAttachment = new NarrativeAttachment();

        DefaultResourceLoader resourceLoader = new DefaultResourceLoader(ClassLoaderUtils.getDefaultClassLoader());
        Resource resource = resourceLoader.getResource(S2STestConstants.ATT_PACKAGE + "/exercise1.pdf");
		InputStream inStream = resource.getInputStream();
		BufferedInputStream bis = new BufferedInputStream(inStream);
		byte[] narrativePdf = new byte[bis.available()];
		narrativeAttachment.setData(narrativePdf);

		narrative.setDevelopmentProposal(document.getDevelopmentProposal());
		narrative.setModuleNumber(1);
		narrative.setModuleSequenceNumber(1);
		narrative.setModuleStatusCode("C");
		narrative.setNarrativeTypeCode("20");
		narrative.setNarrativeAttachment(narrativeAttachment);
		narrative.setObjectId("12345678890abcd");
		narrative.setName("exercise1");
		NarrativeType narrativeType = new NarrativeType();
        narrativeType.setCode("1");
        narrativeType.setAllowMultiple(false);
        narrativeType.setSystemGenerated(false);
		narrativeType.setDescription("Testing for Project Attachment");
        getService(DataObjectService.class).save(narrativeType);
		narrative.setNarrativeType(narrativeType);
        narrative.setNarrativeTypeCode("1");
		naList.add(narrative);
		narrative.setNarrativeAttachment(null);
		developmentProposal.setNarratives(naList);
		document.setDevelopmentProposal(developmentProposal);
	}
}
