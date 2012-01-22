/*
 * Copyright 2005-2010 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.workflow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.irb.onlinereview.ProtocolOnlineReview;
import org.kuali.kra.irb.onlinereview.ProtocolOnlineReviewService;
import org.kuali.kra.kim.bo.KcKimAttributes;
import org.kuali.rice.core.api.membership.MemberType;
import org.kuali.rice.kim.api.role.RoleMembership;
import org.kuali.rice.kim.api.role.RoleService;
import org.kuali.rice.kns.kim.role.DerivedRoleTypeServiceBase;
import org.kuali.rice.krad.service.BusinessObjectService;

/**
 * 
 * This class provides some workflow routing logic for the protocol online review documents
 */
public class ProtocolOnlineReviewDerivedRoleTypeServiceImpl extends DerivedRoleTypeServiceBase {
  
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
		    for (ProtocolOnlineReview pReview : getProtocolOnlineReviewService().getProtocolReviews(submissionId)) {
    		    if (!pReview.getProtocolReviewer().getNonEmployeeFlag() && reviewId.equals(pReview.getProtocolOnlineReviewId())) {
    		        pReview.refresh();
    		        members.add(RoleMembership.Builder.create(null, null, pReview.getProtocolReviewer().getPersonId(), MemberType.PRINCIPAL, null).build() );
    		    }
    		}
		    
		    //Per KCIRB-1376: Add IRB Admins as review comment approvers if they have a role qualifier that matches the protocol lead unit 
		    List<RoleMembership> adminMembers = getIRBAdmins(protocolLeadUnitNumber);
		    for(RoleMembership member : adminMembers) {
                members.add(RoleMembership.Builder.create(null, null, member.getMemberId(), member.getType(), null).build() );
		    }
		}
		    
		return members;
	}

	/**
	 * 
	 * @see org.kuali.rice.kns.kim.role.RoleTypeServiceBase#hasDerivedRole(java.lang.String, java.util.List, java.lang.String, java.lang.String, org.kuali.rice.core.util.AttributeSet)
	 */
	@Override
	public boolean hasDerivedRole(
	        String principalId, List<String> groupIds, String namespaceCode, String roleName, Map<String,String> qualification){
	        validateRequiredAttributesAgainstReceived(qualification);
	        //a principal has the role if they have an online review for the protocol.
	        String protocolNumber = qualification.get(KcKimAttributes.PROTOCOL);
	        List<ProtocolOnlineReview> reviews = getProtocolOnlineReviewService().getProtocolReviews(protocolNumber);
	        for( ProtocolOnlineReview review : reviews ) {
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
    private List<RoleMembership> getIRBAdmins(String leadUnitNumber) {    
        List<String> roleIds = new ArrayList<String>();
        String roleId = getRoleService().getRoleIdByNameAndNamespaceCode(RoleConstants.DEPARTMENT_ROLE_TYPE, RoleConstants.IRB_ADMINISTRATOR);
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
    
}
