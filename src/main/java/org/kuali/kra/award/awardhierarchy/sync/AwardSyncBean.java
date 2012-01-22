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
package org.kuali.kra.award.awardhierarchy.sync;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForward;
import org.kuali.kra.award.AwardForm;
import org.kuali.kra.award.awardhierarchy.AwardHierarchy;
import org.kuali.kra.award.awardhierarchy.AwardHierarchyService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.krad.bo.PersistableBusinessObject;
import org.kuali.rice.krad.service.KRADServiceLocatorWeb;
import org.kuali.rice.krad.workflow.service.WorkflowDocumentService;

/**
 * Award Form Bean with helper methods and properties for award sync.
 */
public class AwardSyncBean implements Serializable {

    private static final long serialVersionUID = 6645717325064929518L;

    private AwardForm awardForm;
    
    private ActionForward currentForward;
    private String statusAwardNumber;
    private List<AwardSyncPendingChangeBean> pendingChanges;
    private List<AwardSyncPendingChangeBean> confirmedPendingChanges;
    
    /**
     * 
     * Constructs a AwardSyncBean.java.
     * @param awardForm
     */
    public AwardSyncBean(AwardForm awardForm) {
        this.awardForm = awardForm;
        pendingChanges = new ArrayList<AwardSyncPendingChangeBean>();
        confirmedPendingChanges = new ArrayList<AwardSyncPendingChangeBean>();
    }

    public ActionForward getCurrentForward() {
        return currentForward;
    }

    public void setCurrentForward(ActionForward currentForward) {
        this.currentForward = currentForward;
    }
    
    /**
     * Gets the parent award status log entry, which is the log entry without an award number.
     * @return
     */
    public AwardSyncStatus getParentAwardStatus() {
        for (AwardSyncStatus status : awardForm.getAwardDocument().getAward().getSyncStatuses()) {
            if (status.getAwardNumber() == null ) {
                return status;
            }
        }
        return null;
    }  
    
    public Map<String, AwardSyncStatus> getAwardStatuses() {
        Map<String, AwardSyncStatus> statuses = new HashMap<String, AwardSyncStatus>();
        for (AwardSyncStatus status : awardForm.getAwardDocument().getAward().getSyncStatuses()) {
            if (status.getAwardNumber() != null) {
                statuses.put(status.getAwardNumber(), status);
            }
        }
        return statuses;
    }
    
    public List<AwardHierarchy> getHierarchyList() {
        AwardHierarchy awardHierarchy = 
            KraServiceLocator.getService(AwardHierarchyService.class).loadAwardHierarchyBranch(awardForm.getAwardDocument().getAward().getAwardNumber());
        List<AwardHierarchy> listOrder = new ArrayList<AwardHierarchy>();
        for (AwardHierarchy hierarchy : awardHierarchy.getChildren()) {
            getHierarchyList(hierarchy, listOrder);
        }
        return listOrder;
    }
    
    protected void getHierarchyList(AwardHierarchy hierarchy, List<AwardHierarchy> listOrder) {
        listOrder.add(hierarchy);
        for (AwardHierarchy child : hierarchy.getChildren()) {
            getHierarchyList(child, listOrder);
        }
    }
    
    public boolean isOnValidationNode() {
        WorkflowDocumentService workflowDocumentService = KRADServiceLocatorWeb.getWorkflowDocumentService();
        return StringUtils.equals(Constants.AWARD_SYNC_VALIDATION_NODE_NAME,
                workflowDocumentService.getCurrentRouteNodeNames(awardForm.getAwardDocument().getDocumentHeader().getWorkflowDocument()));
    }
    
    public String getStatusAwardNumber() {
        return statusAwardNumber;
    }

    public void setStatusAwardNumber(String statusAwardNumber) {
        this.statusAwardNumber = statusAwardNumber;
    }

    public void setPendingChanges(List<AwardSyncPendingChangeBean> pendingChanges) {
        this.pendingChanges = pendingChanges;
    }

    public List<AwardSyncPendingChangeBean> getPendingChanges() {
        return pendingChanges;
    }
    
    public void addPendingChange(AwardSyncType type, PersistableBusinessObject object, String awardAttr, String attrName) {
        AwardSyncPendingChangeBean pendingBean = new AwardSyncPendingChangeBean(type, object, awardAttr, attrName);
        getPendingChanges().add(pendingBean);
    }
    
    public void addPendingChange(AwardSyncType type, PersistableBusinessObject object, String awardAttr) {
        AwardSyncPendingChangeBean pendingBean = new AwardSyncPendingChangeBean(type, object, awardAttr);
        getPendingChanges().add(pendingBean);
    }
    

    public List<AwardSyncPendingChangeBean> getConfirmedPendingChanges() {
        return confirmedPendingChanges;
    }

    public void setConfirmedPendingChanges(List<AwardSyncPendingChangeBean> confirmedPendingChanges) {
        this.confirmedPendingChanges = confirmedPendingChanges;
    }
    public void addConfirmedPendingChange(AwardSyncType type, PersistableBusinessObject object, String awardAttr, String attrName) {
        AwardSyncPendingChangeBean pendingBean = new AwardSyncPendingChangeBean(type, object, awardAttr, attrName);
        getConfirmedPendingChanges().add(pendingBean);
    }
    public void addConfirmedPendingChange(AwardSyncType type, PersistableBusinessObject object, String awardAttr) {
        AwardSyncPendingChangeBean pendingBean = new AwardSyncPendingChangeBean(type, object, awardAttr);
        getConfirmedPendingChanges().add(pendingBean);
    }    
    public void confirmPendingChanges() {
        getConfirmedPendingChanges().addAll(getPendingChanges());
        getPendingChanges().clear();
    }
    
}
