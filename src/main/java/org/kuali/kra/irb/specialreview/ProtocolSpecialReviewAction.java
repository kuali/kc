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
package org.kuali.kra.irb.specialreview;

import java.sql.Date;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.common.specialreview.rule.event.AddSpecialReviewEvent;
import org.kuali.kra.common.specialreview.service.SpecialReviewService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolAction;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.ProtocolForm;

/**
 * This class represents the Struts Action for Special Review page(ProtocolSpecialReview.jsp).
 */
public class ProtocolSpecialReviewAction extends ProtocolAction {
    
    private SpecialReviewService specialReviewService;
    
    @Override
    @SuppressWarnings("unchecked")
    public ActionForward refresh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception {

        ActionForward forward = super.refresh(mapping, form, request, response);
        
        Protocol protocol = getSpecialReviewService().getProtocol(request.getParameterMap());

        if (protocol != null) {
            String prefix = getSpecialReviewService().getProtocolSaveLocationPrefix(request.getParameterMap());
            ProtocolForm protocolForm = (ProtocolForm) form;
            
            ProtocolSpecialReview protocolSpecialReview = null;
            if (prefix.startsWith("specialReviewHelper.newSpecialReview")) {
                protocolSpecialReview = protocolForm.getSpecialReviewHelper().getNewSpecialReview();
            } else {
                int index = getSpecialReviewService().getProtocolIndex(prefix);
                if (index != -1) {
                    protocolSpecialReview = protocolForm.getDocument().getProtocol().getSpecialReviews().get(index);
                }
            }
            
            if (protocolSpecialReview != null) {
                // Set Approval Status once we get the mapping
                Timestamp submissionDate = protocol.getProtocolSubmission().getSubmissionDate();
                protocolSpecialReview.setApplicationDate(submissionDate == null ? null : new Date(submissionDate.getTime()));
                protocolSpecialReview.setApprovalDate(protocol.getLastApprovalDate() == null ? protocol.getApprovalDate() : protocol.getLastApprovalDate());
                protocolSpecialReview.setExpirationDate(protocol.getExpirationDate());
                // Set Exemption # once we get the mapping
            }
        }
        
        return forward;
    }

    /**
     * Adds a Protocol Special Review to the list.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     * @throws Exception
     */
    public ActionForward addSpecialReview(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolDocument document = protocolForm.getDocument();
        ProtocolSpecialReview newSpecialReview = protocolForm.getSpecialReviewHelper().getNewSpecialReview();
        
        if (applyRules(new AddSpecialReviewEvent<ProtocolSpecialReview>(document, newSpecialReview))) {
            newSpecialReview.setSpecialReviewNumber(document.getDocumentNextValue(Constants.SPECIAL_REVIEW_NUMBER));
            document.getProtocol().getSpecialReviews().add(newSpecialReview);
            protocolForm.getSpecialReviewHelper().setNewSpecialReview(new ProtocolSpecialReview());
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * Deletes a Protocol Special Review from the list.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deleteSpecialReview(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
        throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolDocument document = protocolForm.getDocument();
        
        document.getProtocol().getSpecialReviews().remove(getLineToDelete(request));
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * Displays the Protocol linked to the new special review item.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward viewNewSpecialReviewProtocolLink(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
        throws Exception {
        
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolSpecialReview protocolSpecialReview = protocolForm.getSpecialReviewHelper().getNewSpecialReview();

        String viewProtocolUrl = getViewProtocolUrl(protocolSpecialReview);

        if (StringUtils.isNotEmpty(viewProtocolUrl)) {
            forward = new ActionForward(viewProtocolUrl, true);
        }
        
        return forward;
    }
    
    /**
     * Displays the Protocol linked to the special review item on the selected line (from the parameter list since this is run through a popup window).
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward viewSpecialReviewProtocolLink(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
        throws Exception {
        
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        String lineNumber = request.getParameter("line");
        
        if (NumberUtils.isNumber(lineNumber)) {
            int index = Integer.parseInt(lineNumber);
            ProtocolSpecialReview protocolSpecialReview = protocolForm.getProtocolDocument().getProtocol().getSpecialReviews().get(index);

            String viewProtocolUrl = getViewProtocolUrl(protocolSpecialReview);

            if (StringUtils.isNotBlank(viewProtocolUrl)) {
                forward = new ActionForward(viewProtocolUrl, true);
            }
        }
        
        return forward;
    }

    private String getViewProtocolUrl(ProtocolSpecialReview specialReview) throws Exception {
        String protocolNumber = specialReview.getProtocolNumber();
        Long routeHeaderId = getSpecialReviewService().getViewSpecialReviewProtocolRouteHeaderId(protocolNumber);
        String forwardUrl = buildForwardUrl(routeHeaderId);
        
        return StringUtils.isNotBlank(forwardUrl) ? forwardUrl + "&viewDocument=true" : Constants.EMPTY_STRING;
    }
    
    public SpecialReviewService getSpecialReviewService() {
        if (specialReviewService == null) {
            specialReviewService = KraServiceLocator.getService(SpecialReviewService.class);
        }
        return specialReviewService;
    }
    
    public void setSpecialReviewService(SpecialReviewService specialReviewService) {
        this.specialReviewService = specialReviewService;
    }
    
}