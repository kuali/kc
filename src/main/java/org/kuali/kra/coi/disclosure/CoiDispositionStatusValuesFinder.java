/*
 * Copyright 2005-2013 The Kuali Foundation
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
package org.kuali.kra.coi.disclosure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.coi.CoiDisclosureForm;
import org.kuali.kra.coi.CoiDisclosureStatus;
import org.kuali.kra.coi.CoiDispositionStatus;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.keyvalues.KeyValuesBase;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;

/**
 * 
 * This class is to serve as a values finder for coi disclosure actions - 'Approve'/'Disapprove'/'Set Disclosure Status'
 */
public class CoiDispositionStatusValuesFinder extends KeyValuesBase {
    
    private static final long serialVersionUID = -6465897852646872789L;
    private transient BusinessObjectService businessObjectService;
    
    /**
     * @see org.kuali.core.lookup.keyvalues.KeyValuesBase#getKeyValues()
     */
    public List getKeyValues() {
        CoiDisclosureForm coiDisclosureForm = (CoiDisclosureForm) KNSGlobalVariables.getKualiForm();
        String personId = coiDisclosureForm.getCoiDisclosureDocument().getCoiDisclosure().getDisclosureReporter().getPersonId();
        List<CoiDispositionStatus> statuses;
        if (StringUtils.equals(personId, GlobalVariables.getUserSession().getPrincipalId()) 
                && !coiDisclosureForm.getCoiDisclosureDocument().isViewOnly() 
                && !coiDisclosureForm.getCoiDisclosureDocument().getDocumentHeader().getWorkflowDocument().isEnroute()
                && !coiDisclosureForm.getCoiDisclosureDocument().getDocumentHeader().getWorkflowDocument().isFinal()) {
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
            businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
        }
        return businessObjectService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
}
