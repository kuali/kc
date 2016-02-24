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
