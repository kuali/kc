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
package org.kuali.kra.iacuc.lookup;

import org.kuali.kra.iacuc.actions.submit.IacucProtocolReviewerType;
import org.kuali.rice.kns.lookup.KualiLookupableHelperServiceImpl;
import org.kuali.rice.krad.bo.BusinessObject;

public class IacucProtocolReviewerTypeLookupableHelperServiceImpl extends KualiLookupableHelperServiceImpl {
    
    private static final long serialVersionUID = -7379856633866034252L;

    protected boolean allowsMaintenanceDeleteAction(BusinessObject businessObject) {

        boolean allowsDelete = true;

        if (businessObject instanceof IacucProtocolReviewerType) {
            IacucProtocolReviewerType reviewerType = (IacucProtocolReviewerType)businessObject; 
            if (IacucProtocolReviewerType.PRIMARY.equals(reviewerType.getReviewerTypeCode()) ||
                IacucProtocolReviewerType.COMMITTEE.equals(reviewerType.getReviewerTypeCode())) {
                allowsDelete = false;
            }
        }
        return allowsDelete;
    }
}
