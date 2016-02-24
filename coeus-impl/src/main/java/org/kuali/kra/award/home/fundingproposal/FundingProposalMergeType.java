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
package org.kuali.kra.award.home.fundingproposal;

import org.apache.commons.lang3.StringUtils;

/**
 * Enum for determining the ways a proposal can be merged or added to an award.
 */
public enum FundingProposalMergeType {

    NOCHANGE("NC", "No Change"), MERGE("M", "Merge"), REPLACE("R", "Replace"), NEWAWARD("N", "Initial Funding");
    private String key;
    private String desc;
    private FundingProposalMergeType(String key, String desc) {
        this.key = key;
        this.desc = desc;
    }
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public static FundingProposalMergeType getFundingProposalMergeType(String key) {
        for (FundingProposalMergeType type : FundingProposalMergeType.values()) {
            if (StringUtils.equals(type.getKey(), key)) {
                return type;
            }
        }
        return null;
    }
}
