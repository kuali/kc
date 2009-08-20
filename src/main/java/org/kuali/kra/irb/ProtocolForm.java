/*
 * Copyright 2006-2008 The Kuali Foundation
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

import static org.kuali.kra.infrastructure.KraServiceLocator.getService;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import org.kuali.kra.irb.permission.PermissionsHelper;
import org.kuali.kra.irb.personnel.PersonnelHelper;
import org.kuali.kra.irb.protocol.ProtocolHelper;
import org.kuali.kra.irb.protocol.participant.ParticipantsHelper;
import org.kuali.kra.irb.protocol.reference.ProtocolReference;
import org.kuali.kra.irb.specialreview.ProtocolSpecialReviewExemption;
import org.kuali.kra.irb.specialreview.SpecialReviewHelper;
import org.kuali.kra.web.struts.form.Auditable;
import org.kuali.kra.web.struts.form.KraTransactionalDocumentFormBase;
import org.kuali.kra.web.struts.form.SpecialReviewFormBase;
import org.kuali.rice.kns.datadictionary.DocumentEntry;
import org.kuali.rice.kns.datadictionary.HeaderNavigation;
import org.kuali.rice.kns.service.DataDictionaryService;
import org.kuali.rice.kns.service.KualiConfigurationService;
import org.kuali.rice.kns.util.ActionFormUtilMap;
import org.kuali.rice.kns.workflow.service.KualiWorkflowDocument;

/**
 * This class...
 * @author Kuali Nervous System Team (kualidev@oncourse.iu.edu)
 */
public class ProtocolForm extends KraTransactionalDocumentFormBase implements PermissionsForm, CustomDataForm, Auditable,  SpecialReviewFormBase<ProtocolSpecialReviewExemption> {
    
    private static final long serialVersionUID = -7633960906991275328L;
    
    private ProtocolHelper protocolHelper;
    private PersonnelHelper personnelHelper;
    private PermissionsHelper permissionsHelper;
    private ParticipantsHelper participantsHelper;
    private CustomDataHelper customDataHelper;
    private SpecialReviewHelper specialReviewHelper;
    private ActionHelper actionHelper;
    //transient so that the helper and its members don't have to be serializable or transient
    //reinitialized in the getter
    private transient ProtocolAttachmentHelper notesAndAttachmentsHelper;
    private boolean auditActivated;
    
    private ProtocolReference newProtocolReference;
    
    //KNS Lookup hooks
    private String lookupResultsSequenceNumber;
    private String lookupResultsBOClassName;
    
    private boolean javaScriptEnabled = true;
    
    public ProtocolForm() {
        super();
        this.setDocument(new ProtocolDocument());
        initialize();
        this.registerEditableProperty("methodToCall");
    }

    /**
     *
     * This method initialize all form variables
     */
    public void initialize() {
        initializeHeaderNavigationTabs(); 
        setProtocolHelper(new ProtocolHelper(this));
        setPersonnelHelper(new PersonnelHelper(this));
        setPermissionsHelper(new PermissionsHelper(this));
        setParticipantsHelper(new ParticipantsHelper());
        setCustomDataHelper(new CustomDataHelper(this));
        setSpecialReviewHelper(new SpecialReviewHelper(this));
        setActionHelper(new ActionHelper(this));
        setNotesAndAttachmentsHelper(new ProtocolAttachmentHelper(this));
        setNewProtocolReference(new ProtocolReference());
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
     * 
     * This method initializes the loads the header navigation tabs.
     */
    protected void initializeHeaderNavigationTabs(){
        DataDictionaryService dataDictionaryService = getDataDictionaryService();
        DocumentEntry docEntry = dataDictionaryService.getDataDictionary()
                                    .getDocumentEntry(org.kuali.kra.irb.ProtocolDocument.class.getName());
        List<HeaderNavigation> navList = docEntry.getHeaderNavigationList();
        HeaderNavigation[] list = new HeaderNavigation[navList.size()];
        navList.toArray(list);
        super.setHeaderNavigationTabs(list); 
    }
    
    /**
     * 
     * This method is a wrapper method for getting DataDictionary Service using the Service Locator.
     * @return
     */
    protected DataDictionaryService getDataDictionaryService(){
        return (DataDictionaryService) KraServiceLocator.getService(Constants.DATA_DICTIONARY_SERVICE_NAME);
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
        getSpecialReviewHelper().reset();
    }

    public KualiConfigurationService getConfigurationService() {
        return getService(KualiConfigurationService.class);
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
     * Gets the Notes And Attachments Helper.
     * @return Notes And Attachments Helper
     */
    public ProtocolAttachmentHelper getNotesAndAttachmentsHelper() {
        if (notesAndAttachmentsHelper == null) {
            notesAndAttachmentsHelper = new ProtocolAttachmentHelper(this);
        }
        
        return notesAndAttachmentsHelper;
    }

    /**
     * Sets the Notes And Attachments Helper.
     * @param notesAndAttachmentsHelper the Notes And Attachments Helper
     */
    public void setNotesAndAttachmentsHelper(ProtocolAttachmentHelper notesAndAttachmentsHelper) {
        this.notesAndAttachmentsHelper = notesAndAttachmentsHelper;
    }

    /**
     * @see org.kuali.kra.web.struts.form.SpecialReviewFormBase#getNewExemptionTypeCodes()
     */
    public String[] getNewExemptionTypeCodes() {
        return specialReviewHelper.getNewExemptionTypeCodes();
    }
    
    // TODO Overriding for 1.1 upgrade 'till we figure out how to actually use this
    public boolean shouldMethodToCallParameterBeUsed(String methodToCallParameterName, String methodToCallParameterValue, HttpServletRequest request) {
        
        return true;
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
    
    /** TODO : rice upgrade hack.  multiple lookup has problem because lookupsequencenumber will not be populated
     * 
     * @see org.kuali.rice.kns.web.struts.form.KualiDocumentFormBase#shouldPropertyBePopulatedInForm(java.lang.String, javax.servlet.http.HttpServletRequest)
     */
    public boolean shouldPropertyBePopulatedInForm(String requestParameterName, HttpServletRequest request) {
        return true;
    }
}
