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
package org.kuali.kra.negotiations.rules;

import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.negotiations.bo.Negotiation;
import org.kuali.kra.negotiations.document.NegotiationDocument;
import org.kuali.kra.negotiations.service.NegotiationService;
import org.kuali.kra.rules.ResearchDocumentRuleBase;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.util.GlobalVariables;

/**
 * 
 * This class handles the business rules for the Negotiation Object.
 */
public class NegotiationDocumentRule extends ResearchDocumentRuleBase {
    
    private static final String NEGOTIATION_ERROR_PATH = "document.negotiationList[0]";
    private static final String END_DATE_PROPERTY = "negotiationEndDate";
    
    private NegotiationService negotiationService;
    
    /**
     * 
     * Constructs a NegotiationDocumentRule.java.
     */
    public NegotiationDocumentRule() {
        super();
    }
    
    @Override
    protected boolean processCustomSaveDocumentBusinessRules(Document document) {
        if (!(document instanceof NegotiationDocument)) {
            return false;
        }
        
        GlobalVariables.getMessageMap().addToErrorPath(DOCUMENT_ERROR_PATH);
        getDictionaryValidationService().validateDocumentAndUpdatableReferencesRecursively(
            document, getMaxDictionaryValidationDepth(), VALIDATION_REQUIRED, CHOMP_LAST_LETTER_S_FROM_COLLECTION_NAME);
        GlobalVariables.getMessageMap().removeFromErrorPath(DOCUMENT_ERROR_PATH);
        
        NegotiationDocument negotiationDocument = (NegotiationDocument) document;
        Negotiation negotiation = negotiationDocument.getNegotiation();
        
        GlobalVariables.getMessageMap().addToErrorPath(NEGOTIATION_ERROR_PATH);
        boolean result = true;
        
        result &= validateEndDate(negotiation);
        GlobalVariables.getMessageMap().removeFromErrorPath(NEGOTIATION_ERROR_PATH);
        
        return result;
    }
    
    public boolean validateEndDate(Negotiation negotiation) {
        boolean result = true;
        if (negotiation.getNegotiationEndDate() != null 
                && getNegotiationService().getInProgressStatusCodes().contains(negotiation.getNegotiationStatus().getCode())) {
            result = false;
            getErrorReporter().reportError(END_DATE_PROPERTY, KeyConstants.NEGOTIATION_ERROR_INPROGRESS_END_DATE);
        }
        
        if (negotiation.getNegotiationEndDate() == null
                && getNegotiationService().getCompletedStatusCodes().contains(negotiation.getNegotiationStatus().getCode())) {
            result = false;
            getErrorReporter().reportError(END_DATE_PROPERTY, KeyConstants.NEGOTIATION_ERROR_COMPLETED_END_DATE);            
        }
        
        if (negotiation.getNegotiationEndDate() != null
                && negotiation.getNegotiationEndDate().compareTo(negotiation.getNegotiationStartDate()) < 0) {
            result = false;
            getErrorReporter().reportError(END_DATE_PROPERTY, KeyConstants.NEGOTIATION_ERROR_END_DATE_GREATER_THAN_START);
        }
        return result;
    }

    protected NegotiationService getNegotiationService() {
        if (negotiationService == null) {
            negotiationService = KraServiceLocator.getService(NegotiationService.class);
        }
        return negotiationService;
    }

    public void setNegotiationService(NegotiationService negotiationService) {
        this.negotiationService = negotiationService;
    }

}
