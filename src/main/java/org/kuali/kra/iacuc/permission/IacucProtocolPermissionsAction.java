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
package org.kuali.kra.iacuc.permission;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.common.permissions.bo.PermissionsUser;
import org.kuali.kra.common.permissions.web.struts.action.PermissionsAction;
import org.kuali.kra.common.permissions.web.struts.form.PermissionsForm;
import org.kuali.kra.iacuc.IacucProtocolAction;
import org.kuali.kra.iacuc.IacucProtocolDocumentRule;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.protocol.ProtocolForm;
import org.kuali.kra.protocol.permission.ProtocolPermissionsActionHelper;
import org.kuali.rice.kns.web.struts.form.KualiDocumentFormBase;
import org.kuali.rice.krad.document.Document;


public class IacucProtocolPermissionsAction extends IacucProtocolAction implements PermissionsAction {
     
    protected ProtocolPermissionsActionHelper permissionsActionHelper = new ProtocolPermissionsActionHelper(this);
    
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
     * @see org.kuali.kra.protocol.ProtocolAction#saveOnClose(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected ActionForward saveOnClose(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward = super.saveOnClose(mapping, form, request, response);

        permissionsActionHelper.save((ProtocolForm) form);
        
        return forward;
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
        PermissionsForm permissionsForm = (PermissionsForm) form;
        Document doc = permissionsForm.getDocument();
        PermissionsUser newUser = permissionsForm.getPermissionsHelper().getNewUser();            
        boolean rulePassed = (new IacucProtocolDocumentRule()).processAddPermissionsUserBusinessRules(doc, 
                permissionsForm.getPermissionsHelper().getUsers(), newUser);
        if (rulePassed) {
            return permissionsActionHelper.addUser(mapping, form, request, response);
        } else {
            return mapping.findForward(Constants.MAPPING_BASIC);
        }
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
