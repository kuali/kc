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
package org.kuali.kra.irb.onlinereview;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

public class ProtocolOnlineReviewDeterminationRecommendation extends KraPersistableBusinessObjectBase {

    private static final long serialVersionUID = 521840115701578958L;

    private Long protocolOnlineReviewDeterminationRecommendationCode;

    private String description;

    /**
     * Gets the protocolReviewDeterminationRecommendationCode attribute. 
     * @return Returns the protocolReviewDeterminationRecommendationCode.
     */
    public Long getProtocolOnlineReviewDeterminationRecommendationCode() {
        return protocolOnlineReviewDeterminationRecommendationCode;
    }

    /**
     * Sets the protocolReviewDeterminationRecommendationCode attribute value.
     * @param protocolOnlineReviewDeterminationRecommendationCode The protocolReviewDeterminationRecommendationCode to set.
     */
    public void setProtocolOnlineReviewDeterminationRecommendationCode(Long protocolOnlineReviewDeterminationRecommendationCode) {
        this.protocolOnlineReviewDeterminationRecommendationCode = protocolOnlineReviewDeterminationRecommendationCode;
    }

    /**
     * Gets the description attribute. 
     * @return Returns the description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description attribute value.
     * @param description The description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
