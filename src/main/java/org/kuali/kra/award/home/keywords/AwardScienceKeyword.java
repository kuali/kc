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
package org.kuali.kra.award.home.keywords;

import org.kuali.kra.award.home.Award;
import org.kuali.kra.bo.AbstractScienceKeyword;
import org.kuali.kra.bo.ScienceKeyword;

/**
 * 
 * This class is BO to represent Award Science Keyword object
 */
public class AwardScienceKeyword extends AbstractScienceKeyword {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -8415861677886653309L;

    private Long awardScienceKeywordId;

    private Award award;

    /**
     * Empty constructor for AwardScienceKeyword.
     */
    public AwardScienceKeyword() {
    }

    /**
     * Constructs a AwardScienceKeyword.
     * @param awardId
     * @param scienceKeyword
     */
    public AwardScienceKeyword(Long awardId, ScienceKeyword scienceKeyword) {
        super();
        setScienceKeywordDescription(scienceKeyword.getDescription());
        setScienceKeywordCode(scienceKeyword.getScienceKeywordCode());
        setScienceKeyword(scienceKeyword);
        setVersionNumber(getVersionNumber() == null ? 1 : getVersionNumber());
    }

    /**
     * Gets the awardScienceKeywordId attribute. 
     * @return Returns the awardScienceKeywordId.
     */
    public Long getAwardScienceKeywordId() {
        return awardScienceKeywordId;
    }

    /**
     * Sets the awardScienceKeywordId attribute value.
     * @param awardScienceKeywordId The awardScienceKeywordId to set.
     */
    public void setAwardScienceKeywordId(Long awardScienceKeywordId) {
        this.awardScienceKeywordId = awardScienceKeywordId;
    }

    /**
     * Gets the award attribute. 
     * @return Returns the award.
     */
    public Award getAward() {
        return award;
    }

    /**
     * Sets the award attribute value.
     * @param award The award to set.
     */
    public void setAward(Award award) {
        this.award = award;
    }
}
