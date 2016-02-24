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

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.sys.framework.persistence.KcPersistenceStructureService;
import org.kuali.coeus.sys.framework.rule.KcMaintenanceDocumentRuleBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.rice.core.api.util.RiceKeyConstants;
import org.kuali.rice.kns.datadictionary.validation.charlevel.AlphaNumericValidationPattern;
import org.kuali.rice.kns.datadictionary.validation.charlevel.AlphaValidationPattern;
import org.kuali.rice.kns.datadictionary.validation.charlevel.AnyCharacterValidationPattern;
import org.kuali.rice.kns.datadictionary.validation.charlevel.NumericValidationPattern;
import org.kuali.rice.kns.datadictionary.validation.fieldlevel.DateValidationPattern;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.kns.service.DataDictionaryService;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.krad.datadictionary.AttributeDefinition;
import org.kuali.rice.krad.datadictionary.DataDictionaryEntry;
import org.kuali.rice.krad.datadictionary.validation.ValidationPattern;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.HashMap;
import java.util.Map;

public class ProposalColumnsToAlterMaintenanceDocumentRule extends KcMaintenanceDocumentRuleBase {
    
    private static Map<String, String> validationClassesMap = new HashMap<String, String>();
    static {
        validationClassesMap.put(AnyCharacterValidationPattern.class.getName(), "STRING");
        validationClassesMap.put(AlphaNumericValidationPattern.class.getName(), "STRING");
        validationClassesMap.put(AlphaValidationPattern.class.getName(), "STRING");
        validationClassesMap.put(DateValidationPattern.class.getName(), "DATE");
        validationClassesMap.put(NumericValidationPattern.class.getName(), "NUMBER");
    }
 

    public ProposalColumnsToAlterMaintenanceDocumentRule() {
        super();
    }
    
    @Override
    protected boolean processCustomRouteDocumentBusinessRules(MaintenanceDocument document) {
        return checkLookupReturn(document);
    }
    
    @Override
    protected boolean processCustomApproveDocumentBusinessRules(MaintenanceDocument document) {
        return checkLookupReturn(document);
    }

    /**
     * 
     * This method to check whether 'lookupreturn' is specified if lookupclass is selected.
     * @param maintenanceDocument
     * @return
     */
    private boolean checkLookupReturn(MaintenanceDocument maintenanceDocument) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("New maintainable is: " + maintenanceDocument.getNewMaintainableObject().getClass());
        }
        ProposalColumnsToAlter newEditableProposalField = (ProposalColumnsToAlter) maintenanceDocument.getNewMaintainableObject().getDataObject();

        if (StringUtils.isNotBlank(newEditableProposalField.getLookupClass())) {
            GlobalVariables.getUserSession().addObject(Constants.LOOKUP_CLASS_NAME, (Object)newEditableProposalField.getLookupClass());
        }
        if (StringUtils.isNotBlank(newEditableProposalField.getLookupClass())
                && StringUtils.isBlank(newEditableProposalField.getLookupReturn())) {
            GlobalVariables.getMessageMap().putError(Constants.PROPOSAL_EDITABLECOLUMN_LOOKUPRETURN, RiceKeyConstants.ERROR_REQUIRED,
                    new String[] { "Lookup Return" });
            return false;
        }

        return true;
    }
    
    @Override
    protected boolean processCustomSaveDocumentBusinessRules(MaintenanceDocument document) {
        return verifyProposaEditableColumnsDataType(document);
    }
    
    private boolean verifyProposaEditableColumnsDataType(MaintenanceDocument maintenanceDocument) {
        ProposalColumnsToAlter newEditableProposalField = (ProposalColumnsToAlter) maintenanceDocument.getNewMaintainableObject().getDataObject();
        KcPersistenceStructureService kraPersistenceStructureService = KcServiceLocator.getService(KcPersistenceStructureService.class);
        DataDictionaryService dataDictionaryService = (DataDictionaryService) KNSServiceLocator.getDataDictionaryService();
        Map<String, String> fieldMap = kraPersistenceStructureService.getDBColumnToObjectAttributeMap(DevelopmentProposal.class);
        DataDictionaryEntry entry = dataDictionaryService.getDataDictionary().getDictionaryObjectEntry(ProposalDevelopmentDocument.class.getName());
        
        boolean returnFlag = true;
        String editableProposalField = "";
        Integer fieldMaxLength = -1;
        Integer inputDataLength = -1;
        String proposalFieldDataType = "";
        String inputDataType = "";
        ValidationPattern validatingPattern = null;
        
        if(newEditableProposalField != null && StringUtils.isNotEmpty(newEditableProposalField.getColumnName())) {
            editableProposalField = fieldMap.get(newEditableProposalField.getColumnName());
            if(StringUtils.isNotEmpty(editableProposalField)) {
                if (entry != null) {
                    AttributeDefinition attributeDefinition = entry.getAttributeDefinition(editableProposalField);
                    if (attributeDefinition != null && attributeDefinition.hasValidationPattern()) {
                        validatingPattern = attributeDefinition.getValidationPattern();
                        if(validatingPattern != null) {
                            proposalFieldDataType = validationClassesMap.get(validatingPattern.getClass().getName());
                            inputDataType = newEditableProposalField.getDataType();
                            if(!proposalFieldDataType.equalsIgnoreCase(inputDataType)) {
                                //throw error
                                GlobalVariables.getMessageMap().putError(Constants.PROPOSAL_EDITABLECOLUMN_DATATYPE, KeyConstants.PROPOSAL_EDITABLECOLUMN_DATATYPE_MISMATCH);
                                returnFlag = false;
                            }
                        }
                    }
                }
                
                inputDataLength = newEditableProposalField.getDataLength();
                fieldMaxLength = dataDictionaryService.getAttributeMaxLength(DevelopmentProposal.class, editableProposalField);
                if(fieldMaxLength > inputDataLength) {
                    //throw error
                    GlobalVariables.getMessageMap().putError(Constants.PROPOSAL_EDITABLECOLUMN_DATALENGTH, KeyConstants.PROPOSAL_EDITABLECOLUMN_DATALENGTH_MISMATCH);
                    returnFlag = false;
                }
            }
        }
        
        return returnFlag;
    }
}
