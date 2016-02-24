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

package org.kuali.coeus.common.committee.impl.document;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.committee.impl.bo.CommitteeBase;
import org.kuali.coeus.common.committee.impl.bo.CommitteeScheduleBase;
import org.kuali.coeus.common.committee.impl.service.CommitteeServiceBase;
import org.kuali.coeus.sys.framework.model.KcTransactionalDocumentBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.bo.RolePersons;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kew.framework.postprocessor.DocumentRouteStatusChange;
import org.kuali.rice.krad.document.Copyable;
import org.kuali.rice.krad.document.SessionDocument;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The CommitteeBase Document wraps a single CommitteeBase BO.  
 * The document is necessary for workflow.
 */
@SuppressWarnings("serial")
public abstract class CommitteeDocumentBase<CD extends CommitteeDocumentBase<CD, CMT, CS>, 
                                            CMT extends CommitteeBase<CMT, CD, CS>, 
                                            CS extends CommitteeScheduleBase<CS, CMT, ?, ?>> 
                        
                                            extends KcTransactionalDocumentBase implements Copyable, SessionDocument {

    private static final long serialVersionUID = 1L;

    private static final String DOCUMENT_TYPE_CODE = "COMT";

	// These 2 properties are for performance purpose.  Especially, for those 1st version committee doc.
    private String committeeId;
    private String docStatusCode;
	
    /*
     * It may be seem strange, but we use a list in order to store a
     * single Committtee BO.  This is due to a problem for one-to-one
     * relationships within OJB in regards to anonymous keys.  We are
     * forced to use a one-to-many relationship.
     */
    private List<CMT> committeeList = new ArrayList<>();
    

    public CommitteeDocumentBase() {
        CMT committee = getNewCommitteeInstanceHook();
        committeeList.add(committee);
        committee.setCommitteeDocument(getThisHook());
    }

    protected abstract CD getThisHook();

    protected abstract CMT getNewCommitteeInstanceHook();
    

    @Override
    public void initialize() {
    }

    /**
     * Get the CommitteeBase BO.  This is a convenience method for easily
     * obtaining the single CommitteeBase BO in the list.
     * @return the CommitteeBase BO
     */
    public CMT getCommittee() {
        return committeeList.get(0);
    }

    /**
     * Set the CommitteeBase BO.  This is a convenience method to easily
     * insert the committee into the list.
     * @param committee the CommitteeBase BO
     */
    public void setCommittee(CMT committee) {
        committeeList.set(0, committee);
    }

    /**
     * Get the list of committees.  
     * WARNING: Developers should never call this method.
     *          This method is for OJB use only.
     * @return the list with the single committee
     */
    public List<CMT> getCommitteeList() {
        return committeeList;
    }

    /**
     * Set the list of committees.
     * WARNING: Developers should never call this method.
     *          This method is for OJB use only.
     * @param committeeList the list containing the single committee
     */
    public void setCommitteeList(List<CMT> committeeList) {
        this.committeeList = committeeList;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List buildListOfDeletionAwareLists() {

        List managedLists = super.buildListOfDeletionAwareLists();
        managedLists.add(getCommittee().getCommitteeMemberships());

        managedLists.add(getCommittee().getCommitteeMemberships().stream()
                .flatMap(membership -> membership.getMembershipRoles().stream()).collect(Collectors.toList()));
        managedLists.add(getCommittee().getCommitteeMemberships().stream()
                .flatMap(membership -> membership.getMembershipExpertise().stream()).collect(Collectors.toList()));

        return managedLists;
    }
    
    @Override
    protected List<RolePersons> getAllRolePersons() {
        return new ArrayList<>();
    }
    
    public String getDocumentTypeCode() {
        return DOCUMENT_TYPE_CODE;
    }
    
    @Override
    public void prepareForSave() {
        super.prepareForSave();
        if (ObjectUtils.isNull(this.getVersionNumber())) {
            this.setVersionNumber(0L);
        }
        if (this.getCommittee() != null) {
            this.setCommitteeId(this.getCommittee().getCommitteeId());
        }
        String routeStatusCode = this.getDocumentHeader().getWorkflowDocument().getStatus().getCode();
        if (StringUtils.isNotBlank(routeStatusCode) && routeStatusCode.equals(KewApiConstants.ROUTE_HEADER_INITIATED_CD)) {
            // route status from I to S will not update document, so do it here with correct status
            this.setDocStatusCode(KewApiConstants.ROUTE_HEADER_SAVED_CD);
        } else {
            this.setDocStatusCode(routeStatusCode);
        }
    }

    @Override
    public void doRouteStatusChange(DocumentRouteStatusChange statusChangeEvent) {
        executeAsLastActionUser(() -> {
            super.doRouteStatusChange(statusChangeEvent);
            this.setDocStatusCode(statusChangeEvent.getNewRouteStatus());
            if (isFinal(statusChangeEvent) && this.getCommittee().getSequenceNumber() > 1) {
                List<CS> newMasterSchedules = getCommitteeService().mergeCommitteeSchedule(this.getCommittee());
                this.getCommittee().setCommitteeSchedules(newMasterSchedules);
                getBusinessObjectService().save(this);
                // finally update all submissions to point to the new committee
                getCommitteeService().updateCommitteeForProtocolSubmissions(this.getCommittee());
            }
            return null;
        });
    }
    
    protected abstract CommitteeServiceBase<CMT, CS> getCommitteeService();
    
    private BusinessObjectService getBusinessObjectService() {
        return KcServiceLocator.getService(BusinessObjectService.class);
    }
    
    /**
     * Has the document entered the final state in workflow?
     */
    private boolean isFinal(DocumentRouteStatusChange statusChangeEvent) {
        return StringUtils.equals(KewApiConstants.ROUTE_HEADER_FINAL_CD, statusChangeEvent.getNewRouteStatus());
    }

    public String getCommitteeId() {
        return committeeId;
    }

    public void setCommitteeId(String committeeId) {
        this.committeeId = committeeId;
    }

    public String getDocStatusCode() {
        return docStatusCode;
    }

    public void setDocStatusCode(String docStatusCode) {
        this.docStatusCode = docStatusCode;
    }
    
    /**
     * This method is to check whether rice async routing is ok now.   
     * Close to hack.  called by holdingpageaction
     * Different document type may have different routing set up, so each document type
     * can implement its own isProcessComplete
     */
    public boolean isProcessComplete() {
        boolean isComplete = false;
        
        if (getDocumentHeader().hasWorkflowDocument()) {
            String docRouteStatus = getDocumentHeader().getWorkflowDocument().getStatus().getCode();
            if (KewApiConstants.ROUTE_HEADER_FINAL_CD.equals(docRouteStatus)) {
                isComplete = true;
            }
        }
           
        return isComplete;
    }

    public String getDocumentBoNumber() {
        return getCommitteeId();
    }
}
