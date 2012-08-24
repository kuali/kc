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
import org.kuali.kra.common.committee.bo.CommonCommittee;
import org.kuali.kra.common.committee.bo.CommitteeMembership;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.kim.bo.KcKimAttributes;
import org.kuali.kra.protocol.Protocol;
import org.kuali.rice.core.api.exception.RiceIllegalArgumentException;
import org.kuali.rice.core.api.membership.MemberType;
import org.kuali.rice.kim.api.role.RoleMembership;
import org.kuali.rice.kns.kim.role.DerivedRoleTypeServiceBase;

public class ActiveCommitteeMemberOnIacucProtocolDerivedRoleTypeServiceImpl extends DerivedRoleTypeServiceBase {

    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(ActiveCommitteeMemberOnIacucProtocolDerivedRoleTypeServiceImpl.class);   

    protected List<String> requiredAttributes = new ArrayList<String>();
    {
        requiredAttributes.add(KcKimAttributes.PROTOCOL);
    }
    
    /**
     * 
     * @see org.kuali.rice.kns.kim.role.DerivedRoleTypeServiceBase#getRoleMembersFromDerivedRole(java.lang.String, java.lang.String, org.kuali.rice.core.util.AttributeSet)
     */
    @Override
    public List<RoleMembership> getRoleMembersFromDerivedRole(String namespaceCode, String roleName,
            Map<String,String> qualification) {
        validateRequiredAttributesAgainstReceived(qualification);
        List<RoleMembership> members = new ArrayList<RoleMembership>();

        String protocolNumber = qualification.get(KcKimAttributes.PROTOCOL);
        
        if (StringUtils.isNotBlank(protocolNumber)) {
            Protocol protocol = getProtocolByNumber(protocolNumber);
            if (protocol != null) {
                if (protocol.getProtocolSubmission() != null) {
                    CommonCommittee committee = protocol.getProtocolSubmission().getCommittee();
                    if (committee != null) {
                        for (CommitteeMembership membership : committee.getCommitteeMemberships()) {
                            if (membership.getPersonId()!=null && membership.isActive()) {
                                members.add(RoleMembership.Builder.create(null, null, membership.getPersonId(), MemberType.PRINCIPAL, null).build());
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
     * @see org.kuali.rice.kns.kim.role.RoleTypeServiceBase#hasDerivedRole(java.lang.String, java.util.List, java.lang.String, java.lang.String, org.kuali.rice.core.util.AttributeSet)
     */
    @Override
    public boolean hasDerivedRole(String principalId, List<String> groupIds, String namespaceCode, String roleName,
            Map<String,String> qualification) {
        validateRequiredAttributesAgainstReceived(qualification);

        String protocolNumber = qualification.get(KcKimAttributes.PROTOCOL);
        
        if (StringUtils.isNotBlank(protocolNumber)) {
            Protocol protocol = getProtocolByNumber(protocolNumber);
            if (protocol != null) {
                if (protocol.getProtocolSubmission() != null) {
                    CommonCommittee committee = protocol.getProtocolSubmission().getCommittee();
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
        // TODO : this seems buggy.  protocolnumber is not a PK.  need to refactor this
        Map<String,Object> keymap = new HashMap<String,Object>();
        keymap.put( "protocolNumber", protocolNumber);
        return (Protocol)getBusinessObjectService().findByPrimaryKey(IacucProtocol.class, keymap );    
    }

    /*
     * Should override if derivedRoles should not to be cached.  Currently defaults to system-wide default.
     */
    @Override
    public boolean dynamicRoleMembership(String namespaceCode, String roleName) {
        super.dynamicRoleMembership(namespaceCode, roleName);
        return true;
    }

}
