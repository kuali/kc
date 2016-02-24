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
package org.kuali.kra.external.unit.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.coeus.common.framework.unit.UnitService;
import org.kuali.coeus.common.framework.unit.admin.UnitAdministrator;
import org.kuali.kra.external.HashMapElement;
import org.kuali.kra.external.unit.UnitDTO;
import org.kuali.kra.external.unit.service.InstitutionalUnitService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Default implementation of the
 * {@link org.kuali.kra.external.unit.service.InstitutionalUnitService InstitutionalUnitService}.
 *
 * The implementation is primarily achieved via delegation to KC's internal
 * {@link org.kuali.coeus.common.framework.unit.UnitService UnitService}.
 *
 * @author Kuali Coeus Development Team
 */
@Component("institutionalUnitService")
public class InstitutionalUnitServiceImpl implements InstitutionalUnitService {

    @Autowired
    @Qualifier("unitService")
    private UnitService unitService;

    @Autowired
    @Qualifier("businessObjectService")
    private BusinessObjectService businessObjectService;
    private static final Log LOG = LogFactory.getLog(InstitutionalUnitServiceImpl.class);

    @Override
    public UnitDTO getUnit(String unitNumber) {
        Unit unit = unitService.getUnit(unitNumber);
        if (ObjectUtils.isNull(unit)) {
            return null;
        }
        return unitBoToDto(unit);
    }
    
    @Override
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
    
    
    @Override
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
