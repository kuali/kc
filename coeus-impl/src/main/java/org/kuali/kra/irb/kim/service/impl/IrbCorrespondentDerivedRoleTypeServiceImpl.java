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
