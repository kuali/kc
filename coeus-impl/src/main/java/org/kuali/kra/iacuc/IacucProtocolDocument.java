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

package org.kuali.kra.iacuc;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.common.framework.custom.DocumentCustomData;
import org.kuali.coeus.common.framework.krms.KcKrmsFactBuilderService;
import org.kuali.coeus.common.notification.impl.bo.KcNotification;
import org.kuali.coeus.sys.framework.controller.KcHoldingPageConstants;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.bo.ResearchAreaBase;
import org.kuali.kra.iacuc.actions.IacucProtocolAction;
import org.kuali.kra.iacuc.actions.IacucProtocolActionType;
import org.kuali.kra.iacuc.actions.IacucProtocolStatus;
import org.kuali.kra.iacuc.actions.IacucProtocolSubmissionDoc;
import org.kuali.kra.iacuc.actions.genericactions.IacucProtocolGenericActionService;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolActionService;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmission;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmissionBuilder;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmissionStatus;
import org.kuali.kra.iacuc.notification.IacucProtocolNotification;
import org.kuali.kra.iacuc.notification.IacucProtocolNotificationContext;
import org.kuali.kra.iacuc.notification.IacucProtocolNotificationRenderer;
import org.kuali.kra.iacuc.protocol.location.IacucProtocolLocationService;
import org.kuali.kra.iacuc.protocol.research.IacucProtocolResearchAreaService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.krms.KcKrmsConstants;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.ProtocolDocumentBase;
import org.kuali.kra.protocol.ProtocolFinderDao;
import org.kuali.kra.protocol.ProtocolVersionService;
import org.kuali.kra.protocol.actions.ProtocolActionBase;
import org.kuali.kra.protocol.actions.genericactions.ProtocolGenericActionService;
import org.kuali.kra.protocol.actions.submit.ProtocolActionService;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase;
import org.kuali.kra.protocol.noteattachment.ProtocolAttachmentProtocolBase;
import org.kuali.kra.protocol.notification.ProtocolNotification;
import org.kuali.kra.protocol.notification.ProtocolNotificationContextBase;
import org.kuali.kra.protocol.protocol.location.ProtocolLocationService;
import org.kuali.kra.protocol.protocol.research.ProtocolResearchAreaService;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants.COMPONENT;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants.NAMESPACE;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.ObjectUtils;
import org.kuali.rice.krms.api.engine.Facts.Builder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * 
 * This class represents the ProtocolBase Document Object.
 * ProtocolDocumentBase has a 1:1 relationship with ProtocolBase Business Object.
 * We have declared a list of ProtocolBase BOs in the ProtocolDocumentBase at the same time to
 * get around the OJB anonymous keys issue of primary keys of different data types.
 * Also we have provided convenient getter and setter methods so that to the outside world;
 * ProtocolBase and ProtocolDocumentBase can have a 1:1 relationship.
 */
@SuppressWarnings("unchecked")
@NAMESPACE(namespace=Constants.MODULE_NAMESPACE_IACUC)
@COMPONENT(component=ParameterConstants.DOCUMENT_COMPONENT)
public class IacucProtocolDocument extends ProtocolDocumentBase { 

    private static final long serialVersionUID = -1014286912251147390L;
    @SuppressWarnings("unused")
    private static final Log LOG = LogFactory.getLog(IacucProtocolDocument.class);
    public static final String DOCUMENT_TYPE_CODE = "ICPR";
    
    private static final String CONTINUATION_KEY = "C";
    
    private static final String DISAPPROVED_CONTEXT_NAME = "Disapproved";
	

	public IacucProtocolDocument() { 
        super();
	}

    public IacucProtocol getProtocol() {
        return (IacucProtocol)super.getProtocol();
    }

	@Override
    protected IacucProtocol createNewProtocolInstanceHook() {
        return new IacucProtocol();
    }
	
	public IacucProtocol getIacucProtocol() {
	    return (IacucProtocol) this.getProtocol();
	}

    public String getDocumentTypeCode() {
        return DOCUMENT_TYPE_CODE;
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
         * This happens when you submit your protocol to IACUC, the current route node is Initiated
         */
        if (this.getProtocol().getProtocolStatusCode().equals(IacucProtocolStatus.SUBMITTED_TO_IACUC)) {
            if (getWorkflowDocumentService().getCurrentRouteNodeNames(getDocumentHeader().getWorkflowDocument()).equalsIgnoreCase(Constants.PROTOCOL_INITIATED_ROUTE_NODE_NAME)) { 
                isComplete = false;
            }     
            // while submitting an amendment for IACUC review, the amendment moves from node Initiated to node IACUCReview, 
            //so need to check if protocolSubmissionStatus is "InAgenda" to avoid the processing page from not going away at all when 
            // an amendment is submitted for review
            // Added for KCIRB-1515 & KCIRB-1528
            getProtocol().getProtocolSubmission().refreshReferenceObject("submissionStatus"); 
            
            
            String status = getProtocol().getProtocolSubmission().getSubmissionStatusCode();
            if (!isNormal()) {
                if (status.equals(IacucProtocolSubmissionStatus.APPROVED) 
                        && getWorkflowDocumentService().getCurrentRouteNodeNames(getDocumentHeader().getWorkflowDocument()).equalsIgnoreCase(Constants.PROTOCOL_IACUCREVIEW_ROUTE_NODE_NAME)) {
                    isComplete = false;
                }
            }

        } else {
            /*
             * If amendment has been merged, need to redirect to the newly created active protocol
             * Wait for the new active protocol to be created before redirecting to it.
             */
            if (getProtocol().getProtocolStatusCode().equals(IacucProtocolStatus.AMENDMENT_MERGED) || 
                    getProtocol().getProtocolStatusCode().equals(IacucProtocolStatus.RENEWAL_MERGED) ||
                    getProtocol().getProtocolStatusCode().equals(IacucProtocolStatus.FYI_MERGED) ||
                    getProtocol().getProtocolStatusCode().equals(IacucProtocolStatus.CONTINUATION_MERGED)) {
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

    @Override
    protected Class<? extends ProtocolLocationService> getProtocolLocationServiceClassHook() {
        return IacucProtocolLocationService.class;
    }
    
    @Override
    protected Class<? extends ProtocolResearchAreaService> getProtocolResearchAreaServiceClassHook() {
        return IacucProtocolResearchAreaService.class;
    }

    @Override
    public void populateContextQualifiers(Map<String, String> qualifiers) {
        qualifiers.put("namespaceCode", Constants.MODULE_NAMESPACE_IACUC);
        qualifiers.put("name", KcKrmsConstants.IacucProtocol.IACUC_PROTOCOL_CONTEXT);
    }

    @Override
    public void addFacts(Builder factsBuilder) {
        KcKrmsFactBuilderService fbService = KcServiceLocator.getService("iacucProtocolFactBuilderService");
        fbService.addFacts(factsBuilder, this);
    }

    @Override
    protected ProtocolActionBase getNewProtocolActionInstanceHook(ProtocolBase protocol, ProtocolSubmissionBase protocolSubmission, String protocolStatusCode) {
        String protocolActionTypeCode = IacucProtocolActionType.RENEWAL_CREATED; 
        if (protocolStatusCode.equals(IacucProtocolStatus.AMENDMENT_MERGED)) {
            protocolActionTypeCode = IacucProtocolActionType.IACUC_APPROVED;
        } else if (protocolStatusCode.equals(IacucProtocolStatus.CONTINUATION_MERGED)) {
            protocolActionTypeCode = IacucProtocolActionType.CONTINUATION;
        } else if (protocolStatusCode.equals(IacucProtocolActionType.ADMINISTRATIVE_CORRECTION)) {
            protocolActionTypeCode = IacucProtocolActionType.ADMINISTRATIVE_CORRECTION;
        }
        return new IacucProtocolAction((IacucProtocol) protocol, (IacucProtocolSubmission) protocolSubmission, protocolActionTypeCode);
    }

    @Override
    protected Class<? extends ProtocolActionService> getProtocolActionServiceClassHook() {
        return IacucProtocolActionService.class;
    }

    @Override
    protected Class<? extends ProtocolBase> getProtocolBOClassHook() {
        return IacucProtocol.class;
    }


    protected ProtocolFinderDao getProtocolFinderDaoHook() {
        return KcServiceLocator.getService(IacucProtocolFinderDao.class);
    }


    protected ProtocolVersionService getProtocolVersionServiceHook() {
        return KcServiceLocator.getService(IacucProtocolVersionService.class);
    }


    protected String getProtocolStatusMergedHook() {
        return IacucProtocolStatus.AMENDMENT_MERGED;
    }


    protected String getProtocolStatusExemptHook() {
        return IacucProtocolStatus.ADMINISTRATIVELY_INCOMPLETE;
    }

    
    protected String getProtocolStatusOnHoldHook() {
        return IacucProtocolStatus.ACTIVE_ON_HOLD;
    }


    protected String getProtocolStatusActiveOpenToEnrollmentHook() {
        return IacucProtocolStatus.ACTIVE;
    }


    protected String getListOfStatusEligibleForMergingHook() {
      StringBuffer listOfStatusEligibleForMerging = new StringBuffer(); 
      listOfStatusEligibleForMerging.append(IacucProtocolStatus.SUBMITTED_TO_IACUC);
      listOfStatusEligibleForMerging.append(" ");
      listOfStatusEligibleForMerging.append(IacucProtocolStatus.MINOR_REVISIONS_REQUIRED);
      listOfStatusEligibleForMerging.append(" ");
      listOfStatusEligibleForMerging.append(IacucProtocolStatus.TABLED);
      listOfStatusEligibleForMerging.append(" ");
      listOfStatusEligibleForMerging.append(IacucProtocolStatus.MAJOR_REVISIONS_REQUIRED);
      listOfStatusEligibleForMerging.append(" ");
      listOfStatusEligibleForMerging.append(IacucProtocolStatus.AMENDMENT_IN_PROGRESS);
      listOfStatusEligibleForMerging.append(" ");
      listOfStatusEligibleForMerging.append(IacucProtocolStatus.RENEWAL_IN_PROGRESS);
      listOfStatusEligibleForMerging.append(" ");
      listOfStatusEligibleForMerging.append(IacucProtocolStatus.CONTINUATION_IN_PROGRESS);
      listOfStatusEligibleForMerging.append(" ");
      listOfStatusEligibleForMerging.append(IacucProtocolStatus.SUSPENDED);
      listOfStatusEligibleForMerging.append(" ");
      listOfStatusEligibleForMerging.append(IacucProtocolStatus.DELETED);
      listOfStatusEligibleForMerging.append(" ");
      listOfStatusEligibleForMerging.append(IacucProtocolStatus.WITHDRAWN);
        return listOfStatusEligibleForMerging.toString();
    }

    public boolean isContinuation() {
        return getProtocol().getProtocolNumber().contains(CONTINUATION_KEY);
    }

    protected String getProtocolMergedStatus() {
        String mergedStatus = IacucProtocolStatus.AMENDMENT_MERGED;
        if (isRenewal()) {
            mergedStatus = IacucProtocolStatus.RENEWAL_MERGED;
        }else if(isContinuation()) {
            mergedStatus = IacucProtocolStatus.CONTINUATION_MERGED;
        }else if (isFYI()) {
            mergedStatus = IacucProtocolStatus.FYI_MERGED;
        }
        return mergedStatus;
    }

    @Override
    public boolean isNormal() {
        return !isAmendment() && !isRenewal() && !isContinuation() && !isFYI();
    }

    @Override
    protected void mergeProtocolAmendment() {
        if (isAmendment()) {
            mergeAmendment(getProtocolMergedStatus(), "Amendment");
        }
        else if (isRenewal()) {
            mergeAmendment(getProtocolMergedStatus(), "Renewal");
        }
        else if (isContinuation()) {
            mergeAmendment(getProtocolMergedStatus(), "Continuation");
        }
        else if (isFYI()) {
            mergeAmendment(getProtocolMergedStatus(), "FYI");
            mergeFyiAttachments();
            getProtocol().reconcileActionsWithSubmissions();
        }
    }

    protected void mergeFyiAttachments() {
        IacucProtocolSubmission fyiSubmission = null;
        ProtocolActionBase createFyiAction = null;

        String fyiNumber = getProtocol().getProtocolNumber().substring(getProtocol().getProtocolNumber().indexOf("F") + 1);

        ProtocolBase originalProtocol = KcServiceLocator.getService(IacucProtocolFinderDao.class).findCurrentProtocolByNumber(getOriginalProtocolNumber());
        for(ProtocolActionBase originalAction : originalProtocol.getProtocolActions()) {
            if(originalAction.getProtocolActionTypeCode().equals(IacucProtocolActionType.NOTIFY_IACUC)
                    && originalAction.getComments().contains("FYI-" + fyiNumber + ": Created")) {
                createFyiAction = originalAction;
                break;
            }
        }

        if(createFyiAction != null) {
            fyiSubmission = (IacucProtocolSubmission) originalProtocol.getProtocolSubmission();
            if(createFyiAction.getSubmissionIdFk() == null) {
                createFyiAction.setProtocolSubmission(fyiSubmission);
                createFyiAction.setSubmissionIdFk(fyiSubmission.getSubmissionId());
                createFyiAction.setSubmissionNumber(fyiSubmission.getSubmissionNumber());
                getBusinessObjectService().save(createFyiAction);
            }
        }

        if(fyiSubmission != null) {
            List<IacucProtocolSubmissionDoc> mergedAttachments = new ArrayList<IacucProtocolSubmissionDoc>();
            for(ProtocolAttachmentProtocolBase attachment : getProtocol().getActiveAttachmentProtocols()) {
                IacucProtocolSubmissionDoc fyiAttachment = IacucProtocolSubmissionBuilder.createProtocolSubmissionDoc(fyiSubmission, attachment.getFile().getName(), attachment.getFile().getType(), attachment.getFile().getData(), attachment.getDescription());
                fyiAttachment.setProtocolNumber(createFyiAction.getProtocolNumber());
                fyiAttachment.setProtocolId(createFyiAction.getProtocolId());
                fyiAttachment.setProtocol(originalProtocol);
                mergedAttachments.add(fyiAttachment);
            }
            getBusinessObjectService().save(mergedAttachments);
        }
        else {
            LOG.error("Couldn't merge FYI attachments into parent protocol-- no submission found for FYI #"+getProtocol().getProtocolNumber());
        }
    }

    @Override
    protected Class<? extends ResearchAreaBase> getResearchAreaBoClassHook() {
        return IacucResearchArea.class;
    }
    
    
    /**
     * Merge the amendment into the original protocol.  Actually, we must first make a new
     * version of the original and then merge the amendment into that new version.
     * Also merge changes into any versions of the protocol that are being amended/renewed.
     * @param protocolStatusCode
     * @throws Exception
     */
    protected void mergeAmendment(String protocolStatusCode, String type) {
        ProtocolBase currentProtocol = getProtocolFinderDaoHook().findCurrentProtocolByNumber(getOriginalProtocolNumber());
        final ProtocolDocumentBase newProtocolDocument;
        try {
            // workflowdocument is null, so need to use documentservice to retrieve it
            currentProtocol.setProtocolDocument((ProtocolDocumentBase)getDocumentService().getByDocumentHeaderId(currentProtocol.getProtocolDocument().getDocumentNumber()));
            currentProtocol.setMergeAmendment(true);
            newProtocolDocument = getProtocolVersionServiceHook().versionProtocolDocument(currentProtocol.getProtocolDocument());
        } catch (Exception e) {
            throw new ProtocolMergeException(e);
        }
        
        newProtocolDocument.getProtocol().merge(getProtocol());
        getProtocol().setProtocolStatusCode(protocolStatusCode);
        
        ProtocolActionBase action = getNewProtocolActionInstanceHook(newProtocolDocument.getProtocol(), null, getProtocolStatusMergedHook()); 
        action.setComments(type + "-" + getProtocolNumberIndex() + ": Approved");
        newProtocolDocument.setProtocolWorkflowType(ProtocolWorkflowType.APPROVED);
        newProtocolDocument.getProtocol().getProtocolActions().add(action);
        if (currentProtocol.getProtocolStatusCode().equals(getProtocolStatusOnHoldHook())) {
            //protocol should retain the Hold status whenever an amendment is approved because the reason for the hold may not pertain to the approved amendment 
            newProtocolDocument.getProtocol().setProtocolStatusCode(getProtocolStatusOnHoldHook());
        } else if (!currentProtocol.getProtocolStatusCode().equals(getProtocolStatusExemptHook())) {
            newProtocolDocument.getProtocol().setProtocolStatusCode(getProtocolStatusActiveOpenToEnrollmentHook());
        }
        try {
            getDocumentService().saveDocument(newProtocolDocument);
            // blanket approve to make the new protocol document 'final'
            setProtocolDocumentToApproveByDefault();
            newProtocolDocument.getDocumentHeader().getWorkflowDocument().route(type + "-" + getProtocolNumberIndex() + ": merged");
        } catch (WorkflowException e) {
            throw new ProtocolMergeException(e);
        }

        // Have to map copied actions to copied submission FKs and re-save
        newProtocolDocument.getProtocol().reconcileActionsWithSubmissions();
        getBusinessObjectService().save(newProtocolDocument.getProtocol().getProtocolActions());
        this.getProtocol().setActive(false);
        
        // now that we've updated the approved protocol, we must find all others under modification and update them too.
        for (ProtocolBase otherProtocol: getProtocolFinderDaoHook().findProtocols(getOriginalProtocolNumber())) {
            String status = otherProtocol.getProtocolStatus().getProtocolStatusCode();
            if (isEligibleForMerging(status, otherProtocol)) {
                // then this protocol version is being amended so push changes to it
                otherProtocol.merge(getProtocol(), false);
                action = getNewProtocolActionInstanceHook(otherProtocol, null, protocolStatusCode);
                action.setComments(type + "-" + getProtocolNumberIndex() + ": Merged");
                otherProtocol.getProtocolActions().add(action);
                getBusinessObjectService().save(otherProtocol);
            }
        }

        finalizeAttachmentProtocol(this.getProtocol());
        mergeProtocolNotifications(newProtocolDocument, this.getProtocol().getLastProtocolAction().getProtocolActionTypeCode());
        getBusinessObjectService().save(this);
    }
    
    protected void mergeProtocolNotifications(ProtocolDocumentBase newProtocolDocument, String protocolActionType) {
        /**
         * We need to find the last instance of an IACUC Protocol Action of type IacucProtocolActionType.APPROVED and copy that action's 
         * notifications.
        */  
        IacucProtocolAction getProtocolPaToUse = null;
        for (ProtocolActionBase pa : getProtocol().getProtocolActions()) {
            if (StringUtils.equals(protocolActionType, pa.getProtocolActionTypeCode())) {
                if (getProtocolPaToUse == null || getProtocolPaToUse.getUpdateTimestamp().before(pa.getUpdateTimestamp())) {
                    getProtocolPaToUse = (IacucProtocolAction) pa;
                }
            }
        }
        IacucProtocolAction newDocPaToUse = null;
        for (ProtocolActionBase pa2 : newProtocolDocument.getProtocol().getProtocolActions()) {
            if (isProtocolApproved(pa2.getProtocolActionTypeCode())) {
                if (newDocPaToUse == null || newDocPaToUse.getUpdateTimestamp().before(pa2.getUpdateTimestamp())) {
                    newDocPaToUse = (IacucProtocolAction) pa2;
                }
            }
        }
        if (newDocPaToUse != null && getProtocolPaToUse != null) {
            for (KcNotification notification : getProtocolPaToUse.getProtocolNotifications()) {
                IacucProtocolNotification newNotification = IacucProtocolNotification.copy(notification);
                newNotification.resetPersistenceState();
                newNotification.persistOwningObject(newProtocolDocument.getProtocol());
            }
            getBusinessObjectService().save(newDocPaToUse);
        }   
    }    
    
    private boolean isProtocolApproved(String protocolActionTypeCode) {
        if (StringUtils.equals(IacucProtocolActionType.IACUC_APPROVED, protocolActionTypeCode) || 
                StringUtils.equals(IacucProtocolActionType.DESIGNATED_REVIEW_APPROVAL, protocolActionTypeCode) ||
                StringUtils.equals(IacucProtocolActionType.RESPONSE_APPROVAL, protocolActionTypeCode) ||
                StringUtils.equals(IacucProtocolActionType.ADMINISTRATIVE_APPROVAL, protocolActionTypeCode)){
            return true;
        }
        return false;
    }
    
    protected boolean isEligibleForMerging(String status, ProtocolBase otherProtocol) {
        return getListOfStatusEligibleForMergingHook().contains(status) && !StringUtils.equals(this.getProtocol().getProtocolNumber(), otherProtocol.getProtocolNumber());
    }

    @Override
    public List<? extends DocumentCustomData> getDocumentCustomData() {
        return getIacucProtocol().getIacucProtocolCustomDataList();
    }

    @Override
    protected Class<? extends ProtocolGenericActionService> getProtocolGenericActionServiceClassHook() {
        return IacucProtocolGenericActionService.class;
    }

    @Override
    protected ProtocolNotification getNewProtocolNotificationInstanceHook() {
        return new IacucProtocolNotification();
    }

    @Override
    protected ProtocolNotificationContextBase getDisapproveNotificationContextHook(ProtocolBase protocol) {        
        return new IacucProtocolNotificationContext((IacucProtocol) protocol, 
                                                     IacucProtocolActionType.IACUC_DISAPPROVED, 
                                                     DISAPPROVED_CONTEXT_NAME,
                                                     new IacucProtocolNotificationRenderer((IacucProtocol) protocol)
                                                    );
    }

    @Override
    protected String getCommitteeDisapprovedStatusCodeHook() {
        return IacucProtocolStatus.DISAPPROVED;
    }

}
