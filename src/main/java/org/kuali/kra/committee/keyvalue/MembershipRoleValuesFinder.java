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
package org.kuali.kra.committee.keyvalue;

import org.kuali.kra.common.committee.bo.CommitteeType;
import org.kuali.kra.common.committee.keyvalue.MembershipRoleValuesFinderBase;

public class MembershipRoleValuesFinder extends MembershipRoleValuesFinderBase {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -8641252213493844509L;

    @Override
    protected String getCommitteeTypeCodeHook() {
        return CommitteeType.IRB_TYPE_CODE;
    }
    
    
// TODO ********************** commented out during IRB backfit ************************
//    @Override
//    public List<KeyValue> getKeyValues() {
//        List<KeyValue> keyLabels = new ArrayList<KeyValue>();
//        keyLabels.add(new ConcreteKeyValue("", "select"));
//
//        CommitteeForm committeeForm = (CommitteeForm) KNSGlobalVariables.getKualiForm();
//        String committeeTypeCode = committeeForm.getCommitteeDocument().getCommittee().getCommitteeTypeCode();
//        List<? extends MembershipRole> roles = new ArrayList<MembershipRole>();
//        Map<String, String> criteria = new HashMap<String, String>();
//        if (StringUtils.equalsIgnoreCase(committeeTypeCode, CommitteeType.IRB_TYPE_CODE)) {
//            criteria.put("committeeTypeCode", CommitteeType.IRB_TYPE_CODE);
//            
//        } else {
//            //IACUC
//            criteria.put("committeeTypeCode", CommitteeType.IACUC_TYPE_CODE);
//
//        }
//        roles = (List<? extends MembershipRole>) getBusinessObjectService().findMatching(MembershipRole.class, criteria);
//
//        for(MembershipRole role : roles) {
//            keyLabels.add(new ConcreteKeyValue(role.getMembershipRoleCode(), role.getDescription()));
//        }
//        return keyLabels;
//    }
//    
//    protected BusinessObjectService getBusinessObjectService() {
//        return KraServiceLocator.getService(BusinessObjectService.class);
//    }
    
}
