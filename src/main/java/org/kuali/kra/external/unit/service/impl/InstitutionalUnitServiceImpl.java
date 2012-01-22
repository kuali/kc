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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.bo.UnitAdministrator;
import org.kuali.kra.external.HashMapElement;
import org.kuali.kra.external.unit.UnitDTO;
import org.kuali.kra.external.unit.service.InstitutionalUnitService;
import org.kuali.kra.service.UnitService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.ObjectUtils;

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
    private BusinessObjectService businessObjectService;
    private static final Log LOG = LogFactory.getLog(InstitutionalUnitServiceImpl.class);

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
     * @see org.kuali.kra.external.unit.service.InstitutionalUnitService#lookupUnits(java.util.List)
     */
    public List<UnitDTO> lookupUnits(List<HashMapElement> criteria) {
        
        HashMap<String, String> searchCriteria =  new HashMap<String, String>();
        List<UnitDTO> unitDTO = new ArrayList<UnitDTO>();
        List<Unit> units;
        
        // if the criteria passed is null, then return all units.
        if (ObjectUtils.isNull(criteria)) {
            units =  new ArrayList<Unit>(businessObjectService.findAll(Unit.class));
        } else {
                // Reconstruct Hashmap from object list
            for (HashMapElement element : criteria) {
                searchCriteria.put(element.getKey(), element.getValue());  
            }
            units =  new ArrayList<Unit>(businessObjectService.findMatching(Unit.class, searchCriteria));      
        }
        
        //converting from bo to dto
        for (Unit unit : units) {
            if (LOG.isDebugEnabled()) {
                LOG.debug("unit in service is " + unit.getUnitName());
            }
            unitDTO.add(unitBoToDto(unit));
        }
       
        return unitDTO;
    }
    
    
    /**
     * {@inheritDoc}
     */
    public List<String> getParentUnits(String unitNumber) {
        ArrayList<String> parentUnits = new ArrayList<String>();
        
        Unit unit = unitService.getUnit(unitNumber); 
        
        if (ObjectUtils.isNull(unit)) {
            LOG.warn("Cannot get parent units for unit " + unitNumber + ": unit does not exist");
        } else {
            while (ObjectUtils.isNotNull(unit.getParentUnit())) {
                parentUnits.add(unit.getParentUnitNumber());
                unit = unit.getParentUnit();
            }
        }
        return parentUnits;
    }

    public void setUnitService(UnitService unitService) {
        this.unitService = unitService;
    }
    
    /**
     * Sets the businessObjectService attribute value. Injected by Spring.
     * 
     * @param businessObjectService The businessObjectService to set.
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
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
