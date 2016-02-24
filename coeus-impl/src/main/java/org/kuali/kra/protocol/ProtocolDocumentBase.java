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
package org.kuali.kra.protocol;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.common.notification.impl.service.KcNotificationService;
import org.kuali.coeus.sys.framework.model.KcTransactionalDocumentBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.bo.ResearchAreaBase;
import org.kuali.kra.krms.KcKrmsConstants;
import org.kuali.coeus.common.framework.krms.KrmsRulesContext;
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
import org.kuali.rice.kew.actiontaken.ActionTakenValue;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kew.framework.postprocessor.DocumentRouteStatusChange;
import org.kuali.rice.kew.routeheader.DocumentRouteHeaderValue;
import org.kuali.rice.kew.routeheader.service.RouteHeaderService;
import org.kuali.rice.krad.document.Copyable;
import org.kuali.rice.krad.document.SessionDocument;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.service.KRADServiceLocatorWeb;
import org.kuali.rice.krad.workflow.KualiDocumentXmlMaterializer;
import org.kuali.rice.krad.workflow.service.WorkflowDocumentService;

import java.util.*;

public abstract class ProtocolDocumentBase extends KcTransactionalDocumentBase implements Copyable, SessionDocument, KrmsRulesContext {

    private static final Log LOG = LogFactory.getLog(ProtocolDocumentBase.class);

    @SuppressWarnings("unused")
    private static final String OLR_DOC_ID_PARAM = "&olrDocId=";


    private static final long serialVersionUID = 6493566444038807312L;
    
    private List<ProtocolBase> protocolList;
    private String protocolWorkflowType;
    private boolean reRouted = false;
    public ProtocolLocationService protocolLocationService;
    

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

         
    @Override
    public void initialize() {
        super.initialize();
        Map<String, String> primaryKeys = new HashMap<String, String>();
        primaryKeys.put("RESEARCH_AREA_CODE", "000001");
        ResearchAreaBase ra = (ResearchAreaBase) this.getBusinessObjectService().findByPrimaryKey(getResearchAreaBoClassHook(), primaryKeys);
        Collection<ResearchAreaBase> selectedBOs = new ArrayList<ResearchAreaBase>();
        selectedBOs.add(ra);
        KcServiceLocator.getService(getProtocolResearchAreaServiceClassHook()).addProtocolResearchArea(this.getProtocol(), selectedBOs);
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
     * @return List&lt;ProtocolBase&gt;
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
    
    
    public abstract String getDocumentTypeCode();
    
    public String getProtocolWorkflowType() {
        return protocolWorkflowType;
    }

    public void setProtocolWorkflowType(ProtocolWorkflowType protocolWorkflowType) {
        this.protocolWorkflowType = protocolWorkflowType.getName();
    }
    
    @Override
    public void doRouteStatusChange(DocumentRouteStatusChange statusChangeEvent) {

        executeAsLastActionUser(() -> {
            super.doRouteStatusChange(statusChangeEvent);
            if (isFinal(statusChangeEvent)) {
                    mergeProtocolAmendment();
            } else if (isDisapproved(statusChangeEvent)) {
                if (!isNormal()) {
                    this.getProtocol().setActive(false);
                    getBusinessObjectService().save(this);
                }
                try {
                    // create editable version of protocol and document if appropriate in this disapproval context
                    performVersioningOperationsOnProtocolAfterDisapproval();
                } catch (Exception e) {
                    // TODO Need to figure out what to do if the versioning throws exceptions
                    LOG.error(e.getMessage(), e);
                }
            } else if (isRecall(statusChangeEvent)) {
                getProtocolGenericActionService().recall(getProtocol());
            }
            return null;
        });
    }
    
    protected DocumentRouteHeaderValue getDocumentRouteHeaderValue() {
        return KcServiceLocator.getService(RouteHeaderService.class).getRouteHeader(
                this.getDocumentHeader().getWorkflowDocument().getDocumentId());
    }
  
    protected void setProtocolDocumentToApproveByDefault() {
        DocumentRouteHeaderValue document = getDocumentRouteHeaderValue();
        document.getDocumentType().getDefaultApprovePolicy().setPolicyValue(true);
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
        return KcServiceLocator.getService(RouteHeaderService.class);
    }
    
    protected KcNotificationService getNotificationService() {
        return KcServiceLocator.getService(KcNotificationService.class);
    }
    
    protected ProtocolGenericActionService getProtocolGenericActionService() {
        return KcServiceLocator.getService(getProtocolGenericActionServiceClassHook());
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
        return KcServiceLocator.getService(getProtocolActionServiceClassHook());
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
        return KcServiceLocator.getService(DocumentService.class);
    }
    
    protected BusinessObjectService getBusinessObjectService() {
        return KcServiceLocator.getService(BusinessObjectService.class);
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
        return getProtocol().isRenewal();
    }

    /**
     * Is this an amendment protocol document?
     * @return
     */
    public boolean isAmendment() {
        return getProtocol().isAmendment();
    }
    
    /**
     * Is this an amendment protocol document?
     * @return
     */
    public boolean isRenewalWithAmendment() {
        return getProtocol().isRenewalWithAmendment();
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

    /*
     * Initialize protocol location.
     * Add default organization.
     */
    private void initializeProtocolLocation() {
    	//workaround for services called in a ctor
        if (getProtocolLocationService() != null) {
            getProtocolLocationService().addDefaultProtocolLocation(this.getProtocol());
        }
    }
    
    protected abstract Class<? extends ProtocolLocationService> getProtocolLocationServiceClassHook();

    
         
    @Override
    public KualiDocumentXmlMaterializer wrapDocumentWithMetadataForXmlSerialization() {
        this.getProtocol().getLeadUnitNumber();
        return super.wrapDocumentWithMetadataForXmlSerialization();
    }
    
    public String getDocumentBoNumber() {
        return getProtocol().getProtocolNumber();
        
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
        BusinessObjectService boService = KcServiceLocator.getService(BusinessObjectService.class);
        List<ProtocolBase> protocols = (List<ProtocolBase>) boService.findMatchingOrderBy(getProtocolBOClassHook(), keyMap, "sequenceNumber", false);
        return (protocols.size() == 0) ? null : protocols.get(0).getProtocolDocument().getDocumentNumber();    
    }

    protected abstract Class<? extends ProtocolBase> getProtocolBOClassHook();

    @Override
    public void populateAgendaQualifiers(Map<String, String> qualifiers) {
        qualifiers.put(KcKrmsConstants.UNIT_NUMBER, getProtocol().getLeadUnitNumber());
    }

	public ProtocolLocationService getProtocolLocationService() {
		if (protocolLocationService == null) {
			return KcServiceLocator.getService(getProtocolLocationServiceClassHook());
		}		
		return protocolLocationService;
	}

	public void setProtocolLocationService(
			ProtocolLocationService protocolLocationService) {
		this.protocolLocationService = protocolLocationService;
	}
}
