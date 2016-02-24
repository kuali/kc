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
package org.kuali.kra.award.home.keywords;

import org.kuali.coeus.common.framework.keyword.AbstractScienceKeyword;
import org.kuali.coeus.common.framework.keyword.ScienceKeyword;
import org.kuali.coeus.common.framework.version.sequence.associate.SequenceAssociate;
import org.kuali.kra.award.home.Award;

/**
 * 
 * This class is BO to represent Award Science Keyword object
 */
public class AwardScienceKeyword extends AbstractScienceKeyword implements SequenceAssociate<Award> {


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
        setScienceKeywordCode(scienceKeyword.getCode());
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
    
    public Award getSequenceOwner() {
        return award;
    }

    public void setSequenceOwner(Award sequenceOwner) {
        award = sequenceOwner;
    }

    public Integer getSequenceNumber() {
        return getSequenceOwner() != null ? getSequenceOwner().getSequenceNumber() : null;
    }

    @Override
    public void resetPersistenceState() {
        awardScienceKeywordId = null;
     }

}
