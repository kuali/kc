/*
 * Copyright 2005-2013 The Kuali Foundation
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.SpecialReviewApprovalType;
import org.kuali.kra.common.specialreview.bo.SpecialReview;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolFinderDao;
import org.kuali.kra.irb.actions.submit.ProtocolExemptStudiesCheckListItem;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.bo.ProposalState;
import org.kuali.kra.proposaldevelopment.specialreview.ProposalSpecialReview;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.specialreview.impl.ProtocolSpecialReviewServiceImplBase;
import org.kuali.rice.krad.service.BusinessObjectService;

public class ProtocolSpecialReviewServiceImpl extends ProtocolSpecialReviewServiceImplBase implements ProtocolSpecialReviewService {

    @SuppressWarnings("rawtypes")
    @Override
    protected void setSpecialReviewApprovalTypeHook(SpecialReview specialReview)
    {
        specialReview.setApprovalTypeCode(SpecialReviewApprovalType.LINK_TO_IRB);        
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    protected void setProtocolExemptStudiesCheckListItemHook(ProtocolBase protocol, SpecialReview specialReview) {
        List<String> exemptionTypeCodes = new ArrayList<String>();
        for (ProtocolExemptStudiesCheckListItem checkListItem : ((Protocol)protocol).getProtocolSubmission().getExemptStudiesCheckList()) {
            exemptionTypeCodes.add(checkListItem.getExemptStudiesCheckListCode());
        }
        specialReview.setExemptionTypeCodes(exemptionTypeCodes);

    }
}
