/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.protocol.specialreview;

import org.kuali.coeus.common.framework.version.sequence.associate.SequenceAssociate;
import org.kuali.coeus.common.framework.compliance.core.SpecialReview;
import org.kuali.kra.protocol.ProtocolBase;

/**
 * Defines a Special Review for a ProtocolBase.
 */
public abstract class ProtocolSpecialReviewBase extends SpecialReview<ProtocolSpecialReviewExemption> implements SequenceAssociate<ProtocolBase> {

    private static final long serialVersionUID = -9010537404528653558L;

    private Long protocolSpecialReviewId;

    private Long protocolId;

    private ProtocolBase sequenceOwner;

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

    public ProtocolBase getSequenceOwner() {
        return sequenceOwner;
    }

    public void setSequenceOwner(ProtocolBase sequenceOwner) {
        this.sequenceOwner = sequenceOwner;
    }

    public Integer getSequenceNumber() {
        return sequenceOwner != null ? sequenceOwner.getSequenceNumber() : null;
    }

    @Override
    public void resetPersistenceState() {
        protocolSpecialReviewId = null;
    }

    public abstract ProtocolSpecialReviewExemption createSpecialReviewExemption(String exemptionTypeCode);
    
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
        ProtocolSpecialReviewBase other = (ProtocolSpecialReviewBase) obj;
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
