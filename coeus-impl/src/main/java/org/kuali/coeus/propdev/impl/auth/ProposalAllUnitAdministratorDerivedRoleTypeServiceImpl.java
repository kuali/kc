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
package org.kuali.coeus.propdev.impl.auth;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.unit.UnitService;
import org.kuali.coeus.common.framework.unit.admin.AbstractUnitAdministrator;
import org.kuali.coeus.common.framework.unit.admin.UnitAdministrator;
import org.kuali.kra.kim.bo.KcKimAttributes;
import org.kuali.coeus.common.framework.unit.admin.AbstractUnitAdministratorDerivedRoleTypeService;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;
import org.kuali.coeus.propdev.impl.person.ProposalPersonUnit;
import org.kuali.rice.kim.framework.role.RoleTypeService;
import org.kuali.rice.krad.data.DataObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component("proposalAllUnitAdministratorDerivedRoleTypeService")
public class ProposalAllUnitAdministratorDerivedRoleTypeServiceImpl extends AbstractUnitAdministratorDerivedRoleTypeService
        implements RoleTypeService {

    @Autowired
    @Qualifier("unitService")
    private UnitService unitService;

    @Autowired
    @Qualifier("dataObjectService")
    private DataObjectService dataObjectService;
    
    public void setUnitService(UnitService unitService) {
        this.unitService = unitService;
    }
    
    protected UnitService getUnitService() {
        return unitService;
    }

    public DataObjectService getDataObjectService() {
        return dataObjectService;
    }

    public void setDataObjectService(DataObjectService dataObjectService) {
        this.dataObjectService = dataObjectService;
    }

    @Override
    public List<? extends AbstractUnitAdministrator> getUnitAdministrators(Map<String, String> qualifiers) {
        String proposalNumber = qualifiers.get(KcKimAttributes.PROPOSAL);
        List<UnitAdministrator> result = new ArrayList<UnitAdministrator>();
        if (proposalNumber != null) {
            DevelopmentProposal proposal = getDataObjectService().find(DevelopmentProposal.class,proposalNumber);
            Set<String> units = getApplicableUnits(proposal);
            for (String unit : units) {
                if (StringUtils.isNotBlank(unit)) {
                    result.addAll(unitService.retrieveUnitAdministratorsByUnitNumber(unit));
                }
            }
        }
        return result;
    }

	protected Set<String> getApplicableUnits(DevelopmentProposal proposal) {
		return proposal.getProposalPersons().stream()
				  .flatMap(person -> person.getUnits().stream())
				  .map(unit -> getUnitNumberForPersonUnit(unit))
				  .filter(Objects::nonNull)
				  .collect(Collectors.toSet());
	}

	protected String getUnitNumberForPersonUnit(ProposalPersonUnit unit) {
		return unit.getUnitNumber();
	}

}
