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

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.common.permissions.web.struts.action.PermissionsAction;
import org.kuali.kra.irb.web.struts.form.ProtocolForm;
import org.kuali.rice.kns.web.struts.form.KualiDocumentFormBase;

/**
 * The ProtocolPermissionsAction responds to user events from the
 * Permissions web page.  
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class ProtocolPermissionsAction extends ProtocolAction implements PermissionsAction {
     
    private ProtocolPermissionsActionHelper permissionsActionHelper = new ProtocolPermissionsActionHelper(this);
    
    /**
     * @see org.kuali.kra.web.struts.action.KraTransactionalDocumentActionBase#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ActionForward actionForward = super.execute(mapping, form, request, response);

        ((ProtocolForm)form).getPermissionsHelper().prepareView();
        
        return actionForward;
    }
    
    /**
     * @see org.kuali.kra.web.struts.action.KraTransactionalDocumentActionBase#preDocumentSave(org.kuali.core.web.struts.form.KualiDocumentFormBase)
     */
    @Override
    protected void preDocumentSave(KualiDocumentFormBase form) throws Exception {
        super.preDocumentSave(form);
        permissionsActionHelper.save((ProtocolForm) form);
    }
    
    /**
     * @see org.kuali.kra.web.struts.action.KraTransactionalDocumentActionBase#saveOnClose(org.kuali.core.web.struts.form.KualiDocumentFormBase)
     */
    @Override
    protected void saveOnClose(KualiDocumentFormBase form) throws Exception {
        super.saveOnClose(form);
        permissionsActionHelper.save((ProtocolForm) form);
    }
    
    /**
     * @see org.kuali.kra.common.permissions.web.struts.action.PermissionsAction#getPermissionsRoleRights(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public ActionForward getPermissionsRoleRights(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        return permissionsActionHelper.getRoleRights(mapping, form, request, response);
    }
    
    /**
     * @see org.kuali.kra.common.permissions.web.struts.action.PermissionsAction#addUser(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public ActionForward addUser(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        return permissionsActionHelper.addUser(mapping, form, request, response);
    }
    
    /**
     * @see org.kuali.kra.common.permissions.web.struts.action.PermissionsAction#deleteUser(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public ActionForward deleteUser(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        return permissionsActionHelper.deleteUser(mapping, form, request, response);
    }
    
    /**
     * @see org.kuali.kra.common.permissions.web.struts.action.PermissionsAction#confirmDeletePermissionsUser(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public ActionForward confirmDeletePermissionsUser(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return permissionsActionHelper.confirmDeletePermissionsUser(mapping, form, request, response);
    }      
    
    /**
     * @see org.kuali.kra.common.permissions.web.struts.action.PermissionsAction#editRoles(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public ActionForward editRoles(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                HttpServletResponse response) throws Exception {
        return permissionsActionHelper.editRoles( mapping, form, request, response) ;
    }
    
    /**
     * @see org.kuali.kra.common.permissions.web.struts.action.PermissionsAction#setEditRoles(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public ActionForward setEditRoles(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                HttpServletResponse response) throws Exception {
       return permissionsActionHelper.setEditRoles(mapping, form, request, response);
    }
}