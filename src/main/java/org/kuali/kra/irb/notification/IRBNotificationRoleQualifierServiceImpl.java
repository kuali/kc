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
package org.kuali.kra.irb.notification;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.common.notification.bo.NotificationModuleRoleQualifier;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.onlinereview.ProtocolOnlineReview;
import org.kuali.kra.kim.bo.KcKimAttributes;

/**
 * Implements the IRBNotificationRoleQualifierService.
 */
public class IRBNotificationRoleQualifierServiceImpl implements IRBNotificationRoleQualifierService {

    private Protocol protocol;
    private ProtocolOnlineReview protocolOnlineReview;
    
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.common.notification.service.KcNotificationRoleQualifierService#getRoleQualifierValue(
     *      org.kuali.kra.common.notification.bo.NotificationModuleRoleQualifier)
     */
    @Override
    public String getRoleQualifierValue(NotificationModuleRoleQualifier qualifier) {
        String roleQualifierValue = null;
        
        String qName = qualifier.getQualifier();
        
        if (StringUtils.equalsIgnoreCase(qName, KcKimAttributes.UNIT_NUMBER) 
                || StringUtils.equalsIgnoreCase(qName, "protocolLeadUnitNumber")) {
            roleQualifierValue = getProtocol().getLeadUnitNumber();
        } else if (StringUtils.equalsIgnoreCase(qName, KcKimAttributes.PROTOCOL)) {
            roleQualifierValue = getProtocol().getProtocolNumber();
        } else if (StringUtils.equalsIgnoreCase(qName, KcKimAttributes.SUBUNITS)) {
            roleQualifierValue = "Y";
        } else if (StringUtils.equalsIgnoreCase(qName, "submissionId")) {
            if (getProtocol().getProtocolSubmission() != null) {
                roleQualifierValue = getProtocol().getProtocolSubmission().getSubmissionId().toString();
            }
        } else if (StringUtils.equals(qName, "protocolOnlineReviewId")) {
            if (protocolOnlineReview != null) {
                roleQualifierValue = protocolOnlineReview.getProtocolOnlineReviewId().toString();
            }
        }
        
        return roleQualifierValue;
    }

    public Protocol getProtocol() {
        return protocol;
    }

    public void setProtocol(Protocol protocol) {
        this.protocol = protocol;
    }

    public ProtocolOnlineReview getProtocolOnlineReview() {
        return protocolOnlineReview;
    }

    public void setProtocolOnlineReview(ProtocolOnlineReview protocolOnlineReview) {
        this.protocolOnlineReview = protocolOnlineReview;
    }

}