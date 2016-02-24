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

import org.kuali.kra.protocol.onlinereview.ProtocolOnlineReviewBase;

import java.sql.Date;

/**
 * This class encapsulates the notion of a protocol review. Essentially 
 * a join between protocol, submission, and a reviewer.  The ProtocolReview
 * is created by the IRB Admin as request.
 */
public class IacucProtocolOnlineReview extends ProtocolOnlineReviewBase {
    
    

    private static final long serialVersionUID = -3526853926706200095L;

    private String determinationReviewTypeCode;

    private Date determinationReviewDateDue;

    
    public String getNamespace() {
        return "KC-IACUC";
    }

    public String getDeterminationReviewTypeCode() {
        return determinationReviewTypeCode;
    }

    public void setDeterminationReviewTypeCode(String determinationReviewTypeCode) {
        this.determinationReviewTypeCode = determinationReviewTypeCode;
    }

    public Date getDeterminationReviewDateDue() {
        return determinationReviewDateDue;
    }

    public void setDeterminationReviewDateDue(Date determinationReviewDateDue) {
        this.determinationReviewDateDue = determinationReviewDateDue;
    }

    @Override
    protected String getProtocolOLRRemovedCancelledStatusCodeHook() {
        return IacucProtocolOnlineReviewStatus.REMOVED_CANCELLED_STATUS_CD;
    }

    @Override
    protected String getProtocolOLRFinalStatusCodeHook() {
        return IacucProtocolOnlineReviewStatus.FINAL_STATUS_CD;
    }


}
