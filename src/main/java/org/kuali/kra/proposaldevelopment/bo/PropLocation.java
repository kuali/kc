package org.kuali.kra.proposaldevelopment.bo;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.bo.Rolodex;

public class PropLocation extends KraPersistableBusinessObjectBase {
	private String location;
	private String proposalNumber;
    private Integer rolodexId;
    private Rolodex rolodex;
    private Integer locationSequenceNumber;

    public PropLocation() {
        super();
        this.setLocation("");
        this.setRolodexId(new Integer(0));
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
