/*
 * Copyright 2005-2014 The Kuali Foundation
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

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.custom.DocumentCustomData;
import org.kuali.coeus.sys.framework.model.KcTransactionalDocumentBase;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.negotiations.bo.Negotiation;
import org.kuali.kra.negotiations.bo.NegotiationActivity;
import org.kuali.kra.negotiations.bo.NegotiationActivityAttachment;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants.COMPONENT;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants.NAMESPACE;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.krad.util.ObjectUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@NAMESPACE(namespace=Constants.MODULE_NAMESPACE_NEGOTIATION)
@COMPONENT(component=ParameterConstants.DOCUMENT_COMPONENT)
public class NegotiationDocument extends KcTransactionalDocumentBase implements Serializable {
    

    private static final long serialVersionUID = 2635757819118105L;

    public static final String DOCUMENT_TYPE_CODE = "NGT";
    
    private List<Negotiation> negotiationList;
    private String docStatusCode;
    
    

    public NegotiationDocument() {
        negotiationList = new ArrayList<Negotiation>();
        negotiationList.add(new Negotiation());
    }
    
    /**
     * 
     * This method returns a new instance of NegotiationDocument.
     * @return
     */
    public NegotiationDocument newInstance() {
        return new NegotiationDocument();
    }
    
    @Override
    public void initialize() {
        super.initialize();
    }
    
    public Negotiation getNegotiation() {
        if (getNegotiationList().isEmpty()) {
            getNegotiationList().add(new Negotiation());
        }
        return getNegotiationList().get(0);
    }
    
    public String getDocStatusCode() {
        return docStatusCode;
    }

    public void setDocStatusCode(String docStatusCode) {
        this.docStatusCode = docStatusCode;
    }

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
        String routeStatusCode = this.getDocumentHeader().getWorkflowDocument().getStatus().getCode();
        if (StringUtils.isNotBlank(routeStatusCode) && routeStatusCode.equals(KewApiConstants.ROUTE_HEADER_INITIATED_CD)) {
            // route status from I to S will not update document, so do it here with correct status
            this.setDocStatusCode(KewApiConstants.ROUTE_HEADER_SAVED_CD);
        } else {
            this.setDocStatusCode(routeStatusCode);
        }
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
            String docRouteStatus = getDocumentHeader().getWorkflowDocument().getStatus().getCode();
            if (KewApiConstants.ROUTE_HEADER_FINAL_CD.equals(docRouteStatus)) {
                isComplete = true;
            }
        }
           
        return isComplete;
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public List buildListOfDeletionAwareLists() {
        List managedLists = super.buildListOfDeletionAwareLists();
        
        managedLists.add(negotiationList);
        
        managedLists.add(getNegotiation().getActivities());
        
        List<NegotiationActivityAttachment> attachments = new ArrayList<NegotiationActivityAttachment>();
        for (NegotiationActivity activity : getNegotiation().getActivities()) {
            attachments.addAll(activity.getAttachments());
        }
        managedLists.add(attachments);
        
        return managedLists;
    }

    public List<Negotiation> getNegotiationList() {
        return negotiationList;
    }

    public void setNegotiationList(List<Negotiation> negotiationList) {
        this.negotiationList = negotiationList;
    }

    @Override
    public List<? extends DocumentCustomData> getDocumentCustomData() {
        return getNegotiation().getNegotiationCustomDataList();
    }

    public String getDocumentBoNumber() {
        return getNegotiation().getNegotiationId().toString();
        
    }
}
