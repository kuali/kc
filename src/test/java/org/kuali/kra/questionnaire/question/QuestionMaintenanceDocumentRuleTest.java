/*
 * Copyright 2006-2008 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
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
package org.kuali.kra.questionnaire.question;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.maintenance.MaintenanceRuleTestBase;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.kns.util.ErrorMap;
import org.kuali.rice.kns.util.ErrorMessage;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.TypedArrayList;

public class QuestionMaintenanceDocumentRuleTest extends MaintenanceRuleTestBase {

    private QuestionMaintenanceDocumentRule rule = null;
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        rule = new QuestionMaintenanceDocumentRule();
    }

    @After
    public void tearDown() throws Exception {
        rule = null;
        super.tearDown();
    }

    @Test
    public void testDataDictionaryValidateErrorPaths() throws Exception {
        Question question = new Question();
        MaintenanceDocument questionMaintenanceDocument = newMaintDoc(question);
        rule.dataDictionaryValidate(questionMaintenanceDocument);
        
        ErrorMap errorMap = GlobalVariables.getErrorMap();
        assertEquals(4, errorMap.getErrorCount());
        
        assertError("document.newMaintainableObject.businessObject.questionId");
        assertError("document.newMaintainableObject.businessObject.question");
        assertError("document.newMaintainableObject.businessObject.status");
        assertError("document.newMaintainableObject.businessObject.categoryTypeCode");
    }
    
    /**
     *  Tests valid yes/no question type 
     */
    @Test
    public void testValidYesNoQuestion() throws Exception {
        Question question = new Question();
        question.setQuestionTypeId(Constants.QUESTION_RESPONSE_TYPE_YES_NO);
        MaintenanceDocument questionMaintenanceDocument = newMaintDoc(question, question);
        assertTrue(rule.processCustomRouteDocumentBusinessRules(questionMaintenanceDocument));

        ErrorMap errorMap = GlobalVariables.getErrorMap();
        assertEquals(0, errorMap.getErrorCount());
    }
    
    /**
     *  Tests valid yes/no/na question type 
     */
    @Test
    public void testValidYesNoNaQuestion() throws Exception {
        Question question = new Question();
        question.setQuestionTypeId(Constants.QUESTION_RESPONSE_TYPE_YES_NO_NA);
        MaintenanceDocument questionMaintenanceDocument = newMaintDoc(question, question);
        assertTrue(rule.processCustomRouteDocumentBusinessRules(questionMaintenanceDocument));

        ErrorMap errorMap = GlobalVariables.getErrorMap();
        assertEquals(0, errorMap.getErrorCount());
    }
    
    /**
     *  Tests valid number question type 
     */
    @Test
    public void testValidNumberQuestion() throws Exception {
        Question question = new Question();
        question.setQuestionTypeId(Constants.QUESTION_RESPONSE_TYPE_NUMBER);
        question.setDisplayedAnswers(1);
        question.setAnswerMaxLength(10);
        question.setMaxAnswers(1);
        MaintenanceDocument questionMaintenanceDocument = newMaintDoc(question, question);
        assertTrue(rule.processCustomRouteDocumentBusinessRules(questionMaintenanceDocument));

        ErrorMap errorMap = GlobalVariables.getErrorMap();
        assertEquals(0, errorMap.getErrorCount());
    }
    
    /**
     *  Tests number question type with missing data 
     */
    @Test
    public void testNumberMissingData() throws Exception {
        Question question = new Question();
        question.setQuestionTypeId(Constants.QUESTION_RESPONSE_TYPE_NUMBER);
        MaintenanceDocument questionMaintenanceDocument = newMaintDoc(question, question);
        assertFalse(rule.processCustomRouteDocumentBusinessRules(questionMaintenanceDocument));

        ErrorMap errorMap = GlobalVariables.getErrorMap();
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
        question.setDisplayedAnswers(1);
        question.setMaxAnswers(1);
        MaintenanceDocument questionMaintenanceDocument = newMaintDoc(question, question);
        assertTrue(rule.processCustomRouteDocumentBusinessRules(questionMaintenanceDocument));

        ErrorMap errorMap = GlobalVariables.getErrorMap();
        assertEquals(0, errorMap.getErrorCount());
    }
    
    /**
     *  Tests date question type with missing data 
     */
    @Test
    public void testDateMissingData() throws Exception {
        Question question = new Question();
        question.setQuestionTypeId(Constants.QUESTION_RESPONSE_TYPE_DATE);
        MaintenanceDocument questionMaintenanceDocument = newMaintDoc(question, question);
        assertFalse(rule.processCustomRouteDocumentBusinessRules(questionMaintenanceDocument));

        ErrorMap errorMap = GlobalVariables.getErrorMap();
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
        question.setDisplayedAnswers(1);
        question.setAnswerMaxLength(10);
        question.setMaxAnswers(1);
        MaintenanceDocument questionMaintenanceDocument = newMaintDoc(question, question);
        assertTrue(rule.processCustomRouteDocumentBusinessRules(questionMaintenanceDocument));

        ErrorMap errorMap = GlobalVariables.getErrorMap();
        assertEquals(0, errorMap.getErrorCount());
    }
    
    /**
     *  Tests text question type with missing data 
     */
    @Test
    public void testTextMissingData() throws Exception {
        Question question = new Question();
        question.setQuestionTypeId(Constants.QUESTION_RESPONSE_TYPE_TEXT);
        MaintenanceDocument questionMaintenanceDocument = newMaintDoc(question, question);
        assertFalse(rule.processCustomRouteDocumentBusinessRules(questionMaintenanceDocument));

        ErrorMap errorMap = GlobalVariables.getErrorMap();
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
        question.setLookupClass("org.kuali.kra.proposaldevelopment.bo.AbstractType");
        question.setLookupReturn("abstractTypeCode");
        question.setMaxAnswers(1);
        MaintenanceDocument questionMaintenanceDocument = newMaintDoc(question, question);
        assertTrue(rule.processCustomRouteDocumentBusinessRules(questionMaintenanceDocument));

        ErrorMap errorMap = GlobalVariables.getErrorMap();
        assertEquals(0, errorMap.getErrorCount());
    }

    /**
     *  Tests missing lookupClass, lookupReturn and maxAnswers 
     */
    @Test
    public void testLookupMissingData() throws Exception {
        Question question = new Question();
        question.setQuestionId(1);
        question.setQuestionTypeId(Constants.QUESTION_RESPONSE_TYPE_LOOKUP);
        MaintenanceDocument questionMaintenanceDocument = newMaintDoc(question, question);
        assertFalse(rule.processCustomRouteDocumentBusinessRules(questionMaintenanceDocument));

        ErrorMap errorMap = GlobalVariables.getErrorMap();
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
        question.setQuestionId(1);
        question.setQuestionTypeId(Constants.QUESTION_RESPONSE_TYPE_LOOKUP);
        question.setLookupClass("org.kuali.kra.proposaldevelopment.bo.AbstractType");
        question.setLookupReturn("eomProcessFlag");
        question.setMaxAnswers(1);
        MaintenanceDocument questionMaintenanceDocument = newMaintDoc(question, question);
        assertFalse(rule.processCustomRouteDocumentBusinessRules(questionMaintenanceDocument));

        ErrorMap errorMap = GlobalVariables.getErrorMap();
        assertEquals(1, errorMap.getErrorCount());
        
        assertError(Constants.QUESTION_DOCUMENT_FIELD_LOOKUP_RETURN, KeyConstants.ERROR_QUESTION_LOOKUP_RETURN_INVALID);
    }
    
    /**
     *  Tests missing question response type 
     */
    @Test
    public void testMissingQuestionResponseType() throws Exception {
        Question question = new Question();
        question.setQuestionId(1);
        MaintenanceDocument questionMaintenanceDocument = newMaintDoc(question, question);
        assertFalse(rule.processCustomRouteDocumentBusinessRules(questionMaintenanceDocument));

        ErrorMap errorMap = GlobalVariables.getErrorMap();
        assertEquals(1, errorMap.getErrorCount());
        
        assertError(Constants.QUESTION_DOCUMENT_FIELD_QUESTION_TYPE_ID, KeyConstants.ERROR_QUESTION_RESPONSE_TYPE_NOT_SPECIFIED);
    }
    
    /**
     *  Tests invalid question response type 
     */
    @Test
    public void testInvalidQuestionResponseType() throws Exception {
        Question question = new Question();
        question.setQuestionId(1);
        question.setQuestionTypeId(999);
        MaintenanceDocument questionMaintenanceDocument = newMaintDoc(question, question);
        assertFalse(rule.processCustomRouteDocumentBusinessRules(questionMaintenanceDocument));

        ErrorMap errorMap = GlobalVariables.getErrorMap();
        assertEquals(1, errorMap.getErrorCount());
        
        assertError(Constants.QUESTION_DOCUMENT_FIELD_QUESTION_TYPE_ID, KeyConstants.ERROR_QUESTION_RESPONSE_TYPE_INVALID);
    }
    
    /**
     * Assert an error.  The Error Map should have one error with the given
     * property key.
     * @param propertyKey
     */
    protected void assertError(String propertyKey) {
        TypedArrayList errors = GlobalVariables.getErrorMap().getMessages(propertyKey);
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
        TypedArrayList errors = GlobalVariables.getErrorMap().getMessages(propertyKey);
        assertNotNull(errors);
        assertTrue(errors.size() == 1);
        
        ErrorMessage message = (ErrorMessage) errors.get(0);
        assertNotNull(message);
        assertEquals(message.getErrorKey(), errorKey);
    }

}
