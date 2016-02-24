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
import org.kuali.coeus.common.committee.impl.document.CommitteeDocumentBase;
import org.kuali.coeus.common.committee.impl.document.authorization.CommitteeTaskBase;
import org.kuali.coeus.common.committee.impl.rules.CommitteeDocumentRuleBase;
import org.kuali.coeus.common.committee.impl.service.CommitteeServiceBase;
import org.kuali.coeus.common.committee.impl.web.struts.form.CommitteeFormBase;
import org.kuali.coeus.sys.framework.controller.DocHandlerService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.bo.ResearchAreaBase;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.krad.bo.PersistableBusinessObject;
import org.kuali.rice.krad.util.KRADConstants;
import org.kuali.rice.krad.util.UrlFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Properties;

/**
 * The CommitteeCommitteeActionBase corresponds to the CommitteeBase tab (web page).  It is
 * responsible for handling all user requests from that tab (web page).
 */
public abstract class CommitteeCommitteeActionBase extends CommitteeActionBase {

    private static final String COMMITTEE_ID = "committeeId";
    private static final String COMMITTEE_DOCUMENT = "CommitteeDocument";

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

    public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
        CommitteeFormBase committeeFormBase = (CommitteeFormBase) form;
        if (committeeFormBase.getWorkflowDocument().isFinal()) {
            response.sendRedirect(makeMaintenaceUrl(committeeFormBase.getDocId(),
                    (committeeFormBase.getCommitteeDocument().getCommitteeId())));
            return null;
        }
            response.sendRedirect(buildForwardUrl(committeeFormBase.getDocId()));
            return null;

    }

    protected String makeMaintenaceUrl(String docId, String committeeId) {
        String baseURL = getDocHandlerService().getDocHandlerUrl(docId);
        final Properties parameters = new Properties();
        parameters.put(KRADConstants.DISPATCH_REQUEST_PARAMETER, KRADConstants.DOC_HANDLER_METHOD);
        parameters.put(KRADConstants.PARAMETER_COMMAND, KewApiConstants.INITIATE_COMMAND);
        parameters.put(KRADConstants.DOCUMENT_TYPE_NAME, COMMITTEE_DOCUMENT);
        parameters.put(COMMITTEE_ID, committeeId);
        return UrlFactory.parameterizeUrl(baseURL, parameters);
    }

    private DocHandlerService getDocHandlerService() {
        return KcServiceLocator.getService(DocHandlerService.class);
    }
    
}
