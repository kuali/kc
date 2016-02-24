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
package org.kuali.kra.irb.specialreview;

import org.kuali.kra.irb.Protocol;
import org.kuali.kra.protocol.specialreview.ProtocolSpecialReviewBase;
import org.kuali.kra.protocol.specialreview.ProtocolSpecialReviewExemption;

/**
 * Defines a Special Review for a Protocol.
 */
public class ProtocolSpecialReview extends ProtocolSpecialReviewBase  {

    private static final long serialVersionUID = -9010537404528653558L;

    @Override
    public ProtocolSpecialReviewExemption createSpecialReviewExemption(String exemptionTypeCode) {
        org.kuali.kra.irb.specialreview.ProtocolSpecialReviewExemption protocolSpecialReviewExemption = new org.kuali.kra.irb.specialreview.ProtocolSpecialReviewExemption();
        protocolSpecialReviewExemption.setExemptionTypeCode(exemptionTypeCode);
        protocolSpecialReviewExemption.setProtocolSpecialReview(this);
        return protocolSpecialReviewExemption;
    }

    public Protocol getSequenceOwner() {
        return (Protocol) super.getSequenceOwner();
    }

    public void resetPersistenceState() {
        super.resetPersistenceState();
        for (ProtocolSpecialReviewExemption exemption : getSpecialReviewExemptions()) {
            exemption.setProtocolSpecialReviewExemptionId(null);
            exemption.setProtocolSpecialReviewId(null);
        }
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((getProtocolId() == null) ? 0 : getProtocolId().hashCode());
        result = prime * result + ((getProtocolSpecialReviewId() == null) ? 0 : getProtocolSpecialReviewId().hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ProtocolSpecialReview other = (ProtocolSpecialReview) obj;
        if (getProtocolId() == null) {
            if (other.getProtocolId() != null) {
                return false;
            }
        } else if (!getProtocolId().equals(other.getProtocolId())) {
            return false;
        }
        if (getProtocolSpecialReviewId() == null) {
            if (other.getProtocolSpecialReviewId() != null) {
                return false;
            }
        } else if (!getProtocolSpecialReviewId().equals(other.getProtocolSpecialReviewId())) {
            return false;
        }
        return true;
    }
}
