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

import java.util.List;

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
import org.kuali.kra.common.specialreview.rule.event.SaveSpecialReviewEvent;
import org.kuali.kra.common.specialreview.rule.event.SaveSpecialReviewLinkEvent;
import org.kuali.kra.common.specialreview.service.SpecialReviewService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.krad.util.KRADConstants;

/**
 * This class represents the Struts Action for Special Review page(AwardSpecialReview.jsp).
 */
public class AwardSpecialReviewAction extends AwardAction {
    
    private static final String SAVE_SPECIAL_REVIEW_FIELD = "document.awardList[0].specialReviews";
    private static final String CONFIRM_DELETE_SPECIAL_REVIEW_KEY = "confirmDeleteSpecialReview";
    
    private SpecialReviewService specialReviewService;
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward = super.execute(mapping, form, request, response);
        
        AwardForm awardForm = (AwardForm) form;
        
        awardForm.getSpecialReviewHelper().prepareView();
        
        return forward;
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public ActionForward refresh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception {

        ActionForward forward = super.refresh(mapping, form, request, response);

        String prefix = getSpecialReviewService().getProtocolSaveLocationPrefix(request.getParameterMap());
        AwardForm awardForm = (AwardForm) form;
        
        AwardSpecialReview awardSpecialReview = null;
        if (StringUtils.startsWith(prefix, "specialReviewHelper.newSpecialReview")) {
            awardSpecialReview = awardForm.getSpecialReviewHelper().getNewSpecialReview();
        } else {
            int index = getSpecialReviewService().getProtocolIndex(prefix);
            if (index != -1) {
                awardSpecialReview = awardForm.getDocument().getAward().getSpecialReviews().get(index);
            }
        }
        
        awardForm.getSpecialReviewHelper().prepareProtocolLinkViewFields(awardSpecialReview);
        
        return forward;
    }
    
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
        AwardSpecialReview specialReview = awardForm.getSpecialReviewHelper().getNewSpecialReview();
        List<AwardSpecialReview> specialReviews = document.getAward().getSpecialReviews();
        boolean isProtocolLinkingEnabled = awardForm.getSpecialReviewHelper().getIsProtocolLinkingEnabled();
        
        awardForm.getSpecialReviewHelper().prepareProtocolLinkViewFields(specialReview);
        
        if (applyRules(new AddSpecialReviewEvent<AwardSpecialReview>(document, specialReview, specialReviews, isProtocolLinkingEnabled))) {
            specialReview.setSpecialReviewNumber(document.getDocumentNextValue(Constants.SPECIAL_REVIEW_NUMBER));
            document.getAward().getSpecialReviews().add(specialReview);
            awardForm.getSpecialReviewHelper().setNewSpecialReview(new AwardSpecialReview());
        }

        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }
    
    /**
     * Deletes a special review item after confirmation.
     * 
     * @param mapping the action mapping
     * @param form the action form
     * @param request the request
     * @param response the response
     * @return the action forward
     * @throws Exception if unable to delete the special review
     */
    public ActionForward deleteSpecialReview(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
        throws Exception {

        return confirm(buildParameterizedConfirmationQuestion(mapping, form, request, response, CONFIRM_DELETE_SPECIAL_REVIEW_KEY,
                KeyConstants.QUESTION_SPECIAL_REVIEW_DELETE_CONFIRMATION), CONFIRM_DELETE_SPECIAL_REVIEW_KEY, "");
    }
    
    /**
     * Deletes a special review item only if the user confirms it.
     * 
     * @param mapping the action mapping
     * @param form the action form
     * @param request the request
     * @param response the response
     * @return the action forward
     * @throws Exception if unable to delete the special review
     */
    public ActionForward confirmDeleteSpecialReview(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
        throws Exception {
        
        Object question = request.getParameter(KRADConstants.QUESTION_INST_ATTRIBUTE_NAME);
        if (CONFIRM_DELETE_SPECIAL_REVIEW_KEY.equals(question)) {
            AwardForm awardForm = (AwardForm) form;
            AwardDocument document = awardForm.getAwardDocument();
            
            document.getAward().getSpecialReviews().remove(getLineToDelete(request));
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.award.web.struts.action.AwardAction#save(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, 
     *      javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        
        AwardForm awardForm = (AwardForm) form;
        AwardDocument document = awardForm.getAwardDocument();
        List<AwardSpecialReview> specialReviews = document.getAward().getSpecialReviews();
        List<String> linkedProtocolNumbers = awardForm.getSpecialReviewHelper().getLinkedProtocolNumbers();
        boolean isAwardProtocolLinkingEnabled = awardForm.getSpecialReviewHelper().getIsProtocolLinkingEnabled();

        if (isAwardProtocolLinkingEnabled) {
            if (applyRules(new SaveSpecialReviewLinkEvent<AwardSpecialReview>(document, specialReviews, linkedProtocolNumbers))) {
                awardForm.getSpecialReviewHelper().syncProtocolFundingSourcesWithSpecialReviews();
            }
        }

        if (applyRules(new SaveSpecialReviewEvent<AwardSpecialReview>(SAVE_SPECIAL_REVIEW_FIELD, document, specialReviews, isAwardProtocolLinkingEnabled))) {
            forward = super.save(mapping, form, request, response);
        }

        return forward;
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
        
        String viewProtocolUrl = Constants.EMPTY_STRING;
        
        AwardForm awardForm = (AwardForm) form;
        AwardSpecialReview awardSpecialReview = awardForm.getSpecialReviewHelper().getNewSpecialReview();
        viewProtocolUrl = getViewProtocolUrl(awardSpecialReview);
        
        return new ActionForward(viewProtocolUrl, true);
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
        
        String viewProtocolUrl = Constants.EMPTY_STRING;
        
        AwardForm awardForm = (AwardForm) form;
        String lineNumber = request.getParameter("line");
        
        if (NumberUtils.isNumber(lineNumber)) {
            int index = Integer.parseInt(lineNumber);
            AwardSpecialReview awardSpecialReview = awardForm.getAwardDocument().getAward().getSpecialReviews().get(index);
            viewProtocolUrl = getViewProtocolUrl(awardSpecialReview);
        }
        
        return new ActionForward(viewProtocolUrl, true);
    }
    
    private String getViewProtocolUrl(AwardSpecialReview specialReview) throws Exception {
        String viewProtocolUrl = Constants.EMPTY_STRING;

        String protocolNumber = specialReview.getProtocolNumber();
        String routeHeaderId = getSpecialReviewService().getViewSpecialReviewProtocolRouteHeaderId(protocolNumber);
        if (StringUtils.isNotEmpty(routeHeaderId)) { 
            viewProtocolUrl = buildForwardUrl(routeHeaderId) + "&viewDocument=true";
        }
        
        return viewProtocolUrl;
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