/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.kra.irb.actions.expeditedapprove;

import org.apache.commons.lang3.StringUtils;
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
        if (expeditedBean.isAssignToAgenda() && StringUtils.isBlank(expeditedBean.getScheduleId())) {
            isValid = false;
            reportError(SCHEDULE_FIELD, KeyConstants.ERROR_PROTOCOL_SCHEDULE_NOT_SELECTED);
        }
        return isValid;
    }
}
