/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
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
package org.kuali.kra.protocol;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.kuali.kra.SequenceOwner;
import org.kuali.kra.UnitAclLoadable;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.coi.Disclosurable;
import org.kuali.kra.common.permissions.Permissionable;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.RoleConstants;

public abstract class Protocol extends KraPersistableBusinessObjectBase implements SequenceOwner<Protocol>, Permissionable, UnitAclLoadable, Disclosurable {
    
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -5556152547067349988L;
    
    private Integer sequenceNumber;
    private Long protocolId;
    private String protocolNumber; 
    
    private boolean active = true;
    private String protocolTypeCode; 
    private String protocolStatusCode;
    private String title; 
    private String description;
    
    private Date initialSubmissionDate;
    private Date approvalDate; 
    private Date expirationDate; 
    private Date lastApprovalDate; 
    private String fdaApplicationNumber; 
    private String referenceNumber1; 
    private String referenceNumber2;
    
    // NOTE: The assignment below defaults to IRB
    private String specialReviewIndicator = "Y";
    
    private String keyStudyPersonIndicator; 
    private String fundingSourceIndicator; 
    private String correspondentIndicator; 
    private String referenceIndicator; 
    
    // lookup field
    private String leadUnitName;

    private String principalInvestigatorId;
    
    public Long getProtocolId() {
        return protocolId;
    }

    public void setProtocolId(Long protocolId) {
        this.protocolId = protocolId;
    }

    public String getProtocolNumber() {
        return protocolNumber;
    }

    public void setProtocolNumber(String protocolNumber) {
        this.protocolNumber = protocolNumber;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    
    public boolean isActive() {
        return active;
    }
    
    public String getProtocolTypeCode() {
        return protocolTypeCode;
    }

    public void setProtocolTypeCode(String protocolTypeCode) {
        this.protocolTypeCode = protocolTypeCode;
    }

    public String getProtocolStatusCode() {
        return protocolStatusCode;
    }

    public void setProtocolStatusCode(String protocolStatusCode) {
        this.protocolStatusCode = protocolStatusCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public Date getInitialSubmissionDate() {
        return initialSubmissionDate;
    }
    
    public void setInitialSubmissionDate(Date initialSubmissionDate) {
        this.initialSubmissionDate = initialSubmissionDate;
    }
    
    public Date getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(Date approvalDate) {
        this.approvalDate = approvalDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Date getLastApprovalDate() {
        return lastApprovalDate;
    }

    public void setLastApprovalDate(Date lastApprovalDate) {
        this.lastApprovalDate = lastApprovalDate;
    }

    public String getFdaApplicationNumber() {
        return fdaApplicationNumber;
    }

    public void setFdaApplicationNumber(String fdaApplicationNumber) {
        this.fdaApplicationNumber = fdaApplicationNumber;
    }

    public String getReferenceNumber1() {
        return referenceNumber1;
    }

    public void setReferenceNumber1(String referenceNumber1) {
        this.referenceNumber1 = referenceNumber1;
    }

    public String getReferenceNumber2() {
        return referenceNumber2;
    }

    public void setReferenceNumber2(String referenceNumber2) {
        this.referenceNumber2 = referenceNumber2;
    }
    
    public String getSpecialReviewIndicator() {
        return specialReviewIndicator;
    }

    public void setSpecialReviewIndicator(String specialReviewIndicator) {
        this.specialReviewIndicator = specialReviewIndicator;
    }
    
    public String getKeyStudyPersonIndicator() {
        return keyStudyPersonIndicator;
    }

    public void setKeyStudyPersonIndicator(String keyStudyPersonIndicator) {
        this.keyStudyPersonIndicator = keyStudyPersonIndicator;
    }

    public String getFundingSourceIndicator() {
        return fundingSourceIndicator;
    }

    public void setFundingSourceIndicator(String fundingSourceIndicator) {
        this.fundingSourceIndicator = fundingSourceIndicator;
    }

    public String getCorrespondentIndicator() {
        return correspondentIndicator;
    }

    public void setCorrespondentIndicator(String correspondentIndicator) {
        this.correspondentIndicator = correspondentIndicator;
    }

    public String getReferenceIndicator() {
        return referenceIndicator;
    }

    public void setReferenceIndicator(String referenceIndicator) {
        this.referenceIndicator = referenceIndicator;
    }
    
    
    
    /**
     * 
     * @see org.kuali.kra.common.permissions.Permissionable#getDocumentKey()
     */
    public String getDocumentKey() {
        return Permissionable.PROTOCOL_KEY;
    }

    
    /**
     * 
     * @see org.kuali.kra.common.permissions.Permissionable#getDocumentNumberForPermission()
     */
    public String getDocumentNumberForPermission() {
        return protocolNumber;
    }
    
    
    /**
     * 
     * @see org.kuali.kra.common.permissions.Permissionable#getRoleNames()
     */
    public List<String> getRoleNames() {
        List<String> roleNames = new ArrayList<String>();

        roleNames.add(RoleConstants.PROTOCOL_AGGREGATOR);
        roleNames.add(RoleConstants.PROTOCOL_VIEWER);

        return roleNames;
    }
    
    
    
    /**
     * @see org.kuali.kra.coi.Disclosurable#getProjectName()
     */
    @Override
    public String getProjectName() {
        return getTitle();
    }

    
    
    /**
     * @see org.kuali.kra.coi.Disclosurable#getProjectId()
     */
    @Override
    public String getProjectId() {
        return getProtocolNumber();
    }

    
    /**
     * @see org.kuali.kra.Sequenceable#getSequenceNumber()
     */
    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }
    
    /** {@inheritDoc} */
    public Integer getOwnerSequenceNumber() {
        return null;
    }
    
    /**
     * @see org.kuali.kra.SequenceOwner#getVersionNameField()
     */
    public String getVersionNameField() {
        return "protocolNumber";
    }

    /** {@inheritDoc} */
    public void incrementSequenceNumber() {
        this.sequenceNumber++; 
    }

    /** {@inheritDoc} */
    public Protocol getSequenceOwner() {
        return this;
    }

    /** {@inheritDoc} */
    public void setSequenceOwner(Protocol newOwner) {
       //no-op
    }
    
    /** {@inheritDoc} */
    public void resetPersistenceState() {
        this.protocolId = null;
    }
    
    // NOTE: will have to override if namespace is different
    public String getNamespace() {
        return Constants.MODULE_NAMESPACE_PROTOCOL;
    }
    
    /**
     * 
     * @see org.kuali.kra.UnitAclLoadable#getUnitNumberOfDocument()
     */
    public String getDocumentUnitNumber() {
        return getLeadUnitNumber();
    }

    /**
     * 
     * @see org.kuali.kra.UnitAclLoadable#getDocumentRoleTypeCode()
     */
    public String getDocumentRoleTypeCode() {
        return RoleConstants.PROTOCOL_ROLE_TYPE;
    }

    public void populateAdditionalQualifiedRoleAttributes(Map<String, String> qualifiedRoleAttributes) {
        return;
    }
    
    
    // TODO This method implementation requires protocol associate BOs and services
    public String getLeadUnitNumber() {
        return "";
    }
    
    // TODO This method implementation requires protocol associate BOs and services
    public String getLeadUnitName() {
       return "";
    }
    
    public void setLeadUnitName(String leadUnitName) {
        this.leadUnitName = leadUnitName;
    }
    
    // TODO This method implementation requires protocol associate BOs and/or services
    public String getPrincipalInvestigatorId() {      
        return "";
    }
    
    public void setPrincipalInvestigatorId(String principalInvestigatorId) {
        this.principalInvestigatorId = principalInvestigatorId;
    }

}
