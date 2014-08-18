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
