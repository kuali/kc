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
package org.kuali.kra.protocol.actions;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.common.committee.bo.CommitteeMembership;
import org.kuali.kra.common.committee.service.CommonCommitteeService;
import org.kuali.kra.protocol.Protocol;
import org.kuali.kra.protocol.actions.submit.ProtocolReviewerType;
import org.kuali.rice.krad.service.BusinessObjectService;

public abstract class ProtocolActionAjaxServiceImpl implements ProtocolActionAjaxService {

    private BusinessObjectService businessObjectService;
    private CommonCommitteeService committeeService;

    public String getReviewers(String protocolId, String committeeId, String scheduleId) {
        StringBuffer ajaxList = new StringBuffer();

        HashMap<String, String> criteria = new HashMap<String, String>();
        criteria.put("protocolId", protocolId);
        Protocol protocol = (Protocol) (businessObjectService.findMatching(getProtocolClassHook(), criteria).toArray())[0];
        // filter out the protocol personnel; they cannot be reviewers on their own protocol
        List<CommitteeMembership> filteredMembers = protocol.filterOutProtocolPersonnel(committeeService.getAvailableMembers(
                committeeId, scheduleId));
        
        for (CommitteeMembership filteredMember : filteredMembers) {
            if (StringUtils.isNotBlank(filteredMember.getPersonId())) {
                ajaxList.append(filteredMember.getPersonId() + ";" + filteredMember.getPersonName() + ";N;");
            } else {
                ajaxList.append(filteredMember.getRolodexId() + ";" + filteredMember.getPersonName() + ";Y;");
            }
        }
        return clipLastChar(ajaxList);
    }
    
    public String getReviewerTypes() {
        StringBuffer ajaxList = new StringBuffer();
        Collection<ProtocolReviewerType> reviewerTypes = getReviewerTypesFromDatabase();
        for (ProtocolReviewerType reviewerType : reviewerTypes) {
            ajaxList.append(reviewerType.getReviewerTypeCode() + ";" + reviewerType.getDescription() + ";");
        }
        return clipLastChar(ajaxList);
    }
    @SuppressWarnings("unchecked")
    protected Collection<ProtocolReviewerType> getReviewerTypesFromDatabase() {
        return businessObjectService.findAll(getProtocolReviewerTypeClassHook());
    }
    
    public abstract Class getProtocolReviewerTypeClassHook();
    
    public abstract Class<? extends Protocol> getProtocolClassHook();
    
    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }
    
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    
    public CommonCommitteeService getCommitteeService() {
        return committeeService;
    }
    
    public void setCommitteeService(CommonCommitteeService committeeService) {
        this.committeeService = committeeService;
    }

    /**
     * Clip the last character from the string buffer. The last character, if there is one, is always a separator that must be
     * removed.
     * 
     * @param ajaxList
     * @return
     */
    protected String clipLastChar(StringBuffer ajaxList) {
        if (ajaxList.length() == 0) {
            return ajaxList.toString();
        }
        return ajaxList.substring(0, ajaxList.length() - 1);
    }
}
