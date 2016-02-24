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
package org.kuali.coeus.propdev.impl.budget.subaward;

import java.io.Serializable;
import java.lang.ref.SoftReference;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.kuali.coeus.common.framework.attachment.KcAttachmentDataDao;
import org.kuali.coeus.common.framework.org.Organization;
import org.kuali.coeus.propdev.api.budget.subaward.BudgetSubAwardsContract;
import org.kuali.coeus.propdev.impl.budget.ProposalDevelopmentBudgetExt;
import org.kuali.coeus.propdev.impl.hierarchy.HierarchyMaintainable;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.rice.krad.data.jpa.converters.BooleanYNConverter;
import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * This class maintains the attributes needed for a subaward budget line.
 */
@Entity
@Table(name = "BUDGET_SUB_AWARDS")
@IdClass(BudgetSubAwards.BudgetSubAwardsId.class)
public class BudgetSubAwards extends KcPersistableBusinessObjectBase implements HierarchyMaintainable, Comparable<BudgetSubAwards>, BudgetSubAwardsContract {

    private static final long serialVersionUID = -857485535655759499L;

    @Column(name = "BUDGET_ID", insertable = false, updatable = false)
    private Long budgetId;
    
    @Id
    @JoinColumn(name = "BUDGET_ID")
    @ManyToOne(cascade = {CascadeType.REFRESH})
    private ProposalDevelopmentBudgetExt budget;

    @Id
    @Column(name = "SUB_AWARD_NUMBER")
    private Integer subAwardNumber;

    @Transient
    private Integer budgetVersionNumber;

    @Column(name = "COMMENTS")
    private String comments;

    @Column(name = "ORGANIZATION_ID")
    private String organizationId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "ORGANIZATION_ID", referencedColumnName = "ORGANIZATION_ID", insertable = false, updatable = false)
    private Organization organization;

    @Column(name = "SUB_AWARD_STATUS_CODE")
    private Integer subAwardStatusCode;

    @Column(name = "FILE_DATA_ID")
    private String fileDataId;

    @Transient
    private transient SoftReference<byte[]> subAwardXfdFileData;

    @Column(name = "SUB_AWARD_XFD_FILE_NAME")
    private String subAwardXfdFileName;

    @Column(name = "XML_DATA_ID")
    private String xmlDataId;

    @Transient
    private transient SoftReference<String> subAwardXmlFileData;

    @Column(name = "TRANSLATION_COMMENTS")
    private String translationComments;

    @Column(name = "XFD_UPDATE_TIMESTAMP")
    private Timestamp xfdUpdateTimestamp;

    @Column(name = "XFD_UPDATE_USER")
    private String xfdUpdateUser;

    @Column(name = "XML_UPDATE_TIMESTAMP")
    private Timestamp xmlUpdateTimestamp;

    @Column(name = "XML_UPDATE_USER")
    private String xmlUpdateUser;

    @Column(name = "NAMESPACE")
    private String namespace;

    @Column(name = "FORM_NAME")
    private String formName;

    @OneToMany(mappedBy = "budgetSubAward", orphanRemoval = true, cascade = { CascadeType.ALL })
    private List<BudgetSubAwardAttachment> budgetSubAwardAttachments;

    @OneToMany(mappedBy = "budgetSubAward", orphanRemoval = true, cascade = { CascadeType.ALL })
    private List<BudgetSubAwardFiles> budgetSubAwardFiles;

    @OneToMany(mappedBy = "budgetSubAward", orphanRemoval = true, cascade = { CascadeType.ALL })
    @OrderBy("budgetPeriod")
    private List<BudgetSubAwardPeriodDetail> budgetSubAwardPeriodDetails;
    
    @Column(name = "HIERARCHY_PROPOSAL_NUMBER")
    private String hierarchyProposalNumber;

    @Column(name = "HIDE_IN_HIERARCHY")
    @Convert(converter = BooleanYNConverter.class)
    private boolean hiddenInHierarchy;

    @Transient
    private transient boolean edit = false;

    @Transient
    private transient MultipartFile newSubAwardFile;

    @Transient
    private transient boolean newSubAwardFileError = false;

    public BudgetSubAwards() {
        budgetSubAwardAttachments = new ArrayList<BudgetSubAwardAttachment>();
        budgetSubAwardFiles = new ArrayList<BudgetSubAwardFiles>();
        budgetSubAwardPeriodDetails = new ArrayList<BudgetSubAwardPeriodDetail>();
    }

    @Override
    public Long getBudgetId() {
        return budgetId;
    }

    public void setBudgetId(Long budgetId) {
        this.budgetId = budgetId;
    }

    @Override
    public Integer getSubAwardNumber() {
        return subAwardNumber;
    }

    public void setSubAwardNumber(Integer subAwardNumber) {
        this.subAwardNumber = subAwardNumber;
    }

    @Override
    public Integer getBudgetVersionNumber() {
        return budgetVersionNumber;
    }

    public void setBudgetVersionNumber(Integer budgetVersionNumber) {
        this.budgetVersionNumber = budgetVersionNumber;
    }

    @Override
    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public String getOrganizationName() {
    	if (organization != null) {
    		return organization.getOrganizationName();
    	} else {
    		return null;
    	}
    }

    @Override
    public Integer getSubAwardStatusCode() {
        return subAwardStatusCode;
    }

    public void setSubAwardStatusCode(Integer subAwardStatusCode) {
        this.subAwardStatusCode = subAwardStatusCode;
    }

    @Override
    public String getSubAwardXfdFileName() {
        return subAwardXfdFileName;
    }

    public void setSubAwardXfdFileName(String subAwardXfdFileName) {
        this.subAwardXfdFileName = subAwardXfdFileName;
    }

    @Override
    public String getTranslationComments() {
        return translationComments;
    }

    public void setTranslationComments(String translationComments) {
        this.translationComments = translationComments;
    }

    @Override
    public Timestamp getXfdUpdateTimestamp() {
        return xfdUpdateTimestamp;
    }

    public void setXfdUpdateTimestamp(Timestamp xfdUpdateTimestamp) {
        this.xfdUpdateTimestamp = xfdUpdateTimestamp;
    }

    @Override
    public String getXfdUpdateUser() {
        return xfdUpdateUser;
    }

    public void setXfdUpdateUser(String xfdUpdateUser) {
        this.xfdUpdateUser = xfdUpdateUser;
    }

    @Override
    public Timestamp getXmlUpdateTimestamp() {
        return xmlUpdateTimestamp;
    }

    public void setXmlUpdateTimestamp(Timestamp xmlUpdateTimestamp) {
        this.xmlUpdateTimestamp = xmlUpdateTimestamp;
    }

    @Override
    public String getXmlUpdateUser() {
        return xmlUpdateUser;
    }

    public void setXmlUpdateUser(String xmlUpdateUser) {
        this.xmlUpdateUser = xmlUpdateUser;
    }

    /**
     * Gets the budgetSubAwardAttachments attribute. 
     * @return Returns the budgetSubAwardAttachments.
     */
    @Override
    public List<BudgetSubAwardAttachment> getBudgetSubAwardAttachments() {
        if (budgetSubAwardAttachments == null) {
            budgetSubAwardAttachments = new ArrayList<BudgetSubAwardAttachment>();
        }
        return budgetSubAwardAttachments;
    }

    public String getAttachmentContentIds() {
        final String SEPARATOR = "; ";
        StringBuilder sb = new StringBuilder();
        for (BudgetSubAwardAttachment attachment : getBudgetSubAwardAttachments()) {
            sb.append(attachment.getName());
            sb.append(SEPARATOR);
        }
        sb.deleteCharAt(sb.length() - 2);
        return sb.toString();
    }

    /**
     * Sets the budgetSubAwardAttachments attribute value.
     * @param budgetSubAwardAttachments The budgetSubAwardAttachments to set.
     */
    public void setBudgetSubAwardAttachments(List<BudgetSubAwardAttachment> budgetSubAwardAttachments) {
        this.budgetSubAwardAttachments = budgetSubAwardAttachments;
    }

    /**
     * Gets the budgetSubAwardFiles attribute. 
     * @return Returns the budgetSubAwardFiles.
     */
    @Override
    public List<BudgetSubAwardFiles> getBudgetSubAwardFiles() {
        return budgetSubAwardFiles;
    }

    /**
     * Sets the budgetSubAwardFiles attribute value.
     * @param budgetSubAwardFiles The budgetSubAwardFiles to set.
     */
    public void setBudgetSubAwardFiles(List<BudgetSubAwardFiles> budgetSubAwardFiles) {
        this.budgetSubAwardFiles = budgetSubAwardFiles;
    }

    /**
     * Gets the hierarchyProposalNumber attribute. 
     * @return Returns the hierarchyProposalNumber.
     */
    @Override
    public String getHierarchyProposalNumber() {
        return hierarchyProposalNumber;
    }

    /**
     * Sets the hierarchyProposalNumber attribute value.
     * @param hierarchyProposalNumber The hierarchyProposalNumber to set.
     */
    public void setHierarchyProposalNumber(String hierarchyProposalNumber) {
        this.hierarchyProposalNumber = hierarchyProposalNumber;
    }

    /**
     * Gets the hiddenInHierarchy attribute. 
     * @return Returns the hiddenInHierarchy.
     */
    @Override
    public boolean isHiddenInHierarchy() {
        return hiddenInHierarchy;
    }

    /**
     * Sets the hiddenInHierarchy attribute value.
     * @param hiddenInHierarchy The hiddenInHierarchy to set.
     */
    public void setHiddenInHierarchy(boolean hiddenInHierarchy) {
        this.hiddenInHierarchy = hiddenInHierarchy;
    }

    /**
     * Gets the namespace attribute. 
     * @return Returns the namespace.
     */
    @Override
    public String getNamespace() {
        return namespace;
    }

    /**
     * Sets the namespace attribute value.
     * @param namespace The namespace to set.
     */
    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    /**
     * Gets the formName attribute. 
     * @return Returns the formName.
     */
    @Override
    public String getFormName() {
        return formName;
    }

    /**
     * Sets the formName attribute value.
     * @param formaName The formName to set.
     */
    public void setFormName(String formaName) {
        this.formName = formaName;
    }

    public boolean getEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }

    public MultipartFile getNewSubAwardFile() {
        return newSubAwardFile;
    }

    public void setNewSubAwardFile(MultipartFile newSubAwardFile) {
        this.newSubAwardFile = newSubAwardFile;
    }

    public boolean getNewSubAwardFileError() {
        return newSubAwardFileError;
    }

    public void setNewSubAwardFileError(boolean pNewSubAwardFileError) {
        this.newSubAwardFileError = pNewSubAwardFileError;
    }

    @Override
    public int compareTo(BudgetSubAwards o) {
        int retVal = -1;
        if (o != null) {
            retVal = ObjectUtils.compare(getOrganizationName(), o.getOrganizationName());
            if (retVal == 0) {
                retVal = ObjectUtils.compare(getComments(), o.getComments());
                if (retVal == 0) {
                    retVal = ObjectUtils.compare(getSubAwardXfdFileName(), o.getSubAwardXfdFileName());
                    if (retVal == 0) {
                        retVal = ObjectUtils.compare(getSubAwardNumber(), o.getSubAwardNumber());
                    }
                }
            }
        }
        return retVal;
    }

    @Override
    public List<BudgetSubAwardPeriodDetail> getBudgetSubAwardPeriodDetails() {
        return budgetSubAwardPeriodDetails;
    }

    public void setBudgetSubAwardPeriodDetails(List<BudgetSubAwardPeriodDetail> budgetSubAwardPeriodDetails) {
        this.budgetSubAwardPeriodDetails = budgetSubAwardPeriodDetails;
    }

    @Override
    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
    	if (organization != null) {
    		organizationId = organization.getOrganizationId();
    	}
        this.organization = organization;
    }

    public void computePeriodDetails() {
        for (BudgetSubAwardPeriodDetail detail : getBudgetSubAwardPeriodDetails()) {
            detail.computeTotal();
        }
    }

    public boolean hasModifiedAmounts() {
        for (BudgetSubAwardPeriodDetail detail : getBudgetSubAwardPeriodDetails()) {
            if (detail.isAmountsModified()) {
                return true;
            }
        }
        return false;
    }

    public static final class BudgetSubAwardsId implements Serializable, Comparable<BudgetSubAwardsId> {

        private Integer subAwardNumber;

        private Long budget;

        public Integer getSubAwardNumber() {
            return subAwardNumber;
        }

        public void setSubAwardNumber(Integer subAwardNumber) {
            this.subAwardNumber = subAwardNumber;
        }

        public Long getBudget() {
            return budget;
        }

        public void setBudget(Long budget) {
            this.budget = budget;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this).append("budget", this.budget).append("subAwardNumber", this.subAwardNumber).toString();
        }

        @Override
        public boolean equals(Object other) {
            if (other == null)
                return false;
            if (other == this)
                return true;
            if (other.getClass() != this.getClass())
                return false;
            final BudgetSubAwardsId rhs = (BudgetSubAwardsId) other;
            return new EqualsBuilder().append(this.budget, rhs.budget).append(this.subAwardNumber, rhs.subAwardNumber).isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(this.budget).append(this.subAwardNumber).toHashCode();
        }

        @Override
        public int compareTo(BudgetSubAwardsId other) {
            return new CompareToBuilder().append(this.budget, other.budget).append(this.subAwardNumber, other.subAwardNumber).toComparison();
        }
    }

	public ProposalDevelopmentBudgetExt getBudget() {
		return budget;
	}

	public void setBudget(ProposalDevelopmentBudgetExt budget) {
		this.budget = budget;
	}

    public String getFileDataId() {
        return fileDataId;
    }

    public void setFileDataId(String fileDataId) {
        this.fileDataId = fileDataId;
    }

    public String getXmlDataId() {
        return xmlDataId;
    }

    public void setXmlDataId(String xmlDataId) {
        this.xmlDataId = xmlDataId;
    }

    @Override
    public byte[] getSubAwardXfdFileData() {
        if (subAwardXfdFileData != null) {
            byte[] existingData = subAwardXfdFileData.get();
            if (existingData != null) {
                return existingData;
            }
        }
        //if we didn't have a softreference, grab the data from the db
        byte[] newData = getKcAttachmentDao().getData(fileDataId);
        subAwardXfdFileData = new SoftReference<byte[]>(newData);
        return newData;
    }

    public void setSubAwardXfdFileData(byte[] subAwardXfdFileData) {
        if (subAwardXfdFileData == null) {
            getKcAttachmentDao().removeData(fileDataId);
            fileDataId = null;
        } else {
            fileDataId = getKcAttachmentDao().saveData(subAwardXfdFileData, fileDataId);
        }
        this.subAwardXfdFileData = new SoftReference<byte[]>(subAwardXfdFileData);
    }

    @Override
    public String getSubAwardXmlFileData() {
        if (subAwardXmlFileData != null) {
            return subAwardXmlFileData.get();
        }
        //if we didn't have a softreference, grab the data from the db
        byte[] newData = getKcAttachmentDao().getData(xmlDataId);

        String newString = newData != null ? new String(newData) : null;
        subAwardXmlFileData = new SoftReference<String>(newString);
        return newString;
    }

    public void setSubAwardXmlFileData(String subAwardXmlFileData) {
        if (subAwardXmlFileData == null) {
            getKcAttachmentDao().removeData(xmlDataId);
            xmlDataId = null;
        } else {
            xmlDataId = getKcAttachmentDao().saveData(subAwardXmlFileData.getBytes(StandardCharsets.UTF_8), xmlDataId);
        }
        this.subAwardXmlFileData = new SoftReference<String>(subAwardXmlFileData);
    }

    private KcAttachmentDataDao getKcAttachmentDao() {
        return KcServiceLocator.getService(KcAttachmentDataDao.class);
    }

    @PostRemove
    public void removeData() {
        if (getFileDataId() != null) {
            getKcAttachmentDao().removeData(getFileDataId());
        }
        if (getXmlDataId() != null) {
            getKcAttachmentDao().removeData(getXmlDataId());
        }
    }

}
