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

package org.kuali.kra.iacuc;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.bo.ResearchArea;
import org.kuali.kra.iacuc.actions.IacucProtocolAction;
import org.kuali.kra.iacuc.actions.IacucProtocolActionType;
import org.kuali.kra.iacuc.actions.IacucProtocolStatus;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolActionService;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmission;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmissionStatus;
import org.kuali.kra.iacuc.protocol.location.IacucProtocolLocationService;
import org.kuali.kra.iacuc.protocol.research.IacucProtocolResearchAreaService;
import org.kuali.kra.iacuc.rules.IacucProtocolFactBuilderService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.krms.KcKrmsConstants;
import org.kuali.kra.protocol.Protocol;
import org.kuali.kra.protocol.ProtocolDocument;
import org.kuali.kra.protocol.ProtocolFinderDao;
import org.kuali.kra.protocol.ProtocolVersionService;
import org.kuali.kra.protocol.actions.ProtocolAction;
import org.kuali.kra.protocol.actions.submit.ProtocolActionService;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmission;
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
 * This class represents the Protocol Document Object.
 * ProtocolDocument has a 1:1 relationship with Protocol Business Object.
 * We have declared a list of Protocol BOs in the ProtocolDocument at the same time to
 * get around the OJB anonymous keys issue of primary keys of different data types.
 * Also we have provided convenient getter and setter methods so that to the outside world;
 * Protocol and ProtocolDocument can have a 1:1 relationship.
 */
@NAMESPACE(namespace=Constants.MODULE_NAMESPACE_IACUC)
@COMPONENT(component=ParameterConstants.DOCUMENT_COMPONENT)
public class IacucProtocolDocument extends ProtocolDocument { 
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -1014286912251147390L;
    private static final Log LOG = LogFactory.getLog(IacucProtocolDocument.class);
    public static final String DOCUMENT_TYPE_CODE = "ICPR";
    
    private static final String CONTINUATION_KEY = "C";
	
    /**
     * Constructs a ProtocolDocument object.
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
        IacucProtocolFactBuilderService fbService = KraServiceLocator.getService(IacucProtocolFactBuilderService.class);
        fbService.addFacts(factsBuilder, this);
    }

    @Override
    protected ProtocolAction getNewProtocolActionInstanceHook(Protocol protocol, ProtocolSubmission protocolSubmission, String protocolStatusCode) {
        String protocolActionTypeCode = IacucProtocolActionType.RENEWAL_CREATED; 
        if(protocolStatusCode.equals(IacucProtocolStatus.AMENDMENT_MERGED)) {
            protocolActionTypeCode = IacucProtocolActionType.AMENDMENT_CREATED;
        }else if(protocolStatusCode.equals(IacucProtocolStatus.CONTINUATION_MERGED)) {
            protocolActionTypeCode = IacucProtocolActionType.CONTINUATION;
        }
        return new IacucProtocolAction((IacucProtocol) protocol, (IacucProtocolSubmission) protocolSubmission, protocolActionTypeCode);
    }

    @Override
    protected Class<? extends ProtocolActionService> getProtocolActionServiceClassHook() {
        return IacucProtocolActionService.class;
    }

    @Override
    protected Class<? extends Protocol> getProtocolBOClassHook() {
        return IacucProtocol.class;
    }

    @Override
    protected ProtocolFinderDao getProtocolFinderDaoHook() {
        return KraServiceLocator.getService(IacucProtocolFinderDao.class);
    }

    @Override
    protected ProtocolVersionService getProtocolVersionServiceHook() {
        return KraServiceLocator.getService(IacucProtocolVersionService.class);
    }

    @Override
    protected String getProtocolActionTypeApprovedHook() {
        return IacucProtocolActionType.IACUC_APPROVED;
    }

    @Override
    protected String getProtocolStatusExemptHook() {
        return IacucProtocolStatus.ADMINISTRATIVELY_INCOMPLETE;
    }

    @Override
    protected String getProtocolStatusActiveOpenToEnrollmentHook() {
        return IacucProtocolStatus.ACTIVE;
    }

    @Override
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
    protected Class<? extends ResearchArea> getResearchAreaBoClassHook() {
        return IacucResearchArea.class;
    }

}
