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
import org.kuali.kra.proposaldevelopment.bo.ProposalAbstract;
import org.kuali.kra.s2s.generator.S2STestBase;

import java.util.ArrayList;
import java.util.List;

/**
 * This class tests the NSFSuggestedReviewersV1_1 Generator
 */
public class NSFSuggestedReviewersV1_1GeneratorTest extends S2STestBase<NSFSuggestedReviewersV1_1Generator> {

    @Override
    protected Class<NSFSuggestedReviewersV1_1Generator> getFormGeneratorClass() {
        return NSFSuggestedReviewersV1_1Generator.class;
    }

    @Override
    protected void prepareData(ProposalDevelopmentDocument document) throws Exception {

        ProposalAbstract propsAbstract = new ProposalAbstract();
        propsAbstract.setAbstractTypeCode("14");
        propsAbstract.setAbstractDetails("NSFSuggestedReviewers AbstractDetails");
        ProposalAbstract propsAbstract1 = new ProposalAbstract();
        propsAbstract1.setAbstractTypeCode("12");
        propsAbstract1.setAbstractDetails("AbstractDetails");
        List<ProposalAbstract> proList = new ArrayList<ProposalAbstract>();
        proList.add(propsAbstract);
        proList.add(propsAbstract1);

        propsAbstract.setProposalNumber(document.getDevelopmentProposal().getProposalNumber());
        propsAbstract1.setProposalNumber(document.getDevelopmentProposal().getProposalNumber());
        document.getDevelopmentProposal().setProposalAbstracts(proList);
    }
}
