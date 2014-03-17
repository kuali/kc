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
package org.kuali.kra.iacuc.species.exception.rule;

import org.kuali.kra.iacuc.IacucProtocolDocument;
import org.kuali.kra.iacuc.species.exception.IacucProtocolException;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.BusinessRule;

/**
 * This class hooks Rule to Event in KNS
 */
public class AddProtocolExceptionEvent extends ProtocolExceptionEventBase {

    /**
     * 
     * Constructs a AddProtocolExceptionEvent.java.
     * @param errorPathPrefix
     * @param document
     * @param iacucProtocolException
     */
    public AddProtocolExceptionEvent(String errorPathPrefix, IacucProtocolDocument document, IacucProtocolException iacucProtocolException) {
        super("adding ProtocolException to document " + getDocumentId(document), errorPathPrefix, document, iacucProtocolException);
    }
    
    /**
     * 
     * Constructs a AddProtocolExceptionEvent.java.
     * @param errorPathPrefix
     * @param document
     * @param iacucProtocolException
     */
    public AddProtocolExceptionEvent(String errorPathPrefix, Document document, IacucProtocolException iacucProtocolException) {
        this(errorPathPrefix, (IacucProtocolDocument)document, iacucProtocolException);
    } 
    
    @Override
    public Class getRuleInterfaceClass() {
        return AddProtocolExceptionRule.class;
    }

    @Override
    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((AddProtocolExceptionRule)rule).processAddProtocolExceptionBusinessRules(this);
    }


}
