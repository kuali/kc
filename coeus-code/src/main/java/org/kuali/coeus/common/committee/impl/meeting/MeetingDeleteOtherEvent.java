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
