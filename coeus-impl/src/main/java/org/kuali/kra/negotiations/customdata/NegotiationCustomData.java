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
package org.kuali.kra.negotiations.customdata;

import org.kuali.coeus.common.framework.custom.DocumentCustomData;
import org.kuali.coeus.common.framework.custom.attr.CustomAttribute;
import org.kuali.coeus.common.framework.version.sequence.owner.SequenceOwner;
import org.kuali.coeus.sys.api.model.IdentifiableNumeric;
import org.kuali.kra.negotiations.NegotiationAssociate;
import org.kuali.kra.negotiations.bo.Negotiation;

/**
 * This is Business Object class for IP custom data BO.
 */
public class NegotiationCustomData extends NegotiationAssociate implements DocumentCustomData, IdentifiableNumeric {

    private static final long serialVersionUID = 1L;

    private Long negotiationCustomDataId;

    private Long customAttributeId;

    private String value;

    private CustomAttribute customAttribute;


    public NegotiationCustomData() {
    }


    public Long getNegotiationCustomDataId() {
        return negotiationCustomDataId;
    }


    public void setProposalCustomDataId(Long proposalCustomDataId) {
        this.negotiationCustomDataId = proposalCustomDataId;
    }


    public Long getCustomAttributeId() {
        return customAttributeId;
    }


    public void setCustomAttributeId(Long customAttributeId) {
        this.customAttributeId = customAttributeId;
    }


    public String getValue() {
        return value;
    }


    public void setValue(String value) {
        this.value = value;
    }


    public CustomAttribute getCustomAttribute() {
        return customAttribute;
    }


    public void setCustomAttribute(CustomAttribute customAttribute) {
        this.customAttribute = customAttribute;
    }

    public SequenceOwner getSequenceOwner() {
        return (SequenceOwner) getNegotiation();
    }

    public void setSequenceOwner(SequenceOwner newlyVersionedOwner) {
        setNegotiation((Negotiation) newlyVersionedOwner);
    }

    public void resetPersistenceState() {
        this.negotiationCustomDataId = null;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((customAttribute == null) ? 0 : customAttribute.hashCode());
        result = prime * result + ((customAttributeId == null) ? 0 : customAttributeId.hashCode());
        result = prime * result + ((negotiationCustomDataId == null) ? 0 : negotiationCustomDataId.hashCode());
        result = prime * result + ((value == null) ? 0 : value.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        NegotiationCustomData other = (NegotiationCustomData) obj;
        if (customAttribute == null) {
            if (other.customAttribute != null) return false;
        } else if (!customAttribute.equals(other.customAttribute)) return false;
        if (customAttributeId == null) {
            if (other.customAttributeId != null) return false;
        } else if (!customAttributeId.equals(other.customAttributeId)) return false;
        if (negotiationCustomDataId == null) {
            if (other.negotiationCustomDataId != null) return false;
        } else if (!negotiationCustomDataId.equals(other.negotiationCustomDataId)) return false;
        if (value == null) {
            if (other.value != null) return false;
        } else if (!value.equals(other.value)) return false;
        return true;
    }
    
	@Override
	public Long getId() {
		return customAttributeId;
	}

}
