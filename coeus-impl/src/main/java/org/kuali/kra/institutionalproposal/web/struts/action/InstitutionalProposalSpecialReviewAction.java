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
package org.kuali.kra.institutionalproposal.web.struts.action;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.common.framework.compliance.core.AddSpecialReviewEvent;
import org.kuali.coeus.common.framework.compliance.core.SaveSpecialReviewEvent;
import org.kuali.coeus.common.framework.compliance.core.SaveSpecialReviewLinkEvent;
import org.kuali.coeus.common.framework.compliance.core.SpecialReviewService;
import org.kuali.coeus.common.notification.impl.service.KcNotificationService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.common.framework.compliance.core.SpecialReviewType;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.institutionalproposal.document.InstitutionalProposalDocument;
import org.kuali.kra.institutionalproposal.notification.InstitutionalProposalNotificationContext;
import org.kuali.kra.institutionalproposal.specialreview.InstitutionalProposalSpecialReview;
import org.kuali.kra.institutionalproposal.web.struts.form.InstitutionalProposalForm;
import org.kuali.rice.krad.util.KRADConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Invokes rules on and applies actions to add, delete, or save SpecialReviews.
 */
public class InstitutionalProposalSpecialReviewAction extends InstitutionalProposalAction {
    
    private static final String SAVE_SPECIAL_REVIEW_FIELD = "document.institutionalProposalList[0].specialReviews";
    private static final String CONFIRM_DELETE_SPECIAL_REVIEW_KEY = "confirmDeleteSpecialReview";
    
    private SpecialReviewService specialReviewService;
    private KcNotificationService notificationService;
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward = super.execute(mapping, form, request, response);
        
        InstitutionalProposalForm institutionalProposalForm = (InstitutionalProposalForm) form;
        
        institutionalProposalForm.getSpecialReviewHelper().prepareView();
        
        return forward;
    }
    
    @Override
    public ActionForward refresh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception {

        ActionForward forward = super.refresh(mapping, form, request, response);

        String prefix = getSpecialReviewService().getProtocolSaveLocationPrefix(request.getParameterMap());
        InstitutionalProposalForm proposalForm = (InstitutionalProposalForm) form;
        
        InstitutionalProposalSpecialReview proposalSpecialReview = null;
        if (StringUtils.startsWith(prefix, "specialReviewHelper.newSpecialReview")) {
            proposalSpecialReview = proposalForm.getSpecialReviewHelper().getNewSpecialReview();
        } else {
            int index = getSpecialReviewService().getProtocolIndex(prefix);
            if (index != -1) {
                proposalSpecialReview = proposalForm.getInstitutionalProposalDocument().getInstitutionalProposal().getSpecialReviews().get(index);
            }
        }
        
        proposalForm.getSpecialReviewHelper().prepareProtocolLinkViewFields(proposalSpecialReview);
        
        return forward;
    }

    public ActionForward addSpecialReview(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        InstitutionalProposalForm institutionalProposalForm = (InstitutionalProposalForm) form;
        InstitutionalProposalDocument document = institutionalProposalForm.getInstitutionalProposalDocument();
        InstitutionalProposalSpecialReview specialReview = institutionalProposalForm.getSpecialReviewHelper().getNewSpecialReview();
        List<InstitutionalProposalSpecialReview> specialReviews = document.getInstitutionalProposal().getSpecialReviews();
        boolean isProtocolLinkingEnabled = false;
        if ( SpecialReviewType.HUMAN_SUBJECTS.equals(specialReview.getSpecialReviewTypeCode()) )
        {
            isProtocolLinkingEnabled =  institutionalProposalForm.getSpecialReviewHelper().getIsIrbProtocolLinkingEnabled() ;
        }
        else if ( SpecialReviewType.ANIMAL_USAGE.equals(specialReview.getSpecialReviewTypeCode()) )
        {
            isProtocolLinkingEnabled =  institutionalProposalForm.getSpecialReviewHelper().getIsIacucProtocolLinkingEnabled();
        }
        
        institutionalProposalForm.getSpecialReviewHelper().prepareProtocolLinkViewFields(specialReview);
        
        ActionForward forward = mapping.findForward(Constants.MAPPING_AWARD_BASIC);
        if (applyRules(new AddSpecialReviewEvent<InstitutionalProposalSpecialReview>(document, specialReview, specialReviews, isProtocolLinkingEnabled ))) {
            specialReview.setSpecialReviewNumber(document.getDocumentNextValue(Constants.SPECIAL_REVIEW_NUMBER));
            document.getInstitutionalProposal().getSpecialReviews().add(specialReview);
            institutionalProposalForm.getSpecialReviewHelper().setNewSpecialReview(new InstitutionalProposalSpecialReview());
            if (specialReview.getSpecialReviewType() == null) {
                specialReview.refreshReferenceObject("specialReviewType");
            }
            if (StringUtils.equals(specialReview.getSpecialReviewType().getSpecialReviewTypeCode(), SpecialReviewType.HUMAN_SUBJECTS)) {
                InstitutionalProposalNotificationContext context = 
                    new InstitutionalProposalNotificationContext(document.getInstitutionalProposal(), "552", "Special Review Inserted", Constants.MAPPING_INSTITUTIONAL_PROPOSAL_SPECIAL_REVIEW_PAGE);
                if (institutionalProposalForm.getNotificationHelper().getPromptUserForNotificationEditor(context)) {
                    institutionalProposalForm.getNotificationHelper().initializeDefaultValues(context);
                    forward = mapping.findForward("notificationEditor");
                } else {
                    getNotificationService().sendNotification(context);                
                }
            }
        }
        
        return forward;
    }

    public ActionForward deleteSpecialReview(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
        throws Exception {

        return confirm(buildParameterizedConfirmationQuestion(mapping, form, request, response, CONFIRM_DELETE_SPECIAL_REVIEW_KEY,
                KeyConstants.QUESTION_SPECIAL_REVIEW_DELETE_CONFIRMATION), CONFIRM_DELETE_SPECIAL_REVIEW_KEY, "");
    }

    public ActionForward confirmDeleteSpecialReview(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
        throws Exception {

        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        Object question = request.getParameter(KRADConstants.QUESTION_INST_ATTRIBUTE_NAME);
        if (CONFIRM_DELETE_SPECIAL_REVIEW_KEY.equals(question)) {
            InstitutionalProposalForm institutionalProposalForm = (InstitutionalProposalForm) form;
            InstitutionalProposalDocument document = institutionalProposalForm.getInstitutionalProposalDocument();
            
            InstitutionalProposalSpecialReview specialReview = document.getInstitutionalProposal().getSpecialReviews().get(getLineToDelete(request));
            document.getInstitutionalProposal().getSpecialReviews().remove(specialReview);
            if (StringUtils.equals(specialReview.getSpecialReviewType().getSpecialReviewTypeCode(), SpecialReviewType.HUMAN_SUBJECTS)) {
                InstitutionalProposalNotificationContext context = 
                    new InstitutionalProposalNotificationContext(document.getInstitutionalProposal(), "553", "Special Review Deleted", Constants.MAPPING_INSTITUTIONAL_PROPOSAL_SPECIAL_REVIEW_PAGE);
                if (institutionalProposalForm.getNotificationHelper().getPromptUserForNotificationEditor(context)) {
                    institutionalProposalForm.getNotificationHelper().initializeDefaultValues(context);
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
        
        InstitutionalProposalForm institutionalProposalForm = (InstitutionalProposalForm) form;
        InstitutionalProposalDocument document = institutionalProposalForm.getInstitutionalProposalDocument();
        List<InstitutionalProposalSpecialReview> specialReviews = document.getInstitutionalProposal().getSpecialReviews();
        List<String> linkedProtocolNumbers = institutionalProposalForm.getSpecialReviewHelper().getLinkedProtocolNumbers();
        boolean isIPIrbProtocolLinkingEnabled = institutionalProposalForm.getSpecialReviewHelper().getIsIrbProtocolLinkingEnabled();
        boolean isIPIacucProtocolLinkingEnabled = institutionalProposalForm.getSpecialReviewHelper().getIsIacucProtocolLinkingEnabled();

        if (isIPIrbProtocolLinkingEnabled || isIPIacucProtocolLinkingEnabled) {
            if (applyRules(new SaveSpecialReviewLinkEvent<>(document, specialReviews, linkedProtocolNumbers))) {
                institutionalProposalForm.getSpecialReviewHelper().syncProtocolFundingSourcesWithSpecialReviews();
            }
        }
        
        if (applyRules(new SaveSpecialReviewEvent<>(
            SAVE_SPECIAL_REVIEW_FIELD, document, specialReviews, isIPIrbProtocolLinkingEnabled, isIPIacucProtocolLinkingEnabled))) {
            // For reasons unknown to me, to enforce saving special review records in order between successive saves, we must save the document before saving 
            // anything else (like the special review indicator) on the document.  This statement can be safely removed if the special review indicator is no 
            // longer being set at this point...
            getDocumentService().saveDocument(document);
            
            if (!document.getInstitutionalProposal().getSpecialReviews().isEmpty()) {
                document.getInstitutionalProposal().setSpecialReviewIndicator("1");
            } else {
                document.getInstitutionalProposal().setSpecialReviewIndicator("0");
            }
        
            forward = super.save(mapping, form, request, response);
        }
        
        return forward;
    }

    public ActionForward viewNewSpecialReviewProtocolLink(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
        throws Exception {
        
        InstitutionalProposalForm institutionalProposalForm = (InstitutionalProposalForm) form;
        InstitutionalProposalSpecialReview institutionalProposalSpecialReview = institutionalProposalForm.getSpecialReviewHelper().getNewSpecialReview();
        String viewProtocolUrl = getViewProtocolUrl(institutionalProposalSpecialReview);
        
        return new ActionForward(viewProtocolUrl, true);
    }

    public ActionForward viewSpecialReviewProtocolLink(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
        throws Exception {
        
        String viewProtocolUrl = Constants.EMPTY_STRING;
        
        InstitutionalProposalForm institutionalProposalForm = (InstitutionalProposalForm) form;
        String lineNumber = request.getParameter("line");
        
        if (NumberUtils.isNumber(lineNumber)) {
            int index = Integer.parseInt(lineNumber);
            InstitutionalProposalSpecialReview institutionalProposalSpecialReview 
                = institutionalProposalForm.getInstitutionalProposalDocument().getInstitutionalProposal().getSpecialReviews().get(index);
            viewProtocolUrl = getViewProtocolUrl(institutionalProposalSpecialReview);
        }
        
        return new ActionForward(viewProtocolUrl, true);
    }
    
    private String getViewProtocolUrl(InstitutionalProposalSpecialReview specialReview) throws Exception {
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

    protected KcNotificationService getNotificationService() {
        if (notificationService == null) {
            notificationService = KcServiceLocator.getService(KcNotificationService.class);
        }
        return notificationService;
    }

    public void setNotificationService(KcNotificationService notificationService) {
        this.notificationService = notificationService;
    }

}
