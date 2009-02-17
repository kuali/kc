/*
 * Copyright 2006-2008 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.irb.web.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.RiceKeyConstants;
import org.kuali.core.question.ConfirmationQuestion;
import org.kuali.core.service.KualiConfigurationService;
import org.kuali.core.web.struts.form.KualiDocumentFormBase;
import org.kuali.kra.common.permissions.web.struts.action.AbstractPermissionsActionHelper;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.irb.web.struts.form.ProtocolForm;
import org.kuali.rice.KNSServiceLocator;
import org.kuali.rice.kns.util.KNSConstants;

/**
 * The ProtocolPermissionsAction responds to user events from the
 * Permissions web page.  
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class ProtocolPermissionsAction extends ProtocolAction {
    
    private static final Log LOG = LogFactory.getLog(ProtocolPermissionsAction.class);
    
    private AbstractPermissionsActionHelper permissionsActionHelper = new ProtocolPermissionsActionHelper(this);
    
    /**
     * @see org.kuali.kra.web.struts.action.KraTransactionalDocumentActionBase#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ActionForward actionForward = super.execute(mapping, form, request, response);
        
        ((ProtocolForm)form).getProtocolHelper().prepareView();
        ((ProtocolForm)form).getPersonnelHelper().prepareView();
        ((ProtocolForm)form).getPermissionsHelper().prepareView();
        
        return actionForward;
    }
    
    /**
     * @see org.kuali.kra.irb.web.struts.action.ProtocolAction#preSave(org.kuali.kra.irb.web.struts.form.ProtocolForm)
     */
    @Override
    protected void preSave(ProtocolForm protocolForm) throws Exception {
        permissionsActionHelper.save(protocolForm);
    }
    
    /**
     * Close the document and take the user back to the index; only after asking the user if they want to save the document first.
     * Only users who have the "canSave()" permission are given this option.
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     * @throws Exception
     */
    @Override
    public ActionForward close(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        KualiDocumentFormBase docForm = (KualiDocumentFormBase) form;
        ProtocolForm protocolForm = (ProtocolForm) form;

        // only want to prompt them to save if they already can save
        if (docForm.getDocumentActionFlags().getCanSave()) {
            Object question = request.getParameter(KNSConstants.QUESTION_INST_ATTRIBUTE_NAME);
            KualiConfigurationService kualiConfiguration = KNSServiceLocator.getKualiConfigurationService();

            // logic for close question
            if (question == null) {
                // ask question if not already asked
                return this.performQuestionWithoutInput(mapping, form, request, response, KNSConstants.DOCUMENT_SAVE_BEFORE_CLOSE_QUESTION, kualiConfiguration.getPropertyString(RiceKeyConstants.QUESTION_SAVE_BEFORE_CLOSE), KNSConstants.CONFIRMATION_QUESTION, KNSConstants.MAPPING_CLOSE, "");
            }
            else {
                Object buttonClicked = request.getParameter(KNSConstants.QUESTION_CLICKED_BUTTON);
                if ((KNSConstants.DOCUMENT_SAVE_BEFORE_CLOSE_QUESTION.equals(question)) && ConfirmationQuestion.YES.equals(buttonClicked)) {
                    permissionsActionHelper.save(protocolForm);
                }
            }
        }

        return super.close(mapping, form, request, response);
    }
    
    /**
     * Get the HTML Page for viewing the Rights (Permissions) for all of
     * the Protocol Roles (Aggregator, Viewer, etc.).
     */
    public ActionForward getPermissionsRoleRights(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        return mapping.findForward(Constants.MAPPING_PERMISSIONS_ROLE_RIGHTS_PAGE);
    }
    
    /**
     * Add a new Protocol User to the list of users who can access a protocol.
     * The user may be assigned any of the roles or the special unassigned role.
     */
    public ActionForward addUser(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        return permissionsActionHelper.addUser(mapping, form, request, response);
    }
    
    /**
     * Delete a Protocol User from the list of users who can access a protocol.
     */
    public ActionForward deleteUser(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        return permissionsActionHelper.deleteUser(mapping, form, request, response);
    }
    
    /**
     * If the the end-user confirms that a user must be deleted from the list of
     * protocol users, then do so.
     */
    public ActionForward confirmDeletePermissionsUser(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return permissionsActionHelper.confirmDeletePermissionsUser(mapping, form, request, response);
    }      
    
    /**
     * Display the Edit Roles HTML web page.  When the "edit role" button is pressed, the Edit Roles
     * web page is displayed.  The roles that the user is assigned to can then be modified.
     */
    public ActionForward editRoles(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                HttpServletResponse response) throws Exception {
        return permissionsActionHelper.editRoles( mapping, form, request, response) ;
    }
    
    /**
     * Set the roles for a user for a given protocol.  The setEditRoles() method works in conjunction
     * with the above editRoles() method.  The editRoles() method causes the Edit Roles web page to
     * be displayed.  The setEditRoles() is invoked when the user clicks on the save button.
     */
    public ActionForward setEditRoles(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                HttpServletResponse response) throws Exception {
       return permissionsActionHelper.setEditRoles(mapping, form, request, response);
    }
}