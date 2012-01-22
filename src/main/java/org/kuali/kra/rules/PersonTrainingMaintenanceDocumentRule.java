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
package org.kuali.kra.rules;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.PersonTraining;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.krad.util.GlobalVariables;

public class PersonTrainingMaintenanceDocumentRule extends KraMaintenanceDocumentRuleBase {

    /**
     * 
     * @see org.kuali.core.maintenance.rules.MaintenanceDocumentRuleBase#processCustomRouteDocumentBusinessRules(org.kuali.core.document.MaintenanceDocument)
     */ 
    protected boolean processCustomRouteDocumentBusinessRules(MaintenanceDocument document) {
        PersonTraining personTraining = (PersonTraining)document.getNoteTarget();
        return validate(personTraining);
    }
    
    /**
     * 
     * @see org.kuali.core.maintenance.rules.MaintenanceDocumentRuleBase#processCustomApproveDocumentBusinessRules(org.kuali.core.document.MaintenanceDocument)
     */
    @Override
    protected boolean processCustomApproveDocumentBusinessRules(MaintenanceDocument document) {
        PersonTraining personTraining = (PersonTraining)document.getNoteTarget();
        return validate(personTraining);
    }

    private boolean validate(PersonTraining personTraining) {
        boolean valid = true;
        if (StringUtils.isNotBlank(personTraining.getPersonId()) && personTraining.getTrainingNumber() != null) {
            Map fieldValues = new HashMap();
            fieldValues.put("personId", personTraining.getPersonId());
            fieldValues.put("trainingNumber", personTraining.getTrainingNumber());
            List<PersonTraining> personTrainings = (List<PersonTraining>)boService.findMatching(PersonTraining.class, fieldValues);
            if (!personTrainings.isEmpty()) {
                PersonTraining existPersonTraining = personTrainings.get(0);
                if (personTraining.getPersonTrainingId() == null || personTraining.getPersonTrainingId() == existPersonTraining.getPersonTrainingId()) {
                    GlobalVariables.getMessageMap().putError("document.newMaintainableObject.personId", KeyConstants.PERSON_TRAINING_EXISTS,
                            new String[] { personTraining.getTrainingNumber().toString(), personTraining.getPersonId() });
                    valid = false;
                }
            }
            
        }
        
        return valid;
    }

}
