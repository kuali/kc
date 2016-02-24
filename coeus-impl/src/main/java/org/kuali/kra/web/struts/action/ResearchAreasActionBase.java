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
package org.kuali.kra.web.struts.action;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.common.framework.auth.task.ApplicationTask;
import org.kuali.coeus.common.framework.auth.task.TaskAuthorizationService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.service.ResearchAreaCurrentReferencerHolderBase;
import org.kuali.kra.service.ResearchAreasServiceBase;
import org.kuali.kra.web.struts.form.ResearchAreasFormBase;
import org.kuali.rice.kns.web.struts.action.KualiAction;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class ResearchAreasActionBase extends KualiAction {

    private static final Log LOG = LogFactory.getLog(ResearchAreasActionBase.class);

    /**
     * 
     * This method is to refresh the research area page.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward reload(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        super.refresh(mapping, form, request, response);

        return mapping.findForward("basic");
    }

    /**
     * 
     * This method is to cancel the maintenance action and go back to portal page
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward cancel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward(KRADConstants.MAPPING_PORTAL);
    }

    /**
     * 
     * This method is to return to page.  JS will save changes before this method is called.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward close(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward(KRADConstants.MAPPING_PORTAL);
    }

    private void setResearchAreas(ActionForm form) {
        ResearchAreasFormBase researchAreaForm = (ResearchAreasFormBase) form;
        ResearchAreasServiceBase researchAreaService = getResearchAreasService();
        if (StringUtils.isNotBlank(researchAreaForm.getAddRA()) && researchAreaForm.getAddRA().equals("Y")) {
            if (researchAreaService.isResearchAreaExist(researchAreaForm.getResearchAreaCode(), researchAreaForm.getDeletedRas())) {
                researchAreaForm.setResearchAreas("<h3>true</h3>");
            }
            else {
                researchAreaForm.setResearchAreas("<h3>false</h3>");
            }
        }
        else if (StringUtils.isNotBlank(researchAreaForm.getAddRA()) && researchAreaForm.getAddRA().equals("S")) {
            try {
                researchAreaService.saveResearchAreas(researchAreaForm.getSqlScripts());
                String error = (String) GlobalVariables.getUserSession().retrieveObject("raError");
                if (StringUtils.isNotBlank(error)) {
                    researchAreaForm.setResearchAreas("<h3>" + error + "</h3>");
                    GlobalVariables.getUserSession().addObject("raError", (Object) null);
                }
                else {
                    researchAreaForm.setResearchAreas("<h3>Success</h3>");
                }
            }
            catch (Exception e) {
                LOG.error(e.getMessage(), e);
            }
        }
        else if (StringUtils.isNotBlank(researchAreaForm.getAddRA()) && researchAreaForm.getAddRA().equals("A")) {
            researchAreaForm.setResearchAreas(researchAreaService.getSubResearchAreasForTreeView(
                    researchAreaForm.getResearchAreaCode(), true));
        }
        else if (StringUtils.isNotBlank(researchAreaForm.getAddRA()) && researchAreaForm.getAddRA().equals("I")) {
            try {
                // check if RA is being referenced by any current protocol or committee or cmt membership
                //ComplianceResearchAreasService researchAreaService = getResearchAreasService();
                String researchAreaCode = researchAreaForm.getResearchAreaCode();
                ResearchAreaCurrentReferencerHolderBase referenceHolder = researchAreaService.getAnyCurrentReferencerForResearchAreaOrDescendant(researchAreaCode);
                if(referenceHolder != ResearchAreaCurrentReferencerHolderBase.NO_REFERENCER) {
                    // let user know about that the research area could not be deactivated because it was being referenced
                    researchAreaForm.setResearchAreas("<h3>" + referenceHolder.getMessage() + "</h3>");
                    GlobalVariables.getUserSession().addObject("raError", (Object) null);
                }                                
                else {
                    // its not being referenced, go ahead and deactivate it
                    researchAreaService.deactivateResearchAreaAndDescendants(researchAreaCode);
                    String error = (String) GlobalVariables.getUserSession().retrieveObject("raError");
                    if (StringUtils.isNotBlank(error)) {
                        researchAreaForm.setResearchAreas("<h3>" + error + "</h3>");
                        GlobalVariables.getUserSession().addObject("raError", (Object) null);
                    }
                    else {
                        researchAreaForm.setResearchAreas("<h3>Success</h3>");
                    }
                }
            }
            catch (Exception e) {
                LOG.error(e.getMessage(), e);
            }
        }
        else if (StringUtils.isNotBlank(researchAreaForm.getAddRA()) && researchAreaForm.getAddRA().equals("D")) {
            try {
                // check if RA is being referenced
                if (researchAreaService.checkResearchAreaAndDescendantsNotReferenced(researchAreaForm.getResearchAreaCode()) ) {
                    // its not being referenced, go ahead and delete it
                    researchAreaService.deleteResearchAreaAndDescendants(researchAreaForm.getResearchAreaCode());
                    String error = (String) GlobalVariables.getUserSession().retrieveObject("raError");
                    if (StringUtils.isNotBlank(error)) {
                        researchAreaForm.setResearchAreas("<h3>" + error + "</h3>");
                        GlobalVariables.getUserSession().addObject("raError", (Object) null);
                    }
                    else {
                        researchAreaForm.setResearchAreas("<h3>Success</h3>");
                    }
                }
                else {
                    // let user know about that the research area could not be deleted because it was being referenced
                    // TODO add code here for appropriate error message
                    researchAreaForm.setResearchAreas("<h3>" + "Research area or descendants are (were) being referenced in a current (past) version of committee, committee member or protocol" + "</h3>");
                    GlobalVariables.getUserSession().addObject("raError", (Object) null);
                }
            }
            catch (Exception e) {
                LOG.error(e.getMessage(), e);
            }
        }
        else {
            researchAreaForm.setResearchAreas(researchAreaService.getSubResearchAreasForTreeView(
                    researchAreaForm.getResearchAreaCode(), false));
        }
    }

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        ResearchAreasFormBase researchAreaForm = (ResearchAreasFormBase) form;
        ActionForward forward = super.execute(mapping, form, request, response);
        setResearchAreas(form);
        canMaintainResearchArea(researchAreaForm);
        return forward;
    }
    
    private void canMaintainResearchArea(ResearchAreasFormBase researchAreaForm) {
        ApplicationTask task = new ApplicationTask(getResearchAreasTask());
        researchAreaForm.setAuthorizedToMaintainResearchAreas(getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task));     
    }

    private String getUserIdentifier() {
        return GlobalVariables.getUserSession().getPrincipalId();
    }
    
    protected TaskAuthorizationService getTaskAuthorizationService() {
        return KcServiceLocator.getService(TaskAuthorizationService.class);
    }

    protected abstract ResearchAreasServiceBase getResearchAreasService();
    protected abstract String getResearchAreasTask();


}
