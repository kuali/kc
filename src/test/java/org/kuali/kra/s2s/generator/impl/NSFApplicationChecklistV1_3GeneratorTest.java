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

import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.bo.ProposalAbstract;
import org.kuali.kra.proposaldevelopment.bo.ProposalYnq;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.questionnaire.Questionnaire;
import org.kuali.kra.questionnaire.answer.Answer;
import org.kuali.kra.questionnaire.answer.AnswerHeader;
import org.kuali.kra.s2s.generator.S2STestBase;

/**
 * This class tests the NSFApplicationChecklistV1_3 Generator
 */
public class NSFApplicationChecklistV1_3GeneratorTest extends
		S2STestBase<NSFApplicationChecklistV1_3Generator> {
	private static final String QUESTIONNAIRE_ANSWER_YES = "Y";
	private static final String QUESTIONNAIRE_ANSWER_NO = "N";

	@Override
	protected Class<NSFApplicationChecklistV1_3Generator> getFormGeneratorClass() {
		return NSFApplicationChecklistV1_3Generator.class;
	}

	@Override
	protected void prepareData(ProposalDevelopmentDocument document)
			throws Exception {
		DevelopmentProposal developmentProposal = document
				.getDevelopmentProposal();
		developmentProposal.setProposalTypeCode("8");
		developmentProposal.setProposalTypeCode("2");
		developmentProposal.setProposalTypeCode("5");
		ProposalYnq proposalYnq = new ProposalYnq();
		proposalYnq.setAnswer("Y");
		proposalYnq.setQuestionId("21");
		ProposalYnq proposalYnq1 = new ProposalYnq();
		proposalYnq1.setAnswer("Y");
		proposalYnq1.setQuestionId("FG");
		List<ProposalYnq> ynqList = new ArrayList<ProposalYnq>();
		ynqList.add(proposalYnq);
		ynqList.add(proposalYnq1);
		developmentProposal.setProposalYnqs(ynqList);

		ProposalAbstract propsAbstract = new ProposalAbstract();
		propsAbstract.setAbstractTypeCode("15");
		ProposalAbstract propsAbstract1 = new ProposalAbstract();
		propsAbstract1.setAbstractTypeCode("12");
		List<ProposalAbstract> proList = new ArrayList<ProposalAbstract>();
		proList.add(propsAbstract);
		proList.add(propsAbstract1);
		developmentProposal.setProposalAbstracts(proList);
		document.setDevelopmentProposal(developmentProposal);
		//TODO Need to Map the Questionnaire with pdDoc.
	}

	private Questionnaire getQuestionire(ProposalDevelopmentDocument document) {
		Questionnaire questionnaire=new Questionnaire();
		questionnaire.setQuestionnaireIdFromInteger(1025);
		questionnaire.setQuestionnaireRefIdFromLong(100L);
		
		AnswerHeader answerHeader=new AnswerHeader();
		answerHeader.setModuleItemKey(document.getDevelopmentProposal().getProposalNumber());
		answerHeader.setModuleItemKey("");//Need to set the values
		answerHeader.setModuleItemCode("3");
		answerHeader.setModuleSubItemCode("0");
		answerHeader.setModuleSubItemKey("0");
		answerHeader.setQuestionnaireRefIdFk("100");
		answerHeader.setAnswers(getQuestionireAnswers());
		return questionnaire;
	}
	private List<Answer> getQuestionireAnswers() {
		List<Answer> answersList = new ArrayList<Answer>();

		Answer answerPreliminary = new Answer();
		answerPreliminary.setQuestionNumber(1110);
		answerPreliminary.setAnswer(QUESTIONNAIRE_ANSWER_YES);
		answersList.add(answerPreliminary);

		Answer answerMeritReviw = new Answer();
		answerMeritReviw.setQuestionNumber(1111);
		answerMeritReviw.setAnswer(QUESTIONNAIRE_ANSWER_NO);
		answersList.add(answerMeritReviw);

		Answer answerMentoring = new Answer();
		answerMentoring.setQuestionNumber(1113);
		answerMentoring.setAnswer(QUESTIONNAIRE_ANSWER_YES);
		answersList.add(answerMentoring);

		Answer answerPriorSupport = new Answer();
		answerPriorSupport.setQuestionNumber(1114);
		answerPriorSupport.setAnswer(QUESTIONNAIRE_ANSWER_NO);
		answersList.add(answerPriorSupport);

		Answer answerHrQuestion = new Answer();
		answerHrQuestion.setQuestionNumber(1108);
		answerHrQuestion.setAnswer(QUESTIONNAIRE_ANSWER_YES);
		answersList.add(answerHrQuestion);

		Answer answerHrRequiredInfo = new Answer();
		answerHrRequiredInfo.setQuestionNumber(1117);
		answerHrRequiredInfo.setAnswer(QUESTIONNAIRE_ANSWER_NO);
		answersList.add(answerHrRequiredInfo);

		return answersList;
	}
}
