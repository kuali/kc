/*
 * Copyright 2005-2013 The Kuali Foundation
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

package org.kuali.kra.iacuc;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.bo.DocumentCustomData;
import org.kuali.kra.bo.ResearchAreaBase;
import org.kuali.kra.iacuc.actions.IacucProtocolAction;
import org.kuali.kra.iacuc.actions.IacucProtocolActionType;
import org.kuali.kra.iacuc.actions.IacucProtocolStatus;
import org.kuali.kra.iacuc.actions.genericactions.IacucProtocolGenericActionService;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolActionService;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmission;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmissionStatus;
import org.kuali.kra.iacuc.notification.IacucProtocolNotification;
import org.kuali.kra.iacuc.notification.IacucProtocolNotificationContext;
import org.kuali.kra.iacuc.notification.IacucProtocolNotificationRenderer;
import org.kuali.kra.iacuc.protocol.location.IacucProtocolLocationService;
import org.kuali.kra.iacuc.protocol.research.IacucProtocolResearchAreaService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.krms.KcKrmsConstants;
import org.kuali.kra.krms.service.KcKrmsFactBuilderService;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.ProtocolDocumentBase;
import org.kuali.kra.protocol.ProtocolFinderDao;
import org.kuali.kra.protocol.ProtocolVersionService;
import org.kuali.kra.protocol.actions.ProtocolActionBase;
import org.kuali.kra.protocol.actions.genericactions.ProtocolGenericActionService;
import org.kuali.kra.protocol.actions.submit.ProtocolActionService;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase;
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
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -1014286912251147390L;
    @SuppressWarnings("unused")
    private static final Log LOG = LogFactory.getLog(IacucProtocolDocument.class);
    public static final String DOCUMENT_TYPE_CODE = "ICPR";
    
    private static final String CONTINUATION_KEY = "C";
    
    private static final String DISAPPROVED_CONTEXT_NAME = "Disapproved";
	
    /**
     * Constructs a ProtocolDocumentBase object.
     */
	public IacucProtocolDocument() { 
        super();
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
            if (isAmendment() || isRenewal() || isContinuation()) {
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
                    getProtocol().getProtocolStatusCode().equals(IacucProtocolStatus.CONTINUATION_MERGED)) {
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
        KcKrmsFactBuilderService fbService = KraServiceLocator.getService("iacucProtocolFactBuilderService");
        fbService.addFacts(factsBuilder, this);
    }

    @Override
    protected ProtocolActionBase getNewProtocolActionInstanceHook(ProtocolBase protocol, ProtocolSubmissionBase protocolSubmission, String protocolStatusCode) {
        String protocolActionTypeCode = IacucProtocolActionType.RENEWAL_CREATED; 
        if (protocolStatusCode.equals(IacucProtocolStatus.AMENDMENT_MERGED)) {
            protocolActionTypeCode = IacucProtocolActionType.AMENDMENT_CREATED;
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
        return KraServiceLocator.getService(IacucProtocolFinderDao.class);
    }


    protected ProtocolVersionService getProtocolVersionServiceHook() {
        return KraServiceLocator.getService(IacucProtocolVersionService.class);
    }


    protected String getProtocolActionTypeApprovedHook() {
        return IacucProtocolActionType.IACUC_APPROVED;
    }


    protected String getProtocolStatusExemptHook() {
        return IacucProtocolStatus.ADMINISTRATIVELY_INCOMPLETE;
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
        }
        return mergedStatus;
    }

    @Override
    public boolean isNormal() {
        return !isAmendment() && !isRenewal() && !isContinuation();
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
        
        ProtocolActionBase action = getNewProtocolActionInstanceHook(newProtocolDocument.getProtocol(), null, getProtocolActionTypeApprovedHook()); 
        action.setComments(type + "-" + getProtocolNumberIndex() + ": Approved");
        newProtocolDocument.setProtocolWorkflowType(ProtocolWorkflowType.APPROVED);
        newProtocolDocument.getProtocol().getProtocolActions().add(action);
        if (!currentProtocol.getProtocolStatusCode().equals(getProtocolStatusExemptHook())) {
            newProtocolDocument.getProtocol().setProtocolStatusCode(getProtocolStatusActiveOpenToEnrollmentHook());
        }
        try {
            getDocumentService().saveDocument(newProtocolDocument);
            // blanket approve to make the new protocol document 'final'
            newProtocolDocument.getDocumentHeader().getWorkflowDocument().route(type + "-" + getProtocolNumberIndex() + ": merged");
        } catch (WorkflowException e) {
            throw new ProtocolMergeException(e);
        }
        
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
        getBusinessObjectService().save(this);
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
