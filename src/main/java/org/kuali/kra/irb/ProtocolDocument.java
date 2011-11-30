/*
 * Copyright 2005-2010 The Kuali Foundation
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
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;
import org.kuali.kra.authorization.KraAuthorizationConstants;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.bo.ResearchArea;
import org.kuali.kra.bo.RolePersons;
import org.kuali.kra.document.ResearchDocumentBase;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.ProtocolStatus;
import org.kuali.kra.irb.actions.submit.ProtocolActionService;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionStatus;
import org.kuali.kra.irb.noteattachment.ProtocolAttachmentProtocol;
import org.kuali.kra.irb.protocol.location.ProtocolLocationService;
import org.kuali.kra.irb.protocol.research.ProtocolResearchAreaService;
import org.kuali.kra.service.KcPersonService;
import org.kuali.kra.service.KraAuthorizationService;
import org.kuali.rice.kew.dto.DocumentRouteStatusChangeDTO;
import org.kuali.rice.kew.exception.WorkflowException;
import org.kuali.rice.kew.routeheader.DocumentRouteHeaderValue;
import org.kuali.rice.kew.routeheader.service.RouteHeaderService;
import org.kuali.rice.kew.util.KEWConstants;
import org.kuali.rice.kim.bo.Person;
import org.kuali.rice.kns.UserSession;
import org.kuali.rice.kns.document.Copyable;
import org.kuali.rice.kns.document.SessionDocument;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.kns.service.ParameterConstants;
import org.kuali.rice.kns.service.ParameterConstants.COMPONENT;
import org.kuali.rice.kns.service.ParameterConstants.NAMESPACE;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.ObjectUtils;
import org.kuali.rice.kns.workflow.KualiDocumentXmlMaterializer;

/**
 * 
 * This class represents the Protocol Document Object.
 * ProtocolDocument has a 1:1 relationship with Protocol Business Object.
 * We have declared a list of Protocol BOs in the ProtocolDocument at the same time to
 * get around the OJB anonymous keys issue of primary keys of different data types.
 * Also we have provided convenient getter and setter methods so that to the outside world;
 * Protocol and ProtocolDocument can have a 1:1 relationship.
 */
@NAMESPACE(namespace=Constants.MODULE_NAMESPACE_PROTOCOL)
@COMPONENT(component=ParameterConstants.DOCUMENT_COMPONENT)
public class ProtocolDocument extends ResearchDocumentBase implements Copyable, SessionDocument { 
    private static final Log LOG = LogFactory.getLog(ProtocolDocument.class);
    public static final String DOCUMENT_TYPE_CODE = "PROT";
    private static final String AMENDMENT_KEY = "A";
    private static final String RENEWAL_KEY = "R";
    private static final String OLR_DOC_ID_PARAM = "&olrDocId=";
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 803158468103165087L;
    
    private static final String APPROVED_COMMENT = "Approved";
    private static final String DISAPPROVED_COMMENT = "Disapproved";
    
    private List<Protocol> protocolList;
    private String protocolWorkflowType;
	private boolean reRouted = false;
	
    /**
     * Constructs a ProtocolDocument object.
     */
	public ProtocolDocument() { 
        super();
        protocolList = new ArrayList<Protocol>();
        Protocol newProtocol = new Protocol();
        newProtocol.setProtocolDocument(this);
        protocolList.add(newProtocol);
        setProtocolWorkflowType(ProtocolWorkflowType.NORMAL);
        initializeProtocolLocation();
	} 
	
	/**
	 * 
	 * @see org.kuali.kra.document.ResearchDocumentBase#initialize()
	 */
    public void initialize() {
        super.initialize();
        Map<String, String> primaryKeys = new HashMap<String, String>();
        primaryKeys.put("RESEARCH_AREA_CODE", "000001");
        ResearchArea ra = (ResearchArea) this.getBusinessObjectService().findByPrimaryKey(ResearchArea.class, primaryKeys);
        Collection<ResearchArea> selectedBOs = new ArrayList<ResearchArea>();
        selectedBOs.add(ra);
        KraServiceLocator.getService(ProtocolResearchAreaService.class).addProtocolResearchArea(this.getProtocol(), selectedBOs);
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
        KraAuthorizationService kraAuthService = 
               (KraAuthorizationService) KraServiceLocator.getService(KraAuthorizationService.class); 
        return kraAuthService.getAllRolePersons(getProtocol());
    }
    
    public String getDocumentTypeCode() {
        return DOCUMENT_TYPE_CODE;
    }
    
    public String getProtocolWorkflowType() {
		return protocolWorkflowType;
	}

	public void setProtocolWorkflowType(ProtocolWorkflowType protocolWorkflowType) {
		this.protocolWorkflowType = protocolWorkflowType.getName();
	}
    
    /**
     * @see org.kuali.rice.kns.document.DocumentBase#doRouteStatusChange(org.kuali.rice.kew.dto.DocumentRouteStatusChangeDTO)
     */
    @Override
    public void doRouteStatusChange(DocumentRouteStatusChangeDTO statusChangeEvent) {
        super.doRouteStatusChange(statusChangeEvent);
        if (isFinal(statusChangeEvent)) {
            // this is implementing option#1 for kcinfr-30.  save original usersession person
            // after merge is done, then reset to the original usersession person.  
            // this is workaround for async.  There will be rice enhancement to resolve this issue.
            try {
                DocumentRouteHeaderValue document = KraServiceLocator.getService(RouteHeaderService.class).getRouteHeader(
                        this.getDocumentHeader().getWorkflowDocument().getRouteHeaderId());
                String principalId = document.getActionsTaken().get(document.getActionsTaken().size() - 1).getPrincipalId();
                String asyncPrincipalId = GlobalVariables.getUserSession().getPrincipalId();
                String asyncPrincipalName = GlobalVariables.getUserSession().getPrincipalName();
                if (!principalId.equals(asyncPrincipalId)) {
                    KcPerson person = KraServiceLocator.getService(KcPersonService.class).getKcPersonByPersonId(principalId);
                    GlobalVariables.setUserSession(new UserSession(person.getUserName()));                    
                }
                if (isAmendment()) {
                    mergeAmendment(ProtocolStatus.AMENDMENT_MERGED, "Amendment");
                }
                else if (isRenewal()) {
                    mergeAmendment(ProtocolStatus.RENEWAL_MERGED, "Renewal");
                }
                
                if (!principalId.equals(asyncPrincipalId)) {
                    GlobalVariables.setUserSession(new UserSession(asyncPrincipalName));                    
                }
            }
            catch (Exception e) {

            }
        }
        else if (isDisapproved(statusChangeEvent)) { 
            if (!isNormal()){
                this.getProtocol().setActive(false);
                getBusinessObjectService().save(this);
            }
        }
    }
    
    /**
     * Add a new protocol action to the protocol and update the status.
     * @param actionTypeCode the new action
     */
    public void updateProtocolStatus(String actionTypeCode, String comments) {
        ProtocolAction protocolAction = new ProtocolAction(getProtocol(), null, actionTypeCode);
        protocolAction.setComments(comments);
        getProtocol().getProtocolActions().add(protocolAction);
        
        getProtocolActionService().updateProtocolStatus(protocolAction, getProtocol());
    }
    
    private ProtocolActionService getProtocolActionService() {
        return KraServiceLocator.getService(ProtocolActionService.class);
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
            // workflowdocument is null, so need to use documentservice to retrieve it
            currentProtocol.setProtocolDocument((ProtocolDocument)getDocumentService().getByDocumentHeaderId(currentProtocol.getProtocolDocument().getDocumentNumber()));
            currentProtocol.setMergeAmendment(true);
            newProtocolDocument = getProtocolVersionService().versionProtocolDocument(currentProtocol.getProtocolDocument());
        } catch (Exception e) {
            throw new ProtocolMergeException(e);
        }
        
        newProtocolDocument.getProtocol().merge(getProtocol());
        getProtocol().setProtocolStatusCode(protocolStatusCode);
        
        ProtocolAction action = new ProtocolAction(newProtocolDocument.getProtocol(), null, ProtocolActionType.APPROVED);
        action.setComments(type + "-" + getProtocolNumberIndex() + ": Approved");
        newProtocolDocument.setProtocolWorkflowType(ProtocolWorkflowType.APPROVED);
        newProtocolDocument.getProtocol().getProtocolActions().add(action);
        if (!currentProtocol.getProtocolStatusCode().equals(ProtocolStatus.EXEMPT)) {
            newProtocolDocument.getProtocol().setProtocolStatusCode(ProtocolStatus.ACTIVE_OPEN_TO_ENROLLMENT);
        }
        try {
            getDocumentService().saveDocument(newProtocolDocument);
            // blanket approve to make the new protocol document 'final'
            newProtocolDocument.getDocumentHeader().getWorkflowDocument().routeDocument(type + "-" + getProtocolNumberIndex() + ": merged");
        } catch (WorkflowException e) {
            throw new ProtocolMergeException(e);
        }
        
        this.getProtocol().setActive(false);
        
        finalizeAttachmentProtocol(this.getProtocol());
        getBusinessObjectService().save(this);
    }
    
    /*
     * This method is to make the document status of the attachment protocol to "finalized" 
     */
    private void finalizeAttachmentProtocol(Protocol protocol) {
        for (ProtocolAttachmentProtocol attachment : protocol.getAttachmentProtocols()) {
            attachment.setProtocol(protocol);
            if ("1".equals(attachment.getDocumentStatusCode())) {
                attachment.setDocumentStatusCode("2");
            }
        }
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
        return StringUtils.equals(KEWConstants.ROUTE_HEADER_FINAL_CD, statusChangeEvent.getNewRouteStatus());
    }
    
    /**
     * Has the document entered the disapproval state in workflow?
     * @param statusChangeEvent
     * @return
     */
    private boolean isDisapproved(DocumentRouteStatusChangeDTO statusChangeEvent) {
        return StringUtils.equals(KEWConstants.ROUTE_HEADER_DISAPPROVED_CD, statusChangeEvent.getNewRouteStatus());
    }
    
    /**
     * Is this a renewal protocol document?
     * @return
     */
    public boolean isRenewal() {
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
    
    /**
     * Has the document been submitted to workflow now
     * @param statusChangeEvent
     * @return
     */
    private boolean isComplete(DocumentRouteStatusChangeDTO statusChangeEvent) {
        return (StringUtils.equals(KEWConstants.ROUTE_HEADER_ENROUTE_CD, statusChangeEvent.getNewRouteStatus()) && 
                StringUtils.equals(KEWConstants.ROUTE_HEADER_SAVED_CD, statusChangeEvent.getOldRouteStatus()));
    }

    private static class ProtocolMergeException extends RuntimeException {
        ProtocolMergeException(Throwable t) {
            super(t);
        }
    }
    
    /**
     * Contains all the property names in this class.
     */
    public static enum ProtocolWorkflowType {
        NORMAL("Normal"), APPROVED("Approved"), APPROVED_AMENDMENT("ApprovedAmendment");
        
        private final String name;
        
        /**
         * Sets the enum properties.
         * @param name the name.
         */
        ProtocolWorkflowType(final String name) {
            this.name = name;
        }
        
        /**
         * Gets the ProtocolWorkflowType name.
         * @return the the ProtocolWorkflowType name.
         */
        public String getName() {
            return this.name;
        }
        
        /**
         * Gets the {@link #getName() }.
         * @return {@link #getName() }
         */
        @Override
        public String toString() {
            return this.name;
        }
    }

    @Override
    public void prepareForSave() {
        super.prepareForSave();
        if (ObjectUtils.isNull(this.getVersionNumber())) {
            this.setVersionNumber(new Long(0));
        }
    }
    
    /*
     * Initialize protocol location.
     * Add default organization.
     */
    private void initializeProtocolLocation() {
        KraServiceLocator.getService(ProtocolLocationService.class).addDefaultProtocolLocation(this.getProtocol());
    }
 
    @Override
    public KualiDocumentXmlMaterializer wrapDocumentWithMetadataForXmlSerialization() {
        this.getProtocol().getLeadUnitNumber();
        return super.wrapDocumentWithMetadataForXmlSerialization();
    }
    
    /** {@inheritDoc} */
    @Override
    public boolean useCustomLockDescriptors() {
        return true;
    }

    /** {@inheritDoc} */
    @Override
    public String getCustomLockDescriptor(Person user) {
        String activeLockRegion = (String) GlobalVariables.getUserSession().retrieveObject(KraAuthorizationConstants.ACTIVE_LOCK_REGION);
        if (StringUtils.isNotEmpty(activeLockRegion)) {
            return this.getDocumentNumber() + "-" + activeLockRegion; 
        }

        return null;
    }
    
    public boolean getReRouted() {
        return reRouted;
    }

    public void setReRouted(boolean reRouted) {
        this.reRouted = reRouted;
    }

    /**
     * 
     * This method is to check whether rice async routing is ok now.   
     * Close to hack.  called by holdingpageaction
     * Different document type may have different routing set up, so each document type
     * can implement its own isProcessComplete
     * @return
     * @throws WorkflowException 
     */
    public boolean isProcessComplete() {
       
        boolean isComplete = true;

        /*
         * This happens when you submit your protocol to IRB, the current route node is Initiated
         */
        if (this.getProtocol().getProtocolStatusCode().equals(ProtocolStatus.SUBMITTED_TO_IRB)) {

            if (getDocumentHeader().getWorkflowDocument().getCurrentRouteNodeNames().equalsIgnoreCase(Constants.PROTOCOL_INITIATED_ROUTE_NODE_NAME)) { 
                isComplete = false;
            }    
            // The following code is in place to go to the holding page when a 
            // while submitting an amendment for IRB review, the amendment moves from node Initiated to node IRBReview, 
            //so need to check if protocolSubmissionStatus to avoid the processing page from not going away at all when 
            // an amendment is submitted for review
            // Added for KCIRB-1515 & KCIRB-1528
            getProtocol().getProtocolSubmission().refreshReferenceObject("submissionStatus"); 
            String status = getProtocol().getProtocolSubmission().getSubmissionStatusCode();
            if (isAmendment()) {              
                if (status.equals(ProtocolSubmissionStatus.APPROVED)
                        && getDocumentHeader().getWorkflowDocument().getCurrentRouteNodeNames().equalsIgnoreCase(Constants.PROTOCOL_IRBREVIEW_ROUTE_NODE_NAME)) {                    
                        isComplete = false;
                }
            }

        } else {  
            /*
             * If amendment has been merged, need to redirect to the newly created active protocol
             * Wait for the new active protocol to be created before redirecting to it.
             */
            if (getProtocol().getProtocolStatusCode().equals(ProtocolStatus.AMENDMENT_MERGED)) {
                String protocolId = getNewProtocolDocId();               
                if (ObjectUtils.isNull(protocolId)) {
                    isComplete = false;
                } else {
                    /*
                     * The new protocol document is only available after the amendment has been merged. So, once the amendment is merged,
                     * find the active protocol available and change the return link to point to that.
                     */
                    String oldLocation = (String) GlobalVariables.getUserSession().retrieveObject(Constants.HOLDING_PAGE_RETURN_LOCATION);
                    String oldDocNbr = getProtocol().getProtocolDocument().getDocumentNumber();
                    String returnLocation = oldLocation.replaceFirst(oldDocNbr, protocolId);
                    GlobalVariables.getUserSession().addObject(Constants.HOLDING_PAGE_RETURN_LOCATION, (Object) returnLocation);
                }
            }
            // approve/expedited approve/response approve
            if (!KEWConstants.ROUTE_HEADER_FINAL_CD.equals(this.getDocumentHeader().getWorkflowDocument().getRouteHeader().getDocRouteStatus())) {
                isComplete = false;
            } 
        }


        return isComplete;
    }
    
    /**
     * This method returns the doc number of the current active protocol
     * @return documentNumber
     */
    protected String getNewProtocolDocId() {
        Map keyMap = new HashMap(); 
        keyMap.put("protocolNumber", getProtocol().getAmendedProtocolNumber());
        keyMap.put("active", "Y");
        BusinessObjectService boService = KraServiceLocator.getService(BusinessObjectService.class);        
        List<Protocol> protocols = (List<Protocol>) boService.findMatchingOrderBy(Protocol.class, keyMap, "sequenceNumber", false);
        return (protocols.size() == 0) ? null : protocols.get(0).getProtocolDocument().getDocumentNumber();    
    }
}
