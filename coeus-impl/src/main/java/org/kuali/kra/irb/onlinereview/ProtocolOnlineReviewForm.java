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
package org.kuali.kra.irb.onlinereview;

import org.apache.commons.logging.Log;
import org.kuali.coeus.common.framework.auth.perm.KcAuthorizationService;
import org.kuali.coeus.common.framework.auth.task.TaskAuthorizationService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.authorization.KraAuthorizationConstants;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.irb.ProtocolOnlineReviewDocument;
import org.kuali.kra.irb.onlinereview.authorization.ProtocolOnlineReviewTask;
import org.kuali.kra.protocol.onlinereview.ProtocolOnlineReviewFormBase;
import org.kuali.rice.core.api.CoreApiServiceLocator;
import org.kuali.rice.kns.web.ui.ExtraButton;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProtocolOnlineReviewForm extends ProtocolOnlineReviewFormBase {
    
    private static org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(ProtocolOnlineReviewForm.class);
    private static final Map<String,String> ONLINE_REVIEW_APPROVE_BUTTON_MAP;
    
    static {
        ONLINE_REVIEW_APPROVE_BUTTON_MAP = new HashMap<String,String>();
        ONLINE_REVIEW_APPROVE_BUTTON_MAP.put(Constants.ONLINE_REVIEW_ROUTE_NODE_ADMIN_INITIAL_REVIEW, "buttonsmall_send_review_request.gif");
        ONLINE_REVIEW_APPROVE_BUTTON_MAP.put(Constants.ONLINE_REVIEW_ROUTE_NODE_ADMIN_REVIEW,"buttonsmall_accept_review_comments.gif");
        ONLINE_REVIEW_APPROVE_BUTTON_MAP.put(Constants.ONLINE_REVIEW_ROUTE_NODE_ONLINE_REVIEWER, "buttonsmall_approve_this_review.gif");
    }

    public ProtocolOnlineReviewForm() throws Exception {
        super();
    }

    private static final long serialVersionUID = -7633960906991275328L;

    @Override
    protected String getDefaultDocumentTypeName() {
        return "ProtocolOnlineReviewDocument";
    }

    @Override
    protected String getLockRegion() {
        return KraAuthorizationConstants.LOCK_DESCRIPTOR_PROTOCOL;
    }

    @Override
    public List<ExtraButton> getExtraActionsButtons() {
        // clear out the extra buttons array
        extraButtons.clear();
        ProtocolOnlineReviewDocument doc = (ProtocolOnlineReviewDocument)this.getProtocolOnlineReviewDocument();
        String externalImageURL = Constants.KRA_EXTERNALIZABLE_IMAGES_URI_KEY;

        
        TaskAuthorizationService tas = KcServiceLocator.getService(TaskAuthorizationService.class);
               
        if( tas.isAuthorized(GlobalVariables.getUserSession().getPrincipalId(), new ProtocolOnlineReviewTask("rejectProtocolOnlineReview",doc))
                && doc.getDocumentHeader().getWorkflowDocument().isEnroute()
                && ProtocolOnlineReviewStatus.FINAL_STATUS_CD.equals(doc.getProtocolOnlineReview().getProtocolOnlineReviewStatusCode())) {
            String resubmissionImage = CoreApiServiceLocator.getKualiConfigurationService().getPropertyValueAsString(externalImageURL) + "buttonsmall_return_to_reviewer.gif";
            addExtraButton("methodToCall.rejectOnlineReview", resubmissionImage, "Return to reviewer");
        }
        
        return extraButtons;
    }

    @Override
    public boolean getAdminFieldsEditable() {
        return KcServiceLocator.getService(KcAuthorizationService.class).hasPermission(GlobalVariables.getUserSession().getPrincipalId(), getProtocolOnlineReviewDocument().getProtocolOnlineReview().getProtocol(),PermissionConstants.MAINTAIN_ONLINE_REVIEWS);
    }

    @Override
    protected Log getLogHook() {
        return LOG;
    }

    @Override
    protected Map<String, String> getOnlineReviewApproveButtonMapHook() {
        return ONLINE_REVIEW_APPROVE_BUTTON_MAP;
    }

    public boolean getIrbAdminFieldsEditable() {
        return getAdminFieldsEditable();
    }    
}
