/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.kra.irb;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.module.CoeusModule;
import org.kuali.coeus.common.framework.module.CoeusSubModule;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.notification.impl.NotificationHelper;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.authorization.KraAuthorizationConstants;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.irb.actions.ActionHelper;
import org.kuali.kra.irb.actions.ProtocolStatus;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.customdata.CustomDataHelper;
import org.kuali.kra.irb.noteattachment.NotesAttachmentsHelper;
import org.kuali.kra.irb.notification.IRBNotificationContext;
import org.kuali.kra.irb.onlinereview.OnlineReviewsActionHelper;
import org.kuali.kra.irb.onlinereview.ProtocolOnlineReviewService;
import org.kuali.kra.irb.permission.PermissionsHelper;
import org.kuali.kra.irb.personnel.PersonnelHelper;
import org.kuali.kra.irb.protocol.ProtocolHelper;
import org.kuali.kra.irb.protocol.reference.ProtocolReferenceBean;
import org.kuali.kra.irb.questionnaire.ProtocolModuleQuestionnaireBean;
import org.kuali.kra.irb.questionnaire.QuestionnaireHelper;
import org.kuali.kra.irb.specialreview.SpecialReviewHelper;
import org.kuali.kra.protocol.ProtocolFormBase;
import org.kuali.kra.protocol.actions.ActionHelperBase;
import org.kuali.kra.protocol.customdata.ProtocolCustomDataHelperBase;
import org.kuali.kra.protocol.noteattachment.NotesAttachmentsHelperBase;
import org.kuali.kra.protocol.notification.ProtocolNotificationContextBase;
import org.kuali.kra.protocol.onlinereview.OnlineReviewsActionHelperBase;
import org.kuali.kra.protocol.permission.PermissionsHelperBase;
import org.kuali.kra.protocol.personnel.PersonnelHelperBase;
import org.kuali.kra.protocol.protocol.ProtocolHelperBase;
import org.kuali.kra.protocol.protocol.reference.ProtocolReferenceBeanBase;
import org.kuali.kra.protocol.questionnaire.QuestionnaireHelperBase;
import org.kuali.kra.protocol.specialreview.ProtocolSpecialReviewHelperBase;
import org.kuali.coeus.common.questionnaire.framework.answer.ModuleQuestionnaireBean;
import org.kuali.coeus.common.questionnaire.framework.answer.QuestionnaireAnswerService;
import org.kuali.rice.core.api.CoreApiServiceLocator;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.kns.datadictionary.HeaderNavigation;
import org.kuali.rice.kns.util.ActionFormUtilMap;
import org.kuali.rice.kns.web.ui.HeaderField;
import org.kuali.rice.krad.util.GlobalVariables;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("deprecation")
public class ProtocolForm extends ProtocolFormBase {
    
    private static final long serialVersionUID = 3736148760520952504L;
    
    /**
     * When true, the online review header will not be displayed when it is disabled.
     */
    private static final boolean HIDE_ONLINE_REVIEW_WHEN_DISABLED = true;
    private static final String ONLINE_REVIEW_NAV_TO = "onlineReview";
    private static final String CUSTOM_DATA_NAV_TO = "customData";
    
    private boolean reinitializeModifySubmissionFields = true;
    
    public ProtocolForm() throws Exception {
        super();
    }
  
    
    
    @Override
    protected String getDefaultDocumentTypeName() {
        return "ProtocolDocument";
    }

    /**
     * @see org.kuali.rice.kns.web.struts.form.KualiForm#getHeaderNavigationTabs()
     * 
     * We only enable the Online Review tab if the protocol is in a state to be reviewed and
     * the user has the IRB Admin role or the user has an Online Review. 
     * 
     * If HIDE_ONLINE_REVIEW_WHEN_DISABLED is true, then the tab will be removed from the tabs 
     * List if it is disabled.
     * 
     */
    @SuppressWarnings("deprecation")
    @Override
    public HeaderNavigation[] getHeaderNavigationTabs() {
        
        HeaderNavigation[] navigation = super.getHeaderNavigationTabs();
        
        ProtocolOnlineReviewService onlineReviewService = getProtocolOnlineReviewService();
        List<HeaderNavigation> resultList = new ArrayList<HeaderNavigation>();
        boolean onlineReviewTabEnabled = false;

        if (getProtocolDocument() != null && getProtocolDocument().getProtocol() != null) {
            String principalId = GlobalVariables.getUserSession().getPrincipalId();
            ProtocolSubmission submission = (ProtocolSubmission) getProtocolDocument().getProtocol().getProtocolSubmission();
            boolean isUserOnlineReviewer = onlineReviewService.isProtocolReviewer(principalId, false, submission);
            boolean isUserIrbAdmin = getSystemAuthorizationService().hasRole(GlobalVariables.getUserSession().getPrincipalId(), "KC-UNT", "IRB Administrator");
            onlineReviewTabEnabled = (isUserOnlineReviewer || isUserIrbAdmin) 
                    && onlineReviewService.isProtocolInStateToBeReviewed((Protocol) getProtocolDocument().getProtocol());
        }
        
            //We have to copy the HeaderNavigation elements into a new collection as the 
            //List returned by DD is it's cached copy of the header navigation list.
        for (HeaderNavigation nav : navigation) {
            if (StringUtils.equals(nav.getHeaderTabNavigateTo(),ONLINE_REVIEW_NAV_TO)) {
                nav.setDisabled(!onlineReviewTabEnabled);
                if (onlineReviewTabEnabled || ((!onlineReviewTabEnabled) && (!HIDE_ONLINE_REVIEW_WHEN_DISABLED))) {
                    resultList.add(nav);
                }
            } else if (StringUtils.equals(nav.getHeaderTabNavigateTo(),CUSTOM_DATA_NAV_TO)) {                
                boolean displayTab = this.getCustomDataHelper().canDisplayCustomDataTab();
                nav.setDisabled(!displayTab);
                if (displayTab) {
                    resultList.add(nav);
                }
            } else {
                resultList.add(nav);
            }
        }
        
        HeaderNavigation[] result = new HeaderNavigation[resultList.size()];
        resultList.toArray(result);
        return result;
    }
    
    /**
     * 
     * This method is a wrapper method for getting ProtocolOnlineReviewerService service.
     * @return
     */
    protected ProtocolOnlineReviewService getProtocolOnlineReviewService() {
        return KcServiceLocator.getService(ProtocolOnlineReviewService.class);
    }

    
    
    @SuppressWarnings("deprecation")
    @Override
    public void populate(HttpServletRequest request) { 
        initAnswerList(request);
        super.populate(request);
        
        // Temporary hack for KRACOEUS-489
        if (getActionFormUtilMap() instanceof ActionFormUtilMap) {
            ((ActionFormUtilMap) getActionFormUtilMap()).clear();
        }
    }
    
    /*
     * For submission questionnaire, it is a popup and not a session document.
     * so, it has to be retrieved, then populate with the new data.
     */
    private void initAnswerList(HttpServletRequest request) {
        
        String protocolNumber = request.getParameter("questionnaireHelper.protocolNumber");
        String submissionNumber = request.getParameter("questionnaireHelper.submissionNumber");
        if (StringUtils.isNotBlank(protocolNumber) && protocolNumber.endsWith("T")) {
            ModuleQuestionnaireBean moduleQuestionnaireBean = new ProtocolModuleQuestionnaireBean(CoeusModule.IRB_MODULE_CODE, protocolNumber, CoeusSubModule.PROTOCOL_SUBMISSION, submissionNumber, false);
            this.getQuestionnaireHelper().setAnswerHeaders(
                    getQuestionnaireAnswerService().getQuestionnaireAnswer(moduleQuestionnaireBean));
        }
    }

    private QuestionnaireAnswerService getQuestionnaireAnswerService() {
        return KcServiceLocator.getService(QuestionnaireAnswerService.class);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void populateHeaderFields(WorkflowDocument workflowDocument) {
        super.populateHeaderFields(workflowDocument);
        ProtocolDocument pd = (ProtocolDocument) getProtocolDocument();
        
        HeaderField documentNumber = getDocInfo().get(0);
        documentNumber.setDdAttributeEntryName("DataDictionary.ProtocolDocument.attributes.documentNumber");
        
        ProtocolStatus protocolStatus = (ProtocolStatus) ((pd == null) ? null : pd.getProtocol().getProtocolStatus());
        HeaderField docStatus = new HeaderField("DataDictionary.AttributeReference.attributes.workflowDocumentStatus", protocolStatus == null? "" : protocolStatus.getDescription());
        getDocInfo().set(1, docStatus);
        
        String lastUpdatedDateStr = null;
        if(pd != null && pd.getUpdateTimestamp() != null) {
            lastUpdatedDateStr = CoreApiServiceLocator.getDateTimeService().toString(pd.getUpdateTimestamp(), "hh:mm a MM/dd/yyyy");
        }
        
        if(getDocInfo().size() > 2) {
            KcPerson initiator = getKcPersonService().getKcPersonByPersonId(pd.getDocumentHeader().getWorkflowDocument().getInitiatorPrincipalId());
            String modifiedInitiatorFieldStr = initiator == null ? "" : initiator.getUserName();
            if(StringUtils.isNotBlank(lastUpdatedDateStr)) {
                modifiedInitiatorFieldStr += (" : " + lastUpdatedDateStr);
            }
            getDocInfo().set(2, new HeaderField("DataDictionary.Protocol.attributes.initiatorLastUpdated", modifiedInitiatorFieldStr));
        }
        
        String protocolSubmissionStatusStr = null;
        if(pd != null && pd.getProtocol() != null && pd.getProtocol().getProtocolSubmission() != null) {
            pd.getProtocol().getProtocolSubmission().refreshReferenceObject("submissionStatus");
            protocolSubmissionStatusStr = pd.getProtocol().getProtocolSubmission().getSubmissionStatus().getDescription();
        }
        HeaderField protocolSubmissionStatus = new HeaderField("DataDictionary.Protocol.attributes.protocolSubmissionStatus", protocolSubmissionStatusStr);
        getDocInfo().set(3, protocolSubmissionStatus);
        
        getDocInfo().add(new HeaderField("DataDictionary.Protocol.attributes.protocolNumber", (pd == null) ? null : pd.getProtocol().getProtocolNumber()));

        String expirationDateStr = null;
        if(pd != null && pd.getProtocol().getExpirationDate() != null) {
            expirationDateStr = CoreApiServiceLocator.getDateTimeService().toString(pd.getProtocol().getExpirationDate(), "MM/dd/yyyy");
        }
        
        HeaderField expirationDate = new HeaderField("DataDictionary.Protocol.attributes.expirationDate", expirationDateStr);
        getDocInfo().add(expirationDate);
    }

    public ProtocolHelper getProtocolHelper() {
        return (ProtocolHelper) super.getProtocolHelper();
    }
    
    public PersonnelHelper getPersonnelHelper() {
        return (PersonnelHelper) super.getPersonnelHelper();
    }
    
    public PermissionsHelper getPermissionsHelper() {
        return (PermissionsHelper) super.getPermissionsHelper();
    }
    
    public ProtocolReferenceBean getNewProtocolReferenceBean() {
        return (ProtocolReferenceBean) super.getNewProtocolReferenceBean();
    }
    
    @Override
    protected String getLockRegion() {
        return KraAuthorizationConstants.LOCK_DESCRIPTOR_PROTOCOL;
    }
    
    @Override
    public String getActionName() {
        return "protocol";
    } 

    /**
     * Gets the Notes &amp; Attachments Helper.
     * @return Notes &amp; Attachments Helper
     */
    public NotesAttachmentsHelper getNotesAttachmentsHelper() {
        return (NotesAttachmentsHelper) super.getNotesAttachmentsHelper();
    }

    public ActionHelper getActionHelper() {
        return (ActionHelper) super.getActionHelper();
    }

    public ProtocolDocument getProtocolDocument() {
        return (ProtocolDocument) super.getProtocolDocument();
    }

    @Override
    public boolean isPropertyEditable(String propertyName) {
        if (propertyName.startsWith("actionHelper.protocolSubmitAction.reviewer") ||
                propertyName.startsWith("methodToCall.printSubmissionQuestionnaireAnswer.line")
                || propertyName.startsWith("methodToCall.saveCorrespondence")
                || propertyName.startsWith("methodToCall.closeCorrespondence")
                || propertyName.startsWith("methodToCall.viewCorrespondence")) {
            return true;
        } else {
            return super.isPropertyEditable(propertyName);
        }
    }
   
    /**
     * 
     * This method returns true if the risk level panel should be displayed.
     * @return
     */
    public boolean getDisplayRiskLevelPanel() {
        return ((Protocol) this.getProtocolDocument().getProtocol()).getProtocolRiskLevels() != null 
            && ((Protocol) this.getProtocolDocument().getProtocol()).getProtocolRiskLevels().size() > 0;
        
    }
    
    public String getModuleCode() {
        return CoeusModule.IRB_MODULE_CODE;
    }



    @Override
    protected NotificationHelper<? extends ProtocolNotificationContextBase> getNotificationHelperHook() {
        return new NotificationHelper<IRBNotificationContext>();
    }



    @Override
    protected ProtocolReferenceBeanBase createNewProtocolReferenceBeanInstance() {
        return new ProtocolReferenceBean();
    }



    @Override
    protected QuestionnaireHelperBase createNewQuestionnaireHelperInstanceHook(ProtocolFormBase protocolForm) {
        return new QuestionnaireHelper((ProtocolForm) protocolForm);
    }



    @Override
    protected ActionHelperBase createNewActionHelperInstanceHook(ProtocolFormBase protocolForm, boolean initializeActions) throws Exception {
    	ActionHelper actionHelper = new ActionHelper((ProtocolForm) protocolForm);
    	if(initializeActions) {
    		actionHelper.initializeProtocolActions();
    	}
    	return actionHelper;
    }



    @Override
    protected ProtocolSpecialReviewHelperBase createNewSpecialReviewHelperInstanceHook(ProtocolFormBase protocolForm) {
        return new SpecialReviewHelper((ProtocolForm) protocolForm);
    }



    @SuppressWarnings("rawtypes")
    @Override
    protected ProtocolCustomDataHelperBase createNewCustomDataHelperInstanceHook(ProtocolFormBase protocolForm) {
        return new CustomDataHelper((ProtocolForm) protocolForm);
    }



    @Override
    protected OnlineReviewsActionHelperBase createNewOnlineReviewsActionHelperInstanceHook(ProtocolFormBase protocolForm) {
        return new OnlineReviewsActionHelper((ProtocolForm) protocolForm);
    }



    @Override
    protected ProtocolHelperBase createNewProtocolHelperInstanceHook(ProtocolFormBase protocolForm) {
        return new ProtocolHelper((ProtocolForm) protocolForm);
    }



    @Override
    protected PermissionsHelperBase createNewPermissionsHelperInstanceHook(ProtocolFormBase protocolForm) {
        return new PermissionsHelper((ProtocolForm) protocolForm);
    }



    @Override
    protected PersonnelHelperBase createNewPersonnelHelperInstanceHook(ProtocolFormBase protocolForm) {
        return new PersonnelHelper((ProtocolForm) protocolForm);
    }



    @Override
    protected QuestionnaireHelperBase createNewQuestionnaireHelper(ProtocolFormBase protocolForm) {
        return new QuestionnaireHelper(protocolForm);
    }



    @Override
    protected NotesAttachmentsHelperBase createNewNotesAttachmentsHelperInstanceHook(ProtocolFormBase protocolForm) {
        return new NotesAttachmentsHelper((ProtocolForm) protocolForm);
    }



    @Override
    protected List<String> getTerminalNodeNamesHook() {
        List<String> retVal = new ArrayList<String>();
        retVal.add(Constants.PROTOCOL_IRBREVIEW_ROUTE_NODE_NAME);
        return retVal;
    }
    
    public boolean isReinitializeModifySubmissionFields() {
        return reinitializeModifySubmissionFields;
    }

    public void setReinitializeModifySubmissionFields(boolean reinitializeModifySubmissionFields) {
        this.reinitializeModifySubmissionFields = reinitializeModifySubmissionFields;
    }

    public String getShortUrl() {
        return getBaseShortUrl() + "/kc-common/irb-protocols/" + getProtocolDocument().getProtocol().getProtocolNumber();
    }

    protected ConfigurationService getConfigurationService() {
        return KcServiceLocator.getService(ConfigurationService.class);
    }
    
}
