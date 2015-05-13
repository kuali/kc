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
package org.kuali.coeus.common.questionnaire.impl.question;

import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.common.framework.custom.attr.CustomAttributeService;
import org.kuali.coeus.common.questionnaire.framework.question.Question;
import org.kuali.coeus.common.questionnaire.framework.question.QuestionExplanation;
import org.kuali.coeus.common.questionnaire.framework.question.QuestionMultiChoice;
import org.kuali.coeus.sys.framework.rule.KcMaintenanceDocumentRuleBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.kns.maintenance.Maintainable;
import org.kuali.rice.krad.bo.PersistableBusinessObject;
import org.kuali.rice.krad.exception.ValidationException;
import org.kuali.rice.krad.util.GlobalVariables;


import java.util.Collection;
import java.util.List;

/**
 * This class contains the business rules that are specific to Question.
 */
public class QuestionMaintenanceDocumentRule extends KcMaintenanceDocumentRuleBase {

    private static final Log LOG = LogFactory.getLog(QuestionMaintenanceDocumentRule.class);


    public QuestionMaintenanceDocumentRule() {
        super();
    }

    protected Collection<Class<?>> relationshipDeleteVerificationIgnores() {
        return Lists.<Class<?>>newArrayList(QuestionMultiChoice.class, QuestionExplanation.class);
    }

    /**
     * 
     * This method executes the DataDictionary Validation against the document.
     * It's an exact replica of MaintenanceDocumentRuleBase with the exception of the
     * error path being "document.newMaintainableObject.businessObject" instead of 
     * "document.newMaintainableObject". 
     * TODO: Find a better solution as this duplicates code and is prone to failure if
     *       the rice framework changes, specifically its dataDictionaryValidate method.
     * @param document
     * @return true if it passes DD validation, false otherwise
     */
    @Override
    protected boolean dataDictionaryValidate(MaintenanceDocument document) {
        LOG.debug("MaintenanceDocument validation beginning");

        // explicitly put the errorPath that the dictionaryValidationService requires
        GlobalVariables.getMessageMap().addToErrorPath("document.newMaintainableObject.businessObject");
        try {
            // document must have a newMaintainable object
            Maintainable newMaintainable = document.getNewMaintainableObject();
            if (newMaintainable == null) {
                GlobalVariables.getMessageMap().removeFromErrorPath("document.newMaintainableObject");
                throw new ValidationException("Maintainable object from Maintenance Document '" + document.getDocumentTitle() + "' is null, unable to proceed.");
            }

            // document's newMaintainable must contain an object (ie, not null)
            PersistableBusinessObject businessObject = newMaintainable.getBusinessObject();
            if (businessObject == null) {
                GlobalVariables.getMessageMap().removeFromErrorPath("document.newMaintainableObject.");
                throw new ValidationException("Maintainable's component business object is null.");
            }

            // run required check from maintenance data dictionary
            maintDocDictionaryService.validateMaintenanceRequiredFields(document);

            //check for duplicate entries in collections if necessary
            maintDocDictionaryService.validateMaintainableCollectionsForDuplicateEntries(document);

            // run the DD DictionaryValidation (non-recursive)
            dictionaryValidationService.validateBusinessObject(businessObject, false);

            // do default (ie, mandatory) existence checks
            dictionaryValidationService.validateDefaultExistenceChecks(businessObject);
        } finally {
            // explicitly remove the errorPath we've added
            GlobalVariables.getMessageMap().removeFromErrorPath("document.newMaintainableObject.businessObject");
        }

        LOG.debug("MaintenanceDocument validation ending");
        return true;
    }
    
    /**
     * This method validates the user entered data of a Question when the document is routed.
     * @param maintenanceDocument
     * @return true if valid, false otherwise
     */
    @Override
    protected boolean processCustomRouteDocumentBusinessRules(MaintenanceDocument maintenanceDocument) {
        boolean isValid = true;

        isValid &= validateQuestionUsage(maintenanceDocument);
        isValid &= validateQuestionResponseType(maintenanceDocument);

        return isValid;
    }

    /**
     * This method validates the question status.  The status can not be inactive when a questionnaire is
     * using the question.
     * @param maintenanceDocument - the maintenance document of the question to be validated
     * @return true if all validation has passed, false otherwise
     */
    private boolean validateQuestionUsage(MaintenanceDocument maintenanceDocument) {
        Question question = (Question) maintenanceDocument.getNewMaintainableObject().getDataObject();

        if (!"A".equals(question.getStatus()) && getQuestionService().isQuestionUsed(question.getQuestionSeqId())) {
            GlobalVariables.getMessageMap().putError(Constants.QUESTION_DOCUMENT_FIELD_STATUS,
                    KeyConstants.ERROR_QUESTION_STATUS_IN_USE);
            return false;
        } else {
            return true;
        }
    }

    /**
     * This method validates the question response type and any additional properties related to the response type.
     * @param maintenanceDocument - the maintenance document of the question to be validated
     * @return true if all validation has passed, false otherwise
     */
    private boolean validateQuestionResponseType(MaintenanceDocument maintenanceDocument) {
        boolean isValid = true;

        Question question = (Question) maintenanceDocument.getNewMaintainableObject().getDataObject();

        if (question.getQuestionTypeId() == null) {
            isValid &= false;
            GlobalVariables.getMessageMap().putError(Constants.QUESTION_DOCUMENT_FIELD_QUESTION_TYPE_ID,
                    KeyConstants.ERROR_QUESTION_RESPONSE_TYPE_NOT_SPECIFIED);
        } else {
            switch (question.getQuestionTypeId().intValue()) {
                case (int) Constants.QUESTION_RESPONSE_TYPE_YES_NO:
                    isValid &= validateResponseTypeYesNo(question); 
                    break;
                case (int) Constants.QUESTION_RESPONSE_TYPE_YES_NO_NA:
                    isValid &= validateResponseTypeYesNoNa(question); 
                    break;
                case (int) Constants.QUESTION_RESPONSE_TYPE_NUMBER:
                    isValid &= validateResponseTypeNumber(question); 
                    break;
                case (int) Constants.QUESTION_RESPONSE_TYPE_DATE:
                    isValid &= validateResponseTypeDate(question); 
                    break;
                case (int) Constants.QUESTION_RESPONSE_TYPE_TEXT:
                    isValid &= validateResponseTypeText(question); 
                    break;
                case (int) Constants.QUESTION_RESPONSE_TYPE_LOOKUP:
                    isValid &= validateResponseTypeLookup(question); 
                    break;
                case (int) Constants.QUESTION_RESPONSE_TYPE_MULTIPLE_CHOICE:
                    isValid &= validateResponseTypeMultipleChoice(question);
                    break;
                default:
                    isValid &= false;
                    GlobalVariables.getMessageMap().putError(Constants.QUESTION_DOCUMENT_FIELD_QUESTION_TYPE_ID,
                            KeyConstants.ERROR_QUESTION_RESPONSE_TYPE_INVALID);
                    break;
            }
        }

        return isValid;
    }

    /**
     * This method validates the additional properties of a Yes/No response to a question.
     * Since no additional properties exist this method always returns true.
     * @param question - the question to be validated
     * @return true
     */
    private boolean validateResponseTypeYesNo(Question question) {
        return true;
    }

    /**
     * This method validates the additional properties of a Yes/No/NA response to a question.
     * Since no additional properties exist this method always returns true.
     * @param question - the question to be validated
     * @return true
     */
    private boolean validateResponseTypeYesNoNa(Question question) {
        return true;
    }

    /**
     * This method validates the additional properties of a numeric response to a question.
     * @param question - the question to be validated
     * @return true if all validation has passed, false otherwise
     */
    private boolean validateResponseTypeNumber(Question question) {
        boolean isValid = true;

        isValid &= validateDisplayedAnswers(question);
        isValid &= validateAnswerMaxLengthWithCeiling(question);
        isValid &= validateMaxAnswers(question);
        
        return isValid;
    }

    /**
     * This method validates the additional properties of a date response to a question.
     * @param question - the question to be validated
     * @return true if all validation has passed, false otherwise
     */
    private boolean validateResponseTypeDate(Question question) {
        boolean isValid = true;

        isValid &= validateDisplayedAnswers(question);
        isValid &= validateMaxAnswers(question);
        
        return isValid;
    }

    /**
     * This method validates the additional properties of a text response to a question.
     * @param question - the question to be validated
     * @return true if all validation has passed, false otherwise
     */
    private boolean validateResponseTypeText(Question question) {
        boolean isValid = true;

        isValid &= validateDisplayedAnswers(question);
        isValid &= validateAnswerMaxLengthWithCeiling(question);
        isValid &= validateMaxAnswers(question);
        
        return isValid;
    }

    /**
     * This method validates the additional properties of a lookup response to a question.
     * @param question - the question to be validated
     * @return true if all validation has passed, false otherwise
     */
    private boolean validateResponseTypeLookup(Question question) {
        boolean isValid = true;

        isValid &= validateLookupClass(question);
        isValid &= validateLookupReturn(question);
        isValid &= validateMaxAnswers(question);
        
        return isValid;
    }

    private boolean validateResponseTypeMultipleChoice(Question question) {
        boolean isValid = true;

        isValid &= validateDisplayedAnswers(question);
        isValid &= validateMaxAnswers(question);
        isValid &= validateMultipleChoiceOptions(question);
        return isValid;
    }

    private boolean validateMultipleChoiceOptions(Question question) {
        boolean isValid = true;

        if (CollectionUtils.isEmpty(question.getQuestionMultiChoices()) || question.getDisplayedAnswers() <= 0) {
            isValid = false;
        }

        for (int i = 0; i < question.getQuestionMultiChoices().size(); i++) {
            GlobalVariables.getMessageMap().addToErrorPath("document.newMaintainableObject.businessObject.questionMultiChoices[" + i + "]");
            try {
                dictionaryValidationService.validate(question.getQuestionMultiChoices().get(i));
            } finally {
                GlobalVariables.getMessageMap().removeFromErrorPath("document.newMaintainableObject.businessObject.questionMultiChoices[" + i + "]");
            }
        }

        return isValid;
    }

    /**
     * This method validates the displayedAnswers field.  The field must contain a number greater than zero.
     * @param question - the question to be validated
     * @return true if all validation has passed, false otherwise
     */
    private boolean validateDisplayedAnswers(Question question) {
        if (question.getDisplayedAnswers() != null && question.getDisplayedAnswers() > 0) {
            return true;
        }

        if (Long.valueOf(Constants.QUESTION_RESPONSE_TYPE_TEXT).equals(question.getQuestionTypeId())) {
            GlobalVariables.getMessageMap().putError(Constants.QUESTION_DOCUMENT_FIELD_DISPLAYED_ANSWERS,
                    KeyConstants.ERROR_QUESTION_DISPLAYED_ANSWERS_INVALID_AREAS);
            return false;
        } else if (!Long.valueOf(Constants.QUESTION_RESPONSE_TYPE_MULTIPLE_CHOICE).equals(question.getQuestionTypeId())) {
            GlobalVariables.getMessageMap().putError(Constants.QUESTION_DOCUMENT_FIELD_DISPLAYED_ANSWERS,
                    KeyConstants.ERROR_QUESTION_DISPLAYED_ANSWERS_INVALID_BOXES);
            return false;
        }

        return true;
    }

    /**
     * This method validates the answerMaxLength field.  The field must contain a number greater than zero.
     * @param question - the question to be validated
     * @return true if all validation has passed, false otherwise
     */
    private boolean validateAnswerMaxLength(Question question) {
        if (question.getAnswerMaxLength() != null && question.getAnswerMaxLength() > 0) {
            return true;
        } else {
            GlobalVariables.getMessageMap().putError(Constants.QUESTION_DOCUMENT_FIELD_ANSWER_MAX_LENGTH,
                    KeyConstants.ERROR_QUESTION_ANSWER_MAX_LENGTH_INVALID);
            return false;
        }
    }
    
    public boolean validateAnswerMaxLengthWithCeiling(Question question) {
        if (validateAnswerMaxLength(question)) {
            if (question.getAnswerMaxLength() != null && question.getAnswerMaxLength() <= 2000) {
                return true;
            } else {
                GlobalVariables.getMessageMap().putError(Constants.QUESTION_DOCUMENT_FIELD_ANSWER_MAX_LENGTH,
                        KeyConstants.ERROR_QUESTION_ANSWER_MAX_LENGTH_VALUE_TOO_LARGE);                
                return false;
            }
        } else {
            return false;
        }
    }

    
    /**
     * This method validates the maxAnswers field.  The field must contain a number greater than zero.
     * @param question - the question to be validated
     * @return true if all validation has passed, false otherwise
     */
    private boolean validateMaxAnswers(Question question) {
        boolean isValid = true;

        if (question.getMaxAnswers() == null || question.getMaxAnswers() <= 0) {
            isValid = false;
        }
        if (question.getQuestionTypeId() != Constants.QUESTION_RESPONSE_TYPE_LOOKUP
                && question.getMaxAnswers() != null && question.getDisplayedAnswers() != null
                && question.getMaxAnswers() > question.getDisplayedAnswers()) {
            isValid = false;
        }
        
        if (!isValid) {
            switch (question.getQuestionTypeId().intValue()) {
                case (int) Constants.QUESTION_RESPONSE_TYPE_LOOKUP :
                    GlobalVariables.getMessageMap().putError(Constants.QUESTION_DOCUMENT_FIELD_MAX_ANSWERS,
                            KeyConstants.ERROR_QUESTION_MAX_ANSWERS_INVALID_RETURNS);
                    break;
                case (int) Constants.QUESTION_RESPONSE_TYPE_TEXT :
                    GlobalVariables.getMessageMap().putError(Constants.QUESTION_DOCUMENT_FIELD_MAX_ANSWERS,
                            KeyConstants.ERROR_QUESTION_MAX_ANSWERS_INVALID_ANSWERS_AREAS);
                    break;
                case (int) Constants.QUESTION_RESPONSE_TYPE_MULTIPLE_CHOICE :
                    GlobalVariables.getMessageMap().putError(Constants.QUESTION_DOCUMENT_FIELD_MAX_ANSWERS,
                            KeyConstants.ERROR_QUESTION_MAX_ANSWERS_INVALID_ANSWERS_CHECKBOXES);
                    break;
                default :
                    GlobalVariables.getMessageMap().putError(Constants.QUESTION_DOCUMENT_FIELD_MAX_ANSWERS,
                            KeyConstants.ERROR_QUESTION_MAX_ANSWERS_INVALID_ANSWERS_BOXES);
                    break;
            }
        }
        
        return isValid;
    }

    /**
     * This method validates the lookupClass field.  The field must may not contain null.
     * @param question - the question to be validated
     * @return true if all validation has passed, false otherwise
     */
    private boolean validateLookupClass(Question question) {
        // Force a reload the lookupReturn dropdown list when the lookupClass changes 
        String prevLookupClass = (String) GlobalVariables.getUserSession().retrieveObject(Constants.LOOKUP_CLASS_NAME);
        if (ObjectUtils.equals(question.getLookupClass(), prevLookupClass)) {
            GlobalVariables.getUserSession().removeObject(Constants.LOOKUP_RETURN_FIELDS);
        }
        
        if (question.getLookupClass() != null) {
            return true;
        } else {
            GlobalVariables.getMessageMap().putError(Constants.QUESTION_DOCUMENT_FIELD_LOOKUP_CLASS,
                    KeyConstants.ERROR_QUESTION_LOOKUP_CLASS_NOT_SPECIFIED);
            return false;
        }
    }

    /**
     * This method validates the lookupReturn field.  The field must may not contain null.
     * @param question - the question to be validated
     * @return true if all validation has passed, false otherwise
     */
    private boolean validateLookupReturn(Question question) {
        if (question.getLookupReturn() != null) {
            return validateLookupReturnBasedOnLookupClass(question);
        } else {
            GlobalVariables.getMessageMap().putError(Constants.QUESTION_DOCUMENT_FIELD_LOOKUP_RETURN,
                    KeyConstants.ERROR_QUESTION_LOOKUP_RETURN_NOT_SPECIFIED);
            return false;
        }
    }

    /**
     * This method validates the lookupReturn with the specified lookupClass of the question.
     * (The lookupReturn may be different if JavaScript is disabled and thus the automatic population
     *  of the lookupReturn drop down list has not occurred after the lookupClass has changed.) 
     * @param question - the question to be validated
     * @return true if validation has passed, false otherwise
     */
    @SuppressWarnings("unchecked")
    private boolean validateLookupReturnBasedOnLookupClass(Question question) {
        if (question.getLookupClass() != null) {
            try {
                List<String> lookupReturnClasses = getCustomAttributeService().getLookupReturns(question.getLookupClass());
                for (String lookupReturnClass : lookupReturnClasses) {
                    if (StringUtils.equals(lookupReturnClass, question.getLookupReturn())) {
                        return true;
                    }
                }
                GlobalVariables.getMessageMap().putError(Constants.QUESTION_DOCUMENT_FIELD_LOOKUP_RETURN,
                        KeyConstants.ERROR_QUESTION_LOOKUP_RETURN_INVALID);
                return false;
            }
            catch (Exception e) {
                LOG.info(e.getMessage());
                throw new RuntimeException("QuestionMaintenanceDocumentRule encountered exception", e);
            }
        }
        return true;
    }
    
    private CustomAttributeService getCustomAttributeService() {
        return KcServiceLocator.getService(CustomAttributeService.class);
    }
    
    private QuestionService getQuestionService() {
        return KcServiceLocator.getService(QuestionService.class);
    }

}
