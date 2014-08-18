/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.protocol.onlinereview;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;
import org.kuali.coeus.common.framework.auth.perm.KcAuthorizationService;
import org.kuali.coeus.sys.framework.controller.KcTransactionalDocumentActionBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public abstract class ProtocolOnlineReviewRedirectActionBase extends KcTransactionalDocumentActionBase {

    private static final String PROTOCOL_DOCUMENT_NUMBER="protocolDocumentNumber";
    private static final String PROTOCOL_ONLINE_REVIEW_DOCUMENT_NUMBER="protocolOnlineReviewDocumentNumber";
    
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception {
        ActionForward forward = super.execute(mapping, form, request, response);
        return forward;
    }

   
    public ActionForward redirectToProtocolFromReview(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ProtocolOnlineReviewFormBase protocolOnlineReviewForm = (ProtocolOnlineReviewFormBase) form;
        super.loadDocument(protocolOnlineReviewForm);
        Map<String, Object> keymap = new HashMap<String, Object>();
        if (protocolOnlineReviewForm.getProtocolOnlineReviewDocument().getProtocolOnlineReview().isActive()) {
            keymap.put("protocolId", protocolOnlineReviewForm.getProtocolOnlineReviewDocument().getProtocolOnlineReview()
                    .getProtocolId());
            ProtocolBase protocol = (ProtocolBase) getBusinessObjectService().findByPrimaryKey(getProtocolClass(), keymap);
            if (isOnlineReviewEnabled(form, protocol)) {
             return new ActionRedirect(String.format(getProtocolOnlineReviewActionIdHook() + ".do?methodToCall=startProtocolOnlineReview&%s=%s", PROTOCOL_DOCUMENT_NUMBER, protocol.getProtocolDocument().getDocumentNumber()));   
                
            }
            else {
                return mapping.findForward(Constants.MAPPING_PROPOSAL_DISPLAY_INACTIVE);
            }
        }
        else {
            return mapping.findForward(Constants.MAPPING_PROPOSAL_DISPLAY_INACTIVE);
        }
    }
    
    
    protected abstract String getProtocolOnlineReviewActionIdHook();


    protected abstract Class<? extends ProtocolBase> getProtocolClass();
    
    private boolean isOnlineReviewEnabled(ActionForm form, ProtocolBase protocol) { 
        String principalId = GlobalVariables.getUserSession().getPrincipalId();
        ProtocolSubmissionBase submission = protocol.getProtocolSubmission();
        boolean isUserOnlineReviewer = getProtocolOnlineReviewService().isProtocolReviewer(principalId, false, submission);
        boolean isProtocolInStateToBeReviewed = getProtocolOnlineReviewService().isProtocolInStateToBeReviewed(protocol);
        boolean isUserAdmin = getKraAuthorizationService().hasRole(GlobalVariables.getUserSession().getPrincipalId(), "KC-UNT", getAdminRoleName()); 
        return isProtocolInStateToBeReviewed && (isUserOnlineReviewer || isUserAdmin);
    }
    
    protected String getAdminRoleName() {
        return "IRB Administrator";
    }
    private KcAuthorizationService getKraAuthorizationService() {
        return KcServiceLocator.getService(KcAuthorizationService.class);
    }

    private ProtocolOnlineReviewService getProtocolOnlineReviewService() {
        return KcServiceLocator.getService(getProtocolOnlineReviewServiceClassHook());
    }
    
    protected abstract Class<? extends ProtocolOnlineReviewService> getProtocolOnlineReviewServiceClassHook();

    
    
    public ActionForward startProtocolOnlineReview(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        String protocolOnlineReviewDocumentNumber = request.getParameter(PROTOCOL_ONLINE_REVIEW_DOCUMENT_NUMBER);
        ((ProtocolOnlineReviewFormBase) form).setDocument(getDocumentService().getByDocumentHeaderId(
                protocolOnlineReviewDocumentNumber));
            return mapping.findForward(Constants.MAPPING_PROPOSAL_DISPLAY_INACTIVE);
    }


    public ActionForward onlineReview(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    @Override
    public ActionForward close(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward(KRADConstants.MAPPING_PORTAL);
    }
    
}
