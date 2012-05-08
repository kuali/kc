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

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.authorization.KraAuthorizationConstants;
import org.kuali.kra.iacuc.actions.IacucProtocolStatus;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmissionStatus;
import org.kuali.kra.iacuc.protocol.location.IacucProtocolLocationService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.protocol.ProtocolDocument;
import org.kuali.kra.protocol.protocol.location.ProtocolLocationService;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants.COMPONENT;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants.NAMESPACE;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.krad.util.GlobalVariables;


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
        if (this.getProtocol().getProtocolStatusCode().equals(IacucProtocolStatus.SUBMITTED_TO_IACUC)) {
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
                if (status.equals(IacucProtocolSubmissionStatus.APPROVED) 
                        && getWorkflowDocumentService().getCurrentRouteNodeNames(getDocumentHeader().getWorkflowDocument()).equalsIgnoreCase(Constants.PROTOCOL_IRBREVIEW_ROUTE_NODE_NAME)) {
                    isComplete = false;
                }
            }

        } else {
//TODO: Must implement the following for IACUC
//            /*
//             * If amendment has been merged, need to redirect to the newly created active protocol
//             * Wait for the new active protocol to be created before redirecting to it.
//             */
//            if (getProtocol().getProtocolStatusCode().equals(ProtocolStatus.AMENDMENT_MERGED) || 
//                    getProtocol().getProtocolStatusCode().equals(ProtocolStatus.RENEWAL_MERGED)) {
//                String protocolId = getNewProtocolDocId();               
//                if (ObjectUtils.isNull(protocolId)) {
//                    isComplete = false;
//                } else {
//                    /*
//                     * The new protocol document is only available after the amendment has been merged. So, once the amendment is merged,
//                     * find the active protocol available and change the return link to point to that.
//                     */
//                    String oldLocation = (String) GlobalVariables.getUserSession().retrieveObject(Constants.HOLDING_PAGE_RETURN_LOCATION);
//                    String oldDocNbr = getProtocol().getProtocolDocument().getDocumentNumber();
//                    String returnLocation = oldLocation.replaceFirst(oldDocNbr, protocolId);
//                    GlobalVariables.getUserSession().addObject(Constants.HOLDING_PAGE_RETURN_LOCATION, (Object) returnLocation);
//                }
//            }         
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

}
