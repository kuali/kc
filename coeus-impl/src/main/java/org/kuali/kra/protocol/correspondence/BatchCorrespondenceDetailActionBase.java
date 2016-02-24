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
package org.kuali.kra.protocol.correspondence;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.core.api.util.RiceConstants;
import org.kuali.rice.core.api.util.RiceKeyConstants;
import org.kuali.rice.kns.question.ConfirmationQuestion;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.kns.web.struts.action.KualiDocumentActionBase;
import org.kuali.rice.krad.exception.AuthorizationException;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Actions of the batch correspondence details.
 */
@SuppressWarnings("deprecation")
public abstract class BatchCorrespondenceDetailActionBase extends KualiDocumentActionBase {
    private static final String BATCH_CORRESPONDENCE_TYPE_CODE = "batchCorrespondenceTypeCode";

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {

        // Check and initialize permissions
        if (getBatchCorrespondenceDetailAuthorizationService().hasPermission(getModifyBatchCorrespondenceDetailPermissionNameHook())) {
            ((BatchCorrespondenceDetailFormBase) form).setReadOnly(false);
        } else if (getBatchCorrespondenceDetailAuthorizationService().hasPermission(getViewBatchCorrespondenceDetailPermissionNameHook())) {
            ((BatchCorrespondenceDetailFormBase) form).setReadOnly(true);
        } else {
            throw new AuthorizationException(GlobalVariables.getUserSession().getPerson().getPrincipalName(), 
                    findMethodToCall(form, request), this.getClass().getSimpleName());
        }
        
        // initialize form on initial page load and on page reload to erase any old user data
        if (StringUtils.equals(request.getParameter("init"), "true")
                || StringUtils.equals((String) request.getAttribute("methodToCallAttribute"), "methodToCall.reload.y")) {
            BatchCorrespondenceDetailFormBase batchCorrespondenceDetailForm = getNewBatchCorrespondenceDetailFormInstanceHook();
            ((BatchCorrespondenceDetailFormBase) form).setBatchCorrespondence(batchCorrespondenceDetailForm.getBatchCorrespondence());
            ((BatchCorrespondenceDetailFormBase) form).setNewBatchCorrespondenceDetail(batchCorrespondenceDetailForm.getNewBatchCorrespondenceDetail());
            ((BatchCorrespondenceDetailFormBase) form).setDeletedBatchCorrespondenceDetail(batchCorrespondenceDetailForm.getDeletedBatchCorrespondenceDetail());
            ((BatchCorrespondenceDetailFormBase) form).setTabStates(new HashMap<String, String>());
        }
        
        return super.execute(mapping, form, request, response);
    }   
    
    protected abstract String getViewBatchCorrespondenceDetailPermissionNameHook();

    protected abstract String getModifyBatchCorrespondenceDetailPermissionNameHook();

    protected abstract BatchCorrespondenceDetailFormBase getNewBatchCorrespondenceDetailFormInstanceHook();

    
    
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
        BatchCorrespondenceDetailFormBase batchCorrespondenceDetailForm = (BatchCorrespondenceDetailFormBase) form;
        BatchCorrespondenceBase batchCorrespondence = batchCorrespondenceDetailForm.getBatchCorrespondence();
        fieldValues.put(BATCH_CORRESPONDENCE_TYPE_CODE, batchCorrespondence.getBatchCorrespondenceTypeCode());
        batchCorrespondence = getBusinessObjectService().findByPrimaryKey(getBatchCorrespondenceClassHook(), fieldValues);
        batchCorrespondenceDetailForm.setBatchCorrespondence(batchCorrespondence);
        if (batchCorrespondence != null) {
            batchCorrespondenceDetailForm.setBatchCorrespondenceTypeCode(batchCorrespondence.getBatchCorrespondenceTypeCode());
        }

        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    protected abstract Class<? extends BatchCorrespondenceBase> getBatchCorrespondenceClassHook();

    
    
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
        BatchCorrespondenceDetailFormBase batchCorrespondenceDetailForm = (BatchCorrespondenceDetailFormBase) form;
        BatchCorrespondenceBase batchCorrespondence = batchCorrespondenceDetailForm.getBatchCorrespondence();
        BatchCorrespondenceDetailBase newBatchCorrespondenceDetail = batchCorrespondenceDetailForm.getNewBatchCorrespondenceDetail();

        // check any business rules
        boolean rulePassed = getNewInstanceOfBatchCorrespondenceDetailRuleHook().processAddBatchCorrespondenceDetailRules(batchCorrespondence, 
                newBatchCorrespondenceDetail);
        if (rulePassed) {
            getBatchCorrespondenceDetailService().addBatchCorrespondenceDetail(batchCorrespondence, newBatchCorrespondenceDetail);
            batchCorrespondenceDetailForm.setNewBatchCorrespondenceDetail(getNewBatchCorrespondenceDetailInstanceHook());
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    protected abstract BatchCorrespondenceDetailRuleBase getNewInstanceOfBatchCorrespondenceDetailRuleHook();

    protected abstract BatchCorrespondenceDetailBase getNewBatchCorrespondenceDetailInstanceHook();
    
    
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
        BatchCorrespondenceDetailFormBase batchCorrespondenceDetailForm = (BatchCorrespondenceDetailFormBase) form;
        BatchCorrespondenceBase batchCorrespondence = batchCorrespondenceDetailForm.getBatchCorrespondence();
        BatchCorrespondenceDetailBase batchCorrespondenceDetail = batchCorrespondence.getBatchCorrespondenceDetails().get(index);
        
        // Add batch correspondence detail to database deletion list
        batchCorrespondenceDetailForm.getDeletedBatchCorrespondenceDetail().add(batchCorrespondenceDetail);
        
        batchCorrespondence.getBatchCorrespondenceDetails().remove(batchCorrespondenceDetail);
        batchCorrespondenceDetailForm.setNewBatchCorrespondenceDetail(getNewBatchCorrespondenceDetailInstanceHook());

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
        if (!getBatchCorrespondenceDetailAuthorizationService().hasPermission(getModifyBatchCorrespondenceDetailPermissionNameHook())) {
            throw new AuthorizationException(GlobalVariables.getUserSession().getPerson().getPrincipalName(), 
                    findMethodToCall(form, request), this.getClass().getSimpleName());
        }
        
        BatchCorrespondenceDetailFormBase batchCorrespondenceDetailForm = (BatchCorrespondenceDetailFormBase) form;
        boolean rulePassed = getNewInstanceOfBatchCorrespondenceDetailRuleHook()
              .processSaveBatchCorrespondenceDetailRules(batchCorrespondenceDetailForm.getBatchCorrespondence());
        if (rulePassed) {
            getBatchCorrespondenceDetailService().saveBatchCorrespondenceDetails(batchCorrespondenceDetailForm.getBatchCorrespondence(), 
                    batchCorrespondenceDetailForm.getDeletedBatchCorrespondenceDetail());
            batchCorrespondenceDetailForm.setDeletedBatchCorrespondenceDetail(new ArrayList<BatchCorrespondenceDetailBase>());
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
        BatchCorrespondenceDetailFormBase batchCorrespondenceDetailForm = (BatchCorrespondenceDetailFormBase) form;
        fieldValues.put(BATCH_CORRESPONDENCE_TYPE_CODE, batchCorrespondenceDetailForm.getBatchCorrespondenceTypeCode());
        BatchCorrespondenceBase batchCorrespondence = getBusinessObjectService().findByPrimaryKey(getBatchCorrespondenceClassHook(), fieldValues);
        batchCorrespondenceDetailForm.setBatchCorrespondence(batchCorrespondence);
        batchCorrespondenceDetailForm.setNewBatchCorrespondenceDetail(getNewBatchCorrespondenceDetailInstanceHook());
        batchCorrespondenceDetailForm.setDeletedBatchCorrespondenceDetail(new ArrayList<BatchCorrespondenceDetailBase>());
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
        
        if (getBatchCorrespondenceDetailAuthorizationService().hasPermission(getModifyBatchCorrespondenceDetailPermissionNameHook())) {
            if (!StringUtils.equals(request.getParameter(KRADConstants.QUESTION_INST_ATTRIBUTE_NAME), KRADConstants.DOCUMENT_SAVE_BEFORE_CLOSE_QUESTION)) {
                // Ask question whether to save before close
                actionForward = this.performQuestionWithoutInput(mapping, form, request, response, KRADConstants.DOCUMENT_SAVE_BEFORE_CLOSE_QUESTION, 
                        getKualiConfigurationService().getPropertyValueAsString(RiceKeyConstants.QUESTION_SAVE_BEFORE_CLOSE), 
                        KRADConstants.CONFIRMATION_QUESTION, KRADConstants.MAPPING_CLOSE, "");
            } else if (StringUtils.equals(request.getParameter(KRADConstants.QUESTION_CLICKED_BUTTON), ConfirmationQuestion.YES)) {
                // Validate document
                BatchCorrespondenceDetailFormBase batchCorrespondenceDetailForm = (BatchCorrespondenceDetailFormBase) form;
                BatchCorrespondenceBase batchCorrespondence = batchCorrespondenceDetailForm.getBatchCorrespondence();
                boolean rulePassed = getNewInstanceOfBatchCorrespondenceDetailRuleHook().processSaveBatchCorrespondenceDetailRules(batchCorrespondence);
                if (!rulePassed) {
                    // Reload document if errors exist 
                    actionForward = mapping.findForward(RiceConstants.MAPPING_BASIC);                    
                } else {
                    // Save document
                    getBatchCorrespondenceDetailService().saveBatchCorrespondenceDetails(batchCorrespondenceDetailForm.getBatchCorrespondence(), 
                            batchCorrespondenceDetailForm.getDeletedBatchCorrespondenceDetail());
                    batchCorrespondenceDetailForm.setDeletedBatchCorrespondenceDetail(new ArrayList<BatchCorrespondenceDetailBase>());
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
        return KcServiceLocator.getService(getBatchCorrespondenceDetailServiceClassHook());
    }

    protected abstract Class<? extends BatchCorrespondenceDetailService> getBatchCorrespondenceDetailServiceClassHook();

    private BatchCorrespondenceDetailAuthorizationService getBatchCorrespondenceDetailAuthorizationService() {
        return KcServiceLocator.getService(getBatchCorrespondenceDetailAuthorizationServiceClassHook());
    }

    protected abstract Class<? extends BatchCorrespondenceDetailAuthorizationService> getBatchCorrespondenceDetailAuthorizationServiceClassHook();

}
