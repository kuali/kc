/*
un * Copyright 2005-2010 The Kuali Foundation
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
import org.kuali.kra.bo.OrganizationCorrespondent;
import org.kuali.kra.bo.UnitCorrespondent;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.summary.OrganizationSummary;
import org.kuali.kra.irb.summary.ProtocolSummary;
import org.kuali.kra.kim.bo.KcKimAttributes;
import org.kuali.kra.service.OrganizationService;
import org.kuali.kra.service.UnitService;
import org.kuali.rice.kim.bo.Role;
import org.kuali.rice.kim.bo.role.dto.RoleMembershipInfo;
import org.kuali.rice.kim.bo.types.dto.AttributeSet;
import org.kuali.rice.kim.service.support.KimRoleTypeService;
import org.kuali.rice.kim.service.support.impl.KimDerivedRoleTypeServiceBase;

/**
 * Checks whether the principal is an IrbCorrespondent for the given Organization ID.
 */
public class IrbCorrespondentDerivedRoleTypeServiceImpl extends KimDerivedRoleTypeServiceBase implements KimRoleTypeService {
    
    private OrganizationService organizationService;
    private UnitService unitService;

    private final String ROLE_NAME_ORGANIZATION_CORRESPONDENT = "Organization Correspondent";
    private final String ROLE_NAME_UNIT_CORRESPONDENT = "Unit Correspondent";
    protected List<String> requiredAttributes = new ArrayList<String>();
    {
        requiredAttributes.add(KcKimAttributes.PROTOCOL);
    }
    
    @Override
    public boolean hasApplicationRole(String principalId, List<String> groupIds, String namespaceCode, 
                                      String roleName, AttributeSet qualification) {
        if (ROLE_NAME_ORGANIZATION_CORRESPONDENT.equals(roleName)) {
            return hasApplicationRoleOrganization(principalId, groupIds, namespaceCode, roleName, qualification);
        } else if (ROLE_NAME_UNIT_CORRESPONDENT.equals(roleName)) {
            return hasApplicationRoleUnit(principalId, groupIds, namespaceCode, roleName, qualification);
        }
        return false;
    }
    
    public boolean hasApplicationRoleOrganization(String principalId, List<String> groupIds, String namespaceCode, 
                                                  String roleName, AttributeSet qualification) {

        String protocolNumber = qualification.get(KcKimAttributes.PROTOCOL);
        Protocol protocol = getProtocolByNumber(protocolNumber);
        String organizationId = protocol.getPerformingOrganizationId();
        if (StringUtils.isNotBlank(organizationId)) {
            List<OrganizationCorrespondent> organizationCorrespondents = getOrganizationService().retrieveOrganizationCorrespondentsByOrganizationId(organizationId);
            for (OrganizationCorrespondent organizationCorrespondent : organizationCorrespondents) {
                if (organizationCorrespondent.getPersonId().equals(principalId)) {
                    return true;
                }
            }
        }

        return false;
    }
    
    public boolean hasApplicationRoleUnit(String principalId, List<String> groupIds, String namespaceCode, 
                                          String roleName, AttributeSet qualification) {

        String protocolNumber = qualification.get(KcKimAttributes.PROTOCOL);
        Protocol protocol = getProtocolByNumber(protocolNumber);
        String unitNumber = protocol.getLeadUnitNumber();
        if (StringUtils.isNotBlank(unitNumber)) {
            List<UnitCorrespondent> unitCorrespondents = getUnitService().retrieveUnitCorrespondentsByUnitNumber(unitNumber);
            for (UnitCorrespondent unitCorrespondent : unitCorrespondents) {
                if (unitCorrespondent.getPersonId().equals(principalId)) {
                    return true;
                }
            }
        }
        return false;
    }
        
        
    @Override
    public List<RoleMembershipInfo> getRoleMembersFromApplicationRole(String namespaceCode, String roleName, AttributeSet qualification) {
        validateRequiredAttributesAgainstReceived(qualification);
        String protocolNumber = qualification.get(KcKimAttributes.PROTOCOL);
        if (protocolNumber != null) {
            Protocol protocol = getProtocolByNumber(protocolNumber);
            System.out.println("    protocolNumber = " + protocolNumber + ", protocol = " + protocol);
            if (ROLE_NAME_ORGANIZATION_CORRESPONDENT.equals(roleName)) {
                return getRoleMembersFromApplicationRoleOrganization(protocol, namespaceCode, roleName, qualification);
            } else if (ROLE_NAME_UNIT_CORRESPONDENT.equals(roleName)) {
                return getRoleMembersFromApplicationRoleUnit(protocol, namespaceCode, roleName, qualification);
            }
        }
        return new ArrayList<RoleMembershipInfo>();
    }

    public List<RoleMembershipInfo> getRoleMembersFromApplicationRoleOrganization(Protocol protocol, String namespaceCode, String roleName, AttributeSet qualification) {
        ProtocolSummary protocolSummary = protocol.getProtocolSummary();
        List<RoleMembershipInfo> members = new ArrayList<RoleMembershipInfo>();
        
        for (OrganizationSummary orgSummary: protocolSummary.getOrganizations()) {
            String organizationId = orgSummary.getId();
            if (StringUtils.isNotBlank(organizationId)) {
                List<OrganizationCorrespondent> organizationCorrespondents = getOrganizationService().retrieveOrganizationCorrespondentsByOrganizationId(organizationId);
                for ( OrganizationCorrespondent organizationCorrespondent : organizationCorrespondents ) {
                    if ( StringUtils.isNotBlank(organizationCorrespondent.getPersonId()) ) {
                        members.add( new RoleMembershipInfo(null, null, organizationCorrespondent.getPersonId(), Role.PRINCIPAL_MEMBER_TYPE, null) );
                    }
                }
            }
        }
            
        return members;
    }

    public List<RoleMembershipInfo> getRoleMembersFromApplicationRoleUnit(Protocol protocol, String namespaceCode, String roleName, AttributeSet qualification) {
        String unitNumber = protocol.getLeadUnitNumber();
        List<RoleMembershipInfo> members = new ArrayList<RoleMembershipInfo>();
        
        if (StringUtils.isNotBlank(unitNumber)) {
            List<UnitCorrespondent> unitCorrespondents = getUnitService().retrieveUnitCorrespondentsByUnitNumber(unitNumber);
            for ( UnitCorrespondent unitCorrespondent : unitCorrespondents ) {
                if ( StringUtils.isNotBlank(unitCorrespondent.getPersonId()) ) {
                    members.add( new RoleMembershipInfo(null, null, unitCorrespondent.getPersonId(), Role.PRINCIPAL_MEMBER_TYPE, null) );
                }
            }
        }
            
        return members;
    }
    
    private UnitService getUnitService() {
        if (unitService == null) {
            unitService = (UnitService) KraServiceLocator.getService(UnitService.class);
        }
        return unitService;
    }

    private OrganizationService getOrganizationService() {
        if (organizationService == null) {
            organizationService = (OrganizationService) KraServiceLocator.getService(OrganizationService.class);
        }
        return organizationService;
    }

    private Protocol getProtocolByNumber(String protocolNumber) {
        Map<String,Object> keymap = new HashMap<String,Object>();
        keymap.put( "protocolNumber", protocolNumber);
        return (Protocol)getBusinessObjectService().findByPrimaryKey(Protocol.class, keymap );    
    }

}
