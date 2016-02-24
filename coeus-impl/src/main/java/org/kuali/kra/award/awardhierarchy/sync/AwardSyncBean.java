/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
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
package org.kuali.kra.award.awardhierarchy.sync;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts.action.ActionForward;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.AwardForm;
import org.kuali.kra.award.awardhierarchy.AwardHierarchy;
import org.kuali.kra.award.awardhierarchy.AwardHierarchyService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.krad.bo.PersistableBusinessObject;
import org.kuali.rice.krad.service.KRADServiceLocatorWeb;
import org.kuali.rice.krad.workflow.service.WorkflowDocumentService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            KcServiceLocator.getService(AwardHierarchyService.class).loadAwardHierarchyBranch(awardForm.getAwardDocument().getAward().getAwardNumber());
        List<AwardHierarchy> listOrder = new ArrayList<AwardHierarchy>();
        if (awardHierarchy != null) {
            for (AwardHierarchy hierarchy : awardHierarchy.getChildren()) {
                getHierarchyList(hierarchy, listOrder);
            }
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
