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
