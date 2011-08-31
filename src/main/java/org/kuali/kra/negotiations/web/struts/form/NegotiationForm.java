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
package org.kuali.kra.negotiations.web.struts.form;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.kuali.kra.authorization.KraAuthorizationConstants;
import org.kuali.kra.negotiations.document.NegotiationDocument;
import org.kuali.kra.web.struts.form.KraTransactionalDocumentFormBase;
import org.kuali.rice.kns.util.KNSConstants;

/**
 * 
 * This class holds all the objects required for a negotiation web object.
 */
public class NegotiationForm extends KraTransactionalDocumentFormBase {
    
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(NegotiationForm.class);

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -3641922366447898075L;
    
    /**
     * 
     * Constructs a NegotiationForm.java.
     */
    public NegotiationForm() {
        super();
    }
    
    public NegotiationDocument getNegotiationDocument() {
        return this.getDocument();
    }
    
    @Override
    public NegotiationDocument getDocument() {
        return (NegotiationDocument) super.getDocument();
    }

    @Override
    protected String getDefaultDocumentTypeName() {
        return "NegotiationDocument";
    }

    @Override
    protected String getLockRegion() {
        return KraAuthorizationConstants.LOCK_NEGOTIATION;
    }

    @Override
    protected void setSaveDocumentControl(Map editMode) {
        getDocumentActions().put(KNSConstants.KUALI_ACTION_CAN_SAVE, KNSConstants.KUALI_DEFAULT_TRUE_VALUE);

    }
    
    @Override
    public void populate(HttpServletRequest request) {
        super.populate(request);
    }
}