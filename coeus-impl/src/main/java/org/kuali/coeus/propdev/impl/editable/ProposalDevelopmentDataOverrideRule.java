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
package org.kuali.coeus.propdev.impl.editable;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentService;
import org.kuali.coeus.sys.framework.persistence.KcPersistenceStructureService;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.rice.core.api.CoreApiServiceLocator;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.core.api.util.RiceKeyConstants;
import org.kuali.rice.kns.datadictionary.validation.charlevel.AnyCharacterValidationPattern;
import org.kuali.rice.kns.datadictionary.validation.charlevel.NumericValidationPattern;
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
        validationClasses.put("STRING", AnyCharacterValidationPattern.class.getName());
        validationClasses.put("NUMBER", NumericValidationPattern.class.getName());
    }

    public boolean processProposalDataOverrideRules(ProposalDataOverrideEvent proposalDataOverrideEvent) {
        ProposalChangedData proposalOverriddenData = proposalDataOverrideEvent.getProposalChangedData();
        DevelopmentProposal developmentProposal = proposalDataOverrideEvent.getDevelopmentProposal();
        boolean valid = true;
        DataDictionaryService dataDictionaryService = (DataDictionaryService) KNSServiceLocator.getDataDictionaryService();
        
        String overriddenValue = proposalOverriddenData.getChangedValue();
        KcPersistenceStructureService kraPersistenceStructureService = KcServiceLocator.getService(KcPersistenceStructureService.class);
        Map<String, String> columnToAttributesMap = kraPersistenceStructureService.getDBColumnToObjectAttributeMap(DevelopmentProposal.class);

        
        if (StringUtils.isEmpty(proposalOverriddenData.getColumnName())) {
            GlobalVariables.getMessageMap().putError("newProposalChangedData.columnName", KeyConstants.ERROR_NO_FIELD_TO_EDIT);
            return false;
        }

        String overriddenName = dataDictionaryService.getAttributeErrorLabel(DevelopmentProposal.class, columnToAttributesMap.get(proposalOverriddenData.getColumnName()));
        Boolean isRequiredField = dataDictionaryService.isAttributeRequired(DevelopmentProposal.class, columnToAttributesMap.get(proposalOverriddenData.getColumnName()));

        if(proposalOverriddenData != null && StringUtils.isNotEmpty(proposalOverriddenData.getChangedValue())) {
            valid &= validateAttributeFormat(proposalOverriddenData, developmentProposal, dataDictionaryService);
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
     * @param proposalOverriddenData
     * @param dataDictionaryService
     * @return boolean
     */
    private boolean validateAttributeFormat(ProposalChangedData proposalOverriddenData, DevelopmentProposal developmentProposal, DataDictionaryService dataDictionaryService) {
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

        try {
            Object currentValue = PropertyUtils.getNestedProperty(developmentProposal,proposalOverriddenData.getAttributeName());
            String currentValueStr = (currentValue != null) ? currentValue.toString() : "";
            if(DATE.equalsIgnoreCase( proposalOverriddenData.getEditableColumn().getDataType()) && currentValue != null) {
                    currentValueStr = dateTimeService.toString((Date) currentValue, "MM/dd/yyyy");
            }

            if(StringUtils.isNotEmpty(currentValueStr) && currentValueStr.equalsIgnoreCase(overriddenValue)) {
                GlobalVariables.getMessageMap().putError(Constants.PROPOSALDATA_CHANGED_VAL_KEY, KeyConstants.PROPOSAL_DATA_OVERRIDE_SAME_VALUE,
                        new String[] { proposalOverriddenData.getEditableColumn().getColumnLabel(), (proposalOverriddenData.getDisplayValue() != null) ? proposalOverriddenData.getDisplayValue() : overriddenValue});
                return false;
            }
        }catch (Exception e) {
            throw new RuntimeException("Error retrieving " + proposalOverriddenData.getAttributeName() + " from the proposal", e);
        }
        
        return true;
    }


}
