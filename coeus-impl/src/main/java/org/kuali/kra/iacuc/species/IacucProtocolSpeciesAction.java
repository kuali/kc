/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.iacuc.species;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.iacuc.IacucProtocolAction;
import org.kuali.kra.iacuc.IacucProtocolDocument;
import org.kuali.kra.iacuc.IacucProtocolForm;
import org.kuali.kra.iacuc.species.rule.AddProtocolSpeciesEvent;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class IacucProtocolSpeciesAction extends IacucProtocolAction{
    private static final String CONFIRM_DELETE_PROTOCOL_SPECIES_KEY = "confirmDeleteProtocolSpecies";
    private static final String DUPLICATE_SPECIES_ERROR_PATH = "iacucProtocolSpeciesHelper.newIacucProtocolSpecies";
    private static final String DUPLICATE_SPECIES_ERROR_KEY = "error.iacuc.validation.duplicate.group";

    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward = super.execute(mapping, form, request, response);
        
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        
        protocolForm.getIacucProtocolSpeciesHelper().prepareView();
        
        return forward;
    }

    public ActionForward addProtocolSpecies(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        IacucProtocolDocument document = protocolForm.getIacucProtocolDocument();
        List<IacucProtocolSpecies> iacucProtocolSpeciesList = document.getIacucProtocol().getIacucProtocolSpeciesList();
        IacucProtocolSpecies iacucProtocolSpecies = protocolForm.getIacucProtocolSpeciesHelper().getNewIacucProtocolSpecies();

        // look for duplicates
        boolean found = false;
        for (IacucProtocolSpecies oldSpecies: iacucProtocolSpeciesList) {
            if (iacucProtocolSpecies.isSameGroupAs(oldSpecies)) {
                GlobalVariables.getMessageMap().clearErrorPath();
                GlobalVariables.getMessageMap().addToErrorPath(DUPLICATE_SPECIES_ERROR_PATH);
                GlobalVariables.getMessageMap().putError("speciesGroup", DUPLICATE_SPECIES_ERROR_KEY);  
               found = true;
            }
        }
 
        if (!found) {
            if (applyRules(new AddProtocolSpeciesEvent(Constants.EMPTY_STRING, document, iacucProtocolSpecies))) {
                getIacucProtocolSpeciesService().addProtocolSpecies(document.getIacucProtocol(), iacucProtocolSpecies);
                protocolForm.getIacucProtocolSpeciesHelper().setNewIacucProtocolSpecies(new IacucProtocolSpecies());
            }
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    
    public ActionForward deleteProtocolSpecies(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
    throws Exception {
        return confirm(buildParameterizedConfirmationQuestion(mapping, form, request, response, CONFIRM_DELETE_PROTOCOL_SPECIES_KEY,
                KeyConstants.QUESTION_PROTOCOL_SPECIES_DELETE_CONFIRMATION), CONFIRM_DELETE_PROTOCOL_SPECIES_KEY, "");
    }


    @SuppressWarnings("deprecation")
    public ActionForward confirmDeleteProtocolSpecies(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
    throws Exception {
        Object question = request.getParameter(KRADConstants.QUESTION_INST_ATTRIBUTE_NAME);
        if (CONFIRM_DELETE_PROTOCOL_SPECIES_KEY.equals(question)) {
            IacucProtocolForm protocolForm = (IacucProtocolForm) form;
            IacucProtocolDocument document = protocolForm.getIacucProtocolDocument();
            
            document.getIacucProtocol().getIacucProtocolSpeciesList().remove(getLineToDelete(request));
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    protected IacucProtocolSpeciesService getIacucProtocolSpeciesService() {
        return (IacucProtocolSpeciesService) KcServiceLocator.getService("iacucProtocolSpeciesService");
    }
    
}
