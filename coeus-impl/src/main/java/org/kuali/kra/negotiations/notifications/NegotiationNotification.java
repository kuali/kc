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
package org.kuali.kra.negotiations.notifications;

import org.kuali.coeus.common.notification.impl.bo.KcNotification;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.negotiations.bo.Negotiation;
import org.kuali.rice.krad.service.BusinessObjectService;

public class NegotiationNotification extends KcNotification {

    private static final long serialVersionUID = -305741736132165017L;

    public NegotiationNotification() {
        super();
    }

    public void persistOwningObject(KcPersistableBusinessObjectBase object) {
        Negotiation negotiation = (Negotiation)object;
        this.setOwningDocumentIdFk(negotiation.getNegotiationId());
        negotiation.addNotification(this);
        KcServiceLocator.getService(BusinessObjectService.class).save(this);
    }

}
