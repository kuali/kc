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

import org.kuali.coeus.common.framework.type.ProposalType;
import org.kuali.coeus.common.framework.ynq.Ynq;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.ynq.ProposalYnq;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.abstrct.ProposalAbstract;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * This class tests the NSFApplicationChecklistV1_2 Generator
 */
public class NSFApplicationChecklistV1_2GeneratorTest extends
		S2STestBase<NSFApplicationChecklistV1_2Generator> {

	@Override
	protected String getFormGeneratorName() {
		return NSFApplicationChecklistV1_2Generator.class.getSimpleName();
	}

	@Override
	protected void prepareData(ProposalDevelopmentDocument document)
			throws Exception {
		DevelopmentProposal developmentProposal = document
				.getDevelopmentProposal();
        ProposalType type = new ProposalType();
        type.setCode("5");
        developmentProposal.setProposalType(type);
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
