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

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.common.framework.unit.Unit;
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

    public FinancialEntityNotificationRenderer(PersonFinIntDisclosure disclosure) {
        this.disclosure = disclosure;
    }

    @Override
    public Map<String, String> getDefaultReplacementParameters() {
        Map<String, String> params = super.getDefaultReplacementParameters();
        String personId = getDisclosure().getPersonId();
        KcPerson reporter = getKcPersonService().getKcPersonByPersonId(personId);
        final Unit unit = reporter.getUnit();
        if (unit != null) {
          params.put("{UNIT}", unit.getUnitName());
        } else {
            params.put("{UNIT}", StringUtils.EMPTY);
        }
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
