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
package org.kuali.kra.iacuc;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.module.CoeusModule;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.notification.impl.NotificationHelper;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.authorization.KraAuthorizationConstants;
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
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.protocol.ProtocolFormBase;
import org.kuali.kra.protocol.actions.ActionHelperBase;
import org.kuali.kra.protocol.actions.ProtocolStatusBase;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase;
import org.kuali.kra.protocol.customdata.ProtocolCustomDataHelperBase;
import org.kuali.kra.protocol.onlinereview.OnlineReviewsActionHelperBase;
import org.kuali.kra.protocol.onlinereview.ProtocolOnlineReviewService;
import org.kuali.kra.protocol.protocol.ProtocolHelperBase;
import org.kuali.kra.protocol.protocol.reference.ProtocolReferenceBeanBase;
import org.kuali.kra.protocol.questionnaire.QuestionnaireHelperBase;
import org.kuali.kra.protocol.specialreview.ProtocolSpecialReviewHelperBase;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.kns.datadictionary.HeaderNavigation;
import org.kuali.rice.kns.util.ActionFormUtilMap;
import org.kuali.rice.kns.web.ui.HeaderField;
import org.kuali.rice.krad.util.GlobalVariables;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


public class IacucProtocolForm extends ProtocolFormBase {
    
    private static final long serialVersionUID = -535557943052220820L;
    private IacucProtocolSpeciesHelper iacucProtocolSpeciesHelper;
    private IacucAlternateSearchHelper iacucAlternateSearchHelper;
    private IacucProtocolExceptionHelper iacucProtocolExceptionHelper;
    private IacucProtocolProceduresHelper iacucProtocolProceduresHelper;
    private boolean defaultOpenCopyTab = false;
    
    private boolean reinitializeModifySubmissionFields = true;
    
    public IacucProtocolForm() throws Exception {
        super();
        initializeIacucProtocolSpecies();
        initializeIacucAlternateSearchHelper();
        initializeIacucProtocolException();
        initializeIacucProtocolProcedures();
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

        return KraAuthorizationConstants.LOCK_DESCRIPTOR_IACUC_PROTOCOL;
    }

    public IacucProtocolHelper getProtocolHelper() {
        return (IacucProtocolHelper)super.getProtocolHelper();
    }

    @Override
    protected ProtocolHelperBase createNewProtocolHelperInstanceHook(ProtocolFormBase protocolForm) {
        return new IacucProtocolHelper((IacucProtocolForm) protocolForm);
    }
    
    
    public IacucPermissionsHelper getPermissionsHelper(ProtocolFormBase protocolForm) {
        return (IacucPermissionsHelper)super.getPermissionsHelper();
    }
    
    @Override
    protected IacucPermissionsHelper createNewPermissionsHelperInstanceHook(ProtocolFormBase protocolForm) {
        return new IacucPermissionsHelper((IacucProtocolForm) protocolForm);
    }
    
    public IacucPersonnelHelper getPersonnelHelper(ProtocolFormBase protocolForm) {
        return (IacucPersonnelHelper)super.getPersonnelHelper();
    }
    
    @Override
    protected IacucPersonnelHelper createNewPersonnelHelperInstanceHook(ProtocolFormBase protocolForm) {
        return new IacucPersonnelHelper((IacucProtocolForm)protocolForm);
    }
    
    public IacucNotesAttachmentsHelper getNotesAttachmentHelper(ProtocolFormBase form) {
        return (IacucNotesAttachmentsHelper)super.getNotesAttachmentsHelper();
    }
    
    @Override
    protected IacucNotesAttachmentsHelper createNewNotesAttachmentsHelperInstanceHook(ProtocolFormBase protocolForm) {
        return new IacucNotesAttachmentsHelper((IacucProtocolForm)protocolForm);
    }
    
    protected QuestionnaireHelperBase createNewQuestionnaireHelper(ProtocolFormBase form) {
        return new IacucQuestionnaireHelper(form);
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
    protected ProtocolReferenceBeanBase createNewProtocolReferenceBeanInstance() {
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
        
        ProtocolStatusBase protocolStatus = (pd == null) ? null : pd.getIacucProtocol().getProtocolStatus();
        HeaderField docStatus = new HeaderField("DataDictionary.AttributeReference.attributes.workflowDocumentStatus", protocolStatus == null? "" : protocolStatus.getDescription());
        getDocInfo().set(1, docStatus);
        
        String lastUpdatedDateStr = null;
        if(pd != null && pd.getUpdateTimestamp() != null) {
            lastUpdatedDateStr = getFormattedDateTime(pd.getUpdateTimestamp());
        }
        
        if(getDocInfo().size() > 2) {
            KcPerson initiator = getKcPersonService().getKcPersonByPersonId(pd.getDocumentHeader().getWorkflowDocument().getInitiatorPrincipalId());
            String modifiedInitiatorFieldStr = initiator == null ? "" : initiator.getUserName();
            if(StringUtils.isNotBlank(lastUpdatedDateStr)) {
                modifiedInitiatorFieldStr += (" : " + lastUpdatedDateStr);
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
            ProtocolSubmissionBase submission = getProtocolDocument().getProtocol().getProtocolSubmission();
            boolean isUserOnlineReviewer = onlineReviewService.isProtocolReviewer(principalId, false, submission);
            boolean isUserIacucAdmin = getSystemAuthorizationService().hasRole(GlobalVariables.getUserSession().getPrincipalId(), "KC-UNT", "IACUC Administrator");
            onlineReviewTabEnabled = (isUserOnlineReviewer || isUserIacucAdmin) 
                    && onlineReviewService.isProtocolInStateToBeReviewed((IacucProtocol)getProtocolDocument().getProtocol());
        }
        
            //We have to copy the HeaderNavigation elements into a new collection as the 
            //List returned by DD is it's cached copy of the header navigation list.
        for (HeaderNavigation nav : navigation) {
            if (StringUtils.equals(nav.getHeaderTabNavigateTo(),ONLINE_REVIEW_NAV_TO)) {
                nav.setDisabled(!onlineReviewTabEnabled);
                if (onlineReviewTabEnabled || ((!onlineReviewTabEnabled) && (!HIDE_ONLINE_REVIEW_WHEN_DISABLED))) {
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

    
   protected ProtocolOnlineReviewService getProtocolOnlineReviewService() {
       return KcServiceLocator.getService(IacucProtocolOnlineReviewService.class);
   }

  
    @Override
    protected QuestionnaireHelperBase createNewQuestionnaireHelperInstanceHook(ProtocolFormBase protocolForm) {
        return new IacucQuestionnaireHelper((IacucProtocolForm) protocolForm);
    }
    
    @Override
    protected ActionHelperBase createNewActionHelperInstanceHook(ProtocolFormBase protocolForm, boolean initializeActions) throws Exception{
    	IacucActionHelper iacucActionHelper = new IacucActionHelper((IacucProtocolForm) protocolForm);
    	if(initializeActions) {
    		iacucActionHelper.initializeProtocolActions();
    	}
    	return iacucActionHelper;
    }
    
    @Override
    protected ProtocolSpecialReviewHelperBase createNewSpecialReviewHelperInstanceHook(ProtocolFormBase protocolForm) {
        return new IacucProtocolSpecialReviewHelper((IacucProtocolForm) protocolForm);
    }
    
    @Override
    protected ProtocolCustomDataHelperBase createNewCustomDataHelperInstanceHook(ProtocolFormBase protocolForm) {
        return new IacucProtocolCustomDataHelper((IacucProtocolForm) protocolForm);
    }
    
    @Override
    protected OnlineReviewsActionHelperBase createNewOnlineReviewsActionHelperInstanceHook(ProtocolFormBase protocolForm) {
        return new IacucOnlineReviewsActionHelper((IacucProtocolForm) protocolForm);
    }

    public IacucProtocolProceduresHelper getIacucProtocolProceduresHelper() {
        return iacucProtocolProceduresHelper;
    }

    public void setIacucProtocolProceduresHelper(IacucProtocolProceduresHelper iacucProtocolProceduresHelper) {
        this.iacucProtocolProceduresHelper = iacucProtocolProceduresHelper;
    }

    @Override
    protected NotificationHelper<IacucProtocolNotificationContext> getNotificationHelperHook() {
        return new NotificationHelper<IacucProtocolNotificationContext>();
    }

    protected IacucProtocolProcedureService getIacucProtocolProcedureService() {
        return (IacucProtocolProcedureService) KcServiceLocator.getService("iacucProtocolProcedureService");
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


    @Override
    protected List<String> getTerminalNodeNamesHook() {
        List<String> retVal = new ArrayList<String>();
        retVal.add(Constants.IACUC_PROTOCOL_IACUCREVIEW_ROUTE_NODE_NAME);
        return retVal;
    }

    public String getShortUrl() {
        return  getBaseShortUrl() + "/kc-common/iacuc-protocols/" + getIacucProtocolDocument().getProtocol().getProtocolNumber();
    }

}
