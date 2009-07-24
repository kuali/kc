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

import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.proposaldevelopment.bo.ProposalAbstract;
import org.kuali.kra.proposaldevelopment.bo.ProposalYnq;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.s2s.generator.S2STestBase;

/**
 * This class tests the NSFApplicationChecklistV1_1 Generator
 */
public class NSFApplicationChecklistV1_1GeneratorTest extends S2STestBase<NSFApplicationChecklistV1_1Generator> {

    @Override
    protected Class<NSFApplicationChecklistV1_1Generator> getFormGeneratorClass() {
        return NSFApplicationChecklistV1_1Generator.class;
    }

    @Override
    protected void prepareData(ProposalDevelopmentDocument document) throws Exception {

        document.getDevelopmentProposal().setProposalTypeCode("8");
        document.getDevelopmentProposal().setProposalTypeCode("2");
        document.getDevelopmentProposal().setProposalTypeCode("5");
        ProposalYnq proposalYnq = new ProposalYnq();
        proposalYnq.setAnswer("Y");
        proposalYnq.setQuestionId("21");
        ProposalYnq proposalYnq1 = new ProposalYnq();
        proposalYnq1.setAnswer("Y");
        proposalYnq1.setQuestionId("FG");
        List<ProposalYnq> ynqList = new ArrayList<ProposalYnq>();
        ynqList.add(proposalYnq);
        ynqList.add(proposalYnq1);
        document.getDevelopmentProposal().setProposalYnqs(ynqList);

        ProposalAbstract propsAbstract = new ProposalAbstract();
        propsAbstract.setAbstractTypeCode("15");
        ProposalAbstract propsAbstract1 = new ProposalAbstract();
        propsAbstract1.setAbstractTypeCode("12");
        List<ProposalAbstract> proList = new ArrayList<ProposalAbstract>();
        proList.add(propsAbstract);
        proList.add(propsAbstract1);
        document.getDevelopmentProposal().setProposalAbstracts(proList);
    }
}
