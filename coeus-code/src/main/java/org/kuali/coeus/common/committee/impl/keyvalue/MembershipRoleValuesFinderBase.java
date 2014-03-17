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
package org.kuali.coeus.common.committee.impl.keyvalue;

import org.kuali.coeus.common.committee.impl.bo.MembershipRole;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.uif.control.UifKeyValuesFinderBase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class MembershipRoleValuesFinderBase extends UifKeyValuesFinderBase {


    private static final long serialVersionUID = -7492852270320003877L;

    @Override
    public List<KeyValue> getKeyValues() {
        List<KeyValue> keyLabels = new ArrayList<KeyValue>();
        keyLabels.add(new ConcreteKeyValue("", "select"));

        Map<String, String> criteria = new HashMap<String, String>();
        
        criteria.put("committeeTypeCode", getCommitteeTypeCodeHook());

        List<? extends MembershipRole> roles = (List<? extends MembershipRole>) getBusinessObjectService().findMatching(MembershipRole.class, criteria);

        for(MembershipRole role : roles) {
            keyLabels.add(new ConcreteKeyValue(role.getMembershipRoleCode(), role.getDescription()));
        }
        return keyLabels;
    }
    
    protected abstract String getCommitteeTypeCodeHook();

    protected BusinessObjectService getBusinessObjectService() {
        return KcServiceLocator.getService(BusinessObjectService.class);
    }
    
}
