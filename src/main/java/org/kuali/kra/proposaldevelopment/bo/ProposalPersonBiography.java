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

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.struts.upload.FormFile;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.bo.PropPerDocType;

/**
 * 
 * This is bo for eps_prop_person_bio.
 */
public class ProposalPersonBiography extends KraPersistableBusinessObjectBase {

	private Integer proposalPersonNumber;
	private String personId;
    private String proposalNumber;
    private Integer biographyNumber;
    private Integer rolodexId;
	private String description;
    private String documentTypeCode;
    private String fileName;
    transient private FormFile personnelAttachmentFile;
    private List<ProposalPersonBiographyAttachment> personnelAttachmentList;
    private PropPerDocType propPerDocType;
    private Timestamp timestampDisplay;
    private String uploadUserDisplay;

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

}
