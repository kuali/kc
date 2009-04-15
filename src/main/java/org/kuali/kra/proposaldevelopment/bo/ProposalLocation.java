/*
 * Copyright 2006-2009 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the License);
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.proposaldevelopment.bo;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.bo.Rolodex;

/**
 * 
 * This is bo class for proposal location.
 */
public class ProposalLocation extends KraPersistableBusinessObjectBase {
	private String location;
	private String proposalNumber;
    private Integer rolodexId;
    private Rolodex rolodex;
    private Integer locationSequenceNumber;

    public ProposalLocation() {
        super();
        this.setLocation("");
        this.setRolodexId(0);
        this.setProposalNumber("");
    }

    
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getProposalNumber() {
		return proposalNumber;
	}

	public void setProposalNumber(String proposalNumber) {
		this.proposalNumber = proposalNumber;
	}

	public Integer getRolodexId() {
		return rolodexId;
	}

	public void setRolodexId(Integer rolodexId) {
		this.rolodexId = rolodexId;
	}


	@Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
        hashMap.put("location", getLocation());
        hashMap.put("locationSequenceNumber", getLocationSequenceNumber());
		hashMap.put("proposalNumber", getProposalNumber());
		hashMap.put("rolodexId", getRolodexId());
		return hashMap;
	}

    public Rolodex getRolodex() {
        return rolodex;
    }

    public void setRolodex(Rolodex rolodex) {
        this.rolodex = rolodex;
    }


    public Integer getLocationSequenceNumber() {
        return locationSequenceNumber;
    }


    public void setLocationSequenceNumber(Integer locationSequenceNumber) {
        this.locationSequenceNumber = locationSequenceNumber;
    }
}
