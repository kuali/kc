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
package org.kuali.kra.subaward.web.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.bo.versioning.VersionHistory;
import org.kuali.kra.bo.versioning.VersionStatus;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.subaward.SubAwardForm;
import org.kuali.kra.subaward.bo.SubAward;
import org.kuali.kra.subaward.document.SubAwardDocument;
import org.kuali.rice.kew.exception.WorkflowException;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.KNSConstants;

public class SubAwardHomeAction extends SubAwardAction{
    
    
    private static final String DOC_HANDLER_URL_PATTERN = "%s/DocHandler.do?command=displayDocSearchView&docId=%s";
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ActionForward actionForward = super.execute(mapping, form, request, response);       
        return actionForward;
    }
    
    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        SubAwardForm subAwardForm = (SubAwardForm) form;
        ActionForward forward = super.save(mapping, form, request, response);
        return forward;
    }
    

    @SuppressWarnings("unchecked")
    @Override
    public ActionForward refresh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        super.refresh(mapping, form, request, response);
        SubAwardForm subAwardMultiLookupForm = (SubAwardForm)form;
        String lookupResultsBOClassName = request.getParameter(KNSConstants.LOOKUP_RESULTS_BO_CLASS_NAME);
        String lookupResultsSequenceNumber = request.getParameter(KNSConstants.LOOKUP_RESULTS_SEQUENCE_NUMBER);
        subAwardMultiLookupForm.setLookupResultsBOClassName(lookupResultsBOClassName);
        subAwardMultiLookupForm.setLookupResultsSequenceNumber(lookupResultsSequenceNumber);
        SubAward subAward = subAwardMultiLookupForm.getSubAwardDocument().getSubAward();
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * This method is used to handle the edit button action on an ACTIVE Subaward. If no Pending version exists for the same
     * subawardCode, a new Subaward version is created. If a Pending version exists, the user is prompted as to whether she would
     * like to edit the Pending version. Answering Yes results in that Pending version SubAwardDocument to be opened. Answering No 
     * simply returns the user to the ACTIVE document screen 
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward editOrVersion(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                        HttpServletResponse response) throws Exception {
        
        SubAwardForm subAwardForm = ((SubAwardForm)form);
        SubAwardDocument subAwardDocument = subAwardForm.getSubAwardDocument();
        SubAward subaward = subAwardDocument.getSubAward();
        ActionForward forward;
     
        forward = createAndSaveNewSubAwardVersion(response, subAwardForm, subAwardDocument, subaward);
        
        return forward;
    }
    
    private ActionForward createAndSaveNewSubAwardVersion(HttpServletResponse response, SubAwardForm subAwardForm,
       SubAwardDocument subAwardDocument, SubAward subAward) throws Exception {
       subAwardForm.getSubAwardDocument().getSubAward().setNewVersion(true); 
        SubAwardDocument newSubAwardDocument=getSubAwardService().createNewSubAwardVersion(subAwardForm.getSubAwardDocument());
       getDocumentService().saveDocument(newSubAwardDocument);
       getSubAwardService().updateSubAwardSequenceStatus(newSubAwardDocument.getSubAward(), VersionStatus.PENDING);
       getVersionHistoryService().createVersionHistory(newSubAwardDocument.getSubAward(), VersionStatus.PENDING,
                GlobalVariables.getUserSession().getPrincipalName());
        reinitializeSubAwardForm(subAwardForm, newSubAwardDocument);
        return new ActionForward(makeDocumentOpenUrl(newSubAwardDocument), true);
    }
    
    private String makeDocumentOpenUrl(SubAwardDocument newSubAwardDocument) {
        String workflowUrl = getKualiConfigurationService().getPropertyString(KNSConstants.WORKFLOW_URL_KEY);
        return String.format(DOC_HANDLER_URL_PATTERN, workflowUrl, newSubAwardDocument.getDocumentNumber());
    }
    /**
     * This method prepares the SubAwardForm with the document found via the SubAward lookup
     * Because the helper beans may have preserved a different SubAwardForm, we need to reset these too
     * @param subAwardForm
     * @param document
     */
    private void reinitializeSubAwardForm(SubAwardForm subAwardForm, SubAwardDocument document) throws WorkflowException {
        subAwardForm.populateHeaderFields(document.getDocumentHeader().getWorkflowDocument());
        subAwardForm.setDocument(document);
        document.setDocumentSaveAfterSubAwardLookupEditOrVersion(true);
        subAwardForm.initialize();
    }
}
