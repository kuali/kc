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
package org.kuali.kra.irb.specialreview;

import org.kuali.coeus.common.framework.compliance.core.SpecialReview;
import org.kuali.coeus.common.framework.compliance.core.SpecialReviewApprovalType;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.submit.ProtocolExemptStudiesCheckListItem;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.specialreview.impl.ProtocolSpecialReviewServiceImplBase;

import java.util.ArrayList;
import java.util.List;

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
