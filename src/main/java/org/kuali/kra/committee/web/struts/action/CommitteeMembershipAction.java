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

import static org.kuali.kra.infrastructure.Constants.MAPPING_BASIC;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.committee.bo.CommitteeMembership;
import org.kuali.kra.committee.bo.CommitteeMembershipRole;
import org.kuali.kra.committee.document.CommitteeDocument;
import org.kuali.kra.committee.rule.event.AddCommitteeMembershipEvent;
import org.kuali.kra.committee.rule.event.SaveCommitteeMembershipEvent;
import org.kuali.kra.committee.service.CommitteeMembershipService;
import org.kuali.kra.committee.web.struts.form.CommitteeForm;
import org.kuali.kra.committee.web.struts.form.MembershipHelper;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;

/**
 * The CommitteeMembershipAction corresponds to the Members tab (web page).  It is
 * responsible for handling all user requests from that tab (web page).
 */
public class CommitteeMembershipAction extends CommitteeAction {
    
    @SuppressWarnings("unused")
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(CommitteeMembershipAction.class);

    
    /**
     * @see org.kuali.kra.committee.web.struts.action.CommitteeAction#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ActionForward actionForward = super.execute(mapping, form, request, response);

        ((CommitteeForm)form).getMembershipHelper().prepareView();
        
        return actionForward;
    }
    
    /**
     * @see org.kuali.kra.committee.web.struts.action.CommitteeAction#isValidSave(org.kuali.kra.committee.web.struts.form.CommitteeForm)
     */
    @Override
    protected boolean isValidSave(CommitteeForm committeeForm) {
        boolean rulePassed = true;
        rulePassed &= applyRules(new SaveCommitteeMembershipEvent(Constants.EMPTY_STRING, committeeForm.getCommitteeDocument()));
        return rulePassed;
    }
   
    /**
     * This method is linked to ProtocolPersonnelService to perform the action - Add Committee Membership. 
     * Method is called in protocolAddCommitteeMembership.tag 
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
        boolean rulePassed = applyRules(new AddCommitteeMembershipEvent(Constants.EMPTY_STRING, committeeForm.getCommitteeDocument(), newCommitteeMembership));
        if (rulePassed) {
            getCommitteeMembershipService().addCommitteeMembership(committeeForm.getCommitteeDocument().getCommittee(), newCommitteeMembership);
            committeeForm.getMembershipHelper().setNewCommitteeMembership(new CommitteeMembership());
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC );
    }

    /**
     * This method is linked to CommitteeMembershipService to perform the action - Delete Committee Membership.
     * Method is called in CommitteeMembership.jsp
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deleteCommitteeMembership(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        CommitteeForm committeeForm = (CommitteeForm) form;
        CommitteeDocument committeeDocument = committeeForm.getCommitteeDocument();
        getCommitteeMembershipService().deleteCommitteeMembership(committeeDocument.getCommittee());
        return mapping.findForward(Constants.MAPPING_BASIC );
    }

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
        MembershipHelper membershipHelper = committeeForm.getMembershipHelper();
        membershipHelper.setNewCommitteeMembership(new CommitteeMembership());
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
    public ActionForward addCommitteeMembershipRole(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    CommitteeForm committeeForm = (CommitteeForm) form;
    CommitteeMembershipRole newCommitteeMembershipRole = committeeForm.getMembershipRolesHelper().getNewCommitteeMembershipRole();
    int selectedMembershipIndex = 0;
    
    // check any business rules
    boolean rulePassed = true; //applyRules(new AddCommitteeMembershipRoleEvent(Constants.EMPTY_STRING, committeeForm.getCommitteeDocument(), newCommitteeMembershipRole));
    if (rulePassed) {
        getCommitteeMembershipService().addCommitteeMembershipRole(committeeForm.getCommitteeDocument().getCommittee().getCommitteeMemberships().get(selectedMembershipIndex), newCommitteeMembershipRole);
        committeeForm.getMembershipRolesHelper().setNewCommitteeMembershipRole(new CommitteeMembershipRole());
    }
    
    return mapping.findForward(Constants.MAPPING_BASIC );
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
    }
    
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
