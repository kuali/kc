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
 * Encapsulates a validation event for a Protocol Risk Level update action.
 */
public class ProtocolUpdateRiskLevelEvent extends KcDocumentEventBaseExtension {
    
    private int index;

    /**
     * Constructs a ProtocolUpdateRiskLevelEvent.
     * 
     * @param document The document to validate
     * @param index The index of the Risk Level to validate
     */
    public ProtocolUpdateRiskLevelEvent(ProtocolDocument document, int index) {
        super("Enter risk level", "", document);
        this.index = index;
    }
    
    public ProtocolDocument getProtocolDocument() {
        return (ProtocolDocument) getDocument();
    }
    
    public int getIndex() {
        return index;
    }

    @Override
    @SuppressWarnings("unchecked")
    public KcBusinessRule getRule() {
        return new ProtocolUpdateRiskLevelRule();
    }

}