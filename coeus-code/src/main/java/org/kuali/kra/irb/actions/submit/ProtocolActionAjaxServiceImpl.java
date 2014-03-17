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
package org.kuali.kra.irb.actions.submit;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.committee.impl.bo.CommitteeMembershipBase;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.actions.ProtocolActionAjaxServiceImplBase;
import org.kuali.rice.core.api.util.KeyValue;

import java.util.HashMap;
import java.util.List;

public class ProtocolActionAjaxServiceImpl extends ProtocolActionAjaxServiceImplBase implements ProtocolActionAjaxService {
    

    @Override
    public String getReviewers(String protocolId, String committeeId, String scheduleId) {
        StringBuffer ajaxList = new StringBuffer();
        HashMap<String, String> hm = new HashMap<String, String>();
        hm.put("protocolId", protocolId);
        Protocol protocol = (Protocol) (getBusinessObjectService().findMatching(Protocol.class, hm).toArray())[0];
        // filter out the protocol personnel; they cannot be reviewers on their own protocol
        List<CommitteeMembershipBase> filteredMembers = protocol.filterOutProtocolPersonnel(getCommitteeService().getAvailableMembers(
                committeeId, scheduleId));

        for (CommitteeMembershipBase filteredMember : filteredMembers) {
            if (StringUtils.isNotBlank(filteredMember.getPersonId())) {
                ajaxList.append(filteredMember.getPersonId() + ";" + filteredMember.getPersonName() + ";N;");
            } else {
                ajaxList.append(filteredMember.getRolodexId() + ";" + filteredMember.getPersonName() + ";Y;");
            }
        }
        return clipLastChar(ajaxList);
    }

    @Override
    public Class getProtocolReviewerTypeClassHook() {
        return ProtocolReviewerType.class;
    }

    @Override
    public Class<? extends ProtocolBase> getProtocolClassHook() {
        return Protocol.class;
    }

    @Override
    public String getValidCommitteeDates(String committeeId, String protocolNumber) {
        StringBuffer ajaxList = new StringBuffer();
        if (isAuthorizedToAccess(protocolNumber)) {
            List<KeyValue> dates = getCommitteeService().getAvailableCommitteeDates(committeeId);
            for (KeyValue date : dates) {
                ajaxList.append(date.getKey() + ";" + date.getValue() + ";");
            }
            return clipLastChar(ajaxList);
        }
        else {
            return ";Not Authorized;";
        }
    }


    
    /*
     * a utility method to check if dwr/ajax call really has authorization
     */
    private boolean isAuthorizedToAccess(String protocolNumber) {
        boolean isAuthorized = true;
        return isAuthorized;
    }
}
