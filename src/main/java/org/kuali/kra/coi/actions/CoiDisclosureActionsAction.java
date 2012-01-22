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
package org.kuali.kra.coi.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.coi.CoiAction;
import org.kuali.kra.coi.CoiActionType;
import org.kuali.kra.coi.CoiDisclosureDocument;
import org.kuali.kra.coi.CoiDisclosureForm;
import org.kuali.kra.coi.CoiUserRole;
import org.kuali.kra.coi.notification.AssignReviewerNotificationRenderer;
import org.kuali.kra.coi.notification.CoiNotificationContext;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.irb.actions.submit.ProtocolSubmitActionEvent;
import org.kuali.kra.web.struts.action.AuditActionHelper;
import org.kuali.rice.krad.util.GlobalVariables;

public class CoiDisclosureActionsAction extends CoiAction {
    
    /**
     * 
     * This method is to activate data validation
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward activate(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        return new AuditActionHelper().setAuditMode(mapping, (CoiDisclosureForm) form, true);
    }

    /**
     * 
     * This method is to deactivate data validation
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deactivate(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        return new AuditActionHelper().setAuditMode(mapping, (CoiDisclosureForm) form, false);
    }

    /**
     * 
     * This method is for approving coi disclosure
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward approveDisclosure(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        /* what include here
         * 1. if there is previous master discl
         *    - copy details/note/attachment
         *    - set previous master discl's 'currentDisclosure' to false
         *    - set this disclosure's 'currentDisclosure' to true
         * 2. need to do audit check
         * 3. need to check disclosurestatus is selected   
         */
        CoiDisclosureForm coiDisclosureForm = (CoiDisclosureForm) form;
        CoiDisclosureDocument coiDisclosureDocument = coiDisclosureForm.getCoiDisclosureDocument();
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        if (StringUtils.isNotBlank(coiDisclosureForm.getCoiDisclosureStatusCode())) {
            AuditActionHelper auditActionHelper = new AuditActionHelper();
            if (auditActionHelper.auditUnconditionally(coiDisclosureDocument)) {                
                getCoiDisclosureActionService().approveDisclosure(coiDisclosureDocument.getCoiDisclosure(), coiDisclosureForm.getCoiDisclosureStatusCode());
                coiDisclosureForm.getDisclosureHelper().setMasterDisclosureBean(
                        getCoiDisclosureService().getMasterDisclosureDetail(coiDisclosureDocument.getCoiDisclosure()));
                forward = mapping.findForward(MASTER_DISCLOSURE);
            } else {
                GlobalVariables.getMessageMap().clearErrorMessages();
                GlobalVariables.getMessageMap().putError("datavalidation", KeyConstants.ERROR_FINANCIAL_ENTITY_STATUS_INCOMPLETE,  new String[] {});
                new AuditActionHelper().setAuditMode(mapping, (CoiDisclosureForm) form, true);            }
        } else {
            GlobalVariables.getMessageMap().putError("coiDisclosureStatusCode", 
                    KeyConstants.ERROR_COI_DISCLOSURE_STATUS_REQUIRED);        }

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
        
        if (checkRule(new AddCoiReviewerEvent("", coiDisclosureDocument.getCoiDisclosure(), coiUserRole))) {
            coiUserRole.setCoiReviewer(coiDisclosureForm.getDisclosureActionHelper().getCoiReviewer(reviewerCode));
            coiUserRole.setPerson(coiDisclosureForm.getDisclosureActionHelper().getKcPerson(userName));
            getCoiDisclosureActionService().addCoiUserRole(coiDisclosureDocument.getCoiDisclosure(), coiUserRole);
            coiDisclosureForm.getDisclosureActionHelper().setNewCoiUserRole(new CoiUserRole());
            
            CoiNotificationContext context = new CoiNotificationContext(coiDisclosureDocument.getCoiDisclosure(), 
                    CoiActionType.ASSIGN_REVIEWER, "Assign Reviewer", 
                    new AssignReviewerNotificationRenderer(coiDisclosureDocument.getCoiDisclosure(), "Assigned"));
            forward = this.sendNotification(mapping, forward, coiDisclosureForm, context);
        }
        
        return forward;

    }

    public ActionForward deleteCoiUserRole(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        CoiDisclosureForm coiDisclosureForm = (CoiDisclosureForm) form;
        CoiDisclosureDocument coiDisclosureDocument = coiDisclosureForm.getCoiDisclosureDocument();
        int index = getSelectedLine(request);
        getCoiDisclosureActionService().deleteCoiUserRole(coiDisclosureDocument.getCoiDisclosure(), index);
        
        CoiNotificationContext context = new CoiNotificationContext(coiDisclosureDocument.getCoiDisclosure(), 
                CoiActionType.ASSIGN_REVIEWER, "Assign Reviewer", 
                new AssignReviewerNotificationRenderer(coiDisclosureDocument.getCoiDisclosure(), "Removed"));
        forward = this.sendNotification(mapping, forward, coiDisclosureForm, context);
        
        return forward;
    }
}
