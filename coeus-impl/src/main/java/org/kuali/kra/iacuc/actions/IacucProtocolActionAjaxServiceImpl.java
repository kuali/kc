/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.kra.iacuc.actions;


import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.committee.impl.bo.CommitteeMembershipBase;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolReviewType;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolReviewerType;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.actions.ProtocolActionAjaxServiceImplBase;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;

import java.util.HashMap;
import java.util.List;

public class IacucProtocolActionAjaxServiceImpl extends ProtocolActionAjaxServiceImplBase implements IacucProtocolActionAjaxService {
    
    private ParameterService parameterService;
    
    public static final String DEFAULT_REVIEW_TYPE_PARAMETER_NAME = "IACUC_ALL_COMM_REVIEWERS_DEFAULT_ASSIGNED";

    public Class<? extends ProtocolBase> getProtocolClassHook() {
        return IacucProtocol.class;
    }

    @Override
    public Class getProtocolReviewerTypeClassHook() {
        return IacucProtocolReviewerType.class;
    }
    
    @Override
    public String getDefaultCommitteeReviewTypeCode() {
        String paramVal = this.getParameterService().getParameterValueAsString("KC-IACUC", "Document", DEFAULT_REVIEW_TYPE_PARAMETER_NAME);
        return paramVal;
    }

    public ParameterService getParameterService() {
        return parameterService;
    }

    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }
    
    public String getReviewers(String protocolId, String committeeId, String scheduleId) {
        StringBuffer ajaxList = new StringBuffer();

        HashMap<String, String> criteria = new HashMap<String, String>();
        criteria.put("protocolId", protocolId);
        ProtocolBase protocol = (ProtocolBase) (getBusinessObjectService().findMatching(IacucProtocol.class, criteria).toArray())[0];
        
        /*
         * no reviewers should be assigned if schedule not chosen.
         */
        if (!StringUtils.isBlank(scheduleId)) {
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
        }
        return clipLastChar(ajaxList);
    }

    public String getModifySubmissionProtocolReviewers(String protocolId, String committeeId, String scheduleId, String protocolReviewTypeCode) {
        StringBuffer ajaxList = new StringBuffer();

        HashMap<String, String> criteria = new HashMap<String, String>();
        criteria.put("protocolId", protocolId);
        ProtocolBase protocol = (ProtocolBase) (getBusinessObjectService().findByPrimaryKey(IacucProtocol.class, criteria));        
        /*
         * no reviewers should be assigned if schedule not chosen for a Full committee review
         */
        if ( !(StringUtils.isBlank(scheduleId) & StringUtils.equals(protocolReviewTypeCode, IacucProtocolReviewType.FULL_COMMITTEE_REVIEW)) ) {
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
        }
        return clipLastChar(ajaxList);
    }


}
