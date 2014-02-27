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
