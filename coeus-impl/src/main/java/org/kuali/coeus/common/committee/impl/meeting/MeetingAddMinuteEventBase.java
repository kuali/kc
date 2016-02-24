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
package org.kuali.coeus.common.committee.impl.meeting;

import org.kuali.coeus.common.committee.impl.document.CommitteeDocumentBase;
import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.rice.krad.document.Document;

/**
 * 
 * This class for the event to add committee schedule minute
 */
public abstract class MeetingAddMinuteEventBase  extends MeetingEventBase<MeetingAddMinuteRuleBase> {
    
    private static final String MSG = "Add meeting minute ";
    
    public MeetingAddMinuteEventBase(String errorPathPrefix, CommitteeDocumentBase document, MeetingHelperBase meetingHelper, ErrorType type) {
        super(MSG + getDocumentId(document), errorPathPrefix, document, meetingHelper, type);
    }
    
    public MeetingAddMinuteEventBase(String errorPathPrefix, Document document, MeetingHelperBase meetingHelper, ErrorType type) {
        this(errorPathPrefix, (CommitteeDocumentBase)document, meetingHelper, type);
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public KcBusinessRule getRule() {
        return getMeetingAddMinuteRuleInstanceHook();
    }

    protected abstract MeetingAddMinuteRuleBase getMeetingAddMinuteRuleInstanceHook();


}
