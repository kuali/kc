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
package org.kuali.kra.irb.permission;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.common.permissions.impl.bo.PermissionsRoleState;
import org.kuali.coeus.common.permissions.impl.bo.PermissionsUserEditRoles;
import org.kuali.coeus.common.permissions.impl.rule.event.EditUserPermissionsRolesEvent;
import org.kuali.coeus.common.permissions.impl.web.bean.Role;
import org.kuali.coeus.common.permissions.impl.web.bean.RoleState;
import org.kuali.coeus.common.permissions.impl.web.bean.User;
import org.kuali.coeus.common.permissions.impl.web.bean.UserState;
import org.kuali.coeus.common.permissions.impl.web.struts.action.PermissionsActionHelperBase;
import org.kuali.coeus.common.permissions.impl.web.struts.form.PermissionsForm;
import org.kuali.coeus.common.permissions.impl.web.struts.form.PermissionsHelperBase;
import org.kuali.coeus.common.framework.auth.perm.KcAuthorizationService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.util.KRADConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * The Protocol Permissions Action Helper performs all of the presentation logic
 * for the Permissions tab web page.  The ProtocolPermissionsAction delegates all
 * of the work to this helper.
 */
class ProtocolPermissionsActionHelper extends PermissionsActionHelperBase {

    /**
     * Constructs a ProtocolPermissionsActionHelper.
     * @param parentAction the parent Action instance that will delegate to this helper
     */
    ProtocolPermissionsAction protocolPermissionsAction; 
    private static final String SET_EDIT_ROLES_METHOD = "setEditRoles";
    private static final String MAINTAIN_IRB_QUESTIONNAIRE = "Maintain IRB Questionnaire";
    private static final String PROTOCOL_DELETER = "Protocol Deleter";
    
    public ProtocolPermissionsActionHelper(ProtocolPermissionsAction protocolPermissionsAction) {
        super(protocolPermissionsAction);
        this.protocolPermissionsAction = protocolPermissionsAction;
    }
    
    @Override
    protected void addUserToRoleInDatabase(Document document, String userId, String roleName) {
        ProtocolDocument protocolDocument = (ProtocolDocument) document;
        getKraAuthorizationService().addDocumentLevelRole(userId, roleName, protocolDocument.getProtocol());
    }
    
    @Override
    protected void removeUserFromRoleInDatabase(Document document, String userId, String roleName) {
        ProtocolDocument protocolDocument = (ProtocolDocument) document;
        getKraAuthorizationService().removeDocumentLevelRole(userId, roleName, protocolDocument.getProtocol());
    }
    
    /**
     * Get the Protocol Authorization Service.
     * @return the Protocol Authorization Service
     */
    private KcAuthorizationService getKraAuthorizationService() {
        return KcServiceLocator.getService(KcAuthorizationService.class);
    }
    @Override
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
        editRoles.setPrinipalInvestigator(isPrincipalInvestigator((ProtocolDocument) permissionsForm.getDocument(), user.getPerson().getPersonId()));
            
        List<PermissionsRoleState> roleStates = new ArrayList<PermissionsRoleState>();
        List<Role> roles = permissionsHelper.getNormalRoles();
        for (Role role : roles) {
            if (RoleConstants.VIEWER.equalsIgnoreCase(role.getDisplayName()) 
                    || RoleConstants.AGGREGATOR.equalsIgnoreCase(role.getDisplayName()) 
                            || role.getDisplayName().equalsIgnoreCase(PROTOCOL_DELETER)) {
                PermissionsRoleState roleState = new PermissionsRoleState(role);
                roleStates.add(roleState);
            }
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
    @Override
    public ActionForward setEditRoles(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                HttpServletResponse response) throws Exception {
       
        ActionForward actionForward = null;
        
        PermissionsHelperBase permissionsHelper = ((PermissionsForm) form).getPermissionsHelper();
        PermissionsForm permissionsForm = (PermissionsForm) form;
        Document doc = permissionsForm.getDocument();
        
        // early exit if not authorized
        if (!permissionsHelper.canModifyPermissions()) {
            return protocolPermissionsAction.processAuthorizationViolation(SET_EDIT_ROLES_METHOD, mapping, form, request, response);
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
        Role rolePI = null;
        Role roleCOI = null;
        UserState userState = permissionsHelper.getUserState(editRoles.getUserName());
        List<RoleState> statesList = userState.getRoleStates();
        for (RoleState roleState : statesList) {
            if (Constants.CO_INVESTIGATOR_ROLE.equalsIgnoreCase(roleState.getRole().getName()) && roleState.isAssigned()) {
                roleCOI = roleState.getRole();
            }
            if (Constants.PRINCIPAL_INVESTIGATOR_ROLE.equalsIgnoreCase(roleState.getRole().getName()) && roleState.isAssigned()) {
                rolePI = roleState.getRole();
            }
        }        
        userState.clearAssignments();
        
        
        /*
         * Set the new roles.
         */
        
        
        List<PermissionsRoleState> roleStates = editRoles.getRoleStates();
        PermissionsRoleState  permissionsRoleState = null;        
       
        if (roleCOI != null) {
            permissionsRoleState = new PermissionsRoleState(roleCOI);
            permissionsRoleState.setState(true);
            if (permissionsRoleState != null || permissionsRoleState.getState()) {
                roleStates.add(permissionsRoleState);
            }
        }
        
        if (rolePI != null) {
            permissionsRoleState = new PermissionsRoleState(rolePI);
            permissionsRoleState.setState(true);
            if (permissionsRoleState != null || permissionsRoleState.getState()) {
                roleStates.add(permissionsRoleState);
            }
        }
       
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
        String parameterName = (String) request.getAttribute(KRADConstants.METHOD_TO_CALL_ATTRIBUTE);
        if (StringUtils.isNotBlank(parameterName)) {
            String lineNumber = StringUtils.substringBetween(parameterName, ".line", ".");
            selectedLine = Integer.parseInt(lineNumber);
        }

        return selectedLine;
    }
    
   
    /*
     * Check if user is PI
     */
    private boolean isPrincipalInvestigator(ProtocolDocument protocolDocument, String personId) {
        return StringUtils.equals(personId, protocolDocument.getProtocol().getPrincipalInvestigatorId());
    }

}
