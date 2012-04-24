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
package org.kuali.kra.iacuc.species;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.iacuc.IacucProtocolAction;
import org.kuali.kra.iacuc.IacucProtocolDocument;
import org.kuali.kra.iacuc.IacucProtocolForm;
import org.kuali.kra.infrastructure.Constants;

public class IacucProtocolSpeciesAction extends IacucProtocolAction{

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
        IacucProtocolSpecies iacucProtocolSpecies = protocolForm.getIacucProtocolSpeciesHelper().getNewIacucProtocolSpecies();
        List<IacucProtocolSpecies> iacucProtocolSpeciesList = document.getIacucProtocol().getIacucProtocolSpeciesList();
        
        //if (applyRules(new AddIacucProtocolSpeciesEvent(document, iacucProtocolSpecies, iacucProtocolSpeciesList, false))) {
            //iacucProtocolSpecies.setIacucProtocolSpeciesId(document.getDocumentNextValue(Constants.SPECIES_ID));
            iacucProtocolSpeciesList.add(iacucProtocolSpecies);
            protocolForm.getIacucProtocolSpeciesHelper().setNewIacucProtocolSpecies(new IacucProtocolSpecies());
        //}
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

}
