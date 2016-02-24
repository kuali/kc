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
package org.kuali.kra.negotiations.customdata;

import org.kuali.coeus.common.framework.custom.CustomDataHelperBase;
import org.kuali.coeus.common.framework.custom.attr.CustomAttributeDocument;
import org.kuali.kra.negotiations.bo.Negotiation;
import org.kuali.kra.negotiations.document.NegotiationDocument;
import org.kuali.kra.negotiations.web.struts.form.NegotiationForm;
import org.kuali.rice.kew.api.WorkflowDocument;

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
       return true;
    }
}
