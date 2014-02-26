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
package org.kuali.coeus.sys.impl.controller;

import org.kuali.coeus.sys.framework.controller.KcCommonControllerService;
import org.kuali.coeus.sys.framework.controller.UifControllerService;
import org.kuali.rice.krad.web.controller.UifControllerBase;
import org.kuali.rice.krad.web.form.UifFormBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Properties;
/** this service must @Override methods and call super in order to elevate a method to public to satisfy the interface. */
@Service(value="uifControllerService")
public class UifControllerServiceImpl extends UifControllerBase implements UifControllerService {

    @Autowired
    @Qualifier("kcCommonControllerService")
    private KcCommonControllerService kcCommonControllerService;

    public UifFormBase initForm(UifFormBase requestForm, HttpServletRequest request, HttpServletResponse response) {
        return kcCommonControllerService.initForm(requestForm,request,response);
    }

    @Override
    public UifFormBase createInitialForm(HttpServletRequest request) {
        return kcCommonControllerService.createInitialForm(request);
    }

    @Override
    public UifFormBase initForm(HttpServletRequest request, HttpServletResponse response) {
        return kcCommonControllerService.initForm(request, response);
    }

    @Override
    public boolean hasDialogBeenDisplayed(String dialogId, UifFormBase form) {
        return super.hasDialogBeenDisplayed(dialogId, form);
    }

    @Override
    public boolean hasDialogBeenAnswered(String dialogId, UifFormBase form) {
        return super.hasDialogBeenAnswered(dialogId, form);
    }

    @Override
    public void resetDialogStatus(String dialogId, UifFormBase form) {
        super.resetDialogStatus(dialogId, form);
    }

    @Override
    public boolean getBooleanDialogResponse(String dialogId, UifFormBase form, HttpServletRequest request,
                                     HttpServletResponse response) {
        return super.getBooleanDialogResponse(dialogId, form, request, response);
    }

    @Override
    public String getStringDialogResponse(String dialogId, UifFormBase form, HttpServletRequest request,
                                   HttpServletResponse response) {
        return super.getStringDialogResponse(dialogId, form, request, response);
    }

    @Override
    public ModelAndView showDialog(String dialogId, UifFormBase form, HttpServletRequest request,
                            HttpServletResponse response) {
        return super.showDialog(dialogId, form, request, response);
    }

    @Override
    public ModelAndView performRedirect(UifFormBase form, String baseUrl, Properties urlParameters) {
        return super.performRedirect(form, baseUrl, urlParameters);
    }

    @Override
    public ModelAndView performRedirect(UifFormBase form, String redirectUrl) {
        return super.performRedirect(form, redirectUrl);
    }

    @Override
    public ModelAndView getMessageView(UifFormBase form, String headerText, String messageText) {
        return super.getMessageView(form, headerText, messageText);
    }

    @Override
    public ModelAndView getUIFModelAndView(UifFormBase form) {
        return super.getUIFModelAndView(form);
    }

    @Override
    public ModelAndView getUIFModelAndView(UifFormBase form, String pageId) {
        return super.getUIFModelAndView(form, pageId);
    }

    @Override
    public ModelAndView getUIFModelAndViewWithInit(UifFormBase form, String viewId) {
        return super.getUIFModelAndViewWithInit(form, viewId);
    }

    @Override
    public ModelAndView getUIFModelAndView(UifFormBase form, Map<String, Object> additionalViewAttributes) {
        return super.getUIFModelAndView(form, additionalViewAttributes);
    }

    public void setKcCommonControllerService(KcCommonControllerService kcCommonControllerService) {
        this.kcCommonControllerService = kcCommonControllerService;
    }
}
