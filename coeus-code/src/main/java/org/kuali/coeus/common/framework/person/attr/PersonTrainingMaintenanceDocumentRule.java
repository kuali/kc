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
package org.kuali.coeus.common.framework.person.attr;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.rule.KcMaintenanceDocumentRuleBase;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonTrainingMaintenanceDocumentRule extends KcMaintenanceDocumentRuleBase {

    @Override
    protected boolean processCustomRouteDocumentBusinessRules(MaintenanceDocument document) {
        PersonTraining personTraining = (PersonTraining)document.getNoteTarget();
        return validate(personTraining);
    }
    
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
