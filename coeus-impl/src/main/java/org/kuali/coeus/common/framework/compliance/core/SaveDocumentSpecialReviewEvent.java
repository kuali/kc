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
package org.kuali.coeus.common.framework.compliance.core;

import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.BusinessRule;
import org.kuali.rice.krad.rules.rule.event.SaveDocumentEvent;

public class SaveDocumentSpecialReviewEvent<T extends SpecialReview> extends SaveDocumentEvent {
  
    public SaveDocumentSpecialReviewEvent(String errorPathPrefix, Document document) {
        super(errorPathPrefix, document);
    }
    
    @Override
    public Class<? extends BusinessRule> getRuleInterfaceClass() {
        return SaveDocumentSpecialReviewRule.class;
    }

    @Override
    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((SaveDocumentSpecialReviewRule) rule).processSaveDocumentSpecialReview(this);
    }
}
