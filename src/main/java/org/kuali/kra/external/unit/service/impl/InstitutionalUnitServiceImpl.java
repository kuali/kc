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
package org.kuali.kra.external.unit.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.kuali.kra.bo.Unit;
import org.kuali.kra.bo.UnitAdministrator;
import org.kuali.kra.external.unit.UnitDTO;
import org.kuali.kra.external.unit.service.InstitutionalUnitService;
import org.kuali.kra.service.UnitService;
import org.kuali.rice.kns.util.ObjectUtils;

/**
 * Default implementation of the
 * {@link org.kuali.kra.external.unit.service.InstitutionalUnitService InstitutionalUnitService}.
 *
 * The implementation is primarily achieved via delegation to KC's internal
 * {@link org.kuali.kra.service.UnitService UnitService}.
 *
 * @author Kuali Coeus Development Team
 */
public class InstitutionalUnitServiceImpl implements InstitutionalUnitService {
    
    private UnitService unitService;
    
    /**
     * {@inheritDoc}
     */
    public UnitDTO getUnit(String unitNumber) {
        Unit unit = unitService.getUnit(unitNumber);
        if (ObjectUtils.isNull(unit)) {
            return null;
        }
        return unitBoToDto(unit);
    }
    
    /**
     * {@inheritDoc}
     */
    public List<UnitDTO> lookupUnits(HashMap<String, String> searchCriteria) {
        return new ArrayList<UnitDTO>();
    }
    
    /**
     * {@inheritDoc}
     */
    public List<String> getParentUnits(String unitNumber) {
        
        return new ArrayList<String>();
    }

    public void setUnitService(UnitService unitService) {
        this.unitService = unitService;
    }
    
    /**
     * Create a new Unit DTO from a Unit BO.
     * 
     * @param unit
     * @return UnitDTO
     */
    protected UnitDTO unitBoToDto(Unit unit) {
        UnitDTO unitDTO = new UnitDTO();
        unitDTO.setUnitNumber(unit.getUnitNumber());
        unitDTO.setUnitName(unit.getUnitName());
        unitDTO.setOrganizationId(unit.getOrganizationId());
        for (UnitAdministrator unitAdmin : unit.getUnitAdministrators()) {
            unitDTO.getUnitAdministrators().add(unitAdmin.getPersonId());
        }
        return unitDTO;
    }
    
}
