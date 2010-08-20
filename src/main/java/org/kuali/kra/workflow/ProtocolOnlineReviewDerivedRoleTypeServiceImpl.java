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
import org.kuali.kra.irb.actions.submit.ProtocolReviewer;
import org.kuali.kra.irb.onlinereview.ProtocolOnlineReview;
import org.kuali.kra.irb.onlinereview.ProtocolOnlineReviewService;
import org.kuali.kra.kim.bo.KcKimAttributes;
import org.kuali.rice.kew.service.WorkflowInfo;
import org.kuali.rice.kim.bo.Role;
import org.kuali.rice.kim.bo.role.dto.RoleMembershipInfo;
import org.kuali.rice.kim.bo.types.dto.AttributeSet;
import org.kuali.rice.kim.service.support.impl.KimDerivedRoleTypeServiceBase;
import org.kuali.rice.kns.service.BusinessObjectService;

public class ProtocolOnlineReviewDerivedRoleTypeServiceImpl extends KimDerivedRoleTypeServiceBase {
  
    private static final Map<String, String> proposalRoleCodeConsants = new HashMap<String, String>();
    private ProtocolOnlineReviewService protocolOnlineReviewService;
    private BusinessObjectService businessObjectService;
    
	protected WorkflowInfo workflowInfo = new WorkflowInfo();
    
	protected List<String> requiredAttributes = new ArrayList<String>();
	{
		requiredAttributes.add(KcKimAttributes.PROTOCOL);
		//requiredAttributes.add(KcKimAttributes.SUBMISSION_ID);
	}
	
	@Override
    public List<RoleMembershipInfo> getRoleMembersFromApplicationRole(String namespaceCode, String roleName, AttributeSet qualification) {
		validateRequiredAttributesAgainstReceived(qualification);
		
		List<RoleMembershipInfo> members = new ArrayList<RoleMembershipInfo>();
		
		String protocolNumber = qualification.get(KcKimAttributes.PROTOCOL);
		Long submissionId = Long.parseLong(qualification.get("submissionId"));
		
		String reviewId = qualification.get("protocolOnlineReviewId");
		Map<String,Object> primaryKeys = new HashMap<String,Object>();
		
		primaryKeys.put("protocolReviewerId", qualification.get("protocolReviewerId"));
		ProtocolReviewer reviewer = (ProtocolReviewer) businessObjectService.findByPrimaryKey(ProtocolReviewer.class, primaryKeys);
		
		for( ProtocolOnlineReview pReview : getProtocolOnlineReviewService().getOnlineReviewersForProtocolSubmission(submissionId)) {
		    if( !pReview.getProtocolReviewer().getNonEmployeeFlag() && 
		            (StringUtils.equals(reviewId,pReview.getProtocolOnlineReviewId()+"") ) ) {
		        pReview.refresh();
		        members.add( new RoleMembershipInfo(null, null, pReview.getProtocolReviewer().getPersonId(), Role.PRINCIPAL_MEMBER_TYPE, null) );
		    }
		}
		    
		return members;
	}

	@Override
	public boolean hasApplicationRole(
	        String principalId, List<String> groupIds, String namespaceCode, String roleName, AttributeSet qualification){
	        validateRequiredAttributesAgainstReceived(qualification);
	        //a principal has the role if they have an online review for the protocol.
	        String protocolNumber = qualification.get(KcKimAttributes.PROTOCOL);
	        List<ProtocolOnlineReview> reviews = getProtocolOnlineReviewService().getProtocolReviewsForCurrentSubmission(protocolNumber);
	        for( ProtocolOnlineReview review : reviews ) {
	            if( StringUtils.equals( review.getProtocolReviewer().getPersonId(), principalId ) ) {
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
    
}
