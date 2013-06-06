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
package org.kuali.kra.protocol;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.authorization.KraAuthorizationConstants;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.bo.ResearchAreaBase;
import org.kuali.kra.bo.RolePersons;
import org.kuali.kra.common.notification.service.KcNotificationService;
import org.kuali.kra.document.ResearchDocumentBase;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.krms.KcKrmsConstants;
import org.kuali.kra.krms.KrmsRulesContext;
import org.kuali.kra.protocol.actions.ProtocolActionBase;
import org.kuali.kra.protocol.actions.genericactions.ProtocolGenericActionService;
import org.kuali.kra.protocol.actions.submit.ProtocolActionService;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase;
import org.kuali.kra.protocol.noteattachment.ProtocolAttachmentProtocolBase;
import org.kuali.kra.protocol.noteattachment.ProtocolAttachmentStatusBase;
import org.kuali.kra.protocol.notification.ProtocolNotification;
import org.kuali.kra.protocol.notification.ProtocolNotificationContextBase;
import org.kuali.kra.protocol.protocol.location.ProtocolLocationService;
import org.kuali.kra.protocol.protocol.research.ProtocolResearchAreaService;
import org.kuali.kra.service.KcPersonService;
import org.kuali.kra.service.KraAuthorizationService;
import org.kuali.rice.kew.actiontaken.ActionTakenValue;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kew.api.KewApiServiceLocator;
import org.kuali.rice.kew.framework.postprocessor.DocumentRouteStatusChange;
import org.kuali.rice.kew.routeheader.DocumentRouteHeaderValue;
import org.kuali.rice.kew.routeheader.service.RouteHeaderService;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.document.Copyable;
import org.kuali.rice.krad.document.SessionDocument;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.service.KRADServiceLocatorWeb;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.ObjectUtils;
import org.kuali.rice.krad.workflow.KualiDocumentXmlMaterializer;
import org.kuali.rice.krad.workflow.service.WorkflowDocumentService;

public abstract class ProtocolDocumentBase extends ResearchDocumentBase implements Copyable, SessionDocument, KrmsRulesContext {

    private static final String AMENDMENT_KEY = "A";
    private static final String RENEWAL_KEY = "R";
    @SuppressWarnings("unused")
    private static final String OLR_DOC_ID_PARAM = "&olrDocId=";

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 6493566444038807312L;
    
    private List<ProtocolBase> protocolList;
    private String protocolWorkflowType;
    private boolean reRouted = false;
    
    /**
     * Constructs a ProtocolDocumentBase object.
     */
    public ProtocolDocumentBase() { 
        super();
        protocolList = new ArrayList<ProtocolBase>();
        ProtocolBase newProtocol = createNewProtocolInstanceHook(); // direct instantiation replaced by hook invocation
        newProtocol.setProtocolDocument(this);
        protocolList.add(newProtocol);  
        setProtocolWorkflowType(ProtocolWorkflowType.NORMAL);        
        initializeProtocolLocation();
    }
    
    protected abstract ProtocolBase createNewProtocolInstanceHook();

         
    /**
     * 
     * @see org.kuali.kra.document.ResearchDocumentBase#initialize()
     */
    public void initialize() {
        super.initialize();
        Map<String, String> primaryKeys = new HashMap<String, String>();
        primaryKeys.put("RESEARCH_AREA_CODE", "000001");
        ResearchAreaBase ra = (ResearchAreaBase) this.getBusinessObjectService().findByPrimaryKey(getResearchAreaBoClassHook(), primaryKeys);
        Collection<ResearchAreaBase> selectedBOs = new ArrayList<ResearchAreaBase>();
        selectedBOs.add(ra);
        KraServiceLocator.getService(getProtocolResearchAreaServiceClassHook()).addProtocolResearchArea(this.getProtocol(), selectedBOs);
    }

    protected abstract Class<? extends ProtocolResearchAreaService> getProtocolResearchAreaServiceClassHook();
    protected abstract Class<? extends ResearchAreaBase> getResearchAreaBoClassHook();

    /**
     * 
     * This method is a convenience method for facilitating a 1:1 relationship between ProtocolDocumentBase 
     * and ProtocolBase to the outside world - aka a single ProtocolBase field associated with ProtocolDocumentBase
     * @return
     */
    public ProtocolBase getProtocol() {
        if (protocolList.size() == 0) return null;
        return protocolList.get(0);
    }

    /**
     * 
     * This method is a convenience method for facilitating a 1:1 relationship between ProtocolDocumentBase 
     * and ProtocolBase to the outside world - aka a single ProtocolBase field associated with ProtocolDocumentBase
     * @param protocol
     */
    public void setProtocol(ProtocolBase protocol) {
        protocolList.set(0, protocol);
    }


    /**
     * 
     * This method is used by OJB to get around with anonymous keys issue.
     * Warning : Developers should never use this method.
     * @return List<ProtocolBase>
     */
    public List<ProtocolBase> getProtocolList() {
        return protocolList;
    }

    /**
     * 
     * This method is used by OJB to get around with anonymous keys issue.
     * Warning : Developers should never use this method
     * @param protocolList
     */
    public void setProtocolList(List<ProtocolBase> protocolList) {
        this.protocolList = protocolList;
    }
    
    /**
     * @see org.kuali.core.bo.PersistableBusinessObjectBase#buildListOfDeletionAwareLists()
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
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
    
    
    public abstract String getDocumentTypeCode();
    
    public String getProtocolWorkflowType() {
        return protocolWorkflowType;
    }

    public void setProtocolWorkflowType(ProtocolWorkflowType protocolWorkflowType) {
        this.protocolWorkflowType = protocolWorkflowType.getName();
    }
    
    /**
     * @see org.kuali.rice.krad.document.DocumentBase#doRouteStatusChange(org.kuali.rice.kew.framework.postprocessor.DocumentRouteStatusChange)
     */
    @Override
    public void doRouteStatusChange(DocumentRouteStatusChange statusChangeEvent) {
        super.doRouteStatusChange(statusChangeEvent);
        if (isFinal(statusChangeEvent)) {
            // this is implementing option#1 for kcinfr-30.  save original usersession person
            // after merge is done, then reset to the original usersession person.  
            // this is workaround for async.  There will be rice enhancement to resolve this issue.
            try {
                DocumentRouteHeaderValue document = KraServiceLocator.getService(RouteHeaderService.class).getRouteHeader(
                        this.getDocumentHeader().getWorkflowDocument().getDocumentId());
                String principalId = document.getActionsTaken().get(document.getActionsTaken().size() - 1).getPrincipalId();
                String asyncPrincipalId = GlobalVariables.getUserSession().getPrincipalId();
                String asyncPrincipalName = GlobalVariables.getUserSession().getPrincipalName();
                if (!principalId.equals(asyncPrincipalId)) {
                    KcPerson person = KraServiceLocator.getService(KcPersonService.class).getKcPersonByPersonId(principalId);
                    GlobalVariables.setUserSession(new UserSession(person.getUserName()));                    
                }
                
                mergeProtocolAmendment();
                
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
            try {
                // create editable version of protocol and document if appropriate in this disapproval context            
                performVersioningOperationsOnProtocolAfterDisapproval();
            }
            catch (Exception e) {
                // TODO Need to figure out what to do if the versioning throws exceptions
                e.printStackTrace();
            }
        } else if (isRecall(statusChangeEvent)) {
            getProtocolGenericActionService().recall(getProtocol());
        }
    }
    
  
    // we will  record actions, update statuses, version protocol doc/bo and send out notifications only for 
    // non-committee disapprovals, i.e. those that happen in routing or via superuser actions
    protected void performVersioningOperationsOnProtocolAfterDisapproval() throws Exception {
       ProtocolBase protocol = this.getProtocol();
       // check to ensure that its not a committee disapproval
       if( !StringUtils.equals(getCommitteeDisapprovedStatusCodeHook(), protocol.getProtocolStatusCode()) ) {     
            // use the protocol generic action service to attach the 'rejected in routing' action to the action history
            getProtocolGenericActionService().recordDisapprovedInRoutingActionAndUpdateStatuses(protocol, getLatestCurrentActionTakenValue());
            // use the protocol generic action service to version the document (and the contained protocol)
            ProtocolDocumentBase newDocument = getProtocolGenericActionService().versionAfterDisapprovalInRouting(protocol);
            // switch to newly versioned protocol and use the kc notification service to send notifications: 
            // first call hooks to customize the notifications
            protocol =  newDocument.getProtocol();
            ProtocolNotificationContextBase context = getDisapproveNotificationContextHook(protocol);
            ProtocolNotification notification = getNewProtocolNotificationInstanceHook();
            getNotificationService().sendNotificationAndPersist(context, notification, protocol);
        }
    }

    protected abstract String getCommitteeDisapprovedStatusCodeHook();

    protected abstract ProtocolNotification getNewProtocolNotificationInstanceHook();

    protected abstract ProtocolNotificationContextBase getDisapproveNotificationContextHook(ProtocolBase protocol);
    
    
    // get the most recent current action instance from the action list
    protected final ActionTakenValue getLatestCurrentActionTakenValue() {
        ActionTakenValue latestCurrentActionTakenVal = null;
        DocumentRouteHeaderValue routeHeaderValue = getRouteHeaderService().getRouteHeader(this.getDocumentHeader().getWorkflowDocument().getDocumentId());
        List<ActionTakenValue> actionsTakenList = routeHeaderValue.getActionsTaken();         
        for(ActionTakenValue actionTakenVal: actionsTakenList) {
            if(actionTakenVal.getCurrentIndicator() && 
                    ( (latestCurrentActionTakenVal == null) || (actionTakenVal.getActionDate().after(latestCurrentActionTakenVal.getActionDate())) )) {
                latestCurrentActionTakenVal = actionTakenVal; 
            }
        }
        return latestCurrentActionTakenVal;
    }
    
    protected RouteHeaderService getRouteHeaderService() {
        return KraServiceLocator.getService(RouteHeaderService.class);
    }
    
    protected KcNotificationService getNotificationService() {
        return KraServiceLocator.getService(KcNotificationService.class);
    }
    
    protected ProtocolGenericActionService getProtocolGenericActionService() {
        return KraServiceLocator.getService(getProtocolGenericActionServiceClassHook());
    }
    
    protected abstract Class<? extends ProtocolGenericActionService> getProtocolGenericActionServiceClassHook();
    
        

    protected abstract void mergeProtocolAmendment();
    
    /**
     * Add a new protocol action to the protocol and update the status.
     * @param actionTypeCode the new action
     */
    public void updateProtocolStatus(String actionTypeCode, String comments) {
        ProtocolActionBase protocolAction = getNewProtocolActionInstanceHook(getProtocol(), null, actionTypeCode);
        protocolAction.setComments(comments);
        getProtocol().getProtocolActions().add(protocolAction);
        
        getProtocolActionService().updateProtocolStatus(protocolAction, getProtocol());
    }
    
    protected abstract ProtocolActionBase getNewProtocolActionInstanceHook(ProtocolBase protocol, ProtocolSubmissionBase protocolSubmission, String actionTypeCode);

    
    protected ProtocolActionService getProtocolActionService() {
        return KraServiceLocator.getService(getProtocolActionServiceClassHook());
    }
    
    protected abstract Class<? extends ProtocolActionService> getProtocolActionServiceClassHook();
    
    /*
     * This method is to make the document status of the attachment protocol to "finalized" 
     */
    protected void finalizeAttachmentProtocol(ProtocolBase protocol) {
        for (ProtocolAttachmentProtocolBase attachment : protocol.getAttachmentProtocols()) {
            attachment.setProtocol(protocol);
            if (attachment.isDraft()) {
                attachment.setDocumentStatusCode(ProtocolAttachmentStatusBase.FINALIZED);
            }
        }
    }

    protected String getProtocolNumberIndex() {
        return this.getProtocol().getProtocolNumber().substring(11);
    }

    protected DocumentService getDocumentService() {
        return KraServiceLocator.getService(DocumentService.class);
    }
    
    protected BusinessObjectService getBusinessObjectService() {
        return KraServiceLocator.getService(BusinessObjectService.class);
    }

    
    /**
     * Amendments/Renewals have a protocol number with a 4 character suffix.
     * The first 10 characters is the protocol number of the original protocol.
     * @return
     */
    protected String getOriginalProtocolNumber() {
        return getProtocol().getProtocolNumber().substring(0, 10);
    }

    /**
     * Has the document entered the final state in workflow?
     * @param statusChangeEvent
     * @return
     */
    protected boolean isFinal(DocumentRouteStatusChange statusChangeEvent) {
        return StringUtils.equals(KewApiConstants.ROUTE_HEADER_FINAL_CD, statusChangeEvent.getNewRouteStatus());
    }
    
    /**
     * Has the document entered the disapproval state in workflow?
     * @param statusChangeEvent
     * @return
     */
    protected boolean isDisapproved(DocumentRouteStatusChange statusChangeEvent) {
        return StringUtils.equals(KewApiConstants.ROUTE_HEADER_DISAPPROVED_CD, statusChangeEvent.getNewRouteStatus());
    }
    
    protected boolean isRecall(DocumentRouteStatusChange statusChangeEvent) {
        return !StringUtils.equals(KewApiConstants.ROUTE_HEADER_INITIATED_CD, statusChangeEvent.getOldRouteStatus()) 
                && StringUtils.equals(KewApiConstants.ROUTE_HEADER_SAVED_CD, statusChangeEvent.getNewRouteStatus());
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
    @SuppressWarnings("unused")
    private boolean isComplete(DocumentRouteStatusChange statusChangeEvent) {
        return (StringUtils.equals(KewApiConstants.ROUTE_HEADER_ENROUTE_CD, statusChangeEvent.getNewRouteStatus()) && 
                StringUtils.equals(KewApiConstants.ROUTE_HEADER_SAVED_CD, statusChangeEvent.getOldRouteStatus()));
    }

    public static class ProtocolMergeException extends RuntimeException {
        /**
         * Comment for <code>serialVersionUID</code>
         */
        private static final long serialVersionUID = 8370108752465881796L;

        public ProtocolMergeException(Throwable t) {
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
        KraServiceLocator.getService(getProtocolLocationServiceClassHook()).addDefaultProtocolLocation(this.getProtocol());
    }
    
    protected abstract Class<? extends ProtocolLocationService> getProtocolLocationServiceClassHook();

    
         
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
        String updatedTimestamp = "";
        if (this.getUpdateTimestamp() != null) {
            updatedTimestamp = (new SimpleDateFormat("MM/dd/yyyy KK:mm a").format(this.getUpdateTimestamp()));
        }
        if (StringUtils.isNotEmpty(activeLockRegion)) {
            return this.getProtocol().getProtocolNumber() + "-" + activeLockRegion + "-" + GlobalVariables.getUserSession().getPrincipalName() + "-" + updatedTimestamp;  
        }

        return null;
    }
    
    public boolean getReRouted() {
        return reRouted;
    }

    public void setReRouted(boolean reRouted) {
        this.reRouted = reRouted;
    }

    
    
    protected WorkflowDocumentService getWorkflowDocumentService() {
        return KRADServiceLocatorWeb.getWorkflowDocumentService();
    }
    
    /**
     * This method returns the doc number of the current active protocol
     * @return documentNumber
     */
    @SuppressWarnings("unchecked")
    protected String getNewProtocolDocId() {
        Map<String, String> keyMap = new HashMap<String, String>(); 
        keyMap.put("protocolNumber", getProtocol().getAmendedProtocolNumber());
        keyMap.put("active", "Y");
        BusinessObjectService boService = KraServiceLocator.getService(BusinessObjectService.class);        
        List<ProtocolBase> protocols = (List<ProtocolBase>) boService.findMatchingOrderBy(getProtocolBOClassHook(), keyMap, "sequenceNumber", false);
        return (protocols.size() == 0) ? null : protocols.get(0).getProtocolDocument().getDocumentNumber();    
    }

    protected abstract Class<? extends ProtocolBase> getProtocolBOClassHook();

    @Override
    public void populateAgendaQualifiers(Map<String, String> qualifiers) {
        qualifiers.put(KcKrmsConstants.UNIT_NUMBER, getProtocol().getLeadUnitNumber());
    }
}
