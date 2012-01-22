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
package org.kuali.kra.committee.rule.event;

import java.util.List;

import org.kuali.kra.committee.bo.CommitteeMembership;
import org.kuali.kra.rule.BusinessRuleInterface;
import org.kuali.kra.rule.event.KraDocumentEventBaseExtension;
import org.kuali.rice.krad.document.Document;

/**
 * 
 * This class is using the new rule framework.  The old framework is using CommitteeMembershipRuleEventBase
 */
public abstract class CommitteeMemberEventBase <Z extends BusinessRuleInterface> extends KraDocumentEventBaseExtension {
    
    /**
     * Enum helps identify type of error to respond.
     */
    public enum ErrorType {HARDERROR, SOFTERROR};
    
    private List<CommitteeMembership> committeeMemberships;
        
    private ErrorType type;
    
    /**
     * 
     * Constructs a CommitteeMemberEventBase.java.
     * @param description
     * @param errorPathPrefix
     * @param document
     * @param committeeMemberships
     * @param type
     */
    public CommitteeMemberEventBase(String description, String errorPathPrefix, Document document, List<CommitteeMembership> committeeMemberships, ErrorType type) {        
        super(description, errorPathPrefix, document);
        this.committeeMemberships = committeeMemberships;
        this.type = type;
    }
        
    public ErrorType getType() {
        return this.type;
    }

    public List<CommitteeMembership> getCommitteeMemberships() {
        return committeeMemberships;
    }

    public void setCommitteeMemberships(List<CommitteeMembership> committeeMemberships) {
        this.committeeMemberships = committeeMemberships;
    }
}

