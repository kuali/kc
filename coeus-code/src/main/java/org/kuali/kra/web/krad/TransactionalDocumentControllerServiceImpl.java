package org.kuali.kra.web.krad;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.kuali.rice.krad.uif.UifConstants;
import org.kuali.rice.krad.uif.UifConstants.WorkflowAction;
import org.kuali.rice.krad.uif.UifParameters;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADUtils;
import org.kuali.rice.krad.web.controller.TransactionalDocumentControllerBase;
import org.kuali.rice.krad.web.form.DocumentFormBase;
import org.kuali.rice.krad.web.form.HistoryManager;
import org.kuali.rice.krad.web.form.UifFormBase;
import org.kuali.rice.krad.web.form.UifFormManager;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

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

@Service("transactionalDocumentControllerService")
public class TransactionalDocumentControllerServiceImpl extends TransactionalDocumentControllerBase implements TransactionalDocumentControllerService {

    /**
     * @see org.kuali.kra.web.krad.TransactionalDocumentControllerService#initForm(org.kuali.rice.krad.web.form.DocumentFormBase, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    @ModelAttribute(value = "KualiForm")
    public UifFormBase initForm(DocumentFormBase requestForm, HttpServletRequest request, HttpServletResponse response) {

        // get Uif form manager from session if exists or setup a new one for the session
        UifFormManager uifFormManager = (UifFormManager) request.getSession().getAttribute(UifParameters.FORM_MANAGER);
        if (uifFormManager == null) {
            uifFormManager = new UifFormManager();
            request.getSession().setAttribute(UifParameters.FORM_MANAGER, uifFormManager);
        }

        // add form manager to GlobalVariables for easy reference by other controller methods
        GlobalVariables.setUifFormManager(uifFormManager);

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
    
    @Override
    protected DocumentFormBase createInitialForm(HttpServletRequest request) {
        throw new UnsupportedOperationException();
    }

    /**
     * @see org.kuali.kra.web.krad.TransactionalDocumentControllerService#performWorkflowAction(org.kuali.rice.krad.web.form.DocumentFormBase, org.kuali.rice.krad.uif.UifConstants.WorkflowAction, boolean)
     */
    @Override
    public void performWorkflowAction(DocumentFormBase form, WorkflowAction action, boolean checkSensitiveData) {
        super.performWorkflowAction(form, action, checkSensitiveData);
    }

    /**
     * @see org.kuali.kra.web.krad.TransactionalDocumentControllerService#getUIFModelAndView(org.kuali.rice.krad.web.form.UifFormBase)
     */
    @Override
    public ModelAndView getUIFModelAndView(UifFormBase form) {
        return super.getUIFModelAndView(form);
    }

    /**
     * @see org.kuali.kra.web.krad.TransactionalDocumentControllerService#getUIFModelAndView(org.kuali.rice.krad.web.form.UifFormBase, java.lang.String)
     */
    @Override
    public ModelAndView getUIFModelAndView(UifFormBase form, String pageId) {
        return super.getUIFModelAndView(form, pageId);
    }

    /**
     * @see org.kuali.kra.web.krad.TransactionalDocumentControllerService#getUIFModelAndViewWithInit(org.kuali.rice.krad.web.form.UifFormBase, java.lang.String)
     */
    @Override
    public ModelAndView getUIFModelAndViewWithInit(UifFormBase form, String viewId) {
        return super.getUIFModelAndViewWithInit(form, viewId);
    }

    /**
     * @see org.kuali.kra.web.krad.TransactionalDocumentControllerService#getUIFModelAndView(org.kuali.rice.krad.web.form.UifFormBase, java.util.Map)
     */
    @Override
    public ModelAndView getUIFModelAndView(UifFormBase form, Map<String, Object> additionalViewAttributes) {
        return super.getUIFModelAndView(form, additionalViewAttributes);
    }

}
