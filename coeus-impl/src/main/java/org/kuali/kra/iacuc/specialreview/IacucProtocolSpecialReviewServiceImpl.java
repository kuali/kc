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
package org.kuali.kra.iacuc.specialreview;

import org.kuali.coeus.common.framework.compliance.core.SpecialReview;
import org.kuali.coeus.common.framework.compliance.core.SpecialReviewApprovalType;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.specialreview.impl.ProtocolSpecialReviewServiceImplBase;
import org.kuali.rice.krad.data.DataObjectService;

import java.util.ArrayList;
import java.util.List;

public class IacucProtocolSpecialReviewServiceImpl extends ProtocolSpecialReviewServiceImplBase 
    implements IacucProtocolSpecialReviewService {


    private DataObjectService dataObjectService;

    @SuppressWarnings("rawtypes")
    @Override
    protected void setSpecialReviewApprovalTypeHook(SpecialReview specialReview)
    {
        specialReview.setApprovalTypeCode(SpecialReviewApprovalType.LINK_TO_IACUC);        
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    protected void setProtocolExemptStudiesCheckListItemHook(ProtocolBase protocol, SpecialReview specialReview) {
        List<String> exemptionTypeCodes = new ArrayList<String>();
        specialReview.setExemptionTypeCodes(exemptionTypeCodes);

    }

    public DevelopmentProposal getPropososalDevelopment(String proposalNumber) {
        DevelopmentProposal dp = null;
        if (proposalNumber != null) {
                dp = getDataObjectService().find(DevelopmentProposal.class, proposalNumber);
        }
        return dp;
    }

    public void setDataObjectService(DataObjectService dataObjectService) {
        this.dataObjectService = dataObjectService;
    }

    public DataObjectService getDataObjectService() {
        return dataObjectService;
    }
    
}
