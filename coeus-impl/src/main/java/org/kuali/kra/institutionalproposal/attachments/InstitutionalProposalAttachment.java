/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 *
 * Copyright 2005-2016 Kuali, Inc.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.kra.institutionalproposal.attachments;


import java.lang.ref.WeakReference;
import java.sql.Timestamp;

import org.apache.struts.upload.FormFile;
import org.kuali.coeus.common.framework.attachment.KcAttachmentDataDao;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.institutionalproposal.InstitutionalProposalAssociate;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposalAttachmentType;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kim.api.identity.PersonService;

import javax.persistence.PostRemove;


public class InstitutionalProposalAttachment extends InstitutionalProposalAssociate  implements Comparable<InstitutionalProposalAttachment> {

	private static final long serialVersionUID = 502762283098287794L;

	private Long proposalAttachmentId;
    
    private Long proposalId;
    
    private String proposalNumber;
    
    private Integer sequenceNumber;
    
    private Integer attachmentNumber;
    
    private String attachmentTitle;
    
    private Integer attachmentTypeCode;
    
    private String fileName;
    
    private String contentType;
    
    private String comments;

	private String fileDataId;

    private String documentStatusCode;
    
    private boolean modifyAttachment=false;

    private transient FormFile newFile;


    private InstitutionalProposalAttachmentType type;

	private transient WeakReference<byte[]> data;

	private Timestamp lastUpdateTimestamp;

	private String lastUpdateUser;

	

    public InstitutionalProposalAttachment() {
        super();
    }
    
    public InstitutionalProposalAttachment(final InstitutionalProposal proposal) {
        this.setInstitutionalProposal(proposal);
    }

	public Long getProposalAttachmentId() {
		return proposalAttachmentId;
	}

	public void setProposalAttachmentId(Long proposalAttachmentId) {
		this.proposalAttachmentId = proposalAttachmentId;
	}

	public Long getProposalId() {
		return proposalId;
	}

	public void setProposalId(Long proposalId) {
		this.proposalId = proposalId;
	}

	public String getProposalNumber() {
		return proposalNumber;
	}

	public void setProposalNumber(String proposalNumber) {
		this.proposalNumber = proposalNumber;
	}

	public Integer getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(Integer sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	public Integer getAttachmentNumber() {
		return attachmentNumber;
	}

	public void setAttachmentNumber(Integer attachmentNumber) {
		this.attachmentNumber = attachmentNumber;
	}

	public String getAttachmentTitle() {
		return attachmentTitle;
	}

	public void setAttachmentTitle(String attachmentTitle) {
		this.attachmentTitle = attachmentTitle;
	}

	public Integer getAttachmentTypeCode() {
		return attachmentTypeCode;
	}

	public void setAttachmentTypeCode(Integer attachmentTypeCode) {
		this.attachmentTypeCode = attachmentTypeCode;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getDocumentStatusCode() {
		return documentStatusCode;
	}

	public void setDocumentStatusCode(String documentStatusCode) {
		this.documentStatusCode = documentStatusCode;
	}

	public boolean isModifyAttachment() {
		return modifyAttachment;
	}

	public void setModifyAttachment(boolean modifyAttachment) {
		this.modifyAttachment = modifyAttachment;
	}

	/**
	 * @return the newFile
	 */
	public FormFile getNewFile() {
		return newFile;
	}

	/**
	 * @param newFile the newFile to set
	 */
	public void setNewFile(FormFile newFile) {
		this.newFile = newFile;
	}

	/**
	 * @return the type
	 */
	public InstitutionalProposalAttachmentType getType() {
		return type;
	}

	public void setType(InstitutionalProposalAttachmentType type) {
		this.type = type;
	}

	public String getFileDataId() {
		return fileDataId;
	}

	public void setFileDataId(String fileDataId) {
		this.fileDataId = fileDataId;
	}

	public Timestamp getLastUpdateTimestamp() {
		return lastUpdateTimestamp;
	}

	public void setLastUpdateTimestamp(Timestamp lastUpdateTimestamp) {
		this.lastUpdateTimestamp = lastUpdateTimestamp;
	}

	public String getLastUpdateUser() {
		return lastUpdateUser;
	}

	public void setLastUpdateUser(String lastUpdateUser) {
		this.lastUpdateUser = lastUpdateUser;
	}

	public String getLastUpdateUserName() {
        Person updateUser = KcServiceLocator.getService(PersonService.class).getPersonByPrincipalName(this.getLastUpdateUser());
        return updateUser != null ? updateUser.getName() : this.getUpdateUser();
    }

	@Override
	public void setInstitutionalProposal(InstitutionalProposal institutionalProposal) {
		super.setInstitutionalProposal(institutionalProposal);
		if (institutionalProposal != null) {
			setProposalId(institutionalProposal.getProposalId());
		}
	}

	@Override
	public int compareTo(InstitutionalProposalAttachment o) {
		return this.getFileDataId().compareTo(o.getFileDataId());
	}

	@PostRemove
	public void postRemove() {
		if (getFileDataId() != null) {
			getKcAttachmentDao().removeData(getFileDataId());
		}
	}

	@Override
	protected void prePersist() {
		super.prePersist();
		if (lastUpdateTimestamp == null) {
			setLastUpdateTimestamp(getUpdateTimestamp());
		}
		if (lastUpdateUser == null) {
			setLastUpdateUser(getUpdateUser());
		}
	}

	public byte[] getData() {
		if (data != null) {
			byte[] existingData = data.get();
			if (existingData != null) {
				return existingData;
			}
		}
		//if we didn't have a weakreference, grab the data from the db
		byte[] newData = getKcAttachmentDao().getData(fileDataId);
		data = new WeakReference<>(newData);
		return newData;
	}

	public void setData(byte[] data) {
		if (data == null) {
			getKcAttachmentDao().removeData(fileDataId);
		} else {
			fileDataId = getKcAttachmentDao().saveData(data, fileDataId);
		}
		this.data = new WeakReference<>(data);
	}

	private KcAttachmentDataDao getKcAttachmentDao() {
		return KcServiceLocator.getService(KcAttachmentDataDao.class);
	}

}
