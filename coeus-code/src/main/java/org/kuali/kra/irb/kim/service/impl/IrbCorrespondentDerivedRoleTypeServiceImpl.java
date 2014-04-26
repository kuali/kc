/*
un * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.irb.kim.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.org.OrganizationService;
import org.kuali.coeus.common.framework.org.crrspndnt.OrganizationCorrespondent;
import org.kuali.coeus.common.framework.unit.UnitService;
import org.kuali.coeus.common.framework.unit.crrspndnt.UnitCorrespondent;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.summary.ProtocolSummary;
import org.kuali.kra.kim.bo.KcKimAttributes;
import org.kuali.kra.protocol.protocol.location.ProtocolLocationBase;
import org.kuali.rice.core.api.membership.MemberType;
import org.kuali.rice.kim.api.role.RoleMembership;
import org.kuali.rice.kim.framework.role.RoleTypeService;
import org.kuali.rice.kns.kim.role.DerivedRoleTypeServiceBase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Checks whether the principal is an IrbCorrespondent for the given Organization ID.
 */
public class IrbCorrespondentDerivedRoleTypeServiceImpl extends DerivedRoleTypeServiceBase implements RoleTypeService {
    
    private OrganizationService organizationService;
    private UnitService unitService;

    private final String ROLE_NAME_ORGANIZATION_CORRESPONDENT = "Organization Correspondent";
    private final String ROLE_NAME_UNIT_CORRESPONDENT = "Unit Correspondent";
    private final String PERFORMING_ORG_TYPE_CODE = "1";
    
    protected List<String> requiredAttributes = new ArrayList<String>();
    {
        requiredAttributes.add(KcKimAttributes.PROTOCOL);
    }
    
    @Override
    public boolean hasDerivedRole(String principalId, List<String> groupIds, String namespaceCode, 
                                      String roleName, Map<String,String> qualification) {
        if (ROLE_NAME_ORGANIZATION_CORRESPONDENT.equals(roleName)) {
            return hasApplicationRoleOrganization(principalId, groupIds, namespaceCode, roleName, qualification);
        } else if (ROLE_NAME_UNIT_CORRESPONDENT.equals(roleName)) {
            return hasApplicationRoleUnit(principalId, groupIds, namespaceCode, roleName, qualification);
        }
        return false;
    }
    
    public boolean hasApplicationRoleOrganization(String principalId, List<String> groupIds, String namespaceCode, 
                                                  String roleName, Map<String,String> qualification) {

        String protocolNumber = qualification.get(KcKimAttributes.PROTOCOL);
        Protocol protocol = getProtocolByNumber(protocolNumber);
        if (protocol != null) {
            for (ProtocolLocationBase location : protocol.getProtocolLocations()) {
                if (PERFORMING_ORG_TYPE_CODE.equals(location.getProtocolOrganizationTypeCode())) {
                    List<OrganizationCorrespondent> organizationCorrespondents = getOrganizationService().retrieveOrganizationCorrespondentsByOrganizationId(location.getOrganizationId());
                    for (OrganizationCorrespondent organizationCorrespondent : organizationCorrespondents) {
                        if (organizationCorrespondent.getPersonId().equals(principalId)) {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }
    
    public boolean hasApplicationRoleUnit(String principalId, List<String> groupIds, String namespaceCode, 
                                          String roleName, Map<String,String> qualification) {

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
    public List<RoleMembership> getRoleMembersFromDerivedRole(String namespaceCode, String roleName, Map<String,String> qualification) {
        validateRequiredAttributesAgainstReceived(qualification);
        String protocolNumber = qualification.get(KcKimAttributes.PROTOCOL);
        if (protocolNumber != null) {
            Protocol protocol = getProtocolByNumber(protocolNumber);
            if (ROLE_NAME_ORGANIZATION_CORRESPONDENT.equals(roleName)) {
                return getRoleMembersFromApplicationRoleOrganization(protocol, namespaceCode, roleName, qualification);
            } else if (ROLE_NAME_UNIT_CORRESPONDENT.equals(roleName)) {
                return getRoleMembersFromApplicationRoleUnit(protocol, namespaceCode, roleName, qualification);
            }
        }
        return new ArrayList<RoleMembership>();
    }

    public List<RoleMembership> getRoleMembersFromApplicationRoleOrganization(Protocol protocol, String namespaceCode, String roleName, Map<String,String> qualification) {
        ProtocolSummary protocolSummary = protocol.getProtocolSummary();
        List<RoleMembership> members = new ArrayList<RoleMembership>();
        
        for (org.kuali.kra.protocol.summary.OrganizationSummary orgSummary: protocolSummary.getOrganizations()) {
            String organizationId = orgSummary.getId();
            if (StringUtils.isNotBlank(organizationId)) {
                List<OrganizationCorrespondent> organizationCorrespondents = getOrganizationService().retrieveOrganizationCorrespondentsByOrganizationId(organizationId);
                for ( OrganizationCorrespondent organizationCorrespondent : organizationCorrespondents ) {
                    if ( StringUtils.isNotBlank(organizationCorrespondent.getPersonId()) ) {
                        members.add( RoleMembership.Builder.create(null, null, organizationCorrespondent.getPersonId(), MemberType.PRINCIPAL, null).build() );
                    }
                }
            }
        }
            
        return members;
    }

    public List<RoleMembership> getRoleMembersFromApplicationRoleUnit(Protocol protocol, String namespaceCode, String roleName, Map<String,String> qualification) {
        String unitNumber = protocol.getLeadUnitNumber();
        List<RoleMembership> members = new ArrayList<RoleMembership>();
        
        if (StringUtils.isNotBlank(unitNumber)) {
            List<UnitCorrespondent> unitCorrespondents = getUnitService().retrieveUnitCorrespondentsByUnitNumber(unitNumber);
            for ( UnitCorrespondent unitCorrespondent : unitCorrespondents ) {
                if ( StringUtils.isNotBlank(unitCorrespondent.getPersonId()) ) {
                    members.add( RoleMembership.Builder.create(null, null, unitCorrespondent.getPersonId(), MemberType.PRINCIPAL, null).build() );
                }
            }
        }
            
        return members;
    }
    
    private UnitService getUnitService() {
        if (unitService == null) {
            unitService = (UnitService) KcServiceLocator.getService(UnitService.class);
        }
        return unitService;
    }

    private OrganizationService getOrganizationService() {
        if (organizationService == null) {
            organizationService = (OrganizationService) KcServiceLocator.getService(OrganizationService.class);
        }
        return organizationService;
    }

    private Protocol getProtocolByNumber(String protocolNumber) {
        Map<String,Object> keymap = new HashMap<String,Object>();
        keymap.put( "protocolNumber", protocolNumber);
        keymap.put("active", "Y");
        return (Protocol)getBusinessObjectService().findByPrimaryKey(Protocol.class, keymap );    
    }

    /*
     * Should override if derivedRoles should not to be cached.  Currently defaulting to system-wide default.
     */
    @Override
    public boolean dynamicRoleMembership(String namespaceCode, String roleName) {
        super.dynamicRoleMembership(namespaceCode, roleName);
        return true;
    }
}
