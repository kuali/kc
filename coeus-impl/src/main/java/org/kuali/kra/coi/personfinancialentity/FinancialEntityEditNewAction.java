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
package org.kuali.kra.coi.personfinancialentity;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.coi.CoiActionType;
import org.kuali.kra.coi.notesandattachments.attachments.FinancialEntityAttachment;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;
import org.kuali.rice.krad.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 * 
 * This class is the struts action for maintaining new financial entity page
 */
public class FinancialEntityEditNewAction extends FinancialEntityAction {
    private static final String NEW_FINANCIAL_ENTITY = "financialEntityHelper.newPersonFinancialEntity";

    /**
     * 
     * This method is to submit the new FE
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward submit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        FinancialEntityForm financialEntityForm = (FinancialEntityForm) form;
        FinancialEntityHelper financialEntityHelper = financialEntityForm.getFinancialEntityHelper();
        PersonFinIntDisclosure newFinancialEntityDisclosure = financialEntityHelper.getNewPersonFinancialEntity();            

        if (isValidToSave(newFinancialEntityDisclosure, NEW_FINANCIAL_ENTITY)) {
            saveNewFinancialEntity(form);
            // send notification to admins that FE has been modified
            sendNotificationAndPersist(CoiActionType.FE_CREATED_EVENT, "Financial Entity Modified", newFinancialEntityDisclosure);
            if (StringUtils.isNotBlank(financialEntityForm.getCoiDocId())) {
                String forward = buildForwardUrl(financialEntityForm.getCoiDocId());
                financialEntityForm.setCoiDocId(null);
                financialEntityForm.getFinancialEntityHelper().setReporterId(null);
                return new ActionForward(forward, true);
            }
        }

//        ((FinancialEntityForm) form).getFinancialEntityHelper().setActiveFinancialEntities(getFinancialEntities(true));
//        ((FinancialEntityForm) form).getFinancialEntityHelper().setInactiveFinancialEntities(getFinancialEntities(false));
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /*
     * utility method to set up the new financial entity for save
     */
    private void saveNewFinancialEntity(ActionForm form) {
        FinancialEntityHelper financialEntityHelper = ((FinancialEntityForm) form).getFinancialEntityHelper();
        PersonFinIntDisclosure personFinIntDisclosure = financialEntityHelper.getNewPersonFinancialEntity();
        personFinIntDisclosure.setEntityNumber(getFinancialEntityService().getNextEntityNumber()); 
        if (ObjectUtils.isNotNull(personFinIntDisclosure.getSponsor()) && ObjectUtils.isNotNull(personFinIntDisclosure.getSponsor().getSponsorName())) {
            personFinIntDisclosure.setSponsorName(personFinIntDisclosure.getSponsor().getSponsorName());
        }
        // it seems coeus always save 1.  not sure we need this because it should be in disclosure details
        personFinIntDisclosure.setRelationshipTypeCode("1");
        personFinIntDisclosure.setProcessStatus("F");
        personFinIntDisclosure.setSequenceNumber(1);
        personFinIntDisclosure.setPerFinIntDisclDetails(getFinancialEntityService().getFinDisclosureDetails(
                financialEntityHelper.getNewRelationDetails(), personFinIntDisclosure.getEntityNumber(),
                personFinIntDisclosure.getSequenceNumber()));
        personFinIntDisclosure.setFinEntityAttachments(financialEntityHelper.getFinEntityAttachmentList());
        saveFinancialEntity(form, personFinIntDisclosure);
        financialEntityHelper.setNewPersonFinancialEntity(new PersonFinIntDisclosure());
        financialEntityHelper.getNewPersonFinancialEntity().setCurrentFlag(true);
        financialEntityHelper.getNewPersonFinancialEntity().setPersonId(GlobalVariables.getUserSession().getPrincipalId());
        financialEntityHelper.getNewPersonFinancialEntity().setFinancialEntityReporterId(
                financialEntityHelper.getFinancialEntityReporter().getFinancialEntityReporterId());
        financialEntityHelper.setNewRelationDetails(getFinancialEntityService().getFinancialEntityDataMatrix());
        financialEntityHelper.setFinEntityAttachmentList(new ArrayList<FinancialEntityAttachment>());
    }

    /**
     * 
     * This method is for Coi disclosure FE 'newFinancialEntity'.  It will be forwarded to here
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward addNewCoiDiscFinancialEntity(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        ((FinancialEntityForm) form).getFinancialEntityHelper().initiate();
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * 
     * This method is to add a new attachment for Financial Entity
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward addNewFinancialEntityAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        FinancialEntityForm financialEntityForm = (FinancialEntityForm) form;
        financialEntityForm.getFinancialEntityHelper().addNewFinancialEntityAttachment();
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * Method called when deleting an attachment from a Financial Entity.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response= 
     * @return
     * @throws Exception
     */
    public ActionForward deleteFinancialEntityAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        FinancialEntityForm financialEntityForm = (FinancialEntityForm) form;
        int selectedLine = getSelectedLine(request);
        financialEntityForm.getFinancialEntityHelper().removeNewFinancialEntityAttachment(selectedLine);
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /*
     * for new FE and user cancels, go back to main page
     */
    @Override
    public ActionForward whereToGoAfterCancel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        return mapping.findForward(KRADConstants.MAPPING_PORTAL);
    }

}
