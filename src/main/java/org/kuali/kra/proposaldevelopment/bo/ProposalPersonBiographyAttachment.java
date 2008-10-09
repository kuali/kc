package org.kuali.kra.proposaldevelopment.bo;

import java.util.LinkedHashMap;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 * This bo for eps_prop_person_bio_attachment
 */
@IdClass(org.kuali.kra.proposaldevelopment.bo.id.ProposalPersonBiographyAttachmentId.class)
@Entity
@Table(name="EPS_PROP_PERSON_BIO_ATTACHMENT")
public class ProposalPersonBiographyAttachment extends AttachmentDataSource {
    
    @Id
    @Column(name="PROPOSAL_NUMBER")
    private String proposalNumber;
    
	@Id
	@Column(name="PROP_PERSON_NUMBER")
	private Integer proposalPersonNumber;
	
    @Id
	@Column(name="BIO_NUMBER")
	private Integer biographyNumber;
    
	@Column(name="BIO_DATA")
	private byte[] biographyData;
    
    @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST })
    @JoinColumns({@JoinColumn(name="PROPOSAL_NUMBER", insertable = false, updatable = false),
                  @JoinColumn(name="PROP_PERSON_NUMBER", insertable=false, updatable=false),
                  @JoinColumn(name="BIO_NUMBER", insertable=false, updatable=false)})
    private ProposalPersonBiography proposalPersonBiography;

	public ProposalPersonBiographyAttachment(){
		super();
	}

	public Integer getProposalPersonNumber() {
		return proposalPersonNumber;
	}

	public void setProposalPersonNumber(Integer proposalPersonNumber) {
		this.proposalPersonNumber = proposalPersonNumber;
	}

	public String getProposalNumber() {
		return proposalNumber;
	}

	public void setProposalNumber(String proposalNumber) {
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

