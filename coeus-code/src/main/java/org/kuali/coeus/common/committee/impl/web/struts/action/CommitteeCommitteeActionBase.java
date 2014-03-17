/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.common.committee.impl.web.struts.action;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.common.committee.impl.bo.CommitteeBase;
import org.kuali.coeus.common.committee.impl.document.CommitteeDocumentBase;
import org.kuali.coeus.common.committee.impl.document.authorization.CommitteeTaskBase;
import org.kuali.coeus.common.committee.impl.rules.CommitteeDocumentRuleBase;
import org.kuali.coeus.common.committee.impl.service.CommitteeServiceBase;
import org.kuali.coeus.common.committee.impl.web.struts.form.CommitteeFormBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.bo.ResearchAreaBase;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.rice.krad.bo.PersistableBusinessObject;
import org.kuali.rice.krad.util.KRADConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

/**
 * The CommitteeCommitteeActionBase corresponds to the CommitteeBase tab (web page).  It is
 * responsible for handling all user requests from that tab (web page).
 */
public abstract class CommitteeCommitteeActionBase extends CommitteeActionBase {
    
    private static final String COMMITTEE_ID = "committeeId";

    @SuppressWarnings("unused")
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(CommitteeCommitteeActionBase.class);
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ActionForward actionForward = super.execute(mapping, form, request, response);
        // Following is for committee lookup - edit committee 
        CommitteeFormBase committeeForm = ((CommitteeFormBase)form);
        String commandParam = request.getParameter(KRADConstants.PARAMETER_COMMAND);
        if (StringUtils.isNotBlank(commandParam) && commandParam.equals("initiate") && StringUtils.isNotBlank(request.getParameter(COMMITTEE_ID))) {
            CommitteeBase committee = getCommitteeService().getLightVersion(request.getParameter(COMMITTEE_ID));
            CommitteeDocumentBase committeeDocument = committeeForm.getCommitteeDocument(); 
            committeeDocument.setCommittee(committee);
            committee.setCommitteeDocument(committeeDocument);
        }
       
        committeeForm.getCommitteeHelper().prepareView();
        
        return actionForward;
    }
    
    @Override
    protected void processMultipleLookupResults(CommitteeFormBase committeeForm,
            Class lookupResultsBOClass, Collection<PersistableBusinessObject> selectedBOs) {
        if (lookupResultsBOClass.isAssignableFrom(getResearchAreaBOClassHook())) {
            CommitteeBase committee = committeeForm.getCommitteeDocument().getCommittee();
            getCommitteeService().addResearchAreas(committee, (Collection) selectedBOs);
            // finally do validation and error reporting for inactive research areas
            getNewCommitteeDocumentRuleInstanceHook().validateCommitteeResearchAreas(committee);
        }
    }

    protected abstract Class<? extends ResearchAreaBase> getResearchAreaBOClassHook();
    
    protected abstract CommitteeDocumentRuleBase getNewCommitteeDocumentRuleInstanceHook();
    

    /**
     * Delete a Research Area from a CommitteeBase.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deleteResearchArea(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception {
        
        CommitteeFormBase committeeForm = (CommitteeFormBase) form;
        CommitteeDocumentBase committeeDocument = committeeForm.getCommitteeDocument();
        CommitteeBase committee = committeeDocument.getCommittee();

        CommitteeTaskBase task = getNewCommitteeTaskInstanceHook(TaskName.MODIFY_COMMITTEE, committee);
        if (isAuthorized(task)) {   
            committee.getCommitteeResearchAreas().remove(getLineToDelete(request));
        }
        // finally do validation and error reporting for inactive research areas
        getNewCommitteeDocumentRuleInstanceHook().validateCommitteeResearchAreas(committee);
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    protected abstract CommitteeTaskBase getNewCommitteeTaskInstanceHook(String taskName, CommitteeBase committee);
    
    
    
    private CommitteeServiceBase getCommitteeService() {
        return (CommitteeServiceBase) KcServiceLocator.getService(getCommitteeServiceBOClassHook());
    }

    protected abstract Class<? extends CommitteeServiceBase> getCommitteeServiceBOClassHook();
    
}
