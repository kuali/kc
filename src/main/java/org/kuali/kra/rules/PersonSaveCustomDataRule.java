/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.rules;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.CustomAttribute;
import org.kuali.kra.bo.CustomAttributeDataType;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.bo.PersonCustomData;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.lookup.keyvalue.ArgValueLookupValuesFinder;
import org.kuali.kra.rule.BusinessRuleInterface;
import org.kuali.kra.rule.event.PersonSaveCustomDataEvent;
import org.kuali.kra.service.KcPersonService;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.core.api.util.RiceKeyConstants;
import org.kuali.rice.kns.datadictionary.validation.charlevel.AnyCharacterValidationPattern;
import org.kuali.rice.kns.datadictionary.validation.charlevel.NumericValidationPattern;
import org.kuali.rice.kns.datadictionary.validation.fieldlevel.DateValidationPattern;
import org.kuali.rice.krad.datadictionary.validation.ValidationPattern;

public class PersonSaveCustomDataRule extends PersonCustomDataRuleBase implements BusinessRuleInterface<PersonSaveCustomDataEvent> {

    private static final String STRING = "String";
    private static final String NUMBER = "Number";
    private static final String DATE = "Date";

    private static final String DATE_REGEX = "(0?[1-9]|1[012])/(0?[1-9]|[12][0-9]|3[01])/(19|2[0-9])[0-9]{2}";
    private static final String KC_PERSON_LOOKUP_CLASS = "org.kuali.kra.bo.KcPerson";
    private static final String ARG_VALUE_LOOKUP_CLASS = "org.kuali.kra.bo.ArgValueLookup";
    
    private static Map<String, ValidationPattern> validationClasses = new HashMap<String, ValidationPattern>();
    static {
        AnyCharacterValidationPattern anyCharacterValidationPattern = new AnyCharacterValidationPattern();
        anyCharacterValidationPattern.setAllowWhitespace(true);
        validationClasses.put(STRING, anyCharacterValidationPattern);
        
        NumericValidationPattern numericValidationPattern = new NumericValidationPattern();
        validationClasses.put(NUMBER, numericValidationPattern);
        
        DateValidationPattern dateValidationPattern = new DateValidationPattern();
        validationClasses.put(DATE, dateValidationPattern);
    }
    
    @Override
    public boolean processRules(PersonSaveCustomDataEvent event) {
        boolean rulePassed = true;

        int i = 0;
        for (PersonCustomData personCustomData : event.getPersonCustomDataList()) {
            String errorKey = event.getErrorPathPrefix() + Constants.LEFT_SQUARE_BRACKET + i++ + Constants.RIGHT_SQUARE_BRACKET + ".value";
            if (StringUtils.isNotBlank(personCustomData.getValue())) {
                rulePassed &= validateFormat(personCustomData, errorKey);
            }
        }

        return rulePassed;
    }

    private boolean validateFormat(PersonCustomData personCustomData, String errorKey) {
        boolean isValid = true;
        
        String value = personCustomData.getValue();
        
        CustomAttribute customAttribute = personCustomData.getCustomAttribute();
        CustomAttributeDataType customAttributeDataType = customAttribute.getCustomAttributeDataType();
        String validFormat = getValidFormat(customAttributeDataType.getDescription());
        
        isValid &= validateLength(value, customAttribute, errorKey);
        isValid &= validateType(value, customAttribute, errorKey, validFormat);
        isValid &= validateLookup(value, customAttribute, errorKey, validFormat);
        
        return isValid;
    }
    
    private boolean validateLength(String value, CustomAttribute customAttribute, String errorKey) {
        boolean isValid = true;
        
        Integer maxLength = customAttribute.getDataLength();
        if ((maxLength != null) && (maxLength.intValue() < value.length())) {
            isValid = false;
            reportError(errorKey, RiceKeyConstants.ERROR_MAX_LENGTH, customAttribute.getLabel(), maxLength.toString());
        }
        
        return isValid;
    }
    
    private boolean validateType(String value, CustomAttribute customAttribute, String errorKey, String validFormat) {
        boolean isValid = true;
        
        CustomAttributeDataType customAttributeDataType = customAttribute.getCustomAttributeDataType();
        ValidationPattern validationPattern = validationClasses.get(customAttributeDataType.getDescription());

        if (customAttributeDataType.getDescription().equalsIgnoreCase(STRING) || customAttributeDataType.getDescription().equalsIgnoreCase(NUMBER)) {
            Pattern validationExpression = validationPattern.getRegexPattern();
        
            if (!validationExpression.matcher(value).matches()) {
                isValid = false;
                reportError(errorKey, RiceKeyConstants.ERROR_INVALID_FORMAT, customAttribute.getLabel(), value, validFormat);
            }
        } else if (customAttributeDataType.getDescription().equalsIgnoreCase(DATE)) {
            if (!value.matches(DATE_REGEX)) {
                isValid = false;
                reportError(errorKey, RiceKeyConstants.ERROR_INVALID_FORMAT, customAttribute.getLabel(), value, validFormat);
            } else {
                if (!StringUtils.isEmpty(value)) {
                    try {
                        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT);
                        dateFormat.setLenient(false);
                        dateFormat.parse(value);
                    } catch (ParseException e) {
                        isValid = false;
                        reportError(errorKey, KeyConstants.ERROR_DATE, value, customAttribute.getLabel());
                    }
                }
            }
        }
        
        return isValid;
    }
    
    private boolean validateLookup(String value, CustomAttribute customAttribute, String errorKey, String validFormat) {
        boolean isValid = true;
            
        String lookupClass = customAttribute.getLookupClass();
        
        if (lookupClass != null) {
            if (lookupClass.equals(KC_PERSON_LOOKUP_CLASS)) {
                isValid = validateKcPersonLookup(value, customAttribute, errorKey, validFormat);
            } else if (lookupClass.equals(ARG_VALUE_LOOKUP_CLASS)) {
                isValid = validateArgValueLookup(value, customAttribute, errorKey, validFormat);
            } else {    
                isValid = validateClassLookup(value, customAttribute, errorKey, validFormat);
            }
        }
        
        return isValid;
    }
    
    private boolean validateKcPersonLookup(String value, CustomAttribute customAttribute, String errorKey, String validFormat) {
        boolean isValid = true;
        
        CustomAttributeDataType customAttributeDataType = customAttribute.getCustomAttributeDataType();
        
        if (customAttributeDataType.getDescription().equalsIgnoreCase(STRING) && customAttribute.getLookupReturn().equals("userName")) {
            KcPerson customPerson = getKcPersonService().getKcPersonByUserName(customAttribute.getValue());
            if (customPerson == null) {
                isValid = false;
                reportError(errorKey, RiceKeyConstants.ERROR_EXISTENCE, customAttribute.getLabel(), value, validFormat);
            }
        }
        
        return isValid;
    }
    
    private boolean validateArgValueLookup(String value, CustomAttribute customAttribute, String errorKey, String validFormat) {
        boolean isValid = true;
        
        ArgValueLookupValuesFinder finder = new  ArgValueLookupValuesFinder();
        finder.setArgName(customAttribute.getLookupReturn());
        boolean keyValueFound = false;
        for (KeyValue element : finder.getKeyValues()) {
            String label = element.getValue().toLowerCase();
            if (label.equals(value.toLowerCase())) {
                keyValueFound = true;
                break;
            }      
        }
        if (!keyValueFound) {
            isValid = false;
            reportError(errorKey, RiceKeyConstants.ERROR_EXISTENCE, customAttribute.getLabel(), value, validFormat);
        }
        
        return isValid;
    }
    
    private boolean validateClassLookup(String value, CustomAttribute customAttribute, String errorKey, String validFormat) {
        boolean isValid = true;
        
        CustomAttributeDataType customAttributeDataType = customAttribute.getCustomAttributeDataType();
        String lookupClass = customAttribute.getLookupClass();
        
        Class<?> boClass = null;
        try {
            boClass = Class.forName(lookupClass);
        } catch (ClassNotFoundException cnfE) {
            // Do nothing, checked on input
        }

        if (isInvalid(boClass, keyValue(customAttribute.getLookupReturn(), customAttribute.getValue()))) {
            validFormat = getValidFormat(customAttributeDataType.getDescription());
            isValid = false;
            reportError(errorKey, RiceKeyConstants.ERROR_EXISTENCE, customAttribute.getLabel(), value, validFormat);
        }
        
        return isValid;
    }

    private String getValidFormat(String dataType) {
        String validFormat = Constants.DATA_TYPE_STRING;
        if (dataType.equalsIgnoreCase(NUMBER)) {
            validFormat = Constants.DATA_TYPE_NUMBER;
        } else if (dataType.equalsIgnoreCase(DATE)) {
            validFormat = Constants.DATA_TYPE_DATE;
        }
        return validFormat;
    }

    public KcPersonService getKcPersonService() {
        return KraServiceLocator.getService(KcPersonService.class);    
    }

}