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
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.personmasschange.web.struts.form.PersonMassChangeForm;
import org.kuali.kra.personmasschange.web.struts.form.PersonMassChangeHomeHelper;
import org.kuali.kra.personmasschange.web.struts.form.PersonMassChangeViewHelper;
import org.kuali.kra.web.struts.action.KraTransactionalDocumentActionBase;
import org.kuali.rice.kew.util.KEWConstants;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.rule.event.KualiDocumentEvent;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.kns.service.KualiRuleService;
import org.kuali.rice.kns.util.KNSConstants;

/**
 * Defines the overall action class for Person Mass Change.
 */
public class PersonMassChangeAction extends KraTransactionalDocumentActionBase {

    @Override
    public ActionForward docHandler(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward = null;
        
        PersonMassChangeForm personMassChangeForm = (PersonMassChangeForm) form;
        String command = personMassChangeForm.getCommand();
        
        if (Constants.MAPPING_PMC_HOME_PAGE.equals(command) || Constants.MAPPING_PMC_VIEW_PAGE.equals(command)) {
            String docIdRequestParameter = request.getParameter(KNSConstants.PARAMETER_DOC_ID);
            Document retrievedDocument = KNSServiceLocator.getDocumentService().getByDocumentHeaderId(docIdRequestParameter);
            personMassChangeForm.setDocument(retrievedDocument);
            request.setAttribute(KNSConstants.PARAMETER_DOC_ID, docIdRequestParameter);
            loadDocument(personMassChangeForm);
        } else {
            forward = super.docHandler(mapping, form, request, response);
        }

        if (KEWConstants.INITIATE_COMMAND.equals(command)) {
            personMassChangeForm.getDocument().initialize();
        } else {
            personMassChangeForm.initialize();
        }
        
        if (Constants.MAPPING_PMC_HOME_PAGE.equals(command)) {
            forward = home(mapping, personMassChangeForm, request, response);
        }
        if (Constants.MAPPING_PMC_VIEW_PAGE.equals(command)) {
            forward = view(mapping, personMassChangeForm, request, response);
        }
        
        return forward;
    }
    
    public ActionForward home(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        PersonMassChangeForm personMassChangeForm = (PersonMassChangeForm) form;
        PersonMassChangeHomeHelper personMassChangeHomeHelper = personMassChangeForm.getPersonMassChangeHomeHelper();
        personMassChangeHomeHelper.prepareView();
        
        return mapping.findForward(Constants.MAPPING_PMC_HOME_PAGE);
    }

    public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        PersonMassChangeForm personMassChangeForm = (PersonMassChangeForm) form;
        PersonMassChangeViewHelper personMassChangeViewHelper = personMassChangeForm.getPersonMassChangeViewHelper();
        personMassChangeViewHelper.prepareView();
        
        return mapping.findForward(Constants.MAPPING_PMC_VIEW_PAGE);
    }
    
    public ActionForward routeToHoldingPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, String command) {
        PersonMassChangeForm personMassChangeForm = (PersonMassChangeForm) form;
        Long routeHeaderId = Long.parseLong(personMassChangeForm.getDocument().getDocumentNumber());
        String returnLocation = buildActionUrl(routeHeaderId, command, "PersonMassChangeDocument");
        
        ActionForward basicForward = mapping.findForward(Constants.MAPPING_BASIC);
        ActionForward holdingPageForward = mapping.findForward(Constants.MAPPING_HOLDING_PAGE);
        return routeToHoldingPage(basicForward, basicForward, holdingPageForward, returnLocation);
    }
    
    public final boolean applyRules(KualiDocumentEvent event) {
        return getKualiRuleService().applyRules(event);
    }
    
    @Override
    protected KualiRuleService getKualiRuleService() {
        return KraServiceLocator.getService(KualiRuleService.class);
    }

}