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
package org.kuali.kra.protocol.permission;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.common.permissions.impl.bo.PermissionsRoleState;
import org.kuali.coeus.common.permissions.impl.bo.PermissionsUserEditRoles;
import org.kuali.coeus.common.permissions.impl.web.bean.Role;
import org.kuali.coeus.common.permissions.impl.web.bean.User;
import org.kuali.coeus.common.permissions.impl.web.struts.action.PermissionsActionHelperBase;
import org.kuali.coeus.common.permissions.impl.web.struts.form.PermissionsForm;
import org.kuali.coeus.common.permissions.impl.web.struts.form.PermissionsHelperBase;
import org.kuali.coeus.common.framework.auth.perm.KcAuthorizationService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.protocol.ProtocolActionBase;
import org.kuali.kra.protocol.ProtocolDocumentBase;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.util.KRADConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * The ProtocolBase Permissions Action Helper performs all of the presentation logic
 * for the Permissions tab web page.  The ProtocolPermissionsAction delegates all
 * of the work to this helper.
 */
public class ProtocolPermissionsActionHelper extends PermissionsActionHelperBase {


    private static final long serialVersionUID = -4490013895383993701L;

    /**
     * Constructs a ProtocolPermissionsActionHelper.
     * @param parentAction the parent Action instance that will delegate to this helper
     */
    public ProtocolPermissionsActionHelper(ProtocolActionBase parentAction) {
        super(parentAction);
    }
    
    @Override
    protected void addUserToRoleInDatabase(Document document, String userId, String roleName) {
        ProtocolDocumentBase protocolDocument = (ProtocolDocumentBase) document;
        getKraAuthorizationService().addDocumentLevelRole(userId, roleName, protocolDocument.getProtocol());
    }
    
    @Override
    protected void removeUserFromRoleInDatabase(Document document, String userId, String roleName) {
        ProtocolDocumentBase protocolDocument = (ProtocolDocumentBase) document;
        getKraAuthorizationService().removeDocumentLevelRole(userId, roleName, protocolDocument.getProtocol());
    }
    
    /**
     * Get the ProtocolBase Authorization Service.
     * @return the ProtocolBase Authorization Service
     */
    private KcAuthorizationService getKraAuthorizationService() {
        return KcServiceLocator.getService(KcAuthorizationService.class);
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
        editRoles.setPrinipalInvestigator(isPrincipalInvestigator((ProtocolDocumentBase) permissionsForm.getDocument(), user.getPerson().getPersonId()));
            
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
     * Check if user is PI
     */
    private boolean isPrincipalInvestigator(ProtocolDocumentBase protocolDocument, String personId) {
        return StringUtils.equals(personId, protocolDocument.getProtocol().getPrincipalInvestigatorId());
    }    
}
