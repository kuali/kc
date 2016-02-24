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
 * Provides necessary accessor for checking the deletion of an Other Action.
 */
public class MeetingDeleteOtherEvent extends MeetingEventBase<MeetingDeleteOtherRule> {
    
    private static final String MSG = "Delete meeting present other  ";
    
    private int otherNumber;
    
    /**
     * Constructs a MeetingDeleteOtherEvent.
     * 
     * @param errorPathPrefix Prefix of document ID of where to put the error message
     * @param document The document being modified
     * @param meetingHelper Helper class for meetings
     * @param type The type of error
     * @param otherNumber The index of the Other Action in the Other Action list.
     */
    public MeetingDeleteOtherEvent(String errorPathPrefix, CommitteeDocumentBase document, MeetingHelperBase meetingHelper, ErrorType type, int otherNumber) {
        super(MSG + getDocumentId(document), errorPathPrefix, document, meetingHelper, type);
        this.otherNumber = otherNumber;
    }
    
    /**
     * Constructs a MeetingDeleteOtherEvent.
     * 
     * @param errorPathPrefix Prefix of document ID of where to put the error message
     * @param document The document being modified
     * @param meetingHelper Helper class for meetings
     * @param type The type of error
     * @param otherNumber The index of the Other Action in the Other Action list.
     */
    public MeetingDeleteOtherEvent(String errorPathPrefix, Document document, MeetingHelperBase meetingHelper, ErrorType type, int otherNumber) {
        this(errorPathPrefix, (CommitteeDocumentBase) document, meetingHelper, type, otherNumber);
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public KcBusinessRule getRule() {
        return new MeetingDeleteOtherRule();
    }
    
    public int getOtherNumber() {
        return otherNumber;
    }

}
