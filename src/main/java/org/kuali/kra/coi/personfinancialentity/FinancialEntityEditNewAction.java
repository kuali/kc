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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.ObjectUtils;

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

        if (isValidToSave(financialEntityHelper.getNewPersonFinancialEntity(), NEW_FINANCIAL_ENTITY)) {
            saveNewFinancialEntity(form);
            if (StringUtils.isNotBlank(financialEntityForm.getCoiDocId())) {
                String forward = buildForwardUrl(Long.parseLong(financialEntityForm.getCoiDocId()));
                financialEntityForm.setCoiDocId(null);
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
        saveFinancialEntity(form, personFinIntDisclosure);
        financialEntityHelper.setNewPersonFinancialEntity(new PersonFinIntDisclosure());
        financialEntityHelper.getNewPersonFinancialEntity().setCurrentFlag(true);
        financialEntityHelper.getNewPersonFinancialEntity().setPersonId(GlobalVariables.getUserSession().getPrincipalId());
        financialEntityHelper.getNewPersonFinancialEntity().setFinancialEntityReporterId(
                financialEntityHelper.getFinancialEntityReporter().getFinancialEntityReporterId());
        financialEntityHelper.setNewRelationDetails(getFinancialEntityService().getFinancialEntityDataMatrix());
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
    

}
