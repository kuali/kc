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
package org.kuali.kra.iacuc;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.authorization.KraAuthorizationConstants;
import org.kuali.kra.bo.CoeusModule;
import org.kuali.kra.common.notification.web.struts.form.NotificationHelper;
import org.kuali.kra.iacuc.actions.IacucActionHelper;
import org.kuali.kra.iacuc.customdata.IacucProtocolCustomDataHelper;
import org.kuali.kra.iacuc.noteattachment.IacucNotesAttachmentsHelper;
import org.kuali.kra.iacuc.notification.IacucProtocolNotificationContext;
import org.kuali.kra.iacuc.onlinereview.IacucOnlineReviewsActionHelper;
import org.kuali.kra.iacuc.onlinereview.IacucProtocolOnlineReviewService;
import org.kuali.kra.iacuc.permission.IacucPermissionsHelper;
import org.kuali.kra.iacuc.personnel.IacucPersonnelHelper;
import org.kuali.kra.iacuc.procedures.IacucProtocolProcedureService;
import org.kuali.kra.iacuc.procedures.IacucProtocolProceduresHelper;
import org.kuali.kra.iacuc.protocol.IacucProtocolHelper;
import org.kuali.kra.iacuc.protocol.reference.IacucProtocolReferenceBean;
import org.kuali.kra.iacuc.questionnaire.IacucQuestionnaireHelper;
import org.kuali.kra.iacuc.specialreview.IacucProtocolSpecialReviewHelper;
import org.kuali.kra.iacuc.species.IacucProtocolSpeciesHelper;
import org.kuali.kra.iacuc.species.exception.IacucProtocolExceptionHelper;
import org.kuali.kra.iacuc.threers.IacucAlternateSearchHelper;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.protocol.ProtocolForm;
import org.kuali.kra.protocol.actions.ActionHelper;
import org.kuali.kra.protocol.actions.ProtocolStatus;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmission;
import org.kuali.kra.protocol.customdata.ProtocolCustomDataHelper;
import org.kuali.kra.protocol.onlinereview.OnlineReviewsActionHelper;
import org.kuali.kra.protocol.onlinereview.ProtocolOnlineReviewService;
import org.kuali.kra.protocol.protocol.ProtocolHelper;
import org.kuali.kra.protocol.protocol.reference.ProtocolReferenceBean;
import org.kuali.kra.protocol.questionnaire.QuestionnaireHelper;
import org.kuali.kra.protocol.specialreview.ProtocolSpecialReviewHelper;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.kns.datadictionary.HeaderNavigation;
import org.kuali.rice.kns.util.ActionFormUtilMap;
import org.kuali.rice.kns.web.ui.HeaderField;
import org.kuali.rice.krad.util.GlobalVariables;

/**
 * This class...
 */
public class IacucProtocolForm extends ProtocolForm {
    
    private static final long serialVersionUID = -535557943052220820L;
    private IacucProtocolSpeciesHelper iacucProtocolSpeciesHelper;
    private IacucAlternateSearchHelper iacucAlternateSearchHelper;
    private IacucProtocolExceptionHelper iacucProtocolExceptionHelper;
    private IacucProtocolProceduresHelper iacucProtocolProceduresHelper;
    private boolean defaultOpenCopyTab = false;
    
    private boolean reinitializeModifySubmissionFields = true;
    
    public IacucProtocolForm() throws Exception {
        super();
        initializeIacucProtocolHelpers();
        initializeIacucProtocolSpecies();
        initializeIacucAlternateSearchHelper();
        initializeIacucProtocolException();
        initializeIacucProtocolProcedures();
    }

    public void initializeIacucProtocolHelpers() throws Exception {
    }
    
    public void initializeIacucProtocolSpecies() throws Exception {
        setIacucProtocolSpeciesHelper(new IacucProtocolSpeciesHelper(this));
    }
    
    public void initializeIacucProtocolProcedures() throws Exception {
        setIacucProtocolProceduresHelper(new IacucProtocolProceduresHelper(this));
    }

    public void initializeIacucProtocolException() throws Exception {
        setIacucProtocolExceptionHelper(new IacucProtocolExceptionHelper(this));
    }

    protected void initializeIacucAlternateSearchHelper() throws Exception {
        setIacucAlternateSearchHelper(new IacucAlternateSearchHelper(this));
    }
    
    @Override
    public String getActionName() {
        return "iacucProtocol";
    }

    /** {@inheritDoc} */
    @Override
    protected String getDefaultDocumentTypeName() {
        return "IacucProtocolDocument";
    }


    /**
     * Gets a {@link IacucProtocolDocument ProtocolDocument}.
     * @return {@link IacucProtocolDocument ProtocolDocument}
     */
    public IacucProtocolDocument getIacucProtocolDocument() {
        return (IacucProtocolDocument) super.getProtocolDocument();
    }


    @Override
    protected String getLockRegion() {
        // TODO Auto-generated method stub
        return KraAuthorizationConstants.LOCK_DESCRIPTOR_IACUC_PROTOCOL;
    }

    public IacucProtocolHelper getProtocolHelper() {
        return (IacucProtocolHelper)super.getProtocolHelper();
    }

    @Override
    protected ProtocolHelper createNewProtocolHelperInstanceHook(ProtocolForm protocolForm) {
        return new IacucProtocolHelper((IacucProtocolForm) protocolForm);
    }
    
    
    public IacucPermissionsHelper getPermissionsHelper(ProtocolForm protocolForm) {
        return (IacucPermissionsHelper)super.getPermissionsHelper();
    }
    
    @Override
    protected IacucPermissionsHelper createNewPermissionsHelperInstanceHook(ProtocolForm protocolForm) {
        return new IacucPermissionsHelper((IacucProtocolForm) protocolForm);
    }
    
    public IacucPersonnelHelper getPersonnelHelper(ProtocolForm protocolForm) {
        return (IacucPersonnelHelper)super.getPersonnelHelper();
    }
    
    @Override
    protected IacucPersonnelHelper createNewPersonnelHelperInstanceHook(ProtocolForm protocolForm) {
        return new IacucPersonnelHelper((IacucProtocolForm)protocolForm);
    }
    
    public IacucNotesAttachmentsHelper getNotesAttachmentHelper(ProtocolForm form) {
        return (IacucNotesAttachmentsHelper)super.getNotesAttachmentsHelper();
    }
    
    @Override
    protected IacucNotesAttachmentsHelper createNewNotesAttachmentsHelperInstanceHook(ProtocolForm protocolForm) {
        return new IacucNotesAttachmentsHelper((IacucProtocolForm)protocolForm);
    }
    
    protected QuestionnaireHelper createNewQuestionnaireHelper(ProtocolForm form) {
        return new IacucQuestionnaireHelper(form);
    }

    protected IacucActionHelper createNewActionHelper(ProtocolForm protocolForm) throws Exception {
        return new IacucActionHelper(protocolForm);
    }

    @Override
    public String getModuleCode() {
        return CoeusModule.IACUC_PROTOCOL_MODULE_CODE;
    }

    public IacucProtocolSpeciesHelper getIacucProtocolSpeciesHelper() {
        return iacucProtocolSpeciesHelper;
    }

    public void setIacucProtocolSpeciesHelper(IacucProtocolSpeciesHelper iacucProtocolSpeciesHelper) {
        this.iacucProtocolSpeciesHelper = iacucProtocolSpeciesHelper;
    }

    public IacucAlternateSearchHelper getIacucAlternateSearchHelper() {
        return iacucAlternateSearchHelper;
    }

    public void setIacucAlternateSearchHelper(IacucAlternateSearchHelper iacucAlternateSearchHelper) {
        this.iacucAlternateSearchHelper = iacucAlternateSearchHelper;
    }

    @Override
    protected ProtocolReferenceBean createNewProtocolReferenceBeanInstance() {
        return new IacucProtocolReferenceBean();
    }

    public IacucProtocolExceptionHelper getIacucProtocolExceptionHelper() {
        return iacucProtocolExceptionHelper;
    }

    public void setIacucProtocolExceptionHelper(IacucProtocolExceptionHelper iacucProtocolExceptionHelper) {
        this.iacucProtocolExceptionHelper = iacucProtocolExceptionHelper;
    }
    
    @Override
    public void populate(HttpServletRequest request) { 
        super.populate(request);
        
        // Temporary hack for KRACOEUS-489
        if (getActionFormUtilMap() instanceof ActionFormUtilMap) {
            ((ActionFormUtilMap) getActionFormUtilMap()).clear();
        }
        getIacucProtocolDocument().getIacucProtocol().setIacucProtocolStudyGroupBeans(getIacucProtocolProcedureService().getRevisedStudyGroupBeans(getIacucProtocolDocument().getIacucProtocol(), 
                getIacucProtocolProceduresHelper().getAllProcedures()));

    }
    
    @Override
    public void populateHeaderFields(WorkflowDocument workflowDocument) {
        super.populateHeaderFields(workflowDocument);
        IacucProtocolDocument pd = getIacucProtocolDocument();
        
        HeaderField documentNumber = getDocInfo().get(0);
        documentNumber.setDdAttributeEntryName("DataDictionary.IacucProtocolDocument.attributes.documentNumber");
        
        ProtocolStatus protocolStatus = (pd == null) ? null : pd.getIacucProtocol().getProtocolStatus();
        HeaderField docStatus = new HeaderField("DataDictionary.AttributeReferenceDummy.attributes.workflowDocumentStatus", protocolStatus == null? "" : protocolStatus.getDescription());
        getDocInfo().set(1, docStatus);
        
        String lastUpdatedDateStr = null;
        if(pd != null && pd.getUpdateTimestamp() != null) {
            lastUpdatedDateStr = getFormattedDateTime(pd.getUpdateTimestamp());
        }
        
        if(getDocInfo().size() > 2) {
            HeaderField initiatorField = getDocInfo().get(2);
            String modifiedInitiatorFieldStr = initiatorField.getDisplayValue();
            if(StringUtils.isNotBlank(lastUpdatedDateStr)) {
                modifiedInitiatorFieldStr = modifiedInitiatorFieldStr + " : " + lastUpdatedDateStr;
            }
            getDocInfo().set(2, new HeaderField("DataDictionary.IacucProtocol.attributes.initiatorLastUpdated", modifiedInitiatorFieldStr));
        }
        
        String protocolSubmissionStatusStr = null;
        if(pd != null && pd.getIacucProtocol() != null && pd.getIacucProtocol().getProtocolSubmission() != null) {
            pd.getIacucProtocol().getProtocolSubmission().refreshReferenceObject("submissionStatus");
            protocolSubmissionStatusStr = pd.getIacucProtocol().getProtocolSubmission().getSubmissionStatus().getDescription();
        }
        HeaderField protocolSubmissionStatus = new HeaderField("DataDictionary.IacucProtocol.attributes.protocolSubmissionStatus", protocolSubmissionStatusStr);
        getDocInfo().set(3, protocolSubmissionStatus);
        
        getDocInfo().add(new HeaderField("DataDictionary.IacucProtocol.attributes.protocolNumber", (pd == null) ? null : pd.getIacucProtocol().getProtocolNumber()));

        String expirationDateStr = null;
        if(pd != null && pd.getProtocol().getExpirationDate() != null) {
            expirationDateStr = getFormattedDate(pd.getIacucProtocol().getExpirationDate()); 
        }
        
        HeaderField expirationDate = new HeaderField("DataDictionary.IacucProtocol.attributes.expirationDate", expirationDateStr);
        getDocInfo().add(expirationDate);
    }
    
    @Override
    public HeaderNavigation[] getHeaderNavigationTabs() {
        
        HeaderNavigation[] navigation = super.getHeaderNavigationTabs();
        
        ProtocolOnlineReviewService onlineReviewService = getProtocolOnlineReviewService();
        List<HeaderNavigation> resultList = new ArrayList<HeaderNavigation>();
        boolean onlineReviewTabEnabled = false;

        if (getProtocolDocument() != null && getProtocolDocument().getProtocol() != null) {
            String principalId = GlobalVariables.getUserSession().getPrincipalId();
            ProtocolSubmission submission = getProtocolDocument().getProtocol().getProtocolSubmission();
            boolean isUserOnlineReviewer = onlineReviewService.isProtocolReviewer(principalId, false, submission);
            boolean isUserIacucAdmin = getKraAuthorizationService().hasRole(GlobalVariables.getUserSession().getPrincipalId(), "KC-UNT", "IACUC Administrator"); 
            onlineReviewTabEnabled = (isUserOnlineReviewer || isUserIacucAdmin) 
                    && onlineReviewService.isProtocolInStateToBeReviewed(getProtocolDocument().getProtocol());
        }
        
            //We have to copy the HeaderNavigation elements into a new collection as the 
            //List returned by DD is it's cached copy of the header navigation list.
        for (HeaderNavigation nav : navigation) {
            if (StringUtils.equals(nav.getHeaderTabNavigateTo(),ONLINE_REVIEW_NAV_TO)) {
                nav.setDisabled(!onlineReviewTabEnabled);
                if (onlineReviewTabEnabled || ((!onlineReviewTabEnabled) && (!HIDE_ONLINE_REVIEW_WHEN_DISABLED))) {
                    resultList.add(nav);
                }
//            } else if (StringUtils.equals(nav.getHeaderTabNavigateTo(),CUSTOM_DATA_NAV_TO)) {
//                boolean displayTab = this.getCustomDataHelper().canDisplayCustomDataTab();
//                nav.setDisabled(!displayTab);
//                if (displayTab) {
//                    resultList.add(nav);
//                }
            } else {
                resultList.add(nav);
            }
        }
        
        HeaderNavigation[] result = new HeaderNavigation[resultList.size()];
        resultList.toArray(result);
        return result;
    }

    
   protected ProtocolOnlineReviewService getProtocolOnlineReviewService() {
       return KraServiceLocator.getService(IacucProtocolOnlineReviewService.class);
   }

  
    @Override
    protected QuestionnaireHelper createNewQuestionnaireHelperInstanceHook(ProtocolForm protocolForm) {
        return new IacucQuestionnaireHelper((IacucProtocolForm) protocolForm);
    }
    
    @Override
    protected ActionHelper createNewActionHelperInstanceHook(ProtocolForm protocolForm) throws Exception{
        return new IacucActionHelper((IacucProtocolForm) protocolForm);
    }
    
    @Override
    protected ProtocolSpecialReviewHelper createNewSpecialReviewHelperInstanceHook(ProtocolForm protocolForm) {
        return new IacucProtocolSpecialReviewHelper((IacucProtocolForm) protocolForm);
    }
    
    @Override
    protected ProtocolCustomDataHelper createNewCustomDataHelperInstanceHook(ProtocolForm protocolForm) {
        return new IacucProtocolCustomDataHelper((IacucProtocolForm) protocolForm);
    }
    
    @Override
    protected OnlineReviewsActionHelper createNewOnlineReviewsActionHelperInstanceHook(ProtocolForm protocolForm) {
        return new IacucOnlineReviewsActionHelper((IacucProtocolForm) protocolForm);
    }

    public IacucProtocolProceduresHelper getIacucProtocolProceduresHelper() {
        return iacucProtocolProceduresHelper;
    }

    public void setIacucProtocolProceduresHelper(IacucProtocolProceduresHelper iacucProtocolProceduresHelper) {
        this.iacucProtocolProceduresHelper = iacucProtocolProceduresHelper;
    }

    @Override
    protected NotificationHelper getNotificationHelperHook() {
        return new NotificationHelper<IacucProtocolNotificationContext>();
    }

    protected IacucProtocolProcedureService getIacucProtocolProcedureService() {
        return (IacucProtocolProcedureService)KraServiceLocator.getService("iacucProtocolProcedureService");
    }

    public boolean isReinitializeModifySubmissionFields() {
        return reinitializeModifySubmissionFields;
    }

    public void setReinitializeModifySubmissionFields(boolean reinitializeModifySubmissionFields) {
        this.reinitializeModifySubmissionFields = reinitializeModifySubmissionFields;
    }

    public boolean isDefaultOpenCopyTab() {
        return defaultOpenCopyTab;
    }

    public void setDefaultOpenCopyTab(boolean defaultOpenCopyTab) {
        this.defaultOpenCopyTab = defaultOpenCopyTab;
    }
   
}
