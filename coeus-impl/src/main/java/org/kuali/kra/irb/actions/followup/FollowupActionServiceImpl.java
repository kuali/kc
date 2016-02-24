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
package org.kuali.kra.irb.actions.followup;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.submit.ValidProtocolActionAction;
import org.kuali.kra.protocol.actions.followup.FollowupActionServiceImplBase;


public class FollowupActionServiceImpl extends FollowupActionServiceImplBase<ValidProtocolActionAction> implements FollowupActionService {
    
    private static final Log LOG = LogFactory.getLog(FollowupActionServiceImpl.class);

    @Override
    protected Class<ValidProtocolActionAction> getValidProtocolActionActionClassHook() {
        return ValidProtocolActionAction.class;
    }

    @Override
    protected String getProtocolActionTypeCodeForRecordCommitteeDecisionHook() {
        return ProtocolActionType.RECORD_COMMITTEE_DECISION;
    }

    @Override
    protected Log getLogHook() {
        return FollowupActionServiceImpl.LOG;
    }
}
