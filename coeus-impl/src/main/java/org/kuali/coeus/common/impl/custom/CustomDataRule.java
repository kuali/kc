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
package org.kuali.coeus.common.impl.custom;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.custom.DocumentCustomData;
import org.kuali.coeus.common.framework.custom.arg.ArgValueLookup;
import org.kuali.coeus.common.framework.custom.attr.CustomAttribute;
import org.kuali.coeus.common.framework.custom.attr.CustomAttributeDataType;
import org.kuali.coeus.common.framework.custom.attr.CustomAttributeDocument;
import org.kuali.coeus.common.framework.custom.attr.CustomAttributeService;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.coeus.common.framework.custom.SaveCustomDataEvent;
import org.kuali.rice.core.api.util.RiceKeyConstants;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.kns.datadictionary.validation.charlevel.NumericValidationPattern;
import org.kuali.rice.kns.datadictionary.validation.charlevel.UTF8AnyCharacterValidationPattern;
import org.kuali.rice.krad.datadictionary.validation.ValidationPattern;
import org.kuali.rice.krad.service.LegacyDataAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.*;

/**
 * Validates the rules for a Custom Attribute save action.
 */
public class CustomDataRule extends KcTransactionalDocumentRuleBase implements KcBusinessRule<SaveCustomDataEvent> {

    private static final String STRING = "String";
    private static final String DATE = "Date";
    private static final String NUMBER = "Number";
    private static String CUSTOMATTRIBUTE_ERROR_START = "[";
    private static String CUSTOMATTRIBUTE_ERROR_END = "].value";   
    private static DateFormat dateFormat;
    private static final String DATE_REGEX = "(0?[1-9]|1[012])/(0?[1-9]|[12][0-9]|3[01])/(19|2[0-9])[0-9]{2}";
    private static Map<String, ValidationPattern> VALIDATION_CLASSES = new HashMap<String, ValidationPattern>();

    @Autowired
    @Qualifier("legacyDataAdapter")
    private LegacyDataAdapter legacyDataAdapter;

    private CustomAttributeService customAttributeService;
    
    static {
        final Map<String, ValidationPattern> tempPatterns = new HashMap<String, ValidationPattern>();
        
        final UTF8AnyCharacterValidationPattern anyCharVal = new UTF8AnyCharacterValidationPattern();
        anyCharVal.setAllowWhitespace(true);
        
        tempPatterns.put(STRING, anyCharVal);
        tempPatterns.put(NUMBER, new NumericValidationPattern());
        
        VALIDATION_CLASSES = Collections.unmodifiableMap(tempPatterns);
        
        dateFormat = DateFormat.getDateInstance(DateFormat.SHORT);
        dateFormat.setLenient(false);
    }
    
    @Override
    public boolean processRules(SaveCustomDataEvent event) {
        boolean rulePassed = true;
        Map<String, CustomAttributeDocument> customAttributeDocuments = event.getCustomAttributeDocuments();
        for (Map.Entry<String, CustomAttributeDocument> customAttributeDocumentEntry : customAttributeDocuments.entrySet()) {

            CustomAttributeDocument customAttributeDocument = customAttributeDocumentEntry.getValue();

            CustomAttribute customAttribute = customAttributeDocument.getCustomAttribute();

            List<? extends DocumentCustomData> customDataList = event.getCustomDataList();

            if (isDocEnrouteAndDoesNotContainCustomAttribute(customAttribute.getId(), customDataList,
                    event.getDocument().getDocumentHeader().getWorkflowDocument())) {
                continue;
            }
            int index = 0;
            for (DocumentCustomData data : customDataList) {
                if (data.getCustomAttributeId() == customAttribute.getId().longValue()) {
                    customAttribute.setValue(data.getValue());
                    break;
                }
                index++;
            }
            String errorKey = event.getErrorPathPrefix() + CUSTOMATTRIBUTE_ERROR_START + index + CUSTOMATTRIBUTE_ERROR_END;
            if (StringUtils.isNotBlank(customAttribute.getValue())) {
                CustomAttributeDataType customAttributeDataType = customAttribute.getCustomAttributeDataType();
                if (customAttributeDataType == null && customAttribute.getDataTypeCode() != null) {
                    customAttributeDataType = getCustomAttributeService().getCustomAttributeDataType(customAttribute.getDataTypeCode());
                }

                if (customAttributeDataType != null) {
                    rulePassed &= validateAttributeFormat(customAttribute, errorKey, event);
                }
            }
            if (event.isValidateRequiredFields() && customAttributeDocument.isRequired() && StringUtils.isBlank(customAttribute.getValue())) {
                event.reportError(customAttribute, errorKey, RiceKeyConstants.ERROR_REQUIRED, 
                        customAttribute.getLabel(),customAttribute.getValue(), getValidFormat(customAttribute.getCustomAttributeDataType().getDescription()));
                 rulePassed = false;
            }
            
        }

        return rulePassed;
    }

    protected boolean isDocEnrouteAndDoesNotContainCustomAttribute(Long customAttributeId, List<? extends DocumentCustomData> customDataList, WorkflowDocument workflowDocument) {
        if (!workflowDocument.isSaved() && !workflowDocument.isInitiated()){
            for (DocumentCustomData data : customDataList) {
                if (data.getCustomAttributeId().equals(customAttributeId)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }


    /**
     * 
     * This method is to validate the format/length of custom attribute
     * @param customAttribute
     * @param errorKey
     * @return
     */
    @SuppressWarnings("unchecked")
    protected boolean validateAttributeFormat(CustomAttribute customAttribute, String errorKey, SaveCustomDataEvent event) {

        boolean isValid = true;
        CustomAttributeDataType customAttributeDataType = customAttribute.getCustomAttributeDataType();
        String attributeValue = customAttribute.getValue();
        if (customAttributeDataType == null && customAttribute.getDataTypeCode() != null) {
            customAttributeDataType = getCustomAttributeService().getCustomAttributeDataType(
                    customAttribute.getDataTypeCode());
        }
        if (customAttributeDataType != null) {
            Integer maxLength = customAttribute.getDataLength();
            if ((maxLength != null) && (maxLength.intValue() < attributeValue.length())) {
                event.reportError(customAttribute, errorKey, RiceKeyConstants.ERROR_MAX_LENGTH, customAttribute.getLabel(), maxLength.toString());
                isValid = false;
            }
                
            ValidationPattern validationPattern = VALIDATION_CLASSES.get(customAttributeDataType.getDescription());
            String validFormat = getValidFormat(customAttributeDataType.getDescription());
            if (validationPattern != null) {
                if (!validationPattern.matches(attributeValue)) {
                    event.reportError(customAttribute, errorKey, KeyConstants.ERROR_INVALID_FORMAT_WITH_FORMAT, customAttribute.getLabel(), attributeValue, validFormat);
                    isValid = false;
                }
            } else if (DATE.equals(customAttributeDataType.getDescription())) {
                if (attributeValue != null && !attributeValue.matches(DATE_REGEX)) {
                    event.reportError(customAttribute, errorKey, RiceKeyConstants.ERROR_INVALID_FORMAT, customAttribute.getLabel(), attributeValue, validFormat);
                    isValid = false;
                } else {
                    try {
                        dateFormat.parse(attributeValue);
                    }
                    catch (ParseException e) {
                        event.reportError(customAttribute, errorKey, KeyConstants.ERROR_DATE, attributeValue, customAttribute.getLabel());
                        isValid = false;
                    }
                }
            }
            
            // validate BO data against the database contents 
            String lookupClass = customAttribute.getLookupClass();
           
            if (lookupClass != null && lookupClass.equals(KcPerson.class.getName()))
            {
                if (customAttribute.getDataTypeCode().equals("1") && customAttribute.getLookupReturn().equals("userName"))
                {
                    validFormat = getValidFormat(customAttributeDataType.getDescription());
                    KcPersonService kps = getKcPersonService();
                    KcPerson customPerson = kps.getKcPersonByUserName(customAttribute.getValue());
                    if (customPerson == null)
                    {
                        event.reportError(customAttribute, errorKey, RiceKeyConstants.ERROR_EXISTENCE, 
                            customAttribute.getLabel(), attributeValue, validFormat);
                        isValid = false;
                    }
                }
            } else if (lookupClass != null && lookupClass.equals(ArgValueLookup.class.getName()))
            {
                Collection<ArgValueLookup> searchResults = getLegacyDataAdapter().findAll(ArgValueLookup.class);
                boolean argValueValid = false;
                for (ArgValueLookup argValueLookup : searchResults) {
                    if (customAttribute.getValue().equals(argValueLookup.getValue())){
                        argValueValid = true;
                        break;
                    }
                }
                if (!argValueValid) {
                    validFormat = getValidFormat(customAttributeDataType.getDescription());
                    event.reportError(customAttribute, errorKey, RiceKeyConstants.ERROR_EXISTENCE,
                            customAttribute.getLabel(), attributeValue, validFormat);
                    isValid = false;
                }
            }
            else if (lookupClass != null)
            {    
                Class boClass = null;
                try
                {
                    boClass = Class.forName(lookupClass);
                }
                catch (ClassNotFoundException cnfE) {/* Do nothing, checked on input */ }

                if (isInvalid(boClass, keyValue(customAttribute.getLookupReturn(), customAttribute.getValue() ) ) )         
                {
                    validFormat = getValidFormat(customAttributeDataType.getDescription());
                    event.reportError(customAttribute, errorKey, RiceKeyConstants.ERROR_EXISTENCE, 
                             customAttribute.getLabel(), attributeValue, validFormat);
                    return false;
                }
            }                 
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
    
    /**
     * Gets the Custom Attribute Service.
     * @return the Custom Attribute Service
     */
    public CustomAttributeService getCustomAttributeService() {
        if (customAttributeService == null) {
            customAttributeService = KcServiceLocator.getService(CustomAttributeService.class);
        }
        return customAttributeService;
    }
    
    /**
     * Sets the Custom Attribute Service.
     * @param customAttributeService the Custom Attribute Service
     */
    public void setCustomAttributeService(CustomAttributeService customAttributeService) {
        this.customAttributeService = customAttributeService;
    }

    /**
     * 
     * This method is a helper method to retrieve KcPersonService.
     * @return
     */
    protected KcPersonService getKcPersonService() {
        return KcServiceLocator.getService(KcPersonService.class);
    }

    public LegacyDataAdapter getLegacyDataAdapter() {
        if (legacyDataAdapter == null) {
            legacyDataAdapter = KcServiceLocator.getService(LegacyDataAdapter.class);
        }
        return legacyDataAdapter;
    }

    public void setLegacyDataAdapter(LegacyDataAdapter legacyDataAdapter) {
        this.legacyDataAdapter = legacyDataAdapter;
    }
}
