/*
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
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

import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.bo.NarrativeAttachment;
import org.kuali.kra.proposaldevelopment.bo.NarrativeType;
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
public class PHS398ResTrainProgPlanV1_0GeneratorTest extends S2STestBase<PHS398ResTrainProgPlanV1_0Generator>{

    @Override
    protected Class<PHS398ResTrainProgPlanV1_0Generator> getFormGeneratorClass() {
        return PHS398ResTrainProgPlanV1_0Generator.class;
    }

    @Override
    protected void prepareData(ProposalDevelopmentDocument document) throws Exception {
        document.getDevelopmentProposal().setProposalTypeCode("5");
        Narrative narrative = new Narrative();
        List<Narrative> naList = new ArrayList<Narrative>();
        NarrativeAttachment narrativeAttachment = new NarrativeAttachment();
        DefaultResourceLoader resourceLoader = new DefaultResourceLoader(ClassLoaderUtils.getDefaultClassLoader());
        Resource resource = resourceLoader.getResource(S2STestConstants.ATT_PACKAGE + "/exercise1.pdf");
        InputStream inStream = resource.getInputStream();
        BufferedInputStream bis = new BufferedInputStream(inStream);
        byte[] narrativePdf = new byte[bis.available()];
        narrativeAttachment.setNarrativeData(narrativePdf);
        List<NarrativeAttachment> narrativeList = new ArrayList<NarrativeAttachment>();
        narrativeList.add(narrativeAttachment);
        narrative.setProposalNumber(document.getDevelopmentProposal().getProposalNumber());
        narrative.setModuleNumber(1);
        narrative.setModuleSequenceNumber(1);
        narrative.setModuleStatusCode("C");
        narrative.setNarrativeAttachmentList(narrativeList);
        narrative.setObjectId("12345678890abcd");
        narrative.setFileName("exercise1");
        NarrativeType narrativeType = new NarrativeType();
        narrativeType.setNarrativeTypeCode("1");
        narrativeType.setAllowMultiple("Y");
        narrativeType.setSystemGenerated("N");
        narrativeType.setDescription("Testing for Project Attachment");
        getService(DataObjectService.class).save(narrativeType);
        narrative.setNarrativeType(narrativeType);
        narrative.setNarrativeTypeCode("1");
        naList.add(narrative);
        narrative.getNarrativeAttachmentList().clear();
        document.getDevelopmentProposal().setNarratives(naList);
        
    }

}
