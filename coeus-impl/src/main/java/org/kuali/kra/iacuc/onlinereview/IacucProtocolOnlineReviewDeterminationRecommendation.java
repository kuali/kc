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
package org.kuali.kra.iacuc.onlinereview;

import org.kuali.kra.iacuc.actions.IacucProtocolActionType;
import org.kuali.kra.protocol.actions.submit.ProtocolReviewTypeBase;
import org.kuali.kra.protocol.onlinereview.ProtocolOnlineReviewDeterminationRecommendationBase;

public class IacucProtocolOnlineReviewDeterminationRecommendation extends ProtocolOnlineReviewDeterminationRecommendationBase {


    private static final long serialVersionUID = -1768290517796704487L;

    private String iacucProtocolReviewTypeCode;
    
    private ProtocolReviewTypeBase iacucProtocolReviewType;

    private IacucProtocolActionType iacucProtocolActionType;
    
    private String iacucProtocolActionTypeCode;
    
    public String getIacucProtocolReviewTypeCode() {
        return iacucProtocolReviewTypeCode;
    }

    public void setIacucProtocolReviewTypeCode(String iacucProtocolReviewTypeCode) {
        this.iacucProtocolReviewTypeCode = iacucProtocolReviewTypeCode;
    }

    public ProtocolReviewTypeBase getIacucProtocolReviewType() {
        if (iacucProtocolReviewType == null || !iacucProtocolReviewType.getReviewTypeCode().equals(iacucProtocolReviewTypeCode)) {
            refreshReferenceObject("iacucProtocolReviewType");
        }
        return iacucProtocolReviewType;
    }

    public void setIacucProtocolReviewType(ProtocolReviewTypeBase iacucProtocolReviewType) {
        this.iacucProtocolReviewType = iacucProtocolReviewType;
    }

    public IacucProtocolActionType getIacucProtocolActionType() {
        if (iacucProtocolActionType == null || !iacucProtocolActionType.getProtocolActionTypeCode().equals(iacucProtocolActionTypeCode)) {
            refreshReferenceObject("iacucProtocolActionType");
        }
        return iacucProtocolActionType;
    }

    public void setIacucProtocolActionType(IacucProtocolActionType iacucProtocolActionType) {
        this.iacucProtocolActionType = iacucProtocolActionType;
    }

    public String getIacucProtocolActionTypeCode() {
        return iacucProtocolActionTypeCode;
    }

    public void setIacucProtocolActionTypeCode(String iacucProtocolActionTypeCode) {
        this.iacucProtocolActionTypeCode = iacucProtocolActionTypeCode;
    }

}
