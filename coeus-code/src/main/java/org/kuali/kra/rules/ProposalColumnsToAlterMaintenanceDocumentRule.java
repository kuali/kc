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
package org.kuali.kra.rules;

import org.apache.commons.lang.StringUtils;
import org.kuali.coeus.sys.framework.persistence.KcPersistenceStructureService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.bo.ProposalColumnsToAlter;
import org.kuali.kra.proposaldevelopment.bo.ProposalOverview;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.rice.core.api.util.RiceKeyConstants;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.kns.maintenance.rules.MaintenanceDocumentRuleBase;
import org.kuali.rice.kns.service.DataDictionaryService;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.krad.datadictionary.AttributeDefinition;
import org.kuali.rice.krad.datadictionary.DataDictionaryEntry;
import org.kuali.rice.krad.datadictionary.validation.ValidationPattern;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.HashMap;
import java.util.Map;

public class ProposalColumnsToAlterMaintenanceDocumentRule extends MaintenanceDocumentRuleBase {
    
    private static Map<String, String> validationClassesMap = new HashMap<String, String>();
    static {
        validationClassesMap.put("org.kuali.rice.kns.datadictionary.validation.charlevel.AnyCharacterValidationPattern", "STRING");
        validationClassesMap.put("org.kuali.rice.kns.datadictionary.validation.charlevel.AlphaNumericValidationPattern", "STRING");
        validationClassesMap.put("org.kuali.rice.kns.datadictionary.validation.charlevel.AlphaValidationPattern", "STRING"); 
        validationClassesMap.put("org.kuali.rice.kns.datadictionary.validation.fieldlevel.DateValidationPattern", "DATE");
        validationClassesMap.put("org.kuali.rice.kns.datadictionary.validation.charlevel.NumericValidationPattern", "NUMBER");
    }
 
    /**
     * Constructs a ProposalColumnsToAlterMaintenanceDocumentRule.java.
     */
    public ProposalColumnsToAlterMaintenanceDocumentRule() {
        super();
    }
    
    /**
     * 
     * @see org.kuali.core.maintenance.rules.MaintenanceDocumentRuleBase#processCustomRouteDocumentBusinessRules(org.kuali.core.document.MaintenanceDocument)
     */ 
    protected boolean processCustomRouteDocumentBusinessRules(MaintenanceDocument document) {
        return checkLookupReturn(document);
    }
    
    /**
     * 
     * @see org.kuali.core.maintenance.rules.MaintenanceDocumentRuleBase#processCustomApproveDocumentBusinessRules(org.kuali.core.document.MaintenanceDocument)
     */
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
    
    /**
     * 
     * @see org.kuali.core.maintenance.rules.MaintenanceDocumentRuleBase#processCustomSaveDocumentBusinessRules(org.kuali.core.document.MaintenanceDocument)
     */ 
    protected boolean processCustomSaveDocumentBusinessRules(MaintenanceDocument document) {
        return verifyProposaEditableColumnsDataType(document);
    }
    
    private boolean verifyProposaEditableColumnsDataType(MaintenanceDocument maintenanceDocument) {
        ProposalColumnsToAlter newEditableProposalField = (ProposalColumnsToAlter) maintenanceDocument.getNewMaintainableObject().getDataObject();
        KcPersistenceStructureService kraPersistenceStructureService = KcServiceLocator.getService(KcPersistenceStructureService.class);
        DataDictionaryService dataDictionaryService = (DataDictionaryService) KNSServiceLocator.getDataDictionaryService();
        Map<String, String> fieldMap = kraPersistenceStructureService.getDBColumnToObjectAttributeMap(ProposalOverview.class);
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
