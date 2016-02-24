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
package org.kuali.kra.protocol.specialreview;

import org.kuali.coeus.common.framework.compliance.exemption.SpecialReviewExemption;

/**
 * Defines a Special Review Exemption for a ProtocolBase.
 */
public class ProtocolSpecialReviewExemption extends SpecialReviewExemption {

    private static final long serialVersionUID = 5397618472812176402L;

    private Long protocolSpecialReviewExemptionId;

    private Long protocolSpecialReviewId;

    private ProtocolSpecialReviewBase protocolSpecialReview;

    public Long getProtocolSpecialReviewExemptionId() {
        return protocolSpecialReviewExemptionId;
    }

    public void setProtocolSpecialReviewExemptionId(Long protocolSpecialReviewExemptionId) {
        this.protocolSpecialReviewExemptionId = protocolSpecialReviewExemptionId;
    }

    public Long getProtocolSpecialReviewId() {
        return protocolSpecialReviewId;
    }

    public void setProtocolSpecialReviewId(Long protocolSpecialReviewId) {
        this.protocolSpecialReviewId = protocolSpecialReviewId;
    }

    public ProtocolSpecialReviewBase getProtocolSpecialReview() {
        return protocolSpecialReview;
    }

    public void setProtocolSpecialReview(ProtocolSpecialReviewBase protocolSpecialReview) {
        this.protocolSpecialReview = protocolSpecialReview;
    }
}
