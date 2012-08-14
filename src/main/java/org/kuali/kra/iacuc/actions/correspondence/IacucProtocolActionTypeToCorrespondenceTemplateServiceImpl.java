/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.iacuc.actions.correspondence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.kra.iacuc.actions.IacucProtocolActionType;
import org.kuali.kra.iacuc.correspondence.IacucProtocolCorrespondenceType;
import org.kuali.kra.protocol.actions.correspondence.ProtocolActionTypeToCorrespondenceTemplateServiceImpl;
import org.kuali.kra.protocol.correspondence.ProtocolCorrespondenceTemplate;
import org.kuali.kra.protocol.correspondence.ProtocolCorrespondenceType;
import org.kuali.rice.krad.service.BusinessObjectService;

public class IacucProtocolActionTypeToCorrespondenceTemplateServiceImpl 
    extends ProtocolActionTypeToCorrespondenceTemplateServiceImpl implements IacucProtocolActionTypeToCorrespondenceTemplateService {
    
    private Map<String, List<String>> actionTypesToCorrespondenceType;

    public IacucProtocolActionTypeToCorrespondenceTemplateServiceImpl() {
        actionTypesToCorrespondenceType = new HashMap<String, List<String>>();
        actionTypesToCorrespondenceType.put(IacucProtocolActionType.REQUEST_DEACTIVATE, Arrays.asList("25"));
        actionTypesToCorrespondenceType.put(IacucProtocolActionType.NOTIFIED_COMMITTEE, Arrays.asList("21"));
        actionTypesToCorrespondenceType.put(IacucProtocolActionType.ADMINISTRATIVE_CORRECTION, Arrays.asList("18"));
        actionTypesToCorrespondenceType.put(IacucProtocolActionType.NOTIFY_IACUC, Arrays.asList("22"));
        actionTypesToCorrespondenceType.put(IacucProtocolActionType.ADMINISTRATIVELY_WITHDRAWN, Arrays.asList("29"));
        actionTypesToCorrespondenceType.put(IacucProtocolActionType.IACUC_WITHDRAWN, Arrays.asList("29"));
        actionTypesToCorrespondenceType.put(IacucProtocolActionType.TABLED, Arrays.asList("27"));
        actionTypesToCorrespondenceType.put(IacucProtocolActionType.LIFT_HOLD, Arrays.asList("17"));
        actionTypesToCorrespondenceType.put(IacucProtocolActionType.DESIGNATED_REVIEW_APPROVAL, Arrays.asList("1", "7", "6", "9"));
        actionTypesToCorrespondenceType.put(IacucProtocolActionType.RESPONSE_APPROVAL, Arrays.asList("1", "7", "6", "9"));
        actionTypesToCorrespondenceType.put(IacucProtocolActionType.IACUC_APPROVED, Arrays.asList("1", "7", "6", "9"));
        actionTypesToCorrespondenceType.put(IacucProtocolActionType.ADMINISTRATIVELY_INCOMPLETE, Arrays.asList("15"));
        actionTypesToCorrespondenceType.put(IacucProtocolActionType.IACUC_DISAPPROVED, Arrays.asList("12"));
        actionTypesToCorrespondenceType.put(IacucProtocolActionType.EXPIRED, Arrays.asList("14"));
        actionTypesToCorrespondenceType.put(IacucProtocolActionType.ADMINISTRATIVELY_DEACTIVATED, Arrays.asList("11"));
        actionTypesToCorrespondenceType.put(IacucProtocolActionType.DEACTIVATED, Arrays.asList("11"));
        actionTypesToCorrespondenceType.put(IacucProtocolActionType.HOLD, Arrays.asList("16"));
        actionTypesToCorrespondenceType.put(IacucProtocolActionType.TERMINATED, Arrays.asList("28"));
        actionTypesToCorrespondenceType.put(IacucProtocolActionType.SUSPENDED, Arrays.asList("26"));
        actionTypesToCorrespondenceType.put(IacucProtocolActionType.IACUC_MAJOR_REVISIONS_REQUIRED, Arrays.asList("8"));
        actionTypesToCorrespondenceType.put(IacucProtocolActionType.IACUC_MINOR_REVISIONS_REQUIRED, Arrays.asList("8"));        
    }

    @Override
    protected Map<String, List<String>> getActionTypesToCorrespondenceTypeMap() {
        return actionTypesToCorrespondenceType;
    }

    @Override
    protected List<ProtocolCorrespondenceTemplate> getCorrespondenceTemplatesForTypeId(String correspondenceTypeId) {
        IacucProtocolCorrespondenceType type = 
            getBusinessObjectService().findBySinglePrimaryKey(IacucProtocolCorrespondenceType.class, correspondenceTypeId);
        if (type != null) {
            return type.getProtocolCorrespondenceTemplates();
        } else {
            return new ArrayList<ProtocolCorrespondenceTemplate>();
        }
    }
}
