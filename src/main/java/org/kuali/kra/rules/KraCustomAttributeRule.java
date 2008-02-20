/*
 * Copyright 2008 The Kuali Foundation.
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
package org.kuali.kra.rules;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.kuali.RiceKeyConstants;
import org.kuali.core.datadictionary.validation.ValidationPattern;
import org.kuali.core.util.GlobalVariables;
import org.kuali.kra.bo.CustomAttribute;
import org.kuali.kra.bo.CustomAttributeDataType;
import org.kuali.kra.bo.CustomAttributeDocument;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.rule.CustomAttributeRule;
import org.kuali.kra.rule.event.SaveCustomAttributeEvent;
import org.kuali.kra.service.CustomAttributeService;

public class KraCustomAttributeRule extends ResearchDocumentRuleBase implements CustomAttributeRule {


    private static Map<String, String> validationClasses = new HashMap<String, String>();
    static {
        validationClasses.put("String", "org.kuali.core.datadictionary.validation.charlevel.AnyCharacterValidationPattern");
        validationClasses.put("Date", "org.kuali.core.datadictionary.validation.fieldlevel.DateValidationPattern");
        validationClasses.put("Number", "org.kuali.core.datadictionary.validation.charlevel.NumericValidationPattern");
    }

    /**
     * 
     * @see org.kuali.kra.rule.CustomAttributeRule#processCustomAttributeRules(org.kuali.kra.rule.event.SaveCustomAttributeEvent)
     */
    public boolean processCustomAttributeRules(SaveCustomAttributeEvent saveCustomAttributeEvent) {
        ProposalDevelopmentDocument document = (ProposalDevelopmentDocument) saveCustomAttributeEvent.getDocument();
        Map<String, CustomAttributeDocument> customAttributeDocuments = document.getCustomAttributeDocuments();
        boolean valid = true;
        for (Map.Entry<String, CustomAttributeDocument> customAttributeDocumentEntry : customAttributeDocuments.entrySet()) {
            CustomAttributeDocument customAttributeDocument = customAttributeDocumentEntry.getValue();
            Map<String, Object> primaryKeys = new HashMap<String, Object>();
            CustomAttribute customAttribute = customAttributeDocument.getCustomAttribute();
            // CustomAttributeDocValue customAttributeDocValue = (CustomAttributeDocValue)
            // KraServiceLocator.getService(BusinessObjectService.class).findByPrimaryKey(CustomAttributeDocValue.class,
            // primaryKeys);
            String errorKey = "customAttributeValues(id" + customAttribute.getId() + ")";
            if (StringUtils.isNotBlank(customAttribute.getValue())) {
                valid &= validateAttributeFormat(customAttribute, errorKey);
            }
            else if (customAttributeDocument.isRequired()) {
                GlobalVariables.getErrorMap().putError(errorKey, RiceKeyConstants.ERROR_REQUIRED,
                        new String[] { customAttribute.getLabel() });
                valid = false;
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
            customAttributeDataType = KraServiceLocator.getService(CustomAttributeService.class).getCustomAttributeDataType(
                    customAttribute.getDataTypeCode());
        }

        if (customAttributeDataType != null) {
            Integer maxLength = customAttribute.getDataLength();
            if ((maxLength != null) && (maxLength.intValue() < attributeValue.length())) {
                GlobalVariables.getErrorMap().putError(errorKey, RiceKeyConstants.ERROR_MAX_LENGTH,
                        new String[] { customAttribute.getLabel(), maxLength.toString() });
                return false;
            }

            ValidationPattern validationPattern = null;
            try {
                validationPattern = (ValidationPattern) Class.forName(
                        validationClasses.get(customAttributeDataType.getDescription())).newInstance();
                if (customAttributeDataType.getDescription().equalsIgnoreCase("String")) {
                    ((org.kuali.core.datadictionary.validation.charlevel.AnyCharacterValidationPattern) validationPattern)
                            .setAllowWhitespace(true);
                }
            }
            catch (Exception e) {
                // what TODO here?
            }

            // if there is no data type matched, then set error ?
            Pattern validationExpression = validationPattern.getRegexPattern();

            if (validationExpression != null && !validationExpression.pattern().equals(".*")) {

                if (!validationExpression.matcher(attributeValue).matches()) {
                    GlobalVariables.getErrorMap().putError(errorKey, RiceKeyConstants.ERROR_INVALID_FORMAT,
                            new String[] { customAttribute.getLabel(), attributeValue });
                    return false;
                }
            }
        }
        return true;
    }


}
