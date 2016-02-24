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
package org.kuali.kra.coi.disclosure;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.keyvalue.FormViewAwareUifKeyValuesFinderBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.coi.CoiDisclosureDocument;
import org.kuali.kra.coi.CoiDispositionStatus;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * This class is to serve as a values finder for coi disclosure actions - 'Approve'/'Disapprove'/'Set Disclosure Status'
 */
public class CoiDispositionStatusValuesFinder extends FormViewAwareUifKeyValuesFinderBase {
    
    private static final long serialVersionUID = -6465897852646872789L;
    private transient BusinessObjectService businessObjectService;

    @Override
    public List<KeyValue> getKeyValues() {
        CoiDisclosureDocument coiDisclosureDocument = (CoiDisclosureDocument) getDocument();
        String personId = coiDisclosureDocument.getCoiDisclosure().getDisclosureReporter().getPersonId();
        List<CoiDispositionStatus> statuses;
        if (StringUtils.equals(personId, GlobalVariables.getUserSession().getPrincipalId()) 
                && !coiDisclosureDocument.isViewOnly()
                && !coiDisclosureDocument.getDocumentHeader().getWorkflowDocument().isEnroute()
                && !coiDisclosureDocument.getDocumentHeader().getWorkflowDocument().isFinal()) {
            Map<String, Object> values = new HashMap<String, Object>();
            values.put("displayToReporter", true);
            statuses = (List<CoiDispositionStatus>) getBusinessObjectService().findMatchingOrderBy(CoiDispositionStatus.class, values, "coiDispositionCode", true);
        } else {
            statuses = (List<CoiDispositionStatus>) getBusinessObjectService().findAllOrderBy(CoiDispositionStatus.class, "coiDispositionCode", true);
        }
        List<KeyValue> keyValues = new ArrayList<KeyValue>();
        keyValues.add(new ConcreteKeyValue("", "select"));
        for (CoiDispositionStatus status : statuses) {
            keyValues.add(new ConcreteKeyValue(status.getCoiDispositionCode(), status.getDescription()));
        }
        return keyValues;
    }
    
    protected BusinessObjectService getBusinessObjectService() {
        if (businessObjectService == null) {
            businessObjectService = KcServiceLocator.getService(BusinessObjectService.class);
        }
        return businessObjectService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
}
