/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.proposaldevelopment.rules;


import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.persistence.KcPersistenceStructureService;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.versions.BudgetVersionOverview;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.budget.bo.BudgetChangedData;
import org.kuali.kra.proposaldevelopment.rule.BudgetDataOverrideRule;
import org.kuali.kra.proposaldevelopment.rule.event.BudgetDataOverrideEvent;
import org.kuali.kra.proposaldevelopment.service.ProposalDevelopmentService;
import org.kuali.rice.core.api.CoreApiServiceLocator;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.core.api.util.RiceKeyConstants;
import org.kuali.rice.kns.service.DataDictionaryService;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.krad.datadictionary.validation.ValidationPattern;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;

import java.text.ParseException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class ProposalBudgetDataOverrideRule extends KcTransactionalDocumentRuleBase implements BudgetDataOverrideRule {

    private static Map<String, String> validationClasses = new HashMap<String, String>();
    private static final String DATE = "DATE";
    static {
        validationClasses.put("STRING", "org.kuali.rice.kns.datadictionary.validation.charlevel.AnyCharacterValidationPattern");
        validationClasses.put("NUMBER", "org.kuali.rice.kns.datadictionary.validation.charlevel.NumericValidationPattern");
    }
    public boolean processBudgetDataOverrideRules(BudgetDataOverrideEvent budgetDataOverrideEvent) {
        BudgetChangedData budgetOverriddenData = budgetDataOverrideEvent.getBudgetChangedData();
        boolean valid = true;
        DataDictionaryService dataDictionaryService = (DataDictionaryService) KNSServiceLocator.getDataDictionaryService();
        String overriddenValue = budgetOverriddenData.getChangedValue();
        KcPersistenceStructureService kraPersistenceStructureService = KcServiceLocator.getService(KcPersistenceStructureService.class);
        Map<String, String> columnToAttributesMap = kraPersistenceStructureService.getDBColumnToObjectAttributeMap(BudgetVersionOverview.class);
        String overriddenName = dataDictionaryService.getAttributeErrorLabel(Budget.class, columnToAttributesMap.get(budgetOverriddenData.getColumnName()));
        Boolean isRequiredField = dataDictionaryService.isAttributeRequired(Budget.class, columnToAttributesMap.get(budgetOverriddenData.getColumnName()));
        
        if (StringUtils.isEmpty(budgetOverriddenData.getColumnName())) {
            valid = false;
            GlobalVariables.getMessageMap().putError("newBudgetChangedData.columnName", KeyConstants.ERROR_NO_FIELD_TO_EDIT);
        }
        
        if(budgetOverriddenData != null && StringUtils.isNotEmpty(budgetOverriddenData.getChangedValue())) {
            valid &= validateAttributeFormat(budgetOverriddenData, dataDictionaryService);
        }
        
        if (isRequiredField && StringUtils.isEmpty(overriddenValue)){
            valid = false;
            GlobalVariables.getMessageMap().putError("newBudgetChangedData.changedValue", RiceKeyConstants.ERROR_REQUIRED, overriddenName);
        }
        
        if(budgetOverriddenData != null && StringUtils.isNotEmpty(budgetOverriddenData.getComments())) {
            int commentsMaxLength = dataDictionaryService.getAttributeMaxLength(BudgetChangedData.class, "comments");
            String commentsLabel = dataDictionaryService.getAttributeLabel(BudgetChangedData.class, "comments");
            if (commentsMaxLength < budgetOverriddenData.getComments().length()) {
                GlobalVariables.getMessageMap().putError(Constants.BUDGETDATA_COMMENTS_KEY, RiceKeyConstants.ERROR_MAX_LENGTH,
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
    
    private boolean validateAttributeFormat(BudgetChangedData budgetOverriddenData, DataDictionaryService dataDictionaryService) {
        ProposalDevelopmentService proposalDevelopmentService = KcServiceLocator.getService(ProposalDevelopmentService.class);
        DateTimeService dateTimeService = CoreApiServiceLocator.getDateTimeService();

        String overriddenValue = budgetOverriddenData.getChangedValue();
        String changedValueLabel = dataDictionaryService.getAttributeLabel(BudgetChangedData.class, "changedValue");

        String dataType = null;
        Integer maxLength = -1;
        
        if(budgetOverriddenData.getEditableColumn() != null) {
            dataType = budgetOverriddenData.getEditableColumn().getDataType();
            maxLength = budgetOverriddenData.getEditableColumn().getDataLength();
        }
        
        ValidationPattern validationPattern = null;

        
        if( DATE.equalsIgnoreCase(dataType) ) {
            try {
                dateTimeService.convertToDate(overriddenValue);
            }
            catch (ParseException e) {
                GlobalVariables.getMessageMap().putError(Constants.BUDGETDATA_CHANGED_VAL_KEY, RiceKeyConstants.ERROR_INVALID_FORMAT,
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
                    throw new RuntimeException("Error in instantiating a ValidationPatternClass for Budget Data Overriding", e);
                }
            } else {
                //throw error
            }
            
            if(validationPattern != null) {
                Pattern validationExpression = validationPattern.getRegexPattern();
                if (validationExpression != null && !validationExpression.pattern().equals(".*")) {
                    if (!validationExpression.matcher(overriddenValue).matches()) {
                        GlobalVariables.getMessageMap().putError(Constants.BUDGETDATA_CHANGED_VAL_KEY, RiceKeyConstants.ERROR_INVALID_FORMAT,
                                new String[] { changedValueLabel, overriddenValue });
                        return false;
                    }
                }
            }
        }
        
      
        
        BusinessObjectService boService = KcServiceLocator.getService(BusinessObjectService.class);
        Map<String, String> documentMap = new HashMap<String, String>();
        documentMap.put("proposalNumber", budgetOverriddenData.getProposalNumber());
        DevelopmentProposal developmentProposal = boService.findByPrimaryKey(DevelopmentProposal.class, documentMap);
        
        Map budgetMap = new HashMap();
        budgetMap.put("parentDocumentKey", developmentProposal.getProposalDocument().getDocumentNumber());
        BudgetDocument budgetDocument = null;
        Collection<BudgetDocument> budgetDocuments = boService.findMatching(BudgetDocument.class, budgetMap);
        for (BudgetDocument document : budgetDocuments) {
            if (document.getBudget().getFinalVersionFlag()) {
                budgetDocument = document;
                break;
            }
        }
        
        Object currentValue = proposalDevelopmentService.getBudgetFieldValueFromDBColumnName(
                budgetDocument.getDocumentNumber(), budgetOverriddenData.getColumnName());
        if (currentValue instanceof BudgetDecimal) {
            try {
                Double overriddenValueToInt = Double.parseDouble(overriddenValue); 
            } catch (Exception e) {
                GlobalVariables.getMessageMap().putError(Constants.BUDGETDATA_CHANGED_VAL_KEY, RiceKeyConstants.ERROR_NUMBER,
                        new String[] { changedValueLabel, overriddenValue });
                return false;
            } 
        }
        
        if ((maxLength != null) && (maxLength.intValue() < overriddenValue.length())) {
            if (!(currentValue instanceof Boolean)) {
                GlobalVariables.getMessageMap().putError(Constants.BUDGETDATA_CHANGED_VAL_KEY, RiceKeyConstants.ERROR_MAX_LENGTH,
                        new String[] { changedValueLabel, maxLength.toString() });
                return false;
            }
        }
        
        String currentValueStr = (currentValue != null) ? currentValue.toString() : "";
        
        if(DATE.equalsIgnoreCase( budgetOverriddenData.getEditableColumn().getDataType()) && currentValue != null) {
                currentValueStr = dateTimeService.toString((Date) currentValue, "MM/dd/yyyy");
        }
        
        if (StringUtils.isNotEmpty(currentValueStr) && currentValueStr.equalsIgnoreCase(overriddenValue)) {
            if (!(currentValue instanceof Boolean)) {
                GlobalVariables.getMessageMap().putError(
                        Constants.BUDGETDATA_CHANGED_VAL_KEY,
                        KeyConstants.BUDGET_DATA_OVERRIDE_SAME_VALUE,
                        new String[] {
                                budgetOverriddenData.getEditableColumn().getColumnLabel(),
                                (budgetOverriddenData.getDisplayValue() != null) ? budgetOverriddenData.getDisplayValue()
                                        : overriddenValue });
                return false;
            }

        }
        
        return true;
    }
}
