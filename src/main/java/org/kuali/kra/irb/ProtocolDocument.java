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

import java.util.ArrayList;
import java.util.List;

import org.codehaus.plexus.util.StringUtils;
import org.kuali.kra.bo.RolePersons;
import org.kuali.kra.document.ResearchDocumentBase;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.ProtocolStatus;
import org.kuali.kra.irb.actions.submit.ProtocolActionService;
import org.kuali.kra.irb.auth.ProtocolAuthorizationService;
import org.kuali.rice.kew.dto.DocumentRouteStatusChangeDTO;
import org.kuali.rice.kew.exception.WorkflowException;
import org.kuali.rice.kns.document.Copyable;
import org.kuali.rice.kns.document.SessionDocument;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.DocumentService;

/**
 * 
 * This class represents the Protocol Document Object.
 * ProtocolDocument has a 1:1 relationship with Protocol Business Object.
 * We have declared a list of Protocol BOs in the ProtocolDocument at the same time to
 * get around the OJB anonymous keys issue of primary keys of different data types.
 * Also we have provided convenient getter and setter methods so that to the outside world;
 * Protocol and ProtocolDocument can have a 1:1 relationship.
 */
public class ProtocolDocument extends ResearchDocumentBase implements Copyable, SessionDocument { 
	
    private static final String DOCUMENT_TYPE_CODE = "PROT";
    private static final String FINAL_STATE = "F";
    private static final String DISAPPROVED_STATE = "D";
    private static final String AMENDMENT_KEY = "A";
    private static final String RENEWAL_KEY = "R";
    
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 803158468103165087L;
    private List<Protocol> protocolList;
	
    /**
     * Constructs a ProtocolDocument object
     */
	public ProtocolDocument() { 
        super();
        protocolList = new ArrayList<Protocol>();
        Protocol newProtocol = new Protocol();
        newProtocol.setProtocolDocument(this);
        protocolList.add(newProtocol);
	} 
	
    public void initialize() {
    }

    
    /**
     * 
     * This method is a convenience method for facilitating a 1:1 relationship between ProtocolDocument 
     * and Protocol to the outside world - aka a single Protocol field associated with ProtocolDocument
     * @return
     */
    public Protocol getProtocol() {
        if (protocolList.size() == 0) return null;
        return protocolList.get(0);
    }

    /**
     * 
     * This method is a convenience method for facilitating a 1:1 relationship between ProtocolDocument 
     * and Protocol to the outside world - aka a single Protocol field associated with ProtocolDocument
     * @param protocol
     */
    public void setProtocol(Protocol protocol) {
        protocolList.set(0, protocol);
    }


    /**
     * 
     * This method is used by OJB to get around with anonymous keys issue.
     * Warning : Developers should never use this method.
     * @return List<Protocol>
     */
    public List<Protocol> getProtocolList() {
        return protocolList;
    }

    /**
     * 
     * This method is used by OJB to get around with anonymous keys issue.
     * Warning : Developers should never use this method
     * @param protocolList
     */
    public void setProtocolList(List<Protocol> protocolList) {
        this.protocolList = protocolList;
    }
    
    /**
     * @see org.kuali.core.bo.PersistableBusinessObjectBase#buildListOfDeletionAwareLists()
     */
    @SuppressWarnings("unchecked")
    @Override
    public List buildListOfDeletionAwareLists() {
        List managedLists = super.buildListOfDeletionAwareLists();
        if (getProtocol() != null) {
            managedLists.addAll(getProtocol().buildListOfDeletionAwareLists());
        }
        managedLists.add(protocolList);
        return managedLists;
    }
    
    /**
     * @see org.kuali.kra.document.ResearchDocumentBase#getAllRolePersons()
     */
    @Override
    protected List<RolePersons> getAllRolePersons() {
        ProtocolAuthorizationService protocolAuthService = 
               (ProtocolAuthorizationService) KraServiceLocator.getService(ProtocolAuthorizationService.class); 
        return protocolAuthService.getAllRolePersons(getProtocol());
    }
    
    public String getDocumentTypeCode() {
        return DOCUMENT_TYPE_CODE;
    }
    
    /**
     * @see org.kuali.rice.kns.document.DocumentBase#doRouteStatusChange(org.kuali.rice.kew.dto.DocumentRouteStatusChangeDTO)
     */
    @Override
    public void doRouteStatusChange(DocumentRouteStatusChangeDTO statusChangeEvent) {
        super.doRouteStatusChange(statusChangeEvent);
        if (isFinal(statusChangeEvent)) {
            if (isAmendment()) {
                mergeAmendment(ProtocolStatus.AMENDMENT_MERGED, "Amendment");
            }
            else if (isRenewal()) {
                mergeAmendment(ProtocolStatus.RENEWAL_MERGED, "Renewal");
            }
            else {
                approveProtocol();
            }
        }
        else if (isDisapproved(statusChangeEvent)) { 
            if (isNormal()){
                disapproveProtocol();
            }
        }
    }
    
    /**
     * Update the protocol's status to approved.
     */
    private void approveProtocol() {
        updateProtocolStatus(ProtocolActionType.APPROVED);
    }
    
    /**
     * Update the protocol's status to disapproved.
     */
    private void disapproveProtocol() {
        updateProtocolStatus(ProtocolActionType.DISAPPROVED);
    }
    
    /**
     * Add a new protocol action to the protocol and update the status.
     * @param actionTypeCode the new action
     */
    private void updateProtocolStatus(String actionTypeCode) {
        ProtocolAction protocolAction = new ProtocolAction(getProtocol(), null, actionTypeCode);
        getProtocol().getProtocolActions().add(protocolAction);
        
        ProtocolActionService protocolActionService = KraServiceLocator.getService(ProtocolActionService.class);
        protocolActionService.updateProtocolStatus(protocolAction, getProtocol());
    }

    /**
     * Merge the amendment into the original protocol.  Actually, we must first make a new
     * version of the original and then merge the amendment into that new version.
     * @param protocolStatusCode
     * @throws Exception
     */
    private void mergeAmendment(String protocolStatusCode, String type) {
        Protocol currentProtocol = getProtocolFinder().findCurrentProtocolByNumber(getOriginalProtocolNumber());
        final ProtocolDocument newProtocolDocument;
        try {
            newProtocolDocument = getProtocolVersionService().versionProtocolDocument(currentProtocol.getProtocolDocument());
        } catch (Exception e) {
            throw new ProtocolMergeException(e);
        }
        
        newProtocolDocument.getProtocol().merge(getProtocol());
        getProtocol().setProtocolStatusCode(protocolStatusCode);
        
        ProtocolAction action = new ProtocolAction(newProtocolDocument.getProtocol(), null, ProtocolActionType.APPROVED);
        action.setComments(type + "-" + getProtocolNumberIndex());
        newProtocolDocument.getProtocol().getProtocolActions().add(action);
        try {
            getDocumentService().saveDocument(newProtocolDocument);
        } catch (WorkflowException e) {
            throw new ProtocolMergeException(e);
        }
        /*
         * TODO: We have to route the new protocol document here so
         * that it goes to the final state.
         */
     
        getBusinessObjectService().save(this);
    }
    
    private ProtocolVersionService getProtocolVersionService() {
        return KraServiceLocator.getService(ProtocolVersionService.class);
    }

    private String getProtocolNumberIndex() {
        return this.getProtocol().getProtocolNumber().substring(11);
    }

    private ProtocolFinderDao getProtocolFinder() {
        return KraServiceLocator.getService(ProtocolFinderDao.class);
    }
    
    private DocumentService getDocumentService() {
        return KraServiceLocator.getService(DocumentService.class);
    }
    
    private BusinessObjectService getBusinessObjectService() {
        return KraServiceLocator.getService(BusinessObjectService.class);
    }

    /**
     * Amendments/Renewals have a protocol number with a 4 character suffix.
     * The first 10 characters is the protocol number of the original protocol.
     * @return
     */
    private String getOriginalProtocolNumber() {
        return getProtocol().getProtocolNumber().substring(0, 10);
    }

    /**
     * Has the document entered the final state in workflow?
     * @param statusChangeEvent
     * @return
     */
    private boolean isFinal(DocumentRouteStatusChangeDTO statusChangeEvent) {
        return StringUtils.equals(FINAL_STATE, statusChangeEvent.getNewRouteStatus());
    }
    
    /**
     * Has the document entered the disapproval state in workflow?
     * @param statusChangeEvent
     * @return
     */
    private boolean isDisapproved(DocumentRouteStatusChangeDTO statusChangeEvent) {
        return StringUtils.equals(DISAPPROVED_STATE, statusChangeEvent.getNewRouteStatus());
    }
    
    /**
     * Is this a renewal protocol document?
     * @return
     */
    private boolean isRenewal() {
        return getProtocol().getProtocolNumber().contains(RENEWAL_KEY);
    }

    /**
     * Is this an amendment protocol document?
     * @return
     */
    public boolean isAmendment() {
        return getProtocol().getProtocolNumber().contains(AMENDMENT_KEY);
    }
    
    /**
     * Is this a normal protocol document?
     * @return
     */
    public boolean isNormal() {
        return !isAmendment() && !isRenewal();
    }
    
    private static class ProtocolMergeException extends RuntimeException {
        ProtocolMergeException(Throwable t) {
            super(t);
        }
    }
}