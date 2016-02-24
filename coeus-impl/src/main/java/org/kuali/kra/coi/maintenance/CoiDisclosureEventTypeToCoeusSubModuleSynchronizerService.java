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
package org.kuali.kra.coi.maintenance;

public interface CoiDisclosureEventTypeToCoeusSubModuleSynchronizerService {
    
    
    
    /**
     * This method ensures that the Coeus submodule entries for the coi module
     * correspond exactly to the set of all active coi disclosure event types.
     * 
     * So if a pre-existing active disclosure event type is deactivated or deleted before this 
     * method runs, then the corresponding coeus submodule code entry (i.e. the one matching 
     * the event type code) will be deleted. If a pre-existing active disclosure event type's
     * description is changed, then the corresponding coeus submodule code entry's description 
     * will also be updated accordingly. Finally if a new active disclosure event type is added
     * then a new corresponding coeus submodule code entry will be inserted.  
     * 
     */
    public void synchronizeCoeusSubModulesWithActiveCoiDisclosureEventTypes();

}
