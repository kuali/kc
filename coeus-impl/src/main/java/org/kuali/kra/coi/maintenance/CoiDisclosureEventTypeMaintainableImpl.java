/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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

import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.maintenance.KraMaintainableImpl;
import org.kuali.rice.krad.bo.DocumentHeader;

public class CoiDisclosureEventTypeMaintainableImpl extends KraMaintainableImpl {

    private static final long serialVersionUID = -316878223662581136L;
    private transient CoiDisclosureEventTypeToCoeusSubModuleSynchronizerService synchronizerService;
    
    @Override
    public void doRouteStatusChange(DocumentHeader documentHeader) {
        executeAsLastActionUser(() -> {
            CoiDisclosureEventTypeToCoeusSubModuleSynchronizerService synchronizer = getSynchronizerService();
            synchronizer.synchronizeCoeusSubModulesWithActiveCoiDisclosureEventTypes();
            return null;
        });
    }
    
    public CoiDisclosureEventTypeToCoeusSubModuleSynchronizerService getSynchronizerService() {
        if(synchronizerService == null) {
            setSynchronizerService(KcServiceLocator.getService(CoiDisclosureEventTypeToCoeusSubModuleSynchronizerService.class));
        }
        return synchronizerService;
    }

    public void setSynchronizerService(CoiDisclosureEventTypeToCoeusSubModuleSynchronizerService synchronizerService) {
        this.synchronizerService = synchronizerService;
    }


    

}
