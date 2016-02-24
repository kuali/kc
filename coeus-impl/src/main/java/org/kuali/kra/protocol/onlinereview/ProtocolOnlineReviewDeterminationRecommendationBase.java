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
package org.kuali.kra.protocol.onlinereview;

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

public abstract class ProtocolOnlineReviewDeterminationRecommendationBase extends KcPersistableBusinessObjectBase {

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
