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
package org.kuali.kra.coi.personfinancialentity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.meeting.MeetingHelper;
import org.kuali.kra.meeting.MeetingSaveEvent;
import org.kuali.kra.meeting.MeetingEventBase.ErrorType;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.DictionaryValidationService;
import org.kuali.rice.kns.service.SequenceAccessorService;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.KNSConstants;
import org.kuali.rice.kns.web.struts.action.KualiAction;

public class FinancialEntityAction extends KualiAction {

    private static final String NEW_FINANCIAL_ENTITY = "financialEntityHelper.newPersonFinancialEntity";

    public ActionForward management(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        ((FinancialEntityForm) form).getFinancialEntityHelper().setActiveFinancialEntities(getFinancialEntities(true));
        ((FinancialEntityForm) form).getFinancialEntityHelper().setInactiveFinancialEntities(getFinancialEntities(false));
        return mapping.findForward("management");
    }

    public ActionForward submit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String parameterName = (String) request.getAttribute(KNSConstants.METHOD_TO_CALL_ATTRIBUTE);
        if (parameterName.contains(".new.")) {
            if (isValidToSave(((FinancialEntityForm) form).getFinancialEntityHelper().getNewPersonFinancialEntity(), NEW_FINANCIAL_ENTITY)) {
                saveNewFinancialEntity(form);
            }
        } else {
            int entityIndex = getSelectedLine(request);
            PersonFinIntDisclosure personFinIntDisclosure = ((FinancialEntityForm) form).getFinancialEntityHelper()
                    .getActiveFinancialEntities().get(entityIndex);

            if (isValidToSave(personFinIntDisclosure, "financialEntityHelper.activeFinancialEntities[" + entityIndex + "]")) {
                saveFinancialEntity(form, personFinIntDisclosure);
            }
            ((FinancialEntityForm) form).getFinancialEntityHelper().setEditEntityIndex(entityIndex);

        }
//        ((FinancialEntityForm) form).getFinancialEntityHelper().setActiveFinancialEntities(getFinancialEntities(true));
//        ((FinancialEntityForm) form).getFinancialEntityHelper().setInactiveFinancialEntities(getFinancialEntities(false));
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    public ActionForward editFinancialEntity(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        int entityIndex = getSelectedLine(request);
        PersonFinIntDisclosure personFinIntDisclosure = ((FinancialEntityForm) form).getFinancialEntityHelper()
                .getActiveFinancialEntities().get(entityIndex);
        ((FinancialEntityForm) form).getFinancialEntityHelper().setEditEntityIndex(entityIndex);
        // ((FinancialEntityForm) form).getFinancialEntityHelper().setActiveFinancialEntities(getFinancialEntities());
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    private void saveFinancialEntity(ActionForm form, PersonFinIntDisclosure personFinIntDisclosure) {
        getBusinessObjectService().save(personFinIntDisclosure);
        ((FinancialEntityForm) form).getFinancialEntityHelper().setActiveFinancialEntities(getFinancialEntities(true));
        ((FinancialEntityForm) form).getFinancialEntityHelper().setInactiveFinancialEntities(getFinancialEntities(false));
        
    }
    
    public ActionForward inactivateFinancialEntity(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        int entityIndex = getSelectedLine(request);
        PersonFinIntDisclosure personFinIntDisclosure = ((FinancialEntityForm) form).getFinancialEntityHelper()
                .getActiveFinancialEntities().get(entityIndex);
        personFinIntDisclosure.setStatusCode(2);
        getBusinessObjectService().save(personFinIntDisclosure);
        ((FinancialEntityForm) form).getFinancialEntityHelper().setActiveFinancialEntities(getFinancialEntities(true));
        ((FinancialEntityForm) form).getFinancialEntityHelper().setInactiveFinancialEntities(getFinancialEntities(false));
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    public ActionForward activateFinancialEntity(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        int entityIndex = getSelectedLine(request);
        PersonFinIntDisclosure personFinIntDisclosure = ((FinancialEntityForm) form).getFinancialEntityHelper()
                .getInactiveFinancialEntities().get(entityIndex);
        personFinIntDisclosure.setStatusCode(1);
        getBusinessObjectService().save(personFinIntDisclosure);
        ((FinancialEntityForm) form).getFinancialEntityHelper().setActiveFinancialEntities(getFinancialEntities(true));
        ((FinancialEntityForm) form).getFinancialEntityHelper().setInactiveFinancialEntities(getFinancialEntities(false));
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    private void saveNewFinancialEntity(ActionForm form) {
        PersonFinIntDisclosure personFinIntDisclosure = ((FinancialEntityForm) form).getFinancialEntityHelper()
                .getNewPersonFinancialEntity();
        personFinIntDisclosure.setEntityNumber(getSequenceAccessorService().getNextAvailableSequenceNumber("SEQ_ENTITY_NUMBER_S")
                .toString()); // sequence #
        personFinIntDisclosure.setRelationshipTypeCode(1);
        personFinIntDisclosure.setSequenceNumber(1);
        personFinIntDisclosure.setPersonId(GlobalVariables.getUserSession().getPrincipalId());
        saveFinancialEntity(form, personFinIntDisclosure);
        ((FinancialEntityForm) form).getFinancialEntityHelper().setNewPersonFinancialEntity(new PersonFinIntDisclosure());
        ((FinancialEntityForm) form).getFinancialEntityHelper().getNewPersonFinancialEntity().setCurrentFlag(true);
    }

    private boolean isValidToSave(PersonFinIntDisclosure personFinIntDisclosure, String errorPath) {

        // TODO : may need to add save event rule
        GlobalVariables.getMessageMap().addToErrorPath(errorPath);
        getDictionaryValidationService().validateBusinessObject(personFinIntDisclosure);
        GlobalVariables.getMessageMap().removeFromErrorPath(errorPath);
        return GlobalVariables.getMessageMap().hasNoErrors();

    }

    private DictionaryValidationService getDictionaryValidationService() {
        return KraServiceLocator.getService(DictionaryValidationService.class);
    }

    private BusinessObjectService getBusinessObjectService() {
        return KraServiceLocator.getService(BusinessObjectService.class);
    }

    private FinancialEntityService getFinancialEntityService() {
        return KraServiceLocator.getService(FinancialEntityService.class);
    }

    private SequenceAccessorService getSequenceAccessorService() {
        return KraServiceLocator.getService(SequenceAccessorService.class);
    }

    private List<PersonFinIntDisclosure> getFinancialEntities(boolean active) {
        return getFinancialEntityService().getFinancialEntities(GlobalVariables.getUserSession().getPrincipalId(), active);
    }
}
