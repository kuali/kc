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
