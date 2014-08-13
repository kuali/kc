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
package org.kuali.kra.irb.actions.expeditedapprove;

import org.drools.core.util.StringUtils;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.irb.actions.approve.ProtocolApproveEvent;
import org.kuali.kra.irb.actions.approve.ProtocolApproveRule;

/**
 * Encapsulates the rules for approving a Protocol.
 */
public class ProtocolExpeditedApproveRule extends ProtocolApproveRule {
    
    private static final String SCHEDULE_FIELD = "scheduleId";

    @Override
    public boolean processRules(ProtocolApproveEvent event) {
        boolean isValid = super.processRules(event);
        ProtocolExpeditedApproveBean expeditedBean = (ProtocolExpeditedApproveBean)event.getProtocolApproveBean();
        if (expeditedBean.isAssignToAgenda() && StringUtils.isEmpty(expeditedBean.getScheduleId())) {
            isValid = false;
            reportError(SCHEDULE_FIELD, KeyConstants.ERROR_PROTOCOL_SCHEDULE_NOT_SELECTED);
        }
        return isValid;
    }
}
