/*
 * Copyright 2006-2008 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.kuali.kra.irb.bo;

import java.util.LinkedHashMap;

import org.kuali.kra.bo.AbstractSpecialReview;
/**
 * 
 * This class represents ProtocolSpecialReview BO
 */
@SuppressWarnings("serial")
public class ProtocolSpecialReview extends AbstractSpecialReview<ProtocolSpecialReviewExemption> { 
	  
    private Long protocolSpecialReviewId; 
	private Protocol protocol; 

	public ProtocolSpecialReview() { 
        super();
    } 

	public Protocol getProtocol() {
		return protocol;
	}

	public void setProtocol(Protocol protocol) {
		this.protocol = protocol;
	}

	@SuppressWarnings("unchecked")
    @Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap map = super.toStringMapper();
		map.put("protocolSpecialReviewId", getProtocolSpecialReviewId());
		return map;
	}

    /**
     * Gets the protocolSpecialReviewId attribute. 
     * @return Returns the protocolSpecialReviewId.
     */
    public Long getProtocolSpecialReviewId() {
        return protocolSpecialReviewId;
    }

    /**
     * Sets the protocolSpecialReviewId attribute value.
     * @param protocolSpecialReviewId The protocolSpecialReviewId to set.
     */
    public void setProtocolSpecialReviewId(Long protocolSpecialReviewId) {
        this.protocolSpecialReviewId = protocolSpecialReviewId;
    }

    /**
     * It creates new ProtocolSpecialReviewExemption instance
     * @see org.kuali.kra.bo.AbstractSpecialReview#newSpecialReviewExemption(java.lang.String)
     */
    @Override
    public ProtocolSpecialReviewExemption newSpecialReviewExemption(String exemptionTypeCode) {
        ProtocolSpecialReviewExemption protocolSpecialReviewExemption = new ProtocolSpecialReviewExemption();
        protocolSpecialReviewExemption.setExemptionTypeCode(exemptionTypeCode);
        protocolSpecialReviewExemption.setProtocolSpecialReview(this);
        return protocolSpecialReviewExemption;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((protocol == null) ? 0 : protocol.hashCode());
        result = prime * result + ((protocolSpecialReviewId == null) ? 0 : protocolSpecialReviewId.hashCode());
        return result;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        ProtocolSpecialReview other = (ProtocolSpecialReview) obj;
        if (protocol == null) {
            if (other.protocol != null)
                return false;
        }
        else if (!protocol.equals(other.protocol))
            return false;
        if (protocolSpecialReviewId == null) {
            if (other.protocolSpecialReviewId != null)
                return false;
        }
        else if (!protocolSpecialReviewId.equals(other.protocolSpecialReviewId))
            return false;
        return true;
    }

    public void init(Protocol protocol) {
        this.protocolSpecialReviewId = null;
        this.protocol = protocol;
    }

    @Override
    public Long getSpecialReviewId() {
        return protocolSpecialReviewId;
    }
}