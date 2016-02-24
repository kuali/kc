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
