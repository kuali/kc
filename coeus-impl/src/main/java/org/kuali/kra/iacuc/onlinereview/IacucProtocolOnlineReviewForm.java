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
package org.kuali.kra.iacuc.onlinereview;

import org.apache.commons.logging.Log;
import org.kuali.coeus.common.framework.auth.perm.KcAuthorizationService;
import org.kuali.coeus.common.framework.auth.task.TaskAuthorizationService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.authorization.KraAuthorizationConstants;
import org.kuali.kra.iacuc.IacucProtocolOnlineReviewDocument;
import org.kuali.kra.iacuc.onlinereview.authorization.IacucProtocolOnlineReviewTask;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.protocol.onlinereview.ProtocolOnlineReviewFormBase;
import org.kuali.rice.core.api.CoreApiServiceLocator;
import org.kuali.rice.kns.web.ui.ExtraButton;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("deprecation")
public class IacucProtocolOnlineReviewForm  extends ProtocolOnlineReviewFormBase  {
    
    

    private static final long serialVersionUID = -5729500189953414964L;
  
    private static org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(IacucProtocolOnlineReviewForm.class);
    
    private static final Map<String,String> ONLINE_REVIEW_APPROVE_BUTTON_MAP;
    
    static {
        ONLINE_REVIEW_APPROVE_BUTTON_MAP = new HashMap<String,String>();
        ONLINE_REVIEW_APPROVE_BUTTON_MAP.put(Constants.IACUC_ONLINE_REVIEW_ROUTE_NODE_ADMIN_INITIAL_REVIEW, "buttonsmall_send_review_request.gif");
        ONLINE_REVIEW_APPROVE_BUTTON_MAP.put(Constants.IACUC_ONLINE_REVIEW_ROUTE_NODE_ADMIN_REVIEW,"buttonsmall_accept_review_comments.gif");
        ONLINE_REVIEW_APPROVE_BUTTON_MAP.put(Constants.IACUC_ONLINE_REVIEW_ROUTE_NODE_ONLINE_REVIEWER, "buttonsmall_approve_this_review.gif");
    }
    
    public IacucProtocolOnlineReviewForm() throws Exception {
        super();
    }

   
    @Override
    protected String getDefaultDocumentTypeName() {
        return "IacucProtocolOnlineReviewDocument";
    }

    
    @Override
    protected String getLockRegion() {
        return KraAuthorizationConstants.LOCK_DESCRIPTOR_IACUC_PROTOCOL;
    }


    public List<ExtraButton> getExtraActionsButtons() {
        // clear out the extra buttons array
        extraButtons.clear();
        IacucProtocolOnlineReviewDocument doc = (IacucProtocolOnlineReviewDocument) this.getProtocolOnlineReviewDocument();
        String externalImageURL = Constants.KRA_EXTERNALIZABLE_IMAGES_URI_KEY;

        
        TaskAuthorizationService tas = KcServiceLocator.getService(TaskAuthorizationService.class);
               
        if( tas.isAuthorized(GlobalVariables.getUserSession().getPrincipalId(), new IacucProtocolOnlineReviewTask("rejectIacucProtocolOnlineReview",doc))
                && doc.getDocumentHeader().getWorkflowDocument().isEnroute()
                && IacucProtocolOnlineReviewStatus.FINAL_STATUS_CD.equals(doc.getProtocolOnlineReview().getProtocolOnlineReviewStatusCode())) {
            String resubmissionImage = CoreApiServiceLocator.getKualiConfigurationService().getPropertyValueAsString(externalImageURL) + "buttonsmall_return_to_reviewer.gif";
            addExtraButton("methodToCall.rejectOnlineReview", resubmissionImage, "Return to reviewer");
        }
        
        return extraButtons;
    }
    
    
    public boolean getAdminFieldsEditable() {
        return KcServiceLocator.getService(KcAuthorizationService.class).hasPermission(GlobalVariables.getUserSession().getPrincipalId(),
                                    getProtocolOnlineReviewDocument().getProtocolOnlineReview().getProtocol(), PermissionConstants.MAINTAIN_IACUC_ONLINE_REVIEWS);
    }


    @Override
    protected Map<String, String> getOnlineReviewApproveButtonMapHook() {
        return ONLINE_REVIEW_APPROVE_BUTTON_MAP;
    }


    @Override
    protected Log getLogHook() {
        return LOG;
    }
    
    

}
