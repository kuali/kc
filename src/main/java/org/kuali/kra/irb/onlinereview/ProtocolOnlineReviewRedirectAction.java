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
package org.kuali.kra.irb.onlinereview;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.ProtocolForm;
import org.kuali.kra.irb.ProtocolOnlineReviewDocument;
import org.kuali.kra.web.struts.action.KraTransactionalDocumentActionBase;
import org.kuali.rice.kns.service.BusinessObjectService;

public class ProtocolOnlineReviewRedirectAction extends KraTransactionalDocumentActionBase  {

    private static final String PROTOCOL_DOCUMENT_NUMBER="protocolDocumentNumber";
    private static final String PROTOCOL_ONLINE_REVIEW_DOCUMENT_NUMBER="protocolOnlineReviewDocumentNumber";
    
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception {
        ActionForward forward = super.execute(mapping, form, request, response);
        return forward;
    }

   
    public ActionForward redirectToProtocolFromReview(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception {
        ProtocolOnlineReviewForm protocolOnlineReviewForm = (ProtocolOnlineReviewForm)form;
        super.loadDocument(protocolOnlineReviewForm);
        Map<String,Object> keymap = new HashMap<String,Object>();
                if (protocolOnlineReviewForm.getDocument().getProtocolOnlineReview().isActive()) {
            Long protocolId = protocolOnlineReviewForm.getDocument().getProtocolOnlineReview().getProtocolId();
            keymap.put( "protocolId", protocolOnlineReviewForm.getDocument().getProtocolOnlineReview().getProtocolId() );
            Protocol protocol = (Protocol)getBusinessObjectService().findByPrimaryKey(Protocol.class, keymap );
            response.sendRedirect(String.format("protocolOnlineReview.do?methodToCall=startProtocolOnlineReview&%s=%s",PROTOCOL_DOCUMENT_NUMBER,protocol.getProtocolDocument().getDocumentNumber()));
        } else {
            return mapping.findForward("displayInactive");
        }
        return null;
    }
    
    public ActionForward startProtocolOnlineReview(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        Map<String, String> fieldValues = new HashMap<String, String>();
        String protocolOnlineReviewDocumentNumber = request.getParameter(PROTOCOL_ONLINE_REVIEW_DOCUMENT_NUMBER);
        ((ProtocolOnlineReviewForm) form).setDocument(getDocumentService().getByDocumentHeaderId(
                protocolOnlineReviewDocumentNumber));
            return mapping.findForward("displayInactive");
    }

    
    
}
