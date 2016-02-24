/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
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
package org.kuali.coeus.common.questionnaire.impl.question;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.questionnaire.framework.question.Question;
import org.kuali.coeus.propdev.impl.abstrct.AbstractType;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.maintenance.MaintenanceRuleTestBase;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.krad.util.ErrorMessage;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.MessageMap;

import java.util.List;
import static org.junit.Assert.*;
public class QuestionMaintenanceDocumentRuleTest extends MaintenanceRuleTestBase {

    private QuestionMaintenanceDocumentRule rule = null;
    
    @Before
    public void setUp() throws Exception {
        rule = new QuestionMaintenanceDocumentRule();
    }

    @After
    public void tearDown() throws Exception {
        rule = null;
    }

    @Test
    public void testDataDictionaryValidateErrorPaths() throws Exception {
        Question question = new Question();
        MaintenanceDocument questionMaintenanceDocument = newMaintDoc(question);
        rule.dataDictionaryValidate(questionMaintenanceDocument);
        
        MessageMap errorMap = GlobalVariables.getMessageMap();
        assertEquals(4, errorMap.getErrorCount());
        
        assertError("document.newMaintainableObject.businessObject.question");
        assertError("document.newMaintainableObject.businessObject.status");
        assertError("document.newMaintainableObject.businessObject.categoryTypeCode");
        assertError("document.newMaintainableObject.businessObject.questionTypeId");
    }
    
    /**
     *  Tests valid yes/no question type 
     */
    @Test
    public void testValidYesNoQuestion() throws Exception {
        Question question = new Question();
        question.setQuestionTypeId(Constants.QUESTION_RESPONSE_TYPE_YES_NO);
        question.setStatus("A");
        MaintenanceDocument questionMaintenanceDocument = newMaintDoc(question, question);
        assertTrue(rule.processCustomRouteDocumentBusinessRules(questionMaintenanceDocument));

        MessageMap errorMap = GlobalVariables.getMessageMap();
        assertEquals(0, errorMap.getErrorCount());
    }
    
    /**
     *  Tests valid yes/no/na question type 
     */
    @Test
    public void testValidYesNoNaQuestion() throws Exception {
        Question question = new Question();
        question.setQuestionTypeId(Constants.QUESTION_RESPONSE_TYPE_YES_NO_NA);
        question.setStatus("A");
        MaintenanceDocument questionMaintenanceDocument = newMaintDoc(question, question);
        assertTrue(rule.processCustomRouteDocumentBusinessRules(questionMaintenanceDocument));

        MessageMap errorMap = GlobalVariables.getMessageMap();
        assertEquals(0, errorMap.getErrorCount());
    }
    
    /**
     *  Tests valid number question type 
     */
    @Test
    public void testValidNumberQuestion() throws Exception {
        Question question = new Question();
        question.setQuestionTypeId(Constants.QUESTION_RESPONSE_TYPE_NUMBER);
        question.setStatus("A");
        question.setDisplayedAnswers(1);
        question.setAnswerMaxLength(10);
        question.setMaxAnswers(1);
        MaintenanceDocument questionMaintenanceDocument = newMaintDoc(question, question);
        assertTrue(rule.processCustomRouteDocumentBusinessRules(questionMaintenanceDocument));

        MessageMap errorMap = GlobalVariables.getMessageMap();
        assertEquals(0, errorMap.getErrorCount());
    }
    
    /**
     *  Tests number question type with missing data 
     */
    @Test
    public void testNumberMissingData() throws Exception {
        Question question = new Question();
        question.setQuestionTypeId(Constants.QUESTION_RESPONSE_TYPE_NUMBER);
        question.setStatus("A");
        MaintenanceDocument questionMaintenanceDocument = newMaintDoc(question, question);
        assertFalse(rule.processCustomRouteDocumentBusinessRules(questionMaintenanceDocument));

        MessageMap errorMap = GlobalVariables.getMessageMap();
        assertEquals(3, errorMap.getErrorCount());

        assertError(Constants.QUESTION_DOCUMENT_FIELD_DISPLAYED_ANSWERS, KeyConstants.ERROR_QUESTION_DISPLAYED_ANSWERS_INVALID_BOXES);
        assertError(Constants.QUESTION_DOCUMENT_FIELD_ANSWER_MAX_LENGTH, KeyConstants.ERROR_QUESTION_ANSWER_MAX_LENGTH_INVALID);
        assertError(Constants.QUESTION_DOCUMENT_FIELD_MAX_ANSWERS, KeyConstants.ERROR_QUESTION_MAX_ANSWERS_INVALID_ANSWERS_BOXES);
    }
    
    /**
     *  Tests valid date question type 
     */
    @Test
    public void testValidDateQuestion() throws Exception {
        Question question = new Question();
        question.setQuestionTypeId(Constants.QUESTION_RESPONSE_TYPE_DATE);
        question.setStatus("A");
        question.setDisplayedAnswers(1);
        question.setMaxAnswers(1);
        MaintenanceDocument questionMaintenanceDocument = newMaintDoc(question, question);
        assertTrue(rule.processCustomRouteDocumentBusinessRules(questionMaintenanceDocument));

        MessageMap errorMap = GlobalVariables.getMessageMap();
        assertEquals(0, errorMap.getErrorCount());
    }
    
    /**
     *  Tests date question type with missing data 
     */
    @Test
    public void testDateMissingData() throws Exception {
        Question question = new Question();
        question.setQuestionTypeId(Constants.QUESTION_RESPONSE_TYPE_DATE);
        question.setStatus("A");
        MaintenanceDocument questionMaintenanceDocument = newMaintDoc(question, question);
        assertFalse(rule.processCustomRouteDocumentBusinessRules(questionMaintenanceDocument));

        MessageMap errorMap = GlobalVariables.getMessageMap();
        assertEquals(2, errorMap.getErrorCount());

        assertError(Constants.QUESTION_DOCUMENT_FIELD_DISPLAYED_ANSWERS, KeyConstants.ERROR_QUESTION_DISPLAYED_ANSWERS_INVALID_BOXES);
        assertError(Constants.QUESTION_DOCUMENT_FIELD_MAX_ANSWERS, KeyConstants.ERROR_QUESTION_MAX_ANSWERS_INVALID_ANSWERS_BOXES);
    }
    
    /**
     *  Tests valid text question type 
     */
    @Test
    public void testValidTextQuestion() throws Exception {
        Question question = new Question();
        question.setQuestionTypeId(Constants.QUESTION_RESPONSE_TYPE_TEXT);
        question.setStatus("A");
        question.setDisplayedAnswers(1);
        question.setAnswerMaxLength(10);
        question.setMaxAnswers(1);
        MaintenanceDocument questionMaintenanceDocument = newMaintDoc(question, question);
        assertTrue(rule.processCustomRouteDocumentBusinessRules(questionMaintenanceDocument));

        MessageMap errorMap = GlobalVariables.getMessageMap();
        assertEquals(0, errorMap.getErrorCount());
    }
    
    /**
     *  Tests text question type with missing data 
     */
    @Test
    public void testTextMissingData() throws Exception {
        Question question = new Question();
        question.setQuestionTypeId(Constants.QUESTION_RESPONSE_TYPE_TEXT);
        question.setStatus("A");
        MaintenanceDocument questionMaintenanceDocument = newMaintDoc(question, question);
        assertFalse(rule.processCustomRouteDocumentBusinessRules(questionMaintenanceDocument));

        MessageMap errorMap = GlobalVariables.getMessageMap();
        assertEquals(3, errorMap.getErrorCount());

        assertError(Constants.QUESTION_DOCUMENT_FIELD_DISPLAYED_ANSWERS, KeyConstants.ERROR_QUESTION_DISPLAYED_ANSWERS_INVALID_AREAS);
        assertError(Constants.QUESTION_DOCUMENT_FIELD_ANSWER_MAX_LENGTH, KeyConstants.ERROR_QUESTION_ANSWER_MAX_LENGTH_INVALID);
        assertError(Constants.QUESTION_DOCUMENT_FIELD_MAX_ANSWERS, KeyConstants.ERROR_QUESTION_MAX_ANSWERS_INVALID_ANSWERS_AREAS);
    }
    
    /**
     *  Tests valid lookup question type 
     */
    @Test
    public void testValidLookupQuestion() throws Exception {
        Question question = new Question();
        question.setQuestionTypeId(Constants.QUESTION_RESPONSE_TYPE_LOOKUP);
        question.setStatus("A");
        question.setLookupClass(AbstractType.class.getName());
        question.setLookupReturn("code");
        question.setMaxAnswers(1);
        MaintenanceDocument questionMaintenanceDocument = newMaintDoc(question, question);
        assertTrue(rule.processCustomRouteDocumentBusinessRules(questionMaintenanceDocument));

        MessageMap errorMap = GlobalVariables.getMessageMap();
        assertEquals(0, errorMap.getErrorCount());
    }

    /**
     *  Tests missing lookupClass, lookupReturn and maxAnswers 
     */
    @Test
    public void testLookupMissingData() throws Exception {
        Question question = new Question();
        question.setQuestionSeqId(1);
        question.setQuestionTypeId(Constants.QUESTION_RESPONSE_TYPE_LOOKUP);
        question.setStatus("A");
        MaintenanceDocument questionMaintenanceDocument = newMaintDoc(question, question);
        assertFalse(rule.processCustomRouteDocumentBusinessRules(questionMaintenanceDocument));

        MessageMap errorMap = GlobalVariables.getMessageMap();
        assertEquals(3, errorMap.getErrorCount());
        
        assertError(Constants.QUESTION_DOCUMENT_FIELD_LOOKUP_CLASS, KeyConstants.ERROR_QUESTION_LOOKUP_CLASS_NOT_SPECIFIED);
        assertError(Constants.QUESTION_DOCUMENT_FIELD_LOOKUP_RETURN, KeyConstants.ERROR_QUESTION_LOOKUP_RETURN_NOT_SPECIFIED);
        assertError(Constants.QUESTION_DOCUMENT_FIELD_MAX_ANSWERS, KeyConstants.ERROR_QUESTION_MAX_ANSWERS_INVALID_RETURNS);
    }

    /**
     *  Tests invalid lookupReturn that does not go with the lookupClass 
     */
    @Test
    public void testInvalidLookupReturn() throws Exception {
        Question question = new Question();
        question.setQuestionSeqId(1);
        question.setStatus("A");
        question.setQuestionTypeId(Constants.QUESTION_RESPONSE_TYPE_LOOKUP);
        question.setLookupClass(AbstractType.class.getName());
        question.setLookupReturn("eomProcessFlag");
        question.setMaxAnswers(1);
        MaintenanceDocument questionMaintenanceDocument = newMaintDoc(question, question);
        assertFalse(rule.processCustomRouteDocumentBusinessRules(questionMaintenanceDocument));

        MessageMap errorMap = GlobalVariables.getMessageMap();
        assertEquals(1, errorMap.getErrorCount());
        
        assertError(Constants.QUESTION_DOCUMENT_FIELD_LOOKUP_RETURN, KeyConstants.ERROR_QUESTION_LOOKUP_RETURN_INVALID);
    }
    
    /**
     *  Tests missing question response type 
     */
    @Test
    public void testMissingQuestionResponseType() throws Exception {
        Question question = new Question();
        question.setQuestionSeqId(1);
        question.setStatus("A");
        MaintenanceDocument questionMaintenanceDocument = newMaintDoc(question, question);
        assertFalse(rule.processCustomRouteDocumentBusinessRules(questionMaintenanceDocument));

        MessageMap errorMap = GlobalVariables.getMessageMap();
        assertEquals(1, errorMap.getErrorCount());
        
        assertError(Constants.QUESTION_DOCUMENT_FIELD_QUESTION_TYPE_ID, KeyConstants.ERROR_QUESTION_RESPONSE_TYPE_NOT_SPECIFIED);
    }
    
    /**
     *  Tests invalid question response type 
     */
    @Test
    public void testInvalidQuestionResponseType() throws Exception {
        Question question = new Question();
        question.setStatus("A");
        question.setQuestionSeqId(1);
        question.setQuestionTypeId(999L);
        MaintenanceDocument questionMaintenanceDocument = newMaintDoc(question, question);
        assertFalse(rule.processCustomRouteDocumentBusinessRules(questionMaintenanceDocument));

        MessageMap errorMap = GlobalVariables.getMessageMap();
        assertEquals(1, errorMap.getErrorCount());
        
        assertError(Constants.QUESTION_DOCUMENT_FIELD_QUESTION_TYPE_ID, KeyConstants.ERROR_QUESTION_RESPONSE_TYPE_INVALID);
    }
    
    /**
     * Assert an error.  The Error Map should have one error with the given
     * property key.
     * @param propertyKey
     */
    protected void assertError(String propertyKey) {
        List errors = GlobalVariables.getMessageMap().getMessages(propertyKey);
        assertNotNull(errors);
        assertTrue(errors.size() == 1);
        
        ErrorMessage message = (ErrorMessage) errors.get(0);
        assertNotNull(message);
    }

    /**
     * Assert an error.  The Error Map should have one error with the given
     * property key and error key.
     * @param propertyKey
     * @param errorKey
     */
    protected void assertError(String propertyKey, String errorKey) {
        List errors = GlobalVariables.getMessageMap().getMessages(propertyKey);
        assertNotNull(errors);
        assertTrue(errors.size() == 1);
        
        ErrorMessage message = (ErrorMessage) errors.get(0);
        assertNotNull(message);
        assertEquals(message.getErrorKey(), errorKey);
    }

}
