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
package org.kuali.kra.web.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.service.ComplianceResearchAreaCurrentReferencerHolder;
import org.kuali.kra.service.ComplianceResearchAreasService;
import org.kuali.kra.service.ResearchAreaCurrentReferencerHolder;
import org.kuali.kra.web.struts.form.ComplianceResearchAreasForm;
import org.kuali.rice.kns.web.struts.action.KualiAction;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;

public abstract class ComplianceResearchAreasAction extends KualiAction {


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
        ComplianceResearchAreasForm researchAreaForm = getResearchAreasForm(form);
        ComplianceResearchAreasService researchAreaService = getResearchAreasService();
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
                e.printStackTrace();
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
                ComplianceResearchAreaCurrentReferencerHolder referenceHolder = researchAreaService.getAnyCurrentReferencerForResearchAreaOrDescendant(researchAreaCode);
                if(referenceHolder != ComplianceResearchAreaCurrentReferencerHolder.NO_REFERENCER) {
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
                e.printStackTrace();
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
                e.printStackTrace();
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
        // TODO Auto-generated method stub
        ActionForward forward = super.execute(mapping, form, request, response);
        setResearchAreas(form);
        return forward;
    }
    
    protected abstract ComplianceResearchAreasForm getResearchAreasForm(ActionForm form);
    protected abstract ComplianceResearchAreasService getResearchAreasService();

}
