/*
 * Copyright 2005-2013 The Kuali Foundation
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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.drools.core.util.StringUtils;
import org.kuali.kra.bo.CustomAttributeDocument;
import org.kuali.kra.bo.DocumentCustomData;
import org.kuali.kra.common.customattributes.CustomDataHelperBase;
import org.kuali.kra.document.ResearchDocumentBase;
import org.kuali.kra.negotiations.bo.Negotiation;
import org.kuali.kra.negotiations.document.NegotiationDocument;
import org.kuali.kra.negotiations.web.struts.form.NegotiationForm;

/**
 *   CustomDataHelper class
 */
public class CustomDataHelper extends CustomDataHelperBase<NegotiationCustomData> {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -716264183914346452L;
    private static final String MAPPING_CUSTOM_DATA = "customData";
    
    
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
        // TODO Auto-generated method stub
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



}
