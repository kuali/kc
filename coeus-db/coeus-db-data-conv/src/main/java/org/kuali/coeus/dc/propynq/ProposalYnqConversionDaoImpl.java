package org.kuali.coeus.dc.propynq;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.kuali.coeus.dc.common.db.ConnectionDaoService;

public class ProposalYnqConversionDaoImpl implements ProposalYnqConversionDao {

	private static final int YES_NO_NA_QUESTION_TYPE_ID = 2;
	private static final String ACTIVE_QUESTION_STATUS = "A";
	private static final String CURRENT_QUESTION_SEQUENCE_STATUS = "C";
	private static final int CERTIFICATION_SUB_MODULE_CODE = 3;
	private static final long NO_PARENT_QUESTION_ID = 0L;
	private static final String NO_FLAG = "N";
	private static final String SEQ_QUESTIONNAIRE_REF_ID = "SEQ_QUESTIONNAIRE_REF_ID";
	private static final String ADMIN_USER = "admin";
	private static final String INVESTIGATOR_YNQ_TYPE = "I";
	private static final String PROPOSAL_YNQ_TYPE = "P";
	private static final Integer EMPTY_SUB_ITEM_CODE = 0;
	private static final Integer PROP_DEV_MODULE_CODE = 3;
	private static final String CERTIFICATION_YNQ_QUESTIONNAIRE_NAME = "Certification Converted YNQs";
	private static final String PROPOSAL_YNQ_QUESTIONNAIRE_NAME = "Proposal Converted YNQs";
	private static final String QUESTION_SEQUENCE = "SEQ_QUESTION_ID";
	private static final String SELECT_PROP_YNQ = "select question_id, answer, explanation, update_timestamp, update_user from eps_prop_ynq where proposal_number = ? order by sort_id";
	private static final String SELECT_DISTINCT_PROPOSAL_YNQ = "select distinct proposal_number from eps_prop_ynq";
	private static final String SELECT_PROP_PERSON_YNQ = "select question_id, answer, null, update_timestamp, update_user from eps_prop_pers_ynq where proposal_number = ? and prop_person_number = ?";
	private static final String SELECT_DISTINCT_PROP_PERSON_YNQ = "select distinct proposal_number, prop_person_number from eps_prop_pers_ynq";
	private static final String SELECT_YNQ = "select question_id, description, question_type, no_of_answers, explanation_required_for,"
		+ " status, group_name, sort_id from ynq where question_type = ?";
	private static final String SELECT_YNQ_EXPLANATION = "select explanation_type, explanation from ynq_explanation where question_id = ?";
	private static final String INSERT_QUESTION_STR = "insert into question (question_ref_id, question_id, sequence_number, sequence_status, question,"
		+ " status, group_type_code, question_type_id, displayed_answers, max_answers, answer_max_length, update_timestamp, update_user, ver_nbr, obj_id)"
		+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String INSERT_QUESTION_EXPLANATION = "insert into question_explanation (question_explanation_id, question_ref_id_fk, explanation_type, explanation, update_timestamp, update_user, ver_nbr, obj_id)" 
		+ " values (?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String INSERT_QUESTIONNAIRE_STR = "insert into questionnaire (questionnaire_ref_id, questionnaire_id, sequence_number, name, description, update_timestamp, update_user, is_final, ver_nbr, obj_id) " + 
		"values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String ADD_QUESTION_TO_QUESTIONNAIRE_STR = "insert into questionnaire_questions (questionnaire_questions_id, questionnaire_ref_id_fk, question_ref_id_fk, question_number, parent_question_number, "
		+ "condition_flag, question_seq_number, update_timestamp, update_user, ver_nbr, obj_id) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String ADD_ANSWER_HEADER_STR = "insert into questionnaire_answer_header (questionnaire_answer_header_id, questionnaire_ref_id_fk, module_item_code, module_sub_item_code, "
		+ "module_item_key, module_sub_item_key, questionnaire_completed_flag, update_timestamp, update_user, ver_nbr, obj_id) "
		+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"; 
	private static final String ADD_ANSWER_STR = "insert into questionnaire_answer (questionnaire_answer_id, questionnaire_ah_id_fk, question_ref_id_fk, questionnaire_questions_id_fk, question_number, answer_number, answer, update_timestamp, update_user, ver_nbr, obj_id) " + 
		"values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String ADD_QUESTIONNAIRE_USAGE_STR = "insert into questionnaire_usage (questionnaire_usage_id, module_item_code, module_sub_item_code, questionnaire_ref_id_fk, questionnaire_sequence_number, " + 
		"questionnaire_label, update_timestamp, update_user, ver_nbr, obj_id, is_mandatory, rule_id) " + 
		"values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
	private static final String UPDATE_PERSON_CERT_BY_STR = "update eps_prop_person set certified_by = (select prncpl_id from krim_prncpl_t where prncpl_nm = ?), certified_time = ? where proposal_number = ? and prop_person_number = ?";
	private static final String BACKUP_PROPOSAL_YNQ = "create table eps_prop_ynq_bak as select * from eps_prop_ynq";
	private static final String DELETE_PROPOSAL_YNQ_STR = "delete from eps_prop_ynq where proposal_number = ?";
	private static final String BACKUP_PROP_PERSON_YNQ = "create table eps_prop_pers_ynq_bak as select * from eps_prop_pers_ynq";
	private static final String DELETE_PROP_PERSON_YNQ_STR = "delete from eps_prop_pers_ynq where proposal_number = ? and prop_person_number = ?";
	private static final String INACTIVATE_YNQ = "update ynq set status = 'I' where question_type = ?";
	
	private ConnectionDaoService connectionDaoService;
	
	@Override
	public void convertProposalYnqs() {
		Connection conn = connectionDaoService.getCoeusConnection();
		try (Statement stmt = conn.createStatement()) {
			stmt.executeUpdate(BACKUP_PROPOSAL_YNQ);
			stmt.executeUpdate(BACKUP_PROP_PERSON_YNQ);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		convertProposalYnq(conn);
		convertProposalPersonYnq(conn);
	}
	
	protected void convertProposalYnq(Connection conn) {
		Integer questionnaireId = null;
		Integer sequenceNumber = 1;
		Map<String, Long> ynqsToQuestionnaireRefId = new HashMap<>();
		Map<String, Long> ynqToQuestionId = new HashMap<>();
		Map<Long, Map<Long, Long>> questionnaireQuestionIds = new HashMap<>();
		Map<Long, Map<Long, Integer>> questionnaireQuestionAnswerNumbers = new HashMap<>();
		Map<String, Ynq> proposalQuestions;
		List<Ynq> lastQuestions = null;
		try {
			proposalQuestions = getYnqOfType(PROPOSAL_YNQ_TYPE, conn);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		try (PreparedStatement selectProposalYnq = 
				conn.prepareStatement(SELECT_PROP_YNQ);
			PreparedStatement insertQuestion =
				conn.prepareStatement(INSERT_QUESTION_STR);
			PreparedStatement insertQuestionnaire =
				conn.prepareStatement(INSERT_QUESTIONNAIRE_STR);
			PreparedStatement addQuestionToQuestionnaire =
				conn.prepareStatement(ADD_QUESTION_TO_QUESTIONNAIRE_STR);
			PreparedStatement addAnswerHeader =
				conn.prepareStatement(ADD_ANSWER_HEADER_STR);
			PreparedStatement addAnswer =
				conn.prepareStatement(ADD_ANSWER_STR);
			PreparedStatement addQuestionnaireUsage =
				conn.prepareStatement(ADD_QUESTIONNAIRE_USAGE_STR);
			PreparedStatement deleteProposalYnq =
				conn.prepareStatement(DELETE_PROPOSAL_YNQ_STR);
			PreparedStatement stmt = 
				conn.prepareStatement(SELECT_DISTINCT_PROPOSAL_YNQ);
			PreparedStatement inactivateYnq =
				conn.prepareStatement(INACTIVATE_YNQ);
			PreparedStatement selectYnqExplanations =
				conn.prepareStatement(SELECT_YNQ_EXPLANATION);
			PreparedStatement insertQuestionExplanation =
				conn.prepareStatement(INSERT_QUESTION_EXPLANATION);
			ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				String proposalNumber = rs.getString(1);
				List<PropYnq> answers = getYnqForProposal(proposalNumber, selectProposalYnq);
				String ynqIds = answers.stream().map(answer -> answer.questionId).sorted().collect(Collectors.joining(","));
				if (ynqsToQuestionnaireRefId.containsKey(ynqIds)) {
					Long refId = ynqsToQuestionnaireRefId.get(ynqIds);
					convertAnswers(answers, 0, proposalNumber, refId, ynqToQuestionId, questionnaireQuestionIds.get(refId),
						questionnaireQuestionAnswerNumbers.get(refId), addAnswerHeader, addAnswer, conn);
				} else {
					List<Ynq> questions = addQuestions(answers, ynqToQuestionId, proposalQuestions, insertQuestion, selectYnqExplanations, insertQuestionExplanation, conn);
					if (questionnaireId == null) {
						questionnaireId = getSequenceValue(QUESTION_SEQUENCE, conn).intValue();
					} else {
						sequenceNumber++;
					}
					Long refId = addQuestionnaire(questions, PROPOSAL_YNQ_QUESTIONNAIRE_NAME, questionnaireId, sequenceNumber, EMPTY_SUB_ITEM_CODE, 
						true, ynqToQuestionId, questionnaireQuestionIds, 
						questionnaireQuestionAnswerNumbers, insertQuestionnaire, addQuestionToQuestionnaire, addQuestionnaireUsage, conn);
					ynqsToQuestionnaireRefId.put(ynqIds, refId);
					
					convertAnswers(answers, 0, proposalNumber, refId, ynqToQuestionId, questionnaireQuestionIds.get(refId),
						questionnaireQuestionAnswerNumbers.get(refId), addAnswerHeader, addAnswer, conn);
					lastQuestions = questions;
				}
				deleteProposalYnq.setString(1, proposalNumber);
				deleteProposalYnq.executeUpdate();
			}
			if (questionnaireId != null && lastQuestions != null) {
				addQuestionnaire(lastQuestions, PROPOSAL_YNQ_QUESTIONNAIRE_NAME, questionnaireId, ++sequenceNumber, EMPTY_SUB_ITEM_CODE, 
					false, ynqToQuestionId, questionnaireQuestionIds, 
					questionnaireQuestionAnswerNumbers, insertQuestionnaire, addQuestionToQuestionnaire, addQuestionnaireUsage, conn);
			}
			inactivateYnq.setString(1, PROPOSAL_YNQ_TYPE);
			inactivateYnq.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	protected void convertProposalPersonYnq(Connection conn) {
		Integer questionnaireId = null;
		Integer sequenceNumber = 1;
		Map<String, Long> ynqsToQuestionnaireRefId = new HashMap<>();
		Map<String, Long> ynqToQuestionId = new HashMap<>();
		Map<Long, Map<Long, Long>> questionnaireQuestionIds = new HashMap<>();
		Map<Long, Map<Long, Integer>> questionnaireQuestionAnswerNumbers = new HashMap<>();
		Map<String, Ynq> proposalQuestions;
		List<Ynq> lastQuestions = null;
		try {
			proposalQuestions = getYnqOfType(INVESTIGATOR_YNQ_TYPE, conn);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		try (PreparedStatement selectProposalYnq = 
				conn.prepareStatement(SELECT_PROP_PERSON_YNQ);
			PreparedStatement insertQuestion =
				conn.prepareStatement(INSERT_QUESTION_STR);
			PreparedStatement insertQuestionnaire =
				conn.prepareStatement(INSERT_QUESTIONNAIRE_STR);
			PreparedStatement addQuestionToQuestionnaire =
				conn.prepareStatement(ADD_QUESTION_TO_QUESTIONNAIRE_STR);
			PreparedStatement addAnswerHeader =
				conn.prepareStatement(ADD_ANSWER_HEADER_STR);
			PreparedStatement addAnswer =
				conn.prepareStatement(ADD_ANSWER_STR);
			PreparedStatement addQuestionnaireUsage =
				conn.prepareStatement(ADD_QUESTIONNAIRE_USAGE_STR);
			PreparedStatement updatePersonCertifiedBy =
				conn.prepareStatement(UPDATE_PERSON_CERT_BY_STR);
			PreparedStatement deleteProposalYnq =
				conn.prepareStatement(DELETE_PROP_PERSON_YNQ_STR);
			PreparedStatement stmt = 
				conn.prepareStatement(SELECT_DISTINCT_PROP_PERSON_YNQ);
			PreparedStatement inactivateYnq =
				conn.prepareStatement(INACTIVATE_YNQ);
			PreparedStatement selectYnqExplanations =
				conn.prepareStatement(SELECT_YNQ_EXPLANATION);
			PreparedStatement insertQuestionExplanation =
				conn.prepareStatement(INSERT_QUESTION_EXPLANATION);			
			ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				String proposalNumber = rs.getString(1);
				Integer propPersonNumber = rs.getInt(2);
				List<PropYnq> answers = getYnqForProposalPerson(proposalNumber, propPersonNumber, selectProposalYnq);
				String ynqIds = answers.stream().map(answer -> answer.questionId).sorted().collect(Collectors.joining(","));
				if (ynqsToQuestionnaireRefId.containsKey(ynqIds)) {
					Long refId = ynqsToQuestionnaireRefId.get(ynqIds);
					convertAnswers(answers, 3, proposalNumber + "|" + propPersonNumber, refId, ynqToQuestionId, questionnaireQuestionIds.get(refId),
						questionnaireQuestionAnswerNumbers.get(refId), addAnswerHeader, addAnswer, conn);
					updateProposalPersonCertifiedByInfo(answers.stream().findFirst().orElse(null), proposalNumber, propPersonNumber, updatePersonCertifiedBy);
				} else {
					List<Ynq> questions = addQuestions(answers, ynqToQuestionId, proposalQuestions, insertQuestion, selectYnqExplanations, insertQuestionExplanation, conn);
					if (questionnaireId == null) {
						questionnaireId = getSequenceValue(QUESTION_SEQUENCE, conn).intValue();
					} else {
						sequenceNumber++;
					}
					Long refId = addQuestionnaire(questions, CERTIFICATION_YNQ_QUESTIONNAIRE_NAME, questionnaireId, sequenceNumber, CERTIFICATION_SUB_MODULE_CODE,
						true, ynqToQuestionId, questionnaireQuestionIds, 
						questionnaireQuestionAnswerNumbers, insertQuestionnaire, addQuestionToQuestionnaire, addQuestionnaireUsage, conn);
					ynqsToQuestionnaireRefId.put(ynqIds, refId);
					
					convertAnswers(answers, 3, proposalNumber + "|" + propPersonNumber, refId, ynqToQuestionId, questionnaireQuestionIds.get(refId),
						questionnaireQuestionAnswerNumbers.get(refId), addAnswerHeader, addAnswer, conn);
					updateProposalPersonCertifiedByInfo(answers.stream().findFirst().orElse(null), proposalNumber, propPersonNumber, updatePersonCertifiedBy);
					lastQuestions = questions;
				}
				deleteProposalYnq.setString(1, proposalNumber);
				deleteProposalYnq.setInt(2, propPersonNumber);
				deleteProposalYnq.executeUpdate();
			}
			if (questionnaireId != null && lastQuestions != null) {
				addQuestionnaire(lastQuestions, CERTIFICATION_YNQ_QUESTIONNAIRE_NAME, questionnaireId, ++sequenceNumber, CERTIFICATION_SUB_MODULE_CODE,
					false, ynqToQuestionId, questionnaireQuestionIds, 
					questionnaireQuestionAnswerNumbers, insertQuestionnaire, addQuestionToQuestionnaire, addQuestionnaireUsage, conn);
			}
			inactivateYnq.setString(1, INVESTIGATOR_YNQ_TYPE);
			inactivateYnq.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}		
	}

	protected void updateProposalPersonCertifiedByInfo(PropYnq answer, String proposalNumber, Integer propPersonNumber,
		PreparedStatement updatePersonCertifiedBy) throws SQLException {
		updatePersonCertifiedBy.setString(1, answer.updateUser);
		updatePersonCertifiedBy.setDate(2, answer.updateTimestamp);
		updatePersonCertifiedBy.setString(3, proposalNumber);
		updatePersonCertifiedBy.setInt(4, propPersonNumber);
		updatePersonCertifiedBy.executeUpdate();
	}

	protected Long addQuestionnaire(List<Ynq> questions, String questionnaireName, Integer questionnaireId, Integer sequenceNumber, Integer moduleSubItemCode,
		boolean addUsage, Map<String, Long> ynqToQuestionId,
		Map<Long, Map<Long, Long>> questionnaireQuestionIds, 
		Map<Long, Map<Long, Integer>> questionnaireQuestionAnswerNumbers, PreparedStatement insertQuestionnaire, PreparedStatement addQuestionToQuestionnaire, PreparedStatement addQuestionnaireUsage, Connection conn) throws SQLException {
		Long refId = createNewQuestionniare(questionnaireName, questionnaireId, sequenceNumber,
			insertQuestionnaire, conn);
		if (addUsage) {
			addQuestionnaireUsage(refId, sequenceNumber, PROP_DEV_MODULE_CODE, moduleSubItemCode, questionnaireName, addQuestionnaireUsage, conn);
		}
		questionnaireQuestionIds.put(refId, new HashMap<>());
		questionnaireQuestionAnswerNumbers.put(refId, new HashMap<>());
		Map<Long, Long> currentQuestionnaireQuestionIds = questionnaireQuestionIds.get(refId);
		Map<Long, Integer> questionAnswerNumbers = questionnaireQuestionAnswerNumbers.get(refId);
		Integer questionNumber = 1;
		for (Ynq question : questions) {
			final String questionId = question.questionId;
			final Long questionRefId = ynqToQuestionId.get(questionId);
			final Long questionnaireQuestionId = addQuestionToQuestionnaire(refId, questionRefId, questionNumber, addQuestionToQuestionnaire, conn);
			currentQuestionnaireQuestionIds.put(questionRefId, 
				questionnaireQuestionId);
			questionAnswerNumbers.put(questionRefId, questionNumber);
			questionNumber += 1;
		}
		return refId;
	}

	protected List<Ynq> addQuestions(List<PropYnq> answers, Map<String, Long> ynqToQuestionId, Map<String, Ynq> proposalQuestions,
		PreparedStatement insertQuestion, PreparedStatement selectYnqExplanation, PreparedStatement insertQuestionExplanation, Connection conn) {
		List<Ynq> questions = answers.stream()
			.map(answer -> proposalQuestions.get(answer.questionId))
			.distinct()
			.filter(question -> question != null)
			.sorted(Comparator.comparing(ynq -> ynq.sortId, Comparator.nullsFirst(Comparator.naturalOrder())))
			.collect(Collectors.toList());
		questions.stream()
			.filter(answer -> !ynqToQuestionId.containsKey(answer.questionId))
			.forEach(question -> ynqToQuestionId.put(question.questionId, createNewQuestion(question, insertQuestion, selectYnqExplanation, insertQuestionExplanation, conn)));
		return questions;
	}

	protected void convertAnswers(List<PropYnq> answers, Integer moduleSubItemCode, String moduleItemKey, Long refId,
		Map<String, Long> ynqToQuestionId, Map<Long, Long> currentQuestionnaireQuestionIds, Map<Long, Integer> questionAnswerNumbers,
		PreparedStatement addAnswerHeader, PreparedStatement addAnswer, Connection conn) throws SQLException {
		PropYnq first = answers.stream().findFirst().orElse(null);
		Long answerHeaderId = createAnswerHeader(refId, String.valueOf(PROP_DEV_MODULE_CODE), moduleSubItemCode, moduleItemKey, String.valueOf(EMPTY_SUB_ITEM_CODE), first.updateTimestamp, first.updateUser, addAnswerHeader, conn);
		String previousQuestionId = null;
		Integer answerNumber = 1;
		for (PropYnq answer : answers.stream().sorted((a1, a2) -> a1.questionId.compareTo(a2.questionId)).collect(Collectors.toList())) {
			final Long questionRefId = ynqToQuestionId.get(answer.questionId);
			if (questionRefId != null) {
				if (previousQuestionId != null && previousQuestionId.equals(answer.questionId)) {
					answerNumber++;
				} else {
					answerNumber = 1;
				}
				final Long questionnaireQuestionsId = currentQuestionnaireQuestionIds.get(questionRefId);
				addAnswer(answerHeaderId, questionRefId, questionnaireQuestionsId, 
					questionAnswerNumbers.get(questionRefId), answerNumber, answer.answer, answer.updateTimestamp, answer.updateUser, addAnswer, conn);
				previousQuestionId = answer.questionId;
			}
		}
	}
	
	protected List<PropYnq> getYnqForProposal(String proposalNumber, PreparedStatement selectProposalYnq) throws SQLException {
		List<PropYnq> answers = new ArrayList<>();
		selectProposalYnq.setString(1, proposalNumber);
		try (ResultSet rs = selectProposalYnq.executeQuery()) {
			while (rs.next()) {
				final PropYnq propYnq = new PropYnq(rs);
				answers.add(propYnq);
			}
		}
		return answers;
	}
	
	protected List<PropYnq> getYnqForProposalPerson(String proposalNumber, Integer propPersonNumber, PreparedStatement selectProposalYnq) throws SQLException {
		List<PropYnq> answers = new ArrayList<>();
		selectProposalYnq.setString(1, proposalNumber);
		selectProposalYnq.setInt(2, propPersonNumber);
		try (ResultSet rs = selectProposalYnq.executeQuery()) {
			while (rs.next()) {
				answers.add(new PropYnq(rs));
			}
		}
		return answers;
	}

	
	/*private static final String insertQuestionStr = "insert into question (question_ref_id, question_id, sequence_number, sequence_status, question, "
		+ " status, group_type_code, question_type_id, displayed_answers, max_answers, answer_max_length, update_timestamp, update_user, ver_nbr, obj_id)"
		+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";*/
	protected Long createNewQuestion(Ynq ynq, PreparedStatement insertQuestion, PreparedStatement selectYnqExplanation, PreparedStatement insertQuestionExplanation, Connection conn) {
		try {
			Long questionRefId = getSequenceValue(SEQ_QUESTIONNAIRE_REF_ID, conn);
			Integer questionId = getSequenceValue(QUESTION_SEQUENCE, conn).intValue();
			insertQuestion.setLong(1, questionRefId);
			insertQuestion.setInt(2, questionId);
			insertQuestion.setInt(3, 1);
			insertQuestion.setString(4, CURRENT_QUESTION_SEQUENCE_STATUS);
			insertQuestion.setString(5, ynq.description);
			insertQuestion.setString(6, ACTIVE_QUESTION_STATUS);
			insertQuestion.setInt(7, 3);
			insertQuestion.setInt(8, YES_NO_NA_QUESTION_TYPE_ID);
			insertQuestion.setInt(9, ynq.noOfAnswers);
			insertQuestion.setInt(10, ynq.noOfAnswers);
			insertQuestion.setInt(11, 1);
			insertQuestion.setDate(12, new Date(new java.util.Date().getTime()));
			insertQuestion.setString(13, ADMIN_USER);
			insertQuestion.setInt(14, 1);
			insertQuestion.setString(15, UUID.randomUUID().toString());
			insertQuestion.executeUpdate();
			createNewQuestionExplanations(ynq, questionRefId, selectYnqExplanation, insertQuestionExplanation, conn);
			return questionRefId;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/*select explanation_type, explanation from ynq_explanation where question_id = ?
	insert into question_explanation (question_explanation_id, question_ref_id_fk, explanation_type, explanation, update_timestamp, update_user, ver_nbr, obj_id)" 
		+ " values (?, ?, ?, ?, ?, ?, ?, ?)*/
	protected void createNewQuestionExplanations(Ynq ynq, Long questionRefId, PreparedStatement selectYnqExplanation, PreparedStatement insertQuestionExplanation, Connection conn) {
		try {
			selectYnqExplanation.setString(1, ynq.questionId);
			try (ResultSet rs = selectYnqExplanation.executeQuery()) {
				while (rs.next()) {
					Long pk = getSequenceValue(SEQ_QUESTIONNAIRE_REF_ID, conn);
					insertQuestionExplanation.setLong(1, pk);
					insertQuestionExplanation.setLong(2, questionRefId);
					insertQuestionExplanation.setString(3, rs.getString(1));
					insertQuestionExplanation.setString(4, rs.getString(2));
					insertQuestionExplanation.setDate(5, new Date(new java.util.Date().getTime()));
					insertQuestionExplanation.setString(6, ADMIN_USER);
					insertQuestionExplanation.setInt(7, 1);
					insertQuestionExplanation.setString(8, UUID.randomUUID().toString());
					insertQuestionExplanation.executeUpdate();
				}
			} 
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/*insert into questionnaire (questionnaire_ref_id, questionnaire_id, sequence_number, name, description, update_timestamp, update_user, is_final, ver_nbr, obj_id) " + 
		"values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)*/
	protected Long createNewQuestionniare(String questionnaireName, Integer questionnaireId, Integer sequenceNumber, 
			PreparedStatement insertQuestionnaire, Connection conn) throws SQLException {
		Long questionnaireRefId = getSequenceValue(SEQ_QUESTIONNAIRE_REF_ID, conn);
		insertQuestionnaire.setLong(1, questionnaireRefId);
		insertQuestionnaire.setInt(2, questionnaireId);
		insertQuestionnaire.setInt(3, sequenceNumber);
		insertQuestionnaire.setString(4, questionnaireName);
		insertQuestionnaire.setString(5, questionnaireName);
		insertQuestionnaire.setDate(6, new Date(new java.util.Date().getTime()));
		insertQuestionnaire.setString(7, ADMIN_USER);
		insertQuestionnaire.setString(8, "Y");
		insertQuestionnaire.setInt(9, 1);
		insertQuestionnaire.setString(10, UUID.randomUUID().toString());
		insertQuestionnaire.executeUpdate();
		return questionnaireRefId;
	}
	
	/* insert into questionnaire_questions (questionnaire_questions_id, questionnaire_ref_id_fk, question_ref_id_fk, question_number, parent_question_number, 
	 * 	condition_flag, question_seq_number, update_timestamp, update_user, ver_nbr, obj_id) 
	 * values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
	 */
	protected Long addQuestionToQuestionnaire(Long questionnaireRefId, Long questionRefId, Integer questionNumber,
			PreparedStatement addQuestion, Connection conn) throws SQLException {
		Long pk = getSequenceValue(SEQ_QUESTIONNAIRE_REF_ID, conn);
		addQuestion.setLong(1, pk);
		addQuestion.setLong(2, questionnaireRefId);
		addQuestion.setLong(3, questionRefId);
		addQuestion.setInt(4, questionNumber);
		addQuestion.setLong(5, NO_PARENT_QUESTION_ID);
		addQuestion.setString(6, NO_FLAG);
		addQuestion.setInt(7, questionNumber);
		addQuestion.setDate(8, new Date(new java.util.Date().getTime()));
		addQuestion.setString(9, ADMIN_USER);
		addQuestion.setInt(10, 1);
		addQuestion.setString(11, UUID.randomUUID().toString());
		addQuestion.executeUpdate();
		return pk;
	}
	
	/*
	 * insert into questionnaire_usage (questionnaire_usage_id, module_item_code, module_sub_item_code, questionnaire_ref_id_fk, questionnaire_sequence_number, 
	 * questionnaire_label, update_timestamp, update_user, ver_nbr, obj_id, is_mandatory, rule_id)
	 * values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);
	 */
	protected void addQuestionnaireUsage(Long questionnaireRefId, Integer questionnaireSequenceNumber,
			Integer moduleItemCode, Integer moduleSubItemCode, String questionnaireLabel, 
			PreparedStatement addQuestionnaireUsage, Connection conn) throws SQLException {
		Long pk = getSequenceValue(SEQ_QUESTIONNAIRE_REF_ID, conn);
		addQuestionnaireUsage.setLong(1, pk);
		addQuestionnaireUsage.setInt(2, moduleItemCode);
		addQuestionnaireUsage.setInt(3, moduleSubItemCode);
		addQuestionnaireUsage.setLong(4, questionnaireRefId);
		addQuestionnaireUsage.setInt(5, questionnaireSequenceNumber);
		addQuestionnaireUsage.setString(6, questionnaireLabel);
		addQuestionnaireUsage.setDate(7, new Date(new java.util.Date().getTime()));
		addQuestionnaireUsage.setString(8, ADMIN_USER);
		addQuestionnaireUsage.setInt(9, 1);
		addQuestionnaireUsage.setString(10, UUID.randomUUID().toString());
		addQuestionnaireUsage.setString(11, NO_FLAG);
		addQuestionnaireUsage.setNull(12, Types.VARCHAR);
		addQuestionnaireUsage.executeUpdate();
	}
	
	/*
	 * insert into questionnaire_answer_header (questionnaire_answer_header_id, questionnaire_ref_id_fk, module_item_code, module_sub_item_code, 
	 * 	module_item_key, module_sub_item_key, questionnaire_completed_flag, update_timestamp, update_user, ver_nbr, obj_id) values 
	 * (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) 
	 */
	protected Long createAnswerHeader(Long questionnaireRefId, String itemCode, Integer subItemCode, String itemKey, String subItemKey, Date updateTimestamp, String updateUser,
			PreparedStatement createAnswerHeader, Connection conn) throws SQLException {
		Long pk = getSequenceValue(SEQ_QUESTIONNAIRE_REF_ID, conn);
		createAnswerHeader.setLong(1, pk);
		createAnswerHeader.setLong(2, questionnaireRefId);
		createAnswerHeader.setString(3, itemCode);
		createAnswerHeader.setInt(4, subItemCode);
		createAnswerHeader.setString(5, itemKey);
		createAnswerHeader.setString(6, subItemKey);
		createAnswerHeader.setString(7, "Y");
		createAnswerHeader.setDate(8, updateTimestamp);
		createAnswerHeader.setString(9, updateUser);
		createAnswerHeader.setInt(10, 1);
		createAnswerHeader.setString(11, UUID.randomUUID().toString());
		createAnswerHeader.executeUpdate();
		return pk;
	}
	
	/*
	 * insert into questionnaire_answer (questionnaire_answer_id, questionnaire_ah_id_fk, question_ref_id_fk, questionnaire_questions_id_kf, question_number, answer_number, parent_answer_number, answer, update_timestamp, update_user, ver_nbr, obj_id)
	 * 	values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
	 */
	protected void addAnswer(Long answerHeaderRefId, Long questionRefId, Long questionnaireQuestionsId, Integer questionNumber, 
			Integer answerNumber, String answer, Date updateTimestamp, String updateUser, PreparedStatement addAnswer, Connection conn) throws SQLException {
		Long pk = getSequenceValue(SEQ_QUESTIONNAIRE_REF_ID, conn);
		addAnswer.setLong(1, pk);
		addAnswer.setLong(2, answerHeaderRefId);
		addAnswer.setLong(3, questionRefId);
		addAnswer.setLong(4, questionnaireQuestionsId);
		addAnswer.setInt(5, questionNumber);
		addAnswer.setInt(6, answerNumber);
		addAnswer.setString(7, answer);
		addAnswer.setDate(8, updateTimestamp);
		addAnswer.setString(9, updateUser);
		addAnswer.setInt(10, 1);
		addAnswer.setString(11, UUID.randomUUID().toString());
		addAnswer.executeUpdate();
	}
	
	
	static class PropYnq {
		String questionId;
		String answer;
		String explanation;
		Date updateTimestamp;
		String updateUser;
		public PropYnq(ResultSet rs) throws SQLException {
			questionId = rs.getString(1);
			answer = rs.getString(2);
			explanation = rs.getString(3);
			updateTimestamp = rs.getDate(4);
			updateUser = rs.getString(5);
		}
	}
	
	public Map<String, Ynq> getYnqOfType(String type, Connection conn) throws SQLException {
		Map<String, Ynq> ynqs = new HashMap<>();
		try (PreparedStatement stmt = conn.prepareStatement(SELECT_YNQ)) {
			stmt.setString(1, type);
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					ynqs.put(rs.getString(1), new Ynq(rs));
				}
			}
		}
		return ynqs;
	}
	
	public Long getSequenceValue(String sequenceName, Connection conn) throws SQLException {
		if (conn.getMetaData().getDatabaseProductName().toLowerCase().contains("oracle")) {
			try (PreparedStatement stmt = conn.prepareStatement("select " + sequenceName + ".nextval from dual");
				ResultSet rs = stmt.executeQuery()) {
				rs.next();
				return rs.getLong(1);
			}
		} else {
			try (PreparedStatement insertVal = conn.prepareStatement("insert into " + sequenceName + " values (null)");
				PreparedStatement selectMax = conn.prepareStatement("select max(id) from " + sequenceName)) {
				insertVal.executeUpdate();
				try (ResultSet rs = selectMax.executeQuery()) {
					rs.next();
					return rs.getLong(1);
				}
			}
		}
	}

	public ConnectionDaoService getConnectionDaoService() {
		return connectionDaoService;
	}

	public void setConnectionDaoService(ConnectionDaoService connectionDaoService) {
		this.connectionDaoService = connectionDaoService;
	}
	
	static class Ynq {
		String questionId;
		String description;
		String questionType;
		Integer noOfAnswers;
		String explanationRequired;
		String status;
		String groupName;
		Integer sortId;
		public Ynq(ResultSet rs) throws SQLException {
			questionId = rs.getString(1);
			description = rs.getString(2);
			questionType = rs.getString(3);
			noOfAnswers = rs.getInt(4);
			explanationRequired = rs.getString(5);
			status = rs.getString(6);
			groupName = rs.getString(7);
			sortId = rs.getInt(8);
		}
	}
}
