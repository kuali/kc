/*
 * Copyright 2005-2013 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.proposaldevelopment.rules;

import org.apache.commons.lang.StringUtils;
import org.kuali.coeus.sys.framework.persistence.KcPersistenceStructureService;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.bo.ProposalChangedData;
import org.kuali.kra.proposaldevelopment.bo.ProposalOverview;
import org.kuali.kra.proposaldevelopment.rule.ProposalDataOverrideRule;
import org.kuali.kra.proposaldevelopment.rule.event.ProposalDataOverrideEvent;
import org.kuali.kra.proposaldevelopment.service.ProposalDevelopmentService;
import org.kuali.rice.core.api.CoreApiServiceLocator;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.core.api.util.RiceKeyConstants;
import org.kuali.rice.kns.service.DataDictionaryService;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.krad.datadictionary.validation.ValidationPattern;
import org.kuali.rice.krad.util.GlobalVariables;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Business Rule to determine if it valid for the user to oevrride the
 * given Proposal Development Document data.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class ProposalDevelopmentDataOverrideRule extends KcTransactionalDocumentRuleBase implements ProposalDataOverrideRule {

    private static Map<String, String> validationClasses = new HashMap<String, String>();
    private static final String DATE="DATE";
    static {
        validationClasses.put("STRING", "org.kuali.rice.kns.datadictionary.validation.charlevel.AnyCharacterValidationPattern");
        validationClasses.put("NUMBER", "org.kuali.rice.kns.datadictionary.validation.charlevel.NumericValidationPattern");
    }

    public boolean processProposalDataOverrideRules(ProposalDataOverrideEvent proposalDataOverrideEvent) {
        ProposalChangedData proposalOverriddenData = proposalDataOverrideEvent.getProposalChangedData();
        boolean valid = true;
        DataDictionaryService dataDictionaryService = (DataDictionaryService) KNSServiceLocator.getDataDictionaryService();
        
        String overriddenValue = proposalOverriddenData.getChangedValue();
        KcPersistenceStructureService kraPersistenceStructureService = KcServiceLocator.getService(KcPersistenceStructureService.class);
        Map<String, String> columnToAttributesMap = kraPersistenceStructureService.getDBColumnToObjectAttributeMap(ProposalOverview.class);
        String overriddenName = dataDictionaryService.getAttributeErrorLabel(DevelopmentProposal.class, columnToAttributesMap.get(proposalOverriddenData.getColumnName()));
        Boolean isRequiredField = dataDictionaryService.isAttributeRequired(DevelopmentProposal.class, columnToAttributesMap.get(proposalOverriddenData.getColumnName()));
        
        if (StringUtils.isEmpty(proposalOverriddenData.getColumnName())) {
            valid = false;
            GlobalVariables.getMessageMap().putError("newProposalChangedData.columnName", KeyConstants.ERROR_NO_FIELD_TO_EDIT);
        }
        
        if(proposalOverriddenData != null && StringUtils.isNotEmpty(proposalOverriddenData.getChangedValue())) {
            valid &= validateAttributeFormat(proposalOverriddenData, dataDictionaryService);
        }
        
        if (isRequiredField && StringUtils.isEmpty(overriddenValue)){
            valid = false;
            GlobalVariables.getMessageMap().putError("newProposalChangedData.changedValue", RiceKeyConstants.ERROR_REQUIRED, overriddenName);
        }
        
        
        if(proposalOverriddenData != null && StringUtils.isNotEmpty(proposalOverriddenData.getComments())) {
            int commentsMaxLength = dataDictionaryService.getAttributeMaxLength(ProposalChangedData.class, "comments");
            String commentsLabel = dataDictionaryService.getAttributeLabel(ProposalChangedData.class, "comments");
            if (commentsMaxLength < proposalOverriddenData.getComments().length()) {
                GlobalVariables.getMessageMap().putError(Constants.PROPOSALDATA_COMMENTS_KEY, RiceKeyConstants.ERROR_MAX_LENGTH,
                        new String[] { commentsLabel, commentsMaxLength+""});
                return false;
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
    private boolean validateAttributeFormat(ProposalChangedData proposalOverriddenData, DataDictionaryService dataDictionaryService) {
        ProposalDevelopmentService proposalDevelopmentService = KcServiceLocator.getService(ProposalDevelopmentService.class);
        DateTimeService dateTimeService = CoreApiServiceLocator.getDateTimeService();
        
        String overriddenValue = proposalOverriddenData.getChangedValue();
        String changedValueLabel = dataDictionaryService.getAttributeLabel(ProposalChangedData.class, "changedValue");
        
        String dataType = null;
        Integer maxLength = -1;
        
        if(proposalOverriddenData.getEditableColumn() != null) {
            dataType = proposalOverriddenData.getEditableColumn().getDataType();
            maxLength = proposalOverriddenData.getEditableColumn().getDataLength();
        }
        
        ValidationPattern validationPattern = null;

        
        if( DATE.equalsIgnoreCase(dataType) ) {
            try {
                dateTimeService.convertToDate(overriddenValue);
            }
            catch (ParseException e) {
                GlobalVariables.getMessageMap().putError(Constants.PROPOSALDATA_CHANGED_VAL_KEY, RiceKeyConstants.ERROR_INVALID_FORMAT,
                        new String[] { changedValueLabel, overriddenValue });
                return false;
            }
        } else {
            String validationClassName = validationClasses.get(dataType);
            if(StringUtils.isNotEmpty(validationClassName)) {
                try {
                    validationPattern = (ValidationPattern) Class.forName(validationClasses.get(dataType)).newInstance();
                    if (dataType.equalsIgnoreCase("STRING")) {
                        ((org.kuali.rice.kns.datadictionary.validation.charlevel.AnyCharacterValidationPattern) validationPattern)
                        .setAllowWhitespace(true);
                    }
                }
                catch (Exception e) {  
                    throw new RuntimeException("Error in instantiating a ValidationPatternClass for Proposal Data Overriding", e);
                }
            } else {
                //throw error
            }

            if(validationPattern != null) {
                Pattern validationExpression = validationPattern.getRegexPattern();
                if (validationExpression != null && !validationExpression.pattern().equals(".*")) {
                    if (!validationExpression.matcher(overriddenValue).matches()) {
                        GlobalVariables.getMessageMap().putError(Constants.PROPOSALDATA_CHANGED_VAL_KEY, RiceKeyConstants.ERROR_INVALID_FORMAT,
                                new String[] { changedValueLabel, overriddenValue });
                        return false;
                    }
                }
            }
        }
        
        if ((maxLength != null) && (maxLength.intValue() < overriddenValue.length())) {
            GlobalVariables.getMessageMap().putError(Constants.PROPOSALDATA_CHANGED_VAL_KEY, RiceKeyConstants.ERROR_MAX_LENGTH,
                    new String[] { changedValueLabel, maxLength.toString() });
            return false;
        }

        Object currentValue = proposalDevelopmentService.getProposalFieldValueFromDBColumnName(proposalOverriddenData.getProposalNumber(), proposalOverriddenData.getColumnName());
        String currentValueStr = (currentValue != null) ? currentValue.toString() : "";
        if(DATE.equalsIgnoreCase( proposalOverriddenData.getEditableColumn().getDataType()) && currentValue != null) {
                currentValueStr = dateTimeService.toString((Date) currentValue, "MM/dd/yyyy");
        }
        
        if(StringUtils.isNotEmpty(currentValueStr) && currentValueStr.equalsIgnoreCase(overriddenValue)) {
            GlobalVariables.getMessageMap().putError(Constants.PROPOSALDATA_CHANGED_VAL_KEY, KeyConstants.PROPOSAL_DATA_OVERRIDE_SAME_VALUE, 
                    new String[] { proposalOverriddenData.getEditableColumn().getColumnLabel(), (proposalOverriddenData.getDisplayValue() != null) ? proposalOverriddenData.getDisplayValue() : overriddenValue});
            return false;
        }
        
        return true;
    }


}
