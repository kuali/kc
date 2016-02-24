/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.kra.award.paymentreports.specialapproval.foreigntravel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.sys.framework.rule.KcDocumentEventBase;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.paymentreports.specialapproval.approvedequipment.AwardApprovedEquipmentRule;
import org.kuali.rice.krad.rules.rule.BusinessRule;

/**
 * This the AwardApprovedEquipmentRuleEvent
 */
public class AwardApprovedForeignTravelRuleEvent extends KcDocumentEventBase {
    private static final Log LOG = LogFactory.getLog(AwardApprovedForeignTravelRuleEvent.class);
    
    private Award award;
    private AwardApprovedForeignTravel approvedForeignTravel;
    
    public AwardApprovedForeignTravelRuleEvent(String errorPathPrefix, 
                                            AwardDocument awardDocument,
                                            Award award,
                                            AwardApprovedForeignTravel approvedForeignTravel) {
        super("Approved equipment item", errorPathPrefix, awardDocument);
        this.award = award;
        this.approvedForeignTravel = approvedForeignTravel; 
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
    public AwardApprovedForeignTravel getForeignTravelForValidation() {
        return approvedForeignTravel;
    }

    @Override
    protected void logEvent() {
        LOG.info("Logging AwardApprovedForeignTravelRuleEvent");
    }

    @Override
    @SuppressWarnings("unchecked")
    public Class getRuleInterfaceClass() {
        return AwardApprovedEquipmentRule.class;
    }

    @Override
    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((AwardApprovedForeignTravelRule)rule).processAwardApprovedForeignTravelBusinessRules(this);
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((approvedForeignTravel == null) ? 0 : approvedForeignTravel.hashCode());
        result = PRIME * result + ((award == null) ? 0 : award.hashCode());
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
        if (!(obj instanceof AwardApprovedForeignTravelRuleEvent)) {
            return false;
        }
        AwardApprovedForeignTravelRuleEvent other = (AwardApprovedForeignTravelRuleEvent) obj;
        if (approvedForeignTravel == null) {
            if (other.approvedForeignTravel != null) {
                return false;
            }
        } else if (!approvedForeignTravel.equals(other.approvedForeignTravel)) {
            return false;
        }
        if (award == null) {
            if (other.award != null) {
                return false;
            }
        } else if (!award.equals(other.award)) {
            return false;
        }
        return true;
    }
}
