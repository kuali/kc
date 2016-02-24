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
package org.kuali.coeus.common.committee.impl.web.struts.action;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.common.committee.impl.bo.CommitteeBase;
import org.kuali.coeus.common.committee.impl.bo.CommitteeMembershipBase;
import org.kuali.coeus.common.committee.impl.bo.CommitteeMembershipRole;
import org.kuali.coeus.common.committee.impl.document.CommitteeDocumentBase;
import org.kuali.coeus.common.committee.impl.rule.event.AddCommitteeMembershipEvent;
import org.kuali.coeus.common.committee.impl.rule.event.AddCommitteeMembershipRoleEvent;
import org.kuali.coeus.common.committee.impl.rule.event.DeleteCommitteeMemberEventBase;
import org.kuali.coeus.common.committee.impl.rule.event.CommitteeMemberEventBase.ErrorType;
import org.kuali.coeus.common.committee.impl.rules.CommitteeDocumentRuleBase;
import org.kuali.coeus.common.committee.impl.service.CommitteeMembershipServiceBase;
import org.kuali.coeus.common.committee.impl.web.struts.form.CommitteeFormBase;
import org.kuali.coeus.common.committee.impl.web.struts.form.CommitteeHelperBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.bo.ResearchAreaBase;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.krad.bo.PersistableBusinessObject;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.util.KRADConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.apache.commons.lang3.StringUtils.substringBetween;
import static org.kuali.kra.infrastructure.Constants.MAPPING_BASIC;
import static org.kuali.rice.krad.util.KRADConstants.METHOD_TO_CALL_ATTRIBUTE;

/**
 * The CommitteeMembershipActionBase corresponds to the Members tab (web page).  It is
 * responsible for handling all user requests from that tab (web page).
 */
public abstract class CommitteeMembershipActionBase extends CommitteeActionBase {
    
    @SuppressWarnings("unused")
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(CommitteeMembershipActionBase.class);

    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ActionForward actionForward = super.execute(mapping, form, request, response);

        ((CommitteeFormBase)form).getCommitteeHelper().prepareView();
        
        // reset member index for multi value lookups unless the multi value lookup is performed
        if (!StringUtils.equals((String) request.getAttribute("methodToCallAttribute"), "methodToCall.refresh.x") 
                && (!StringUtils.startsWith((String) request.getAttribute("methodToCallAttribute"), "methodToCall.performLookup."))) {
            ((CommitteeFormBase)form).getCommitteeHelper().setMemberIndex(-1);
        }
        
        return actionForward;
    }
    
    /**
     * This method perform the action - Add CommitteeBase Membership. 
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
        CommitteeFormBase committeeForm = (CommitteeFormBase) form;
        CommitteeMembershipBase newCommitteeMembership = committeeForm.getCommitteeHelper().getNewCommitteeMembership();
        // check any business rules
        boolean rulePassed = applyRules(new AddCommitteeMembershipEvent(Constants.EMPTY_STRING, committeeForm.getCommitteeDocument(), newCommitteeMembership));
        if (rulePassed) {
            getCommitteeMembershipService().addCommitteeMembership(committeeForm.getCommitteeDocument().getCommittee(), newCommitteeMembership);

            committeeForm.getCommitteeHelper().setNewCommitteeMembership(getNewCommitteeMembershipInstanceHook());
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC );
    }
    
    protected abstract CommitteeMembershipBase getNewCommitteeMembershipInstanceHook();

    /**
     * This method is perform the action - Delete CommitteeBase Membership.
     * Method is called in CommitteeMembershipBase.jsp
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deleteCommitteeMembership(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        CommitteeFormBase committeeForm = (CommitteeFormBase) form;
        CommitteeDocumentBase committeeDocument = committeeForm.getCommitteeDocument();
        if (applyRules(getNewDeleteCommitteeMemberEventInstanceHook(Constants.EMPTY_STRING, committeeForm.getDocument(), committeeForm
                .getCommitteeDocument().getCommittee().getCommitteeMemberships(), ErrorType.HARDERROR))) {
            getCommitteeMembershipService().deleteCommitteeMembership(committeeDocument.getCommittee());
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    protected abstract DeleteCommitteeMemberEventBase getNewDeleteCommitteeMemberEventInstanceHook(String errorPathPrefix, Document document, List<CommitteeMembershipBase> committeeMemberships,
            ErrorType type);

    /**
     * This method is clears the CommitteeMembershipBase selection.
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
        CommitteeFormBase committeeForm = (CommitteeFormBase) form;
        CommitteeHelperBase committeeHelper = committeeForm.getCommitteeHelper();

        committeeHelper.setNewCommitteeMembership(getNewCommitteeMembershipInstanceHook());
        return mapping.findForward(MAPPING_BASIC);
    }
    
    /**
     * This method is linked to CommitteeMembershipService to perform the action.
     * Add a CommitteeMembershipRole to a CommitteeMembershipBase.
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
        CommitteeFormBase committeeForm = (CommitteeFormBase) form;
        CommitteeBase committee = committeeForm.getCommitteeDocument().getCommittee();
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
     * Delete a CommitteeMembershipRole from a CommitteeMembershipBase.
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
        CommitteeFormBase committeeForm = (CommitteeFormBase) form;
        CommitteeBase committee = committeeForm.getCommitteeDocument().getCommittee();
        
        getCommitteeMembershipService().deleteCommitteeMembershipRole(committee, getSelectedMembershipIndex(request), getSelectedLine(request));

        return mapping.findForward(MAPPING_BASIC);
    }

    @Override
    public ActionForward performLookup(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        String parameterName = (String) request.getAttribute(KRADConstants.METHOD_TO_CALL_ATTRIBUTE);
        String memberIndex = StringUtils.substringBetween(parameterName, "memberIndex", ".");
        if (StringUtils.isNotBlank(memberIndex)) {
            ((CommitteeFormBase)form).getCommitteeHelper().setMemberIndex(Integer.parseInt(memberIndex));
        }
        return super.performLookup(mapping, form, request, response);
    }
    
    /**
     * This method is linked to CommitteeMembershipService to perform the action.
     * Add CommitteeMembershipExpertise to a CommitteeMembershipBase.
     * Method is called in committeeMembershipExpertiseSection.tag
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    protected void processMultipleLookupResults(CommitteeFormBase committeeForm,
            Class lookupResultsBOClass, Collection<PersistableBusinessObject> selectedBOs) {
        int membershipIndex = committeeForm.getCommitteeHelper().getMemberIndex();
        CommitteeMembershipBase committeeMembership = ((CommitteeBase<?, ?, ?>)(committeeForm.getCommitteeDocument().getCommittee())).getCommitteeMemberships().get(membershipIndex);
        if (lookupResultsBOClass.isAssignableFrom(getResearchAreaBOClassHook())) {
            getCommitteeMembershipService().addCommitteeMembershipExpertise(committeeMembership, (Collection) selectedBOs);
            // finally do validation and error reporting for inactive research areas
            (getNewCommitteeDocumentRuleInstanceHook()).checkResearchAreasForCommitteeMember(committeeMembership, membershipIndex);

        }
    }
    
    protected abstract Class<? extends ResearchAreaBase> getResearchAreaBOClassHook();
    protected abstract CommitteeDocumentRuleBase getNewCommitteeDocumentRuleInstanceHook();

    /**
     * This method is linked to CommitteeMembershipService to perform the action.
     * Delete a CommitteeMembershipExpertise from a CommitteeMembershipBase.
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
        CommitteeFormBase committeeForm = (CommitteeFormBase) form;
        CommitteeBase<?, ?, ?> committee = committeeForm.getCommitteeDocument().getCommittee();
        int membershipIndex = getSelectedMembershipIndex(request);
        getCommitteeMembershipService().deleteCommitteeMembershipExpertise(committee, membershipIndex, getSelectedLine(request));
        // finally do validation and error reporting for inactive research areas
        (getNewCommitteeDocumentRuleInstanceHook()).checkResearchAreasForCommitteeMember(committee.getCommitteeMemberships().get(membershipIndex), membershipIndex);

        return mapping.findForward(MAPPING_BASIC);
    }
    
    /**
     * This method is to get committee membership service
     * @return CommitteeMembershipService
     */
    private CommitteeMembershipServiceBase getCommitteeMembershipService() {

        return KcServiceLocator.getService(getCommitteeMembershipServiceClassHook());
    }

    protected abstract Class<? extends CommitteeMembershipServiceBase> getCommitteeMembershipServiceClassHook();
    
    

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
        ((CommitteeFormBase) form).getCommitteeHelper().setShowActiveMembersOnly(false);
        ((CommitteeFormBase) form).setTabStates(new HashMap<String, String>());
        return mapping.findForward(MAPPING_BASIC);
    }

    public ActionForward showActiveMembersOnly(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        ((CommitteeFormBase) form).getCommitteeHelper().setShowActiveMembersOnly(true);
        ((CommitteeFormBase) form).setTabStates(new HashMap<String, String>());
        return mapping.findForward(MAPPING_BASIC);
    }

    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        ActionForward actionForward = super.save(mapping, form, request, response);
        ((CommitteeFormBase) form).getCommitteeHelper().flagInactiveMembers();
        return actionForward;
    }

    public ActionForward route(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        ActionForward actionForward = super.route(mapping, form, request, response);
        ((CommitteeFormBase) form).getCommitteeHelper().flagInactiveMembers();
        return actionForward;
    }

    public ActionForward reload(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        ActionForward actionForward = super.reload(mapping, form, request, response);
        ((CommitteeFormBase) form).getCommitteeHelper().flagInactiveMembers();
        return actionForward;
    }
}
