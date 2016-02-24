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
package org.kuali.kra.award.web.struts.action;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.common.framework.compliance.core.AddSpecialReviewEvent;
import org.kuali.coeus.common.framework.compliance.core.SaveSpecialReviewEvent;
import org.kuali.coeus.common.framework.compliance.core.SaveSpecialReviewLinkEvent;
import org.kuali.coeus.common.framework.compliance.core.SpecialReviewService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.AwardForm;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.notification.AwardNotificationContext;
import org.kuali.kra.award.specialreview.AwardSpecialReview;
import org.kuali.coeus.common.framework.compliance.core.SpecialReviewType;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.krad.util.KRADConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

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
                awardSpecialReview = awardForm.getAwardDocument().getAward().getSpecialReviews().get(index);
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
        boolean isProtocolLinkingEnabled = false;
        if ( SpecialReviewType.HUMAN_SUBJECTS.equals(specialReview.getSpecialReviewTypeCode()) )
        {
            isProtocolLinkingEnabled =  awardForm.getSpecialReviewHelper().getIsIrbProtocolLinkingEnabled() ;
        }
        else if ( SpecialReviewType.ANIMAL_USAGE.equals(specialReview.getSpecialReviewTypeCode()) )
        {
            isProtocolLinkingEnabled =  awardForm.getSpecialReviewHelper().getIsIacucProtocolLinkingEnabled();
        }
        
        awardForm.getSpecialReviewHelper().prepareProtocolLinkViewFields(specialReview);
        
        ActionForward forward = mapping.findForward(Constants.MAPPING_AWARD_BASIC);
        if (applyRules(new AddSpecialReviewEvent<AwardSpecialReview>(document, specialReview, specialReviews, isProtocolLinkingEnabled ))) {
            specialReview.setSpecialReviewNumber(document.getDocumentNextValue(Constants.SPECIAL_REVIEW_NUMBER));
            document.getAward().getSpecialReviews().add(specialReview);
            awardForm.getSpecialReviewHelper().setNewSpecialReview(new AwardSpecialReview());
            if (specialReview.getSpecialReviewType() == null) {
                specialReview.refreshReferenceObject("specialReviewType");
            }
            AwardNotificationContext context = null; 
            if (StringUtils.equals(specialReview.getSpecialReviewType().getSpecialReviewTypeCode(), SpecialReviewType.HUMAN_SUBJECTS)) {
                context = new AwardNotificationContext(document.getAward(), Award.NOTIFICATION_IRB_SPECIAL_REVIEW_LINK_ADDED, "Special Review Inserted", 
                                                       Constants.MAPPING_AWARD_SPECIAL_REVIEW_PAGE);
            } else if (StringUtils.equals(specialReview.getSpecialReviewType().getSpecialReviewTypeCode(), SpecialReviewType.ANIMAL_USAGE)) {
                context = new AwardNotificationContext(document.getAward(), Award.NOTIFICATION_IACUC_SPECIAL_REVIEW_LINK_ADDED, "Special Review Inserted", 
                        Constants.MAPPING_AWARD_SPECIAL_REVIEW_PAGE);
            }
            if (context != null) {
                if (awardForm.getNotificationHelper().getPromptUserForNotificationEditor(context)) {
                    awardForm.getNotificationHelper().initializeDefaultValues(context);
                    forward = mapping.findForward("notificationEditor");
                } else {
                    getNotificationService().sendNotification(context);                
                }
            }            
        }

        return forward;
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
        
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        Object question = request.getParameter(KRADConstants.QUESTION_INST_ATTRIBUTE_NAME);
        if (CONFIRM_DELETE_SPECIAL_REVIEW_KEY.equals(question)) {
            AwardForm awardForm = (AwardForm) form;
            AwardDocument document = awardForm.getAwardDocument();
            AwardSpecialReview specialReview = document.getAward().getSpecialReviews().get(getLineToDelete(request));
            document.getAward().getSpecialReviews().remove(specialReview);
            
            if (specialReview.getSpecialReviewType() == null) {
                specialReview.refreshReferenceObject("specialReviewType");
            }
            AwardNotificationContext context = null; 
            if (StringUtils.equals(specialReview.getSpecialReviewType().getSpecialReviewTypeCode(), SpecialReviewType.HUMAN_SUBJECTS)) {
                    context = new AwardNotificationContext(document.getAward(), Award.NOTIFICATION_IRB_SPECIAL_REVIEW_LINK_DELETED, "Special Review Deleted", 
                                                           Constants.MAPPING_AWARD_SPECIAL_REVIEW_PAGE);
            } else if (StringUtils.equals(specialReview.getSpecialReviewType().getSpecialReviewTypeCode(), SpecialReviewType.ANIMAL_USAGE)) {
                    context = new AwardNotificationContext(document.getAward(), Award.NOTIFICATION_IACUC_SPECIAL_REVIEW_LINK_DELETED, "Special Review Deleted", 
                                                           Constants.MAPPING_AWARD_SPECIAL_REVIEW_PAGE);
            }
            if (context != null) {
                if (awardForm.getNotificationHelper().getPromptUserForNotificationEditor(context)) {
                    awardForm.getNotificationHelper().initializeDefaultValues(context);
                    forward = mapping.findForward("notificationEditor");
                } else {
                    getNotificationService().sendNotification(context);                
                }
            }            
        }
        
        return forward;
    }
    
    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        
        AwardForm awardForm = (AwardForm) form;
        AwardDocument document = awardForm.getAwardDocument();
        List<AwardSpecialReview> specialReviews = document.getAward().getSpecialReviews();
        List<String> linkedProtocolNumbers = awardForm.getSpecialReviewHelper().getLinkedProtocolNumbers();
        boolean isAwardIrbProtocolLinkingEnabled = awardForm.getSpecialReviewHelper().getIsIrbProtocolLinkingEnabled();
        boolean isAwardIacucProtocolLinkingEnabled = awardForm.getSpecialReviewHelper().getIsIacucProtocolLinkingEnabled();
        
        if (awardForm.getAwardDocument().getAward().getSpecialReviews() == null || awardForm.getAwardDocument().getAward().getSpecialReviews().isEmpty()) {
            awardForm.getAwardDocument().getAward().setSpecialReviewIndicator(Constants.NO_FLAG);
        } else {
            awardForm.getAwardDocument().getAward().setSpecialReviewIndicator(Constants.YES_FLAG);
        }
        
        if (isAwardIrbProtocolLinkingEnabled || isAwardIacucProtocolLinkingEnabled) {
            if (applyRules(new SaveSpecialReviewLinkEvent<AwardSpecialReview>(document, specialReviews, linkedProtocolNumbers))) {
                awardForm.getSpecialReviewHelper().syncProtocolFundingSourcesWithSpecialReviews();
            }
        }

        if (applyRules(new SaveSpecialReviewEvent<AwardSpecialReview>(SAVE_SPECIAL_REVIEW_FIELD, document, specialReviews, isAwardIrbProtocolLinkingEnabled, isAwardIacucProtocolLinkingEnabled))) {
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
        String routeHeaderId = getSpecialReviewService().getViewSpecialReviewProtocolRouteHeaderId(protocolNumber, specialReview.getSpecialReviewTypeCode());
        if (StringUtils.isNotEmpty(routeHeaderId)) { 
            viewProtocolUrl = buildForwardUrl(routeHeaderId) + "&viewDocument=true";
        }
        
        return viewProtocolUrl;
    }
    
    public SpecialReviewService getSpecialReviewService() {
        if (specialReviewService == null) {
            specialReviewService = KcServiceLocator.getService(SpecialReviewService.class);
        }
        return specialReviewService;
    }
    
    public void setSpecialReviewService(SpecialReviewService specialReviewService) {
        this.specialReviewService = specialReviewService;
    }
    
}
