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
package org.kuali.kra.negotiations;

import java.util.LinkedHashMap;

import org.kuali.kra.negotiations.NegotiationAssociate;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.negotiations.bo.Negotiation;

/**
 * This class handles the legacy sequenceNumber/awardNumber data from Coeus
 */
public class NegotiationAssociate extends KraPersistableBusinessObjectBase {

    /**
     * Comment for <code>serialVersionUID</code>
     */
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

    
    /** {@inheritDoc} */
    @Override 
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> hashMap = new LinkedHashMap<String, Object>();
        hashMap.put("negotiationNumber", this.getNegotiationNumber());
        hashMap.put("negotiation", this.getNegotiation());
        return hashMap;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
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
