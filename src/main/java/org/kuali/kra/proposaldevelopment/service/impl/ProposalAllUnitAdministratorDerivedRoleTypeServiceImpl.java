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
package org.kuali.kra.proposaldevelopment.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.AbstractUnitAdministrator;
import org.kuali.kra.bo.UnitAdministrator;
import org.kuali.kra.kim.bo.KcKimAttributes;
import org.kuali.kra.kim.service.impl.AbstractUnitAdministratorDerivedRoleTypeService;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonUnit;
import org.kuali.kra.service.UnitService;
import org.kuali.rice.kim.framework.role.RoleTypeService;

public class ProposalAllUnitAdministratorDerivedRoleTypeServiceImpl extends AbstractUnitAdministratorDerivedRoleTypeService
        implements RoleTypeService {

    private UnitService unitService;

    public void setUnitService(UnitService unitService) {
        this.unitService = unitService;
    }
    
    protected UnitService getUnitService() {
        return unitService;
    }
    
    @Override
    public List<? extends AbstractUnitAdministrator> getUnitAdministrators(Map<String, String> qualifiers) {
        String proposalNumber = qualifiers.get(KcKimAttributes.PROPOSAL);
        List<UnitAdministrator> result = new ArrayList<UnitAdministrator>();
        if (proposalNumber != null) {
            DevelopmentProposal proposal = getBusinessObjectService().findBySinglePrimaryKey(DevelopmentProposal.class, proposalNumber);
            HashSet<String> units = new HashSet<String>();
            for (ProposalPerson person : proposal.getProposalPersons()) {
                for (ProposalPersonUnit unit : person.getUnits()) {
                    units.add(unit.getUnitNumber());
                }
            }
        
            for (String unit : units) {
                if (StringUtils.isNotBlank(unit)) {
                    result.addAll(unitService.retrieveUnitAdministratorsByUnitNumber(unit));
                }
            }
        }   
        return result;
    }

}
