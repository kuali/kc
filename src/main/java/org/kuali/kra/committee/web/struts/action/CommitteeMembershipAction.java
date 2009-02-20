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
package org.kuali.kra.committee.web.struts.action;

import static org.apache.commons.lang.StringUtils.isNotBlank;
import static org.apache.commons.lang.StringUtils.substringBetween;
import static org.kuali.kra.infrastructure.Constants.MAPPING_BASIC;
import static org.kuali.rice.kns.util.KNSConstants.METHOD_TO_CALL_ATTRIBUTE;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.core.web.ui.KeyLabelPair;
import org.kuali.kra.award.bo.AwardReportTerm;
import org.kuali.kra.award.bo.AwardReportTermRecipient;
import org.kuali.kra.award.bo.ReportClass;
import org.kuali.kra.award.web.struts.form.AwardForm;
import org.kuali.kra.committee.bo.CommitteeMembership;
import org.kuali.kra.committee.service.CommitteeMembershipService;
import org.kuali.kra.committee.web.struts.form.CommitteeForm;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.bo.ProtocolPerson;
import org.kuali.kra.irb.bo.ProtocolUnit;
import org.kuali.kra.irb.document.ProtocolDocument;
import org.kuali.kra.irb.rule.event.AddProtocolPersonnelEvent;
import org.kuali.kra.irb.rule.event.AddProtocolUnitEvent;
import org.kuali.kra.irb.rule.event.SaveProtocolPersonnelEvent;
import org.kuali.kra.irb.rule.event.UpdateProtocolPersonnelEvent;
import org.kuali.kra.irb.service.ProtocolPersonTrainingService;
import org.kuali.kra.irb.service.ProtocolPersonnelService;
import org.kuali.kra.irb.web.struts.form.ProtocolForm;
import org.kuali.kra.service.AwardReportsService;

/**
 * The CommitteeMembershipAction corresponds to the Members tab (web page).  It is
 * responsible for handling all user requests from that tab (web page).
 */
public class CommitteeMembershipAction extends CommitteeAction {
    
//    @SuppressWarnings("unused")
//    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(CommitteeMembershipAction.class);
//
//    /**
//     * @see org.kuali.kra.committee.web.struts.action.CommitteeAction#isValidSave(org.kuali.kra.committee.web.struts.form.CommitteeForm)
//     */
//    @Override
//    protected boolean isValidSave(CommitteeForm committeeForm) {    
//        boolean rulePassed = applyRules(new SaveCommitteeMembershipEvent(Constants.EMPTY_STRING, committeeForm.getCommitteeDocument()));
//        return rulePassed;
//    }
   
//    @Override
//    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
//            throws Exception {
//        ActionForward actionForward = super.execute(mapping, form, request, response);
//        getProtocolPersonnelService().selectProtocolUnit(getProtocolPersons(form));
//        getProtocolPersonTrainingService().isPersonTrained(getProtocolPersons(form));
//        
//        ((ProtocolForm)form).getProtocolHelper().prepareView();
//        ((ProtocolForm)form).getPersonnelHelper().prepareView();
//        ((ProtocolForm)form).getPermissionsHelper().prepareView();
//        
//        return actionForward;
//    }

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
    public ActionForward addCommitteeMembership(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        CommitteeForm committeeForm = (CommitteeForm) form;
        CommitteeMembership newCommitteeMembership = committeeForm.getMembershipHelper().getNewCommitteeMembership();
        
        // check any business rules
        //boolean rulePassed = applyRules(new AddProtocolPersonnelEvent(Constants.EMPTY_STRING, protocolForm.getProtocolDocument(), protocolForm.getNewProtocolPerson()));
        boolean rulePassed = true;
        if (rulePassed) {
            getCommitteeMembershipService().addCommitteeMembership(committeeForm.getCommitteeDocument().getCommittee(), newCommitteeMembership);
            committeeForm.getMembershipHelper().setNewCommitteeMembership(new CommitteeMembership());
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC );
    }

//    /**
//     * This method is linked to ProtocolPersonnelService to perform the action - Delete Protocol Person.
//     * Method is called in ProtocolPersonnel.jsp
//     * @param mapping
//     * @param form
//     * @param request
//     * @param response
//     * @return
//     * @throws Exception
//     */
//    public ActionForward deleteProtocolPerson(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
//        ProtocolForm protocolForm = (ProtocolForm) form;
//        ProtocolDocument protocolDocument = protocolForm.getProtocolDocument();
//        getProtocolPersonnelService().deleteProtocolPerson(protocolDocument.getProtocol());
//        return mapping.findForward(Constants.MAPPING_BASIC );
//    }
//
    /**
     * This method is clears the CommitteeMembership selection.
     * Method is called in protocolAddCommitteeMembership.tag
       *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward clearCommitteeMembership(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        CommitteeForm committeeForm = (CommitteeForm) form;
        committeeForm.getMembershipHelper().setNewCommitteeMembership(new CommitteeMembership());
        committeeForm.getMembershipHelper().setNewPersonName(null);
        return mapping.findForward(MAPPING_BASIC);
    }
    
//    
//    /**
//     * This method is linked to ProtocolPersonnelService to perform the action
//     * Add ProtocolUnit to Person.
//     * Method is called in personUnitsSection.tag
//     * @param mapping
//     * @param form
//     * @param request
//     * @param response
//     * @return
//     * @throws Exception
//     */
//    public ActionForward addProtocolPersonUnit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
//        ProtocolForm protocolForm = (ProtocolForm) form;
//        ProtocolDocument protocolDocument = protocolForm.getProtocolDocument();
//        int selectedPersonIndex = getSelectedPersonIndex(request, protocolDocument);
//
//        ProtocolPerson protocolPerson = protocolDocument.getProtocol().getProtocolPerson(selectedPersonIndex);
//        
//        ProtocolUnit newProtocolPersonUnit = protocolForm.getNewProtocolPersonUnits().get(selectedPersonIndex);
//        boolean rulePassed = applyRules(new AddProtocolUnitEvent(Constants.EMPTY_STRING, protocolForm.getProtocolDocument(), 
//                newProtocolPersonUnit, selectedPersonIndex));
//
//        if (rulePassed) {
//            getProtocolPersonnelService().addProtocolPersonUnit(protocolForm.getNewProtocolPersonUnits(), protocolPerson, selectedPersonIndex);
//        }
//
//        return mapping.findForward(MAPPING_BASIC);
//    }
//    
//    /**
//     * This method is linked to ProtocolPersonnelService to perform the action.
//     * Delete ProtocolUnit from Person unit list.
//     * Method is called in personUnitsSection.tag
//     * @param mapping
//     * @param form
//     * @param request
//     * @param response
//     * @return
//     * @throws Exception
//     */
//    public ActionForward deleteProtocolPersonUnit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
//        ProtocolForm protocolForm = (ProtocolForm) form;
//        ProtocolDocument protocolDocument = protocolForm.getProtocolDocument();
//        int selectedPersonIndex = getSelectedPersonIndex(request, protocolDocument);
//        ProtocolPerson protocolPerson = protocolDocument.getProtocol().getProtocolPerson(selectedPersonIndex);
//        getProtocolPersonnelService().deleteProtocolPersonUnit(protocolDocument.getProtocol(), protocolPerson, selectedPersonIndex, getSelectedLine(request));
//
//        return mapping.findForward(MAPPING_BASIC);
//    }
//    
//    /**
//     * This method is to update protocol person details view based on modified person role. 
//     * @param mapping
//     * @param form
//     * @param request
//     * @param response
//     * @return
//     * @throws Exception
//     */
//    public ActionForward updateProtocolPersonView(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
//        ProtocolForm protocolForm = (ProtocolForm) form;
//        ProtocolDocument protocolDocument = protocolForm.getProtocolDocument();
//        int selectedPersonIndex = getSelectedPersonIndex(request, protocolDocument);
//        boolean rulePassed = applyRules(new UpdateProtocolPersonnelEvent(Constants.EMPTY_STRING, protocolForm.getProtocolDocument(), selectedPersonIndex));
//        if(rulePassed) {
//            ProtocolPerson protocolPerson = protocolDocument.getProtocol().getProtocolPerson(selectedPersonIndex);
//            protocolPerson.refreshReferenceObject("protocolPersonRole");
//        }
//        return mapping.findForward(MAPPING_BASIC);
//    }

    /**
     * This method is to get committee membership service
     * @return CommitteeMembershipService
     */
    private CommitteeMembershipService getCommitteeMembershipService() {
        return (CommitteeMembershipService)KraServiceLocator.getService("committeeMembershipService");
    }

//    /**
//     * This method is to get selected person index.
//     * Each person data is displayed in individual panel.
//     * Person index is required to identify the person to perform an action.
//     * @param request
//     * @param document
//     * @return
//     */
//    protected int getSelectedPersonIndex(HttpServletRequest request, ProtocolDocument document) {
//        int selectedPersonIndex = -1;
//        String parameterName = (String) request.getAttribute(METHOD_TO_CALL_ATTRIBUTE);
//        if (isNotBlank(parameterName)) {
//            selectedPersonIndex = Integer.parseInt(substringBetween(parameterName, "protocolPersons[", "]."));
//        }
//        return selectedPersonIndex;
//    }
//    
//    /**
//     * This method is to get list of protocol persons
//     * @param form
//     * @return
//     */
//    private List<ProtocolPerson> getProtocolPersons(ActionForm form) {
//        return ((ProtocolForm) form).getProtocolDocument().getProtocol().getProtocolPersons();
//    }
}
