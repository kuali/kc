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
package org.kuali.kra.award.rule.event;

import org.apache.log4j.Logger;
import org.kuali.core.rule.BusinessRule;
import org.kuali.kra.award.bo.AwardApprovedEquipment;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.rule.AwardApprovedEquipmentRule;
import org.kuali.kra.award.rules.MinimumCapitalizationInfo;
import org.kuali.kra.rule.event.KraDocumentEventBase;

/**
 * This the AwardApprovedEquipmentRuleEvent
 */
public class AwardApprovedEquipmentRuleEvent extends KraDocumentEventBase {
    private static final Logger LOG = Logger.getLogger(AwardApprovedEquipmentRuleEvent.class);
    
    private AwardApprovedEquipment approvedEquipmentItem;
    private MinimumCapitalizationInfo minimumCapitalization;
    
    public AwardApprovedEquipmentRuleEvent(String errorPathPrefix, 
                                            AwardDocument awardDocument,
                                            AwardApprovedEquipment approvedEquipmentItem,
                                            MinimumCapitalizationInfo minimumCapitalization) {
        super("Approved equipment item", errorPathPrefix, awardDocument);
        this.approvedEquipmentItem = approvedEquipmentItem;
        this.minimumCapitalization = minimumCapitalization;
    }

    /**
     * Convenience method to return an AwardDocument
     * @return
     */
    public AwardDocument getAwardDocument() {
        return (AwardDocument) getDocument();
    }
    
    /**
     * This method returns the equipment item for validation
     * @return
     */
    public AwardApprovedEquipment getEquipmentItemForValidation() {
        return approvedEquipmentItem;
    }

    /**
     * Gets the institutionMinimumCapitalization attribute. 
     * @return Returns the institutionMinimumCapitalization.
     */
    public MinimumCapitalizationInfo getMinimumCapitalization() {
        return minimumCapitalization;
    }

    /**
     * @see org.kuali.kra.rule.event.KraDocumentEventBase#logEvent()
     */
    @Override
    protected void logEvent() {
        LOG.info("Logging AwardApprovedEquipmentRuleEvent");
    }

    /**
     * @see org.kuali.core.rule.event.KualiDocumentEvent#getRuleInterfaceClass()
     */
    @SuppressWarnings("unchecked")
    public Class getRuleInterfaceClass() {
        return AwardApprovedEquipmentRule.class;
    }

    /**
     * @see org.kuali.core.rule.event.KualiDocumentEvent#invokeRuleMethod(org.kuali.core.rule.BusinessRule)
     */
    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((AwardApprovedEquipmentRule)rule).processAwardApprovedEquipmentBusinessRules(this);
    }
}