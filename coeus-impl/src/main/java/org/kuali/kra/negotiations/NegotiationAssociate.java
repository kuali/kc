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
package org.kuali.kra.negotiations;

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.kra.negotiations.bo.Negotiation;

/**
 * This class handles the legacy sequenceNumber/awardNumber data from Coeus
 */
public class NegotiationAssociate extends KcPersistableBusinessObjectBase {


    private static final long serialVersionUID = -3915267055124592134L;

    private String negotiationNumber;

    private Negotiation negotiation;

    /**
     * Gets the proposalNumber attribute. 
     * @return Returns the proposalNumber.
     */
    public String getNegotiationNumber() {
        return negotiationNumber;
    }

    /**
     * Sets the proposalNumber attribute value.
     * @param proposalNumber The proposalNumber to set.
     */
    public void setNegotiationNumber(String negotiationNumber) {
        this.negotiationNumber = negotiationNumber;
    }

    /**
     * Gets the negotiations attribute. 
     * @return Returns the negotiations.
     */
    public Negotiation getNegotiation() {
        return negotiation;
    }

    /**
     * Sets the negotiation attribute value.
     * @param negotiation The negotiation to set.
     */
    public void setNegotiation(Negotiation negotiation) {
        this.negotiation = negotiation;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof NegotiationAssociate)) {
            return false;
        }
        NegotiationAssociate other = (NegotiationAssociate) obj;
        if (negotiationNumber == null) {
            if (other.negotiationNumber != null) {
                return false;
            }
        } else if (!negotiationNumber.equals(other.negotiationNumber)) {
            return false;
        }
        return true;
    }
}
