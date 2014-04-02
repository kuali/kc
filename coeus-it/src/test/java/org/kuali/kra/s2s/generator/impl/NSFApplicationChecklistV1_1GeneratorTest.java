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
import org.kuali.coeus.propdev.impl.ynq.ProposalYnq;
import org.kuali.kra.bo.Ynq;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.bo.ProposalAbstract;
import org.kuali.kra.s2s.generator.S2STestBase;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

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

        DevelopmentProposal developmentProposal = document
                .getDevelopmentProposal();
        developmentProposal.setProposalTypeCode("8");
        developmentProposal.setProposalTypeCode("2");
        developmentProposal.setProposalTypeCode("5");
        ProposalYnq proposalYnq = new ProposalYnq();
        proposalYnq.setAnswer("Y");
        proposalYnq.setQuestionId("21");
        Ynq ynq = new Ynq();
        ynq.setQuestionId("21");
        ynq.setGroupName("groupName");
        ynq.setDescription("description");
        ynq.setEffectiveDate(new Date(1));
        ynq.setNoOfAnswers(1);
        ynq.setQuestionType("A");
        ynq.setStatus("A");
        proposalYnq.setYnq(ynq);
        saveBO(ynq);
        ProposalYnq proposalYnq1 = new ProposalYnq();
        proposalYnq1.setAnswer("Y");
        proposalYnq1.setQuestionId("FG");
        Ynq ynq1 = new Ynq();
        ynq1.setQuestionId("FG");
        ynq1.setGroupName("groupName1");
        ynq1.setDescription("description1");
        ynq1.setEffectiveDate(new Date(1));
        ynq1.setNoOfAnswers(1);
        ynq1.setQuestionType("B");
        ynq1.setStatus("B");
        proposalYnq1.setYnq(ynq1);
        saveBO(ynq1);
        List<ProposalYnq> ynqList = new ArrayList<ProposalYnq>();
        ynqList.add(proposalYnq);
        ynqList.add(proposalYnq1);

        proposalYnq.setProposalNumber(document.getDevelopmentProposal().getProposalNumber());
        proposalYnq1.setProposalNumber(document.getDevelopmentProposal().getProposalNumber());

        developmentProposal.setProposalYnqs(ynqList);

        ProposalAbstract propsAbstract = new ProposalAbstract();
        propsAbstract.setAbstractTypeCode("15");
        propsAbstract.setAbstractDetails("details details");
        propsAbstract.setProposalNumber(document.getDevelopmentProposal().getProposalNumber());

        ProposalAbstract propsAbstract1 = new ProposalAbstract();
        propsAbstract1.setAbstractTypeCode("12");
        propsAbstract1.setAbstractDetails("details details1");
        propsAbstract1.setProposalNumber(document.getDevelopmentProposal().getProposalNumber());

        List<ProposalAbstract> proList = new ArrayList<ProposalAbstract>();
        proList.add(propsAbstract);
        proList.add(propsAbstract1);

        developmentProposal.setProposalAbstracts(proList);
        document.setDevelopmentProposal(developmentProposal);
    }
}
