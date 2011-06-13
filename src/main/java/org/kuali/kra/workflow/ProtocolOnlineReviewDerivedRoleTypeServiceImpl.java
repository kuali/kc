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
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.irb.onlinereview.ProtocolOnlineReview;
import org.kuali.kra.irb.onlinereview.ProtocolOnlineReviewService;
import org.kuali.kra.kim.bo.KcKimAttributes;
import org.kuali.rice.kew.service.WorkflowInfo;
import org.kuali.rice.kim.bo.Role;
import org.kuali.rice.kim.bo.role.dto.KimRoleInfo;
import org.kuali.rice.kim.bo.role.dto.RoleMembershipInfo;
import org.kuali.rice.kim.bo.types.dto.AttributeSet;
import org.kuali.rice.kim.service.RoleManagementService;
import org.kuali.rice.kim.service.support.impl.KimDerivedRoleTypeServiceBase;
import org.kuali.rice.kns.service.BusinessObjectService;

/**
 * 
 * This class provides some workflow routing logic for the protocol online review documents
 */
public class ProtocolOnlineReviewDerivedRoleTypeServiceImpl extends KimDerivedRoleTypeServiceBase {
  
    private ProtocolOnlineReviewService protocolOnlineReviewService;
    private BusinessObjectService businessObjectService;
    private RoleManagementService roleManagementService;
    
	protected WorkflowInfo workflowInfo = new WorkflowInfo();
    
	protected List<String> requiredAttributes = new ArrayList<String>();
	{
		requiredAttributes.add(KcKimAttributes.PROTOCOL);
		//requiredAttributes.add(KcKimAttributes.SUBMISSION_ID);
	}
	
	/**
	 * This method returns members for a given application role
	 * @see org.kuali.rice.kim.service.support.impl.KimDerivedRoleTypeServiceBase#getRoleMembersFromApplicationRole(java.lang.String, java.lang.String, org.kuali.rice.kim.bo.types.dto.AttributeSet)
	 */
	@Override
    public List<RoleMembershipInfo> getRoleMembersFromApplicationRole(String namespaceCode, String roleName, AttributeSet qualification) {
		validateRequiredAttributesAgainstReceived(qualification);
		
		List<RoleMembershipInfo> members = new ArrayList<RoleMembershipInfo>();
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
    		        members.add(new RoleMembershipInfo(null, null, pReview.getProtocolReviewer().getPersonId(), Role.PRINCIPAL_MEMBER_TYPE, null) );
    		    }
    		}
		    
		    //Per KCIRB-1376: Add IRB Admins as review comment approvers if they have a role qualifier that matches the protocol lead unit 
		    List<RoleMembershipInfo> adminMembers = getIRBAdmins(protocolLeadUnitNumber);
		    for(RoleMembershipInfo member : adminMembers) {
                members.add(new RoleMembershipInfo(null, null, member.getMemberId(), member.getMemberTypeCode(), null) );
		    }
		}
		    
		return members;
	}

	/**
	 * 
	 * @see org.kuali.rice.kim.service.support.impl.KimRoleTypeServiceBase#hasApplicationRole(java.lang.String, java.util.List, java.lang.String, java.lang.String, org.kuali.rice.kim.bo.types.dto.AttributeSet)
	 */
	@Override
	public boolean hasApplicationRole(
	        String principalId, List<String> groupIds, String namespaceCode, String roleName, AttributeSet qualification){
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
    private List<RoleMembershipInfo> getIRBAdmins(String leadUnitNumber) {    
        List<String> roleIds = new ArrayList<String>();
        String roleId = getRoleManagementService().getRoleIdByName(RoleConstants.DEPARTMENT_ROLE_TYPE, RoleConstants.IRB_ADMINISTRATOR);
        roleIds.add(roleId);
        
        AttributeSet attrSet = new AttributeSet();
        attrSet.put(KcKimAttributes.UNIT_NUMBER, leadUnitNumber);
        
        return getRoleManagementService().getRoleMembers(roleIds, attrSet);
    }  
    
    /**
     * 
     * This method is a convenience method for retrieving the role management service
     * @return a reference to the role service
     */
    private RoleManagementService getRoleManagementService() {
        return roleManagementService;

    }

    public void setRoleManagementService(RoleManagementService roleManagementService) {
        this.roleManagementService = roleManagementService;
    }
    
}
