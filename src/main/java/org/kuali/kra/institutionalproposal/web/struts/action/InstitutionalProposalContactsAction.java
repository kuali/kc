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
package org.kuali.kra.institutionalproposal.web.struts.action;

import static org.apache.commons.lang.StringUtils.isNotBlank;
import static org.apache.commons.lang.StringUtils.substringBetween;
import static org.kuali.rice.krad.util.KRADConstants.METHOD_TO_CALL_ATTRIBUTE;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.institutionalproposal.contacts.InstitutionalProposalCreditSplitBean;
import org.kuali.kra.institutionalproposal.contacts.InstitutionalProposalPerson;
import org.kuali.kra.institutionalproposal.contacts.InstitutionalProposalProjectPersonnelBean;
import org.kuali.kra.institutionalproposal.contacts.InstitutionalProposalUnitContactsBean;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.web.struts.form.InstitutionalProposalForm;
import org.kuali.kra.service.ServiceHelper;
import org.kuali.kra.web.struts.action.StrutsConfirmation;

/**
 * This class...
 */
public class InstitutionalProposalContactsAction extends InstitutionalProposalAction {

    private static final String DELETE_PROJECT_PERSON_UNIT_PREFIX = "deleteProjectPersonUnit.";
    private static final String LINE_SUFFIX = ".line";
    private static final String CONFIRM_SYNC_UNIT_CONTACTS = "confirmSyncUnitContacts";
    private static final String CONFIRM_SYNC_UNIT_CONTACTS_KEY = "confirmSyncUnitContactsKey";

    
    
    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        InstitutionalProposalForm institutionalProposalForm = (InstitutionalProposalForm) form;
        InstitutionalProposal institutionalProposal = institutionalProposalForm.getInstitutionalProposalDocument().getInstitutionalProposal();
        setLeadUnitOnInstitutionalProposalFromPILeadUnit(institutionalProposal, institutionalProposalForm);
        institutionalProposalForm.getCentralAdminContactsBean().initCentralAdminContacts();
        ActionForward forward = super.save(mapping, form, request, response); 
        return forward;
    }
    
    public ActionForward reload(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        InstitutionalProposalForm institutionalProposalForm = (InstitutionalProposalForm) form;
        ActionForward forward = super.reload(mapping, form, request, response);
        institutionalProposalForm.getCentralAdminContactsBean().initCentralAdminContacts();

        return forward;
    }
    
    /**
     * This method is called to reset the Lead Unit on the InstitutionalProposal if the lead unit is changed on the PI.
     * @param institutionalProposal
     */
//    private void setLeadUnitOnInstitutionalProposalFromPILeadUnit(InstitutionalProposal institutionalProposal) {
//        for (InstitutionalProposalPerson person : institutionalProposal.getProjectPersons()) {
//            if(person.isPrincipalInvestigator()) {
//                Unit leadUnit = person.findLeadUnit();
//                institutionalProposal.setLeadUnit(leadUnit);
//                if (leadUnit != null) {
//                    institutionalProposal.setUnitNumber(leadUnit.getUnitNumber());
//                } else {
//                    institutionalProposal.setUnitNumber(null);
//                }
//            }
//        }
//    }
    
    /**
     * This method is called to reset the Lead Unit on the award if the lead unit is changed on the PI.
     * @param award
     */
    @SuppressWarnings("unchecked")
    private void setLeadUnitOnInstitutionalProposalFromPILeadUnit(InstitutionalProposal institutionalProposal, InstitutionalProposalForm institutionalProposalForm) {
        for (InstitutionalProposalPerson person : institutionalProposal.getProjectPersons()) {
            if(person.isPrincipalInvestigator()) {
                List<Unit> units= (List<Unit>) getBusinessObjectService().findMatching(Unit.class, 
                        ServiceHelper.getInstance().buildCriteriaMap("unitName", institutionalProposalForm.getProjectPersonnelBean().getSelectedLeadUnit()));
                if (units.size() > 0) {
                Unit leadUnit = units.get(0);
                institutionalProposal.setUnitNumber(leadUnit.getUnitNumber());
                institutionalProposal.setLeadUnit(leadUnit);
                }else {
                    institutionalProposal.setUnitNumber(null);
                    institutionalProposal.setLeadUnit(null);
                }
            }
        }
    }
    
    
    /**
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward addNewProjectPersonUnit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
                                                                                                                        throws Exception {
        
        getProjectPersonnelBean(form).addNewProjectPersonUnit(getSelectedLine(request));
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }
    
    /**
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward addProjectPerson(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
                                                                                                                        throws Exception {
        getProjectPersonnelBean(form).addProjectPerson();
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }
    
    /**
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deleteProjectPerson(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
                                                                                                                        throws Exception {
        getProjectPersonnelBean(form).deleteProjectPerson(getLineToDelete(request));
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }
    
    /**
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward addUnitContact(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
                                                                                                                        throws Exception {
        getUnitContactsBean(form).addUnitContact();
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }
    
    /**
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deleteUnitContact(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
                                                                                                                        throws Exception {
    
        getUnitContactsBean(form).deleteUnitContact(getLineToDelete(request));
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }

    /**
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deleteProjectPersonUnit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
                                                                                                                        throws Exception {
        getProjectPersonnelBean(form).deleteProjectPersonUnit(getProjectPersonIndex(request), getLineToDelete(request));
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }
    
    private InstitutionalProposalProjectPersonnelBean getProjectPersonnelBean(ActionForm form) {
        return ((InstitutionalProposalForm) form).getProjectPersonnelBean();
    }
    
    private int getProjectPersonIndex(HttpServletRequest request) {
        int selectedPersonIndex = -1;
        String parameterName = (String) request.getAttribute(METHOD_TO_CALL_ATTRIBUTE);
        if (isNotBlank(parameterName)) {
            selectedPersonIndex = Integer.parseInt(substringBetween(parameterName, DELETE_PROJECT_PERSON_UNIT_PREFIX, LINE_SUFFIX));
        }

        return selectedPersonIndex;
    }
    
    /**
     * Simply returns and the recalculation will happen
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward recalculateCreditSplit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
                                                                                                                        throws Exception {
        getInstitutionalProposalCreditSplitBean(form).recalculateCreditSplit();
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }
    
    /**
     * This is action called when sync the unit contacts is called from Award Unit contacts tab.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward syncDefaultUnitContactsToLeadUnit (ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
                                                                                                                    throws Exception {
        return confirm(buildSyncUnitContactsConfirmationQuestion(mapping, form, request, response), CONFIRM_SYNC_UNIT_CONTACTS, "");
    }
    
    /**
     * 
     * This method is to build the confirmation question for syncing unit contacts.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @param deletePeriod
     * @return
     * @throws Exception
     */
    private StrutsConfirmation buildSyncUnitContactsConfirmationQuestion(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        return buildParameterizedConfirmationQuestion(mapping, form, request, response, CONFIRM_SYNC_UNIT_CONTACTS_KEY,
                KeyConstants.QUESTION_SYNC_UNIT_CONTACTS);
    }
    
    /**
     * This method is called if the user clicks 'yes' in confirmation question.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return mapping forward
     * @throws Exception
     */
    public ActionForward confirmSyncUnitContacts(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        InstitutionalProposalForm institutionalProposalForm = (InstitutionalProposalForm) form;
        getUnitContactsBean(institutionalProposalForm).syncInstitutionalProposalUnitContactsToLeadUnitContacts();
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    
    private InstitutionalProposalCreditSplitBean getInstitutionalProposalCreditSplitBean(ActionForm form) {
        return ((InstitutionalProposalForm) form).getInstitutionalProposalCreditSplitBean();
    }
    
    private InstitutionalProposalUnitContactsBean getUnitContactsBean(ActionForm form) {
        return ((InstitutionalProposalForm) form).getUnitContactsBean();
    }
    
    
}
