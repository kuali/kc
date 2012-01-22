/*
 * Copyright 2005-2010 The Kuali Foundation
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

import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.bo.versioning.VersionStatus;
import org.kuali.kra.document.ResearchDocumentBase;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.service.VersionHistoryService;
import org.kuali.kra.subaward.bo.SubAward;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.kew.framework.postprocessor.DocumentRouteStatusChange;
import org.kuali.rice.krad.document.Copyable;
import org.kuali.rice.krad.document.SessionDocument;
import org.kuali.rice.krad.util.GlobalVariables;

public class SubAwardDocument extends ResearchDocumentBase implements  Copyable, SessionDocument{
    
    /**
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
    /**
     * Constructs a subAwardDocument object
     */
    public SubAwardDocument(){        
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

    /**
     * @see org.kuali.rice.kns.document.DocumentBase#doRouteStatusChange(org.kuali.rice.kew.dto.DocumentRouteStatusChangeDTO)
     */
    @Override
    public void doRouteStatusChange(DocumentRouteStatusChange statusChangeEvent) {
        super.doRouteStatusChange(statusChangeEvent);
        
        String newStatus = statusChangeEvent.getNewRouteStatus();
        String oldStatus = statusChangeEvent.getOldRouteStatus();
        
        if (KewApiConstants.ROUTE_HEADER_FINAL_CD.equalsIgnoreCase(newStatus)) {
            getVersionHistoryService().updateVersionHistoryOnRouteToFinal(getSubAward(), VersionStatus.ACTIVE, GlobalVariables.getUserSession().getPrincipalName());
        }
        if (newStatus.equalsIgnoreCase(KewApiConstants.ROUTE_HEADER_CANCEL_CD) || newStatus.equalsIgnoreCase(KewApiConstants.ROUTE_HEADER_DISAPPROVED_CD)) {
            getVersionHistoryService().updateVersionHistoryOnCancel(getSubAward(), VersionStatus.CANCELED, GlobalVariables.getUserSession().getPrincipalName());
        }
   
        for (SubAward subAward : subAwardList) {
            subAward.setSubAwardDocument(this);
        }
    }
    /**
     * This method specifies if this document may be edited; i.e. it's only initiated or saved
     * @return
     */
    public boolean isEditable() {
        WorkflowDocument workflowDoc = getDocumentHeader().getWorkflowDocument();
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

    
}
