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
package org.kuali.kra.irb.correspondence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.rice.core.api.util.RiceConstants;
import org.kuali.rice.core.api.util.RiceKeyConstants;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.kns.web.struts.action.KualiDocumentActionBase;
import org.kuali.rice.krad.exception.AuthorizationException;
import org.kuali.rice.kns.question.ConfirmationQuestion;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;

/**
 * Actions of the batch correspondence details.
 */
public class BatchCorrespondenceDetailAction extends KualiDocumentActionBase {
    private static final String BATCH_CORRESPONDENCE_TYPE_CODE = "batchCorrespondenceTypeCode";

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {

        // Check and initialize permissions
        if (getBatchCorrespondenceDetailAuthorizationService().hasPermission(PermissionConstants.MODIFY_BATCH_CORRESPONDENCE_DETAIL)) {
            ((BatchCorrespondenceDetailForm) form).setReadOnly(false);
        } else if (getBatchCorrespondenceDetailAuthorizationService().hasPermission(PermissionConstants.VIEW_BATCH_CORRESPONDENCE_DETAIL)) {
            ((BatchCorrespondenceDetailForm) form).setReadOnly(true);
        } else {
            throw new AuthorizationException(GlobalVariables.getUserSession().getPerson().getPrincipalName(), 
                    findMethodToCall(form, request), this.getClass().getSimpleName());
        }
        
        // initialize form on initial page load and on page reload to erase any old user data
        if (StringUtils.equals(request.getParameter("init"), "true")
                || StringUtils.equals((String) request.getAttribute("methodToCallAttribute"), "methodToCall.reload.y")) {
            BatchCorrespondenceDetailForm batchCorrespondenceDetailForm = new BatchCorrespondenceDetailForm();
            ((BatchCorrespondenceDetailForm) form).setBatchCorrespondence(batchCorrespondenceDetailForm.getBatchCorrespondence());
            ((BatchCorrespondenceDetailForm) form).setNewBatchCorrespondenceDetail(batchCorrespondenceDetailForm.getNewBatchCorrespondenceDetail());
            ((BatchCorrespondenceDetailForm) form).setDeletedBatchCorrespondenceDetail(batchCorrespondenceDetailForm.getDeletedBatchCorrespondenceDetail());
            ((BatchCorrespondenceDetailForm) form).setTabStates(new HashMap<String, String>());
        }
        
        return super.execute(mapping, form, request, response);
    }
    
    /**
     * 
     * This method renders the batch correspondence details page for the selected batch 
     * correspondence when the refresh button is clicked.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return action forward
     * @throws Exception
     */
    public ActionForward start(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
            throws Exception {

        Map<String, String> fieldValues = new HashMap<String, String>();
        BatchCorrespondenceDetailForm batchCorrespondenceDetailForm = (BatchCorrespondenceDetailForm) form;
        BatchCorrespondence batchCorrespondence = batchCorrespondenceDetailForm.getBatchCorrespondence();
        fieldValues.put(BATCH_CORRESPONDENCE_TYPE_CODE, batchCorrespondence.getBatchCorrespondenceTypeCode());
        batchCorrespondence = (BatchCorrespondence) getBusinessObjectService().findByPrimaryKey(BatchCorrespondence.class, fieldValues);
        batchCorrespondenceDetailForm.setBatchCorrespondence(batchCorrespondence);
        if (batchCorrespondence != null) {
            batchCorrespondenceDetailForm.setBatchCorrespondenceTypeCode(batchCorrespondence.getBatchCorrespondenceTypeCode());
        }

        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /** 
     * 
     * This method adds a batch correspondence detail entry.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return action forward
     * @throws Exception
     */
    public ActionForward addBatchCorrespondenceDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        BatchCorrespondenceDetailForm batchCorrespondenceDetailForm = (BatchCorrespondenceDetailForm) form;
        BatchCorrespondence batchCorrespondence = batchCorrespondenceDetailForm.getBatchCorrespondence();
        BatchCorrespondenceDetail newBatchCorrespondenceDetail = batchCorrespondenceDetailForm.getNewBatchCorrespondenceDetail();

        // check any business rules
        boolean rulePassed = new BatchCorrespondenceDetailRule().processAddBatchCorrespondenceDetailRules(batchCorrespondence, 
                newBatchCorrespondenceDetail);
        if (rulePassed) {
            getBatchCorrespondenceDetailService().addBatchCorrespondenceDetail(batchCorrespondence, newBatchCorrespondenceDetail);
            batchCorrespondenceDetailForm.setNewBatchCorrespondenceDetail(new BatchCorrespondenceDetail());
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /** 
     * 
     * This method deletes a batch correspondence detail entry.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return action forward
     * @throws Exception
     */
    public ActionForward deleteBatchCorrespondenceDetail (ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        int index = getSelectedBatchCorrespondenceDetail(request);
        BatchCorrespondenceDetailForm batchCorrespondenceDetailForm = (BatchCorrespondenceDetailForm) form;
        BatchCorrespondence batchCorrespondence = batchCorrespondenceDetailForm.getBatchCorrespondence();
        BatchCorrespondenceDetail batchCorrespondenceDetail = batchCorrespondence.getBatchCorrespondenceDetails().get(index);
        
        // Add batch correspondence detail to database deletion list
        batchCorrespondenceDetailForm.getDeletedBatchCorrespondenceDetail().add(batchCorrespondenceDetail);
        
        batchCorrespondence.getBatchCorrespondenceDetails().remove(batchCorrespondenceDetail);
        batchCorrespondenceDetailForm.setNewBatchCorrespondenceDetail(new BatchCorrespondenceDetail());

        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * This method returns the index of the selected correspondence type.
     * @param request
     * @return index
     */
    protected int getSelectedBatchCorrespondenceDetail(HttpServletRequest request) {
        int index = -1;
        String parameterName = (String) request.getAttribute(KRADConstants.METHOD_TO_CALL_ATTRIBUTE);
        if (StringUtils.isNotBlank(parameterName)) {
            index = Integer.parseInt(StringUtils.substringBetween(parameterName, "batchCorrespondenceDetail[", "]"));
        }
        return index;
    }

    /**
     * 
     * This method is called when saving the correspondence templates.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return action forward
     * @throws Exception
     */
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {

        // Check modify permission
        if (!getBatchCorrespondenceDetailAuthorizationService().hasPermission(PermissionConstants.MODIFY_BATCH_CORRESPONDENCE_DETAIL)) {
            throw new AuthorizationException(GlobalVariables.getUserSession().getPerson().getPrincipalName(), 
                    findMethodToCall(form, request), this.getClass().getSimpleName());
        }
        
        BatchCorrespondenceDetailForm batchCorrespondenceDetailForm = (BatchCorrespondenceDetailForm) form;
        boolean rulePassed = new BatchCorrespondenceDetailRule()
              .processSaveBatchCorrespondenceDetailRules(batchCorrespondenceDetailForm.getBatchCorrespondence());
        if (rulePassed) {
            getBatchCorrespondenceDetailService().saveBatchCorrespondenceDetails(batchCorrespondenceDetailForm.getBatchCorrespondence(), 
                    batchCorrespondenceDetailForm.getDeletedBatchCorrespondenceDetail());
            batchCorrespondenceDetailForm.setDeletedBatchCorrespondenceDetail(new ArrayList<BatchCorrespondenceDetail>());
            KNSGlobalVariables.getMessageList().add(RiceKeyConstants.MESSAGE_SAVED);
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * 
     * This method is called when reloading the batch correspondence.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return action forward
     * @throws Exception
     */
    @Override
    public ActionForward reload(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        KNSGlobalVariables.getMessageList().add(RiceKeyConstants.MESSAGE_RELOADED);
        
        Map<String, String> fieldValues = new HashMap<String, String>();
        BatchCorrespondenceDetailForm batchCorrespondenceDetailForm = (BatchCorrespondenceDetailForm) form;
        fieldValues.put(BATCH_CORRESPONDENCE_TYPE_CODE, batchCorrespondenceDetailForm.getBatchCorrespondenceTypeCode());
        BatchCorrespondence batchCorrespondence = (BatchCorrespondence) getBusinessObjectService().findByPrimaryKey(BatchCorrespondence.class, fieldValues);
        batchCorrespondenceDetailForm.setBatchCorrespondence(batchCorrespondence);
        batchCorrespondenceDetailForm.setNewBatchCorrespondenceDetail(new BatchCorrespondenceDetail());
        batchCorrespondenceDetailForm.setDeletedBatchCorrespondenceDetail(new ArrayList<BatchCorrespondenceDetail>());
        batchCorrespondenceDetailForm.setTabStates(new HashMap<String, String>());
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * 
     * This method is called when closing the correspondence templates.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return action forward
     * @throws Exception
     */
    @Override
    public ActionForward close(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        ActionForward actionForward = mapping.findForward(KRADConstants.MAPPING_PORTAL);
        
        if (getBatchCorrespondenceDetailAuthorizationService().hasPermission(PermissionConstants.MODIFY_BATCH_CORRESPONDENCE_DETAIL)) {
            if (!StringUtils.equals(request.getParameter(KRADConstants.QUESTION_INST_ATTRIBUTE_NAME), KRADConstants.DOCUMENT_SAVE_BEFORE_CLOSE_QUESTION)) {
                // Ask question whether to save before close
                actionForward = this.performQuestionWithoutInput(mapping, form, request, response, KRADConstants.DOCUMENT_SAVE_BEFORE_CLOSE_QUESTION, 
                        getKualiConfigurationService().getPropertyValueAsString(RiceKeyConstants.QUESTION_SAVE_BEFORE_CLOSE), 
                        KRADConstants.CONFIRMATION_QUESTION, KRADConstants.MAPPING_CLOSE, "");
            } else if (StringUtils.equals(request.getParameter(KRADConstants.QUESTION_CLICKED_BUTTON), ConfirmationQuestion.YES)) {
                // Validate document
                BatchCorrespondenceDetailForm batchCorrespondenceDetailForm = (BatchCorrespondenceDetailForm) form;
                BatchCorrespondence batchCorrespondence = batchCorrespondenceDetailForm.getBatchCorrespondence();
                boolean rulePassed = new BatchCorrespondenceDetailRule().processSaveBatchCorrespondenceDetailRules(batchCorrespondence);
                if (!rulePassed) {
                    // Reload document if errors exist 
                    actionForward = mapping.findForward(RiceConstants.MAPPING_BASIC);                    
                } else {
                    // Save document
                    getBatchCorrespondenceDetailService().saveBatchCorrespondenceDetails(batchCorrespondenceDetailForm.getBatchCorrespondence(), 
                            batchCorrespondenceDetailForm.getDeletedBatchCorrespondenceDetail());
                    batchCorrespondenceDetailForm.setDeletedBatchCorrespondenceDetail(new ArrayList<BatchCorrespondenceDetail>());
                    actionForward = mapping.findForward(KRADConstants.MAPPING_PORTAL);
                }
            }
        }
        
        return actionForward;
    }
    
    /**
     * 
     * This method is called when canceling the correspondence templates.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return action forward
     * @throws Exception
     */
    @Override
    public ActionForward cancel(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        ActionForward actionForward;
        
        if (StringUtils.equals(request.getParameter(KRADConstants.QUESTION_INST_ATTRIBUTE_NAME), KRADConstants.DOCUMENT_CANCEL_QUESTION)) {
            if (StringUtils.equals(request.getParameter(KRADConstants.QUESTION_CLICKED_BUTTON), ConfirmationQuestion.YES)) {
                // Cancel document and return to portal if cancel has been confirmed
                actionForward = mapping.findForward(KRADConstants.MAPPING_PORTAL);
            } else {
                // Reload document if cancel has been aborted 
                actionForward = mapping.findForward(RiceConstants.MAPPING_BASIC);
            }
        } else {
            // Ask question to confirm cancel
            actionForward = performQuestionWithoutInput(mapping, form, request, response, KRADConstants.DOCUMENT_CANCEL_QUESTION, 
                    getKualiConfigurationService().getPropertyValueAsString("document.question.cancel.text"), KRADConstants.CONFIRMATION_QUESTION, 
                    KRADConstants.MAPPING_CANCEL, "");
        }

        return actionForward;
    }
    
    private BatchCorrespondenceDetailService getBatchCorrespondenceDetailService() {
        return (BatchCorrespondenceDetailService) KraServiceLocator.getService(BatchCorrespondenceDetailService.class);
    }

    private BatchCorrespondenceDetailAuthorizationService getBatchCorrespondenceDetailAuthorizationService() {
        return KraServiceLocator.getService(BatchCorrespondenceDetailAuthorizationService.class);
    }

}
