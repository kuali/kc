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
package org.kuali.kra.iacuc.onlinereview;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.committee.bo.CommitteeMembership;
import org.kuali.kra.iacuc.IacucProtocolAction;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.protocol.Protocol;
import org.kuali.kra.protocol.ProtocolForm;
import org.kuali.kra.protocol.ProtocolOnlineReviewDocument;
import org.kuali.kra.protocol.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.notification.AssignReviewerNotificationRenderer;
import org.kuali.kra.irb.actions.notification.ProtocolNotificationRequestBean;
import org.kuali.kra.protocol.actions.submit.ProtocolReviewer;
import org.kuali.kra.irb.actions.submit.ProtocolReviewerBean;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmission;
import org.kuali.kra.protocol.onlinereview.OnlineReviewsActionHelper;
import org.kuali.kra.protocol.onlinereview.ProtocolOnlineReview;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.util.GlobalVariables;

public class IacucProtocolOnlineReviewAction extends IacucProtocolAction {

    
    
    public ActionForward createOnlineReview(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ProtocolForm protocolForm = (ProtocolForm) form;
        OnlineReviewsActionHelper onlineReviewHelper = protocolForm.getOnlineReviewsActionHelper();

//        if (validateCreateNewProtocolOnlineReview(protocolForm)) {
            CommitteeMembership membership
                = getBusinessObjectService().findBySinglePrimaryKey(CommitteeMembership.class, onlineReviewHelper.getNewProtocolReviewCommitteeMembershipId());
            ProtocolReviewerBean bean = new ProtocolReviewerBean(membership);
            
            String principalId = bean.getPersonId();
            boolean nonEmployeeFlag = bean.getNonEmployeeFlag();
            //String reviewerTypeCode = StringUtils.isEmpty(bean.getReviewerTypeCode()) ? ProtocolReviewerType.PRIMARY : bean.getReviewerTypeCode();
            String reviewerTypeCode = onlineReviewHelper.getNewReviewerTypeCode();
            ProtocolSubmission submission = protocolForm.getProtocolDocument().getProtocol().getProtocolSubmission();
            ProtocolReviewer reviewer = getProtocolOnlineReviewService().createProtocolReviewer(principalId, nonEmployeeFlag, reviewerTypeCode, submission);
            
            ProtocolOnlineReviewDocument document = getProtocolOnlineReviewService().createAndRouteProtocolOnlineReviewDocument(submission, reviewer, 
                    onlineReviewHelper.getNewReviewDocumentDescription(), onlineReviewHelper.getNewReviewExplanation(), 
                    onlineReviewHelper.getNewReviewOrganizationDocumentNumber(), null, true, onlineReviewHelper.getNewReviewDateRequested(), 
                    onlineReviewHelper.getNewReviewDateDue(), GlobalVariables.getUserSession().getPrincipalId());

            protocolForm.getOnlineReviewsActionHelper().init(true);
            recordOnlineReviewActionSuccess("created", document);
            
            //send notification now that the online review has been created.
            Protocol protocol = submission.getProtocol();
            ProtocolOnlineReview protocolOnlineReview = document.getProtocolOnlineReview();
            // TODO : IACUC
        //    AssignReviewerNotificationRenderer renderer = new AssignReviewerNotificationRenderer(protocol, "added");
//            IRBNotificationContext context = new IRBNotificationContext(protocol, protocolOnlineReview, ProtocolActionType.ASSIGN_REVIEWER, "Assign Reviewer", renderer);
//            getKcNotificationService().sendNotification(context);
//            return checkToSendNotification(mapping, mapping.findForward(PROTOCOL_OLR_TAB), protocolForm, renderer, new ProtocolNotificationRequestBean(protocol, protocolOnlineReview, ProtocolActionType.ASSIGN_REVIEWER, "Assign Reviewer", null, null));
//        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    protected void recordOnlineReviewActionSuccess(String onlineReviewActionName, ProtocolOnlineReviewDocument document) {
        String documentInfo = String.format("document number:%s, reviewer:%s", document.getDocumentNumber(), document.getProtocolOnlineReview().getProtocolReviewer().getFullName());
        KNSGlobalVariables.getMessageList().add(KeyConstants.MESSAGE_ONLINE_REVIEW_ACTION_SUCCESSFULLY_COMPLETED,onlineReviewActionName, documentInfo);
    }

}
