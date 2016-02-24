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
