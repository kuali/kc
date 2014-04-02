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
/**
 * This class tests the EDAbstractV1_1Generator
 */
public class EDAbstractV1_1GeneratorTest extends S2STestBase<EDAbstractV1_1Generator> {

    @Override
    protected Class<EDAbstractV1_1Generator> getFormGeneratorClass() {
        return EDAbstractV1_1Generator.class;
    }

    @Override
    protected void prepareData(ProposalDevelopmentDocument document) throws Exception {

        Narrative narrative = new Narrative();
        List<Narrative> naList = new ArrayList<Narrative>();
        NarrativeAttachment narrativeAttachment = new NarrativeAttachment();
        DefaultResourceLoader resourceLoader = new DefaultResourceLoader(ClassLoaderUtils.getDefaultClassLoader());
        Resource resource = resourceLoader.getResource(S2STestConstants.ATT_PACKAGE + "/exercise2.pdf");
        InputStream inStream = resource.getInputStream();
        BufferedInputStream bis = new BufferedInputStream(inStream);
        byte[] narrativePdf = new byte[bis.available()];
        narrativeAttachment.setNarrativeData(narrativePdf);
        narrativeAttachment.setProposalNumber(document.getDevelopmentProposal().getProposalNumber());
        narrativeAttachment.setModuleNumber(1);
        narrativeAttachment.setFileName("exercise2");
        saveBO(narrativeAttachment);

        List<NarrativeAttachment> narrativeList = new ArrayList<NarrativeAttachment>();
        narrativeList.add(narrativeAttachment);
        narrative.setProposalNumber(document.getDevelopmentProposal().getProposalNumber());
        narrative.setModuleNumber(1);
        narrative.setModuleSequenceNumber(1);
        narrative.setModuleStatusCode("C");
        narrative.setNarrativeTypeCode("52");
        narrative.setNarrativeAttachmentList(narrativeList);
        narrative.setObjectId("12345678890abcd");
        narrative.setFileName("exercise2");

        NarrativeType narrativeType = new NarrativeType();
        narrativeType.setNarrativeTypeCode("52");
        narrativeType.setAllowMultiple("Y");
        narrativeType.setSystemGenerated("N");
        narrativeType.setDescription("Testing for EDAbstract Attachment");
        getService(DataObjectService.class).save(narrativeType);
        narrative.setNarrativeType(narrativeType);
        narrative.setNarrativeTypeCode("52");
        naList.add(narrative);

        document.getDevelopmentProposal().setNarratives(naList);
        saveBO(document.getDevelopmentProposal());
    }
}
