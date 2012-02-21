/*
 * Copyright 2005-2010 The Kuali Foundation
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

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.coi.CoiDisclProject;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.rule.BusinessRuleInterface;
import org.kuali.kra.rules.ResearchDocumentRuleBase;
import org.kuali.rice.core.api.util.RiceKeyConstants;
import org.kuali.rice.kns.service.DataDictionaryService;
import org.kuali.rice.krad.util.GlobalVariables;

public class AddManualProjectRule extends ResearchDocumentRuleBase implements BusinessRuleInterface<AddManualProjectEvent>  {

    
    @Override
    public boolean processRules(AddManualProjectEvent event) {

        CoiDisclProject coiDisclProject = event.getCoiDisclProject();
        GlobalVariables.getMessageMap().addToErrorPath(event.getPropertyName());
        DataDictionaryService dataDictionaryService = KraServiceLocator.getService(DataDictionaryService.class);

        /*
        boolean valid = validateRequiredFields(coiDisclProject);


        if (coiDisclProject.isProposalEvent()) {
            if (coiDisclProject.getDateField1() != null && coiDisclProject.getDateField2() != null) {
                if (coiDisclProject.getDateField1().after(coiDisclProject.getDateField2())) {
                    valid = false;
                    GlobalVariables.getMessageMap().putError(
                            "coiProjectStartDate",
                            KeyConstants.ERROR_START_DATE_AFTER_END_DATE,
                            new String[] {
                                    dataDictionaryService.getAttributeErrorLabel(CoiDisclProject.class, "coiProjectStartDate"),
                                    dataDictionaryService.getAttributeErrorLabel(CoiDisclProject.class, "coiProjectEndDate") });
                }
            }
        }
        if (StringUtils.isNotBlank(coiDisclProject.getShortTextField1())) {
            valid &= validateUniqueProjectId(coiDisclProject);
        }
        return valid;
        */
        return true;
    }
    
    private boolean validateUniqueProjectId(CoiDisclProject coiDisclProject) {
        boolean valid = true;
        /*
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("coiDisclosureNumber", coiDisclProject.getCoiDisclosureNumber());
        fieldValues.put("sequenceNumber", coiDisclProject.getSequenceNumber());
        fieldValues.put("shortTextField1", coiDisclProject.getShortTextField1());

        if (getBusinessObjectService().countMatching(CoiDisclProject.class, fieldValues) > 0) {
            valid = false;
            GlobalVariables.getMessageMap().putError("coiProjectId", KeyConstants.ERROR_COI_DUPLICATE_PROJECT_ID);

        }
        else {
            for (CoiDisclProject disclProject : coiDisclProject.getCoiDisclosure().getCoiDisclProjects()) {
                if (StringUtils.equals(disclProject.getShortTextField1(), coiDisclProject.getShortTextField1())) {
                    valid = false;
                    GlobalVariables.getMessageMap().putError("coiProjectId", KeyConstants.ERROR_COI_DUPLICATE_PROJECT_ID);
                    break;
                }
            }
        }
        */
        return valid;
        
    }
    
    private boolean validateRequiredFields(CoiDisclProject coiDisclProject) {
        boolean valid = true;
        /*
        DataDictionaryService dataDictionaryService = KraServiceLocator.getService(DataDictionaryService.class);
        valid &= validateRequiredField("disclosureEventType", coiDisclProject.getDisclosureEventType(),
                dataDictionaryService.getAttributeErrorLabel(CoiDisclProject.class, "disclosureEventType"));
        valid &= validateRequiredField("coiProjectId", coiDisclProject.getShortTextField1(), coiDisclProject.getProjectIdLabel());
        valid &= validateRequiredField("coiProjectTitle", coiDisclProject.getLongTextField1(),
                coiDisclProject.getProjectTitleLabel());
        if (StringUtils.isNotBlank(coiDisclProject.getDisclosureEventType())) {
            if (!coiDisclProject.isAwardEvent()) {
                valid &= validateRequiredField("coiProjectType", coiDisclProject.getShortTextField2(),
                        coiDisclProject.getProjectTypeLabel());
            }
            if (!coiDisclProject.isProtocolEvent()) {
                valid &= validateRequiredDateField("coiProjectStartDate", coiDisclProject.getDateField1(),
                        coiDisclProject.getProjectStartDateLabel());
            }
            if (coiDisclProject.isProposalEvent()) {
                valid &= validateRequiredDateField("coiProjectEndDate", coiDisclProject.getDateField2(),
                        dataDictionaryService.getAttributeErrorLabel(CoiDisclProject.class, "coiProjectEndDate"));
                valid &= validateRequiredField("coiProjectSponsor", coiDisclProject.getLongTextField2(),
                        dataDictionaryService.getAttributeErrorLabel(CoiDisclProject.class, "coiProjectSponsor"));
            }
        }
        */
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
}
