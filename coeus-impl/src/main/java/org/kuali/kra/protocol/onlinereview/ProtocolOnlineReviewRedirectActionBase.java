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
package org.kuali.kra.protocol.onlinereview;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;
import org.kuali.coeus.common.framework.auth.SystemAuthorizationService;
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
        boolean isUserAdmin = getSystemAuthorizationService().hasRole(GlobalVariables.getUserSession().getPrincipalId(), "KC-UNT", getAdminRoleName());
        return isProtocolInStateToBeReviewed && (isUserOnlineReviewer || isUserAdmin);
    }
    
    protected String getAdminRoleName() {
        return "IRB Administrator";
    }

    private SystemAuthorizationService getSystemAuthorizationService() {
        return KcServiceLocator.getService(SystemAuthorizationService.class);
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
