/*
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
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
package org.kuali.coeus.propdev.impl.budget.subaward;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.struts.upload.FormFile;
import org.kuali.coeus.common.framework.org.Organization;
import org.kuali.coeus.propdev.api.budget.subaward.BudgetSubAwardsContract;
import org.kuali.coeus.propdev.impl.hierarchy.HierarchyMaintainable;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.rice.krad.data.jpa.converters.BooleanYNConverter;

/**
 * 
 * This class maintains the attributes needed for a subaward budget line.
 */
@Entity
@Table(name = "BUDGET_SUB_AWARDS")
@IdClass(BudgetSubAwards.BudgetSubAwardsId.class)
public class BudgetSubAwards extends KcPersistableBusinessObjectBase implements HierarchyMaintainable, Comparable<BudgetSubAwards>, BudgetSubAwardsContract {

    private static final long serialVersionUID = -857485535655759499L;

    @Transient
    private String proposalNumber;

    @Column(name = "BUDGET_ID")
    @Id
    private Long budgetId;

    @Id
    @Column(name = "SUB_AWARD_NUMBER")
    private Integer subAwardNumber;

    @Transient
    private Integer budgetVersionNumber;

    @Column(name = "COMMENTS")
    private String comments;

    @Column(name = "ORGANIZATION_ID")
    private String organizationId;

    @Column(name = "ORGANIZATION_NAME")
    private String organizationName;

    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "ORGANIZATION_ID", referencedColumnName = "ORGANIZATION_ID", insertable = false, updatable = false)
    private Organization organization;

    @Column(name = "SUB_AWARD_STATUS_CODE")
    private Integer subAwardStatusCode;

    @Column(name = "SUB_AWARD_XFD_FILE")
    private byte[] subAwardXfdFileData;

    @Column(name = "SUB_AWARD_XFD_FILE_NAME")
    private String subAwardXfdFileName;

    @Column(name = "SUB_AWARD_XML_FILE")
    @Lob
    private String subAwardXmlFileData;

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

    @OneToMany(orphanRemoval = true, cascade = { CascadeType.ALL })
    @JoinColumns({ @JoinColumn(name = "BUDGET_ID", referencedColumnName = "BUDGET_ID"), @JoinColumn(name = "SUB_AWARD_NUMBER", referencedColumnName = "SUB_AWARD_NUMBER") })
    private List<BudgetSubAwardAttachment> budgetSubAwardAttachments;

    @OneToMany(orphanRemoval = true, cascade = { CascadeType.ALL })
    @JoinColumns({ @JoinColumn(name = "BUDGET_ID", referencedColumnName = "BUDGET_ID"), @JoinColumn(name = "SUB_AWARD_NUMBER", referencedColumnName = "SUB_AWARD_NUMBER") })
    private List<BudgetSubAwardFiles> budgetSubAwardFiles;

    @OneToMany(orphanRemoval = true, cascade = { CascadeType.ALL })
    @JoinColumns({ @JoinColumn(name = "BUDGET_ID", referencedColumnName = "BUDGET_ID"), @JoinColumn(name = "SUB_AWARD_NUMBER", referencedColumnName = "SUBAWARD_NUMBER") })
    private List<BudgetSubAwardPeriodDetail> budgetSubAwardPeriodDetails;

    @Column(name = "HIERARCHY_PROPOSAL_NUMBER")
    private String hierarchyProposalNumber;

    @Column(name = "HIDE_IN_HIERARCHY")
    @Convert(converter = BooleanYNConverter.class)
    private boolean hiddenInHierarchy;

    @Transient
    private transient boolean edit = false;

    @Transient
    private transient FormFile newSubAwardFile;

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
    public String getProposalNumber() {
        return proposalNumber;
    }

    public void setProposalNumber(String proposalNumber) {
        this.proposalNumber = proposalNumber;
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
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
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

    @Override
    public byte[] getSubAwardXfdFileData() {
        return subAwardXfdFileData;
    }

    public void setSubAwardXfdFileData(byte[] subAwardXfdFileData) {
        this.subAwardXfdFileData = subAwardXfdFileData;
    }

    @Override
    public String getSubAwardXmlFileData() {
        return subAwardXmlFileData;
    }

    public void setSubAwardXmlFileData(String subAwardXmlFileData) {
        this.subAwardXmlFileData = subAwardXmlFileData;
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

    public FormFile getNewSubAwardFile() {
        return newSubAwardFile;
    }

    public void setNewSubAwardFile(FormFile newSubAwardFile) {
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
        if (!StringUtils.equals(this.organizationId, organizationId)) {
            this.organizationId = organizationId;
            refreshReferenceObject("organization");
            if (getOrganization() != null) {
                setOrganizationName(getOrganization().getOrganizationName());
            }
        }
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
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

        private Long budgetId;

        public Integer getSubAwardNumber() {
            return subAwardNumber;
        }

        public void setSubAwardNumber(Integer subAwardNumber) {
            this.subAwardNumber = subAwardNumber;
        }

        public Long getBudgetId() {
            return budgetId;
        }

        public void setBudgetId(Long budgetId) {
            this.budgetId = budgetId;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this).append("budgetId", this.budgetId).append("subAwardNumber", this.subAwardNumber).toString();
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
            return new EqualsBuilder().append(this.budgetId, rhs.budgetId).append(this.subAwardNumber, rhs.subAwardNumber).isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(this.budgetId).append(this.subAwardNumber).toHashCode();
        }

        @Override
        public int compareTo(BudgetSubAwardsId other) {
            return new CompareToBuilder().append(this.budgetId, other.budgetId).append(this.subAwardNumber, other.subAwardNumber).toComparison();
        }
    }
}
