/*
 * Copyright 2005-2013 The Kuali Foundation
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
package org.kuali.kra.subaward.document;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.authorization.KraAuthorizationConstants;
import org.kuali.kra.bo.DocumentCustomData;
import org.kuali.kra.bo.versioning.VersionStatus;
import org.kuali.kra.document.ResearchDocumentBase;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.service.KraWorkflowService;
import org.kuali.kra.service.VersionHistoryService;
import org.kuali.kra.subaward.bo.SubAward;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.kew.framework.postprocessor.DocumentRouteStatusChange;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.krad.document.Copyable;
import org.kuali.rice.krad.document.SessionDocument;
import org.kuali.rice.krad.util.GlobalVariables;

/**
 * This class is for subAwardDocument...
 */
public class SubAwardDocument extends ResearchDocumentBase
implements  Copyable, SessionDocument {

    /**.
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 5454534590787613256L;
    private transient boolean documentSaveAfterVersioning;
    private List<SubAward> subAwardList;
    public static final String DOCUMENT_TYPE_CODE = "SAWD";
    @Override
    public String getDocumentTypeCode() {
        return DOCUMENT_TYPE_CODE;
    }
    /**.
     * Constructs a subAwardDocument object
     */
    public SubAwardDocument() {
        super();
        init();
    }
    public SubAward getSubAward() {
        return getSubAwardList().size() > 0 ? getSubAwardList().get(0) : new SubAward();
    }
    public void setSubAward(SubAward subAward){
        subAwardList.set(0, subAward);
    }
    public void setSubAwardList(List<SubAward> subAwardList) {
        this.subAwardList = subAwardList;
    }
    public List<SubAward> getSubAwardList() {
        return subAwardList;
    }

    /**.
     * The method is for doRouteStatusChange
     */
    @Override
    public void doRouteStatusChange(
    DocumentRouteStatusChange statusChangeEvent) {
        super.doRouteStatusChange(statusChangeEvent);

        String newStatus = statusChangeEvent.getNewRouteStatus();

        if (KewApiConstants.ROUTE_HEADER_FINAL_CD.equalsIgnoreCase(newStatus)) {
            getVersionHistoryService().updateVersionHistory(getSubAward(), VersionStatus.ACTIVE, GlobalVariables.
                    getUserSession().getPrincipalName());
        }
        if (newStatus.equalsIgnoreCase(KewApiConstants.ROUTE_HEADER_CANCEL_CD)
        || newStatus.equalsIgnoreCase(
        KewApiConstants.ROUTE_HEADER_DISAPPROVED_CD)) {
            getVersionHistoryService().updateVersionHistory(getSubAward(), VersionStatus.CANCELED, GlobalVariables. 
                    getUserSession().getPrincipalName());
        }

        for (SubAward subAward : subAwardList) {
            subAward.setSubAwardDocument(this);
        }
    }
    /**
     * This method specifies if this document may be
     * edited; i.e. it's only initiated or saved
     * @return boolean
     */
    public boolean isEditable() {
        WorkflowDocument workflowDoc =
        getDocumentHeader().getWorkflowDocument();
        return workflowDoc.isInitiated() || workflowDoc.isSaved();
    }
    
    protected void init() {
        subAwardList = new ArrayList<SubAward>();
        subAwardList.add(new SubAward());
    }
    /**
     * @return
     */
    public boolean isDocumentSaveAfterVersioning() {
        return documentSaveAfterVersioning;
    }
    
    /**
     * @param documentSaveAfterVersioning
     */
    public void setDocumentSaveAfterSubAwardLookupEditOrVersion(boolean documentSaveAfterVersioning) {
        this.documentSaveAfterVersioning = documentSaveAfterVersioning;
    }
    /**
     * @return
     */
    protected VersionHistoryService getVersionHistoryService() {
        return KraServiceLocator.getService(VersionHistoryService.class);
    }
    /**
     * This method is to check whether rice
     * async routing is ok now.
     * Close to hack.  called by holdingpageaction
     * Different document type may have different
     * routing set up, so each document type
     * can implement its own
     *  isProcessComplete
     * @return
     */
    public boolean isProcessComplete() {

        boolean isComplete = false;

        if (getDocumentHeader().hasWorkflowDocument()) {
            /**
             * per KRACOEUS-5394 changing from getDocumentHeader().getWorkflowDocument().isFinal().  This way
             * we route back to the award document more appropriately from holding page.
             */
            if (getDocumentHeader().getWorkflowDocument().isFinal() 
                    || getDocumentHeader().getWorkflowDocument().isProcessed()
                    || KraServiceLocator.getService(KraWorkflowService.class).hasPendingApprovalRequests(getDocumentHeader().getWorkflowDocument())) {
                isComplete = true;
            }
        }
           
        return isComplete;
    }
    @Override
    public List<? extends DocumentCustomData> getDocumentCustomData() {
        return getSubAward().getSubAwardCustomDataList();
    }
    
    /**
     * 
     * @see org.kuali.core.bo.PersistableBusinessObjectBase#buildListOfDeletionAwareLists()
     */
    @SuppressWarnings("unchecked")
    @Override
    public List buildListOfDeletionAwareLists() {
        List managedLists = super.buildListOfDeletionAwareLists();
        SubAward subAward = getSubAward();
        managedLists.add(subAward.getSubAwardFundingSourceList());
        managedLists.add(subAward.getSubAwardContactsList());
        managedLists.add(subAward.getSubAwardCloseoutList());
        return managedLists;
    }
    
    @Override
    public String getCustomLockDescriptor(Person user) {
        String activeLockRegion = (String) GlobalVariables.getUserSession().retrieveObject(KraAuthorizationConstants.ACTIVE_LOCK_REGION);
        String updatedTimestamp = "";
        if (this.getUpdateTimestamp() != null) {
            updatedTimestamp = (new SimpleDateFormat("MM/dd/yyyy KK:mm a").format(this.getUpdateTimestamp()));
        }
        if (StringUtils.isNotEmpty(activeLockRegion)) {
            return this.getSubAward().getSubAwardCode() + "-" + activeLockRegion + "-" + GlobalVariables.getUserSession().getPrincipalName() + "-" + updatedTimestamp;  
        }

        return null;
    }
    
    @Override
    public boolean useCustomLockDescriptors() {
        return true;
    }
}
