package org.kuali.kra.proposaldevelopment.bo;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.bo.Rolodex;

public class ProposalLocation extends KraPersistableBusinessObjectBase {
	private String location;
	private Integer proposalNumber;
    private Integer rolodexId;
    private Rolodex rolodex;
    private Integer locationSequenceNumber;

    public ProposalLocation() {
        super();
        this.setLocation("");
        this.setRolodexId(0);
        this.setProposalNumber(0);
    }

    
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Integer getProposalNumber() {
		return proposalNumber;
	}

	public void setProposalNumber(Integer proposalNumber) {
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
