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
package org.kuali.kra.kim.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeMembership;
import org.kuali.kra.irb.Protocol;

import org.kuali.kra.kim.bo.KcKimAttributes;
import org.kuali.rice.kim.bo.Role;
import org.kuali.rice.kim.bo.role.dto.RoleMembershipInfo;
import org.kuali.rice.kim.bo.types.dto.AttributeSet;
import org.kuali.rice.kim.service.support.impl.KimDerivedRoleTypeServiceBase;

/**
 * 
 * This class supports the active committee member on protocol derived role type.  It is intended to support
 * the viewing of protocols by active committee members that are not reviewers.  Please note that the method
 * getRoleMembersFromApplicationRole was not over-ridden as there is currently not workflow items for this
 * particular role.
 */
public class ActiveCommitteeMemberOnProtocolDerivedRoleTypeServiceImpl extends KimDerivedRoleTypeServiceBase {

    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(ActiveCommitteeMemberOnProtocolDerivedRoleTypeServiceImpl.class);   

    protected List<String> requiredAttributes = new ArrayList<String>();
    {
        requiredAttributes.add(KcKimAttributes.PROTOCOL);
    }
    
    /**
     * 
     * @see org.kuali.rice.kim.service.support.impl.KimDerivedRoleTypeServiceBase#getRoleMembersFromApplicationRole(java.lang.String, java.lang.String, org.kuali.rice.kim.bo.types.dto.AttributeSet)
     */
    @Override
    public List<RoleMembershipInfo> getRoleMembersFromApplicationRole(String namespaceCode, String roleName,
            AttributeSet qualification) {
        validateRequiredAttributesAgainstReceived(qualification);
        List<RoleMembershipInfo> members = new ArrayList<RoleMembershipInfo>();

        String protocolNumber = qualification.get(KcKimAttributes.PROTOCOL);
        
        if (StringUtils.isNotBlank(protocolNumber)) {
            Protocol protocol = getProtocolByNumber(protocolNumber);
            if (protocol != null) {
                if (protocol.getProtocolSubmission() != null) {
                    Committee committee = protocol.getProtocolSubmission().getCommittee();
                    if (committee != null) {
                        for (CommitteeMembership membership : committee.getCommitteeMemberships()) {
                            if (membership.getPersonId()!=null && membership.isActive()) {
                                members.add(new RoleMembershipInfo(null, null, membership.getPersonId(), Role.PRINCIPAL_MEMBER_TYPE, null));
                            }
                        }
                    }
                }
            }
        }
        
        return members;
    }
    
    /**
     * 
     * @see org.kuali.rice.kim.service.support.impl.KimRoleTypeServiceBase#hasApplicationRole(java.lang.String, java.util.List, java.lang.String, java.lang.String, org.kuali.rice.kim.bo.types.dto.AttributeSet)
     */
    @Override
    public boolean hasApplicationRole(String principalId, List<String> groupIds, String namespaceCode, String roleName,
            AttributeSet qualification) {
        validateRequiredAttributesAgainstReceived(qualification);

        String protocolNumber = qualification.get(KcKimAttributes.PROTOCOL);
        
        if (StringUtils.isNotBlank(protocolNumber)) {
            Protocol protocol = getProtocolByNumber(protocolNumber);
            if (protocol != null) {
                if (protocol.getProtocolSubmission() != null) {
                    Committee committee = protocol.getProtocolSubmission().getCommittee();
                    if (committee != null) {
                        for (CommitteeMembership membership : committee.getCommitteeMemberships()) {
                            if (membership.getPersonId()!=null && StringUtils.equals(principalId, membership.getPersonId())) {
                                return true;
                            }
                        }
                    }
                }
            }

        }
        
        return false;
    }
    
    private Protocol getProtocolByNumber(String protocolNumber) {
        Map<String,Object> keymap = new HashMap<String,Object>();
        keymap.put( "protocolNumber", protocolNumber);
        return (Protocol)getBusinessObjectService().findByPrimaryKey(Protocol.class, keymap );    
    }

}
