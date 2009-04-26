/*
 * Copyright 2006-2008 The Kuali Foundation
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
import static org.kuali.rice.kns.util.KNSConstants.METHOD_TO_CALL_ATTRIBUTE;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.award.contacts.AwardCentralAdminContactsBean;
import org.kuali.kra.award.contacts.AwardProjectPersonnelBean;
import org.kuali.kra.award.contacts.AwardSponsorContactsBean;
import org.kuali.kra.award.contacts.AwardUnitContactsBean;
import org.kuali.kra.award.web.struts.form.AwardForm;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.kns.service.BusinessObjectService;

/**
 * 
 * This class represents the Struts Action for Contacts page(AwardContacts.jsp)
 */
public class AwardContactsAction extends AwardAction {
    
    private static final String DELETE_PROJECT_PERSON_UNIT_PREFIX = "deleteProjectPersonUnit.";
    private static final String LINE_SUFFIX = ".line";

    public ActionForward addNewProjectPersonUnit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
                                                                                                                        throws Exception {
        
        getProjectPersonnelBean(form).addNewProjectPersonUnit(getSelectedLine(request));
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }
    
    public ActionForward addProjectPerson(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
                                                                                                                        throws Exception {
        getProjectPersonnelBean(form).addProjectPerson();
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }
    
    public ActionForward addSponsorContact(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
                                                                                                                        throws Exception {
        getSponsorContactsBean(form).addSponsorContact();
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }

    public ActionForward addCentralAdminContact(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
                                                                                                                        throws Exception {
        getCentralAdminContactsBean(form).addCentralAdminContact();
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }
    
    public ActionForward addUnitContact(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
                                                                                                                        throws Exception {
        getUnitContactsBean(form).addUnitContact();
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }
    
    public ActionForward clearNewProjectPerson(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
                                                                                                                        throws Exception {
        getProjectPersonnelBean(form).clearNewContact();
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }

    public ActionForward clearNewProjectPersonUnit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
                                                                                                                        throws Exception {
        getProjectPersonnelBean(form).clearNewProjectPersonUnit();
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }
    
    public ActionForward clearNewCentralAdminContact(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
                                                                                                                        throws Exception {
        getCentralAdminContactsBean(form).clearNewContact();
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }
    
    public ActionForward clearNewUnitContact(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
                                                                                                                        throws Exception {
        getUnitContactsBean(form).clearNewContact();
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }
    
    public ActionForward clearNewSponsorContact(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
                                                                                                                        throws Exception {
        getSponsorContactsBean(form).clearNewContact();
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }
    
    public ActionForward deleteProjectPerson(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
                                                                                                                        throws Exception {
        
        getProjectPersonnelBean(form).deleteProjectPerson(getLineToDelete(request));
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }

    public ActionForward deleteProjectPersonUnit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
                                                                                                                        throws Exception {
        getProjectPersonnelBean(form).deleteProjectPersonUnit(getProjectPersonIndex(request), getLineToDelete(request));
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }
    
    public ActionForward deleteUnitContact(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
                                                                                                                        throws Exception {
    
        getUnitContactsBean(form).deleteUnitContact(getLineToDelete(request));
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }
    
    public ActionForward deleteCentralAdminContact(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
                                                                                                                        throws Exception {
    
        getCentralAdminContactsBean(form).deleteContact(getLineToDelete(request));
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }
    
    public ActionForward deleteSponsorContact(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
                                                                                                                        throws Exception {
    
        getSponsorContactsBean(form).deleteSponsorContact(getLineToDelete(request));
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
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
            selectedPersonIndex = Integer.parseInt(substringBetween(parameterName, DELETE_PROJECT_PERSON_UNIT_PREFIX, LINE_SUFFIX));
        }

        return selectedPersonIndex;
    }
    
    private AwardCentralAdminContactsBean getCentralAdminContactsBean(ActionForm form) {
        return ((AwardForm) form).getCentralAdminContactsBean();
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
}