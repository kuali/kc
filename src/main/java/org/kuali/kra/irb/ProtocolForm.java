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
package org.kuali.kra.irb;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.authorization.KraAuthorizationConstants;
import org.kuali.kra.bo.AbstractSpecialReview;
import org.kuali.kra.common.customattributes.CustomDataForm;
import org.kuali.kra.common.permissions.web.struts.form.PermissionsForm;
import org.kuali.kra.document.ResearchDocumentBase;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.actions.ActionHelper;
import org.kuali.kra.irb.customdata.CustomDataHelper;
import org.kuali.kra.irb.noteattachment.ProtocolAttachmentHelper;
import org.kuali.kra.irb.noteattachment.ProtocolNotepadHelper;
import org.kuali.kra.irb.onlinereview.OnlineReviewActionHelper;
import org.kuali.kra.irb.onlinereview.OnlineReviewsActionHelper;
import org.kuali.kra.irb.onlinereview.ProtocolOnlineReviewService;
import org.kuali.kra.irb.permission.PermissionsHelper;
import org.kuali.kra.irb.personnel.PersonnelHelper;
import org.kuali.kra.irb.protocol.ProtocolHelper;
import org.kuali.kra.irb.protocol.participant.ParticipantsHelper;
import org.kuali.kra.irb.protocol.reference.ProtocolReference;
import org.kuali.kra.irb.questionnaire.QuestionnaireHelper;
import org.kuali.kra.irb.specialreview.ProtocolSpecialReviewExemption;
import org.kuali.kra.irb.specialreview.SpecialReviewHelper;
import org.kuali.kra.web.struts.form.Auditable;
import org.kuali.kra.web.struts.form.KraTransactionalDocumentFormBase;
import org.kuali.kra.web.struts.form.SpecialReviewFormBase;
import org.kuali.rice.kns.datadictionary.DocumentEntry;
import org.kuali.rice.kns.datadictionary.HeaderNavigation;
import org.kuali.rice.kns.service.DataDictionaryService;
import org.kuali.rice.kns.util.ActionFormUtilMap;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.workflow.service.KualiWorkflowDocument;

/**
 * This class...
 * @author Kuali Nervous System Team (kualidev@oncourse.iu.edu)
 */
public class ProtocolForm extends KraTransactionalDocumentFormBase implements PermissionsForm, CustomDataForm, Auditable,  SpecialReviewFormBase<ProtocolSpecialReviewExemption> {
    
    private static final long serialVersionUID = -7633960906991275328L;
    
    /**
     * When true, the online review header will not be displayed when it is disabled.
     */
    private static final boolean HIDE_ONLINE_REVIEW_WHEN_DISABLED = true;
    private static final String ONLINE_REVIEW_NAV_TO = "onlineReview";
    
    private ProtocolHelper protocolHelper;
    private PersonnelHelper personnelHelper;
    private PermissionsHelper permissionsHelper;
    private ParticipantsHelper participantsHelper;
    private CustomDataHelper customDataHelper;
    private SpecialReviewHelper specialReviewHelper;
    private ActionHelper actionHelper;
    private OnlineReviewsActionHelper onlineReviewsActionHelper;
    private QuestionnaireHelper questionnaireHelper;
    //transient so that the helper and its members don't have to be serializable or transient
    //reinitialized in the getter
    private transient ProtocolAttachmentHelper attachmentsHelper;
    private transient ProtocolNotepadHelper notepadHelper;
    private boolean auditActivated;
    
    private ProtocolReference newProtocolReference;
    
    //KNS Lookup hooks
    private String lookupResultsSequenceNumber;
    private String lookupResultsBOClassName;
    
    private boolean javaScriptEnabled = true;
    
    public ProtocolForm() throws Exception {
        super();
        this.setDocument(new ProtocolDocument());
        initialize();
        this.registerEditableProperty("methodToCall");
    }

    /**
     *
     * This method initialize all form variables
     * @throws Exception 
     */
    public void initialize() throws Exception {
        setProtocolHelper(new ProtocolHelper(this));
        setPersonnelHelper(new PersonnelHelper(this));
        setPermissionsHelper(new PermissionsHelper(this));
        setParticipantsHelper(new ParticipantsHelper());
        setCustomDataHelper(new CustomDataHelper(this));
        setSpecialReviewHelper(new SpecialReviewHelper(this));
        setActionHelper(new ActionHelper(this));
        setQuestionnaireHelper(new QuestionnaireHelper(this));
        setAttachmentsHelper(new ProtocolAttachmentHelper(this));
        setNotepadHelper(new ProtocolNotepadHelper(this));
        setNewProtocolReference(new ProtocolReference());
        setOnlineReviewsActionHelper(new OnlineReviewsActionHelper(this));
    }
    
    /**
     * Gets a {@link ProtocolDocument ProtocolDocument}.
     * @return {@link ProtocolDocument ProtocolDocument}
     */
    @Override
    public ProtocolDocument getDocument() {
        return (ProtocolDocument) super.getDocument();
    }

    
    /**
     * Disable the online review tab, and remove it from the nav list if HIDE_ONLINE_REVIEW_WHEN_DISABLED is true
     * @param navList
     * @return
     */
    protected List<HeaderNavigation> disableOnlineReviewTab(List<HeaderNavigation> navList) {
        HeaderNavigation onlineReview = null;
        for (HeaderNavigation nav : navList) {
            if (StringUtils.equals(nav.getHeaderTabNavigateTo(),ONLINE_REVIEW_NAV_TO)) {
                onlineReview = nav;
            }
        }
        if (onlineReview != null) {
            onlineReview.setDisabled(true);
            if (HIDE_ONLINE_REVIEW_WHEN_DISABLED) {
                navList.remove(onlineReview); 
            }
        }
        
        return navList;
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
    @Override
    public HeaderNavigation[] getHeaderNavigationTabs() {
        DataDictionaryService dataDictionaryService = getDataDictionaryService();
        ProtocolOnlineReviewService onlineReviewService = getProtocolOnlineReviewService();
        //RoleService roleService = KraServiceLocator.getService(RoleService.class);
        
        DocumentEntry docEntry = dataDictionaryService.getDataDictionary()
                                    .getDocumentEntry(org.kuali.kra.irb.ProtocolDocument.class.getName());
        List<HeaderNavigation> navList = docEntry.getHeaderNavigationList();
        HeaderNavigation[] list = new HeaderNavigation[navList.size()];
        
        if( getProtocolDocument() != null && getProtocolDocument().getProtocol() != null ) {
            boolean isUserOnlineReviewer = onlineReviewService.isUserAnOnlineReviewerOfProtocol(GlobalVariables.getUserSession().getPrincipalId(), getProtocolDocument().getProtocol());
            boolean isProtocolInStateToBeReviewed = onlineReviewService.isProtocolInStateToBeReviewed(getProtocolDocument().getProtocol());
            boolean isUserIrbAdmin = false;
            
            //TODO: Actually use an authorizer
            //Collection<String> members = roleService.getRoleMemberPrincipalIds("KC-UNT", "IRB Administrator", new AttributeSet());
            //if( members != null && members.contains(GlobalVariables.getUserSession().getPrincipalId())) {
            //    isUserIrbAdmin = true;
            //}
            //END TODO
            
            if (!(isProtocolInStateToBeReviewed && (isUserOnlineReviewer || isUserIrbAdmin))) { 
                navList = disableOnlineReviewTab(navList);
            } else {
                //leave the onlinereview tab enabled.
            }
        } else {
            navList = disableOnlineReviewTab(navList);
        }
        
        navList.toArray(list);
        super.setHeaderNavigationTabs(list);
        return list;
    }
    
    /**
     * 
     * This method is a wrapper method for getting DataDictionary Service using the Service Locator.
     * @return
     */
    protected DataDictionaryService getDataDictionaryService(){
        return (DataDictionaryService) KraServiceLocator.getService(Constants.DATA_DICTIONARY_SERVICE_NAME);
    }

    /**
     * 
     * This method is a wrapper method for getting ProtocolOnlineReviewerService service.
     * @return
     */
    protected ProtocolOnlineReviewService getProtocolOnlineReviewService() {
        return KraServiceLocator.getService(ProtocolOnlineReviewService.class);
    }

    
    
    @Override
    public void populate(HttpServletRequest request) {
        super.populate(request);
        
        // Temporary hack for KRACOEUS-489
        if (getActionFormUtilMap() instanceof ActionFormUtilMap) {
            ((ActionFormUtilMap) getActionFormUtilMap()).clear();
        }       
    }
    
    @Override
    public void populateHeaderFields(KualiWorkflowDocument workflowDocument) {
        super.populateHeaderFields(workflowDocument);
    }

    /* Reset method
     * @param mapping
     * @param request
     */
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        super.reset(mapping, request);
        this.setLookupResultsSequenceNumber(null);
        this.setLookupResultsBOClassName(null);
        onlineReviewsActionHelper.init(true);
        getSpecialReviewHelper().reset();
    }
    
    public String getLookupResultsSequenceNumber() {
        return lookupResultsSequenceNumber;
    }

    public void setLookupResultsSequenceNumber(String lookupResultsSequenceNumber) {
        this.lookupResultsSequenceNumber = lookupResultsSequenceNumber;
    }
    
    public String getLookupResultsBOClassName() {
        return lookupResultsBOClassName;
    }

    public void setLookupResultsBOClassName(String lookupResultsBOClassName) {
        this.lookupResultsBOClassName = lookupResultsBOClassName;
    }

    public void setProtocolHelper(ProtocolHelper protocolHelper) {
        this.protocolHelper = protocolHelper;
    }

    public ProtocolHelper getProtocolHelper() {
        return protocolHelper;
    }
    
    private void setPersonnelHelper(PersonnelHelper personnelHelper) {
        this.personnelHelper = personnelHelper;
    }
    
    public PersonnelHelper getPersonnelHelper() {
        return personnelHelper;
    }
    
    private void setPermissionsHelper(PermissionsHelper permissionsHelper) {
        this.permissionsHelper = permissionsHelper;
    }
    
    public PermissionsHelper getPermissionsHelper() {
        return permissionsHelper;
    }
    
    public void setParticipantsHelper(ParticipantsHelper participantsHelper) {
        this.participantsHelper = participantsHelper;
    }

    public ParticipantsHelper getParticipantsHelper() {
        return participantsHelper;
    }

    public void setNewProtocolReference(ProtocolReference newProtocolReference) {
        this.newProtocolReference = newProtocolReference;
    }

    public ProtocolReference getNewProtocolReference() {
        return newProtocolReference;
    }
    
    protected void setSaveDocumentControl(Map editMode) {
      
    }
    
    protected String getLockRegion() {
        return KraAuthorizationConstants.LOCK_DESCRIPTOR_PROTOCOL;
    }
    
    public String getActionName() {
        return "protocol";
    }

    public CustomDataHelper getCustomDataHelper() {
        return customDataHelper;
    }

    public void setCustomDataHelper(CustomDataHelper customDataHelper) {
        this.customDataHelper = customDataHelper;
    }
    
    /** {@inheritDoc} */
    public boolean isAuditActivated() {
        return this.auditActivated;
    }

    /** {@inheritDoc} */
    public void setAuditActivated(boolean auditActivated) {
        this.auditActivated = auditActivated;
    }

    public SpecialReviewHelper getSpecialReviewHelper() {
        return specialReviewHelper;
    }

    public void setSpecialReviewHelper(SpecialReviewHelper specialReviewHelper) {
        this.specialReviewHelper = specialReviewHelper;
    }

    /**
     * Gets the Attachments Helper.
     * @return Attachments Helper
     */
    public ProtocolAttachmentHelper getAttachmentsHelper() {
        if (attachmentsHelper == null) {
            attachmentsHelper = new ProtocolAttachmentHelper(this);
        }
        
        return attachmentsHelper;
    }

    /**
     * Sets the Attachments Helper.
     * @param attachmentsHelper the Attachments Helper
     */
    public void setAttachmentsHelper(ProtocolAttachmentHelper attachmentsHelper) {
        this.attachmentsHelper = attachmentsHelper;
    }
    
    /**
     * Gets the Notepad Helper.
     * @return Notepad Helper
     */
    public ProtocolNotepadHelper getNotepadHelper() {
        if (notepadHelper == null) {
            notepadHelper = new ProtocolNotepadHelper(this);
        }
        
        return notepadHelper;
    }

    /**
     * Sets the Notepad Helper.
     * @param notepadHelper the Notepad Helper
     */
    public void setNotepadHelper(ProtocolNotepadHelper notepadHelper) {
        this.notepadHelper = notepadHelper;
    }

    /**
     * @see org.kuali.kra.web.struts.form.SpecialReviewFormBase#getNewExemptionTypeCodes()
     */
    public String[] getNewExemptionTypeCodes() {
        return specialReviewHelper.getNewExemptionTypeCodes();
    }

    /**
     * @see org.kuali.kra.web.struts.form.SpecialReviewFormBase#getNewSpecialReview()
     */
    public AbstractSpecialReview<ProtocolSpecialReviewExemption> getNewSpecialReview() {
        return specialReviewHelper.getNewSpecialReview();
    }

    /**
     * @see org.kuali.kra.web.struts.form.SpecialReviewFormBase#getNewSpecialReviewExemptions()
     */
    public List<ProtocolSpecialReviewExemption> getNewSpecialReviewExemptions() {
        return specialReviewHelper.getNewSpecialReviewExemptions();
    }

    /**
     * @see org.kuali.kra.web.struts.form.SpecialReviewFormBase#getResearchDocument()
     */
    public ResearchDocumentBase getResearchDocument() {
        return this.getDocument();
    }
    
    public ActionHelper getActionHelper() {
        return actionHelper;
    }
    
    private void setActionHelper(ActionHelper actionHelper) {
        this.actionHelper = actionHelper;
    }

    public boolean isJavaScriptEnabled() {
        return javaScriptEnabled;
    }

    public void setJavaScriptEnabled(boolean javaScriptEnabled) {
        this.javaScriptEnabled = javaScriptEnabled;
    }

    public ProtocolDocument getProtocolDocument() {
        return (ProtocolDocument) getDocument();
    }

    public QuestionnaireHelper getQuestionnaireHelper() {
        return questionnaireHelper;
    }

    public void setQuestionnaireHelper(QuestionnaireHelper questionnaireHelper) {
        this.questionnaireHelper = questionnaireHelper;
    }

    public void setOnlineReviewsActionHelper(OnlineReviewsActionHelper onlineReviewActionHelper) {
        this.onlineReviewsActionHelper = onlineReviewActionHelper;
    }

    public OnlineReviewsActionHelper getOnlineReviewsActionHelper() {
        return onlineReviewsActionHelper;
    }
 
    @Override
    public boolean isPropertyEditable(String propertyName) {
        if (propertyName.startsWith("actionHelper.protocolSubmitAction.reviewer")) {
            return true;
        } else {
            return super.isPropertyEditable(propertyName);
        }
    }

}
