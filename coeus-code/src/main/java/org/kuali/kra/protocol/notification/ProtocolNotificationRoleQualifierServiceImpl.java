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
package org.kuali.kra.protocol.notification;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.notification.impl.bo.NotificationModuleRoleQualifier;
import org.kuali.kra.kim.bo.KcKimAttributes;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.onlinereview.ProtocolOnlineReviewBase;

/**
 * Implements the common protocol NotificationRoleQualifierService.
 */
public class ProtocolNotificationRoleQualifierServiceImpl implements ProtocolNotificationRoleQualifierService {

    private ProtocolBase protocol;
    private ProtocolOnlineReviewBase protocolOnlineReview;
    
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

    public ProtocolBase getProtocol() {
        return protocol;
    }

    public void setProtocol(ProtocolBase protocol) {
        this.protocol = protocol;
    }

    public ProtocolOnlineReviewBase getProtocolOnlineReview() {
        return protocolOnlineReview;
    }

    public void setProtocolOnlineReview(ProtocolOnlineReviewBase protocolOnlineReview) {
        this.protocolOnlineReview = protocolOnlineReview;
    }

}
