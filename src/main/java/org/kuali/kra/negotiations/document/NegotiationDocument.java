/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.negotiations.document;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.document.ResearchDocumentBase;
import org.kuali.kra.negotiations.bo.Negotiation;
import org.kuali.kra.negotiations.bo.NegotiationUnassociatedDetail;
import org.kuali.rice.kew.dto.DocumentRouteStatusChangeDTO;
import org.kuali.rice.kew.util.KEWConstants;
import org.kuali.rice.kns.document.Copyable;
import org.kuali.rice.kns.document.SessionDocument;
import org.kuali.rice.kns.util.ObjectUtils;

/**
 * This class...
 */
public class NegotiationDocument extends ResearchDocumentBase implements Serializable {
    
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 2635757819118105L;

    private static final String DOCUMENT_TYPE_CODE = "NGT";
    
    private Negotiation negotiation;
    private NegotiationUnassociatedDetail unAssociatedDetail;
    private String docStatusCode;
    
    /**
     * 
     * Constructs a NegotiationDocument.java.
     */
    public NegotiationDocument() {
        Negotiation neg = new Negotiation();
        this.setNegotiation(neg);
    }  
    
    /**
     * 
     * This method returns a new instance of NegotiationDocument.
     * @return
     */
    public NegotiationDocument newInstance() {
        return new NegotiationDocument();
    }
    
    /**
     * 
     * @see org.kuali.kra.document.ResearchDocumentBase#initialize()
     */
    @Override
    public void initialize() {
        super.initialize();
    }
    
    public Negotiation getNegotiation() {
        return negotiation;
    }

    public void setNegotiation(Negotiation negotiation) {
        this.negotiation = negotiation;
    }

    public NegotiationUnassociatedDetail getUnAssociatedDetail() {
        return unAssociatedDetail;
    }

    public void setUnAssociatedDetail(NegotiationUnassociatedDetail unAssociatedDetail) {
        this.unAssociatedDetail = unAssociatedDetail;
    }
    
    public String getDocStatusCode() {
        return docStatusCode;
    }

    public void setDocStatusCode(String docStatusCode) {
        this.docStatusCode = docStatusCode;
    }

    /**
     * @see org.kuali.kra.document.ResearchDocumentBase#getDocumentTypeCode()
     */
    @Override
    public String getDocumentTypeCode() {
        return DOCUMENT_TYPE_CODE;
    }
    
    @Override
    public void prepareForSave() {
        super.prepareForSave();
        if (ObjectUtils.isNull(this.getVersionNumber())) {
            this.setVersionNumber(new Long(0));
        }
        String routeStatusCode = this.getDocumentHeader().getWorkflowDocument().getRouteHeader().getDocRouteStatus();
        if (StringUtils.isNotBlank(routeStatusCode) && routeStatusCode.equals(KEWConstants.ROUTE_HEADER_INITIATED_CD)) {
            // route status from I to S will not update document, so do it here with correct status
            this.setDocStatusCode(KEWConstants.ROUTE_HEADER_SAVED_CD);
        } else {
            this.setDocStatusCode(routeStatusCode);
        }
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
     * This method is to check whether rice async routing is ok now.   
     * Close to hack.  called by holdingpageaction
     * Different document type may have different routing set up, so each document type
     * can implement its own isProcessComplete
     * @return
     */
    public boolean isProcessComplete() {
        boolean isComplete = false;
        
        if (getDocumentHeader().hasWorkflowDocument()) {
            String docRouteStatus = getDocumentHeader().getWorkflowDocument().getRouteHeader().getDocRouteStatus();
            if (KEWConstants.ROUTE_HEADER_FINAL_CD.equals(docRouteStatus)) {
                isComplete = true;
            }
        }
           
        return isComplete;
    }

}
