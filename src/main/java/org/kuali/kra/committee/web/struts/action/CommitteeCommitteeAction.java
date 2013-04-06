/*
 * Copyright 2005-2013 The Kuali Foundation
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

import org.kuali.kra.bo.ResearchAreaBase;
import org.kuali.kra.committee.rules.CommitteeDocumentRule;
import org.kuali.kra.committee.service.CommitteeService;
import org.kuali.kra.common.committee.bo.CommitteeBase;
import org.kuali.kra.common.committee.document.authorization.CommitteeTaskBase;
import org.kuali.kra.common.committee.rules.CommitteeDocumentRuleBase;
import org.kuali.kra.common.committee.service.CommitteeServiceBase;
import org.kuali.kra.common.committee.web.struts.action.CommitteeCommitteeActionBase;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.infrastructure.TaskGroupName;
import org.kuali.kra.irb.ResearchArea;

/**
 * The CommitteeCommitteeAction corresponds to the Committee tab (web page).  It is
 * responsible for handling all user requests from that tab (web page).
 */
public class CommitteeCommitteeAction extends CommitteeCommitteeActionBase {

    @Override
    protected Class<? extends ResearchAreaBase> getResearchAreaBOClassHook() {
        return ResearchArea.class;
    }

    @Override
    protected CommitteeDocumentRuleBase getNewCommitteeDocumentRuleInstanceHook() {
        return new CommitteeDocumentRule();
    }

    @Override
    protected CommitteeTaskBase getNewCommitteeTaskInstanceHook(String taskName, CommitteeBase committee) {
        // creating an anonymous class to avoid task hierarchy issues
        return new CommitteeTaskBase<Committee>(TaskGroupName.COMMITTEE, taskName, (Committee) committee) {};
    }

    @Override
    protected Class<? extends CommitteeServiceBase> getCommitteeServiceBOClassHook() {
        return CommitteeService.class;
    }

    @Override
    protected String getCommitteeDocumentTypeSimpleNameHook() {
        return "CommitteeDocument";
    }

// TODO ********************** commented out during IRB backfit ************************    
//    private static final String COMMITTEE_ID = "committeeId";
//
//    @SuppressWarnings("unused")
//    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(CommitteeCommitteeAction.class);
//    
//    @Override
//    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
//            throws Exception {
//        ActionForward actionForward = super.execute(mapping, form, request, response);
//        // Following is for committee lookup - edit committee 
//        CommitteeForm committeeForm = ((CommitteeForm)form);
//        String commandParam = request.getParameter(KRADConstants.PARAMETER_COMMAND);
//        if (StringUtils.isNotBlank(commandParam) && commandParam.equals("initiate") && StringUtils.isNotBlank(request.getParameter(COMMITTEE_ID))) {
//            Committee committee = getCommitteeService().getCommitteeById(request.getParameter(COMMITTEE_ID));
//            /* don't need the original committeeDocument saved in xml content */
//            committee.setCommitteeDocument(null);
//            committeeForm.getCommitteeDocument().setCommittee(committee);
//            VersioningService versionService = new VersioningServiceImpl();
//            committeeForm.getCommitteeDocument().setCommittee((Committee) versionService.createNewVersion(committee));
//            committeeForm.getCommitteeDocument().getCommittee().setCommitteeDocument(committeeForm.getCommitteeDocument());
//        }
//       
//        committeeForm.getCommitteeHelper().prepareView();
//        
//        return actionForward;
//    }
//    
//    /**
//     * @see org.kuali.kra.committee.web.struts.action.CommitteeAction#processMultipleLookupResults(org.kuali.kra.committee.document.CommitteeDocument, java.lang.Class, java.util.Collection)
//     */
//    @Override
//    protected void processMultipleLookupResults(CommitteeForm committeeForm,
//            Class lookupResultsBOClass, Collection<PersistableBusinessObject> selectedBOs) {
//        if (lookupResultsBOClass.isAssignableFrom(ResearchArea.class)) {
//            Committee committee = committeeForm.getCommitteeDocument().getCommittee();
//            getCommitteeService().addResearchAreas(committee, (Collection) selectedBOs);
//            // finally do validation and error reporting for inactive research areas
//            getCommitteeBusinessLogic(committee).validateCommitteeResearchAreas();
//        }
//    }
//    
//    /**
//     * Delete a Research Area from a Committee.
//     * @param mapping
//     * @param form
//     * @param request
//     * @param response
//     * @return
//     * @throws Exception
//     */
//    public ActionForward deleteResearchArea(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
//    throws Exception {
//        
//        CommitteeForm committeeForm = (CommitteeForm) form;
//        CommitteeDocument committeeDocument = committeeForm.getCommitteeDocument();
//        Committee committee = committeeDocument.getCommittee();
//        
//        CommitteeTask task = new CommitteeTask(TaskName.MODIFY_COMMITTEE, committee);
//        if (isAuthorized(task)) {   
//            committee.getCommitteeResearchAreas().remove(getLineToDelete(request));
//        }
//        // finally do validation and error reporting for inactive research areas
//        getCommitteeBusinessLogic(committee).validateCommitteeResearchAreas();
//        
//        return mapping.findForward(Constants.MAPPING_BASIC);
//    }
//
//    public CommitteeBusinessLogic getCommitteeBusinessLogic(Committee committee) {
//        CommitteeCollaboratorBusinessLogicFactoryGroup cmtGrp = KraServiceLocator.getService(CommitteeCollaboratorBusinessLogicFactoryGroup.class);
//        CommitteeBusinessLogic committeeBusinessLogic = cmtGrp.getCommitteeBusinessLogicFor(committee);
//        return committeeBusinessLogic;
//    }
//
//    private CommitteeService getCommitteeService() {
//        return (CommitteeService) KraServiceLocator.getService(CommitteeService.class);
//    }

}
