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
package org.kuali.kra.award.customdata;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.bo.CustomAttribute;
import org.kuali.kra.bo.CustomAttributeDataType;
import org.kuali.kra.bo.CustomAttributeDocument;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.rules.ResearchDocumentRuleBase;
import org.kuali.kra.service.AwardCommentService;
import org.kuali.kra.service.CustomAttributeService;
import org.kuali.rice.kns.datadictionary.validation.ValidationPattern;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.RiceKeyConstants;

/**
 * This contains methods to process business rules on Award Custom data.
 */
public class AwardCustomDataRuleImpl extends ResearchDocumentRuleBase implements AwardCustomDataRule {
    
    private String CUSTOMATTRIBUTE_VALUES_ID = "customAttributeValues(id";
    private String RIGHT_PAREN = ")";
    private String STRING = "String";
    private String NUMBER = "Number";
    private String DATE = "Date";
    private String DOT_STAR = ".*";

    private static Map<String, String> validationClasses = new HashMap<String, String>();
    static {
        validationClasses.put("String", "org.kuali.rice.kns.datadictionary.validation.charlevel.AnyCharacterValidationPattern");
        validationClasses.put("Date", "org.kuali.rice.kns.datadictionary.validation.fieldlevel.DateValidationPattern");
        validationClasses.put("Number", "org.kuali.rice.kns.datadictionary.validation.charlevel.NumericValidationPattern");
    }
    
    /**
     * @see org.kuali.kra.award.customdata.AwardCustomDataRule#processSaveAwardCustomDataBusinessRules(org.kuali.kra.award.customdata.AwardSaveCustomDataRuleEvent)
     */
    public boolean processSaveAwardCustomDataBusinessRules(AwardSaveCustomDataRuleEvent awardSaveCustomDataRuleEvent) {
        AwardDocument awardDocument = awardSaveCustomDataRuleEvent.getAwardDocument();
        Map<String, CustomAttributeDocument> customAttributeDocuments = awardDocument.getCustomAttributeDocuments();
        boolean valid = true;
        for (Map.Entry<String, CustomAttributeDocument> customAttributeDocumentEntry : customAttributeDocuments.entrySet()) {
            CustomAttributeDocument customAttributeDocument = customAttributeDocumentEntry.getValue();
            CustomAttribute customAttribute = customAttributeDocument.getCustomAttribute();
            //set this value from awardCustomDataList so you have all of the data in one object to be passed to helper method.
            if(awardDocument.getAward().getAwardCustomDataList().size() > 0) {
                int customAttributeId = customAttributeDocument.getCustomAttributeId();
                List<AwardCustomData> awardCustomDataList = awardDocument.getAward().getAwardCustomDataList();
                for(AwardCustomData awardCustomData : awardCustomDataList){
                  if(awardCustomData.getCustomAttributeId() == customAttributeId){  
                      customAttribute.setValue(awardCustomData.getValue());
                      break;
                  }
                }
            }
            String errorKey = CUSTOMATTRIBUTE_VALUES_ID + customAttribute.getId() + RIGHT_PAREN;
            if (StringUtils.isNotBlank(customAttribute.getValue())) {
                valid &= validateAttributeFormat(customAttribute, errorKey);
            }
        }

        return valid;
    }
    
    /**
     * 
     * This method is to validate the format/length of custom attribute
     * @param customAttribute
     * @param errorKey
     * @return
     */
    private boolean validateAttributeFormat(CustomAttribute customAttribute, String errorKey) {

        CustomAttributeDataType customAttributeDataType = customAttribute.getCustomAttributeDataType();
        String attributeValue = customAttribute.getValue();
        if (customAttributeDataType == null && customAttribute.getDataTypeCode() != null) {
            customAttributeDataType = getCustomAttributeService().getCustomAttributeDataType(
                    customAttribute.getDataTypeCode());
        }
        if (customAttributeDataType != null) {
            Integer maxLength = customAttribute.getDataLength();
            if ((maxLength != null) && (maxLength.intValue() < attributeValue.length())) {
                GlobalVariables.getErrorMap().putError(errorKey, RiceKeyConstants.ERROR_MAX_LENGTH, customAttribute.getLabel(), maxLength.toString());
                return false;
            }
            ValidationPattern validationPattern = null;
            try {validationPattern = (ValidationPattern) Class.forName(
                    validationClasses.get(customAttributeDataType.getDescription())).newInstance();
                if (customAttributeDataType.getDescription().equalsIgnoreCase(STRING)) {
                    ((org.kuali.rice.kns.datadictionary.validation.charlevel.AnyCharacterValidationPattern) validationPattern).setAllowWhitespace(true);
                }
            } catch (Exception e) {
                //do nothing
            }
            Pattern validationExpression = validationPattern.getRegexPattern();
            String validFormat = getValidFormat(customAttributeDataType.getDescription());
            if (validationExpression != null && !validationExpression.pattern().equals(DOT_STAR)) {
                if (!validationExpression.matcher(attributeValue).matches()) {
                    GlobalVariables.getErrorMap().putError(errorKey, RiceKeyConstants.ERROR_INVALID_FORMAT, 
                                                                customAttribute.getLabel(), attributeValue, validFormat);
                    return false;
                }
            }
        }
        return true;
    }
    
    /**
     * This method returns the valid format for the data type of the custom attribute.
     * @param dataType
     * @return
     */
    private String getValidFormat(String dataType) {
        String validFormat = Constants.DATA_TYPE_STRING;
        if(dataType.equalsIgnoreCase(NUMBER)) {
            validFormat = Constants.DATA_TYPE_NUMBER;
        }else if(dataType.equalsIgnoreCase(DATE)) {
            validFormat = Constants.DATA_TYPE_DATE;
        }
        return validFormat;
    }
    
    /**
     * 
     * This method is a helper method to retrieve CustomAttributeService.
     * @return
     */
    protected CustomAttributeService getCustomAttributeService() {
        return KraServiceLocator.getService(CustomAttributeService.class);
    }

}
