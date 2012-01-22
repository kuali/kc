/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.irb.specialreview;

import org.kuali.kra.SequenceAssociate;
import org.kuali.kra.common.specialreview.bo.SpecialReview;
import org.kuali.kra.irb.Protocol;

/**
 * Defines a Special Review for a Protocol.
 */
public class ProtocolSpecialReview extends SpecialReview<ProtocolSpecialReviewExemption> implements SequenceAssociate<Protocol> {

    private static final long serialVersionUID = -9010537404528653558L;

    private Long protocolSpecialReviewId;

    private Long protocolId;

    private Protocol sequenceOwner;

    public Long getProtocolSpecialReviewId() {
        return protocolSpecialReviewId;
    }

    public void setProtocolSpecialReviewId(Long protocolSpecialReviewId) {
        this.protocolSpecialReviewId = protocolSpecialReviewId;
    }

    public Long getProtocolId() {
        return protocolId;
    }

    public void setProtocolId(Long protocolId) {
        this.protocolId = protocolId;
    }

    public Protocol getSequenceOwner() {
        return sequenceOwner;
    }

    public void setSequenceOwner(Protocol sequenceOwner) {
        this.sequenceOwner = sequenceOwner;
    }

    public Integer getSequenceNumber() {
        return sequenceOwner != null ? sequenceOwner.getSequenceNumber() : null;
    }

    /**
     * {@inheritDoc}
     * @see org.kuali.kra.Sequenceable#resetPersistenceState()
     */
    public void resetPersistenceState() {
        protocolSpecialReviewId = null;
        for (ProtocolSpecialReviewExemption exemption : getSpecialReviewExemptions()) {
            exemption.setProtocolSpecialReviewExemptionId(null);
            exemption.setProtocolSpecialReviewId(null);
        }
    }

    @Override
    public ProtocolSpecialReviewExemption createSpecialReviewExemption(String exemptionTypeCode) {
        ProtocolSpecialReviewExemption protocolSpecialReviewExemption = new ProtocolSpecialReviewExemption();
        protocolSpecialReviewExemption.setExemptionTypeCode(exemptionTypeCode);
        protocolSpecialReviewExemption.setProtocolSpecialReview(this);
        return protocolSpecialReviewExemption;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((protocolId == null) ? 0 : protocolId.hashCode());
        result = prime * result + ((protocolSpecialReviewId == null) ? 0 : protocolSpecialReviewId.hashCode());
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
        if (protocolId == null) {
            if (other.protocolId != null) {
                return false;
            }
        } else if (!protocolId.equals(other.protocolId)) {
            return false;
        }
        if (protocolSpecialReviewId == null) {
            if (other.protocolSpecialReviewId != null) {
                return false;
            }
        } else if (!protocolSpecialReviewId.equals(other.protocolSpecialReviewId)) {
            return false;
        }
        return true;
    }
}
