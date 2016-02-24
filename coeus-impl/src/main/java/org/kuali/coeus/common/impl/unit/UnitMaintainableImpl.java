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
package org.kuali.coeus.common.impl.unit;

import java.util.ArrayList;
import java.util.List;

import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.coeus.common.framework.unit.UnitService;
import org.kuali.coeus.common.framework.unit.admin.UnitAdministrator;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.maintenance.KraMaintainableImpl;
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
