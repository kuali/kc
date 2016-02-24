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
package org.kuali.kra.iacuc.workflow;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.kim.bo.KcKimAttributes;
import org.kuali.kra.protocol.onlinereview.ProtocolOnlineReviewBase;
import org.kuali.kra.protocol.onlinereview.ProtocolOnlineReviewService;
import org.kuali.rice.core.api.membership.MemberType;
import org.kuali.rice.kim.api.role.RoleMembership;
import org.kuali.rice.kim.api.role.RoleService;
import org.kuali.rice.kns.kim.role.DerivedRoleTypeServiceBase;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IacucProtocolOnlineReviewDerivedRoleTypeServiceImpl  extends DerivedRoleTypeServiceBase {
  
    private ProtocolOnlineReviewService protocolOnlineReviewService;
    private BusinessObjectService businessObjectService;
    private RoleService roleManagementService;
    
    protected List<String> requiredAttributes = new ArrayList<String>();
    {
        requiredAttributes.add(KcKimAttributes.PROTOCOL);
    }
    
    /**
     * This method returns members for a given application role
     * @see org.kuali.rice.kns.kim.role.DerivedRoleTypeServiceBase#getRoleMembersFromDerivedRole(java.lang.String, java.lang.String, org.kuali.rice.core.util.AttributeSet)
     */
    @Override
    public List<RoleMembership> getRoleMembersFromDerivedRole(String namespaceCode, String roleName, Map<String,String> qualification) {
        validateRequiredAttributesAgainstReceived(qualification);
        
        List<RoleMembership> members = new ArrayList<RoleMembership>();
        String qualificationSubmissionId = qualification.get("submissionId");
        String qualificationReviewId = qualification.get("protocolOnlineReviewId");
        String protocolLeadUnitNumber = qualification.get("protocolLeadUnitNumber");

        //If the protocol has been submitted for review, get members with the appropriate role
        if (NumberUtils.isNumber(qualificationSubmissionId) && NumberUtils.isNumber(qualificationReviewId)) {
            Long submissionId = Long.parseLong(qualificationSubmissionId);
            Long reviewId = Long.parseLong(qualificationReviewId);
            for (ProtocolOnlineReviewBase pReview : getProtocolOnlineReviewService().getProtocolReviews(submissionId)) {
                if (!pReview.getProtocolReviewer().getNonEmployeeFlag() && reviewId.equals(pReview.getProtocolOnlineReviewId())) {
                    pReview.refresh();
                    members.add(RoleMembership.Builder.create(null, null, pReview.getProtocolReviewer().getPersonId(), MemberType.PRINCIPAL, null).build() );
                }
            }
            
            //Per KCIRB-1376: Add IRB Admins as review comment approvers if they have a role qualifier that matches the protocol lead unit 
            List<RoleMembership> adminMembers = getIACUCAdmins(protocolLeadUnitNumber);
            for(RoleMembership member : adminMembers) {
                members.add(RoleMembership.Builder.create(null, null, member.getMemberId(), member.getType(), null).build() );
            }
        }
            
        return members;
    }

    @Override
    public boolean hasDerivedRole(
            String principalId, List<String> groupIds, String namespaceCode, String roleName, Map<String,String> qualification){
            validateRequiredAttributesAgainstReceived(qualification);
            //a principal has the role if they have an online review for the protocol.
            String protocolNumber = qualification.get(KcKimAttributes.PROTOCOL);
            List<ProtocolOnlineReviewBase> reviews = getProtocolOnlineReviewService().getProtocolReviews(protocolNumber);
            for( ProtocolOnlineReviewBase review : reviews ) {
                if( !review.getProtocolReviewer().getNonEmployeeFlag() && StringUtils.equals( review.getProtocolReviewer().getPersonId(), principalId ) ) {
                    return  true;
                }
            }
            return false;
    }

    public void setProtocolOnlineReviewService(ProtocolOnlineReviewService protocolOnlineReviewService) {
        this.protocolOnlineReviewService = protocolOnlineReviewService;
    }

    public ProtocolOnlineReviewService getProtocolOnlineReviewService() {
        return protocolOnlineReviewService;
    }
    
    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }
    
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    
    @Override
    public List<String> getQualifiersForExactMatch() {
        ArrayList<String> quals = new ArrayList<String>();
        quals.add("protocol");
        quals.add("protocolOnlineReview");
        return quals;
    }
    
    /**
     * 
     * This method retrieves all members who have the role of IRB Administrator.
     * It then compares their role qualifier (home unit) to the lead unit of the 
     * protocol.  If their unit is at or above the protocol unit, they are a
     * qualified member.
     * @param qualification
     * @return a list of qualifying role members
     */
    private List<RoleMembership> getIACUCAdmins(String leadUnitNumber) {    
        List<String> roleIds = new ArrayList<String>();
        String roleId = getRoleService().getRoleIdByNamespaceCodeAndName(RoleConstants.DEPARTMENT_ROLE_TYPE, RoleConstants.IACUC_ADMINISTRATOR);
        roleIds.add(roleId);
        
        Map<String,String> attrSet =new HashMap<String,String>();
        attrSet.put(KcKimAttributes.UNIT_NUMBER, leadUnitNumber);
        
        return getRoleService().getRoleMembers(roleIds, attrSet);
    }  
    
    /**
     * 
     * This method is a convenience method for retrieving the role management service
     * @return a reference to the role service
     */
    private RoleService getRoleService() {
        return roleManagementService;

    }

    public void setRoleManagementService(RoleService roleManagementService) {
        this.roleManagementService = roleManagementService;
    }
    
    /*
     * Should override if derivedRoles should not to be cached.
     */
    @Override
    public boolean dynamicRoleMembership(String namespaceCode, String roleName) {
        super.dynamicRoleMembership(namespaceCode, roleName);
        return true;
    }

}
