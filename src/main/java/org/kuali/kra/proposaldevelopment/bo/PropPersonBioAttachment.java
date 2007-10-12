package org.kuali.kra.proposaldevelopment.bo;

import java.util.LinkedHashMap;

import org.apache.struts.upload.FormFile;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

public class PropPersonBioAttachment extends AttachmentDataSource {

	private Integer bioNumber;
	private String personId;
	private Integer proposalNumber;
	private byte[] bioData;
//	private String fileName;
//    private String contentType;

	public PropPersonBioAttachment(){
		super();
	}

	public Integer getBioNumber() {
		return bioNumber;
	}

	public void setBioNumber(Integer bioNumber) {
		this.bioNumber = bioNumber;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public Integer getProposalNumber() {
		return proposalNumber;
	}

	public void setProposalNumber(Integer proposalNumber) {
		this.proposalNumber = proposalNumber;
	}

	public byte[] getBioData() {
		return bioData;
	}

	public void setBioData(byte[] bioData) {
		this.bioData = bioData;
	}


	@Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("bioNumber", getBioNumber());
		hashMap.put("personId", getPersonId());
		hashMap.put("proposalNumber", getProposalNumber());
		//hashMap.put("bioAttachment", getBioAttachment());
		hashMap.put("fileName", getFileName());
		return hashMap;
	}

    @Override
    public byte[] getContent() {
        return bioData;
    }

}
