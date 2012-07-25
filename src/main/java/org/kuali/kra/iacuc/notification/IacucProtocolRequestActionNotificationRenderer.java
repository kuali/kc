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
package org.kuali.kra.iacuc.notification;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.drools.core.util.StringUtils;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.actions.IacucProtocolAction;
import org.kuali.kra.iacuc.actions.IacucProtocolActionType;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolReviewType;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmission;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmissionQualifierType;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmissionType;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.protocol.notification.ProtocolNotificationRenderer;
import org.kuali.kra.protocol.notification.ProtocolReplacementParameters;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.service.KRADServiceLocator;

/**
 * Renders fields for the IRB and IACUC notifications.
 */
public class IacucProtocolRequestActionNotificationRenderer extends IacucProtocolNotificationRenderer {

    private static final long serialVersionUID = 1058103556564286014L;

    private String reason;

    private static final String NO_REASON_GIVEN="message.none.given";
    
    public IacucProtocolRequestActionNotificationRenderer(IacucProtocol protocol, String reason) {
        super(protocol);
        this.reason = reason;
    }

    public Map<String, String> getDefaultReplacementParameters() {
        Map<String, String> params = super.getDefaultReplacementParameters();
        params.put(ProtocolReplacementParameters.REASON, !StringUtils.isEmpty(reason) ? reason : KRADServiceLocator.getKualiConfigurationService().getPropertyValueAsString(NO_REASON_GIVEN));
        return params;
    }
}
