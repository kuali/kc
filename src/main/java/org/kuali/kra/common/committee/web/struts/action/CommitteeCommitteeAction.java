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
package org.kuali.kra.common.committee.web.struts.action;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.bo.ResearchArea;
import org.kuali.kra.common.committee.bo.CommonCommittee;
import org.kuali.kra.common.committee.bo.businessLogic.CommonCommitteeBusinessLogic;
import org.kuali.kra.common.committee.bo.businessLogic.CommonCommitteeCollaboratorBusinessLogicFactoryGroup;
import org.kuali.kra.common.committee.document.CommonCommitteeDocument;
import org.kuali.kra.common.committee.document.authorization.CommitteeTask;
import org.kuali.kra.common.committee.service.CommonCommitteeService;
import org.kuali.kra.common.committee.web.struts.form.CommonCommitteeForm;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.service.VersioningService;
import org.kuali.kra.service.impl.VersioningServiceImpl;
import org.kuali.rice.krad.bo.PersistableBusinessObject;
import org.kuali.rice.krad.util.KRADConstants;

/**
 * The CommitteeCommitteeAction corresponds to the Committee tab (web page).  It is
 * responsible for handling all user requests from that tab (web page).
 */
public class CommitteeCommitteeAction extends CommitteeAction {
    
    private static final String COMMITTEE_ID = "committeeId";

    @SuppressWarnings("unused")
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(CommitteeCommitteeAction.class);
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ActionForward actionForward = super.execute(mapping, form, request, response);
        // Following is for committee lookup - edit committee 
        CommonCommitteeForm committeeForm = ((CommonCommitteeForm)form);
        String commandParam = request.getParameter(KRADConstants.PARAMETER_COMMAND);
        if (StringUtils.isNotBlank(commandParam) && commandParam.equals("initiate") && StringUtils.isNotBlank(request.getParameter(COMMITTEE_ID))) {
            CommonCommittee committee = getCommitteeService().getCommitteeById(request.getParameter(COMMITTEE_ID));
            /* don't need the original committeeDocument saved in xml content */
            committee.setCommitteeDocument(null);
            committeeForm.getCommitteeDocument().setCommittee(committee);
            VersioningService versionService = new VersioningServiceImpl();
            committeeForm.getCommitteeDocument().setCommittee((CommonCommittee) versionService.createNewVersion(committee));
            committeeForm.getCommitteeDocument().getCommittee().setCommitteeDocument(committeeForm.getCommitteeDocument());
        }
       
        committeeForm.getCommitteeHelper().prepareView();
        
        return actionForward;
    }
    
    /**
     * @see org.kuali.kra.common.committee.web.struts.action.CommitteeAction#processMultipleLookupResults(org.kuali.kra.common.committee.document.CommonCommitteeDocument, java.lang.Class, java.util.Collection)
     */
    @Override
    protected void processMultipleLookupResults(CommonCommitteeForm committeeForm,
            Class lookupResultsBOClass, Collection<PersistableBusinessObject> selectedBOs) {
        if (lookupResultsBOClass.isAssignableFrom(ResearchArea.class)) {
            CommonCommittee committee = committeeForm.getCommitteeDocument().getCommittee();
            getCommitteeService().addResearchAreas(committee, (Collection) selectedBOs);
            // finally do validation and error reporting for inactive research areas
            getCommitteeBusinessLogic(committee).validateCommitteeResearchAreas();
        }
    }
    
    /**
     * Delete a Research Area from a Committee.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deleteResearchArea(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception {
        
        CommonCommitteeForm committeeForm = (CommonCommitteeForm) form;
        CommonCommitteeDocument committeeDocument = committeeForm.getCommitteeDocument();
        CommonCommittee committee = committeeDocument.getCommittee();
        
        CommitteeTask task = new CommitteeTask(TaskName.MODIFY_COMMITTEE, committee);
        if (isAuthorized(task)) {   
            committee.getCommitteeResearchAreas().remove(getLineToDelete(request));
        }
        // finally do validation and error reporting for inactive research areas
        getCommitteeBusinessLogic(committee).validateCommitteeResearchAreas();
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    public CommonCommitteeBusinessLogic getCommitteeBusinessLogic(CommonCommittee committee) {
        CommonCommitteeCollaboratorBusinessLogicFactoryGroup cmtGrp = KraServiceLocator.getService(CommonCommitteeCollaboratorBusinessLogicFactoryGroup.class);
        CommonCommitteeBusinessLogic committeeBusinessLogic = cmtGrp.getCommitteeBusinessLogicFor(committee);
        return committeeBusinessLogic;
    }

    private CommonCommitteeService getCommitteeService() {
        return (CommonCommitteeService) KraServiceLocator.getService(CommonCommitteeService.class);
    }

}
