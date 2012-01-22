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
package org.kuali.kra.award.web.struts.action;

import static org.apache.commons.lang.StringUtils.isNotBlank;
import static org.apache.commons.lang.StringUtils.substringBetween;
import static org.kuali.rice.krad.util.KRADConstants.METHOD_TO_CALL_ATTRIBUTE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.cxf.common.util.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.award.AwardForm;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncPendingChangeBean;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncType;
import org.kuali.kra.award.contacts.AwardCreditSplitBean;
import org.kuali.kra.award.contacts.AwardPerson;
import org.kuali.kra.award.contacts.AwardPersonUnit;
import org.kuali.kra.award.contacts.AwardProjectPersonnelBean;
import org.kuali.kra.award.contacts.AwardSponsorContact;
import org.kuali.kra.award.contacts.AwardSponsorContactsBean;
import org.kuali.kra.award.contacts.AwardUnitContactsBean;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.ContactRole;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonRole;
import org.kuali.kra.service.ServiceHelper;
import org.kuali.kra.service.SponsorService;
import org.kuali.kra.web.struts.action.StrutsConfirmation;
import org.kuali.rice.krad.service.BusinessObjectService;

/**
 * 
 * This class represents the Struts Action for Contacts page(AwardContacts.jsp)
 */
public class AwardContactsAction extends AwardAction {
    
    private static final String PROJECT_PERSON_PREFIX = ".personIndex";
    private static final String LINE_SUFFIX = ".line";
    private static final String CONFIRM_SYNC_UNIT_CONTACTS = "confirmSyncUnitContacts";
    private static final String CONFIRM_SYNC_UNIT_DETAILS = "confirmSyncUnitDetails";
    private static final String ADD_SYNC_UNIT_DETAILS = "addSyncUnitDetails";
    private static final String CONFIRM_SYNC_UNIT_CONTACTS_KEY = "confirmSyncUnitContactsKey";


    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AwardForm awardForm = (AwardForm) form;
        Award award = awardForm.getAwardDocument().getAward();
        ActionForward forward;
        if (isValidSave(awardForm)) {
            setLeadUnitOnAwardFromPILeadUnit(award, awardForm);
            award.initCentralAdminContacts();
            forward = super.save(mapping, form, request, response);
        } else {
            forward = mapping.findForward(Constants.MAPPING_AWARD_BASIC);            
        }
        return forward;
    }
    
    public ActionForward reload(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AwardForm awardForm = (AwardForm) form;
        ActionForward forward = super.reload(mapping, form, request, response);
        awardForm.getAwardDocument().getAward().initCentralAdminContacts();

        return forward;
    }
    
    /**
     * This method is called to reset the Lead Unit on the award if the lead unit is changed on the PI.
     * @param award
     */
    @SuppressWarnings("unchecked")
    private void setLeadUnitOnAwardFromPILeadUnit(Award award, AwardForm awardForm) {
        for (AwardPerson person : award.getProjectPersons()) {
            if (person.isPrincipalInvestigator() && person.getUnits().size() >= 1) {
                AwardPersonUnit selectedUnit = null;
                for (AwardPersonUnit unit : person.getUnits()) {
                    if (unit.isLeadUnit()) {
                        selectedUnit = unit;
                    }
                }
                //if a unit hasn't been selected as lead, use the first unit
                if (selectedUnit == null) {
                    selectedUnit = person.getUnit(0);
                }
                if (selectedUnit != null) {
                    award.setUnitNumber(selectedUnit.getUnitNumber());
                    award.setLeadUnit(selectedUnit.getUnit());
                } else {
                    award.setUnitNumber(null);
                    award.setLeadUnit(null);
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
        Award award = ((AwardForm)form).getAwardDocument().getAward();
        AwardPersonUnit newPersonUnit = getProjectPersonnelBean(form).getNewAwardPersonUnit(getSelectedLine(request));
    
        if( newPersonUnit.isLeadUnit() && award.getLeadUnit() != null){
            return confirm(buildSyncUnitDetailsConfirmationQuestion(mapping, form, request, response), CONFIRM_SYNC_UNIT_DETAILS, ADD_SYNC_UNIT_DETAILS);
        }
        else
        {
            AwardPersonUnit unit = getProjectPersonnelBean(form).addNewProjectPersonUnit(getSelectedLine(request));
            if (unit != null) {
                return confirmSyncAction(mapping, form, request, response, AwardSyncType.ADD_SYNC, unit, "projectPersons", null, mapping.findForward(Constants.MAPPING_AWARD_BASIC));       
            } else {
                return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
            }
        }
    }
    
    /**
     * 
     * This method is to build the confirmation question for syncing unit contacts.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return StrutsConfirmation
     * @throws Exception
     */
    private StrutsConfirmation buildSyncUnitDetailsConfirmationQuestion(ActionMapping mapping, ActionForm form,
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
    public ActionForward confirmSyncUnitDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        AwardPersonUnit unit = getProjectPersonnelBean(form).addNewProjectPersonUnit(getSelectedLine(request));
        if (unit != null) {
            AwardForm awardForm = (AwardForm) form;
            getUnitContactsBean(awardForm).syncAwardUnitContactsToLeadUnitContacts();
            getAward(form).initCentralAdminContacts();
            return confirmSyncAction(mapping, form, request, response, AwardSyncType.ADD_SYNC, unit, "projectPersons", null, mapping.findForward(Constants.MAPPING_AWARD_BASIC));       
        } else {
            return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
        }
        
    }
    /**
     * This method is called if the user clicks 'No' in confirmation question.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return mapping forward
     * @throws Exception
     */
    public ActionForward addSyncUnitDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        AwardForm awardForm = (AwardForm)form;
        AwardPersonUnit unit = getProjectPersonnelBean(form).addNewProjectPersonUnit(getSelectedLine(request));
        if (unit != null) {
            getAward(awardForm).initCentralAdminContacts();
            return confirmSyncAction(mapping, form, request, response, AwardSyncType.ADD_SYNC, unit, "projectPersons", null, mapping.findForward(Constants.MAPPING_AWARD_BASIC));       
        } else {
            return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
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
    public ActionForward addProjectPerson(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
                                                                                                                        throws Exception {
        AwardPerson awardPerson = getProjectPersonnelBean(form).addProjectPerson();
        if (awardPerson != null) {
            return this.confirmSyncAction(mapping, form, request, response, AwardSyncType.ADD_SYNC, awardPerson, "projectPersons", null, 
                    mapping.findForward(Constants.MAPPING_AWARD_BASIC));
        } else {
            return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
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
    public ActionForward addSponsorContact(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
                                                                                                                        throws Exception {
        AwardSponsorContact contact = getSponsorContactsBean(form).addSponsorContact();
        if (contact != null) {
            return this.confirmSyncAction(mapping, form, request, response, AwardSyncType.ADD_SYNC, contact, "sponsorContacts", null, 
                    mapping.findForward(Constants.MAPPING_AWARD_BASIC));
        } else {
            return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
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
    public ActionForward deleteProjectPerson(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
                                                                                                                        throws Exception {
        AwardPerson awardPerson = getProjectPersonnelBean(form).getProjectPersonnel().get(getLineToDelete(request));
        getProjectPersonnelBean(form).deleteProjectPerson(getLineToDelete(request));
        return this.confirmSyncAction(mapping, form, request, response, AwardSyncType.DELETE_SYNC, awardPerson, "projectPersons", null, mapping.findForward(Constants.MAPPING_AWARD_BASIC));
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
        AwardPersonUnit unit = getProjectPersonnelBean(form).getProjectPersonnel().get(getProjectPersonIndex(request)).getUnit(getLineToDelete(request));
        getProjectPersonnelBean(form).deleteProjectPersonUnit(getProjectPersonIndex(request), getLineToDelete(request));
        return this.confirmSyncAction(mapping, form, request, response, AwardSyncType.DELETE_SYNC, unit, "projectPersons", null, mapping.findForward(Constants.MAPPING_AWARD_BASIC));
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
    public ActionForward deleteSponsorContact(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
                                                                                                                        throws Exception {
        AwardSponsorContact contact = getSponsorContactsBean(form).getSponsorContacts().get(getLineToDelete(request));
        getSponsorContactsBean(form).deleteSponsorContact(getLineToDelete(request));
        return this.confirmSyncAction(mapping, form, request, response, AwardSyncType.DELETE_SYNC, contact, "sponsorContacts", null, mapping.findForward(Constants.MAPPING_AWARD_BASIC));
    }

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, ServletRequest request, ServletResponse response) throws Exception {
        ActionForward actionForward = super.execute(mapping, form, request, response);

        SponsorService sponsorService = getSponsorService();
        Award award = getAward(form);

        return actionForward;
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
        getAwardCreditSplitBean(form).recalculateCreditSplit();
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
        AwardForm awardForm = (AwardForm) form;
        getUnitContactsBean(awardForm).syncAwardUnitContactsToLeadUnitContacts();
        getAward(awardForm).initCentralAdminContacts();
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * @return
     */
    protected BusinessObjectService getBusinessObjectService() {
        return KraServiceLocator.getService(BusinessObjectService.class);
    }
    
    private int getProjectPersonIndex(HttpServletRequest request) {
        int selectedPersonIndex = -1;
        String parameterName = (String) request.getAttribute(METHOD_TO_CALL_ATTRIBUTE);
        if (isNotBlank(parameterName)) {
            selectedPersonIndex = Integer.parseInt(substringBetween(parameterName, PROJECT_PERSON_PREFIX, LINE_SUFFIX));
        }

        return selectedPersonIndex;
    }
    
    private AwardCreditSplitBean getAwardCreditSplitBean(ActionForm form) {
        return ((AwardForm) form).getAwardCreditSplitBean();
    }
    
    private AwardProjectPersonnelBean getProjectPersonnelBean(ActionForm form) {
        return ((AwardForm) form).getProjectPersonnelBean();
    }
    
    private AwardSponsorContactsBean getSponsorContactsBean(ActionForm form) {
        return ((AwardForm) form).getSponsorContactsBean();
    }
    
    private AwardUnitContactsBean getUnitContactsBean(ActionForm form) {
        return ((AwardForm) form).getUnitContactsBean();
    }
    
    public ActionForward syncProjectPerson(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        AwardForm awardForm = (AwardForm)form;
        Award award = awardForm.getAwardDocument().getAward();
        AwardPerson person = award.getProjectPerson(getSelectedLine(request));
        awardForm.getAwardSyncBean().addConfirmedPendingChange(AwardSyncType.ADD_SYNC, person, "projectPersons");
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }

    public ActionForward syncProjectPersonUnit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        AwardForm awardForm = (AwardForm)form;
        Award award = awardForm.getAwardDocument().getAward();
        AwardPersonUnit unit = award.getProjectPerson(getProjectPersonIndex(request)).getUnit(getSelectedLine(request));
        awardForm.getAwardSyncBean().addConfirmedPendingChange(AwardSyncType.ADD_SYNC, unit, "projectPersons");
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }  
    
    public ActionForward syncSponsorContact(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        AwardForm awardForm = (AwardForm)form;
        Award award = awardForm.getAwardDocument().getAward();
        AwardSponsorContact contact = award.getSponsorContacts().get(getSelectedLine(request));
        awardForm.getAwardSyncBean().addConfirmedPendingChange(AwardSyncType.ADD_SYNC, contact, "sponsorContacts");
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }    
    
}