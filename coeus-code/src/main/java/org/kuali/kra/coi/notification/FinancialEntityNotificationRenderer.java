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

import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.common.notification.impl.NotificationRendererBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.coi.personfinancialentity.PersonFinIntDisclosure;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.util.Map;

/**
 * Renders fields for the IRB notifications.
 */
public class FinancialEntityNotificationRenderer extends NotificationRendererBase {

    private static final long serialVersionUID = 9043632939341627699L;

    private PersonFinIntDisclosure disclosure;
    
    private transient BusinessObjectService businessObjectService;
    private transient KcPersonService kcPersonService;
    
    /**
     * Constructs a Financial Entity notification renderer.
     * @param PersonFinIntDisclosure
     */
    public FinancialEntityNotificationRenderer(PersonFinIntDisclosure disclosure) {
        this.disclosure = disclosure;
    }

    @Override
    public Map<String, String> getDefaultReplacementParameters() {
        Map<String, String> params = super.getDefaultReplacementParameters();
        String personId = getDisclosure().getPersonId();
        KcPerson reporter = getKcPersonService().getKcPersonByPersonId(personId);
        params.put("{UNIT}", reporter.getUnit().getUnitName());
        params.put("{FE_ENTITY_NAME}", disclosure.getEntityName());
        return params;
    }

    public PersonFinIntDisclosure getDisclosure() {
        return disclosure;
    }

    public void setDisclosure(PersonFinIntDisclosure disclosure) {
        this.disclosure = disclosure;
    }

    public BusinessObjectService getBusinessObjectService() {
        if (businessObjectService == null) {
            businessObjectService = KNSServiceLocator.getBusinessObjectService();
        }
        return businessObjectService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    public KcPersonService getKcPersonService() {
        if (kcPersonService == null) {
            kcPersonService = KcServiceLocator.getService(KcPersonService.class);
        }
        return kcPersonService;
    }

    public void setKcPersonService(KcPersonService kcPersonService) {
        this.kcPersonService = kcPersonService;
    }

}
