/*
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
