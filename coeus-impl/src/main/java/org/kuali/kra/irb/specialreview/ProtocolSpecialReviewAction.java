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
package org.kuali.kra.irb.specialreview;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.common.framework.compliance.core.AddSpecialReviewEvent;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.irb.ProtocolAction;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.ProtocolForm;
import org.kuali.rice.krad.util.KRADConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * This class represents the Struts Action for Special Review page(ProtocolSpecialReview.jsp).
 */
public class ProtocolSpecialReviewAction extends ProtocolAction {
    
    private static final String CONFIRM_DELETE_SPECIAL_REVIEW_KEY = "confirmDeleteSpecialReview";
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward = super.execute(mapping, form, request, response);
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        
        protocolForm.getSpecialReviewHelper().prepareView();
        
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
        ProtocolDocument document = protocolForm.getProtocolDocument();
        ProtocolSpecialReview specialReview = (ProtocolSpecialReview)protocolForm.getSpecialReviewHelper().getNewSpecialReview();
        List<ProtocolSpecialReview> specialReviews = (List)document.getProtocol().getSpecialReviews();
        
        if (applyRules(new AddSpecialReviewEvent<ProtocolSpecialReview>(document, specialReview, specialReviews, false))) {
            specialReview.setSpecialReviewNumber(document.getDocumentNextValue(Constants.SPECIAL_REVIEW_NUMBER));
            document.getProtocol().getSpecialReviews().add(specialReview);
            protocolForm.getSpecialReviewHelper().setNewSpecialReview(new ProtocolSpecialReview());
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
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
            ProtocolForm protocolForm = (ProtocolForm) form;
            ProtocolDocument document = protocolForm.getProtocolDocument();
            
            document.getProtocol().getSpecialReviews().remove(getLineToDelete(request));
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
}
