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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kuali.coeus.sys.framework.controller.KcCommonControllerService;
import org.kuali.rice.krad.web.controller.MethodAccessible;
import org.kuali.rice.krad.web.service.ControllerService;
import org.kuali.rice.krad.web.service.ModelAndViewService;
import org.kuali.rice.krad.web.service.NavigationControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LandingPageController {

    @Autowired
    @Qualifier("modelAndViewService")
    private ModelAndViewService modelAndViewService;

    @Autowired
    @Qualifier("controllerService")
    private ControllerService controllerService;

    @Autowired
    @Qualifier("kcCommonControllerService")
    private KcCommonControllerService kcCommonControllerService;
    
    @MethodAccessible
    @RequestMapping(value = "/landingPage")
    public ModelAndView defaultRequest(@ModelAttribute("KualiForm") LandingPageForm form, BindingResult result,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        return getModelAndViewService().getModelAndView(form);
    }

    @MethodAccessible
    @ModelAttribute(value = "KualiForm")
    public LandingPageForm initForm(HttpServletRequest request, HttpServletResponse response) {
        return (LandingPageForm) getKcCommonControllerService().initForm(new LandingPageForm(), request, response);
    }

    @MethodAccessible
    @RequestMapping(value = "/landingPage", params = "methodToCall=defaultMapping")
    public ModelAndView defaultMapping(@ModelAttribute(value = "KualiForm") LandingPageForm form, BindingResult result, HttpServletRequest request,
            HttpServletResponse response) {
        return getControllerService().start(form);
    }

    @MethodAccessible
    @RequestMapping(value = "/landingPage", params = "methodToCall=start")
    public ModelAndView start(@ModelAttribute(value = "KualiForm") LandingPageForm form, HttpServletRequest request, HttpServletResponse response) {
        return getControllerService().start(form);
    }

    @MethodAccessible
    @RequestMapping(value = "/landingPage", params = "methodToCall=sessionTimeout")
    public ModelAndView sessionTimeout(@ModelAttribute(value = "KualiForm") LandingPageForm form, BindingResult result, HttpServletRequest request,
            HttpServletResponse response) {
        return getControllerService().sessionTimeout(form);
    }

    @MethodAccessible
    @RequestMapping(value = "/landingPage", params = "methodToCall=checkForm")
    public ModelAndView checkForm(@ModelAttribute(value = "KualiForm") LandingPageForm form, BindingResult result, HttpServletRequest request, HttpServletResponse response) {
        return getModelAndViewService().checkForm(form);
    }

    public ModelAndViewService getModelAndViewService() {
        return modelAndViewService;
    }

    public void setModelAndViewService(ModelAndViewService modelAndViewService) {
        this.modelAndViewService = modelAndViewService;
    }

    public KcCommonControllerService getKcCommonControllerService() {
        return kcCommonControllerService;
    }

    public void setKcCommonControllerService(KcCommonControllerService kcCommonControllerService) {
        this.kcCommonControllerService = kcCommonControllerService;
    }

    public ControllerService getControllerService() {
        return controllerService;
    }

    public void setControllerService(ControllerService controllerService) {
        this.controllerService = controllerService;
    }
}
