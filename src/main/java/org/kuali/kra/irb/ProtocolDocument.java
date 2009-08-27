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
import org.kuali.kra.bo.DocumentNextvalue;
import org.kuali.kra.bo.RolePersons;
import org.kuali.kra.document.ResearchDocumentBase;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.ProtocolStatus;
import org.kuali.kra.irb.actions.submit.ProtocolActionService;
import org.kuali.kra.irb.auth.ProtocolAuthorizationService;
import org.kuali.kra.service.VersioningService;
import org.kuali.rice.kew.dto.DocumentRouteStatusChangeDTO;
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
    public void doRouteStatusChange(DocumentRouteStatusChangeDTO statusChangeEvent) throws Exception {
        super.doRouteStatusChange(statusChangeEvent);
        if (isFinal(statusChangeEvent)) {
            if (isAmendment()) {
                mergeAmendment(ProtocolStatus.AMENDMENT_MERGED);
            }
            else if (isRenewal()) {
                mergeAmendment(ProtocolStatus.RENEWAL_MERGED);
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
    private void mergeAmendment(String protocolStatusCode) throws Exception {
        Protocol currentProtocol = getProtocolFinder().findCurrentProtocolByNumber(getOriginalProtocolNumber());
        ProtocolDocument newProtocolDocument = createVersion(currentProtocol.getProtocolDocument());
        newProtocolDocument.getProtocol().merge(getProtocol());
        getProtocol().setProtocolStatusCode(protocolStatusCode);
        
        getDocumentService().saveDocument(newProtocolDocument);
        
        /*
         * TODO: We have to route the new protocol document here so
         * that it goes to the final state.
         */
     
        getBusinessObjectService().save(this);
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

    private String getOriginalProtocolNumber() {
        return getProtocol().getProtocolNumber().substring(0, 10);
    }

    private boolean isFinal(DocumentRouteStatusChangeDTO statusChangeEvent) {
        return StringUtils.equals(FINAL_STATE, statusChangeEvent.getNewRouteStatus());
    }
    
    private boolean isDisapproved(DocumentRouteStatusChangeDTO statusChangeEvent) {
        return StringUtils.equals(DISAPPROVED_STATE, statusChangeEvent.getNewRouteStatus());
    }
    
    private boolean isRenewal() {
        return getProtocol().getProtocolNumber().contains("R");
    }

    public boolean isAmendment() {
        return getProtocol().getProtocolNumber().contains("A");
    }
    
    public boolean isNormal() {
        return !isAmendment() && !isRenewal();
    }
    
    private ProtocolDocument createVersion(ProtocolDocument oldDoc) throws Exception {
        VersioningService versioningService = KraServiceLocator.getService(VersioningService.class);
        Protocol newVersion = versioningService.createNewVersion(oldDoc.getProtocol());
        
        ProtocolDocument protocolDocument = (ProtocolDocument) getDocumentService().getNewDocument(ProtocolDocument.class);
        protocolDocument.getDocumentHeader().setDocumentDescription(oldDoc.getDocumentHeader().getDocumentDescription());
      
        fixNextValues(oldDoc, protocolDocument);
        protocolDocument.setProtocol(newVersion);
        newVersion.setProtocolDocument(protocolDocument);
        
        return protocolDocument;
    }

    private void fixNextValues(ProtocolDocument oldDoc, ProtocolDocument newDoc) {
        List<DocumentNextvalue> newNextValues = new ArrayList<DocumentNextvalue>();
        List<DocumentNextvalue> oldNextValues = oldDoc.getDocumentNextvalues();
        for (DocumentNextvalue oldNextValue : oldNextValues) {
            DocumentNextvalue newNextValue = new DocumentNextvalue();
            newNextValue.setPropertyName(oldNextValue.getPropertyName());
            newNextValue.setNextValue(oldNextValue.getNextValue());
            newNextValue.setDocumentKey(newDoc.getDocumentNumber());
            newNextValues.add(newNextValue);
        }
        newDoc.setDocumentNextvalues(newNextValues);
    }
}