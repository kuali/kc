/*
 * Copyright 2005-2013 The Kuali Foundation
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
package org.kuali.kra.coi.disclosure;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.coi.CoiDisclProject;
import org.kuali.kra.coi.CoiDisclosureEventType;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.core.api.util.RiceKeyConstants;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.kuali.rice.kns.service.DataDictionaryService;
import org.kuali.rice.krad.util.GlobalVariables;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddManualProjectRule extends KcTransactionalDocumentRuleBase implements KcBusinessRule<AddManualProjectEvent> {

    private static final String DISCLOSURE_EVENT_TYPE = "disclosureEventType";
    
    @Override
    public boolean processRules(AddManualProjectEvent event) {
        boolean valid = true;
        CoiDisclProject coiDisclProject = event.getCoiDisclProject();
        GlobalVariables.getMessageMap().addToErrorPath(event.getPropertyName());
        DataDictionaryService dataDictionaryService = KcServiceLocator.getService(DataDictionaryService.class);

        String eventTypeId = coiDisclProject.getDisclosureEventType();
        
        if (StringUtils.isNotBlank(eventTypeId)) {
            CoiDisclosureEventType disclosureEventType = getDisclosureEventType(eventTypeId);
            if (disclosureEventType != null) {
                valid = validateRequiredFields(coiDisclProject, disclosureEventType);
                valid &= isValidProjectId(coiDisclProject);
            }
        } else {
            GlobalVariables.getMessageMap().putError(DISCLOSURE_EVENT_TYPE, RiceKeyConstants.ERROR_REQUIRED, "Event Type (Event Type)" );
            valid = false;
        }

        return valid;
    }
    
    /**
     * This method is used to verify that there are no others disclosures present in the system for the user for the same projectId and event type.
     * @param coiDisclProject
     * @return
     */
    protected boolean isValidProjectId(CoiDisclProject coiDisclProject) {
        boolean isValid = true;
        String eventDisclosureNumber = coiDisclProject.getCoiDisclosureNumber();
        String eventType = coiDisclProject.getDisclosureEventType();
        Map<String, String> criteria = new HashMap<String, String>();
        criteria.put("coiProjectId", coiDisclProject.getCoiProjectId());
        criteria.put("disclosureEventType", eventType);
        criteria.put("coiDisclosureNumber", eventDisclosureNumber);
        List<CoiDisclProject> projects = (List<CoiDisclProject>) getBusinessObjectService().findMatching(CoiDisclProject.class, criteria);
        
        for (CoiDisclProject project : projects) {
            GlobalVariables.getMessageMap().putError("coiProjectId", KeyConstants.ERROR_COI_DUPLICATE_PROJECT_ID, "(" + coiDisclProject.getCoiProjectId() + ")"); 
            isValid = false;
        }
        return isValid;
    }
    
    private boolean validateRequiredFields(CoiDisclProject coiDisclProject, CoiDisclosureEventType disclosureEventType) {
        boolean valid = true;
        
        valid &= validateRequiredField("coiProjectId", coiDisclProject.getCoiProjectId(), disclosureEventType.getProjectIdLabel());
        valid &= validateRequiredField("coiProjectTitle", coiDisclProject.getCoiProjectTitle(), disclosureEventType.getProjectTitleLabel());

        if (disclosureEventType.isUseShortTextField1() && disclosureEventType.isRequireShortTextField1()) {
            valid &= validateRequiredField("shortTextField1", coiDisclProject.getShortTextField1(), disclosureEventType.getShortTextField1Label());
        }
        
        if (disclosureEventType.isUseShortTextField2() && disclosureEventType.isRequireShortTextField2()) {
            valid &= validateRequiredField("shortTextField2", coiDisclProject.getShortTextField2(), disclosureEventType.getShortTextField2Label());
        }

        if (disclosureEventType.isUseShortTextField3() && disclosureEventType.isRequireShortTextField3()) {
            valid &= validateRequiredField("shortTextField3", coiDisclProject.getShortTextField3(), disclosureEventType.getShortTextField3Label());
        }

        if (disclosureEventType.isUseLongTextField1() && disclosureEventType.isRequireLongTextField1()) {
            valid &= validateRequiredField("longTextField1", coiDisclProject.getLongTextField1(), disclosureEventType.getLongTextField1Label());
        }

        if (disclosureEventType.isUseLongTextField2() && disclosureEventType.isRequireLongTextField2()) {
            valid &= validateRequiredField("longTextField2", coiDisclProject.getLongTextField2(), disclosureEventType.getLongTextField2Label());
        }

        if (disclosureEventType.isUseLongTextField3() && disclosureEventType.isRequireLongTextField3()) {
            valid &= validateRequiredField("longTextField3", coiDisclProject.getLongTextField3(), disclosureEventType.getLongTextField3Label());
        }

        if (disclosureEventType.isUseDateField1() && disclosureEventType.isRequireDateField1()) {
            valid &= validateRequiredDateField("dateField1", coiDisclProject.getDateField1(), disclosureEventType.getDateField1Label());
        }
        
        if (disclosureEventType.isUseDateField2() && disclosureEventType.isRequireDateField2()) {
            valid &= validateRequiredDateField("dateField2", coiDisclProject.getDateField2(), disclosureEventType.getDateField2Label());
        }

        if (disclosureEventType.isUseNumberField1() && disclosureEventType.isRequireNumberField1()) {
            valid &= validateRequiredNumberField("numberField1", coiDisclProject.getNumberField1(), disclosureEventType.getNumberField1Label());
        }

        if (disclosureEventType.isUseNumberField2() && disclosureEventType.isRequireNumberField2()) {
            valid &= validateRequiredNumberField("numberField2", coiDisclProject.getNumberField2(), disclosureEventType.getNumberField2Label());
        }
        
        if (disclosureEventType.isUseSelectBox1() && disclosureEventType.isRequireSelectBox1()) {
            valid &= validateRequiredField("selectBox1", coiDisclProject.getSelectBox1(), disclosureEventType.getSelectBox1Label());
        }
        
        return valid;
    }
    
    private boolean validateRequiredField(String fieldName, String fieldValue, String fieldLabel) {
        boolean valid = true;
        if (StringUtils.isBlank(fieldValue)) {
            valid = false;
            GlobalVariables.getMessageMap().putError(fieldName, RiceKeyConstants.ERROR_REQUIRED, fieldLabel + " (" + fieldLabel + ")" );
        }
        return valid;

    }
    
    private boolean validateRequiredDateField(String fieldName, Date fieldValue, String fieldLabel) {
        boolean valid = true;
        if (fieldValue == null) {
            valid = false;
            GlobalVariables.getMessageMap().putError(fieldName, RiceKeyConstants.ERROR_REQUIRED, fieldLabel + " (" + fieldLabel + ")" );
        }
        return valid;
    }

    private boolean validateRequiredNumberField(String fieldName, KualiDecimal fieldValue, String fieldLabel) {
        boolean valid = true;
        if (fieldValue == null) {
            valid = false;
            GlobalVariables.getMessageMap().putError(fieldName, RiceKeyConstants.ERROR_REQUIRED, fieldLabel + " (" + fieldLabel + ")" );
        }
        return valid;
    }    
    
    private CoiDisclosureEventType getDisclosureEventType(String eventTypeId) {
        CoiDisclosureEventType discEventType = null;
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("eventTypeCode", eventTypeId);

        List<CoiDisclosureEventType> disclEventTypes = (List<CoiDisclosureEventType>)getBusinessObjectService().findMatching(CoiDisclosureEventType.class, fieldValues);
        if (CollectionUtils.isNotEmpty(disclEventTypes)) {
            discEventType = disclEventTypes.get(0);
        }
        
        return discEventType;
    }
    
}
