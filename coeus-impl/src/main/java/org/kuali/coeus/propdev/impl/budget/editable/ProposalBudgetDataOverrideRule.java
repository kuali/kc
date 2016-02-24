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
package org.kuali.coeus.propdev.impl.budget.editable;


import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentService;
import org.kuali.coeus.sys.framework.persistence.KcPersistenceStructureService;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.core.api.util.RiceKeyConstants;
import org.kuali.rice.kns.datadictionary.validation.charlevel.AnyCharacterValidationPattern;
import org.kuali.rice.kns.datadictionary.validation.charlevel.NumericValidationPattern;
import org.kuali.rice.kns.service.DataDictionaryService;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.datadictionary.validation.ValidationPattern;
import org.kuali.rice.krad.util.GlobalVariables;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class ProposalBudgetDataOverrideRule extends KcTransactionalDocumentRuleBase implements BudgetDataOverrideRule {

    private static Map<String, String> validationClasses = new HashMap<String, String>();
    private static final String DATE = "DATE";
    static {
        validationClasses.put("STRING", AnyCharacterValidationPattern.class.getName());
        validationClasses.put("NUMBER", NumericValidationPattern.class.getName());
    }
    private KcPersistenceStructureService kcPersistenceStructureService;
    private DataDictionaryService dataDictionaryService;
    private ProposalDevelopmentService proposalDevelopmentService;
    private DateTimeService dateTimeService;
    private DataObjectService dataObjectService;

    protected KcPersistenceStructureService getKcPersistenceStructureService (){
        if (kcPersistenceStructureService == null)
            kcPersistenceStructureService = KcServiceLocator.getService(KcPersistenceStructureService.class);
        return kcPersistenceStructureService;
    }
    protected DataDictionaryService getDataDictionaryService(){
        if (dataDictionaryService == null)
            dataDictionaryService = KNSServiceLocator.getDataDictionaryService();
        return dataDictionaryService;
    }
    protected ProposalDevelopmentService getProposalDevelopmentService (){
        if (proposalDevelopmentService == null)
            proposalDevelopmentService = KcServiceLocator.getService(ProposalDevelopmentService.class);
        return proposalDevelopmentService;
    }
    protected DateTimeService getDateTimeService (){
        if(dateTimeService == null)
            dateTimeService = KcServiceLocator.getService(DateTimeService.class);
        return dateTimeService;
    }
    public boolean processBudgetDataOverrideRules(BudgetDataOverrideEvent budgetDataOverrideEvent) {
        BudgetChangedData budgetOverriddenData = budgetDataOverrideEvent.getBudgetChangedData();
        boolean valid = true;
        DataDictionaryService dataDictionaryService = getDataDictionaryService();
        String overriddenValue = budgetOverriddenData.getChangedValue();
        KcPersistenceStructureService kraPersistenceStructureService = getKcPersistenceStructureService();
        Map<String, String> columnToAttributesMap = kraPersistenceStructureService.getDBColumnToObjectAttributeMap(Budget.class);
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
     * @param budgetOverriddenData
     * @param dataDictionaryService
     * @return
     */
    
    private boolean validateAttributeFormat(BudgetChangedData budgetOverriddenData, DataDictionaryService dataDictionaryService) {
        ProposalDevelopmentService proposalDevelopmentService = getProposalDevelopmentService();
        DateTimeService dateTimeService = getDateTimeService();

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
        
        DevelopmentProposal developmentProposal = getDataObjectService().find(DevelopmentProposal.class, budgetOverriddenData.getProposalNumber());
        
        Budget finalBudget = developmentProposal.getFinalBudget();
        
        Object currentValue = proposalDevelopmentService.getBudgetFieldValueFromDBColumnName(
        		finalBudget.getParentDocumentKey(), budgetOverriddenData.getColumnName());
        if (currentValue instanceof ScaleTwoDecimal) {
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
    
	protected DataObjectService getDataObjectService() {
		if (dataObjectService == null) {
			dataObjectService = KcServiceLocator.getService(DataObjectService.class);
		}
		return dataObjectService;
	}
	
	public void setDataObjectService(DataObjectService dataObjectService) {
		this.dataObjectService = dataObjectService;
	}
}
