package org.kuali.kra.proposaldevelopment.bo;

import java.util.LinkedHashMap;

import org.apache.struts.upload.FormFile;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

/**
 * 
 * This bo for eps_prop_person_bio_attachment
 */
public class ProposalPersonBiographyAttachment extends AttachmentDataSource {

	private Integer proposalPersonNumber;
    private Integer proposalNumber;
    private Integer biographyNumber;
	private byte[] biographyData;
//	private String fileName;
//    private String contentType;

	public ProposalPersonBiographyAttachment(){
		super();
	}

	public Integer getProposalPersonNumber() {
		return proposalPersonNumber;
	}

	public void setProposalPersonNumber(Integer proposalPersonNumber) {
		this.proposalPersonNumber = proposalPersonNumber;
	}

	public Integer getProposalNumber() {
		return proposalNumber;
	}

	public void setProposalNumber(Integer proposalNumber) {
		this.proposalNumber = proposalNumber;
	}

	public byte[] getBiographyData() {
		return biographyData;
	}

	public void setBiographyData(byte[] biographyData) {
		this.biographyData = biographyData;
	}


	@Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("proposalPersonNumber", getProposalPersonNumber());
		hashMap.put("proposalNumber", getProposalNumber());
		//hashMap.put("bioAttachment", getBioAttachment());
		hashMap.put("fileName", getFileName());
		return hashMap;
	}

    @Override
    public byte[] getContent() {
        return biographyData;
    }

    public Integer getBiographyNumber() {
        return biographyNumber;
    }

    public void setBiographyNumber(Integer biographyNumber) {
        this.biographyNumber = biographyNumber;
    }

}
