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
package org.kuali.coeus.common.questionnaire.framework.answer;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.core.api.util.RiceKeyConstants;
import org.kuali.rice.kns.datadictionary.validation.charlevel.NumericValidationPattern;
import org.kuali.rice.kns.datadictionary.validation.charlevel.UTF8AnyCharacterValidationPattern;
import org.kuali.rice.kns.datadictionary.validation.fieldlevel.DateValidationPattern;
import org.kuali.rice.krad.datadictionary.validation.ValidationPattern;
import org.kuali.rice.krad.util.GlobalVariables;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 
 * This class is primarily to validate questionnaire answer format.
 */
public class SaveQuestionnaireAnswerRule implements KcBusinessRule<SaveQuestionnaireAnswerEvent> {

    private static final String QUESTION_TYPE_NUMBER = "3";
    private static final String QUESTION_TYPE_DATE = "4";
    private static final String QUESTION_TYPE_TEXT = "5";
    private static final String ANSWER = "Answer ";
    private static final Map<String, ValidationPattern> VALIDATION_CLASSES;
    
    static {
        final Map<String, ValidationPattern> tempPatterns = new HashMap<String, ValidationPattern>();

        final UTF8AnyCharacterValidationPattern anyCharVal = new UTF8AnyCharacterValidationPattern();
        anyCharVal.setAllowWhitespace(true);

        tempPatterns.put(QUESTION_TYPE_TEXT, anyCharVal);
        tempPatterns.put(QUESTION_TYPE_DATE, new DateValidationPattern());
        tempPatterns.put(QUESTION_TYPE_NUMBER, new NumericValidationPattern());

        VALIDATION_CLASSES = Collections.unmodifiableMap(tempPatterns);
    }
    
    @Override
    public boolean processRules(SaveQuestionnaireAnswerEvent event) {
        boolean valid = true;
        int answerHeaderIndex = 0;
        for (AnswerHeader answerHeader : event.getAnswerHeaders()) {
            int questionIndex = 0;
            for (Answer answer : answerHeader.getAnswers()) {
                String errorKey = event.getFormProperty()+".answerHeaders[" + answerHeaderIndex + "].answers[" + questionIndex + "].answer";
                if (StringUtils.isNotBlank(answer.getAnswer()) && VALIDATION_CLASSES.containsKey(answer.getQuestion().getQuestionTypeId().toString())) {
                    boolean validAttributeFormat = validateAttributeFormat(answer, errorKey, questionIndex);
                    if (!validAttributeFormat) {
                        answerHeader.setShowQuestions("Y");
                    }
                    valid &= validAttributeFormat;
                }
                questionIndex++;
            }
            answerHeaderIndex++;
        }

        return valid;
    }

    /*
     * 
     * This method is to validate the format of questionnaire answer
     * 
     * @param answer
     * 
     * @param errorKey
     * 
     * @return
     */
    private boolean validateAttributeFormat(Answer answer, String errorKey, int questionIndex) {
        boolean valid = true;

        ValidationPattern validationPattern = VALIDATION_CLASSES.get(answer.getQuestion().getQuestionTypeId().toString());

        // if there is no data type matched, then set error ?
        Pattern validationExpression = validationPattern.getRegexPattern();

        String validFormat = getValidFormat(answer.getQuestion().getQuestionTypeId().toString());

        if (validFormat.equals(Constants.DATA_TYPE_STRING) || validFormat.equals(Constants.DATA_TYPE_NUMBER)) {
            if (!validationExpression.matcher(answer.getAnswer()).matches()) {
                GlobalVariables.getMessageMap().putError(errorKey, KeyConstants.ERROR_INVALID_FORMAT_WITH_FORMAT,
                        new String[] { ANSWER + (questionIndex + 1), answer.getAnswer(), validFormat });
                valid = false;
            }
        } else if (validFormat.equals(Constants.DATA_TYPE_DATE)) {
            try {
                DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT);
                dateFormat.setLenient(false);
                dateFormat.parse(answer.getAnswer());
            } catch (ParseException e) {
                GlobalVariables.getMessageMap().putError(errorKey, RiceKeyConstants.ERROR_INVALID_FORMAT,
                        ANSWER + (questionIndex + 1), answer.getAnswer(), validFormat);
                valid = false;
            }
        }

        return valid;
    }

    private String getValidFormat(String dataType) {
        String validFormat = Constants.DATA_TYPE_STRING;
        if (dataType.equalsIgnoreCase(QUESTION_TYPE_NUMBER)) {
            validFormat = Constants.DATA_TYPE_NUMBER;
        } else if (dataType.equalsIgnoreCase(QUESTION_TYPE_DATE)) {
            validFormat = Constants.DATA_TYPE_DATE;
        }
        return validFormat;
    }

}
