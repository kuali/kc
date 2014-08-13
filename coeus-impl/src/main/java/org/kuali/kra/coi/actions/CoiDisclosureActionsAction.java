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
package org.kuali.kra.coi.actions;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
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
            ActionForward holdingPageForward = mapping.findForward(Constants.MAPPING_HOLDING_PAGE);
    
            return routeToHoldingPage(basicForward, basicForward, holdingPageForward, returnLocation);
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
            ActionForward holdingPageForward = mapping.findForward(Constants.MAPPING_HOLDING_PAGE);
    
            return routeToHoldingPage(basicForward, basicForward, holdingPageForward, returnLocation);
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
