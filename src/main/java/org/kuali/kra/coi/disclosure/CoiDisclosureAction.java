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
package org.kuali.kra.coi.disclosure;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.coi.CoiAction;
import org.kuali.kra.coi.CoiDisclosure;
import org.kuali.kra.coi.CoiDisclosureDocument;
import org.kuali.kra.coi.CoiDisclosureForm;
import org.kuali.kra.coi.DisclosureReporter;
import org.kuali.kra.coi.DisclosureReporterUnit;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.rule.event.KraDocumentEventBaseExtension;
import org.springframework.util.CollectionUtils;

public class CoiDisclosureAction extends CoiAction {

   
    public ActionForward addDisclosurePersonUnit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        CoiDisclosureForm coiDisclosureForm = (CoiDisclosureForm) form;
        DisclosureHelper disclosureHelper = coiDisclosureForm.getDisclosureHelper();
        if (checkRule(new AddDisclosureReporterUnitEvent("disclosureHelper.newDisclosurePersonUnit", disclosureHelper.getNewDisclosurePersonUnit(),
                ((CoiDisclosureDocument)coiDisclosureForm.getDocument()).getCoiDisclosure().getDisclosureReporter().getDisclosurePersonUnits()))) {
            getCoiDisclosureService().addDisclosureReporterUnit(
                   ((CoiDisclosureDocument)coiDisclosureForm.getDocument()).getCoiDisclosure().getDisclosureReporter(),
                   disclosureHelper.getNewDisclosurePersonUnit());
            disclosureHelper.setNewDisclosurePersonUnit(new DisclosurePersonUnit());
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    public ActionForward deleteDisclosurePersonUnit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        CoiDisclosureForm coiDisclosureForm = (CoiDisclosureForm) form;
        DisclosureHelper disclosureHelper = coiDisclosureForm.getDisclosureHelper();
        getCoiDisclosureService().deleteDisclosureReporterUnit(((CoiDisclosureDocument)coiDisclosureForm.getDocument()).getCoiDisclosure().getDisclosureReporter(), disclosureHelper.getDeletedUnits(), getSelectedLine(request));
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    private CoiDisclosureService getCoiDisclosureService() {
        return KraServiceLocator.getService(CoiDisclosureService.class);
    }

    private boolean checkRule(KraDocumentEventBaseExtension event) {
        return event.getRule().processRules(event);
    }

    @Override
    public final ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        
        ActionForward actionForward = mapping.findForward(Constants.MAPPING_BASIC);
        CoiDisclosureForm coiDisclosureForm = (CoiDisclosureForm) form;
        CoiDisclosure coiDisclosure = ((CoiDisclosureDocument)coiDisclosureForm.getDocument()).getCoiDisclosure();
        if (coiDisclosure.getCoiDisclosureId() == null) {
            coiDisclosure.initRequiredFields();            
        } else {
            getCoiDisclosureService().resetLeadUnit(coiDisclosure.getDisclosureReporter());
        }
        actionForward = super.save(mapping, form, request, response);
        
        return actionForward;
    }

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // TODO Auto-generated method stub
        ActionForward actionForward = super.execute(mapping, form, request, response);
        CoiDisclosureDocument coiDisclosureDocument = (CoiDisclosureDocument)((CoiDisclosureForm) form).getDocument();
        coiDisclosureDocument.getCoiDisclosure().initSelectedUnit();
        checkToLoadDisclosureDetails(coiDisclosureDocument.getCoiDisclosure());
        return actionForward;

    }

    private void checkToLoadDisclosureDetails(CoiDisclosure coiDisclosure) {
        // TODO : load FE disclosure when creating coi disc
        // still need more clarification on whether there is any other occasion this need to be loaded
        if (coiDisclosure.getCoiDisclosureId() == null && CollectionUtils.isEmpty(coiDisclosure.getCoiDiscDetails())) {
            getCoiDisclosureService().InitializeDisclosureDetails(coiDisclosure);
        }
        
    }
    
}
