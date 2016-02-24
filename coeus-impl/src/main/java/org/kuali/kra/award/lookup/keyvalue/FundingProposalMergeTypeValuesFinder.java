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
package org.kuali.kra.award.lookup.keyvalue;

import org.kuali.coeus.sys.framework.keyvalue.FormViewAwareUifKeyValuesFinderBase;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.fundingproposal.FundingProposalMergeType;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;

import java.util.ArrayList;
import java.util.List;

public class FundingProposalMergeTypeValuesFinder extends FormViewAwareUifKeyValuesFinderBase {

    @Override
    public List<KeyValue> getKeyValues() {
        List<KeyValue> values = new ArrayList<KeyValue>();
        if (isNewAward()) {
            values.add(new ConcreteKeyValue(FundingProposalMergeType.NEWAWARD.getKey(), FundingProposalMergeType.NEWAWARD.getDesc()));
        } else {
            for (FundingProposalMergeType type : FundingProposalMergeType.values()) {
                if (type != FundingProposalMergeType.NEWAWARD) {
                    values.add(new ConcreteKeyValue(type.getKey(), type.getDesc()));
                }
            }
        }
        return values;
    }
    
    protected boolean isNewAward() {
        Award award = ((AwardDocument) getDocument()).getAward();
        return award.isNew() && award.getSequenceNumber() <= 1 && award.getFundingProposals().isEmpty();
    }

}
