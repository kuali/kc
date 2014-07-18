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
package org.kuali.coeus.sys.framework.controller;

import org.kuali.rice.krad.exception.AuthorizationException;
import org.kuali.rice.krad.uif.field.AttributeQueryResult;
import org.kuali.rice.krad.web.form.UifFormBase;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Properties;

/** public and protected methods from Rice's UifControllerBase */
public interface UifControllerService extends KcCommonControllerService {

    //public methods

    UifFormBase initForm(HttpServletRequest request, HttpServletResponse response);

    ModelAndView defaultMapping(UifFormBase form, BindingResult result,
                                       HttpServletRequest request, HttpServletResponse response);

    ModelAndView start(UifFormBase form, HttpServletRequest request,
                              HttpServletResponse response);

    void checkViewAuthorization(UifFormBase form, String methodToCall) throws AuthorizationException;

    ModelAndView sessionTimeout(UifFormBase form, BindingResult result,
                                       HttpServletRequest request, HttpServletResponse response);

    ModelAndView addLine(final UifFormBase uifForm, BindingResult result,
                                HttpServletRequest request, HttpServletResponse response);

    ModelAndView addBlankLine(final UifFormBase uifForm, HttpServletRequest request,
                                     HttpServletResponse response);

    ModelAndView saveLine(final UifFormBase uifForm, BindingResult result,
                                 HttpServletRequest request, HttpServletResponse response);

    ModelAndView deleteLine(final UifFormBase uifForm, BindingResult result,
                                   HttpServletRequest request, HttpServletResponse response);

    ModelAndView cancel(UifFormBase form, BindingResult result,
                               HttpServletRequest request, HttpServletResponse response);

    ModelAndView back(UifFormBase form, BindingResult result,
                             HttpServletRequest request, HttpServletResponse response);

    ModelAndView returnToPrevious(UifFormBase form);

    ModelAndView returnToHub(UifFormBase form);

    ModelAndView returnToHistory(UifFormBase form, boolean homeFlag);

    ModelAndView navigate(UifFormBase form, BindingResult result,
                                 HttpServletRequest request, HttpServletResponse response);

    ModelAndView refresh(final UifFormBase form, BindingResult result,
                                final HttpServletRequest request, HttpServletResponse response) throws Exception;

    ModelAndView performLookup(UifFormBase form, BindingResult result,
                                      HttpServletRequest request, HttpServletResponse response);

    ModelAndView checkForm(UifFormBase form, BindingResult result,
                                  HttpServletRequest request, HttpServletResponse response);

    AttributeQueryResult performFieldSuggest(UifFormBase form, BindingResult result,
                                             HttpServletRequest request, HttpServletResponse response);

    AttributeQueryResult performFieldQuery(UifFormBase form, BindingResult result,
                                           HttpServletRequest request, HttpServletResponse response);

    ModelAndView retrieveCollectionPage(UifFormBase form, BindingResult result,
                                               HttpServletRequest request, HttpServletResponse response) throws Exception;

    ModelAndView tableJsonRetrieval(UifFormBase form, BindingResult result,
                                           HttpServletRequest request, HttpServletResponse response);
    
    //protected methods

    UifFormBase createInitialForm(HttpServletRequest request);

    ModelAndView performRedirect(UifFormBase form, String baseUrl, Properties urlParameters);

    ModelAndView performRedirect(UifFormBase form, String redirectUrl);

    ModelAndView getMessageView(UifFormBase form, String headerText, String messageText);

    ModelAndView getUIFModelAndView(UifFormBase form);

    ModelAndView getUIFModelAndView(UifFormBase form, String pageId);

    ModelAndView getUIFModelAndViewWithInit(UifFormBase form, String viewId);

    ModelAndView getUIFModelAndView(UifFormBase form, Map<String, Object> additionalViewAttributes);

    public ModelAndView addFileUploadLine(UifFormBase uifForm, BindingResult result,
                                          MultipartHttpServletRequest request, HttpServletResponse response) throws Exception;

    public ModelAndView deleteFileUploadLine(UifFormBase uifForm,
                                             BindingResult result, HttpServletRequest request, HttpServletResponse response) throws Exception;

    public void getFileFromLine(UifFormBase uifForm, BindingResult result,
                                HttpServletRequest request, HttpServletResponse response) throws Exception;
    
    ModelAndView showDialog(String dialogId, boolean confirmation, UifFormBase form);
}
