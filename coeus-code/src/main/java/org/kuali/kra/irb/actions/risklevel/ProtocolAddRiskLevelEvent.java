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
package org.kuali.kra.irb.actions.risklevel;

import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.coeus.sys.framework.rule.KcDocumentEventBaseExtension;
import org.kuali.kra.irb.ProtocolDocument;

/**
 * Encapsulates a validation event for a Protocol Risk Level add action.
 */
public class ProtocolAddRiskLevelEvent extends KcDocumentEventBaseExtension {
    
    private String propertyName;
    private ProtocolRiskLevel riskLevel;

    /**
     * Constructs a ProtocolAddRiskLevelEvent.
     * 
     * @param document The document to validate
     * @param propertyName The error path property prefix
     * @param riskLevel The added Risk Level
     */
    public ProtocolAddRiskLevelEvent(ProtocolDocument document, String propertyName, ProtocolRiskLevel riskLevel) {
        super("Enter risk level", "", document);
        this.propertyName = propertyName;
        this.riskLevel = riskLevel;
    }
    
    public ProtocolDocument getProtocolDocument() {
        return (ProtocolDocument) getDocument();
    }
    
    public String getPropertyName() {
        return propertyName;
    }
    
    public ProtocolRiskLevel getProtocolRiskLevel() {
        return riskLevel;
    }

    @Override
    @SuppressWarnings("unchecked")
    public KcBusinessRule getRule() {
        return new ProtocolAddRiskLevelRule();
    }

}