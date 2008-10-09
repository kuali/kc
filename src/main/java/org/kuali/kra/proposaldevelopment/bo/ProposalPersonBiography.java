package org.kuali.kra.proposaldevelopment.bo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.struts.upload.FormFile;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.bo.PropPerDocType;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;

/**
 * 
 * This is bo for eps_prop_person_bio.
 */
@IdClass(org.kuali.kra.proposaldevelopment.bo.id.ProposalPersonBiographyId.class)
@Entity
@Table(name="EPS_PROP_PERSON_BIO")
public class ProposalPersonBiography extends KraPersistableBusinessObjectBase {
    
    @Id
    @Column(name="PROPOSAL_NUMBER")
    private String proposalNumber;
    
	@Id
	@Column(name="PROP_PERSON_NUMBER")
	private Integer proposalPersonNumber;
	
	@Column(name="PERSON_ID")
	private String personId;
	
    @Id
	@Column(name="BIO_NUMBER")
	private Integer biographyNumber;
    
    @Column(name="ROLODEX_ID")
	private Integer rolodexId;
    
	@Column(name="DESCRIPTION")
	private String description;
	
    @Column(name="DOCUMENT_TYPE_CODE")
	private String documentTypeCode;
    
    @Column(name="FILE_NAME")
	private String fileName;
    
    transient private FormFile personnelAttachmentFile;
    private Timestamp timestampDisplay;
    private String uploadUserDisplay;
    
    @OneToMany(cascade={CascadeType.REMOVE, CascadeType.MERGE}, 
           targetEntity=org.kuali.kra.proposaldevelopment.bo.ProposalPersonBiographyAttachment.class, mappedBy="proposalPersonBiography")
	private List<ProposalPersonBiographyAttachment> personnelAttachmentList;
    
    @OneToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST})
	@JoinColumn(name="DOCUMENT_TYPE_CODE", insertable=false, updatable=false)
	private PropPerDocType propPerDocType;
    
    @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST })
    @JoinColumn(name="PROPOSAL_NUMBER", insertable = false, updatable = false)
    private ProposalDevelopmentDocument proposalDevelopmentDocument;

	public ProposalPersonBiography(){
		super();
		personnelAttachmentList = new ArrayList<ProposalPersonBiographyAttachment>(1);
	}

	public Integer getProposalPersonNumber() {
		return proposalPersonNumber;
	}

	public void setProposalPersonNumber(Integer proposalPersonNumber) {
		this.proposalPersonNumber = proposalPersonNumber;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getProposalNumber() {
		return proposalNumber;
	}

	public void setProposalNumber(String proposalNumber) {
		this.proposalNumber = proposalNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDocumentTypeCode() {
		return documentTypeCode;
	}

	public void setDocumentTypeCode(String documentTypeCode) {
		this.documentTypeCode = documentTypeCode;
	}


	@Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("proposalPersonNumber", getProposalPersonNumber());
        hashMap.put("personId", getPersonId());
        hashMap.put("rolodexId", getRolodexId());
		hashMap.put("proposalNumber", getProposalNumber());
		hashMap.put("description", getDescription());
		hashMap.put("documentTypeCode", getDocumentTypeCode());
		return hashMap;
	}

    public FormFile getPersonnelAttachmentFile() {
        return personnelAttachmentFile;
    }

    public void setPersonnelAttachmentFile(FormFile personnelAttachmentFile) {
        this.personnelAttachmentFile = personnelAttachmentFile;
    }

    public List<ProposalPersonBiographyAttachment> getPersonnelAttachmentList() {
        return personnelAttachmentList;
    }

    public void setPersonnelAttachmentList(List<ProposalPersonBiographyAttachment> personnelAttachmentList) {
        this.personnelAttachmentList = personnelAttachmentList;
    }

    public PropPerDocType getPropPerDocType() {
        return propPerDocType;
    }

    public void setPropPerDocType(PropPerDocType propPerDocType) {
        this.propPerDocType = propPerDocType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Integer getRolodexId() {
        return rolodexId;
    }

    public void setRolodexId(Integer rolodexId) {
        this.rolodexId = rolodexId;
    }

    public Integer getBiographyNumber() {
        return biographyNumber;
    }

    public void setBiographyNumber(Integer biographyNumber) {
        this.biographyNumber = biographyNumber;
    }

    public Timestamp getTimestampDisplay() {
        return timestampDisplay;
    }

    public void setTimestampDisplay(Timestamp timestampDisplay) {
        this.timestampDisplay = timestampDisplay;
    }

    public String getUploadUserDisplay() {
        return uploadUserDisplay;
    }

    public void setUploadUserDisplay(String uploadUserDisplay) {
        this.uploadUserDisplay = uploadUserDisplay;
    }

    public ProposalDevelopmentDocument getProposalDevelopmentDocument() {
        return proposalDevelopmentDocument;
    }

    public void setProposalDevelopmentDocument(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        this.proposalDevelopmentDocument = proposalDevelopmentDocument;
    }
}
