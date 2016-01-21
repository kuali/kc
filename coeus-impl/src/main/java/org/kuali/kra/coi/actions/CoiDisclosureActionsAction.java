/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.kra.coi.actions;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;
import org.kuali.coeus.sys.framework.controller.KcHoldingPageConstants;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.sys.framework.validation.AuditHelper;
import org.kuali.kra.coi.*;
import org.kuali.kra.coi.disclosure.CoiDisclosureAdministratorActionRule;
import org.kuali.kra.coi.notesandattachments.CoiNoteAndAttachmentAction;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;

public class CoiDisclosureActionsAction extends CoiNoteAndAttachmentAction {
    
    public ActionForward approve(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        CoiDisclosureForm coiDisclosureForm = (CoiDisclosureForm) form;
        String disclosureStatus = CoiDisclosureStatus.APPROVED;
        String dispositionCode = coiDisclosureForm.getDisclosureActionHelper().getMaximumDispositionStatus().getCoiDispositionCode();
        CoiDisclosureDocument coiDisclosureDocument = coiDisclosureForm.getCoiDisclosureDocument();

        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        AuditHelper auditHelper = KcServiceLocator.getService(AuditHelper.class);
        
        if (new CoiDisclosureAdministratorActionRule().isValidStatus(disclosureStatus, dispositionCode)) {
            if (!auditHelper.auditUnconditionally(coiDisclosureDocument)) {
                coiDisclosureForm.setAuditActivated(true);
                GlobalVariables.getMessageMap().putError("coiAdminActionErrors", KeyConstants.ERROR_COI_VALIDATION, "approving");
                return forward;
            }
            coiDisclosureForm.getDisclosureActionHelper().approveDisclosure(dispositionCode);
            ActionForward basicForward = mapping.findForward(KRADConstants.MAPPING_PORTAL);
            String routeHeaderId = coiDisclosureDocument.getDocumentNumber();
            String returnLocation = buildActionUrl(routeHeaderId, Constants.MAPPING_COI_DISCLOSURE_ACTIONS_PAGE, "CoiDisclosureDocument");
            ActionRedirect holdingPageForward = new ActionRedirect(mapping.findForward(KcHoldingPageConstants.MAPPING_HOLDING_PAGE));
            holdingPageForward.addParameter(KcHoldingPageConstants.HOLDING_PAGE_DOCUMENT_ID, routeHeaderId);
    
            return routeToHoldingPage(basicForward, basicForward, holdingPageForward, returnLocation, routeHeaderId);
        } else {
            return forward;
        }
    }
    
    public ActionForward disapprove(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        CoiDisclosureForm coiDisclosureForm = (CoiDisclosureForm) form;
        String disclosureStatus = CoiDisclosureStatus.DISAPPROVED;
        String dispositionCode = coiDisclosureForm.getDisclosureActionHelper().getMaximumDispositionStatus().getCoiDispositionCode();
        CoiDisclosureDocument coiDisclosureDocument = coiDisclosureForm.getCoiDisclosureDocument();

        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        AuditHelper auditHelper = KcServiceLocator.getService(AuditHelper.class);
        
        if (new CoiDisclosureAdministratorActionRule().isValidStatus(disclosureStatus, dispositionCode)) {
            if (!auditHelper.auditUnconditionally(coiDisclosureDocument)) {
                coiDisclosureForm.setAuditActivated(true);
                GlobalVariables.getMessageMap().putError("coiAdminActionErrors", KeyConstants.ERROR_COI_VALIDATION, "disapproving");
                return forward;
            }
            coiDisclosureForm.getDisclosureActionHelper().disapproveDisclosure(dispositionCode);
            ActionForward basicForward = mapping.findForward(KRADConstants.MAPPING_PORTAL);
            String routeHeaderId = coiDisclosureDocument.getDocumentNumber();
            String returnLocation = buildActionUrl(routeHeaderId, Constants.MAPPING_COI_DISCLOSURE_ACTIONS_PAGE, "CoiDisclosureDocument");
            ActionRedirect holdingPageForward = new ActionRedirect(mapping.findForward(KcHoldingPageConstants.MAPPING_HOLDING_PAGE));
            holdingPageForward.addParameter(KcHoldingPageConstants.HOLDING_PAGE_DOCUMENT_ID, routeHeaderId);
    
            return routeToHoldingPage(basicForward, basicForward, holdingPageForward, returnLocation, routeHeaderId);
        } else {
            return forward;
        }
    }

    public ActionForward updateDisclosureReviewStatus(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception {
        CoiDisclosureForm coiDisclosureForm = (CoiDisclosureForm) form;
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        CoiDisclosure coiDisclosure = coiDisclosureForm.getCoiDisclosureDocument().getCoiDisclosure();
        getCoiDisclosureActionService().updateDisclosureReviewStatus(coiDisclosure);
        return forward;
    }
    
    
    public ActionForward completeReview(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception {
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        CoiDisclosureForm coiDisclosureForm = (CoiDisclosureForm) form;
        getCoiDisclosureActionService().completeCoiReview(coiDisclosureForm.getCoiDisclosureDocument().getCoiDisclosure());
        return forward;
    }
    
    public ActionForward addCoiUserRole(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        CoiDisclosureForm coiDisclosureForm = (CoiDisclosureForm) form;
        CoiDisclosureDocument coiDisclosureDocument = coiDisclosureForm.getCoiDisclosureDocument();
        String userName = coiDisclosureForm.getDisclosureActionHelper().getNewCoiUserRole().getUserId();
        String reviewerCode = coiDisclosureForm.getDisclosureActionHelper().getNewCoiUserRole().getReviewerCode();
        CoiUserRole coiUserRole = new CoiUserRole();
        coiUserRole.setCoiDisclosureId(coiDisclosureDocument.getCoiDisclosure().getCoiDisclosureId());
        coiUserRole.setCoiDisclosureNumber(coiDisclosureDocument.getCoiDisclosure().getCoiDisclosureNumber());
        coiUserRole.setSequenceNumber(coiDisclosureDocument.getCoiDisclosure().getSequenceNumber());
        coiUserRole.setUserId(userName);
        coiUserRole.setRoleName(RoleConstants.COI_REVIEWER);
        coiUserRole.setReviewerCode(reviewerCode);
        coiUserRole.setDateAssigned(new Date(System.currentTimeMillis()));
        
        if (checkRule(new AddCoiReviewerEvent("", coiDisclosureDocument.getCoiDisclosure(), coiUserRole))) {
            coiUserRole.setCoiReviewer(coiDisclosureForm.getDisclosureActionHelper().getCoiReviewer(reviewerCode));
            coiUserRole.setPerson(coiDisclosureForm.getDisclosureActionHelper().getKcPerson(userName));
            forward = getCoiDisclosureActionService().addCoiUserRole(mapping, form, coiDisclosureDocument.getCoiDisclosure(), coiUserRole);
            coiDisclosureForm.getDisclosureActionHelper().setNewCoiUserRole(new CoiUserRole());
        }
        
        return forward;

    }

    public ActionForward deleteCoiUserRole(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        CoiDisclosureForm coiDisclosureForm = (CoiDisclosureForm) form;
        CoiDisclosureDocument coiDisclosureDocument = coiDisclosureForm.getCoiDisclosureDocument();
        int index = getSelectedLine(request);
        forward = getCoiDisclosureActionService().deleteCoiUserRole(mapping, form, coiDisclosureDocument.getCoiDisclosure(), index);
        return forward;
    }
    
    @Override
    public ActionForward reload(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ActionForward actionForward = super.reload(mapping, form, request, response);
        CoiDisclosureForm coiDisclosureForm = (CoiDisclosureForm) form;
        CoiDisclosure coiDisclosure = coiDisclosureForm.getCoiDisclosureDocument().getCoiDisclosure();
        return actionForward;
    }

}
