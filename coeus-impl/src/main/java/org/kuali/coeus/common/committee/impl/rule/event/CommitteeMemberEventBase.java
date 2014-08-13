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
package org.kuali.coeus.common.committee.impl.rule.event;

import org.kuali.coeus.common.committee.impl.bo.CommitteeMembershipBase;
import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.coeus.sys.framework.rule.KcDocumentEventBaseExtension;
import org.kuali.rice.krad.document.Document;

import java.util.List;

/**
 * 
 * This class is using the new rule framework.  The old framework is using CommitteeMembershipRuleEventBase
 */
public abstract class CommitteeMemberEventBase <Z extends KcBusinessRule> extends KcDocumentEventBaseExtension {
    
    /**
     * Enum helps identify type of error to respond.
     */
    public enum ErrorType {HARDERROR, SOFTERROR};
    
    private List<CommitteeMembershipBase> committeeMemberships;
        
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
    public CommitteeMemberEventBase(String description, String errorPathPrefix, Document document, List<CommitteeMembershipBase> committeeMemberships, ErrorType type) {        
        super(description, errorPathPrefix, document);
        this.committeeMemberships = committeeMemberships;
        this.type = type;
    }
        
    public ErrorType getType() {
        return this.type;
    }

    public List<CommitteeMembershipBase> getCommitteeMemberships() {
        return committeeMemberships;
    }

    public void setCommitteeMemberships(List<CommitteeMembershipBase> committeeMemberships) {
        this.committeeMemberships = committeeMemberships;
    }
}

