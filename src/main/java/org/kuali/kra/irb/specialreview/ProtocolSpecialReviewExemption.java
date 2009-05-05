/*
 * Copyright 2006-2008 The Kuali Foundation
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

import java.util.LinkedHashMap;

import org.kuali.kra.bo.AbstractSpecialReviewExemption;

/**
 * This class represents ProtocolSpecialReviewExemption
 */
@SuppressWarnings("serial")
public class ProtocolSpecialReviewExemption extends AbstractSpecialReviewExemption {
   
    private Long protocolSpecialReviewExemptionId;
    private ProtocolSpecialReview protocolSpecialReview;
    
    /**
     * Gets the protocolSpecialReviewExemptionId attribute. 
     * @return Returns the protocolSpecialReviewExemptionId.
     */
    public Long getProtocolSpecialReviewExemptionId() {
        return protocolSpecialReviewExemptionId;
    }
    
    /**
     * Sets the protocolSpecialReviewExemptionId attribute value.
     * @param protocolSpecialReviewExemptionId The protocolSpecialReviewExemptionId to set.
     */
    public void setProtocolSpecialReviewExemptionId(Long protocolSpecialReviewExemptionId) {
        this.protocolSpecialReviewExemptionId = protocolSpecialReviewExemptionId;
    }
    
    /**
     * Gets the protocolSpecialReview attribute. 
     * @return Returns the protocolSpecialReview.
     */
    public ProtocolSpecialReview getProtocolSpecialReview() {
        return protocolSpecialReview;
    }
    
    /**
     * Sets the protocolSpecialReview attribute value.
     * @param protocolSpecialReview The protocolSpecialReview to set.
     */
    public void setProtocolSpecialReview(ProtocolSpecialReview protocolSpecialReview) {
        this.protocolSpecialReview = protocolSpecialReview;
    }
    
    /**
     * @see org.kuali.kra.bo.AbstractSpecialReviewExemption#toStringMapper()
     */
    @SuppressWarnings("unchecked")
    @Override
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap hashMap = super.toStringMapper();
        hashMap.put("protocolSpecialReviewExemptionId", getProtocolSpecialReviewExemptionId());
        return hashMap;
    }
}
