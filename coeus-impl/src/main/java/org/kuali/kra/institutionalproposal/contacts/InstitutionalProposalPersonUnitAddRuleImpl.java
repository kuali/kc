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
package org.kuali.kra.institutionalproposal.contacts;

import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.coeus.common.framework.unit.UnitService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.krad.util.GlobalVariables;


public class InstitutionalProposalPersonUnitAddRuleImpl implements InstitutionalProposalPersonUnitAddRule {

    @Override
    public boolean processAddInstitutionalProposalPersonUnitBusinessRules(InstitutionalProposalPersonUnitRuleAddEvent event) {
        return checkForValidUnit(event.getNewPersonUnit()) && checkForDuplicateUnits(event.getProjectPerson(), event.getNewPersonUnit());
    }
        
    boolean checkForDuplicateUnits(InstitutionalProposalPerson projectPerson, InstitutionalProposalPersonUnit newInstitutionalProposalPersonUnit) {
        boolean valid = true;
        for(InstitutionalProposalPersonUnit apu: projectPerson.getUnits()) {
            valid = !apu.getUnit().equals(newInstitutionalProposalPersonUnit.getUnit());
            if(!valid) {
                break;
            }
        }
        
        if(!valid) {
            Unit dupeUnit = newInstitutionalProposalPersonUnit.getUnit();
            GlobalVariables.getMessageMap().putError(NEW_UNIT_NUMBER_FIELD, 
                                                        ERROR_PROPOSAL_PROJECT_PERSON_DUPLICATE_UNITS, 
                                                        dupeUnit.getUnitName(), dupeUnit.getUnitNumber(),
                                                        projectPerson.getFullName());
            //this null is to just clean stuff up
            newInstitutionalProposalPersonUnit.setUnit(null);
        }
        
        return valid;
    }
    
    protected boolean checkForValidUnit(InstitutionalProposalPersonUnit newInstitutionalProposalPersonUnit){
        String newUnitNumber = newInstitutionalProposalPersonUnit.getUnitNumber();
        boolean valid =  KcServiceLocator.getService(UnitService.class).getUnit(newUnitNumber) != null;
        if (!valid) {
            GlobalVariables.getMessageMap().putError(NEW_UNIT_NUMBER_FIELD, 
                    KeyConstants.ERROR_INVALID_UNIT, newUnitNumber);
            //this null is to just clean stuff up
            newInstitutionalProposalPersonUnit.setUnit(null);
        }
        return valid;
    }

}
