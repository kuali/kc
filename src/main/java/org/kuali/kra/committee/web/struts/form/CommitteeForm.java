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
package org.kuali.kra.committee.web.struts.form;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.kuali.kra.authorization.KraAuthorizationConstants;
import org.kuali.kra.committee.document.CommitteeDocument;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.web.struts.form.KraTransactionalDocumentFormBase;
import org.kuali.rice.kns.datadictionary.DocumentEntry;
import org.kuali.rice.kns.datadictionary.HeaderNavigation;
import org.kuali.rice.kns.service.DataDictionaryService;
import org.kuali.rice.kns.util.KNSConstants;
import org.kuali.rice.kns.workflow.service.KualiWorkflowDocument;

/**
 * The Committee Form contains the fields necessary for all POST
 * operations coming from a web browser pertaining to the editing
 * of a Committee.
 * 
 * The form contains a reference to the original committee document.
 * User requests will result in the document fields being set.  It
 * is the responsibility of the business rules to validate the new
 * values of those fields.
 * 
 * The Committee Form has Helpers for some tabs (web pages), if necessary.  
 * For extra properties, e.g. fields for adding a new entry to a table, those
 * properties reside in the Helper corresponding to the tab (web page)
 * they are associated with.
 */
@SuppressWarnings("serial")
public class CommitteeForm extends KraTransactionalDocumentFormBase {

    private CommitteeHelper committeeHelper;
    private MembershipHelper membershipHelper;
    private MembershipRolesHelper membershipRolesHelper;
    private MembershipExpertiseHelper membershipExpertiseHelper;
     
    @SuppressWarnings("unused")
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(CommitteeForm.class);
    
    // KNS Lookup hooks
    private String lookupResultsSequenceNumber;
    private String lookupResultsBOClassName;
  
    //private ScheduleData scheduleData;
    
    private CommitteeScheduleHelper committeeScheduleHelper;

    /**
     * Constructs a CommitteeForm.
     */
    public CommitteeForm() {
        super();
        this.setDocument(new CommitteeDocument());

        //this.setScheduleData(new ScheduleData());
        initialize();
    }

    /**
     * This method initialize all form variables
     */
    public void initialize() {
        initializeHeaderNavigationTabs();
        setCommitteeHelper(new CommitteeHelper(this));
        setMembershipHelper(new MembershipHelper(this));
        setMembershipRolesHelper(new MembershipRolesHelper(this));
        setMembershipExpertiseHelper(new MembershipExpertiseHelper(this));
        setCommitteeScheduleHelper(new CommitteeScheduleHelper(this));
    }

    /**
     * Get the Committee Document.
     * @return the committee document
     */
    public CommitteeDocument getCommitteeDocument() {
        return (CommitteeDocument) this.getDocument();
    }

    /**
     * This method initializes and loads the header navigation tabs.
     */
    protected void initializeHeaderNavigationTabs(){
        DocumentEntry docEntry = getDataDictionaryService().getDataDictionary()
                                    .getDocumentEntry(org.kuali.kra.committee.document.CommitteeDocument.class.getName());
        List<HeaderNavigation> navList = docEntry.getHeaderNavigationList();
        HeaderNavigation[] list = new HeaderNavigation[navList.size()];
        navList.toArray(list);
        super.setHeaderNavigationTabs(list); 
    }
    
    /**
     * Get the Data Dictionary Service.
     * @return the Data Dictionary Service
     */
    private DataDictionaryService getDataDictionaryService(){
        return (DataDictionaryService) KraServiceLocator.getService(Constants.DATA_DICTIONARY_SERVICE_NAME);
    }

    /**
     * @see org.kuali.core.web.struts.form.KualiDocumentFormBase#populate(javax.servlet.http.HttpServletRequest)
     */
    @Override
    public void populate(HttpServletRequest request) {
        super.populate(request);
    }
    
    /**
     * @see org.kuali.core.web.struts.form.KualiDocumentFormBase#populateHeaderFields(org.kuali.core.workflow.service.KualiWorkflowDocument)
     */
    public void populateHeaderFields(KualiWorkflowDocument workflowDocument) {
        super.populateHeaderFields(workflowDocument);
    }

    /**
     * @see org.kuali.kra.web.struts.form.KraTransactionalDocumentFormBase#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
     */
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        super.reset(mapping, request);
        this.setLookupResultsSequenceNumber(null);
        this.setLookupResultsBOClassName(null);
    }

    /**
     * Get the Lookup Results Sequence Number.
     * @return the lookup results sequence number
     */
    public String getLookupResultsSequenceNumber() {
        return lookupResultsSequenceNumber;
    }

    /**
     * Set the Lookup Results Sequence Number.
     * @param lookupResultsSequenceNumber the lookup results sequence number
     */
    public void setLookupResultsSequenceNumber(String lookupResultsSequenceNumber) {
        this.lookupResultsSequenceNumber = lookupResultsSequenceNumber;
    }
    
    /**
     * Get the Lookup Results BO Classname
     * @return the lookup results BO classname
     */
    public String getLookupResultsBOClassName() {
        return lookupResultsBOClassName;
    }

    /**
     * Set the Lookup Results BO Classname
     * @param lookupResultsBOClassName the lookup results BO classname
     */
    public void setLookupResultsBOClassName(String lookupResultsBOClassName) {
        this.lookupResultsBOClassName = lookupResultsBOClassName;
    }
    
    /**
     * @see org.kuali.kra.web.struts.form.KraTransactionalDocumentFormBase#setSaveDocumentControl(org.kuali.core.document.authorization.DocumentActionFlags, java.util.Map)
     */
    @SuppressWarnings("unchecked")
    protected void setSaveDocumentControl(Map editMode) {
        getDocumentActions().put(KNSConstants.KUALI_ACTION_CAN_SAVE, KNSConstants.KUALI_DEFAULT_TRUE_VALUE);
    }
    
    /**
     * @see org.kuali.kra.web.struts.form.KraTransactionalDocumentFormBase#getLockRegion()
     */
    protected String getLockRegion() {
        return KraAuthorizationConstants.LOCK_DESCRIPTOR_COMMITTEE;
    }

    public MembershipHelper getMembershipHelper() {
        return membershipHelper;
    }

    public void setMembershipHelper(MembershipHelper membershipHelper) {
        this.membershipHelper = membershipHelper;
    }
    
    
    public MembershipRolesHelper getMembershipRolesHelper() {
        return membershipRolesHelper;
    }

    public void setMembershipRolesHelper(MembershipRolesHelper membershipRolesHelper) {
        this.membershipRolesHelper = membershipRolesHelper;
    }
    
    public MembershipExpertiseHelper getMembershipExpertiseHelper() {
        return membershipExpertiseHelper;
    }
    
    public void setMembershipExpertiseHelper(MembershipExpertiseHelper membershipExpertiseHelper) {
        this.membershipExpertiseHelper = membershipExpertiseHelper;
    }

    public CommitteeScheduleHelper getCommitteeScheduleHelper() {
        return committeeScheduleHelper;
    }

    public void setCommitteeScheduleHelper(CommitteeScheduleHelper committeeScheduleHelper) {
        this.committeeScheduleHelper = committeeScheduleHelper;
    }

    public CommitteeHelper getCommitteeHelper() {
        return committeeHelper;
    }

    public void setCommitteeHelper(CommitteeHelper committeeHelper) {
        this.committeeHelper = committeeHelper;
    }  
    
    // TODO Overriding for 1.1 upgrade 'till we figure out how to actually use this
    public boolean shouldMethodToCallParameterBeUsed(String methodToCallParameterName, String methodToCallParameterValue, HttpServletRequest request) {
        
        return true;
    }
    
    /** TODO : rice upgrade hack.  multiple lookup has problem because lookupsequencenumber will not be populated
     * 
     * @see org.kuali.rice.kns.web.struts.form.KualiDocumentFormBase#shouldPropertyBePopulatedInForm(java.lang.String, javax.servlet.http.HttpServletRequest)
     */
    public boolean shouldPropertyBePopulatedInForm(String requestParameterName, HttpServletRequest request) {
        return true;
    }
}

