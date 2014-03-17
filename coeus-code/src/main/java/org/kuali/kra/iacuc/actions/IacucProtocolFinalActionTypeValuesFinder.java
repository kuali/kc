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
package org.kuali.kra.iacuc.actions;

import org.kuali.coeus.common.committee.impl.service.CommitteeServiceBase;
import org.kuali.kra.iacuc.committee.service.IacucCommitteeService;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;

import java.util.*;

public class IacucProtocolFinalActionTypeValuesFinder extends IacucActionsKeyValuesBase {


    private static final long serialVersionUID = -4549709102598366272L;

    private static final String FINAL_ACTION_FOR_BATCH_CORRESP_FIELD = "finalActionForBatchCorrespondence";
    private static final String DESCRIPTION_FIELD = "description";

    /**
     * Build the list of KeyValues using the key (keyAttributeName) and
     * label (labelAttributeName) of the list of all business objects found
     * for the BO class specified along with a "no action" entry.
     * 
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<KeyValue> getKeyValues() {
        List<KeyValue> keyValues = new ArrayList<KeyValue>();

        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put(FINAL_ACTION_FOR_BATCH_CORRESP_FIELD, "Y");
        Collection<IacucProtocolActionType> protocolActionTypes = 
            this.getBusinessObjectService().findMatchingOrderBy(IacucProtocolActionType.class, fieldValues, DESCRIPTION_FIELD, true);
        
        keyValues.add(new ConcreteKeyValue("  ", "no action"));
        for (IacucProtocolActionType protocolActionType : protocolActionTypes) {
            keyValues.add(new ConcreteKeyValue(protocolActionType.getProtocolActionTypeCode(), protocolActionType.getDescription()));
        }
        
        return keyValues;
    }
    
    @Override
    protected Class<? extends CommitteeServiceBase> getCommitteeServiceClassHook() {
        return IacucCommitteeService.class;
    }

}
