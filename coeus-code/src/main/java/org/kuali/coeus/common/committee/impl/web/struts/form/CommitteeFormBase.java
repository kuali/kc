/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.common.committee.impl.web.struts.form;


import org.apache.commons.lang3.StringUtils;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.common.committee.impl.document.CommitteeDocumentBase;
import org.kuali.coeus.sys.framework.model.KcTransactionalDocumentFormBase;
import org.kuali.kra.authorization.KraAuthorizationConstants;
import org.kuali.rice.core.api.CoreApiServiceLocator;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.kns.datadictionary.HeaderNavigation;
import org.kuali.rice.kns.web.ui.HeaderField;
import org.kuali.rice.krad.util.KRADConstants;
import org.kuali.rice.krad.util.ObjectUtils;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The CommitteeBase Form contains the fields necessary for all POST
 * operations coming from a web browser pertaining to the editing
 * of a CommitteeBase.
 * 
 * The form contains a reference to the original committee document.
 * User requests will result in the document fields being set.  It
 * is the responsibility of the business rules to validate the new
 * values of those fields.
 * 
 * The CommitteeBase Form has Helpers for some tabs (web pages), if necessary.  
 * For extra properties, e.g. fields for adding a new entry to a table, those
 * properties reside in the Helper corresponding to the tab (web page)
 * they are associated with.
 */
@SuppressWarnings("serial")
public abstract class CommitteeFormBase extends KcTransactionalDocumentFormBase {

    private CommitteeHelperBase committeeHelper;
    
    @SuppressWarnings("unused")
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(CommitteeFormBase.class);
    
    // KNS Lookup hooks
    private String lookupResultsSequenceNumber;
    private String lookupResultsBOClassName;
  
    /**
     * Constructs a CommitteeFormBase.
     */
    public CommitteeFormBase() {
        super();
        //this.setScheduleData(new ScheduleData());
        initialize();
    }
    
    /** {@inheritDoc} */
    // we mark an inherited non-abstract method as abstract to indicate that the subclasses will need to provide an implementation, 
    protected abstract String getDefaultDocumentTypeName();

    /**
     * This method initialize all form variables
     */
    public void initialize() {
        setCommitteeHelper(getNewCommitteeHelperInstanceHook(this));
    }

    protected abstract CommitteeHelperBase getNewCommitteeHelperInstanceHook(CommitteeFormBase commonCommitteeForm);
    
    
    /**
     * Get the CommitteeBase Document.
     * @return the committee document
     */
    public CommitteeDocumentBase getCommitteeDocument() {
        return (CommitteeDocumentBase) this.getDocument();
    }

    /**o
     * @see org.kuali.core.web.struts.form.KualiDocumentFormBase#populate(javax.servlet.http.HttpServletRequest)
     */
    @Override
    public void populate(HttpServletRequest request) {
        super.populate(request);
    }
    
    protected String getCommitteeNameForHeaderDisplay(CommitteeDocumentBase committeeDoc) {
        String trimmedCommitteeName = null;
        if(committeeDoc != null && !CollectionUtils.isEmpty(committeeDoc.getCommitteeList())) {  
            trimmedCommitteeName = committeeDoc.getCommittee().getCommitteeName();
            if(StringUtils.isNotEmpty(trimmedCommitteeName)) {
                if(trimmedCommitteeName.length() > 60) {
                    trimmedCommitteeName = trimmedCommitteeName.substring(0, 60);
                }
            }
        } 
        return trimmedCommitteeName;
    }
    
    /**
     * @see org.kuali.core.web.struts.form.KualiDocumentFormBase#populateHeaderFields(org.kuali.core.workflow.service.KualiWorkflowDocument)
     */
    @Override
    public void populateHeaderFields(WorkflowDocument workflowDocument) {
        super.populateHeaderFields(workflowDocument);
        CommitteeDocumentBase committeeDoc = getCommitteeDocument();
        
        HeaderField documentNumber = getDocInfo().get(0);
        documentNumber.setDdAttributeEntryName("DataDictionary.CommitteeDocument.attributes.documentNumber");
        
        if(CollectionUtils.isEmpty(committeeDoc.getCommitteeList())) {
            ObjectUtils.materializeObjects(committeeDoc.getCommitteeList()); 
        }
        
        String lastUpdatedDateStr = null;
        if(committeeDoc != null && committeeDoc.getUpdateTimestamp() != null) {
            lastUpdatedDateStr = CoreApiServiceLocator.getDateTimeService().toString(committeeDoc.getUpdateTimestamp(), "hh:mm a MM/dd/yyyy");
        }
        
        HeaderField lastUpdatedDate = new HeaderField("DataDictionary.CommitteeDocument.attributes.updateTimestamp", lastUpdatedDateStr);
        getDocInfo().set(3, lastUpdatedDate);
        
        getDocInfo().add(new HeaderField("DataDictionary.KraAttributeReferenceDummy.attributes.committeeId", (committeeDoc == null || CollectionUtils.isEmpty(committeeDoc.getCommitteeList())) ? null : committeeDoc.getCommittee().getCommitteeId()));
        getDocInfo().add(new HeaderField("DataDictionary.KraAttributeReferenceDummy.attributes.committeeName", getCommitteeNameForHeaderDisplay(committeeDoc)));
    }

    /**
     * @see org.kuali.coeus.sys.framework.model.KcTransactionalDocumentFormBase#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
     */
    @Override
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
     * @see org.kuali.coeus.sys.framework.model.KcTransactionalDocumentFormBase#setSaveDocumentControl(org.kuali.core.document.authorization.DocumentActionFlags, java.util.Map)
     */
    @Override
    @SuppressWarnings("unchecked")
    protected void setSaveDocumentControl(Map editMode) {
        getDocumentActions().put(KRADConstants.KUALI_ACTION_CAN_SAVE, KRADConstants.KUALI_DEFAULT_TRUE_VALUE);
    }
    
    /**
     * @see org.kuali.coeus.sys.framework.model.KcTransactionalDocumentFormBase#getLockRegion()
     */
    @Override
    protected String getLockRegion() {
        return KraAuthorizationConstants.LOCK_DESCRIPTOR_COMMITTEE;
    }

    public CommitteeHelperBase getCommitteeHelper() {
        return committeeHelper;
    }

    public void setCommitteeHelper(CommitteeHelperBase committeeHelper) {
        this.committeeHelper = committeeHelper;
    }
  
    /**
     * Without this hack, a committee member that is inactive and has a validation error can't be
     * edited in the Active Members only display mode.
     * 
     * @see org.kuali.rice.kns.web.struts.form.pojo.PojoFormBase#isPropertyEditable(java.lang.String)
     */
    @Override
    public boolean isPropertyEditable(String propertyName) {
        if (propertyName.startsWith("document.committeeList[0].committeeMemberships[")) {
            return true;
        } else {
            return super.isPropertyEditable(propertyName);
        }
    }
    
    /**
     * We need to remove the "Actions" tab when the committee is in edit mode, as
     * actions are only supposed to be taken by the admin when viewing.
     * 
     * @see org.kuali.rice.kns.web.struts.form.KualiForm#getHeaderNavigationTabs()
     */
    @Override
    public HeaderNavigation[] getHeaderNavigationTabs() {

        HeaderNavigation[] navigation = super.getHeaderNavigationTabs();
        List<HeaderNavigation> resultList = new ArrayList<HeaderNavigation>();
        for (int i = 0; i < navigation.length; i++) {
            if (!StringUtils.equals("Actions", navigation[i].getHeaderTabDisplayName())) {
                resultList.add(navigation[i]);
            } else {
                if (!StringUtils.equals(KewApiConstants.INITIATE_COMMAND, this.getCommand())) {
                    resultList.add(navigation[i]);
                }
            }
        }
        
        HeaderNavigation[] result = new HeaderNavigation[resultList.size()];
        resultList.toArray(result);
        
        return result;
    }
}

