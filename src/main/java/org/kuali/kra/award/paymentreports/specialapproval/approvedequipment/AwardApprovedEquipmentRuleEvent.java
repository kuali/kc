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
package org.kuali.kra.award.paymentreports.specialapproval.approvedequipment;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.rule.event.KraDocumentEventBase;
import org.kuali.rice.krad.rules.rule.BusinessRule;

/**
 * This the AwardApprovedEquipmentRuleEvent
 */
public class AwardApprovedEquipmentRuleEvent extends KraDocumentEventBase {
    private static final Log LOG = LogFactory.getLog(AwardApprovedEquipmentRuleEvent.class);
    
    private Award award;
    private AwardApprovedEquipment approvedEquipmentItem;
    private MinimumCapitalizationInfo minimumCapitalization;
    
    public AwardApprovedEquipmentRuleEvent(String errorPathPrefix, 
                                            AwardDocument awardDocument,
                                            Award award,
                                            AwardApprovedEquipment approvedEquipmentItem,
                                            MinimumCapitalizationInfo minimumCapitalization) {
        super("Approved equipment item", errorPathPrefix, awardDocument);
        this.award = award;
        this.approvedEquipmentItem = approvedEquipmentItem;
        this.minimumCapitalization = minimumCapitalization; 
    }

    /**
     * Convenience method to return an Award
     * @return
     */
    public Award getAward() {
        return award;
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

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((approvedEquipmentItem == null) ? 0 : approvedEquipmentItem.hashCode());
        result = PRIME * result + ((minimumCapitalization == null) ? 0 : minimumCapitalization.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof AwardApprovedEquipmentRuleEvent)) {
            return false;
        }
        AwardApprovedEquipmentRuleEvent other = (AwardApprovedEquipmentRuleEvent) obj;
        if (approvedEquipmentItem == null) {
            if (other.approvedEquipmentItem != null) {
                return false;
            }
        } else if (!approvedEquipmentItem.equals(other.approvedEquipmentItem)) {
            return false;
        }
        if (minimumCapitalization == null) {
            if (other.minimumCapitalization != null) {
                return false;
            }
        } else if (!minimumCapitalization.equals(other.minimumCapitalization)) {
            return false;
        }
        return true;
    }
}
