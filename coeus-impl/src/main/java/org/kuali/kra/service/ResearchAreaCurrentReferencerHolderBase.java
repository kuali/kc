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
package org.kuali.kra.service;

import org.kuali.coeus.common.committee.impl.bo.CommitteeBase;
import org.kuali.coeus.common.committee.impl.bo.CommitteeMembershipBase;
import org.kuali.kra.protocol.ProtocolBase;

/**
 * This class encapsulates a current protocol, committee or committee membership (if any) that references a given research area. 
 * This class is used as a return value from the methods of Research Area service.
 * 
 */
public class ResearchAreaCurrentReferencerHolderBase {
    
    private String researchAreaCode;
    private ProtocolBase currentReferencingProtocol;
    private CommitteeBase currentReferencingCommittee;
    private CommitteeMembershipBase currentReferencingCommitteeMembership;
    public static final ResearchAreaCurrentReferencerHolderBase NO_REFERENCER = new ResearchAreaCurrentReferencerHolderBase();
    
    private ResearchAreaCurrentReferencerHolderBase() {
        // does nothing, all instance variables will be initialized to null
    }
    
    
    public ResearchAreaCurrentReferencerHolderBase(String researchAreaCode, ProtocolBase currentReferencingProtocol, CommitteeBase currentReferencingCommittee, CommitteeMembershipBase currentReferencingCommitteeMembership) {
        this.researchAreaCode = researchAreaCode;
        this.currentReferencingProtocol = currentReferencingProtocol;
        this.currentReferencingCommittee = currentReferencingCommittee;
        this.currentReferencingCommitteeMembership = currentReferencingCommitteeMembership;
    }
    
    public ProtocolBase getCurrentReferencingProtocol() {
        return currentReferencingProtocol;
    }
    public CommitteeBase getCurrentReferencingCommittee() {
        return currentReferencingCommittee;
    }
    public CommitteeMembershipBase getCurrentReferencingCommitteeMembership() {
        return currentReferencingCommitteeMembership;
    }

    public String getResearchAreaCode() {
        return researchAreaCode;
    }
    
    public String getMessage() {
        String retValue = "";
        if(this == ResearchAreaCurrentReferencerHolderBase.NO_REFERENCER) {
            retValue = "No referencers";
        }
        else if(null == this.researchAreaCode) {
            retValue = "Research area code undefined";
        }
        else if(null != this.currentReferencingProtocol) {
            retValue = "Research area " + this.researchAreaCode + " is referenced by current version of protocol with number " + this.currentReferencingProtocol.getProtocolNumber();
        }
        else if(null != this.currentReferencingCommitteeMembership) {
            //TODO make this message better?
            retValue = "Research area " + this.researchAreaCode + " is referenced by current version of committee membership named " + this.currentReferencingCommitteeMembership.getPersonName();
            if(null != this.currentReferencingCommittee) {
                retValue = retValue + " in committee with ID " + this.currentReferencingCommittee.getCommitteeId();
            }
        }
        else if(null != this.currentReferencingCommittee) {
            retValue = "Research area " + this.researchAreaCode + " is referenced by current version of committee with ID " + this.currentReferencingCommittee.getCommitteeId();
        }
        
        return retValue;
    }

}
