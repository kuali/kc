package org.kuali.kra.proposaldevelopment.bo;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.struts.upload.FormFile;
import org.kuali.core.bo.user.UniversalUser;
import org.kuali.core.exceptions.UserNotFoundException;
import org.kuali.core.service.UniversalUserService;
import org.kuali.core.util.ObjectUtils;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.bo.PropPerDocType;
import org.kuali.rice.KNSServiceLocator;

public class PropPersonBio extends KraPersistableBusinessObjectBase {

	private Integer bioNumber;
	private String personId;
	private Integer proposalNumber;
	private String description;
    private String documentTypeCode;
    private String fileName;
    transient private FormFile personnelAttachmentFile;
    private List<PropPersonBioAttachment> personnelAttachmentList;
    private PropPerDocType propPerDocType;

	public PropPersonBio(){
		super();
		personnelAttachmentList = new ArrayList<PropPersonBioAttachment>(1);
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
		hashMap.put("bioNumber", getBioNumber());
		hashMap.put("personId", getPersonId());
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

    public List<PropPersonBioAttachment> getPersonnelAttachmentList() {
        return personnelAttachmentList;
    }

    public void setPersonnelAttachmentList(List<PropPersonBioAttachment> personnelAttachmentList) {
        this.personnelAttachmentList = personnelAttachmentList;
    }

    public PropPerDocType getPropPerDocType() {
        return propPerDocType;
    }

    public void setPropPerDocType(PropPerDocType propPerDocType) {
        this.propPerDocType = propPerDocType;
    }

    //Helper methods
    // TODO : move helper method to upperclass for sharing ??

    public String getAuthorPersonName(){
        UniversalUser user=null;
        try {
            user = KNSServiceLocator.getBean(UniversalUserService.class).getUniversalUser(getUpdateUser());
        }
        catch (UserNotFoundException unfe) {
        }
        if (ObjectUtils.isNull(user)) {
            return "Person not found";
        }
        else {
            return user.getPersonName();
        }
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

}
