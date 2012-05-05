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
package org.kuali.kra.committee.keyvalue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.committee.bo.CommitteeType;
import org.kuali.kra.committee.bo.MembershipRole;
import org.kuali.kra.committee.web.struts.form.CommitteeForm;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.keyvalues.KeyValuesBase;
import org.kuali.rice.krad.service.BusinessObjectService;

public class MembershipRoleValuesFinder extends KeyValuesBase {

    @Override
    public List<KeyValue> getKeyValues() {
        List<KeyValue> keyLabels = new ArrayList<KeyValue>();
        keyLabels.add(new ConcreteKeyValue("", "select"));

        CommitteeForm committeeForm = (CommitteeForm) KNSGlobalVariables.getKualiForm();
        String committeeTypeCode = committeeForm.getCommitteeDocument().getCommittee().getCommitteeTypeCode();
        List<? extends MembershipRole> roles = new ArrayList<MembershipRole>();
        Map<String, String> criteria = new HashMap<String, String>();
        if (StringUtils.equalsIgnoreCase(committeeTypeCode, CommitteeType.IRB_TYPE_CODE)) {
            criteria.put("committeeTypeCode", CommitteeType.IRB_TYPE_CODE);
            
        } else {
            //IACUC
            criteria.put("committeeTypeCode", CommitteeType.IACUC_TYPE_CODE);

        }
        roles = (List<? extends MembershipRole>) getBusinessObjectService().findMatching(MembershipRole.class, criteria);

        for(MembershipRole role : roles) {
            keyLabels.add(new ConcreteKeyValue(role.getMembershipRoleCode(), role.getDescription()));
        }
        return keyLabels;
    }
    
    protected BusinessObjectService getBusinessObjectService() {
        return KraServiceLocator.getService(BusinessObjectService.class);
    }
    
}
