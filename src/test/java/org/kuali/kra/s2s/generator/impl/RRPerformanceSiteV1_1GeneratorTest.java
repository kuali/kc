/*
 * Copyright 2008 The Kuali Foundation.
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
import java.util.ArrayList;
import java.util.List;

import org.kuali.rice.kns.service.BusinessObjectService; 
import org.kuali.kra.bo.Organization;
import org.kuali.kra.bo.Rolodex;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.bo.NarrativeAttachment;
import org.kuali.kra.proposaldevelopment.bo.NarrativeType;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.s2s.generator.S2STestBase;
import org.kuali.kra.s2s.generator.util.S2STestUtils;

/**
 * This class tests the RRPerformanceSite
 */
public class RRPerformanceSiteV1_1GeneratorTest extends S2STestBase<RRPerformanceSiteV1_1Generator> {

    @Override
    protected Class<RRPerformanceSiteV1_1Generator> getFormGeneratorClass() {
        return RRPerformanceSiteV1_1Generator.class;
    }

    @Override
    protected void prepareData(ProposalDevelopmentDocument document) throws Exception {

        Organization performOrganization = new Organization();
        performOrganization.setOrganizationName("Espace");
        Rolodex rolodex = new Rolodex();
        rolodex.setAddressLine1("1290 Avenue of the Americas");
        rolodex.setAddressLine2("Suite 550");
        rolodex.setCity("New York");
        rolodex.setCounty("United States");
        rolodex.setPostalCode("10104");
        rolodex.setState("AL");
        rolodex.setCountryCode("USA");
        performOrganization.setRolodex(rolodex);
        document.getDevelopmentProposal().setPerformingOrgFromOrganization(performOrganization);

        Narrative narrative = new Narrative();
        List<Narrative> naList = new ArrayList<Narrative>();
        NarrativeAttachment narrativeAttachment = new NarrativeAttachment();
        File file = new File(S2STestUtils.ATT_DIR_PATH + "exercise2.pdf");
        InputStream inStream = new FileInputStream(file);
        BufferedInputStream bis = new BufferedInputStream(inStream);
        byte[] narrativePdf = new byte[bis.available()];
        narrativeAttachment.setNarrativeData(narrativePdf);
        List<NarrativeAttachment> narrativeList = new ArrayList<NarrativeAttachment>();
        narrativeList.add(narrativeAttachment);
        narrative.setProposalNumber(document.getDevelopmentProposal().getProposalNumber());
        narrative.setModuleNumber(1);
        narrative.setModuleSequenceNumber(1);
        narrative.setModuleStatusCode("C");
        narrative.setNarrativeTypeCode("40");
        narrative.setNarrativeAttachmentList(narrativeList);
        narrative.setObjectId("12345678890abcd");
        narrative.setFileName("exercise3");
        NarrativeType narrativeType = new NarrativeType();
        narrativeType.setDescription("Testing for performancesite Attachment");
        narrative.setNarrativeType(narrativeType);
        naList.add(narrative);
        getService(BusinessObjectService.class).save(narrative);
        narrative.getNarrativeAttachmentList().clear();
        document.getDevelopmentProposal().setNarratives(naList);
    }
}
