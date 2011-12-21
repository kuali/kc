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
package org.kuali.kra.personmasschange.web.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.irb.ProtocolForm;
import org.kuali.kra.personmasschange.document.PersonMassChangeDocument;
import org.kuali.kra.personmasschange.web.struts.form.PersonMassChangeForm;
import org.kuali.kra.personmasschange.web.struts.form.PersonMassChangeHelper;
import org.kuali.kra.web.struts.action.KraTransactionalDocumentActionBase;

/**
 * Defines the overall action class for Person Mass Change.
 */
public class PersonMassChangeAction extends KraTransactionalDocumentActionBase {

    @Override
    public ActionForward docHandler(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward = super.docHandler(mapping, form, request, response);
        
        PersonMassChangeForm personMassChangeForm = (PersonMassChangeForm) form;
        PersonMassChangeDocument personMassChangeDocument = (PersonMassChangeDocument) personMassChangeForm.getDocument();
        personMassChangeDocument.initialize();
        
        return forward;
    }
    
    public ActionForward home(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        return mapping.findForward("home");
    }

    public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        PersonMassChangeForm personMassChangeForm = (PersonMassChangeForm) form;
        PersonMassChangeHelper personMassChangeHelper = personMassChangeForm.getPersonMassChangeHelper();
        personMassChangeHelper.prepareView();
        
        return mapping.findForward("view");
    }

}