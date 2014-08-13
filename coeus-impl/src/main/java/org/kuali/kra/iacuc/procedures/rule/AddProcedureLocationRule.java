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
package org.kuali.kra.iacuc.procedures.rule;

import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.kra.iacuc.procedures.IacucProtocolStudyGroupLocation;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.MessageMap;

/**
 * This class adds rule for adding new <code>ProcedureLocation</code> object
 */
public class AddProcedureLocationRule extends KcTransactionalDocumentRuleBase implements KcBusinessRule<AddProcedureLocationEvent> {
    private static final String NEW_PROCEDURE_LOCATION_PATH = "newIacucProtocolStudyGroupLocation";
    
    @Override
    public boolean processRules(AddProcedureLocationEvent event) {
        return processAddProcedureLocationBusinessRules(event);
    }
    
    private boolean processAddProcedureLocationBusinessRules(AddProcedureLocationEvent event) {
        boolean rulePassed = true;
        rulePassed &= isLocationValid(event);
        if(rulePassed) {
            rulePassed &= !isDuplicateLocation(event);
        }
        return rulePassed;
    }
    
    /**
     * This method is to verify whether location mandatory fields are selected during Add
     * @param event
     * @return
     */
    private boolean isLocationValid(AddProcedureLocationEvent event) {
        boolean locationValid = true;
        IacucProtocolStudyGroupLocation newIacucProtocolStudyGroupLocation = event.getIacucProtocolStudyGroupLocation();
        MessageMap errorMap = GlobalVariables.getMessageMap();
        errorMap.addToErrorPath(getErrorPath(event));
        getDictionaryValidationService().validateBusinessObject(newIacucProtocolStudyGroupLocation);
        locationValid &= GlobalVariables.getMessageMap().hasNoErrors();
        errorMap.removeFromErrorPath(getErrorPath(event));
        return locationValid;
    }

    /**
     * This method is to check for duplicate location in the list
     * @param event
     * @return
     */
    private boolean isDuplicateLocation(AddProcedureLocationEvent event) {
        boolean duplicateLocation = false;
        IacucProtocolStudyGroupLocation newIacucProtocolStudyGroupLocation = event.getIacucProtocolStudyGroupLocation();
        for(IacucProtocolStudyGroupLocation studyGroupLocation : event.getIacucProtocolDocument().getIacucProtocol().getIacucProtocolStudyGroupLocations()) {
            if(studyGroupLocation.getLocationId().equals(newIacucProtocolStudyGroupLocation.getLocationId()) &&
                    studyGroupLocation.getLocationTypeCode().equals(newIacucProtocolStudyGroupLocation.getLocationTypeCode())){
                GlobalVariables.getMessageMap().putError(getErrorPath(event) + "locationTypeCode", 
                        KeyConstants.ERROR_IACUC_VALIDATION_DUPLICATE_STUDY_GROUP_LOCATION);                
                duplicateLocation = true;
            }
        }
        return duplicateLocation;
    }
    
    private String getErrorPath(AddProcedureLocationEvent event) {
        StringBuffer errorPath = new StringBuffer();
        errorPath.append(NEW_PROCEDURE_LOCATION_PATH);
        return errorPath.toString();
    }
}
