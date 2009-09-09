/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.irb.actions;

import static org.kuali.kra.infrastructure.KraServiceLocator.getService;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.ProtocolForm;
import org.kuali.kra.irb.actions.amendrenew.ProtocolAmendRenewService;
import org.kuali.kra.irb.actions.amendrenew.ProtocolAmendmentBean;
import org.kuali.kra.irb.actions.amendrenew.ProtocolModule;
import org.kuali.kra.irb.actions.delete.ProtocolDeleteBean;
import org.kuali.kra.irb.actions.notifyirb.ProtocolNotifyIrbBean;
import org.kuali.kra.irb.actions.request.ProtocolRequestBean;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionType;
import org.kuali.kra.irb.actions.submit.ProtocolSubmitAction;
import org.kuali.kra.irb.actions.withdraw.ProtocolWithdrawBean;
import org.kuali.kra.irb.auth.ProtocolTask;
import org.kuali.kra.rice.shim.UniversalUser;
import org.kuali.kra.service.TaskAuthorizationService;
import org.kuali.rice.kns.bo.Parameter;
import org.kuali.rice.kns.service.KualiConfigurationService;
import org.kuali.rice.kns.util.GlobalVariables;

/**
 * The form helper class for the Protocol Actions tab.
 */
@SuppressWarnings("serial")
public class ActionHelper implements Serializable {

    /**
     * Each Helper must contain a reference to its document form
     * so that it can access the document.
     */
    private ProtocolForm form;
    
    private boolean canSubmitProtocol = false;
    private String submissionConstraint;
    
    private boolean canCreateAmendment = false;
    private boolean canCreateRenewal = false;
    private boolean canNotifyIrb = false;
    private boolean canWithdraw = false;
    private boolean canRequestClose = false;
    private boolean canRequestSuspension = false;
    private boolean canRequestCloseEnrollment = false;
    private boolean canRequestReOpenEnrollment = false;
    private boolean canRequestDataAnalysis = false;
    private boolean canDeleteProtocolAmendRenew = false;
    
    private ProtocolSubmitAction protocolSubmitAction;
    private ProtocolWithdrawBean protocolWithdrawBean;
    private ProtocolRequestBean protocolCloseRequestBean;
    private ProtocolRequestBean protocolSuspendRequestBean;
    private ProtocolRequestBean protocolCloseEnrollmentRequestBean;
    private ProtocolRequestBean protocolReOpenEnrollmentRequestBean;
    private ProtocolRequestBean protocolDataAnalysisRequestBean;
    private ProtocolNotifyIrbBean protocolNotifyIrbBean;
    private ProtocolAmendmentBean protocolAmendmentBean;
    private ProtocolAmendmentBean protocolRenewAmendmentBean;
    private ProtocolDeleteBean protocolDeleteBean;
    
    /*
     * Identifies the protocol "document" to print.
     */
    private String printTag;
    
    /**
     * Constructs an ActionHelper.
     * @param form the protocol form
     * @throws  
     */
    public ActionHelper(ProtocolForm form) {
        this.form = form;
        protocolSubmitAction = new ProtocolSubmitAction(this);
        protocolWithdrawBean = new ProtocolWithdrawBean();
        protocolCloseRequestBean = new ProtocolRequestBean(ProtocolActionType.REQUEST_TO_CLOSE, 
                                                           ProtocolSubmissionType.REQUEST_TO_CLOSE);
        protocolSuspendRequestBean = new ProtocolRequestBean(ProtocolActionType.REQUEST_FOR_SUSPENSION, 
                                                             ProtocolSubmissionType.REQUEST_FOR_SUSPENSION);
        protocolCloseEnrollmentRequestBean = new ProtocolRequestBean(ProtocolActionType.REQUEST_TO_CLOSE_ENROLLMENT, 
                                                                     ProtocolSubmissionType.REQUEST_TO_CLOSE_ENROLLMENT);
        protocolReOpenEnrollmentRequestBean = new ProtocolRequestBean(ProtocolActionType.REQUEST_TO_REOPEN_ENROLLMENT, 
                                                                      ProtocolSubmissionType.REQUEST_TO_REOPEN_ENROLLMENT);
        protocolDataAnalysisRequestBean = new ProtocolRequestBean(ProtocolActionType.REQUEST_FOR_DATA_ANALYSIS_ONLY, 
                                                                  ProtocolSubmissionType.REQUEST_FOR_DATA_ANALYSIS_ONLY);
        protocolNotifyIrbBean = new ProtocolNotifyIrbBean();
        protocolAmendmentBean = createAmendmentBean();
        protocolRenewAmendmentBean = createAmendmentBean();
        protocolDeleteBean = new ProtocolDeleteBean();
    }
    
    /**
     * Create an Amendment Bean.  The modules that can be selected depends upon the
     * current outstanding amendments.  If a module is currently being modified by a
     * previous amendment, it cannot be modified by another amendment.  Once the 
     * previous amendment has completed (approved, disapproved, etc), then a new
     * amendment can modify the same module.
     * @return
     */
    private ProtocolAmendmentBean createAmendmentBean() {
        ProtocolAmendmentBean amendmentBean = new ProtocolAmendmentBean();
     
        ProtocolAmendRenewService protocolAmendRenewService = getProtocolAmendRenewService();
        List<String> moduleTypeCodes = protocolAmendRenewService.getAvailableModules(getProtocol().getProtocolNumber());
        
        for (String moduleTypeCode : moduleTypeCodes) {
            enableModuleOption(moduleTypeCode, amendmentBean);
        }
        
        return amendmentBean;
    }
    
    /**
     * Enable a module for selection by a user by setting its corresponding enabled
     * flag to true in the amendment bean.
     * @param moduleTypeCode
     * @param amendmentBean
     */
    private void enableModuleOption(String moduleTypeCode, ProtocolAmendmentBean amendmentBean) {
        if (StringUtils.equals(ProtocolModule.GENERAL_INFO, moduleTypeCode)) {
            amendmentBean.setGeneralInfoEnabled(true);
        } 
        else if (StringUtils.equals(ProtocolModule.ADD_MODIFY_ATTACHMENTS, moduleTypeCode)) {
            amendmentBean.setAddModifyAttachmentsEnabled(true);
        }
        else if (StringUtils.equals(ProtocolModule.AREAS_OF_RESEARCH, moduleTypeCode)) {
            amendmentBean.setAreasOfResearchEnabled(true);
        }
        else if (StringUtils.equals(ProtocolModule.FUNDING_SOURCE, moduleTypeCode)) {
            amendmentBean.setFundingSourceEnabled(true);
        }
        else if (StringUtils.equals(ProtocolModule.OTHERS, moduleTypeCode)) {
            amendmentBean.setOthersEnabled(true);
        }
        else if (StringUtils.equals(ProtocolModule.PROTOCOL_ORGANIZATIONS, moduleTypeCode)) {
            amendmentBean.setProtocolOrganizationsEnabled(true);
        }
        else if (StringUtils.equals(ProtocolModule.PROTOCOL_PERSONNEL, moduleTypeCode)) {
            amendmentBean.setProtocolPersonnelEnabled(true);
        }
        else if (StringUtils.equals(ProtocolModule.PROTOCOL_REFERENCES, moduleTypeCode)) {
            amendmentBean.setProtocolReferencesEnabled(true);
        }
        else if (StringUtils.equals(ProtocolModule.SPECIAL_REVIEW, moduleTypeCode)) {
            amendmentBean.setSpecialReviewEnabled(true);
        }
        else if (StringUtils.equals(ProtocolModule.SUBJECTS,moduleTypeCode)) {
            amendmentBean.setSubjectsEnabled(true);
        }
    }

    private ProtocolAmendRenewService getProtocolAmendRenewService() {
        return KraServiceLocator.getService(ProtocolAmendRenewService.class);
    }

    public void prepareView() {
        protocolSubmitAction.prepareView();
        canSubmitProtocol = hasSubmitProtocolPermission();
        submissionConstraint = getParameterValue(Constants.PARAMETER_IRB_COMM_SELECTION_DURING_SUBMISSION);
        
        canCreateAmendment = hasCreateAmendmentPermission();
        canCreateRenewal = hasCreateRenewalPermission();
        canNotifyIrb = hasNotifyIrbPermission();
        canWithdraw = hasWithdrawPermission();
        canRequestClose = hasRequestClosePermission();
        canRequestSuspension = hasRequestSuspensionPermission();
        canRequestCloseEnrollment = hasRequestCloseEnrollmentPermission();
        canRequestReOpenEnrollment = hasRequestReOpenEnrollmentPermission();
        canRequestDataAnalysis = hasRequestDataAnalysisPermission();
        canDeleteProtocolAmendRenew = hasDeleteProtocolAmendRenewPermission();
    }

    private String getParameterValue(String parameterName) {
        KualiConfigurationService configService = getService(KualiConfigurationService.class);
        Parameter param = configService.getParameterWithoutExceptions(Constants.PARAMETER_MODULE_PROTOCOL, 
                                                                      Constants.PARAMETER_COMPONENT_DOCUMENT, 
                                                                      parameterName);
        
        return param.getParameterValue();        
    }

    private Protocol getProtocol() {
        ProtocolDocument document = form.getDocument();
        if (document == null || document.getProtocol() == null) {
            throw new IllegalArgumentException("invalid (null) ProtocolDocument in ProtocolForm");
        }
        return document.getProtocol();
    }
    
    private boolean hasSubmitProtocolPermission() {
        ProtocolTask task = new ProtocolTask(TaskName.SUBMIT_PROTOCOL, getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserName(), task);
    }
    
    private boolean hasCreateAmendmentPermission() {
        ProtocolTask task = new ProtocolTask(TaskName.CREATE_PROTOCOL_AMMENDMENT, getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserName(), task);
    }
    
    private boolean hasCreateRenewalPermission() {
        ProtocolTask task = new ProtocolTask(TaskName.CREATE_PROTOCOL_RENEWAL, getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserName(), task);
    }
    
    private boolean hasNotifyIrbPermission() {
        ProtocolTask task = new ProtocolTask(TaskName.NOTIFY_IRB, getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserName(), task);
    }
    
    private boolean hasWithdrawPermission() {
        ProtocolTask task = new ProtocolTask(TaskName.PROTOCOL_WITHDRAW, getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserName(), task);
    }
    
    private boolean hasRequestClosePermission() {
        ProtocolTask task = new ProtocolTask(TaskName.PROTOCOL_REQUEST_CLOSE, getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserName(), task);
    }
    
    private boolean hasRequestSuspensionPermission() {
        ProtocolTask task = new ProtocolTask(TaskName.PROTOCOL_REQUEST_SUSPENSION, getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserName(), task);
    }
    
    private boolean hasRequestCloseEnrollmentPermission() {
        ProtocolTask task = new ProtocolTask(TaskName.PROTOCOL_REQUEST_CLOSE_ENROLLMENT, getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserName(), task);
    }
    
    private boolean hasRequestReOpenEnrollmentPermission() {
        ProtocolTask task = new ProtocolTask(TaskName.PROTOCOL_REQUEST_REOPEN_ENROLLMENT, getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserName(), task);
    }
    
    private boolean hasRequestDataAnalysisPermission() {
        ProtocolTask task = new ProtocolTask(TaskName.PROTOCOL_REQUEST_DATA_ANALYSIS, getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserName(), task);
    }
    
    private boolean hasDeleteProtocolAmendRenewPermission() {
        ProtocolTask task = new ProtocolTask(TaskName.PROTOCOL_AMEND_RENEW_DELETE, getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserName(), task);
    }
    
    private TaskAuthorizationService getTaskAuthorizationService() {
        return KraServiceLocator.getService(TaskAuthorizationService.class);
    }
    
    public ProtocolSubmitAction getProtocolSubmitAction() {
        return protocolSubmitAction;
    }

    public ProtocolForm getProtocolForm() {
        return form;
    }

    public boolean getCanSubmitProtocol() {
        return canSubmitProtocol;
    }
    
    /**
     * Get the userName of the user for the current session.
     * @return the current session's userName
     */
    private String getUserName() {
         UniversalUser user = new UniversalUser(GlobalVariables.getUserSession().getPerson());
         return user.getPersonUserIdentifier();
    }

    public String getSubmissionConstraint() {
        return submissionConstraint;
    }

    public ProtocolWithdrawBean getProtocolWithdrawBean() {
        return protocolWithdrawBean;
    }
    
    public ProtocolRequestBean getProtocolCloseRequestBean() {
        return protocolCloseRequestBean;
    }

    public ProtocolRequestBean getProtocolSuspendRequestBean() {
        return protocolSuspendRequestBean;
    }
    
    public ProtocolRequestBean getProtocolCloseEnrollmentRequestBean() {
        return protocolCloseEnrollmentRequestBean;
    }

    public ProtocolRequestBean getProtocolReOpenEnrollmentRequestBean() {
        return protocolReOpenEnrollmentRequestBean;
    }
    
    public ProtocolRequestBean getProtocolDataAnalysisRequestBean() {
        return protocolDataAnalysisRequestBean;
    }
    
    public ProtocolNotifyIrbBean getProtocolNotifyIrbBean() {
        return protocolNotifyIrbBean;
    }
    
    public ProtocolAmendmentBean getProtocolAmendmentBean() {
        return protocolAmendmentBean;
    }
    
    public ProtocolAmendmentBean getProtocolRenewAmendmentBean() {
        return protocolRenewAmendmentBean;
    }
    
    public ProtocolDeleteBean getProtocolDeleteBean() {
        return protocolDeleteBean;
    }

    public boolean getCanCreateAmendment() {
        return canCreateAmendment;
    }

    public boolean getCanCreateRenewal() {
        return canCreateRenewal;
    }
    
    public boolean getCanNotifyIrb() {
        return canNotifyIrb;
    }
    
    public boolean getCanWithdraw() {
        return canWithdraw;
    }
    
    public boolean getCanRequestClose() {
        return canRequestClose;
    }
    
    public boolean getCanRequestSuspension() {
        return canRequestSuspension;
    }
    
    public boolean getCanRequestCloseEnrollment() {
        return canRequestCloseEnrollment;
    }
    
    public boolean getCanRequestReOpenEnrollment() {
        return canRequestReOpenEnrollment;
    }
    
    public boolean getCanRequestDataAnalysis() {
        return canRequestDataAnalysis;
    }
    
    public boolean getCanDeleteProtocolAmendRenew() {
        return canDeleteProtocolAmendRenew;
    }
    
    public void setPrintTag(String printTag) {
        this.printTag = printTag;
    }
    
    public String getPrintTag() {
        return printTag;
    }
}
