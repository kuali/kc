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
