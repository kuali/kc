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
package org.kuali.kra.coi.notification;

import org.kuali.coeus.common.notification.impl.bo.KcNotification;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.coi.CoiDisclosure;
import org.kuali.rice.krad.service.BusinessObjectService;

// class used to satisfy java bean convention
public class CoiNotification extends KcNotification {

    private static final long serialVersionUID = 5124828394114551463L;

    public CoiNotification() {
        super();
    }

    public void persistOwningObject(KcPersistableBusinessObjectBase object) {
        if (object instanceof CoiDisclosure) {
            CoiDisclosure disclosure = (CoiDisclosure)object;
            disclosure.addNotification(this);
            KcServiceLocator.getService(BusinessObjectService.class).save(disclosure);
            KcServiceLocator.getService(BusinessObjectService.class).save(this);
        } else {
            //TODO how to persist notification for FE
        }
    }

}
