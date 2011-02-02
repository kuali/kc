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
package org.kuali.kra.award.lookup.keyvalue;

import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.award.AwardForm;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.fundingproposal.FundingProposalMergeType;
import org.kuali.rice.core.util.KeyLabelPair;
import org.kuali.rice.kns.lookup.keyvalues.KeyValuesBase;
import org.kuali.rice.kns.lookup.keyvalues.PersistableBusinessObjectValuesFinder;
import org.kuali.rice.kns.util.GlobalVariables;

public class FundingProposalMergeTypeValuesFinder extends PersistableBusinessObjectValuesFinder {

    public List<KeyLabelPair> getKeyValues() {
        List<KeyLabelPair> values = new ArrayList<KeyLabelPair>();
        if (isNewAward()) {
            values.add(new KeyLabelPair(FundingProposalMergeType.NEWAWARD.getKey(), FundingProposalMergeType.NEWAWARD.getDesc()));
        } else {
            for (FundingProposalMergeType type : FundingProposalMergeType.values()) {
                if (type != FundingProposalMergeType.NEWAWARD) {
                    values.add(new KeyLabelPair(type.getKey(), type.getDesc()));
                }
            }
        }
        return values;
    }
    
    protected boolean isNewAward() {
        Award award = ((AwardForm)GlobalVariables.getKualiForm()).getAwardDocument().getAward();
        return award.isNew() && award.getSequenceNumber() <= 1 && award.getFundingProposals().isEmpty();
    }

}
