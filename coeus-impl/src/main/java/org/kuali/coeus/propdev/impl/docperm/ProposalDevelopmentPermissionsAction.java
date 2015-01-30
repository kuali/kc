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
package org.kuali.coeus.propdev.impl.docperm;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.propdev.impl.auth.perm.ProposalDevelopmentPermissionsService;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentAction;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentForm;
import org.kuali.coeus.common.framework.auth.perm.KcAuthorizationService;
import org.kuali.coeus.sys.framework.controller.StrutsConfirmation;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.coeus.propdev.impl.attachment.LegacyNarrativeService;
import org.kuali.rice.kim.api.identity.PersonService;
import org.kuali.rice.kns.web.struts.form.KualiDocumentFormBase;
import org.kuali.rice.krad.service.KualiRuleService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.MessageMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static org.kuali.coeus.sys.framework.service.KcServiceLocator.getService;
import static org.kuali.kra.infrastructure.Constants.MAPPING_BASIC;
import static org.kuali.rice.krad.util.KRADConstants.QUESTION_INST_ATTRIBUTE_NAME;

/**
 * The ProposalDevelopmentPermissionsAction responds to user events from the
 * Permissions web page.  
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class ProposalDevelopmentPermissionsAction extends ProposalDevelopmentAction {
    
    private ProposalDevelopmentPermissionsService proposalDevelopmentPermissionsService;
    private PersonService personService;

    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
       
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument doc = proposalDevelopmentForm.getProposalDevelopmentDocument();
        
        List<ProposalUserRoles> currentProposalUsers = proposalDevelopmentForm.getCurrentProposalUserRoles();
        List<ProposalUserRoles> proposalUsers = proposalDevelopmentForm.getProposalUserRoles();

        getProposalDevelopmentPermissionsService().savePermissions(doc, currentProposalUsers, proposalUsers);
        
        return super.save(mapping, form, request, response);
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
    protected ActionForward saveOnClose(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        KualiDocumentFormBase docForm = (KualiDocumentFormBase) form;
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument doc = proposalDevelopmentForm.getProposalDevelopmentDocument();
        getProposalDevelopmentPermissionsService().savePermissions(doc, proposalDevelopmentForm.getCurrentProposalUserRoles(), 
                proposalDevelopmentForm.getProposalUserRoles());

        return super.saveOnClose(mapping, form, request, response);
    }
    
    /**
     * Get the HTML Page for viewing the Rights (Permissions) for all of
     * the Proposal Roles (Aggregator, Budget Creator, Narrative Writer, and Viewer).
     * 
     * @param mapping the mapping associated with this action.
     * @param form the Proposal Development form.
     * @param request the HTTP request
     * @param response the HTTP response
     * @return the name of the THTML page to display
     * @throws Exception
     */
    public ActionForward getPermissionsRoleRights(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        return mapping.findForward(Constants.MAPPING_PERMISSIONS_ROLE_RIGHTS_PAGE);
    }
    
    /**
     * Add a new Proposal User to the list of users who can access a proposal.
     * The user may be assigned any of the roles or the special unassigned role.
     * 
     * @param mapping the mapping associated with this action.
     * @param form the Proposal Development form.
     * @param request the HTTP request
     * @param response the HTTP response
     * @return the name of the HTML page to display
     * @throws Exception
     */
    public ActionForward addProposalUser(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * Delete a Proposal User from the list of users who can access a proposal.
     * 
     * @param mapping the mapping associated with this action.
     * @param form the Proposal Development form.
     * @param request the HTTP request
     * @param response the HTTP response
     * @return the next HTML page to display
     * @throws Exception
     */
    public ActionForward deleteProposalUser(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument doc = proposalDevelopmentForm.getProposalDevelopmentDocument();
        
        // Find the proposal user to be deleted.
        
        int lineNum = getLineToDelete(request);
        List<ProposalUserRoles> proposalUserRolesList = proposalDevelopmentForm.getProposalUserRoles();

        // check any business rules
        boolean rulePassed = getKualiRuleService().applyRules(new DeleteProposalUserEvent(doc, proposalUserRolesList, lineNum));
        
        // if the rule evaluation passed, let's add it
        if (rulePassed) {
            // ask for a confirmation before deleting the user from the list
            return confirm(buildDeleteProposalUserConfirmationQuestion(mapping, form, request, response), Constants.CONFIRM_DELETE_PROPOSAL_USER_KEY, "");
        }
        else {
            return mapping.findForward(Constants.MAPPING_BASIC);
        }
    }
    
    /**
     * If the the user confirms that a user must be deleted from the list of
     * proposal users, then do so.
     * 
     * @param mapping the mapping associated with this action.
     * @param form the Proposal Development form.
     * @param request the HTTP request
     * @param response the HTTP response
     * @return the Permissions web page
     * @throws Exception
     */
    public ActionForward confirmDeleteProposalUser(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Object question = request.getParameter(QUESTION_INST_ATTRIBUTE_NAME);
        if (Constants.CONFIRM_DELETE_PROPOSAL_USER_KEY.equals(question)) { 
            ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
            ProposalDevelopmentDocument doc = proposalDevelopmentForm.getProposalDevelopmentDocument();
            
            int lineNum = getLineToDelete(request);
            List<ProposalUserRoles> proposalUserRolesList = proposalDevelopmentForm.getProposalUserRoles();
            ProposalUserRoles proposalUserRoles = proposalUserRolesList.get(lineNum);
            proposalUserRolesList.remove(lineNum);
            
            // Remove the user from all of the Narratives.
            
            LegacyNarrativeService narrativeService = KcServiceLocator.getService(LegacyNarrativeService.class);
            narrativeService.deletePerson(getPersonService().getPersonByPrincipalName(proposalUserRoles.getUsername()).getPrincipalId(), doc); 
        }
        
        return mapping.findForward(MAPPING_BASIC);
    }      
    
    /**
     * Build the confirmation question that will be asked of the user.
     * 
     * @param mapping the mapping associated with this action.
     * @param form the Proposal Development form.
     * @param request the HTTP request
     * @param response the HTTP response
     * @return the struts confirmation
     * @throws Exception
     */
    private StrutsConfirmation buildDeleteProposalUserConfirmationQuestion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        String fullname = proposalDevelopmentForm.getProposalUserRoles().get(getLineToDelete(request)).getFullname();
        return buildParameterizedConfirmationQuestion(mapping, form, request, response, Constants.CONFIRM_DELETE_PROPOSAL_USER_KEY, KeyConstants.QUESTION_DELETE_PROPOSAL_USER_CONFIRMATION, fullname);
    }
    
    /**
     * Display the Edit Roles HTML web page.  When the "edit role" button is pressed, the Edit Roles
     * web page is displayed.  The roles that the user is assigned to can then be modified.
     * 
     * @param mapping the mapping associated with this action.
     * @param form the Proposal Development form.
     * @param request the HTTP request
     * @param response the HTTP response
     * @return the EDIT ROLES HTML page
     * @throws Exception
     */
    public ActionForward editRoles(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                HttpServletResponse response) throws Exception {
 
        // Find the Proposal User whose roles will be modified.
        
        int lineNum = getLineNum(request);
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalUserRoles proposalUserRoles = proposalDevelopmentForm.getProposalUserRoles().get(lineNum);
        

        return mapping.findForward(Constants.MAPPING_PERMISSIONS_EDIT_ROLES_PAGE);
    }
    
    /**
     * Set the roles for a user for a given proposal.  The setEditRoles() method works in conjunction
     * with the above editRoles() method.  The editRoles() method causes the Edit Roles web page to
     * be displayed.  The setEditRoles() is invoked when the user clicks on the save button.  Note that
     * the Edit Roles BO created in editRoles() is retrieved in setEditRoles() to determine the user
     * and what the new roles should be.
     * 
     * @param mapping the mapping associated with this action.
     * @param form the Proposal Development form.
     * @param request the HTTP request
     * @param response the HTTP response
     * @return the next web page to display
     * @throws Exception
     */
    public ActionForward setEditRoles(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                HttpServletResponse response) throws Exception {
       
        ActionForward actionForward = null;
        
        KcAuthorizationService kraAuthorizationService = KcServiceLocator.getService(KcAuthorizationService.class);
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument doc = proposalDevelopmentForm.getProposalDevelopmentDocument();
        
        // The Edit Roles BO contains the username, javascriptEnabled, and the new set of
        // roles to set for the user in the proposal.

        return actionForward;
    }

    /**
     * Get the line number of the user to operate on.  
     * 
     * @param request the HTTP request
     * @return the line number
     */
    private int getLineNum(HttpServletRequest request) {
        
        // If JavaScript is enabled, the line is returned to the web server
        // as an HTTP parameter.  If not, it is embedded within the "methodToCall" syntax.
        
        String lineNumStr = request.getParameter("line");
        try {
            return Integer.parseInt(lineNumStr);
        } catch (Exception ex) {
            return this.getLineToDelete(request);
        }
    }
    
    /**
     * Is JavaScript enabled on the user's browser?  This implementation is
     * a bit of a hack.  When JavaScript is enabled, the line is returned
     * to the web server as an HTTP request.  If not, it is embedded with
     * the "methodToCall" syntax.
     * 
     * @param request the HTTP request
     * @return true if JavaScript is enabled; otherwise false
     */
    private boolean isJavaScriptEnabled(HttpServletRequest request) {
        String lineNumStr = request.getParameter("line");
        try {
            Integer.parseInt(lineNumStr);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
    
    @Override
    public ActionForward processAuthorizationViolation(String taskName, ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward = null;
        if (!StringUtils.equals(taskName, "setEditRoles")) {
            forward = super.processAuthorizationViolation(taskName, mapping, form, request, response);
        }
        else {
            MessageMap errorMap = GlobalVariables.getMessageMap();
            errorMap.putError(Constants.EDIT_ROLES_PROPERTY_KEY, KeyConstants.AUTHORIZATION_VIOLATION);
            forward = mapping.findForward(Constants.MAPPING_PERMISSIONS_EDIT_ROLES_PAGE);
        }
        return forward;
    }
    
    /**
     * Get the Kuali Rule Service.
     * @return the Kuali Rule Service
     */
    protected KualiRuleService getKualiRuleService() {
        return getService(KualiRuleService.class);
    }
    
    
    /**
     * Saves new viewers to the list of users who can access a proposal.
     * 
     * @param mapping the mapping associated with this action.
     * @param form the Proposal Development form.
     * @param request the HTTP request
     * @param response the HTTP response
     * @return the name of the HTML page to display
     * @throws Exception
     */
    public ActionForward saveViewers(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument doc = proposalDevelopmentForm.getProposalDevelopmentDocument();

        List<ProposalUserRoles> currentProposalUsers = proposalDevelopmentForm.getCurrentProposalUserRoles();
        List<ProposalUserRoles> newProposalUsers = proposalDevelopmentForm.getProposalUserRoles();
        OUTER: for (ProposalUserRoles proposalUser : newProposalUsers) {
            if (!currentProposalUsers.contains(proposalUser)) {
                for (String roleName : proposalUser.getRoleNames()) {
                    if (!StringUtils.equals(roleName, RoleConstants.VIEWER)) {
                        continue OUTER;
                    }
                }
                getProposalDevelopmentPermissionsService().saveProposalUser(proposalUser, doc);
            }
        }

        return mapping.findForward(Constants.MAPPING_BASIC);
    }



    protected ProposalDevelopmentPermissionsService getProposalDevelopmentPermissionsService() {
        if (proposalDevelopmentPermissionsService == null) {
            proposalDevelopmentPermissionsService = KcServiceLocator.getService(ProposalDevelopmentPermissionsService.class);
        }
        return proposalDevelopmentPermissionsService;
    }



    public void setProposalDevelopmentPermissionsService(ProposalDevelopmentPermissionsService proposalDevelopmentPermissionsService) {
        this.proposalDevelopmentPermissionsService = proposalDevelopmentPermissionsService;
    }



    public PersonService getPersonService() {
        return personService;
    }



    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }
}
