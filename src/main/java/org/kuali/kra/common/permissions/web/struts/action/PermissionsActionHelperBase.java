/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.common.permissions.web.struts.action;

import static org.apache.commons.lang.StringUtils.replace;
import static org.kuali.kra.infrastructure.KraServiceLocator.getService;
import static org.kuali.rice.kns.util.KNSConstants.CONFIRMATION_QUESTION;
import static org.kuali.rice.kns.util.KNSConstants.QUESTION_INST_ATTRIBUTE_NAME;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.common.permissions.bo.PermissionsRoleState;
import org.kuali.kra.common.permissions.bo.PermissionsUser;
import org.kuali.kra.common.permissions.bo.PermissionsUserEditRoles;
import org.kuali.kra.common.permissions.rule.event.AddPermissionsUserEvent;
import org.kuali.kra.common.permissions.rule.event.DeletePermissionsUserEvent;
import org.kuali.kra.common.permissions.rule.event.EditUserPermissionsRolesEvent;
import org.kuali.kra.common.permissions.web.bean.Role;
import org.kuali.kra.common.permissions.web.bean.RoleState;
import org.kuali.kra.common.permissions.web.bean.User;
import org.kuali.kra.common.permissions.web.bean.UserState;
import org.kuali.kra.common.permissions.web.struts.form.PermissionsForm;
import org.kuali.kra.common.permissions.web.struts.form.PermissionsHelperBase;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.web.struts.action.KraTransactionalDocumentActionBase;
import org.kuali.kra.web.struts.action.StrutsConfirmation;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.rule.event.KualiDocumentEvent;
import org.kuali.rice.kns.service.KualiConfigurationService;
import org.kuali.rice.kns.service.KualiRuleService;
import org.kuali.rice.kns.util.KNSConstants;

/**
 * The PermissionsActionHelperBase is a delegate for supporting the Permissions Tab web page.
 * A subclass is necessary to perform operations specific to the document and roles for the 
 * Permissions web page, e.g. saving users and roles to the database.
 */
public abstract class PermissionsActionHelperBase implements Serializable {
    
    private static final String ADD_USER_METHOD = "addUser";
    private static final String DELETE_USER_METHOD = "deleteUser";
    private static final String SET_EDIT_ROLES_METHOD = "setEditRoles";
    
    private KraTransactionalDocumentActionBase parentAction;
    
    protected PermissionsActionHelperBase(KraTransactionalDocumentActionBase parentAction) {
        this.parentAction = parentAction;
    }
    
    /**
     * Assign a user to the given role for a specific document.
     * @param document the document
     * @param userName the user's unique userName
     * @param roleName the unique name of the role
     */
    protected abstract void addUserToRoleInDatabase(Document document, String userName, String roleName);
   
    /**
     * Remove a user from a role for a specific document.
     * @param document the document
     * @param userName the user's unique userName
     * @param roleName the unique name of the role
     */
    protected abstract void removeUserFromRoleInDatabase(Document document, String userName, String roleName);
   
    /**
     * Save the changes to the database.  All of the changes involve
     * either adding or removing roles for a user.
     * @param form the form
     * @throws Exception
     */
    public void save(PermissionsForm form) throws Exception {
        List<UserState> userStates = form.getPermissionsHelper().getUserStates();
        removeRolesFromUsers(form.getDocument(), userStates);
        addRolesToUsers(form.getDocument(), userStates);
        removeUsersWithNoRoles(userStates);
    }
    
    /*
     * If a user no longer has a given role, remove it.  Also update its
     * current state to false to indicate that it has been removed from
     * the database.  This is necessary for any future changes to users 
     * and their roles. 
     */
    private void removeRolesFromUsers(Document document, List<UserState> userStates) {
        for (UserState userState : userStates) {
            List<RoleState> roleStates = userState.getRoleStates();
            for (RoleState roleState : roleStates) {
                if (roleState.needsToBeRemoved()) {
                    removeUserFromRoleInDatabase(document, userState.getPerson().getUserName(), roleState.getRole().getName());
                    roleState.setSaved(false);
                }
            }
        }
    }
    
    /*
     * If a role has been assigned a role but it is not yet in the database,
     * then make the change to the database.  Also update its
     * current state to true to indicate that it has been added to
     * the database.  This is necessary for any future changes to users 
     * and their roles. 
     */
    private void addRolesToUsers(Document document, List<UserState> userStates) {
        for (UserState userState : userStates) {
            List<RoleState> roleStates = userState.getRoleStates();
            for (RoleState roleState : roleStates) {
                if (roleState.needsToBeAdded()) {
                    addUserToRoleInDatabase(document, userState.getPerson().getUserName(), roleState.getRole().getName());
                    roleState.setSaved(true);
                }
            }
        }
    }
    
    /*
     * If a user has been deleted from the document, then he/she won't
     * have any more roles for that document.  If so, we can remove
     * that user from the list of userStates.  NOTE: For Java newbies
     * please note that the for-each loop cannot be used to traverse
     * a list and remove entries; an iterator must be used.
     */
    private void removeUsersWithNoRoles(List<UserState> userStates) {
        Iterator<UserState> iter = userStates.iterator();
        while (iter.hasNext()) {
            UserState userState = iter.next();
            if (!userState.hasAnyRole()) {
                iter.remove();
            }
        }
    }
    
    /**
     * Get the Role Rights web page.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward getRoleRights(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        return mapping.findForward(Constants.MAPPING_PERMISSIONS_ROLE_RIGHTS_PAGE);
    }

    /**
     * Add a new user with a role to the document.
     * @param mapping 
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public final ActionForward addUser(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
         
        PermissionsForm permissionsForm = (PermissionsForm) form;
        PermissionsHelperBase permissionsHelper = permissionsForm.getPermissionsHelper();
        Document doc = permissionsForm.getDocument();
        
        // early exit if the end-user is not authorized
        if (!permissionsHelper.canModifyPermissions()) {
            return parentAction.processAuthorizationViolation(ADD_USER_METHOD, mapping, form, request, response);
        }
        
        PermissionsUser newUser = permissionsForm.getPermissionsHelper().getNewUser();            
        boolean rulePassed = applyRules(new AddPermissionsUserEvent(doc, permissionsForm.getPermissionsHelper().getUsers(), newUser));
        if (rulePassed) {
            permissionsForm.getPermissionsHelper().addNewUser();
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * Delete a user and his/her roles from the document.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public final ActionForward deleteUser(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
       
        ActionForward actionForward = mapping.findForward(Constants.MAPPING_BASIC);
        
        PermissionsForm permissionsForm = (PermissionsForm) form;
        PermissionsHelperBase permissionsHelper = permissionsForm.getPermissionsHelper();
        Document doc = permissionsForm.getDocument();
        
        // early exit if not authorized
        if (!permissionsHelper.canModifyPermissions()) {
            actionForward = parentAction.processAuthorizationViolation(DELETE_USER_METHOD, mapping, form, request, response);
        }
        else {
        
            // The lineNum is the index into "users" of the user to be deleted.
            int lineNum = getLineToDelete(request);
            List<User> users = permissionsForm.getPermissionsHelper().getUsers();
            
            boolean rulePassed = applyRules(new DeletePermissionsUserEvent(doc, users, lineNum));
            if (rulePassed) {
                // ask for a confirmation before deleting the user from the list
                actionForward = parentAction.confirm(buildDeleteUserConfirmationQuestion(mapping, form, request, response), Constants.CONFIRM_DELETE_PERMISSIONS_USER_KEY, "");
            }
        }
        
        return actionForward;
    }
    
    /**
     * If the the end-user confirms that a user must be deleted from the list, then do so.
     * @param mapping the mapping associated with this action.
     * @param form the form.
     * @param request the HTTP request
     * @param response the HTTP response
     * @return the Permissions web page
     * @throws Exception
     */
    public ActionForward confirmDeletePermissionsUser(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Object question = request.getParameter(QUESTION_INST_ATTRIBUTE_NAME);
        if (Constants.CONFIRM_DELETE_PERMISSIONS_USER_KEY.equals(question)) { 
            PermissionsForm permissionsForm = (PermissionsForm) form;
            int lineNum = getLineToDelete(request);
            User user = permissionsForm.getPermissionsHelper().getUsers().get(lineNum);
            UserState userState = permissionsForm.getPermissionsHelper().getUserState(user.getPerson().getUserName());
            if (userState != null) {
                userState.clearAssignments();
            }
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }      
    
    /*
     * Build the confirmation question that will be asked of the end-user 
     * before deleting a user from the list.
     */
    private StrutsConfirmation buildDeleteUserConfirmationQuestion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        PermissionsForm permissionsForm = (PermissionsForm) form;
        String fullname = permissionsForm.getPermissionsHelper().getUsers().get(getLineToDelete(request)).getPerson().getFullName();
        return buildParameterizedConfirmationQuestion(mapping, form, request, response, Constants.CONFIRM_DELETE_PERMISSIONS_USER_KEY, KeyConstants.QUESTION_DELETE_PROPOSAL_USER_CONFIRMATION, fullname);
    }
    
    /*
     * Generically creates a <code>{@link StrutsConfirmation}</code> instance while deriving the question from a resource bundle.<br/>
     * <br/> In this case, the question in the resource bundle is expected to be parameterized. This method takes this into account,
     * and passes parameters and replaces tokens in the question with the parameters.
     */
    private StrutsConfirmation buildParameterizedConfirmationQuestion(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response, String questionId, String configurationId, String... params)
            throws Exception {
        
        StrutsConfirmation retval = new StrutsConfirmation();
        retval.setMapping(mapping);
        retval.setForm(form);
        retval.setRequest(request);
        retval.setResponse(response);
        retval.setQuestionId(questionId);
        retval.setQuestionType(CONFIRMATION_QUESTION);

        KualiConfigurationService kualiConfiguration = getService(KualiConfigurationService.class);
        String questionText = kualiConfiguration.getPropertyString(configurationId);

        for (int i = 0; i < params.length; i++) {
            questionText = replace(questionText, "{" + i + "}", params[i]);
        }
        retval.setQuestionText(questionText);

        return retval;
    }
    
    /**
     * Edit the roles for a user.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward editRoles(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        PermissionsForm permissionsForm = (PermissionsForm) form;
        PermissionsHelperBase permissionsHelper = permissionsForm.getPermissionsHelper();
           
        int lineNum = getLineNum(request);
        User user = permissionsHelper.getUsers().get(lineNum);
            
        /*
         * Create an Edit Roles BO that will be used by the form for setting
         * the boolean flags for the roles. 
         */   
        PermissionsUserEditRoles editRoles = new PermissionsUserEditRoles();
        editRoles.setLineNum(lineNum);
        editRoles.setJavaScriptEnabled(isJavaScriptEnabled(request));
        editRoles.setUserName(user.getPerson().getUserName());
            
        List<PermissionsRoleState> roleStates = new ArrayList<PermissionsRoleState>();
        List<Role> roles = permissionsHelper.getNormalRoles();
        for (Role role : roles) {
            PermissionsRoleState roleState = new PermissionsRoleState(role);
            roleStates.add(roleState);
        }
        editRoles.setRoleStates(roleStates);
        
        /*
         * Initialize the Edit Roles BO to the roles that the user is currently assigned to.
         */
        List<Role> userRoles = user.getRoles();
        for (Role userRole : userRoles) {
            editRoles.setRoleState(userRole.getName(), Boolean.TRUE);
        }
        
        permissionsHelper.setUserEditRoles(editRoles);
        
        return mapping.findForward(Constants.MAPPING_PERMISSIONS_EDIT_ROLES_PAGE);
    }
    
    /**
     * Set the roles for a user for a given document.  The setEditRoles() method works in conjunction
     * with the above editRoles() method.  The editRoles() method causes the Edit Roles web page to
     * be displayed.  The setEditRoles() is invoked when the user clicks on the save button.  Note that
     * the Edit Roles BO created in editRoles() is retrieved in setEditRoles() to determine the user
     * and what the new roles should be.
     * 
     * @param mapping the mapping associated with this action.
     * @param form the form.
     * @param request the HTTP request
     * @param response the HTTP response
     * @return the next web page to display
     * @throws Exception
     */
    public ActionForward setEditRoles(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                HttpServletResponse response) throws Exception {
       
        ActionForward actionForward = null;
        
        PermissionsHelperBase permissionsHelper = ((PermissionsForm) form).getPermissionsHelper();
        PermissionsForm permissionsForm = (PermissionsForm) form;
        Document doc = permissionsForm.getDocument();
        
        // early exit if not authorized
        if (!permissionsHelper.canModifyPermissions()) {
            return parentAction.processAuthorizationViolation(SET_EDIT_ROLES_METHOD, mapping, form, request, response);
        }
        
        // The Edit Roles BO contains the username, javascriptEnabled, and the new set of
        // roles to set for the user in the document.
        
        PermissionsUserEditRoles editRoles = permissionsHelper.getEditRoles();
       
        // check any business rules
        boolean rulePassed = applyRules(new EditUserPermissionsRolesEvent(doc, permissionsHelper.getUsers(), editRoles));
        if (!rulePassed) {
            
            // If the user can't perform the save of the roles, then we will report the
            // error on the Edit Roles web page.  This allows the user to fix the error
            // and re-submit the save.
            
            actionForward = mapping.findForward(Constants.MAPPING_PERMISSIONS_EDIT_ROLES_PAGE);
        } 
        else {
            updateRoles(editRoles, permissionsHelper);
            
            // If Javascript was enabled, we can simply cause the pop-up window to close.
            // If not, then we must return the user to the Permissions page.
            
            if (editRoles.getJavaScriptEnabled()) {
                actionForward = mapping.findForward(Constants.MAPPING_PERMISSIONS_CLOSE_EDIT_ROLES_PAGE);
            } 
            else {
                actionForward = mapping.findForward(Constants.MAPPING_BASIC);
            }
        }
        
        return actionForward;
    }
    
    /*
     * Update the roles for the user.
     * @param editRoles the new set of roles for the user
     * @param permissionsHelper the PermissionsHelper
     */
    private void updateRoles(PermissionsUserEditRoles editRoles , PermissionsHelperBase permissionsHelper) {
        
        /*
         * Clear the current roles before setting the new ones
         */
        UserState userState = permissionsHelper.getUserState(editRoles.getUserName());
        userState.clearAssignments();
        
        /*
         * Set the new roles.
         */
        List<PermissionsRoleState> roleStates = editRoles.getRoleStates();
        for (PermissionsRoleState roleState : roleStates) {
            if (roleState.getState()) {
                userState.setAssigned(roleState.getRole().getName(), true);   
            }
        }
        
        // If the user isn't assigned to any of the normal roles, then he/she will
        // be assigned to the unassigned role.  
        
        if (!userState.hasAnyRole() && permissionsHelper.getUnassignedRoleName() != null) {
            userState.setAssigned(permissionsHelper.getUnassignedRoleName(), true);
        }
    }

    /*
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
    
    /*
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
    
    /*
     * Parses the method to call attribute to pick off the line number which should be deleted.
     */
    private int getLineToDelete(HttpServletRequest request) {
        return getSelectedLine(request);
    }

    /*
     * Parses the method to call attribute to pick off the line number which should have an action performed on it.
     */
    private int getSelectedLine(HttpServletRequest request) {
        int selectedLine = -1;
        String parameterName = (String) request.getAttribute(KNSConstants.METHOD_TO_CALL_ATTRIBUTE);
        if (StringUtils.isNotBlank(parameterName)) {
            String lineNumber = StringUtils.substringBetween(parameterName, ".line", ".");
            selectedLine = Integer.parseInt(lineNumber);
        }

        return selectedLine;
    }
    
    /*
     * Get the Kuali Rule Service.
     */
    private KualiRuleService getKualiRuleService() {
        return getService(KualiRuleService.class);
    }
    
    /*
     * Use the Kuali Rule Service to apply the rules for the given event.
     */
    protected final boolean applyRules(KualiDocumentEvent event) {
        return getKualiRuleService().applyRules(event);
    }
}
