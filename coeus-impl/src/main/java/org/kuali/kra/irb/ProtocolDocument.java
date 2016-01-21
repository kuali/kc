/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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

package org.kuali.kra.irb;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.common.framework.custom.DocumentCustomData;
import org.kuali.coeus.common.framework.custom.attr.CustomAttributeDocValue;
import org.kuali.coeus.common.notification.impl.bo.KcNotification;
import org.kuali.coeus.sys.framework.controller.KcHoldingPageConstants;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.bo.ResearchAreaBase;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.ProtocolStatus;
import org.kuali.kra.irb.actions.notification.ProtocolDisapprovedNotificationRenderer;
import org.kuali.kra.irb.actions.submit.ProtocolActionService;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionStatus;
import org.kuali.kra.irb.correspondence.ProtocolCorrespondence;
import org.kuali.kra.irb.noteattachment.ProtocolAttachmentStatus;
import org.kuali.kra.irb.notification.IRBNotificationContext;
import org.kuali.kra.irb.notification.IRBProtocolNotification;
import org.kuali.kra.irb.protocol.location.ProtocolLocationService;
import org.kuali.kra.irb.protocol.research.ProtocolResearchAreaService;
import org.kuali.kra.krms.KcKrmsConstants;
import org.kuali.coeus.common.impl.krms.KcKrmsFactBuilderServiceHelper;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.ProtocolDocumentBase;
import org.kuali.kra.protocol.actions.ProtocolActionBase;
import org.kuali.kra.protocol.actions.genericactions.ProtocolGenericActionService;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase;
import org.kuali.kra.protocol.noteattachment.ProtocolAttachmentProtocolBase;
import org.kuali.kra.protocol.notification.ProtocolNotification;
import org.kuali.kra.protocol.notification.ProtocolNotificationContextBase;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants.COMPONENT;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants.NAMESPACE;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.ObjectUtils;
import org.kuali.rice.krms.api.engine.Facts.Builder;


/**
 * 
 * This class represents the Protocol Document Object.
 * ProtocolDocument has a 1:1 relationship with Protocol Business Object.
 * We have declared a list of Protocol BOs in the ProtocolDocument at the same time to
 * get around the OJB anonymous keys issue of primary keys of different data types.
 * Also we have provided convenient getter and setter methods so that to the outside world;
 * Protocol and ProtocolDocument can have a 1:1 relationship.
 */
@SuppressWarnings("unchecked")
@NAMESPACE(namespace=Constants.MODULE_NAMESPACE_PROTOCOL)
@COMPONENT(component=ParameterConstants.DOCUMENT_COMPONENT)
public class ProtocolDocument extends ProtocolDocumentBase { 
    

    private static final long serialVersionUID = 803158468103165087L;

    private static final Log LOG = LogFactory.getLog(ProtocolDocument.class);
    public static final String DOCUMENT_TYPE_CODE = "PROT";
    
    @SuppressWarnings("unused")
    private static final String APPROVED_COMMENT = "Approved";
    @SuppressWarnings("unused")
    private static final String DISAPPROVED_COMMENT = "Disapproved";
    private static final String listOfStatiiEligibleForMerging = ProtocolStatus.SUBMITTED_TO_IRB + " " + ProtocolStatus.SPECIFIC_MINOR_REVISIONS_REQUIRED + " " + 
                                                                 ProtocolStatus.DEFERRED + " " + ProtocolStatus.SUBSTANTIVE_REVISIONS_REQUIRED + " " +  
                                                                 ProtocolStatus.AMENDMENT_IN_PROGRESS + " " + ProtocolStatus.RENEWAL_IN_PROGRESS + " " + 
                                                                 ProtocolStatus.SUSPENDED_BY_PI + " " + ProtocolStatus.DELETED + " " + ProtocolStatus.WITHDRAWN;

    private static final String DISAPPROVED_CONTEXT_NAME = "Disapproved";
    
    private List<CustomAttributeDocValue> customDataList;
    
    public ProtocolDocument() {
        super();
        customDataList = new ArrayList<CustomAttributeDocValue>();
    }    

    /**
     * 
     * This method is a convenience method for facilitating a 1:1 relationship between ProtocolDocument 
     * and Protocol to the outside world - aka a single Protocol field associated with ProtocolDocument
     * @return
     */
    public Protocol getProtocol() {
       return (Protocol)super.getProtocol();
    }
    
    public String getDocumentTypeCode() {
        return DOCUMENT_TYPE_CODE;
    }
    
    
    @Override
    protected void mergeProtocolAmendment() {
        if (isAmendment()) {
            mergeAmendment(ProtocolStatus.AMENDMENT_MERGED, "Amendment");
        }
        else if (isRenewal()) {
            mergeAmendment(ProtocolStatus.RENEWAL_MERGED, "Renewal");
        }
    }

    /**
     * Merge the amendment into the original protocol.  Actually, we must first make a new
     * version of the original and then merge the amendment into that new version.
     * Also merge changes into any versions of the protocol that are being amended/renewed.
     * @param protocolStatusCode
     * @throws Exception
     */
    protected void mergeAmendment(String protocolStatusCode, String type) {
        // Need to get approval action date before versioning or else duplicated OJB proxy will attach actions to Amendment instead of new base version
        Timestamp approvalActionDate = getProtocol().getLastProtocolAction().getActionDate();
        Protocol currentProtocol = (Protocol) getProtocolFinder().findCurrentProtocolByNumber(getOriginalProtocolNumber());
        final ProtocolDocument newProtocolDocument;
        try {
            // workflowdocument is null, so need to use documentservice to retrieve it
            currentProtocol.setProtocolDocument((ProtocolDocument)getDocumentService().getByDocumentHeaderId(currentProtocol.getProtocolDocument().getDocumentNumber()));
            currentProtocol.setMergeAmendment(true);
            newProtocolDocument = (ProtocolDocument) getProtocolVersionService().versionProtocolDocument(currentProtocol.getProtocolDocument());
        } catch (Exception e) {
            throw new ProtocolMergeException(e);
        }
        
        newProtocolDocument.getProtocol().merge(getProtocol());
        getProtocol().setProtocolStatusCode(protocolStatusCode);

        ProtocolActionBase lastApprovalAction = getLastApprovalAction();
        List<ProtocolSubmissionBase> protocolSubmissions = newProtocolDocument.getProtocol().getProtocolSubmissions();
        ProtocolSubmission mergedSubmission = (ProtocolSubmission) (protocolSubmissions == null || protocolSubmissions.size() == 0 ? null : protocolSubmissions.get(protocolSubmissions.size() - 1));
        ProtocolAction action = new ProtocolAction((Protocol) newProtocolDocument.getProtocol(), mergedSubmission, lastApprovalAction.getProtocolActionTypeCode());
        //ProtocolAction action = new ProtocolAction((Protocol) newProtocolDocument.getProtocol(), null, lastApprovalAction);
        action.setComments(type + "-" + getProtocolNumberIndex() + ": Approved");
        action.setActionDate(approvalActionDate);
        newProtocolDocument.setProtocolWorkflowType(ProtocolWorkflowType.APPROVED);
        newProtocolDocument.getProtocol().getProtocolActions().add(action);
        if (isProtocolExpiredAndForRenewal(currentProtocol) || isProtocolSuspendedAndForAmendmentOrRenewal(currentProtocol)) {
            newProtocolDocument.getProtocol().setProtocolStatusCode(ProtocolStatus.ACTIVE_OPEN_TO_ENROLLMENT);
        }
        try {
            getDocumentService().saveDocument(newProtocolDocument);
            // blanket approve to make the new protocol document 'final'
            setProtocolDocumentToApproveByDefault();
            newProtocolDocument.getDocumentHeader().getWorkflowDocument().route(type + "-" + getProtocolNumberIndex() + ": merged");
        } catch (WorkflowException e) {
            throw new ProtocolMergeException(e);
        }
        
        this.getProtocol().setActive(false);
        
        // now that we've updated the approved protocol, we must find all others under modification and update them too.
        for (ProtocolBase otherProtocol: getProtocolFinder().findProtocols(getOriginalProtocolNumber())) {
            String status = otherProtocol.getProtocolStatus().getProtocolStatusCode();
            if (isEligibleForMerging(status, (Protocol) otherProtocol)) {
                // then this protocol version is being amended so push changes to it
                LOG.info("Merging amendment " + this.getProtocol().getProtocolNumber() + " into editable protocol " + otherProtocol.getProtocolNumber());
                otherProtocol.merge(getProtocol(), false);
                String protocolType = protocolStatusCode.equals(ProtocolStatus.AMENDMENT_MERGED) ? ProtocolActionType.AMENDMENT_CREATED 
                                                                                                 : ProtocolActionType.RENEWAL_CREATED;
                action = new ProtocolAction((Protocol) otherProtocol, null, protocolType);
                action.setComments(type + "-" + getProtocolNumberIndex() + ": Merged");
                otherProtocol.getProtocolActions().add(action);
                getBusinessObjectService().save(otherProtocol);
            }
        }

        finalizeAttachmentProtocol((Protocol)this.getProtocol());
        getBusinessObjectService().save(this);
        
        mergeProtocolCorrespondenceAndNotification(newProtocolDocument, getLastApprovalAction().getProtocolActionType().getProtocolActionTypeCode());
    }
    
    /**
     * This method is to verify whether a protocol expired and we are renewing that protocol
     * @param currentProtocol
     * @return
     */
    private boolean isProtocolExpiredAndForRenewal(Protocol currentProtocol) {
        return currentProtocol.getProtocolStatusCode().equals(ProtocolStatus.EXPIRED) && this.isRenewal();
    }

    /**
     * This method is to verify whether original protocol is suspended and currently we are on amendment/renewal/amendment with renewal
     * to un-suspend a protocol.
     * @param currentProtocol
     * @return
     */
    private boolean isProtocolSuspendedAndForAmendmentOrRenewal(Protocol currentProtocol) {
        String currentProtocolStatus = currentProtocol.getProtocolStatusCode();
        if((currentProtocolStatus.equals(ProtocolStatus.SUSPENDED_BY_DSMB) || currentProtocolStatus.equals(ProtocolStatus.SUSPENDED_BY_IRB) || 
                currentProtocolStatus.equals(ProtocolStatus.SUSPENDED_BY_PI)) && (this.isRenewal() || this.isAmendment() || this.isRenewalWithAmendment())) {
            return true;
        }
        return false;
    }
    
    protected void mergeProtocolCorrespondenceAndNotification(ProtocolDocument newProtocolDocument, String protocolActionType) {
        /**
         * This is a hack, but if there another way to do it?  Not that I can find.
         * We need to find the last instance of a Protocol Action of type ProtocolActionType.APPROVED and copy that action's correspondence, and put it in the
         * corresponding action on .  Per KCIRB-1830
        */  
        ProtocolAction getProtocolPaToUse = null;
        for (ProtocolActionBase pa : getProtocol().getProtocolActions()) {
            if (StringUtils.equals(protocolActionType, pa.getProtocolActionTypeCode())) {
                if (getProtocolPaToUse == null || getProtocolPaToUse.getUpdateTimestamp().before(pa.getUpdateTimestamp())) {
                    getProtocolPaToUse = (ProtocolAction) pa;
                }
            }
        }
        ProtocolAction newDocPaToUse = null;
        for (ProtocolActionBase pa2 : newProtocolDocument.getProtocol().getProtocolActions()) {
            if (isProtocolApproved(pa2.getProtocolActionTypeCode())) {
                if (newDocPaToUse == null || newDocPaToUse.getUpdateTimestamp().before(pa2.getUpdateTimestamp())) {
                    newDocPaToUse = (ProtocolAction) pa2;
                }
            }
        }
        if (newDocPaToUse != null && getProtocolPaToUse != null) {
            for (org.kuali.kra.protocol.correspondence.ProtocolCorrespondence pc : getProtocolPaToUse.getProtocolCorrespondences()) {
                ProtocolCorrespondence newPc = new ProtocolCorrespondence();
                newPc.setActionId(pc.getActionId());
                newPc.setActionIdFk(newDocPaToUse.getProtocolActionId());
                newPc.setCorrespondence(pc.getCorrespondence());
                newPc.setCreateTimestamp(pc.getCreateTimestamp());
                newPc.setCreateUser(pc.getCreateUser());
                newPc.setExtension(pc.getExtension());
                newPc.setFinalFlag(pc.getFinalFlag());
                newPc.setFinalFlagTimestamp(pc.getFinalFlagTimestamp());
                newPc.setForwardName(pc.getForwardName());
                newPc.setHoldingPage(pc.isHoldingPage());
                newPc.setNewCollectionRecord(pc.isNewCollectionRecord());
                newPc.setNotificationRequestBean(pc.getNotificationRequestBean());
                newPc.setProtocol(newDocPaToUse.getProtocol());
                newPc.setProtocolAction(newDocPaToUse);
                newPc.setProtocolCorrespondenceType(pc.getProtocolCorrespondenceType());
                newPc.setProtocolId(newDocPaToUse.getProtocolId());
                newPc.setProtocolNumber(newDocPaToUse.getProtocolNumber());
                newPc.setProtoCorrespTypeCode(pc.getProtoCorrespTypeCode());
                newPc.setRegenerateFlag(pc.isRegenerateFlag());
                newPc.setSequenceNumber(pc.getSequenceNumber());
                if (newDocPaToUse.getProtocolCorrespondences() == null) {
                    newDocPaToUse.setProtocolCorrespondences(new ArrayList<org.kuali.kra.protocol.correspondence.ProtocolCorrespondence>());
                }
                newDocPaToUse.getProtocolCorrespondences().add(newPc);
                getBusinessObjectService().save(newPc);
            }
            for (KcNotification notification : getProtocolPaToUse.getProtocolNotifications()) {
                IRBProtocolNotification newNotification = IRBProtocolNotification.copy(notification);
                newNotification.resetPersistenceState();
                newNotification.persistOwningObject(newProtocolDocument.getProtocol());
            }
            getBusinessObjectService().save(newDocPaToUse);
        }   
    }
    
        private boolean isProtocolApproved(String protocolActionTypeCode) {
            if (StringUtils.equals(ProtocolActionType.APPROVED, protocolActionTypeCode) || 
                    StringUtils.equals(ProtocolActionType.EXPEDITE_APPROVAL, protocolActionTypeCode) ||
                    StringUtils.equals(ProtocolActionType.RESPONSE_APPROVAL, protocolActionTypeCode)){
                return true;
            }
            return false;
        }
        
    private boolean isEligibleForMerging(String status, Protocol otherProtocol) {
        return listOfStatiiEligibleForMerging.contains(status) && !StringUtils.equals(this.getProtocol().getProtocolNumber(), otherProtocol.getProtocolNumber());
    }

    /*
     * This method is to make the document status of the attachment protocol to "finalized" 
     */
    private void finalizeAttachmentProtocol(Protocol protocol) {
        for (ProtocolAttachmentProtocolBase attachment : protocol.getAttachmentProtocols()) {
            attachment.setProtocol(protocol);
            if (attachment.isDraft()) {
                attachment.setDocumentStatusCode(ProtocolAttachmentStatus.FINALIZED);
            }
        }
    }


    private ProtocolVersionService getProtocolVersionService() {
        return KcServiceLocator.getService(ProtocolVersionService.class);
    }

    private ProtocolFinderDao getProtocolFinder() {
        return KcServiceLocator.getService(ProtocolFinderDao.class);
    }

    private ProtocolActionBase getLastApprovalAction() {
        ProtocolActionBase result = null;
        for (ProtocolActionBase action: getProtocol().getProtocolActions()) {
            if (ProtocolActionType.APPROVED.equals(action.getProtocolActionTypeCode()) ||
                ProtocolActionType.EXPEDITE_APPROVAL.equals(action.getProtocolActionTypeCode()) ||
                ProtocolActionType.GRANT_EXEMPTION.equals(action.getProtocolActionTypeCode().equals(ProtocolActionType.APPROVED))) {
                result = action;
            }
        }
        return result;
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
            if (getWorkflowDocumentService().getCurrentRouteNodeNames(getDocumentHeader().getWorkflowDocument()).equalsIgnoreCase(Constants.PROTOCOL_INITIATED_ROUTE_NODE_NAME)) { 
                isComplete = false;
            }     
            // while submitting an amendment for IRB review, the amendment moves from node Initiated to node IRBReview, 
            //so need to check if protocolSubmissionStatus is "InAgenda" to avoid the processing page from not going away at all when 
            // an amendment is submitted for review
            // Added for KCIRB-1515 & KCIRB-1528
            getProtocol().getProtocolSubmission().refreshReferenceObject("submissionStatus"); 
            String status = getProtocol().getProtocolSubmission().getSubmissionStatusCode();
            if (isAmendment() || isRenewal()) {
                if (status.equals(ProtocolSubmissionStatus.APPROVED) 
                    && getWorkflowDocumentService().getCurrentRouteNodeNames(getDocumentHeader().getWorkflowDocument()).equalsIgnoreCase(Constants.PROTOCOL_IRBREVIEW_ROUTE_NODE_NAME)) {
                        isComplete = false;
               }
            }
               
        } else {
            /*
             * If amendment has been merged, need to redirect to the newly created active protocol
             * Wait for the new active protocol to be created before redirecting to it.
             */
            if (getProtocol().getProtocolStatusCode().equals(ProtocolStatus.AMENDMENT_MERGED) || 
                getProtocol().getProtocolStatusCode().equals(ProtocolStatus.RENEWAL_MERGED)) {
                String protocolId = getNewProtocolDocId();               
                if (ObjectUtils.isNull(protocolId)) {
                    isComplete = false;
                } else {
                    /*
                     * The new protocol document is only available after the amendment has been merged. So, once the amendment is merged,
                     * find the active protocol available and change the return link to point to that.
                     */
                    String oldLocation = (String) GlobalVariables.getUserSession().retrieveObject(KcHoldingPageConstants.HOLDING_PAGE_RETURN_LOCATION);
                    String oldDocNbr = getProtocol().getProtocolDocument().getDocumentNumber();
                    String returnLocation = oldLocation.replaceFirst(oldDocNbr, protocolId);
                    GlobalVariables.getUserSession().addObject(KcHoldingPageConstants.HOLDING_PAGE_RETURN_LOCATION, (Object) returnLocation);
                }
            }         
            // approve/expedited approve/response approve
            if (!getDocumentHeader().getWorkflowDocument().isFinal()) {
                isComplete = false;
            } 
        }


        return isComplete;
    }

    public void populateContextQualifiers(Map<String, String> qualifiers) {
        qualifiers.put("namespaceCode", Constants.MODULE_NAMESPACE_PROTOCOL);
        qualifiers.put("name", KcKrmsConstants.IrbProtocol.IRB_PROTOCOL_CONTEXT);
    }

    public void addFacts(Builder factsBuilder) {
        KcKrmsFactBuilderServiceHelper fbService = KcServiceLocator.getService("irbProtocolFactBuilderService");
        fbService.addFacts(factsBuilder, this);
    }


    @Override
    protected Protocol createNewProtocolInstanceHook() {
        return new Protocol();
    }


    @Override
    protected Class<? extends org.kuali.kra.protocol.protocol.research.ProtocolResearchAreaService> getProtocolResearchAreaServiceClassHook() {
        return ProtocolResearchAreaService.class;
    }


    @Override
    protected Class<? extends ResearchAreaBase> getResearchAreaBoClassHook() {
        return ResearchArea.class;
    }


    @Override
    protected ProtocolActionBase getNewProtocolActionInstanceHook(ProtocolBase protocol, ProtocolSubmissionBase protocolSubmission, String actionTypeCode) {
        return new ProtocolAction((Protocol) protocol, (ProtocolSubmission) protocolSubmission, actionTypeCode);
    }


    @Override
    protected Class<? extends org.kuali.kra.protocol.actions.submit.ProtocolActionService> getProtocolActionServiceClassHook() {
        return ProtocolActionService.class;
    }


    @Override
    protected Class<? extends org.kuali.kra.protocol.protocol.location.ProtocolLocationService> getProtocolLocationServiceClassHook() {
        return ProtocolLocationService.class;
    }


    @Override
    protected Class<? extends ProtocolBase> getProtocolBOClassHook() {
        return Protocol.class;
    }


    @Override
    public List<? extends DocumentCustomData> getDocumentCustomData() {
        return getCustomDataList();
    }


    public List<CustomAttributeDocValue> getCustomDataList() {
        return customDataList;
    }


    public void setCustomDataList(List<CustomAttributeDocValue> customDataList) {
        this.customDataList = customDataList;
    }

    @Override
    protected Class<? extends ProtocolGenericActionService> getProtocolGenericActionServiceClassHook() {
        return ProtocolGenericActionService.class;
    }
   
    

    @Override
    protected ProtocolNotification getNewProtocolNotificationInstanceHook() {
        return new IRBProtocolNotification();
    }

    @Override
    protected ProtocolNotificationContextBase getDisapproveNotificationContextHook(ProtocolBase protocol) {
        return new IRBNotificationContext( (Protocol) protocol, 
                                            ProtocolActionType.DISAPPROVED, 
                                            DISAPPROVED_CONTEXT_NAME, 
                                            new ProtocolDisapprovedNotificationRenderer( (Protocol) protocol));
    }

    @Override
    protected String getCommitteeDisapprovedStatusCodeHook() {
        return ProtocolStatus.DISAPPROVED;
    }
}
