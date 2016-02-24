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
package org.kuali.kra.committee.rule.event;

import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.coeus.sys.framework.rule.KcDocumentEventBaseExtension;
import org.kuali.kra.committee.bo.CommitteeMembership;
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

