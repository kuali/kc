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
package org.kuali.coeus.propdev.impl.attachment;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.kuali.coeus.common.framework.attachment.KcAttachmentDataDao;
import org.kuali.coeus.common.framework.attachment.KcAttachmentService;
import org.kuali.coeus.sys.api.model.KcFile;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.propdev.impl.hierarchy.HierarchyMaintainable;
import org.kuali.coeus.propdev.api.attachment.NarrativeContract;
import org.kuali.rice.core.api.CoreConstants;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.krad.data.jpa.converters.BooleanYNConverter;
import org.kuali.rice.krad.file.FileMeta;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.*;

@Entity
@Table(name = "NARRATIVE")
@IdClass(Narrative.NarrativeId.class)
public class Narrative extends KcPersistableBusinessObjectBase implements HierarchyMaintainable, KcFile, NarrativeContract, FileMeta, ProposalDevelopmentAttachment {

    @Id
    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "PROPOSAL_NUMBER")
    private DevelopmentProposal developmentProposal;

    @Id
    @Column(name = "MODULE_NUMBER")
    private Integer moduleNumber;

    @Column(name = "COMMENTS")
    private String comments;

    @Column(name = "CONTACT_NAME")
    private String contactName;

    @Column(name = "EMAIL_ADDRESS")
    private String emailAddress;

    @Column(name = "MODULE_SEQUENCE_NUMBER")
    private Integer moduleSequenceNumber;

    @Column(name = "MODULE_STATUS_CODE")
    private String moduleStatusCode;

    @Column(name = "MODULE_TITLE")
    private String moduleTitle;

    @Column(name = "NARRATIVE_TYPE_CODE")
    private String narrativeTypeCode;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "FILE_NAME")
    private String name;

    @Column(name = "CONTENT_TYPE")
    private String type;

    @Column(name = "HIERARCHY_PROPOSAL_NUMBER")
    private String hierarchyProposalNumber;

    @Column(name = "HIDE_IN_HIERARCHY")
    @Convert(converter = BooleanYNConverter.class)
    private boolean hiddenInHierarchy;

    @ManyToOne(cascade = { CascadeType.REFRESH})
    @JoinColumn(name = "NARRATIVE_TYPE_CODE", referencedColumnName = "NARRATIVE_TYPE_CODE", insertable = false, updatable = false)
    private NarrativeType narrativeType;

    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "MODULE_STATUS_CODE", referencedColumnName = "NARRATIVE_STATUS_CODE", insertable = false, updatable = false)
    private NarrativeStatus narrativeStatus;

    @OneToMany(orphanRemoval = true, cascade = { CascadeType.ALL })
    @JoinColumns({ @JoinColumn(name = "PROPOSAL_NUMBER", referencedColumnName = "PROPOSAL_NUMBER"), @JoinColumn(name = "MODULE_NUMBER", referencedColumnName = "MODULE_NUMBER") })
    private List<NarrativeUserRights> narrativeUserRights;

    @OneToOne(mappedBy = "narrative", cascade = { CascadeType.ALL })
    private NarrativeAttachment narrativeAttachment;

    @Transient
    private String uploadUserFullName;

    @Transient
    private String id;

    @Transient
    private Long size;

    @Transient
    private Date dateUploaded;

    @Transient
    private String url;

    @Transient
    private transient MultipartFile multipartFile;

    @Transient
    private transient DateTimeService dateTimeService;

    @Transient
    private transient KcAttachmentService kcAttachmentService;

    @Override
    public void init(MultipartFile multipartFile) throws Exception {
        this.name = multipartFile.getOriginalFilename();
        this.size = multipartFile.getSize();

        NarrativeAttachment attachment = new NarrativeAttachment();
        attachment.setType(multipartFile.getContentType());
        attachment.setData(multipartFile.getBytes());
        attachment.setName(multipartFile.getOriginalFilename());
        setNarrativeAttachment(attachment);
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getContentType() {
        if (narrativeAttachment != null) {
            return this.getNarrativeAttachment().getType();
        }
        return null;
    }

    @Override
    public void setContentType(String contentType) {
        if (narrativeAttachment != null) {
            this.getNarrativeAttachment().setType(contentType);
        }
    }

    @Override
    public Long getSize() {
        return size;
    }

    @Override
    public void setSize(Long size) {
        this.size = size;
    }

    @Override
    public Date getDateUploaded() {
        return dateUploaded;
    }

    @Override
    public void setDateUploaded(Date dateUploaded) {
        this.dateUploaded = dateUploaded;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String getSizeFormatted() {
        return getKcAttachmentService().formatFileSizeString(size);
    }

    @Override
    public String getDateUploadedFormatted() {
        if (this.getUpdateTimestamp() != null) {
            return getDateTimeService().toString(new Date(this.getUpdateTimestamp().getTime()), CoreConstants.TIMESTAMP_TO_STRING_FORMAT_FOR_USER_INTERFACE_DEFAULT);
        }
        return StringUtils.EMPTY;
    }

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }

    public Narrative() {
        narrativeUserRights = new ArrayList<NarrativeUserRights>();
    }

    @Override
    public Integer getModuleNumber() {
        return moduleNumber;
    }

    public void setModuleNumber(Integer moduleNumber) {
        this.moduleNumber = moduleNumber;
    }

    @Override
    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    @Override
    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
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

    @Override
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

    @Override
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public NarrativeType getNarrativeType() {
        return narrativeType;
    }

    public void setNarrativeType(NarrativeType narrativeType) {
        this.narrativeType = narrativeType;
    }

    @Override
    public List<NarrativeUserRights> getNarrativeUserRights() {
        if (narrativeUserRights != null) {
            Collections.sort(this.narrativeUserRights, new Comparator() {

                public int compare(Object o1, Object o2) {
                    NarrativeUserRights r1 = (NarrativeUserRights) o1;
                    NarrativeUserRights r2 = (NarrativeUserRights) o2;
                    if (r1 == null || r2 == null)
                        return 0;
                    String name1 = r1.getPersonName();
                    String name2 = r2.getPersonName();
                    if (name1 == null || name2 == null)
                        return 0;
                    return name1.compareTo(name2);
                }
            });
        }
        return narrativeUserRights;
    }

    public void setNarrativeUserRights(List<NarrativeUserRights> narrativeUserRights) {
        this.narrativeUserRights = narrativeUserRights;
    }

    @Override
    public NarrativeStatus getNarrativeStatus() {
        return narrativeStatus;
    }

    public void setNarrativeStatus(NarrativeStatus narrativeStatus) {
        this.narrativeStatus = narrativeStatus;
    }

    @Override
    public NarrativeAttachment getNarrativeAttachment() {
        return narrativeAttachment;
    }

    public void setNarrativeAttachment(NarrativeAttachment narrativeAttachment) {
        this.narrativeAttachment = narrativeAttachment;
        if (narrativeAttachment!=null) {
            this.narrativeAttachment.setNarrative(this);
        }
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * This method is to return institutional attachment type.  This column does not exist in table.
     * Basically, it is for narrativetypecode in table.
     * @return
     */
    public String getInstitutionalAttachmentTypeCode() {
        return narrativeTypeCode;
    }

    /**
     * 
     * This method set the institutional attachment type.  Since instituteattachmenttypecode is set as 'required' in DD.
     * So, it is set to narrativetypecode, and hence to return narrative type code.
     * @param institutionalAttachmentTypeCode
     */
    public void setInstitutionalAttachmentTypeCode(String institutionalAttachmentTypeCode) {
        this.narrativeTypeCode = institutionalAttachmentTypeCode;
    }

    public NarrativeUserRights getNarrativeUserRight(int index) {
        while (getNarrativeUserRights().size() <= index) {
            getNarrativeUserRights().add(new NarrativeUserRights());
        }
        return getNarrativeUserRights().get(index);
    }

    @Override
    public List buildListOfDeletionAwareLists() {
        List managedLists = super.buildListOfDeletionAwareLists();
        managedLists.add(getNarrativeUserRights());
        return managedLists;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((comments == null) ? 0 : comments.hashCode());
        result = prime * result + ((contactName == null) ? 0 : contactName.hashCode());
        result = prime * result + ((emailAddress == null) ? 0 : emailAddress.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((moduleNumber == null) ? 0 : moduleNumber.hashCode());
        result = prime * result + ((moduleSequenceNumber == null) ? 0 : moduleSequenceNumber.hashCode());
        result = prime * result + ((moduleStatusCode == null) ? 0 : moduleStatusCode.hashCode());
        result = prime * result + ((moduleTitle == null) ? 0 : moduleTitle.hashCode());
        result = prime * result + ((narrativeStatus == null) ? 0 : narrativeStatus.hashCode());
        result = prime * result + ((narrativeType == null) ? 0 : narrativeType.hashCode());
        result = prime * result + ((narrativeTypeCode == null) ? 0 : narrativeTypeCode.hashCode());
        result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
        result = prime * result + ((developmentProposal == null) ? 0 : developmentProposal.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Narrative other = (Narrative) obj;
        if (comments == null) {
            if (other.comments != null)
                return false;
        } else if (!comments.equals(other.comments))
            return false;
        if (contactName == null) {
            if (other.contactName != null)
                return false;
        } else if (!contactName.equals(other.contactName))
            return false;
        if (emailAddress == null) {
            if (other.emailAddress != null)
                return false;
        } else if (!emailAddress.equals(other.emailAddress))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (moduleNumber == null) {
            if (other.moduleNumber != null)
                return false;
        } else if (!moduleNumber.equals(other.moduleNumber))
            return false;
        if (moduleSequenceNumber == null) {
            if (other.moduleSequenceNumber != null)
                return false;
        } else if (!moduleSequenceNumber.equals(other.moduleSequenceNumber))
            return false;
        if (moduleStatusCode == null) {
            if (other.moduleStatusCode != null)
                return false;
        } else if (!moduleStatusCode.equals(other.moduleStatusCode))
            return false;
        if (moduleTitle == null) {
            if (other.moduleTitle != null)
                return false;
        } else if (!moduleTitle.equals(other.moduleTitle))
            return false;
        if (narrativeStatus == null) {
            if (other.narrativeStatus != null)
                return false;
        } else if (!narrativeStatus.equals(other.narrativeStatus))
            return false;
        if (narrativeType == null) {
            if (other.narrativeType != null)
                return false;
        } else if (!narrativeType.equals(other.narrativeType))
            return false;
        if (narrativeTypeCode == null) {
            if (other.narrativeTypeCode != null)
                return false;
        } else if (!narrativeTypeCode.equals(other.narrativeTypeCode))
            return false;
        if (phoneNumber == null) {
            if (other.phoneNumber != null)
                return false;
        } else if (!phoneNumber.equals(other.phoneNumber))
            return false;
        if (developmentProposal == null) {
            if (other.developmentProposal != null)
                return false;
        } else if (!developmentProposal.equals(other.developmentProposal))
            return false;
        return true;
    }

    public int hierarchyHashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((comments == null) ? 0 : comments.hashCode());
        result = prime * result + ((contactName == null) ? 0 : contactName.hashCode());
        result = prime * result + ((emailAddress == null) ? 0 : emailAddress.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((moduleNumber == null) ? 0 : moduleNumber.hashCode());
        result = prime * result + ((moduleSequenceNumber == null) ? 0 : moduleSequenceNumber.hashCode());
        result = prime * result + ((moduleStatusCode == null) ? 0 : moduleStatusCode.hashCode());
        result = prime * result + ((moduleTitle == null) ? 0 : moduleTitle.hashCode());
        result = prime * result + ((narrativeStatus == null) ? 0 : narrativeStatus.hashCode());
        result = prime * result + ((narrativeType == null) ? 0 : narrativeType.hashCode());
        result = prime * result + ((narrativeTypeCode == null) ? 0 : narrativeTypeCode.hashCode());
        result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
        return result;
    }

    public Timestamp getTimestampDisplay() {
        if (getNarrativeAttachment() == null || getNarrativeAttachment().getUploadTimestamp() == null) {
            return getDateTimeService().getCurrentTimestamp();
        }

        return getNarrativeAttachment().getUploadTimestamp();
}

    public String getUploadUserDisplay() {
        if (getNarrativeAttachment() == null || StringUtils.isBlank(getNarrativeAttachment().getUploadUser())) {
            return this.getUpdateUser();
        }

        return getNarrativeAttachment().getUploadUser();
    }

    public String getUploadUserFullName() {
        return uploadUserFullName;
    }

    public void setUploadUserFullName(String uploadUserFullName) {
        this.uploadUserFullName = uploadUserFullName;
    }

    @Override
    public String getHierarchyProposalNumber() {
        return hierarchyProposalNumber;
    }

    public void setHierarchyProposalNumber(String hierarchyProposalNumber) {
        this.hierarchyProposalNumber = hierarchyProposalNumber;
    }

    @Override
    public boolean isHiddenInHierarchy() {
        return hiddenInHierarchy;
    }

    public void setHiddenInHierarchy(boolean hiddenInHierarchy) {
        this.hiddenInHierarchy = hiddenInHierarchy;
    }

    @Override
    public String getType() {
        return type;
    }

    public void setType(String contentType) {
        this.type = contentType;
    }

    public byte[] getData() {
        return getNarrativeAttachment() != null ? getNarrativeAttachment().getData() : null;
    }

    public static final class NarrativeId implements Serializable, Comparable<NarrativeId> {

        public static final String DEVELOPMENT_PROPOSAL = "developmentProposal";
        public static final String MODULE_NUMBER = "moduleNumber";
        private String developmentProposal;

        private Integer moduleNumber;

        public NarrativeId(String proposalNumber, Integer moduleNumber) {
            this.moduleNumber = moduleNumber;
            this.developmentProposal = proposalNumber;
        }

        public Integer getModuleNumber() {
            return this.moduleNumber;
        }

        public void setModuleNumber(Integer moduleNumber) {
            this.moduleNumber = moduleNumber;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this).append(DEVELOPMENT_PROPOSAL, this.developmentProposal).append(MODULE_NUMBER, this.moduleNumber).toString();
        }

        @Override
        public boolean equals(Object other) {
            if (other == null)
                return false;
            if (other == this)
                return true;
            if (other.getClass() != this.getClass())
                return false;
            final NarrativeId rhs = (NarrativeId) other;
            return new EqualsBuilder().append(this.developmentProposal, rhs.developmentProposal).append(this.moduleNumber, rhs.moduleNumber).isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(this.developmentProposal).append(this.moduleNumber).toHashCode();
        }

        @Override
        public int compareTo(NarrativeId other) {
        	return new CompareToBuilder().append(this.developmentProposal, other.developmentProposal).append(this.moduleNumber, other.moduleNumber).toComparison();
        }

		public String getDevelopmentProposal() {
			return developmentProposal;
		}

		public void setDevelopmentProposal(String developmentProposal) {
			this.developmentProposal = developmentProposal;
		}
    }



	public DevelopmentProposal getDevelopmentProposal() {
		return developmentProposal;
	}

	public void setDevelopmentProposal(DevelopmentProposal developmentProposal) {
		this.developmentProposal = developmentProposal;
	}

	@Override
	public String getProposalNumber() {
		return getDevelopmentProposal().getProposalNumber();
	}

    public DateTimeService getDateTimeService() {
        if (dateTimeService == null)
            dateTimeService = KcServiceLocator.getService(DateTimeService.class);
        return dateTimeService;
    }

    public void setDateTimeService(DateTimeService dateTimeService) {
        this.dateTimeService = dateTimeService;
    }

    public KcAttachmentService getKcAttachmentService() {
        if (kcAttachmentService == null)
            kcAttachmentService = KcServiceLocator.getService(KcAttachmentService.class);
        return kcAttachmentService;
    }

    public void setKcAttachmentService(KcAttachmentService kcAttachmentService) {
        this.kcAttachmentService = kcAttachmentService;
    }

    @PostRemove
    public void removeData() {
        if (getNarrativeAttachment() != null && getNarrativeAttachment().getFileDataId() != null) {
            getKcAttachmentDao().removeData(getNarrativeAttachment().getFileDataId());
        }
    }

    private KcAttachmentDataDao getKcAttachmentDao() {
        return KcServiceLocator.getService(KcAttachmentDataDao.class);
    }
}

