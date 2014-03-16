/*
 * Copyright 2005-2014 The Kuali Foundation
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
