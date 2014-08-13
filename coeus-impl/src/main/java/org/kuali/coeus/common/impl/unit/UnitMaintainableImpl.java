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
package org.kuali.coeus.common.impl.unit;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.coeus.common.framework.unit.UnitService;
import org.kuali.coeus.common.framework.unit.admin.UnitAdministrator;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.maintenance.KraMaintainableImpl;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.kns.maintenance.Maintainable;
import org.kuali.rice.krad.service.BusinessObjectService;

public class UnitMaintainableImpl extends KraMaintainableImpl implements Maintainable {

    /*
     * this is to prevent Unit Admins from being deleted when modifying an editable portion of the Unit (NAME).
     * see KRACOEUS-6830
     * Setting auto-delete="none" and auto-update="none/link" did not remedy this...
     */
    @Override
    public void saveBusinessObject() {
        Unit newUnit = (Unit) this.getBusinessObject();
        UnitService unitService = KcServiceLocator.getService(UnitService.class);
        BusinessObjectService boService = KcServiceLocator.getService(BusinessObjectService.class);
        List<UnitAdministrator> administrators = new ArrayList<UnitAdministrator>();
        boolean deactivating = false;
        
        if(isOldBusinessObjectInDocument()) {
            Unit oldUnit = boService.findBySinglePrimaryKey(Unit.class, newUnit.getUnitNumber());
            if(oldUnit != null) {
                if(oldUnit.isActive() && newUnit.isActive()) {
                    administrators.addAll(unitService.retrieveUnitAdministratorsByUnitNumber(oldUnit.getUnitNumber()));
                }
                //Deletion is (somewhat) understandable if the unit is being inactivated.
            }
        }
        
        super.saveBusinessObject();
        
        //re-add the deleted admins if the record remains active.
        for(UnitAdministrator admin : administrators) {
            admin.setUnitNumber(newUnit.getUnitNumber());
            boService.save(admin);
        }
    }

}
