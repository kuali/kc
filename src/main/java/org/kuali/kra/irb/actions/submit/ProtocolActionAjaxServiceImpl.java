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
package org.kuali.kra.irb.actions.submit;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.committee.bo.CommitteeMembership;
import org.kuali.kra.committee.service.CommitteeService;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolForm;
import org.kuali.kra.irb.actions.ActionHelper;
import org.kuali.kra.irb.protocol.funding.ProtocolFundingSourceServiceImpl;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.service.BusinessObjectService;

public class ProtocolActionAjaxServiceImpl implements ProtocolActionAjaxService {

    private static final Log LOG = LogFactory.getLog(ProtocolActionAjaxServiceImpl.class);
    private CommitteeService committeeService;
    private BusinessObjectService businessObjectService;

    /**
     * Inject the Committee Service.
     * 
     * @param committeeService the Committee Service
     */
    public void setCommitteeService(CommitteeService committeeService) {
        this.committeeService = committeeService;
    }

    /**
     * Inject the Business Object Service.
     * 
     * @param businessObjectService the Business Object Service
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    /**
     * @see org.kuali.kra.irb.actions.submit.ProtocolActionAjaxService#getValidCommitteeDates(java.lang.String)
     */
    public String getValidCommitteeDates(String committeeId, String protocolNumber) {
        StringBuffer ajaxList = new StringBuffer();
        if (isAuthorizedToAccess(protocolNumber)) {
            List<KeyValue> dates = committeeService.getAvailableCommitteeDates(committeeId);
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
// don't think the following is necessary... since html controls won't show unless user has authority        
//        UserSession userSession = GlobalVariables.getUserSession();
//        if (userSession != null) {
//            // TODO : this is a quick hack for KC 3.1.1 to provide authorization check for dwr/ajax call. dwr/ajax will be replaced by
//            // jquery/ajax in rice 2.0
//            isAuthorized = ActionHelper.hasAssignCmtSchedPermission(userSession.getPrincipalName(), protocolNumber);
//        } else {
//            // TODO : it seemed that tomcat has this issue intermittently ?
//            LOG.info("Attention: dwr/ajax STILL does not have session ");
//        }
        return isAuthorized;
    }

    /**
     * @see org.kuali.kra.irb.actions.submit.ProtocolActionAjaxService#getReviewers(java.lang.String, java.lang.String)
     */
    public String getReviewers(String protocolId, String committeeId, String scheduleId, String docFormKey) {
        StringBuffer ajaxList = new StringBuffer();
        if (isAuthorizedToAccess(docFormKey)) {
            HashMap<String, String> hm = new HashMap<String, String>();
            hm.put("protocolId", protocolId);
            Protocol protocol = (Protocol) (this.businessObjectService.findMatching(Protocol.class, hm).toArray())[0];
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
        }
        return clipLastChar(ajaxList);
    }


    /**
     * @see org.kuali.kra.irb.actions.submit.ProtocolActionAjaxService#getReviewerTypes()
     */
    public String getReviewerTypes() {
        StringBuffer ajaxList = new StringBuffer();
        Collection<ProtocolReviewerType> reviewerTypes = getReviewerTypesFromDatabase();
        for (ProtocolReviewerType reviewerType : reviewerTypes) {
            ajaxList.append(reviewerType.getReviewerTypeCode() + ";" + reviewerType.getDescription() + ";");
        }
        return clipLastChar(ajaxList);
    }

    /**
     * Get the Reviewer Types from the database.
     * 
     * @return
     */
    @SuppressWarnings("unchecked")
    protected Collection<ProtocolReviewerType> getReviewerTypesFromDatabase() {
        return businessObjectService.findAll(ProtocolReviewerType.class);
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
