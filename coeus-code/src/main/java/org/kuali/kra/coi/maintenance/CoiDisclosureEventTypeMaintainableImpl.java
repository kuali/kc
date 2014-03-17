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

import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.maintenance.KraMaintainableImpl;
import org.kuali.rice.krad.bo.DocumentHeader;

public class CoiDisclosureEventTypeMaintainableImpl extends KraMaintainableImpl {

    private static final long serialVersionUID = -316878223662581136L;
    private transient CoiDisclosureEventTypeToCoeusSubModuleSynchronizerService synchronizerService;
    
    @Override
    public void doRouteStatusChange(DocumentHeader documentHeader) {
        CoiDisclosureEventTypeToCoeusSubModuleSynchronizerService synchronizer =  getSynchronizerService();
        synchronizer.synchronizeCoeusSubModulesWithActiveCoiDisclosureEventTypes();
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
