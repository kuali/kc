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
package org.kuali.coeus.sys.impl.controller;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.controller.KcCommonControllerService;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.rice.krad.uif.UifConstants;
import org.kuali.rice.krad.uif.UifParameters;
import org.kuali.rice.krad.uif.util.ScriptUtils;
import org.kuali.rice.krad.util.KRADUtils;
import org.kuali.rice.krad.web.form.HistoryManager;
import org.kuali.rice.krad.web.form.UifFormBase;
import org.kuali.rice.krad.web.form.UifFormManager;
import org.kuali.rice.krad.web.service.ModelAndViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Component("kcCommonControllerService")
public class KcCommonControllerServiceImpl implements KcCommonControllerService {

    @Autowired
    @Qualifier("globalVariableService")
    private GlobalVariableService globalVariableService;

    @Autowired
    @Qualifier("modelAndViewService")
    private ModelAndViewService modelAndViewService;

    private static final String CLOSE_DIALOG_JS_FN = "dismissDialog";

    @Override
    public UifFormBase initForm(UifFormBase requestForm, HttpServletRequest request, HttpServletResponse response) {

        // get Uif form manager from session if exists or setup a new one for the session
        UifFormManager uifFormManager = (UifFormManager) request.getSession().getAttribute(UifParameters.FORM_MANAGER);
        if (uifFormManager == null) {
            uifFormManager = new UifFormManager();
            request.getSession().setAttribute(UifParameters.FORM_MANAGER, uifFormManager);
        }

        // add form manager to GlobalVariables for easy reference by other controller methods
        globalVariableService.setUifFormManager(uifFormManager);

        String formKeyParam = request.getParameter(UifParameters.FORM_KEY);
        if (StringUtils.isNotBlank(formKeyParam)) {
            // retrieves the session form and updates the request from with the session transient attributes
            uifFormManager.updateFormWithSession(requestForm, formKeyParam);
        }

        //set the originally requested form key
        String requestedFormKey = request.getParameter(UifParameters.REQUESTED_FORM_KEY);
        if (StringUtils.isNotBlank(requestedFormKey)) {
            requestForm.setRequestedFormKey(requestedFormKey);
        } else {
            requestForm.setRequestedFormKey(formKeyParam);
        }

        //get the initial referer
        String referer = request.getHeader(UifConstants.REFERER);

        //if none, set the no return flag string
        if (StringUtils.isBlank(referer) && StringUtils.isBlank(requestForm.getReturnLocation())) {
            requestForm.setReturnLocation(UifConstants.NO_RETURN);
        } else if (StringUtils.isBlank(requestForm.getReturnLocation())) {
            requestForm.setReturnLocation(referer);
        }

        //get initial request params
        if (requestForm.getInitialRequestParameters() == null) {
            Map<String, String> requestParams = new HashMap<String, String>();
            Enumeration<String> names = request.getParameterNames();

            while (names != null && names.hasMoreElements()) {
                String name = KRADUtils.stripXSSPatterns(names.nextElement());
                String value = KRADUtils.stripXSSPatterns(request.getParameter(name));

                requestParams.put(name, value);
            }

            requestParams.remove(UifConstants.UrlParams.LOGIN_USER);
            //requestForm.setInitialRequestParameters(requestParams);
        }

        //set the original request url for this view/form
        String requestUrl = KRADUtils.stripXSSPatterns(KRADUtils.getFullURL(request));
        requestForm.setRequestUrl(requestUrl);

        Object historyManager = request.getSession().getAttribute(UifConstants.HistoryFlow.HISTORY_MANAGER);
        String flowKey = request.getParameter(UifConstants.HistoryFlow.FLOW);

        //add history manager and current flowKey to the form
        if (requestForm != null && historyManager != null && historyManager instanceof HistoryManager) {
            requestForm.setHistoryManager((HistoryManager) historyManager);
            requestForm.setFlowKey(flowKey);
        }

        // sets the request form in the request for later retrieval
        request.setAttribute(UifConstants.REQUEST_FORM, requestForm);

        return requestForm;
    }

    public ModelAndView closeDialog(String dialogId, UifFormBase form) {
        ModelAndView modelAndView = getModelAndViewService().getModelAndView(form);
        getModelAndViewService().prepareView(form.getRequest(), modelAndView);
        org.kuali.rice.krad.uif.component.Component updateComponent;
        if (form.isAjaxRequest()) {
            updateComponent = form.getUpdateComponent();
        } else {
            updateComponent = form.getView();
        }
        String onReadyScript = ScriptUtils.appendScript(updateComponent.getOnDocumentReadyScript(), getCloseDialogScript(dialogId));
        updateComponent.setOnDocumentReadyScript(onReadyScript);
        form.getRequest().setAttribute(UifParameters.Attributes.VIEW_LIFECYCLE_COMPLETE, "true");
        return modelAndView;
    }
    
    protected String getCloseDialogScript(String dialogId) {
        StringBuilder closeDialogScript = new StringBuilder();
        closeDialogScript.append(CLOSE_DIALOG_JS_FN);
        closeDialogScript.append("('");
        closeDialogScript.append(dialogId);
        closeDialogScript.append("');");
        return closeDialogScript.toString();
    }
    
    public GlobalVariableService getGlobalVariableService() {
        return globalVariableService;
    }

    public void setGlobalVariableService(GlobalVariableService globalVariableService) {
        this.globalVariableService = globalVariableService;
    }

	public ModelAndViewService getModelAndViewService() {
		return modelAndViewService;
	}

	public void setModelAndViewService(ModelAndViewService modelAndViewService) {
		this.modelAndViewService = modelAndViewService;
	}
}
