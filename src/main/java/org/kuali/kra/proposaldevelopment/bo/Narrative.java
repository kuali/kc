/*
 * Copyright 2007 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.proposaldevelopment.bo;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.struts.upload.FormFile;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

public class Narrative extends KraPersistableBusinessObjectBase {
	private Integer proposalNumber;
    private Integer moduleNumber;
	private String comments;
	private String contactName;
	private String emailAddress;
	private Integer moduleSequenceNumber;
	private String moduleStatusCode;
	private String moduleTitle;
	private String narrativeTypeCode;
	private String phoneNumber;
	private NarrativeType narrativeType;
    private NarrativeStatus narrativeStatus;
    private String fileName;
	private List<NarrativeUserRights> narrativeUserRights;
	private List<NarrativeAttachment> narrativeAttachmentList;
    transient private FormFile narrativeFile;
    private String institutionalAttachmentTypeCode;
    private boolean viewAttachment;
    private boolean modifyAttachment;

	public Narrative(){
	    narrativeAttachmentList = new ArrayList<NarrativeAttachment>(1);
	    narrativeUserRights = new ArrayList<NarrativeUserRights>();
	}

    public Integer getModuleNumber() {
		return moduleNumber;
	}

	public void setModuleNumber(Integer moduleNumber) {
		this.moduleNumber = moduleNumber;
	}

	public Integer getProposalNumber() {
		return proposalNumber;
	}

	public void setProposalNumber(Integer proposalNumber) {
		this.proposalNumber = proposalNumber;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public Integer getModuleSequenceNumber() {
		return moduleSequenceNumber;
	}

	public void setModuleSequenceNumber(Integer moduleSequenceNumber) {
		this.moduleSequenceNumber = moduleSequenceNumber;
	}

	public String getModuleStatusCode() {
		return moduleStatusCode;
	}

	public void setModuleStatusCode(String moduleStatusCode) {
		this.moduleStatusCode = moduleStatusCode;
	}

	public String getModuleTitle() {
		return moduleTitle;
	}

	public void setModuleTitle(String moduleTitle) {
		this.moduleTitle = moduleTitle;
	}

	public String getNarrativeTypeCode() {
		return narrativeTypeCode;
	}

	public void setNarrativeTypeCode(String narrativeTypeCode) {
		this.narrativeTypeCode = narrativeTypeCode;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	@Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("moduleNumber", getModuleNumber());
		hashMap.put("proposalNumber", getProposalNumber());
		hashMap.put("comments", getComments());
		hashMap.put("contactName", getContactName());
		hashMap.put("emailAddress", getEmailAddress());
		hashMap.put("moduleSequenceNumber", getModuleSequenceNumber());
		hashMap.put("moduleStatusCode", getModuleStatusCode());
		hashMap.put("moduleTitle", getModuleTitle());
		hashMap.put("narrativeTypeCode", getNarrativeTypeCode());
		hashMap.put("phoneNumber", getPhoneNumber());
		return hashMap;
	}

    public NarrativeType getNarrativeType() {
        return narrativeType;
    }

    public void setNarrativeType(NarrativeType narrativeType) {
        this.narrativeType = narrativeType;
    }

    public List<NarrativeUserRights> getNarrativeUserRights() {
        return narrativeUserRights;
    }

    public void setNarrativeUserRights(List<NarrativeUserRights> narrativeUserRights) {
        this.narrativeUserRights = narrativeUserRights;
    }
    /**
     * Gets the narrativeStatus attribute. 
     * @return Returns the narrativeStatus.
     */
    public NarrativeStatus getNarrativeStatus() {
        return narrativeStatus;
    }
    /**
     * Sets the narrativeStatus attribute value.
     * @param narrativeStatus The narrativeStatus to set.
     */
    public void setNarrativeStatus(NarrativeStatus narrativeStatus) {
        this.narrativeStatus = narrativeStatus;
    }
    public List<NarrativeAttachment> getNarrativeAttachmentList() {
        return narrativeAttachmentList;
    }
    public void setNarrativeAttachmentList(List<NarrativeAttachment> narrativePdfList) {
        this.narrativeAttachmentList = narrativePdfList;
    }
    public FormFile getNarrativeFile() {
        return narrativeFile;
    }
    public void setNarrativeFile(FormFile narrativeFile) {
        this.narrativeFile = narrativeFile;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getInstitutionalAttachmentTypeCode() {
        return institutionalAttachmentTypeCode;
    }

    public void setInstitutionalAttachmentTypeCode(String institutionalAttachmentTypeCode) {
        this.institutionalAttachmentTypeCode = institutionalAttachmentTypeCode;
    }

    /**
     * Gets the view attribute. 
     * @return Returns the view.
     */
    public boolean getViewAttachment() {
        return viewAttachment;
    }

    /**
     * Sets the view attribute value.
     * @param view The view to set.
     */
    public void setViewAttachment(boolean view) {
        this.viewAttachment = view;
    }

    /**
     * Gets the modify attribute. 
     * @return Returns the modify.
     */
    public boolean getModifyAttachment() {
        return modifyAttachment;
    }

    /**
     * Sets the modify attribute value.
     * @param modify The modify to set.
     */
    public void setModifyAttachment(boolean modify) {
        this.modifyAttachment = modify;
        //If user has modify right, gets view access as well
        if(modify) setViewAttachment(true);
    }
}
