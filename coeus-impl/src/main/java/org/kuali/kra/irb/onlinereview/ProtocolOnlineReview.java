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
package org.kuali.kra.irb.onlinereview;

import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.protocol.onlinereview.ProtocolOnlineReviewBase;

import java.util.ArrayList;
import java.util.List;

/**
 * This class encapsulates the notion of a protocol review. Essentially 
 * a join between protocol, submission, and a reviewer.  The ProtocolReview
 * is created by the IRB Admin as request.
 */
public class ProtocolOnlineReview extends ProtocolOnlineReviewBase {

    private static final long serialVersionUID = 531397319695764847L;

    public String getNamespace() {
        return "KC-PROTOCOL";
    }

    public List<String> getRoleNames() {
        List<String> roleNames = new ArrayList<String>();
        roleNames.add(RoleConstants.IRB_REVIEWER);
        return roleNames;
    }

    @Override
    protected String getProtocolOLRRemovedCancelledStatusCodeHook() {
        return ProtocolOnlineReviewStatus.REMOVED_CANCELLED_STATUS_CD;
    }

    @Override
    protected String getProtocolOLRFinalStatusCodeHook() {
        return ProtocolOnlineReviewStatus.FINAL_STATUS_CD;
    }
}
