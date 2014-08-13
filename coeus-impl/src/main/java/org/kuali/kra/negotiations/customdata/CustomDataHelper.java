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
package org.kuali.kra.negotiations.customdata;

import org.kuali.coeus.common.framework.custom.CustomDataHelperBase;
import org.kuali.coeus.common.framework.custom.attr.CustomAttributeDocument;
import org.kuali.kra.negotiations.bo.Negotiation;
import org.kuali.kra.negotiations.document.NegotiationDocument;
import org.kuali.kra.negotiations.web.struts.form.NegotiationForm;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.krad.document.Document;

import java.util.List;
import java.util.Map;

/**
 *   CustomDataHelper class
 */
public class CustomDataHelper extends CustomDataHelperBase<NegotiationCustomData> {


    private static final long serialVersionUID = -716264183914346452L;

    private NegotiationForm negotiationForm;    
    
    /**
     * Constructs a CustomDataHelper.
     * @param from the awardForm
     */
    public CustomDataHelper(NegotiationForm negotiationForm) {
        this.negotiationForm = negotiationForm;
    }
    
    
    
    /*
     * Get the Negotiation.
     */
    private Negotiation getNegotiation() {
        NegotiationDocument document = negotiationForm.getNegotiationDocument();
        if (document == null || document.getNegotiation() == null) {
            throw new IllegalArgumentException("invalid (null) NegotiationDocument in NegotiationForm");
        }
        return document.getNegotiation();
    }
    
    @Override
    public boolean canModifyCustomData() {

        return false;
    }
    
    @Override
    protected NegotiationCustomData getNewCustomData() {
        return new NegotiationCustomData();
    }

    @Override
    public List<NegotiationCustomData> getCustomDataList() {
        return getNegotiation().getNegotiationCustomDataList();
    }

    @Override
    public Map<String, CustomAttributeDocument> getCustomAttributeDocuments() {
        return negotiationForm.getNegotiationDocument().getCustomAttributeDocuments();
    }
   
    @Override
    public boolean documentNotRouted() {
        WorkflowDocument doc = negotiationForm.getNegotiationDocument().getDocumentHeader().getWorkflowDocument();
        return doc.isSaved() || doc.isInitiated();

    }
}
