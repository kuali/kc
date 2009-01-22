/*
 * Copyright 2006-2008 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.irb.web.struts.action;

import static org.apache.commons.lang.StringUtils.isNotBlank;
import static org.apache.commons.lang.StringUtils.substringBetween;
import static org.kuali.kra.infrastructure.Constants.MAPPING_BASIC;
import static org.kuali.rice.kns.util.KNSConstants.METHOD_TO_CALL_ATTRIBUTE;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.bo.ProtocolPerson;
import org.kuali.kra.irb.bo.ProtocolUnit;
import org.kuali.kra.irb.document.ProtocolDocument;
import org.kuali.kra.irb.service.ProtocolPersonnelService;
import org.kuali.kra.irb.web.struts.form.ProtocolForm;

/**
 * The ProtocolProtocolAction corresponds to the Protocol tab (web page).  It is
 * responsible for handling all user requests from that tab (web page).
 */
public class ProtocolPersonnelAction extends ProtocolAction {
    
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(ProtocolPersonnelAction.class);
    
    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {      

        boolean rulePassed = true;
        // check any business rules
        ProtocolForm protocolForm = (ProtocolForm) form;
        return super.save(mapping, form, request, response);
    }

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ActionForward actionForward = super.execute(mapping, form, request, response);
        
        //Call to prepareView sets Labels Values from configuration service
        ((ProtocolForm)form).getProtocolHelper().prepareView();
        
        return actionForward;
    }

    /**
     * This method is linked to ProtocolPersonnelService to perform the action - Add Protocol Person. 
     * Method is called in protocolAddPersonnelSection.tag 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward addProtocolPerson(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolPerson newProtocolPerson = protocolForm.getNewProtocolPerson();
        
        // check any business rules
        boolean rulePassed = true;
        if (rulePassed) {
            getProtocolPersonnelService().addProtocolPerson(protocolForm.getProtocolDocument().getProtocol(), newProtocolPerson);
            protocolForm.setNewProtocolPerson(new ProtocolPerson());
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC );
    }

    /**
     * This method is to clear existing protocol person.
     * Method is called in protocolAddPersonnelSection.tag
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward clearProtocolPerson(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProtocolForm protocolForm = (ProtocolForm) form;
        protocolForm.setNewProtocolPerson(new ProtocolPerson());
        return mapping.findForward(MAPPING_BASIC);
    }
    
    
    /**
     * This method is linked to ProtocolPersonnelService to perform the action - Add ProtocolUnit to Person.
     * Method is called in personUnitsSection.tag
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward addProtocolPersonUnit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolDocument protocolDocument = protocolForm.getProtocolDocument();
        int selectedPersonIndex = getSelectedPersonIndex(request, protocolDocument);
        ProtocolUnit newProtocolPersonUnit = protocolForm.getNewProtocolPersonUnits().get(selectedPersonIndex);

        ProtocolPerson protocolPerson = protocolDocument.getProtocol().getProtocolPerson(selectedPersonIndex);
        
        
        // check any business rules
        boolean rulePassed = true;

        if (rulePassed) {
            getProtocolPersonnelService().addProtocolPersonUnit(protocolDocument.getProtocol(), protocolPerson, newProtocolPersonUnit);
            protocolForm.getNewProtocolPersonUnits().remove(selectedPersonIndex);
            protocolForm.getNewProtocolPersonUnits().add(selectedPersonIndex,new ProtocolUnit());
        }

        return mapping.findForward(MAPPING_BASIC);
    }
    
    /**
     * This method is to get protocol personnel service
     * @return ProtocolPersonnelService
     */
    private ProtocolPersonnelService getProtocolPersonnelService() {
        ProtocolPersonnelService protocolPersonnelService = (ProtocolPersonnelService)KraServiceLocator.getService("protocolPersonnelService");
        return protocolPersonnelService;
    }

    protected int getSelectedPersonIndex(HttpServletRequest request, ProtocolDocument document) {
        int selectedPersonIndex = -1;
        String parameterName = (String) request.getAttribute(METHOD_TO_CALL_ATTRIBUTE);
        if (isNotBlank(parameterName)) {
            selectedPersonIndex = Integer.parseInt(substringBetween(parameterName, "protocolPersons[", "]."));
        }
        return selectedPersonIndex;
    }
    
}
