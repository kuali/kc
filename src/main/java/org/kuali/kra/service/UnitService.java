/*
 * Copyright 2007 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.service;

import java.util.List;
import java.util.Collection;

import org.kuali.kra.bo.Unit;

/**
 * The Unit Service provides a set of queries related to Units.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public interface UnitService {
    
    /**
     * This method returns the Unit name for a given Unit Number.
     * @param unitNumber identifier for the unit
     * @return The name of the unit identified by this number.
     */
    public String getUnitName(String unitNumber);

    /**
     * Get the Unit based upon its unique unit number.
     * 
     * @param unitNumber the unit's unique unit number
     * @return the unit or null if not found.
     */
    public Unit getUnit(String unitNumber);
    
    /**
     * Get the list of immediate sub-units for the given unit.
     * @param unitNumber the number of unit to find sub-units for
     * @return the list of immediate sub-units
     */
    public List<Unit> getSubUnits(String unitNumber);
    
    /**
     * Get the list of all of the sub-units for a given unit.
     * @param unitNumber the number of unit to find sub-units for
     * @return the list of all sub-units
     */
    public List<Unit> getAllSubUnits(String unitNumber);

    /**
     * Get all of the Units.
     * @return all of the units
     */
    public Collection<Unit> getUnits();
}
