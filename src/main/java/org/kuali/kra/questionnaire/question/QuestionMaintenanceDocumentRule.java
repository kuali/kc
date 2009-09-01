/*
 * Copyright 2006-2009 The Kuali Foundation
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

import java.util.List;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.plexus.util.StringUtils;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.service.CustomAttributeService;
import org.kuali.rice.kns.bo.PersistableBusinessObject;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.kns.exception.ValidationException;
import org.kuali.rice.kns.maintenance.Maintainable;
import org.kuali.rice.kns.maintenance.rules.MaintenanceDocumentRuleBase;
import org.kuali.rice.kns.util.GlobalVariables;

/**
 * This class contains the business rules that are specific to Question.
 */
public class QuestionMaintenanceDocumentRule extends MaintenanceDocumentRuleBase {

    private static final Log LOG = LogFactory.getLog(QuestionMaintenanceDocumentRule.class);

    /**
     * Constructs a QuestionMaintenanceDocumentRule.
     */
    public QuestionMaintenanceDocumentRule() {
        super();
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
        GlobalVariables.getErrorMap().addToErrorPath("document.newMaintainableObject.businessObject");

        // document must have a newMaintainable object
        Maintainable newMaintainable = document.getNewMaintainableObject();
        if (newMaintainable == null) {
            GlobalVariables.getErrorMap().removeFromErrorPath("document.newMaintainableObject");
            throw new ValidationException("Maintainable object from Maintenance Document '" + document.getDocumentTitle() + "' is null, unable to proceed.");
        }

        // document's newMaintainable must contain an object (ie, not null)
        PersistableBusinessObject businessObject = newMaintainable.getBusinessObject();
        if (businessObject == null) {
            GlobalVariables.getErrorMap().removeFromErrorPath("document.newMaintainableObject.");
            throw new ValidationException("Maintainable's component business object is null.");
        }
        
        // run required check from maintenance data dictionary
        maintDocDictionaryService.validateMaintenanceRequiredFields(document);
        
        //check for duplicate entries in collections if necessary
        maintDocDictionaryService.validateMaintainableCollectionsForDuplicateEntries(document);

        // run the DD DictionaryValidation (non-recursive)
        dictionaryValidationService.validateBusinessObject(businessObject,false);

        // do default (ie, mandatory) existence checks
        dictionaryValidationService.validateDefaultExistenceChecks(businessObject);

        // do apc checks
        dictionaryValidationService.validateApcRules(businessObject);
        

        // explicitly remove the errorPath we've added
        GlobalVariables.getErrorMap().removeFromErrorPath("document.newMaintainableObject.businessObject");

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

        isValid &= validateQuestionId(maintenanceDocument);
        isValid &= validateQuestionResponseType(maintenanceDocument);

        return isValid;
    }

    /**
     * 
     * This method validates the question id to ensure it remains unique within the versioning schema.
     * @param maintenanceDocument - the maintenance document of the question to be validated
     * @return true if valid, false otherwise
     */
    private boolean validateQuestionId(MaintenanceDocument maintenanceDocument) {
        Question oldQuestion = (Question) maintenanceDocument.getOldMaintainableObject().getBusinessObject();
        Question newQuestion = (Question) maintenanceDocument.getNewMaintainableObject().getBusinessObject();
        
        if (ObjectUtils.equals(oldQuestion.getQuestionId(), newQuestion.getQuestionId())) {
            return true;
        } else {
            Question existingQuestion = getQuestionService().getQuestionById(newQuestion.getQuestionId());
            if (existingQuestion == null) {
                return true;
            } else {
                GlobalVariables.getErrorMap().putError(Constants.QUESTION_DOCUMENT_FIELD_QUESTION_ID,
                        KeyConstants.ERROR_QUESTION_ID_DUPLICATE);
                return false;
            }
        }
    }

    private QuestionService getQuestionService() {
        return (QuestionService) KraServiceLocator.getService(QuestionService.class);
    }

    /**
     * This method validates the question response type and any additional properties related to the response type.
     * @param maintenanceDocument - the maintenance document of the question to be validated
     * @return true if all validation has passed, false otherwise
     */
    private boolean validateQuestionResponseType(MaintenanceDocument maintenanceDocument) {
        boolean isValid = true;

        Question question = (Question) maintenanceDocument.getNewMaintainableObject().getBusinessObject();

        if (question.getQuestionTypeId() == null) {
            isValid &= false;
            GlobalVariables.getErrorMap().putError(Constants.QUESTION_DOCUMENT_FIELD_QUESTION_TYPE_ID,
                    KeyConstants.ERROR_QUESTION_RESPONSE_TYPE_NOT_SPECIFIED);
        } else {
            switch (question.getQuestionTypeId()) {
                case Constants.QUESTION_RESPONSE_TYPE_YES_NO: 
                    isValid &= validateResponseTypeYesNo(question); 
                    break;
                case Constants.QUESTION_RESPONSE_TYPE_YES_NO_NA: 
                    isValid &= validateResponseTypeYesNoNa(question); 
                    break;
                case Constants.QUESTION_RESPONSE_TYPE_NUMBER: 
                    isValid &= validateResponseTypeNumber(question); 
                    break;
                case Constants.QUESTION_RESPONSE_TYPE_DATE: 
                    isValid &= validateResponseTypeDate(question); 
                    break;
                case Constants.QUESTION_RESPONSE_TYPE_TEXT: 
                    isValid &= validateResponseTypeText(question); 
                    break;
                case Constants.QUESTION_RESPONSE_TYPE_LOOKUP:
                    isValid &= validateResponseTypeLookup(question); 
                    break;
                default:
                    isValid &= false;
                    GlobalVariables.getErrorMap().putError(Constants.QUESTION_DOCUMENT_FIELD_QUESTION_TYPE_ID,
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
        isValid &= validateAnswerMaxLength(question);
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
        isValid &= validateAnswerMaxLength(question);
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

    /**
     * This method validates the displayedAnswers field.  The field must contain a number greater than zero.
     * @param question - the question to be validated
     * @return true if all validation has passed, false otherwise
     */
    private boolean validateDisplayedAnswers(Question question) {
        if (question.getDisplayedAnswers() != null && question.getDisplayedAnswers() > 0) {
            return true;
        } else {
            if (question.getQuestionTypeId() == Constants.QUESTION_RESPONSE_TYPE_TEXT) {
                GlobalVariables.getErrorMap().putError(Constants.QUESTION_DOCUMENT_FIELD_DISPLAYED_ANSWERS,
                        KeyConstants.ERROR_QUESTION_DISPLAYED_ANSWERS_INVALID_AREAS);
            } else {
                GlobalVariables.getErrorMap().putError(Constants.QUESTION_DOCUMENT_FIELD_DISPLAYED_ANSWERS,
                        KeyConstants.ERROR_QUESTION_DISPLAYED_ANSWERS_INVALID_BOXES);
            }
            return false;
        }
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
            GlobalVariables.getErrorMap().putError(Constants.QUESTION_DOCUMENT_FIELD_ANSWER_MAX_LENGTH,
                    KeyConstants.ERROR_QUESTION_ANSWER_MAX_LENGTH_INVALID);
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
            switch (question.getQuestionTypeId()) {
                case Constants.QUESTION_RESPONSE_TYPE_LOOKUP :
                    GlobalVariables.getErrorMap().putError(Constants.QUESTION_DOCUMENT_FIELD_MAX_ANSWERS,
                            KeyConstants.ERROR_QUESTION_MAX_ANSWERS_INVALID_RETURNS);
                    break;
                case Constants.QUESTION_RESPONSE_TYPE_TEXT :
                    GlobalVariables.getErrorMap().putError(Constants.QUESTION_DOCUMENT_FIELD_MAX_ANSWERS,
                            KeyConstants.ERROR_QUESTION_MAX_ANSWERS_INVALID_ANSWERS_AREAS);
                    break;
                default :
                    GlobalVariables.getErrorMap().putError(Constants.QUESTION_DOCUMENT_FIELD_MAX_ANSWERS,
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
            GlobalVariables.getErrorMap().putError(Constants.QUESTION_DOCUMENT_FIELD_LOOKUP_CLASS,
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
            GlobalVariables.getErrorMap().putError(Constants.QUESTION_DOCUMENT_FIELD_LOOKUP_RETURN,
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
                GlobalVariables.getErrorMap().putError(Constants.QUESTION_DOCUMENT_FIELD_LOOKUP_RETURN,
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
        return KraServiceLocator.getService(CustomAttributeService.class);
    }

}