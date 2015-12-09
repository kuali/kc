/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.coeus.s2sgen.impl.generate.support;
import static org.kuali.coeus.sys.framework.service.KcServiceLocator.getService;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.kuali.coeus.common.framework.person.attr.CitizenshipType;
import org.kuali.coeus.propdev.impl.attachment.Narrative;
import org.kuali.coeus.propdev.impl.attachment.NarrativeAttachment;
import org.kuali.coeus.propdev.impl.attachment.NarrativeType;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;
import org.kuali.rice.core.api.util.ClassLoaderUtils;
import org.kuali.rice.krad.data.DataObjectService;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

public class PHS398CareerDevelopmentAwardSupV2_0GeneratorTest extends
		S2SModularBudgetTestBase<PHS398CareerDevelopmentAwardSupV2_0Generator> {

	@Override
	protected String getFormGeneratorName() {
		return PHS398CareerDevelopmentAwardSupV2_0Generator.class.getSimpleName();
	}

	@Override
	protected void prepareData(ProposalDevelopmentDocument document)
			throws Exception {
		
		NarrativeAttachment narrativeAttachment = new NarrativeAttachment();
		DefaultResourceLoader resourceLoader = new DefaultResourceLoader(
				ClassLoaderUtils.getDefaultClassLoader());
		Resource resource = resourceLoader
				.getResource(S2STestConstants.ATT_PACKAGE + "/exercise2.pdf");
		InputStream inputStream = resource.getInputStream();
		BufferedInputStream bis = new BufferedInputStream(inputStream);
		byte[] narrativePdf = new byte[bis.available()];
		narrativeAttachment.setData(narrativePdf);
		narrativeAttachment.setName("exercise1");
		Narrative narrative = new Narrative();
		List<Narrative> narrativeList = new ArrayList<Narrative>();
		narrative.setDevelopmentProposal(document.getDevelopmentProposal());
		NarrativeType narrativeType = new NarrativeType();
		narrativeType.setCode("128");
		narrativeType.setAllowMultiple(false);
		narrativeType.setSystemGenerated(false);
		narrativeType.setDescription("Test for Attachment");
		getService(DataObjectService.class).save(narrativeType);
        narrative.setName("exercise1");
        narrative.setNarrativeType(narrativeType);
		narrative.setNarrativeTypeCode("128");
		narrative.setNarrativeAttachment(narrativeAttachment);
        narrative.setModuleNumber(1);
        narrative.setModuleSequenceNumber(1);
        narrative.setModuleStatusCode("C");
		narrativeList.add(narrative);
		document.getDevelopmentProposal().setNarratives(narrativeList);		
		
		List<ProposalPerson> proposalPersons = new ArrayList<ProposalPerson>();
		ProposalPerson principalInvestigator = new ProposalPerson();
		principalInvestigator.setFirstName("ALAN");
		principalInvestigator.setLastName("MCAFEE");
		principalInvestigator.setProposalPersonRoleId("PI");
		principalInvestigator.setPersonId("0001");
        principalInvestigator.setProposalPersonNumber(1);
        principalInvestigator.setDevelopmentProposal(document.getDevelopmentProposal());
		CitizenshipType citizenshipType = new CitizenshipType();
		
		citizenshipType.setCode(1);
		principalInvestigator.setCitizenshipType(citizenshipType);
		proposalPersons.add(principalInvestigator);
		document.getDevelopmentProposal().setProposalPersons(proposalPersons);
		
		
	}
}
