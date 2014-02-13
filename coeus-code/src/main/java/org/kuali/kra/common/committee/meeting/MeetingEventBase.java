/*
 * Copyright 2005-2013 The Kuali Foundation
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
package org.kuali.kra.common.committee.meeting;

import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.coeus.sys.framework.rule.KcDocumentEventBaseExtension;
import org.kuali.rice.krad.document.Document;

/**
 * 
 * This class is the event base class for all meeting event.
 */
@SuppressWarnings("unchecked")
public abstract class MeetingEventBase<Z extends KcBusinessRule> extends KcDocumentEventBaseExtension {
    
    /**
     * Enum helps identify type of error to respond.
     */
    public enum ErrorType {HARDERROR, SOFTERROR};
    
    
    private MeetingHelperBase meetingHelper;
    
    private ErrorType type;
    
    public MeetingEventBase(String description, String errorPathPrefix, Document document,  MeetingHelperBase meetingHelper, ErrorType type) {        
        super(description, errorPathPrefix, document);
        this.meetingHelper = meetingHelper;
        this.type = type;
    }
    
    
    /**
     * This method should return instance of MeetingHelperBase.
     * @return
     */
    public MeetingHelperBase getMeetingHelper(){
        return this.meetingHelper;
    }
    
    /**
     * This method should return Error type.
     * @return
     */
    public ErrorType getType() {
        return this.type;
    }


}
