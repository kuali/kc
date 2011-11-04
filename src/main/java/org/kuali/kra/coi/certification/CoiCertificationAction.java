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
package org.kuali.kra.coi.certification;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.coi.CoiAction;
import org.kuali.kra.coi.CoiDisclosure;
import org.kuali.kra.coi.CoiDisclosureDocument;
import org.kuali.kra.coi.CoiDisclosureForm;
import org.kuali.kra.coi.disclosure.DisclosurePerson;
import org.kuali.kra.coi.service.CoiPrintingService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.rule.event.KraDocumentEventBaseExtension;

public class CoiCertificationAction extends CoiAction {

    //TODO: This may need some work...
    public ActionForward saveDisclosureCertification(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        CoiDisclosureForm coiDisclosureForm = (CoiDisclosureForm) form;
        CoiDisclosure disclosure = ((CoiDisclosureDocument)coiDisclosureForm.getDocument()).getCoiDisclosure();
        if (checkRule(new CertifyDisclosureEvent("disclosureHelper.certifyDisclosure", disclosure))) {
            disclosure.certifyDisclosure();
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    //TODO: This will need some work...
    public ActionForward printDisclosureCertification(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
System.out.println("\nNew printDisclosureCertification event occurred.... ");        
        CoiDisclosureForm coiDisclosureForm = (CoiDisclosureForm) form;
        CoiDisclosureDocument coiDisclosureDocument = coiDisclosureForm.getCoiDisclosureDocument();
        CoiDisclosure coiDisclosure = coiDisclosureDocument.getCoiDisclosure();
        DisclosurePerson selectedPerson = coiDisclosure.getDisclosurePersons().get(0);
        CoiPrintingService printService = KraServiceLocator.getService(CoiPrintingService.class);
        Map<String,Object> reportParameters = new HashMap<String,Object>();
        reportParameters.put(CoiPrintingService.PRINT_CERTIFICATION_PERSON, selectedPerson);
        reportParameters.put(CoiPrintingService.PRINT_CERTIFICATION_STATEMENT, coiDisclosure.getCertificationStatement());
        reportParameters.put(CoiPrintingService.PRINT_CERTIFICATION_TIMESTAMP, coiDisclosure.getCertificationTimestamp());
//TODO        AttachmentDataSource dataStream = printService.print(coiDisclosure, CoiPrintingService.PRINT_CERTIFICATION, reportParameters);
//TODO        streamToResponse(dataStream, response);
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    private boolean checkRule(KraDocumentEventBaseExtension event) {
        return event.getRule().processRules(event);
    }

}
