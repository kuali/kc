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
package org.kuali.kra.kim.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.committee.impl.bo.CommitteeBase;
import org.kuali.coeus.common.committee.impl.bo.CommitteeMembershipBase;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.kim.bo.KcKimAttributes;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.personnel.ProtocolPersonBase;
import org.kuali.rice.core.api.membership.MemberType;
import org.kuali.rice.kim.api.role.RoleMembership;
import org.kuali.rice.kns.kim.role.DerivedRoleTypeServiceBase;

public class ActiveCommitteeMemberOnIacucProtocolDerivedRoleTypeServiceImpl extends DerivedRoleTypeServiceBase {

    protected List<String> requiredAttributes = new ArrayList<String>();
    {
        requiredAttributes.add(KcKimAttributes.PROTOCOL);
    }

    @Override
    public List<RoleMembership> getRoleMembersFromDerivedRole(String namespaceCode, String roleName,
            Map<String,String> qualification) {
        validateRequiredAttributesAgainstReceived(qualification);
        List<RoleMembership> members = new ArrayList<RoleMembership>();

        String protocolNumber = qualification.get(KcKimAttributes.PROTOCOL);
        
        if (StringUtils.isNotBlank(protocolNumber)) {
            ProtocolBase protocol = getProtocolByNumber(protocolNumber);
            if (protocol != null) {
                if (protocol.getProtocolSubmission() != null) {
                    CommitteeBase<?, ?, ?> committee = protocol.getProtocolSubmission().getCommittee();
                    if (committee != null) {
                        for (CommitteeMembershipBase membership : committee.getCommitteeMemberships()) {
                            if (membership.getPersonId()!=null && membership.isActive() && isMemberInProtocol(protocol, membership.getPersonId())) {
                                members.add(RoleMembership.Builder.create(null, null, membership.getPersonId(), MemberType.PRINCIPAL, null).build());
                            }
                        }
                    }
                }
            }
        }
        
        return members;
    }

    @Override
    public boolean hasDerivedRole(String principalId, List<String> groupIds, String namespaceCode, String roleName,
            Map<String,String> qualification) {
        validateRequiredAttributesAgainstReceived(qualification);

        String protocolNumber = qualification.get(KcKimAttributes.PROTOCOL);
        
        if (StringUtils.isNotBlank(protocolNumber)) {
            ProtocolBase protocol = getProtocolByNumber(protocolNumber);
            if (protocol != null) {
                if (protocol.getProtocolSubmission() != null) {
                    CommitteeBase<?, ?, ?> committee = protocol.getProtocolSubmission().getCommittee();
                    if (committee != null) {
                        for (CommitteeMembershipBase membership : committee.getCommitteeMemberships()) {
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
    
    private boolean isMemberInProtocol(ProtocolBase protocol, String memberPersonId) {
        List<ProtocolPersonBase> protocolPersons =  protocol.getProtocolPersons();
        for(ProtocolPersonBase protocolPerson : protocolPersons) {
            if(protocolPerson.getPersonId().equalsIgnoreCase(memberPersonId)) {
                return true;
            }
        }
        return false;
    }
    
    private ProtocolBase getProtocolByNumber(String protocolNumber) {
        // TODO : this seems buggy.  protocolnumber is not a PK.  need to refactor this
        Map<String,Object> keymap = new HashMap<String,Object>();
        keymap.put( "protocolNumber", protocolNumber);
        return (ProtocolBase)getBusinessObjectService().findByPrimaryKey(IacucProtocol.class, keymap );    
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
