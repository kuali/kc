/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.irb.service.impl;

import java.util.Collection;
import java.util.List;

import org.kuali.core.service.BusinessObjectService;
import org.kuali.core.web.ui.KeyLabelPair;
import org.kuali.kra.committee.bo.CommitteeMembership;
import org.kuali.kra.committee.service.CommitteeService;
import org.kuali.kra.irb.bo.ProtocolReviewerType;
import org.kuali.kra.irb.service.ProtocolActionAjaxService;

public class ProtocolActionAjaxServiceImpl implements ProtocolActionAjaxService {

    private CommitteeService committeeService;
    private BusinessObjectService businessObjectService;
    
    /**
     * Inject the Committee Service.
     * @param committeeService the Committee Service
     */
    public void setCommitteeService(CommitteeService committeeService) {
        this.committeeService = committeeService;
    }
    
    /**
     * Inject the Business Object Service.
     * @param businessObjectService the Business Object Service
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    
    /**
     * @see org.kuali.kra.irb.service.ProtocolActionAjaxService#getValidCommitteeDates(java.lang.String)
     */
    public String getValidCommitteeDates(String committeeId) {
        StringBuffer ajaxList = new StringBuffer();
        List<KeyLabelPair> dates = committeeService.getValidCommitteeDates(committeeId);
        for (KeyLabelPair date : dates) {
            ajaxList.append(date.getKey() + ";" + date.getLabel() + ";");
        }
        return clipLastChar(ajaxList);
    }

    /**
     * @see org.kuali.kra.irb.service.ProtocolActionAjaxService#getReviewers(java.lang.String, java.lang.String)
     */
    public String getReviewers(String committeeId, String scheduleId) {
        StringBuffer ajaxList = new StringBuffer();
        List<CommitteeMembership> members = committeeService.getActiveMembers(committeeId, scheduleId);
        for (CommitteeMembership member : members) {
            ajaxList.append(member.getPersonId() + ";" + member.getPersonName() + ";");
        }
        return clipLastChar(ajaxList);
    }
    
    /**
     * @see org.kuali.kra.irb.service.ProtocolActionAjaxService#getReviewerTypes()
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
     * @return
     */
    @SuppressWarnings("unchecked")
    private Collection<ProtocolReviewerType> getReviewerTypesFromDatabase() {
        return businessObjectService.findAll(ProtocolReviewerType.class);
    }
    
    /**
     * Clip the last character from the string buffer.  The last character,
     * if there is one, is always a separator that must be removed.
     * @param ajaxList
     * @return
     */
    private String clipLastChar(StringBuffer ajaxList) {
        if (ajaxList.length() == 0) {
            return ajaxList.toString();
        }
        return ajaxList.substring(0, ajaxList.length() - 1);
    }
}
