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
package org.kuali.kra.irb.specialreview;

import org.kuali.kra.common.specialreview.bo.SpecialReviewExemption;

/**
 * Defines a Special Review Exemption for a Protocol.
 */
public class ProtocolSpecialReviewExemption extends SpecialReviewExemption {

    private static final long serialVersionUID = 5397618472812176402L;

    private Long protocolSpecialReviewExemptionId;

    private Long protocolSpecialReviewId;

    private ProtocolSpecialReview protocolSpecialReview;

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

    public ProtocolSpecialReview getProtocolSpecialReview() {
        return protocolSpecialReview;
    }

    public void setProtocolSpecialReview(ProtocolSpecialReview protocolSpecialReview) {
        this.protocolSpecialReview = protocolSpecialReview;
    }
}
