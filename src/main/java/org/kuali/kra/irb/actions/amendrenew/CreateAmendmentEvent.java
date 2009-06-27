/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.irb.actions.amendrenew;

import java.util.List;

import org.kuali.kra.committee.rules.CommitteeScheduleFilterDatesRule;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.rule.BusinessRuleInterface;
import org.kuali.kra.rule.event.KraDocumentEventBaseExtension;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.rule.BusinessRule;
import org.kuali.rice.kns.rule.event.KualiDocumentEvent;

public class CreateAmendmentEvent<T extends BusinessRuleInterface> extends KraDocumentEventBaseExtension {

    private ProtocolAmendmentBean amendmentBean;
    private String propertyName;

    public CreateAmendmentEvent(ProtocolDocument document, String propertyName, ProtocolAmendmentBean amendmentBean) {
        super("Create Amendment", "", document);
        this.propertyName = propertyName;
        this.amendmentBean = amendmentBean;
    }
    
    public ProtocolDocument getProtocolDocument() {
        return (ProtocolDocument) getDocument();
    }
    
    public String getPropertyName() {
        return propertyName;
    }
    
    public ProtocolAmendmentBean getAmendmentBean() {
        return amendmentBean;
    }

    @SuppressWarnings("unchecked")
    @Override
    public BusinessRuleInterface getRule() {
        return new CreateAmendmentRule();
    }
}
