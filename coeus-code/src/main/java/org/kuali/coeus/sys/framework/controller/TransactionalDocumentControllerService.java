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

import org.kuali.rice.krad.web.form.TransactionalDocumentFormBase;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** public and protected methods from Rice's TransactionalDocumentControllerBase */
public interface TransactionalDocumentControllerService extends DocumentControllerService, KcCommonControllerService {

    //from TransactionalDocumentControllerBase
    public ModelAndView copy(TransactionalDocumentFormBase form, BindingResult result,
                             HttpServletRequest request, HttpServletResponse response) throws Exception;

}