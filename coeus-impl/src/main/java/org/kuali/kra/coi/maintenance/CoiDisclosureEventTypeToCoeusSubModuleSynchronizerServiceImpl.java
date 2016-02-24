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

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.module.CoeusSubModule;
import org.kuali.kra.coi.CoiDisclosureEventType;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CoiDisclosureEventTypeToCoeusSubModuleSynchronizerServiceImpl implements CoiDisclosureEventTypeToCoeusSubModuleSynchronizerService {

    private BusinessObjectService businessObjectService;
    
    private String coiCoeusModuleCode;

    @Override
    public void synchronizeCoeusSubModulesWithActiveCoiDisclosureEventTypes() {
        // get all the current coeus sub-modules for coi module
        List<CoeusSubModule> currentSubModulesForCoi = getCurrentCoeusSubModulesForCoiModule();
        // get all the current active coi disclosure event types
        List<CoiDisclosureEventType> currentActiveCoiDisclosureEventTypes = getCurrentActiveCoiDisclosureEventTypes();
        
        // iterate through the submodules, compare each to its corresponding event type (if any) and delete or update the submodule if required.
        for(CoeusSubModule subModule : currentSubModulesForCoi) {
            // extract the corresponding event type, if any exists, from the currently active event list.            
            CoiDisclosureEventType correspondingCoiEventType = extractCorrespondingCoiEventType(subModule.getSubModuleCode(), currentActiveCoiDisclosureEventTypes);
            if(correspondingCoiEventType != null) {
               // check and update submodule's description if needed
               if(!StringUtils.equals(subModule.getDescription(), correspondingCoiEventType.getDescription())) {
                  subModule.setDescription(correspondingCoiEventType.getDescription());
                  getBusinessObjectService().save(subModule);
               }
            }
            else{
                // this submodule does not have a corresponding event type so delete it
                getBusinessObjectService().delete(subModule);
            }
        }
        
        
        // the remaining (i.e. unextracted) coi event types, if any, are all newly added, so create corresponding submodules for them
       if(!currentActiveCoiDisclosureEventTypes.isEmpty()) {
           int nextId = getMaxCoeusSubModuleId() + 1;
           for(CoiDisclosureEventType remainingCoiEventType : currentActiveCoiDisclosureEventTypes) {
               CoeusSubModule correspondingSubModule = getCorrespondingCoeusSubModule(remainingCoiEventType);               
               correspondingSubModule.setCoeusSubModuleId(nextId);
               getBusinessObjectService().save(correspondingSubModule);
               nextId++;
           }
       }
       
    }
    
    
    // return the current max id amongst all CoeusSubModules in the system, in the unlikely event of there being no 
    // CoeusSubModuless in the system, this method will return 0. 
    private int getMaxCoeusSubModuleId() {
        int retVal = 0;
        List<CoeusSubModule> allCoeusSubModulesSortedById = new ArrayList<CoeusSubModule>();
        allCoeusSubModulesSortedById.addAll(getBusinessObjectService().findAllOrderBy(CoeusSubModule.class, "coeusSubModuleId", false));
        if(!allCoeusSubModulesSortedById.isEmpty()) {
            retVal = allCoeusSubModulesSortedById.get(0).getCoeusSubModuleId();
        }
        return retVal;
    }

    // create a new sub-module, and set its module code, submodule code and description
    private CoeusSubModule getCorrespondingCoeusSubModule(CoiDisclosureEventType remainingCoiEventType) {
        CoeusSubModule correspondingSubModule = new CoeusSubModule();
        correspondingSubModule.setModuleCode(getCoiCoeusModuleCode());
        correspondingSubModule.setSubModuleCode(remainingCoiEventType.getEventTypeCode());
        correspondingSubModule.setDescription(remainingCoiEventType.getDescription());
        return correspondingSubModule;
    }

    // iterate through the list of disclosure event types and if one if found with a code matching the given 
    // submodule code then remove it from list and return it, else return null.
    private CoiDisclosureEventType extractCorrespondingCoiEventType(String subModuleCode, List<CoiDisclosureEventType> currentActiveCoiDisclosureEventTypes) {
        CoiDisclosureEventType retVal = null;
        for(CoiDisclosureEventType candidateEventType : currentActiveCoiDisclosureEventTypes) {
            if(StringUtils.equals(candidateEventType.getEventTypeCode(), subModuleCode)) {
                retVal = candidateEventType;
                break;
            }
        }
        currentActiveCoiDisclosureEventTypes.remove(retVal);
        return retVal;
    }

    // use the boservice to query for all active coi event types
    private List<CoiDisclosureEventType> getCurrentActiveCoiDisclosureEventTypes() {
        List<CoiDisclosureEventType> activeCoiEventTypes = new ArrayList<CoiDisclosureEventType>();
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("active", true);
        activeCoiEventTypes.addAll(getBusinessObjectService().findMatchingOrderBy(CoiDisclosureEventType.class, fieldValues, "eventTypeCode", true));
        return activeCoiEventTypes;
    }

    // use the boservice to query for all sub modules having module code of coi
    private List<CoeusSubModule> getCurrentCoeusSubModulesForCoiModule() {
        List<CoeusSubModule> coiSubModules = new ArrayList<CoeusSubModule>();
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("moduleCode", getCoiCoeusModuleCode());
        coiSubModules.addAll(getBusinessObjectService().findMatchingOrderBy(CoeusSubModule.class, fieldValues, "subModuleCode", true));
        return coiSubModules;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }
    
    public String getCoiCoeusModuleCode() {
        return coiCoeusModuleCode;
    }

    public void setCoiCoeusModuleCode(String coiCoeusModuleCode) {
        this.coiCoeusModuleCode = coiCoeusModuleCode;
    }
    

}
