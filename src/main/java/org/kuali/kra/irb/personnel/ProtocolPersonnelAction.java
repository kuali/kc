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
package org.kuali.kra.irb.personnel;

import static org.apache.commons.lang.StringUtils.isNotBlank;
import static org.apache.commons.lang.StringUtils.substringBetween;
import static org.kuali.kra.infrastructure.Constants.MAPPING_BASIC;
import static org.kuali.rice.kns.util.KNSConstants.METHOD_TO_CALL_ATTRIBUTE;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.ProtocolAction;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.ProtocolForm;

/**
 * The ProtocolPersonnelAction corresponds to the Personnel tab (web page).  It is
 * responsible for handling all user requests from that tab (web page).
 */
public class ProtocolPersonnelAction extends ProtocolAction {
    
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(ProtocolPersonnelAction.class);

    /**
     * @see org.kuali.kra.irb.ProtocolAction#isValidSave(org.kuali.kra.irb.ProtocolForm)
     */
    @Override
    protected boolean isValidSave(ProtocolForm protocolForm) {    
        getProtocolPersonnelService().syncProtocolPersonRoleChanges(getProtocolPersons(protocolForm));
        boolean rulePassed = applyRules(new SaveProtocolPersonnelEvent(Constants.EMPTY_STRING, protocolForm.getDocument()));
        return rulePassed;
    }
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ActionForward actionForward = super.execute(mapping, form, request, response);
        getProtocolPersonnelService().selectProtocolUnit(getProtocolPersons(form));
        ((ProtocolForm)form).getPersonnelHelper().prepareView();
        
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
        ProtocolPerson newProtocolPerson = protocolForm.getPersonnelHelper().getNewProtocolPerson();
        
        // check any business rules
        boolean rulePassed = applyRules(new AddProtocolPersonnelEvent(Constants.EMPTY_STRING, protocolForm.getDocument(), newProtocolPerson));
        if (rulePassed) {
            getProtocolPersonnelService().addProtocolPerson(protocolForm.getDocument().getProtocol(), newProtocolPerson);
            protocolForm.getPersonnelHelper().setNewProtocolPerson(new ProtocolPerson());
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC );
    }

    /**
     * This method is linked to ProtocolPersonnelService to perform the action - Delete Protocol Person.
     * Method is called in ProtocolPersonnel.jsp
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deleteProtocolPerson(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolDocument protocolDocument = protocolForm.getDocument();
        getProtocolPersonnelService().deleteProtocolPerson(protocolDocument.getProtocol());
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
        protocolForm.getPersonnelHelper().setNewProtocolPerson(new ProtocolPerson());
        return mapping.findForward(MAPPING_BASIC);
    }
    
    
    /**
     * This method is linked to ProtocolPersonnelService to perform the action
     * Add ProtocolUnit to Person.
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
        ProtocolDocument protocolDocument = protocolForm.getDocument();
        int selectedPersonIndex = getSelectedPersonIndex(request, protocolDocument);

        ProtocolPerson protocolPerson = protocolDocument.getProtocol().getProtocolPerson(selectedPersonIndex);
        
        ProtocolUnit newProtocolPersonUnit = protocolForm.getPersonnelHelper().getNewProtocolPersonUnits().get(selectedPersonIndex);
        boolean rulePassed = applyRules(new AddProtocolUnitEvent(Constants.EMPTY_STRING, protocolForm.getDocument(), 
                newProtocolPersonUnit, selectedPersonIndex));

        if (rulePassed) {
            getProtocolPersonnelService().addProtocolPersonUnit(protocolForm.getPersonnelHelper().getNewProtocolPersonUnits(), protocolPerson, selectedPersonIndex);
        }

        return mapping.findForward(MAPPING_BASIC);
    }
    
    /**
     * This method is linked to ProtocolPersonnelService to perform the action.
     * Delete ProtocolUnit from Person unit list.
     * Method is called in personUnitsSection.tag
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deleteProtocolPersonUnit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolDocument protocolDocument = protocolForm.getDocument();
        int selectedPersonIndex = getSelectedPersonIndex(request, protocolDocument);
        getProtocolPersonnelService().deleteProtocolPersonUnit(protocolDocument.getProtocol(), selectedPersonIndex, getSelectedLine(request));

        return mapping.findForward(MAPPING_BASIC);
    }
    
    /**
     * This method is to update protocol person details view based on modified person role. 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward updateProtocolPersonView(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolDocument protocolDocument = protocolForm.getDocument();
        int selectedPersonIndex = getSelectedPersonIndex(request, protocolDocument);
        getProtocolPersonnelService().switchInvestigatorCoInvestigatorRole(protocolDocument.getProtocol().getProtocolPersons());
        getProtocolPersonnelService().syncPersonRoleAndUnit(protocolDocument.getProtocol().getProtocolPerson(selectedPersonIndex));
        getProtocolPersonnelService().syncPersonRoleAndAffiliation(protocolDocument.getProtocol().getProtocolPerson(selectedPersonIndex));
        return mapping.findForward(MAPPING_BASIC);
    }

    /**
     * This method is to get protocol personnel service
     * @return ProtocolPersonnelService
     */
    private ProtocolPersonnelService getProtocolPersonnelService() {
        return (ProtocolPersonnelService)KraServiceLocator.getService("protocolPersonnelService");
    }

    /**
     * This method is to get selected person index.
     * Each person data is displayed in individual panel.
     * Person index is required to identify the person to perform an action.
     * @param request
     * @param document
     * @return
     */
    protected int getSelectedPersonIndex(HttpServletRequest request, ProtocolDocument document) {
        int selectedPersonIndex = -1;
        String parameterName = (String) request.getAttribute(METHOD_TO_CALL_ATTRIBUTE);
        if (isNotBlank(parameterName)) {
            selectedPersonIndex = Integer.parseInt(substringBetween(parameterName, "protocolPersons[", "]."));
        }
        return selectedPersonIndex;
    }
    
    /**
     * This method is to get list of protocol persons
     * @param form
     * @return
     */
    private List<ProtocolPerson> getProtocolPersons(ActionForm form) {
        return ((ProtocolForm) form).getDocument().getProtocol().getProtocolPersons();
    }

}
