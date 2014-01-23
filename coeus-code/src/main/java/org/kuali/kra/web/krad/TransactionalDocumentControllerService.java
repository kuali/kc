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
package org.kuali.kra.web.krad;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kuali.rice.krad.uif.UifConstants.WorkflowAction;
import org.kuali.rice.krad.uif.field.AttributeQueryResult;
import org.kuali.rice.krad.web.form.DocumentFormBase;
import org.kuali.rice.krad.web.form.UifFormBase;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;


public interface TransactionalDocumentControllerService {

    /**
     * Create/obtain the model(form) object before it is passed to the Binder/BeanWrapper. This method
     * is not intended to be overridden by client applications as it handles framework setup and session
     * maintenance. Clients should override createInitialForm() instead when they need custom form initialization.
     *
     * @param request the http request that was made
     * @param response the http response object
     */
    @ModelAttribute(value = "KualiForm")
    public UifFormBase initForm(DocumentFormBase requestForm, HttpServletRequest request, HttpServletResponse response);

    public void performWorkflowAction(DocumentFormBase form, WorkflowAction action, boolean checkSensitiveData);

    public ModelAndView getUIFModelAndView(UifFormBase form);

    public ModelAndView getUIFModelAndView(UifFormBase form, String pageId);

    public ModelAndView getUIFModelAndViewWithInit(UifFormBase form, String viewId);

    public ModelAndView getUIFModelAndView(UifFormBase form, Map<String, Object> additionalViewAttributes);

    public ModelAndView addBlankLine(UifFormBase uifForm, HttpServletRequest request, HttpServletResponse response);

    public ModelAndView docHandler(DocumentFormBase form, BindingResult result, HttpServletRequest request,
            HttpServletResponse response) throws Exception;

    public ModelAndView addLine(UifFormBase form, BindingResult result, HttpServletRequest request,
            HttpServletResponse response);

    public ModelAndView back(UifFormBase form, BindingResult result, HttpServletRequest request, HttpServletResponse response);

    public ModelAndView checkForm(UifFormBase form, BindingResult result, HttpServletRequest request,
            HttpServletResponse response);

    public void checkViewAuthorization(UifFormBase form, String methodToCall);

    public ModelAndView complete(DocumentFormBase form, BindingResult result, HttpServletRequest request,
            HttpServletResponse response) throws Exception;

    public ModelAndView defaultMapping(UifFormBase form, BindingResult result, HttpServletRequest request,
            HttpServletResponse response);

    public ModelAndView deleteLine(UifFormBase form, BindingResult result, HttpServletRequest request,
            HttpServletResponse response);

    public ModelAndView start(UifFormBase form, HttpServletRequest request, HttpServletResponse response);

    public ModelAndView downloadAttachment(UifFormBase form, BindingResult result, HttpServletRequest request,
            HttpServletResponse response) throws FileNotFoundException, IOException, ServletRequestBindingException;

    public ModelAndView navigate(UifFormBase form, BindingResult result, HttpServletRequest request,
            HttpServletResponse response);

    public AttributeQueryResult performFieldQuery(UifFormBase form, BindingResult result, HttpServletRequest request,
            HttpServletResponse response);

    public ModelAndView performLookup(UifFormBase form, BindingResult result, HttpServletRequest request,
            HttpServletResponse response);

    public ModelAndView refresh(UifFormBase form, BindingResult result, HttpServletRequest request,
            HttpServletResponse response) throws Exception;

    public AttributeQueryResult performFieldSuggest(UifFormBase form, BindingResult result, HttpServletRequest request,
            HttpServletResponse response);

    public ModelAndView reload(DocumentFormBase form, BindingResult result, HttpServletRequest request, HttpServletResponse response) throws Exception;

    public ModelAndView returnFromLightbox(UifFormBase form, BindingResult result, HttpServletRequest request,
            HttpServletResponse response);

    public ModelAndView returnToHistory(UifFormBase form, boolean homeFlag);

    public ModelAndView returnToHub(UifFormBase form);

    public ModelAndView returnToPrevious(UifFormBase form);

    public ModelAndView saveLine(UifFormBase form, BindingResult result, HttpServletRequest request,
            HttpServletResponse response);

    public ModelAndView sessionTimeout(UifFormBase form, BindingResult result, HttpServletRequest request,
            HttpServletResponse response);

    public String tableCsvRetrieval(UifFormBase form, BindingResult result, HttpServletRequest request,
            HttpServletResponse response);

    public String tableXlsRetrieval(UifFormBase form, BindingResult result, HttpServletRequest request,
            HttpServletResponse response);

    public String tableXmlRetrieval(UifFormBase form, BindingResult result, HttpServletRequest request,
            HttpServletResponse response);

    public ModelAndView tableJsonRetrieval(UifFormBase form, BindingResult result, HttpServletRequest request,
            HttpServletResponse response);

}