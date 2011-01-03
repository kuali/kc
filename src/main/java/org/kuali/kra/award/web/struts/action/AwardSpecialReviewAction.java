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
package org.kuali.kra.award.web.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.award.AwardForm;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.specialreview.AwardSpecialReview;
import org.kuali.kra.common.specialreview.rule.event.AddSpecialReviewEvent;
import org.kuali.kra.common.specialreview.service.SpecialReviewService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;

/**
 * This class represents the Struts Action for Special Review page(AwardSpecialReview.jsp).
 */
public class AwardSpecialReviewAction extends AwardAction {
    
    private SpecialReviewService specialReviewService;
    
    /**
     * This method is for adding AwardSpecialReview to the list.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     * @throws Exception
     */
    public ActionForward addSpecialReview(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AwardForm awardForm = (AwardForm) form;
        AwardDocument document = awardForm.getAwardDocument();
        AwardSpecialReview newSpecialReview = awardForm.getSpecialReviewHelper().getNewSpecialReview();
        
        if (applyRules(new AddSpecialReviewEvent<AwardSpecialReview>(document, newSpecialReview))) {
            newSpecialReview.setSpecialReviewNumber(document.getDocumentNextValue(Constants.SPECIAL_REVIEW_NUMBER));
            document.getAward().getSpecialReviews().add(newSpecialReview);
            awardForm.getSpecialReviewHelper().setNewSpecialReview(new AwardSpecialReview());
        }

        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }

    /**
     * This method deletes the SpecialReview from the list.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deleteSpecialReview(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
        throws Exception {
        
        AwardForm awardForm = (AwardForm) form;
        AwardDocument document = awardForm.getAwardDocument();
        
        document.getAward().getSpecialReviews().remove(getLineToDelete(request));

        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
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
        
        AwardForm awardForm = (AwardForm) form;
        AwardSpecialReview awardSpecialReview = awardForm.getSpecialReviewHelper().getNewSpecialReview();

        String viewProtocolUrl = getViewProtocolUrl(awardSpecialReview);

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
        
        AwardForm awardForm = (AwardForm) form;
        String lineNumber = request.getParameter("line");
        
        if (NumberUtils.isNumber(lineNumber)) {
            int index = Integer.parseInt(lineNumber);
            AwardSpecialReview awardSpecialReview = awardForm.getAwardDocument().getAward().getSpecialReviews().get(index);

            String viewProtocolUrl = getViewProtocolUrl(awardSpecialReview);

            if (StringUtils.isNotBlank(viewProtocolUrl)) {
                forward = new ActionForward(viewProtocolUrl, true);
            }
        }
        
        return forward;
    }
    
    private String getViewProtocolUrl(AwardSpecialReview specialReview) throws Exception {
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