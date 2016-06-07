package org.kuali.coeus.common.questionnaire.impl.print;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.kuali.coeus.common.framework.person.PropAwardPersonRole;
import org.kuali.coeus.common.framework.person.PropAwardPersonRoleService;
import org.kuali.coeus.common.questionnaire.framework.answer.Answer;
import org.kuali.coeus.common.questionnaire.framework.answer.AnswerHeader;
import org.kuali.coeus.common.questionnaire.framework.core.Questionnaire;
import org.kuali.coeus.common.questionnaire.framework.core.QuestionnaireConstants;
import org.kuali.coeus.common.questionnaire.framework.core.QuestionnaireQuestion;
import org.kuali.coeus.common.questionnaire.framework.question.Question;
import org.kuali.coeus.common.questionnaire.framework.question.QuestionExplanation;
import org.kuali.coeus.common.questionnaire.impl.answer.QuestionnaireAnswerServiceImpl;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;
import org.kuali.kra.printing.schema.QuestionInfoType;
import org.kuali.kra.printing.schema.QuestionnaireDocument;
import org.kuali.rice.krad.service.BusinessObjectService;

public class QuestionnaireXmlStreamTest {

	private static final String NEGATIVE_TEXT = "No sir, no sir";
	private static final String AFFIRMITIVE_TEXT = "Yes sir, yes sir";
	private static final String ANSWER_1 = "Y";
	private static final String ANSWER_2 = "Y";
	private static final String ANSWER_3 = "N";
	private static final String ANSWER_4 = "Y";
	private static final String ANSWER_5 = "N";
	private static final String QUESTION_1 = "Foobar?";
	private static final String QUESTION_2 = "Foobar For Real?";
	private static final String QUESTION_3 = "For Real?";
	private static final String QUESTION_4 = "Parent";
	private static final String QUESTION_5 = "Child";
	private static final String QUESTION_6 = "SubChild";
	private static final String DEMO_QUESTIONNAIRE = "Demo Questionnaire";
	private static final String PRINCIPAL_INVESTIGATOR = "Principal Investigator";
	private static final String CLARK_KENT = "Clark Kent";
	
	private Map<Integer, String> answerMap = new HashMap<>();

	@Test
	public void test_getQuestionnaireData() {
		BusinessObjectService boService = mock(BusinessObjectService.class);
		final Questionnaire questionnaire = getQuestionnaire();
		when(boService.findMatchingOrderBy(eq(Questionnaire.class), anyMap(), anyString(), anyBoolean())).thenReturn(Collections.singletonList(questionnaire));
		
		QuestionnaireAnswerServiceImpl questionnaireAnswerService = mock(QuestionnaireAnswerServiceImpl.class);
		when(questionnaireAnswerService.getModuleSpecificBean(anyString(), anyString(), anyString(), anyString(), anyBoolean())).thenCallRealMethod();
		when(questionnaireAnswerService.getQuestionnaireAnswer(anyObject())).thenReturn(Collections.singletonList(getAnswerHeader(questionnaire)));
		
		PropAwardPersonRoleService propAwardPersonRoleService = mock(PropAwardPersonRoleService.class);
		final PropAwardPersonRole personRole = new PropAwardPersonRole();
		personRole.setDescription(PRINCIPAL_INVESTIGATOR);
		when(propAwardPersonRoleService.getRole(anyString(), anyString())).thenReturn(personRole);
		
		QuestionnaireXmlStream stream = new QuestionnaireXmlStream();
		stream.setBusinessObjectService(boService);
		stream.setQuestionnaireAnswerService(questionnaireAnswerService);
		
		ProposalPerson person = new ProposalPerson();
		DevelopmentProposal proposal = new DevelopmentProposal();
		proposal.setSponsorCode("000500");
		proposal.setProposalNumber("123");
		person.setDevelopmentProposal(proposal);
		person.setProposalPersonNumber(1);
		person.setProposalPersonRoleId("PI");
		person.setFullName(CLARK_KENT);
		person.setPropAwardPersonRoleService(propAwardPersonRoleService);
		
		Map<String, Object> reportParams = new HashMap<>();
		reportParams.put(QuestionnaireConstants.QUESTIONNAIRE_SEQUENCE_ID_PARAMETER_NAME, 1);
		
		QuestionnaireDocument xmlDocument = stream.getQuestionnaireData(person, reportParams);
		assertNotNull(xmlDocument);
		assertEquals(CLARK_KENT, xmlDocument.getQuestionnaire().getProposalInfoArray(0).getProposalPersonName());
		assertEquals(PRINCIPAL_INVESTIGATOR, xmlDocument.getQuestionnaire().getProposalInfoArray(0).getProposalPersonRole());
		
		assertEquals(DEMO_QUESTIONNAIRE, xmlDocument.getQuestionnaire().getQuestionnaireName());
		
		assertEquals(QUESTION_1, xmlDocument.getQuestionnaire().getQuestionsArray(0).getQuestionInfoArray(0).getQuestion());
		assertEquals("Yes" , xmlDocument.getQuestionnaire().getQuestionsArray(0).getQuestionInfoArray(0).getAnswerInfoArray(0).getAnswer());
		
		assertEquals(AFFIRMITIVE_TEXT, xmlDocument.getQuestionnaire().getQuestionsArray(0).getQuestionInfoArray(1).getQuestion());
		assertEquals("Yes" , xmlDocument.getQuestionnaire().getQuestionsArray(0).getQuestionInfoArray(1).getAnswerInfoArray(0).getAnswer());

		assertEquals(NEGATIVE_TEXT, xmlDocument.getQuestionnaire().getQuestionsArray(0).getQuestionInfoArray(2).getQuestion());
		assertEquals("No" , xmlDocument.getQuestionnaire().getQuestionsArray(0).getQuestionInfoArray(2).getAnswerInfoArray(0).getAnswer());

		final QuestionInfoType xmlQuestion4 = xmlDocument.getQuestionnaire().getQuestionsArray(0).getQuestionInfoArray(3);
		assertEquals(QUESTION_4, xmlQuestion4.getQuestion());
		assertEquals("Yes" , xmlQuestion4.getAnswerInfoArray(0).getAnswer());
		
		final QuestionInfoType xmlQuestion5 = xmlQuestion4.getQuestionInfoArray(0);
		assertEquals(QUESTION_5, xmlQuestion5.getQuestion());
		assertEquals("No" , xmlQuestion5.getAnswerInfoArray(0).getAnswer());
		
		assertEquals(0, xmlQuestion5.getQuestionInfoArray().length);

	}

	protected Questionnaire getQuestionnaire() {
		Questionnaire questionnaire = new Questionnaire() {
			@Override
			public void refreshReferenceObject(String reference) { }
		};
		questionnaire.setName(DEMO_QUESTIONNAIRE);
		questionnaire.setId(485L);
		questionnaire.setQuestionnaireSeqId("2");
		questionnaire.getQuestionnaireQuestions().add(getQuestionnaireQuestion(234L, 345L, 1, 123, QUESTION_1, ANSWER_1, questionnaire, false));
		questionnaire.getQuestionnaireQuestions().add(getQuestionnaireQuestion(235L, 346L, 2, 124, QUESTION_2, ANSWER_2, questionnaire, true));
		questionnaire.getQuestionnaireQuestions().add(getQuestionnaireQuestion(236L, 347L, 3, 125, QUESTION_3, ANSWER_3, questionnaire, true));
		final QuestionnaireQuestion question4 = getQuestionnaireQuestion(237L, 348L, 4, 126, QUESTION_4, ANSWER_4, questionnaire, false);
		questionnaire.getQuestionnaireQuestions().add(question4);
		final QuestionnaireQuestion question5 = getQuestionnaireQuestion(238L, 349L, 5, 127, QUESTION_5, ANSWER_5, questionnaire, false);
		question5.setParentQuestionNumber(4);
		question5.setConditionValue(ANSWER_4);
		questionnaire.getQuestionnaireQuestions().add(question5);
		
		final QuestionnaireQuestion question6 = getQuestionnaireQuestion(239L, 350L, 6, 128, QUESTION_6, null, questionnaire, false);
		question6.setParentQuestionNumber(5);
		question6.setConditionValue("Not the same answer");
		questionnaire.getQuestionnaireQuestions().add(question6);
		return questionnaire;
	}
	
	protected QuestionnaireQuestion getQuestionnaireQuestion(Long id, Long questionId, Integer number, Integer questionSeqId, String questionText, String answer, Questionnaire questionnaire, boolean addExplanationText) {
		QuestionnaireQuestion questionnaireQuestion = new QuestionnaireQuestion();
		questionnaireQuestion.setId(id);
		questionnaireQuestion.setQuestionNumber(number);
		questionnaireQuestion.setQuestion(getQuestion(questionId, questionSeqId, questionText, addExplanationText));
		questionnaireQuestion.setQuestionId(questionId);
		questionnaireQuestion.setParentQuestionNumber(0);
		questionnaireQuestion.setQuestionnaireId(questionnaire.getId());
		answerMap.put(number, answer);
		return questionnaireQuestion;
	}
	
	protected Question getQuestion(Long questionId, Integer questionSeqId, String questionText, boolean addExplanationText) {
		Question question = new Question();
		question.setId(questionId);
		question.setQuestion(questionText);
		question.setQuestionSeqId(questionSeqId);
		question.setQuestionTypeId(1L);
		question.setQuestionExplanations(new ArrayList<>());
		if (addExplanationText) {
			question.getQuestionExplanations().add(getQuestionExplanation("A", AFFIRMITIVE_TEXT));
			question.getQuestionExplanations().add(getQuestionExplanation("N", NEGATIVE_TEXT));
		} else {
			question.getQuestionExplanations().add(getQuestionExplanation("A", ""));
			question.getQuestionExplanations().add(getQuestionExplanation("N", ""));
		}
		return question;
	}

	protected QuestionExplanation getQuestionExplanation(String type, String explanationText) {
		QuestionExplanation explanation = new QuestionExplanation();
		explanation.setExplanationType(type);
		explanation.setExplanation(explanationText);
		return explanation;
	}
	
	protected AnswerHeader getAnswerHeader(Questionnaire questionnaire) {
		AnswerHeader header = new AnswerHeader();
		header.setQuestionnaire(questionnaire);
		header.setQuestionnaireId(questionnaire.getId());
		header.setAnswers(new ArrayList<>());
		header.getAnswers().add(getAnswer(questionnaire.getQuestionnaireQuestion(0)));
		header.getAnswers().add(getAnswer(questionnaire.getQuestionnaireQuestion(1)));
		header.getAnswers().add(getAnswer(questionnaire.getQuestionnaireQuestion(2)));
		final Answer answer4 = getAnswer(questionnaire.getQuestionnaireQuestion(3));
		header.getAnswers().add(answer4);
		final Answer answer5 = getAnswer(questionnaire.getQuestionnaireQuestion(4));
		answer5.setParentAnswers(new ArrayList<>());
		answer5.getParentAnswers().add(answer4);
		header.getAnswers().add(answer5);
		
		return header;
	}
	
	protected Answer getAnswer(QuestionnaireQuestion questionnaireQuestion) {
		Answer answer = new Answer();
		answer.setAnswer(answerMap.get(questionnaireQuestion.getQuestionNumber()));
		answer.setAnswerNumber(0);
		answer.setQuestionNumber(questionnaireQuestion.getQuestionNumber());
		answer.setQuestionId(questionnaireQuestion.getQuestionId());
		answer.setQuestionnaireQuestion(questionnaireQuestion);
		answer.setQuestion(questionnaireQuestion.getQuestion());
		return answer;
	}
}
