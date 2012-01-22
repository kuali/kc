/*
 * Copyright 2005-2010 The Kuali Foundation
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
import static org.kuali.rice.krad.util.KRADConstants.METHOD_TO_CALL_ATTRIBUTE;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.bo.ResearchArea;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeMembership;
import org.kuali.kra.committee.bo.CommitteeMembershipRole;
import org.kuali.kra.committee.document.CommitteeDocument;
import org.kuali.kra.committee.rule.event.AddCommitteeMembershipEvent;
import org.kuali.kra.committee.rule.event.AddCommitteeMembershipRoleEvent;
import org.kuali.kra.committee.rule.event.DeleteCommitteeMemberEvent;
import org.kuali.kra.committee.rule.event.CommitteeMemberEventBase.ErrorType;
import org.kuali.kra.committee.rules.CommitteeDocumentRule;
import org.kuali.kra.committee.service.CommitteeMembershipService;
import org.kuali.kra.committee.web.struts.form.CommitteeForm;
import org.kuali.kra.committee.web.struts.form.CommitteeHelper;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.krad.bo.PersistableBusinessObject;
import org.kuali.rice.krad.util.KRADConstants;

/**
 * The CommitteeMembershipAction corresponds to the Members tab (web page).  It is
 * responsible for handling all user requests from that tab (web page).
 */
public class CommitteeMembershipAction extends CommitteeAction {
    
    @SuppressWarnings("unused")
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(CommitteeMembershipAction.class);

    
    /**
     * @see org.kuali.kra.committee.web.struts.action.CommitteeAction#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ActionForward actionForward = super.execute(mapping, form, request, response);

        ((CommitteeForm)form).getCommitteeHelper().prepareView();
        
        // reset member index for multi value lookups unless the multi value lookup is performed
        if (!StringUtils.equals((String) request.getAttribute("methodToCallAttribute"), "methodToCall.refresh.x") 
                && (!StringUtils.startsWith((String) request.getAttribute("methodToCallAttribute"), "methodToCall.performLookup."))) {
            ((CommitteeForm)form).getCommitteeHelper().setMemberIndex(-1);
        }
        
        return actionForward;
    }
    
    /**
     * This method perform the action - Add Committee Membership. 
     * Method is called in committeeAddCommitteeMembership.tag; rules are validated and if passed the committee member is added. 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward addCommitteeMembership(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        CommitteeForm committeeForm = (CommitteeForm) form;
        CommitteeMembership newCommitteeMembership = committeeForm.getCommitteeHelper().getNewCommitteeMembership();
        // check any business rules
        boolean rulePassed = applyRules(new AddCommitteeMembershipEvent(Constants.EMPTY_STRING, committeeForm.getCommitteeDocument(), newCommitteeMembership));
        if (rulePassed) {
            getCommitteeMembershipService().addCommitteeMembership(committeeForm.getCommitteeDocument().getCommittee(), newCommitteeMembership);
            committeeForm.getCommitteeHelper().setNewCommitteeMembership(new CommitteeMembership());
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC );
    }

    /**
     * This method is perform the action - Delete Committee Membership.
     * Method is called in CommitteeMembership.jsp
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deleteCommitteeMembership(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        CommitteeForm committeeForm = (CommitteeForm) form;
        CommitteeDocument committeeDocument = committeeForm.getCommitteeDocument();
        if (applyRules(new DeleteCommitteeMemberEvent(Constants.EMPTY_STRING, committeeForm.getDocument(), committeeForm
                .getCommitteeDocument().getCommittee().getCommitteeMemberships(), ErrorType.HARDERROR))) {
            getCommitteeMembershipService().deleteCommitteeMembership(committeeDocument.getCommittee());
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * This method is clears the CommitteeMembership selection.
     * Method is called in committeeAddCommitteeMembership.tag
       *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward clearCommitteeMembership(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        CommitteeForm committeeForm = (CommitteeForm) form;
        CommitteeHelper committeeHelper = committeeForm.getCommitteeHelper();
        committeeHelper.setNewCommitteeMembership(new CommitteeMembership());
        return mapping.findForward(MAPPING_BASIC);
    }
    
    /**
     * This method is linked to CommitteeMembershipService to perform the action.
     * Add a CommitteeMembershipRole to a CommitteeMembership.
     * Method is called in committeeMembershipRoleSection.tag
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward addCommitteeMembershipRole(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        CommitteeForm committeeForm = (CommitteeForm) form;
        Committee committee = committeeForm.getCommitteeDocument().getCommittee();
        int selectedMembershipIndex = getSelectedMembershipIndex(request);
        CommitteeMembershipRole newCommitteeMembershipRole 
            = committeeForm.getCommitteeHelper().getNewCommitteeMembershipRoles().get(selectedMembershipIndex);
    
        // check any business rules
        boolean rulePassed = applyRules(new AddCommitteeMembershipRoleEvent(Constants.EMPTY_STRING, committeeForm.getCommitteeDocument(), 
                                        newCommitteeMembershipRole, selectedMembershipIndex));

        if (rulePassed) {
            getCommitteeMembershipService().addCommitteeMembershipRole(committee, selectedMembershipIndex, newCommitteeMembershipRole);
            committeeForm.getCommitteeHelper().setNewCommitteeMembershipRoles(new ArrayList<CommitteeMembershipRole>());
        }
    
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * This method is linked to CommitteeMembershipService to perform the action.
     * Delete a CommitteeMembershipRole from a CommitteeMembership.
     * Method is called in committeeMembershipRoleSection.tag
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deleteCommitteeMembershipRole(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        CommitteeForm committeeForm = (CommitteeForm) form;
        Committee committee = committeeForm.getCommitteeDocument().getCommittee();
        
        getCommitteeMembershipService().deleteCommitteeMembershipRole(committee, getSelectedMembershipIndex(request), getSelectedLine(request));

        return mapping.findForward(MAPPING_BASIC);
    }

    /**
     * 
     * @see org.kuali.core.web.struts.action.KualiAction#performLookup(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward performLookup(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        String parameterName = (String) request.getAttribute(KRADConstants.METHOD_TO_CALL_ATTRIBUTE);
        String memberIndex = StringUtils.substringBetween(parameterName, "memberIndex", ".");
        if (StringUtils.isNotBlank(memberIndex)) {
            ((CommitteeForm)form).getCommitteeHelper().setMemberIndex(Integer.parseInt(memberIndex));
        }
        return super.performLookup(mapping, form, request, response);
    }
    
    /**
     * This method is linked to CommitteeMembershipService to perform the action.
     * Add CommitteeMembershipExpertise to a CommitteeMembership.
     * Method is called in committeeMembershipExpertiseSection.tag
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    protected void processMultipleLookupResults(CommitteeForm committeeForm,
            Class lookupResultsBOClass, Collection<PersistableBusinessObject> selectedBOs) {
        int membershipIndex = committeeForm.getCommitteeHelper().getMemberIndex();
        CommitteeMembership committeeMembership = committeeForm.getCommitteeDocument().getCommittee().getCommitteeMemberships().get(membershipIndex);
        if (lookupResultsBOClass.isAssignableFrom(ResearchArea.class)) {
            getCommitteeMembershipService().addCommitteeMembershipExpertise(committeeMembership, (Collection) selectedBOs);
            // finally do validation and error reporting for inactive research areas
            (new CommitteeDocumentRule()).checkResearchAreasForCommitteeMember(committeeMembership, membershipIndex);
        }
    }
    
    /**
     * This method is linked to CommitteeMembershipService to perform the action.
     * Delete a CommitteeMembershipExpertise from a CommitteeMembership.
     * Method is called in committeeMembershipExpertiseSection.tag
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deleteCommitteeMembershipExpertise(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        CommitteeForm committeeForm = (CommitteeForm) form;
        Committee committee = committeeForm.getCommitteeDocument().getCommittee();
        int membershipIndex = getSelectedMembershipIndex(request);
        getCommitteeMembershipService().deleteCommitteeMembershipExpertise(committee, membershipIndex, getSelectedLine(request));
        // finally do validation and error reporting for inactive research areas
        (new CommitteeDocumentRule()).checkResearchAreasForCommitteeMember(committee.getCommitteeMemberships().get(membershipIndex), membershipIndex);
        return mapping.findForward(MAPPING_BASIC);
    }
    
    /**
     * This method is to get committee membership service
     * @return CommitteeMembershipService
     */
    private CommitteeMembershipService getCommitteeMembershipService() {
        return (CommitteeMembershipService)KraServiceLocator.getService("committeeMembershipService");
    }

    /**
     * This method is to get selected membership index.
     * Each membership data is displayed in individual panel.
     * Membership index is required to identify the membership to perform an action.
     * @param request
     * @return index
     */
    protected int getSelectedMembershipIndex(HttpServletRequest request) {
        int selectedMembershipIndex = -1;
        String parameterName = (String) request.getAttribute(METHOD_TO_CALL_ATTRIBUTE);
        if (isNotBlank(parameterName)) {
            selectedMembershipIndex = Integer.parseInt(substringBetween(parameterName, "committeeMemberships[", "]."));
        }
        return selectedMembershipIndex;
    }
    
    public ActionForward showAllMembers(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        ((CommitteeForm) form).getCommitteeHelper().setShowActiveMembersOnly(false);
        ((CommitteeForm) form).setTabStates(new HashMap<String, String>());
        return mapping.findForward(MAPPING_BASIC);
    }

    public ActionForward showActiveMembersOnly(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        ((CommitteeForm) form).getCommitteeHelper().setShowActiveMembersOnly(true);
        ((CommitteeForm) form).setTabStates(new HashMap<String, String>());
        return mapping.findForward(MAPPING_BASIC);
    }

    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        ActionForward actionForward = super.save(mapping, form, request, response);
        ((CommitteeForm) form).getCommitteeHelper().flagInactiveMembers();
        return actionForward;
    }

    public ActionForward route(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        ActionForward actionForward = super.route(mapping, form, request, response);
        ((CommitteeForm) form).getCommitteeHelper().flagInactiveMembers();
        return actionForward;
    }

    public ActionForward reload(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        ActionForward actionForward = super.reload(mapping, form, request, response);
        ((CommitteeForm) form).getCommitteeHelper().flagInactiveMembers();
        return actionForward;
    }
}
