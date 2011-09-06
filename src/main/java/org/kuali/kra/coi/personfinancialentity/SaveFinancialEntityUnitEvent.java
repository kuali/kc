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
package org.kuali.kra.coi.personfinancialentity;

import java.util.List;

import org.kuali.kra.rule.BusinessRuleInterface;
import org.kuali.kra.rule.event.KraDocumentEventBaseExtension;

public class SaveFinancialEntityUnitEvent  extends KraDocumentEventBaseExtension {
    
    private String propertyName;
    private List<FinancialEntityUnit> financialEntityUnits;
    /**
     * Constructs a ProtocolAddReviewAttachmentEvent.
     * 
     * @param document The document to validate
     * @param propertyName The error path property prefix
     * @param reviewAttachment The added Reviewer Attachment
     */
    public SaveFinancialEntityUnitEvent(String propertyName, List<FinancialEntityUnit> financialEntityUnits) {
        super("Add financial entity unit", "", null);
        this.propertyName = propertyName;
        this.financialEntityUnits = financialEntityUnits;
    }
        
    public String getPropertyName() {
        return propertyName;
    }
    
 
    @Override
    @SuppressWarnings("unchecked")
    public BusinessRuleInterface getRule() {
        return new SaveFinancialEntityUnitRule();
    }

    public List<FinancialEntityUnit> getFinancialEntityUnits() {
        return financialEntityUnits;
    }

    public void setFinancialEntityUnits(List<FinancialEntityUnit> financialEntityUnits) {
        this.financialEntityUnits = financialEntityUnits;
    }


}
